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

package com.liferay.osb.lcs.internal.events;

import com.liferay.osb.lcs.navigation.util.NavigationUtil;
import com.liferay.osb.lcs.service.LCSClusterNodeLocalServiceUtil;
import com.liferay.osb.lcs.service.LCSProjectLocalServiceUtil;
import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Mladen Cikara
 */
public class RedirectUserAction extends Action {

	@Override
	public void run(HttpServletRequest request, HttpServletResponse response)
		throws ActionException {

		try {
			doRun(request, response);
		}
		catch (Exception e) {
			throw new ActionException(e);
		}
	}

	protected void doRun(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException, PortalException, SystemException {

		if (!isDefaultLandingPageRequest(request)) {
			if (_log.isDebugEnabled()) {
				_log.debug("Skipping default landing page");
			}

			return;
		}

		User user = PortalUtil.getUser(request);

		if (user == null) {
			if (_log.isDebugEnabled()) {
				_log.debug("User is not authenticated");
			}

			return;
		}

		LCSProjectLocalServiceUtil.checkUserAccountEntryLCSProject(user);

		if (!LCSClusterNodeLocalServiceUtil.hasUserLCSClusterNodes(
				user.getUserId())) {

			if (_log.isDebugEnabled()) {
				_log.debug("User has no LCS cluster nodes");
			}

			return;
		}

		if (_log.isInfoEnabled()) {
			_log.info("Redirecting request to " + _REDIRECT);
		}

		response.sendRedirect(_REDIRECT);
	}

	private boolean isDefaultLandingPageRequest(HttpServletRequest request) {
		String currentURL = PortalUtil.getCurrentURL(request);

		if (_log.isTraceEnabled()) {
			_log.trace("Current URL " + currentURL);
		}

		if (currentURL.equals(StringPool.SLASH)) {
			return true;
		}

		return false;
	}

	private static final String _REDIRECT =
		NavigationUtil.FRIENDLY_URL_LCS_PRIVATE_SITE +
			NavigationUtil.FRIENDLY_URL_DASHBOARD;

	private static Log _log = LogFactoryUtil.getLog(RedirectUserAction.class);

}