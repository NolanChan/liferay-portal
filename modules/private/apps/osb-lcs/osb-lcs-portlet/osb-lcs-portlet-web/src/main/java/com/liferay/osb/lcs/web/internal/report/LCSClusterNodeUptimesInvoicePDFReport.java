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
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import com.liferay.lcs.util.LCSConstants;
import com.liferay.osb.lcs.model.LCSClusterNodeUptime;
import com.liferay.osb.lcs.service.LCSClusterNodeUptimeService;
import com.liferay.osb.lcs.service.LCSProjectService;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalService;
import com.liferay.portal.service.RepositoryLocalService;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portlet.documentlibrary.NoSuchFileEntryException;
import com.liferay.portlet.documentlibrary.NoSuchFolderException;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.documentlibrary.service.DLAppLocalService;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import java.text.NumberFormat;

import java.util.Date;
import java.util.List;

/**
 * @author Matija Petanjek
 */
public class LCSClusterNodeUptimesInvoicePDFReport extends BaseReport {

	@Override
	public ByteArrayOutputStream process(ReportContext reportContext)
		throws Exception {

		long lcsProjectId = GetterUtil.getInteger(
			reportContext.getParameter("lcsProjectId"),
			LCSConstants.ALL_LCS_CLUSTER_OBJECTS_ID);
		int month = GetterUtil.getInteger(reportContext.getParameter("month"));
		int year = GetterUtil.getInteger(reportContext.getParameter("year"));

		FileEntry fileEntry = getFileEntry(reportContext);

		ByteArrayOutputStream byteArrayOutputStream = null;

		if (fileEntry == null) {
			List<LCSClusterNodeUptime> lcsClusterNodeUptimes =
				_lcsClusterNodeUptimeService.
					getMonthlyElasticTotalLCSClusterNodeUptimes(
						lcsProjectId, month, year);

			double total =
				_lcsClusterNodeUptimeService.
					getMonthlyElasticLCSClusterNodeUptimeTotal(
						lcsProjectId, month, year);

			byteArrayOutputStream = createPdfDocument(
				lcsClusterNodeUptimes, total, reportContext);

			addFileEntry(reportContext, byteArrayOutputStream);
		}
		else {
			byteArrayOutputStream = getByteArrayOutputStream(fileEntry);
		}

		return byteArrayOutputStream;
	}

	protected FileEntry addFileEntry(
			ReportContext reportContext,
			ByteArrayOutputStream byteArrayOutputStream)
		throws Exception {

		Group group = _groupLocalService.getFriendlyURLGroup(
			reportContext.getCompanyId(), "/guest");
		Folder folder = getFolder(reportContext);

		int month = GetterUtil.getInteger(reportContext.getParameter("month"));
		int year = GetterUtil.getInteger(reportContext.getParameter("year"));

		String fileName = getFileName(month, year);

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		return _dlAppLocalService.addFileEntry(
			reportContext.getUserId(), group.getGroupId(), folder.getFolderId(),
			fileName, ContentTypes.APPLICATION_PDF, fileName, StringPool.BLANK,
			StringPool.BLANK, byteArrayOutputStream.toByteArray(),
			serviceContext);
	}

	protected void createAddressPdfPTable(
		PdfContentByte pdfContentByte, Font normalFont, Font smallFont,
		ReportContext reportContext) {

		PdfPTable pdfPTable = new PdfPTable(1);

		PdfPCell pdfPCell = new PdfPCell(
			new Phrase(
				reportContext.getLanguage("bill-to-address"), smallFont));

		pdfPCell.setBorder(Rectangle.NO_BORDER);
		pdfPCell.setUseAscender(true);

		pdfPTable.addCell(pdfPCell);

		pdfPCell = new PdfPCell(
			new Phrase(reportContext.getLanguage("attention"), normalFont));

		pdfPCell.setBorder(Rectangle.NO_BORDER);
		pdfPCell.setUseAscender(true);

		pdfPTable.addCell(pdfPCell);

		long lcsProjectId = GetterUtil.getInteger(
			reportContext.getParameter("lcsProjectId"));

		try {
			pdfPCell = new PdfPCell(
				new Phrase(
					_lcsProjectService.getLCSProjectAdministratorEmailAddress(
						lcsProjectId),
					normalFont));
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}

		pdfPCell.setBorder(Rectangle.NO_BORDER);
		pdfPCell.setUseAscender(true);

		pdfPTable.addCell(pdfPCell);

		pdfPTable.setTotalWidth(100f);

		pdfPTable.writeSelectedRows(0, -1, 50, 710, pdfContentByte);
	}

