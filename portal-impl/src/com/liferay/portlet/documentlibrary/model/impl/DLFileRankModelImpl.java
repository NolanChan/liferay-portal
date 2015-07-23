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

package com.liferay.portlet.documentlibrary.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.User;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;

import com.liferay.portlet.documentlibrary.model.DLFileRank;
import com.liferay.portlet.documentlibrary.model.DLFileRankModel;
import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the DLFileRank service. Represents a row in the &quot;DLFileRank&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.liferay.portlet.documentlibrary.model.DLFileRankModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link DLFileRankImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DLFileRankImpl
 * @see com.liferay.portlet.documentlibrary.model.DLFileRank
 * @see com.liferay.portlet.documentlibrary.model.DLFileRankModel
 * @generated
 */
@ProviderType
public class DLFileRankModelImpl extends BaseModelImpl<DLFileRank>
	implements DLFileRankModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a document library file rank model instance should use the {@link com.liferay.portlet.documentlibrary.model.DLFileRank} interface instead.
	 */
	public static final String TABLE_NAME = "DLFileRank";
	public static final Object[][] TABLE_COLUMNS = {
			{ "fileRankId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "createDate", Types.TIMESTAMP },
			{ "fileEntryId", Types.BIGINT },
			{ "active_", Types.BOOLEAN }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("fileRankId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("fileEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("active_", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE = "create table DLFileRank (fileRankId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,createDate DATE null,fileEntryId LONG,active_ BOOLEAN)";
	public static final String TABLE_SQL_DROP = "drop table DLFileRank";
	public static final String ORDER_BY_JPQL = " ORDER BY dlFileRank.createDate DESC";
	public static final String ORDER_BY_SQL = " ORDER BY DLFileRank.createDate DESC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.entity.cache.enabled.DLFileRank"), true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.finder.cache.enabled.DLFileRank"), true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.column.bitmask.enabled.DLFileRank"), true);
	public static final long ACTIVE_COLUMN_BITMASK = 1L;
	public static final long COMPANYID_COLUMN_BITMASK = 2L;
	public static final long FILEENTRYID_COLUMN_BITMASK = 4L;
	public static final long GROUPID_COLUMN_BITMASK = 8L;
	public static final long USERID_COLUMN_BITMASK = 16L;
	public static final long CREATEDATE_COLUMN_BITMASK = 32L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
				"lock.expiration.time.DLFileRank"));

	public DLFileRankModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _fileRankId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setFileRankId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _fileRankId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return DLFileRank.class;
	}

	@Override
	public String getModelClassName() {
		return DLFileRank.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("fileRankId", getFileRankId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("fileEntryId", getFileEntryId());
		attributes.put("active", getActive());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long fileRankId = (Long)attributes.get("fileRankId");

		if (fileRankId != null) {
			setFileRankId(fileRankId);
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

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Long fileEntryId = (Long)attributes.get("fileEntryId");

		if (fileEntryId != null) {
			setFileEntryId(fileEntryId);
		}

		Boolean active = (Boolean)attributes.get("active");

		if (active != null) {
			setActive(active);
		}
	}

	@Override
	public long getFileRankId() {
		return _fileRankId;
	}

	@Override
	public void setFileRankId(long fileRankId) {
		_fileRankId = fileRankId;
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
		_columnBitmask |= USERID_COLUMN_BITMASK;

		if (!_setOriginalUserId) {
			_setOriginalUserId = true;

			_originalUserId = _userId;
		}

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

	public long getOriginalUserId() {
		return _originalUserId;
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_columnBitmask = -1L;

		_createDate = createDate;
	}

	@Override
	public long getFileEntryId() {
		return _fileEntryId;
	}

	@Override
	public void setFileEntryId(long fileEntryId) {
		_columnBitmask |= FILEENTRYID_COLUMN_BITMASK;

		if (!_setOriginalFileEntryId) {
			_setOriginalFileEntryId = true;

			_originalFileEntryId = _fileEntryId;
		}

		_fileEntryId = fileEntryId;
	}

	public long getOriginalFileEntryId() {
		return _originalFileEntryId;
	}

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

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			DLFileRank.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public DLFileRank toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (DLFileRank)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		DLFileRankImpl dlFileRankImpl = new DLFileRankImpl();

		dlFileRankImpl.setFileRankId(getFileRankId());
		dlFileRankImpl.setGroupId(getGroupId());
		dlFileRankImpl.setCompanyId(getCompanyId());
		dlFileRankImpl.setUserId(getUserId());
		dlFileRankImpl.setCreateDate(getCreateDate());
		dlFileRankImpl.setFileEntryId(getFileEntryId());
		dlFileRankImpl.setActive(getActive());

		dlFileRankImpl.resetOriginalValues();

		return dlFileRankImpl;
	}

	@Override
	public int compareTo(DLFileRank dlFileRank) {
		int value = 0;

		value = DateUtil.compareTo(getCreateDate(), dlFileRank.getCreateDate());

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

		if (!(obj instanceof DLFileRank)) {
			return false;
		}

		DLFileRank dlFileRank = (DLFileRank)obj;

		long primaryKey = dlFileRank.getPrimaryKey();

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
		DLFileRankModelImpl dlFileRankModelImpl = this;

		dlFileRankModelImpl._originalGroupId = dlFileRankModelImpl._groupId;

		dlFileRankModelImpl._setOriginalGroupId = false;

		dlFileRankModelImpl._originalCompanyId = dlFileRankModelImpl._companyId;

		dlFileRankModelImpl._setOriginalCompanyId = false;

		dlFileRankModelImpl._originalUserId = dlFileRankModelImpl._userId;

		dlFileRankModelImpl._setOriginalUserId = false;

		dlFileRankModelImpl._originalFileEntryId = dlFileRankModelImpl._fileEntryId;

		dlFileRankModelImpl._setOriginalFileEntryId = false;

		dlFileRankModelImpl._originalActive = dlFileRankModelImpl._active;

		dlFileRankModelImpl._setOriginalActive = false;

		dlFileRankModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<DLFileRank> toCacheModel() {
		DLFileRankCacheModel dlFileRankCacheModel = new DLFileRankCacheModel();

		dlFileRankCacheModel.fileRankId = getFileRankId();

		dlFileRankCacheModel.groupId = getGroupId();

		dlFileRankCacheModel.companyId = getCompanyId();

		dlFileRankCacheModel.userId = getUserId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			dlFileRankCacheModel.createDate = createDate.getTime();
		}
		else {
			dlFileRankCacheModel.createDate = Long.MIN_VALUE;
		}

		dlFileRankCacheModel.fileEntryId = getFileEntryId();

		dlFileRankCacheModel.active = getActive();

		return dlFileRankCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{fileRankId=");
		sb.append(getFileRankId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", fileEntryId=");
		sb.append(getFileEntryId());
		sb.append(", active=");
		sb.append(getActive());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(25);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portlet.documentlibrary.model.DLFileRank");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>fileRankId</column-name><column-value><![CDATA[");
		sb.append(getFileRankId());
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
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>fileEntryId</column-name><column-value><![CDATA[");
		sb.append(getFileEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>active</column-name><column-value><![CDATA[");
		sb.append(getActive());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = DLFileRank.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			DLFileRank.class
		};
	private long _fileRankId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private long _originalUserId;
	private boolean _setOriginalUserId;
	private Date _createDate;
	private long _fileEntryId;
	private long _originalFileEntryId;
	private boolean _setOriginalFileEntryId;
	private boolean _active;
	private boolean _originalActive;
	private boolean _setOriginalActive;
	private long _columnBitmask;
	private DLFileRank _escapedModel;
}