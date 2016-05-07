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

package com.liferay.portal.workflow.kaleo.forms.web.upgrade.v1_0_2;

import com.liferay.dynamic.data.mapping.service.DDMTemplateLinkLocalService;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess;
import com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Rafael Praxedes
 */
public class UpgradeKaleoProcessTemplateLink extends UpgradeProcess {

	public UpgradeKaleoProcessTemplateLink(
		ClassNameLocalService classNameLocalService,
		DDMTemplateLinkLocalService ddmTemplateLinkLocalService) {

		_classNameLocalService = classNameLocalService;
		_ddmTemplateLinkLocalService = ddmTemplateLinkLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		updateKaleoProcess();
		updateKaleoProcessLink();
	}

	protected void updateKaleoProcess() throws SQLException {
		try (PreparedStatement ps = connection.prepareStatement(
				"select * from KaleoProcess");
			ResultSet rs = ps.executeQuery()) {

			long kaleoProcessClassNameId =
				_classNameLocalService.getClassNameId(
					KaleoProcess.class.getName());

			while (rs.next()) {
				long kaleoProcessLinkId = rs.getLong("kaleoProcessId");
				long ddmTemplateId = rs.getLong("DDMTemplateId");

				if (ddmTemplateId > 0) {
					_ddmTemplateLinkLocalService.addTemplateLink(
						kaleoProcessClassNameId, kaleoProcessLinkId,
						ddmTemplateId);
				}
			}
		}
	}

	protected void updateKaleoProcessLink() throws SQLException {
		try (PreparedStatement ps = connection.prepareStatement(
				"select * from KaleoProcessLink");
			ResultSet rs = ps.executeQuery()) {

			long kaleoProcessLinkClassNameId =
				_classNameLocalService.getClassNameId(
					KaleoProcessLink.class.getName());

			while (rs.next()) {
				long kaleoProcessLinkId = rs.getLong("kaleoProcessLinkId");
				long ddmTemplateId = rs.getLong("DDMTemplateId");

				if (ddmTemplateId > 0) {
					_ddmTemplateLinkLocalService.addTemplateLink(
						kaleoProcessLinkClassNameId, kaleoProcessLinkId,
						ddmTemplateId);
				}
			}
		}
	}

	private final ClassNameLocalService _classNameLocalService;
	private final DDMTemplateLinkLocalService _ddmTemplateLinkLocalService;

}