	protected void createDetailsPdfPTable(
		List<LCSClusterNodeUptime> lcsClusterNodeUptimes, float pageWidth,
		PdfContentByte pdfContentByte, Font bigFont, Font normalFont,
		Font smallFont, ReportContext reportContext) {

		PdfPTable pdfPTable = new PdfPTable(2);

		PdfPCell pdfPCell = new PdfPCell(
			new Phrase(reportContext.getLanguage("details"), bigFont));

		pdfPCell.setBackgroundColor(new BaseColor(74, 150, 232));
		pdfPCell.setBorderColor(BaseColor.LIGHT_GRAY);
		pdfPCell.setColspan(2);
		pdfPCell.setUseDescender(true);

		pdfPTable.addCell(pdfPCell);

		long curLCSClusterEntryId = 0;
		long curLCSClusterNodeId = 0;

		for (LCSClusterNodeUptime lcsClusterNodeUptime :
				lcsClusterNodeUptimes) {

			long lcsClusterEntryId =
				lcsClusterNodeUptime.getLcsClusterEntryId();
			long lcsClusterNodeId = lcsClusterNodeUptime.getLcsClusterNodeId();

			if (lcsClusterEntryId != curLCSClusterEntryId) {
				pdfPCell = new PdfPCell(
					new Phrase(
						lcsClusterNodeUptime.getLcsClusterEntryName(),
						normalFont));

				pdfPCell.setBorderColor(BaseColor.LIGHT_GRAY);
				pdfPCell.setColspan(2);
				pdfPCell.setPaddingLeft(10f);
				pdfPCell.setUseDescender(true);

				pdfPTable.addCell(pdfPCell);

				curLCSClusterEntryId = lcsClusterEntryId;
			}

			if (lcsClusterNodeId != curLCSClusterNodeId) {
				pdfPCell = new PdfPCell(
					new Phrase(
						lcsClusterNodeUptime.getLcsClusterNodeName(),
						normalFont));

				pdfPCell.disableBorderSide(Rectangle.RIGHT);
				pdfPCell.setBorderColor(BaseColor.LIGHT_GRAY);
				pdfPCell.setPaddingLeft(20f);
				pdfPCell.setUseDescender(true);

				pdfPTable.addCell(pdfPCell);

				pdfPCell = new PdfPCell(
					new Phrase(
						String.valueOf(lcsClusterNodeUptime.getAmount()),
						smallFont));

				pdfPCell.disableBorderSide(Rectangle.LEFT);
				pdfPCell.setBorderColor(BaseColor.LIGHT_GRAY);
				pdfPCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				pdfPCell.setUseDescender(true);

				pdfPTable.addCell(pdfPCell);

				curLCSClusterNodeId = lcsClusterNodeId;
			}
		}

		pdfPTable.setTotalWidth(pageWidth);

		pdfPTable.writeSelectedRows(0, -1, 50, 450, pdfContentByte);
	}

	protected void createLeftHeaderPdfPTable(
			PdfContentByte pdfContentByte, Font bigFont, Font smallFont,
			ReportContext reportContext)
		throws Exception {

		PdfPTable pdfPTable = new PdfPTable(1);

		PdfPCell pdfPCell = new PdfPCell(getLogoImage(reportContext), true);

		pdfPCell.setBorder(Rectangle.NO_BORDER);
		pdfPCell.setFixedHeight(45f);

		pdfPTable.addCell(pdfPCell);

		pdfPCell = new PdfPCell(
			new Phrase(reportContext.getLanguage("account-number"), smallFont));

		pdfPCell.setBorder(Rectangle.NO_BORDER);
		pdfPCell.setVerticalAlignment(Element.ALIGN_BOTTOM);

		pdfPTable.addCell(pdfPCell);

		pdfPCell = new PdfPCell(new Phrase("[$ACCOUNT_NUMBER$]", bigFont));

		pdfPCell.setBorder(Rectangle.NO_BORDER);
		pdfPCell.setUseAscender(true);

		pdfPTable.addCell(pdfPCell);

		pdfPTable.setTotalWidth(175f);

		pdfPTable.writeSelectedRows(0, -1, 50, 790, pdfContentByte);
	}

