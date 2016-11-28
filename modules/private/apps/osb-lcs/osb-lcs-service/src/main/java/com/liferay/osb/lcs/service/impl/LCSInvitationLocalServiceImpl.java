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

import com.liferay.osb.lcs.exception.DuplicateLCSInvitationException;
import com.liferay.osb.lcs.model.LCSInvitation;
import com.liferay.osb.lcs.service.base.LCSInvitationLocalServiceBaseImpl;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;

import java.util.Date;
import java.util.List;

/**
 * @author Igor Beslic
 * @see    LCSInvitationLocalServiceBaseImpl
 * @see    com.liferay.osb.lcs.service.LCSInvitationLocalServiceUtil
 */
@ProviderType
public class LCSInvitationLocalServiceImpl
	extends LCSInvitationLocalServiceBaseImpl {

	@Override
	public LCSInvitation addLCSInvitation(
			long userId, long lcsProjectId, String emailAddress,
			long lcsClusterEntryId, int role)
		throws PortalException {

		validate(lcsProjectId, emailAddress);

		long lcsInvitationId = counterLocalService.increment(
			LCSInvitation.class.getName());

		LCSInvitation lcsInvitation = createLCSInvitation(lcsInvitationId);

		lcsInvitation.setUserId(userId);
		lcsInvitation.setCreateDate(new Date());
		lcsInvitation.setLcsProjectId(lcsProjectId);
		lcsInvitation.setEmailAddress(emailAddress);
		lcsInvitation.setLcsClusterEntryId(lcsClusterEntryId);
		lcsInvitation.setRole(role);

		return lcsInvitationPersistence.update(lcsInvitation);
	}

	@Override
	public LCSInvitation getLCSProjectLCSInvitation(
			long lcsProjectId, String emailAddress)
		throws PortalException {

		return lcsInvitationPersistence.findByLPI_EA(
			lcsProjectId, emailAddress);
	}

	@Override
	public List<LCSInvitation> getLCSProjectLCSInvitations(long lcsProjectId) {
		return lcsInvitationPersistence.findByLCSProjectId(
			lcsProjectId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	@Override
	public List<LCSInvitation> getUserLCSInvitations(String emailAddress)
		throws PortalException {

		return lcsInvitationPersistence.findByEmailAddress(emailAddress);
	}

	@Override
	public boolean hasUserLCSInvitation(long userId) throws PortalException {
		User user = userLocalService.getUser(userId);

		if (lcsInvitationPersistence.countByEmailAddress(
				user.getEmailAddress()) > 0) {

			return true;
		}

		return false;
	}

	protected void validate(long lcsProjectId, String emailAddress)
		throws PortalException {

		LCSInvitation lcsInvitation = lcsInvitationPersistence.fetchByLPI_EA(
			lcsProjectId, emailAddress);

		if (lcsInvitation != null) {
			throw new DuplicateLCSInvitationException();
		}
	}

}