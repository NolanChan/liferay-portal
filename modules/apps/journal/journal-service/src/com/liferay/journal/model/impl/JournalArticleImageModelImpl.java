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

package com.liferay.journal.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.journal.model.JournalArticleImage;
import com.liferay.journal.model.JournalArticleImageModel;

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
 * The base model implementation for the JournalArticleImage service. Represents a row in the &quot;JournalArticleImage&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link JournalArticleImageModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link JournalArticleImageImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JournalArticleImageImpl
 * @see JournalArticleImage
 * @see JournalArticleImageModel
 * @generated
 */
@ProviderType
public class JournalArticleImageModelImpl extends BaseModelImpl<JournalArticleImage>
	implements JournalArticleImageModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a journal article image model instance should use the {@link JournalArticleImage} interface instead.
	 */
	public static final String TABLE_NAME = "JournalArticleImage";
	public static final Object[][] TABLE_COLUMNS = {
			{ "articleImageId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "articleId", Types.VARCHAR },
			{ "version", Types.DOUBLE },
			{ "elInstanceId", Types.VARCHAR },
			{ "elName", Types.VARCHAR },
			{ "languageId", Types.VARCHAR },
			{ "tempImage", Types.BOOLEAN }
		};
	public static final String TABLE_SQL_CREATE = "create table JournalArticleImage (articleImageId LONG not null primary key,groupId LONG,articleId VARCHAR(75) null,version DOUBLE,elInstanceId VARCHAR(75) null,elName VARCHAR(75) null,languageId VARCHAR(75) null,tempImage BOOLEAN)";
	public static final String TABLE_SQL_DROP = "drop table JournalArticleImage";
	public static final String ORDER_BY_JPQL = " ORDER BY journalArticleImage.articleImageId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY JournalArticleImage.articleImageId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.journal.model.JournalArticleImage"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.journal.model.JournalArticleImage"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.journal.model.JournalArticleImage"),
			true);
	public static final long ARTICLEID_COLUMN_BITMASK = 1L;
	public static final long ELINSTANCEID_COLUMN_BITMASK = 2L;
	public static final long ELNAME_COLUMN_BITMASK = 4L;
	public static final long GROUPID_COLUMN_BITMASK = 8L;
	public static final long LANGUAGEID_COLUMN_BITMASK = 16L;
	public static final long TEMPIMAGE_COLUMN_BITMASK = 32L;
	public static final long VERSION_COLUMN_BITMASK = 64L;
	public static final long ARTICLEIMAGEID_COLUMN_BITMASK = 128L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.journal.model.JournalArticleImage"));

	public JournalArticleImageModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _articleImageId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setArticleImageId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _articleImageId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return JournalArticleImage.class;
	}

	@Override
	public String getModelClassName() {
		return JournalArticleImage.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("articleImageId", getArticleImageId());
		attributes.put("groupId", getGroupId());
		attributes.put("articleId", getArticleId());
		attributes.put("version", getVersion());
		attributes.put("elInstanceId", getElInstanceId());
		attributes.put("elName", getElName());
		attributes.put("languageId", getLanguageId());
		attributes.put("tempImage", getTempImage());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long articleImageId = (Long)attributes.get("articleImageId");

		if (articleImageId != null) {
			setArticleImageId(articleImageId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		String articleId = (String)attributes.get("articleId");

		if (articleId != null) {
			setArticleId(articleId);
		}

		Double version = (Double)attributes.get("version");

		if (version != null) {
			setVersion(version);
		}

		String elInstanceId = (String)attributes.get("elInstanceId");

		if (elInstanceId != null) {
			setElInstanceId(elInstanceId);
		}

		String elName = (String)attributes.get("elName");

		if (elName != null) {
			setElName(elName);
		}

		String languageId = (String)attributes.get("languageId");

		if (languageId != null) {
			setLanguageId(languageId);
		}

		Boolean tempImage = (Boolean)attributes.get("tempImage");

		if (tempImage != null) {
			setTempImage(tempImage);
		}
	}

	@Override
	public long getArticleImageId() {
		return _articleImageId;
	}

	@Override
	public void setArticleImageId(long articleImageId) {
		_articleImageId = articleImageId;
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
	public String getArticleId() {
		if (_articleId == null) {
			return StringPool.BLANK;
		}
		else {
			return _articleId;
		}
	}

	@Override
	public void setArticleId(String articleId) {
		_columnBitmask |= ARTICLEID_COLUMN_BITMASK;

		if (_originalArticleId == null) {
			_originalArticleId = _articleId;
		}

		_articleId = articleId;
	}

	public String getOriginalArticleId() {
		return GetterUtil.getString(_originalArticleId);
	}

	@Override
	public double getVersion() {
		return _version;
	}

	@Override
	public void setVersion(double version) {
		_columnBitmask |= VERSION_COLUMN_BITMASK;

		if (!_setOriginalVersion) {
			_setOriginalVersion = true;

			_originalVersion = _version;
		}

		_version = version;
	}

	public double getOriginalVersion() {
		return _originalVersion;
	}

	@Override
	public String getElInstanceId() {
		if (_elInstanceId == null) {
			return StringPool.BLANK;
		}
		else {
			return _elInstanceId;
		}
	}

	@Override
	public void setElInstanceId(String elInstanceId) {
		_columnBitmask |= ELINSTANCEID_COLUMN_BITMASK;

		if (_originalElInstanceId == null) {
			_originalElInstanceId = _elInstanceId;
		}

		_elInstanceId = elInstanceId;
	}

	public String getOriginalElInstanceId() {
		return GetterUtil.getString(_originalElInstanceId);
	}

	@Override
	public String getElName() {
		if (_elName == null) {
			return StringPool.BLANK;
		}
		else {
			return _elName;
		}
	}

	@Override
	public void setElName(String elName) {
		_columnBitmask |= ELNAME_COLUMN_BITMASK;

		if (_originalElName == null) {
			_originalElName = _elName;
		}

		_elName = elName;
	}

	public String getOriginalElName() {
		return GetterUtil.getString(_originalElName);
	}

	@Override
	public String getLanguageId() {
		if (_languageId == null) {
			return StringPool.BLANK;
		}
		else {
			return _languageId;
		}
	}

	@Override
	public void setLanguageId(String languageId) {
		_columnBitmask |= LANGUAGEID_COLUMN_BITMASK;

		if (_originalLanguageId == null) {
			_originalLanguageId = _languageId;
		}

		_languageId = languageId;
	}

	public String getOriginalLanguageId() {
		return GetterUtil.getString(_originalLanguageId);
	}

	@Override
	public boolean getTempImage() {
		return _tempImage;
	}

	@Override
	public boolean isTempImage() {
		return _tempImage;
	}

	@Override
	public void setTempImage(boolean tempImage) {
		_columnBitmask |= TEMPIMAGE_COLUMN_BITMASK;

		if (!_setOriginalTempImage) {
			_setOriginalTempImage = true;

			_originalTempImage = _tempImage;
		}

		_tempImage = tempImage;
	}

	public boolean getOriginalTempImage() {
		return _originalTempImage;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
			JournalArticleImage.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public JournalArticleImage toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (JournalArticleImage)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		JournalArticleImageImpl journalArticleImageImpl = new JournalArticleImageImpl();

		journalArticleImageImpl.setArticleImageId(getArticleImageId());
		journalArticleImageImpl.setGroupId(getGroupId());
		journalArticleImageImpl.setArticleId(getArticleId());
		journalArticleImageImpl.setVersion(getVersion());
		journalArticleImageImpl.setElInstanceId(getElInstanceId());
		journalArticleImageImpl.setElName(getElName());
		journalArticleImageImpl.setLanguageId(getLanguageId());
		journalArticleImageImpl.setTempImage(getTempImage());

		journalArticleImageImpl.resetOriginalValues();

		return journalArticleImageImpl;
	}

	@Override
	public int compareTo(JournalArticleImage journalArticleImage) {
		long primaryKey = journalArticleImage.getPrimaryKey();

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

		if (!(obj instanceof JournalArticleImage)) {
			return false;
		}

		JournalArticleImage journalArticleImage = (JournalArticleImage)obj;

		long primaryKey = journalArticleImage.getPrimaryKey();

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
		JournalArticleImageModelImpl journalArticleImageModelImpl = this;

		journalArticleImageModelImpl._originalGroupId = journalArticleImageModelImpl._groupId;

		journalArticleImageModelImpl._setOriginalGroupId = false;

		journalArticleImageModelImpl._originalArticleId = journalArticleImageModelImpl._articleId;

		journalArticleImageModelImpl._originalVersion = journalArticleImageModelImpl._version;

		journalArticleImageModelImpl._setOriginalVersion = false;

		journalArticleImageModelImpl._originalElInstanceId = journalArticleImageModelImpl._elInstanceId;

		journalArticleImageModelImpl._originalElName = journalArticleImageModelImpl._elName;

		journalArticleImageModelImpl._originalLanguageId = journalArticleImageModelImpl._languageId;

		journalArticleImageModelImpl._originalTempImage = journalArticleImageModelImpl._tempImage;

		journalArticleImageModelImpl._setOriginalTempImage = false;

		journalArticleImageModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<JournalArticleImage> toCacheModel() {
		JournalArticleImageCacheModel journalArticleImageCacheModel = new JournalArticleImageCacheModel();

		journalArticleImageCacheModel.articleImageId = getArticleImageId();

		journalArticleImageCacheModel.groupId = getGroupId();

		journalArticleImageCacheModel.articleId = getArticleId();

		String articleId = journalArticleImageCacheModel.articleId;

		if ((articleId != null) && (articleId.length() == 0)) {
			journalArticleImageCacheModel.articleId = null;
		}

		journalArticleImageCacheModel.version = getVersion();

		journalArticleImageCacheModel.elInstanceId = getElInstanceId();

		String elInstanceId = journalArticleImageCacheModel.elInstanceId;

		if ((elInstanceId != null) && (elInstanceId.length() == 0)) {
			journalArticleImageCacheModel.elInstanceId = null;
		}

		journalArticleImageCacheModel.elName = getElName();

		String elName = journalArticleImageCacheModel.elName;

		if ((elName != null) && (elName.length() == 0)) {
			journalArticleImageCacheModel.elName = null;
		}

		journalArticleImageCacheModel.languageId = getLanguageId();

		String languageId = journalArticleImageCacheModel.languageId;

		if ((languageId != null) && (languageId.length() == 0)) {
			journalArticleImageCacheModel.languageId = null;
		}

		journalArticleImageCacheModel.tempImage = getTempImage();

		return journalArticleImageCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{articleImageId=");
		sb.append(getArticleImageId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", articleId=");
		sb.append(getArticleId());
		sb.append(", version=");
		sb.append(getVersion());
		sb.append(", elInstanceId=");
		sb.append(getElInstanceId());
		sb.append(", elName=");
		sb.append(getElName());
		sb.append(", languageId=");
		sb.append(getLanguageId());
		sb.append(", tempImage=");
		sb.append(getTempImage());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(28);

		sb.append("<model><model-name>");
		sb.append("com.liferay.journal.model.JournalArticleImage");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>articleImageId</column-name><column-value><![CDATA[");
		sb.append(getArticleImageId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>articleId</column-name><column-value><![CDATA[");
		sb.append(getArticleId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>version</column-name><column-value><![CDATA[");
		sb.append(getVersion());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>elInstanceId</column-name><column-value><![CDATA[");
		sb.append(getElInstanceId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>elName</column-name><column-value><![CDATA[");
		sb.append(getElName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>languageId</column-name><column-value><![CDATA[");
		sb.append(getLanguageId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>tempImage</column-name><column-value><![CDATA[");
		sb.append(getTempImage());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = JournalArticleImage.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			JournalArticleImage.class
		};
	private long _articleImageId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private String _articleId;
	private String _originalArticleId;
	private double _version;
	private double _originalVersion;
	private boolean _setOriginalVersion;
	private String _elInstanceId;
	private String _originalElInstanceId;
	private String _elName;
	private String _originalElName;
	private String _languageId;
	private String _originalLanguageId;
	private boolean _tempImage;
	private boolean _originalTempImage;
	private boolean _setOriginalTempImage;
	private long _columnBitmask;
	private JournalArticleImage _escapedModel;
}