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
import com.liferay.lcs.util.LCSConstants;
import com.liferay.osb.lcs.constants.LCSMessageConstants;
import com.liferay.osb.lcs.constants.OSBLCSActionKeys;
import com.liferay.osb.lcs.exception.NoSuchLCSProjectException;
import com.liferay.osb.lcs.model.LCSMessage;
import com.liferay.osb.lcs.model.LCSProject;
import com.liferay.osb.lcs.service.base.LCSMessageServiceBaseImpl;
import com.liferay.osb.lcs.service.permission.LCSProjectPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

import java.util.Date;
import java.util.List;

/**
 * @author  Igor Beslic
 * @version LCS 1.7.1
 * @since   LCS 1.6
 */
@ProviderType
public class LCSMessageServiceImpl extends LCSMessageServiceBaseImpl {

	@Override
	public LCSMessage addCorpProjectLCSMessage(
			long corpProjectId, long sourceMessageId, String content, int type)
		throws PortalException {

		LCSProject lcsProject = lcsProjectLocalService.fetchByCorpProject(
			corpProjectId);

		if (lcsProject == null) {
			throw new NoSuchLCSProjectException(
				"No LCS project exists with the corp project ID " +
					corpProjectId);
		}

		LCSEventType lcsEventType = LCSEventType.valueOf(type);

		return addLCSProjectLCSMessage(
			lcsProject.getLcsProjectId(), sourceMessageId,
			LCSConstants.SOURCE_SYSTEM_NAME_OSB, content,
			new Date(LCSMessageConstants.END_DATE_INDEFINITE), true,
			lcsEventType.getSeverityLevel(), type, true, true);
	}

	@Override
	public LCSMessage addLCSProjectLCSMessage(
			long lcsProjectId, long sourceMessageId, String sourceSystemName,
			String content, Date endDate, boolean global, int severityLevel,
			int type, boolean adminsOnly, boolean generateUserLCSMessages)
		throws PortalException {

		PermissionChecker permissionChecker = getPermissionChecker();

		if (!permissionChecker.isSignedIn()) {
			throw new PrincipalException();
		}

		LCSProjectPermission.check(
			getPermissionChecker(), lcsProjectId, OSBLCSActionKeys.MANAGE);

		return lcsMessageLocalService.addLCSProjectLCSMessage(
			lcsProjectId, sourceMessageId, sourceSystemName, content, endDate,
			global, severityLevel, type, adminsOnly, generateUserLCSMessages);
	}

	@Override
	public void deleteCorpProjectLCSMessage(
			long corpProjectId, long sourceMessageId)
		throws PortalException {

		LCSProject lcsProject = lcsProjectLocalService.fetchByCorpProject(
			corpProjectId);

		if (lcsProject == null) {
			throw new NoSuchLCSProjectException(
				"No LCS project exists with the corp project ID " +
					corpProjectId);
		}

		deleteLCSProjectLCSMessage(
			lcsProject.getLcsProjectId(), sourceMessageId,
			LCSConstants.SOURCE_SYSTEM_NAME_OSB);
	}

	@Override
	public void deleteLCSProjectLCSMessage(
			long lcsProjectId, long sourceMessageId, String sourceSystemName)
		throws PortalException {

		PermissionChecker permissionChecker = getPermissionChecker();

		if (!permissionChecker.isSignedIn()) {
			throw new PrincipalException();
		}

		LCSProjectPermission.check(
			getPermissionChecker(), lcsProjectId, OSBLCSActionKeys.MANAGE);

		lcsMessageLocalService.deleteLCSProjectLCSMessage(
			lcsProjectId, sourceMessageId, sourceSystemName,
			classNameLocalService.getClassNameId(LCSProject.class.getName()));
}

	@Override
	public List<LCSMessage> getLCSProjectLCSMessages(
			long lcsProjectId, String sourceSystemName)
		throws PortalException {

		LCSProjectPermission.check(
			getPermissionChecker(), lcsProjectId, OSBLCSActionKeys.VIEW);

		return lcsMessagePersistence.findByS_C_C(
			sourceSystemName,
			classNameLocalService.getClassNameId(LCSProject.class.getName()),
			lcsProjectId);
	}

	@Override
	public List<LCSMessage> getLCSMessages(Date modifyDateGT, Date modifyDateLT)
		throws PortalException {

		PermissionChecker permissionChecker = getPermissionChecker();

		if (!permissionChecker.isSignedIn()) {
			throw new PrincipalException();
		}

		return lcsMessageLocalService.getLCSMessages(
			getUserId(), modifyDateGT, modifyDateLT);
	}

}