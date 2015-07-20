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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.GroupedModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the KaleoCondition service. Represents a row in the &quot;KaleoCondition&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoConditionModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoConditionImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoCondition
 * @see com.liferay.portal.workflow.kaleo.model.impl.KaleoConditionImpl
 * @see com.liferay.portal.workflow.kaleo.model.impl.KaleoConditionModelImpl
 * @generated
 */
@ProviderType
public interface KaleoConditionModel extends BaseModel<KaleoCondition>,
	GroupedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a kaleo condition model instance should use the {@link KaleoCondition} interface instead.
	 */

	/**
	 * Returns the primary key of this kaleo condition.
	 *
	 * @return the primary key of this kaleo condition
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this kaleo condition.
	 *
	 * @param primaryKey the primary key of this kaleo condition
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the kaleo condition ID of this kaleo condition.
	 *
	 * @return the kaleo condition ID of this kaleo condition
	 */
	public long getKaleoConditionId();

	/**
	 * Sets the kaleo condition ID of this kaleo condition.
	 *
	 * @param kaleoConditionId the kaleo condition ID of this kaleo condition
	 */
	public void setKaleoConditionId(long kaleoConditionId);

	/**
	 * Returns the group ID of this kaleo condition.
	 *
	 * @return the group ID of this kaleo condition
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this kaleo condition.
	 *
	 * @param groupId the group ID of this kaleo condition
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this kaleo condition.
	 *
	 * @return the company ID of this kaleo condition
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this kaleo condition.
	 *
	 * @param companyId the company ID of this kaleo condition
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this kaleo condition.
	 *
	 * @return the user ID of this kaleo condition
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this kaleo condition.
	 *
	 * @param userId the user ID of this kaleo condition
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this kaleo condition.
	 *
	 * @return the user uuid of this kaleo condition
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this kaleo condition.
	 *
	 * @param userUuid the user uuid of this kaleo condition
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this kaleo condition.
	 *
	 * @return the user name of this kaleo condition
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this kaleo condition.
	 *
	 * @param userName the user name of this kaleo condition
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this kaleo condition.
	 *
	 * @return the create date of this kaleo condition
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this kaleo condition.
	 *
	 * @param createDate the create date of this kaleo condition
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this kaleo condition.
	 *
	 * @return the modified date of this kaleo condition
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this kaleo condition.
	 *
	 * @param modifiedDate the modified date of this kaleo condition
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the kaleo definition ID of this kaleo condition.
	 *
	 * @return the kaleo definition ID of this kaleo condition
	 */
	public long getKaleoDefinitionId();

	/**
	 * Sets the kaleo definition ID of this kaleo condition.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID of this kaleo condition
	 */
	public void setKaleoDefinitionId(long kaleoDefinitionId);

	/**
	 * Returns the kaleo node ID of this kaleo condition.
	 *
	 * @return the kaleo node ID of this kaleo condition
	 */
	public long getKaleoNodeId();

	/**
	 * Sets the kaleo node ID of this kaleo condition.
	 *
	 * @param kaleoNodeId the kaleo node ID of this kaleo condition
	 */
	public void setKaleoNodeId(long kaleoNodeId);

	/**
	 * Returns the script of this kaleo condition.
	 *
	 * @return the script of this kaleo condition
	 */
	@AutoEscape
	public String getScript();

	/**
	 * Sets the script of this kaleo condition.
	 *
	 * @param script the script of this kaleo condition
	 */
	public void setScript(String script);

	/**
	 * Returns the script language of this kaleo condition.
	 *
	 * @return the script language of this kaleo condition
	 */
	@AutoEscape
	public String getScriptLanguage();

	/**
	 * Sets the script language of this kaleo condition.
	 *
	 * @param scriptLanguage the script language of this kaleo condition
	 */
	public void setScriptLanguage(String scriptLanguage);

	/**
	 * Returns the script required contexts of this kaleo condition.
	 *
	 * @return the script required contexts of this kaleo condition
	 */
	@AutoEscape
	public String getScriptRequiredContexts();

	/**
	 * Sets the script required contexts of this kaleo condition.
	 *
	 * @param scriptRequiredContexts the script required contexts of this kaleo condition
	 */
	public void setScriptRequiredContexts(String scriptRequiredContexts);

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
	public int compareTo(
		com.liferay.portal.workflow.kaleo.model.KaleoCondition kaleoCondition);

	@Override
	public int hashCode();

	@Override
	public CacheModel<com.liferay.portal.workflow.kaleo.model.KaleoCondition> toCacheModel();

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoCondition toEscapedModel();

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoCondition toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}