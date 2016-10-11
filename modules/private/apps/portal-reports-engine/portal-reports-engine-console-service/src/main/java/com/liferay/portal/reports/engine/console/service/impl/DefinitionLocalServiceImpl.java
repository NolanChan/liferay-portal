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

package com.liferay.portal.reports.engine.console.service.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.document.library.kernel.store.DLStore;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Junction;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.reports.engine.console.exception.DefinitionFileException;
import com.liferay.portal.reports.engine.console.exception.DefinitionNameException;
import com.liferay.portal.reports.engine.console.model.Definition;
import com.liferay.portal.reports.engine.console.service.base.DefinitionLocalServiceBaseImpl;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.InputStream;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Gavin Wan
 */
@ProviderType
public class DefinitionLocalServiceImpl extends DefinitionLocalServiceBaseImpl {

	public Definition addDefinition(
			long userId, long groupId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, long sourceId,
			String reportParameters, String fileName, InputStream inputStream,
			ServiceContext serviceContext)
		throws PortalException {

		// Definition

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		validate(nameMap);

		long definitionId = counterLocalService.increment();

		Definition definition = definitionPersistence.create(definitionId);

		definition.setUuid(serviceContext.getUuid());
		definition.setGroupId(groupId);
		definition.setCompanyId(user.getCompanyId());
		definition.setUserId(user.getUserId());
		definition.setUserName(user.getFullName());
		definition.setCreateDate(serviceContext.getCreateDate(now));
		definition.setModifiedDate(serviceContext.getModifiedDate(now));
		definition.setNameMap(nameMap);
		definition.setDescriptionMap(descriptionMap);
		definition.setSourceId(sourceId);
		definition.setReportName(
			StringUtil.extractFirst(fileName, StringPool.PERIOD));
		definition.setReportParameters(reportParameters);

		definitionPersistence.update(definition);

		// Resources

		resourceLocalService.addModelResources(definition, serviceContext);

		// Attachments

		if (Validator.isNotNull(fileName) && (inputStream != null)) {
			addDefinitionFile(
				user.getCompanyId(), definition, fileName, inputStream);
		}
		else {
			throw new DefinitionFileException.InvalidDefinitionFile(
				fileName, inputStream == null);
		}

		return definition;
	}

	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public Definition deleteDefinition(Definition definition)
		throws PortalException {

		// Definition

		definitionPersistence.remove(definition);

		// Resources

		resourceLocalService.deleteResource(
			definition.getCompanyId(), Definition.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL, definition.getDefinitionId());

		// Attachments

		deleteDefinitionTemplates(
			definition.getCompanyId(), definition.getAttachmentsDir());

		return definition;
	}

	@Override
	public Definition deleteDefinition(long definitionId)
		throws PortalException {

		Definition definition = definitionPersistence.findByPrimaryKey(
			definitionId);

		return definitionLocalService.deleteDefinition(definition);
	}

	@Override
	public void deleteDefinitions(long groupId) throws PortalException {
		List<Definition> definitions = definitionPersistence.findByGroupId(
			groupId);

		for (Definition definition : definitions) {
			definitionLocalService.deleteDefinition(definition);
		}
	}

