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

import com.liferay.osb.ldn.github.internal.configuration.GitHubServiceConfiguration;
import com.liferay.osb.ldn.github.internal.util.GitHubCommunicatorUtil;
import com.liferay.osb.ldn.github.internal.util.GitHubServiceConfigurationUtil;
import com.liferay.osb.ldn.github.model.GitHubContributor;
import com.liferay.osb.ldn.github.model.GitHubRepository;
import com.liferay.osb.ldn.github.service.base.GitHubRepositoryLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.PortalUtil;

import java.util.Date;
import java.util.List;

/**
 * @author Howie Chou
 */
@ProviderType
public class GitHubRepositoryLocalServiceImpl
	extends GitHubRepositoryLocalServiceBaseImpl {

	@Override
	public GitHubRepository deleteGitHubRepository(
		GitHubRepository gitHubRepository) {

		// GitHub repository

		gitHubRepositoryPersistence.remove(gitHubRepository);

		// GitHub contributor

		gitHubContributorPersistence.removeByGitHubRepositoryId(
			gitHubRepository.getGitHubRepositoryId());

		return gitHubRepository;
	}

	@Override
	public GitHubRepository deleteGitHubRepository(long gitHubRepositoryId)
		throws PortalException {

		GitHubRepository gitHubRepository =
			gitHubRepositoryPersistence.findByPrimaryKey(gitHubRepositoryId);

		return deleteGitHubRepository(gitHubRepository);
	}

	public GitHubRepository deleteGitHubRepository(String owner, String name)
		throws PortalException {

		GitHubRepository gitHubRepository =
			gitHubRepositoryPersistence.findByO_N(owner, name);

		return deleteGitHubRepository(gitHubRepository);
	}

	public GitHubRepository getGitHubRepository(
			long userId, String owner, String name)
		throws Exception {

		_gitHubServiceConfiguration =
			GitHubServiceConfigurationUtil.getGitHubServiceConfiguration();

		String apiKey = _gitHubServiceConfiguration.apiKey();

		GitHubRepository gitHubRepository =
			gitHubRepositoryPersistence.fetchByO_N(owner, name);

		if (gitHubRepository == null) {
			gitHubRepository = GitHubCommunicatorUtil.getRepository(
				owner, name, apiKey);

			return addGitHubRepository(
				userId, owner, name, apiKey, gitHubRepository.getCommits(),
				gitHubRepository.getOpenIssues(),
				gitHubRepository.getRepositoryCreateDate(),
				gitHubRepository.getStars(), gitHubRepository.getUrl());
		}

		return gitHubRepository;
	}

	public void updateGitHubRopositoryCache() throws Exception {
		_gitHubServiceConfiguration =
			GitHubServiceConfigurationUtil.getGitHubServiceConfiguration();

		String apiKey = _gitHubServiceConfiguration.apiKey();

		int gitHubContributorMaxCount =
			_gitHubServiceConfiguration.gitHubContributorMaxCount();

		List<GitHubRepository> gitHubRepositories =
			gitHubRepositoryPersistence.findAll();

		for (GitHubRepository gitHubRepository : gitHubRepositories) {
			GitHubRepository gitHubRepositoryHolder =
				GitHubCommunicatorUtil.getRepository(
					gitHubRepository.getOwner(), gitHubRepository.getName(),
					apiKey);

			gitHubRepository.setModifiedDate(new Date());
			gitHubRepository.setCommits(gitHubRepositoryHolder.getCommits());
			gitHubRepository.setOpenIssues(
				gitHubRepositoryHolder.getOpenIssues());
			gitHubRepository.setStars(gitHubRepositoryHolder.getStars());
			gitHubRepository.setUrl(gitHubRepositoryHolder.getUrl());

			gitHubRepositoryPersistence.update(gitHubRepository);

			updateGitHubContributorCache(
				gitHubRepository.getGitHubRepositoryId(),
				gitHubRepository.getOwner(), gitHubRepository.getName(), apiKey,
				gitHubContributorMaxCount);
		}
	}

	protected GitHubRepository addGitHubRepository(
			long userId, String owner, String name, String apiKey, int commits,
			int openIssues, Date repositoryCreateDate, int stars, String url)
		throws Exception {

		if (userId == 0) {
			userId = userLocalService.getDefaultUserId(
				PortalUtil.getDefaultCompanyId());
		}

		User user = userPersistence.findByPrimaryKey(userId);

		long gitHubRepositoryId = counterLocalService.increment();

		GitHubRepository gitHubRepository = gitHubRepositoryPersistence.create(
			gitHubRepositoryId);

		Date now = new Date();

		gitHubRepository.setCompanyId(user.getCompanyId());
		gitHubRepository.setUserId(userId);
		gitHubRepository.setUserName(user.getFullName());
		gitHubRepository.setCreateDate(now);
		gitHubRepository.setModifiedDate(now);
		gitHubRepository.setOwner(owner);
		gitHubRepository.setName(name);
		gitHubRepository.setCommits(commits);
		gitHubRepository.setOpenIssues(openIssues);
		gitHubRepository.setRepositoryCreateDate(repositoryCreateDate);
		gitHubRepository.setStars(stars);
		gitHubRepository.setUrl(url);

		return gitHubRepositoryPersistence.update(gitHubRepository);
	}

	protected void updateGitHubContributorCache(
			long gitHubRepositoryId, String owner, String name, String apiKey,
			int count)
		throws Exception {

		List<GitHubContributor> gitHubContributors =
			gitHubContributorPersistence.findByGitHubRepositoryId(
				gitHubRepositoryId);

		List<GitHubContributor> gitHubContributorHolders =
			GitHubCommunicatorUtil.getTopContributors(
				owner, name, count, apiKey);

		for (int i = 0; i < count; i++) {
			GitHubContributor gitHubContributor = gitHubContributors.get(i);

			GitHubContributor gitHubContributorHolder =
				gitHubContributorHolders.get(i);

			gitHubContributor.setModifiedDate(new Date());
			gitHubContributor.setName(gitHubContributorHolder.getName());
			gitHubContributor.setAvatarURL(
				gitHubContributorHolder.getAvatarURL());
			gitHubContributor.setContributions(
				gitHubContributorHolder.getContributions());

			gitHubContributorPersistence.update(gitHubContributor);
		}
	}

	private GitHubServiceConfiguration _gitHubServiceConfiguration;

}