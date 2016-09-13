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

import com.liferay.osb.lcs.model.UserLCSMessage;
import com.liferay.osb.lcs.model.UserLCSMessageModel;
import com.liferay.osb.lcs.model.UserLCSMessageSoap;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the UserLCSMessage service. Represents a row in the &quot;OSBLCS_UserLCSMessage&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link UserLCSMessageModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link UserLCSMessageImpl}.
 * </p>
 *
 * @author Igor Beslic
 * @see UserLCSMessageImpl
 * @see UserLCSMessage
 * @see UserLCSMessageModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class UserLCSMessageModelImpl extends BaseModelImpl<UserLCSMessage>
	implements UserLCSMessageModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a user l c s message model instance should use the {@link UserLCSMessage} interface instead.
	 */
	public static final String TABLE_NAME = "OSBLCS_UserLCSMessage";
	public static final Object[][] TABLE_COLUMNS = {
			{ "userLcsMessageId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "lcsMessageId", Types.BIGINT },
			{ "hidden_", Types.BOOLEAN },
			{ "read_", Types.BOOLEAN }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("userLcsMessageId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("lcsMessageId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("hidden_", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("read_", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE = "create table OSBLCS_UserLCSMessage (userLcsMessageId LONG not null primary key,userId LONG,lcsMessageId LONG,hidden_ BOOLEAN,read_ BOOLEAN)";
	public static final String TABLE_SQL_DROP = "drop table OSBLCS_UserLCSMessage";
	public static final String ORDER_BY_JPQL = " ORDER BY userLCSMessage.lcsMessageId DESC, userLCSMessage.read DESC";
	public static final String ORDER_BY_SQL = " ORDER BY OSBLCS_UserLCSMessage.lcsMessageId DESC, OSBLCS_UserLCSMessage.read_ DESC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.osb.lcs.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.osb.lcs.model.UserLCSMessage"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.osb.lcs.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.osb.lcs.model.UserLCSMessage"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.osb.lcs.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.osb.lcs.model.UserLCSMessage"),
			true);
	public static final long HIDDEN_COLUMN_BITMASK = 1L;
	public static final long LCSMESSAGEID_COLUMN_BITMASK = 2L;
	public static final long READ_COLUMN_BITMASK = 4L;
	public static final long USERID_COLUMN_BITMASK = 8L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static UserLCSMessage toModel(UserLCSMessageSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		UserLCSMessage model = new UserLCSMessageImpl();

		model.setUserLcsMessageId(soapModel.getUserLcsMessageId());
		model.setUserId(soapModel.getUserId());
		model.setLcsMessageId(soapModel.getLcsMessageId());
		model.setHidden(soapModel.getHidden());
		model.setRead(soapModel.getRead());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<UserLCSMessage> toModels(UserLCSMessageSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<UserLCSMessage> models = new ArrayList<UserLCSMessage>(soapModels.length);

		for (UserLCSMessageSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.osb.lcs.service.util.ServiceProps.get(
				"lock.expiration.time.com.liferay.osb.lcs.model.UserLCSMessage"));

	public UserLCSMessageModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _userLcsMessageId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setUserLcsMessageId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _userLcsMessageId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return UserLCSMessage.class;
	}

	@Override
	public String getModelClassName() {
		return UserLCSMessage.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("userLcsMessageId", getUserLcsMessageId());
		attributes.put("userId", getUserId());
		attributes.put("lcsMessageId", getLcsMessageId());
		attributes.put("hidden", getHidden());
		attributes.put("read", getRead());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long userLcsMessageId = (Long)attributes.get("userLcsMessageId");

		if (userLcsMessageId != null) {
			setUserLcsMessageId(userLcsMessageId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long lcsMessageId = (Long)attributes.get("lcsMessageId");

		if (lcsMessageId != null) {
			setLcsMessageId(lcsMessageId);
		}

		Boolean hidden = (Boolean)attributes.get("hidden");

		if (hidden != null) {
			setHidden(hidden);
		}

		Boolean read = (Boolean)attributes.get("read");

		if (read != null) {
			setRead(read);
		}
	}

	@JSON
	@Override
	public long getUserLcsMessageId() {
		return _userLcsMessageId;
	}

	@Override
	public void setUserLcsMessageId(long userLcsMessageId) {
		_userLcsMessageId = userLcsMessageId;
	}

	@JSON
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

	@JSON
	@Override
	public long getLcsMessageId() {
		return _lcsMessageId;
	}

	@Override
	public void setLcsMessageId(long lcsMessageId) {
		_columnBitmask = -1L;

		if (!_setOriginalLcsMessageId) {
			_setOriginalLcsMessageId = true;

			_originalLcsMessageId = _lcsMessageId;
		}

		_lcsMessageId = lcsMessageId;
	}

	public long getOriginalLcsMessageId() {
		return _originalLcsMessageId;
	}

	@JSON
	@Override
	public boolean getHidden() {
		return _hidden;
	}

	@JSON
	@Override
	public boolean isHidden() {
		return _hidden;
	}

	@Override
	public void setHidden(boolean hidden) {
		_columnBitmask |= HIDDEN_COLUMN_BITMASK;

		if (!_setOriginalHidden) {
			_setOriginalHidden = true;

			_originalHidden = _hidden;
		}

		_hidden = hidden;
	}

	public boolean getOriginalHidden() {
		return _originalHidden;
	}

	@JSON
	@Override
	public boolean getRead() {
		return _read;
	}

	@JSON
	@Override
	public boolean isRead() {
		return _read;
	}

	@Override
	public void setRead(boolean read) {
		_columnBitmask = -1L;

		if (!_setOriginalRead) {
			_setOriginalRead = true;

			_originalRead = _read;
		}

		_read = read;
	}

	public boolean getOriginalRead() {
		return _originalRead;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
			UserLCSMessage.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public UserLCSMessage toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (UserLCSMessage)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		UserLCSMessageImpl userLCSMessageImpl = new UserLCSMessageImpl();

		userLCSMessageImpl.setUserLcsMessageId(getUserLcsMessageId());
		userLCSMessageImpl.setUserId(getUserId());
		userLCSMessageImpl.setLcsMessageId(getLcsMessageId());
		userLCSMessageImpl.setHidden(getHidden());
		userLCSMessageImpl.setRead(getRead());

		userLCSMessageImpl.resetOriginalValues();

		return userLCSMessageImpl;
	}

	@Override
	public int compareTo(UserLCSMessage userLCSMessage) {
		int value = 0;

		if (getLcsMessageId() < userLCSMessage.getLcsMessageId()) {
			value = -1;
		}
		else if (getLcsMessageId() > userLCSMessage.getLcsMessageId()) {
			value = 1;
		}
		else {
			value = 0;
		}

		value = value * -1;

		if (value != 0) {
			return value;
		}

		value = Boolean.compare(getRead(), userLCSMessage.getRead());

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

		if (!(obj instanceof UserLCSMessage)) {
			return false;
		}

		UserLCSMessage userLCSMessage = (UserLCSMessage)obj;

		long primaryKey = userLCSMessage.getPrimaryKey();

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
		UserLCSMessageModelImpl userLCSMessageModelImpl = this;

		userLCSMessageModelImpl._originalUserId = userLCSMessageModelImpl._userId;

		userLCSMessageModelImpl._setOriginalUserId = false;

		userLCSMessageModelImpl._originalLcsMessageId = userLCSMessageModelImpl._lcsMessageId;

		userLCSMessageModelImpl._setOriginalLcsMessageId = false;

		userLCSMessageModelImpl._originalHidden = userLCSMessageModelImpl._hidden;

		userLCSMessageModelImpl._setOriginalHidden = false;

		userLCSMessageModelImpl._originalRead = userLCSMessageModelImpl._read;

		userLCSMessageModelImpl._setOriginalRead = false;

		userLCSMessageModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<UserLCSMessage> toCacheModel() {
		UserLCSMessageCacheModel userLCSMessageCacheModel = new UserLCSMessageCacheModel();

		userLCSMessageCacheModel.userLcsMessageId = getUserLcsMessageId();

		userLCSMessageCacheModel.userId = getUserId();

		userLCSMessageCacheModel.lcsMessageId = getLcsMessageId();

		userLCSMessageCacheModel.hidden = getHidden();

		userLCSMessageCacheModel.read = getRead();

		return userLCSMessageCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{userLcsMessageId=");
		sb.append(getUserLcsMessageId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", lcsMessageId=");
		sb.append(getLcsMessageId());
		sb.append(", hidden=");
		sb.append(getHidden());
		sb.append(", read=");
		sb.append(getRead());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(19);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.lcs.model.UserLCSMessage");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>userLcsMessageId</column-name><column-value><![CDATA[");
		sb.append(getUserLcsMessageId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>lcsMessageId</column-name><column-value><![CDATA[");
		sb.append(getLcsMessageId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>hidden</column-name><column-value><![CDATA[");
		sb.append(getHidden());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>read</column-name><column-value><![CDATA[");
		sb.append(getRead());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = UserLCSMessage.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			UserLCSMessage.class
		};
	private long _userLcsMessageId;
	private long _userId;
	private long _originalUserId;
	private boolean _setOriginalUserId;
	private long _lcsMessageId;
	private long _originalLcsMessageId;
	private boolean _setOriginalLcsMessageId;
	private boolean _hidden;
	private boolean _originalHidden;
	private boolean _setOriginalHidden;
	private boolean _read;
	private boolean _originalRead;
	private boolean _setOriginalRead;
	private long _columnBitmask;
	private UserLCSMessage _escapedModel;
}