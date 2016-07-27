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

package com.liferay.saml.messaging;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseSchedulerEntryMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import com.liferay.portal.kernel.scheduler.TriggerFactoryUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.saml.configuration.SAMLConfiguration;
import com.liferay.saml.model.SamlIdpSpConnection;
import com.liferay.saml.model.SamlSpIdpConnection;
import com.liferay.saml.service.SamlIdpSpConnectionLocalService;
import com.liferay.saml.service.SamlSpIdpConnectionLocalService;
import com.liferay.saml.util.SamlUtil;

import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Mika Koivisto
 */
@Component(
	configurationPid = "com.liferay.saml.configuration.SAMLConfiguration",
	configurationPolicy = ConfigurationPolicy.OPTIONAL, immediate = true,
	service = SamlMetadataMessageListener.class
)
public class SamlMetadataMessageListener
	extends BaseSchedulerEntryMessageListener {

	@Activate
	protected void activate(Map<String, Object> properties) {
		_samlConfiguration = ConfigurableUtil.createConfigurable(
			SAMLConfiguration.class, properties);

		schedulerEntryImpl.setTrigger(
			TriggerFactoryUtil.createTrigger(
				getEventListenerClass(), getEventListenerClass(),
				_samlConfiguration.getMetadataRefreshInterval(),
				TimeUnit.SECOND));

		_schedulerEngineHelper.register(
			this, schedulerEntryImpl, DestinationNames.SCHEDULER_DISPATCH);
	}

	@Deactivate
	protected void deactivate() {
		_schedulerEngineHelper.unregister(this);
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		List<Company> companies = _companyLocalService.getCompanies(false);

		for (Company company : companies) {
			if (!company.isActive()) {
				continue;
			}

			Long companyId = CompanyThreadLocal.getCompanyId();

			CompanyThreadLocal.setCompanyId(company.getCompanyId());

			try {
				if (!SamlUtil.isEnabled()) {
					continue;
				}

				try {
					if (SamlUtil.isRoleIdp()) {
						updateSpMetadata(company.getCompanyId());
					}
					else if (SamlUtil.isRoleSp()) {
						updateIdpMetadata(company.getCompanyId());
					}
				}
				catch (SystemException se) {
					if (_log.isWarnEnabled()) {
						_log.warn(
							"Unable to refresh metadata for company " +
								company.getCompanyId(),
							se);
					}
				}
			}
			finally {
				CompanyThreadLocal.setCompanyId(companyId);
			}
		}
	}

	@Reference(unbind = "-")
	protected void setTriggerFactory(TriggerFactory triggerFactory) {
	}

	protected void updateIdpMetadata(long companyId) {
		List<SamlSpIdpConnection> samlSpIdpConnections =
			_samlSpIdpConnectionLocalService.getSamlSpIdpConnections(companyId);

		for (SamlSpIdpConnection samlSpIdpConnection : samlSpIdpConnections) {
			if (!samlSpIdpConnection.isEnabled() ||
				Validator.isNull(samlSpIdpConnection.getMetadataUrl())) {

				continue;
			}

			try {
				_samlSpIdpConnectionLocalService.updateMetadata(
					samlSpIdpConnection.getSamlSpIdpConnectionId());
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to refresh IdP metadata for " +
							samlSpIdpConnection.getSamlIdpEntityId(),
						e);
				}
			}
		}
	}

	protected void updateSpMetadata(long companyId) {
		List<SamlIdpSpConnection> samlIdpSpConnections =
			_samlIdpSpConnectionLocalService.getSamlIdpSpConnections(companyId);

		for (SamlIdpSpConnection samlIdpSpConnection : samlIdpSpConnections) {
			if (!samlIdpSpConnection.isEnabled() ||
				Validator.isNull(samlIdpSpConnection.getMetadataUrl())) {

				continue;
			}

			try {
				_samlSpIdpConnectionLocalService.updateMetadata(
					samlIdpSpConnection.getSamlIdpSpConnectionId());
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to refresh SP metadata for " +
							samlIdpSpConnection.getSamlSpEntityId(),
						e);
				}
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SamlMetadataMessageListener.class);

	@Reference
	private CompanyLocalService _companyLocalService;

	private SAMLConfiguration _samlConfiguration;

	@Reference
	private SamlIdpSpConnectionLocalService _samlIdpSpConnectionLocalService;

	@Reference
	private SamlSpIdpConnectionLocalService _samlSpIdpConnectionLocalService;

	@Reference
	private SchedulerEngineHelper _schedulerEngineHelper;

}