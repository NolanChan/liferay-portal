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

package com.liferay.saml.upgrade;

import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;
import com.liferay.saml.upgrade.v1_0_0.UpgradeSamlSpAuthRequest;
import com.liferay.saml.upgrade.v1_0_0.UpgradeSamlSpMessage;
import org.osgi.service.component.annotations.Component;

/**
 * @author Carlos Sierra Andrés
 */
@Component(immediate=true)
public class UpgradeSaml implements UpgradeStepRegistrator {

	public void register(Registry registry) {
		registry.register(
			"com.liferay.saml.service", "1.0.0", "1.1.0",
			new com.liferay.saml.upgrade.v1_0_0.UpgradeSamlIdpSpSession(),
			new UpgradeSamlSpAuthRequest(), new UpgradeSamlSpMessage(),
			new com.liferay.saml.upgrade.v1_0_0.UpgradeSamlSpSession());

		registry.register(
			"com.liferay.saml.service", "1.1.0", "1.1.1",
			new com.liferay.saml.upgrade.v1_1_0.UpgradeSamlSpSession());

		registry.register(
			"com.liferay.saml.service", "1.1.1", "1.1.2",
			new com.liferay.saml.upgrade.v1_1_1.UpgradeSamlSpSession());

		registry.register(
			"com.liferay.saml.service", "1.1.2", "1.1.3",
			new com.liferay.saml.upgrade.v1_1_2.UpgradeSamlSpIdpConnection());
	}

}
