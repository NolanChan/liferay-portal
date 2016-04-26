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

package com.liferay.portal.reports.service.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.document.library.kernel.store.DLStore;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Junction;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.StorageType;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.settings.GroupServiceSettingsLocator;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.SubscriptionSender;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.reports.ReportStatus;
import com.liferay.portal.reports.configuration.ReportsGroupServiceEmailConfiguration;
import com.liferay.portal.reports.constants.ReportsPortletKeys;
import com.liferay.portal.reports.engine.MemoryReportDesignRetriever;
import com.liferay.portal.reports.engine.ReportDataSourceType;
import com.liferay.portal.reports.engine.ReportDesignRetriever;
import com.liferay.portal.reports.engine.ReportRequest;
import com.liferay.portal.reports.engine.ReportRequestContext;
import com.liferay.portal.reports.engine.messaging.DestinationNames;
import com.liferay.portal.reports.exception.DefinitionNameException;
import com.liferay.portal.reports.exception.EntryEmailDeliveryException;
import com.liferay.portal.reports.exception.EntryEmailNotificationsException;
import com.liferay.portal.reports.model.Definition;
import com.liferay.portal.reports.model.Entry;
import com.liferay.portal.reports.model.Source;
import com.liferay.portal.reports.service.base.EntryLocalServiceBaseImpl;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.text.DateFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletPreferences;

/**
 * @author Gavin Wan
 */
@ProviderType
public class EntryLocalServiceImpl extends EntryLocalServiceBaseImpl {

	@Override
	public Entry addEntry(
			long userId, long groupId, long definitionId, String format,
			boolean schedulerRequest, Date startDate, Date endDate,
			boolean repeating, String recurrence, String emailNotifications,
			String emailDelivery, String portletId, String pageURL,
			String reportName, String reportParameters,
			ServiceContext serviceContext)
		throws PortalException {

		// Entry

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		validate(emailNotifications, emailDelivery, reportName);

		long entryId = counterLocalService.increment();

		Entry entry = entryPersistence.create(entryId);

		entry.setGroupId(groupId);
		entry.setCompanyId(user.getCompanyId());
		entry.setUserId(user.getUserId());
		entry.setUserName(user.getFullName());
		entry.setCreateDate(serviceContext.getCreateDate(now));
		entry.setModifiedDate(serviceContext.getModifiedDate(now));
		entry.setDefinitionId(definitionId);
		entry.setFormat(format);
		entry.setScheduleRequest(schedulerRequest);
		entry.setStartDate(startDate);
		entry.setEndDate(endDate);
		entry.setReportParameters(reportParameters);
		entry.setRepeating(repeating);
		entry.setRecurrence(recurrence);
		entry.setEmailNotifications(emailNotifications);
		entry.setEmailDelivery(emailDelivery);
		entry.setPortletId(portletId);

		StringBundler sb = new StringBundler(5);

		sb.append(pageURL);
		sb.append("&");
		sb.append(PortalUtil.getPortletNamespace(portletId));
		sb.append("entryId=");
		sb.append(entryId);

		entry.setPageURL(sb.toString());

		entry.setStatus(ReportStatus.PENDING.getValue());

		entryPersistence.update(entry);

		// Resources

		resourceLocalService.addModelResources(entry, serviceContext);

		// Scheduler

		if (schedulerRequest) {
			scheduleEntry(entry, reportName);
		}

		// Report

		if (!schedulerRequest) {
			generateReport(entryId, reportName);
		}

		return entry;
	}

	@Override
	public void addEntryResources(
			Entry entry, boolean addCommunityPermissions,
			boolean addGuestPermissions)
		throws PortalException {

		resourceLocalService.addResources(
			entry.getCompanyId(), entry.getGroupId(), entry.getUserId(),
			Entry.class.getName(), entry.getEntryId(), false,
			addCommunityPermissions, addGuestPermissions);
	}

	@Override
	public void addEntryResources(
			Entry entry, String[] communityPermissions,
			String[] guestPermissions)
		throws PortalException {

		resourceLocalService.addModelResources(
			entry.getCompanyId(), entry.getGroupId(), entry.getUserId(),
			Entry.class.getName(), entry.getEntryId(), communityPermissions,
			guestPermissions);
	}

	@Override
	public void deleteAttachment(long companyId, String fileName)
		throws PortalException {

		_dlStore.deleteFile(companyId, CompanyConstants.SYSTEM, fileName);
	}

