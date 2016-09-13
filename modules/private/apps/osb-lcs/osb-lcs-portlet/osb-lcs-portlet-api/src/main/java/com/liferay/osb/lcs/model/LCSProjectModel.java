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

package com.liferay.osb.lcs.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the LCSProject service. Represents a row in the &quot;OSBLCS_LCSProject&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.osb.lcs.model.impl.LCSProjectModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.osb.lcs.model.impl.LCSProjectImpl}.
 * </p>
 *
 * @author Igor Beslic
 * @see LCSProject
 * @see com.liferay.osb.lcs.model.impl.LCSProjectImpl
 * @see com.liferay.osb.lcs.model.impl.LCSProjectModelImpl
 * @generated
 */
@ProviderType
public interface LCSProjectModel extends BaseModel<LCSProject> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a l c s project model instance should use the {@link LCSProject} interface instead.
	 */

	/**
	 * Returns the primary key of this l c s project.
	 *
	 * @return the primary key of this l c s project
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this l c s project.
	 *
	 * @param primaryKey the primary key of this l c s project
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the lcs project ID of this l c s project.
	 *
	 * @return the lcs project ID of this l c s project
	 */
	public long getLcsProjectId();

	/**
	 * Sets the lcs project ID of this l c s project.
	 *
	 * @param lcsProjectId the lcs project ID of this l c s project
	 */
	public void setLcsProjectId(long lcsProjectId);

	/**
	 * Returns the create date of this l c s project.
	 *
	 * @return the create date of this l c s project
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this l c s project.
	 *
	 * @param createDate the create date of this l c s project
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this l c s project.
	 *
	 * @return the modified date of this l c s project
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this l c s project.
	 *
	 * @param modifiedDate the modified date of this l c s project
	 */
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the source system name of this l c s project.
	 *
	 * @return the source system name of this l c s project
	 */
	@AutoEscape
	public String getSourceSystemName();

	/**
	 * Sets the source system name of this l c s project.
	 *
	 * @param sourceSystemName the source system name of this l c s project
	 */
	public void setSourceSystemName(String sourceSystemName);

	/**
	 * Returns the name of this l c s project.
	 *
	 * @return the name of this l c s project
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this l c s project.
	 *
	 * @param name the name of this l c s project
	 */
	public void setName(String name);

	/**
	 * Returns the organization ID of this l c s project.
	 *
	 * @return the organization ID of this l c s project
	 */
	public long getOrganizationId();

	/**
	 * Sets the organization ID of this l c s project.
	 *
	 * @param organizationId the organization ID of this l c s project
	 */
	public void setOrganizationId(long organizationId);

	/**
	 * Returns the address ID of this l c s project.
	 *
	 * @return the address ID of this l c s project
	 */
	public long getAddressId();

	/**
	 * Sets the address ID of this l c s project.
	 *
	 * @param addressId the address ID of this l c s project
	 */
	public void setAddressId(long addressId);

	/**
	 * Returns the account entry ID of this l c s project.
	 *
	 * @return the account entry ID of this l c s project
	 */
	public long getAccountEntryId();

	/**
	 * Sets the account entry ID of this l c s project.
	 *
	 * @param accountEntryId the account entry ID of this l c s project
	 */
	public void setAccountEntryId(long accountEntryId);

	/**
	 * Returns the corp project ID of this l c s project.
	 *
	 * @return the corp project ID of this l c s project
	 */
	public long getCorpProjectId();

	/**
	 * Sets the corp project ID of this l c s project.
	 *
	 * @param corpProjectId the corp project ID of this l c s project
	 */
	public void setCorpProjectId(long corpProjectId);

	/**
	 * Returns the contact email address of this l c s project.
	 *
	 * @return the contact email address of this l c s project
	 */
	@AutoEscape
	public String getContactEmailAddress();

	/**
	 * Sets the contact email address of this l c s project.
	 *
	 * @param contactEmailAddress the contact email address of this l c s project
	 */
	public void setContactEmailAddress(String contactEmailAddress);

	/**
	 * Returns the phone number of this l c s project.
	 *
	 * @return the phone number of this l c s project
	 */
	@AutoEscape
	public String getPhoneNumber();

	/**
	 * Sets the phone number of this l c s project.
	 *
	 * @param phoneNumber the phone number of this l c s project
	 */
	public void setPhoneNumber(String phoneNumber);

	/**
	 * Returns the subscription active of this l c s project.
	 *
	 * @return the subscription active of this l c s project
	 */
	public boolean getSubscriptionActive();

	/**
	 * Returns <code>true</code> if this l c s project is subscription active.
	 *
	 * @return <code>true</code> if this l c s project is subscription active; <code>false</code> otherwise
	 */
	public boolean isSubscriptionActive();

	/**
	 * Sets whether this l c s project is subscription active.
	 *
	 * @param subscriptionActive the subscription active of this l c s project
	 */
	public void setSubscriptionActive(boolean subscriptionActive);

	/**
	 * Returns the archived of this l c s project.
	 *
	 * @return the archived of this l c s project
	 */
	public boolean getArchived();

	/**
	 * Returns <code>true</code> if this l c s project is archived.
	 *
	 * @return <code>true</code> if this l c s project is archived; <code>false</code> otherwise
	 */
	public boolean isArchived();

	/**
	 * Sets whether this l c s project is archived.
	 *
	 * @param archived the archived of this l c s project
	 */
	public void setArchived(boolean archived);

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
	public int compareTo(LCSProject lcsProject);

	@Override
	public int hashCode();

	@Override
	public CacheModel<LCSProject> toCacheModel();

	@Override
	public LCSProject toEscapedModel();

	@Override
	public LCSProject toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}