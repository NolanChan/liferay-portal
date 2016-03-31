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

package com.liferay.portal.security.audit.storage.internal;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.security.audit.AuditEvent;

import java.io.Serializable;

import java.util.Date;

/**
 * @author Brian Greenwald
 * @author Prathima Shreenath
 */
public class AuditEventImpl implements AuditEvent, Serializable {

	public AuditEventImpl(
		com.liferay.portal.security.audit.storage.model.AuditEvent
			auditEvent) {

		_auditEvent = auditEvent;
	}

	@Override
	public String getAdditionalInfo() {
		return _auditEvent.getAdditionalInfo();
	}

	@Override
	public long getAuditEventId() {
		return _auditEvent.getAuditEventId();
	}

	@Override
	public String getClassName() {
		return _auditEvent.getClassName();
	}

	@Override
	public String getClassPK() {
		return _auditEvent.getClassPK();
	}

	@Override
	public String getClientHost() {
		return _auditEvent.getClientHost();
	}

	@Override
	public String getClientIP() {
		return _auditEvent.getClientIP();
	}

	@Override
	public long getCompanyId() {
		return _auditEvent.getCompanyId();
	}

	@Override
	public Date getCreateDate() {
		return _auditEvent.getCreateDate();
	}

	@Override
	public String getEventType() {
		return _auditEvent.getEventType();
	}

	@Override
	public String getMessage() {
		return _auditEvent.getMessage();
	}

	@Override
	public long getPrimaryKey() {
		return _auditEvent.getPrimaryKey();
	}

	@Override
	public String getServerName() {
		return _auditEvent.getServerName();
	}

	@Override
	public int getServerPort() {
		return _auditEvent.getServerPort();
	}

	@Override
	public String getSessionID() {
		return _auditEvent.getSessionID();
	}

	@Override
	public long getUserId() {
		return _auditEvent.getUserId();
	}

	@Override
	public String getUserName() {
		return _auditEvent.getUserName();
	}

	@Override
	public String getUserUuid() {
		return _auditEvent.getUserUuid();
	}

	@Override
	public void setAdditionalInfo(String additionalInfo) {
		_auditEvent.setAdditionalInfo(additionalInfo);
	}

	@Override
	public void setAuditEventId(long auditEventId) {
		_auditEvent.setAuditEventId(auditEventId);
	}

	@Override
	public void setClassName(String className) {
		_auditEvent.setClassName(className);
	}

	@Override
	public void setClassPK(String classPK) {
		_auditEvent.setClassPK(classPK);
	}

	@Override
	public void setClientHost(String clientHost) {
		_auditEvent.setClientHost(clientHost);
	}

	@Override
	public void setClientIP(String clientIP) {
		_auditEvent.setClientIP(clientIP);
	}

	@Override
	public void setCompanyId(long companyId) {
		_auditEvent.setCompanyId(companyId);
	}

	@Override
	public void setCreateDate(Date createDate) {
		_auditEvent.setCreateDate(createDate);
	}

	@Override
	public void setEventType(String eventType) {
		_auditEvent.setEventType(eventType);
	}

	@Override
	public void setMessage(String message) {
		_auditEvent.setMessage(message);
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		_auditEvent.setPrimaryKey(primaryKey);
	}

	@Override
	public void setServerName(String serverName) {
		_auditEvent.setServerName(serverName);
	}

	@Override
	public void setServerPort(int serverPort) {
		_auditEvent.setServerPort(serverPort);
	}

	@Override
	public void setSessionID(String sessionID) {
		_auditEvent.setSessionID(sessionID);
	}

	@Override
	public void setUserId(long userId) {
		_auditEvent.setUserId(userId);
	}

	@Override
	public void setUserName(String userName) {
		_auditEvent.setUserName(userName);
	}

	@Override
	public void setUserUuid(String userUuid) {
		_auditEvent.setUserUuid(userUuid);
	}

	@Override
	public AuditEvent toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (AuditEvent)ProxyUtil.newProxyInstance(
				_classLoader, _escapedModelInterfaces,
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	private static final ClassLoader _classLoader =
		AuditEvent.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
		AuditEvent.class
	};

	private final com.liferay.portal.security.audit.storage.model.AuditEvent
		_auditEvent;
	private AuditEvent _escapedModel;

}