	protected void createParagraphPdfPTable(
		float pageWidth, PdfContentByte pdfContentByte, Font normalFont,
		Font smallFont, ReportContext reportContext) {

		PdfPTable pdfPTable = new PdfPTable(1);

		Phrase phrase = new Phrase(
			reportContext.getLanguage("this-invoice-is-for-the-billing-period"),
			normalFont);

		PdfPCell pdfPCell = new PdfPCell(phrase);

		pdfPCell.setBorder(Rectangle.NO_BORDER);
		pdfPCell.setUseDescender(true);

		pdfPTable.addCell(pdfPCell);

		pdfPCell = new PdfPCell(
			new Phrase(
				reportContext.getLanguage(
					"greetings-from-liferay-connected-services"),
				smallFont));

		pdfPCell.setBorder(Rectangle.NO_BORDER);
		pdfPCell.setUseDescender(true);

		pdfPTable.addCell(pdfPCell);

		pdfPTable.setTotalWidth(pageWidth);

		pdfPTable.writeSelectedRows(0, -1, 50, 630, pdfContentByte);
	}

	protected ByteArrayOutputStream createPdfDocument(
			List<LCSClusterNodeUptime> lcsClusterNodeUptimes, double total,
			ReportContext reportContext)
		throws Exception {

		Document document = new Document(PageSize.A4, 50, 50, 50, 50);

		ByteArrayOutputStream byteArrayOutputStream =
			new ByteArrayOutputStream();

		PdfWriter pdfWriter = PdfWriter.getInstance(
			document, byteArrayOutputStream);

		document.open();

		PdfContentByte pdfContentByte = pdfWriter.getDirectContent();

		Font bigFont = new Font(Font.FontFamily.HELVETICA, 14, Font.NORMAL);
		Font smallFont = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);

		createLeftHeaderPdfPTable(
			pdfContentByte, bigFont, smallFont, reportContext);

