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

package com.liferay.osb.lcs.web.internal.action;

import com.liferay.osb.lcs.constants.OSBLCSConstants;
import com.liferay.osb.lcs.storage.LogStorageManager;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.struts.BaseStrutsAction;
import com.liferay.portal.kernel.struts.StrutsAction;
import com.liferay.util.bean.PortletBeanLocatorUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Mladen Cikara
 */
@Component(
	immediate = true,
	property = "path=" + OSBLCSConstants.PUBLIC_PATH_UPLOAD_LCS_CLIENT_EXCEPTION,
	service = StrutsAction.class
)
public class LCSClientExceptionUploadAction extends BaseStrutsAction {

	@Override
	public String execute(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		try {
			doExecute(request, response);
		}
		catch (Exception e) {
			_log.error(e, e);

			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

		return null;
	}

	protected void doExecute(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		String key = request.getParameter("key");
		String message = request.getParameter("message");
		String throwable = request.getParameter("throwable");

		if (_logStorageManager == null) {
			_logStorageManager =
				(LogStorageManager)PortletBeanLocatorUtil.locate(
					"com.liferay.osb.lcs.storage.LogStorageManager");
		}

		_logStorageManager.writeLogFile(key, message, throwable);

		if (_log.isDebugEnabled()) {
			_log.debug(key + " " + throwable);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LCSClientExceptionUploadAction.class);

	private LogStorageManager _logStorageManager;

}