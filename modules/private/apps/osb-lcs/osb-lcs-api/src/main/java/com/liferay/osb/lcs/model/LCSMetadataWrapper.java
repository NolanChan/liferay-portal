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
 * This class is a wrapper for {@link LCSMetadata}.
 * </p>
 *
 * @author Igor Beslic
 * @see LCSMetadata
 * @generated
 */
@ProviderType
public class LCSMetadataWrapper implements LCSMetadata,
	ModelWrapper<LCSMetadata> {
	public LCSMetadataWrapper(LCSMetadata lcsMetadata) {
		_lcsMetadata = lcsMetadata;
	}

	@Override
	public Class<?> getModelClass() {
		return LCSMetadata.class;
	}

	@Override
	public String getModelClassName() {
		return LCSMetadata.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("lcsMetadataId", getLcsMetadataId());
		attributes.put("availabilityIndex", getAvailabilityIndex());
		attributes.put("buildNumber", getBuildNumber());
		attributes.put("gitTag", getGitTag());
		attributes.put("portalEdition", getPortalEdition());
		attributes.put("supportedLCSPortlet", getSupportedLCSPortlet());
		attributes.put("supportedPatchingTool", getSupportedPatchingTool());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long lcsMetadataId = (Long)attributes.get("lcsMetadataId");

		if (lcsMetadataId != null) {
			setLcsMetadataId(lcsMetadataId);
		}

		Long availabilityIndex = (Long)attributes.get("availabilityIndex");

		if (availabilityIndex != null) {
			setAvailabilityIndex(availabilityIndex);
		}

		Integer buildNumber = (Integer)attributes.get("buildNumber");

		if (buildNumber != null) {
			setBuildNumber(buildNumber);
		}

		String gitTag = (String)attributes.get("gitTag");

		if (gitTag != null) {
			setGitTag(gitTag);
		}

		String portalEdition = (String)attributes.get("portalEdition");

		if (portalEdition != null) {
			setPortalEdition(portalEdition);
		}

		Integer supportedLCSPortlet = (Integer)attributes.get(
				"supportedLCSPortlet");

		if (supportedLCSPortlet != null) {
			setSupportedLCSPortlet(supportedLCSPortlet);
		}

		Integer supportedPatchingTool = (Integer)attributes.get(
				"supportedPatchingTool");

		if (supportedPatchingTool != null) {
			setSupportedPatchingTool(supportedPatchingTool);
		}
	}

	@Override
	public LCSMetadata toEscapedModel() {
		return new LCSMetadataWrapper(_lcsMetadata.toEscapedModel());
	}

	@Override
	public LCSMetadata toUnescapedModel() {
		return new LCSMetadataWrapper(_lcsMetadata.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _lcsMetadata.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _lcsMetadata.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _lcsMetadata.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _lcsMetadata.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<LCSMetadata> toCacheModel() {
		return _lcsMetadata.toCacheModel();
	}

	@Override
	public int compareTo(LCSMetadata lcsMetadata) {
		return _lcsMetadata.compareTo(lcsMetadata);
	}

	/**
	* Returns the build number of this l c s metadata.
	*
	* @return the build number of this l c s metadata
	*/
	@Override
	public int getBuildNumber() {
		return _lcsMetadata.getBuildNumber();
	}

	/**
	* Returns the supported l c s portlet of this l c s metadata.
	*
	* @return the supported l c s portlet of this l c s metadata
	*/
	@Override
	public int getSupportedLCSPortlet() {
		return _lcsMetadata.getSupportedLCSPortlet();
	}

	/**
	* Returns the supported patching tool of this l c s metadata.
	*
	* @return the supported patching tool of this l c s metadata
	*/
	@Override
	public int getSupportedPatchingTool() {
		return _lcsMetadata.getSupportedPatchingTool();
	}

	@Override
	public int hashCode() {
		return _lcsMetadata.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _lcsMetadata.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new LCSMetadataWrapper((LCSMetadata)_lcsMetadata.clone());
	}

	/**
	* Returns the git tag of this l c s metadata.
	*
	* @return the git tag of this l c s metadata
	*/
	@Override
	public java.lang.String getGitTag() {
		return _lcsMetadata.getGitTag();
	}

	/**
	* Returns the portal edition of this l c s metadata.
	*
	* @return the portal edition of this l c s metadata
	*/
	@Override
	public java.lang.String getPortalEdition() {
		return _lcsMetadata.getPortalEdition();
	}

	@Override
	public java.lang.String toString() {
		return _lcsMetadata.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _lcsMetadata.toXmlString();
	}

	/**
	* Returns the availability index of this l c s metadata.
	*
	* @return the availability index of this l c s metadata
	*/
	@Override
	public long getAvailabilityIndex() {
		return _lcsMetadata.getAvailabilityIndex();
	}

	/**
	* Returns the lcs metadata ID of this l c s metadata.
	*
	* @return the lcs metadata ID of this l c s metadata
	*/
	@Override
	public long getLcsMetadataId() {
		return _lcsMetadata.getLcsMetadataId();
	}

	/**
	* Returns the primary key of this l c s metadata.
	*
	* @return the primary key of this l c s metadata
	*/
	@Override
	public long getPrimaryKey() {
		return _lcsMetadata.getPrimaryKey();
	}

	@Override
	public void persist() {
		_lcsMetadata.persist();
	}

	/**
	* Sets the availability index of this l c s metadata.
	*
	* @param availabilityIndex the availability index of this l c s metadata
	*/
	@Override
	public void setAvailabilityIndex(long availabilityIndex) {
		_lcsMetadata.setAvailabilityIndex(availabilityIndex);
	}

	/**
	* Sets the build number of this l c s metadata.
	*
	* @param buildNumber the build number of this l c s metadata
	*/
	@Override
	public void setBuildNumber(int buildNumber) {
		_lcsMetadata.setBuildNumber(buildNumber);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_lcsMetadata.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_lcsMetadata.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_lcsMetadata.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_lcsMetadata.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the git tag of this l c s metadata.
	*
	* @param gitTag the git tag of this l c s metadata
	*/
	@Override
	public void setGitTag(java.lang.String gitTag) {
		_lcsMetadata.setGitTag(gitTag);
	}

	/**
	* Sets the lcs metadata ID of this l c s metadata.
	*
	* @param lcsMetadataId the lcs metadata ID of this l c s metadata
	*/
	@Override
	public void setLcsMetadataId(long lcsMetadataId) {
		_lcsMetadata.setLcsMetadataId(lcsMetadataId);
	}

	@Override
	public void setNew(boolean n) {
		_lcsMetadata.setNew(n);
	}

	/**
	* Sets the portal edition of this l c s metadata.
	*
	* @param portalEdition the portal edition of this l c s metadata
	*/
	@Override
	public void setPortalEdition(java.lang.String portalEdition) {
		_lcsMetadata.setPortalEdition(portalEdition);
	}

	/**
	* Sets the primary key of this l c s metadata.
	*
	* @param primaryKey the primary key of this l c s metadata
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_lcsMetadata.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_lcsMetadata.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the supported l c s portlet of this l c s metadata.
	*
	* @param supportedLCSPortlet the supported l c s portlet of this l c s metadata
	*/
	@Override
	public void setSupportedLCSPortlet(int supportedLCSPortlet) {
		_lcsMetadata.setSupportedLCSPortlet(supportedLCSPortlet);
	}

	/**
	* Sets the supported patching tool of this l c s metadata.
	*
	* @param supportedPatchingTool the supported patching tool of this l c s metadata
	*/
	@Override
	public void setSupportedPatchingTool(int supportedPatchingTool) {
		_lcsMetadata.setSupportedPatchingTool(supportedPatchingTool);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LCSMetadataWrapper)) {
			return false;
		}

		LCSMetadataWrapper lcsMetadataWrapper = (LCSMetadataWrapper)obj;

		if (Objects.equals(_lcsMetadata, lcsMetadataWrapper._lcsMetadata)) {
			return true;
		}

		return false;
	}

	@Override
	public LCSMetadata getWrappedModel() {
		return _lcsMetadata;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _lcsMetadata.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _lcsMetadata.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_lcsMetadata.resetOriginalValues();
	}

	private final LCSMetadata _lcsMetadata;
}