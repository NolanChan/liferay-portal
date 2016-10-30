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

package com.liferay.osb.ldn.github.internal.util;

import com.liferay.osb.ldn.github.internal.configuration.GitHubServiceConfiguration;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;

/**
 * @author Howie Chou
 */
@Component(
	configurationPid = "com.liferay.osb.ldn.github.internal.configuration.GitHubServiceConfiguration",
	configurationPolicy = ConfigurationPolicy.REQUIRE, immediate = true
)
public class GitHubServiceConfigurationUtil {

	public static String getAPIKey() {
		return _gitHubServiceConfiguration.apiKey();
	}

	public static int getGitHubContributorMaxCount() {
		return _gitHubServiceConfiguration.gitHubContributorMaxCount();
	}

	public static GitHubServiceConfiguration getGitHubServiceConfiguration() {
		return _gitHubServiceConfiguration;
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_gitHubServiceConfiguration = ConfigurableUtil.createConfigurable(
			GitHubServiceConfiguration.class, properties);
	}

	private static volatile GitHubServiceConfiguration
		_gitHubServiceConfiguration;

}