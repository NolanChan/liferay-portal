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

package com.liferay.portal.workflow.kaleo.forms.web.upgrade.v1_0_1;

import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.dynamic.data.lists.service.DDLRecordSetLocalService;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.upgrade.util.UpgradeProcessUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.util.Locale;

/**
 * @author Inácio Nery
 */
public class UpgradeKaleoProcess extends UpgradeProcess {

	public UpgradeKaleoProcess(
		AssetEntryLocalService assetEntryLocalService,
		DDLRecordSetLocalService ddlRecordSetLocalService) {

		_assetEntryLocalService = assetEntryLocalService;
		_ddlRecordSetLocalService = ddlRecordSetLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"select kaleoProcessId, groupId, companyId, userId, " +
					"createDate, modifiedDate, DDLRecordSetId, uuid_ from " +
						"KaleoProcess");

			rs = ps.executeQuery();

			while (rs.next()) {
				long kaleoProcessId = rs.getLong("kaleoProcessId");
				long groupId = rs.getLong("groupId");
				long companyId = rs.getLong("companyId");
				long userId = rs.getLong("userId");
				Timestamp createDate = rs.getTimestamp("createDate");
				Timestamp modifiedDate = rs.getTimestamp("modifiedDate");
				long ddlRecordSetId = rs.getLong("DDLRecordSetId");
				String uuid = rs.getString("uuid_");

				if (Validator.isNull(uuid)) {
					uuid = PortalUUIDUtil.generate();

					runSQL(
						"update KaleoProcess set uuid_ = '" + uuid +
							"' where kaleoProcessId = " + kaleoProcessId);
				}

				updateAsset(
					kaleoProcessId, groupId, companyId, userId, createDate,
					modifiedDate, ddlRecordSetId, uuid);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	protected Locale getDefaultLocale(long companyId) {
		String locale = null;

		try {
			locale = UpgradeProcessUtil.getDefaultLanguageId(companyId);
		}
		catch (SQLException sqle) {
			_log.error(
				"Unable to get default locale for company " + companyId, sqle);

			throw new RuntimeException(sqle);
		}

		return LocaleUtil.fromLanguageId(locale);
	}

	protected void updateAsset(
			long kaleoProcessId, long groupId, long companyId, long userId,
			Timestamp createDate, Timestamp modifiedDate, long ddlRecordSetId,
			String uuid)
		throws PortalException {

		Locale locale = getDefaultLocale(companyId);
		DDLRecordSet ddlRecordSet = _ddlRecordSetLocalService.getDDLRecordSet(
			ddlRecordSetId);

		boolean visible = true;

		DDMStructure ddmStructure = ddlRecordSet.getDDMStructure();

		String ddmStructureName = ddmStructure.getName(locale);

		String recordSetName = ddlRecordSet.getName(locale);

		String title = LanguageUtil.format(
			locale, "new-x-for-list-x",
			new Object[] {ddmStructureName, recordSetName}, false);

		_assetEntryLocalService.updateEntry(
			userId, groupId, createDate, modifiedDate,
			KaleoProcess.class.getName(), kaleoProcessId, uuid, 0, null, null,
			true, visible, null, null, null, ContentTypes.TEXT_HTML, title,
			null, StringPool.BLANK, null, null, 0, 0, null);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UpgradeKaleoProcess.class);

	private final AssetEntryLocalService _assetEntryLocalService;
	private final DDLRecordSetLocalService _ddlRecordSetLocalService;

}