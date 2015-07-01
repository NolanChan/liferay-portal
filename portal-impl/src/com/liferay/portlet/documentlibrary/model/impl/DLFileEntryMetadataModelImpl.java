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
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.documentlibrary.model.DLFileEntryMetadata;
import com.liferay.portlet.documentlibrary.model.DLFileEntryMetadataModel;
import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the DLFileEntryMetadata service. Represents a row in the &quot;DLFileEntryMetadata&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link DLFileEntryMetadataModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link DLFileEntryMetadataImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DLFileEntryMetadataImpl
 * @see DLFileEntryMetadata
 * @see DLFileEntryMetadataModel
 * @generated
 */
@ProviderType
public class DLFileEntryMetadataModelImpl extends BaseModelImpl<DLFileEntryMetadata>
	implements DLFileEntryMetadataModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a document library file entry metadata model instance should use the {@link DLFileEntryMetadata} interface instead.
	 */
	public static final String TABLE_NAME = "DLFileEntryMetadata";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "fileEntryMetadataId", Types.BIGINT },
			{ "DDMStorageId", Types.BIGINT },
			{ "DDMStructureId", Types.BIGINT },
			{ "fileEntryId", Types.BIGINT },
			{ "fileVersionId", Types.BIGINT }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("fileEntryMetadataId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("DDMStorageId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("DDMStructureId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("fileEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("fileVersionId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE = "create table DLFileEntryMetadata (uuid_ VARCHAR(75) null,fileEntryMetadataId LONG not null primary key,DDMStorageId LONG,DDMStructureId LONG,fileEntryId LONG,fileVersionId LONG)";
	public static final String TABLE_SQL_DROP = "drop table DLFileEntryMetadata";
	public static final String ORDER_BY_JPQL = " ORDER BY dlFileEntryMetadata.fileEntryMetadataId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY DLFileEntryMetadata.fileEntryMetadataId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.entity.cache.enabled.com.liferay.portlet.documentlibrary.model.DLFileEntryMetadata"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.finder.cache.enabled.com.liferay.portlet.documentlibrary.model.DLFileEntryMetadata"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.column.bitmask.enabled.com.liferay.portlet.documentlibrary.model.DLFileEntryMetadata"),
			true);
	public static final long DDMSTRUCTUREID_COLUMN_BITMASK = 1L;
	public static final long FILEENTRYID_COLUMN_BITMASK = 2L;
	public static final long FILEVERSIONID_COLUMN_BITMASK = 4L;
	public static final long UUID_COLUMN_BITMASK = 8L;
	public static final long FILEENTRYMETADATAID_COLUMN_BITMASK = 16L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
				"lock.expiration.time.com.liferay.portlet.documentlibrary.model.DLFileEntryMetadata"));

	public DLFileEntryMetadataModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _fileEntryMetadataId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setFileEntryMetadataId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _fileEntryMetadataId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return DLFileEntryMetadata.class;
	}

	@Override
	public String getModelClassName() {
		return DLFileEntryMetadata.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("fileEntryMetadataId", getFileEntryMetadataId());
		attributes.put("DDMStorageId", getDDMStorageId());
		attributes.put("DDMStructureId", getDDMStructureId());
		attributes.put("fileEntryId", getFileEntryId());
		attributes.put("fileVersionId", getFileVersionId());

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

		Long fileEntryMetadataId = (Long)attributes.get("fileEntryMetadataId");

		if (fileEntryMetadataId != null) {
			setFileEntryMetadataId(fileEntryMetadataId);
		}

		Long DDMStorageId = (Long)attributes.get("DDMStorageId");

		if (DDMStorageId != null) {
			setDDMStorageId(DDMStorageId);
		}

		Long DDMStructureId = (Long)attributes.get("DDMStructureId");

		if (DDMStructureId != null) {
			setDDMStructureId(DDMStructureId);
		}

		Long fileEntryId = (Long)attributes.get("fileEntryId");

		if (fileEntryId != null) {
			setFileEntryId(fileEntryId);
		}

		Long fileVersionId = (Long)attributes.get("fileVersionId");

		if (fileVersionId != null) {
			setFileVersionId(fileVersionId);
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
	public long getFileEntryMetadataId() {
		return _fileEntryMetadataId;
	}

	@Override
	public void setFileEntryMetadataId(long fileEntryMetadataId) {
		_fileEntryMetadataId = fileEntryMetadataId;
	}

	@Override
	public long getDDMStorageId() {
		return _DDMStorageId;
	}

	@Override
	public void setDDMStorageId(long DDMStorageId) {
		_DDMStorageId = DDMStorageId;
	}

	@Override
	public long getDDMStructureId() {
		return _DDMStructureId;
	}

	@Override
	public void setDDMStructureId(long DDMStructureId) {
		_columnBitmask |= DDMSTRUCTUREID_COLUMN_BITMASK;

		if (!_setOriginalDDMStructureId) {
			_setOriginalDDMStructureId = true;

			_originalDDMStructureId = _DDMStructureId;
		}

		_DDMStructureId = DDMStructureId;
	}

	public long getOriginalDDMStructureId() {
		return _originalDDMStructureId;
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
	public long getFileVersionId() {
		return _fileVersionId;
	}

	@Override
	public void setFileVersionId(long fileVersionId) {
		_columnBitmask |= FILEVERSIONID_COLUMN_BITMASK;

		if (!_setOriginalFileVersionId) {
			_setOriginalFileVersionId = true;

			_originalFileVersionId = _fileVersionId;
		}

		_fileVersionId = fileVersionId;
	}

	public long getOriginalFileVersionId() {
		return _originalFileVersionId;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
			DLFileEntryMetadata.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public DLFileEntryMetadata toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (DLFileEntryMetadata)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		DLFileEntryMetadataImpl dlFileEntryMetadataImpl = new DLFileEntryMetadataImpl();

		dlFileEntryMetadataImpl.setUuid(getUuid());
		dlFileEntryMetadataImpl.setFileEntryMetadataId(getFileEntryMetadataId());
		dlFileEntryMetadataImpl.setDDMStorageId(getDDMStorageId());
		dlFileEntryMetadataImpl.setDDMStructureId(getDDMStructureId());
		dlFileEntryMetadataImpl.setFileEntryId(getFileEntryId());
		dlFileEntryMetadataImpl.setFileVersionId(getFileVersionId());

		dlFileEntryMetadataImpl.resetOriginalValues();

		return dlFileEntryMetadataImpl;
	}

	@Override
	public int compareTo(DLFileEntryMetadata dlFileEntryMetadata) {
		long primaryKey = dlFileEntryMetadata.getPrimaryKey();

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

		if (!(obj instanceof DLFileEntryMetadata)) {
			return false;
		}

		DLFileEntryMetadata dlFileEntryMetadata = (DLFileEntryMetadata)obj;

		long primaryKey = dlFileEntryMetadata.getPrimaryKey();

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
		DLFileEntryMetadataModelImpl dlFileEntryMetadataModelImpl = this;

		dlFileEntryMetadataModelImpl._originalUuid = dlFileEntryMetadataModelImpl._uuid;

		dlFileEntryMetadataModelImpl._originalDDMStructureId = dlFileEntryMetadataModelImpl._DDMStructureId;

		dlFileEntryMetadataModelImpl._setOriginalDDMStructureId = false;

		dlFileEntryMetadataModelImpl._originalFileEntryId = dlFileEntryMetadataModelImpl._fileEntryId;

		dlFileEntryMetadataModelImpl._setOriginalFileEntryId = false;

		dlFileEntryMetadataModelImpl._originalFileVersionId = dlFileEntryMetadataModelImpl._fileVersionId;

		dlFileEntryMetadataModelImpl._setOriginalFileVersionId = false;

		dlFileEntryMetadataModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<DLFileEntryMetadata> toCacheModel() {
		DLFileEntryMetadataCacheModel dlFileEntryMetadataCacheModel = new DLFileEntryMetadataCacheModel();

		dlFileEntryMetadataCacheModel.uuid = getUuid();

		String uuid = dlFileEntryMetadataCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			dlFileEntryMetadataCacheModel.uuid = null;
		}

		dlFileEntryMetadataCacheModel.fileEntryMetadataId = getFileEntryMetadataId();

		dlFileEntryMetadataCacheModel.DDMStorageId = getDDMStorageId();

		dlFileEntryMetadataCacheModel.DDMStructureId = getDDMStructureId();

		dlFileEntryMetadataCacheModel.fileEntryId = getFileEntryId();

		dlFileEntryMetadataCacheModel.fileVersionId = getFileVersionId();

		return dlFileEntryMetadataCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", fileEntryMetadataId=");
		sb.append(getFileEntryMetadataId());
		sb.append(", DDMStorageId=");
		sb.append(getDDMStorageId());
		sb.append(", DDMStructureId=");
		sb.append(getDDMStructureId());
		sb.append(", fileEntryId=");
		sb.append(getFileEntryId());
		sb.append(", fileVersionId=");
		sb.append(getFileVersionId());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(22);

		sb.append("<model><model-name>");
		sb.append(
			"com.liferay.portlet.documentlibrary.model.DLFileEntryMetadata");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>fileEntryMetadataId</column-name><column-value><![CDATA[");
		sb.append(getFileEntryMetadataId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>DDMStorageId</column-name><column-value><![CDATA[");
		sb.append(getDDMStorageId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>DDMStructureId</column-name><column-value><![CDATA[");
		sb.append(getDDMStructureId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>fileEntryId</column-name><column-value><![CDATA[");
		sb.append(getFileEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>fileVersionId</column-name><column-value><![CDATA[");
		sb.append(getFileVersionId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = DLFileEntryMetadata.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			DLFileEntryMetadata.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _fileEntryMetadataId;
	private long _DDMStorageId;
	private long _DDMStructureId;
	private long _originalDDMStructureId;
	private boolean _setOriginalDDMStructureId;
	private long _fileEntryId;
	private long _originalFileEntryId;
	private boolean _setOriginalFileEntryId;
	private long _fileVersionId;
	private long _originalFileVersionId;
	private boolean _setOriginalFileVersionId;
	private long _columnBitmask;
	private DLFileEntryMetadata _escapedModel;
}