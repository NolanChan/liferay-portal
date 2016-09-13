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

import com.liferay.lcs.util.LCSConstants;
import com.liferay.osb.lcs.model.LCSClusterNodeUptime;
import com.liferay.osb.lcs.service.LCSClusterNodeUptimeService;
import com.liferay.osb.lcs.subscriptions.util.SubscriptionsUtil;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;

import java.util.List;

/**
 * @author Ivica Cardic
 * @author Matija Petanjek
 */
public class LCSClusterNodeUptimesDelimitedReport extends BaseReport {

	@Override
	public ByteArrayOutputStream process(ReportContext reportContext)
		throws Exception {

		ByteArrayOutputStream byteArrayOutputStream =
			new ByteArrayOutputStream();

		BufferedWriter bufferedWriter = new BufferedWriter(
			new OutputStreamWriter(byteArrayOutputStream));

		long lcsClusterEntryId = GetterUtil.get(
			reportContext.getParameter("lcsClusterEntryId"),
			LCSConstants.ALL_LCS_CLUSTER_OBJECTS_ID);
		long lcsClusterNodeId = GetterUtil.get(
			reportContext.getParameter("lcsClusterNodeId"),
			LCSConstants.ALL_LCS_CLUSTER_OBJECTS_ID);
		long lcsProjectId = GetterUtil.get(
			reportContext.getParameter("lcsProjectId"),
			LCSConstants.ALL_LCS_CLUSTER_OBJECTS_ID);
		int month = GetterUtil.get(
			reportContext.getParameter("month"),
			LCSConstants.ALL_LCS_CLUSTER_OBJECTS_ID);
		int year = GetterUtil.get(
			reportContext.getParameter("year"),
			LCSConstants.ALL_LCS_CLUSTER_OBJECTS_ID);

		List<LCSClusterNodeUptime> lcsClusterNodeUptimes =
			_lcsClusterNodeUptimeService.getMonthlyLCSClusterNodeUptimes(
				lcsClusterEntryId, lcsClusterNodeId, lcsProjectId, month, year,
				true, true);

		for (LCSClusterNodeUptime lcsClusterNodeUptime :
				lcsClusterNodeUptimes) {

			bufferedWriter.write(lcsClusterNodeUptime.getLcsProjectName());
			bufferedWriter.write(StringPool.SEMICOLON);
			bufferedWriter.write(lcsClusterNodeUptime.getLcsClusterEntryName());
			bufferedWriter.write(StringPool.SEMICOLON);
			bufferedWriter.write(lcsClusterNodeUptime.getLcsClusterNodeName());
			bufferedWriter.write(StringPool.SEMICOLON);
			bufferedWriter.write(
				reportContext.formatTime(lcsClusterNodeUptime.getStartTime()));
			bufferedWriter.write(StringPool.SEMICOLON);
			bufferedWriter.write(
				reportContext.formatTime(lcsClusterNodeUptime.getEndTime()));
			bufferedWriter.write(StringPool.SEMICOLON);
			bufferedWriter.write(
				SubscriptionsUtil.formatUptime(
					lcsClusterNodeUptime.getStartTime(),
					lcsClusterNodeUptime.getEndTime(),
					reportContext.getLocale(),
					reportContext.getPortletConfig()));
			bufferedWriter.write(StringPool.SEMICOLON);
			bufferedWriter.write(reportContext.getLineSeparator());

			bufferedWriter.flush();
		}

		return byteArrayOutputStream;
	}

	@BeanReference(type = LCSClusterNodeUptimeService.class)
	private LCSClusterNodeUptimeService _lcsClusterNodeUptimeService;

}