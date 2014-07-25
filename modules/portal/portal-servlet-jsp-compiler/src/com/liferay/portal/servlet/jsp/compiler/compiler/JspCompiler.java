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

package com.liferay.portal.servlet.jsp.compiler.compiler;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.kernel.xml.XPath;
import com.liferay.portal.servlet.jsp.compiler.compiler.internal.JspResolverFactory;
import com.liferay.portal.util.ClassLoaderUtil;

import java.io.File;
import java.io.IOException;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import java.security.CodeSource;
import java.security.ProtectionDomain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.servlet.ServletContext;

import javax.tools.JavaFileManager;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;

import org.apache.jasper.Constants;
import org.apache.jasper.JspCompilationContext;
import org.apache.jasper.compiler.ErrorDispatcher;
import org.apache.jasper.compiler.Jsr199JavaCompiler;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.wiring.BundleRequirement;
import org.osgi.framework.wiring.BundleRevision;
import org.osgi.framework.wiring.BundleWire;
import org.osgi.framework.wiring.BundleWiring;

import org.phidias.compile.BundleJavaManager;

/**
 * @author Raymond Augé
 * @author Miguel Pastor
 */
public class JspCompiler extends Jsr199JavaCompiler {

	@Override
	public void init(
		JspCompilationContext jspCompilationContext,
		ErrorDispatcher errorDispatcher, boolean suppressLogging) {

		super.init(jspCompilationContext, errorDispatcher, suppressLogging);

		ServletContext servletContext =
			jspCompilationContext.getServletContext();

		BundleContext bundleContext =
			(BundleContext)servletContext.getAttribute("osgi-bundlecontext");

		_bundle = bundleContext.getBundle();

		initClassPath(servletContext);
		initTLDMappings(servletContext);
	}

	protected void addBundleWirings(BundleJavaManager bundleJavaManager) {
		Bundle bundle = FrameworkUtil.getBundle(getClass());

		BundleWiring bundleWiring = bundle.adapt(BundleWiring.class);

		List<BundleWire> providedWires = bundleWiring.getRequiredWires(null);

		for (BundleWire bundleWire : providedWires) {
			BundleWiring providerWiring = bundleWire.getProviderWiring();

			bundleJavaManager.addBundleWiring(providerWiring);
		}

		List<BundleRequirement> requirements = bundleWiring.getRequirements(
			BundleRevision.PACKAGE_NAMESPACE);

		for (BundleRequirement bundleRequirement : requirements) {
			bundleJavaManager.addBundleRequirement(bundleRequirement);
		}
	}

	protected void addDependenciesToClassPath() {
		for (String className : _JSP_COMPILER_DEPENDENCIES) {
			File file = new File(className);

			if (file.exists() && file.canRead()) {
				if (_classPath.contains(file)) {
					_classPath.remove(file);
				}

				_classPath.add(0, file);

				continue;
			}

			try {
				Class<?> clazz = Class.forName(
					className, true, ClassLoaderUtil.getPortalClassLoader());

				addDependencyToClassPath(clazz);

				continue;
			}
			catch (ClassNotFoundException e) {
			}

			if (_log.isErrorEnabled()) {
				_log.error(
					"Could not add depedency {" + className +
						"} to the classpath");
			}
		}
	}

	protected void addDependencyToClassPath(Class<?> clazz) {
		ProtectionDomain protectionDomain = clazz.getProtectionDomain();

		if (protectionDomain == null) {
			return;
		}

		CodeSource codeSource = protectionDomain.getCodeSource();

		URL url = codeSource.getLocation();

		try {
			File file = new File(toURI(url));

			if (file.exists() && file.canRead()) {
				if (_classPath.contains(file)) {
					_classPath.remove(file);
				}

				_classPath.add(0, file);
			}
		}
		catch (Exception use) {
			_log.error(use, use);
		}
	}

