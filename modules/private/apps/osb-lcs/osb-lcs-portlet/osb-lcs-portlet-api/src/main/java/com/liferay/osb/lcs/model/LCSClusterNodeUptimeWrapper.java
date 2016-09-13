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
 * This class is a wrapper for {@link LCSClusterNodeUptime}.
 * </p>
 *
 * @author Igor Beslic
 * @see LCSClusterNodeUptime
 * @generated
 */
@ProviderType
public class LCSClusterNodeUptimeWrapper implements LCSClusterNodeUptime,
	ModelWrapper<LCSClusterNodeUptime> {
	public LCSClusterNodeUptimeWrapper(
		LCSClusterNodeUptime lcsClusterNodeUptime) {
		_lcsClusterNodeUptime = lcsClusterNodeUptime;
	}

	@Override
	public Class<?> getModelClass() {
		return LCSClusterNodeUptime.class;
	}

	@Override
	public String getModelClassName() {
		return LCSClusterNodeUptime.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("lcsClusterNodeUptimeId", getLcsClusterNodeUptimeId());
		attributes.put("lcsClusterNodeId", getLcsClusterNodeId());
		attributes.put("startTime", getStartTime());
		attributes.put("endTime", getEndTime());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long lcsClusterNodeUptimeId = (Long)attributes.get(
				"lcsClusterNodeUptimeId");

		if (lcsClusterNodeUptimeId != null) {
			setLcsClusterNodeUptimeId(lcsClusterNodeUptimeId);
		}

		Long lcsClusterNodeId = (Long)attributes.get("lcsClusterNodeId");

		if (lcsClusterNodeId != null) {
			setLcsClusterNodeId(lcsClusterNodeId);
		}

		Long startTime = (Long)attributes.get("startTime");

		if (startTime != null) {
			setStartTime(startTime);
		}

		Long endTime = (Long)attributes.get("endTime");

		if (endTime != null) {
			setEndTime(endTime);
		}
	}

	@Override
	public LCSClusterNodeUptime toEscapedModel() {
		return new LCSClusterNodeUptimeWrapper(_lcsClusterNodeUptime.toEscapedModel());
	}

	@Override
	public LCSClusterNodeUptime toUnescapedModel() {
		return new LCSClusterNodeUptimeWrapper(_lcsClusterNodeUptime.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _lcsClusterNodeUptime.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _lcsClusterNodeUptime.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _lcsClusterNodeUptime.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _lcsClusterNodeUptime.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<LCSClusterNodeUptime> toCacheModel() {
		return _lcsClusterNodeUptime.toCacheModel();
	}

	@Override
	public int compareTo(LCSClusterNodeUptime lcsClusterNodeUptime) {
		return _lcsClusterNodeUptime.compareTo(lcsClusterNodeUptime);
	}

	@Override
	public int hashCode() {
		return _lcsClusterNodeUptime.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _lcsClusterNodeUptime.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new LCSClusterNodeUptimeWrapper((LCSClusterNodeUptime)_lcsClusterNodeUptime.clone());
	}

	@Override
	public java.lang.String toString() {
		return _lcsClusterNodeUptime.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _lcsClusterNodeUptime.toXmlString();
	}

	/**
	* Returns the end time of this l c s cluster node uptime.
	*
	* @return the end time of this l c s cluster node uptime
	*/
	@Override
	public long getEndTime() {
		return _lcsClusterNodeUptime.getEndTime();
	}

	/**
	* Returns the lcs cluster node ID of this l c s cluster node uptime.
	*
	* @return the lcs cluster node ID of this l c s cluster node uptime
	*/
	@Override
	public long getLcsClusterNodeId() {
		return _lcsClusterNodeUptime.getLcsClusterNodeId();
	}

	/**
	* Returns the lcs cluster node uptime ID of this l c s cluster node uptime.
	*
	* @return the lcs cluster node uptime ID of this l c s cluster node uptime
	*/
	@Override
	public long getLcsClusterNodeUptimeId() {
		return _lcsClusterNodeUptime.getLcsClusterNodeUptimeId();
	}

	/**
	* Returns the primary key of this l c s cluster node uptime.
	*
	* @return the primary key of this l c s cluster node uptime
	*/
	@Override
	public long getPrimaryKey() {
		return _lcsClusterNodeUptime.getPrimaryKey();
	}

	/**
	* Returns the start time of this l c s cluster node uptime.
	*
	* @return the start time of this l c s cluster node uptime
	*/
	@Override
	public long getStartTime() {
		return _lcsClusterNodeUptime.getStartTime();
	}

	@Override
	public void persist() {
		_lcsClusterNodeUptime.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_lcsClusterNodeUptime.setCachedModel(cachedModel);
	}

	/**
	* Sets the end time of this l c s cluster node uptime.
	*
	* @param endTime the end time of this l c s cluster node uptime
	*/
	@Override
	public void setEndTime(long endTime) {
		_lcsClusterNodeUptime.setEndTime(endTime);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_lcsClusterNodeUptime.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_lcsClusterNodeUptime.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_lcsClusterNodeUptime.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the lcs cluster node ID of this l c s cluster node uptime.
	*
	* @param lcsClusterNodeId the lcs cluster node ID of this l c s cluster node uptime
	*/
	@Override
	public void setLcsClusterNodeId(long lcsClusterNodeId) {
		_lcsClusterNodeUptime.setLcsClusterNodeId(lcsClusterNodeId);
	}

	/**
	* Sets the lcs cluster node uptime ID of this l c s cluster node uptime.
	*
	* @param lcsClusterNodeUptimeId the lcs cluster node uptime ID of this l c s cluster node uptime
	*/
	@Override
	public void setLcsClusterNodeUptimeId(long lcsClusterNodeUptimeId) {
		_lcsClusterNodeUptime.setLcsClusterNodeUptimeId(lcsClusterNodeUptimeId);
	}

	@Override
	public void setNew(boolean n) {
		_lcsClusterNodeUptime.setNew(n);
	}

	/**
	* Sets the primary key of this l c s cluster node uptime.
	*
	* @param primaryKey the primary key of this l c s cluster node uptime
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_lcsClusterNodeUptime.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_lcsClusterNodeUptime.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the start time of this l c s cluster node uptime.
	*
	* @param startTime the start time of this l c s cluster node uptime
	*/
	@Override
	public void setStartTime(long startTime) {
		_lcsClusterNodeUptime.setStartTime(startTime);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LCSClusterNodeUptimeWrapper)) {
			return false;
		}

		LCSClusterNodeUptimeWrapper lcsClusterNodeUptimeWrapper = (LCSClusterNodeUptimeWrapper)obj;

		if (Objects.equals(_lcsClusterNodeUptime,
					lcsClusterNodeUptimeWrapper._lcsClusterNodeUptime)) {
			return true;
		}

		return false;
	}

	@Override
	public LCSClusterNodeUptime getWrappedModel() {
		return _lcsClusterNodeUptime;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _lcsClusterNodeUptime.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _lcsClusterNodeUptime.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_lcsClusterNodeUptime.resetOriginalValues();
	}

	private final LCSClusterNodeUptime _lcsClusterNodeUptime;
}