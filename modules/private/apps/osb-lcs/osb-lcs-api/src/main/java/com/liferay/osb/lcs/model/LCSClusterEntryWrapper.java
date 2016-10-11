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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link LCSClusterEntry}.
 * </p>
 *
 * @author Igor Beslic
 * @see LCSClusterEntry
 * @generated
 */
@ProviderType
public class LCSClusterEntryWrapper implements LCSClusterEntry,
	ModelWrapper<LCSClusterEntry> {
	public LCSClusterEntryWrapper(LCSClusterEntry lcsClusterEntry) {
		_lcsClusterEntry = lcsClusterEntry;
	}

	@Override
	public Class<?> getModelClass() {
		return LCSClusterEntry.class;
	}

	@Override
	public String getModelClassName() {
		return LCSClusterEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("lcsClusterEntryId", getLcsClusterEntryId());
		attributes.put("lcsProjectId", getLcsProjectId());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("elastic", getElastic());
		attributes.put("highPageLoadTime", getHighPageLoadTime());
		attributes.put("location", getLocation());
		attributes.put("mediumPageLoadTime", getMediumPageLoadTime());
		attributes.put("subscriptionType", getSubscriptionType());
		attributes.put("type", getType());
		attributes.put("archived", getArchived());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long lcsClusterEntryId = (Long)attributes.get("lcsClusterEntryId");

		if (lcsClusterEntryId != null) {
			setLcsClusterEntryId(lcsClusterEntryId);
		}

		Long lcsProjectId = (Long)attributes.get("lcsProjectId");

		if (lcsProjectId != null) {
			setLcsProjectId(lcsProjectId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Boolean elastic = (Boolean)attributes.get("elastic");

		if (elastic != null) {
			setElastic(elastic);
		}

		Integer highPageLoadTime = (Integer)attributes.get("highPageLoadTime");

		if (highPageLoadTime != null) {
			setHighPageLoadTime(highPageLoadTime);
		}

		String location = (String)attributes.get("location");

		if (location != null) {
			setLocation(location);
		}

		Integer mediumPageLoadTime = (Integer)attributes.get(
				"mediumPageLoadTime");

		if (mediumPageLoadTime != null) {
			setMediumPageLoadTime(mediumPageLoadTime);
		}

		String subscriptionType = (String)attributes.get("subscriptionType");

		if (subscriptionType != null) {
			setSubscriptionType(subscriptionType);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Boolean archived = (Boolean)attributes.get("archived");

		if (archived != null) {
			setArchived(archived);
		}
	}

	@Override
	public LCSClusterEntry toEscapedModel() {
		return new LCSClusterEntryWrapper(_lcsClusterEntry.toEscapedModel());
	}

	@Override
	public LCSClusterEntry toUnescapedModel() {
		return new LCSClusterEntryWrapper(_lcsClusterEntry.toUnescapedModel());
	}

	/**
	* Returns the archived of this l c s cluster entry.
	*
	* @return the archived of this l c s cluster entry
	*/
	@Override
	public boolean getArchived() {
		return _lcsClusterEntry.getArchived();
	}

	/**
	* Returns the elastic of this l c s cluster entry.
	*
	* @return the elastic of this l c s cluster entry
	*/
	@Override
	public boolean getElastic() {
		return _lcsClusterEntry.getElastic();
	}

	@Override
	public boolean hasOfflineLCSClusterNode() {
		return _lcsClusterEntry.hasOfflineLCSClusterNode();
	}

	/**
	* Returns <code>true</code> if this l c s cluster entry is archived.
	*
	* @return <code>true</code> if this l c s cluster entry is archived; <code>false</code> otherwise
	*/
	@Override
	public boolean isArchived() {
		return _lcsClusterEntry.isArchived();
	}

	@Override
	public boolean isCachedModel() {
		return _lcsClusterEntry.isCachedModel();
	}

	@Override
	public boolean isCluster() {
		return _lcsClusterEntry.isCluster();
	}

	/**
	* Returns <code>true</code> if this l c s cluster entry is elastic.
	*
	* @return <code>true</code> if this l c s cluster entry is elastic; <code>false</code> otherwise
	*/
	@Override
	public boolean isElastic() {
		return _lcsClusterEntry.isElastic();
	}

	@Override
	public boolean isEnvironment() {
		return _lcsClusterEntry.isEnvironment();
	}

	@Override
	public boolean isEscapedModel() {
		return _lcsClusterEntry.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _lcsClusterEntry.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _lcsClusterEntry.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<LCSClusterEntry> toCacheModel() {
		return _lcsClusterEntry.toCacheModel();
	}

	@Override
	public int compareTo(LCSClusterEntry lcsClusterEntry) {
		return _lcsClusterEntry.compareTo(lcsClusterEntry);
	}

	/**
	* Returns the high page load time of this l c s cluster entry.
	*
	* @return the high page load time of this l c s cluster entry
	*/
	@Override
	public int getHighPageLoadTime() {
		return _lcsClusterEntry.getHighPageLoadTime();
	}

	/**
	* Returns the medium page load time of this l c s cluster entry.
	*
	* @return the medium page load time of this l c s cluster entry
	*/
	@Override
	public int getMediumPageLoadTime() {
		return _lcsClusterEntry.getMediumPageLoadTime();
	}

	/**
	* Returns the type of this l c s cluster entry.
	*
	* @return the type of this l c s cluster entry
	*/
	@Override
	public int getType() {
		return _lcsClusterEntry.getType();
	}

	@Override
	public int hashCode() {
		return _lcsClusterEntry.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _lcsClusterEntry.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new LCSClusterEntryWrapper((LCSClusterEntry)_lcsClusterEntry.clone());
	}

	/**
	* Returns the description of this l c s cluster entry.
	*
	* @return the description of this l c s cluster entry
	*/
	@Override
	public java.lang.String getDescription() {
		return _lcsClusterEntry.getDescription();
	}

	/**
	* Returns the location of this l c s cluster entry.
	*
	* @return the location of this l c s cluster entry
	*/
	@Override
	public java.lang.String getLocation() {
		return _lcsClusterEntry.getLocation();
	}

	/**
	* Returns the name of this l c s cluster entry.
	*
	* @return the name of this l c s cluster entry
	*/
	@Override
	public java.lang.String getName() {
		return _lcsClusterEntry.getName();
	}

	/**
	* Returns the subscription type of this l c s cluster entry.
	*
	* @return the subscription type of this l c s cluster entry
	*/
	@Override
	public java.lang.String getSubscriptionType() {
		return _lcsClusterEntry.getSubscriptionType();
	}

	@Override
	public java.lang.String toString() {
		return _lcsClusterEntry.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _lcsClusterEntry.toXmlString();
	}

	/**
	* Returns the lcs cluster entry ID of this l c s cluster entry.
	*
	* @return the lcs cluster entry ID of this l c s cluster entry
	*/
	@Override
	public long getLcsClusterEntryId() {
		return _lcsClusterEntry.getLcsClusterEntryId();
	}

	/**
	* Returns the lcs project ID of this l c s cluster entry.
	*
	* @return the lcs project ID of this l c s cluster entry
	*/
	@Override
	public long getLcsProjectId() {
		return _lcsClusterEntry.getLcsProjectId();
	}

	/**
	* Returns the primary key of this l c s cluster entry.
	*
	* @return the primary key of this l c s cluster entry
	*/
	@Override
	public long getPrimaryKey() {
		return _lcsClusterEntry.getPrimaryKey();
	}

	@Override
	public void persist() {
		_lcsClusterEntry.persist();
	}

	/**
	* Sets whether this l c s cluster entry is archived.
	*
	* @param archived the archived of this l c s cluster entry
	*/
	@Override
	public void setArchived(boolean archived) {
		_lcsClusterEntry.setArchived(archived);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_lcsClusterEntry.setCachedModel(cachedModel);
	}

	/**
	* Sets the description of this l c s cluster entry.
	*
	* @param description the description of this l c s cluster entry
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_lcsClusterEntry.setDescription(description);
	}

	/**
	* Sets whether this l c s cluster entry is elastic.
	*
	* @param elastic the elastic of this l c s cluster entry
	*/
	@Override
	public void setElastic(boolean elastic) {
		_lcsClusterEntry.setElastic(elastic);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_lcsClusterEntry.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_lcsClusterEntry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_lcsClusterEntry.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the high page load time of this l c s cluster entry.
	*
	* @param highPageLoadTime the high page load time of this l c s cluster entry
	*/
	@Override
	public void setHighPageLoadTime(int highPageLoadTime) {
		_lcsClusterEntry.setHighPageLoadTime(highPageLoadTime);
	}

	/**
	* Sets the lcs cluster entry ID of this l c s cluster entry.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID of this l c s cluster entry
	*/
	@Override
	public void setLcsClusterEntryId(long lcsClusterEntryId) {
		_lcsClusterEntry.setLcsClusterEntryId(lcsClusterEntryId);
	}

	/**
	* Sets the lcs project ID of this l c s cluster entry.
	*
	* @param lcsProjectId the lcs project ID of this l c s cluster entry
	*/
	@Override
	public void setLcsProjectId(long lcsProjectId) {
		_lcsClusterEntry.setLcsProjectId(lcsProjectId);
	}

	/**
	* Sets the location of this l c s cluster entry.
	*
	* @param location the location of this l c s cluster entry
	*/
	@Override
	public void setLocation(java.lang.String location) {
		_lcsClusterEntry.setLocation(location);
	}

	/**
	* Sets the medium page load time of this l c s cluster entry.
	*
	* @param mediumPageLoadTime the medium page load time of this l c s cluster entry
	*/
	@Override
	public void setMediumPageLoadTime(int mediumPageLoadTime) {
		_lcsClusterEntry.setMediumPageLoadTime(mediumPageLoadTime);
	}

	/**
	* Sets the name of this l c s cluster entry.
	*
	* @param name the name of this l c s cluster entry
	*/
	@Override
	public void setName(java.lang.String name) {
		_lcsClusterEntry.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_lcsClusterEntry.setNew(n);
	}

	/**
	* Sets the primary key of this l c s cluster entry.
	*
	* @param primaryKey the primary key of this l c s cluster entry
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_lcsClusterEntry.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_lcsClusterEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the subscription type of this l c s cluster entry.
	*
	* @param subscriptionType the subscription type of this l c s cluster entry
	*/
	@Override
	public void setSubscriptionType(java.lang.String subscriptionType) {
		_lcsClusterEntry.setSubscriptionType(subscriptionType);
	}

	/**
	* Sets the type of this l c s cluster entry.
	*
	* @param type the type of this l c s cluster entry
	*/
	@Override
	public void setType(int type) {
		_lcsClusterEntry.setType(type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LCSClusterEntryWrapper)) {
			return false;
		}

		LCSClusterEntryWrapper lcsClusterEntryWrapper = (LCSClusterEntryWrapper)obj;

		if (Objects.equals(_lcsClusterEntry,
					lcsClusterEntryWrapper._lcsClusterEntry)) {
			return true;
		}

		return false;
	}

	@Override
	public LCSClusterEntry getWrappedModel() {
		return _lcsClusterEntry;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _lcsClusterEntry.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _lcsClusterEntry.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_lcsClusterEntry.resetOriginalValues();
	}

	private final LCSClusterEntry _lcsClusterEntry;
}