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

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

import com.liferay.lcs.util.LCSConstants;
import com.liferay.osb.lcs.model.LCSClusterNodeUptime;
import com.liferay.osb.lcs.service.LCSClusterNodeUptimeService;
import com.liferay.osb.lcs.subscriptions.util.SubscriptionsUtil;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.text.DateFormatSymbols;

import java.util.List;

/**
 * @author Ivica Cardic
 * @author Matija Petanjek
 */
public class LCSClusterNodeUptimesPDFReport extends BaseReport {

	@Override
	public ByteArrayOutputStream process(ReportContext reportContext)
		throws Exception {

		long lcsClusterEntryId = GetterUtil.getInteger(
			reportContext.getParameter("lcsClusterEntryId"),
			LCSConstants.ALL_LCS_CLUSTER_OBJECTS_ID);
		long lcsClusterNodeId = GetterUtil.getInteger(
			reportContext.getParameter("lcsClusterNodeId"),
			LCSConstants.ALL_LCS_CLUSTER_OBJECTS_ID);
		long lcsProjectId = GetterUtil.getInteger(
			reportContext.getParameter("lcsProjectId"),
			LCSConstants.ALL_LCS_CLUSTER_OBJECTS_ID);
		int month = GetterUtil.getInteger(reportContext.getParameter("month"));
		int year = GetterUtil.getInteger(reportContext.getParameter("year"));

		List<LCSClusterNodeUptime> lcsClusterNodeUptimes =
			_lcsClusterNodeUptimeService.getMonthlyLCSClusterNodeUptimes(
				lcsClusterEntryId, lcsClusterNodeId, lcsProjectId, month, year,
				true, true);

		ByteArrayOutputStream pdfDocumentByteArrayOutputStream =
			createPdfDocument(lcsClusterNodeUptimes, reportContext);

		LCSClusterNodeUptime lcsClusterNodeUptime = lcsClusterNodeUptimes.get(
			0);

		return createPdfPageHeaders(
			pdfDocumentByteArrayOutputStream,
			lcsClusterNodeUptime.getLcsProjectName(), month, year);
	}

	protected PdfPTable createHeaderPdfPTable(int page, String headerContent)
		throws DocumentException {

		PdfPTable pdfPTable = new PdfPTable(2);

		PdfPCell defaultPdfPCell = pdfPTable.getDefaultCell();

		defaultPdfPCell.setBorder(Rectangle.BOTTOM);
		defaultPdfPCell.setFixedHeight(20);

		pdfPTable.addCell(headerContent);

		defaultPdfPCell.setHorizontalAlignment(Element.ALIGN_RIGHT);

		pdfPTable.addCell(String.format("Page %d", page));

		pdfPTable.setLockedWidth(true);
		pdfPTable.setTotalWidth(527);
		pdfPTable.setWidths(new int[] {400, 127});

		return pdfPTable;
	}

	protected PdfPCell createLCSClusterEntryPdfPCell(
		String lcsClusterEntryName) {

		PdfPCell pdfPCell = new PdfPCell(
			new Phrase(
				"Environment: " + lcsClusterEntryName,
				new Font(
					Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.NORMAL,
					BaseColor.WHITE)));

		pdfPCell.setBackgroundColor(BaseColor.BLACK);
		pdfPCell.setColspan(5);
		pdfPCell.setFixedHeight(22f);
		pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pdfPCell.setPaddingTop(3f);

		return pdfPCell;
	}

	protected PdfPCell createLCSClusterNodePdfPCell(String lcsClusterNodeName) {
		PdfPCell pdfPCell = new PdfPCell(
			new Phrase("Server: " + lcsClusterNodeName));

		pdfPCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		pdfPCell.setColspan(5);
		pdfPCell.setFixedHeight(22f);
		pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pdfPCell.setPaddingTop(3f);

		return pdfPCell;
	}