	@Override
	protected JavaFileManager getJavaFileManager(
		JavaFileManager javaFileManager) {

		if (javaFileManager instanceof StandardJavaFileManager) {
			StandardJavaFileManager standardJavaFileManager =
				(StandardJavaFileManager)javaFileManager;

			try {
				standardJavaFileManager.setLocation(
					StandardLocation.CLASS_PATH, _classPath);

				if (_log.isTraceEnabled()) {
					options.add("-verbose");
				}

				BundleJavaManager bundleJavaManager = new BundleJavaManager(
					_bundle, standardJavaFileManager, options, true);

				addBundleWirings(bundleJavaManager);

				bundleJavaManager.setResourceResolver(
					JspResolverFactory.getResourceResolver());

				javaFileManager = bundleJavaManager;
			}
			catch (IOException ioe) {
				_log.error(ioe, ioe);
			}
		}

		return super.getJavaFileManager(javaFileManager);
	}

	protected String getTldUri(URL url) {
		try {
			Document document = SAXReaderUtil.read(url, false);

			XPath xPath = SAXReaderUtil.createXPath(
				"/ns:taglib/ns:uri/text()", "ns",
				"http://java.sun.com/xml/ns/j2ee");

			Node node = xPath.selectSingleNode(document);

			if (node != null) {
				return node.asXML();
			}

			return document.valueOf("/taglib/uri/text()");
		}
		catch (DocumentException e) {
			return null;
		}
	}

	protected void initClassPath(ServletContext servletContext) {
		_lock.lock();

		try {
			_classPath = (ArrayList<File>)servletContext.getAttribute(
				_JSP_COMPILER_CLASS_PATH);

			if (_classPath != null) {
				return;
			}

			_classPath = new ArrayList<File>();

			addDependenciesToClassPath();

			servletContext.setAttribute(_JSP_COMPILER_CLASS_PATH, _classPath);
		}
		finally {
			_lock.unlock();
		}
	}

	protected void initTLDMappings(ServletContext servletContext) {
		Map<String, String[]> tldMappings =
			(Map<String, String[]>)servletContext.getAttribute(
				Constants.JSP_TLD_URI_TO_LOCATION_MAP);

		if (tldMappings != null) {
			return;
		}

		tldMappings = new HashMap<String, String[]>();

		tldMappings.put(
			"http://java.sun.com/jsp/jstl/core",
			new String[] {"/WEB-INF/tld/c.tld", null});

		BundleWiring bundleWiring = _bundle.adapt(BundleWiring.class);

		Collection<String> wiringEntries = bundleWiring.listResources(
			"/", "*.tld", BundleWiring.FINDENTRIES_RECURSE);

		Iterator<String> iterator = wiringEntries.iterator();

		while (iterator.hasNext()) {
			String resourcePath = iterator.next();

			URL url = _bundle.getResource(resourcePath);

			String uri = getTldUri(url);

			if (uri != null) {
				tldMappings.put(
					uri,
					new String[] {
						StringPool.SLASH.concat(resourcePath), null});
			}
		}

		servletContext.setAttribute(
			Constants.JSP_TLD_URI_TO_LOCATION_MAP, tldMappings);
	}

	protected URI toURI(URL url)
		throws MalformedURLException, URISyntaxException {

		String protocol = url.getProtocol();

		if (protocol.equals("reference")) {
			String file = url.getFile();

			url = new URL(file);

			protocol = url.getProtocol();
		}

		if (protocol.equals("file")) {
			return url.toURI();
		}
		else if (protocol.equals("jar")) {
			String file = url.getFile();

			return new URI(StringUtil.extractFirst(file, "!/"));
		}

		throw new URISyntaxException(
			url.toString(), "Unknown protocol " + protocol);
	}

	private static final String _JSP_COMPILER_CLASS_PATH =
		JspCompiler.class.getName() + "#JSP_COMPILER_CLASS_PATH";

	private static final String[] _JSP_COMPILER_DEPENDENCIES = {
		"com.liferay.portal.kernel.exception.PortalException",
		"com.liferay.portal.util.PortalImpl",
		"javax.portlet.PortletException", "javax.servlet.ServletException"
	};

	private static Log _log = LogFactoryUtil.getLog(JspCompiler.class);

	private Bundle _bundle;
	private List<File> _classPath;
	private Lock _lock = new ReentrantLock();

}