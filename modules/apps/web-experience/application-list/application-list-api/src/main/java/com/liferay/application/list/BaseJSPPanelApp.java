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

package com.liferay.application.list;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class provides a skeletal implementation of the {@link PanelApp} with
 * JSP support to minimize the effort required to implement this interface.
 *
 * To implement a JSP application the programmer needs to extend this class and
 * implement <code>getJspPath</code> method which returns a path for main JSP
 * application view in current servlet context.
 * Servlet context should also be set using <code>setServletContext</code>
 * method to use appropriate servlet context for JSP pages otherwise
 * <code>include</code> method will throw a <code>NullPointerException</code>.
 *
 * JSP applications are included within JSP application category defined by
 * {@link BaseJSPPanelCategory} implementations.
 *
 * @see BasePanelApp
 * @see PanelApp
 *
 * @author Julio Camarero
 */
public abstract class BaseJSPPanelApp extends BasePanelApp {

	public abstract String getJspPath();

	@Override
	public boolean include(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException {

		String jspPath = getJspPath();

		if (Validator.isNull(jspPath)) {
			return false;
		}

		RequestDispatcher requestDispatcher =
			_servletContext.getRequestDispatcher(jspPath);

		try {
			requestDispatcher.include(request, response);
		}
		catch (ServletException se) {
			_log.error("Unable to include " + jspPath, se);

			return false;
		}

		return true;
	}

	public void setServletContext(ServletContext servletContext) {
		_servletContext = servletContext;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		BaseJSPPanelApp.class);

	private ServletContext _servletContext;

}