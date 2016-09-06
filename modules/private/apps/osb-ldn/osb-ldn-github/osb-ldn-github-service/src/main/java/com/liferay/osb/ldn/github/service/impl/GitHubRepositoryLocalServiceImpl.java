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
import com.liferay.osb.ldn.github.internal.util.GitHubCommunicatorUtil;
import com.liferay.osb.ldn.github.model.GitHubRepository;
import com.liferay.osb.ldn.github.service.base.GitHubRepositoryLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;

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

		if (gitHubRepository == null) {
			gitHubRepository = GitHubCommunicatorUtil.getRepository(
				owner, name);

			return addGitHubRepository(
				userId, owner, name, gitHubRepository.getCommits(),
				gitHubRepository.getOpenIssues(),
				gitHubRepository.getRepositoryCreateDate(),
				gitHubRepository.getStars(), gitHubRepository.getUrl());
		}

		return gitHubRepository;
	}

	protected GitHubRepository addGitHubRepository(
			long userId, String owner, String name, int commits, int openIssues,
			Date repositoryCreateDate, int stars, String url)
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

		gitHubRepositoryPersistence.update(gitHubRepository);

		gitHubContributorLocalService.getTopGitHubContributors(
			userId, owner, name, 10);

		return gitHubRepository;
	}

	protected GitHubRepository updateGitHubRepository(
			long userId, String owner, String name, int commits, int openIssues,
			int stars, String url)
		throws Exception {

		User user = userPersistence.findByPrimaryKey(userId);

		GitHubRepository gitHubRepository =
			gitHubRepositoryPersistence.findByO_N(owner, name);

		validate(commits, openIssues, stars, url);

		Date now = new Date();

		gitHubRepository.setCompanyId(user.getCompanyId());
		gitHubRepository.setUserId(userId);
		gitHubRepository.setUserName(user.getFullName());
		gitHubRepository.setModifiedDate(now);
		gitHubRepository.setCommits(commits);
		gitHubRepository.setOpenIssues(openIssues);
		gitHubRepository.setStars(stars);
		gitHubRepository.setUrl(url);

		gitHubRepositoryPersistence.update(gitHubRepository);

		gitHubContributorLocalService.getTopGitHubContributors(
			userId, owner, name, 0);

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

}