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

package com.liferay.marketplace.store.web.oauth.util;

import com.liferay.marketplace.store.web.configuration.MarketplaceStoreWebConfigurationValues;
import com.liferay.marketplace.store.web.oauth.api.MarketplaceApi;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.User;
import com.liferay.portal.service.CompanyLocalService;
import com.liferay.portlet.expando.DuplicateColumnNameException;
import com.liferay.portlet.expando.DuplicateTableNameException;
import com.liferay.portlet.expando.model.ExpandoColumnConstants;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.model.ExpandoValue;
import com.liferay.portlet.expando.service.ExpandoColumnLocalService;
import com.liferay.portlet.expando.service.ExpandoTableLocalService;
import com.liferay.portlet.expando.service.ExpandoValueLocalService;

import java.util.List;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import org.scribe.builder.api.Api;
import org.scribe.model.OAuthConfig;
import org.scribe.model.SignatureType;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

/**
 * @author Ryan Park
 */
@Component(immediate = true)
public class OAuthUtil {

	public static void deleteAccessToken(User user) throws PortalException {
		_instance._expandoValueLocalService.deleteValue(
			user.getCompanyId(), User.class.getName(), "MP", "secret",
			user.getUserId());
		_instance._expandoValueLocalService.deleteValue(
			user.getCompanyId(), User.class.getName(), "MP", "token",
			user.getUserId());
	}

	public static Token getAccessToken(User user) throws PortalException {
		ExpandoValue secretExpandoValue =
			_instance._expandoValueLocalService.getValue(
				user.getCompanyId(), User.class.getName(), "MP", "secret",
				user.getUserId());
		ExpandoValue tokenExpandoValue =
			_instance._expandoValueLocalService.getValue(
				user.getCompanyId(), User.class.getName(), "MP", "token",
				user.getUserId());

		if ((secretExpandoValue == null) || (tokenExpandoValue == null)) {
			return null;
		}

		return new Token(
			tokenExpandoValue.getString(), secretExpandoValue.getString());
	}

	public static OAuthService getOAuthService() {
		Api api = new MarketplaceApi();

		OAuthConfig oAuthConfig = new OAuthConfig(
			MarketplaceStoreWebConfigurationValues.MARKETPLACE_KEY,
			MarketplaceStoreWebConfigurationValues.MARKETPLACE_SECRET,
			MarketplaceStoreWebConfigurationValues.MARKETPLACE_URL,
			SignatureType.Header, null, null);

		return api.createService(oAuthConfig);
	}

	public static void updateAccessToken(User user, Token token)
		throws PortalException {

		_instance._expandoValueLocalService.addValue(
			user.getCompanyId(), User.class.getName(), "MP", "secret",
			user.getUserId(), token.getSecret());
		_instance._expandoValueLocalService.addValue(
			user.getCompanyId(), User.class.getName(), "MP", "token",
			user.getUserId(), token.getToken());
	}

	@Activate
	protected void activate() {
		_instance = this;

		List<Company> companys = _companyLocalService.getCompanies();

		for (Company company : companys) {
			try {
				_setupExpando(company.getCompanyId());
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to setup Marketplace for company " +
							company.getCompanyId() + ": " + e.getMessage());
				}
			}
		}
	}

	@Reference(unbind = "-")
	protected void setCompanyLocalService(
		CompanyLocalService companyLocalService) {

		_companyLocalService = companyLocalService;
	}

	@Reference(unbind = "-")
	protected void setExpandoColumnLocalService(
		ExpandoColumnLocalService expandoColumnLocalService) {

		_expandoColumnLocalService = expandoColumnLocalService;
	}

	@Reference(unbind = "-")
	protected void setExpandoTableLocalService(
		ExpandoTableLocalService expandoTableLocalService) {

		_expandoTableLocalService = expandoTableLocalService;
	}

	@Reference(unbind = "-")
	protected void setExpandoValueLocalService(
		ExpandoValueLocalService expandoValueLocalService) {

		_expandoValueLocalService = expandoValueLocalService;
	}

	private void _setupExpando(long companyId) throws Exception {
		ExpandoTable table = null;

		try {
			table = _expandoTableLocalService.addTable(
				companyId, User.class.getName(), "MP");
		}
		catch (DuplicateTableNameException dtne) {
			table = _expandoTableLocalService.getTable(
				companyId, User.class.getName(), "MP");
		}

		try {
			_expandoColumnLocalService.addColumn(
				table.getTableId(), "secret", ExpandoColumnConstants.STRING);
			_expandoColumnLocalService.addColumn(
				table.getTableId(), "token", ExpandoColumnConstants.STRING);
		}
		catch (DuplicateColumnNameException dcne) {
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(OAuthUtil.class);

	private static OAuthUtil _instance;

	private CompanyLocalService _companyLocalService;
	private ExpandoColumnLocalService _expandoColumnLocalService;
	private ExpandoTableLocalService _expandoTableLocalService;
	private ExpandoValueLocalService _expandoValueLocalService;

}