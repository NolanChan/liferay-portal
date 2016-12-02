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

package com.liferay.my.subscriptions.web.internal.portal.profile;

import com.liferay.my.subscriptions.web.internal.application.list.MySubscriptionPanelApp;
import com.liferay.my.subscriptions.web.internal.portlet.MySubscriptionsPortlet;
import com.liferay.my.subscriptions.web.internal.upgrade.MySubscriptionsWebUpgrade;
import com.liferay.portal.profile.BaseDSModulePortalProfile;
import com.liferay.portal.profile.PortalProfile;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author Shuyang Zhou
 */
@Component(immediate = true, service = PortalProfile.class)
public class ModulePortalProfile extends BaseDSModulePortalProfile {

	@Activate
	public void activate(ComponentContext componentContext) {
		Set<String> supportedPortalProfileNames = new HashSet<>(
			Arrays.asList(
				PortalProfile.PORTAL_PROFILE_NAME_CE,
				PortalProfile.PORTAL_PROFILE_NAME_DXP));

		init(
			componentContext, supportedPortalProfileNames,
			MySubscriptionPanelApp.class.getName(),
			MySubscriptionsPortlet.class.getName(),
			MySubscriptionsWebUpgrade.class.getName());
	}

}