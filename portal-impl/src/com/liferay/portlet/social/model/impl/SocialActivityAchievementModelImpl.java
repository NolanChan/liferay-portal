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

package com.liferay.portlet.social.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

import com.liferay.social.kernel.model.SocialActivityAchievement;
import com.liferay.social.kernel.model.SocialActivityAchievementModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the SocialActivityAchievement service. Represents a row in the &quot;SocialActivityAchievement&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link SocialActivityAchievementModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SocialActivityAchievementImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SocialActivityAchievementImpl
 * @see SocialActivityAchievement
 * @see SocialActivityAchievementModel
 * @generated
 */
@ProviderType
public class SocialActivityAchievementModelImpl extends BaseModelImpl<SocialActivityAchievement>
	implements SocialActivityAchievementModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a social activity achievement model instance should use the {@link SocialActivityAchievement} interface instead.
	 */
	public static final String TABLE_NAME = "SocialActivityAchievement";
	public static final Object[][] TABLE_COLUMNS = {
			{ "activityAchievementId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "createDate", Types.BIGINT },
			{ "name", Types.VARCHAR },
			{ "firstInGroup", Types.BOOLEAN }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("activityAchievementId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("firstInGroup", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE = "create table SocialActivityAchievement (activityAchievementId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,createDate LONG,name VARCHAR(75) null,firstInGroup BOOLEAN)";
	public static final String TABLE_SQL_DROP = "drop table SocialActivityAchievement";
	public static final String ORDER_BY_JPQL = " ORDER BY socialActivityAchievement.activityAchievementId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY SocialActivityAchievement.activityAchievementId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.entity.cache.enabled.com.liferay.social.kernel.model.SocialActivityAchievement"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.finder.cache.enabled.com.liferay.social.kernel.model.SocialActivityAchievement"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.column.bitmask.enabled.com.liferay.social.kernel.model.SocialActivityAchievement"),
			true);
	public static final long FIRSTINGROUP_COLUMN_BITMASK = 1L;
	public static final long GROUPID_COLUMN_BITMASK = 2L;
	public static final long NAME_COLUMN_BITMASK = 4L;
	public static final long USERID_COLUMN_BITMASK = 8L;
	public static final long ACTIVITYACHIEVEMENTID_COLUMN_BITMASK = 16L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
				"lock.expiration.time.com.liferay.social.kernel.model.SocialActivityAchievement"));

	public SocialActivityAchievementModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _activityAchievementId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setActivityAchievementId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _activityAchievementId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return SocialActivityAchievement.class;
	}

	@Override
	public String getModelClassName() {
		return SocialActivityAchievement.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("activityAchievementId", getActivityAchievementId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("name", getName());
		attributes.put("firstInGroup", getFirstInGroup());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long activityAchievementId = (Long)attributes.get(
				"activityAchievementId");

		if (activityAchievementId != null) {
			setActivityAchievementId(activityAchievementId);
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

		Long createDate = (Long)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		Boolean firstInGroup = (Boolean)attributes.get("firstInGroup");

		if (firstInGroup != null) {
			setFirstInGroup(firstInGroup);
		}
	}

	@Override
	public long getActivityAchievementId() {
		return _activityAchievementId;
	}

	@Override
	public void setActivityAchievementId(long activityAchievementId) {
		_activityAchievementId = activityAchievementId;
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
	public long getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(long createDate) {
		_createDate = createDate;
	}

	@Override
	public String getName() {
		if (_name == null) {
			return StringPool.BLANK;
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		_columnBitmask |= NAME_COLUMN_BITMASK;

		if (_originalName == null) {
			_originalName = _name;
		}

		_name = name;
	}

	public String getOriginalName() {
		return GetterUtil.getString(_originalName);
	}

	@Override
	public boolean getFirstInGroup() {
		return _firstInGroup;
	}

	@Override
	public boolean isFirstInGroup() {
		return _firstInGroup;
	}

	@Override
	public void setFirstInGroup(boolean firstInGroup) {
		_columnBitmask |= FIRSTINGROUP_COLUMN_BITMASK;

		if (!_setOriginalFirstInGroup) {
			_setOriginalFirstInGroup = true;

			_originalFirstInGroup = _firstInGroup;
		}

		_firstInGroup = firstInGroup;
	}

	public boolean getOriginalFirstInGroup() {
		return _originalFirstInGroup;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			SocialActivityAchievement.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public SocialActivityAchievement toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (SocialActivityAchievement)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		SocialActivityAchievementImpl socialActivityAchievementImpl = new SocialActivityAchievementImpl();

		socialActivityAchievementImpl.setActivityAchievementId(getActivityAchievementId());
		socialActivityAchievementImpl.setGroupId(getGroupId());
		socialActivityAchievementImpl.setCompanyId(getCompanyId());
		socialActivityAchievementImpl.setUserId(getUserId());
		socialActivityAchievementImpl.setCreateDate(getCreateDate());
		socialActivityAchievementImpl.setName(getName());
		socialActivityAchievementImpl.setFirstInGroup(getFirstInGroup());

		socialActivityAchievementImpl.resetOriginalValues();

		return socialActivityAchievementImpl;
	}

	@Override
	public int compareTo(SocialActivityAchievement socialActivityAchievement) {
		long primaryKey = socialActivityAchievement.getPrimaryKey();

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

		if (!(obj instanceof SocialActivityAchievement)) {
			return false;
		}

		SocialActivityAchievement socialActivityAchievement = (SocialActivityAchievement)obj;

		long primaryKey = socialActivityAchievement.getPrimaryKey();

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
		SocialActivityAchievementModelImpl socialActivityAchievementModelImpl = this;

		socialActivityAchievementModelImpl._originalGroupId = socialActivityAchievementModelImpl._groupId;

		socialActivityAchievementModelImpl._setOriginalGroupId = false;

		socialActivityAchievementModelImpl._originalUserId = socialActivityAchievementModelImpl._userId;

		socialActivityAchievementModelImpl._setOriginalUserId = false;

		socialActivityAchievementModelImpl._originalName = socialActivityAchievementModelImpl._name;

		socialActivityAchievementModelImpl._originalFirstInGroup = socialActivityAchievementModelImpl._firstInGroup;

		socialActivityAchievementModelImpl._setOriginalFirstInGroup = false;

		socialActivityAchievementModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<SocialActivityAchievement> toCacheModel() {
		SocialActivityAchievementCacheModel socialActivityAchievementCacheModel = new SocialActivityAchievementCacheModel();

		socialActivityAchievementCacheModel.activityAchievementId = getActivityAchievementId();

		socialActivityAchievementCacheModel.groupId = getGroupId();

		socialActivityAchievementCacheModel.companyId = getCompanyId();

		socialActivityAchievementCacheModel.userId = getUserId();

		socialActivityAchievementCacheModel.createDate = getCreateDate();

		socialActivityAchievementCacheModel.name = getName();

		String name = socialActivityAchievementCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			socialActivityAchievementCacheModel.name = null;
		}

		socialActivityAchievementCacheModel.firstInGroup = getFirstInGroup();

		return socialActivityAchievementCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{activityAchievementId=");
		sb.append(getActivityAchievementId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", firstInGroup=");
		sb.append(getFirstInGroup());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(25);

		sb.append("<model><model-name>");
		sb.append("com.liferay.social.kernel.model.SocialActivityAchievement");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>activityAchievementId</column-name><column-value><![CDATA[");
		sb.append(getActivityAchievementId());
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
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>firstInGroup</column-name><column-value><![CDATA[");
		sb.append(getFirstInGroup());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = SocialActivityAchievement.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			SocialActivityAchievement.class
		};
	private long _activityAchievementId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _userId;
	private long _originalUserId;
	private boolean _setOriginalUserId;
	private long _createDate;
	private String _name;
	private String _originalName;
	private boolean _firstInGroup;
	private boolean _originalFirstInGroup;
	private boolean _setOriginalFirstInGroup;
	private long _columnBitmask;
	private SocialActivityAchievement _escapedModel;
}