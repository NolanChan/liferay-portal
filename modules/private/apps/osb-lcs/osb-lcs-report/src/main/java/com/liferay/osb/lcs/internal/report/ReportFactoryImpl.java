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

package com.liferay.osb.lcs.internal.report;

import com.liferay.osb.lcs.report.Report;
import com.liferay.osb.lcs.report.ReportFactory;
import com.liferay.osb.lcs.report.ReportType;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author Ivica Cardic
 */
@Component(immediate = true, service = ReportFactory.class)
public class ReportFactoryImpl implements ReportFactory {

	@Activate
	public void activate() {
		for (ReportType reportType : ReportType.values()) {
			Class<?> reportClass = reportType.getReportClass();

			try {
				_reports.put(reportType, (Report)reportClass.newInstance());
			}
			catch (Exception e) {
				_log.error(
					"Unable to initalize report type " + reportType.name());
			}
		}
	}

	public Report getReport(ReportType reportType) {
		return _reports.get(reportType);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ReportFactoryImpl.class);

	private final Map<ReportType, Report> _reports = new HashMap<>();

}