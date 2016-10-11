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

import com.liferay.osb.lcs.model.LCSClusterEntry;
import com.liferay.osb.lcs.model.LCSClusterNode;
import com.liferay.osb.lcs.model.LCSMessage;
import com.liferay.osb.lcs.model.LCSProject;
import com.liferay.osb.lcs.model.LCSRole;
import com.liferay.osb.lcs.model.UserLCSMessage;
import com.liferay.osb.lcs.service.base.UserLCSMessageLocalServiceBaseImpl;
import com.liferay.osb.lcs.util.comparator.UserLCSMessageModifiedDateComparator;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.ListUtil;

import java.util.List;

/**
 * @author  Igor Beslic
 * @version LCS 1.7.1
 * @since   LCS 1.6
 */
@ProviderType
public class UserLCSMessageLocalServiceImpl
	extends UserLCSMessageLocalServiceBaseImpl {

	@Override
	public UserLCSMessage addUserLCSMessage(long userId, long lcsMessageId)
		throws PortalException {

		UserLCSMessage userLCSMessage = createUserLCSMessage(
			counterLocalService.increment(UserLCSMessage.class.getName()));

		userLCSMessage.setUserId(userId);
		userLCSMessage.setLcsMessageId(lcsMessageId);
		userLCSMessage.setRead(false);
		userLCSMessage.setHidden(false);

		return userLCSMessagePersistence.update(userLCSMessage);
	}

	@Override
	public void deleteUserLCSMessages(LCSRole lcsRole) throws PortalException {
		long lcsClusterEntryClassNameId = classNameLocalService.getClassNameId(
			LCSClusterEntry.class.getName());
		long lcsClusterNodeClassNameId = classNameLocalService.getClassNameId(
			LCSClusterNode.class.getName());
		long lcsProjectClassNameId = classNameLocalService.getClassNameId(
			LCSProject.class.getName());

		if (lcsRole.getLcsClusterEntryId() == 0) {
			List<LCSClusterEntry> lcsClusterEntries =
				lcsClusterEntryLocalService.getUserLCSClusterEntries(
					lcsRole.getUserId(), lcsRole.getLcsProjectId());

			for (LCSClusterEntry lcsClusterEntry : lcsClusterEntries) {
				deleteUserLCSMessages(
					lcsRole.getUserId(), lcsClusterEntryClassNameId,
					lcsClusterEntry.getLcsClusterEntryId());
			}

			List<LCSClusterNode> lcsClusterNodes =
				lcsClusterNodeLocalService.getLCSProjectLCSClusterNodes(
					lcsRole.getUserId(), lcsRole.getLcsProjectId());

			for (LCSClusterNode lcsClusterNode : lcsClusterNodes) {
				deleteUserLCSMessages(
					lcsRole.getUserId(), lcsClusterEntryClassNameId,
					lcsClusterNode.getLcsClusterNodeId());
			}

			deleteUserLCSMessages(
				lcsRole.getUserId(), lcsProjectClassNameId,
				lcsRole.getLcsProjectId());
		}
		else {
			deleteUserLCSMessages(
				lcsRole.getUserId(), lcsClusterEntryClassNameId,
				lcsRole.getLcsClusterEntryId());

			List<LCSClusterNode> lcsClusterNodes =
				lcsClusterNodeLocalService.getLCSClusterEntryLCSClusterNodes(
					lcsRole.getLcsClusterEntryId());

			for (LCSClusterNode lcsClusterNode : lcsClusterNodes) {
				deleteUserLCSMessages(
					lcsRole.getUserId(), lcsClusterNodeClassNameId,
					lcsClusterNode.getLcsClusterNodeId());
			}
		}
	}

	@Override
	public void deleteUserLCSMessages(long userId) {
		userLCSMessagePersistence.removeByUserId(userId);
	}

	@Override
	public List<UserLCSMessage> getUserLCSMessages(long userId, boolean hidden)
		throws PortalException {

		return ListUtil.sort(
			userLCSMessagePersistence.findByU_H(userId, hidden),
			new UserLCSMessageModifiedDateComparator());
	}

	@Override
	public List<UserLCSMessage> getUserLCSMessages(long userId, int max)
		throws PortalException {

		return ListUtil.sort(
			userLCSMessagePersistence.findByUserId(userId, 0, max),
			new UserLCSMessageModifiedDateComparator());
	}

	@Override
	public int getUserLCSMessagesCount(long userId) {
		return userLCSMessagePersistence.countByUserId(userId);
	}

	@Override
	public UserLCSMessage updateRead(long userLCSMessageId, boolean read)
		throws PortalException {

		UserLCSMessage userLCSMessage =
			userLCSMessagePersistence.findByPrimaryKey(userLCSMessageId);

		userLCSMessage.setRead(read);

		return userLCSMessagePersistence.update(userLCSMessage);
	}

	protected void deleteUserLCSMessages(
		long userId, long classNameId, long classPK) {

		List<LCSMessage> lcsMessages = lcsMessagePersistence.findByC_C(
			classNameId, classPK);

		for (LCSMessage lcsMessage : lcsMessages) {
			UserLCSMessage userLCSMessage =
				userLCSMessagePersistence.fetchByU_LMI(
					userId, lcsMessage.getLcsMessageId());

			if (userLCSMessage == null) {
				continue;
			}

			userLCSMessagePersistence.remove(userLCSMessage);
		}
	}

}