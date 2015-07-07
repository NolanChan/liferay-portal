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

package com.liferay.portal.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ResourceTypePermission;
import com.liferay.portal.model.ResourceTypePermissionModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the ResourceTypePermission service. Represents a row in the &quot;ResourceTypePermission&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link ResourceTypePermissionModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ResourceTypePermissionImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ResourceTypePermissionImpl
 * @see ResourceTypePermission
 * @see ResourceTypePermissionModel
 * @generated
 */
@ProviderType
public class ResourceTypePermissionModelImpl extends BaseModelImpl<ResourceTypePermission>
	implements ResourceTypePermissionModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a resource type permission model instance should use the {@link ResourceTypePermission} interface instead.
	 */
	public static final String TABLE_NAME = "ResourceTypePermission";
	public static final Object[][] TABLE_COLUMNS = {
			{ "mvccVersion", Types.BIGINT },
			{ "resourceTypePermissionId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "name", Types.VARCHAR },
			{ "roleId", Types.BIGINT },
			{ "actionIds", Types.BIGINT }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("resourceTypePermissionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("roleId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("actionIds", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE = "create table ResourceTypePermission (mvccVersion LONG default 0,resourceTypePermissionId LONG not null primary key,companyId LONG,groupId LONG,name VARCHAR(75) null,roleId LONG,actionIds LONG)";
	public static final String TABLE_SQL_DROP = "drop table ResourceTypePermission";
	public static final String ORDER_BY_JPQL = " ORDER BY resourceTypePermission.resourceTypePermissionId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY ResourceTypePermission.resourceTypePermissionId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.entity.cache.enabled.com.liferay.portal.model.ResourceTypePermission"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.finder.cache.enabled.com.liferay.portal.model.ResourceTypePermission"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.column.bitmask.enabled.com.liferay.portal.model.ResourceTypePermission"),
			true);
	public static final long COMPANYID_COLUMN_BITMASK = 1L;
	public static final long GROUPID_COLUMN_BITMASK = 2L;
	public static final long NAME_COLUMN_BITMASK = 4L;
	public static final long ROLEID_COLUMN_BITMASK = 8L;
	public static final long RESOURCETYPEPERMISSIONID_COLUMN_BITMASK = 16L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
				"lock.expiration.time.com.liferay.portal.model.ResourceTypePermission"));

	public ResourceTypePermissionModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _resourceTypePermissionId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setResourceTypePermissionId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _resourceTypePermissionId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return ResourceTypePermission.class;
	}

	@Override
	public String getModelClassName() {
		return ResourceTypePermission.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("resourceTypePermissionId", getResourceTypePermissionId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("name", getName());
		attributes.put("roleId", getRoleId());
		attributes.put("actionIds", getActionIds());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		Long resourceTypePermissionId = (Long)attributes.get(
				"resourceTypePermissionId");

		if (resourceTypePermissionId != null) {
			setResourceTypePermissionId(resourceTypePermissionId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		Long roleId = (Long)attributes.get("roleId");

		if (roleId != null) {
			setRoleId(roleId);
		}

		Long actionIds = (Long)attributes.get("actionIds");

		if (actionIds != null) {
			setActionIds(actionIds);
		}
	}

	@Override
	public long getMvccVersion() {
		return _mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	@Override
	public long getResourceTypePermissionId() {
		return _resourceTypePermissionId;
	}

	@Override
	public void setResourceTypePermissionId(long resourceTypePermissionId) {
		_resourceTypePermissionId = resourceTypePermissionId;
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
		_columnBitmask |= NAME_COLUMN_BITMASK;

		if (_originalName == null) {
			_originalName = _name;
		}

		_name = name;
	}

	public String getOriginalName() {
		return GetterUtil.getString(_originalName);
	}

	@Override
	public long getRoleId() {
		return _roleId;
	}

	@Override
	public void setRoleId(long roleId) {
		_columnBitmask |= ROLEID_COLUMN_BITMASK;

		if (!_setOriginalRoleId) {
			_setOriginalRoleId = true;

			_originalRoleId = _roleId;
		}

		_roleId = roleId;
	}

	public long getOriginalRoleId() {
		return _originalRoleId;
	}

	@Override
	public long getActionIds() {
		return _actionIds;
	}

	@Override
	public void setActionIds(long actionIds) {
		_actionIds = actionIds;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			ResourceTypePermission.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public ResourceTypePermission toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (ResourceTypePermission)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		ResourceTypePermissionImpl resourceTypePermissionImpl = new ResourceTypePermissionImpl();

		resourceTypePermissionImpl.setMvccVersion(getMvccVersion());
		resourceTypePermissionImpl.setResourceTypePermissionId(getResourceTypePermissionId());
		resourceTypePermissionImpl.setCompanyId(getCompanyId());
		resourceTypePermissionImpl.setGroupId(getGroupId());
		resourceTypePermissionImpl.setName(getName());
		resourceTypePermissionImpl.setRoleId(getRoleId());
		resourceTypePermissionImpl.setActionIds(getActionIds());

		resourceTypePermissionImpl.resetOriginalValues();

		return resourceTypePermissionImpl;
	}

	@Override
	public int compareTo(ResourceTypePermission resourceTypePermission) {
		long primaryKey = resourceTypePermission.getPrimaryKey();

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

		if (!(obj instanceof ResourceTypePermission)) {
			return false;
		}

		ResourceTypePermission resourceTypePermission = (ResourceTypePermission)obj;

		long primaryKey = resourceTypePermission.getPrimaryKey();

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
		ResourceTypePermissionModelImpl resourceTypePermissionModelImpl = this;

		resourceTypePermissionModelImpl._originalCompanyId = resourceTypePermissionModelImpl._companyId;

		resourceTypePermissionModelImpl._setOriginalCompanyId = false;

		resourceTypePermissionModelImpl._originalGroupId = resourceTypePermissionModelImpl._groupId;

		resourceTypePermissionModelImpl._setOriginalGroupId = false;

		resourceTypePermissionModelImpl._originalName = resourceTypePermissionModelImpl._name;

		resourceTypePermissionModelImpl._originalRoleId = resourceTypePermissionModelImpl._roleId;

		resourceTypePermissionModelImpl._setOriginalRoleId = false;

		resourceTypePermissionModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<ResourceTypePermission> toCacheModel() {
		ResourceTypePermissionCacheModel resourceTypePermissionCacheModel = new ResourceTypePermissionCacheModel();

		resourceTypePermissionCacheModel.mvccVersion = getMvccVersion();

		resourceTypePermissionCacheModel.resourceTypePermissionId = getResourceTypePermissionId();

		resourceTypePermissionCacheModel.companyId = getCompanyId();

		resourceTypePermissionCacheModel.groupId = getGroupId();

		resourceTypePermissionCacheModel.name = getName();

		String name = resourceTypePermissionCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			resourceTypePermissionCacheModel.name = null;
		}

		resourceTypePermissionCacheModel.roleId = getRoleId();

		resourceTypePermissionCacheModel.actionIds = getActionIds();

		return resourceTypePermissionCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{mvccVersion=");
		sb.append(getMvccVersion());
		sb.append(", resourceTypePermissionId=");
		sb.append(getResourceTypePermissionId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", roleId=");
		sb.append(getRoleId());
		sb.append(", actionIds=");
		sb.append(getActionIds());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(25);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portal.model.ResourceTypePermission");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>mvccVersion</column-name><column-value><![CDATA[");
		sb.append(getMvccVersion());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>resourceTypePermissionId</column-name><column-value><![CDATA[");
		sb.append(getResourceTypePermissionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>roleId</column-name><column-value><![CDATA[");
		sb.append(getRoleId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>actionIds</column-name><column-value><![CDATA[");
		sb.append(getActionIds());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = ResourceTypePermission.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			ResourceTypePermission.class
		};
	private long _mvccVersion;
	private long _resourceTypePermissionId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private String _name;
	private String _originalName;
	private long _roleId;
	private long _originalRoleId;
	private boolean _setOriginalRoleId;
	private long _actionIds;
	private long _columnBitmask;
	private ResourceTypePermission _escapedModel;
}