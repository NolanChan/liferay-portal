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

import com.liferay.osb.lcs.report.internal.LCSClusterNodeUptimesDelimitedReport;
import com.liferay.osb.lcs.report.internal.LCSClusterNodeUptimesInvoicePDFReport;
import com.liferay.osb.lcs.report.internal.LCSClusterNodeUptimesPDFReport;

/**
 * @author Igor Beslic
 */
public enum ReportType {

	LCS_CLUSTER_NODE_UPTIMES_DELIMITED(
		LCSClusterNodeUptimesDelimitedReport.class),
	LCS_CLUSTER_NODE_UPTIMES_INVOICE_PDF(
		LCSClusterNodeUptimesInvoicePDFReport.class),
	LCS_CLUSTER_NODE_UPTIMES_PDF(LCSClusterNodeUptimesPDFReport.class);

	public Class<?> getReportClass() {
		return _class;
	}

	private ReportType(Class<?> clazz) {
		_class = clazz;
	}

	private final Class<?> _class;

}