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

import com.liferay.portlet.documentlibrary.model.DLContent;
import com.liferay.portlet.documentlibrary.model.DLContentDataBlobModel;
import com.liferay.portlet.documentlibrary.model.DLContentModel;
import com.liferay.portlet.documentlibrary.service.DLContentLocalServiceUtil;
import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.sql.Blob;
import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the DLContent service. Represents a row in the &quot;DLContent&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.liferay.portlet.documentlibrary.model.DLContentModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link DLContentImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DLContentImpl
 * @see com.liferay.portlet.documentlibrary.model.DLContent
 * @see com.liferay.portlet.documentlibrary.model.DLContentModel
 * @generated
 */
@ProviderType
public class DLContentModelImpl extends BaseModelImpl<DLContent>
	implements DLContentModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a document library content model instance should use the {@link com.liferay.portlet.documentlibrary.model.DLContent} interface instead.
	 */
	public static final String TABLE_NAME = "DLContent";
	public static final Object[][] TABLE_COLUMNS = {
			{ "contentId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "repositoryId", Types.BIGINT },
			{ "path_", Types.VARCHAR },
			{ "version", Types.VARCHAR },
			{ "data_", Types.BLOB },
			{ "size_", Types.BIGINT }
		};
	public static final String TABLE_SQL_CREATE = "create table DLContent (contentId LONG not null primary key,groupId LONG,companyId LONG,repositoryId LONG,path_ VARCHAR(255) null,version VARCHAR(75) null,data_ BLOB,size_ LONG)";
	public static final String TABLE_SQL_DROP = "drop table DLContent";
	public static final String ORDER_BY_JPQL = " ORDER BY dlContent.version DESC";
	public static final String ORDER_BY_SQL = " ORDER BY DLContent.version DESC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.entity.cache.enabled.com.liferay.portlet.documentlibrary.model.DLContent"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.finder.cache.enabled.com.liferay.portlet.documentlibrary.model.DLContent"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.column.bitmask.enabled.com.liferay.portlet.documentlibrary.model.DLContent"),
			true);
	public static long COMPANYID_COLUMN_BITMASK = 1L;
	public static long PATH_COLUMN_BITMASK = 2L;
	public static long REPOSITORYID_COLUMN_BITMASK = 4L;
	public static long VERSION_COLUMN_BITMASK = 8L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
				"lock.expiration.time.com.liferay.portlet.documentlibrary.model.DLContent"));

	public DLContentModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _contentId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setContentId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _contentId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return DLContent.class;
	}

	@Override
	public String getModelClassName() {
		return DLContent.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("contentId", getContentId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("repositoryId", getRepositoryId());
		attributes.put("path", getPath());
		attributes.put("version", getVersion());
		attributes.put("data", getData());
		attributes.put("size", getSize());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long contentId = (Long)attributes.get("contentId");

		if (contentId != null) {
			setContentId(contentId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long repositoryId = (Long)attributes.get("repositoryId");

		if (repositoryId != null) {
			setRepositoryId(repositoryId);
		}

		String path = (String)attributes.get("path");

		if (path != null) {
			setPath(path);
		}

		String version = (String)attributes.get("version");

		if (version != null) {
			setVersion(version);
		}

		Blob data = (Blob)attributes.get("data");

		if (data != null) {
			setData(data);
		}

		Long size = (Long)attributes.get("size");

		if (size != null) {
			setSize(size);
		}
	}

	@Override
	public long getContentId() {
		return _contentId;
	}

	@Override
	public void setContentId(long contentId) {
		_contentId = contentId;
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
	public long getRepositoryId() {
		return _repositoryId;
	}

	@Override
	public void setRepositoryId(long repositoryId) {
		_columnBitmask |= REPOSITORYID_COLUMN_BITMASK;

		if (!_setOriginalRepositoryId) {
			_setOriginalRepositoryId = true;

			_originalRepositoryId = _repositoryId;
		}

		_repositoryId = repositoryId;
	}

	public long getOriginalRepositoryId() {
		return _originalRepositoryId;
	}

	@Override
	public String getPath() {
		if (_path == null) {
			return StringPool.BLANK;
		}
		else {
			return _path;
		}
	}

	@Override
	public void setPath(String path) {
		_columnBitmask |= PATH_COLUMN_BITMASK;

		if (_originalPath == null) {
			_originalPath = _path;
		}

		_path = path;
	}

	public String getOriginalPath() {
		return GetterUtil.getString(_originalPath);
	}

	@Override
	public String getVersion() {
		if (_version == null) {
			return StringPool.BLANK;
		}
		else {
			return _version;
		}
	}

	@Override
	public void setVersion(String version) {
		_columnBitmask = -1L;

		if (_originalVersion == null) {
			_originalVersion = _version;
		}

		_version = version;
	}

	public String getOriginalVersion() {
		return GetterUtil.getString(_originalVersion);
	}

	@Override
	public Blob getData() {
		if (_dataBlobModel == null) {
			try {
				_dataBlobModel = DLContentLocalServiceUtil.getDataBlobModel(getPrimaryKey());
			}
			catch (Exception e) {
			}
		}

		Blob blob = null;

		if (_dataBlobModel != null) {
			blob = _dataBlobModel.getDataBlob();
		}

		return blob;
	}

	@Override
	public void setData(Blob data) {
		if (_dataBlobModel == null) {
			_dataBlobModel = new DLContentDataBlobModel(getPrimaryKey(), data);
		}
		else {
			_dataBlobModel.setDataBlob(data);
		}
	}

	@Override
	public long getSize() {
		return _size;
	}

	@Override
	public void setSize(long size) {
		_size = size;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			DLContent.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public DLContent toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (DLContent)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		DLContentImpl dlContentImpl = new DLContentImpl();

		dlContentImpl.setContentId(getContentId());
		dlContentImpl.setGroupId(getGroupId());
		dlContentImpl.setCompanyId(getCompanyId());
		dlContentImpl.setRepositoryId(getRepositoryId());
		dlContentImpl.setPath(getPath());
		dlContentImpl.setVersion(getVersion());
		dlContentImpl.setSize(getSize());

		dlContentImpl.resetOriginalValues();

		return dlContentImpl;
	}

	@Override
	public int compareTo(DLContent dlContent) {
		int value = 0;

		value = getVersion().compareTo(dlContent.getVersion());

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

		if (!(obj instanceof DLContent)) {
			return false;
		}

		DLContent dlContent = (DLContent)obj;

		long primaryKey = dlContent.getPrimaryKey();

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
		DLContentModelImpl dlContentModelImpl = this;

		dlContentModelImpl._originalCompanyId = dlContentModelImpl._companyId;

		dlContentModelImpl._setOriginalCompanyId = false;

		dlContentModelImpl._originalRepositoryId = dlContentModelImpl._repositoryId;

		dlContentModelImpl._setOriginalRepositoryId = false;

		dlContentModelImpl._originalPath = dlContentModelImpl._path;

		dlContentModelImpl._originalVersion = dlContentModelImpl._version;

		dlContentModelImpl._dataBlobModel = null;

		dlContentModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<DLContent> toCacheModel() {
		DLContentCacheModel dlContentCacheModel = new DLContentCacheModel();

		dlContentCacheModel.contentId = getContentId();

		dlContentCacheModel.groupId = getGroupId();

		dlContentCacheModel.companyId = getCompanyId();

		dlContentCacheModel.repositoryId = getRepositoryId();

		dlContentCacheModel.path = getPath();

		String path = dlContentCacheModel.path;

		if ((path != null) && (path.length() == 0)) {
			dlContentCacheModel.path = null;
		}

		dlContentCacheModel.version = getVersion();

		String version = dlContentCacheModel.version;

		if ((version != null) && (version.length() == 0)) {
			dlContentCacheModel.version = null;
		}

		dlContentCacheModel.size = getSize();

		return dlContentCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{contentId=");
		sb.append(getContentId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", repositoryId=");
		sb.append(getRepositoryId());
		sb.append(", path=");
		sb.append(getPath());
		sb.append(", version=");
		sb.append(getVersion());
		sb.append(", size=");
		sb.append(getSize());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(28);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portlet.documentlibrary.model.DLContent");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>contentId</column-name><column-value><![CDATA[");
		sb.append(getContentId());
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
			"<column><column-name>repositoryId</column-name><column-value><![CDATA[");
		sb.append(getRepositoryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>path</column-name><column-value><![CDATA[");
		sb.append(getPath());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>version</column-name><column-value><![CDATA[");
		sb.append(getVersion());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>size</column-name><column-value><![CDATA[");
		sb.append(getSize());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static ClassLoader _classLoader = DLContent.class.getClassLoader();
	private static Class<?>[] _escapedModelInterfaces = new Class[] {
			DLContent.class
		};
	private long _contentId;
	private long _groupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _repositoryId;
	private long _originalRepositoryId;
	private boolean _setOriginalRepositoryId;
	private String _path;
	private String _originalPath;
	private String _version;
	private String _originalVersion;
	private DLContentDataBlobModel _dataBlobModel;
	private long _size;
	private long _columnBitmask;
	private DLContent _escapedModel;
}