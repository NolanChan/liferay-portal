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
 * This class is a wrapper for {@link LCSClusterNode}.
 * </p>
 *
 * @author Igor Beslic
 * @see LCSClusterNode
 * @generated
 */
@ProviderType
public class LCSClusterNodeWrapper implements LCSClusterNode,
	ModelWrapper<LCSClusterNode> {
	public LCSClusterNodeWrapper(LCSClusterNode lcsClusterNode) {
		_lcsClusterNode = lcsClusterNode;
	}

	@Override
	public Class<?> getModelClass() {
		return LCSClusterNode.class;
	}

	@Override
	public String getModelClassName() {
		return LCSClusterNode.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("lcsClusterNodeId", getLcsClusterNodeId());
		attributes.put("lcsClusterEntryId", getLcsClusterEntryId());
		attributes.put("installationId", getInstallationId());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("buildNumber", getBuildNumber());
		attributes.put("key", getKey());
		attributes.put("location", getLocation());
		attributes.put("processorCoresTotal", getProcessorCoresTotal());
		attributes.put("archived", getArchived());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long lcsClusterNodeId = (Long)attributes.get("lcsClusterNodeId");

		if (lcsClusterNodeId != null) {
			setLcsClusterNodeId(lcsClusterNodeId);
		}

		Long lcsClusterEntryId = (Long)attributes.get("lcsClusterEntryId");

		if (lcsClusterEntryId != null) {
			setLcsClusterEntryId(lcsClusterEntryId);
		}

		Long installationId = (Long)attributes.get("installationId");

		if (installationId != null) {
			setInstallationId(installationId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Integer buildNumber = (Integer)attributes.get("buildNumber");

		if (buildNumber != null) {
			setBuildNumber(buildNumber);
		}

		String key = (String)attributes.get("key");

		if (key != null) {
			setKey(key);
		}

		String location = (String)attributes.get("location");

		if (location != null) {
			setLocation(location);
		}

		Integer processorCoresTotal = (Integer)attributes.get(
				"processorCoresTotal");

		if (processorCoresTotal != null) {
			setProcessorCoresTotal(processorCoresTotal);
		}

		Boolean archived = (Boolean)attributes.get("archived");

		if (archived != null) {
			setArchived(archived);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	@Override
	public LCSClusterNode toEscapedModel() {
		return new LCSClusterNodeWrapper(_lcsClusterNode.toEscapedModel());
	}

	@Override
	public LCSClusterNode toUnescapedModel() {
		return new LCSClusterNodeWrapper(_lcsClusterNode.toUnescapedModel());
	}

	/**
	* Returns the archived of this l c s cluster node.
	*
	* @return the archived of this l c s cluster node
	*/
	@Override
	public boolean getArchived() {
		return _lcsClusterNode.getArchived();
	}

	/**
	* Returns <code>true</code> if this l c s cluster node is archived.
	*
	* @return <code>true</code> if this l c s cluster node is archived; <code>false</code> otherwise
	*/
	@Override
	public boolean isArchived() {
		return _lcsClusterNode.isArchived();
	}

	@Override
	public boolean isCachedModel() {
		return _lcsClusterNode.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _lcsClusterNode.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _lcsClusterNode.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _lcsClusterNode.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<LCSClusterNode> toCacheModel() {
		return _lcsClusterNode.toCacheModel();
	}

	@Override
	public int compareTo(LCSClusterNode lcsClusterNode) {
		return _lcsClusterNode.compareTo(lcsClusterNode);
	}

	/**
	* Returns the build number of this l c s cluster node.
	*
	* @return the build number of this l c s cluster node
	*/
	@Override
	public int getBuildNumber() {
		return _lcsClusterNode.getBuildNumber();
	}

	/**
	* Returns the processor cores total of this l c s cluster node.
	*
	* @return the processor cores total of this l c s cluster node
	*/
	@Override
	public int getProcessorCoresTotal() {
		return _lcsClusterNode.getProcessorCoresTotal();
	}

	/**
	* Returns the status of this l c s cluster node.
	*
	* @return the status of this l c s cluster node
	*/
	@Override
	public int getStatus() {
		return _lcsClusterNode.getStatus();
	}

	@Override
	public int hashCode() {
		return _lcsClusterNode.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _lcsClusterNode.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new LCSClusterNodeWrapper((LCSClusterNode)_lcsClusterNode.clone());
	}

	/**
	* Returns the description of this l c s cluster node.
	*
	* @return the description of this l c s cluster node
	*/
	@Override
	public java.lang.String getDescription() {
		return _lcsClusterNode.getDescription();
	}

	/**
	* Returns the key of this l c s cluster node.
	*
	* @return the key of this l c s cluster node
	*/
	@Override
	public java.lang.String getKey() {
		return _lcsClusterNode.getKey();
	}

	/**
	* Returns the location of this l c s cluster node.
	*
	* @return the location of this l c s cluster node
	*/
	@Override
	public java.lang.String getLocation() {
		return _lcsClusterNode.getLocation();
	}

	/**
	* Returns the name of this l c s cluster node.
	*
	* @return the name of this l c s cluster node
	*/
	@Override
	public java.lang.String getName() {
		return _lcsClusterNode.getName();
	}

	@Override
	public java.lang.String toString() {
		return _lcsClusterNode.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _lcsClusterNode.toXmlString();
	}

	/**
	* Returns the installation ID of this l c s cluster node.
	*
	* @return the installation ID of this l c s cluster node
	*/
	@Override
	public long getInstallationId() {
		return _lcsClusterNode.getInstallationId();
	}

	/**
	* Returns the lcs cluster entry ID of this l c s cluster node.
	*
	* @return the lcs cluster entry ID of this l c s cluster node
	*/
	@Override
	public long getLcsClusterEntryId() {
		return _lcsClusterNode.getLcsClusterEntryId();
	}

	/**
	* Returns the lcs cluster node ID of this l c s cluster node.
	*
	* @return the lcs cluster node ID of this l c s cluster node
	*/
	@Override
	public long getLcsClusterNodeId() {
		return _lcsClusterNode.getLcsClusterNodeId();
	}

	/**
	* Returns the primary key of this l c s cluster node.
	*
	* @return the primary key of this l c s cluster node
	*/
	@Override
	public long getPrimaryKey() {
		return _lcsClusterNode.getPrimaryKey();
	}

	@Override
	public void persist() {
		_lcsClusterNode.persist();
	}

	/**
	* Sets whether this l c s cluster node is archived.
	*
	* @param archived the archived of this l c s cluster node
	*/
	@Override
	public void setArchived(boolean archived) {
		_lcsClusterNode.setArchived(archived);
	}

	/**
	* Sets the build number of this l c s cluster node.
	*
	* @param buildNumber the build number of this l c s cluster node
	*/
	@Override
	public void setBuildNumber(int buildNumber) {
		_lcsClusterNode.setBuildNumber(buildNumber);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_lcsClusterNode.setCachedModel(cachedModel);
	}

	/**
	* Sets the description of this l c s cluster node.
	*
	* @param description the description of this l c s cluster node
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_lcsClusterNode.setDescription(description);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_lcsClusterNode.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_lcsClusterNode.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_lcsClusterNode.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the installation ID of this l c s cluster node.
	*
	* @param installationId the installation ID of this l c s cluster node
	*/
	@Override
	public void setInstallationId(long installationId) {
		_lcsClusterNode.setInstallationId(installationId);
	}

	/**
	* Sets the key of this l c s cluster node.
	*
	* @param key the key of this l c s cluster node
	*/
	@Override
	public void setKey(java.lang.String key) {
		_lcsClusterNode.setKey(key);
	}

	/**
	* Sets the lcs cluster entry ID of this l c s cluster node.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID of this l c s cluster node
	*/
	@Override
	public void setLcsClusterEntryId(long lcsClusterEntryId) {
		_lcsClusterNode.setLcsClusterEntryId(lcsClusterEntryId);
	}

	/**
	* Sets the lcs cluster node ID of this l c s cluster node.
	*
	* @param lcsClusterNodeId the lcs cluster node ID of this l c s cluster node
	*/
	@Override
	public void setLcsClusterNodeId(long lcsClusterNodeId) {
		_lcsClusterNode.setLcsClusterNodeId(lcsClusterNodeId);
	}

	/**
	* Sets the location of this l c s cluster node.
	*
	* @param location the location of this l c s cluster node
	*/
	@Override
	public void setLocation(java.lang.String location) {
		_lcsClusterNode.setLocation(location);
	}

	/**
	* Sets the name of this l c s cluster node.
	*
	* @param name the name of this l c s cluster node
	*/
	@Override
	public void setName(java.lang.String name) {
		_lcsClusterNode.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_lcsClusterNode.setNew(n);
	}

	/**
	* Sets the primary key of this l c s cluster node.
	*
	* @param primaryKey the primary key of this l c s cluster node
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_lcsClusterNode.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_lcsClusterNode.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the processor cores total of this l c s cluster node.
	*
	* @param processorCoresTotal the processor cores total of this l c s cluster node
	*/
	@Override
	public void setProcessorCoresTotal(int processorCoresTotal) {
		_lcsClusterNode.setProcessorCoresTotal(processorCoresTotal);
	}

	/**
	* Sets the status of this l c s cluster node.
	*
	* @param status the status of this l c s cluster node
	*/
	@Override
	public void setStatus(int status) {
		_lcsClusterNode.setStatus(status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LCSClusterNodeWrapper)) {
			return false;
		}

		LCSClusterNodeWrapper lcsClusterNodeWrapper = (LCSClusterNodeWrapper)obj;

		if (Objects.equals(_lcsClusterNode,
					lcsClusterNodeWrapper._lcsClusterNode)) {
			return true;
		}

		return false;
	}

	@Override
	public LCSClusterNode getWrappedModel() {
		return _lcsClusterNode;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _lcsClusterNode.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _lcsClusterNode.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_lcsClusterNode.resetOriginalValues();
	}

	private final LCSClusterNode _lcsClusterNode;
}