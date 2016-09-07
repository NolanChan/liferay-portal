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

import com.liferay.osb.ldn.github.exception.GithubRepositoryCommitsException;
import com.liferay.osb.ldn.github.exception.GithubRepositoryOpenIssuesException;
import com.liferay.osb.ldn.github.exception.GithubRepositoryStarsException;
import com.liferay.osb.ldn.github.exception.GithubRepositoryURLException;
import com.liferay.osb.ldn.github.internal.util.GithubCommunicatorUtil;
import com.liferay.osb.ldn.github.model.GithubRepository;
import com.liferay.osb.ldn.github.service.base.GithubRepositoryLocalServiceBaseImpl;
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
public class GithubRepositoryLocalServiceImpl
	extends GithubRepositoryLocalServiceBaseImpl {

	@Override
	public GithubRepository deleteGithubRepository(
		GithubRepository githubRepository) {

		// Github repository

		githubRepositoryPersistence.remove(githubRepository);

		// Github contributor

		githubContributorPersistence.removeByGithubRepositoryId(
			githubRepository.getGithubRepositoryId());

		return githubRepository;
	}

	@Override
	public GithubRepository deleteGithubRepository(long githubRepositoryId)
		throws PortalException {

		GithubRepository githubRepository =
			githubRepositoryPersistence.findByPrimaryKey(githubRepositoryId);

		return deleteGithubRepository(githubRepository);
	}

	public GithubRepository deleteGithubRepository(String owner, String name)
		throws PortalException {

		GithubRepository githubRepository =
			githubRepositoryPersistence.findByO_N(owner, name);

		return deleteGithubRepository(githubRepository);
	}

	public GithubRepository getGithubRepository(
			long userId, String owner, String name)
		throws Exception {

		GithubRepository githubRepository =
			githubRepositoryPersistence.fetchByO_N(owner, name);

		if (githubRepository == null) {
			githubRepository = GithubCommunicatorUtil.getRepository(
				owner, name);

			return addGithubRepository(
				userId, owner, name, githubRepository.getCommits(),
				githubRepository.getOpenIssues(),
				githubRepository.getRepositoryCreateDate(),
				githubRepository.getStars(), githubRepository.getUrl());
		}

		return githubRepository;
	}

	protected GithubRepository addGithubRepository(
			long userId, String owner, String name, int commits, int openIssues,
			Date repositoryCreateDate, int stars, String url)
		throws Exception {

		if (userId == 0) {
			userId = userLocalService.getDefaultUserId(
				PortalUtil.getDefaultCompanyId());
		}

		User user = userPersistence.findByPrimaryKey(userId);

		validate(commits, openIssues, stars, url);

		long githubRepositoryId = counterLocalService.increment();

		GithubRepository githubRepository = githubRepositoryPersistence.create(
			githubRepositoryId);

		Date now = new Date();

		githubRepository.setCompanyId(user.getCompanyId());
		githubRepository.setUserId(userId);
		githubRepository.setUserName(user.getFullName());
		githubRepository.setCreateDate(now);
		githubRepository.setModifiedDate(now);
		githubRepository.setOwner(owner);
		githubRepository.setName(name);
		githubRepository.setCommits(commits);
		githubRepository.setOpenIssues(openIssues);
		githubRepository.setRepositoryCreateDate(repositoryCreateDate);
		githubRepository.setStars(stars);
		githubRepository.setUrl(url);

		githubRepositoryPersistence.update(githubRepository);

		githubContributorLocalService.getTopGithubContributors(
			userId, owner, name, 10);

		return githubRepository;
	}

	protected GithubRepository updateGithubRepository(
			long userId, String owner, String name, int commits, int openIssues,
			int stars, String url)
		throws Exception {

		User user = userPersistence.findByPrimaryKey(userId);

		GithubRepository githubRepository =
			githubRepositoryPersistence.findByO_N(owner, name);

		validate(commits, openIssues, stars, url);

		Date now = new Date();

		githubRepository.setCompanyId(user.getCompanyId());
		githubRepository.setUserId(userId);
		githubRepository.setUserName(user.getFullName());
		githubRepository.setModifiedDate(now);
		githubRepository.setCommits(commits);
		githubRepository.setOpenIssues(openIssues);
		githubRepository.setStars(stars);
		githubRepository.setUrl(url);

		githubRepositoryPersistence.update(githubRepository);

		githubContributorLocalService.getTopGithubContributors(
			userId, owner, name, 0);

		return githubRepository;
	}

	protected void validate(int commits, int openIssues, int stars, String url)
		throws PortalException {

		if (commits < 0) {
			throw new GithubRepositoryCommitsException(
				"Commits is less than 0");
		}

		if (openIssues < 0) {
			throw new GithubRepositoryOpenIssuesException(
				"Open issues is less than 0");
		}

		if (stars < 0) {
			throw new GithubRepositoryStarsException("Stars is less than 0");
		}

		if (!Validator.isUrl(url)) {
			throw new GithubRepositoryURLException("Repository URL is invalid");
		}
	}

}