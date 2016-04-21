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
 * The base model interface for the WorkflowInstanceLink service. Represents a row in the &quot;WorkflowInstanceLink&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portal.model.impl.WorkflowInstanceLinkModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portal.model.impl.WorkflowInstanceLinkImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see WorkflowInstanceLink
 * @see com.liferay.portal.model.impl.WorkflowInstanceLinkImpl
 * @see com.liferay.portal.model.impl.WorkflowInstanceLinkModelImpl
 * @generated
 */
@ProviderType
public interface WorkflowInstanceLinkModel extends AttachedModel,
	BaseModel<WorkflowInstanceLink>, GroupedModel, MVCCModel, ShardedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a workflow instance link model instance should use the {@link WorkflowInstanceLink} interface instead.
	 */

	/**
	 * Returns the primary key of this workflow instance link.
	 *
	 * @return the primary key of this workflow instance link
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this workflow instance link.
	 *
	 * @param primaryKey the primary key of this workflow instance link
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this workflow instance link.
	 *
	 * @return the mvcc version of this workflow instance link
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this workflow instance link.
	 *
	 * @param mvccVersion the mvcc version of this workflow instance link
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the workflow instance link ID of this workflow instance link.
	 *
	 * @return the workflow instance link ID of this workflow instance link
	 */
	public long getWorkflowInstanceLinkId();

	/**
	 * Sets the workflow instance link ID of this workflow instance link.
	 *
	 * @param workflowInstanceLinkId the workflow instance link ID of this workflow instance link
	 */
	public void setWorkflowInstanceLinkId(long workflowInstanceLinkId);

	/**
	 * Returns the group ID of this workflow instance link.
	 *
	 * @return the group ID of this workflow instance link
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this workflow instance link.
	 *
	 * @param groupId the group ID of this workflow instance link
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this workflow instance link.
	 *
	 * @return the company ID of this workflow instance link
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this workflow instance link.
	 *
	 * @param companyId the company ID of this workflow instance link
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this workflow instance link.
	 *
	 * @return the user ID of this workflow instance link
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this workflow instance link.
	 *
	 * @param userId the user ID of this workflow instance link
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this workflow instance link.
	 *
	 * @return the user uuid of this workflow instance link
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this workflow instance link.
	 *
	 * @param userUuid the user uuid of this workflow instance link
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this workflow instance link.
	 *
	 * @return the user name of this workflow instance link
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this workflow instance link.
	 *
	 * @param userName the user name of this workflow instance link
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this workflow instance link.
	 *
	 * @return the create date of this workflow instance link
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this workflow instance link.
	 *
	 * @param createDate the create date of this workflow instance link
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this workflow instance link.
	 *
	 * @return the modified date of this workflow instance link
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this workflow instance link.
	 *
	 * @param modifiedDate the modified date of this workflow instance link
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the fully qualified class name of this workflow instance link.
	 *
	 * @return the fully qualified class name of this workflow instance link
	 */
	@Override
	public String getClassName();

	public void setClassName(String className);

	/**
	 * Returns the class name ID of this workflow instance link.
	 *
	 * @return the class name ID of this workflow instance link
	 */
	@Override
	public long getClassNameId();

	/**
	 * Sets the class name ID of this workflow instance link.
	 *
	 * @param classNameId the class name ID of this workflow instance link
	 */
	@Override
	public void setClassNameId(long classNameId);

	/**
	 * Returns the class p k of this workflow instance link.
	 *
	 * @return the class p k of this workflow instance link
	 */
	@Override
	public long getClassPK();

	/**
	 * Sets the class p k of this workflow instance link.
	 *
	 * @param classPK the class p k of this workflow instance link
	 */
	@Override
	public void setClassPK(long classPK);

	/**
	 * Returns the workflow instance ID of this workflow instance link.
	 *
	 * @return the workflow instance ID of this workflow instance link
	 */
	public long getWorkflowInstanceId();

	/**
	 * Sets the workflow instance ID of this workflow instance link.
	 *
	 * @param workflowInstanceId the workflow instance ID of this workflow instance link
	 */
	public void setWorkflowInstanceId(long workflowInstanceId);

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
	public int compareTo(WorkflowInstanceLink workflowInstanceLink);

	@Override
	public int hashCode();

	@Override
	public CacheModel<WorkflowInstanceLink> toCacheModel();

	@Override
	public WorkflowInstanceLink toEscapedModel();

	@Override
	public WorkflowInstanceLink toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}