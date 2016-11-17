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

package com.liferay.osb.ldn.github.top.contributors.web.internal.configuration;

import com.liferay.osb.ldn.github.top.contributors.web.internal.constants.GitHubPortletKeys;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

/**
 * @author Howie Chou
 */
@Component(
	configurationPolicy = ConfigurationPolicy.OPTIONAL, immediate = true,
	property = {
		"javax.portlet.name=" + GitHubPortletKeys.GITHUB_TOP_CONTRIBUTORS
	},
	service = ConfigurationAction.class
)
public class GitHubTopContributorsPortletConfigurationAction
	extends DefaultConfigurationAction {
}