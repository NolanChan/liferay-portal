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

import com.liferay.osb.lcs.advisor.SubscriptionsAdvisor;
import com.liferay.osb.lcs.model.LCSClusterNodeUptime;
import com.liferay.osb.lcs.report.ReportContext;
import com.liferay.osb.lcs.service.LCSClusterNodeUptimeService;
import com.liferay.portal.kernel.util.StringPool;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;

import java.util.List;

import org.osgi.service.component.annotations.Reference;

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

		long lcsClusterEntryId = reportContext.getLcsClusterEntryId();
		long lcsClusterNodeId = reportContext.getLcsClusterNodeId();
		long lcsProjectId = reportContext.getLcsProjectId();
		int month = reportContext.getMonth();
		int year = reportContext.getYear();

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
				_subscriptionsAdvisor.formatUptime(
					lcsClusterNodeUptime.getStartTime(),
					lcsClusterNodeUptime.getEndTime(),
					reportContext.getLocale()));
			bufferedWriter.write(StringPool.SEMICOLON);
			bufferedWriter.write(reportContext.getLineSeparator());

			bufferedWriter.flush();
		}

		return byteArrayOutputStream;
	}

	@Reference(unbind = "-")
	public void setLCSClusterNodeUptimeService(
		LCSClusterNodeUptimeService lcsClusterNodeUptimeService) {

		_lcsClusterNodeUptimeService = lcsClusterNodeUptimeService;
	}

	@Reference(unbind = "-")
	public void setSubscriptionsAdvisor(
		SubscriptionsAdvisor subscriptionsAdvisor) {

		_subscriptionsAdvisor = subscriptionsAdvisor;
	}

	private LCSClusterNodeUptimeService _lcsClusterNodeUptimeService;
	private SubscriptionsAdvisor _subscriptionsAdvisor;

}