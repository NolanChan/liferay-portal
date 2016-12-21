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

package com.liferay.sync.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;

import com.liferay.sync.model.SyncDLFileVersionDiff;
import com.liferay.sync.model.SyncDLFileVersionDiffModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the SyncDLFileVersionDiff service. Represents a row in the &quot;SyncDLFileVersionDiff&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link SyncDLFileVersionDiffModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SyncDLFileVersionDiffImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SyncDLFileVersionDiffImpl
 * @see SyncDLFileVersionDiff
 * @see SyncDLFileVersionDiffModel
 * @generated
 */
@ProviderType
public class SyncDLFileVersionDiffModelImpl extends BaseModelImpl<SyncDLFileVersionDiff>
	implements SyncDLFileVersionDiffModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a sync dl file version diff model instance should use the {@link SyncDLFileVersionDiff} interface instead.
	 */
	public static final String TABLE_NAME = "SyncDLFileVersionDiff";
	public static final Object[][] TABLE_COLUMNS = {
			{ "syncDLFileVersionDiffId", Types.BIGINT },
			{ "fileEntryId", Types.BIGINT },
			{ "sourceFileVersionId", Types.BIGINT },
			{ "targetFileVersionId", Types.BIGINT },
			{ "dataFileEntryId", Types.BIGINT },
			{ "size_", Types.BIGINT },
			{ "expirationDate", Types.TIMESTAMP }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("syncDLFileVersionDiffId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("fileEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("sourceFileVersionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("targetFileVersionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("dataFileEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("size_", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("expirationDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE = "create table SyncDLFileVersionDiff (syncDLFileVersionDiffId LONG not null primary key,fileEntryId LONG,sourceFileVersionId LONG,targetFileVersionId LONG,dataFileEntryId LONG,size_ LONG,expirationDate DATE null)";
	public static final String TABLE_SQL_DROP = "drop table SyncDLFileVersionDiff";
	public static final String ORDER_BY_JPQL = " ORDER BY syncDLFileVersionDiff.syncDLFileVersionDiffId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY SyncDLFileVersionDiff.syncDLFileVersionDiffId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.sync.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.sync.model.SyncDLFileVersionDiff"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.sync.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.sync.model.SyncDLFileVersionDiff"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.sync.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.sync.model.SyncDLFileVersionDiff"),
			true);
	public static final long EXPIRATIONDATE_COLUMN_BITMASK = 1L;
	public static final long FILEENTRYID_COLUMN_BITMASK = 2L;
	public static final long SOURCEFILEVERSIONID_COLUMN_BITMASK = 4L;
	public static final long TARGETFILEVERSIONID_COLUMN_BITMASK = 8L;
	public static final long SYNCDLFILEVERSIONDIFFID_COLUMN_BITMASK = 16L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.sync.service.util.ServiceProps.get(
				"lock.expiration.time.com.liferay.sync.model.SyncDLFileVersionDiff"));

	public SyncDLFileVersionDiffModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _syncDLFileVersionDiffId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setSyncDLFileVersionDiffId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _syncDLFileVersionDiffId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return SyncDLFileVersionDiff.class;
	}

	@Override
	public String getModelClassName() {
		return SyncDLFileVersionDiff.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("syncDLFileVersionDiffId", getSyncDLFileVersionDiffId());
		attributes.put("fileEntryId", getFileEntryId());
		attributes.put("sourceFileVersionId", getSourceFileVersionId());
		attributes.put("targetFileVersionId", getTargetFileVersionId());
		attributes.put("dataFileEntryId", getDataFileEntryId());
		attributes.put("size", getSize());
		attributes.put("expirationDate", getExpirationDate());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long syncDLFileVersionDiffId = (Long)attributes.get(
				"syncDLFileVersionDiffId");

		if (syncDLFileVersionDiffId != null) {
			setSyncDLFileVersionDiffId(syncDLFileVersionDiffId);
		}

		Long fileEntryId = (Long)attributes.get("fileEntryId");

		if (fileEntryId != null) {
			setFileEntryId(fileEntryId);
		}

		Long sourceFileVersionId = (Long)attributes.get("sourceFileVersionId");

		if (sourceFileVersionId != null) {
			setSourceFileVersionId(sourceFileVersionId);
		}

		Long targetFileVersionId = (Long)attributes.get("targetFileVersionId");

		if (targetFileVersionId != null) {
			setTargetFileVersionId(targetFileVersionId);
		}

		Long dataFileEntryId = (Long)attributes.get("dataFileEntryId");

		if (dataFileEntryId != null) {
			setDataFileEntryId(dataFileEntryId);
		}

		Long size = (Long)attributes.get("size");

		if (size != null) {
			setSize(size);
		}

		Date expirationDate = (Date)attributes.get("expirationDate");

		if (expirationDate != null) {
			setExpirationDate(expirationDate);
		}
	}

	@Override
	public long getSyncDLFileVersionDiffId() {
		return _syncDLFileVersionDiffId;
	}

	@Override
	public void setSyncDLFileVersionDiffId(long syncDLFileVersionDiffId) {
		_syncDLFileVersionDiffId = syncDLFileVersionDiffId;
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
	public long getSourceFileVersionId() {
		return _sourceFileVersionId;
	}

	@Override
	public void setSourceFileVersionId(long sourceFileVersionId) {
		_columnBitmask |= SOURCEFILEVERSIONID_COLUMN_BITMASK;

		if (!_setOriginalSourceFileVersionId) {
			_setOriginalSourceFileVersionId = true;

			_originalSourceFileVersionId = _sourceFileVersionId;
		}

		_sourceFileVersionId = sourceFileVersionId;
	}

	public long getOriginalSourceFileVersionId() {
		return _originalSourceFileVersionId;
	}

	@Override
	public long getTargetFileVersionId() {
		return _targetFileVersionId;
	}

	@Override
	public void setTargetFileVersionId(long targetFileVersionId) {
		_columnBitmask |= TARGETFILEVERSIONID_COLUMN_BITMASK;

		if (!_setOriginalTargetFileVersionId) {
			_setOriginalTargetFileVersionId = true;

			_originalTargetFileVersionId = _targetFileVersionId;
		}

		_targetFileVersionId = targetFileVersionId;
	}

	public long getOriginalTargetFileVersionId() {
		return _originalTargetFileVersionId;
	}

	@Override
	public long getDataFileEntryId() {
		return _dataFileEntryId;
	}

	@Override
	public void setDataFileEntryId(long dataFileEntryId) {
		_dataFileEntryId = dataFileEntryId;
	}

	@Override
	public long getSize() {
		return _size;
	}

	@Override
	public void setSize(long size) {
		_size = size;
	}

	@Override
	public Date getExpirationDate() {
		return _expirationDate;
	}

	@Override
	public void setExpirationDate(Date expirationDate) {
		_columnBitmask |= EXPIRATIONDATE_COLUMN_BITMASK;

		if (_originalExpirationDate == null) {
			_originalExpirationDate = _expirationDate;
		}

		_expirationDate = expirationDate;
	}

	public Date getOriginalExpirationDate() {
		return _originalExpirationDate;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
			SyncDLFileVersionDiff.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public SyncDLFileVersionDiff toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (SyncDLFileVersionDiff)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		SyncDLFileVersionDiffImpl syncDLFileVersionDiffImpl = new SyncDLFileVersionDiffImpl();

		syncDLFileVersionDiffImpl.setSyncDLFileVersionDiffId(getSyncDLFileVersionDiffId());
		syncDLFileVersionDiffImpl.setFileEntryId(getFileEntryId());
		syncDLFileVersionDiffImpl.setSourceFileVersionId(getSourceFileVersionId());
		syncDLFileVersionDiffImpl.setTargetFileVersionId(getTargetFileVersionId());
		syncDLFileVersionDiffImpl.setDataFileEntryId(getDataFileEntryId());
		syncDLFileVersionDiffImpl.setSize(getSize());
		syncDLFileVersionDiffImpl.setExpirationDate(getExpirationDate());

		syncDLFileVersionDiffImpl.resetOriginalValues();

		return syncDLFileVersionDiffImpl;
	}

	@Override
	public int compareTo(SyncDLFileVersionDiff syncDLFileVersionDiff) {
		long primaryKey = syncDLFileVersionDiff.getPrimaryKey();

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

		if (!(obj instanceof SyncDLFileVersionDiff)) {
			return false;
		}

		SyncDLFileVersionDiff syncDLFileVersionDiff = (SyncDLFileVersionDiff)obj;

		long primaryKey = syncDLFileVersionDiff.getPrimaryKey();

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
		SyncDLFileVersionDiffModelImpl syncDLFileVersionDiffModelImpl = this;

		syncDLFileVersionDiffModelImpl._originalFileEntryId = syncDLFileVersionDiffModelImpl._fileEntryId;

		syncDLFileVersionDiffModelImpl._setOriginalFileEntryId = false;

		syncDLFileVersionDiffModelImpl._originalSourceFileVersionId = syncDLFileVersionDiffModelImpl._sourceFileVersionId;

		syncDLFileVersionDiffModelImpl._setOriginalSourceFileVersionId = false;

		syncDLFileVersionDiffModelImpl._originalTargetFileVersionId = syncDLFileVersionDiffModelImpl._targetFileVersionId;

		syncDLFileVersionDiffModelImpl._setOriginalTargetFileVersionId = false;

		syncDLFileVersionDiffModelImpl._originalExpirationDate = syncDLFileVersionDiffModelImpl._expirationDate;

		syncDLFileVersionDiffModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<SyncDLFileVersionDiff> toCacheModel() {
		SyncDLFileVersionDiffCacheModel syncDLFileVersionDiffCacheModel = new SyncDLFileVersionDiffCacheModel();

		syncDLFileVersionDiffCacheModel.syncDLFileVersionDiffId = getSyncDLFileVersionDiffId();

		syncDLFileVersionDiffCacheModel.fileEntryId = getFileEntryId();

		syncDLFileVersionDiffCacheModel.sourceFileVersionId = getSourceFileVersionId();

		syncDLFileVersionDiffCacheModel.targetFileVersionId = getTargetFileVersionId();

		syncDLFileVersionDiffCacheModel.dataFileEntryId = getDataFileEntryId();

		syncDLFileVersionDiffCacheModel.size = getSize();

		Date expirationDate = getExpirationDate();

		if (expirationDate != null) {
			syncDLFileVersionDiffCacheModel.expirationDate = expirationDate.getTime();
		}
		else {
			syncDLFileVersionDiffCacheModel.expirationDate = Long.MIN_VALUE;
		}

		return syncDLFileVersionDiffCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{syncDLFileVersionDiffId=");
		sb.append(getSyncDLFileVersionDiffId());
		sb.append(", fileEntryId=");
		sb.append(getFileEntryId());
		sb.append(", sourceFileVersionId=");
		sb.append(getSourceFileVersionId());
		sb.append(", targetFileVersionId=");
		sb.append(getTargetFileVersionId());
		sb.append(", dataFileEntryId=");
		sb.append(getDataFileEntryId());
		sb.append(", size=");
		sb.append(getSize());
		sb.append(", expirationDate=");
		sb.append(getExpirationDate());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(25);

		sb.append("<model><model-name>");
		sb.append("com.liferay.sync.model.SyncDLFileVersionDiff");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>syncDLFileVersionDiffId</column-name><column-value><![CDATA[");
		sb.append(getSyncDLFileVersionDiffId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>fileEntryId</column-name><column-value><![CDATA[");
		sb.append(getFileEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>sourceFileVersionId</column-name><column-value><![CDATA[");
		sb.append(getSourceFileVersionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>targetFileVersionId</column-name><column-value><![CDATA[");
		sb.append(getTargetFileVersionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>dataFileEntryId</column-name><column-value><![CDATA[");
		sb.append(getDataFileEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>size</column-name><column-value><![CDATA[");
		sb.append(getSize());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>expirationDate</column-name><column-value><![CDATA[");
		sb.append(getExpirationDate());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = SyncDLFileVersionDiff.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			SyncDLFileVersionDiff.class
		};
	private long _syncDLFileVersionDiffId;
	private long _fileEntryId;
	private long _originalFileEntryId;
	private boolean _setOriginalFileEntryId;
	private long _sourceFileVersionId;
	private long _originalSourceFileVersionId;
	private boolean _setOriginalSourceFileVersionId;
	private long _targetFileVersionId;
	private long _originalTargetFileVersionId;
	private boolean _setOriginalTargetFileVersionId;
	private long _dataFileEntryId;
	private long _size;
	private Date _expirationDate;
	private Date _originalExpirationDate;
	private long _columnBitmask;
	private SyncDLFileVersionDiff _escapedModel;
}