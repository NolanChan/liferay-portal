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

package com.liferay.portal.kernel.util;

import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.servlet.BrowserSnifferUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.util.PortalUtil;

import java.io.IOException;

import javax.portlet.ActionResponse;
import javax.portlet.MimeResponse;
import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Adolfo Pérez
 */
public class JSONResponseUtil {

	public static void writeJSON(
			PortletRequest portletRequest, ActionResponse actionResponse,
			Object json)
		throws IOException {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		String contentType = ContentTypes.APPLICATION_JSON;

		if (BrowserSnifferUtil.isIe(request)) {
			contentType = ContentTypes.TEXT_HTML;
		}

		HttpServletResponse response = PortalUtil.getHttpServletResponse(
			actionResponse);

		response.setContentType(contentType);

		ServletResponseUtil.write(response, json.toString());

		response.flushBuffer();
	}

	public static void writeJSON(
			PortletRequest portletRequest, MimeResponse mimeResponse,
			Object json)
		throws IOException {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		String contentType = ContentTypes.APPLICATION_JSON;

		if (BrowserSnifferUtil.isIe(request)) {
			contentType = ContentTypes.TEXT_HTML;
		}

		mimeResponse.setContentType(contentType);

		PortletResponseUtil.write(mimeResponse, json.toString());

		mimeResponse.flushBuffer();
	}

}