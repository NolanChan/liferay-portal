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

package com.liferay.portal.reports.engine.jasper.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.Sync;
import com.liferay.portal.kernel.test.rule.SynchronousDestinationTestRule;
import com.liferay.portal.reports.engine.ByteArrayReportResultContainer;
import com.liferay.portal.reports.engine.MemoryReportDesignRetriever;
import com.liferay.portal.reports.engine.ReportDataSourceType;
import com.liferay.portal.reports.engine.ReportDesignRetriever;
import com.liferay.portal.reports.engine.ReportEngine;
import com.liferay.portal.reports.engine.ReportFormat;
import com.liferay.portal.reports.engine.ReportRequest;
import com.liferay.portal.reports.engine.ReportRequestContext;
import com.liferay.portal.reports.engine.ReportResultContainer;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceReference;

import java.io.InputStream;

import java.util.Date;
import java.util.HashMap;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.apache.commons.io.IOUtils;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Michael C. Han
 * @author Brian Greenwald
 * @author Prathima Shreenath
 */
@RunWith(Arquillian.class)
@Sync
public class ReportEngineImplTest extends TestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			SynchronousDestinationTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		Registry registry = RegistryUtil.getRegistry();

		ServiceReference<ReportEngine> reportEngineServiceReference =
			registry.getServiceReference(ReportEngine.class);

		_reportEngine = registry.getService(reportEngineServiceReference);
	}

	@Test
	public void testCompileCsv() throws Exception {
		compile(
			ReportDataSourceType.CSV, "CsvDataSource.txt",
			"CsvDataSourceReport.jrxml", ReportFormat.CSV);
	}

	@Test
	public void testCompileXls() throws Exception {
		compile(
			ReportDataSourceType.XLS, "XlsDataSource.data.xls",
			"XlsDataSourceReport.jrxml", ReportFormat.CSV);
	}

	@Test
	public void testCompileXml() throws Exception {
		compile(
			ReportDataSourceType.XML, "northwind.xml", "OrdersReport.jrxml",
			ReportFormat.CSV);
	}

	@Test
	public void testExportCsv() throws Exception {
		export(ReportFormat.CSV);
	}

	@Test
	public void testExportPdf() throws Exception {
		export(ReportFormat.PDF);
	}

	@Test
	public void testExportRtf() throws Exception {
		export(ReportFormat.RTF);
	}

	@Test
	public void testExportTxt() throws Exception {
		export(ReportFormat.TXT);
	}

	@Test
	public void testExportXls() throws Exception {
		export(ReportFormat.XLS);
	}

	@Test
	public void testExportXml() throws Exception {
		export(ReportFormat.XML);
	}

	protected ReportRequest compile(
			ReportDataSourceType reportDataSourceType,
			String dataSourceFileName, String dataSourceReportFileName,
			ReportFormat reportFormat)
		throws Exception {

		ReportRequest reportRequest = getReportRequest(
			reportDataSourceType, dataSourceFileName, dataSourceReportFileName,
			reportFormat);

		_reportEngine.compile(reportRequest);

		return reportRequest;
	}

	protected void export(ReportFormat reportFormat) throws Exception {
		ReportRequest reportRequest = compile(
			ReportDataSourceType.CSV, "CsvDataSource.txt",
			"CsvDataSourceReport.jrxml", ReportFormat.CSV);

		ReportResultContainer reportResultContainer =
			new ByteArrayReportResultContainer();

		_reportEngine.execute(reportRequest, reportResultContainer);

		Assert.assertFalse(reportResultContainer.hasError());
		Assert.assertNotNull(reportResultContainer.getResults());
	}

	protected ReportRequest getReportRequest(
			ReportDataSourceType reportDataSourceType,
			String dataSourceFileName, String dataSourceReportFileName,
			ReportFormat reportFormat)
		throws Exception {

		ReportRequestContext reportRequestContext = new ReportRequestContext(
			reportDataSourceType);

		ClassLoader classLoader = getClass().getClassLoader();

		InputStream dataSourceInputStream = classLoader.getResourceAsStream(
			dataSourceFileName);

		byte[] dataSourceByteArray = IOUtils.toByteArray(dataSourceInputStream);

		reportRequestContext.setAttribute(
			ReportRequestContext.DATA_SOURCE_BYTE_ARRAY, dataSourceByteArray);

		reportRequestContext.setAttribute(
			ReportRequestContext.DATA_SOURCE_COLUMN_NAMES,
			"city,id,name,address,state");

		InputStream dataSourceReportInputStream =
			classLoader.getResourceAsStream(dataSourceReportFileName);

		byte[] reportByteArray = IOUtils.toByteArray(
			dataSourceReportInputStream);

		ReportDesignRetriever reportDesignRetriever =
			new MemoryReportDesignRetriever(
				"test", new Date(), reportByteArray);

		ReportRequest reportRequest = new ReportRequest(
			reportRequestContext, reportDesignRetriever,
			new HashMap<String, String>(), reportFormat.getValue());

		return reportRequest;
	}

	private ReportEngine _reportEngine;

}