		Font normalFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);

		createAddressPdfPTable(
			pdfContentByte, normalFont, smallFont, reportContext);

		Font boldFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);

		createRightHeaderPdfPTable(
			pdfContentByte, total, bigFont, boldFont, smallFont, reportContext);

		float pageWidth = document.right() - document.left();

		createParagraphPdfPTable(
			pageWidth, pdfContentByte, normalFont, smallFont, reportContext);

		createSummaryPdfPTable(
			pageWidth, pdfContentByte, total, bigFont, normalFont, smallFont,
			reportContext);

		createDetailsPdfPTable(
			lcsClusterNodeUptimes, pageWidth, pdfContentByte, bigFont,
			normalFont, smallFont, reportContext);

		document.close();

		return byteArrayOutputStream;
	}

	protected void createRightHeaderPdfPTable(
		PdfContentByte pdfContentByte, double total, Font bigFont,
		Font boldFont, Font smallFont, ReportContext reportContext) {

		PdfPTable pdfPTable = new PdfPTable(new float[]{5, 4});

		PdfPCell pdfPCell = new PdfPCell(
			new Phrase(
				reportContext.getLanguage("liferay-connected-services-invoice"),
				bigFont));

		pdfPCell.setBorder(Rectangle.BOTTOM);
		pdfPCell.setBorderColor(BaseColor.LIGHT_GRAY);
		pdfPCell.setBorderWidthBottom(1.5f);
		pdfPCell.setColspan(2);
		pdfPCell.setFixedHeight(22f);
		pdfPCell.setUseDescender(true);

		pdfPTable.addCell(pdfPCell);

		pdfPCell = new PdfPCell(
			new Phrase(reportContext.getLanguage("invoice-summary"), boldFont));

		pdfPCell.setBorder(Rectangle.BOTTOM);
		pdfPCell.setBorderColor(BaseColor.LIGHT_GRAY);
		pdfPCell.setBorderWidthBottom(0.5f);
		pdfPCell.setColspan(2);
		pdfPCell.setPaddingTop(0f);
		pdfPCell.setUseDescender(true);

		pdfPTable.addCell(pdfPCell);

		pdfPCell = new PdfPCell(
			new Phrase(reportContext.getLanguage("invoice-number"), smallFont));

		pdfPCell.setBorder(Rectangle.NO_BORDER);
		pdfPCell.setUseAscender(true);

		pdfPTable.addCell(pdfPCell);

		pdfPCell = new PdfPCell(
			new Phrase(
				String.valueOf(_invoiceNumberGenerator.getInvoiceNumber()),
				smallFont));

		pdfPCell.setBorder(Rectangle.NO_BORDER);
		pdfPCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pdfPCell.setUseAscender(true);

		pdfPTable.addCell(pdfPCell);

		pdfPCell = new PdfPCell(
			new Phrase(reportContext.getLanguage("invoice-date"), smallFont));

		pdfPCell.setBorder(Rectangle.BOTTOM);
		pdfPCell.setBorderColor(BaseColor.LIGHT_GRAY);
		pdfPCell.setBorderWidthBottom(0.5f);
		pdfPCell.setUseAscender(true);

		pdfPTable.addCell(pdfPCell);

		pdfPCell = new PdfPCell(
			new Phrase(reportContext.formatDate(new Date()), smallFont));

		pdfPCell.setBorder(Rectangle.BOTTOM);
		pdfPCell.setBorderColor(BaseColor.LIGHT_GRAY);
		pdfPCell.setBorderWidthBottom(0.5f);
		pdfPCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pdfPCell.setUseAscender(true);

		pdfPTable.addCell(pdfPCell);

		pdfPCell = new PdfPCell(
			new Phrase(
				reportContext.getLanguage("total-amount-due-on"), boldFont));

		pdfPCell.setBorder(Rectangle.BOTTOM);
		pdfPCell.setBorderColor(BaseColor.LIGHT_GRAY);
		pdfPCell.setBorderWidthBottom(1.5f);
		pdfPCell.setFixedHeight(18f);
		pdfPCell.setPaddingTop(0f);
		pdfPCell.setUseDescender(true);

		pdfPTable.addCell(pdfPCell);

		pdfPCell = new PdfPCell(new Phrase(String.valueOf(total), boldFont));

		pdfPCell.setBorder(Rectangle.BOTTOM);
		pdfPCell.setBorderColor(BaseColor.LIGHT_GRAY);
		pdfPCell.setBorderWidthBottom(1.5f);
		pdfPCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pdfPCell.setPaddingTop(0f);
		pdfPCell.setUseDescender(true);

		pdfPTable.addCell(pdfPCell);

		pdfPTable.setTotalWidth(285f);

		pdfPTable.writeSelectedRows(0, -1, 260, 773, pdfContentByte);
	}

	protected void createSummaryPdfPTable(
		float pageWidth, PdfContentByte pdfContentByte, double total,
		Font bigFont, Font normalFont, Font smallFont,
		ReportContext reportContext) {

		PdfPTable pdfPTable = new PdfPTable(2);

		PdfPCell pdfPCell = new PdfPCell(
			new Phrase(reportContext.getLanguage("summary"), bigFont));

		pdfPCell.setBackgroundColor(new BaseColor(74, 150, 232));
		pdfPCell.setBorderColor(BaseColor.LIGHT_GRAY);
		pdfPCell.setColspan(2);
		pdfPCell.setUseDescender(true);

		pdfPTable.addCell(pdfPCell);

		pdfPCell = new PdfPCell(
			new Phrase(
				reportContext.getLanguage("liferay-connected-services-charges"),
				normalFont));

		pdfPCell.disableBorderSide(Rectangle.RIGHT);
		pdfPCell.setBorderColor(BaseColor.LIGHT_GRAY);
		pdfPCell.setPaddingLeft(10f);
		pdfPCell.setUseDescender(true);

		pdfPTable.addCell(pdfPCell);

		pdfPCell = new PdfPCell(new Phrase(String.valueOf(total), normalFont));

		pdfPCell.disableBorderSide(Rectangle.LEFT);
		pdfPCell.setBorderColor(BaseColor.LIGHT_GRAY);
		pdfPCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pdfPCell.setUseDescender(true);

		pdfPTable.addCell(pdfPCell);

		pdfPCell = new PdfPCell(
			new Phrase(reportContext.getLanguage("charges"), smallFont));

		pdfPCell.disableBorderSide(Rectangle.RIGHT);
		pdfPCell.setBorderColor(BaseColor.LIGHT_GRAY);
		pdfPCell.setPaddingLeft(20f);
		pdfPCell.setUseDescender(true);

		pdfPTable.addCell(pdfPCell);

		pdfPCell = new PdfPCell(new Phrase("[$CHARGES$]", smallFont));

		pdfPCell.disableBorderSide(Rectangle.LEFT);
		pdfPCell.setBorderColor(BaseColor.LIGHT_GRAY);
		pdfPCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pdfPCell.setUseDescender(true);

		pdfPTable.addCell(pdfPCell);

		pdfPCell = new PdfPCell(
			new Phrase(reportContext.getLanguage("tax"), smallFont));

		pdfPCell.disableBorderSide(Rectangle.RIGHT);
		pdfPCell.setBorderColor(BaseColor.LIGHT_GRAY);
		pdfPCell.setPaddingLeft(20f);
		pdfPCell.setUseDescender(true);

		pdfPTable.addCell(pdfPCell);

		pdfPCell = new PdfPCell(new Phrase("[$TAX$]", smallFont));

		pdfPCell.disableBorderSide(Rectangle.LEFT);
		pdfPCell.setBorderColor(BaseColor.LIGHT_GRAY);
		pdfPCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pdfPCell.setUseDescender(true);

		pdfPTable.addCell(pdfPCell);

		pdfPCell = new PdfPCell(
			new Phrase(
				reportContext.getLanguage("total-for-this-invoice"),
				normalFont));

		pdfPCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		pdfPCell.setBorderColor(BaseColor.LIGHT_GRAY);
		pdfPCell.setUseDescender(true);

		pdfPTable.addCell(pdfPCell);

		pdfPCell = new PdfPCell(new Phrase(String.valueOf(total), normalFont));

		pdfPCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		pdfPCell.setBorderColor(BaseColor.LIGHT_GRAY);
		pdfPCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pdfPCell.setUseDescender(true);

		pdfPTable.addCell(pdfPCell);

		pdfPTable.setTotalWidth(pageWidth);

		pdfPTable.writeSelectedRows(0, -1, 50, 575, pdfContentByte);
	}

	protected ByteArrayOutputStream getByteArrayOutputStream(
			FileEntry fileEntry)
		throws Exception {

		ByteArrayOutputStream byteArrayOutputStream =
			new ByteArrayOutputStream();

		byte[] data = new byte[16384];
		InputStream inputStream = fileEntry.getContentStream();
		int read = 0;

		while ((read = inputStream.read(data, 0, data.length)) != -1) {
			byteArrayOutputStream.write(data, 0, read);
		}

		byteArrayOutputStream.flush();

		return byteArrayOutputStream;
	}

	protected FileEntry getFileEntry(ReportContext reportContext)
		throws Exception {

		Group group = _groupLocalService.getFriendlyURLGroup(
			reportContext.getCompanyId(), "/guest");
		Folder folder = getFolder(reportContext);

		int month = GetterUtil.getInteger(reportContext.getParameter("month"));
		int year = GetterUtil.getInteger(reportContext.getParameter("year"));

		try {
			return _dlAppLocalService.getFileEntry(
				group.getGroupId(), folder.getFolderId(),
				getFileName(month, year));
		}
		catch (NoSuchFileEntryException nsfee) {
			return null;
		}
	}

	protected String getFileName(int month, int year) {
		NumberFormat numberFormat = NumberFormat.getNumberInstance();

		numberFormat.setMinimumIntegerDigits(2);

		return String.valueOf(year).concat(numberFormat.format(month)).concat(
			".pdf");
	}

	protected Folder getFolder(ReportContext reportContext) throws Exception {
		User user = _userLocalService.getUserByEmailAddress(
			reportContext.getCompanyId(), "system@liferay.com");
		Group group = _groupLocalService.getFriendlyURLGroup(
			reportContext.getCompanyId(), "/guest");

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		Folder invoicesFolder = null;

		try {
			invoicesFolder = _dlAppLocalService.getFolder(
				group.getGroupId(), DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
				"Invoices");
		}
		catch (NoSuchFolderException nsfe) {
			invoicesFolder = _dlAppLocalService.addFolder(
				user.getUserId(), group.getGroupId(),
				DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, "Invoices",
				StringPool.BLANK, serviceContext);
		}

		long lcsProjectId = GetterUtil.getInteger(
			reportContext.getParameter("lcsProjectId"));

		try {
			return _dlAppLocalService.getFolder(
				group.getGroupId(), invoicesFolder.getFolderId(),
				String.valueOf(lcsProjectId));
		}
		catch (NoSuchFolderException nsfe) {
			return _dlAppLocalService.addFolder(
				user.getUserId(), group.getGroupId(),
				invoicesFolder.getFolderId(), String.valueOf(lcsProjectId),
				StringPool.BLANK, serviceContext);
		}
	}

	protected Image getLogoImage(ReportContext reportContext) throws Exception {
		return Image.getInstance(
			reportContext.getRealPath(
				"/WEB-INF/classes/com/liferay/osb/lcs/report/dependencies" +
					"/logo.png"));
	}

	@BeanReference(type = DLAppLocalService.class)
	private DLAppLocalService _dlAppLocalService;

	@BeanReference(type = GroupLocalService.class)
	private GroupLocalService _groupLocalService;

	@BeanReference(type = InvoiceNumberGenerator.class)
	private InvoiceNumberGenerator _invoiceNumberGenerator;

	@BeanReference(type = LCSClusterNodeUptimeService.class)
	private LCSClusterNodeUptimeService _lcsClusterNodeUptimeService;

	@BeanReference(type = LCSProjectService.class)
	private LCSProjectService _lcsProjectService;

	@BeanReference(type = RepositoryLocalService.class)
	private RepositoryLocalService _repositoryLocalService;

	@BeanReference(type = UserLocalService.class)
	private UserLocalService _userLocalService;

}