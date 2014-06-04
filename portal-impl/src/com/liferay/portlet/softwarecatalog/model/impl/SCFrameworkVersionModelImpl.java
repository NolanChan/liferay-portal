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

package com.liferay.portlet.softwarecatalog.model.impl;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.User;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;
import com.liferay.portlet.softwarecatalog.model.SCFrameworkVersion;
import com.liferay.portlet.softwarecatalog.model.SCFrameworkVersionModel;
import com.liferay.portlet.softwarecatalog.model.SCFrameworkVersionSoap;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the SCFrameworkVersion service. Represents a row in the &quot;SCFrameworkVersion&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.liferay.portlet.softwarecatalog.model.SCFrameworkVersionModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SCFrameworkVersionImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SCFrameworkVersionImpl
 * @see com.liferay.portlet.softwarecatalog.model.SCFrameworkVersion
 * @see com.liferay.portlet.softwarecatalog.model.SCFrameworkVersionModel
 * @generated
 */
@JSON(strict = true)
public class SCFrameworkVersionModelImpl extends BaseModelImpl<SCFrameworkVersion>
	implements SCFrameworkVersionModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a s c framework version model instance should use the {@link com.liferay.portlet.softwarecatalog.model.SCFrameworkVersion} interface instead.
	 */
	public static final String TABLE_NAME = "SCFrameworkVersion";
	public static final Object[][] TABLE_COLUMNS = {
			{ "frameworkVersionId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "name", Types.VARCHAR },
			{ "url", Types.VARCHAR },
			{ "active_", Types.BOOLEAN },
			{ "priority", Types.INTEGER }
		};
	public static final String TABLE_SQL_CREATE = "create table SCFrameworkVersion (frameworkVersionId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,name VARCHAR(75) null,url STRING null,active_ BOOLEAN,priority INTEGER)";
	public static final String TABLE_SQL_DROP = "drop table SCFrameworkVersion";
	public static final String ORDER_BY_JPQL = " ORDER BY scFrameworkVersion.name DESC";
	public static final String ORDER_BY_SQL = " ORDER BY SCFrameworkVersion.name DESC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.entity.cache.enabled.com.liferay.portlet.softwarecatalog.model.SCFrameworkVersion"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.finder.cache.enabled.com.liferay.portlet.softwarecatalog.model.SCFrameworkVersion"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.column.bitmask.enabled.com.liferay.portlet.softwarecatalog.model.SCFrameworkVersion"),
			true);
	public static long ACTIVE_COLUMN_BITMASK = 1L;
	public static long COMPANYID_COLUMN_BITMASK = 2L;
	public static long GROUPID_COLUMN_BITMASK = 4L;
	public static long NAME_COLUMN_BITMASK = 8L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static SCFrameworkVersion toModel(SCFrameworkVersionSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		SCFrameworkVersion model = new SCFrameworkVersionImpl();

		model.setFrameworkVersionId(soapModel.getFrameworkVersionId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setName(soapModel.getName());
		model.setUrl(soapModel.getUrl());
		model.setActive(soapModel.getActive());
		model.setPriority(soapModel.getPriority());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<SCFrameworkVersion> toModels(
		SCFrameworkVersionSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<SCFrameworkVersion> models = new ArrayList<SCFrameworkVersion>(soapModels.length);

		for (SCFrameworkVersionSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final String MAPPING_TABLE_SCFRAMEWORKVERSI_SCPRODUCTVERS_NAME =
		"SCFrameworkVersi_SCProductVers";
	public static final Object[][] MAPPING_TABLE_SCFRAMEWORKVERSI_SCPRODUCTVERS_COLUMNS =
		{
			{ "frameworkVersionId", Types.BIGINT },
			{ "productVersionId", Types.BIGINT }
		};
	public static final String MAPPING_TABLE_SCFRAMEWORKVERSI_SCPRODUCTVERS_SQL_CREATE =
		"create table SCFrameworkVersi_SCProductVers (frameworkVersionId LONG not null,productVersionId LONG not null,primary key (frameworkVersionId, productVersionId))";
	public static final boolean FINDER_CACHE_ENABLED_SCFRAMEWORKVERSI_SCPRODUCTVERS =
		GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.finder.cache.enabled.SCFrameworkVersi_SCProductVers"),
			true);
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
				"lock.expiration.time.com.liferay.portlet.softwarecatalog.model.SCFrameworkVersion"));

	public SCFrameworkVersionModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _frameworkVersionId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setFrameworkVersionId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _frameworkVersionId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return SCFrameworkVersion.class;
	}

	@Override
	public String getModelClassName() {
		return SCFrameworkVersion.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("frameworkVersionId", getFrameworkVersionId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("url", getUrl());
		attributes.put("active", getActive());
		attributes.put("priority", getPriority());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long frameworkVersionId = (Long)attributes.get("frameworkVersionId");

		if (frameworkVersionId != null) {
			setFrameworkVersionId(frameworkVersionId);
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

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String url = (String)attributes.get("url");

		if (url != null) {
			setUrl(url);
		}

		Boolean active = (Boolean)attributes.get("active");

		if (active != null) {
			setActive(active);
		}

		Integer priority = (Integer)attributes.get("priority");

		if (priority != null) {
			setPriority(priority);
		}
	}

	@JSON
	@Override
	public long getFrameworkVersionId() {
		return _frameworkVersionId;
	}

	@Override
	public void setFrameworkVersionId(long frameworkVersionId) {
		_frameworkVersionId = frameworkVersionId;
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

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public String getName() {
		if (_name == null) {
			return StringPool.BLANK;
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		_columnBitmask = -1L;

		_name = name;
	}

	@JSON
	@Override
	public String getUrl() {
		if (_url == null) {
			return StringPool.BLANK;
		}
		else {
			return _url;
		}
	}

	@Override
	public void setUrl(String url) {
		_url = url;
	}

	@JSON
	@Override
	public boolean getActive() {
		return _active;
	}

	@Override
	public boolean isActive() {
		return _active;
	}

	@Override
	public void setActive(boolean active) {
		_columnBitmask |= ACTIVE_COLUMN_BITMASK;

		if (!_setOriginalActive) {
			_setOriginalActive = true;

			_originalActive = _active;
		}

		_active = active;
	}

	public boolean getOriginalActive() {
		return _originalActive;
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

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			SCFrameworkVersion.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public SCFrameworkVersion toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (SCFrameworkVersion)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		SCFrameworkVersionImpl scFrameworkVersionImpl = new SCFrameworkVersionImpl();

		scFrameworkVersionImpl.setFrameworkVersionId(getFrameworkVersionId());
		scFrameworkVersionImpl.setGroupId(getGroupId());
		scFrameworkVersionImpl.setCompanyId(getCompanyId());
		scFrameworkVersionImpl.setUserId(getUserId());
		scFrameworkVersionImpl.setUserName(getUserName());
		scFrameworkVersionImpl.setCreateDate(getCreateDate());
		scFrameworkVersionImpl.setModifiedDate(getModifiedDate());
		scFrameworkVersionImpl.setName(getName());
		scFrameworkVersionImpl.setUrl(getUrl());
		scFrameworkVersionImpl.setActive(getActive());
		scFrameworkVersionImpl.setPriority(getPriority());

		scFrameworkVersionImpl.resetOriginalValues();

		return scFrameworkVersionImpl;
	}

	@Override
	public int compareTo(SCFrameworkVersion scFrameworkVersion) {
		int value = 0;

		value = getName().compareTo(scFrameworkVersion.getName());

		value = value * -1;

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

		if (!(obj instanceof SCFrameworkVersion)) {
			return false;
		}

		SCFrameworkVersion scFrameworkVersion = (SCFrameworkVersion)obj;

		long primaryKey = scFrameworkVersion.getPrimaryKey();

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
		SCFrameworkVersionModelImpl scFrameworkVersionModelImpl = this;

		scFrameworkVersionModelImpl._originalGroupId = scFrameworkVersionModelImpl._groupId;

		scFrameworkVersionModelImpl._setOriginalGroupId = false;

		scFrameworkVersionModelImpl._originalCompanyId = scFrameworkVersionModelImpl._companyId;

		scFrameworkVersionModelImpl._setOriginalCompanyId = false;

		scFrameworkVersionModelImpl._originalActive = scFrameworkVersionModelImpl._active;

		scFrameworkVersionModelImpl._setOriginalActive = false;

		scFrameworkVersionModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<SCFrameworkVersion> toCacheModel() {
		SCFrameworkVersionCacheModel scFrameworkVersionCacheModel = new SCFrameworkVersionCacheModel();

		scFrameworkVersionCacheModel.frameworkVersionId = getFrameworkVersionId();

		scFrameworkVersionCacheModel.groupId = getGroupId();

		scFrameworkVersionCacheModel.companyId = getCompanyId();

		scFrameworkVersionCacheModel.userId = getUserId();

		scFrameworkVersionCacheModel.userName = getUserName();

		String userName = scFrameworkVersionCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			scFrameworkVersionCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			scFrameworkVersionCacheModel.createDate = createDate.getTime();
		}
		else {
			scFrameworkVersionCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			scFrameworkVersionCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			scFrameworkVersionCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		scFrameworkVersionCacheModel.name = getName();

		String name = scFrameworkVersionCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			scFrameworkVersionCacheModel.name = null;
		}

		scFrameworkVersionCacheModel.url = getUrl();

		String url = scFrameworkVersionCacheModel.url;

		if ((url != null) && (url.length() == 0)) {
			scFrameworkVersionCacheModel.url = null;
		}

		scFrameworkVersionCacheModel.active = getActive();

		scFrameworkVersionCacheModel.priority = getPriority();

		return scFrameworkVersionCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{frameworkVersionId=");
		sb.append(getFrameworkVersionId());
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
		sb.append(", name=");
		sb.append(getName());
		sb.append(", url=");
		sb.append(getUrl());
		sb.append(", active=");
		sb.append(getActive());
		sb.append(", priority=");
		sb.append(getPriority());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(37);

		sb.append("<model><model-name>");
		sb.append(
			"com.liferay.portlet.softwarecatalog.model.SCFrameworkVersion");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>frameworkVersionId</column-name><column-value><![CDATA[");
		sb.append(getFrameworkVersionId());
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
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>url</column-name><column-value><![CDATA[");
		sb.append(getUrl());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>active</column-name><column-value><![CDATA[");
		sb.append(getActive());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>priority</column-name><column-value><![CDATA[");
		sb.append(getPriority());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static ClassLoader _classLoader = SCFrameworkVersion.class.getClassLoader();
	private static Class<?>[] _escapedModelInterfaces = new Class[] {
			SCFrameworkVersion.class
		};
	private long _frameworkVersionId;
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
	private String _name;
	private String _url;
	private boolean _active;
	private boolean _originalActive;
	private boolean _setOriginalActive;
	private int _priority;
	private long _columnBitmask;
	private SCFrameworkVersion _escapedModel;
}