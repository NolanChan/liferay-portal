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

package com.liferay.portal.reports.service.permission;

import com.liferay.portal.kernel.security.permission.PermissionUpdateHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.reports.model.Entry;
import com.liferay.portal.reports.service.EntryLocalService;

import java.util.Date;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Prathima Shreenath
 */
@Component(
	property = {"model.class.name=com.liferay.portal.reports.model.Entry"},
	service = PermissionUpdateHandler.class
)
public class EntryPermissionUpdateHandler implements PermissionUpdateHandler {

	@Override
	public void updatedPermission(String primKey) {
		Entry entry = _entryLocalService.fetchEntry(
			GetterUtil.getLong(primKey));

		if (entry == null) {
			return;
		}

		entry.setModifiedDate(new Date());

		_entryLocalService.updateEntry(entry);
	}

	@Reference(unbind = "-")
	protected void setEntryLocalService(EntryLocalService entryLocalService) {
		_entryLocalService = entryLocalService;
	}

	private static EntryLocalService _entryLocalService;

}