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

package com.liferay.osb.lcs.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.osb.lcs.model.LCSClusterEntryToken;
import com.liferay.osb.lcs.model.LCSClusterEntryTokenModel;
import com.liferay.osb.lcs.model.LCSClusterEntryTokenSoap;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the LCSClusterEntryToken service. Represents a row in the &quot;OSBLCS_LCSClusterEntryToken&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link LCSClusterEntryTokenModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link LCSClusterEntryTokenImpl}.
 * </p>
 *
 * @author Igor Beslic
 * @see LCSClusterEntryTokenImpl
 * @see LCSClusterEntryToken
 * @see LCSClusterEntryTokenModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class LCSClusterEntryTokenModelImpl extends BaseModelImpl<LCSClusterEntryToken>
	implements LCSClusterEntryTokenModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a l c s cluster entry token model instance should use the {@link LCSClusterEntryToken} interface instead.
	 */
	public static final String TABLE_NAME = "OSBLCS_LCSClusterEntryToken";
	public static final Object[][] TABLE_COLUMNS = {
			{ "lcsClusterEntryTokenId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "createDate", Types.TIMESTAMP },
			{ "lcsClusterEntryId", Types.BIGINT },
			{ "content", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("lcsClusterEntryTokenId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("lcsClusterEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("content", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table OSBLCS_LCSClusterEntryToken (lcsClusterEntryTokenId LONG not null primary key,userId LONG,createDate DATE null,lcsClusterEntryId LONG,content VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table OSBLCS_LCSClusterEntryToken";
	public static final String ORDER_BY_JPQL = " ORDER BY lcsClusterEntryToken.lcsClusterEntryTokenId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY OSBLCS_LCSClusterEntryToken.lcsClusterEntryTokenId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.osb.lcs.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.osb.lcs.model.LCSClusterEntryToken"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.osb.lcs.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.osb.lcs.model.LCSClusterEntryToken"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.osb.lcs.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.osb.lcs.model.LCSClusterEntryToken"),
			true);
	public static final long LCSCLUSTERENTRYID_COLUMN_BITMASK = 1L;
	public static final long LCSCLUSTERENTRYTOKENID_COLUMN_BITMASK = 2L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static LCSClusterEntryToken toModel(
		LCSClusterEntryTokenSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		LCSClusterEntryToken model = new LCSClusterEntryTokenImpl();

		model.setLcsClusterEntryTokenId(soapModel.getLcsClusterEntryTokenId());
		model.setUserId(soapModel.getUserId());
		model.setCreateDate(soapModel.getCreateDate());
		model.setLcsClusterEntryId(soapModel.getLcsClusterEntryId());
		model.setContent(soapModel.getContent());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<LCSClusterEntryToken> toModels(
		LCSClusterEntryTokenSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<LCSClusterEntryToken> models = new ArrayList<LCSClusterEntryToken>(soapModels.length);

		for (LCSClusterEntryTokenSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.osb.lcs.service.util.ServiceProps.get(
				"lock.expiration.time.com.liferay.osb.lcs.model.LCSClusterEntryToken"));

	public LCSClusterEntryTokenModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _lcsClusterEntryTokenId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setLcsClusterEntryTokenId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _lcsClusterEntryTokenId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return LCSClusterEntryToken.class;
	}

	@Override
	public String getModelClassName() {
		return LCSClusterEntryToken.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("lcsClusterEntryTokenId", getLcsClusterEntryTokenId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("lcsClusterEntryId", getLcsClusterEntryId());
		attributes.put("content", getContent());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long lcsClusterEntryTokenId = (Long)attributes.get(
				"lcsClusterEntryTokenId");

		if (lcsClusterEntryTokenId != null) {
			setLcsClusterEntryTokenId(lcsClusterEntryTokenId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Long lcsClusterEntryId = (Long)attributes.get("lcsClusterEntryId");

		if (lcsClusterEntryId != null) {
			setLcsClusterEntryId(lcsClusterEntryId);
		}

		String content = (String)attributes.get("content");

		if (content != null) {
			setContent(content);
		}
	}

	@JSON
	@Override
	public long getLcsClusterEntryTokenId() {
		return _lcsClusterEntryTokenId;
	}

	@Override
	public void setLcsClusterEntryTokenId(long lcsClusterEntryTokenId) {
		_lcsClusterEntryTokenId = lcsClusterEntryTokenId;
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
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@JSON
	@Override
	public long getLcsClusterEntryId() {
		return _lcsClusterEntryId;
	}

	@Override
	public void setLcsClusterEntryId(long lcsClusterEntryId) {
		_columnBitmask |= LCSCLUSTERENTRYID_COLUMN_BITMASK;

		if (!_setOriginalLcsClusterEntryId) {
			_setOriginalLcsClusterEntryId = true;

			_originalLcsClusterEntryId = _lcsClusterEntryId;
		}

		_lcsClusterEntryId = lcsClusterEntryId;
	}

	public long getOriginalLcsClusterEntryId() {
		return _originalLcsClusterEntryId;
	}

	@JSON
	@Override
	public String getContent() {
		if (_content == null) {
			return StringPool.BLANK;
		}
		else {
			return _content;
		}
	}

	@Override
	public void setContent(String content) {
		_content = content;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
			LCSClusterEntryToken.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public LCSClusterEntryToken toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (LCSClusterEntryToken)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		LCSClusterEntryTokenImpl lcsClusterEntryTokenImpl = new LCSClusterEntryTokenImpl();

		lcsClusterEntryTokenImpl.setLcsClusterEntryTokenId(getLcsClusterEntryTokenId());
		lcsClusterEntryTokenImpl.setUserId(getUserId());
		lcsClusterEntryTokenImpl.setCreateDate(getCreateDate());
		lcsClusterEntryTokenImpl.setLcsClusterEntryId(getLcsClusterEntryId());
		lcsClusterEntryTokenImpl.setContent(getContent());

		lcsClusterEntryTokenImpl.resetOriginalValues();

		return lcsClusterEntryTokenImpl;
	}

	@Override
	public int compareTo(LCSClusterEntryToken lcsClusterEntryToken) {
		long primaryKey = lcsClusterEntryToken.getPrimaryKey();

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

		if (!(obj instanceof LCSClusterEntryToken)) {
			return false;
		}

		LCSClusterEntryToken lcsClusterEntryToken = (LCSClusterEntryToken)obj;

		long primaryKey = lcsClusterEntryToken.getPrimaryKey();

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
		LCSClusterEntryTokenModelImpl lcsClusterEntryTokenModelImpl = this;

		lcsClusterEntryTokenModelImpl._originalLcsClusterEntryId = lcsClusterEntryTokenModelImpl._lcsClusterEntryId;

		lcsClusterEntryTokenModelImpl._setOriginalLcsClusterEntryId = false;

		lcsClusterEntryTokenModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<LCSClusterEntryToken> toCacheModel() {
		LCSClusterEntryTokenCacheModel lcsClusterEntryTokenCacheModel = new LCSClusterEntryTokenCacheModel();

		lcsClusterEntryTokenCacheModel.lcsClusterEntryTokenId = getLcsClusterEntryTokenId();

		lcsClusterEntryTokenCacheModel.userId = getUserId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			lcsClusterEntryTokenCacheModel.createDate = createDate.getTime();
		}
		else {
			lcsClusterEntryTokenCacheModel.createDate = Long.MIN_VALUE;
		}

		lcsClusterEntryTokenCacheModel.lcsClusterEntryId = getLcsClusterEntryId();

		lcsClusterEntryTokenCacheModel.content = getContent();

		String content = lcsClusterEntryTokenCacheModel.content;

		if ((content != null) && (content.length() == 0)) {
			lcsClusterEntryTokenCacheModel.content = null;
		}

		return lcsClusterEntryTokenCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{lcsClusterEntryTokenId=");
		sb.append(getLcsClusterEntryTokenId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", lcsClusterEntryId=");
		sb.append(getLcsClusterEntryId());
		sb.append(", content=");
		sb.append(getContent());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(19);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.lcs.model.LCSClusterEntryToken");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>lcsClusterEntryTokenId</column-name><column-value><![CDATA[");
		sb.append(getLcsClusterEntryTokenId());
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
			"<column><column-name>lcsClusterEntryId</column-name><column-value><![CDATA[");
		sb.append(getLcsClusterEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>content</column-name><column-value><![CDATA[");
		sb.append(getContent());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = LCSClusterEntryToken.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			LCSClusterEntryToken.class
		};
	private long _lcsClusterEntryTokenId;
	private long _userId;
	private Date _createDate;
	private long _lcsClusterEntryId;
	private long _originalLcsClusterEntryId;
	private boolean _setOriginalLcsClusterEntryId;
	private String _content;
	private long _columnBitmask;
	private LCSClusterEntryToken _escapedModel;
}