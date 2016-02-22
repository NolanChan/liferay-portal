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

package com.liferay.portal.reports.engine.jasper;

import java.util.Map;

import javax.servlet.ServletContext;

import com.liferay.portal.reports.engine.ReportEngine;
import com.liferay.portal.reports.engine.ReportFormatExporter;
import com.liferay.portal.reports.engine.ReportFormatExporterRegistry;
import com.liferay.portal.reports.engine.ReportGenerationException;
import com.liferay.portal.reports.engine.ReportRequest;
import com.liferay.portal.reports.engine.ReportRequestContext;
import com.liferay.portal.reports.engine.ReportResultContainer;
import com.liferay.portal.reports.engine.jasper.compiler.ReportCompiler;
import com.liferay.portal.reports.engine.jasper.fillmanager.ReportFillManager;
import com.liferay.portal.reports.engine.jasper.fillmanager.ReportFillManagerRegistry;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

/**
 * @author Michael C. Han
 * @author Brian Wing Shun Chan
 */
public class ReportEngineImpl implements ReportEngine {

	@Override
	public void compile(ReportRequest reportRequest)
		throws ReportGenerationException {

		try {
			_reportCompiler.compile(
				reportRequest.getReportDesignRetriever(), true);
		}
		catch (Exception e) {
			throw new ReportGenerationException("Unable to compile report", e);
		}
	}

	@Override
	public void destroy() {
	}

	@Override
	public void execute(
			ReportRequest reportRequest, ReportResultContainer resultContainer)
		throws ReportGenerationException {

		try {
			JasperReport jasperReport = _reportCompiler.compile(
				reportRequest.getReportDesignRetriever());

			ReportRequestContext reportRequestContext =
				reportRequest.getReportRequestContext();

			ReportFillManager reportFillManager =
				_reportFillManagerRegistry.getReportFillManager(
					reportRequestContext.getReportDataSourceType());

			JasperPrint jasperPrint = reportFillManager.fillReport(
				jasperReport, reportRequest);

			ReportFormatExporter reportFormatExporter =
				_reportFormatExporterRegistry.getReportFormatExporter(
					reportRequest.getReportFormat());

			reportFormatExporter.format(
				jasperPrint, reportRequest, resultContainer);
		}
		catch (Exception e) {
			throw new ReportGenerationException("Unable to execute report", e);
		}
	}

	@Override
	public Map<String, String> getEngineParameters() {
		return _engineParameters;
	}

	@Override
	public void init(ServletContext servletContext) {
	}

	@Override
	public void setEngineParameters(Map<String, String> engineParameters) {
		_engineParameters = engineParameters;
	}

	public void setReportCompiler(ReportCompiler reportCompiler) {
		_reportCompiler = reportCompiler;
	}

	public void setReportFillManagerRegistry(
		ReportFillManagerRegistry reportFillManagerRegistry) {

		_reportFillManagerRegistry = reportFillManagerRegistry;
	}

	@Override
	public void setReportFormatExporterRegistry(
		ReportFormatExporterRegistry reportFormatExporterRegistry) {

		_reportFormatExporterRegistry = reportFormatExporterRegistry;
	}

	private Map<String, String> _engineParameters;
	private ReportCompiler _reportCompiler;
	private ReportFillManagerRegistry _reportFillManagerRegistry;
	private ReportFormatExporterRegistry _reportFormatExporterRegistry;

}