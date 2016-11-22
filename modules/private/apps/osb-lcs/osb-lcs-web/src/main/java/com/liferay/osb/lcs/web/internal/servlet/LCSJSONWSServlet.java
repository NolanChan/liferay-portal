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

package com.liferay.osb.lcs.web.internal.servlet;

import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.osb.lcs.advisor.ServiceControllerAdvisor;
import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.dao.orm.ORMException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.HttpMethods;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.ServiceContextThreadLocal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;

import java.nio.charset.Charset;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

/**
 * @author Igor Beslic
 */
public class LCSJSONWSServlet extends HttpServlet {

	@Override
	public void init() throws ServletException {
		super.init();

		_serviceControllerAdvisor = new ServiceControllerAdvisor();
	}

	@Override
	protected void doDelete(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		doProcessRequest(request, response);
	}

	@Override
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		doProcessRequest(request, response);
	}

	@Override
	protected void doPost(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		doProcessRequest(request, response);
	}

	protected void doProcessRequest(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException {

		Map<String, String> serviceControllerMap = getServiceControllerMap(
			request);

		response.setCharacterEncoding("UTF-8");
		response.setContentType(ContentTypes.APPLICATION_JSON);

		Writer writer = response.getWriter();

		try {
			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				request);

			ServiceContextThreadLocal.pushServiceContext(serviceContext);

			String content = _serviceControllerAdvisor.execute(
				serviceControllerMap);

			writer.write(content);
		}
		catch (Exception e) {
			int status = HttpServletResponse.SC_BAD_REQUEST;

			if (e instanceof NoSuchModelException) {
				status = HttpServletResponse.SC_NOT_FOUND;
			}
			else if (e instanceof ORMException) {
				status = HttpServletResponse.SC_NOT_ACCEPTABLE;
			}
			else if (e instanceof PrincipalException) {
				status = HttpServletResponse.SC_FORBIDDEN;
			}

			response.setStatus(status);

			StringBundler sb = new StringBundler(5);

			sb.append("{\"exception\":\"");
			sb.append(e.getMessage());
			sb.append(", \"status\":");
			sb.append(status);
			sb.append("\"}");

			_log.error(
				"Unable to complete request because " + sb.toString(), e);

			writer.write(sb.toString());
		}
		finally {
			ServiceContextThreadLocal.popServiceContext();

			writer.flush();

			writer.close();
		}
	}

	@Override
	protected void doPut(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		doProcessRequest(request, response);
	}

	protected Map<String, String> getParameterMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<>();

		String method = request.getMethod();

		if (method.equals(HttpMethods.PUT)) {
			try {
				BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(request.getInputStream()));

				List<NameValuePair> nameValuePairs = URLEncodedUtils.parse(
					bufferedReader.readLine(),
					Charset.forName(StringPool.UTF8));

				for (NameValuePair nameValuePair : nameValuePairs) {
					map.put(nameValuePair.getName(), nameValuePair.getValue());
				}
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		else {
			Enumeration<String> enumeration = request.getParameterNames();

			while (enumeration.hasMoreElements()) {
				String parameterName = enumeration.nextElement();

				map.put(parameterName, request.getParameter(parameterName));
			}
		}

		return map;
	}

	protected Map<String, String> getServiceControllerMap(
		HttpServletRequest request) {

		String path = request.getRequestURI();

		path = path.replace(
			request.getContextPath() + "/lcs/jsonws/", StringPool.BLANK);

		String[] pathParts = path.split(StringPool.SLASH);

		if (pathParts.length < 2) {
			throw new UnsupportedOperationException();
		}

		Matcher versionMatcher = _versionPattern.matcher(pathParts[0]);

		if (!versionMatcher.find()) {
			throw new UnsupportedOperationException();
		}

		Map<String, String> serviceControllerMap = new HashMap<>();

		serviceControllerMap.put("controller", pathParts[1] + "Controller");
		serviceControllerMap.put(
			"method", StringUtil.toLowerCase(request.getMethod()));
		serviceControllerMap.put("model", pathParts[1]);
		serviceControllerMap.put("version", pathParts[0]);

		if (pathParts.length == 3) {
			serviceControllerMap.put("path", pathParts[2]);
		}

		serviceControllerMap.putAll(getParameterMap(request));

		return serviceControllerMap;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LCSJSONWSServlet.class);

	private static final Pattern _versionPattern = Pattern.compile(
		"^v\\d+(_\\d+)?$");

	private ServiceControllerAdvisor _serviceControllerAdvisor;

}