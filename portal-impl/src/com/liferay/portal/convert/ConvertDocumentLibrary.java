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

package com.liferay.portal.convert;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Image;
import com.liferay.portal.repository.liferayrepository.model.LiferayFileEntry;
import com.liferay.portal.service.ImageLocalServiceUtil;
import com.liferay.portal.util.MaintenanceUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;
import com.liferay.portlet.documentlibrary.store.Store;
import com.liferay.portlet.documentlibrary.store.StoreFactory;
import com.liferay.portlet.documentlibrary.util.DLPreviewableProcessor;
import com.liferay.portlet.documentlibrary.util.comparator.FileVersionVersionComparator;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;

import java.io.InputStream;

import java.util.Collection;
import java.util.List;

/**
 * @author Minhchau Dang
 * @author Alexander Chow
 * @author László Csontos
 */
public class ConvertDocumentLibrary
	extends BaseConvertProcess implements DLStoreConverter {

	@Override
	public String getConfigurationErrorMessage() {
		return "there-are-no-valid-configuration-for-any-store-please-" +
			"configure-a-store-using-configuration-admin";
	}

	@Override
	public String getDescription() {
		return "migrate-documents-from-one-repository-to-another";
	}

	@Override
	public String getParameterDescription() {
		return "please-select-a-new-repository-hook";
	}

	@Override
	public String[] getParameterNames() {
		StoreFactory storeFactory = StoreFactory.getInstance();

		String[] hooks = storeFactory.getStoreTypes();

		StringBundler sb = new StringBundler(hooks.length * 2 + 2);

		Store store = storeFactory.getStoreInstance();

		if (store == null) {
			return null;
		}

		sb.append(PropsKeys.DL_STORE_IMPL);
		sb.append(StringPool.EQUAL);

		for (String hook : hooks) {
			if (!hook.equals(store.getClass().getName())) {
				sb.append(hook);
				sb.append(StringPool.SEMICOLON);
			}
		}

		return new String[] {
			sb.toString(), "delete-files-from-previous-repository=checkbox"
		};
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public void migrateDLFileEntry(
		long companyId, long repositoryId, FileEntry fileEntry) {

		Object model = fileEntry.getModel();

		if (!(model instanceof DLFileEntry)) {
			throw new IllegalArgumentException(
				"Unsupported file entry model " + model.getClass());
		}

		String fileName = ((DLFileEntry)model).getName();

		List<FileVersion> fileVersions = getFileVersions(fileEntry);

		if (fileVersions.isEmpty()) {
			String versionNumber = Store.VERSION_DEFAULT;

			migrateFile(companyId, repositoryId, fileName, versionNumber);

			return;
		}

		for (FileVersion fileVersion : fileVersions) {
			String versionNumber = fileVersion.getVersion();

			migrateFile(companyId, repositoryId, fileName, versionNumber);
		}
	}

	@Override
	public void validate() throws FileSystemStoreRootDirException {
		String sourceStoreClassName = getSourceStoreClassName();

		if (!sourceStoreClassName.endsWith(_FILE_SYSTEM_STORE_SUFFIX)) {
			return;
		}

		String targetStoreClassName = getTargetStoreClassName();

		if (!targetStoreClassName.endsWith(_FILE_SYSTEM_STORE_SUFFIX)) {
			return;
		}
	}

	@Override
	protected void doConvert() throws Exception {
		StoreFactory storeFactory = StoreFactory.getInstance();

		_sourceStore = storeFactory.getStoreInstance();

		String targetStoreClassName = getTargetStoreClassName();

		_targetStore = storeFactory.getStoreInstance(targetStoreClassName);

		migratePortlets();

		storeFactory.setStoreInstance(targetStoreClassName);

		MaintenanceUtil.appendStatus(
			"Please set " + PropsKeys.DL_STORE_IMPL +
				" in your portal-ext.properties to use " +
					targetStoreClassName);

		PropsValues.DL_STORE_IMPL = targetStoreClassName;
	}

	protected List<FileVersion> getFileVersions(FileEntry fileEntry) {
		List<FileVersion> fileVersions = fileEntry.getFileVersions(
			WorkflowConstants.STATUS_ANY);

		return ListUtil.sort(
			fileVersions, new FileVersionVersionComparator(true));
	}

	protected String getSourceStoreClassName() {
		StoreFactory storeFactory = StoreFactory.getInstance();

		Store sourceStore = storeFactory.getStoreInstance();

		return sourceStore.getClass().getName();
	}

	protected String getTargetStoreClassName() {
		String[] values = getParameterValues();

		return values[0];
	}

	protected boolean isDeleteFilesFromSourceStore() {
		String[] values = getParameterValues();

		return GetterUtil.getBoolean(values[1]);
	}

	protected void migrateDL() throws PortalException {
		int count = DLFileEntryLocalServiceUtil.getFileEntriesCount();

		MaintenanceUtil.appendStatus(
			"Migrating " + count + " documents and media files");

		ActionableDynamicQuery actionableDynamicQuery =
			DLFileEntryLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {

				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					Property classNameIdProperty = PropertyFactoryUtil.forName(
						"classNameId");

					dynamicQuery.add(classNameIdProperty.eq(0L));
				}

			});
		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod() {

				@Override
				public void performAction(Object object) {
					DLFileEntry dlFileEntry = (DLFileEntry)object;

					migrateDLFileEntry(
						dlFileEntry.getCompanyId(),
						dlFileEntry.getDataRepositoryId(),
						new LiferayFileEntry(dlFileEntry));
				}

			});

		actionableDynamicQuery.performActions();

		if (isDeleteFilesFromSourceStore()) {
			DLPreviewableProcessor.deleteFiles();
		}
	}

	protected void migrateFile(
		long companyId, long repositoryId, String fileName,
		String versionNumber) {

		try {
			InputStream is = _sourceStore.getFileAsStream(
				companyId, repositoryId, fileName, versionNumber);

			if (versionNumber.equals(Store.VERSION_DEFAULT)) {
				_targetStore.addFile(companyId, repositoryId, fileName, is);
			}
			else {
				_targetStore.updateFile(
					companyId, repositoryId, fileName, versionNumber, is);
			}

			if (isDeleteFilesFromSourceStore()) {
				_sourceStore.deleteFile(
					companyId, repositoryId, fileName, versionNumber);
			}
		}
		catch (Exception e) {
			_log.error("Migration failed for " + fileName, e);
		}
	}

	protected void migrateImages() throws PortalException {
		int count = ImageLocalServiceUtil.getImagesCount();

		MaintenanceUtil.appendStatus("Migrating " + count + " images");

		ActionableDynamicQuery actionableDynamicQuery =
			ImageLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod() {

				@Override
				public void performAction(Object object) {
					Image image = (Image)object;

					String fileName =
						image.getImageId() + StringPool.PERIOD +
							image.getType();

					migrateFile(0, 0, fileName, Store.VERSION_DEFAULT);
				}

			});

		actionableDynamicQuery.performActions();
	}

	protected void migrateMB() throws PortalException {
		int count = MBMessageLocalServiceUtil.getMBMessagesCount();

		MaintenanceUtil.appendStatus(
			"Migrating message boards attachments in " + count + " messages");

		ActionableDynamicQuery actionableDynamicQuery =
			MBMessageLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod() {

				@Override
				public void performAction(Object object)
					throws PortalException {

					MBMessage mbMessage = (MBMessage)object;

					for (FileEntry fileEntry :
							mbMessage.getAttachmentsFileEntries()) {

						DLFileEntry dlFileEntry =
							(DLFileEntry)fileEntry.getModel();

						migrateDLFileEntry(
							mbMessage.getCompanyId(),
							DLFolderConstants.getDataRepositoryId(
								dlFileEntry.getRepositoryId(),
								dlFileEntry.getFolderId()),
							new LiferayFileEntry(dlFileEntry));
					}
				}

			});

		actionableDynamicQuery.performActions();
	}

	protected void migratePortlets() throws Exception {
		migrateImages();
		migrateDL();
		migrateMB();

		Collection<DLStoreConvertProcess> dlStoreConvertProcesses =
			_getDLStoreConvertProcesses();

		for (DLStoreConvertProcess dlStoreConvertProcess :
				dlStoreConvertProcesses) {

			dlStoreConvertProcess.migrate(this);
		}
	}

	private Collection<DLStoreConvertProcess> _getDLStoreConvertProcesses() {
		try {
			Registry registry = RegistryUtil.getRegistry();

			return registry.getServices(DLStoreConvertProcess.class, null);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	private static final String _FILE_SYSTEM_STORE_SUFFIX = "FileSystemStore";

	private static final Log _log = LogFactoryUtil.getLog(
		ConvertDocumentLibrary.class);

	private Store _sourceStore;
	private Store _targetStore;

}