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

package com.liferay.social.kernel.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.AttachedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

/**
 * The base model interface for the SocialActivity service. Represents a row in the &quot;SocialActivity&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portlet.social.model.impl.SocialActivityModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portlet.social.model.impl.SocialActivityImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SocialActivity
 * @see com.liferay.portlet.social.model.impl.SocialActivityImpl
 * @see com.liferay.portlet.social.model.impl.SocialActivityModelImpl
 * @generated
 */
@ProviderType
public interface SocialActivityModel extends AttachedModel,
	BaseModel<SocialActivity>, ShardedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a social activity model instance should use the {@link SocialActivity} interface instead.
	 */

	/**
	 * Returns the primary key of this social activity.
	 *
	 * @return the primary key of this social activity
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this social activity.
	 *
	 * @param primaryKey the primary key of this social activity
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the activity ID of this social activity.
	 *
	 * @return the activity ID of this social activity
	 */
	public long getActivityId();

	/**
	 * Sets the activity ID of this social activity.
	 *
	 * @param activityId the activity ID of this social activity
	 */
	public void setActivityId(long activityId);

	/**
	 * Returns the group ID of this social activity.
	 *
	 * @return the group ID of this social activity
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this social activity.
	 *
	 * @param groupId the group ID of this social activity
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this social activity.
	 *
	 * @return the company ID of this social activity
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this social activity.
	 *
	 * @param companyId the company ID of this social activity
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this social activity.
	 *
	 * @return the user ID of this social activity
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this social activity.
	 *
	 * @param userId the user ID of this social activity
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this social activity.
	 *
	 * @return the user uuid of this social activity
	 */
	public String getUserUuid();

	/**
	 * Sets the user uuid of this social activity.
	 *
	 * @param userUuid the user uuid of this social activity
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the create date of this social activity.
	 *
	 * @return the create date of this social activity
	 */
	public long getCreateDate();

	/**
	 * Sets the create date of this social activity.
	 *
	 * @param createDate the create date of this social activity
	 */
	public void setCreateDate(long createDate);

	/**
	 * Returns the activity set ID of this social activity.
	 *
	 * @return the activity set ID of this social activity
	 */
	public long getActivitySetId();

	/**
	 * Sets the activity set ID of this social activity.
	 *
	 * @param activitySetId the activity set ID of this social activity
	 */
	public void setActivitySetId(long activitySetId);

	/**
	 * Returns the mirror activity ID of this social activity.
	 *
	 * @return the mirror activity ID of this social activity
	 */
	public long getMirrorActivityId();

	/**
	 * Sets the mirror activity ID of this social activity.
	 *
	 * @param mirrorActivityId the mirror activity ID of this social activity
	 */
	public void setMirrorActivityId(long mirrorActivityId);

	/**
	 * Returns the fully qualified class name of this social activity.
	 *
	 * @return the fully qualified class name of this social activity
	 */
	@Override
	public String getClassName();

	public void setClassName(String className);

	/**
	 * Returns the class name ID of this social activity.
	 *
	 * @return the class name ID of this social activity
	 */
	@Override
	public long getClassNameId();

	/**
	 * Sets the class name ID of this social activity.
	 *
	 * @param classNameId the class name ID of this social activity
	 */
	@Override
	public void setClassNameId(long classNameId);

	/**
	 * Returns the class p k of this social activity.
	 *
	 * @return the class p k of this social activity
	 */
	@Override
	public long getClassPK();

	/**
	 * Sets the class p k of this social activity.
	 *
	 * @param classPK the class p k of this social activity
	 */
	@Override
	public void setClassPK(long classPK);

	/**
	 * Returns the parent class name ID of this social activity.
	 *
	 * @return the parent class name ID of this social activity
	 */
	public long getParentClassNameId();

	/**
	 * Sets the parent class name ID of this social activity.
	 *
	 * @param parentClassNameId the parent class name ID of this social activity
	 */
	public void setParentClassNameId(long parentClassNameId);

	/**
	 * Returns the parent class p k of this social activity.
	 *
	 * @return the parent class p k of this social activity
	 */
	public long getParentClassPK();

	/**
	 * Sets the parent class p k of this social activity.
	 *
	 * @param parentClassPK the parent class p k of this social activity
	 */
	public void setParentClassPK(long parentClassPK);

	/**
	 * Returns the type of this social activity.
	 *
	 * @return the type of this social activity
	 */
	public int getType();

	/**
	 * Sets the type of this social activity.
	 *
	 * @param type the type of this social activity
	 */
	public void setType(int type);

	/**
	 * Returns the extra data of this social activity.
	 *
	 * @return the extra data of this social activity
	 */
	public String getExtraData();

	/**
	 * Sets the extra data of this social activity.
	 *
	 * @param extraData the extra data of this social activity
	 */
	public void setExtraData(String extraData);

	/**
	 * Returns the receiver user ID of this social activity.
	 *
	 * @return the receiver user ID of this social activity
	 */
	public long getReceiverUserId();

	/**
	 * Sets the receiver user ID of this social activity.
	 *
	 * @param receiverUserId the receiver user ID of this social activity
	 */
	public void setReceiverUserId(long receiverUserId);

	/**
	 * Returns the receiver user uuid of this social activity.
	 *
	 * @return the receiver user uuid of this social activity
	 */
	public String getReceiverUserUuid();

	/**
	 * Sets the receiver user uuid of this social activity.
	 *
	 * @param receiverUserUuid the receiver user uuid of this social activity
	 */
	public void setReceiverUserUuid(String receiverUserUuid);

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
	public int compareTo(SocialActivity socialActivity);

	@Override
	public int hashCode();

	@Override
	public CacheModel<SocialActivity> toCacheModel();

	@Override
	public SocialActivity toEscapedModel();

	@Override
	public SocialActivity toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}