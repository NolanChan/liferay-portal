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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.repository.capabilities.SyncCapability;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.transaction.TransactionCommitCallbackRegistryUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.repository.liferayrepository.model.LiferayFileEntry;
import com.liferay.portal.repository.liferayrepository.model.LiferayFolder;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portlet.documentlibrary.model.DLSyncConstants;
import com.liferay.portlet.documentlibrary.model.DLSyncEvent;
import com.liferay.portlet.documentlibrary.service.DLSyncEventLocalServiceUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @author Adolfo Pérez
 */
public class LiferaySyncCapability implements SyncCapability {

	@Override
	public void addFolder(Folder folder) throws PortalException {
		throw new UnsupportedOperationException("not implemented");
	}

	@Override
	public void deleteFileEntry(FileEntry fileEntry) throws PortalException {
		throw new UnsupportedOperationException("not implemented");
	}

	@Override
	public void deleteFolder(Folder folder) throws PortalException {
		throw new UnsupportedOperationException("not implemented");
	}

	@Override
	public void moveFileEntry(FileEntry fileEntry) throws PortalException {
		throw new UnsupportedOperationException("not implemented");
	}

	@Override
	public void moveFolder(Folder folder) throws PortalException {
		throw new UnsupportedOperationException("not implemented");
	}

	@Override
	public void restoreFileEntry(FileEntry fileEntry) throws PortalException {
		throw new UnsupportedOperationException("not implemented");
	}

	@Override
	public void restoreFolder(Folder folder) throws PortalException {
		throw new UnsupportedOperationException("not implemented");
	}

	@Override
	public void trashFileEntry(FileEntry fileEntry) throws PortalException {
		throw new UnsupportedOperationException("not implemented");
	}

	@Override
	public void trashFolder(Folder folder) throws PortalException {
		throw new UnsupportedOperationException("not implemented");
	}

	@Override
	public void updateFileEntry(FileEntry fileEntry) throws PortalException {
		throw new UnsupportedOperationException("not implemented");
	}

	@Override
	public void updateFolder(Folder folder) throws PortalException {
		throw new UnsupportedOperationException("not implemented");
	}

	protected boolean isStagingGroup(long groupId) {
		try {
			Group group = GroupLocalServiceUtil.getGroup(groupId);

			return group.isStagingGroup();
		}
		catch (Exception e) {
			return false;
		}
	}

	protected void registerDLSyncEventCallback(
		final String event, final String type, final long typePK) {

		DLSyncEvent dlSyncEvent = DLSyncEventLocalServiceUtil.addDLSyncEvent(
			event, type, typePK);

		final long modifiedTime = dlSyncEvent.getModifiedTime();

		TransactionCommitCallbackRegistryUtil.registerCallback(
			new Callable<Void>() {

				@Override
				public Void call() throws Exception {
					Message message = new Message();

					Map<String, Object> values = new HashMap<String, Object>(4);

					values.put("event", event);
					values.put("modifiedTime", modifiedTime);
					values.put("type", type);
					values.put("typePK", typePK);

					message.setValues(values);

					MessageBusUtil.sendMessage(
						DestinationNames.DOCUMENT_LIBRARY_SYNC_EVENT_PROCESSOR,
						message);

					return null;
				}

			}
		);
	}

	protected void registerDLSyncEventCallback(
			String event, FileEntry fileEntry)
		throws PortalException {

		if (isStagingGroup(fileEntry.getGroupId()) ||
			!(fileEntry instanceof LiferayFileEntry)) {

			return;
		}

		if (!event.equals(DLSyncConstants.EVENT_DELETE) &&
			!event.equals(DLSyncConstants.EVENT_TRASH)) {

			FileVersion fileVersion = fileEntry.getFileVersion();

			if (!fileVersion.isApproved()) {
				return;
			}
		}

		registerDLSyncEventCallback(
			event, DLSyncConstants.TYPE_FILE, fileEntry.getFileEntryId());
	}

	protected void registerDLSyncEventCallback(String event, Folder folder) {
		if (isStagingGroup(folder.getGroupId()) ||
			!(folder instanceof LiferayFolder)) {

			return;
		}

		registerDLSyncEventCallback(
			event, DLSyncConstants.TYPE_FOLDER, folder.getFolderId());
	}

}