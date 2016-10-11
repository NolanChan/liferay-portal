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

/**
 * @author Igor Beslic
 */
public class LCSRoleConstants {

	public static final int ROLE_LCS_ADMINISTRATOR = 4;

	public static final int ROLE_LCS_ENVIRONMENT_MANAGER = 1;

	public static final int ROLE_LCS_ENVIRONMENT_MEMBERSHIP_PENDING_USER = 0;

	public static final int ROLE_LCS_ENVIRONMENT_VIEWER = 2;

	public static String getRoleLabel(int role) {
		if (role == ROLE_LCS_ADMINISTRATOR) {
			return "lcs-administrator";
		}
		else if (role == ROLE_LCS_ENVIRONMENT_MANAGER) {
			return "lcs-environment-manager";
		}
		else if (role == ROLE_LCS_ENVIRONMENT_MEMBERSHIP_PENDING_USER) {
			return "lcs-environment-membership-pending-user";
		}
		else if (role == ROLE_LCS_ENVIRONMENT_VIEWER) {
			return "lcs-environment-viewer";
		}
		else {
			return null;
		}
	}

}