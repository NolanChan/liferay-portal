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

package com.liferay.portlet.messageboards.comment;

import com.liferay.portal.kernel.comment.Comment;
import com.liferay.portal.kernel.comment.WorkflowableComment;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;
import com.liferay.portlet.messageboards.util.MBUtil;

import java.util.Date;

/**
 * @author Adolfo Pérez
 */
public class MBCommentImpl implements Comment, WorkflowableComment {

	public MBCommentImpl(MBMessage message, String pathThemeImages) {
		_message = message;
		_pathThemeImages = pathThemeImages;
	}

	@Override
	public String getBody() {
		return _message.getBody();
	}

	@Override
	public long getCommentId() {
		return _message.getMessageId();
	}

	@Override
	public long getCompanyId() {
		return _message.getCompanyId();
	}

	@Override
	public Date getCreateDate() {
		return _message.getCreateDate();
	}

	@Override
	public long getGroupId() {
		return _message.getGroupId();
	}

	@Override
	public Class<?> getModelClass() {
		return MBMessage.class;
	}

	@Override
	public Date getModifiedDate() {
		return _message.getModifiedDate();
	}

	@Override
	public Comment getParentComment() throws PortalException {
		long parentMessageId = _message.getParentMessageId();

		MBMessage parentMessage = MBMessageLocalServiceUtil.getMessage(
			parentMessageId);

		return new MBCommentImpl(parentMessage, _pathThemeImages);
	}

	@Override
	public long getParentCommentId() {
		return _message.getParentMessageId();
	}

	@Override
	public long getPrimaryKey() {
		return _message.getPrimaryKey();
	}

	@Override
	public int getStatus() {
		return _message.getStatus();
	}

	@Override
	public String getTranslatedBody() {
		if (_message.isFormatBBCode()) {
			return MBUtil.getBBCodeHTML(getBody(), _pathThemeImages);
		}

		return getBody();
	}

	@Override
	public User getUser() throws PortalException {
		return UserLocalServiceUtil.fetchUser(getUserId());
	}

	@Override
	public long getUserId() {
		return _message.getUserId();
	}

	@Override
	public String getUserName() {
		return _message.getUserName();
	}

	private final MBMessage _message;
	private final String _pathThemeImages;

}