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

import com.liferay.osb.lcs.constants.LCSRoleConstants;
import com.liferay.osb.lcs.model.LCSClusterEntry;
import com.liferay.osb.lcs.model.LCSClusterNode;
import com.liferay.osb.lcs.model.LCSMessage;
import com.liferay.osb.lcs.model.LCSProject;
import com.liferay.osb.lcs.model.LCSRole;
import com.liferay.osb.lcs.model.impl.LCSMessageImpl;
import com.liferay.osb.lcs.service.base.LCSMessageLocalServiceBaseImpl;
import com.liferay.osb.lcs.util.comparator.LCSMessageModifiedDateComparator;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author  Igor Beslic
 * @version LCS 1.7.1
 * @since   LCS 1.6
 */
@ProviderType
public class LCSMessageLocalServiceImpl extends LCSMessageLocalServiceBaseImpl {

	@Override
	public LCSMessage addLCSClusterEntryLCSMessage(
			long lcsClusterEntryId, long sourceMessageId,
			String sourceSystemName, String content, Date endDate,
			boolean global, int severityLevel, int type,
			boolean addUserLCSMessages)
		throws PortalException {

		LCSMessage lcsMessage = addLCSMessage(
			sourceMessageId, sourceSystemName,
			classNameLocalService.getClassNameId(
				LCSClusterEntry.class.getName()),
			lcsClusterEntryId, content, endDate, global, severityLevel, type);

		if (!addUserLCSMessages) {
			return lcsMessage;
		}

		List<LCSRole> lcsRoles = lcsRoleLocalService.getLCSClusterEntryLCSRoles(
			lcsClusterEntryId);

		addUserLCSMessages(lcsMessage.getLcsMessageId(), lcsRoles);

		return lcsMessage;
	}

	@Override
	public LCSMessage addLCSClusterNodeLCSMessage(
			long lcsClusterNodeId, long sourceMessageId,
			String sourceSystemName, String content, Date endDate,
			boolean global, int severityLevel, int type)
		throws PortalException {

		LCSMessage lcsMessage = addLCSMessage(
			sourceMessageId, sourceSystemName,
			classNameLocalService.getClassNameId(
				LCSClusterNode.class.getName()),
			lcsClusterNodeId, content, endDate, global, severityLevel, type);

		LCSClusterNode lcsClusterNode =
			lcsClusterNodeLocalService.getLCSClusterNode(lcsClusterNodeId);

		List<LCSRole> lcsRoles = lcsRoleLocalService.getLCSClusterEntryLCSRoles(
			lcsClusterNode.getLcsClusterEntryId());

		addUserLCSMessages(lcsMessage.getLcsMessageId(), lcsRoles);

		return lcsMessage;
	}

	@Override
	public LCSMessage addLCSMessage(
		long sourceMessageId, String sourceSystemName, long classNameId,
		long classPK, String content, Date endDate, boolean global,
		int severityLevel, int type) {

		LCSMessage lcsMessage = lcsMessagePersistence.fetchByS_S_C_C(
			sourceMessageId, sourceSystemName, classNameId, classPK);

		if (lcsMessage == null) {
			lcsMessage = createLCSMessage(
				counterLocalService.increment(LCSMessage.class.getName()));

			lcsMessage.setCreateDate(new Date());
			lcsMessage.setModifiedDate(lcsMessage.getCreateDate());
			lcsMessage.setSourceMessageId(sourceMessageId);
			lcsMessage.setSourceSystemName(sourceSystemName);
			lcsMessage.setClassNameId(classNameId);
			lcsMessage.setClassPK(classPK);
		}
		else {
			lcsMessage.setModifiedDate(new Date());
		}

		lcsMessage.setContent(content);
		lcsMessage.setEndDate(endDate);
		lcsMessage.setGlobal(global);
		lcsMessage.setSeverityLevel(severityLevel);
		lcsMessage.setType(type);

		return lcsMessagePersistence.update(lcsMessage);
	}

	@Override
	public LCSMessage addLCSProjectLCSMessage(
			long lcsProjectId, long sourceMessageId, String sourceSystemName,
			String content, Date endDate, boolean global, int severityLevel,
			int type, boolean adminsOnly, boolean generateUserLCSMessages)
		throws PortalException {

		LCSMessage lcsMessage = addLCSMessage(
			sourceMessageId, sourceSystemName,
			classNameLocalService.getClassNameId(LCSProject.class.getName()),
			lcsProjectId, content, endDate, global, severityLevel, type);

		List<LCSRole> lcsRoles;

		if (generateUserLCSMessages) {
			if (adminsOnly) {
				lcsRoles = lcsRoleLocalService.getLCSProjectLCSRoles(
					lcsProjectId, LCSRoleConstants.ROLE_LCS_ADMINISTRATOR);
			}
			else {
				lcsRoles = lcsRoleLocalService.getLCSProjectLCSRoles(
					lcsProjectId);
			}

			addUserLCSMessages(lcsMessage.getLcsMessageId(), lcsRoles);
		}

		return lcsMessage;
	}

	@Override
	public void deleteLCSClusterEntryLCSMessages(long lcsClusterEntryId)
		throws PortalException {

		List<LCSMessage> lcsMessages = lcsMessagePersistence.findByC_C(
			classNameLocalService.getClassNameId(
				LCSClusterEntry.class.getName()),
			lcsClusterEntryId);

		deleteLCSMessages(lcsMessages);
	}

	@Override
	public void deleteLCSClusterNodeLCSMessages(long lcsClusterNodeId)
		throws PortalException {

		List<LCSMessage> lcsMessages = lcsMessagePersistence.findByC_C(
			classNameLocalService.getClassNameId(
				LCSClusterNode.class.getName()),
			lcsClusterNodeId);

		deleteLCSMessages(lcsMessages);
	}

