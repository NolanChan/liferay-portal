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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link LCSProject}.
 * </p>
 *
 * @author Igor Beslic
 * @see LCSProject
 * @generated
 */
@ProviderType
public class LCSProjectWrapper implements LCSProject, ModelWrapper<LCSProject> {
	public LCSProjectWrapper(LCSProject lcsProject) {
		_lcsProject = lcsProject;
	}

	@Override
	public Class<?> getModelClass() {
		return LCSProject.class;
	}

	@Override
	public String getModelClassName() {
		return LCSProject.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("lcsProjectId", getLcsProjectId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("sourceSystemName", getSourceSystemName());
		attributes.put("name", getName());
		attributes.put("organizationId", getOrganizationId());
		attributes.put("addressId", getAddressId());
		attributes.put("accountEntryId", getAccountEntryId());
		attributes.put("corpProjectId", getCorpProjectId());
		attributes.put("contactEmailAddress", getContactEmailAddress());
		attributes.put("phoneNumber", getPhoneNumber());
		attributes.put("subscriptionActive", getSubscriptionActive());
		attributes.put("archived", getArchived());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long lcsProjectId = (Long)attributes.get("lcsProjectId");

		if (lcsProjectId != null) {
			setLcsProjectId(lcsProjectId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String sourceSystemName = (String)attributes.get("sourceSystemName");

		if (sourceSystemName != null) {
			setSourceSystemName(sourceSystemName);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		Long organizationId = (Long)attributes.get("organizationId");

		if (organizationId != null) {
			setOrganizationId(organizationId);
		}

		Long addressId = (Long)attributes.get("addressId");

		if (addressId != null) {
			setAddressId(addressId);
		}

		Long accountEntryId = (Long)attributes.get("accountEntryId");

		if (accountEntryId != null) {
			setAccountEntryId(accountEntryId);
		}

		Long corpProjectId = (Long)attributes.get("corpProjectId");

		if (corpProjectId != null) {
			setCorpProjectId(corpProjectId);
		}

		String contactEmailAddress = (String)attributes.get(
				"contactEmailAddress");

		if (contactEmailAddress != null) {
			setContactEmailAddress(contactEmailAddress);
		}

		String phoneNumber = (String)attributes.get("phoneNumber");

		if (phoneNumber != null) {
			setPhoneNumber(phoneNumber);
		}

		Boolean subscriptionActive = (Boolean)attributes.get(
				"subscriptionActive");

		if (subscriptionActive != null) {
			setSubscriptionActive(subscriptionActive);
		}

		Boolean archived = (Boolean)attributes.get("archived");

		if (archived != null) {
			setArchived(archived);
		}
	}

	@Override
	public LCSProject toEscapedModel() {
		return new LCSProjectWrapper(_lcsProject.toEscapedModel());
	}

	@Override
	public LCSProject toUnescapedModel() {
		return new LCSProjectWrapper(_lcsProject.toUnescapedModel());
	}

	/**
	* Returns the archived of this l c s project.
	*
	* @return the archived of this l c s project
	*/
	@Override
	public boolean getArchived() {
		return _lcsProject.getArchived();
	}

	/**
	* Returns the subscription active of this l c s project.
	*
	* @return the subscription active of this l c s project
	*/
	@Override
	public boolean getSubscriptionActive() {
		return _lcsProject.getSubscriptionActive();
	}

	/**
	* Returns <code>true</code> if this l c s project is archived.
	*
	* @return <code>true</code> if this l c s project is archived; <code>false</code> otherwise
	*/
	@Override
	public boolean isArchived() {
		return _lcsProject.isArchived();
	}

	@Override
	public boolean isCachedModel() {
		return _lcsProject.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _lcsProject.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _lcsProject.isNew();
	}

	/**
	* Returns <code>true</code> if this l c s project is subscription active.
	*
	* @return <code>true</code> if this l c s project is subscription active; <code>false</code> otherwise
	*/
	@Override
	public boolean isSubscriptionActive() {
		return _lcsProject.isSubscriptionActive();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _lcsProject.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<LCSProject> toCacheModel() {
		return _lcsProject.toCacheModel();
	}

	@Override
	public int compareTo(LCSProject lcsProject) {
		return _lcsProject.compareTo(lcsProject);
	}

	@Override
	public int hashCode() {
		return _lcsProject.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _lcsProject.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new LCSProjectWrapper((LCSProject)_lcsProject.clone());
	}

	/**
	* Returns the contact email address of this l c s project.
	*
	* @return the contact email address of this l c s project
	*/
	@Override
	public java.lang.String getContactEmailAddress() {
		return _lcsProject.getContactEmailAddress();
	}

	/**
	* Returns the name of this l c s project.
	*
	* @return the name of this l c s project
	*/
	@Override
	public java.lang.String getName() {
		return _lcsProject.getName();
	}

	/**
	* Returns the phone number of this l c s project.
	*
	* @return the phone number of this l c s project
	*/
	@Override
	public java.lang.String getPhoneNumber() {
		return _lcsProject.getPhoneNumber();
	}

	/**
	* Returns the source system name of this l c s project.
	*
	* @return the source system name of this l c s project
	*/
	@Override
	public java.lang.String getSourceSystemName() {
		return _lcsProject.getSourceSystemName();
	}

	@Override
	public java.lang.String toString() {
		return _lcsProject.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _lcsProject.toXmlString();
	}

	/**
	* Returns the create date of this l c s project.
	*
	* @return the create date of this l c s project
	*/
	@Override
	public Date getCreateDate() {
		return _lcsProject.getCreateDate();
	}

	/**
	* Returns the modified date of this l c s project.
	*
	* @return the modified date of this l c s project
	*/
	@Override
	public Date getModifiedDate() {
		return _lcsProject.getModifiedDate();
	}

	/**
	* Returns the account entry ID of this l c s project.
	*
	* @return the account entry ID of this l c s project
	*/
	@Override
	public long getAccountEntryId() {
		return _lcsProject.getAccountEntryId();
	}

	/**
	* Returns the address ID of this l c s project.
	*
	* @return the address ID of this l c s project
	*/
	@Override
	public long getAddressId() {
		return _lcsProject.getAddressId();
	}

	/**
	* Returns the corp project ID of this l c s project.
	*
	* @return the corp project ID of this l c s project
	*/
	@Override
	public long getCorpProjectId() {
		return _lcsProject.getCorpProjectId();
	}

	/**
	* Returns the lcs project ID of this l c s project.
	*
	* @return the lcs project ID of this l c s project
	*/
	@Override
	public long getLcsProjectId() {
		return _lcsProject.getLcsProjectId();
	}

	/**
	* Returns the organization ID of this l c s project.
	*
	* @return the organization ID of this l c s project
	*/
	@Override
	public long getOrganizationId() {
		return _lcsProject.getOrganizationId();
	}

	/**
	* Returns the primary key of this l c s project.
	*
	* @return the primary key of this l c s project
	*/
	@Override
	public long getPrimaryKey() {
		return _lcsProject.getPrimaryKey();
	}

	@Override
	public void persist() {
		_lcsProject.persist();
	}

	/**
	* Sets the account entry ID of this l c s project.
	*
	* @param accountEntryId the account entry ID of this l c s project
	*/
	@Override
	public void setAccountEntryId(long accountEntryId) {
		_lcsProject.setAccountEntryId(accountEntryId);
	}

	/**
	* Sets the address ID of this l c s project.
	*
	* @param addressId the address ID of this l c s project
	*/
	@Override
	public void setAddressId(long addressId) {
		_lcsProject.setAddressId(addressId);
	}

	/**
	* Sets whether this l c s project is archived.
	*
	* @param archived the archived of this l c s project
	*/
	@Override
	public void setArchived(boolean archived) {
		_lcsProject.setArchived(archived);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_lcsProject.setCachedModel(cachedModel);
	}

	/**
	* Sets the contact email address of this l c s project.
	*
	* @param contactEmailAddress the contact email address of this l c s project
	*/
	@Override
	public void setContactEmailAddress(java.lang.String contactEmailAddress) {
		_lcsProject.setContactEmailAddress(contactEmailAddress);
	}

	/**
	* Sets the corp project ID of this l c s project.
	*
	* @param corpProjectId the corp project ID of this l c s project
	*/
	@Override
	public void setCorpProjectId(long corpProjectId) {
		_lcsProject.setCorpProjectId(corpProjectId);
	}

	/**
	* Sets the create date of this l c s project.
	*
	* @param createDate the create date of this l c s project
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_lcsProject.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_lcsProject.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_lcsProject.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_lcsProject.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the lcs project ID of this l c s project.
	*
	* @param lcsProjectId the lcs project ID of this l c s project
	*/
	@Override
	public void setLcsProjectId(long lcsProjectId) {
		_lcsProject.setLcsProjectId(lcsProjectId);
	}

	/**
	* Sets the modified date of this l c s project.
	*
	* @param modifiedDate the modified date of this l c s project
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_lcsProject.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this l c s project.
	*
	* @param name the name of this l c s project
	*/
	@Override
	public void setName(java.lang.String name) {
		_lcsProject.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_lcsProject.setNew(n);
	}

	/**
	* Sets the organization ID of this l c s project.
	*
	* @param organizationId the organization ID of this l c s project
	*/
	@Override
	public void setOrganizationId(long organizationId) {
		_lcsProject.setOrganizationId(organizationId);
	}

	/**
	* Sets the phone number of this l c s project.
	*
	* @param phoneNumber the phone number of this l c s project
	*/
	@Override
	public void setPhoneNumber(java.lang.String phoneNumber) {
		_lcsProject.setPhoneNumber(phoneNumber);
	}

	/**
	* Sets the primary key of this l c s project.
	*
	* @param primaryKey the primary key of this l c s project
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_lcsProject.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_lcsProject.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the source system name of this l c s project.
	*
	* @param sourceSystemName the source system name of this l c s project
	*/
	@Override
	public void setSourceSystemName(java.lang.String sourceSystemName) {
		_lcsProject.setSourceSystemName(sourceSystemName);
	}

	/**
	* Sets whether this l c s project is subscription active.
	*
	* @param subscriptionActive the subscription active of this l c s project
	*/
	@Override
	public void setSubscriptionActive(boolean subscriptionActive) {
		_lcsProject.setSubscriptionActive(subscriptionActive);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LCSProjectWrapper)) {
			return false;
		}

		LCSProjectWrapper lcsProjectWrapper = (LCSProjectWrapper)obj;

		if (Objects.equals(_lcsProject, lcsProjectWrapper._lcsProject)) {
			return true;
		}

		return false;
	}

	@Override
	public LCSProject getWrappedModel() {
		return _lcsProject;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _lcsProject.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _lcsProject.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_lcsProject.resetOriginalValues();
	}

	private final LCSProject _lcsProject;
}