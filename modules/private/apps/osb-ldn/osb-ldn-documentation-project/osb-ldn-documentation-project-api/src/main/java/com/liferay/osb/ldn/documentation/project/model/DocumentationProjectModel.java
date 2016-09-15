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

package com.liferay.osb.ldn.documentation.project.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedAuditedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the DocumentationProject service. Represents a row in the &quot;OSB_LDN_DocumentationProject&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.osb.ldn.documentation.project.model.impl.DocumentationProjectModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.osb.ldn.documentation.project.model.impl.DocumentationProjectImpl}.
 * </p>
 *
 * @author Ryan Park
 * @see DocumentationProject
 * @see com.liferay.osb.ldn.documentation.project.model.impl.DocumentationProjectImpl
 * @see com.liferay.osb.ldn.documentation.project.model.impl.DocumentationProjectModelImpl
 * @generated
 */
@ProviderType
public interface DocumentationProjectModel extends BaseModel<DocumentationProject>,
	ShardedModel, StagedAuditedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a documentation project model instance should use the {@link DocumentationProject} interface instead.
	 */

	/**
	 * Returns the primary key of this documentation project.
	 *
	 * @return the primary key of this documentation project
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this documentation project.
	 *
	 * @param primaryKey the primary key of this documentation project
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this documentation project.
	 *
	 * @return the uuid of this documentation project
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this documentation project.
	 *
	 * @param uuid the uuid of this documentation project
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the documentation project ID of this documentation project.
	 *
	 * @return the documentation project ID of this documentation project
	 */
	public long getDocumentationProjectId();

	/**
	 * Sets the documentation project ID of this documentation project.
	 *
	 * @param documentationProjectId the documentation project ID of this documentation project
	 */
	public void setDocumentationProjectId(long documentationProjectId);

	/**
	 * Returns the company ID of this documentation project.
	 *
	 * @return the company ID of this documentation project
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this documentation project.
	 *
	 * @param companyId the company ID of this documentation project
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this documentation project.
	 *
	 * @return the user ID of this documentation project
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this documentation project.
	 *
	 * @param userId the user ID of this documentation project
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this documentation project.
	 *
	 * @return the user uuid of this documentation project
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this documentation project.
	 *
	 * @param userUuid the user uuid of this documentation project
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this documentation project.
	 *
	 * @return the user name of this documentation project
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this documentation project.
	 *
	 * @param userName the user name of this documentation project
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this documentation project.
	 *
	 * @return the create date of this documentation project
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this documentation project.
	 *
	 * @param createDate the create date of this documentation project
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this documentation project.
	 *
	 * @return the modified date of this documentation project
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this documentation project.
	 *
	 * @param modifiedDate the modified date of this documentation project
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the name of this documentation project.
	 *
	 * @return the name of this documentation project
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this documentation project.
	 *
	 * @param name the name of this documentation project
	 */
	public void setName(String name);

	/**
	 * Returns the description of this documentation project.
	 *
	 * @return the description of this documentation project
	 */
	@AutoEscape
	public String getDescription();

	/**
	 * Sets the description of this documentation project.
	 *
	 * @param description the description of this documentation project
	 */
	public void setDescription(String description);

	/**
	 * Returns the icon file name of this documentation project.
	 *
	 * @return the icon file name of this documentation project
	 */
	@AutoEscape
	public String getIconFileName();

	/**
	 * Sets the icon file name of this documentation project.
	 *
	 * @param iconFileName the icon file name of this documentation project
	 */
	public void setIconFileName(String iconFileName);

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
	public int compareTo(DocumentationProject documentationProject);

	@Override
	public int hashCode();

	@Override
	public CacheModel<DocumentationProject> toCacheModel();

	@Override
	public DocumentationProject toEscapedModel();

	@Override
	public DocumentationProject toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}