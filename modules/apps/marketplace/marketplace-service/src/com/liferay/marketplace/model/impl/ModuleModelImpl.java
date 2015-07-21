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

package com.liferay.marketplace.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.marketplace.model.Module;
import com.liferay.marketplace.model.ModuleModel;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the Module service. Represents a row in the &quot;Marketplace_Module&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.liferay.marketplace.model.ModuleModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ModuleImpl}.
 * </p>
 *
 * @author Ryan Park
 * @see ModuleImpl
 * @see com.liferay.marketplace.model.Module
 * @see com.liferay.marketplace.model.ModuleModel
 * @generated
 */
@ProviderType
public class ModuleModelImpl extends BaseModelImpl<Module>
	implements ModuleModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a module model instance should use the {@link com.liferay.marketplace.model.Module} interface instead.
	 */
	public static final String TABLE_NAME = "Marketplace_Module";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "moduleId", Types.BIGINT },
			{ "appId", Types.BIGINT },
			{ "bundleSymbolicName", Types.VARCHAR },
			{ "bundleVersion", Types.VARCHAR },
			{ "contextName", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("moduleId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("appId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("bundleSymbolicName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("bundleVersion", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("contextName", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table Marketplace_Module (uuid_ VARCHAR(75) null,moduleId LONG not null primary key,appId LONG,bundleSymbolicName VARCHAR(500) null,bundleVersion VARCHAR(75) null,contextName VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table Marketplace_Module";
	public static final String ORDER_BY_JPQL = " ORDER BY module.moduleId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY Marketplace_Module.moduleId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.marketplace.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.Module"), true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.marketplace.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.Module"), true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.marketplace.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.Module"), true);
	public static final long APPID_COLUMN_BITMASK = 1L;
	public static final long BUNDLESYMBOLICNAME_COLUMN_BITMASK = 2L;
	public static final long BUNDLEVERSION_COLUMN_BITMASK = 4L;
	public static final long CONTEXTNAME_COLUMN_BITMASK = 8L;
	public static final long UUID_COLUMN_BITMASK = 16L;
	public static final long MODULEID_COLUMN_BITMASK = 32L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.marketplace.service.util.ServiceProps.get(
				"lock.expiration.time.Module"));

	public ModuleModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _moduleId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setModuleId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _moduleId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Module.class;
	}

	@Override
	public String getModelClassName() {
		return Module.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("moduleId", getModuleId());
		attributes.put("appId", getAppId());
		attributes.put("bundleSymbolicName", getBundleSymbolicName());
		attributes.put("bundleVersion", getBundleVersion());
		attributes.put("contextName", getContextName());

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

		Long moduleId = (Long)attributes.get("moduleId");

		if (moduleId != null) {
			setModuleId(moduleId);
		}

		Long appId = (Long)attributes.get("appId");

		if (appId != null) {
			setAppId(appId);
		}

		String bundleSymbolicName = (String)attributes.get("bundleSymbolicName");

		if (bundleSymbolicName != null) {
			setBundleSymbolicName(bundleSymbolicName);
		}

		String bundleVersion = (String)attributes.get("bundleVersion");

		if (bundleVersion != null) {
			setBundleVersion(bundleVersion);
		}

		String contextName = (String)attributes.get("contextName");

		if (contextName != null) {
			setContextName(contextName);
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
	public long getModuleId() {
		return _moduleId;
	}

	@Override
	public void setModuleId(long moduleId) {
		_moduleId = moduleId;
	}

	@Override
	public long getAppId() {
		return _appId;
	}

	@Override
	public void setAppId(long appId) {
		_columnBitmask |= APPID_COLUMN_BITMASK;

		if (!_setOriginalAppId) {
			_setOriginalAppId = true;

			_originalAppId = _appId;
		}

		_appId = appId;
	}

	public long getOriginalAppId() {
		return _originalAppId;
	}

	@Override
	public String getBundleSymbolicName() {
		if (_bundleSymbolicName == null) {
			return StringPool.BLANK;
		}
		else {
			return _bundleSymbolicName;
		}
	}

	@Override
	public void setBundleSymbolicName(String bundleSymbolicName) {
		_columnBitmask |= BUNDLESYMBOLICNAME_COLUMN_BITMASK;

		if (_originalBundleSymbolicName == null) {
			_originalBundleSymbolicName = _bundleSymbolicName;
		}

		_bundleSymbolicName = bundleSymbolicName;
	}

	public String getOriginalBundleSymbolicName() {
		return GetterUtil.getString(_originalBundleSymbolicName);
	}

	@Override
	public String getBundleVersion() {
		if (_bundleVersion == null) {
			return StringPool.BLANK;
		}
		else {
			return _bundleVersion;
		}
	}

	@Override
	public void setBundleVersion(String bundleVersion) {
		_columnBitmask |= BUNDLEVERSION_COLUMN_BITMASK;

		if (_originalBundleVersion == null) {
			_originalBundleVersion = _bundleVersion;
		}

		_bundleVersion = bundleVersion;
	}

	public String getOriginalBundleVersion() {
		return GetterUtil.getString(_originalBundleVersion);
	}

	@Override
	public String getContextName() {
		if (_contextName == null) {
			return StringPool.BLANK;
		}
		else {
			return _contextName;
		}
	}

	@Override
	public void setContextName(String contextName) {
		_columnBitmask |= CONTEXTNAME_COLUMN_BITMASK;

		if (_originalContextName == null) {
			_originalContextName = _contextName;
		}

		_contextName = contextName;
	}

	public String getOriginalContextName() {
		return GetterUtil.getString(_originalContextName);
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
			Module.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Module toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (Module)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		ModuleImpl moduleImpl = new ModuleImpl();

		moduleImpl.setUuid(getUuid());
		moduleImpl.setModuleId(getModuleId());
		moduleImpl.setAppId(getAppId());
		moduleImpl.setBundleSymbolicName(getBundleSymbolicName());
		moduleImpl.setBundleVersion(getBundleVersion());
		moduleImpl.setContextName(getContextName());

		moduleImpl.resetOriginalValues();

		return moduleImpl;
	}

	@Override
	public int compareTo(Module module) {
		long primaryKey = module.getPrimaryKey();

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

		if (!(obj instanceof Module)) {
			return false;
		}

		Module module = (Module)obj;

		long primaryKey = module.getPrimaryKey();

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
		ModuleModelImpl moduleModelImpl = this;

		moduleModelImpl._originalUuid = moduleModelImpl._uuid;

		moduleModelImpl._originalAppId = moduleModelImpl._appId;

		moduleModelImpl._setOriginalAppId = false;

		moduleModelImpl._originalBundleSymbolicName = moduleModelImpl._bundleSymbolicName;

		moduleModelImpl._originalBundleVersion = moduleModelImpl._bundleVersion;

		moduleModelImpl._originalContextName = moduleModelImpl._contextName;

		moduleModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Module> toCacheModel() {
		ModuleCacheModel moduleCacheModel = new ModuleCacheModel();

		moduleCacheModel.uuid = getUuid();

		String uuid = moduleCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			moduleCacheModel.uuid = null;
		}

		moduleCacheModel.moduleId = getModuleId();

		moduleCacheModel.appId = getAppId();

		moduleCacheModel.bundleSymbolicName = getBundleSymbolicName();

		String bundleSymbolicName = moduleCacheModel.bundleSymbolicName;

		if ((bundleSymbolicName != null) && (bundleSymbolicName.length() == 0)) {
			moduleCacheModel.bundleSymbolicName = null;
		}

		moduleCacheModel.bundleVersion = getBundleVersion();

		String bundleVersion = moduleCacheModel.bundleVersion;

		if ((bundleVersion != null) && (bundleVersion.length() == 0)) {
			moduleCacheModel.bundleVersion = null;
		}

		moduleCacheModel.contextName = getContextName();

		String contextName = moduleCacheModel.contextName;

		if ((contextName != null) && (contextName.length() == 0)) {
			moduleCacheModel.contextName = null;
		}

		return moduleCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", moduleId=");
		sb.append(getModuleId());
		sb.append(", appId=");
		sb.append(getAppId());
		sb.append(", bundleSymbolicName=");
		sb.append(getBundleSymbolicName());
		sb.append(", bundleVersion=");
		sb.append(getBundleVersion());
		sb.append(", contextName=");
		sb.append(getContextName());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(22);

		sb.append("<model><model-name>");
		sb.append("com.liferay.marketplace.model.Module");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>moduleId</column-name><column-value><![CDATA[");
		sb.append(getModuleId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>appId</column-name><column-value><![CDATA[");
		sb.append(getAppId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>bundleSymbolicName</column-name><column-value><![CDATA[");
		sb.append(getBundleSymbolicName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>bundleVersion</column-name><column-value><![CDATA[");
		sb.append(getBundleVersion());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>contextName</column-name><column-value><![CDATA[");
		sb.append(getContextName());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = Module.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			Module.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _moduleId;
	private long _appId;
	private long _originalAppId;
	private boolean _setOriginalAppId;
	private String _bundleSymbolicName;
	private String _originalBundleSymbolicName;
	private String _bundleVersion;
	private String _originalBundleVersion;
	private String _contextName;
	private String _originalContextName;
	private long _columnBitmask;
	private Module _escapedModel;
}