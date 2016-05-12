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

package com.liferay.portal.resiliency.spi.model;

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
 * This class is a wrapper for {@link SPIDefinition}.
 * </p>
 *
 * @author Michael C. Han
 * @see SPIDefinition
 * @generated
 */
@ProviderType
public class SPIDefinitionWrapper implements SPIDefinition,
	ModelWrapper<SPIDefinition> {
	public SPIDefinitionWrapper(SPIDefinition spiDefinition) {
		_spiDefinition = spiDefinition;
	}

	@Override
	public Class<?> getModelClass() {
		return SPIDefinition.class;
	}

	@Override
	public String getModelClassName() {
		return SPIDefinition.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("spiDefinitionId", getSpiDefinitionId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("connectorAddress", getConnectorAddress());
		attributes.put("connectorPort", getConnectorPort());
		attributes.put("description", getDescription());
		attributes.put("jvmArguments", getJvmArguments());
		attributes.put("portletIds", getPortletIds());
		attributes.put("servletContextNames", getServletContextNames());
		attributes.put("typeSettings", getTypeSettings());
		attributes.put("status", getStatus());
		attributes.put("statusMessage", getStatusMessage());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long spiDefinitionId = (Long)attributes.get("spiDefinitionId");

		if (spiDefinitionId != null) {
			setSpiDefinitionId(spiDefinitionId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String connectorAddress = (String)attributes.get("connectorAddress");

		if (connectorAddress != null) {
			setConnectorAddress(connectorAddress);
		}

		Integer connectorPort = (Integer)attributes.get("connectorPort");

		if (connectorPort != null) {
			setConnectorPort(connectorPort);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String jvmArguments = (String)attributes.get("jvmArguments");

		if (jvmArguments != null) {
			setJvmArguments(jvmArguments);
		}

		String portletIds = (String)attributes.get("portletIds");

		if (portletIds != null) {
			setPortletIds(portletIds);
		}

		String servletContextNames = (String)attributes.get(
				"servletContextNames");

		if (servletContextNames != null) {
			setServletContextNames(servletContextNames);
		}

		String typeSettings = (String)attributes.get("typeSettings");

		if (typeSettings != null) {
			setTypeSettings(typeSettings);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		String statusMessage = (String)attributes.get("statusMessage");

		if (statusMessage != null) {
			setStatusMessage(statusMessage);
		}
	}

	@Override
	public SPIDefinition toEscapedModel() {
		return new SPIDefinitionWrapper(_spiDefinition.toEscapedModel());
	}

	@Override
	public SPIDefinition toUnescapedModel() {
		return new SPIDefinitionWrapper(_spiDefinition.toUnescapedModel());
	}

	@Override
	public boolean isAlive() {
		return _spiDefinition.isAlive();
	}

	@Override
	public boolean isCachedModel() {
		return _spiDefinition.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _spiDefinition.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _spiDefinition.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _spiDefinition.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<SPIDefinition> toCacheModel() {
		return _spiDefinition.toCacheModel();
	}

	@Override
	public com.liferay.portal.kernel.resiliency.spi.SPI getSPI() {
		return _spiDefinition.getSPI();
	}

	@Override
	public com.liferay.portal.kernel.util.UnicodeProperties getTypeSettingsProperties() {
		return _spiDefinition.getTypeSettingsProperties();
	}

	@Override
	public int compareTo(SPIDefinition spiDefinition) {
		return _spiDefinition.compareTo(spiDefinition);
	}

	/**
	* Returns the connector port of this s p i definition.
	*
	* @return the connector port of this s p i definition
	*/
	@Override
	public int getConnectorPort() {
		return _spiDefinition.getConnectorPort();
	}

	@Override
	public int getMaxRestartAttempts() {
		return _spiDefinition.getMaxRestartAttempts();
	}

	@Override
	public int getMaxThreads() {
		return _spiDefinition.getMaxThreads();
	}

	@Override
	public int getMinThreads() {
		return _spiDefinition.getMinThreads();
	}

	@Override
	public int getRestartAttempts() {
		return _spiDefinition.getRestartAttempts();
	}

	/**
	* Returns the status of this s p i definition.
	*
	* @return the status of this s p i definition
	*/
	@Override
	public int getStatus() {
		return _spiDefinition.getStatus();
	}

	@Override
	public int hashCode() {
		return _spiDefinition.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _spiDefinition.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new SPIDefinitionWrapper((SPIDefinition)_spiDefinition.clone());
	}

	@Override
	public java.lang.String getAgentClassName() {
		return _spiDefinition.getAgentClassName();
	}

	@Override
	public java.lang.String getBaseDir() {
		return _spiDefinition.getBaseDir();
	}

	/**
	* Returns the connector address of this s p i definition.
	*
	* @return the connector address of this s p i definition
	*/
	@Override
	public java.lang.String getConnectorAddress() {
		return _spiDefinition.getConnectorAddress();
	}

	/**
	* Returns the description of this s p i definition.
	*
	* @return the description of this s p i definition
	*/
	@Override
	public java.lang.String getDescription() {
		return _spiDefinition.getDescription();
	}

	@Override
	public java.lang.String getJavaExecutable() {
		return _spiDefinition.getJavaExecutable();
	}

	/**
	* Returns the jvm arguments of this s p i definition.
	*
	* @return the jvm arguments of this s p i definition
	*/
	@Override
	public java.lang.String getJvmArguments() {
		return _spiDefinition.getJvmArguments();
	}

	/**
	* Returns the name of this s p i definition.
	*
	* @return the name of this s p i definition
	*/
	@Override
	public java.lang.String getName() {
		return _spiDefinition.getName();
	}

	@Override
	public java.lang.String getNotificationRecipients() {
		return _spiDefinition.getNotificationRecipients();
	}

	@Override
	public java.lang.String getPortalProperties() {
		return _spiDefinition.getPortalProperties();
	}

	/**
	* Returns the portlet IDs of this s p i definition.
	*
	* @return the portlet IDs of this s p i definition
	*/
	@Override
	public java.lang.String getPortletIds() {
		return _spiDefinition.getPortletIds();
	}

	/**
	* Returns the servlet context names of this s p i definition.
	*
	* @return the servlet context names of this s p i definition
	*/
	@Override
	public java.lang.String getServletContextNames() {
		return _spiDefinition.getServletContextNames();
	}

	@Override
	public java.lang.String getStatusLabel() {
		return _spiDefinition.getStatusLabel();
	}

	/**
	* Returns the status message of this s p i definition.
	*
	* @return the status message of this s p i definition
	*/
	@Override
	public java.lang.String getStatusMessage() {
		return _spiDefinition.getStatusMessage();
	}

	/**
	* Returns the type settings of this s p i definition.
	*
	* @return the type settings of this s p i definition
	*/
	@Override
	public java.lang.String getTypeSettings() {
		return _spiDefinition.getTypeSettings();
	}

	@Override
	public java.lang.String getTypeSettingsProperty(java.lang.String key) {
		return _spiDefinition.getTypeSettingsProperty(key);
	}

	@Override
	public java.lang.String getTypeSettingsProperty(java.lang.String key,
		java.lang.String defaultValue) {
		return _spiDefinition.getTypeSettingsProperty(key, defaultValue);
	}

	/**
	* Returns the user name of this s p i definition.
	*
	* @return the user name of this s p i definition
	*/
	@Override
	public java.lang.String getUserName() {
		return _spiDefinition.getUserName();
	}

	/**
	* Returns the user uuid of this s p i definition.
	*
	* @return the user uuid of this s p i definition
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _spiDefinition.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _spiDefinition.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _spiDefinition.toXmlString();
	}

	/**
	* Returns the create date of this s p i definition.
	*
	* @return the create date of this s p i definition
	*/
	@Override
	public Date getCreateDate() {
		return _spiDefinition.getCreateDate();
	}

	/**
	* Returns the modified date of this s p i definition.
	*
	* @return the modified date of this s p i definition
	*/
	@Override
	public Date getModifiedDate() {
		return _spiDefinition.getModifiedDate();
	}

	/**
	* Returns the company ID of this s p i definition.
	*
	* @return the company ID of this s p i definition
	*/
	@Override
	public long getCompanyId() {
		return _spiDefinition.getCompanyId();
	}

	@Override
	public long getPingInterval() {
		return _spiDefinition.getPingInterval();
	}

	/**
	* Returns the primary key of this s p i definition.
	*
	* @return the primary key of this s p i definition
	*/
	@Override
	public long getPrimaryKey() {
		return _spiDefinition.getPrimaryKey();
	}

	@Override
	public long getRegisterTimeout() {
		return _spiDefinition.getRegisterTimeout();
	}

	@Override
	public long getShutdownTimeout() {
		return _spiDefinition.getShutdownTimeout();
	}

	/**
	* Returns the spi definition ID of this s p i definition.
	*
	* @return the spi definition ID of this s p i definition
	*/
	@Override
	public long getSpiDefinitionId() {
		return _spiDefinition.getSpiDefinitionId();
	}

	/**
	* Returns the user ID of this s p i definition.
	*
	* @return the user ID of this s p i definition
	*/
	@Override
	public long getUserId() {
		return _spiDefinition.getUserId();
	}

	@Override
	public void deleteBaseDir() {
		_spiDefinition.deleteBaseDir();
	}

	@Override
	public void persist() {
		_spiDefinition.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_spiDefinition.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this s p i definition.
	*
	* @param companyId the company ID of this s p i definition
	*/
	@Override
	public void setCompanyId(long companyId) {
		_spiDefinition.setCompanyId(companyId);
	}

	/**
	* Sets the connector address of this s p i definition.
	*
	* @param connectorAddress the connector address of this s p i definition
	*/
	@Override
	public void setConnectorAddress(java.lang.String connectorAddress) {
		_spiDefinition.setConnectorAddress(connectorAddress);
	}

	/**
	* Sets the connector port of this s p i definition.
	*
	* @param connectorPort the connector port of this s p i definition
	*/
	@Override
	public void setConnectorPort(int connectorPort) {
		_spiDefinition.setConnectorPort(connectorPort);
	}

	/**
	* Sets the create date of this s p i definition.
	*
	* @param createDate the create date of this s p i definition
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_spiDefinition.setCreateDate(createDate);
	}

	/**
	* Sets the description of this s p i definition.
	*
	* @param description the description of this s p i definition
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_spiDefinition.setDescription(description);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_spiDefinition.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_spiDefinition.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_spiDefinition.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the jvm arguments of this s p i definition.
	*
	* @param jvmArguments the jvm arguments of this s p i definition
	*/
	@Override
	public void setJvmArguments(java.lang.String jvmArguments) {
		_spiDefinition.setJvmArguments(jvmArguments);
	}

	@Override
	public void setMaxRestartAttempts(int maxRestartAttempts) {
		_spiDefinition.setMaxRestartAttempts(maxRestartAttempts);
	}

	/**
	* Sets the modified date of this s p i definition.
	*
	* @param modifiedDate the modified date of this s p i definition
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_spiDefinition.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this s p i definition.
	*
	* @param name the name of this s p i definition
	*/
	@Override
	public void setName(java.lang.String name) {
		_spiDefinition.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_spiDefinition.setNew(n);
	}

	@Override
	public void setNotificationRecipients(
		java.lang.String notificationRecipients) {
		_spiDefinition.setNotificationRecipients(notificationRecipients);
	}

	@Override
	public void setPortalProperties(java.lang.String portalProperties) {
		_spiDefinition.setPortalProperties(portalProperties);
	}

	/**
	* Sets the portlet IDs of this s p i definition.
	*
	* @param portletIds the portlet IDs of this s p i definition
	*/
	@Override
	public void setPortletIds(java.lang.String portletIds) {
		_spiDefinition.setPortletIds(portletIds);
	}

	/**
	* Sets the primary key of this s p i definition.
	*
	* @param primaryKey the primary key of this s p i definition
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_spiDefinition.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_spiDefinition.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public void setRestartAttempts(int restartAttempts) {
		_spiDefinition.setRestartAttempts(restartAttempts);
	}

	/**
	* Sets the servlet context names of this s p i definition.
	*
	* @param servletContextNames the servlet context names of this s p i definition
	*/
	@Override
	public void setServletContextNames(java.lang.String servletContextNames) {
		_spiDefinition.setServletContextNames(servletContextNames);
	}

	/**
	* Sets the spi definition ID of this s p i definition.
	*
	* @param spiDefinitionId the spi definition ID of this s p i definition
	*/
	@Override
	public void setSpiDefinitionId(long spiDefinitionId) {
		_spiDefinition.setSpiDefinitionId(spiDefinitionId);
	}

	/**
	* Sets the status of this s p i definition.
	*
	* @param status the status of this s p i definition
	*/
	@Override
	public void setStatus(int status) {
		_spiDefinition.setStatus(status);
	}

	/**
	* Sets the status message of this s p i definition.
	*
	* @param statusMessage the status message of this s p i definition
	*/
	@Override
	public void setStatusMessage(java.lang.String statusMessage) {
		_spiDefinition.setStatusMessage(statusMessage);
	}

	/**
	* Sets the type settings of this s p i definition.
	*
	* @param typeSettings the type settings of this s p i definition
	*/
	@Override
	public void setTypeSettings(java.lang.String typeSettings) {
		_spiDefinition.setTypeSettings(typeSettings);
	}

	@Override
	public void setTypeSettingsProperties(
		com.liferay.portal.kernel.util.UnicodeProperties typeSettingsProperties) {
		_spiDefinition.setTypeSettingsProperties(typeSettingsProperties);
	}

	/**
	* Sets the user ID of this s p i definition.
	*
	* @param userId the user ID of this s p i definition
	*/
	@Override
	public void setUserId(long userId) {
		_spiDefinition.setUserId(userId);
	}

	/**
	* Sets the user name of this s p i definition.
	*
	* @param userName the user name of this s p i definition
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_spiDefinition.setUserName(userName);
	}

	/**
	* Sets the user uuid of this s p i definition.
	*
	* @param userUuid the user uuid of this s p i definition
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_spiDefinition.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SPIDefinitionWrapper)) {
			return false;
		}

		SPIDefinitionWrapper spiDefinitionWrapper = (SPIDefinitionWrapper)obj;

		if (Objects.equals(_spiDefinition, spiDefinitionWrapper._spiDefinition)) {
			return true;
		}

		return false;
	}

	@Override
	public SPIDefinition getWrappedModel() {
		return _spiDefinition;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _spiDefinition.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _spiDefinition.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_spiDefinition.resetOriginalValues();
	}

	private final SPIDefinition _spiDefinition;
}