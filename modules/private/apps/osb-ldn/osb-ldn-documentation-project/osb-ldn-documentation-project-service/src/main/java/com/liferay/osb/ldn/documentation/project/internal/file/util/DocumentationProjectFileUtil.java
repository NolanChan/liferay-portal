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

package com.liferay.osb.ldn.documentation.project.internal.file.util;

import com.liferay.document.library.kernel.exception.FileNameException;
import com.liferay.document.library.kernel.store.DLStoreUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.File;
import java.io.InputStream;

/**
 * @author Ryan Park
 */
public class DocumentationProjectFileUtil {

	public static void addDocumentationProjectFile(
			long documentationProjectId, String fileName, File file)
		throws PortalException {

		String filePath = getFilePath(documentationProjectId, fileName);

		DLStoreUtil.addFile(
			PortalUtil.getDefaultCompanyId(), CompanyConstants.SYSTEM, filePath,
			file);
	}

	public static void destroyDocumentationProjectDirectory(
			long documentationProjectId)
		throws PortalException {

		DLStoreUtil.deleteDirectory(
			PortalUtil.getDefaultCompanyId(), CompanyConstants.SYSTEM,
			getDirPath(documentationProjectId));
	}

	public static InputStream getDocumentProjectFileAsStream(
			long documentationProjectId, String fileName)
		throws PortalException {

		String filePath = getFilePath(documentationProjectId, fileName);

		return DLStoreUtil.getFileAsStream(
			PortalUtil.getDefaultCompanyId(), CompanyConstants.SYSTEM,
			filePath);
	}

	public static void initDocumentationProjectDirectory(
			long documentationProjectId)
		throws PortalException {

		DLStoreUtil.addDirectory(
			PortalUtil.getDefaultCompanyId(), CompanyConstants.SYSTEM,
			getDirPath(documentationProjectId));
	}

	public static void updateDocumentationProjectFile(
			long documentationProjectId, String fileName, File file)
		throws PortalException {

		String filePath = getFilePath(documentationProjectId, fileName);

		DLStoreUtil.deleteFile(
			PortalUtil.getDefaultCompanyId(), CompanyConstants.SYSTEM,
			filePath);

		DLStoreUtil.addFile(
			PortalUtil.getDefaultCompanyId(), CompanyConstants.SYSTEM, filePath,
			file);
	}

	protected static String getDirPath(long documentationProjectId) {
		return StringUtil.replace(
			_DOCUMENTATION_PROJECT_DIR_PATH, "{documentationProjectId}",
			String.valueOf(documentationProjectId));
	}

	protected static String getFilePath(
			long documentationProjectId, String fileName)
		throws PortalException {

		validate(fileName);

		String dirPath = getDirPath(documentationProjectId);

		return dirPath + StringPool.FORWARD_SLASH + fileName;
	}

	protected static void validate(String fileName) throws PortalException {
		if (fileName.contains(StringPool.FORWARD_SLASH)) {
			throw new FileNameException(
				"File name contains invalid characters");
		}
	}

	private static final String _DOCUMENTATION_PROJECT_DIR_PATH =
		"osb-ldn/documentation-project/{documentProjectId}";

}