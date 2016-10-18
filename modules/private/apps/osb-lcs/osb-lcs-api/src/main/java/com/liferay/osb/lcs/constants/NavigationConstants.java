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

package com.liferay.osb.lcs.constants;

import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;

/**
 * @author Igor Beslic
 */
public class NavigationConstants {

	public static final String CHECK_LCS_INVITATION_URL =
		"/c/portal/lcs/check_lcs_invitation";

	public static final String FRIENDLY_URL_ACCOUNT = "/account";

	public static final String FRIENDLY_URL_DASHBOARD = "/dashboard";

	public static final String FRIENDLY_URL_DOWNLOADS = "/downloads";

	public static final String FRIENDLY_URL_FEEDBACK = "/feedback";

	public static final String FRIENDLY_URL_INFO = "/info";

	public static final String FRIENDLY_URL_LCS_CLUSTER_ENTRY = "/environment";

	public static final String FRIENDLY_URL_LCS_CLUSTER_NODE = "/server";

	public static final String FRIENDLY_URL_LCS_PRIVATE_SITE = PropsUtil.get(
		PropsKeys.LAYOUT_FRIENDLY_URL_PRIVATE_GROUP_SERVLET_MAPPING) + "/guest";

	public static final String FRIENDLY_URL_LCS_PUBLIC_SITE = PropsUtil.get(
		PropsKeys.LAYOUT_FRIENDLY_URL_PUBLIC_SERVLET_MAPPING) + "/guest";

	public static final String FRIENDLY_URL_PROJECTS = "/projects";

	public static final String FRIENDLY_URL_SUBSCRIPTIONS = "/subscriptions";

	public static final String FRIENDLY_URL_USERS = "/users";

	public static final String HEALTH_STATUS_CSS_CLASS_EMPTY = "empty";

	public static final String HEALTH_STATUS_CSS_CLASS_OFFLINE = "offline";

	public static final String HEALTH_STATUS_CSS_CLASS_ONLINE = "online";

	public static final String LOGOUT_URL = "/c/portal/logout";

}