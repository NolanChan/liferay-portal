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

package com.liferay.portal.workflow.kaleo.forms.web.upgrade;

import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.dynamic.data.lists.service.DDLRecordSetLocalService;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLinkLocalService;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;
import com.liferay.portal.workflow.kaleo.forms.web.upgrade.v1_0_2.UpgradeKaleoProcessTemplateLink;
import com.liferay.portal.workflow.kaleo.forms.web.upgrade.v1_0_2.UpgradePortletId;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marcellus Tavares
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class KaleoFormsWebUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register(
			"com.liferay.portal.workflow.kaleo.forms.web", "0.0.1", "1.0.0",
			new com.liferay.portal.workflow.kaleo.forms.web.upgrade.v1_0_0.
				UpgradeSchema());

		registry.register(
			"com.liferay.portal.workflow.kaleo.forms.web", "1.0.0", "1.0.1",
			new com.liferay.portal.workflow.kaleo.forms.web.upgrade.v1_0_1.
				UpgradeKaleoProcess(),
			new com.liferay.portal.workflow.kaleo.forms.web.upgrade.v1_0_1.
				UpgradeSchema());

		registry.register(
			"com.liferay.portal.workflow.kaleo.forms.web", "1.0.1", "1.0.2",
			new com.liferay.portal.workflow.kaleo.forms.web.upgrade.v1_0_2.
				UpgradeKaleoProcess(
					_assetEntryLocalService, _ddlRecordSetLocalService),
			new UpgradeKaleoProcessTemplateLink(
				_classNameLocalService, _ddmTemplateLinkLocalService),
			new UpgradePortletId());
	}

	@Reference(unbind = "-")
	public void setAssetEntryLocalService(
		AssetEntryLocalService assetEntryLocalService) {

		_assetEntryLocalService = assetEntryLocalService;
	}

	@Reference(unbind = "-")
	public void setDDLRecordSetLocalService(
		DDLRecordSetLocalService ddlRecordSetLocalService) {

		_ddlRecordSetLocalService = ddlRecordSetLocalService;
	}

	@Reference(unbind = "-")
	public void setDDMTemplateLinkLocalService(
		DDMTemplateLinkLocalService ddmTemplateLinkLocalService) {

		_ddmTemplateLinkLocalService = ddmTemplateLinkLocalService;
	}

	@Reference(unbind = "-")
	protected void setClassNameLocalService(
		ClassNameLocalService classNameLocalService) {

		_classNameLocalService = classNameLocalService;
	}

	private AssetEntryLocalService _assetEntryLocalService;
	private ClassNameLocalService _classNameLocalService;
	private DDLRecordSetLocalService _ddlRecordSetLocalService;
	private DDMTemplateLinkLocalService _ddmTemplateLinkLocalService;

}