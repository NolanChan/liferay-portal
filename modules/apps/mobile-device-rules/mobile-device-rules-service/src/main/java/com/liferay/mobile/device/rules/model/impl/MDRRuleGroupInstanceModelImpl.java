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

package com.liferay.mobile.device.rules.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.mobile.device.rules.model.MDRRuleGroupInstance;
import com.liferay.mobile.device.rules.model.MDRRuleGroupInstanceModel;
import com.liferay.mobile.device.rules.model.MDRRuleGroupInstanceSoap;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the MDRRuleGroupInstance service. Represents a row in the &quot;MDRRuleGroupInstance&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link MDRRuleGroupInstanceModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link MDRRuleGroupInstanceImpl}.
 * </p>
 *
 * @author Edward C. Han
 * @see MDRRuleGroupInstanceImpl
 * @see MDRRuleGroupInstance
 * @see MDRRuleGroupInstanceModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class MDRRuleGroupInstanceModelImpl extends BaseModelImpl<MDRRuleGroupInstance>
	implements MDRRuleGroupInstanceModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a m d r rule group instance model instance should use the {@link MDRRuleGroupInstance} interface instead.
	 */
	public static final String TABLE_NAME = "MDRRuleGroupInstance";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "ruleGroupInstanceId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "classNameId", Types.BIGINT },
			{ "classPK", Types.BIGINT },
			{ "ruleGroupId", Types.BIGINT },
			{ "priority", Types.INTEGER },
			{ "lastPublishDate", Types.TIMESTAMP }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("ruleGroupInstanceId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ruleGroupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("priority", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("lastPublishDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE = "create table MDRRuleGroupInstance (uuid_ VARCHAR(75) null,ruleGroupInstanceId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,classNameId LONG,classPK LONG,ruleGroupId LONG,priority INTEGER,lastPublishDate DATE null)";
	public static final String TABLE_SQL_DROP = "drop table MDRRuleGroupInstance";
	public static final String ORDER_BY_JPQL = " ORDER BY mdrRuleGroupInstance.ruleGroupInstanceId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY MDRRuleGroupInstance.ruleGroupInstanceId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.mobile.device.rules.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.mobile.device.rules.model.MDRRuleGroupInstance"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.mobile.device.rules.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.mobile.device.rules.model.MDRRuleGroupInstance"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.mobile.device.rules.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.mobile.device.rules.model.MDRRuleGroupInstance"),
			true);
	public static final long CLASSNAMEID_COLUMN_BITMASK = 1L;
	public static final long CLASSPK_COLUMN_BITMASK = 2L;
	public static final long COMPANYID_COLUMN_BITMASK = 4L;
	public static final long GROUPID_COLUMN_BITMASK = 8L;
	public static final long RULEGROUPID_COLUMN_BITMASK = 16L;
	public static final long UUID_COLUMN_BITMASK = 32L;
	public static final long RULEGROUPINSTANCEID_COLUMN_BITMASK = 64L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static MDRRuleGroupInstance toModel(
		MDRRuleGroupInstanceSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		MDRRuleGroupInstance model = new MDRRuleGroupInstanceImpl();

		model.setUuid(soapModel.getUuid());
		model.setRuleGroupInstanceId(soapModel.getRuleGroupInstanceId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setClassNameId(soapModel.getClassNameId());
		model.setClassPK(soapModel.getClassPK());
		model.setRuleGroupId(soapModel.getRuleGroupId());
		model.setPriority(soapModel.getPriority());
		model.setLastPublishDate(soapModel.getLastPublishDate());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<MDRRuleGroupInstance> toModels(
		MDRRuleGroupInstanceSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<MDRRuleGroupInstance> models = new ArrayList<MDRRuleGroupInstance>(soapModels.length);

		for (MDRRuleGroupInstanceSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.mobile.device.rules.service.util.ServiceProps.get(
				"lock.expiration.time.com.liferay.mobile.device.rules.model.MDRRuleGroupInstance"));

	public MDRRuleGroupInstanceModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _ruleGroupInstanceId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setRuleGroupInstanceId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ruleGroupInstanceId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return MDRRuleGroupInstance.class;
	}

	@Override
	public String getModelClassName() {
		return MDRRuleGroupInstance.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("ruleGroupInstanceId", getRuleGroupInstanceId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("ruleGroupId", getRuleGroupId());
		attributes.put("priority", getPriority());
		attributes.put("lastPublishDate", getLastPublishDate());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long ruleGroupInstanceId = (Long)attributes.get("ruleGroupInstanceId");

		if (ruleGroupInstanceId != null) {
			setRuleGroupInstanceId(ruleGroupInstanceId);
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

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		Long ruleGroupId = (Long)attributes.get("ruleGroupId");

		if (ruleGroupId != null) {
			setRuleGroupId(ruleGroupId);
		}

		Integer priority = (Integer)attributes.get("priority");

		if (priority != null) {
			setPriority(priority);
		}

		Date lastPublishDate = (Date)attributes.get("lastPublishDate");

		if (lastPublishDate != null) {
			setLastPublishDate(lastPublishDate);
		}
	}

	@JSON
	@Override
	public String getUuid() {
		if (_uuid == null) {
			return StringPool.BLANK;
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@JSON
	@Override
	public long getRuleGroupInstanceId() {
		return _ruleGroupInstanceId;
	}

	@Override
	public void setRuleGroupInstanceId(long ruleGroupInstanceId) {
		_ruleGroupInstanceId = ruleGroupInstanceId;
	}

	@JSON
	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
	}

	@JSON
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

	@JSON
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

	@JSON
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

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@JSON
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
	public String getClassName() {
		if (getClassNameId() <= 0) {
			return StringPool.BLANK;
		}

		return PortalUtil.getClassName(getClassNameId());
	}

	@Override
	public void setClassName(String className) {
		long classNameId = 0;

		if (Validator.isNotNull(className)) {
			classNameId = PortalUtil.getClassNameId(className);
		}

		setClassNameId(classNameId);
	}

	@JSON
	@Override
	public long getClassNameId() {
		return _classNameId;
	}

	@Override
	public void setClassNameId(long classNameId) {
		_columnBitmask |= CLASSNAMEID_COLUMN_BITMASK;

		if (!_setOriginalClassNameId) {
			_setOriginalClassNameId = true;

			_originalClassNameId = _classNameId;
		}

		_classNameId = classNameId;
	}

	public long getOriginalClassNameId() {
		return _originalClassNameId;
	}

	@JSON
	@Override
	public long getClassPK() {
		return _classPK;
	}

	@Override
	public void setClassPK(long classPK) {
		_columnBitmask |= CLASSPK_COLUMN_BITMASK;

		if (!_setOriginalClassPK) {
			_setOriginalClassPK = true;

			_originalClassPK = _classPK;
		}

		_classPK = classPK;
	}

	public long getOriginalClassPK() {
		return _originalClassPK;
	}

	@JSON
	@Override
	public long getRuleGroupId() {
		return _ruleGroupId;
	}

	@Override
	public void setRuleGroupId(long ruleGroupId) {
		_columnBitmask |= RULEGROUPID_COLUMN_BITMASK;

		if (!_setOriginalRuleGroupId) {
			_setOriginalRuleGroupId = true;

			_originalRuleGroupId = _ruleGroupId;
		}

		_ruleGroupId = ruleGroupId;
	}

	public long getOriginalRuleGroupId() {
		return _originalRuleGroupId;
	}

	@JSON
	@Override
	public int getPriority() {
		return _priority;
	}

	@Override
	public void setPriority(int priority) {
		_priority = priority;
	}

	@JSON
	@Override
	public Date getLastPublishDate() {
		return _lastPublishDate;
	}

	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_lastPublishDate = lastPublishDate;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(PortalUtil.getClassNameId(
				MDRRuleGroupInstance.class.getName()), getClassNameId());
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			MDRRuleGroupInstance.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public MDRRuleGroupInstance toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (MDRRuleGroupInstance)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		MDRRuleGroupInstanceImpl mdrRuleGroupInstanceImpl = new MDRRuleGroupInstanceImpl();

		mdrRuleGroupInstanceImpl.setUuid(getUuid());
		mdrRuleGroupInstanceImpl.setRuleGroupInstanceId(getRuleGroupInstanceId());
		mdrRuleGroupInstanceImpl.setGroupId(getGroupId());
		mdrRuleGroupInstanceImpl.setCompanyId(getCompanyId());
		mdrRuleGroupInstanceImpl.setUserId(getUserId());
		mdrRuleGroupInstanceImpl.setUserName(getUserName());
		mdrRuleGroupInstanceImpl.setCreateDate(getCreateDate());
		mdrRuleGroupInstanceImpl.setModifiedDate(getModifiedDate());
		mdrRuleGroupInstanceImpl.setClassNameId(getClassNameId());
		mdrRuleGroupInstanceImpl.setClassPK(getClassPK());
		mdrRuleGroupInstanceImpl.setRuleGroupId(getRuleGroupId());
		mdrRuleGroupInstanceImpl.setPriority(getPriority());
		mdrRuleGroupInstanceImpl.setLastPublishDate(getLastPublishDate());

		mdrRuleGroupInstanceImpl.resetOriginalValues();

		return mdrRuleGroupInstanceImpl;
	}

	@Override
	public int compareTo(MDRRuleGroupInstance mdrRuleGroupInstance) {
		long primaryKey = mdrRuleGroupInstance.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MDRRuleGroupInstance)) {
			return false;
		}

		MDRRuleGroupInstance mdrRuleGroupInstance = (MDRRuleGroupInstance)obj;

		long primaryKey = mdrRuleGroupInstance.getPrimaryKey();

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
		MDRRuleGroupInstanceModelImpl mdrRuleGroupInstanceModelImpl = this;

		mdrRuleGroupInstanceModelImpl._originalUuid = mdrRuleGroupInstanceModelImpl._uuid;

		mdrRuleGroupInstanceModelImpl._originalGroupId = mdrRuleGroupInstanceModelImpl._groupId;

		mdrRuleGroupInstanceModelImpl._setOriginalGroupId = false;

		mdrRuleGroupInstanceModelImpl._originalCompanyId = mdrRuleGroupInstanceModelImpl._companyId;

		mdrRuleGroupInstanceModelImpl._setOriginalCompanyId = false;

		mdrRuleGroupInstanceModelImpl._setModifiedDate = false;

		mdrRuleGroupInstanceModelImpl._originalClassNameId = mdrRuleGroupInstanceModelImpl._classNameId;

		mdrRuleGroupInstanceModelImpl._setOriginalClassNameId = false;

		mdrRuleGroupInstanceModelImpl._originalClassPK = mdrRuleGroupInstanceModelImpl._classPK;

		mdrRuleGroupInstanceModelImpl._setOriginalClassPK = false;

		mdrRuleGroupInstanceModelImpl._originalRuleGroupId = mdrRuleGroupInstanceModelImpl._ruleGroupId;

		mdrRuleGroupInstanceModelImpl._setOriginalRuleGroupId = false;

		mdrRuleGroupInstanceModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<MDRRuleGroupInstance> toCacheModel() {
		MDRRuleGroupInstanceCacheModel mdrRuleGroupInstanceCacheModel = new MDRRuleGroupInstanceCacheModel();

		mdrRuleGroupInstanceCacheModel.uuid = getUuid();

		String uuid = mdrRuleGroupInstanceCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			mdrRuleGroupInstanceCacheModel.uuid = null;
		}

		mdrRuleGroupInstanceCacheModel.ruleGroupInstanceId = getRuleGroupInstanceId();

		mdrRuleGroupInstanceCacheModel.groupId = getGroupId();

		mdrRuleGroupInstanceCacheModel.companyId = getCompanyId();

		mdrRuleGroupInstanceCacheModel.userId = getUserId();

		mdrRuleGroupInstanceCacheModel.userName = getUserName();

		String userName = mdrRuleGroupInstanceCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			mdrRuleGroupInstanceCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			mdrRuleGroupInstanceCacheModel.createDate = createDate.getTime();
		}
		else {
			mdrRuleGroupInstanceCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			mdrRuleGroupInstanceCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			mdrRuleGroupInstanceCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		mdrRuleGroupInstanceCacheModel.classNameId = getClassNameId();

		mdrRuleGroupInstanceCacheModel.classPK = getClassPK();

		mdrRuleGroupInstanceCacheModel.ruleGroupId = getRuleGroupId();

		mdrRuleGroupInstanceCacheModel.priority = getPriority();

		Date lastPublishDate = getLastPublishDate();

		if (lastPublishDate != null) {
			mdrRuleGroupInstanceCacheModel.lastPublishDate = lastPublishDate.getTime();
		}
		else {
			mdrRuleGroupInstanceCacheModel.lastPublishDate = Long.MIN_VALUE;
		}

		return mdrRuleGroupInstanceCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", ruleGroupInstanceId=");
		sb.append(getRuleGroupInstanceId());
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
		sb.append(", classNameId=");
		sb.append(getClassNameId());
		sb.append(", classPK=");
		sb.append(getClassPK());
		sb.append(", ruleGroupId=");
		sb.append(getRuleGroupId());
		sb.append(", priority=");
		sb.append(getPriority());
		sb.append(", lastPublishDate=");
		sb.append(getLastPublishDate());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(43);

		sb.append("<model><model-name>");
		sb.append("com.liferay.mobile.device.rules.model.MDRRuleGroupInstance");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>ruleGroupInstanceId</column-name><column-value><![CDATA[");
		sb.append(getRuleGroupInstanceId());
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
			"<column><column-name>classNameId</column-name><column-value><![CDATA[");
		sb.append(getClassNameId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classPK</column-name><column-value><![CDATA[");
		sb.append(getClassPK());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>ruleGroupId</column-name><column-value><![CDATA[");
		sb.append(getRuleGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>priority</column-name><column-value><![CDATA[");
		sb.append(getPriority());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>lastPublishDate</column-name><column-value><![CDATA[");
		sb.append(getLastPublishDate());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = MDRRuleGroupInstance.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			MDRRuleGroupInstance.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _ruleGroupInstanceId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _classNameId;
	private long _originalClassNameId;
	private boolean _setOriginalClassNameId;
	private long _classPK;
	private long _originalClassPK;
	private boolean _setOriginalClassPK;
	private long _ruleGroupId;
	private long _originalRuleGroupId;
	private boolean _setOriginalRuleGroupId;
	private int _priority;
	private Date _lastPublishDate;
	private long _columnBitmask;
	private MDRRuleGroupInstance _escapedModel;
}