	@Override
	public void deleteDefinitionTemplates(
			long companyId, String attachmentsDirectory)
		throws PortalException {

		_dlStore.deleteDirectory(
			companyId, CompanyConstants.SYSTEM, attachmentsDirectory);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Definition> getDefinitions(
		long groupId, String definitionName, String description,
		String sourceId, String reportName, boolean andSearch, int start,
		int end, OrderByComparator orderByComparator) {

		DynamicQuery dynamicQuery = buildDynamicQuery(
			groupId, definitionName, description, sourceId, reportName,
			andSearch);

		return dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	@Override
	public int getDefinitionsCount(
		long groupId, String definitionName, String description,
		String sourceId, String reportName, boolean andSearch) {

		DynamicQuery dynamicQuery = buildDynamicQuery(
			groupId, definitionName, description, sourceId, reportName,
			andSearch);

		return (int)dynamicQueryCount(dynamicQuery);
	}

	@Override
	public Definition updateDefinition(
			long definitionId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, long sourceId,
			String reportParameters, String fileName, InputStream inputStream,
			ServiceContext serviceContext)
		throws PortalException {

		// Definition

		Definition definition = definitionPersistence.findByPrimaryKey(
			definitionId);

		validate(nameMap);

		definition.setModifiedDate(serviceContext.getModifiedDate(null));
		definition.setNameMap(nameMap);
		definition.setDescriptionMap(descriptionMap);
		definition.setSourceId(sourceId);

		if (Validator.isNotNull(fileName)) {
			definition.setReportName(
				StringUtil.extractFirst(fileName, StringPool.PERIOD));
		}

		definition.setReportParameters(reportParameters);

		definitionPersistence.update(definition);

		// Resources

		if ((serviceContext.getGroupPermissions() != null) ||
			(serviceContext.getGuestPermissions() != null)) {

			updateDefinitionResources(
				definition, serviceContext.getGroupPermissions(),
				serviceContext.getGuestPermissions());
		}

		// Attachments

		if (Validator.isNotNull(fileName) && (inputStream != null)) {
			long companyId = definition.getCompanyId();

			_dlStore.deleteDirectory(
				companyId, CompanyConstants.SYSTEM,
				definition.getAttachmentsDir());

			addDefinitionFile(companyId, definition, fileName, inputStream);
		}

		return definition;
	}

	@Override
	public void updateDefinitionResources(
			Definition definition, String[] communityPermissions,
			String[] guestPermissions)
		throws PortalException {

		resourceLocalService.updateResources(
			definition.getCompanyId(), definition.getGroupId(),
			Definition.class.getName(), definition.getDefinitionId(),
			communityPermissions, guestPermissions);
	}

	protected void addDefinitionFile(
			long companyId, Definition definition, String fileName,
			InputStream inputStream)
		throws PortalException {

		String directoryName = definition.getAttachmentsDir();

		_dlStore.addDirectory(
			companyId, CompanyConstants.SYSTEM, directoryName);

		String fileLocation = directoryName.concat(
			StringPool.SLASH).concat(fileName);

		_dlStore.addFile(
			companyId, CompanyConstants.SYSTEM, fileLocation, false,
			inputStream);
	}

	protected DynamicQuery buildDynamicQuery(
		long groupId, String definitionName, String description,
		String sourceId, String reportName, boolean andSearch) {

		Junction junction = null;

		if (andSearch) {
			junction = RestrictionsFactoryUtil.conjunction();
		}
		else {
			junction = RestrictionsFactoryUtil.disjunction();
		}

		if (Validator.isNotNull(definitionName)) {
			Property property = PropertyFactoryUtil.forName("name");

			String value =
				StringPool.PERCENT + definitionName + StringPool.PERCENT;

			junction.add(property.like(value));
		}

		if (Validator.isNotNull(description)) {
			Property property = PropertyFactoryUtil.forName("description");

			String value =
				StringPool.PERCENT + description + StringPool.PERCENT;

			junction.add(property.like(value));
		}

		if (Validator.isNotNull(sourceId)) {
			Property property = PropertyFactoryUtil.forName("sourceId");

			junction.add(property.eq(GetterUtil.getLong(sourceId)));
		}

		if (Validator.isNotNull(reportName)) {
			Property property = PropertyFactoryUtil.forName("reportName");

			String value = StringPool.PERCENT + reportName + StringPool.PERCENT;

			junction.add(property.like(value));
		}

		DynamicQuery dynamicQuery = dynamicQuery();

		if (groupId > 0) {
			Property property = PropertyFactoryUtil.forName("groupId");

			dynamicQuery.add(property.eq(groupId));
		}

		dynamicQuery.add(junction);

		return dynamicQuery;
	}

	protected void validate(Map<Locale, String> nameMap)
		throws PortalException {

		Locale locale = LocaleUtil.getDefault();

		String name = nameMap.get(locale);

		if (Validator.isNull(name)) {
			throw new DefinitionNameException.NullDefinitionFileName();
		}
	}

	@ServiceReference(type = DLStore.class)
	private DLStore _dlStore;

}