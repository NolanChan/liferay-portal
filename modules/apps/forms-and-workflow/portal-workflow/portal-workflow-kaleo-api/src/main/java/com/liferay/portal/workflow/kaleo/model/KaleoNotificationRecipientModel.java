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

package com.liferay.portal.workflow.kaleo.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.GroupedModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the KaleoNotificationRecipient service. Represents a row in the &quot;KaleoNotificationRecipient&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoNotificationRecipientModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoNotificationRecipientImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoNotificationRecipient
 * @see com.liferay.portal.workflow.kaleo.model.impl.KaleoNotificationRecipientImpl
 * @see com.liferay.portal.workflow.kaleo.model.impl.KaleoNotificationRecipientModelImpl
 * @generated
 */
@ProviderType
public interface KaleoNotificationRecipientModel extends BaseModel<KaleoNotificationRecipient>,
	GroupedModel, ShardedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a kaleo notification recipient model instance should use the {@link KaleoNotificationRecipient} interface instead.
	 */

	/**
	 * Returns the primary key of this kaleo notification recipient.
	 *
	 * @return the primary key of this kaleo notification recipient
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this kaleo notification recipient.
	 *
	 * @param primaryKey the primary key of this kaleo notification recipient
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the kaleo notification recipient ID of this kaleo notification recipient.
	 *
	 * @return the kaleo notification recipient ID of this kaleo notification recipient
	 */
	public long getKaleoNotificationRecipientId();

	/**
	 * Sets the kaleo notification recipient ID of this kaleo notification recipient.
	 *
	 * @param kaleoNotificationRecipientId the kaleo notification recipient ID of this kaleo notification recipient
	 */
	public void setKaleoNotificationRecipientId(
		long kaleoNotificationRecipientId);

	/**
	 * Returns the group ID of this kaleo notification recipient.
	 *
	 * @return the group ID of this kaleo notification recipient
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this kaleo notification recipient.
	 *
	 * @param groupId the group ID of this kaleo notification recipient
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this kaleo notification recipient.
	 *
	 * @return the company ID of this kaleo notification recipient
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this kaleo notification recipient.
	 *
	 * @param companyId the company ID of this kaleo notification recipient
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this kaleo notification recipient.
	 *
	 * @return the user ID of this kaleo notification recipient
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this kaleo notification recipient.
	 *
	 * @param userId the user ID of this kaleo notification recipient
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this kaleo notification recipient.
	 *
	 * @return the user uuid of this kaleo notification recipient
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this kaleo notification recipient.
	 *
	 * @param userUuid the user uuid of this kaleo notification recipient
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this kaleo notification recipient.
	 *
	 * @return the user name of this kaleo notification recipient
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this kaleo notification recipient.
	 *
	 * @param userName the user name of this kaleo notification recipient
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this kaleo notification recipient.
	 *
	 * @return the create date of this kaleo notification recipient
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this kaleo notification recipient.
	 *
	 * @param createDate the create date of this kaleo notification recipient
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this kaleo notification recipient.
	 *
	 * @return the modified date of this kaleo notification recipient
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this kaleo notification recipient.
	 *
	 * @param modifiedDate the modified date of this kaleo notification recipient
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the kaleo definition ID of this kaleo notification recipient.
	 *
	 * @return the kaleo definition ID of this kaleo notification recipient
	 */
	public long getKaleoDefinitionId();

	/**
	 * Sets the kaleo definition ID of this kaleo notification recipient.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID of this kaleo notification recipient
	 */
	public void setKaleoDefinitionId(long kaleoDefinitionId);

	/**
	 * Returns the kaleo notification ID of this kaleo notification recipient.
	 *
	 * @return the kaleo notification ID of this kaleo notification recipient
	 */
	public long getKaleoNotificationId();

	/**
	 * Sets the kaleo notification ID of this kaleo notification recipient.
	 *
	 * @param kaleoNotificationId the kaleo notification ID of this kaleo notification recipient
	 */
	public void setKaleoNotificationId(long kaleoNotificationId);

	/**
	 * Returns the recipient class name of this kaleo notification recipient.
	 *
	 * @return the recipient class name of this kaleo notification recipient
	 */
	@AutoEscape
	public String getRecipientClassName();

	/**
	 * Sets the recipient class name of this kaleo notification recipient.
	 *
	 * @param recipientClassName the recipient class name of this kaleo notification recipient
	 */
	public void setRecipientClassName(String recipientClassName);

	/**
	 * Returns the recipient class p k of this kaleo notification recipient.
	 *
	 * @return the recipient class p k of this kaleo notification recipient
	 */
	public long getRecipientClassPK();

	/**
	 * Sets the recipient class p k of this kaleo notification recipient.
	 *
	 * @param recipientClassPK the recipient class p k of this kaleo notification recipient
	 */
	public void setRecipientClassPK(long recipientClassPK);

	/**
	 * Returns the recipient role type of this kaleo notification recipient.
	 *
	 * @return the recipient role type of this kaleo notification recipient
	 */
	public int getRecipientRoleType();

	/**
	 * Sets the recipient role type of this kaleo notification recipient.
	 *
	 * @param recipientRoleType the recipient role type of this kaleo notification recipient
	 */
	public void setRecipientRoleType(int recipientRoleType);

	/**
	 * Returns the recipient script of this kaleo notification recipient.
	 *
	 * @return the recipient script of this kaleo notification recipient
	 */
	@AutoEscape
	public String getRecipientScript();

	/**
	 * Sets the recipient script of this kaleo notification recipient.
	 *
	 * @param recipientScript the recipient script of this kaleo notification recipient
	 */
	public void setRecipientScript(String recipientScript);

	/**
	 * Returns the recipient script language of this kaleo notification recipient.
	 *
	 * @return the recipient script language of this kaleo notification recipient
	 */
	@AutoEscape
	public String getRecipientScriptLanguage();

	/**
	 * Sets the recipient script language of this kaleo notification recipient.
	 *
	 * @param recipientScriptLanguage the recipient script language of this kaleo notification recipient
	 */
	public void setRecipientScriptLanguage(String recipientScriptLanguage);

	/**
	 * Returns the recipient script contexts of this kaleo notification recipient.
	 *
	 * @return the recipient script contexts of this kaleo notification recipient
	 */
	@AutoEscape
	public String getRecipientScriptContexts();

	/**
	 * Sets the recipient script contexts of this kaleo notification recipient.
	 *
	 * @param recipientScriptContexts the recipient script contexts of this kaleo notification recipient
	 */
	public void setRecipientScriptContexts(String recipientScriptContexts);

	/**
	 * Returns the address of this kaleo notification recipient.
	 *
	 * @return the address of this kaleo notification recipient
	 */
	@AutoEscape
	public String getAddress();

	/**
	 * Sets the address of this kaleo notification recipient.
	 *
	 * @param address the address of this kaleo notification recipient
	 */
	public void setAddress(String address);

	/**
	 * Returns the notification reception type of this kaleo notification recipient.
	 *
	 * @return the notification reception type of this kaleo notification recipient
	 */
	@AutoEscape
	public String getNotificationReceptionType();

	/**
	 * Sets the notification reception type of this kaleo notification recipient.
	 *
	 * @param notificationReceptionType the notification reception type of this kaleo notification recipient
	 */
	public void setNotificationReceptionType(String notificationReceptionType);

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
	public int compareTo(KaleoNotificationRecipient kaleoNotificationRecipient);

	@Override
	public int hashCode();

	@Override
	public CacheModel<KaleoNotificationRecipient> toCacheModel();

	@Override
	public KaleoNotificationRecipient toEscapedModel();

	@Override
	public KaleoNotificationRecipient toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}