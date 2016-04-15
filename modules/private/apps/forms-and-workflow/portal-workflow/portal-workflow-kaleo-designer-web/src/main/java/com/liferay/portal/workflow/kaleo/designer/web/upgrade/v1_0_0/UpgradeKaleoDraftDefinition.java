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

package com.liferay.portal.workflow.kaleo.designer.web.upgrade.v1_0_0;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.ResourceLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition;
import com.liferay.portal.workflow.kaleo.designer.service.KaleoDraftDefinitionLocalService;

/**
 * @author Leonardo Barros
 */
public class UpgradeKaleoDraftDefinition extends UpgradeProcess {

	public UpgradeKaleoDraftDefinition(
		CompanyLocalService companyLocalService,
		KaleoDraftDefinitionLocalService kaleoDraftDefinitionLocalService,
		ResourceLocalService resourceLocalService) {

		_companyLocalService = companyLocalService;
		_kaleoDraftDefinitionLocalService = kaleoDraftDefinitionLocalService;
		_resourceLocalService = resourceLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		ActionableDynamicQuery actionableDynamicQuery =
			_kaleoDraftDefinitionLocalService.getActionableDynamicQuery();

		final ServiceContext serviceContext = new ServiceContext();

		actionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {

				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					Property groupIdProperty = PropertyFactoryUtil.forName(
						"groupId");

					dynamicQuery.add(groupIdProperty.eq(Long.valueOf(0)));
				}

			});
		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.
				PerformActionMethod<KaleoDraftDefinition>() {

				@Override
				public void performAction(
						KaleoDraftDefinition kaleoDraftDefinition)
					throws PortalException {

					long companyId = kaleoDraftDefinition.getCompanyId();

					Company company = _companyLocalService.getCompany(
						companyId);

					kaleoDraftDefinition.setGroupId(company.getGroupId());

					_kaleoDraftDefinitionLocalService.
						updateKaleoDraftDefinition(kaleoDraftDefinition);

					_resourceLocalService.addModelResources(
						kaleoDraftDefinition, serviceContext);
				}

			});

		actionableDynamicQuery.performActions();
	}

	private final CompanyLocalService _companyLocalService;
	private final KaleoDraftDefinitionLocalService
		_kaleoDraftDefinitionLocalService;
	private final ResourceLocalService _resourceLocalService;

}