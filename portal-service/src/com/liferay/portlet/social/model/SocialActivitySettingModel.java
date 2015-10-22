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

package com.liferay.portlet.social.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.PartitionedModel;
import com.liferay.portal.model.TypedModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

/**
 * The base model interface for the SocialActivitySetting service. Represents a row in the &quot;SocialActivitySetting&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portlet.social.model.impl.SocialActivitySettingModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portlet.social.model.impl.SocialActivitySettingImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SocialActivitySetting
 * @see com.liferay.portlet.social.model.impl.SocialActivitySettingImpl
 * @see com.liferay.portlet.social.model.impl.SocialActivitySettingModelImpl
 * @generated
 */
@ProviderType
public interface SocialActivitySettingModel extends BaseModel<SocialActivitySetting>,
	PartitionedModel, TypedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a social activity setting model instance should use the {@link SocialActivitySetting} interface instead.
	 */

	/**
	 * Returns the primary key of this social activity setting.
	 *
	 * @return the primary key of this social activity setting
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this social activity setting.
	 *
	 * @param primaryKey the primary key of this social activity setting
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the activity setting ID of this social activity setting.
	 *
	 * @return the activity setting ID of this social activity setting
	 */
	public long getActivitySettingId();

	/**
	 * Sets the activity setting ID of this social activity setting.
	 *
	 * @param activitySettingId the activity setting ID of this social activity setting
	 */
	public void setActivitySettingId(long activitySettingId);

	/**
	 * Returns the group ID of this social activity setting.
	 *
	 * @return the group ID of this social activity setting
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this social activity setting.
	 *
	 * @param groupId the group ID of this social activity setting
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this social activity setting.
	 *
	 * @return the company ID of this social activity setting
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this social activity setting.
	 *
	 * @param companyId the company ID of this social activity setting
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the fully qualified class name of this social activity setting.
	 *
	 * @return the fully qualified class name of this social activity setting
	 */
	@Override
	public String getClassName();

	public void setClassName(String className);

	/**
	 * Returns the class name ID of this social activity setting.
	 *
	 * @return the class name ID of this social activity setting
	 */
	@Override
	public long getClassNameId();

	/**
	 * Sets the class name ID of this social activity setting.
	 *
	 * @param classNameId the class name ID of this social activity setting
	 */
	@Override
	public void setClassNameId(long classNameId);

	/**
	 * Returns the activity type of this social activity setting.
	 *
	 * @return the activity type of this social activity setting
	 */
	public int getActivityType();

	/**
	 * Sets the activity type of this social activity setting.
	 *
	 * @param activityType the activity type of this social activity setting
	 */
	public void setActivityType(int activityType);

	/**
	 * Returns the name of this social activity setting.
	 *
	 * @return the name of this social activity setting
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this social activity setting.
	 *
	 * @param name the name of this social activity setting
	 */
	public void setName(String name);

	/**
	 * Returns the value of this social activity setting.
	 *
	 * @return the value of this social activity setting
	 */
	@AutoEscape
	public String getValue();

	/**
	 * Sets the value of this social activity setting.
	 *
	 * @param value the value of this social activity setting
	 */
	public void setValue(String value);

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
		com.liferay.portlet.social.model.SocialActivitySetting socialActivitySetting);

	@Override
	public int hashCode();

	@Override
	public CacheModel<com.liferay.portlet.social.model.SocialActivitySetting> toCacheModel();

	@Override
	public com.liferay.portlet.social.model.SocialActivitySetting toEscapedModel();

	@Override
	public com.liferay.portlet.social.model.SocialActivitySetting toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}