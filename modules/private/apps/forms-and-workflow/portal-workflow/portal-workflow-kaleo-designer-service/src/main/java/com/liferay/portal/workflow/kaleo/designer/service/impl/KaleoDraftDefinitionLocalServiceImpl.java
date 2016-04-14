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

package com.liferay.portal.workflow.kaleo.designer.service.impl;

import com.liferay.portal.dao.orm.custom.sql.CustomSQLUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Junction;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionList;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowDefinition;
import com.liferay.portal.kernel.workflow.WorkflowDefinitionManagerUtil;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.workflow.kaleo.designer.exception.DuplicateKaleoDraftDefinitionNameException;
import com.liferay.portal.workflow.kaleo.designer.exception.KaleoDraftDefinitionContentException;
import com.liferay.portal.workflow.kaleo.designer.exception.KaleoDraftDefinitionNameException;
import com.liferay.portal.workflow.kaleo.designer.exception.NoSuchKaleoDraftDefinitionException;
import com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition;
import com.liferay.portal.workflow.kaleo.designer.service.base.KaleoDraftDefinitionLocalServiceBaseImpl;
import com.liferay.portal.workflow.kaleo.designer.util.KaleoDesignerUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Eduardo Lundgren
 * @author Marcellus Tavares
 */
public class KaleoDraftDefinitionLocalServiceImpl
	extends KaleoDraftDefinitionLocalServiceBaseImpl {

	public KaleoDraftDefinition addKaleoDraftDefinition(
			long userId, long groupId, String name,
			Map<Locale, String> titleMap, String content, int version,
			int draftVersion, ServiceContext serviceContext)
		throws PortalException {

		// Kaleo draft definition

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		validate(user.getCompanyId(), name, version, draftVersion);

		long kaleoDraftDefinitionId = counterLocalService.increment();

		KaleoDraftDefinition kaleoDraftDefinition =
			kaleoDraftDefinitionPersistence.create(kaleoDraftDefinitionId);

		kaleoDraftDefinition.setGroupId(groupId);
		kaleoDraftDefinition.setCompanyId(user.getCompanyId());
		kaleoDraftDefinition.setUserId(user.getUserId());
		kaleoDraftDefinition.setUserName(user.getFullName());
		kaleoDraftDefinition.setCreateDate(now);
		kaleoDraftDefinition.setModifiedDate(now);
		kaleoDraftDefinition.setName(name);
		kaleoDraftDefinition.setTitleMap(titleMap);
		kaleoDraftDefinition.setContent(content);
		kaleoDraftDefinition.setVersion(version);
		kaleoDraftDefinition.setDraftVersion(draftVersion);

		kaleoDraftDefinitionPersistence.update(kaleoDraftDefinition);

		// Resources

		resourceLocalService.addModelResources(
			kaleoDraftDefinition, serviceContext);

		return kaleoDraftDefinition;
	}

	@Override
	public KaleoDraftDefinition deleteKaleoDraftDefinition(
			KaleoDraftDefinition kaleoDraftDefinition)
		throws PortalException {

		// Kaleo draft definition

		kaleoDraftDefinitionPersistence.remove(kaleoDraftDefinition);

		// Resources

		resourceLocalService.deleteResource(
			kaleoDraftDefinition, ResourceConstants.SCOPE_COMPANY);

		return kaleoDraftDefinition;
	}

	public KaleoDraftDefinition deleteKaleoDraftDefinition(
			String name, int version, int draftVersion,
			ServiceContext serviceContext)
		throws PortalException {

		KaleoDraftDefinition kaleoDraftDefinition = getKaleoDraftDefinition(
			name, version, draftVersion, serviceContext);

		return deleteKaleoDraftDefinition(kaleoDraftDefinition);
	}

	public void deleteKaleoDraftDefinitions(
			String name, int version, ServiceContext serviceContext)
		throws PortalException {

		List<KaleoDraftDefinition> kaleoDraftDefinitions =
			kaleoDraftDefinitionPersistence.findByC_N_V(
				serviceContext.getCompanyId(), name, version);

		for (KaleoDraftDefinition kaleoDraftDefinition :
				kaleoDraftDefinitions) {

			deleteKaleoDraftDefinition(kaleoDraftDefinition);
		}
	}

	public KaleoDraftDefinition getKaleoDraftDefinition(
			String name, int version, int draftVersion,
			ServiceContext serviceContext)
		throws PortalException {

		return kaleoDraftDefinitionPersistence.findByC_N_V_D(
			serviceContext.getCompanyId(), name, version, draftVersion);
	}

	public List<KaleoDraftDefinition> getKaleoDraftDefinitions(
		String name, int version, int start, int end,
		OrderByComparator orderByComparator, ServiceContext serviceContext) {

		return kaleoDraftDefinitionPersistence.findByC_N_V(
			serviceContext.getCompanyId(), name, version, start, end,
			orderByComparator);
	}

	public int getKaleoDraftDefinitionsCount(
		String name, int version, ServiceContext serviceContext) {

		return kaleoDraftDefinitionPersistence.countByC_N_V(
			serviceContext.getCompanyId(), name, version);
	}

	public KaleoDraftDefinition getLatestKaleoDraftDefinition(
			String name, int version, ServiceContext serviceContext)
		throws PortalException {

		List<KaleoDraftDefinition> kaleoDraftDefinitions =
			kaleoDraftDefinitionPersistence.findByC_N_V(
				serviceContext.getCompanyId(), name, version, 0, 1);

		if (kaleoDraftDefinitions.isEmpty()) {
			throw new NoSuchKaleoDraftDefinitionException();
		}

		return kaleoDraftDefinitions.get(0);
	}

	public List<KaleoDraftDefinition> getLatestKaleoDraftDefinitions(
		long companyId, int version, int start, int end,
		OrderByComparator orderByComparator) {

		return getLatestKaleoDraftDefinitions(
			companyId, null, version, start, end, orderByComparator);
	}

	public List<KaleoDraftDefinition> getLatestKaleoDraftDefinitions(
		long companyId, String keywords, int version, int start, int end,
		OrderByComparator orderByComparator) {

		List<Long> kaleoDraftDefinitioIds = getKaleoDraftDefinitionIds(
			companyId, keywords, version);

		if (kaleoDraftDefinitioIds.isEmpty()) {
			return Collections.emptyList();
		}

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			KaleoDraftDefinition.class, getClassLoader());

		Property property = PropertyFactoryUtil.forName(
			"kaleoDraftDefinitionId");

		dynamicQuery.add(property.in(kaleoDraftDefinitioIds));

		return dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	public int getLatestKaleoDraftDefinitionsCount(
		long companyId, int version) {

		return getLatestKaleoDraftDefinitionsCount(companyId, null, version);
	}

	public int getLatestKaleoDraftDefinitionsCount(
		long companyId, String keywords, int version) {

		List<Long> kaleoDraftDefinitioIds = getKaleoDraftDefinitionIds(
			companyId, keywords, version);

		if (kaleoDraftDefinitioIds.isEmpty()) {
			return 0;
		}

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			KaleoDraftDefinition.class, getClassLoader());

		Property property = PropertyFactoryUtil.forName(
			"kaleoDraftDefinitionId");

		dynamicQuery.add(property.in(kaleoDraftDefinitioIds));

		return (int)dynamicQueryCount(dynamicQuery);
	}

	public KaleoDraftDefinition incrementKaleoDraftDefinitionDraftVersion(
			long userId, String name, int version,
			ServiceContext serviceContext)
		throws PortalException {

		KaleoDraftDefinition kaleoDraftDefinition =
			getLatestKaleoDraftDefinition(name, version, serviceContext);

		return addKaleoDraftDefinition(
			userId, kaleoDraftDefinition.getGroupId(),
			kaleoDraftDefinition.getName(), kaleoDraftDefinition.getTitleMap(),
			kaleoDraftDefinition.getContent(),
			kaleoDraftDefinition.getVersion(),
			kaleoDraftDefinition.getDraftVersion() + 1, serviceContext);
	}

	public KaleoDraftDefinition publishKaleoDraftDefinition(
			long userId, long groupId, String name,
			Map<Locale, String> titleMap, String content,
			ServiceContext serviceContext)
		throws PortalException {

		validate(content);

		WorkflowDefinition workflowDefinition =
			KaleoDesignerUtil.deployWorkflowDefinition(
				serviceContext.getCompanyId(), serviceContext.getUserId(),
				titleMap, content);

		int version = workflowDefinition.getVersion();

		KaleoDraftDefinition kaleoDraftDefinition = addKaleoDraftDefinition(
			userId, groupId, name, titleMap, content, version, 1,
			serviceContext);

		if (version == 1) {
			deleteKaleoDraftDefinitions(name, 0, serviceContext);
		}

		return kaleoDraftDefinition;
	}

	public KaleoDraftDefinition updateKaleoDraftDefinition(
			long userId, String name, Map<Locale, String> titleMap,
			String content, int version, ServiceContext serviceContext)
		throws PortalException {

		KaleoDraftDefinition kaleoDraftDefinition =
			incrementKaleoDraftDefinitionDraftVersion(
				userId, name, version, serviceContext);

		kaleoDraftDefinition.setTitleMap(titleMap);
		kaleoDraftDefinition.setContent(content);

		kaleoDraftDefinitionPersistence.update(kaleoDraftDefinition);

		return kaleoDraftDefinition;
	}

	protected void addKeywordsCriterion(
		DynamicQuery dynamicQuery, String keywords) {

		if (Validator.isNull(keywords)) {
			return;
		}

		Junction junction = RestrictionsFactoryUtil.disjunction();

		for (String keyword : CustomSQLUtil.keywords(keywords)) {
			junction.add(RestrictionsFactoryUtil.ilike("name", keyword));
			junction.add(RestrictionsFactoryUtil.ilike("title", keyword));
		}

		dynamicQuery.add(junction);
	}

	protected void addVersionCriterion(DynamicQuery dynamicQuery, int version) {
		if (version < 0) {
			return;
		}

		Property versionProperty = PropertyFactoryUtil.forName("version");

		dynamicQuery.add(versionProperty.eq(version));
	}

	protected List<Long> getKaleoDraftDefinitionIds(
		long companyId, int version) {

		return getKaleoDraftDefinitionIds(companyId, null, version);
	}

	protected List<Long> getKaleoDraftDefinitionIds(
		long companyId, String keywords, int version) {

		List<Long> kaleoDraftDefinitionIds = new ArrayList<>();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			KaleoDraftDefinition.class, getClassLoader());

		Property companyIdProperty = PropertyFactoryUtil.forName("companyId");

		dynamicQuery.add(companyIdProperty.eq(companyId));

		addKeywordsCriterion(dynamicQuery, keywords);
		addVersionCriterion(dynamicQuery, version);

		ProjectionList projectionList = ProjectionFactoryUtil.projectionList();

		projectionList.add(ProjectionFactoryUtil.max("kaleoDraftDefinitionId"));
		projectionList.add(ProjectionFactoryUtil.groupProperty("name"));

		dynamicQuery.setProjection(projectionList);

		List<Object[]> results = dynamicQuery(dynamicQuery);

		for (Object[] result : results) {
			kaleoDraftDefinitionIds.add((Long)result[0]);
		}

		return kaleoDraftDefinitionIds;
	}

	protected void validate(
			long companyId, String name, int version, int draftVersion)
		throws PortalException {

		if (Validator.isNull(name)) {
			throw new KaleoDraftDefinitionNameException();
		}

		if (kaleoDraftDefinitionPersistence.countByC_N_V_D(
				companyId, name, version, draftVersion) > 0) {

			throw new DuplicateKaleoDraftDefinitionNameException();
		}
	}

	protected void validate(
			long companyId, String name, String content, int version,
			int draftVersion)
		throws PortalException {

		validate(companyId, name, version, draftVersion);
		validate(content);
	}

	protected void validate(String content) throws PortalException {
		try {
			WorkflowDefinitionManagerUtil.validateWorkflowDefinition(
				content.getBytes());
		}
		catch (WorkflowException we) {
			throw new KaleoDraftDefinitionContentException(we);
		}
	}

}