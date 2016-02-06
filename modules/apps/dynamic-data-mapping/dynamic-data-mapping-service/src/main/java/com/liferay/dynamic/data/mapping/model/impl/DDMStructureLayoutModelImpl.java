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

package com.liferay.dynamic.data.mapping.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.dynamic.data.mapping.model.DDMStructureLayout;
import com.liferay.dynamic.data.mapping.model.DDMStructureLayoutModel;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the DDMStructureLayout service. Represents a row in the &quot;DDMStructureLayout&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link DDMStructureLayoutModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link DDMStructureLayoutImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DDMStructureLayoutImpl
 * @see DDMStructureLayout
 * @see DDMStructureLayoutModel
 * @generated
 */
@ProviderType
public class DDMStructureLayoutModelImpl extends BaseModelImpl<DDMStructureLayout>
	implements DDMStructureLayoutModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a d d m structure layout model instance should use the {@link DDMStructureLayout} interface instead.
	 */
	public static final String TABLE_NAME = "DDMStructureLayout";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "structureLayoutId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "structureVersionId", Types.BIGINT },
			{ "definition", Types.CLOB }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("structureLayoutId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("structureVersionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("definition", Types.CLOB);
	}

	public static final String TABLE_SQL_CREATE = "create table DDMStructureLayout (uuid_ VARCHAR(75) null,structureLayoutId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,structureVersionId LONG,definition TEXT null)";
	public static final String TABLE_SQL_DROP = "drop table DDMStructureLayout";
	public static final String ORDER_BY_JPQL = " ORDER BY ddmStructureLayout.structureLayoutId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY DDMStructureLayout.structureLayoutId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.dynamic.data.mapping.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.dynamic.data.mapping.model.DDMStructureLayout"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.dynamic.data.mapping.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.dynamic.data.mapping.model.DDMStructureLayout"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.dynamic.data.mapping.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.dynamic.data.mapping.model.DDMStructureLayout"),
			true);
	public static final long COMPANYID_COLUMN_BITMASK = 1L;
	public static final long GROUPID_COLUMN_BITMASK = 2L;
	public static final long STRUCTUREVERSIONID_COLUMN_BITMASK = 4L;
	public static final long UUID_COLUMN_BITMASK = 8L;
	public static final long STRUCTURELAYOUTID_COLUMN_BITMASK = 16L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.dynamic.data.mapping.service.util.ServiceProps.get(
				"lock.expiration.time.com.liferay.dynamic.data.mapping.model.DDMStructureLayout"));

	public DDMStructureLayoutModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _structureLayoutId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setStructureLayoutId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _structureLayoutId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return DDMStructureLayout.class;
	}

	@Override
	public String getModelClassName() {
		return DDMStructureLayout.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("structureLayoutId", getStructureLayoutId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("structureVersionId", getStructureVersionId());
		attributes.put("definition", getDefinition());

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

		Long structureLayoutId = (Long)attributes.get("structureLayoutId");

		if (structureLayoutId != null) {
			setStructureLayoutId(structureLayoutId);
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

		Long structureVersionId = (Long)attributes.get("structureVersionId");

		if (structureVersionId != null) {
			setStructureVersionId(structureVersionId);
		}

		String definition = (String)attributes.get("definition");

		if (definition != null) {
			setDefinition(definition);
		}
	}

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

	@Override
	public long getStructureLayoutId() {
		return _structureLayoutId;
	}

	@Override
	public void setStructureLayoutId(long structureLayoutId) {
		_structureLayoutId = structureLayoutId;
	}

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
	public long getStructureVersionId() {
		return _structureVersionId;
	}

	@Override
	public void setStructureVersionId(long structureVersionId) {
		_columnBitmask |= STRUCTUREVERSIONID_COLUMN_BITMASK;

		if (!_setOriginalStructureVersionId) {
			_setOriginalStructureVersionId = true;

			_originalStructureVersionId = _structureVersionId;
		}

		_structureVersionId = structureVersionId;
	}

	public long getOriginalStructureVersionId() {
		return _originalStructureVersionId;
	}

	@Override
	public String getDefinition() {
		if (_definition == null) {
			return StringPool.BLANK;
		}
		else {
			return _definition;
		}
	}

	@Override
	public void setDefinition(String definition) {
		_definition = definition;
	}

	public com.liferay.dynamic.data.mapping.model.DDMFormLayout getDDMFormLayout() {
		return null;
	}

	public void setDDMFormLayout(
		com.liferay.dynamic.data.mapping.model.DDMFormLayout ddmFormLayout) {
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(PortalUtil.getClassNameId(
				DDMStructureLayout.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			DDMStructureLayout.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public DDMStructureLayout toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (DDMStructureLayout)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		DDMStructureLayoutImpl ddmStructureLayoutImpl = new DDMStructureLayoutImpl();

		ddmStructureLayoutImpl.setUuid(getUuid());
		ddmStructureLayoutImpl.setStructureLayoutId(getStructureLayoutId());
		ddmStructureLayoutImpl.setGroupId(getGroupId());
		ddmStructureLayoutImpl.setCompanyId(getCompanyId());
		ddmStructureLayoutImpl.setUserId(getUserId());
		ddmStructureLayoutImpl.setUserName(getUserName());
		ddmStructureLayoutImpl.setCreateDate(getCreateDate());
		ddmStructureLayoutImpl.setModifiedDate(getModifiedDate());
		ddmStructureLayoutImpl.setStructureVersionId(getStructureVersionId());
		ddmStructureLayoutImpl.setDefinition(getDefinition());

		ddmStructureLayoutImpl.resetOriginalValues();

		return ddmStructureLayoutImpl;
	}

	@Override
	public int compareTo(DDMStructureLayout ddmStructureLayout) {
		long primaryKey = ddmStructureLayout.getPrimaryKey();

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

		if (!(obj instanceof DDMStructureLayout)) {
			return false;
		}

		DDMStructureLayout ddmStructureLayout = (DDMStructureLayout)obj;

		long primaryKey = ddmStructureLayout.getPrimaryKey();

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
		DDMStructureLayoutModelImpl ddmStructureLayoutModelImpl = this;

		ddmStructureLayoutModelImpl._originalUuid = ddmStructureLayoutModelImpl._uuid;

		ddmStructureLayoutModelImpl._originalGroupId = ddmStructureLayoutModelImpl._groupId;

		ddmStructureLayoutModelImpl._setOriginalGroupId = false;

		ddmStructureLayoutModelImpl._originalCompanyId = ddmStructureLayoutModelImpl._companyId;

		ddmStructureLayoutModelImpl._setOriginalCompanyId = false;

		ddmStructureLayoutModelImpl._setModifiedDate = false;

		ddmStructureLayoutModelImpl._originalStructureVersionId = ddmStructureLayoutModelImpl._structureVersionId;

		ddmStructureLayoutModelImpl._setOriginalStructureVersionId = false;

		setDDMFormLayout(null);

		ddmStructureLayoutModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<DDMStructureLayout> toCacheModel() {
		DDMStructureLayoutCacheModel ddmStructureLayoutCacheModel = new DDMStructureLayoutCacheModel();

		ddmStructureLayoutCacheModel.uuid = getUuid();

		String uuid = ddmStructureLayoutCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			ddmStructureLayoutCacheModel.uuid = null;
		}

		ddmStructureLayoutCacheModel.structureLayoutId = getStructureLayoutId();

		ddmStructureLayoutCacheModel.groupId = getGroupId();

		ddmStructureLayoutCacheModel.companyId = getCompanyId();

		ddmStructureLayoutCacheModel.userId = getUserId();

		ddmStructureLayoutCacheModel.userName = getUserName();

		String userName = ddmStructureLayoutCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			ddmStructureLayoutCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			ddmStructureLayoutCacheModel.createDate = createDate.getTime();
		}
		else {
			ddmStructureLayoutCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			ddmStructureLayoutCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			ddmStructureLayoutCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		ddmStructureLayoutCacheModel.structureVersionId = getStructureVersionId();

		ddmStructureLayoutCacheModel.definition = getDefinition();

		String definition = ddmStructureLayoutCacheModel.definition;

		if ((definition != null) && (definition.length() == 0)) {
			ddmStructureLayoutCacheModel.definition = null;
		}

		ddmStructureLayoutCacheModel._ddmFormLayout = getDDMFormLayout();

		return ddmStructureLayoutCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", structureLayoutId=");
		sb.append(getStructureLayoutId());
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
		sb.append(", structureVersionId=");
		sb.append(getStructureVersionId());
		sb.append(", definition=");
		sb.append(getDefinition());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(34);

		sb.append("<model><model-name>");
		sb.append("com.liferay.dynamic.data.mapping.model.DDMStructureLayout");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>structureLayoutId</column-name><column-value><![CDATA[");
		sb.append(getStructureLayoutId());
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
			"<column><column-name>structureVersionId</column-name><column-value><![CDATA[");
		sb.append(getStructureVersionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>definition</column-name><column-value><![CDATA[");
		sb.append(getDefinition());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = DDMStructureLayout.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			DDMStructureLayout.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _structureLayoutId;
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
	private long _structureVersionId;
	private long _originalStructureVersionId;
	private boolean _setOriginalStructureVersionId;
	private String _definition;
	private long _columnBitmask;
	private DDMStructureLayout _escapedModel;
}