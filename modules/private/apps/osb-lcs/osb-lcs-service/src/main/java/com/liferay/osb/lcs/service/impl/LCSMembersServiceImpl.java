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

package com.liferay.osb.lcs.service.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.lcs.notification.LCSEventType;
import com.liferay.osb.lcs.service.base.LCSMembersServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * Provides the messaging API for external modules to notify watchers about
 * events that occur in portal instances monitored by LCS.
 *
 * <p>
 * This class's send email methods send an email message to any watchers
 * subscribed to the event. Each send email method uses the appropriate {@link
 * LCSEventType} to create the message that {@link #fireLCSEvent(String,
 * LCSEventType)} publishes to the message bus.
 * </p>
 *
 * @author  Igor Beslic
 * @author  Ivica Cardic
 * @version LCS 1.7.1
 * @see     com.liferay.osb.lcs.hook.messaging.LCSEventMessageListener
 * @see     com.liferay.osb.lcs.email.EmailAdvisor#sendEmail(
 *          com.liferay.osb.lcs.email.EmailContext)
 * @since   LCS 1.3
 */
@ProviderType
public class LCSMembersServiceImpl extends LCSMembersServiceBaseImpl {

	/**
	 * Publishes a monitoring unavailable event that targets all watchers of the
	 * portal instance specified by the key.
	 *
	 * @param  key the portal instance key provided by the LCS key generator
	 * @throws PortalException if a portal exception occurred
	 * @since  LCS 1.3
	 */
	@Override
	public void sendMonitoringUnavailableEmail(String key)
		throws PortalException {

		checkPermission();

		lcsMembersLocalService.fireLCSEvent(
			key, LCSEventType.MONITORING_UNAVAILABLE);
	}

	/**
	 * Publishes a patching tool unavailable event that targets all watchers of
	 * the portal instance specified by the key.
	 *
	 * @param  key the portal instance key provided by the LCS key generator
	 * @throws PortalException if a portal exception occurred
	 * @since  LCS 1.3
	 */
	@Override
	public void sendPatchingToolUnavailableEmail(String key)
		throws PortalException {

		checkPermission();

		lcsMembersLocalService.fireLCSEvent(
			key, LCSEventType.PATCHING_TOOL_UNAVAILABLE);
	}

	/**
	 * Publishes a server manually shut down event that targets all watchers of
	 * the portal instance identified by the key.
	 *
	 * @param  key the portal instance key provided by the LCS key generator
	 * @throws PortalException if a portal exception occurred
	 * @since  LCS 1.3
	 */
	@Override
	public void sendServerManuallyShutdownEmail(String key)
		throws PortalException {

		checkPermission();

		lcsMembersLocalService.fireLCSEvent(
			key, LCSEventType.SERVER_MANUALLY_SHUTDOWN);
	}

	/**
	 * Publishes a server unexpectedly shut down event that targets all watchers
	 * of the portal instance specified by the key.
	 *
	 * @param  key the portal instance key provided by the LCS key generator
	 * @throws PortalException if a portal exception occurred
	 * @since  LCS 1.3
	 */
	@Override
	public void sendServerUnexpectedlyShutdownEmail(String key)
		throws PortalException {

		checkPermission();

		lcsMembersLocalService.fireLCSEvent(
			key, LCSEventType.SERVER_UNEXPECTEDLY_SHUTDOWN);
	}

	/**
	 * Determines if the current user has permission to publish the event to the
	 * portal's message bus. Only an LCS system user can invoke this service.
	 *
	 * @throws PortalException if a portal exception occurred
	 */
	protected void checkPermission() throws PortalException {
		User user = getUser();

		if (StringUtil.equalsIgnoreCase(
				user.getEmailAddress(), "system@liferay.com")) {

			return;
		}

		throw new PrincipalException();
	}

}