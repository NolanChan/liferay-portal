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

package com.liferay.osb.lcs.web.internal.advisor;

import com.liferay.osb.lcs.constants.LCSRoleConstants;
import com.liferay.osb.lcs.model.LCSRole;
import com.liferay.osb.lcs.service.LCSRoleLocalService;
import com.liferay.portal.kernel.util.PortalUtil;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marko Cikos
 * @author Igor Beslic
 */
@Component(immediate = true)
public class EnrollmentAdvisor {

	public List<String> getAdministratorEmailAddresses(long lcsProjectId) {
		List<String> emailAddresses = new ArrayList<>();

		List<LCSRole> lcsRoles = _lcsRoleLocalService.getLCSProjectLCSRoles(
			lcsProjectId, LCSRoleConstants.ROLE_LCS_ADMINISTRATOR);

		for (LCSRole lcsRole : lcsRoles) {
			String emailAddress = PortalUtil.getUserEmailAddress(
				lcsRole.getUserId());

			emailAddresses.add(emailAddress);
		}

		return emailAddresses;
	}

	@Reference(unbind = "-")
	public void setLcsRoleLocalService(
		LCSRoleLocalService lcsRoleLocalService) {

		_lcsRoleLocalService = lcsRoleLocalService;
	}

	private LCSRoleLocalService _lcsRoleLocalService;

}