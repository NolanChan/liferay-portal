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

package com.liferay.osb.lcs.advisor.impl;

import com.liferay.lcs.internal.activation.LCSClusterEntryTokenContentAdvisor;
import com.liferay.lcs.security.KeyStoreAdvisor;
import com.liferay.lcs.security.KeyStoreFactory;
import com.liferay.lcs.util.LCSConstants;
import com.liferay.oauth.model.OAuthUser;
import com.liferay.oauth.service.OAuthUserService;
import com.liferay.osb.lcs.advisor.LCSClusterEntryTokenAdvisor;
import com.liferay.osb.lcs.model.LCSClusterEntryToken;
import com.liferay.osb.lcs.service.LCSClusterEntryTokenService;
import com.liferay.osb.lcs.util.PortletPropsValues;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONSerializer;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.util.Encryptor;

import java.security.Key;
import java.security.KeyStore;

import java.util.Map;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Igor Beslic
 */
public class LCSClusterEntryTokenAdvisorImpl
	implements LCSClusterEntryTokenAdvisor {

	public void generateLCSClusterEntryToken(
			long lcsClusterEntryId,
			Map<String, String> lcsServicesConfiguration,
			ServiceContext serviceContext)
		throws PortalException {

		OAuthUser oAuthUser = _oAuthUserService.addOAuthUser(
			PortletPropsValues.OSB_LCS_PORTLET_OAUTH_CONSUMER_KEY,
			serviceContext);

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

		_lcsClusterEntryTokenService.addLCSClusterEntryToken(
			lcsClusterEntryId, lcsClusterEntryTokenContentAdvisor.getContent());
	}

	public byte[] getLCSEntryTokenEncryptedBytes(long lcsClusterEntryId)
		throws Exception {

		LCSClusterEntryToken lcsClusterEntryToken =
			_lcsClusterEntryTokenService.
				fetchLCSClusterEntryLCSClusterEntryToken(lcsClusterEntryId);

		JSONSerializer jsonSerializer = JSONFactoryUtil.createJSONSerializer();

		String lcsClusterEntryTokenJSON = jsonSerializer.serialize(
			lcsClusterEntryToken);

		KeyStore keyStore = KeyStoreFactory.getInstance(
			PortletPropsValues.DIGITAL_SIGNATURE_KEY_STORE_PATH,
			PortletPropsValues.DIGITAL_SIGNATURE_KEY_STORE_TYPE);

		KeyStoreAdvisor keyStoreAdvisor = new KeyStoreAdvisor();

		String keyName = keyStoreAdvisor.getLatestKeyAlias(
			PortletPropsValues.DIGITAL_SIGNATURE_KEY_NAME, keyStore);

		KeyStore.ProtectionParameter protectionParameter =
			new KeyStore.PasswordProtection("_k3y#5t0r3-p45S".toCharArray());

		KeyStore.PrivateKeyEntry privateKeyEntry =
			(KeyStore.PrivateKeyEntry)keyStore.getEntry(
				keyName, protectionParameter);

		Key symmetricKeyEntry = Encryptor.generateKey("AES");

		byte[] symmetricKeyEncoded = symmetricKeyEntry.getEncoded();

		byte[] symmetricKeyEncrypted = Encryptor.encryptUnencoded(
			privateKeyEntry.getPrivateKey(), symmetricKeyEncoded);

		byte[] lcsClusterEntryTokenEncrypted = Encryptor.encryptUnencoded(
			symmetricKeyEntry, lcsClusterEntryTokenJSON);

		return ArrayUtil.append(
			symmetricKeyEncrypted, lcsClusterEntryTokenEncrypted);
	}

	public void regenerateLCSClusterEntryToken(
			long lcsClusterEntryId,
			Map<String, String> lcsServicesConfiguration,
			ServiceContext serviceContext)
		throws PortalException {

		LCSClusterEntryToken lcsClusterEntryToken =
			_lcsClusterEntryTokenService.
				fetchLCSClusterEntryLCSClusterEntryToken(lcsClusterEntryId);

		_lcsClusterEntryTokenService.deleteLCSClusterEntryToken(
			lcsClusterEntryToken.getLcsClusterEntryTokenId());

		generateLCSClusterEntryToken(
			lcsClusterEntryId, lcsServicesConfiguration, serviceContext);
	}

	@Reference(bind = "-", unbind = "-")
	public void setLcsClusterEntryTokenService(
		LCSClusterEntryTokenService lcsClusterEntryTokenService) {

		_lcsClusterEntryTokenService = lcsClusterEntryTokenService;
	}

	private LCSClusterEntryTokenService _lcsClusterEntryTokenService;
	private final OAuthUserService _oAuthUserService;

}