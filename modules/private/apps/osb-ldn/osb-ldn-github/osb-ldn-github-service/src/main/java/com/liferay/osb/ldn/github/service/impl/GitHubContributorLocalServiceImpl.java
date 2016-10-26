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

package com.liferay.osb.ldn.github.service.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.ldn.github.exception.GitHubContributorCountException;
import com.liferay.osb.ldn.github.internal.configuration.GitHubServiceConfiguration;
import com.liferay.osb.ldn.github.internal.util.GitHubCommunicatorUtil;
import com.liferay.osb.ldn.github.internal.util.GitHubServiceConfigurationUtil;
import com.liferay.osb.ldn.github.model.GitHubContributor;
import com.liferay.osb.ldn.github.model.GitHubRepository;
import com.liferay.osb.ldn.github.service.base.GitHubContributorLocalServiceBaseImpl;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Howie Chou
 */
@ProviderType
public class GitHubContributorLocalServiceImpl
	extends GitHubContributorLocalServiceBaseImpl {

	public List<GitHubContributor> getTopGitHubContributors(
			long userId, String owner, String name, int count)
		throws Exception {

		_gitHubServiceConfiguration =
			GitHubServiceConfigurationUtil.getGitHubServiceConfiguration();

		String apiKey = _gitHubServiceConfiguration.apiKey();

		GitHubRepository gitHubRepository =
			gitHubRepositoryPersistence.findByO_N(owner, name);

		List<GitHubContributor> gitHubContributors =
			gitHubContributorPersistence.findByGitHubRepositoryId(
				gitHubRepository.getGitHubRepositoryId());

		int gitHubContributorMaxCount =
			_gitHubServiceConfiguration.gitHubContributorMaxCount();

		validateCount(count, gitHubContributorMaxCount);

		if (gitHubContributors.isEmpty()) {
			gitHubContributors = addGitHubContributors(
				userId, gitHubRepository.getGitHubRepositoryId(), owner, name,
				apiKey, gitHubContributorMaxCount);
		}

		if (count < gitHubContributorMaxCount) {
			return ListUtil.subList(gitHubContributors, 0, count);
		}

		return gitHubContributors;
	}

	protected List<GitHubContributor> addGitHubContributors(
			long userId, long gitHubRepositoryId, String owner, String name,
			String apiKey, int count)
		throws Exception {

		List<GitHubContributor> gitHubContributorsHolders =
			GitHubCommunicatorUtil.getTopContributors(
				owner, name, count, apiKey);

		List<GitHubContributor> gitHubContributors = new ArrayList<>();

		if (userId == 0) {
			userId = userLocalService.getDefaultUserId(
				PortalUtil.getDefaultCompanyId());
		}

		User user = userPersistence.findByPrimaryKey(userId);

		Date now = new Date();

		for (GitHubContributor gitHubContributorsHolder :
				gitHubContributorsHolders) {

			long gitHubContributorId = counterLocalService.increment();

			GitHubContributor gitHubContributor =
				gitHubContributorPersistence.create(gitHubContributorId);

			gitHubContributor.setCompanyId(user.getCompanyId());
			gitHubContributor.setUserId(userId);
			gitHubContributor.setUserName(user.getFullName());
			gitHubContributor.setCreateDate(now);
			gitHubContributor.setModifiedDate(now);
			gitHubContributor.setGitHubRepositoryId(gitHubRepositoryId);
			gitHubContributor.setName(gitHubContributorsHolder.getName());
			gitHubContributor.setAvatarURL(
				gitHubContributorsHolder.getAvatarURL());
			gitHubContributor.setContributions(
				gitHubContributorsHolder.getContributions());

			gitHubContributor = gitHubContributorPersistence.update(
				gitHubContributor);

			gitHubContributors.add(gitHubContributor);
		}

		return gitHubContributors;
	}

	protected void validateCount(int count, int gitHubContributorMaxCount)
		throws Exception {

		if (count <= 0) {
			throw new GitHubContributorCountException(
				"GitHub contributor count must greater than 0");
		}

		if (count > gitHubContributorMaxCount) {
			throw new GitHubContributorCountException(
				"GitHub contributor count is greater than contributor max " +
					"count");
		}
	}

	private GitHubServiceConfiguration _gitHubServiceConfiguration;

}