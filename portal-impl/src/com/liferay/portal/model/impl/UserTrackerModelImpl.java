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
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserTracker;
import com.liferay.portal.model.UserTrackerModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the UserTracker service. Represents a row in the &quot;UserTracker&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link UserTrackerModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link UserTrackerImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserTrackerImpl
 * @see UserTracker
 * @see UserTrackerModel
 * @generated
 */
@ProviderType
public class UserTrackerModelImpl extends BaseModelImpl<UserTracker>
	implements UserTrackerModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a user tracker model instance should use the {@link UserTracker} interface instead.
	 */
	public static final String TABLE_NAME = "UserTracker";
	public static final Object[][] TABLE_COLUMNS = {
			{ "mvccVersion", Types.BIGINT },
			{ "userTrackerId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "sessionId", Types.VARCHAR },
			{ "remoteAddr", Types.VARCHAR },
			{ "remoteHost", Types.VARCHAR },
			{ "userAgent", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userTrackerId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("sessionId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("remoteAddr", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("remoteHost", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("userAgent", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table UserTracker (mvccVersion LONG default 0,userTrackerId LONG not null primary key,companyId LONG,userId LONG,modifiedDate DATE null,sessionId VARCHAR(200) null,remoteAddr VARCHAR(75) null,remoteHost VARCHAR(75) null,userAgent VARCHAR(200) null)";
	public static final String TABLE_SQL_DROP = "drop table UserTracker";
	public static final String ORDER_BY_JPQL = " ORDER BY userTracker.userTrackerId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY UserTracker.userTrackerId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.entity.cache.enabled.com.liferay.portal.model.UserTracker"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.finder.cache.enabled.com.liferay.portal.model.UserTracker"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.column.bitmask.enabled.com.liferay.portal.model.UserTracker"),
			true);
	public static final long COMPANYID_COLUMN_BITMASK = 1L;
	public static final long SESSIONID_COLUMN_BITMASK = 2L;
	public static final long USERID_COLUMN_BITMASK = 4L;
	public static final long USERTRACKERID_COLUMN_BITMASK = 8L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
				"lock.expiration.time.com.liferay.portal.model.UserTracker"));

	public UserTrackerModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _userTrackerId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setUserTrackerId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _userTrackerId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return UserTracker.class;
	}

	@Override
	public String getModelClassName() {
		return UserTracker.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("userTrackerId", getUserTrackerId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("sessionId", getSessionId());
		attributes.put("remoteAddr", getRemoteAddr());
		attributes.put("remoteHost", getRemoteHost());
		attributes.put("userAgent", getUserAgent());

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

		Long userTrackerId = (Long)attributes.get("userTrackerId");

		if (userTrackerId != null) {
			setUserTrackerId(userTrackerId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String sessionId = (String)attributes.get("sessionId");

		if (sessionId != null) {
			setSessionId(sessionId);
		}

		String remoteAddr = (String)attributes.get("remoteAddr");

		if (remoteAddr != null) {
			setRemoteAddr(remoteAddr);
		}

		String remoteHost = (String)attributes.get("remoteHost");

		if (remoteHost != null) {
			setRemoteHost(remoteHost);
		}

		String userAgent = (String)attributes.get("userAgent");

		if (userAgent != null) {
			setUserAgent(userAgent);
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
	public long getUserTrackerId() {
		return _userTrackerId;
	}

	@Override
	public void setUserTrackerId(long userTrackerId) {
		_userTrackerId = userTrackerId;
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
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	@Override
	public String getSessionId() {
		if (_sessionId == null) {
			return StringPool.BLANK;
		}
		else {
			return _sessionId;
		}
	}

	@Override
	public void setSessionId(String sessionId) {
		_columnBitmask |= SESSIONID_COLUMN_BITMASK;

		if (_originalSessionId == null) {
			_originalSessionId = _sessionId;
		}

		_sessionId = sessionId;
	}

	public String getOriginalSessionId() {
		return GetterUtil.getString(_originalSessionId);
	}

	@Override
	public String getRemoteAddr() {
		if (_remoteAddr == null) {
			return StringPool.BLANK;
		}
		else {
			return _remoteAddr;
		}
	}

	@Override
	public void setRemoteAddr(String remoteAddr) {
		_remoteAddr = remoteAddr;
	}

	@Override
	public String getRemoteHost() {
		if (_remoteHost == null) {
			return StringPool.BLANK;
		}
		else {
			return _remoteHost;
		}
	}

	@Override
	public void setRemoteHost(String remoteHost) {
		_remoteHost = remoteHost;
	}

	@Override
	public String getUserAgent() {
		if (_userAgent == null) {
			return StringPool.BLANK;
		}
		else {
			return _userAgent;
		}
	}

	@Override
	public void setUserAgent(String userAgent) {
		_userAgent = userAgent;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			UserTracker.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public UserTracker toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (UserTracker)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		UserTrackerImpl userTrackerImpl = new UserTrackerImpl();

		userTrackerImpl.setMvccVersion(getMvccVersion());
		userTrackerImpl.setUserTrackerId(getUserTrackerId());
		userTrackerImpl.setCompanyId(getCompanyId());
		userTrackerImpl.setUserId(getUserId());
		userTrackerImpl.setModifiedDate(getModifiedDate());
		userTrackerImpl.setSessionId(getSessionId());
		userTrackerImpl.setRemoteAddr(getRemoteAddr());
		userTrackerImpl.setRemoteHost(getRemoteHost());
		userTrackerImpl.setUserAgent(getUserAgent());

		userTrackerImpl.resetOriginalValues();

		return userTrackerImpl;
	}

	@Override
	public int compareTo(UserTracker userTracker) {
		long primaryKey = userTracker.getPrimaryKey();

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

		if (!(obj instanceof UserTracker)) {
			return false;
		}

		UserTracker userTracker = (UserTracker)obj;

		long primaryKey = userTracker.getPrimaryKey();

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
		UserTrackerModelImpl userTrackerModelImpl = this;

		userTrackerModelImpl._originalCompanyId = userTrackerModelImpl._companyId;

		userTrackerModelImpl._setOriginalCompanyId = false;

		userTrackerModelImpl._originalUserId = userTrackerModelImpl._userId;

		userTrackerModelImpl._setOriginalUserId = false;

		userTrackerModelImpl._originalSessionId = userTrackerModelImpl._sessionId;

		userTrackerModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<UserTracker> toCacheModel() {
		UserTrackerCacheModel userTrackerCacheModel = new UserTrackerCacheModel();

		userTrackerCacheModel.mvccVersion = getMvccVersion();

		userTrackerCacheModel.userTrackerId = getUserTrackerId();

		userTrackerCacheModel.companyId = getCompanyId();

		userTrackerCacheModel.userId = getUserId();

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			userTrackerCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			userTrackerCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		userTrackerCacheModel.sessionId = getSessionId();

		String sessionId = userTrackerCacheModel.sessionId;

		if ((sessionId != null) && (sessionId.length() == 0)) {
			userTrackerCacheModel.sessionId = null;
		}

		userTrackerCacheModel.remoteAddr = getRemoteAddr();

		String remoteAddr = userTrackerCacheModel.remoteAddr;

		if ((remoteAddr != null) && (remoteAddr.length() == 0)) {
			userTrackerCacheModel.remoteAddr = null;
		}

		userTrackerCacheModel.remoteHost = getRemoteHost();

		String remoteHost = userTrackerCacheModel.remoteHost;

		if ((remoteHost != null) && (remoteHost.length() == 0)) {
			userTrackerCacheModel.remoteHost = null;
		}

		userTrackerCacheModel.userAgent = getUserAgent();

		String userAgent = userTrackerCacheModel.userAgent;

		if ((userAgent != null) && (userAgent.length() == 0)) {
			userTrackerCacheModel.userAgent = null;
		}

		return userTrackerCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{mvccVersion=");
		sb.append(getMvccVersion());
		sb.append(", userTrackerId=");
		sb.append(getUserTrackerId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", sessionId=");
		sb.append(getSessionId());
		sb.append(", remoteAddr=");
		sb.append(getRemoteAddr());
		sb.append(", remoteHost=");
		sb.append(getRemoteHost());
		sb.append(", userAgent=");
		sb.append(getUserAgent());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(31);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portal.model.UserTracker");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>mvccVersion</column-name><column-value><![CDATA[");
		sb.append(getMvccVersion());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userTrackerId</column-name><column-value><![CDATA[");
		sb.append(getUserTrackerId());
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
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>sessionId</column-name><column-value><![CDATA[");
		sb.append(getSessionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>remoteAddr</column-name><column-value><![CDATA[");
		sb.append(getRemoteAddr());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>remoteHost</column-name><column-value><![CDATA[");
		sb.append(getRemoteHost());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userAgent</column-name><column-value><![CDATA[");
		sb.append(getUserAgent());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = UserTracker.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			UserTracker.class
		};
	private long _mvccVersion;
	private long _userTrackerId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private long _originalUserId;
	private boolean _setOriginalUserId;
	private Date _modifiedDate;
	private String _sessionId;
	private String _originalSessionId;
	private String _remoteAddr;
	private String _remoteHost;
	private String _userAgent;
	private long _columnBitmask;
	private UserTracker _escapedModel;
}