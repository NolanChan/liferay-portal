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

package com.liferay.dynamic.data.mapping.upgrade.v1_0_0;

import com.liferay.dynamic.data.mapping.model.DDMContent;
import com.liferay.dynamic.data.mapping.model.DDMStorageLink;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMStructureLink;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.portal.upgrade.v7_0_0.UpgradeKernelPackage;

/**
 * @author Marcellus Tavares
 */
public class UpgradeClassNames extends UpgradeKernelPackage {

	@Override
	protected String[][] getClassNames() {
		return _CLASS_NAMES;
	}

	@Override
	protected String[][] getResourceNames() {
		return _RESOURCE_NAMES;
	}

	private static final String[][] _CLASS_NAMES = new String[][] {
		{
			"com.liferay.portlet.dynamicdatamapping.model.DDMStructure",
			DDMStructure.class.getName()
		},
		{
			"com.liferay.portlet.dynamicdatamapping.model.DDMTemplate",
			DDMTemplate.class.getName()
		},
		{
			"com.liferay.portlet.dynamicdatamapping.model.DDMContent",
			DDMContent.class.getName()
		},
		{
			"com.liferay.portlet.dynamicdatamapping.model.DDMStorageLink",
			DDMStorageLink.class.getName()
		},
		{
			"com.liferay.portlet.dynamicdatamapping.model.DDMStructureLink",
			DDMStructureLink.class.getName()
		},
		{
			"com.liferay.portlet.dynamicdatamapping.model.DDMTemplate",
			DDMTemplate.class.getName()
		}
	};

	private static final String[][] _RESOURCE_NAMES = new String[][] {
		{
			"com.liferay.portlet.dynamicdatamapping",
			"com.liferay.dynamic.data.mapping"
		}
	};

}