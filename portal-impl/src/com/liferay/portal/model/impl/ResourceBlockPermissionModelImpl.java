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
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ResourceBlockPermission;
import com.liferay.portal.model.ResourceBlockPermissionModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the ResourceBlockPermission service. Represents a row in the &quot;ResourceBlockPermission&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link ResourceBlockPermissionModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ResourceBlockPermissionImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ResourceBlockPermissionImpl
 * @see ResourceBlockPermission
 * @see ResourceBlockPermissionModel
 * @generated
 */
@ProviderType
public class ResourceBlockPermissionModelImpl extends BaseModelImpl<ResourceBlockPermission>
	implements ResourceBlockPermissionModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a resource block permission model instance should use the {@link ResourceBlockPermission} interface instead.
	 */
	public static final String TABLE_NAME = "ResourceBlockPermission";
	public static final Object[][] TABLE_COLUMNS = {
			{ "mvccVersion", Types.BIGINT },
			{ "resourceBlockPermissionId", Types.BIGINT },
			{ "resourceBlockId", Types.BIGINT },
			{ "roleId", Types.BIGINT },
			{ "actionIds", Types.BIGINT }
		};
	public static final String TABLE_SQL_CREATE = "create table ResourceBlockPermission (mvccVersion LONG default 0,resourceBlockPermissionId LONG not null primary key,resourceBlockId LONG,roleId LONG,actionIds LONG)";
	public static final String TABLE_SQL_DROP = "drop table ResourceBlockPermission";
	public static final String ORDER_BY_JPQL = " ORDER BY resourceBlockPermission.resourceBlockPermissionId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY ResourceBlockPermission.resourceBlockPermissionId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.entity.cache.enabled.com.liferay.portal.model.ResourceBlockPermission"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.finder.cache.enabled.com.liferay.portal.model.ResourceBlockPermission"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.column.bitmask.enabled.com.liferay.portal.model.ResourceBlockPermission"),
			true);
	public static final long RESOURCEBLOCKID_COLUMN_BITMASK = 1L;
	public static final long ROLEID_COLUMN_BITMASK = 2L;
	public static final long RESOURCEBLOCKPERMISSIONID_COLUMN_BITMASK = 4L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
				"lock.expiration.time.com.liferay.portal.model.ResourceBlockPermission"));

	public ResourceBlockPermissionModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _resourceBlockPermissionId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setResourceBlockPermissionId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _resourceBlockPermissionId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return ResourceBlockPermission.class;
	}

	@Override
	public String getModelClassName() {
		return ResourceBlockPermission.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("resourceBlockPermissionId",
			getResourceBlockPermissionId());
		attributes.put("resourceBlockId", getResourceBlockId());
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

		Long resourceBlockPermissionId = (Long)attributes.get(
				"resourceBlockPermissionId");

		if (resourceBlockPermissionId != null) {
			setResourceBlockPermissionId(resourceBlockPermissionId);
		}

		Long resourceBlockId = (Long)attributes.get("resourceBlockId");

		if (resourceBlockId != null) {
			setResourceBlockId(resourceBlockId);
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
	public long getResourceBlockPermissionId() {
		return _resourceBlockPermissionId;
	}

	@Override
	public void setResourceBlockPermissionId(long resourceBlockPermissionId) {
		_resourceBlockPermissionId = resourceBlockPermissionId;
	}

	@Override
	public long getResourceBlockId() {
		return _resourceBlockId;
	}

	@Override
	public void setResourceBlockId(long resourceBlockId) {
		_columnBitmask |= RESOURCEBLOCKID_COLUMN_BITMASK;

		if (!_setOriginalResourceBlockId) {
			_setOriginalResourceBlockId = true;

			_originalResourceBlockId = _resourceBlockId;
		}

		_resourceBlockId = resourceBlockId;
	}

	public long getOriginalResourceBlockId() {
		return _originalResourceBlockId;
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
		return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
			ResourceBlockPermission.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public ResourceBlockPermission toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (ResourceBlockPermission)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		ResourceBlockPermissionImpl resourceBlockPermissionImpl = new ResourceBlockPermissionImpl();

		resourceBlockPermissionImpl.setMvccVersion(getMvccVersion());
		resourceBlockPermissionImpl.setResourceBlockPermissionId(getResourceBlockPermissionId());
		resourceBlockPermissionImpl.setResourceBlockId(getResourceBlockId());
		resourceBlockPermissionImpl.setRoleId(getRoleId());
		resourceBlockPermissionImpl.setActionIds(getActionIds());

		resourceBlockPermissionImpl.resetOriginalValues();

		return resourceBlockPermissionImpl;
	}

	@Override
	public int compareTo(ResourceBlockPermission resourceBlockPermission) {
		long primaryKey = resourceBlockPermission.getPrimaryKey();

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

		if (!(obj instanceof ResourceBlockPermission)) {
			return false;
		}

		ResourceBlockPermission resourceBlockPermission = (ResourceBlockPermission)obj;

		long primaryKey = resourceBlockPermission.getPrimaryKey();

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
		ResourceBlockPermissionModelImpl resourceBlockPermissionModelImpl = this;

		resourceBlockPermissionModelImpl._originalResourceBlockId = resourceBlockPermissionModelImpl._resourceBlockId;

		resourceBlockPermissionModelImpl._setOriginalResourceBlockId = false;

		resourceBlockPermissionModelImpl._originalRoleId = resourceBlockPermissionModelImpl._roleId;

		resourceBlockPermissionModelImpl._setOriginalRoleId = false;

		resourceBlockPermissionModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<ResourceBlockPermission> toCacheModel() {
		ResourceBlockPermissionCacheModel resourceBlockPermissionCacheModel = new ResourceBlockPermissionCacheModel();

		resourceBlockPermissionCacheModel.mvccVersion = getMvccVersion();

		resourceBlockPermissionCacheModel.resourceBlockPermissionId = getResourceBlockPermissionId();

		resourceBlockPermissionCacheModel.resourceBlockId = getResourceBlockId();

		resourceBlockPermissionCacheModel.roleId = getRoleId();

		resourceBlockPermissionCacheModel.actionIds = getActionIds();

		return resourceBlockPermissionCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{mvccVersion=");
		sb.append(getMvccVersion());
		sb.append(", resourceBlockPermissionId=");
		sb.append(getResourceBlockPermissionId());
		sb.append(", resourceBlockId=");
		sb.append(getResourceBlockId());
		sb.append(", roleId=");
		sb.append(getRoleId());
		sb.append(", actionIds=");
		sb.append(getActionIds());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(19);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portal.model.ResourceBlockPermission");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>mvccVersion</column-name><column-value><![CDATA[");
		sb.append(getMvccVersion());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>resourceBlockPermissionId</column-name><column-value><![CDATA[");
		sb.append(getResourceBlockPermissionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>resourceBlockId</column-name><column-value><![CDATA[");
		sb.append(getResourceBlockId());
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

	private static final ClassLoader _classLoader = ResourceBlockPermission.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			ResourceBlockPermission.class
		};
	private long _mvccVersion;
	private long _resourceBlockPermissionId;
	private long _resourceBlockId;
	private long _originalResourceBlockId;
	private boolean _setOriginalResourceBlockId;
	private long _roleId;
	private long _originalRoleId;
	private boolean _setOriginalRoleId;
	private long _actionIds;
	private long _columnBitmask;
	private ResourceBlockPermission _escapedModel;
}