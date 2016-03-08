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

package com.liferay.portal.workflow.kaleo.forms.web.display.context;

import com.liferay.dynamic.data.mapping.storage.StorageEngine;
import com.liferay.dynamic.data.mapping.util.DDMDisplay;
import com.liferay.dynamic.data.mapping.util.DDMDisplayRegistry;
import com.liferay.portal.workflow.kaleo.forms.web.display.context.util.KaleoFormsAdminRequestHelper;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Leonardo Barros
 */
public class KaleoFormsAdminDisplayContext extends KaleoFormsDisplayContext {

	public KaleoFormsAdminDisplayContext(
		HttpServletRequest request, DDMDisplayRegistry ddmDisplayRegistry,
		StorageEngine storageEngine) {

		super(storageEngine);

		_ddmDisplayRegistry = ddmDisplayRegistry;

		_kaleoFormsAdminRequestHelper = new KaleoFormsAdminRequestHelper(
			request);
	}

	public DDMDisplay getDDMDisplay() {
		return _ddmDisplayRegistry.getDDMDisplay(
			_kaleoFormsAdminRequestHelper.getPortletId());
	}

	private final DDMDisplayRegistry _ddmDisplayRegistry;
	private final KaleoFormsAdminRequestHelper _kaleoFormsAdminRequestHelper;

}