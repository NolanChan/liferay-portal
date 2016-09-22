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

		String dlStoreFileName = getDLStoreFileName(
			documentationProjectId, fileName);

		DLStoreUtil.addFile(
			PortalUtil.getDefaultCompanyId(), CompanyConstants.SYSTEM,
			dlStoreFileName, file);
	}

	public static void deleteDocumentationProjectFile(
			long documentationProjectId, String fileName)
		throws PortalException {

		String dlStoreFileName = getDLStoreFileName(
			documentationProjectId, fileName);

		DLStoreUtil.deleteFile(
			PortalUtil.getDefaultCompanyId(), CompanyConstants.SYSTEM,
			dlStoreFileName);
	}

	public static void destroyDocumentationProjectDirectory(
			long documentationProjectId)
		throws PortalException {

		DLStoreUtil.deleteDirectory(
			PortalUtil.getDefaultCompanyId(), CompanyConstants.SYSTEM,
			getDirName(documentationProjectId));
	}

	public static File getDocumentProjectFile(
			long documentationProjectId, String fileName)
		throws PortalException {

		String dlStoreFileName = getDLStoreFileName(
			documentationProjectId, fileName);

		return DLStoreUtil.getFile(
			PortalUtil.getDefaultCompanyId(), CompanyConstants.SYSTEM,
			dlStoreFileName);
	}

	public static InputStream getDocumentProjectFileAsStream(
			long documentationProjectId, String fileName)
		throws PortalException {

		String dlStoreFileName = getDLStoreFileName(
			documentationProjectId, fileName);

		return DLStoreUtil.getFileAsStream(
			PortalUtil.getDefaultCompanyId(), CompanyConstants.SYSTEM,
			dlStoreFileName);
	}

	public static void initDocumentationProjectDirectory(
			long documentationProjectId)
		throws PortalException {

		DLStoreUtil.addDirectory(
			PortalUtil.getDefaultCompanyId(), CompanyConstants.SYSTEM,
			getDirName(documentationProjectId));
	}

	protected static String getDirName(long documentationProjectId) {
		return StringUtil.replace(
			_DIR_NAME, "{documentationProjectId}",
			String.valueOf(documentationProjectId));
	}

	protected static String getDLStoreFileName(
			long documentationProjectId, String fileName)
		throws PortalException {

		validate(fileName);

		String dirPath = getDirName(documentationProjectId);

		return dirPath + StringPool.FORWARD_SLASH + fileName;
	}

	protected static void validate(String fileName) throws PortalException {
		if (fileName.contains(StringPool.FORWARD_SLASH)) {
			throw new FileNameException(
				"File name contains invalid characters");
		}
	}

	private static final String _DIR_NAME =
		"osb-ldn/documentation-project/{documentationProjectId}";

}