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

import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.SimpleAction;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.User;
import com.liferay.portlet.expando.model.ExpandoColumn;
import com.liferay.portlet.expando.model.ExpandoColumnConstants;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;

/**
 * @author Ivica Cardic
 */
public class SetUpExpandoAction extends SimpleAction {

	@Override
	public void run(String[] ids) throws ActionException {
		try {
			ExpandoTable expandoTable =
				ExpandoTableLocalServiceUtil.fetchDefaultTable(
					GetterUtil.getLong(ids[0]), User.class.getName());

			if (expandoTable == null) {
				expandoTable = ExpandoTableLocalServiceUtil.addDefaultTable(
					GetterUtil.getLong(ids[0]), User.class.getName());
			}

			ExpandoColumn expandoColumn =
				ExpandoColumnLocalServiceUtil.getColumn(
					expandoTable.getTableId(), "defaultLCSProjectId");

			if (expandoColumn == null) {
				ExpandoColumnLocalServiceUtil.addColumn(
					expandoTable.getTableId(), "defaultLCSProjectId",
					ExpandoColumnConstants.LONG, 0l);
			}
		}
		catch (Exception e) {
			throw new ActionException(e);
		}
	}

}