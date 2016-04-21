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

package com.liferay.portal.reports.upgrade.v1_0_0;

import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Wesley Gong
 * @author Calvin Keum
 */
public class UpgradeReportDefinition extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		if (hasTable("Definition")) {
			updateReportDefinitions();
		}
	}

	protected void updateReportDefinitions() throws Exception {
		try (PreparedStatement ps1 = connection.prepareStatement(
				"select companyId, definitionId, reportParameters from " +
					"Definition")) {

			try (PreparedStatement ps2 =
					AutoBatchPreparedStatementUtil.concurrentAutoBatch(
						connection,
						"update Definition set reportParameters = ? where " +
							"companyId = ? and definitionId = ?");
				ResultSet rs = ps1.executeQuery()) {

				while (rs.next()) {
					long companyId = rs.getLong("companyId");
					long definitionId = rs.getLong("definitionId");
					String reportParameters = rs.getString("reportParameters");

					String updatedReportParameters = updateReportParameters(
						reportParameters);

					if (Validator.isNotNull(reportParameters) &&
						reportParameters.equals(updatedReportParameters)) {

						continue;
					}

					ps2.setString(1, updatedReportParameters);

					ps2.setString(1, reportParameters);
					ps2.setLong(2, companyId);
					ps2.setLong(3, definitionId);

					ps2.addBatch();
				}

				ps2.executeBatch();
			}
		}
	}

	protected String updateReportParameters(String reportParameters) {
		Matcher matcher = _pattern.matcher(reportParameters);

		if (!matcher.find()) {
			return reportParameters;
		}

		JSONArray reportParametersJSONArray = JSONFactoryUtil.createJSONArray();

		String[] keyValuePairs = StringUtil.split(reportParameters);

		for (String keyValuePair : keyValuePairs) {
			if (Validator.isNull(keyValuePair) ||
				!keyValuePair.contains(StringPool.EQUAL)) {

				continue;
			}

			JSONObject reportParameterJSONObject =
				JSONFactoryUtil.createJSONObject();

			reportParameterJSONObject.put(
				"key", keyValuePair.split(StringPool.EQUAL)[0]);
			reportParameterJSONObject.put(
				"type", keyValuePair.split(StringPool.EQUAL)[2]);
			reportParameterJSONObject.put(
				"value", keyValuePair.split(StringPool.EQUAL)[1]);

			reportParametersJSONArray.put(reportParameterJSONObject);
		}

		return reportParametersJSONArray.toString();
	}

	private static final Pattern _pattern = Pattern.compile("[.*=.*=.*]+");

}