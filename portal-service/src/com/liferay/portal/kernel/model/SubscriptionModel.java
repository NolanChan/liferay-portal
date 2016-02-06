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

package com.liferay.portal.kernel.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the Subscription service. Represents a row in the &quot;Subscription&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portal.model.impl.SubscriptionModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portal.model.impl.SubscriptionImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Subscription
 * @see com.liferay.portal.model.impl.SubscriptionImpl
 * @see com.liferay.portal.model.impl.SubscriptionModelImpl
 * @generated
 */
@ProviderType
public interface SubscriptionModel extends AttachedModel, BaseModel<Subscription>,
	GroupedModel, MVCCModel, ShardedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a subscription model instance should use the {@link Subscription} interface instead.
	 */

	/**
	 * Returns the primary key of this subscription.
	 *
	 * @return the primary key of this subscription
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this subscription.
	 *
	 * @param primaryKey the primary key of this subscription
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this subscription.
	 *
	 * @return the mvcc version of this subscription
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this subscription.
	 *
	 * @param mvccVersion the mvcc version of this subscription
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the subscription ID of this subscription.
	 *
	 * @return the subscription ID of this subscription
	 */
	public long getSubscriptionId();

	/**
	 * Sets the subscription ID of this subscription.
	 *
	 * @param subscriptionId the subscription ID of this subscription
	 */
	public void setSubscriptionId(long subscriptionId);

	/**
	 * Returns the group ID of this subscription.
	 *
	 * @return the group ID of this subscription
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this subscription.
	 *
	 * @param groupId the group ID of this subscription
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this subscription.
	 *
	 * @return the company ID of this subscription
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this subscription.
	 *
	 * @param companyId the company ID of this subscription
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this subscription.
	 *
	 * @return the user ID of this subscription
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this subscription.
	 *
	 * @param userId the user ID of this subscription
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this subscription.
	 *
	 * @return the user uuid of this subscription
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this subscription.
	 *
	 * @param userUuid the user uuid of this subscription
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this subscription.
	 *
	 * @return the user name of this subscription
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this subscription.
	 *
	 * @param userName the user name of this subscription
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this subscription.
	 *
	 * @return the create date of this subscription
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this subscription.
	 *
	 * @param createDate the create date of this subscription
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this subscription.
	 *
	 * @return the modified date of this subscription
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this subscription.
	 *
	 * @param modifiedDate the modified date of this subscription
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the fully qualified class name of this subscription.
	 *
	 * @return the fully qualified class name of this subscription
	 */
	@Override
	public String getClassName();

	public void setClassName(String className);

	/**
	 * Returns the class name ID of this subscription.
	 *
	 * @return the class name ID of this subscription
	 */
	@Override
	public long getClassNameId();

	/**
	 * Sets the class name ID of this subscription.
	 *
	 * @param classNameId the class name ID of this subscription
	 */
	@Override
	public void setClassNameId(long classNameId);

	/**
	 * Returns the class p k of this subscription.
	 *
	 * @return the class p k of this subscription
	 */
	@Override
	public long getClassPK();

	/**
	 * Sets the class p k of this subscription.
	 *
	 * @param classPK the class p k of this subscription
	 */
	@Override
	public void setClassPK(long classPK);

	/**
	 * Returns the frequency of this subscription.
	 *
	 * @return the frequency of this subscription
	 */
	@AutoEscape
	public String getFrequency();

	/**
	 * Sets the frequency of this subscription.
	 *
	 * @param frequency the frequency of this subscription
	 */
	public void setFrequency(String frequency);

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
		com.liferay.portal.kernel.model.Subscription subscription);

	@Override
	public int hashCode();

	@Override
	public CacheModel<com.liferay.portal.kernel.model.Subscription> toCacheModel();

	@Override
	public com.liferay.portal.kernel.model.Subscription toEscapedModel();

	@Override
	public com.liferay.portal.kernel.model.Subscription toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}