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
import com.liferay.portal.reports.model.Source;
import com.liferay.portal.reports.service.base.SourceServiceBaseImpl;
import com.liferay.portal.reports.service.permission.AdminPermission;
import com.liferay.portal.reports.service.permission.ReportsActionKeys;
import com.liferay.portal.reports.service.permission.SourcePermission;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Gavin Wan
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class SourceServiceImpl extends SourceServiceBaseImpl {

	public Source addSource(
			long groupId, Map<Locale, String> nameMap, String driverClassName,
			String driverUrl, String driverUserName, String driverPassword,
			ServiceContext serviceContext)
		throws PortalException {

		AdminPermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			ReportsActionKeys.ADD_SOURCE);

		return sourceLocalService.addSource(
			getUserId(), groupId, nameMap, driverClassName, driverUrl,
			driverUserName, driverPassword, serviceContext);
	}

	public Source deleteSource(long sourceId) throws PortalException {
		SourcePermission.check(
			getPermissionChecker(), sourceId, ActionKeys.DELETE);

		return sourceLocalService.deleteSource(sourceId);
	}

	public Source getSource(long sourceId) throws PortalException {
		SourcePermission.check(
			getPermissionChecker(), sourceId, ActionKeys.VIEW);

		return sourceLocalService.getSource(sourceId);
	}

	public List<Source> getSources(
			long groupId, String name, String driverUrl, boolean andSearch,
			int start, int end, OrderByComparator orderByComparator)
		throws PortalException {

		List<Source> sources = sourceLocalService.getSources(
			groupId, name, driverUrl, andSearch, start, end, orderByComparator);

		return filterSources(sources);
	}

	public int getSourcesCount(
		long groupId, String name, String driverUrl, boolean andSearch) {

		return sourceLocalService.getSourcesCount(
			groupId, name, driverUrl, andSearch);
	}

	public Source updateSource(
			long sourceId, Map<Locale, String> nameMap, String driverClassName,
			String driverUrl, String driverUserName, String driverPassword,
			ServiceContext serviceContext)
		throws PortalException {

		SourcePermission.check(
			getPermissionChecker(), sourceId, ActionKeys.UPDATE);

		return sourceLocalService.updateSource(
			sourceId, nameMap, driverClassName, driverUrl, driverUserName,
			driverPassword, serviceContext);
	}

	protected List<Source> filterSources(List<Source> sources)
		throws PortalException {

		sources = ListUtil.copy(sources);

		Iterator<Source> itr = sources.iterator();

		while (itr.hasNext()) {
			if (!SourcePermission.contains(
					getPermissionChecker(), itr.next(), ActionKeys.VIEW)) {

				itr.remove();
			}
		}

		return sources;
	}

}