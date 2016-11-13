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

import com.liferay.osb.lcs.constants.NavigationConstants;
import com.liferay.osb.lcs.service.LCSClusterNodeLocalService;
import com.liferay.osb.lcs.service.LCSProjectLocalService;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.events.LifecycleEvent;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Mladen Cikara
 */
@Component(
	immediate = true, property = {"key=servlet.service.events.pre"},
	service = LifecycleAction.class
)
public class RedirectUserAction implements LifecycleAction {

	@Override
	public void processLifecycleEvent(LifecycleEvent lifecycleEvent)
		throws ActionException {

		try {
			doRun(lifecycleEvent.getRequest(), lifecycleEvent.getResponse());
		}
		catch (Exception e) {
			throw new ActionException(e);
		}
	}

	@Reference(bind = "-", unbind = "-")
	public void setLcsClusterNodeLocalService(
		LCSClusterNodeLocalService lcsClusterNodeLocalService) {

		_lcsClusterNodeLocalService = lcsClusterNodeLocalService;
	}

	@Reference(bind = "-", unbind = "-")
	public void setLcsProjectLocalService(
		LCSProjectLocalService lcsProjectLocalService) {

		_lcsProjectLocalService = lcsProjectLocalService;
	}

	protected void doRun(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException, PortalException {

		if (!_isDefaultLandingPageRequest(request)) {
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

		_lcsProjectLocalService.checkUserAccountEntryLCSProject(user);

		if (!_lcsClusterNodeLocalService.hasUserLCSClusterNodes(
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

	private boolean _isDefaultLandingPageRequest(HttpServletRequest request) {
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
		NavigationConstants.FRIENDLY_URL_LCS_PRIVATE_SITE +
			NavigationConstants.FRIENDLY_URL_DASHBOARD;

	private static final Log _log = LogFactoryUtil.getLog(
		RedirectUserAction.class);

	private LCSClusterNodeLocalService _lcsClusterNodeLocalService;
	private LCSProjectLocalService _lcsProjectLocalService;

}