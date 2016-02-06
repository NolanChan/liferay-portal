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

package com.liferay.portal.workflow.kaleo.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the KaleoTaskAssignment service. Represents a row in the &quot;KaleoTaskAssignment&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link KaleoTaskAssignmentModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link KaleoTaskAssignmentImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTaskAssignmentImpl
 * @see KaleoTaskAssignment
 * @see KaleoTaskAssignmentModel
 * @generated
 */
@ProviderType
public class KaleoTaskAssignmentModelImpl extends BaseModelImpl<KaleoTaskAssignment>
	implements KaleoTaskAssignmentModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a kaleo task assignment model instance should use the {@link KaleoTaskAssignment} interface instead.
	 */
	public static final String TABLE_NAME = "KaleoTaskAssignment";
	public static final Object[][] TABLE_COLUMNS = {
			{ "kaleoTaskAssignmentId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "kaleoClassName", Types.VARCHAR },
			{ "kaleoClassPK", Types.BIGINT },
			{ "kaleoDefinitionId", Types.BIGINT },
			{ "kaleoNodeId", Types.BIGINT },
			{ "assigneeClassName", Types.VARCHAR },
			{ "assigneeClassPK", Types.BIGINT },
			{ "assigneeActionId", Types.VARCHAR },
			{ "assigneeScript", Types.CLOB },
			{ "assigneeScriptLanguage", Types.VARCHAR },
			{ "assigneeScriptRequiredContexts", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("kaleoTaskAssignmentId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("kaleoClassName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("kaleoClassPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("kaleoDefinitionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("kaleoNodeId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("assigneeClassName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("assigneeClassPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("assigneeActionId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("assigneeScript", Types.CLOB);
		TABLE_COLUMNS_MAP.put("assigneeScriptLanguage", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("assigneeScriptRequiredContexts", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table KaleoTaskAssignment (kaleoTaskAssignmentId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(200) null,createDate DATE null,modifiedDate DATE null,kaleoClassName VARCHAR(200) null,kaleoClassPK LONG,kaleoDefinitionId LONG,kaleoNodeId LONG,assigneeClassName VARCHAR(200) null,assigneeClassPK LONG,assigneeActionId VARCHAR(75) null,assigneeScript TEXT null,assigneeScriptLanguage VARCHAR(75) null,assigneeScriptRequiredContexts STRING null)";
	public static final String TABLE_SQL_DROP = "drop table KaleoTaskAssignment";
	public static final String ORDER_BY_JPQL = " ORDER BY kaleoTaskAssignment.kaleoTaskAssignmentId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY KaleoTaskAssignment.kaleoTaskAssignmentId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.workflow.kaleo.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.workflow.kaleo.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.portal.workflow.kaleo.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment"),
			true);
	public static final long ASSIGNEECLASSNAME_COLUMN_BITMASK = 1L;
	public static final long COMPANYID_COLUMN_BITMASK = 2L;
	public static final long KALEOCLASSNAME_COLUMN_BITMASK = 4L;
	public static final long KALEOCLASSPK_COLUMN_BITMASK = 8L;
	public static final long KALEODEFINITIONID_COLUMN_BITMASK = 16L;
	public static final long KALEOTASKASSIGNMENTID_COLUMN_BITMASK = 32L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.workflow.kaleo.service.util.ServiceProps.get(
				"lock.expiration.time.com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment"));

	public KaleoTaskAssignmentModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _kaleoTaskAssignmentId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setKaleoTaskAssignmentId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _kaleoTaskAssignmentId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return KaleoTaskAssignment.class;
	}

	@Override
	public String getModelClassName() {
		return KaleoTaskAssignment.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("kaleoTaskAssignmentId", getKaleoTaskAssignmentId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("kaleoClassName", getKaleoClassName());
		attributes.put("kaleoClassPK", getKaleoClassPK());
		attributes.put("kaleoDefinitionId", getKaleoDefinitionId());
		attributes.put("kaleoNodeId", getKaleoNodeId());
		attributes.put("assigneeClassName", getAssigneeClassName());
		attributes.put("assigneeClassPK", getAssigneeClassPK());
		attributes.put("assigneeActionId", getAssigneeActionId());
		attributes.put("assigneeScript", getAssigneeScript());
		attributes.put("assigneeScriptLanguage", getAssigneeScriptLanguage());
		attributes.put("assigneeScriptRequiredContexts",
			getAssigneeScriptRequiredContexts());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long kaleoTaskAssignmentId = (Long)attributes.get(
				"kaleoTaskAssignmentId");

		if (kaleoTaskAssignmentId != null) {
			setKaleoTaskAssignmentId(kaleoTaskAssignmentId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
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

		String kaleoClassName = (String)attributes.get("kaleoClassName");

		if (kaleoClassName != null) {
			setKaleoClassName(kaleoClassName);
		}

		Long kaleoClassPK = (Long)attributes.get("kaleoClassPK");

		if (kaleoClassPK != null) {
			setKaleoClassPK(kaleoClassPK);
		}

		Long kaleoDefinitionId = (Long)attributes.get("kaleoDefinitionId");

		if (kaleoDefinitionId != null) {
			setKaleoDefinitionId(kaleoDefinitionId);
		}

		Long kaleoNodeId = (Long)attributes.get("kaleoNodeId");

		if (kaleoNodeId != null) {
			setKaleoNodeId(kaleoNodeId);
		}

		String assigneeClassName = (String)attributes.get("assigneeClassName");

		if (assigneeClassName != null) {
			setAssigneeClassName(assigneeClassName);
		}

		Long assigneeClassPK = (Long)attributes.get("assigneeClassPK");

		if (assigneeClassPK != null) {
			setAssigneeClassPK(assigneeClassPK);
		}

		String assigneeActionId = (String)attributes.get("assigneeActionId");

		if (assigneeActionId != null) {
			setAssigneeActionId(assigneeActionId);
		}

		String assigneeScript = (String)attributes.get("assigneeScript");

		if (assigneeScript != null) {
			setAssigneeScript(assigneeScript);
		}

		String assigneeScriptLanguage = (String)attributes.get(
				"assigneeScriptLanguage");

		if (assigneeScriptLanguage != null) {
			setAssigneeScriptLanguage(assigneeScriptLanguage);
		}

		String assigneeScriptRequiredContexts = (String)attributes.get(
				"assigneeScriptRequiredContexts");

		if (assigneeScriptRequiredContexts != null) {
			setAssigneeScriptRequiredContexts(assigneeScriptRequiredContexts);
		}
	}

	@Override
	public long getKaleoTaskAssignmentId() {
		return _kaleoTaskAssignmentId;
	}

	@Override
	public void setKaleoTaskAssignmentId(long kaleoTaskAssignmentId) {
		_columnBitmask = -1L;

		_kaleoTaskAssignmentId = kaleoTaskAssignmentId;
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@Override
	public String getUserName() {
		if (_userName == null) {
			return StringPool.BLANK;
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@Override
	public String getKaleoClassName() {
		if (_kaleoClassName == null) {
			return StringPool.BLANK;
		}
		else {
			return _kaleoClassName;
		}
	}

	@Override
	public void setKaleoClassName(String kaleoClassName) {
		_columnBitmask |= KALEOCLASSNAME_COLUMN_BITMASK;

		if (_originalKaleoClassName == null) {
			_originalKaleoClassName = _kaleoClassName;
		}

		_kaleoClassName = kaleoClassName;
	}

	public String getOriginalKaleoClassName() {
		return GetterUtil.getString(_originalKaleoClassName);
	}

	@Override
	public long getKaleoClassPK() {
		return _kaleoClassPK;
	}

	@Override
	public void setKaleoClassPK(long kaleoClassPK) {
		_columnBitmask |= KALEOCLASSPK_COLUMN_BITMASK;

		if (!_setOriginalKaleoClassPK) {
			_setOriginalKaleoClassPK = true;

			_originalKaleoClassPK = _kaleoClassPK;
		}

		_kaleoClassPK = kaleoClassPK;
	}

	public long getOriginalKaleoClassPK() {
		return _originalKaleoClassPK;
	}

	@Override
	public long getKaleoDefinitionId() {
		return _kaleoDefinitionId;
	}

	@Override
	public void setKaleoDefinitionId(long kaleoDefinitionId) {
		_columnBitmask |= KALEODEFINITIONID_COLUMN_BITMASK;

		if (!_setOriginalKaleoDefinitionId) {
			_setOriginalKaleoDefinitionId = true;

			_originalKaleoDefinitionId = _kaleoDefinitionId;
		}

		_kaleoDefinitionId = kaleoDefinitionId;
	}

	public long getOriginalKaleoDefinitionId() {
		return _originalKaleoDefinitionId;
	}

	@Override
	public long getKaleoNodeId() {
		return _kaleoNodeId;
	}

	@Override
	public void setKaleoNodeId(long kaleoNodeId) {
		_kaleoNodeId = kaleoNodeId;
	}

	@Override
	public String getAssigneeClassName() {
		if (_assigneeClassName == null) {
			return StringPool.BLANK;
		}
		else {
			return _assigneeClassName;
		}
	}

	@Override
	public void setAssigneeClassName(String assigneeClassName) {
		_columnBitmask |= ASSIGNEECLASSNAME_COLUMN_BITMASK;

		if (_originalAssigneeClassName == null) {
			_originalAssigneeClassName = _assigneeClassName;
		}

		_assigneeClassName = assigneeClassName;
	}

	public String getOriginalAssigneeClassName() {
		return GetterUtil.getString(_originalAssigneeClassName);
	}

	@Override
	public long getAssigneeClassPK() {
		return _assigneeClassPK;
	}

	@Override
	public void setAssigneeClassPK(long assigneeClassPK) {
		_assigneeClassPK = assigneeClassPK;
	}

	@Override
	public String getAssigneeActionId() {
		if (_assigneeActionId == null) {
			return StringPool.BLANK;
		}
		else {
			return _assigneeActionId;
		}
	}

	@Override
	public void setAssigneeActionId(String assigneeActionId) {
		_assigneeActionId = assigneeActionId;
	}

	@Override
	public String getAssigneeScript() {
		if (_assigneeScript == null) {
			return StringPool.BLANK;
		}
		else {
			return _assigneeScript;
		}
	}

	@Override
	public void setAssigneeScript(String assigneeScript) {
		_assigneeScript = assigneeScript;
	}

	@Override
	public String getAssigneeScriptLanguage() {
		if (_assigneeScriptLanguage == null) {
			return StringPool.BLANK;
		}
		else {
			return _assigneeScriptLanguage;
		}
	}

	@Override
	public void setAssigneeScriptLanguage(String assigneeScriptLanguage) {
		_assigneeScriptLanguage = assigneeScriptLanguage;
	}

	@Override
	public String getAssigneeScriptRequiredContexts() {
		if (_assigneeScriptRequiredContexts == null) {
			return StringPool.BLANK;
		}
		else {
			return _assigneeScriptRequiredContexts;
		}
	}

	@Override
	public void setAssigneeScriptRequiredContexts(
		String assigneeScriptRequiredContexts) {
		_assigneeScriptRequiredContexts = assigneeScriptRequiredContexts;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			KaleoTaskAssignment.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public KaleoTaskAssignment toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (KaleoTaskAssignment)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		KaleoTaskAssignmentImpl kaleoTaskAssignmentImpl = new KaleoTaskAssignmentImpl();

		kaleoTaskAssignmentImpl.setKaleoTaskAssignmentId(getKaleoTaskAssignmentId());
		kaleoTaskAssignmentImpl.setGroupId(getGroupId());
		kaleoTaskAssignmentImpl.setCompanyId(getCompanyId());
		kaleoTaskAssignmentImpl.setUserId(getUserId());
		kaleoTaskAssignmentImpl.setUserName(getUserName());
		kaleoTaskAssignmentImpl.setCreateDate(getCreateDate());
		kaleoTaskAssignmentImpl.setModifiedDate(getModifiedDate());
		kaleoTaskAssignmentImpl.setKaleoClassName(getKaleoClassName());
		kaleoTaskAssignmentImpl.setKaleoClassPK(getKaleoClassPK());
		kaleoTaskAssignmentImpl.setKaleoDefinitionId(getKaleoDefinitionId());
		kaleoTaskAssignmentImpl.setKaleoNodeId(getKaleoNodeId());
		kaleoTaskAssignmentImpl.setAssigneeClassName(getAssigneeClassName());
		kaleoTaskAssignmentImpl.setAssigneeClassPK(getAssigneeClassPK());
		kaleoTaskAssignmentImpl.setAssigneeActionId(getAssigneeActionId());
		kaleoTaskAssignmentImpl.setAssigneeScript(getAssigneeScript());
		kaleoTaskAssignmentImpl.setAssigneeScriptLanguage(getAssigneeScriptLanguage());
		kaleoTaskAssignmentImpl.setAssigneeScriptRequiredContexts(getAssigneeScriptRequiredContexts());

		kaleoTaskAssignmentImpl.resetOriginalValues();

		return kaleoTaskAssignmentImpl;
	}

	@Override
	public int compareTo(KaleoTaskAssignment kaleoTaskAssignment) {
		int value = 0;

		if (getKaleoTaskAssignmentId() < kaleoTaskAssignment.getKaleoTaskAssignmentId()) {
			value = -1;
		}
		else if (getKaleoTaskAssignmentId() > kaleoTaskAssignment.getKaleoTaskAssignmentId()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof KaleoTaskAssignment)) {
			return false;
		}

		KaleoTaskAssignment kaleoTaskAssignment = (KaleoTaskAssignment)obj;

		long primaryKey = kaleoTaskAssignment.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		KaleoTaskAssignmentModelImpl kaleoTaskAssignmentModelImpl = this;

		kaleoTaskAssignmentModelImpl._originalCompanyId = kaleoTaskAssignmentModelImpl._companyId;

		kaleoTaskAssignmentModelImpl._setOriginalCompanyId = false;

		kaleoTaskAssignmentModelImpl._setModifiedDate = false;

		kaleoTaskAssignmentModelImpl._originalKaleoClassName = kaleoTaskAssignmentModelImpl._kaleoClassName;

		kaleoTaskAssignmentModelImpl._originalKaleoClassPK = kaleoTaskAssignmentModelImpl._kaleoClassPK;

		kaleoTaskAssignmentModelImpl._setOriginalKaleoClassPK = false;

		kaleoTaskAssignmentModelImpl._originalKaleoDefinitionId = kaleoTaskAssignmentModelImpl._kaleoDefinitionId;

		kaleoTaskAssignmentModelImpl._setOriginalKaleoDefinitionId = false;

		kaleoTaskAssignmentModelImpl._originalAssigneeClassName = kaleoTaskAssignmentModelImpl._assigneeClassName;

		kaleoTaskAssignmentModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<KaleoTaskAssignment> toCacheModel() {
		KaleoTaskAssignmentCacheModel kaleoTaskAssignmentCacheModel = new KaleoTaskAssignmentCacheModel();

		kaleoTaskAssignmentCacheModel.kaleoTaskAssignmentId = getKaleoTaskAssignmentId();

		kaleoTaskAssignmentCacheModel.groupId = getGroupId();

		kaleoTaskAssignmentCacheModel.companyId = getCompanyId();

		kaleoTaskAssignmentCacheModel.userId = getUserId();

		kaleoTaskAssignmentCacheModel.userName = getUserName();

		String userName = kaleoTaskAssignmentCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			kaleoTaskAssignmentCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			kaleoTaskAssignmentCacheModel.createDate = createDate.getTime();
		}
		else {
			kaleoTaskAssignmentCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			kaleoTaskAssignmentCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			kaleoTaskAssignmentCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		kaleoTaskAssignmentCacheModel.kaleoClassName = getKaleoClassName();

		String kaleoClassName = kaleoTaskAssignmentCacheModel.kaleoClassName;

		if ((kaleoClassName != null) && (kaleoClassName.length() == 0)) {
			kaleoTaskAssignmentCacheModel.kaleoClassName = null;
		}

		kaleoTaskAssignmentCacheModel.kaleoClassPK = getKaleoClassPK();

		kaleoTaskAssignmentCacheModel.kaleoDefinitionId = getKaleoDefinitionId();

		kaleoTaskAssignmentCacheModel.kaleoNodeId = getKaleoNodeId();

		kaleoTaskAssignmentCacheModel.assigneeClassName = getAssigneeClassName();

		String assigneeClassName = kaleoTaskAssignmentCacheModel.assigneeClassName;

		if ((assigneeClassName != null) && (assigneeClassName.length() == 0)) {
			kaleoTaskAssignmentCacheModel.assigneeClassName = null;
		}

		kaleoTaskAssignmentCacheModel.assigneeClassPK = getAssigneeClassPK();

		kaleoTaskAssignmentCacheModel.assigneeActionId = getAssigneeActionId();

		String assigneeActionId = kaleoTaskAssignmentCacheModel.assigneeActionId;

		if ((assigneeActionId != null) && (assigneeActionId.length() == 0)) {
			kaleoTaskAssignmentCacheModel.assigneeActionId = null;
		}

		kaleoTaskAssignmentCacheModel.assigneeScript = getAssigneeScript();

		String assigneeScript = kaleoTaskAssignmentCacheModel.assigneeScript;

		if ((assigneeScript != null) && (assigneeScript.length() == 0)) {
			kaleoTaskAssignmentCacheModel.assigneeScript = null;
		}

		kaleoTaskAssignmentCacheModel.assigneeScriptLanguage = getAssigneeScriptLanguage();

		String assigneeScriptLanguage = kaleoTaskAssignmentCacheModel.assigneeScriptLanguage;

		if ((assigneeScriptLanguage != null) &&
				(assigneeScriptLanguage.length() == 0)) {
			kaleoTaskAssignmentCacheModel.assigneeScriptLanguage = null;
		}

		kaleoTaskAssignmentCacheModel.assigneeScriptRequiredContexts = getAssigneeScriptRequiredContexts();

		String assigneeScriptRequiredContexts = kaleoTaskAssignmentCacheModel.assigneeScriptRequiredContexts;

		if ((assigneeScriptRequiredContexts != null) &&
				(assigneeScriptRequiredContexts.length() == 0)) {
			kaleoTaskAssignmentCacheModel.assigneeScriptRequiredContexts = null;
		}

		return kaleoTaskAssignmentCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(35);

		sb.append("{kaleoTaskAssignmentId=");
		sb.append(getKaleoTaskAssignmentId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", kaleoClassName=");
		sb.append(getKaleoClassName());
		sb.append(", kaleoClassPK=");
		sb.append(getKaleoClassPK());
		sb.append(", kaleoDefinitionId=");
		sb.append(getKaleoDefinitionId());
		sb.append(", kaleoNodeId=");
		sb.append(getKaleoNodeId());
		sb.append(", assigneeClassName=");
		sb.append(getAssigneeClassName());
		sb.append(", assigneeClassPK=");
		sb.append(getAssigneeClassPK());
		sb.append(", assigneeActionId=");
		sb.append(getAssigneeActionId());
		sb.append(", assigneeScript=");
		sb.append(getAssigneeScript());
		sb.append(", assigneeScriptLanguage=");
		sb.append(getAssigneeScriptLanguage());
		sb.append(", assigneeScriptRequiredContexts=");
		sb.append(getAssigneeScriptRequiredContexts());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(55);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>kaleoTaskAssignmentId</column-name><column-value><![CDATA[");
		sb.append(getKaleoTaskAssignmentId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>kaleoClassName</column-name><column-value><![CDATA[");
		sb.append(getKaleoClassName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>kaleoClassPK</column-name><column-value><![CDATA[");
		sb.append(getKaleoClassPK());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>kaleoDefinitionId</column-name><column-value><![CDATA[");
		sb.append(getKaleoDefinitionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>kaleoNodeId</column-name><column-value><![CDATA[");
		sb.append(getKaleoNodeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>assigneeClassName</column-name><column-value><![CDATA[");
		sb.append(getAssigneeClassName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>assigneeClassPK</column-name><column-value><![CDATA[");
		sb.append(getAssigneeClassPK());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>assigneeActionId</column-name><column-value><![CDATA[");
		sb.append(getAssigneeActionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>assigneeScript</column-name><column-value><![CDATA[");
		sb.append(getAssigneeScript());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>assigneeScriptLanguage</column-name><column-value><![CDATA[");
		sb.append(getAssigneeScriptLanguage());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>assigneeScriptRequiredContexts</column-name><column-value><![CDATA[");
		sb.append(getAssigneeScriptRequiredContexts());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = KaleoTaskAssignment.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			KaleoTaskAssignment.class
		};
	private long _kaleoTaskAssignmentId;
	private long _groupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _kaleoClassName;
	private String _originalKaleoClassName;
	private long _kaleoClassPK;
	private long _originalKaleoClassPK;
	private boolean _setOriginalKaleoClassPK;
	private long _kaleoDefinitionId;
	private long _originalKaleoDefinitionId;
	private boolean _setOriginalKaleoDefinitionId;
	private long _kaleoNodeId;
	private String _assigneeClassName;
	private String _originalAssigneeClassName;
	private long _assigneeClassPK;
	private String _assigneeActionId;
	private String _assigneeScript;
	private String _assigneeScriptLanguage;
	private String _assigneeScriptRequiredContexts;
	private long _columnBitmask;
	private KaleoTaskAssignment _escapedModel;
}