	protected ByteArrayOutputStream createPdfDocument(
			List<LCSClusterNodeUptime> lcsClusterNodeUptimes,
			ReportContext reportContext)
		throws DocumentException {

		ByteArrayOutputStream byteArrayOutputStream =
			new ByteArrayOutputStream();

		Document document = new Document(PageSize.A4, 40, 40, 80, 70);

		PdfWriter.getInstance(document, byteArrayOutputStream);

		document.open();

		PdfPTable pdfPTable = new PdfPTable(new float[] {1, 1, 1});

		PdfPCell defaultPdfPCell = pdfPTable.getDefaultCell();

		defaultPdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		defaultPdfPCell.setPaddingBottom(5f);
		defaultPdfPCell.setVerticalAlignment(Element.ALIGN_MIDDLE);

		Font boldFont = new Font(
			Font.FontFamily.TIMES_ROMAN, Font.DEFAULTSIZE, Font.BOLD);

		boolean changeCellBackgroundColor = false;
		long curLCSClusterEntryId = 0;
		long curLCSClusterNodeId = 0;

		for (LCSClusterNodeUptime lcsClusterNodeUptime :
				lcsClusterNodeUptimes) {

			long lcsClusterEntryId =
				lcsClusterNodeUptime.getLcsClusterEntryId();
			long lcsClusterNodeId = lcsClusterNodeUptime.getLcsClusterNodeId();

			if (lcsClusterEntryId != curLCSClusterEntryId) {
				pdfPTable.addCell(
					createLCSClusterEntryPdfPCell(
						lcsClusterNodeUptime.getLcsClusterEntryName()));

				curLCSClusterEntryId = lcsClusterEntryId;
			}

			if (lcsClusterNodeId != curLCSClusterNodeId) {
				pdfPTable.addCell(
					createLCSClusterNodePdfPCell(
						lcsClusterNodeUptime.getLcsClusterNodeName()));
				pdfPTable.addCell(
					new Phrase(
						reportContext.getLanguage("start-time"), boldFont));
				pdfPTable.addCell(
					new Phrase(
						reportContext.getLanguage("end-time"), boldFont));
				pdfPTable.addCell(
					new Phrase(reportContext.getLanguage("uptime"), boldFont));

				curLCSClusterNodeId = lcsClusterNodeId;

				changeCellBackgroundColor = false;
			}

			if (changeCellBackgroundColor) {
				defaultPdfPCell.setBackgroundColor(new GrayColor(0.95f));
			}

			pdfPTable.addCell(
				reportContext.formatTime(lcsClusterNodeUptime.getStartTime()));
			pdfPTable.addCell(
				reportContext.formatTime(lcsClusterNodeUptime.getEndTime()));
			pdfPTable.addCell(
				SubscriptionsUtil.formatUptime(
					lcsClusterNodeUptime.getStartTime(),
					lcsClusterNodeUptime.getEndTime(),
					reportContext.getLocale(),
					reportContext.getPortletConfig()));

			defaultPdfPCell.setBackgroundColor(BaseColor.WHITE);

			changeCellBackgroundColor ^= true;
		}

		pdfPTable.setWidthPercentage(80f);

		document.add(pdfPTable);

		document.close();

		return byteArrayOutputStream;
	}

	protected ByteArrayOutputStream createPdfPageHeaders(
			ByteArrayOutputStream pdfDocumentByteArrayOutputStream,
			String lcsProjectName, int month, int year)
		throws DocumentException, IOException {

		ByteArrayOutputStream pdfPageHeadersByteArrayOutputStream =
			new ByteArrayOutputStream();

		PdfReader pdfReader = new PdfReader(
			pdfDocumentByteArrayOutputStream.toByteArray());

		PdfStamper pdfStamper = new PdfStamper(
			pdfReader, pdfPageHeadersByteArrayOutputStream);

		StringBundler sb = new StringBundler(7);

		DateFormatSymbols dateFormatSymbols = new DateFormatSymbols();

		String[] months = dateFormatSymbols.getMonths();

		sb.append(months[month]);

		sb.append(StringPool.COMMA_AND_SPACE);
		sb.append(year);
		sb.append(StringPool.SPACE);
		sb.append(StringPool.DASH);
		sb.append(StringPool.SPACE);
		sb.append(lcsProjectName);

		for (int i = 1; i <= pdfReader.getNumberOfPages(); i++) {
			PdfPTable headerPdfTable = createHeaderPdfPTable(i, sb.toString());

			headerPdfTable.writeSelectedRows(
				0, -1, 34, 810, pdfStamper.getOverContent(i));
		}

		pdfStamper.close();

		pdfReader.close();

		return pdfPageHeadersByteArrayOutputStream;
	}

	@BeanReference(type = LCSClusterNodeUptimeService.class)
	private LCSClusterNodeUptimeService _lcsClusterNodeUptimeService;

}