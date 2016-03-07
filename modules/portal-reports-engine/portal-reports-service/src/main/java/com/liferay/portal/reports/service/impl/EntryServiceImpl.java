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

package com.liferay.portal.reports.service.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.reports.model.Entry;
import com.liferay.portal.reports.service.base.EntryServiceBaseImpl;
import com.liferay.portal.reports.service.permission.DefinitionPermission;
import com.liferay.portal.reports.service.permission.EntryPermission;
import com.liferay.portal.reports.service.permission.ReportsActionKeys;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author Gavin Wan
 */
@ProviderType
public class EntryServiceImpl extends EntryServiceBaseImpl {

	public Entry addEntry(
			long groupId, long definitionId, String format,
			boolean schedulerRequest, Date startDate, Date endDate,
			boolean repeating, String recurrence, String emailNotifications,
			String emailDelivery, String portletId, String pageURL,
			String reportName, String reportParameters,
			ServiceContext serviceContext)
		throws PortalException {

		DefinitionPermission.check(
			getPermissionChecker(), definitionId, ReportsActionKeys.ADD_REPORT);

		return entryLocalService.addEntry(
			getUserId(), groupId, definitionId, format, schedulerRequest,
			startDate, endDate, repeating, recurrence, emailNotifications,
			emailDelivery, portletId, pageURL, reportName, reportParameters,
			serviceContext);
	}

	public void deleteAttachment(long companyId, long entryId, String fileName)
		throws PortalException {

		EntryPermission.check(
			getPermissionChecker(), entryId, ActionKeys.DELETE);

		entryLocalService.deleteAttachment(companyId, fileName);
	}

	public Entry deleteEntry(long entryId) throws PortalException {
		EntryPermission.check(
			getPermissionChecker(), entryId, ActionKeys.DELETE);

		return entryLocalService.deleteEntry(entryId);
	}

	public List<Entry> getEntries(
			long groupId, String definitionName, String userName,
			Date createDateGT, Date createDateLT, boolean andSearch, int start,
			int end, OrderByComparator orderByComparator)
		throws PortalException {

		List<Entry> entries = entryLocalService.getEntries(
			groupId, definitionName, userName, createDateGT, createDateLT,
			andSearch, start, end, orderByComparator);

		return filterEntries(entries);
	}

	public int getEntriesCount(
		long groupId, String definitionName, String userName, Date createDateGT,
		Date createDateLT, boolean andSearch) {

		return entryLocalService.getEntriesCount(
			groupId, definitionName, userName, createDateGT, createDateLT,
			andSearch);
	}

	public void sendEmails(
			long entryId, String fileName, String[] emailAddresses,
			boolean notification)
		throws PortalException {

		EntryPermission.check(getPermissionChecker(), entryId, ActionKeys.VIEW);

		entryLocalService.sendEmails(
			entryId, fileName, emailAddresses, notification);
	}

	public void unscheduleEntry(long entryId) throws PortalException {
		EntryPermission.check(
			getPermissionChecker(), entryId, ActionKeys.DELETE);

		entryLocalService.unscheduleEntry(entryId);
	}

	protected List<Entry> filterEntries(List<Entry> entries)
		throws PortalException {

		entries = ListUtil.copy(entries);

		Iterator<Entry> itr = entries.iterator();

		while (itr.hasNext()) {
			if (!EntryPermission.contains(
					getPermissionChecker(), itr.next(), ActionKeys.VIEW)) {

				itr.remove();
			}
		}

		return entries;
	}

}