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

package com.liferay.portlet.messageboards.model.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.lock.Lock;
import com.liferay.portal.kernel.lock.LockManagerUtil;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Repository;
import com.liferay.portal.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.messageboards.model.MBCategory;
import com.liferay.portlet.messageboards.model.MBCategoryConstants;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.model.MBThread;
import com.liferay.portlet.messageboards.service.MBCategoryLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 * @author Mika Koivisto
 */
public class MBThreadImpl extends MBThreadBaseImpl {

	@Override
	public Folder addAttachmentsFolder() throws PortalException {
		if (_attachmentsFolderId !=
				DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {

			return PortletFileRepositoryUtil.getPortletFolder(
				_attachmentsFolderId);
		}

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		Repository repository = PortletFileRepositoryUtil.addPortletRepository(
			getGroupId(), PortletKeys.MESSAGE_BOARDS, serviceContext);

		MBMessage message = MBMessageLocalServiceUtil.getMessage(
			getRootMessageId());

		Folder folder = PortletFileRepositoryUtil.addPortletFolder(
			message.getUserId(), repository.getRepositoryId(),
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			String.valueOf(getThreadId()), serviceContext);

		_attachmentsFolderId = folder.getFolderId();

		return folder;
	}

	@Override
	public long getAttachmentsFolderId() {
		if (_attachmentsFolderId !=
				DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {

			return _attachmentsFolderId;
		}

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		Repository repository =
			PortletFileRepositoryUtil.fetchPortletRepository(
				getGroupId(), PortletKeys.MESSAGE_BOARDS);

		if (repository == null) {
			return DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;
		}

		try {
			Folder folder = PortletFileRepositoryUtil.getPortletFolder(
				repository.getRepositoryId(),
				DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
				String.valueOf(getThreadId()));

			_attachmentsFolderId = folder.getFolderId();
		}
		catch (Exception e) {
		}

		return _attachmentsFolderId;
	}

	@Override
	public MBCategory getCategory() throws PortalException {
		long parentCategoryId = getCategoryId();

		if ((parentCategoryId ==
				MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID) ||
			(parentCategoryId == MBCategoryConstants.DISCUSSION_CATEGORY_ID)) {

			return null;
		}

		return MBCategoryLocalServiceUtil.getCategory(getCategoryId());
	}

	@Override
	public Lock getLock() {
		try {
			return LockManagerUtil.getLock(
				MBThread.class.getName(), getThreadId());
		}
		catch (Exception e) {
		}

		return null;
	}

	@Override
	public long[] getParticipantUserIds() {
		Set<Long> participantUserIds = new HashSet<>();

		List<MBMessage> messages = MBMessageLocalServiceUtil.getThreadMessages(
			getThreadId(), WorkflowConstants.STATUS_ANY);

		for (MBMessage message : messages) {
			participantUserIds.add(message.getUserId());
		}

		return ArrayUtil.toLongArray(participantUserIds);
	}

	@Override
	public boolean hasLock(long userId) {
		try {
			return LockManagerUtil.hasLock(
				userId, MBThread.class.getName(), getThreadId());
		}
		catch (Exception e) {
		}

		return false;
	}

	@Override
	public boolean isLocked() {
		try {
			if (isInTrash()) {
				return true;
			}

			return LockManagerUtil.isLocked(
				MBThread.class.getName(), getThreadId());
		}
		catch (Exception e) {
		}

		return false;
	}

	private long _attachmentsFolderId;

}