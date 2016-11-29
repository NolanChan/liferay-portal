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

import com.liferay.lcs.security.KeyStoreAdvisor;
import com.liferay.lcs.security.KeyStoreFactory;
import com.liferay.osb.lcs.advisor.LCSClusterEntryTokenAdvisor;
import com.liferay.osb.lcs.configuration.OSBLCSConfiguration;
import com.liferay.osb.lcs.model.LCSClusterEntryToken;
import com.liferay.petra.encryptor.Encryptor;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONSerializer;
import com.liferay.portal.kernel.util.ArrayUtil;

import java.security.Key;
import java.security.KeyStore;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Igor Beslic
 */
@Component(
	configurationPid = "com.liferay.osb.lcs.configuration.OSBLCSConfiguration",
	configurationPolicy = ConfigurationPolicy.REQUIRE, immediate = true,
	service = LCSClusterEntryTokenAdvisor.class
)
public class LCSClusterEntryTokenAdvisorImpl
	implements LCSClusterEntryTokenAdvisor {

	@Override
	public byte[] getLCSEntryTokenEncryptedBytes(
			LCSClusterEntryToken lcsClusterEntryToken)
		throws Exception {

		JSONSerializer jsonSerializer = JSONFactoryUtil.createJSONSerializer();

		String lcsClusterEntryTokenJSON = jsonSerializer.serialize(
			lcsClusterEntryToken);

		KeyStore keyStore = KeyStoreFactory.getInstance(
			_osbLCSConfiguration.digitalSignatureKeyStorePath(),
			_osbLCSConfiguration.osbPortletKeyStoreType());

		KeyStoreAdvisor keyStoreAdvisor = new KeyStoreAdvisor();

		String keyName = keyStoreAdvisor.getLatestKeyAlias(
			_osbLCSConfiguration.digitalSignatureKeyName(), keyStore);

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

	@Activate
	protected void activate(Map<String, Object> properties) {
		_osbLCSConfiguration = ConfigurableUtil.createConfigurable(
			OSBLCSConfiguration.class, properties);
	}

	@Deactivate
	protected void deactivate() {
		_osbLCSConfiguration = null;
	}

	private static volatile OSBLCSConfiguration _osbLCSConfiguration;

}