/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.js.loader.modules.extender;

import aQute.bnd.osgi.Constants;

import aQute.lib.converter.Converter;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;

import java.net.URL;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListMap;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import org.osgi.framework.Bundle;
import org.osgi.framework.Filter;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.Version;
import org.osgi.framework.wiring.BundleCapability;
import org.osgi.framework.wiring.BundleWire;
import org.osgi.framework.wiring.BundleWiring;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

/**
 * @author Raymond Augé
 */
@Component(
	immediate = true,
	property = {
		"osgi.http.whiteboard.servlet.name=JS Loader Modules Servlet",
		"osgi.http.whiteboard.servlet.pattern=/js_loader_modules",
		"service.ranking:Integer=" + Details.MAX_VALUE_LESS_1K
	},
	service = {JSLoaderModulesServlet.class, Servlet.class}
)
public class JSLoaderModulesServlet extends HttpServlet
	implements Servlet, ServiceTrackerCustomizer
		<ServletContext, ServiceReference<ServletContext>> {

	@Override
	public ServiceReference<ServletContext> addingService(
		ServiceReference<ServletContext> serviceReference) {

		String contextPath = (String)serviceReference.getProperty(
			"osgi.web.contextpath");

		JSLoaderModule jsLoaderModule = new JSLoaderModule(
			serviceReference.getBundle(), contextPath);

		_jsLoaderModules.put(serviceReference, jsLoaderModule);

		return serviceReference;
	}

	@Override
	public void modifiedService(
		ServiceReference<ServletContext> serviceReference,
		ServiceReference<ServletContext> trackedServiceReference) {

		removedService(serviceReference, trackedServiceReference);

		addingService(serviceReference);
	}

	@Override
	public void removedService(
		ServiceReference<ServletContext> serviceReference,
		ServiceReference<ServletContext> trackedServiceReference) {

		_jsLoaderModules.remove(serviceReference);
	}

	public class JSLoaderModule {

		public JSLoaderModule(Bundle bundle, String contextPath) {
			_bundle = bundle;
			_contextPath = contextPath;

			Version version = _bundle.getVersion();

			_version = version.toString();

			BundleWiring bundleWiring = _bundle.adapt(BundleWiring.class);

			List<BundleCapability> bundleCapabilities =
				bundleWiring.getCapabilities(Details.OSGI_WEBRESOURCE);

			if (bundleCapabilities.isEmpty()) {
				_name = _bundle.getSymbolicName();

				return;
			}

			BundleCapability bundleCapability = bundleCapabilities.get(0);

			Map<String, Object> attributes = bundleCapability.getAttributes();

			_name = (String)attributes.get(Details.OSGI_WEBRESOURCE);

			URL url = _bundle.getEntry(Details.CONFIG_JSON);

			_urlToConfiguration(url, bundleWiring);
		}

		public String getContextPath() {
			return _contextPath;
		}

		public String getName() {
			return _name;
		}

		public String getUnversionedConfiguration() {
			return _unversionedConfiguration;
		}

		public String getVersion() {
			return _version;
		}

		public String getVersionedConfiguration() {
			return _versionedConfiguration;
		}

		private String _generateConfiguration(
			JSONObject jsonObject, BundleWiring bundleWiring,
			boolean versionedModuleName) {

			if (!_details.applyVersioning()) {
				if (versionedModuleName) {
					return "";
				}

				return jsonObject.toString();
			}

			List<BundleWire> bundleWires = bundleWiring.getRequiredWires(
				Details.OSGI_WEBRESOURCE);

			JSONArray namesJSONArray = jsonObject.names();

			for (int i = 0; i < namesJSONArray.length(); i++) {
				String name = (String)namesJSONArray.get(i);

				int x = name.indexOf('/');

				if (x == -1) {
					continue;
				}

				String moduleName = name.substring(0, x);

				if (!moduleName.equals(getName())) {
					continue;
				}

				String modulePath = name.substring(x);

				moduleName = getName() + "@" + getVersion() + modulePath;

				JSONObject nameJSONObject = jsonObject.getJSONObject(name);

				JSONArray dependenciesJSONArray = nameJSONObject.getJSONArray(
					"dependencies");

				for (int j = 0; j < dependenciesJSONArray.length(); j++) {
					String dependency = dependenciesJSONArray.getString(j);

					int y = dependency.indexOf('/');

					if (y == -1) {
						continue;
					}

					String dependencyName = dependency.substring(0, y);
					String dependencyPath = dependency.substring(y);

					if (dependencyName.equals(getName())) {
						dependencyName =
							getName() + "@" + getVersion() + dependencyPath;

						dependenciesJSONArray.put(j, dependencyName);
					}
					else {
						_normalizeDependencies(
							dependencyName, dependencyPath,
							dependenciesJSONArray, j, bundleWires);
					}
				}

				if (versionedModuleName) {
					jsonObject.remove(name);

					jsonObject.put(moduleName, nameJSONObject);
				}
				else {
					jsonObject.put(name, nameJSONObject);
				}
			}

			return jsonObject.toString();
		}

		private String _normalize(String jsonString) {
			if (jsonString.startsWith("{") && jsonString.endsWith("}")) {
				jsonString = jsonString.substring(1, jsonString.length() - 1);
			}

			return jsonString;
		}

		private void _normalizeDependencies(
			String dependencyName, String dependencyPath, JSONArray jsonArray,
			int index, List<BundleWire> bundleWires) {

			for (BundleWire bundleWire : bundleWires) {
				BundleCapability bundleCapability = bundleWire.getCapability();

				Map<String, Object> attributes =
					bundleCapability.getAttributes();

				String attributesDependencyName = (String)attributes.get(
					Details.OSGI_WEBRESOURCE);

				if (!attributesDependencyName.equals(dependencyName)) {
					continue;
				}

				Version version = (Version)attributes.get(
					Constants.VERSION_ATTRIBUTE);

				dependencyName =
					dependencyName + "@" + version.toString() + dependencyPath;

				jsonArray.put(index, dependencyName);

				return;
			}
		}

		private void _urlToConfiguration(URL url, BundleWiring bundleWiring) {
			if (url == null) {
				return;
			}

			try (Reader reader = new InputStreamReader(url.openStream())) {
				JSONTokener jsonTokener = new JSONTokener(reader);

				JSONObject jsonObject = new JSONObject(jsonTokener);

				_unversionedConfiguration = _normalize(
					_generateConfiguration(jsonObject, bundleWiring, false));
				_versionedConfiguration = _normalize(
					_generateConfiguration(jsonObject, bundleWiring, true));
			}
			catch (IOException ioe) {
				throw new RuntimeException(ioe);
			}
		}

		private final Bundle _bundle;
		private final String _contextPath;
		private final String _name;
		private String _unversionedConfiguration = "";
		private final String _version;
		private String _versionedConfiguration = "";

	}

	@Activate
	@Modified
	protected void activate(
			ComponentContext componentContext, Map<String, Object> properties)
		throws Exception {

		if (_serviceTracker != null) {
			_serviceTracker.close();
		}

		setDetails(Converter.cnv(Details.class, properties));

		Filter filter = FrameworkUtil.createFilter(
			"(&(objectClass=" + ServletContext.class.getName() +
				")(osgi.web.contextpath=*))");

		_serviceTracker = new ServiceTracker<>(
			componentContext.getBundleContext(), filter, this);

		_serviceTracker.open();
	}

	@Deactivate
	protected void deactivate() {
		_serviceTracker.close();

		_serviceTracker = null;
	}

	protected long getTrackingCount() {
		return _serviceTracker.getTrackingCount();
	}

	@Override
	protected void service(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException {

		response.setContentType(Details.CONTENT_TYPE);

		PrintWriter writer = response.getWriter();

		writer.println("(function() {");
		writer.print(_details.globalJSVariable());
		writer.println(".PATHS = {");

		Collection<JSLoaderModule> values = _jsLoaderModules.values();

		Set<String> _processed = new HashSet<>();
		String deliminter = "";

		for (JSLoaderModule loaderModule : values) {
			String contextPath = loaderModule.getContextPath();
			String name = loaderModule.getName();
			String version = loaderModule.getVersion();

			writer.write(deliminter);
			writer.write("'");
			writer.write(name);
			writer.write('@');
			writer.write(version);
			writer.write("': '");
			writer.write(contextPath);
			writer.write("'");

			if (!_processed.contains(name)) {
				_processed.add(name);

				writer.println(",");
				writer.write("'");
				writer.write(name);
				writer.write("': '");
				writer.write(contextPath);
				writer.write("'");
			}

			deliminter = ",\n";
		}

		writer.println("\n};");
		writer.print(_details.globalJSVariable());
		writer.println(".MODULES = {");

		_processed = new HashSet<>();
		deliminter = "";

		for (JSLoaderModule loaderModule : values) {
			String name = loaderModule.getName();
			String unversionedConfig = loaderModule.getUnversionedConfiguration();
			String versionedConfig = loaderModule.getVersionedConfiguration();

			if (unversionedConfig.length() == 0) {
				continue;
			}

			if (!_processed.contains(name)) {
				_processed.add(name);

				writer.write(deliminter);
				writer.write(unversionedConfig);
				deliminter = ",\n";
			}

			if (versionedConfig.length() > 0) {
				writer.write(deliminter);
				writer.write(versionedConfig);

				deliminter = ",\n";
			}
		}

		writer.println("\n};");
		writer.print(_details.globalJSVariable());
		writer.println(".MAPS = {");

		_processed = new HashSet<>();
		deliminter = "";

		for (JSLoaderModule loaderModule : values) {
			String name = loaderModule.getName();
			String version = loaderModule.getVersion();

			if (_processed.contains(name)) {
				continue;
			}

			_processed.add(name);

			writer.write(deliminter);
			writer.write("'");
			writer.write(name);
			writer.write("': '");
			writer.write(name);
			writer.write('@');
			writer.write(version);
			writer.write("'");

			deliminter = ",\n";
		}

		writer.println("\n};");
		writer.println("}());");
		writer.close();
	}

	protected void setDetails(Details details) {
		_details = details;
	}

	private volatile Details _details;
	private final Map<ServiceReference <ServletContext>, JSLoaderModule>
		_jsLoaderModules = new ConcurrentSkipListMap<>();
	private ServiceTracker<ServletContext, ServiceReference<ServletContext>>
		_serviceTracker;

}