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

import com.liferay.osb.ldn.github.exception.GitHubAPIException;
import com.liferay.osb.ldn.github.exception.GitHubContributorCountException;
import com.liferay.osb.ldn.github.internal.util.GitHubCommunicatorUtil;
import com.liferay.osb.ldn.github.internal.util.GitHubServiceConfigurationUtil;
import com.liferay.osb.ldn.github.model.GitHubContributor;
import com.liferay.osb.ldn.github.model.GitHubRepository;
import com.liferay.osb.ldn.github.service.base.GitHubContributorLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
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
		throws PortalException {

		validate(count);

		GitHubRepository gitHubRepository =
			gitHubRepositoryPersistence.findByO_N(owner, name);

		List<GitHubContributor> gitHubContributors =
			gitHubContributorPersistence.findByGitHubRepositoryId(
				gitHubRepository.getGitHubRepositoryId(), 0, count);

		if (!gitHubContributors.isEmpty()) {
			return gitHubContributors;
		}

		if (gitHubContributors.isEmpty()) {
			gitHubContributors = addGitHubContributors(
				userId, gitHubRepository.getGitHubRepositoryId(), owner, name);
		}

		return ListUtil.subList(gitHubContributors, 0, count);
	}

	protected List<GitHubContributor> addGitHubContributors(
			long userId, long gitHubRepositoryId, String owner, String name)
		throws PortalException {

		List<GitHubContributor> gitHubContributors = new ArrayList<>();

		List<GitHubContributor> gitHubContributorsHolders = null;

		try {
			gitHubContributorsHolders =
				GitHubCommunicatorUtil.getTopContributors(
					owner, name,
					GitHubServiceConfigurationUtil.
						getGitHubContributorMaxCount(),
					GitHubServiceConfigurationUtil.getAPIKey());
		}
		catch (Exception e) {
			throw new GitHubAPIException(e);
		}

		if (userId == 0) {
			userId = userLocalService.getDefaultUserId(
				PortalUtil.getDefaultCompanyId());
		}

		User user = userLocalService.getUser(userId);

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
			gitHubContributor.setProfileURL(
				gitHubContributorsHolder.getProfileURL());

			gitHubContributor = gitHubContributorPersistence.update(
				gitHubContributor);

			gitHubContributors.add(gitHubContributor);
		}

		return gitHubContributors;
	}

	protected void validate(int count) throws PortalException {
		if (count <= 0) {
			throw new GitHubContributorCountException(
				"GitHub contributor count must greater than 0");
		}

		if (count >
				GitHubServiceConfigurationUtil.getGitHubContributorMaxCount()) {

			throw new GitHubContributorCountException(
				"GitHub contributor count is greater than contributor max " +
					"count");
		}
	}

}