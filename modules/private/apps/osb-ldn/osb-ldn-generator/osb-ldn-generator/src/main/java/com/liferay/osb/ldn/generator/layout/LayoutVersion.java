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

package com.liferay.osb.ldn.generator.layout;

import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.expando.kernel.model.ExpandoColumnConstants;
import com.liferay.expando.kernel.model.ExpandoTable;
import com.liferay.expando.kernel.model.ExpandoTableConstants;
import com.liferay.expando.kernel.model.ExpandoValue;
import com.liferay.expando.kernel.service.ExpandoColumnLocalService;
import com.liferay.expando.kernel.service.ExpandoTableLocalService;
import com.liferay.expando.kernel.service.ExpandoValueLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.LayoutLocalService;

import java.util.List;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ryan Park
 */
@Component(service = LayoutVersion.class)
public class LayoutVersion {

	public int getLayoutVersion(long plid) throws PortalException {
		Layout layout = _layoutLocalService.getLayout(plid);

		ExpandoValue expandoValue = _expandoValueLocalService.getValue(
			layout.getCompanyId(), Layout.class.getName(), _TABLE_NAME,
			_COLUMN_NAME, plid);

		if (expandoValue == null) {
			return 0;
		}

		return expandoValue.getInteger();
	}

	public void setLayoutVersion(long plid, int version)
		throws PortalException {

		Layout layout = _layoutLocalService.getLayout(plid);

		_expandoValueLocalService.addValue(
			layout.getCompanyId(), Layout.class.getName(), _TABLE_NAME,
			_COLUMN_NAME, plid, version);
	}

	@Activate
	protected void activate() {
		try {
			List<Company> companies = _companyLocalService.getCompanies();

			for (Company company : companies) {
				setupLayoutExpando(company.getCompanyId());
			}
		}
		catch (PortalException pe) {
			_log.error("Unable to setup layout version", pe);
		}
	}

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	protected void setupLayoutExpando(long companyId) throws PortalException {
		ExpandoTable expandoTable = null;

		try {
			expandoTable = _expandoTableLocalService.getTable(
				companyId, Layout.class.getName(), _TABLE_NAME);
		}
		catch (PortalException pe) {

			// LPS-52675

			if (_log.isDebugEnabled()) {
				_log.debug(pe, pe);
			}

			expandoTable = _expandoTableLocalService.addDefaultTable(
				companyId, Layout.class.getName());
		}

		ExpandoColumn expandoColumn = _expandoColumnLocalService.getColumn(
			expandoTable.getTableId(), _COLUMN_NAME);

		if (expandoColumn == null) {
			_expandoColumnLocalService.addColumn(
				expandoTable.getTableId(), _COLUMN_NAME,
				ExpandoColumnConstants.INTEGER);
		}
	}

	private static final String _COLUMN_NAME = "version";

	private static final String _TABLE_NAME =
		ExpandoTableConstants.DEFAULT_TABLE_NAME;

	private static final Log _log = LogFactoryUtil.getLog(LayoutVersion.class);

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference
	private ExpandoColumnLocalService _expandoColumnLocalService;

	@Reference
	private ExpandoTableLocalService _expandoTableLocalService;

	@Reference
	private ExpandoValueLocalService _expandoValueLocalService;

	@Reference
	private LayoutLocalService _layoutLocalService;

}