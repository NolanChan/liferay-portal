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

package com.liferay.osb.lcs.report.internal;

import com.liferay.osb.lcs.report.Report;
import com.liferay.osb.lcs.report.ReportFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ivica Cardic
 */
public class ReportFactoryImpl implements ReportFactory {

	public Report getReport(Type type) {
		return _reports.get(type);
	}

	public void setReports(Map<Type, Report> reports) {
		_reports = reports;
	}

	private Map<Type, Report> _reports = new HashMap<>();

}