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
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient;
import com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipientModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the KaleoNotificationRecipient service. Represents a row in the &quot;KaleoNotificationRecipient&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link KaleoNotificationRecipientModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link KaleoNotificationRecipientImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoNotificationRecipientImpl
 * @see KaleoNotificationRecipient
 * @see KaleoNotificationRecipientModel
 * @generated
 */
@ProviderType
public class KaleoNotificationRecipientModelImpl extends BaseModelImpl<KaleoNotificationRecipient>
	implements KaleoNotificationRecipientModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a kaleo notification recipient model instance should use the {@link KaleoNotificationRecipient} interface instead.
	 */
	public static final String TABLE_NAME = "KaleoNotificationRecipient";
	public static final Object[][] TABLE_COLUMNS = {
			{ "kaleoNotificationRecipientId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "kaleoDefinitionId", Types.BIGINT },
			{ "kaleoNotificationId", Types.BIGINT },
			{ "recipientClassName", Types.VARCHAR },
			{ "recipientClassPK", Types.BIGINT },
			{ "recipientRoleType", Types.INTEGER },
			{ "recipientScript", Types.CLOB },
			{ "recipientScriptLanguage", Types.VARCHAR },
			{ "recipientScriptContexts", Types.VARCHAR },
			{ "address", Types.VARCHAR },
			{ "notificationReceptionType", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("kaleoNotificationRecipientId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("kaleoDefinitionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("kaleoNotificationId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("recipientClassName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("recipientClassPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("recipientRoleType", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("recipientScript", Types.CLOB);
		TABLE_COLUMNS_MAP.put("recipientScriptLanguage", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("recipientScriptContexts", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("address", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("notificationReceptionType", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table KaleoNotificationRecipient (kaleoNotificationRecipientId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(200) null,createDate DATE null,modifiedDate DATE null,kaleoDefinitionId LONG,kaleoNotificationId LONG,recipientClassName VARCHAR(200) null,recipientClassPK LONG,recipientRoleType INTEGER,recipientScript TEXT null,recipientScriptLanguage VARCHAR(75) null,recipientScriptContexts STRING null,address VARCHAR(255) null,notificationReceptionType VARCHAR(3) null)";
	public static final String TABLE_SQL_DROP = "drop table KaleoNotificationRecipient";
	public static final String ORDER_BY_JPQL = " ORDER BY kaleoNotificationRecipient.kaleoNotificationRecipientId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY KaleoNotificationRecipient.kaleoNotificationRecipientId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.workflow.kaleo.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.workflow.kaleo.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.portal.workflow.kaleo.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient"),
			true);
	public static final long COMPANYID_COLUMN_BITMASK = 1L;
	public static final long KALEODEFINITIONID_COLUMN_BITMASK = 2L;
	public static final long KALEONOTIFICATIONID_COLUMN_BITMASK = 4L;
	public static final long KALEONOTIFICATIONRECIPIENTID_COLUMN_BITMASK = 8L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.workflow.kaleo.service.util.ServiceProps.get(
				"lock.expiration.time.com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient"));

	public KaleoNotificationRecipientModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _kaleoNotificationRecipientId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setKaleoNotificationRecipientId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _kaleoNotificationRecipientId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return KaleoNotificationRecipient.class;
	}

	@Override
	public String getModelClassName() {
		return KaleoNotificationRecipient.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("kaleoNotificationRecipientId",
			getKaleoNotificationRecipientId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("kaleoDefinitionId", getKaleoDefinitionId());
		attributes.put("kaleoNotificationId", getKaleoNotificationId());
		attributes.put("recipientClassName", getRecipientClassName());
		attributes.put("recipientClassPK", getRecipientClassPK());
		attributes.put("recipientRoleType", getRecipientRoleType());
		attributes.put("recipientScript", getRecipientScript());
		attributes.put("recipientScriptLanguage", getRecipientScriptLanguage());
		attributes.put("recipientScriptContexts", getRecipientScriptContexts());
		attributes.put("address", getAddress());
		attributes.put("notificationReceptionType",
			getNotificationReceptionType());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long kaleoNotificationRecipientId = (Long)attributes.get(
				"kaleoNotificationRecipientId");

		if (kaleoNotificationRecipientId != null) {
			setKaleoNotificationRecipientId(kaleoNotificationRecipientId);
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

		Long kaleoDefinitionId = (Long)attributes.get("kaleoDefinitionId");

		if (kaleoDefinitionId != null) {
			setKaleoDefinitionId(kaleoDefinitionId);
		}

		Long kaleoNotificationId = (Long)attributes.get("kaleoNotificationId");

		if (kaleoNotificationId != null) {
			setKaleoNotificationId(kaleoNotificationId);
		}

		String recipientClassName = (String)attributes.get("recipientClassName");

		if (recipientClassName != null) {
			setRecipientClassName(recipientClassName);
		}

		Long recipientClassPK = (Long)attributes.get("recipientClassPK");

		if (recipientClassPK != null) {
			setRecipientClassPK(recipientClassPK);
		}

		Integer recipientRoleType = (Integer)attributes.get("recipientRoleType");

		if (recipientRoleType != null) {
			setRecipientRoleType(recipientRoleType);
		}

		String recipientScript = (String)attributes.get("recipientScript");

		if (recipientScript != null) {
			setRecipientScript(recipientScript);
		}

		String recipientScriptLanguage = (String)attributes.get(
				"recipientScriptLanguage");

		if (recipientScriptLanguage != null) {
			setRecipientScriptLanguage(recipientScriptLanguage);
		}

		String recipientScriptContexts = (String)attributes.get(
				"recipientScriptContexts");

		if (recipientScriptContexts != null) {
			setRecipientScriptContexts(recipientScriptContexts);
		}

		String address = (String)attributes.get("address");

		if (address != null) {
			setAddress(address);
		}

		String notificationReceptionType = (String)attributes.get(
				"notificationReceptionType");

		if (notificationReceptionType != null) {
			setNotificationReceptionType(notificationReceptionType);
		}
	}

	@Override
	public long getKaleoNotificationRecipientId() {
		return _kaleoNotificationRecipientId;
	}

	@Override
	public void setKaleoNotificationRecipientId(
		long kaleoNotificationRecipientId) {
		_columnBitmask = -1L;

		_kaleoNotificationRecipientId = kaleoNotificationRecipientId;
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
	public long getKaleoNotificationId() {
		return _kaleoNotificationId;
	}

	@Override
	public void setKaleoNotificationId(long kaleoNotificationId) {
		_columnBitmask |= KALEONOTIFICATIONID_COLUMN_BITMASK;

		if (!_setOriginalKaleoNotificationId) {
			_setOriginalKaleoNotificationId = true;

			_originalKaleoNotificationId = _kaleoNotificationId;
		}

		_kaleoNotificationId = kaleoNotificationId;
	}

	public long getOriginalKaleoNotificationId() {
		return _originalKaleoNotificationId;
	}

	@Override
	public String getRecipientClassName() {
		if (_recipientClassName == null) {
			return StringPool.BLANK;
		}
		else {
			return _recipientClassName;
		}
	}

	@Override
	public void setRecipientClassName(String recipientClassName) {
		_recipientClassName = recipientClassName;
	}

	@Override
	public long getRecipientClassPK() {
		return _recipientClassPK;
	}

	@Override
	public void setRecipientClassPK(long recipientClassPK) {
		_recipientClassPK = recipientClassPK;
	}

	@Override
	public int getRecipientRoleType() {
		return _recipientRoleType;
	}

	@Override
	public void setRecipientRoleType(int recipientRoleType) {
		_recipientRoleType = recipientRoleType;
	}

	@Override
	public String getRecipientScript() {
		if (_recipientScript == null) {
			return StringPool.BLANK;
		}
		else {
			return _recipientScript;
		}
	}

	@Override
	public void setRecipientScript(String recipientScript) {
		_recipientScript = recipientScript;
	}

	@Override
	public String getRecipientScriptLanguage() {
		if (_recipientScriptLanguage == null) {
			return StringPool.BLANK;
		}
		else {
			return _recipientScriptLanguage;
		}
	}

	@Override
	public void setRecipientScriptLanguage(String recipientScriptLanguage) {
		_recipientScriptLanguage = recipientScriptLanguage;
	}

	@Override
	public String getRecipientScriptContexts() {
		if (_recipientScriptContexts == null) {
			return StringPool.BLANK;
		}
		else {
			return _recipientScriptContexts;
		}
	}

	@Override
	public void setRecipientScriptContexts(String recipientScriptContexts) {
		_recipientScriptContexts = recipientScriptContexts;
	}

	@Override
	public String getAddress() {
		if (_address == null) {
			return StringPool.BLANK;
		}
		else {
			return _address;
		}
	}

	@Override
	public void setAddress(String address) {
		_address = address;
	}

	@Override
	public String getNotificationReceptionType() {
		if (_notificationReceptionType == null) {
			return StringPool.BLANK;
		}
		else {
			return _notificationReceptionType;
		}
	}

	@Override
	public void setNotificationReceptionType(String notificationReceptionType) {
		_notificationReceptionType = notificationReceptionType;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			KaleoNotificationRecipient.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public KaleoNotificationRecipient toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (KaleoNotificationRecipient)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		KaleoNotificationRecipientImpl kaleoNotificationRecipientImpl = new KaleoNotificationRecipientImpl();

		kaleoNotificationRecipientImpl.setKaleoNotificationRecipientId(getKaleoNotificationRecipientId());
		kaleoNotificationRecipientImpl.setGroupId(getGroupId());
		kaleoNotificationRecipientImpl.setCompanyId(getCompanyId());
		kaleoNotificationRecipientImpl.setUserId(getUserId());
		kaleoNotificationRecipientImpl.setUserName(getUserName());
		kaleoNotificationRecipientImpl.setCreateDate(getCreateDate());
		kaleoNotificationRecipientImpl.setModifiedDate(getModifiedDate());
		kaleoNotificationRecipientImpl.setKaleoDefinitionId(getKaleoDefinitionId());
		kaleoNotificationRecipientImpl.setKaleoNotificationId(getKaleoNotificationId());
		kaleoNotificationRecipientImpl.setRecipientClassName(getRecipientClassName());
		kaleoNotificationRecipientImpl.setRecipientClassPK(getRecipientClassPK());
		kaleoNotificationRecipientImpl.setRecipientRoleType(getRecipientRoleType());
		kaleoNotificationRecipientImpl.setRecipientScript(getRecipientScript());
		kaleoNotificationRecipientImpl.setRecipientScriptLanguage(getRecipientScriptLanguage());
		kaleoNotificationRecipientImpl.setRecipientScriptContexts(getRecipientScriptContexts());
		kaleoNotificationRecipientImpl.setAddress(getAddress());
		kaleoNotificationRecipientImpl.setNotificationReceptionType(getNotificationReceptionType());

		kaleoNotificationRecipientImpl.resetOriginalValues();

		return kaleoNotificationRecipientImpl;
	}

	@Override
	public int compareTo(KaleoNotificationRecipient kaleoNotificationRecipient) {
		int value = 0;

		if (getKaleoNotificationRecipientId() < kaleoNotificationRecipient.getKaleoNotificationRecipientId()) {
			value = -1;
		}
		else if (getKaleoNotificationRecipientId() > kaleoNotificationRecipient.getKaleoNotificationRecipientId()) {
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

		if (!(obj instanceof KaleoNotificationRecipient)) {
			return false;
		}

		KaleoNotificationRecipient kaleoNotificationRecipient = (KaleoNotificationRecipient)obj;

		long primaryKey = kaleoNotificationRecipient.getPrimaryKey();

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
		KaleoNotificationRecipientModelImpl kaleoNotificationRecipientModelImpl = this;

		kaleoNotificationRecipientModelImpl._originalCompanyId = kaleoNotificationRecipientModelImpl._companyId;

		kaleoNotificationRecipientModelImpl._setOriginalCompanyId = false;

		kaleoNotificationRecipientModelImpl._setModifiedDate = false;

		kaleoNotificationRecipientModelImpl._originalKaleoDefinitionId = kaleoNotificationRecipientModelImpl._kaleoDefinitionId;

		kaleoNotificationRecipientModelImpl._setOriginalKaleoDefinitionId = false;

		kaleoNotificationRecipientModelImpl._originalKaleoNotificationId = kaleoNotificationRecipientModelImpl._kaleoNotificationId;

		kaleoNotificationRecipientModelImpl._setOriginalKaleoNotificationId = false;

		kaleoNotificationRecipientModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<KaleoNotificationRecipient> toCacheModel() {
		KaleoNotificationRecipientCacheModel kaleoNotificationRecipientCacheModel =
			new KaleoNotificationRecipientCacheModel();

		kaleoNotificationRecipientCacheModel.kaleoNotificationRecipientId = getKaleoNotificationRecipientId();

		kaleoNotificationRecipientCacheModel.groupId = getGroupId();

		kaleoNotificationRecipientCacheModel.companyId = getCompanyId();

		kaleoNotificationRecipientCacheModel.userId = getUserId();

		kaleoNotificationRecipientCacheModel.userName = getUserName();

		String userName = kaleoNotificationRecipientCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			kaleoNotificationRecipientCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			kaleoNotificationRecipientCacheModel.createDate = createDate.getTime();
		}
		else {
			kaleoNotificationRecipientCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			kaleoNotificationRecipientCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			kaleoNotificationRecipientCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		kaleoNotificationRecipientCacheModel.kaleoDefinitionId = getKaleoDefinitionId();

		kaleoNotificationRecipientCacheModel.kaleoNotificationId = getKaleoNotificationId();

		kaleoNotificationRecipientCacheModel.recipientClassName = getRecipientClassName();

		String recipientClassName = kaleoNotificationRecipientCacheModel.recipientClassName;

		if ((recipientClassName != null) && (recipientClassName.length() == 0)) {
			kaleoNotificationRecipientCacheModel.recipientClassName = null;
		}

		kaleoNotificationRecipientCacheModel.recipientClassPK = getRecipientClassPK();

		kaleoNotificationRecipientCacheModel.recipientRoleType = getRecipientRoleType();

		kaleoNotificationRecipientCacheModel.recipientScript = getRecipientScript();

		String recipientScript = kaleoNotificationRecipientCacheModel.recipientScript;

		if ((recipientScript != null) && (recipientScript.length() == 0)) {
			kaleoNotificationRecipientCacheModel.recipientScript = null;
		}

		kaleoNotificationRecipientCacheModel.recipientScriptLanguage = getRecipientScriptLanguage();

		String recipientScriptLanguage = kaleoNotificationRecipientCacheModel.recipientScriptLanguage;

		if ((recipientScriptLanguage != null) &&
				(recipientScriptLanguage.length() == 0)) {
			kaleoNotificationRecipientCacheModel.recipientScriptLanguage = null;
		}

		kaleoNotificationRecipientCacheModel.recipientScriptContexts = getRecipientScriptContexts();

		String recipientScriptContexts = kaleoNotificationRecipientCacheModel.recipientScriptContexts;

		if ((recipientScriptContexts != null) &&
				(recipientScriptContexts.length() == 0)) {
			kaleoNotificationRecipientCacheModel.recipientScriptContexts = null;
		}

		kaleoNotificationRecipientCacheModel.address = getAddress();

		String address = kaleoNotificationRecipientCacheModel.address;

		if ((address != null) && (address.length() == 0)) {
			kaleoNotificationRecipientCacheModel.address = null;
		}

		kaleoNotificationRecipientCacheModel.notificationReceptionType = getNotificationReceptionType();

		String notificationReceptionType = kaleoNotificationRecipientCacheModel.notificationReceptionType;

		if ((notificationReceptionType != null) &&
				(notificationReceptionType.length() == 0)) {
			kaleoNotificationRecipientCacheModel.notificationReceptionType = null;
		}

		return kaleoNotificationRecipientCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(35);

		sb.append("{kaleoNotificationRecipientId=");
		sb.append(getKaleoNotificationRecipientId());
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
		sb.append(", kaleoDefinitionId=");
		sb.append(getKaleoDefinitionId());
		sb.append(", kaleoNotificationId=");
		sb.append(getKaleoNotificationId());
		sb.append(", recipientClassName=");
		sb.append(getRecipientClassName());
		sb.append(", recipientClassPK=");
		sb.append(getRecipientClassPK());
		sb.append(", recipientRoleType=");
		sb.append(getRecipientRoleType());
		sb.append(", recipientScript=");
		sb.append(getRecipientScript());
		sb.append(", recipientScriptLanguage=");
		sb.append(getRecipientScriptLanguage());
		sb.append(", recipientScriptContexts=");
		sb.append(getRecipientScriptContexts());
		sb.append(", address=");
		sb.append(getAddress());
		sb.append(", notificationReceptionType=");
		sb.append(getNotificationReceptionType());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(55);

		sb.append("<model><model-name>");
		sb.append(
			"com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>kaleoNotificationRecipientId</column-name><column-value><![CDATA[");
		sb.append(getKaleoNotificationRecipientId());
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
			"<column><column-name>kaleoDefinitionId</column-name><column-value><![CDATA[");
		sb.append(getKaleoDefinitionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>kaleoNotificationId</column-name><column-value><![CDATA[");
		sb.append(getKaleoNotificationId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>recipientClassName</column-name><column-value><![CDATA[");
		sb.append(getRecipientClassName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>recipientClassPK</column-name><column-value><![CDATA[");
		sb.append(getRecipientClassPK());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>recipientRoleType</column-name><column-value><![CDATA[");
		sb.append(getRecipientRoleType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>recipientScript</column-name><column-value><![CDATA[");
		sb.append(getRecipientScript());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>recipientScriptLanguage</column-name><column-value><![CDATA[");
		sb.append(getRecipientScriptLanguage());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>recipientScriptContexts</column-name><column-value><![CDATA[");
		sb.append(getRecipientScriptContexts());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>address</column-name><column-value><![CDATA[");
		sb.append(getAddress());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>notificationReceptionType</column-name><column-value><![CDATA[");
		sb.append(getNotificationReceptionType());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = KaleoNotificationRecipient.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			KaleoNotificationRecipient.class
		};
	private long _kaleoNotificationRecipientId;
	private long _groupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _kaleoDefinitionId;
	private long _originalKaleoDefinitionId;
	private boolean _setOriginalKaleoDefinitionId;
	private long _kaleoNotificationId;
	private long _originalKaleoNotificationId;
	private boolean _setOriginalKaleoNotificationId;
	private String _recipientClassName;
	private long _recipientClassPK;
	private int _recipientRoleType;
	private String _recipientScript;
	private String _recipientScriptLanguage;
	private String _recipientScriptContexts;
	private String _address;
	private String _notificationReceptionType;
	private long _columnBitmask;
	private KaleoNotificationRecipient _escapedModel;
}