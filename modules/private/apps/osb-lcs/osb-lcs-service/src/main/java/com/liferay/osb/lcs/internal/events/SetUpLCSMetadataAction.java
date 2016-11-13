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

package com.liferay.osb.lcs.internal.events;

import com.liferay.osb.lcs.configuration.OSBLCSConfiguration;
import com.liferay.osb.lcs.model.LCSMetadata;
import com.liferay.osb.lcs.nosql.service.LCSMetadataDetailsService;
import com.liferay.osb.lcs.service.LCSMetadataLocalServiceUtil;
import com.liferay.osb.lcs.util.ApplicationProfile;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.events.LifecycleEvent;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.io.IOException;
import java.io.InputStream;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

/**
 * @author Matija Petanjek
 */
@Component(
	configurationPid = "com.liferay.osb.lcs.configuration.OSBLCSConfiguration",
	configurationPolicy = ConfigurationPolicy.REQUIRE, immediate = true,
	property = {"key=application.startup.events"},
	service = LifecycleAction.class
)
public class SetUpLCSMetadataAction implements LifecycleAction {

	@Override
	public void processLifecycleEvent(LifecycleEvent lifecycleEvent)
		throws ActionException {

		if (_osbLCSConfiguration.applicationProfile() ==
				ApplicationProfile.PRODUCTION) {

			return;
		}

		try {
			addLCSMetadata();
		}
		catch (Exception e) {
			throw new ActionException(e);
		}
	}

	public void setLCSMetadataDetailsService(
		LCSMetadataDetailsService lcsMetadataDetailsService) {

		_lcsMetadataDetailsService = lcsMetadataDetailsService;
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_osbLCSConfiguration = ConfigurableUtil.createConfigurable(
			OSBLCSConfiguration.class, properties);
	}

	protected void addLCSMetadata() throws Exception {
		int i = 0;

		for (String lcsMetadataEntry :
				_osbLCSConfiguration.osbLcsPortletModelLcsmetadata()) {

			String[] lcsMetadataAttributes = lcsMetadataEntry.split(
				StringPool.COMMA);

			int buildNumber = GetterUtil.getInteger(lcsMetadataAttributes[0]);
			String gitTag = lcsMetadataAttributes[1];
			String portalEdition = lcsMetadataAttributes[2];

			LCSMetadata lcsMetadata =
				LCSMetadataLocalServiceUtil.fetchLCSMetadata(
					buildNumber, gitTag, portalEdition);

			if (lcsMetadata == null) {
				int supportedLCSPortlet = GetterUtil.getInteger(
					lcsMetadataAttributes[3]);
				int supportedPatchingTool = GetterUtil.getInteger(
					lcsMetadataAttributes[4]);

				LCSMetadataLocalServiceUtil.addLCSMetadata(
					buildNumber, gitTag, portalEdition, supportedLCSPortlet,
					supportedPatchingTool);

				_lcsMetadataDetailsService.addLCSMetadataDetails(
					buildNumber, new HashMap<String, String>(), gitTag,
					portalEdition, getPortalProperties(gitTag));
			}
		}
	}

	protected Map<String, String> getPortalProperties(String gitTag)
		throws IOException {

		Map<String, String> portalProperties = new HashMap<>();

		InputStream inputStream =
			SetUpLCSMetadataAction.class.getResourceAsStream(
				"dependencies/" + gitTag + "-portal.properties");

		Properties properties = new Properties();

		properties.load(inputStream);

		for (String key : properties.stringPropertyNames()) {
			portalProperties.put(key, properties.getProperty(key));
		}

		return portalProperties;
	}

	private static volatile OSBLCSConfiguration _osbLCSConfiguration;

	private LCSMetadataDetailsService _lcsMetadataDetailsService;

}