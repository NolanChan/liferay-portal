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

package com.liferay.osb.lcs.service.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.lcs.activation.LCSClusterEntryTokenContentAdvisor;
import com.liferay.lcs.util.LCSConstants;
import com.liferay.oauth.model.OAuthUser;
import com.liferay.oauth.service.OAuthUserService;
import com.liferay.osb.lcs.advisor.CommandMessageAdvisor;
import com.liferay.osb.lcs.configuration.OSBLCSConfiguration;
import com.liferay.osb.lcs.model.LCSClusterEntryToken;
import com.liferay.osb.lcs.model.LCSClusterNode;
import com.liferay.osb.lcs.model.impl.LCSClusterEntryTokenImpl;
import com.liferay.osb.lcs.service.base.LCSClusterEntryTokenLocalServiceBaseImpl;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Igor Beslic
 * @see    LCSClusterEntryTokenLocalServiceBaseImpl
 * @see    com.liferay.osb.lcs.service.LCSClusterEntryTokenLocalServiceUtil
 */
@ProviderType
public class LCSClusterEntryTokenLocalServiceImpl
	extends LCSClusterEntryTokenLocalServiceBaseImpl {

	@Override
	public LCSClusterEntryToken addLCSClusterEntryToken(
			long userId, long lcsClusterEntryId,
			Map<String, String> lcsServicesConfiguration)
		throws PortalException {

		LCSClusterEntryToken lcsClusterEntryToken =
			new LCSClusterEntryTokenImpl();

		lcsClusterEntryToken.setLcsClusterEntryTokenId(
			counterLocalService.increment(
				LCSClusterEntryToken.class.getName()));

		lcsClusterEntryToken.setUserId(userId);
		lcsClusterEntryToken.setCreateDate(new Date());
		lcsClusterEntryToken.setLcsClusterEntryId(lcsClusterEntryId);

		OSBLCSConfiguration configuration = getConfiguration();

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		OAuthUser oAuthUser = _oAuthUserService.addOAuthUser(
			configuration.osbLcsPortletOauthConsumerKey(), serviceContext);

		boolean portalPropertiesLCSServiceEnabled = GetterUtil.getBoolean(
			lcsServicesConfiguration.get(
				LCSConstants.PORTAL_PROPERTIES_LCS_SERVICE_ENABLED));

		String blacklistedPropertyKeysString = null;

		if (portalPropertiesLCSServiceEnabled &&
			lcsServicesConfiguration.containsKey(
				LCSConstants.PORTAL_PROPERTIES_BLACKLIST)) {

			blacklistedPropertyKeysString = lcsServicesConfiguration.get(
				LCSConstants.PORTAL_PROPERTIES_BLACKLIST);

			lcsServicesConfiguration.remove(
				LCSConstants.PORTAL_PROPERTIES_BLACKLIST);
		}

		LCSClusterEntryTokenContentAdvisor lcsClusterEntryTokenContentAdvisor =
			new LCSClusterEntryTokenContentAdvisor(
				oAuthUser.getAccessSecret(), oAuthUser.getAccessToken(),
				lcsServicesConfiguration, blacklistedPropertyKeysString);

		lcsClusterEntryToken.setContent(
			lcsClusterEntryTokenContentAdvisor.getContent());

		return lcsClusterEntryTokenPersistence.update(lcsClusterEntryToken);
	}

	@Override
	public LCSClusterEntryToken deleteLCSClusterEntryToken(
			long lcsClusterEntryTokenId)
		throws PortalException {

		LCSClusterEntryToken lcsClusterEntryToken =
			lcsClusterEntryTokenPersistence.fetchByPrimaryKey(
				lcsClusterEntryTokenId);

		List<LCSClusterNode> lcsClusterNodes =
			lcsClusterNodeLocalService.getLCSClusterEntryLCSClusterNodes(
				lcsClusterEntryToken.getLcsClusterEntryId(), true);

		for (LCSClusterNode lcsClusterNode : lcsClusterNodes) {
			if (lcsClusterNode.isOffline()) {
				continue;
			}

			_commandMessageAdvisor.invalidateLCSClusterEntryToken(
				lcsClusterNode.getKey());
		}

		return lcsClusterEntryTokenPersistence.remove(lcsClusterEntryTokenId);
	}

	@Override
	public LCSClusterEntryToken fetchLCSClusterEntryLCSClusterEntryToken(
		long lcsClusterEntryId) {

		return lcsClusterEntryTokenPersistence.fetchByLCSClusterEntryId(
			lcsClusterEntryId);
	}

	@Override
	public LCSClusterEntryToken fetchLCSClusterEntryToken(
		long lcsClusterEntryTokenId) {

		return lcsClusterEntryTokenPersistence.fetchByPrimaryKey(
			lcsClusterEntryTokenId);
	}

	@Override
	public LCSClusterEntryToken regenerateLCSClusterEntryToken(
			long userId, long lcsClusterEntryId,
			Map<String, String> lcsServicesConfiguration)
		throws PortalException {

		LCSClusterEntryToken lcsClusterEntryToken =
			fetchLCSClusterEntryLCSClusterEntryToken(lcsClusterEntryId);

		deleteLCSClusterEntryToken(
			lcsClusterEntryToken.getLcsClusterEntryTokenId());

		return addLCSClusterEntryToken(
			userId, lcsClusterEntryId, lcsServicesConfiguration);
	}

	protected OSBLCSConfiguration getConfiguration()
		throws ConfigurationException {

		return _configurationProvider.getCompanyConfiguration(
			OSBLCSConfiguration.class, 0);
	}

	@ServiceReference(type = CommandMessageAdvisor.class)
	private CommandMessageAdvisor _commandMessageAdvisor;

	@ServiceReference(type = ConfigurationProvider.class)
	private ConfigurationProvider _configurationProvider;

	@BeanReference(type = OAuthUserService.class)
	private OAuthUserService _oAuthUserService;

}