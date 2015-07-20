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
 * The base model interface for the KaleoAction service. Represents a row in the &quot;KaleoAction&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoActionModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoActionImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoAction
 * @see com.liferay.portal.workflow.kaleo.model.impl.KaleoActionImpl
 * @see com.liferay.portal.workflow.kaleo.model.impl.KaleoActionModelImpl
 * @generated
 */
@ProviderType
public interface KaleoActionModel extends BaseModel<KaleoAction>, GroupedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a kaleo action model instance should use the {@link KaleoAction} interface instead.
	 */

	/**
	 * Returns the primary key of this kaleo action.
	 *
	 * @return the primary key of this kaleo action
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this kaleo action.
	 *
	 * @param primaryKey the primary key of this kaleo action
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the kaleo action ID of this kaleo action.
	 *
	 * @return the kaleo action ID of this kaleo action
	 */
	public long getKaleoActionId();

	/**
	 * Sets the kaleo action ID of this kaleo action.
	 *
	 * @param kaleoActionId the kaleo action ID of this kaleo action
	 */
	public void setKaleoActionId(long kaleoActionId);

	/**
	 * Returns the group ID of this kaleo action.
	 *
	 * @return the group ID of this kaleo action
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this kaleo action.
	 *
	 * @param groupId the group ID of this kaleo action
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this kaleo action.
	 *
	 * @return the company ID of this kaleo action
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this kaleo action.
	 *
	 * @param companyId the company ID of this kaleo action
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this kaleo action.
	 *
	 * @return the user ID of this kaleo action
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this kaleo action.
	 *
	 * @param userId the user ID of this kaleo action
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this kaleo action.
	 *
	 * @return the user uuid of this kaleo action
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this kaleo action.
	 *
	 * @param userUuid the user uuid of this kaleo action
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this kaleo action.
	 *
	 * @return the user name of this kaleo action
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this kaleo action.
	 *
	 * @param userName the user name of this kaleo action
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this kaleo action.
	 *
	 * @return the create date of this kaleo action
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this kaleo action.
	 *
	 * @param createDate the create date of this kaleo action
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this kaleo action.
	 *
	 * @return the modified date of this kaleo action
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this kaleo action.
	 *
	 * @param modifiedDate the modified date of this kaleo action
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the kaleo class name of this kaleo action.
	 *
	 * @return the kaleo class name of this kaleo action
	 */
	@AutoEscape
	public String getKaleoClassName();

	/**
	 * Sets the kaleo class name of this kaleo action.
	 *
	 * @param kaleoClassName the kaleo class name of this kaleo action
	 */
	public void setKaleoClassName(String kaleoClassName);

	/**
	 * Returns the kaleo class p k of this kaleo action.
	 *
	 * @return the kaleo class p k of this kaleo action
	 */
	public long getKaleoClassPK();

	/**
	 * Sets the kaleo class p k of this kaleo action.
	 *
	 * @param kaleoClassPK the kaleo class p k of this kaleo action
	 */
	public void setKaleoClassPK(long kaleoClassPK);

	/**
	 * Returns the kaleo definition ID of this kaleo action.
	 *
	 * @return the kaleo definition ID of this kaleo action
	 */
	public long getKaleoDefinitionId();

	/**
	 * Sets the kaleo definition ID of this kaleo action.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID of this kaleo action
	 */
	public void setKaleoDefinitionId(long kaleoDefinitionId);

	/**
	 * Returns the kaleo node name of this kaleo action.
	 *
	 * @return the kaleo node name of this kaleo action
	 */
	@AutoEscape
	public String getKaleoNodeName();

	/**
	 * Sets the kaleo node name of this kaleo action.
	 *
	 * @param kaleoNodeName the kaleo node name of this kaleo action
	 */
	public void setKaleoNodeName(String kaleoNodeName);

	/**
	 * Returns the name of this kaleo action.
	 *
	 * @return the name of this kaleo action
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this kaleo action.
	 *
	 * @param name the name of this kaleo action
	 */
	public void setName(String name);

	/**
	 * Returns the description of this kaleo action.
	 *
	 * @return the description of this kaleo action
	 */
	@AutoEscape
	public String getDescription();

	/**
	 * Sets the description of this kaleo action.
	 *
	 * @param description the description of this kaleo action
	 */
	public void setDescription(String description);

	/**
	 * Returns the execution type of this kaleo action.
	 *
	 * @return the execution type of this kaleo action
	 */
	@AutoEscape
	public String getExecutionType();

	/**
	 * Sets the execution type of this kaleo action.
	 *
	 * @param executionType the execution type of this kaleo action
	 */
	public void setExecutionType(String executionType);

	/**
	 * Returns the script of this kaleo action.
	 *
	 * @return the script of this kaleo action
	 */
	@AutoEscape
	public String getScript();

	/**
	 * Sets the script of this kaleo action.
	 *
	 * @param script the script of this kaleo action
	 */
	public void setScript(String script);

	/**
	 * Returns the script language of this kaleo action.
	 *
	 * @return the script language of this kaleo action
	 */
	@AutoEscape
	public String getScriptLanguage();

	/**
	 * Sets the script language of this kaleo action.
	 *
	 * @param scriptLanguage the script language of this kaleo action
	 */
	public void setScriptLanguage(String scriptLanguage);

	/**
	 * Returns the script required contexts of this kaleo action.
	 *
	 * @return the script required contexts of this kaleo action
	 */
	@AutoEscape
	public String getScriptRequiredContexts();

	/**
	 * Sets the script required contexts of this kaleo action.
	 *
	 * @param scriptRequiredContexts the script required contexts of this kaleo action
	 */
	public void setScriptRequiredContexts(String scriptRequiredContexts);

	/**
	 * Returns the priority of this kaleo action.
	 *
	 * @return the priority of this kaleo action
	 */
	public int getPriority();

	/**
	 * Sets the priority of this kaleo action.
	 *
	 * @param priority the priority of this kaleo action
	 */
	public void setPriority(int priority);

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
		com.liferay.portal.workflow.kaleo.model.KaleoAction kaleoAction);

	@Override
	public int hashCode();

	@Override
	public CacheModel<com.liferay.portal.workflow.kaleo.model.KaleoAction> toCacheModel();

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoAction toEscapedModel();

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoAction toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}