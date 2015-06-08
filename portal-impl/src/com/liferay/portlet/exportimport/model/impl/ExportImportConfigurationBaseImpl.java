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

package com.liferay.portlet.exportimport.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portlet.exportimport.model.ExportImportConfiguration;
import com.liferay.portlet.exportimport.service.ExportImportConfigurationLocalServiceUtil;

/**
 * The extended model base implementation for the ExportImportConfiguration service. Represents a row in the &quot;ExportImportConfiguration&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ExportImportConfigurationImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ExportImportConfigurationImpl
 * @see ExportImportConfiguration
 * @generated
 */
@ProviderType
public abstract class ExportImportConfigurationBaseImpl
	extends ExportImportConfigurationModelImpl
	implements ExportImportConfiguration {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a export import configuration model instance should use the {@link ExportImportConfiguration} interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			ExportImportConfigurationLocalServiceUtil.addExportImportConfiguration(this);
		}
		else {
			ExportImportConfigurationLocalServiceUtil.updateExportImportConfiguration(this);
		}
	}
}