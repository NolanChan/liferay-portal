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

import com.liferay.osb.lcs.constants.OSBLCSActionKeys;
import com.liferay.osb.lcs.model.LCSInvitation;
import com.liferay.osb.lcs.service.base.LCSInvitationServiceBaseImpl;
import com.liferay.osb.lcs.service.permission.LCSProjectPermission;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

/**
 * @author Igor Beslic
 * @see    LCSInvitationServiceBaseImpl
 * @see    com.liferay.osb.lcs.service.LCSInvitationServiceUtil
 */
@ProviderType
public class LCSInvitationServiceImpl extends LCSInvitationServiceBaseImpl {

	@Override
	public LCSInvitation addLCSInvitation(
			long lcsProjectId, String emailAddress, long lcsClusterEntryId,
			int role)
		throws PortalException {

		LCSProjectPermission.check(
			getPermissionChecker(), lcsProjectId,
			OSBLCSActionKeys.MANAGE_USERS);

		return lcsInvitationLocalService.addLCSInvitation(
			getUserId(), lcsProjectId, emailAddress, lcsClusterEntryId, role);
	}

	@Override
	public LCSInvitation deleteLCSInvitation(long lcsInvitationId)
		throws PortalException {

		LCSInvitation lcsInvitation = lcsInvitationPersistence.findByPrimaryKey(
			lcsInvitationId);

		LCSProjectPermission.check(
			getPermissionChecker(), lcsInvitation.getLcsProjectId(),
			OSBLCSActionKeys.MANAGE_USERS);

		return lcsInvitationPersistence.remove(lcsInvitationId);
	}

	@Override
	public LCSInvitation getLCSProjectLCSInvitation(
			long lcsProjectId, String emailAddress)
		throws PortalException {

		LCSProjectPermission.check(
			getPermissionChecker(), lcsProjectId,
			OSBLCSActionKeys.MANAGE_USERS);

		return lcsInvitationLocalService.getLCSProjectLCSInvitation(
			lcsProjectId, emailAddress);
	}

	@Override
	public List<LCSInvitation> getLCSProjectLCSInvitations(long lcsProjectId)
		throws PortalException {

		LCSProjectPermission.check(
			getPermissionChecker(), lcsProjectId,
			OSBLCSActionKeys.MANAGE_USERS);

		return lcsInvitationLocalService.getLCSProjectLCSInvitations(
			lcsProjectId);
	}

}