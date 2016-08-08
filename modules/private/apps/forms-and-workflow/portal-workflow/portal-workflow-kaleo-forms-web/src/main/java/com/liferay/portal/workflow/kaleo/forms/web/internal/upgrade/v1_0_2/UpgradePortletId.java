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

package com.liferay.portal.workflow.kaleo.forms.web.internal.upgrade.v1_0_2;

import com.liferay.portal.kernel.model.LayoutTypePortletConstants;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.workflow.kaleo.forms.web.constants.KaleoFormsPortletKeys;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Inácio Nery
 */
public class UpgradePortletId
	extends com.liferay.portal.upgrade.util.UpgradePortletId {

	protected void deletePortletReferences(String portletId) throws Exception {
		runSQL("delete from Portlet where portletId = '" + portletId + "'");

		runSQL(
			"delete from PortletPreferences where portletId = '" + portletId +
				"'");

		runSQL("delete from ResourceAction where name = '" + portletId + "'");

		runSQL(
			"delete from ResourcePermission where name = '" + portletId + "'");

		removePortletIdFromLayouts(portletId);
	}

	@Override
	protected void doUpgrade() throws Exception {
		super.doUpgrade();

		deletePortletReferences("1_WAR_kaleoformsportlet");
	}

	protected String getNewTypeSettings(
		String typeSettings, String oldRootPortletId) {

		UnicodeProperties typeSettingsProperties = new UnicodeProperties(true);

		typeSettingsProperties.fastLoad(typeSettings);

		for (int i = 1; i <= 10; i++) {
			String column = LayoutTypePortletConstants.COLUMN_PREFIX + i;

			if (!typeSettingsProperties.containsKey(column)) {
				continue;
			}

			String[] portletIds = StringUtil.split(
				typeSettingsProperties.getProperty(column));

			portletIds = ArrayUtil.remove(portletIds, oldRootPortletId);

			typeSettingsProperties.setProperty(
				column, StringUtil.merge(portletIds).concat(StringPool.COMMA));
		}

		return typeSettingsProperties.toString();
	}

	@Override
	protected String[][] getRenamePortletIdsArray() {
		return new String[][] {
			new String[] {
				"2_WAR_kaleoformsportlet",
				KaleoFormsPortletKeys.KALEO_FORMS_ADMIN
			}
		};
	}

	protected void removePortletIdFromLayouts(String oldRootPortletId)
		throws Exception {

		String sql =
			"select plid, typeSettings from Layout where " +
				getTypeSettingsCriteria(oldRootPortletId);

		try (PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				long plid = rs.getLong("plid");
				String typeSettings = rs.getString("typeSettings");

				String newTypeSettings = getNewTypeSettings(
					typeSettings, oldRootPortletId);

				updateLayout(plid, newTypeSettings);
			}
		}
	}

}