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

import com.liferay.osb.ldn.github.exception.GithubContributorAvatarURLException;
import com.liferay.osb.ldn.github.exception.GithubContributorContributionsException;
import com.liferay.osb.ldn.github.exception.GithubContributorNameException;
import com.liferay.osb.ldn.github.internal.util.GithubCommunicatorUtil;
import com.liferay.osb.ldn.github.model.GithubContributor;
import com.liferay.osb.ldn.github.model.GithubRepository;
import com.liferay.osb.ldn.github.service.base.GithubContributorLocalServiceBaseImpl;
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
public class GithubContributorLocalServiceImpl
	extends GithubContributorLocalServiceBaseImpl {

	public List<GithubContributor> getTopGithubContributors(
			long userId, String owner, String name, int count)
		throws Exception {

		GithubRepository githubRepository =
			githubRepositoryPersistence.findByO_N(owner, name);

		long githubRepositoryId = githubRepository.getGithubRepositoryId();

		int currentCount =
			githubContributorPersistence.countByGithubRepositoryId(
				githubRepositoryId);

		if (count == 0) {
			return updateTopGithubContributors(
				userId, githubRepository.getGithubRepositoryId(), owner, name,
				currentCount);
		}

		if (count > currentCount) {
			return updateTopGithubContributors(
				userId, githubRepository.getGithubRepositoryId(), owner, name,
				count);
		}

		return githubContributorPersistence.findByGithubRepositoryId(
			githubRepositoryId, 0, count);
	}

	protected GithubContributor addGithubContributor(
			long userId, long githubRepositoryId, String name, String avatarURL,
			int contributions)
		throws PortalException {

		if (userId == 0) {
			userId = userLocalService.getDefaultUserId(
				PortalUtil.getDefaultCompanyId());
		}

		User user = userPersistence.findByPrimaryKey(userId);

		validate(name, avatarURL, contributions);

		long githubContributorId = counterLocalService.increment();

		GithubContributor githubContributor =
			githubContributorPersistence.create(githubContributorId);

		Date now = new Date();

		githubContributor.setCompanyId(user.getCompanyId());
		githubContributor.setUserId(userId);
		githubContributor.setUserName(user.getFullName());
		githubContributor.setCreateDate(now);
		githubContributor.setModifiedDate(now);
		githubContributor.setGithubRepositoryId(githubRepositoryId);
		githubContributor.setName(name);
		githubContributor.setAvatarURL(avatarURL);
		githubContributor.setContributions(contributions);

		return githubContributorPersistence.update(githubContributor);
	}

	protected List<GithubContributor> updateTopGithubContributors(
			long userId, long githubRepositoryId, String owner, String name,
			int count)
		throws Exception {

		githubContributorPersistence.removeByGithubRepositoryId(
			githubRepositoryId);

		List<GithubContributor> githubContributors =
			GithubCommunicatorUtil.getTopContributors(owner, name, count);

		for (GithubContributor githubContributor : githubContributors) {
			addGithubContributor(
				userId, githubRepositoryId, githubContributor.getName(),
				githubContributor.getAvatarURL(),
				githubContributor.getContributions());
		}

		return githubContributors;
	}

	protected void validate(String name, String avatarUrl, int contributions)
		throws PortalException {

		if (Validator.isNull(name)) {
			throw new GithubContributorNameException(
				"Contributor name is null");
		}

		if (!Validator.isUrl(avatarUrl)) {
			throw new GithubContributorAvatarURLException(
				"Avatar URL is invalid");
		}

		if (contributions < 0) {
			throw new GithubContributorContributionsException(
				"Contributions is less than 0");
		}
	}

}