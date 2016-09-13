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

/**
 * @author Ivica Cardic
 */
public interface ReportFactory {

	public Report getReport(Type type);

	public enum Type {

		LCS_CLUSTER_NODE_DELIMITED, LCS_CLUSTER_NODE_UPTIMES_DELIMITED,
		LCS_CLUSTER_NODE_UPTIMES_INVOICE_PDF, LCS_CLUSTER_NODE_UPTIMES_PDF

	}

}