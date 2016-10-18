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

package com.liferay.osb.lcs.web.internal.action;

import com.liferay.osb.lcs.advisor.PatchAdvisor;
import com.liferay.osb.lcs.constants.OSBLCSConstants;
import com.liferay.osb.lcs.exception.NoSuchLCSPatchEntryException;
import com.liferay.osb.lcs.service.LCSPatchEntryLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.struts.BaseStrutsAction;
import com.liferay.portal.kernel.struts.StrutsAction;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.SystemProperties;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import java.text.DateFormat;

import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
@Component(
	immediate = true,
	property = "path=" + OSBLCSConstants.PUBLIC_PATH_PATCH_UPLOAD,
	service = StrutsAction.class
)
public class PatchUploadAction extends BaseStrutsAction {

	@Override
	public String execute(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		try {
			doExecute(request, response);
		}
		catch (Exception e) {
			_log.error(e, e);

			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

		return null;
	}

	public void setLCSPatchEntryLocalService(
		LCSPatchEntryLocalService lcsPatchEntryLocalService) {

		_lcsPatchEntryLocalService = lcsPatchEntryLocalService;
	}

	@Reference(unbind = "-")
	public void setPatchAdvisor(PatchAdvisor patchAdvisor) {
		_patchAdvisor = patchAdvisor;
	}

	protected void addLCSPatchEntry(InputStream inputStream, long size)
		throws Exception {

		DocumentBuilder documentBuilder =
			_documentBuilderFactory.newDocumentBuilder();

		Document document = null;

		try {
			document = documentBuilder.parse(inputStream);
		}
		finally {
			inputStream.close();
		}

		Element rootElement = document.getDocumentElement();

		rootElement.normalize();

		String patchId = getTextContent(rootElement, "id");

		try {
			_lcsPatchEntryLocalService.getLCSPatchEntry(patchId);

			if (_log.isInfoEnabled()) {
				_log.info("Patch " + patchId + " already exists");
			}

			return;
		}
		catch (NoSuchLCSPatchEntryException nslcspee) {
			if (_log.isDebugEnabled()) {
				_log.debug("Register new patch " + patchId);
			}
		}

		String name = getTextContent(rootElement, "name");
		String description = getTextContent(rootElement, "description");
		int patchingToolVersion = GetterUtil.getInteger(
			getTextContent(rootElement, "patching-tool-version"));
		boolean incremental = GetterUtil.getBoolean(
			getTextContent(rootElement, "incremental"));
		boolean singular = GetterUtil.getBoolean(
			getTextContent(rootElement, "singular"));
		int version = GetterUtil.getInteger(
			getTextContent(rootElement, "version"));
		long rank = GetterUtil.getLong(getTextContent(rootElement, "rank"));
		String requirements = getTextContent(rootElement, "requirements");
		String component = getTextContent(rootElement, "component");
		String compatibleBuild = getTextContent(
			rootElement, "compatible-build");
		String product = getTextContent(rootElement, "product");
		String fixedIssues = getTextContent(rootElement, "fixed-issues");
		String moduleName = getTextContent(rootElement, "module-name");
		String moduleId = getTextContent(rootElement, "module-id");
		boolean tunnelWeb = GetterUtil.getBoolean(
			getTextContent(rootElement, "tunnel-web"));
		Date buildDate = GetterUtil.getDate(
			getTextContent(rootElement, "build-date"),
			DateFormat.getDateTimeInstance());
		String builtFor = getTextContent(rootElement, "built-for");

		_lcsPatchEntryLocalService.addLCSPatchEntry(
			patchId, name, description, patchingToolVersion, incremental,
			singular, version, size, rank, requirements, component,
			compatibleBuild, product, fixedIssues, moduleName, moduleId,
			tunnelWeb, buildDate, builtFor);
	}

	protected void decompress(
			InputStream zipFileInputStream, File decompressedFile)
		throws Exception {

		if (_log.isInfoEnabled()) {
			_log.info("Extract to " + decompressedFile.getAbsolutePath());
		}

		FileOutputStream fileOutputStream = new FileOutputStream(
			decompressedFile);

		byte[] bytes = new byte[4 * 1024];
		int size = 0;

		while ((size = zipFileInputStream.read(bytes)) != -1) {
			fileOutputStream.write(bytes, 0, size);
		}

		fileOutputStream.flush();

		fileOutputStream.close();
	}

	protected void doExecute(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		ServletFileUpload servletFileUpload = new ServletFileUpload(
			new DiskFileItemFactory(1024, _tmpDir));

		long maxSize = GetterUtil.getLong(
			PropsUtil.get(PropsKeys.UPLOAD_SERVLET_REQUEST_IMPL_MAX_SIZE));

		servletFileUpload.setFileSizeMax(maxSize);

		List<FileItem> fileItems = servletFileUpload.parseRequest(request);

		if (fileItems.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);

			return;
		}

		FileItem fileItem = null;

		for (FileItem curFileItem : fileItems) {
			String contentType = curFileItem.getContentType();

			if (curFileItem.isFormField()) {
				String fieldName = curFileItem.getFieldName();

				if (fieldName.endsWith("redirect")) {
					String redirect = curFileItem.getString();

					response.sendRedirect(redirect);
				}

				continue;
			}

			if (contentType.equals(ContentTypes.APPLICATION_OCTET_STREAM) ||
				contentType.equals(ContentTypes.APPLICATION_ZIP) ||
				contentType.equals("application/x-zip-compressed")) {

				fileItem = curFileItem;
			}
		}

		if (fileItem == null) {
			response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);

			return;
		}

		String name = fileItem.getName();

		if (name.contains("/") || name.contains("\\") ||
			!name.endsWith(".zip")) {

			response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);

			return;
		}

		File file = new File(_tmpDir, name);

		fileItem.write(file);

		processZipFile(file);

		fileItem.delete();

		file.delete();

		_patchAdvisor.resetInstallablePatches();
	}

	protected String getTextContent(Element rootElement, String name) {
		NodeList nodeList = rootElement.getElementsByTagName(name);

		Node node = nodeList.item(0);

		if (node == null) {
			return null;
		}

		return node.getTextContent();
	}

	protected void processZipFile(File file) throws Exception {
		ZipFile zipFile = new ZipFile(file);

		Enumeration<? extends ZipEntry> enumeration = zipFile.entries();

		while (enumeration.hasMoreElements()) {
			ZipEntry zipEntry = enumeration.nextElement();

			String name = zipEntry.getName();

			if (name.equals("fixpack_documentation.xml")) {
				_patchAdvisor.writePatchFile(file);

				addLCSPatchEntry(
					zipFile.getInputStream(zipEntry), file.length());
			}
			else if (name.endsWith("zip")) {
				File decompressedFile = new File(_tmpDir, zipEntry.getName());

				decompress(zipFile.getInputStream(zipEntry), decompressedFile);

				processZipFile(decompressedFile);

				decompressedFile.delete();
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PatchUploadAction.class);

	private static final DocumentBuilderFactory _documentBuilderFactory =
		DocumentBuilderFactory.newInstance();
	private static final File _tmpDir = new File(
		SystemProperties.get(SystemProperties.TMP_DIR));

	private LCSPatchEntryLocalService _lcsPatchEntryLocalService;
	private PatchAdvisor _patchAdvisor;

}