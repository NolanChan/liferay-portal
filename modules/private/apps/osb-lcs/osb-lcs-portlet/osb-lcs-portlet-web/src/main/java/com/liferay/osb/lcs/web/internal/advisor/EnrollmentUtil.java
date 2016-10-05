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

package com.liferay.osb.lcs.enrollment.util;

import com.liferay.osb.lcs.model.LCSProject;
import com.liferay.osb.lcs.model.LCSRole;
import com.liferay.osb.lcs.model.LCSRoleConstants;
import com.liferay.osb.lcs.service.LCSProjectLocalServiceUtil;
import com.liferay.osb.lcs.service.LCSProjectServiceUtil;
import com.liferay.osb.lcs.service.LCSRoleLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UniqueList;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Marko Cikos
 * @author Igor Beslic
 */
public class EnrollmentUtil {

	public static List<String> getAdministratorEmailAddresses(long lcsProjectId)
		throws SystemException {

		List<String> emailAddresses = new UniqueList<String>();

		List<LCSRole> lcsRoles = LCSRoleLocalServiceUtil.getLCSProjectLCSRoles(
			lcsProjectId, LCSRoleConstants.ROLE_LCS_ADMINISTRATOR);

		for (LCSRole lcsRole : lcsRoles) {
			String emailAddress = PortalUtil.getUserEmailAddress(
				lcsRole.getUserId());

			emailAddresses.add(emailAddress);
		}

		return emailAddresses;
	}

	public static List<LCSProject> getCompanyLCSProjects(long userId)
		throws PortalException, SystemException {

		String subdomain = getUserEmailAddressSubdomain(userId);

		if (ArrayUtil.contains(_EMAIL_ADDRESS_SUBDOMAINS, subdomain)) {
			return Collections.emptyList();
		}

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			LCSRole.class);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"role", LCSRoleConstants.ROLE_LCS_ADMINISTRATOR));

		List<LCSRole> lcsRoles = new ArrayList<LCSRole>(
			LCSRoleLocalServiceUtil.dynamicQuery(dynamicQuery));

		List<LCSProject> userLCSProjects = new ArrayList<LCSProject>();

		userLCSProjects.addAll(LCSProjectServiceUtil.getUserLCSProjects());

		List<LCSProject> pendingUserLCSProjects =
			LCSProjectServiceUtil.getUserLCSProjects(
				true,
				LCSRoleConstants.ROLE_LCS_ENVIRONMENT_MEMBERSHIP_PENDING_USER);

		userLCSProjects.addAll(pendingUserLCSProjects);

		for (LCSProject userLCSProject : userLCSProjects) {
			List<LCSRole> lcsProjectLCSRoles =
				LCSRoleLocalServiceUtil.getLCSProjectLCSRoles(
					userLCSProject.getLcsProjectId(),
					LCSRoleConstants.ROLE_LCS_ADMINISTRATOR);

			lcsRoles.removeAll(lcsProjectLCSRoles);
		}

		List<LCSProject> companyLCSProjects = new UniqueList<LCSProject>();

		for (LCSRole lcsRole : lcsRoles) {
			String curSubdomain = getUserEmailAddressSubdomain(
				lcsRole.getUserId());

			if (Validator.equals(curSubdomain, subdomain)) {
				try {
					LCSProject lcsProject =
						LCSProjectLocalServiceUtil.getLCSProject(
							lcsRole.getLcsProjectId());

					companyLCSProjects.add(lcsProject);
				}
				catch (PortalException pe) {
					_log.error(pe, pe);
				}
			}
		}

		return companyLCSProjects;
	}

	public static List<LCSProject> getLCSProjects(boolean administratorLCSRole)
		throws PortalException, SystemException {

		List<LCSProject> lcsProjects = new ArrayList<LCSProject>();

		List<LCSProject> userLCSProjects =
			LCSProjectServiceUtil.getUserLCSProjects(false);

		for (LCSProject lcsProject : userLCSProjects) {
			List<LCSRole> administratorLCSRoles =
				LCSRoleLocalServiceUtil.getLCSProjectLCSRoles(
					lcsProject.getLcsProjectId(),
					LCSRoleConstants.ROLE_LCS_ADMINISTRATOR);

			if ((administratorLCSRole && !administratorLCSRoles.isEmpty()) ||
				(!administratorLCSRole && administratorLCSRoles.isEmpty())) {

				lcsProjects.add(lcsProject);
			}
		}

		return lcsProjects;
	}

	public static String getUserEmailAddressSubdomain(long userId)
		throws PortalException, SystemException {

		User user = UserLocalServiceUtil.getUser(userId);

		String domain = StringUtil.extractLast(
			user.getEmailAddress(), StringPool.AT);

		return domain.replaceFirst("\\..+$", StringPool.BLANK);
	}

	private static final String[] _EMAIL_ADDRESS_SUBDOMAINS =
		{"aol", "gmail", "hotmail", "mail", "outlook", "yahoo"};

	private static Log _log = LogFactoryUtil.getLog(EnrollmentUtil.class);

}