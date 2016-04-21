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

package com.liferay.portal.reports.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.reports.model.Entry;
import com.liferay.portal.reports.model.EntryModel;
import com.liferay.portal.reports.model.EntrySoap;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the Entry service. Represents a row in the &quot;Entry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link EntryModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link EntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EntryImpl
 * @see Entry
 * @see EntryModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class EntryModelImpl extends BaseModelImpl<Entry> implements EntryModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a entry model instance should use the {@link Entry} interface instead.
	 */
	public static final String TABLE_NAME = "Entry";
	public static final Object[][] TABLE_COLUMNS = {
			{ "entryId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "definitionId", Types.BIGINT },
			{ "format", Types.VARCHAR },
			{ "scheduleRequest", Types.BOOLEAN },
			{ "startDate", Types.TIMESTAMP },
			{ "endDate", Types.TIMESTAMP },
			{ "repeating", Types.BOOLEAN },
			{ "recurrence", Types.VARCHAR },
			{ "emailNotifications", Types.VARCHAR },
			{ "emailDelivery", Types.VARCHAR },
			{ "portletId", Types.VARCHAR },
			{ "pageURL", Types.VARCHAR },
			{ "reportParameters", Types.VARCHAR },
			{ "status", Types.VARCHAR },
			{ "errorMessage", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("entryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("definitionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("format", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("scheduleRequest", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("startDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("endDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("repeating", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("recurrence", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("emailNotifications", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("emailDelivery", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("portletId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("pageURL", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("reportParameters", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("status", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("errorMessage", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table Entry (entryId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,definitionId LONG,format VARCHAR(75) null,scheduleRequest BOOLEAN,startDate DATE null,endDate DATE null,repeating BOOLEAN,recurrence VARCHAR(75) null,emailNotifications VARCHAR(200) null,emailDelivery VARCHAR(200) null,portletId VARCHAR(75) null,pageURL STRING null,reportParameters VARCHAR(255) null,status VARCHAR(75) null,errorMessage VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table Entry";
	public static final String ORDER_BY_JPQL = " ORDER BY entry.modifiedDate ASC";
	public static final String ORDER_BY_SQL = " ORDER BY Entry.modifiedDate ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.reports.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.portal.reports.model.Entry"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.reports.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.portal.reports.model.Entry"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = false;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static Entry toModel(EntrySoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		Entry model = new EntryImpl();

		model.setEntryId(soapModel.getEntryId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setDefinitionId(soapModel.getDefinitionId());
		model.setFormat(soapModel.getFormat());
		model.setScheduleRequest(soapModel.getScheduleRequest());
		model.setStartDate(soapModel.getStartDate());
		model.setEndDate(soapModel.getEndDate());
		model.setRepeating(soapModel.getRepeating());
		model.setRecurrence(soapModel.getRecurrence());
		model.setEmailNotifications(soapModel.getEmailNotifications());
		model.setEmailDelivery(soapModel.getEmailDelivery());
		model.setPortletId(soapModel.getPortletId());
		model.setPageURL(soapModel.getPageURL());
		model.setReportParameters(soapModel.getReportParameters());
		model.setStatus(soapModel.getStatus());
		model.setErrorMessage(soapModel.getErrorMessage());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<Entry> toModels(EntrySoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<Entry> models = new ArrayList<Entry>(soapModels.length);

		for (EntrySoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.reports.service.util.ServiceProps.get(
				"lock.expiration.time.com.liferay.portal.reports.model.Entry"));

	public EntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _entryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _entryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Entry.class;
	}

	@Override
	public String getModelClassName() {
		return Entry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("entryId", getEntryId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("definitionId", getDefinitionId());
		attributes.put("format", getFormat());
		attributes.put("scheduleRequest", getScheduleRequest());
		attributes.put("startDate", getStartDate());
		attributes.put("endDate", getEndDate());
		attributes.put("repeating", getRepeating());
		attributes.put("recurrence", getRecurrence());
		attributes.put("emailNotifications", getEmailNotifications());
		attributes.put("emailDelivery", getEmailDelivery());
		attributes.put("portletId", getPortletId());
		attributes.put("pageURL", getPageURL());
		attributes.put("reportParameters", getReportParameters());
		attributes.put("status", getStatus());
		attributes.put("errorMessage", getErrorMessage());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long entryId = (Long)attributes.get("entryId");

		if (entryId != null) {
			setEntryId(entryId);
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

		Long definitionId = (Long)attributes.get("definitionId");

		if (definitionId != null) {
			setDefinitionId(definitionId);
		}

		String format = (String)attributes.get("format");

		if (format != null) {
			setFormat(format);
		}

		Boolean scheduleRequest = (Boolean)attributes.get("scheduleRequest");

		if (scheduleRequest != null) {
			setScheduleRequest(scheduleRequest);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		Boolean repeating = (Boolean)attributes.get("repeating");

		if (repeating != null) {
			setRepeating(repeating);
		}

		String recurrence = (String)attributes.get("recurrence");

		if (recurrence != null) {
			setRecurrence(recurrence);
		}

		String emailNotifications = (String)attributes.get("emailNotifications");

		if (emailNotifications != null) {
			setEmailNotifications(emailNotifications);
		}

		String emailDelivery = (String)attributes.get("emailDelivery");

		if (emailDelivery != null) {
			setEmailDelivery(emailDelivery);
		}

		String portletId = (String)attributes.get("portletId");

		if (portletId != null) {
			setPortletId(portletId);
		}

		String pageURL = (String)attributes.get("pageURL");

		if (pageURL != null) {
			setPageURL(pageURL);
		}

		String reportParameters = (String)attributes.get("reportParameters");

		if (reportParameters != null) {
			setReportParameters(reportParameters);
		}

		String status = (String)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		String errorMessage = (String)attributes.get("errorMessage");

		if (errorMessage != null) {
			setErrorMessage(errorMessage);
		}
	}

	@JSON
	@Override
	public long getEntryId() {
		return _entryId;
	}

	@Override
	public void setEntryId(long entryId) {
		_entryId = entryId;
	}

	@JSON
	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
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

	@JSON
	@Override
	public long getDefinitionId() {
		return _definitionId;
	}

	@Override
	public void setDefinitionId(long definitionId) {
		_definitionId = definitionId;
	}

	@JSON
	@Override
	public String getFormat() {
		if (_format == null) {
			return StringPool.BLANK;
		}
		else {
			return _format;
		}
	}

	@Override
	public void setFormat(String format) {
		_format = format;
	}

	@JSON
	@Override
	public boolean getScheduleRequest() {
		return _scheduleRequest;
	}

	@Override
	public boolean isScheduleRequest() {
		return _scheduleRequest;
	}

	@Override
	public void setScheduleRequest(boolean scheduleRequest) {
		_scheduleRequest = scheduleRequest;
	}

	@JSON
	@Override
	public Date getStartDate() {
		return _startDate;
	}

	@Override
	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	@JSON
	@Override
	public Date getEndDate() {
		return _endDate;
	}

	@Override
	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	@JSON
	@Override
	public boolean getRepeating() {
		return _repeating;
	}

	@Override
	public boolean isRepeating() {
		return _repeating;
	}

	@Override
	public void setRepeating(boolean repeating) {
		_repeating = repeating;
	}

	@JSON
	@Override
	public String getRecurrence() {
		if (_recurrence == null) {
			return StringPool.BLANK;
		}
		else {
			return _recurrence;
		}
	}

	@Override
	public void setRecurrence(String recurrence) {
		_recurrence = recurrence;
	}

	@JSON
	@Override
	public String getEmailNotifications() {
		if (_emailNotifications == null) {
			return StringPool.BLANK;
		}
		else {
			return _emailNotifications;
		}
	}

	@Override
	public void setEmailNotifications(String emailNotifications) {
		_emailNotifications = emailNotifications;
	}

	@JSON
	@Override
	public String getEmailDelivery() {
		if (_emailDelivery == null) {
			return StringPool.BLANK;
		}
		else {
			return _emailDelivery;
		}
	}

	@Override
	public void setEmailDelivery(String emailDelivery) {
		_emailDelivery = emailDelivery;
	}

	@JSON
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
		_portletId = portletId;
	}

	@JSON
	@Override
	public String getPageURL() {
		if (_pageURL == null) {
			return StringPool.BLANK;
		}
		else {
			return _pageURL;
		}
	}

	@Override
	public void setPageURL(String pageURL) {
		_pageURL = pageURL;
	}

	@JSON
	@Override
	public String getReportParameters() {
		if (_reportParameters == null) {
			return StringPool.BLANK;
		}
		else {
			return _reportParameters;
		}
	}

	@Override
	public void setReportParameters(String reportParameters) {
		_reportParameters = reportParameters;
	}

	@JSON
	@Override
	public String getStatus() {
		if (_status == null) {
			return StringPool.BLANK;
		}
		else {
			return _status;
		}
	}

	@Override
	public void setStatus(String status) {
		_status = status;
	}

	@JSON
	@Override
	public String getErrorMessage() {
		if (_errorMessage == null) {
			return StringPool.BLANK;
		}
		else {
			return _errorMessage;
		}
	}

	@Override
	public void setErrorMessage(String errorMessage) {
		_errorMessage = errorMessage;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			Entry.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Entry toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (Entry)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		EntryImpl entryImpl = new EntryImpl();

		entryImpl.setEntryId(getEntryId());
		entryImpl.setGroupId(getGroupId());
		entryImpl.setCompanyId(getCompanyId());
		entryImpl.setUserId(getUserId());
		entryImpl.setUserName(getUserName());
		entryImpl.setCreateDate(getCreateDate());
		entryImpl.setModifiedDate(getModifiedDate());
		entryImpl.setDefinitionId(getDefinitionId());
		entryImpl.setFormat(getFormat());
		entryImpl.setScheduleRequest(getScheduleRequest());
		entryImpl.setStartDate(getStartDate());
		entryImpl.setEndDate(getEndDate());
		entryImpl.setRepeating(getRepeating());
		entryImpl.setRecurrence(getRecurrence());
		entryImpl.setEmailNotifications(getEmailNotifications());
		entryImpl.setEmailDelivery(getEmailDelivery());
		entryImpl.setPortletId(getPortletId());
		entryImpl.setPageURL(getPageURL());
		entryImpl.setReportParameters(getReportParameters());
		entryImpl.setStatus(getStatus());
		entryImpl.setErrorMessage(getErrorMessage());

		entryImpl.resetOriginalValues();

		return entryImpl;
	}

	@Override
	public int compareTo(Entry entry) {
		int value = 0;

		value = DateUtil.compareTo(getModifiedDate(), entry.getModifiedDate());

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

		if (!(obj instanceof Entry)) {
			return false;
		}

		Entry entry = (Entry)obj;

		long primaryKey = entry.getPrimaryKey();

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
		EntryModelImpl entryModelImpl = this;

		entryModelImpl._setModifiedDate = false;
	}

	@Override
	public CacheModel<Entry> toCacheModel() {
		EntryCacheModel entryCacheModel = new EntryCacheModel();

		entryCacheModel.entryId = getEntryId();

		entryCacheModel.groupId = getGroupId();

		entryCacheModel.companyId = getCompanyId();

		entryCacheModel.userId = getUserId();

		entryCacheModel.userName = getUserName();

		String userName = entryCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			entryCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			entryCacheModel.createDate = createDate.getTime();
		}
		else {
			entryCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			entryCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			entryCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		entryCacheModel.definitionId = getDefinitionId();

		entryCacheModel.format = getFormat();

		String format = entryCacheModel.format;

		if ((format != null) && (format.length() == 0)) {
			entryCacheModel.format = null;
		}

		entryCacheModel.scheduleRequest = getScheduleRequest();

		Date startDate = getStartDate();

		if (startDate != null) {
			entryCacheModel.startDate = startDate.getTime();
		}
		else {
			entryCacheModel.startDate = Long.MIN_VALUE;
		}

		Date endDate = getEndDate();

		if (endDate != null) {
			entryCacheModel.endDate = endDate.getTime();
		}
		else {
			entryCacheModel.endDate = Long.MIN_VALUE;
		}

		entryCacheModel.repeating = getRepeating();

		entryCacheModel.recurrence = getRecurrence();

		String recurrence = entryCacheModel.recurrence;

		if ((recurrence != null) && (recurrence.length() == 0)) {
			entryCacheModel.recurrence = null;
		}

		entryCacheModel.emailNotifications = getEmailNotifications();

		String emailNotifications = entryCacheModel.emailNotifications;

		if ((emailNotifications != null) && (emailNotifications.length() == 0)) {
			entryCacheModel.emailNotifications = null;
		}

		entryCacheModel.emailDelivery = getEmailDelivery();

		String emailDelivery = entryCacheModel.emailDelivery;

		if ((emailDelivery != null) && (emailDelivery.length() == 0)) {
			entryCacheModel.emailDelivery = null;
		}

		entryCacheModel.portletId = getPortletId();

		String portletId = entryCacheModel.portletId;

		if ((portletId != null) && (portletId.length() == 0)) {
			entryCacheModel.portletId = null;
		}

		entryCacheModel.pageURL = getPageURL();

		String pageURL = entryCacheModel.pageURL;

		if ((pageURL != null) && (pageURL.length() == 0)) {
			entryCacheModel.pageURL = null;
		}

		entryCacheModel.reportParameters = getReportParameters();

		String reportParameters = entryCacheModel.reportParameters;

		if ((reportParameters != null) && (reportParameters.length() == 0)) {
			entryCacheModel.reportParameters = null;
		}

		entryCacheModel.status = getStatus();

		String status = entryCacheModel.status;

		if ((status != null) && (status.length() == 0)) {
			entryCacheModel.status = null;
		}

		entryCacheModel.errorMessage = getErrorMessage();

		String errorMessage = entryCacheModel.errorMessage;

		if ((errorMessage != null) && (errorMessage.length() == 0)) {
			entryCacheModel.errorMessage = null;
		}

		return entryCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(43);

		sb.append("{entryId=");
		sb.append(getEntryId());
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
		sb.append(", definitionId=");
		sb.append(getDefinitionId());
		sb.append(", format=");
		sb.append(getFormat());
		sb.append(", scheduleRequest=");
		sb.append(getScheduleRequest());
		sb.append(", startDate=");
		sb.append(getStartDate());
		sb.append(", endDate=");
		sb.append(getEndDate());
		sb.append(", repeating=");
		sb.append(getRepeating());
		sb.append(", recurrence=");
		sb.append(getRecurrence());
		sb.append(", emailNotifications=");
		sb.append(getEmailNotifications());
		sb.append(", emailDelivery=");
		sb.append(getEmailDelivery());
		sb.append(", portletId=");
		sb.append(getPortletId());
		sb.append(", pageURL=");
		sb.append(getPageURL());
		sb.append(", reportParameters=");
		sb.append(getReportParameters());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append(", errorMessage=");
		sb.append(getErrorMessage());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(67);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portal.reports.model.Entry");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>entryId</column-name><column-value><![CDATA[");
		sb.append(getEntryId());
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
			"<column><column-name>definitionId</column-name><column-value><![CDATA[");
		sb.append(getDefinitionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>format</column-name><column-value><![CDATA[");
		sb.append(getFormat());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>scheduleRequest</column-name><column-value><![CDATA[");
		sb.append(getScheduleRequest());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>startDate</column-name><column-value><![CDATA[");
		sb.append(getStartDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>endDate</column-name><column-value><![CDATA[");
		sb.append(getEndDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>repeating</column-name><column-value><![CDATA[");
		sb.append(getRepeating());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>recurrence</column-name><column-value><![CDATA[");
		sb.append(getRecurrence());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>emailNotifications</column-name><column-value><![CDATA[");
		sb.append(getEmailNotifications());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>emailDelivery</column-name><column-value><![CDATA[");
		sb.append(getEmailDelivery());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>portletId</column-name><column-value><![CDATA[");
		sb.append(getPortletId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>pageURL</column-name><column-value><![CDATA[");
		sb.append(getPageURL());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>reportParameters</column-name><column-value><![CDATA[");
		sb.append(getReportParameters());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>errorMessage</column-name><column-value><![CDATA[");
		sb.append(getErrorMessage());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = Entry.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			Entry.class
		};
	private long _entryId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _definitionId;
	private String _format;
	private boolean _scheduleRequest;
	private Date _startDate;
	private Date _endDate;
	private boolean _repeating;
	private String _recurrence;
	private String _emailNotifications;
	private String _emailDelivery;
	private String _portletId;
	private String _pageURL;
	private String _reportParameters;
	private String _status;
	private String _errorMessage;
	private Entry _escapedModel;
}