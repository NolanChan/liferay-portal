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

package com.liferay.amazon.rankings.web.portlet.action;

import aQute.bnd.annotation.metatype.Configurable;
import com.liferay.amazon.rankings.web.util.AmazonRankingsConfiguration;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Arrays;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	configurationPid = "com.liferay.amazon.rankings",
	configurationPolicy = ConfigurationPolicy.OPTIONAL,
	immediate = true,
	property = {
		"javax.portlet.name=com_liferay_amazon_rankings_web_portlet_AmazonRankingsPortlet"
	},
	service = ConfigurationAction.class
)
public class AmazonRankingsConfigurationAction
	extends DefaultConfigurationAction {

	@Override
	public void processAction(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		String amazonAccessKeyId = getParameter(
			actionRequest, "amazon.access.key.id");

		setPreference(actionRequest, "amazon.access.key.id", amazonAccessKeyId);

		String amazonAssociateTag = getParameter(
			actionRequest, "amazon.associate.tag");

		setPreference(
			actionRequest, "amazon.associate.tag", amazonAssociateTag);

		String amazonSecretAccessKey = getParameter(
			actionRequest, "amazon.secret.access.key");

		setPreference(
			actionRequest, "amazon.secret.access.key", amazonSecretAccessKey);

		String[] isbns = StringUtil.split(
			StringUtil.toUpperCase(getParameter(actionRequest, "isbns")),
			CharPool.SPACE);

		Arrays.sort(isbns);

		setPreference(actionRequest, "isbns", isbns);

		super.processAction(portletConfig, actionRequest, actionResponse);
	}

	public String render(
			PortletConfig portletConfig, RenderRequest renderRequest,
			RenderResponse renderResponse)
		throws Exception {

		renderRequest.setAttribute("configuration", _configuration);

		return super.render(portletConfig, renderRequest, renderResponse);
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_configuration = Configurable.createConfigurable(
			AmazonRankingsConfiguration.class, properties);
	}


	private AmazonRankingsConfiguration _configuration;

}