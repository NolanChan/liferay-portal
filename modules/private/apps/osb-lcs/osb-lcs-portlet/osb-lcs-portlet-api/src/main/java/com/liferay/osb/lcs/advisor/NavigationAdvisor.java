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

package com.liferay.osb.lcs.advisor;

import com.liferay.portal.kernel.exception.PortalException;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

/**
 * @author Igor Beslic
 */
@Component(
	configurationPid = "com.liferay.osb.lcs.configuration.OSBLCSConfiguration",
	configurationPolicy = ConfigurationPolicy.REQUIRE, immediate = true
)
public interface NavigationAdvisor {

	public String getLCSClusterEntryHealthStatusCSSClass(
		Boolean lcsClusterEntryOffline,
		int lcsClusterEntryLCSClusterNodesCount);

	public String getLCSClusterEntryHealthStatusTitle(
		boolean cluster, Boolean lcsClusterNodeOffline);

	public String getLCSClusterEntryURL(long lcsClusterEntryId)
		throws PortalException;

	public String getLCSClusterNodeHealthStatusCSSClass(
		Boolean lcsClusterNodeOffline);

	public Map<Long, Boolean> getLCSClusterNodeOfflineStatuses(
			long lcsClusterEntryId)
		throws PortalException;

	public String getLCSClusterNodeURL(long lcsClusterNodeId)
		throws PortalException;

	public String getLCSLayoutURL(
		String friendlyURL, Map<String, String> publicRenderParameters);

	public String getLCSNotificationsURL();

	public String getLCSPortalURL();

	public String getLCSProjectURL(long lcsProjectId);

	public String getLCSProjectUsersURL(long lcsProjectId);

	public String getPublicRenderParameterName(String parameterName);

	public Map<String, String> getPublicRenderParametersMap(
		String... parameters);

	public String getSubscriptionsURL(long lcsProjectId);

}