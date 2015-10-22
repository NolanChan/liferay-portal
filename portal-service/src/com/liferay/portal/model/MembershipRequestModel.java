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

package com.liferay.portal.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the MembershipRequest service. Represents a row in the &quot;MembershipRequest&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portal.model.impl.MembershipRequestModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portal.model.impl.MembershipRequestImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MembershipRequest
 * @see com.liferay.portal.model.impl.MembershipRequestImpl
 * @see com.liferay.portal.model.impl.MembershipRequestModelImpl
 * @generated
 */
@ProviderType
public interface MembershipRequestModel extends BaseModel<MembershipRequest>,
	MVCCModel, PartitionedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a membership request model instance should use the {@link MembershipRequest} interface instead.
	 */

	/**
	 * Returns the primary key of this membership request.
	 *
	 * @return the primary key of this membership request
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this membership request.
	 *
	 * @param primaryKey the primary key of this membership request
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this membership request.
	 *
	 * @return the mvcc version of this membership request
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this membership request.
	 *
	 * @param mvccVersion the mvcc version of this membership request
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the membership request ID of this membership request.
	 *
	 * @return the membership request ID of this membership request
	 */
	public long getMembershipRequestId();

	/**
	 * Sets the membership request ID of this membership request.
	 *
	 * @param membershipRequestId the membership request ID of this membership request
	 */
	public void setMembershipRequestId(long membershipRequestId);

	/**
	 * Returns the group ID of this membership request.
	 *
	 * @return the group ID of this membership request
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this membership request.
	 *
	 * @param groupId the group ID of this membership request
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this membership request.
	 *
	 * @return the company ID of this membership request
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this membership request.
	 *
	 * @param companyId the company ID of this membership request
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this membership request.
	 *
	 * @return the user ID of this membership request
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this membership request.
	 *
	 * @param userId the user ID of this membership request
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this membership request.
	 *
	 * @return the user uuid of this membership request
	 */
	public String getUserUuid();

	/**
	 * Sets the user uuid of this membership request.
	 *
	 * @param userUuid the user uuid of this membership request
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the create date of this membership request.
	 *
	 * @return the create date of this membership request
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this membership request.
	 *
	 * @param createDate the create date of this membership request
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Returns the comments of this membership request.
	 *
	 * @return the comments of this membership request
	 */
	@AutoEscape
	public String getComments();

	/**
	 * Sets the comments of this membership request.
	 *
	 * @param comments the comments of this membership request
	 */
	public void setComments(String comments);

	/**
	 * Returns the reply comments of this membership request.
	 *
	 * @return the reply comments of this membership request
	 */
	@AutoEscape
	public String getReplyComments();

	/**
	 * Sets the reply comments of this membership request.
	 *
	 * @param replyComments the reply comments of this membership request
	 */
	public void setReplyComments(String replyComments);

	/**
	 * Returns the reply date of this membership request.
	 *
	 * @return the reply date of this membership request
	 */
	public Date getReplyDate();

	/**
	 * Sets the reply date of this membership request.
	 *
	 * @param replyDate the reply date of this membership request
	 */
	public void setReplyDate(Date replyDate);

	/**
	 * Returns the replier user ID of this membership request.
	 *
	 * @return the replier user ID of this membership request
	 */
	public long getReplierUserId();

	/**
	 * Sets the replier user ID of this membership request.
	 *
	 * @param replierUserId the replier user ID of this membership request
	 */
	public void setReplierUserId(long replierUserId);

	/**
	 * Returns the replier user uuid of this membership request.
	 *
	 * @return the replier user uuid of this membership request
	 */
	public String getReplierUserUuid();

	/**
	 * Sets the replier user uuid of this membership request.
	 *
	 * @param replierUserUuid the replier user uuid of this membership request
	 */
	public void setReplierUserUuid(String replierUserUuid);

	/**
	 * Returns the status ID of this membership request.
	 *
	 * @return the status ID of this membership request
	 */
	public long getStatusId();

	/**
	 * Sets the status ID of this membership request.
	 *
	 * @param statusId the status ID of this membership request
	 */
	public void setStatusId(long statusId);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(
		com.liferay.portal.model.MembershipRequest membershipRequest);

	@Override
	public int hashCode();

	@Override
	public CacheModel<com.liferay.portal.model.MembershipRequest> toCacheModel();

	@Override
	public com.liferay.portal.model.MembershipRequest toEscapedModel();

	@Override
	public com.liferay.portal.model.MembershipRequest toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}