	@Override
	public Entry deleteEntry(Entry entry) throws PortalException {

		// Entry

		entryPersistence.remove(entry);

		// Resources

		resourceLocalService.deleteResource(
			entry.getCompanyId(), Entry.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL, entry.getEntryId());

		// Scheduler

		if (entry.isRepeating()) {
			_schedulerEngineHelper.unschedule(
				entry.getJobName(), entry.getSchedulerRequestName(),
				StorageType.PERSISTED);
		}

		// Attachments

		_dlStore.deleteDirectory(
			entry.getCompanyId(), CompanyConstants.SYSTEM,
			entry.getAttachmentsDir());

		return entry;
	}

	@Override
	public Entry deleteEntry(long entryId) throws PortalException {
		Entry entry = entryPersistence.findByPrimaryKey(entryId);

		return deleteEntry(entry);
	}

	@Override
	public void generateReport(long entryId) throws PortalException {
		Entry entry = entryPersistence.findByPrimaryKey(entryId);

		Definition definition = definitionPersistence.findByPrimaryKey(
			entry.getDefinitionId());

		generateReport(entryId, definition.getReportName());
	}

	@Override
	public void generateReport(long entryId, String reportName)
		throws PortalException {

		Entry entry = entryPersistence.findByPrimaryKey(entryId);

		Definition definition = definitionPersistence.findByPrimaryKey(
			entry.getDefinitionId());

		String[] existingFiles = definition.getAttachmentsFiles();

		byte[] templateFile = _dlStore.getFileAsBytes(
			definition.getCompanyId(), CompanyConstants.SYSTEM,
			existingFiles[0]);

		ReportDesignRetriever retriever = new MemoryReportDesignRetriever(
			reportName + StringPool.PERIOD + entry.getFormat(),
			definition.getModifiedDate(), templateFile);

		long sourceId = definition.getSourceId();

		Map<String, String> reportParameters = new HashMap<>();

		JSONArray reportParametersJSONArray = _jsonFactory.createJSONArray(
			entry.getReportParameters());

		for (int i = 0; i < reportParametersJSONArray.length(); i++) {
			JSONObject reportParameterJSONObject =
				reportParametersJSONArray.getJSONObject(i);

			reportParameters.put(
				reportParameterJSONObject.getString("key"),
				reportParameterJSONObject.getString("value"));
		}

		ReportRequestContext reportRequestContext = null;

		if (sourceId == 0) {
			reportRequestContext = new ReportRequestContext(
				ReportDataSourceType.PORTAL);
		}
		else {
			Source source = sourcePersistence.findByPrimaryKey(sourceId);

			reportRequestContext = new ReportRequestContext(
				ReportDataSourceType.JDBC);

			reportRequestContext.setAttribute(
				ReportRequestContext.JDBC_DRIVER_CLASS,
				source.getDriverClassName());
			reportRequestContext.setAttribute(
				ReportRequestContext.JDBC_URL, source.getDriverUrl());
			reportRequestContext.setAttribute(
				ReportRequestContext.JDBC_USER_NAME,
				source.getDriverUserName());
			reportRequestContext.setAttribute(
				ReportRequestContext.JDBC_PASSWORD, source.getDriverPassword());
		}

		ReportRequest reportRequest = new ReportRequest(
			reportRequestContext, retriever, reportParameters,
			entry.getFormat());

		Message message = new Message();

		message.setPayload(reportRequest);
		message.setResponseId(String.valueOf(entry.getEntryId()));
		message.setResponseDestinationName("liferay/reports_admin");

		_messageBus.sendMessage(DestinationNames.REPORT_REQUEST, message);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Entry> getEntries(
		long groupId, String definitionName, String userName, Date createDateGT,
		Date createDateLT, boolean andSearch, int start, int end,
		OrderByComparator orderByComparator) {

		DynamicQuery dynamicQuery = buildDynamicQuery(
			groupId, definitionName, userName, createDateGT, createDateLT,
			andSearch);

		return dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	@Override
	public int getEntriesCount(
		long groupId, String definitionName, String userName, Date createDateGT,
		Date createDateLT, boolean andSearch) {

		DynamicQuery dynamicQuery = buildDynamicQuery(
			groupId, definitionName, userName, createDateGT, createDateLT,
			andSearch);

		return (int)dynamicQueryCount(dynamicQuery);
	}

	@Override
	public void sendEmails(
			long entryId, String fileName, String[] emailAddresses,
			boolean notification)
		throws PortalException {

		Entry entry = entryLocalService.getEntry(entryId);

		String reportName = StringUtil.extractLast(
			fileName, StringPool.FORWARD_SLASH);

		File file = null;
		InputStream inputStream = null;

		try {
			inputStream = _dlStore.getFileAsStream(
				entry.getCompanyId(), CompanyConstants.SYSTEM, fileName);

			if (inputStream == null) {
				throw new IOException("Unable to open file " + fileName);
			}

			file = FileUtil.createTempFile(inputStream);

			notifySubscribers(
				entry, emailAddresses, reportName, file, notification,
				new ServiceContext());
		}
		catch (IOException ioe) {
			throw new PortalException(ioe.getMessage());
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			StreamUtil.cleanUp(inputStream);

			if (file != null) {
				file.delete();
			}
		}
	}

	@Override
	public void unscheduleEntry(long entryId) throws PortalException {
		Entry entry = entryPersistence.findByPrimaryKey(entryId);

		entry.setScheduleRequest(false);
		entry.setRepeating(false);

		entryPersistence.update(entry);

		_schedulerEngineHelper.unschedule(
			entry.getJobName(), entry.getSchedulerRequestName(),
			StorageType.PERSISTED);
	}

	@Override
	public void updateEntry(
			long entryId, String reportName, byte[] reportResults)
		throws PortalException {

		Entry entry = entryPersistence.findByPrimaryKey(entryId);
		Date now = new Date();

		if (entry.isScheduleRequest()) {
			StringBundler sb = new StringBundler(4);

			sb.append(StringUtil.extractFirst(reportName, StringPool.PERIOD));

			DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
				"MM_dd_yyyy_HH_mm");

			sb.append(dateFormat.format(now));

			sb.append(StringPool.PERIOD);
			sb.append(StringUtil.extractLast(reportName, StringPool.PERIOD));

			reportName = sb.toString();
		}

		String fileName =
			entry.getAttachmentsDir() + StringPool.SLASH + reportName;

		_dlStore.addDirectory(
			entry.getCompanyId(), CompanyConstants.SYSTEM,
			entry.getAttachmentsDir());

		_dlStore.addFile(
			entry.getCompanyId(), CompanyConstants.SYSTEM, fileName, false,
			reportResults);

		String[] emailAddresses = StringUtil.split(entry.getEmailDelivery());

		sendEmails(entryId, fileName, emailAddresses, false);

		emailAddresses = StringUtil.split(entry.getEmailNotifications());

		sendEmails(entryId, fileName, emailAddresses, true);

		entry.setModifiedDate(now);
		entry.setStatus(ReportStatus.COMPLETE.getValue());

		entryPersistence.update(entry);
	}

	@Override
	public void updateEntryStatus(
			long entryId, ReportStatus status, String errorMessage)
		throws PortalException {

		Entry entry = entryLocalService.getEntry(entryId);

		entry.setStatus(status.getValue());
		entry.setErrorMessage(errorMessage);

		entryPersistence.update(entry);
	}

	protected DynamicQuery buildDynamicQuery(
		long groupId, String definitionName, String userName, Date createDateGT,
		Date createDateLT, boolean andSearch) {

		Junction junction = null;

		if (andSearch) {
			junction = RestrictionsFactoryUtil.conjunction();
		}
		else {
			junction = RestrictionsFactoryUtil.disjunction();
		}

		if (Validator.isNotNull(definitionName)) {
			DynamicQuery dynamicQuery = definitionLocalService.dynamicQuery();

			Property nameProperty = PropertyFactoryUtil.forName("name");

			String value =
				StringPool.PERCENT + definitionName + StringPool.PERCENT;

			dynamicQuery.add(nameProperty.like(value));

			dynamicQuery.setProjection(
				ProjectionFactoryUtil.property("definitionId"));

			Property definitionIdProperty = PropertyFactoryUtil.forName(
				"definitionId");

			junction.add(definitionIdProperty.in(dynamicQuery));
		}

		if (Validator.isNotNull(userName)) {
			DynamicQuery dynamicQuery = _userLocalService.dynamicQuery();

			Property nameProperty = PropertyFactoryUtil.forName("screenName");

			String value = StringPool.PERCENT + userName + StringPool.PERCENT;

			dynamicQuery.add(nameProperty.like(value));

			dynamicQuery.setProjection(
				ProjectionFactoryUtil.property("userId"));

			Property definitionIdProperty = PropertyFactoryUtil.forName(
				"userId");

			junction.add(definitionIdProperty.in(dynamicQuery));
		}

		DynamicQuery dynamicQuery = entryLocalService.dynamicQuery();

		if (groupId > 0) {
			Property property = PropertyFactoryUtil.forName("groupId");

			dynamicQuery.add(property.eq(groupId));
		}

		if (createDateGT != null) {
			Property property = PropertyFactoryUtil.forName("createDate");

			dynamicQuery.add(property.gt(createDateGT));
		}

		if (createDateLT != null) {
			Property property = PropertyFactoryUtil.forName("createDate");

			dynamicQuery.add(property.lt(createDateLT));
		}

		dynamicQuery.add(junction);

		return dynamicQuery;
	}

	protected ReportsGroupServiceEmailConfiguration
			getReportsGroupServiceEmailConfiguration(
				long groupId)
		throws ConfigurationException {

		return configurationProvider.getConfiguration(
			ReportsGroupServiceEmailConfiguration.class,
			new GroupServiceSettingsLocator(
				groupId, ReportsPortletKeys.SERVICE_NAME));
	}

	protected void notifySubscribers(
			Entry entry, String[] emailAddresses, String reportName, File file,
			boolean notification, ServiceContext serviceContext)
		throws Exception {

		if (emailAddresses.length == 0) {
			return;
		}

		long groupId = entry.getGroupId();
		String portletId = entry.getPortletId();

		ReportsGroupServiceEmailConfiguration
			reportsGroupServiceEmailConfiguration =
				getReportsGroupServiceEmailConfiguration(groupId);

		String fromName = reportsGroupServiceEmailConfiguration.emailFromName();

		String fromAddress =
			reportsGroupServiceEmailConfiguration.emailFromAddress();

		Map<Locale, String> localizedSubjectMap = null;
		Map<Locale, String> localizedBodyMap = null;

		if (notification) {
			localizedBodyMap = LocalizationUtil.getMap(
				reportsGroupServiceEmailConfiguration.emailNotificationsBody());

			localizedSubjectMap = LocalizationUtil.getMap(
				reportsGroupServiceEmailConfiguration.
					emailNotificationsSubject());
		}
		else {
			localizedBodyMap = LocalizationUtil.getMap(
				reportsGroupServiceEmailConfiguration.emailDeliveryBody());

			localizedSubjectMap = LocalizationUtil.getMap(
				reportsGroupServiceEmailConfiguration.emailDeliverySubject());
		}

		SubscriptionSender subscriptionSender = new SubscriptionSender();

		if (!notification) {
			subscriptionSender.addFileAttachment(file, reportName);
		}

		subscriptionSender.setCompanyId(entry.getCompanyId());
		subscriptionSender.setContextAttributes(
			"[$FROM_ADDRESS$]", fromAddress, "[$FROM_NAME$]", fromName,
			"[$PAGE_URL$]", entry.getPageURL(), "[$REPORT_NAME$]", reportName);
		subscriptionSender.setFrom(fromAddress, fromName);
		subscriptionSender.setGroupId(entry.getGroupId());
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setLocalizedBodyMap(localizedBodyMap);
		subscriptionSender.setLocalizedSubjectMap(localizedSubjectMap);
		subscriptionSender.setMailId("reports_entry", entry.getEntryId());
		subscriptionSender.setReplyToAddress(fromAddress);
		subscriptionSender.setUserId(entry.getUserId());

		subscriptionSender.setPortletId(portletId);

		subscriptionSender.setScopeGroupId(entry.getGroupId());
		subscriptionSender.setServiceContext(serviceContext);

		for (String emailAddress : emailAddresses) {
			subscriptionSender.addRuntimeSubscribers(
				emailAddress, emailAddress);
		}

		subscriptionSender.flushNotificationsAsync();
	}

	protected void scheduleEntry(Entry entry, String reportName)
		throws PortalException {

		Trigger trigger = TriggerFactoryUtil.createTrigger(
			entry.getJobName(), entry.getSchedulerRequestName(),
			entry.getStartDate(), entry.getEndDate(), entry.getRecurrence());

		Message message = new Message();

		message.put("entryId", entry.getEntryId());
		message.put("reportName", reportName);

		_schedulerEngineHelper.schedule(
			trigger, StorageType.PERSISTED, null,
			"liferay/reports_scheduler_event", message, 0);
	}

	protected void validate(
			String emailNotifications, String emailDelivery, String reportName)
		throws PortalException {

		for (String emailAddress : StringUtil.split(emailNotifications)) {
			if (!Validator.isEmailAddress(emailAddress)) {
				throw new EntryEmailNotificationsException();
			}
		}

		for (String emailAddress : StringUtil.split(emailDelivery)) {
			if (!Validator.isEmailAddress(emailAddress)) {
				throw new EntryEmailDeliveryException();
			}
		}

		if (Validator.isNull(reportName)) {
			throw new DefinitionNameException();
		}
	}

	@ServiceReference(type = ConfigurationProvider.class)
	protected ConfigurationProvider configurationProvider;

	@ServiceReference(type = DLStore.class)
	private DLStore _dlStore;

	@ServiceReference(type = JSONFactory.class)
	private JSONFactory _jsonFactory;

	@ServiceReference(type = MessageBus.class)
	private MessageBus _messageBus;

	@ServiceReference(type = SchedulerEngineHelper.class)
	private SchedulerEngineHelper _schedulerEngineHelper;

	@ServiceReference(type = UserLocalService.class)
	private UserLocalService _userLocalService;

}