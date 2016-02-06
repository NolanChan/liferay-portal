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

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserNotificationDelivery;
import com.liferay.portal.model.UserNotificationDeliveryModel;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.io.Serializable;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the UserNotificationDelivery service. Represents a row in the &quot;UserNotificationDelivery&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link UserNotificationDeliveryModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link UserNotificationDeliveryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserNotificationDeliveryImpl
 * @see UserNotificationDelivery
 * @see UserNotificationDeliveryModel
 * @generated
 */
@ProviderType
public class UserNotificationDeliveryModelImpl extends BaseModelImpl<UserNotificationDelivery>
	implements UserNotificationDeliveryModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a user notification delivery model instance should use the {@link UserNotificationDelivery} interface instead.
	 */
	public static final String TABLE_NAME = "UserNotificationDelivery";
	public static final Object[][] TABLE_COLUMNS = {
			{ "mvccVersion", Types.BIGINT },
			{ "userNotificationDeliveryId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "portletId", Types.VARCHAR },
			{ "classNameId", Types.BIGINT },
			{ "notificationType", Types.INTEGER },
			{ "deliveryType", Types.INTEGER },
			{ "deliver", Types.BOOLEAN }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userNotificationDeliveryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("portletId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("notificationType", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("deliveryType", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("deliver", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE = "create table UserNotificationDelivery (mvccVersion LONG default 0 not null,userNotificationDeliveryId LONG not null primary key,companyId LONG,userId LONG,portletId VARCHAR(200) null,classNameId LONG,notificationType INTEGER,deliveryType INTEGER,deliver BOOLEAN)";
	public static final String TABLE_SQL_DROP = "drop table UserNotificationDelivery";
	public static final String ORDER_BY_JPQL = " ORDER BY userNotificationDelivery.userNotificationDeliveryId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY UserNotificationDelivery.userNotificationDeliveryId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.entity.cache.enabled.com.liferay.portal.model.UserNotificationDelivery"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.finder.cache.enabled.com.liferay.portal.model.UserNotificationDelivery"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.column.bitmask.enabled.com.liferay.portal.model.UserNotificationDelivery"),
			true);
	public static final long CLASSNAMEID_COLUMN_BITMASK = 1L;
	public static final long DELIVERYTYPE_COLUMN_BITMASK = 2L;
	public static final long NOTIFICATIONTYPE_COLUMN_BITMASK = 4L;
	public static final long PORTLETID_COLUMN_BITMASK = 8L;
	public static final long USERID_COLUMN_BITMASK = 16L;
	public static final long USERNOTIFICATIONDELIVERYID_COLUMN_BITMASK = 32L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
				"lock.expiration.time.com.liferay.portal.model.UserNotificationDelivery"));

	public UserNotificationDeliveryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _userNotificationDeliveryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setUserNotificationDeliveryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _userNotificationDeliveryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return UserNotificationDelivery.class;
	}

	@Override
	public String getModelClassName() {
		return UserNotificationDelivery.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("userNotificationDeliveryId",
			getUserNotificationDeliveryId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("portletId", getPortletId());
		attributes.put("classNameId", getClassNameId());
		attributes.put("notificationType", getNotificationType());
		attributes.put("deliveryType", getDeliveryType());
		attributes.put("deliver", getDeliver());

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

		Long userNotificationDeliveryId = (Long)attributes.get(
				"userNotificationDeliveryId");

		if (userNotificationDeliveryId != null) {
			setUserNotificationDeliveryId(userNotificationDeliveryId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String portletId = (String)attributes.get("portletId");

		if (portletId != null) {
			setPortletId(portletId);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Integer notificationType = (Integer)attributes.get("notificationType");

		if (notificationType != null) {
			setNotificationType(notificationType);
		}

		Integer deliveryType = (Integer)attributes.get("deliveryType");

		if (deliveryType != null) {
			setDeliveryType(deliveryType);
		}

		Boolean deliver = (Boolean)attributes.get("deliver");

		if (deliver != null) {
			setDeliver(deliver);
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
	public long getUserNotificationDeliveryId() {
		return _userNotificationDeliveryId;
	}

	@Override
	public void setUserNotificationDeliveryId(long userNotificationDeliveryId) {
		_userNotificationDeliveryId = userNotificationDeliveryId;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
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
	public String getPortletId() {
		if (_portletId == null) {
			return StringPool.BLANK;
		}
		else {
			return _portletId;
		}
	}

	@Override
	public void setPortletId(String portletId) {
		_columnBitmask |= PORTLETID_COLUMN_BITMASK;

		if (_originalPortletId == null) {
			_originalPortletId = _portletId;
		}

		_portletId = portletId;
	}

	public String getOriginalPortletId() {
		return GetterUtil.getString(_originalPortletId);
	}

	@Override
	public String getClassName() {
		if (getClassNameId() <= 0) {
			return StringPool.BLANK;
		}

		return PortalUtil.getClassName(getClassNameId());
	}

	@Override
	public void setClassName(String className) {
		long classNameId = 0;

		if (Validator.isNotNull(className)) {
			classNameId = PortalUtil.getClassNameId(className);
		}

		setClassNameId(classNameId);
	}

	@Override
	public long getClassNameId() {
		return _classNameId;
	}

	@Override
	public void setClassNameId(long classNameId) {
		_columnBitmask |= CLASSNAMEID_COLUMN_BITMASK;

		if (!_setOriginalClassNameId) {
			_setOriginalClassNameId = true;

			_originalClassNameId = _classNameId;
		}

		_classNameId = classNameId;
	}

	public long getOriginalClassNameId() {
		return _originalClassNameId;
	}

	@Override
	public int getNotificationType() {
		return _notificationType;
	}

	@Override
	public void setNotificationType(int notificationType) {
		_columnBitmask |= NOTIFICATIONTYPE_COLUMN_BITMASK;

		if (!_setOriginalNotificationType) {
			_setOriginalNotificationType = true;

			_originalNotificationType = _notificationType;
		}

		_notificationType = notificationType;
	}

	public int getOriginalNotificationType() {
		return _originalNotificationType;
	}

	@Override
	public int getDeliveryType() {
		return _deliveryType;
	}

	@Override
	public void setDeliveryType(int deliveryType) {
		_columnBitmask |= DELIVERYTYPE_COLUMN_BITMASK;

		if (!_setOriginalDeliveryType) {
			_setOriginalDeliveryType = true;

			_originalDeliveryType = _deliveryType;
		}

		_deliveryType = deliveryType;
	}

	public int getOriginalDeliveryType() {
		return _originalDeliveryType;
	}

	@Override
	public boolean getDeliver() {
		return _deliver;
	}

	@Override
	public boolean isDeliver() {
		return _deliver;
	}

	@Override
	public void setDeliver(boolean deliver) {
		_deliver = deliver;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			UserNotificationDelivery.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public UserNotificationDelivery toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (UserNotificationDelivery)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		UserNotificationDeliveryImpl userNotificationDeliveryImpl = new UserNotificationDeliveryImpl();

		userNotificationDeliveryImpl.setMvccVersion(getMvccVersion());
		userNotificationDeliveryImpl.setUserNotificationDeliveryId(getUserNotificationDeliveryId());
		userNotificationDeliveryImpl.setCompanyId(getCompanyId());
		userNotificationDeliveryImpl.setUserId(getUserId());
		userNotificationDeliveryImpl.setPortletId(getPortletId());
		userNotificationDeliveryImpl.setClassNameId(getClassNameId());
		userNotificationDeliveryImpl.setNotificationType(getNotificationType());
		userNotificationDeliveryImpl.setDeliveryType(getDeliveryType());
		userNotificationDeliveryImpl.setDeliver(getDeliver());

		userNotificationDeliveryImpl.resetOriginalValues();

		return userNotificationDeliveryImpl;
	}

	@Override
	public int compareTo(UserNotificationDelivery userNotificationDelivery) {
		long primaryKey = userNotificationDelivery.getPrimaryKey();

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

		if (!(obj instanceof UserNotificationDelivery)) {
			return false;
		}

		UserNotificationDelivery userNotificationDelivery = (UserNotificationDelivery)obj;

		long primaryKey = userNotificationDelivery.getPrimaryKey();

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
		UserNotificationDeliveryModelImpl userNotificationDeliveryModelImpl = this;

		userNotificationDeliveryModelImpl._originalUserId = userNotificationDeliveryModelImpl._userId;

		userNotificationDeliveryModelImpl._setOriginalUserId = false;

		userNotificationDeliveryModelImpl._originalPortletId = userNotificationDeliveryModelImpl._portletId;

		userNotificationDeliveryModelImpl._originalClassNameId = userNotificationDeliveryModelImpl._classNameId;

		userNotificationDeliveryModelImpl._setOriginalClassNameId = false;

		userNotificationDeliveryModelImpl._originalNotificationType = userNotificationDeliveryModelImpl._notificationType;

		userNotificationDeliveryModelImpl._setOriginalNotificationType = false;

		userNotificationDeliveryModelImpl._originalDeliveryType = userNotificationDeliveryModelImpl._deliveryType;

		userNotificationDeliveryModelImpl._setOriginalDeliveryType = false;

		userNotificationDeliveryModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<UserNotificationDelivery> toCacheModel() {
		UserNotificationDeliveryCacheModel userNotificationDeliveryCacheModel = new UserNotificationDeliveryCacheModel();

		userNotificationDeliveryCacheModel.mvccVersion = getMvccVersion();

		userNotificationDeliveryCacheModel.userNotificationDeliveryId = getUserNotificationDeliveryId();

		userNotificationDeliveryCacheModel.companyId = getCompanyId();

		userNotificationDeliveryCacheModel.userId = getUserId();

		userNotificationDeliveryCacheModel.portletId = getPortletId();

		String portletId = userNotificationDeliveryCacheModel.portletId;

		if ((portletId != null) && (portletId.length() == 0)) {
			userNotificationDeliveryCacheModel.portletId = null;
		}

		userNotificationDeliveryCacheModel.classNameId = getClassNameId();

		userNotificationDeliveryCacheModel.notificationType = getNotificationType();

		userNotificationDeliveryCacheModel.deliveryType = getDeliveryType();

		userNotificationDeliveryCacheModel.deliver = getDeliver();

		return userNotificationDeliveryCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{mvccVersion=");
		sb.append(getMvccVersion());
		sb.append(", userNotificationDeliveryId=");
		sb.append(getUserNotificationDeliveryId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", portletId=");
		sb.append(getPortletId());
		sb.append(", classNameId=");
		sb.append(getClassNameId());
		sb.append(", notificationType=");
		sb.append(getNotificationType());
		sb.append(", deliveryType=");
		sb.append(getDeliveryType());
		sb.append(", deliver=");
		sb.append(getDeliver());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(31);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portal.model.UserNotificationDelivery");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>mvccVersion</column-name><column-value><![CDATA[");
		sb.append(getMvccVersion());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userNotificationDeliveryId</column-name><column-value><![CDATA[");
		sb.append(getUserNotificationDeliveryId());
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
			"<column><column-name>portletId</column-name><column-value><![CDATA[");
		sb.append(getPortletId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classNameId</column-name><column-value><![CDATA[");
		sb.append(getClassNameId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>notificationType</column-name><column-value><![CDATA[");
		sb.append(getNotificationType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>deliveryType</column-name><column-value><![CDATA[");
		sb.append(getDeliveryType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>deliver</column-name><column-value><![CDATA[");
		sb.append(getDeliver());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = UserNotificationDelivery.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			UserNotificationDelivery.class
		};
	private long _mvccVersion;
	private long _userNotificationDeliveryId;
	private long _companyId;
	private long _userId;
	private long _originalUserId;
	private boolean _setOriginalUserId;
	private String _portletId;
	private String _originalPortletId;
	private long _classNameId;
	private long _originalClassNameId;
	private boolean _setOriginalClassNameId;
	private int _notificationType;
	private int _originalNotificationType;
	private boolean _setOriginalNotificationType;
	private int _deliveryType;
	private int _originalDeliveryType;
	private boolean _setOriginalDeliveryType;
	private boolean _deliver;
	private long _columnBitmask;
	private UserNotificationDelivery _escapedModel;
}