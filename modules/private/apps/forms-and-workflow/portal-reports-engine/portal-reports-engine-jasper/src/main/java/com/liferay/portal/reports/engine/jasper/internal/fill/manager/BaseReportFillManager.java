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

package com.liferay.portal.reports.engine.jasper.internal.fill.manager;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.reports.engine.ReportRequest;
import com.liferay.portal.reports.engine.ReportRequestContext;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import java.sql.Connection;

import java.text.DateFormat;
import java.text.ParseException;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

/**
 * @author Gavin Wan
 * @author Brian Wing Shun Chan
 */
public abstract class BaseReportFillManager implements ReportFillManager {

	public JasperPrint fillReport(
			JasperReport jasperReport, ReportRequest reportRequest)
		throws JRException {

		Connection connection = null;

		try {
			connection = getConnection(reportRequest);
		}
		catch (JRException jre) {
			throw jre;
		}
		catch (Exception e) {
			throw new JRException(e);
		}

		Map<String, Object> reportParameters = getReportParameters(
			jasperReport, reportRequest);

		if (connection != null) {
			try {
				return JasperFillManager.fillReport(
					jasperReport, reportParameters, connection);
			}
			finally {
				DataAccess.cleanUp(connection);
			}
		}

		JRDataSource jrDataSource = null;

		try {
			jrDataSource = getJRDataSource(reportRequest);
		}
		catch (JRException jre) {
			throw jre;
		}
		catch (Exception e) {
			throw new JRException(e);
		}

		if (jrDataSource != null) {
			return JasperFillManager.fillReport(
				jasperReport, reportParameters, jrDataSource);
		}

		return JasperFillManager.fillReport(jasperReport, reportParameters);
	}

	protected Connection getConnection(ReportRequest reportRequest)
		throws Exception {

		return null;
	}

	protected InputStream getDataSourceByteArrayInputStream(
		ReportRequest reportRequest) {

		ReportRequestContext reportRequestContext =
			reportRequest.getReportRequestContext();

		byte[] dataSourceByteArray = (byte[])reportRequestContext.getAttribute(
			ReportRequestContext.DATA_SOURCE_BYTE_ARRAY);

		return new ByteArrayInputStream(dataSourceByteArray);
	}

	protected String getDataSourceCharSet(ReportRequest reportRequest) {
		ReportRequestContext reportRequestContext =
			reportRequest.getReportRequestContext();

		String dataSourceCharSet = GetterUtil.getString(
			(String)reportRequestContext.getAttribute(
				ReportRequestContext.DATA_SOURCE_CHARSET),
			StringPool.UTF8);

		return dataSourceCharSet;
	}

	protected String[] getDataSourceColumnNames(ReportRequest reportRequest) {
		ReportRequestContext reportRequestContext =
			reportRequest.getReportRequestContext();

		String dataSourceColumnNames =
			(String)reportRequestContext.getAttribute(
				ReportRequestContext.DATA_SOURCE_COLUMN_NAMES);

		if (Validator.isNotNull(dataSourceColumnNames)) {
			return StringUtil.split(dataSourceColumnNames, StringPool.COMMA);
		}
		else {
			return null;
		}
	}

	protected JRDataSource getJRDataSource(ReportRequest reportRequest)
		throws Exception {

		return null;
	}

	protected Map<String, Object> getReportParameters(
		JasperReport jasperReport, ReportRequest reportRequest) {

		Map<String, Object> reportParameters = new HashMap<>();

		Map<String, String> stringReportParameters =
			reportRequest.getReportParameters();

		JRParameter[] jrParameters = jasperReport.getParameters();

		for (JRParameter jrParameter : jrParameters) {
			String name = jrParameter.getName();
			Class<?> clazz = jrParameter.getValueClass();

			String stringValue = stringReportParameters.get(name);

			Object value = stringValue;

			if (clazz.equals(Date.class)) {
				DateFormat dateFormat =
					DateFormatFactoryUtil.getSimpleDateFormat("yyyy-MM-dd");

				try {
					value = dateFormat.parse(stringValue);
				}
				catch (ParseException pe) {
					_log.error(stringValue + " is not yyyy-MM-dd");
				}
			}
			else if (clazz.equals(List.class)) {
				List<String> listValue = ListUtil.fromArray(
					StringUtil.split(stringValue));

				value = new ArrayList<>(listValue);
			}

			if (value != null) {
				reportParameters.put(name, value);
			}
		}

		return reportParameters;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		BaseReportFillManager.class);

}