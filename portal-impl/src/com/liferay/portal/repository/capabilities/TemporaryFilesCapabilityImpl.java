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

package com.liferay.portal.repository.capabilities;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.LocalRepository;
import com.liferay.portal.kernel.repository.capabilities.ConfigurationCapability;
import com.liferay.portal.kernel.repository.capabilities.TemporaryFilesCapability;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.documentlibrary.NoSuchFolderException;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * @author Iván Zaera
 */
public class TemporaryFilesCapabilityImpl implements TemporaryFilesCapability {

	public TemporaryFilesCapabilityImpl(LocalRepository localRepository) {
		_localRepository = localRepository;
	}

	@Override
	public FileEntry addTemporaryFile(
			UUID callerUuid, String folderPath, String fileName, long userId,
			String mimeType, InputStream inputStream)
		throws PortalException {

		Folder folder = addTempFolder(userId, callerUuid, folderPath);

		File file = null;

		try {
			file = FileUtil.createTempFile(inputStream);

			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setAddGroupPermissions(true);
			serviceContext.setAddGuestPermissions(true);

			return _localRepository.addFileEntry(
				userId, folder.getFolderId(), fileName, mimeType, fileName,
				StringPool.BLANK, StringPool.BLANK, file, serviceContext);
		}
		catch (IOException ioe) {
			throw new SystemException("Unable to write temporary file", ioe);
		}
		finally {
			FileUtil.delete(file);
		}
	}

	@Override
	public void deleteExpiredTemporaryFiles() {
	}

	@Override
	public void deleteTemporaryFile(
			UUID callerUuid, String folderPath, String fileName)
		throws PortalException {

		try {
			FileEntry fileEntry = getTemporaryFile(
				callerUuid, folderPath, fileName);

			_localRepository.deleteFileEntry(fileEntry.getFileEntryId());
		}
		catch (NoSuchModelException nsme) {
		}
	}

	@Override
	public FileEntry getTemporaryFile(
			UUID callerUuid, String folderPath, String fileName)
		throws PortalException {

		Folder folder = getTempFolder(callerUuid, folderPath);

		return _localRepository.getFileEntry(folder.getFolderId(), fileName);
	}

	@Override
	public List<FileEntry> getTemporaryFiles(
			UUID callerUuid, String folderPath)
		throws PortalException {

		try {
			Folder folder = getTempFolder(callerUuid, folderPath);

			return _localRepository.getRepositoryFileEntries(
				folder.getFolderId(), 0, QueryUtil.ALL_POS, null);
		}
		catch (NoSuchModelException nsme) {
			return Collections.emptyList();
		}
	}

	@Override
	public long getTemporaryFilesTimeout() {
		ConfigurationCapability configurationCapability =
			_localRepository.getCapability(ConfigurationCapability.class);

		String minimumLifespanMilliseconds =
			configurationCapability.getProperty(
				getClass(), _PROPERTY_TEMPORARY_FILES_TIMEOUT);

		if (minimumLifespanMilliseconds == null) {
			return _DEFAULT_TEMPORARY_FILES_TIMEOUT;
		}

		return GetterUtil.getLong(minimumLifespanMilliseconds);
	}

	@Override
	public void setTemporaryFilesTimeout(long temporaryFilesTimeout) {
		ConfigurationCapability configurationCapability =
			_localRepository.getCapability(ConfigurationCapability.class);

		configurationCapability.setProperty(
			getClass(), _PROPERTY_TEMPORARY_FILES_TIMEOUT,
			String.valueOf(temporaryFilesTimeout));
	}

	protected Folder addTempFolder(
			long userId, UUID callerUuid, String folderPath)
		throws PortalException {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		Folder tempFolder = _addFolder(
			userId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			_FOLDER_NAME_TEMP, serviceContext);

		Folder callerFolder = _addFolder(
			userId, tempFolder.getFolderId(), callerUuid.toString(),
			serviceContext);

		return _addFolders(
			userId, callerFolder.getFolderId(), folderPath, serviceContext);
	}

	protected Folder getTempFolder(UUID callerUuid, String folderPath)
		throws PortalException {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		Folder tempFolder = _getFolder(
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, _FOLDER_NAME_TEMP);

		Folder callerFolder = _getFolder(
			tempFolder.getFolderId(), callerUuid.toString());

		return _getFolders(callerFolder.getFolderId(), folderPath);
	}

	private Folder _addFolder(
			long userId, long parentFolderId, String folderName,
			ServiceContext serviceContext)
		throws PortalException {

		try {
			return _getFolder(parentFolderId, folderName);
		}
		catch (NoSuchFolderException nsfe) {
			return _localRepository.addFolder(
				userId, parentFolderId, folderName, StringPool.BLANK,
				serviceContext);
		}
	}

	private Folder _addFolders(
			long userId, long folderId, String folderPath,
			ServiceContext serviceContext)
		throws PortalException {

		Folder folder = null;

		String[] folderNames = StringUtil.split(folderPath, StringPool.SLASH);

		for (String folderName : folderNames) {
			folder = _addFolder(userId, folderId, folderName, serviceContext);

			folderId = folder.getFolderId();
		}

		return folder;
	}

	private Folder _getFolder(long parentFolderId, String folderName)
		throws PortalException {

		return _localRepository.getFolder(parentFolderId, folderName);
	}

	private Folder _getFolders(long parentFolderId, String folderPath)
		throws PortalException {

		Folder folder = null;

		String[] folderNames = StringUtil.split(folderPath, StringPool.SLASH);

		for (String folderName : folderNames) {
			folder = _localRepository.getFolder(parentFolderId, folderName);

			parentFolderId = folder.getFolderId();
		}

		return folder;
	}


	private static final long _DEFAULT_TEMPORARY_FILES_TIMEOUT =
		12 * 60 * 60 * 1000;

	private static final String _FOLDER_NAME_TEMP = "temp";

	private static final String _PROPERTY_TEMPORARY_FILES_TIMEOUT =
		"temporaryFilesTimeout";

	private LocalRepository _localRepository;

}