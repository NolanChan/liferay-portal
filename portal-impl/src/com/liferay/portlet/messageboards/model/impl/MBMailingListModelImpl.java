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

package com.liferay.portlet.messageboards.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.User;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;
import com.liferay.portlet.exportimport.lar.StagedModelType;
import com.liferay.portlet.messageboards.model.MBMailingList;
import com.liferay.portlet.messageboards.model.MBMailingListModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the MBMailingList service. Represents a row in the &quot;MBMailingList&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.liferay.portlet.messageboards.model.MBMailingListModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link MBMailingListImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MBMailingListImpl
 * @see com.liferay.portlet.messageboards.model.MBMailingList
 * @see com.liferay.portlet.messageboards.model.MBMailingListModel
 * @generated
 */
@ProviderType
public class MBMailingListModelImpl extends BaseModelImpl<MBMailingList>
	implements MBMailingListModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a message boards mailing list model instance should use the {@link com.liferay.portlet.messageboards.model.MBMailingList} interface instead.
	 */
	public static final String TABLE_NAME = "MBMailingList";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "mailingListId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "categoryId", Types.BIGINT },
			{ "emailAddress", Types.VARCHAR },
			{ "inProtocol", Types.VARCHAR },
			{ "inServerName", Types.VARCHAR },
			{ "inServerPort", Types.INTEGER },
			{ "inUseSSL", Types.BOOLEAN },
			{ "inUserName", Types.VARCHAR },
			{ "inPassword", Types.VARCHAR },
			{ "inReadInterval", Types.INTEGER },
			{ "outEmailAddress", Types.VARCHAR },
			{ "outCustom", Types.BOOLEAN },
			{ "outServerName", Types.VARCHAR },
			{ "outServerPort", Types.INTEGER },
			{ "outUseSSL", Types.BOOLEAN },
			{ "outUserName", Types.VARCHAR },
			{ "outPassword", Types.VARCHAR },
			{ "allowAnonymous", Types.BOOLEAN },
			{ "active_", Types.BOOLEAN }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("mailingListId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("categoryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("emailAddress", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("inProtocol", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("inServerName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("inServerPort", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("inUseSSL", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("inUserName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("inPassword", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("inReadInterval", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("outEmailAddress", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("outCustom", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("outServerName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("outServerPort", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("outUseSSL", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("outUserName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("outPassword", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("allowAnonymous", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("active_", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE = "create table MBMailingList (uuid_ VARCHAR(75) null,mailingListId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,categoryId LONG,emailAddress VARCHAR(75) null,inProtocol VARCHAR(75) null,inServerName VARCHAR(75) null,inServerPort INTEGER,inUseSSL BOOLEAN,inUserName VARCHAR(75) null,inPassword VARCHAR(75) null,inReadInterval INTEGER,outEmailAddress VARCHAR(75) null,outCustom BOOLEAN,outServerName VARCHAR(75) null,outServerPort INTEGER,outUseSSL BOOLEAN,outUserName VARCHAR(75) null,outPassword VARCHAR(75) null,allowAnonymous BOOLEAN,active_ BOOLEAN)";
	public static final String TABLE_SQL_DROP = "drop table MBMailingList";
	public static final String ORDER_BY_JPQL = " ORDER BY mbMailingList.mailingListId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY MBMailingList.mailingListId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.entity.cache.enabled.MBMailingList"), true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.finder.cache.enabled.MBMailingList"), true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.column.bitmask.enabled.MBMailingList"), true);
	public static final long ACTIVE_COLUMN_BITMASK = 1L;
	public static final long CATEGORYID_COLUMN_BITMASK = 2L;
	public static final long COMPANYID_COLUMN_BITMASK = 4L;
	public static final long GROUPID_COLUMN_BITMASK = 8L;
	public static final long UUID_COLUMN_BITMASK = 16L;
	public static final long MAILINGLISTID_COLUMN_BITMASK = 32L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
				"lock.expiration.time.MBMailingList"));

	public MBMailingListModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _mailingListId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setMailingListId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _mailingListId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return MBMailingList.class;
	}

	@Override
	public String getModelClassName() {
		return MBMailingList.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("mailingListId", getMailingListId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("categoryId", getCategoryId());
		attributes.put("emailAddress", getEmailAddress());
		attributes.put("inProtocol", getInProtocol());
		attributes.put("inServerName", getInServerName());
		attributes.put("inServerPort", getInServerPort());
		attributes.put("inUseSSL", getInUseSSL());
		attributes.put("inUserName", getInUserName());
		attributes.put("inPassword", getInPassword());
		attributes.put("inReadInterval", getInReadInterval());
		attributes.put("outEmailAddress", getOutEmailAddress());
		attributes.put("outCustom", getOutCustom());
		attributes.put("outServerName", getOutServerName());
		attributes.put("outServerPort", getOutServerPort());
		attributes.put("outUseSSL", getOutUseSSL());
		attributes.put("outUserName", getOutUserName());
		attributes.put("outPassword", getOutPassword());
		attributes.put("allowAnonymous", getAllowAnonymous());
		attributes.put("active", getActive());

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

		Long mailingListId = (Long)attributes.get("mailingListId");

		if (mailingListId != null) {
			setMailingListId(mailingListId);
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

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long categoryId = (Long)attributes.get("categoryId");

		if (categoryId != null) {
			setCategoryId(categoryId);
		}

		String emailAddress = (String)attributes.get("emailAddress");

		if (emailAddress != null) {
			setEmailAddress(emailAddress);
		}

		String inProtocol = (String)attributes.get("inProtocol");

		if (inProtocol != null) {
			setInProtocol(inProtocol);
		}

		String inServerName = (String)attributes.get("inServerName");

		if (inServerName != null) {
			setInServerName(inServerName);
		}

		Integer inServerPort = (Integer)attributes.get("inServerPort");

		if (inServerPort != null) {
			setInServerPort(inServerPort);
		}

		Boolean inUseSSL = (Boolean)attributes.get("inUseSSL");

		if (inUseSSL != null) {
			setInUseSSL(inUseSSL);
		}

		String inUserName = (String)attributes.get("inUserName");

		if (inUserName != null) {
			setInUserName(inUserName);
		}

		String inPassword = (String)attributes.get("inPassword");

		if (inPassword != null) {
			setInPassword(inPassword);
		}

		Integer inReadInterval = (Integer)attributes.get("inReadInterval");

		if (inReadInterval != null) {
			setInReadInterval(inReadInterval);
		}

		String outEmailAddress = (String)attributes.get("outEmailAddress");

		if (outEmailAddress != null) {
			setOutEmailAddress(outEmailAddress);
		}

		Boolean outCustom = (Boolean)attributes.get("outCustom");

		if (outCustom != null) {
			setOutCustom(outCustom);
		}

		String outServerName = (String)attributes.get("outServerName");

		if (outServerName != null) {
			setOutServerName(outServerName);
		}

		Integer outServerPort = (Integer)attributes.get("outServerPort");

		if (outServerPort != null) {
			setOutServerPort(outServerPort);
		}

		Boolean outUseSSL = (Boolean)attributes.get("outUseSSL");

		if (outUseSSL != null) {
			setOutUseSSL(outUseSSL);
		}

		String outUserName = (String)attributes.get("outUserName");

		if (outUserName != null) {
			setOutUserName(outUserName);
		}

		String outPassword = (String)attributes.get("outPassword");

		if (outPassword != null) {
			setOutPassword(outPassword);
		}

		Boolean allowAnonymous = (Boolean)attributes.get("allowAnonymous");

		if (allowAnonymous != null) {
			setAllowAnonymous(allowAnonymous);
		}

		Boolean active = (Boolean)attributes.get("active");

		if (active != null) {
			setActive(active);
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
	public long getMailingListId() {
		return _mailingListId;
	}

	@Override
	public void setMailingListId(long mailingListId) {
		_mailingListId = mailingListId;
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

	@Override
	public String getUserName() {
		if (_userName == null) {
			return StringPool.BLANK;
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@Override
	public long getCategoryId() {
		return _categoryId;
	}

	@Override
	public void setCategoryId(long categoryId) {
		_columnBitmask |= CATEGORYID_COLUMN_BITMASK;

		if (!_setOriginalCategoryId) {
			_setOriginalCategoryId = true;

			_originalCategoryId = _categoryId;
		}

		_categoryId = categoryId;
	}

	public long getOriginalCategoryId() {
		return _originalCategoryId;
	}

	@Override
	public String getEmailAddress() {
		if (_emailAddress == null) {
			return StringPool.BLANK;
		}
		else {
			return _emailAddress;
		}
	}

	@Override
	public void setEmailAddress(String emailAddress) {
		_emailAddress = emailAddress;
	}

	@Override
	public String getInProtocol() {
		if (_inProtocol == null) {
			return StringPool.BLANK;
		}
		else {
			return _inProtocol;
		}
	}

	@Override
	public void setInProtocol(String inProtocol) {
		_inProtocol = inProtocol;
	}

	@Override
	public String getInServerName() {
		if (_inServerName == null) {
			return StringPool.BLANK;
		}
		else {
			return _inServerName;
		}
	}

	@Override
	public void setInServerName(String inServerName) {
		_inServerName = inServerName;
	}

	@Override
	public int getInServerPort() {
		return _inServerPort;
	}

	@Override
	public void setInServerPort(int inServerPort) {
		_inServerPort = inServerPort;
	}

	@Override
	public boolean getInUseSSL() {
		return _inUseSSL;
	}

	@Override
	public boolean isInUseSSL() {
		return _inUseSSL;
	}

	@Override
	public void setInUseSSL(boolean inUseSSL) {
		_inUseSSL = inUseSSL;
	}

	@Override
	public String getInUserName() {
		if (_inUserName == null) {
			return StringPool.BLANK;
		}
		else {
			return _inUserName;
		}
	}

	@Override
	public void setInUserName(String inUserName) {
		_inUserName = inUserName;
	}

	@Override
	public String getInPassword() {
		if (_inPassword == null) {
			return StringPool.BLANK;
		}
		else {
			return _inPassword;
		}
	}

	@Override
	public void setInPassword(String inPassword) {
		_inPassword = inPassword;
	}

	@Override
	public int getInReadInterval() {
		return _inReadInterval;
	}

	@Override
	public void setInReadInterval(int inReadInterval) {
		_inReadInterval = inReadInterval;
	}

	@Override
	public String getOutEmailAddress() {
		if (_outEmailAddress == null) {
			return StringPool.BLANK;
		}
		else {
			return _outEmailAddress;
		}
	}

	@Override
	public void setOutEmailAddress(String outEmailAddress) {
		_outEmailAddress = outEmailAddress;
	}

	@Override
	public boolean getOutCustom() {
		return _outCustom;
	}

	@Override
	public boolean isOutCustom() {
		return _outCustom;
	}

	@Override
	public void setOutCustom(boolean outCustom) {
		_outCustom = outCustom;
	}

	@Override
	public String getOutServerName() {
		if (_outServerName == null) {
			return StringPool.BLANK;
		}
		else {
			return _outServerName;
		}
	}

	@Override
	public void setOutServerName(String outServerName) {
		_outServerName = outServerName;
	}

	@Override
	public int getOutServerPort() {
		return _outServerPort;
	}

	@Override
	public void setOutServerPort(int outServerPort) {
		_outServerPort = outServerPort;
	}

	@Override
	public boolean getOutUseSSL() {
		return _outUseSSL;
	}

	@Override
	public boolean isOutUseSSL() {
		return _outUseSSL;
	}

	@Override
	public void setOutUseSSL(boolean outUseSSL) {
		_outUseSSL = outUseSSL;
	}

	@Override
	public String getOutUserName() {
		if (_outUserName == null) {
			return StringPool.BLANK;
		}
		else {
			return _outUserName;
		}
	}

	@Override
	public void setOutUserName(String outUserName) {
		_outUserName = outUserName;
	}

	@Override
	public String getOutPassword() {
		if (_outPassword == null) {
			return StringPool.BLANK;
		}
		else {
			return _outPassword;
		}
	}

	@Override
	public void setOutPassword(String outPassword) {
		_outPassword = outPassword;
	}

	@Override
	public boolean getAllowAnonymous() {
		return _allowAnonymous;
	}

	@Override
	public boolean isAllowAnonymous() {
		return _allowAnonymous;
	}

	@Override
	public void setAllowAnonymous(boolean allowAnonymous) {
		_allowAnonymous = allowAnonymous;
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

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(PortalUtil.getClassNameId(
				MBMailingList.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			MBMailingList.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public MBMailingList toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (MBMailingList)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		MBMailingListImpl mbMailingListImpl = new MBMailingListImpl();

		mbMailingListImpl.setUuid(getUuid());
		mbMailingListImpl.setMailingListId(getMailingListId());
		mbMailingListImpl.setGroupId(getGroupId());
		mbMailingListImpl.setCompanyId(getCompanyId());
		mbMailingListImpl.setUserId(getUserId());
		mbMailingListImpl.setUserName(getUserName());
		mbMailingListImpl.setCreateDate(getCreateDate());
		mbMailingListImpl.setModifiedDate(getModifiedDate());
		mbMailingListImpl.setCategoryId(getCategoryId());
		mbMailingListImpl.setEmailAddress(getEmailAddress());
		mbMailingListImpl.setInProtocol(getInProtocol());
		mbMailingListImpl.setInServerName(getInServerName());
		mbMailingListImpl.setInServerPort(getInServerPort());
		mbMailingListImpl.setInUseSSL(getInUseSSL());
		mbMailingListImpl.setInUserName(getInUserName());
		mbMailingListImpl.setInPassword(getInPassword());
		mbMailingListImpl.setInReadInterval(getInReadInterval());
		mbMailingListImpl.setOutEmailAddress(getOutEmailAddress());
		mbMailingListImpl.setOutCustom(getOutCustom());
		mbMailingListImpl.setOutServerName(getOutServerName());
		mbMailingListImpl.setOutServerPort(getOutServerPort());
		mbMailingListImpl.setOutUseSSL(getOutUseSSL());
		mbMailingListImpl.setOutUserName(getOutUserName());
		mbMailingListImpl.setOutPassword(getOutPassword());
		mbMailingListImpl.setAllowAnonymous(getAllowAnonymous());
		mbMailingListImpl.setActive(getActive());

		mbMailingListImpl.resetOriginalValues();

		return mbMailingListImpl;
	}

	@Override
	public int compareTo(MBMailingList mbMailingList) {
		long primaryKey = mbMailingList.getPrimaryKey();

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

		if (!(obj instanceof MBMailingList)) {
			return false;
		}

		MBMailingList mbMailingList = (MBMailingList)obj;

		long primaryKey = mbMailingList.getPrimaryKey();

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
		MBMailingListModelImpl mbMailingListModelImpl = this;

		mbMailingListModelImpl._originalUuid = mbMailingListModelImpl._uuid;

		mbMailingListModelImpl._originalGroupId = mbMailingListModelImpl._groupId;

		mbMailingListModelImpl._setOriginalGroupId = false;

		mbMailingListModelImpl._originalCompanyId = mbMailingListModelImpl._companyId;

		mbMailingListModelImpl._setOriginalCompanyId = false;

		mbMailingListModelImpl._setModifiedDate = false;

		mbMailingListModelImpl._originalCategoryId = mbMailingListModelImpl._categoryId;

		mbMailingListModelImpl._setOriginalCategoryId = false;

		mbMailingListModelImpl._originalActive = mbMailingListModelImpl._active;

		mbMailingListModelImpl._setOriginalActive = false;

		mbMailingListModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<MBMailingList> toCacheModel() {
		MBMailingListCacheModel mbMailingListCacheModel = new MBMailingListCacheModel();

		mbMailingListCacheModel.uuid = getUuid();

		String uuid = mbMailingListCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			mbMailingListCacheModel.uuid = null;
		}

		mbMailingListCacheModel.mailingListId = getMailingListId();

		mbMailingListCacheModel.groupId = getGroupId();

		mbMailingListCacheModel.companyId = getCompanyId();

		mbMailingListCacheModel.userId = getUserId();

		mbMailingListCacheModel.userName = getUserName();

		String userName = mbMailingListCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			mbMailingListCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			mbMailingListCacheModel.createDate = createDate.getTime();
		}
		else {
			mbMailingListCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			mbMailingListCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			mbMailingListCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		mbMailingListCacheModel.categoryId = getCategoryId();

		mbMailingListCacheModel.emailAddress = getEmailAddress();

		String emailAddress = mbMailingListCacheModel.emailAddress;

		if ((emailAddress != null) && (emailAddress.length() == 0)) {
			mbMailingListCacheModel.emailAddress = null;
		}

		mbMailingListCacheModel.inProtocol = getInProtocol();

		String inProtocol = mbMailingListCacheModel.inProtocol;

		if ((inProtocol != null) && (inProtocol.length() == 0)) {
			mbMailingListCacheModel.inProtocol = null;
		}

		mbMailingListCacheModel.inServerName = getInServerName();

		String inServerName = mbMailingListCacheModel.inServerName;

		if ((inServerName != null) && (inServerName.length() == 0)) {
			mbMailingListCacheModel.inServerName = null;
		}

		mbMailingListCacheModel.inServerPort = getInServerPort();

		mbMailingListCacheModel.inUseSSL = getInUseSSL();

		mbMailingListCacheModel.inUserName = getInUserName();

		String inUserName = mbMailingListCacheModel.inUserName;

		if ((inUserName != null) && (inUserName.length() == 0)) {
			mbMailingListCacheModel.inUserName = null;
		}

		mbMailingListCacheModel.inPassword = getInPassword();

		String inPassword = mbMailingListCacheModel.inPassword;

		if ((inPassword != null) && (inPassword.length() == 0)) {
			mbMailingListCacheModel.inPassword = null;
		}

		mbMailingListCacheModel.inReadInterval = getInReadInterval();

		mbMailingListCacheModel.outEmailAddress = getOutEmailAddress();

		String outEmailAddress = mbMailingListCacheModel.outEmailAddress;

		if ((outEmailAddress != null) && (outEmailAddress.length() == 0)) {
			mbMailingListCacheModel.outEmailAddress = null;
		}

		mbMailingListCacheModel.outCustom = getOutCustom();

		mbMailingListCacheModel.outServerName = getOutServerName();

		String outServerName = mbMailingListCacheModel.outServerName;

		if ((outServerName != null) && (outServerName.length() == 0)) {
			mbMailingListCacheModel.outServerName = null;
		}

		mbMailingListCacheModel.outServerPort = getOutServerPort();

		mbMailingListCacheModel.outUseSSL = getOutUseSSL();

		mbMailingListCacheModel.outUserName = getOutUserName();

		String outUserName = mbMailingListCacheModel.outUserName;

		if ((outUserName != null) && (outUserName.length() == 0)) {
			mbMailingListCacheModel.outUserName = null;
		}

		mbMailingListCacheModel.outPassword = getOutPassword();

		String outPassword = mbMailingListCacheModel.outPassword;

		if ((outPassword != null) && (outPassword.length() == 0)) {
			mbMailingListCacheModel.outPassword = null;
		}

		mbMailingListCacheModel.allowAnonymous = getAllowAnonymous();

		mbMailingListCacheModel.active = getActive();

		return mbMailingListCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(53);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", mailingListId=");
		sb.append(getMailingListId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", categoryId=");
		sb.append(getCategoryId());
		sb.append(", emailAddress=");
		sb.append(getEmailAddress());
		sb.append(", inProtocol=");
		sb.append(getInProtocol());
		sb.append(", inServerName=");
		sb.append(getInServerName());
		sb.append(", inServerPort=");
		sb.append(getInServerPort());
		sb.append(", inUseSSL=");
		sb.append(getInUseSSL());
		sb.append(", inUserName=");
		sb.append(getInUserName());
		sb.append(", inPassword=");
		sb.append(getInPassword());
		sb.append(", inReadInterval=");
		sb.append(getInReadInterval());
		sb.append(", outEmailAddress=");
		sb.append(getOutEmailAddress());
		sb.append(", outCustom=");
		sb.append(getOutCustom());
		sb.append(", outServerName=");
		sb.append(getOutServerName());
		sb.append(", outServerPort=");
		sb.append(getOutServerPort());
		sb.append(", outUseSSL=");
		sb.append(getOutUseSSL());
		sb.append(", outUserName=");
		sb.append(getOutUserName());
		sb.append(", outPassword=");
		sb.append(getOutPassword());
		sb.append(", allowAnonymous=");
		sb.append(getAllowAnonymous());
		sb.append(", active=");
		sb.append(getActive());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(82);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portlet.messageboards.model.MBMailingList");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>mailingListId</column-name><column-value><![CDATA[");
		sb.append(getMailingListId());
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
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>categoryId</column-name><column-value><![CDATA[");
		sb.append(getCategoryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>emailAddress</column-name><column-value><![CDATA[");
		sb.append(getEmailAddress());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>inProtocol</column-name><column-value><![CDATA[");
		sb.append(getInProtocol());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>inServerName</column-name><column-value><![CDATA[");
		sb.append(getInServerName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>inServerPort</column-name><column-value><![CDATA[");
		sb.append(getInServerPort());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>inUseSSL</column-name><column-value><![CDATA[");
		sb.append(getInUseSSL());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>inUserName</column-name><column-value><![CDATA[");
		sb.append(getInUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>inPassword</column-name><column-value><![CDATA[");
		sb.append(getInPassword());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>inReadInterval</column-name><column-value><![CDATA[");
		sb.append(getInReadInterval());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>outEmailAddress</column-name><column-value><![CDATA[");
		sb.append(getOutEmailAddress());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>outCustom</column-name><column-value><![CDATA[");
		sb.append(getOutCustom());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>outServerName</column-name><column-value><![CDATA[");
		sb.append(getOutServerName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>outServerPort</column-name><column-value><![CDATA[");
		sb.append(getOutServerPort());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>outUseSSL</column-name><column-value><![CDATA[");
		sb.append(getOutUseSSL());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>outUserName</column-name><column-value><![CDATA[");
		sb.append(getOutUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>outPassword</column-name><column-value><![CDATA[");
		sb.append(getOutPassword());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>allowAnonymous</column-name><column-value><![CDATA[");
		sb.append(getAllowAnonymous());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>active</column-name><column-value><![CDATA[");
		sb.append(getActive());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = MBMailingList.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			MBMailingList.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _mailingListId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _categoryId;
	private long _originalCategoryId;
	private boolean _setOriginalCategoryId;
	private String _emailAddress;
	private String _inProtocol;
	private String _inServerName;
	private int _inServerPort;
	private boolean _inUseSSL;
	private String _inUserName;
	private String _inPassword;
	private int _inReadInterval;
	private String _outEmailAddress;
	private boolean _outCustom;
	private String _outServerName;
	private int _outServerPort;
	private boolean _outUseSSL;
	private String _outUserName;
	private String _outPassword;
	private boolean _allowAnonymous;
	private boolean _active;
	private boolean _originalActive;
	private boolean _setOriginalActive;
	private long _columnBitmask;
	private MBMailingList _escapedModel;
}