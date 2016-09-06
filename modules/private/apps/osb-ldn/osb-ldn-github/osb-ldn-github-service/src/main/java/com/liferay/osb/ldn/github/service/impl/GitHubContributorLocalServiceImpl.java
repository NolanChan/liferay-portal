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

import com.liferay.osb.ldn.github.exception.GitHubContributorAvatarURLException;
import com.liferay.osb.ldn.github.exception.GitHubContributorContributionsException;
import com.liferay.osb.ldn.github.exception.GitHubContributorNameException;
import com.liferay.osb.ldn.github.internal.util.GitHubCommunicatorUtil;
import com.liferay.osb.ldn.github.model.GitHubContributor;
import com.liferay.osb.ldn.github.model.GitHubRepository;
import com.liferay.osb.ldn.github.service.base.GitHubContributorLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;

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

		GitHubRepository gitHubRepository =
			gitHubRepositoryPersistence.findByO_N(owner, name);

		long gitHubRepositoryId = gitHubRepository.getGitHubRepositoryId();

		int currentCount =
			gitHubContributorPersistence.countByGitHubRepositoryId(
				gitHubRepositoryId);

		if (count == 0) {
			return updateTopGitHubContributors(
				userId, gitHubRepository.getGitHubRepositoryId(), owner, name,
				currentCount);
		}

		if (count > currentCount) {
			return updateTopGitHubContributors(
				userId, gitHubRepository.getGitHubRepositoryId(), owner, name,
				count);
		}

		return gitHubContributorPersistence.findByGitHubRepositoryId(
			gitHubRepositoryId, 0, count);
	}

	protected GitHubContributor addGitHubContributor(
			long userId, long gitHubRepositoryId, String name, String avatarURL,
			int contributions)
		throws PortalException {

		if (userId == 0) {
			userId = userLocalService.getDefaultUserId(
				PortalUtil.getDefaultCompanyId());
		}

		User user = userPersistence.findByPrimaryKey(userId);

		validate(name, avatarURL, contributions);

		long gitHubContributorId = counterLocalService.increment();

		GitHubContributor gitHubContributor =
			gitHubContributorPersistence.create(gitHubContributorId);

		Date now = new Date();

		gitHubContributor.setCompanyId(user.getCompanyId());
		gitHubContributor.setUserId(userId);
		gitHubContributor.setUserName(user.getFullName());
		gitHubContributor.setCreateDate(now);
		gitHubContributor.setModifiedDate(now);
		gitHubContributor.setGitHubRepositoryId(gitHubRepositoryId);
		gitHubContributor.setName(name);
		gitHubContributor.setAvatarURL(avatarURL);
		gitHubContributor.setContributions(contributions);

		return gitHubContributorPersistence.update(gitHubContributor);
	}

	protected List<GitHubContributor> updateTopGitHubContributors(
			long userId, long gitHubRepositoryId, String owner, String name,
			int count)
		throws Exception {

		gitHubContributorPersistence.removeByGitHubRepositoryId(
			gitHubRepositoryId);

		List<GitHubContributor> gitHubContributors =
			GitHubCommunicatorUtil.getTopContributors(owner, name, count);

		for (GitHubContributor gitHubContributor : gitHubContributors) {
			addGitHubContributor(
				userId, gitHubRepositoryId, gitHubContributor.getName(),
				gitHubContributor.getAvatarURL(),
				gitHubContributor.getContributions());
		}

		return gitHubContributors;
	}

	protected void validate(String name, String avatarUrl, int contributions)
		throws PortalException {

		if (Validator.isNull(name)) {
			throw new GitHubContributorNameException(
				"Contributor name is null");
		}

		if (!Validator.isUrl(avatarUrl)) {
			throw new GitHubContributorAvatarURLException(
				"Avatar URL is invalid");
		}

		if (contributions < 0) {
			throw new GitHubContributorContributionsException(
				"Contributions is less than 0");
		}
	}

}