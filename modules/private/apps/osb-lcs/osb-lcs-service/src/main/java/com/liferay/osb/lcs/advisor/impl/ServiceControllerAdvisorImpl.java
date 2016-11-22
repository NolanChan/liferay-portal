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

package com.liferay.osb.lcs.advisor.impl;

import com.liferay.osb.lcs.advisor.ServiceControllerAdvisor;
import com.liferay.osb.lcs.annotation.JSONWebServiceParameter;
import com.liferay.portal.kernel.exception.NoSuchModelException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.UnsupportedEncodingException;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.net.URLDecoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

/**
 * @author Igor Beslic
 */
@Component(
	configurationPid = "com.liferay.osb.lcs.configuration.OSBLCSConfiguration",
	configurationPolicy = ConfigurationPolicy.REQUIRE, immediate = true,
	service = ServiceControllerAdvisor.class
)
public class ServiceControllerAdvisorImpl implements ServiceControllerAdvisor {

	@Override
	public String execute(Map<String, String> serviceControllerMap)
		throws Exception {

		String controllerClassName =
			"com.liferay.osb.lcs.service.controller." +
				serviceControllerMap.get("version") + "." +
					serviceControllerMap.get("controller");

		Class<?> controllerClass = Class.forName(controllerClassName);

		Object controller = _controllers.get(controllerClass);

		if (controller == null) {
			controller = controllerClass.newInstance();

			_controllers.put(controllerClass, controller);
		}

		MethodConfiguration methodConfiguration = resolveMethod(
			controllerClass, serviceControllerMap);

		if (methodConfiguration == null) {
			throw new UnsupportedOperationException(
				"Unable to resolve method in " + controllerClassName);
		}

		Method method = methodConfiguration._method;

		try {
			Object result = method.invoke(
				controller, methodConfiguration._arguments);

			return String.valueOf(result);
		}
		catch (Exception e) {
			if (e instanceof InvocationTargetException) {
				if (e.getCause() instanceof NoSuchMethodError) {
					NoSuchMethodError noSuchMethodError =
						(NoSuchMethodError)e.getCause();

					throw new RuntimeException(noSuchMethodError.getMessage());
				}
				else if (e.getCause() instanceof NoSuchModelException) {
					throw (Exception)e.getCause();
				}
			}

			throw e;
		}
	}

	protected MethodConfiguration resolveMethod(
		Class<?> controllerClass, Map<String, String> controllerMap) {

		List<MethodConfiguration> methodConfigurations = new ArrayList<>();

		for (Method method : controllerClass.getMethods()) {
			if (!method.isAnnotationPresent(JSONWebService.class)) {
				continue;
			}

			JSONWebService jsonWebService = method.getAnnotation(
				JSONWebService.class);

			if (jsonWebService.mode() == JSONWebServiceMode.IGNORE) {
				continue;
			}

			String httpMethod = controllerMap.get("method");

			if (!StringUtil.equalsIgnoreCase(
					httpMethod, jsonWebService.method())) {

				continue;
			}

			String path = controllerMap.get("path");

			if (path != null) {
				String jsonWebServiceValue = jsonWebService.value();

				if (!StringUtil.equalsIgnoreCase(path, jsonWebServiceValue)) {
					continue;
				}
			}

			boolean resolved = true;

			Annotation[][] parameterAnnotations =
				method.getParameterAnnotations();

			Class<?>[] parameterTypes = method.getParameterTypes();

			Object[] arguments = new Object[parameterTypes.length];

			for (int i = 0; i < parameterTypes.length; i++) {
				if (parameterAnnotations[i].length == 0) {
					resolved = false;

					break;
				}

				JSONWebServiceParameter jsonWebServiceParameter = null;

				for (Annotation annotation : parameterAnnotations[i]) {
					if (annotation instanceof JSONWebServiceParameter) {
						jsonWebServiceParameter =
							(JSONWebServiceParameter)annotation;
					}
				}

				if (jsonWebServiceParameter == null) {
					resolved = false;

					break;
				}

				String parameterValue = null;

				if (!controllerMap.containsKey(
						"-" + jsonWebServiceParameter.name())) {

					parameterValue = controllerMap.get(
						jsonWebServiceParameter.name());

					if (parameterValue == null) {
						resolved = false;

						continue;
					}
				}

				if ((parameterTypes[i] == Boolean.class) ||
					(parameterTypes[i] == Boolean.TYPE)) {

					arguments[i] = GetterUtil.getBoolean(parameterValue);
				}
				else if ((parameterTypes[i] == Integer.class) ||
						 (parameterTypes[i] == Integer.TYPE)) {

					arguments[i] = GetterUtil.getInteger(parameterValue);
				}
				else if ((parameterTypes[i] == Long.class) ||
						 (parameterTypes[i] == Long.TYPE)) {

					arguments[i] = GetterUtil.getLong(parameterValue);
				}
				else if (parameterTypes[i] == String.class) {
					if (parameterValue != null) {
						try {
							arguments[i] = URLDecoder.decode(
								parameterValue, "UTF-8");
						}
						catch (UnsupportedEncodingException uee) {
							throw new RuntimeException(uee);
						}
					}
				}
				else {
					resolved = false;

					_log.error(
						"Unsupported JSON web service parameter " +
							jsonWebServiceParameter.name());

					break;
				}
			}

			if (resolved) {
				methodConfigurations.add(
					new MethodConfiguration(method, arguments));
			}
		}

		if (methodConfigurations.isEmpty()) {
			_log.error("Unable to resolve method for given URL and parameters");

			return null;
		}

		MethodConfiguration bestMethodConfiguration = methodConfigurations.get(
			0);

		for (MethodConfiguration methodConfiguration : methodConfigurations) {
			if (bestMethodConfiguration._arguments.length >=
					methodConfiguration._arguments.length) {

				continue;
			}

			bestMethodConfiguration = methodConfiguration;
		}

		return bestMethodConfiguration;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ServiceControllerAdvisorImpl.class);

	private final Map<Class, Object> _controllers = new HashMap<>();

	private class MethodConfiguration {

		protected MethodConfiguration(Method method, Object[] arguments) {
			_method = method;
			_arguments = arguments;
		}

		private final Object[] _arguments;
		private final Method _method;

	}

}