	@Override
	public void deleteLCSProjectLCSMessage(
			long lcsProjectId, long sourceMessageId, String sourceSystemName,
			long classNameId)
		throws PortalException {

		LCSMessage lcsMessage = lcsMessagePersistence.removeByS_S_C_C(
			sourceMessageId, sourceSystemName, classNameId, lcsProjectId);

		userLCSMessagePersistence.removeByLCSMessageId(
			lcsMessage.getLcsMessageId());
	}

	@Override
	public void deleteLCSProjectLCSMessages(long lcsProjectId)
		throws PortalException {

		List<LCSMessage> lcsMessages = lcsMessagePersistence.findByC_C(
			classNameLocalService.getClassNameId(LCSProject.class.getName()),
			lcsProjectId);

		deleteLCSMessages(lcsMessages);
	}

	@Override
	public LCSMessage fetchLastLCSProjectLCSMessage(
		long lcsProjectId, int type) {

		OrderByComparator orderByComparator =
			OrderByComparatorFactoryUtil.create(
				LCSMessageImpl.TABLE_NAME, "createDate", false);

		return lcsMessagePersistence.fetchByC_C_T_Last(
			classNameLocalService.getClassNameId(LCSProject.class.getName()),
			lcsProjectId, type, orderByComparator);
	}

	@Override
	public List<LCSMessage> getLCSMessages(
			long userId, Date modifyDateGT, Date modifyDateLT)
		throws PortalException {

		User user = userLocalService.getUserById(userId);

		Date createDate = user.getCreateDate();

		if (createDate.after(modifyDateGT)) {
			modifyDateGT = createDate;
		}

		List<LCSRole> lcsRoles = lcsRoleLocalService.getUserLCSRoles(userId);

		List<LCSMessage> lcsMessages = getLCSMessages(lcsRoles);

		Iterator iterator = lcsMessages.iterator();

		while (iterator.hasNext()) {
			LCSMessage lcsMessage = (LCSMessage)iterator.next();

			Date modifiedDate = lcsMessage.getModifiedDate();

			if (modifiedDate.before(modifyDateGT) |
				modifiedDate.after(modifyDateLT)) {

				iterator.remove();
			}
		}

		return ListUtil.sort(
			lcsMessages, new LCSMessageModifiedDateComparator());
	}

	protected void addUserLCSMessages(long lcsMessageId, List<LCSRole> lcsRoles)
		throws PortalException {

		Set<Long> lcsRoleUserIds = new HashSet<>();

		for (LCSRole lcsRole : lcsRoles) {
			if (lcsRoleUserIds.contains(lcsRole.getUserId())) {
				continue;
			}

			userLCSMessageLocalService.addUserLCSMessage(
				lcsRole.getUserId(), lcsMessageId);

			lcsRoleUserIds.add(lcsRole.getUserId());
		}
	}

	protected void deleteLCSMessages(List<LCSMessage> lcsMessages)
		throws PortalException {

		for (LCSMessage lcsMessage : lcsMessages) {
			lcsMessagePersistence.remove(lcsMessage.getLcsMessageId());

			userLCSMessagePersistence.removeByLCSMessageId(
				lcsMessage.getLcsMessageId());
		}
	}

	protected List<LCSMessage> getLCSMessages(List<LCSRole> lcsRoles) {
		long lcsClusterEntryClassNameId = classNameLocalService.getClassNameId(
			LCSClusterEntry.class.getName());
		long lcsClusterNodeClassNameId = classNameLocalService.getClassNameId(
			LCSClusterNode.class.getName());
		long lcsProjectClassNameId = classNameLocalService.getClassNameId(
			LCSProject.class.getName());

		List<LCSMessage> lcsMessages = new ArrayList<>();

		for (LCSRole lcsRole : lcsRoles) {
			long lcsClusterEntryId = lcsRole.getLcsClusterEntryId();
			long lcsProjectId = lcsRole.getLcsProjectId();

			if (lcsClusterEntryId == 0) {
				lcsMessages.addAll(
					lcsMessagePersistence.findByC_C(
						lcsProjectClassNameId, lcsProjectId));

				List<LCSClusterEntry> lcsClusterEntries =
					lcsClusterEntryLocalService.getLCSProjectLCSClusterEntries(
						lcsProjectId);

				for (LCSClusterEntry lcsClusterEntry : lcsClusterEntries) {
					lcsMessages.addAll(
						lcsMessagePersistence.findByC_C(
							lcsClusterEntryClassNameId,
							lcsClusterEntry.getLcsClusterEntryId()));
				}

				List<LCSClusterNode> lcsClusterNodes =
					lcsClusterNodeLocalService.getLCSProjectLCSClusterNodes(
						lcsRole.getUserId(), lcsProjectId);

				for (LCSClusterNode lcsClusterNode : lcsClusterNodes) {
					lcsMessages.addAll(
						lcsMessagePersistence.findByC_C(
							lcsClusterNodeClassNameId,
							lcsClusterNode.getLcsClusterNodeId()));
				}
			}

			lcsMessages.addAll(
				lcsMessagePersistence.findByC_C(
					lcsClusterEntryClassNameId, lcsClusterEntryId));

			List<LCSClusterNode> lcsClusterNodes =
				lcsClusterNodeLocalService.getLCSClusterEntryLCSClusterNodes(
					lcsClusterEntryId);

			for (LCSClusterNode lcsClusterNode : lcsClusterNodes) {
				lcsMessages.addAll(
					lcsMessagePersistence.findByC_C(
						lcsClusterNodeClassNameId,
						lcsClusterNode.getLcsClusterNodeId()));
			}
		}

		return lcsMessages;
	}

}