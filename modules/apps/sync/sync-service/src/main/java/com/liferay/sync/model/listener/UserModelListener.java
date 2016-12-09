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

package com.liferay.sync.model.listener;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.ResourcePermission;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.sync.constants.SyncDLObjectConstants;
import com.liferay.sync.constants.SyncDeviceConstants;
import com.liferay.sync.model.SyncDLObject;
import com.liferay.sync.model.SyncDevice;
import com.liferay.sync.service.SyncDLObjectLocalService;
import com.liferay.sync.service.SyncDeviceLocalService;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jonathan McCann
 */
@Component(immediate = true, service = ModelListener.class)
public class UserModelListener extends BaseModelListener<User> {

	@Override
	public void onAfterRemove(User user) throws ModelListenerException {
		try {
			List<SyncDevice> syncDevices =
				_syncDeviceLocalService.getSyncDevices(
					user.getUserId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null);

			for (SyncDevice syncDevice : syncDevices) {
				_syncDeviceLocalService.deleteSyncDevice(syncDevice);
			}
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	@Override
	public void onBeforeAddAssociation(
			Object classPK, String associationClassName,
			Object associationClassPK)
		throws ModelListenerException {

		if (!associationClassName.equals(Role.class.getName())) {
			return;
		}

		long roleId = (Long)associationClassPK;

		List<ResourcePermission> resourcePermissions =
			_resourcePermissionLocalService.getRoleResourcePermissions(roleId);

		for (ResourcePermission resourcePermission : resourcePermissions) {
			if (resourcePermission.hasActionId(ActionKeys.VIEW)) {
				SyncDLObject syncDLObject = getSyncDLObject(resourcePermission);

				if (syncDLObject == null) {
					return;
				}

				updateSyncDLObject(syncDLObject);
			}
		}
	}

	@Override
	public void onBeforeRemoveAssociation(
			Object classPK, String associationClassName,
			Object associationClassPK)
		throws ModelListenerException {

		if (!associationClassName.equals(Role.class.getName())) {
			return;
		}

		long roleId = (Long)associationClassPK;

		List<ResourcePermission> resourcePermissions =
			_resourcePermissionLocalService.getRoleResourcePermissions(roleId);

		for (ResourcePermission resourcePermission : resourcePermissions) {
			if (resourcePermission.hasActionId(ActionKeys.VIEW)) {
				SyncDLObject syncDLObject = getSyncDLObject(resourcePermission);

				if (syncDLObject == null) {
					return;
				}

				Date date = new Date();

				syncDLObject.setModifiedTime(date.getTime());
				syncDLObject.setLastPermissionChangeDate(date);

				_syncDLObjectLocalService.updateSyncDLObject(syncDLObject);
			}
		}
	}

	@Override
	public void onBeforeUpdate(User user) throws ModelListenerException {
		try {
			User originalUser = _userLocalService.getUser(user.getUserId());

			if (originalUser.isActive() && !user.isActive()) {
				List<SyncDevice> syncDevices =
					_syncDeviceLocalService.getSyncDevices(
						user.getUserId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS,
						null);

				for (SyncDevice syncDevice : syncDevices) {
					_syncDeviceLocalService.updateStatus(
						syncDevice.getSyncDeviceId(),
						SyncDeviceConstants.STATUS_INACTIVE);
				}
			}
			else if (!originalUser.isActive() && user.isActive()) {
				List<SyncDevice> syncDevices =
					_syncDeviceLocalService.getSyncDevices(
						user.getUserId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS,
						null);

				for (SyncDevice syncDevice : syncDevices) {
					_syncDeviceLocalService.updateStatus(
						syncDevice.getSyncDeviceId(),
						SyncDeviceConstants.STATUS_ACTIVE);
				}
			}
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	protected SyncDLObject getSyncDLObject(
		ResourcePermission resourcePermission) {

		String modelName = resourcePermission.getName();

		if (modelName.equals(DLFileEntry.class.getName())) {
			return _syncDLObjectLocalService.fetchSyncDLObject(
				SyncDLObjectConstants.TYPE_FILE,
				GetterUtil.getLong(resourcePermission.getPrimKey()));
		}
		else if (modelName.equals(DLFolder.class.getName())) {
			return _syncDLObjectLocalService.fetchSyncDLObject(
				SyncDLObjectConstants.TYPE_FOLDER,
				GetterUtil.getLong(resourcePermission.getPrimKey()));
		}

		return null;
	}

	@Reference(unbind = "-")
	protected void setResourcePermissionLocalService(
		ResourcePermissionLocalService resourcePermissionLocalService) {

		_resourcePermissionLocalService = resourcePermissionLocalService;
	}

	@Reference(unbind = "-")
	protected void setSyncDeviceLocalService(
		SyncDeviceLocalService syncDeviceLocalService) {

		_syncDeviceLocalService = syncDeviceLocalService;
	}

	@Reference(unbind = "-")
	protected void setSyncDLObjectLocalService(
		SyncDLObjectLocalService syncDLObjectLocalService) {

		_syncDLObjectLocalService = syncDLObjectLocalService;
	}

	@Reference(unbind = "-")
	protected void setUserLocalService(UserLocalService userLocalService) {
		_userLocalService = userLocalService;
	}

	protected void updateSyncDLObject(SyncDLObject syncDLObject) {
		syncDLObject.setModifiedTime(System.currentTimeMillis());

		_syncDLObjectLocalService.updateSyncDLObject(syncDLObject);

		String type = syncDLObject.getType();

		if (!type.equals(SyncDLObjectConstants.TYPE_FOLDER)) {
			return;
		}

		List<SyncDLObject> childSyncDLObjects =
			_syncDLObjectLocalService.getSyncDLObjects(
				syncDLObject.getRepositoryId(), syncDLObject.getTypePK());

		for (SyncDLObject childSyncDLObject : childSyncDLObjects) {
			updateSyncDLObject(childSyncDLObject);
		}
	}

	private static ResourcePermissionLocalService
		_resourcePermissionLocalService;
	private static SyncDeviceLocalService _syncDeviceLocalService;
	private static SyncDLObjectLocalService _syncDLObjectLocalService;
	private static UserLocalService _userLocalService;

}