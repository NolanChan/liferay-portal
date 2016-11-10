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

import com.liferay.osb.lcs.model.LCSMetadata;
import com.liferay.osb.lcs.nosql.service.LCSMetadataDetailsServiceUtil;
import com.liferay.osb.lcs.service.LCSMetadataLocalServiceUtil;
import com.liferay.osb.lcs.util.ApplicationProfile;
import com.liferay.osb.lcs.util.PortletPropsValues;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.SimpleAction;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.util.portlet.PortletProps;

import java.io.IOException;
import java.io.InputStream;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author Matija Petanjek
 */
public class SetUpLCSMetadataAction extends SimpleAction {

	@Override
	public void run(String[] strings) throws ActionException {
		if (PortletPropsValues.APPLICATION_PROFILE ==
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

	protected void addLCSMetadata() throws Exception {
		int i = 0;

		while (true) {
			String[] lcsMetadataAttributes = PortletProps.getArray(
				"osb.lcs.portlet.model.lcsmetadata." + i++);

			if (lcsMetadataAttributes.length == 0) {
				return;
			}

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

				LCSMetadataDetailsServiceUtil.addLCSMetadataDetails(
					buildNumber, new HashMap<String, String>(), gitTag,
					portalEdition, getPortalProperties(gitTag));
			}
		}
	}

	protected Map<String, String> getPortalProperties(String gitTag)
		throws IOException {

		Map<String, String> portalProperties = new HashMap<String, String>();

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

}