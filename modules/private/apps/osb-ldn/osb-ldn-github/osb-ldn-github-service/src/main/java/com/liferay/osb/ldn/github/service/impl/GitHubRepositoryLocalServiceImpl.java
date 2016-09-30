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

import com.liferay.osb.ldn.github.exception.GitHubRepositoryCommitsException;
import com.liferay.osb.ldn.github.exception.GitHubRepositoryOpenIssuesException;
import com.liferay.osb.ldn.github.exception.GitHubRepositoryStarsException;
import com.liferay.osb.ldn.github.exception.GitHubRepositoryURLException;
import com.liferay.osb.ldn.github.internal.configuration.GitHubServiceConfiguration;
import com.liferay.osb.ldn.github.internal.util.GitHubCommunicatorUtil;
import com.liferay.osb.ldn.github.internal.util.GitHubServiceConfigurationUtil;
import com.liferay.osb.ldn.github.model.GitHubRepository;
import com.liferay.osb.ldn.github.service.base.GitHubRepositoryLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.lock.model.Lock;
import com.liferay.portal.lock.service.LockLocalService;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.Calendar;
import java.util.Date;

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

		GitHubRepository gitHubRepository =
			gitHubRepositoryPersistence.fetchByO_N(owner, name);

		_gitHubServiceConfiguration =
			GitHubServiceConfigurationUtil.getGitHubServiceConfiguration();

		String apiKey = _gitHubServiceConfiguration.apiKey();

		if (gitHubRepository == null) {
			gitHubRepository = GitHubCommunicatorUtil.getRepository(
				owner, name, apiKey);

			return addGitHubRepository(
				userId, owner, name, apiKey, gitHubRepository.getCommits(),
				gitHubRepository.getOpenIssues(),
				gitHubRepository.getRepositoryCreateDate(),
				gitHubRepository.getStars(), gitHubRepository.getUrl());
		}

		if (isExpired(gitHubRepository)) {
			return updateGitHubRepository(
				userId, owner, name, apiKey, gitHubRepository);
		}

		return gitHubRepository;
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

		validate(commits, openIssues, stars, url);

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

	protected boolean isExpired(GitHubRepository gitHubRepository)
		throws Exception {

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(gitHubRepository.getModifiedDate());

		_gitHubServiceConfiguration =
			GitHubServiceConfigurationUtil.getGitHubServiceConfiguration();

		int updateIntervalHours =
			_gitHubServiceConfiguration.updateIntervalHours();

		calendar.add(Calendar.HOUR_OF_DAY, updateIntervalHours);

		Date gitHubRepositoryExpirationTime = calendar.getTime();

		if (gitHubRepositoryExpirationTime.compareTo(new Date()) < 0) {
			return true;
		}

		return false;
	}

	protected GitHubRepository updateGitHubRepository(
			long userId, String owner, String name, String apiKey,
			GitHubRepository gitHubRepository)
		throws Exception {

		Lock lock = _lockLocalService.lock(
			GitHubRepository.class.getName(),
			String.valueOf(gitHubRepository.getGitHubRepositoryId()),
			GitHubRepositoryLocalServiceImpl.class.getName());

		_gitHubServiceConfiguration =
			GitHubServiceConfigurationUtil.getGitHubServiceConfiguration();

		int updateWindowMilliseconds =
			_gitHubServiceConfiguration.updateWindowMilliseconds();

		if (lock.isNew() || lock.isExpired()) {
			_lockLocalService.refresh(
				lock.getUuid(), lock.getCompanyId(), updateWindowMilliseconds);

			GitHubRepository gitHubRepositoryHolder =
				GitHubCommunicatorUtil.getRepository(owner, name, apiKey);

			validate(
				gitHubRepositoryHolder.getCommits(),
				gitHubRepositoryHolder.getOpenIssues(),
				gitHubRepositoryHolder.getStars(),
				gitHubRepositoryHolder.getUrl());

			Date now = new Date();

			gitHubRepository.setModifiedDate(now);

			gitHubRepository.setCommits(gitHubRepositoryHolder.getCommits());
			gitHubRepository.setOpenIssues(
				gitHubRepositoryHolder.getOpenIssues());
			gitHubRepository.setStars(gitHubRepositoryHolder.getStars());
			gitHubRepository.setUrl(gitHubRepositoryHolder.getUrl());

			gitHubRepository = gitHubRepositoryPersistence.update(
				gitHubRepository);

			_lockLocalService.unlock(
				GitHubRepository.class.getName(),
				String.valueOf(gitHubRepository.getGitHubRepositoryId()),
				GitHubRepositoryLocalServiceImpl.class.getName());
		}

		return gitHubRepository;
	}

	protected void validate(int commits, int openIssues, int stars, String url)
		throws PortalException {

		if (commits < 0) {
			throw new GitHubRepositoryCommitsException(
				"Commits is less than 0");
		}

		if (openIssues < 0) {
			throw new GitHubRepositoryOpenIssuesException(
				"Open issues is less than 0");
		}

		if (stars < 0) {
			throw new GitHubRepositoryStarsException("Stars is less than 0");
		}

		if (!Validator.isUrl(url)) {
			throw new GitHubRepositoryURLException("Repository URL is invalid");
		}
	}

	private GitHubServiceConfiguration _gitHubServiceConfiguration;

	@ServiceReference(type = LockLocalService.class)
	private LockLocalService _lockLocalService;

}