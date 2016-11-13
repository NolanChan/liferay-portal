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

package com.liferay.osb.lcs.internal.events;

import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.expando.kernel.model.ExpandoColumnConstants;
import com.liferay.expando.kernel.model.ExpandoTable;
import com.liferay.expando.kernel.service.ExpandoColumnLocalService;
import com.liferay.expando.kernel.service.ExpandoTableLocalService;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.events.LifecycleEvent;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.GetterUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ivica Cardic
 */
@Component(
	immediate = true, property = {"key=application.startup.events"},
	service = LifecycleAction.class
)
public class SetUpExpandoAction implements LifecycleAction {

	@Override
	public void processLifecycleEvent(LifecycleEvent lifecycleEvent)
		throws ActionException {

		try {
			String[] ids = lifecycleEvent.getIds();

			ExpandoTable expandoTable =
				_expandoTableLocalService.fetchDefaultTable(
					GetterUtil.getLong(ids[0]), User.class.getName());

			if (expandoTable == null) {
				expandoTable = _expandoTableLocalService.addDefaultTable(
					GetterUtil.getLong(ids[0]), User.class.getName());
			}

			ExpandoColumn expandoColumn = _expandoColumnLocalService.getColumn(
				expandoTable.getTableId(), "defaultLCSProjectId");

			if (expandoColumn == null) {
				_expandoColumnLocalService.addColumn(
					expandoTable.getTableId(), "defaultLCSProjectId",
					ExpandoColumnConstants.LONG, 0L);
			}
		}
		catch (Exception e) {
			throw new ActionException(e);
		}
	}

	@Reference(bind = "-", unbind = "-")
	public void setExpandoColumnLocalService(
		ExpandoColumnLocalService expandoColumnLocalService) {

		_expandoColumnLocalService = expandoColumnLocalService;
	}

	@Reference(bind = "-", unbind = "-")
	public void setExpandoTableLocalService(
		ExpandoTableLocalService expandoTableLocalService) {

		_expandoTableLocalService = expandoTableLocalService;
	}

	private ExpandoColumnLocalService _expandoColumnLocalService;
	private ExpandoTableLocalService _expandoTableLocalService;

}