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

/**
 * The base model interface for the LCSClusterNode service. Represents a row in the &quot;OSBLCS_LCSClusterNode&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.osb.lcs.model.impl.LCSClusterNodeModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.osb.lcs.model.impl.LCSClusterNodeImpl}.
 * </p>
 *
 * @author Igor Beslic
 * @see LCSClusterNode
 * @see com.liferay.osb.lcs.model.impl.LCSClusterNodeImpl
 * @see com.liferay.osb.lcs.model.impl.LCSClusterNodeModelImpl
 * @generated
 */
@ProviderType
public interface LCSClusterNodeModel extends BaseModel<LCSClusterNode> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a l c s cluster node model instance should use the {@link LCSClusterNode} interface instead.
	 */

	/**
	 * Returns the primary key of this l c s cluster node.
	 *
	 * @return the primary key of this l c s cluster node
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this l c s cluster node.
	 *
	 * @param primaryKey the primary key of this l c s cluster node
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the lcs cluster node ID of this l c s cluster node.
	 *
	 * @return the lcs cluster node ID of this l c s cluster node
	 */
	public long getLcsClusterNodeId();

	/**
	 * Sets the lcs cluster node ID of this l c s cluster node.
	 *
	 * @param lcsClusterNodeId the lcs cluster node ID of this l c s cluster node
	 */
	public void setLcsClusterNodeId(long lcsClusterNodeId);

	/**
	 * Returns the lcs cluster entry ID of this l c s cluster node.
	 *
	 * @return the lcs cluster entry ID of this l c s cluster node
	 */
	public long getLcsClusterEntryId();

	/**
	 * Sets the lcs cluster entry ID of this l c s cluster node.
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID of this l c s cluster node
	 */
	public void setLcsClusterEntryId(long lcsClusterEntryId);

	/**
	 * Returns the installation ID of this l c s cluster node.
	 *
	 * @return the installation ID of this l c s cluster node
	 */
	public long getInstallationId();

	/**
	 * Sets the installation ID of this l c s cluster node.
	 *
	 * @param installationId the installation ID of this l c s cluster node
	 */
	public void setInstallationId(long installationId);

	/**
	 * Returns the name of this l c s cluster node.
	 *
	 * @return the name of this l c s cluster node
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this l c s cluster node.
	 *
	 * @param name the name of this l c s cluster node
	 */
	public void setName(String name);

	/**
	 * Returns the description of this l c s cluster node.
	 *
	 * @return the description of this l c s cluster node
	 */
	@AutoEscape
	public String getDescription();

	/**
	 * Sets the description of this l c s cluster node.
	 *
	 * @param description the description of this l c s cluster node
	 */
	public void setDescription(String description);

	/**
	 * Returns the build number of this l c s cluster node.
	 *
	 * @return the build number of this l c s cluster node
	 */
	public int getBuildNumber();

	/**
	 * Sets the build number of this l c s cluster node.
	 *
	 * @param buildNumber the build number of this l c s cluster node
	 */
	public void setBuildNumber(int buildNumber);

	/**
	 * Returns the key of this l c s cluster node.
	 *
	 * @return the key of this l c s cluster node
	 */
	@AutoEscape
	public String getKey();

	/**
	 * Sets the key of this l c s cluster node.
	 *
	 * @param key the key of this l c s cluster node
	 */
	public void setKey(String key);

	/**
	 * Returns the location of this l c s cluster node.
	 *
	 * @return the location of this l c s cluster node
	 */
	@AutoEscape
	public String getLocation();

	/**
	 * Sets the location of this l c s cluster node.
	 *
	 * @param location the location of this l c s cluster node
	 */
	public void setLocation(String location);

	/**
	 * Returns the processor cores total of this l c s cluster node.
	 *
	 * @return the processor cores total of this l c s cluster node
	 */
	public int getProcessorCoresTotal();

	/**
	 * Sets the processor cores total of this l c s cluster node.
	 *
	 * @param processorCoresTotal the processor cores total of this l c s cluster node
	 */
	public void setProcessorCoresTotal(int processorCoresTotal);

	/**
	 * Returns the archived of this l c s cluster node.
	 *
	 * @return the archived of this l c s cluster node
	 */
	public boolean getArchived();

	/**
	 * Returns <code>true</code> if this l c s cluster node is archived.
	 *
	 * @return <code>true</code> if this l c s cluster node is archived; <code>false</code> otherwise
	 */
	public boolean isArchived();

	/**
	 * Sets whether this l c s cluster node is archived.
	 *
	 * @param archived the archived of this l c s cluster node
	 */
	public void setArchived(boolean archived);

	/**
	 * Returns the status of this l c s cluster node.
	 *
	 * @return the status of this l c s cluster node
	 */
	public int getStatus();

	/**
	 * Sets the status of this l c s cluster node.
	 *
	 * @param status the status of this l c s cluster node
	 */
	public void setStatus(int status);

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
	public int compareTo(LCSClusterNode lcsClusterNode);

	@Override
	public int hashCode();

	@Override
	public CacheModel<LCSClusterNode> toCacheModel();

	@Override
	public LCSClusterNode toEscapedModel();

	@Override
	public LCSClusterNode toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}