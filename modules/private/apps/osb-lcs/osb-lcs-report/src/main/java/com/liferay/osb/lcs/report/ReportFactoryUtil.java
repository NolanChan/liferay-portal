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

package com.liferay.osb.lcs.report;

import com.liferay.osb.lcs.report.Report;

/**
 * @author Ivica Cardic
 */
public class ReportFactoryUtil {

	public static Report getReport(ReportFactory.Type type) {
		return _reportFactory.getReport(type);
	}

	public void setReportFactory(ReportFactory reportFactory) {
		_reportFactory = reportFactory;
	}

	private static ReportFactory _reportFactory;

}