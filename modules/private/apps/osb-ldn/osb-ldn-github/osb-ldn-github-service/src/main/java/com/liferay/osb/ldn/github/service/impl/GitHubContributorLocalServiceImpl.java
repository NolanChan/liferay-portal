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
import com.liferay.osb.ldn.github.internal.configuration.GitHubServiceConfiguration;
import com.liferay.osb.ldn.github.internal.util.GitHubCommunicatorUtil;
import com.liferay.osb.ldn.github.internal.util.GitHubServiceConfigurationUtil;
import com.liferay.osb.ldn.github.model.GitHubContributor;
import com.liferay.osb.ldn.github.model.GitHubRepository;
import com.liferay.osb.ldn.github.service.base.GitHubContributorLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.lock.model.Lock;
import com.liferay.portal.lock.service.LockLocalService;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.ArrayList;
import java.util.Calendar;
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

		List<GitHubContributor> gitHubContributors =
			gitHubContributorPersistence.findByGitHubRepositoryId(
				gitHubRepository.getGitHubRepositoryId());

		_gitHubServiceConfiguration =
			GitHubServiceConfigurationUtil.getGitHubServiceConfiguration();

		if (gitHubContributors.isEmpty()) {
			return getNewGitHubContributors(
				userId, gitHubRepository.getGitHubRepositoryId(), owner, name,
				_gitHubServiceConfiguration.apiKey(), count);
		}

		if (isExpired(gitHubContributors.get(0))) {
			return getUpdatedGitHubContributors(
				userId, gitHubRepository.getGitHubRepositoryId(), owner, name,
				_gitHubServiceConfiguration.apiKey(), count,
				gitHubContributors.size());
		}

		return getExistingGitHubContributors(
			userId, gitHubRepository.getGitHubRepositoryId(), owner, name,
			_gitHubServiceConfiguration.apiKey(), count,
			gitHubContributors.size(), gitHubContributors);
	}

	protected GitHubContributor addGitHubContributor(
			Date now, long userId, long gitHubRepositoryId, String name,
			String avatarURL, int contributions)
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

	protected List<GitHubContributor> addGitHubContributors(
			long userId, long gitHubRepositoryId, String owner, String name,
			String apiKey, int count)
		throws Exception {

		List<GitHubContributor> gitHubContributorsHolder =
			GitHubCommunicatorUtil.getTopContributors(
				owner, name, count, apiKey);

		List<GitHubContributor> gitHubContributors = new ArrayList<>();

		Date now = new Date();

		for (GitHubContributor gitHubContributor : gitHubContributorsHolder) {
			gitHubContributor = addGitHubContributor(
				now, userId, gitHubRepositoryId, gitHubContributor.getName(),
				gitHubContributor.getAvatarURL(),
				gitHubContributor.getContributions());

			gitHubContributors.add(gitHubContributor);
		}

		return gitHubContributors;
	}

	protected List<GitHubContributor> getExistingGitHubContributors(
			long userId, long gitHubRepositoryId, String owner, String name,
			String apiKey, int count, int currentCount,
			List<GitHubContributor> gitHubContributors)
		throws Exception {

		if (count <= currentCount) {
			if ((count > 0) && (count < currentCount)) {
				return ListUtil.subList(gitHubContributors, 0, count);
			}

			return gitHubContributors;
		}

		return updateGitHubContributors(
			userId, gitHubRepositoryId, owner, name, apiKey, count);
	}

	protected List<GitHubContributor> getNewGitHubContributors(
			long userId, long gitHubRepositoryId, String owner, String name,
			String apiKey, int count)
		throws Exception {

		_gitHubServiceConfiguration =
			GitHubServiceConfigurationUtil.getGitHubServiceConfiguration();

		int topContributorCount =
			_gitHubServiceConfiguration.topContributorCount();

		if (count <= topContributorCount) {
			List<GitHubContributor> gitHubContributors = addGitHubContributors(
				userId, gitHubRepositoryId, owner, name, apiKey,
				topContributorCount);

			if ((count > 0) && (count < topContributorCount)) {
				return ListUtil.subList(gitHubContributors, 0, count);
			}

			return gitHubContributors;
		}

		return addGitHubContributors(
			userId, gitHubRepositoryId, owner, name, apiKey, count);
	}

	protected List<GitHubContributor> getUpdatedGitHubContributors(
			long userId, long gitHubRepositoryId, String owner, String name,
			String apiKey, int count, int currentCount)
		throws Exception {

		if (count <= currentCount) {
			List<GitHubContributor> gitHubContributors =
				updateGitHubContributors(
					userId, gitHubRepositoryId, owner, name, apiKey,
					currentCount);

			if ((count > 0) && (count < currentCount)) {
				return ListUtil.subList(gitHubContributors, 0, count);
			}

			return gitHubContributors;
		}

		return updateGitHubContributors(
			userId, gitHubRepositoryId, owner, name, apiKey, count);
	}

	protected boolean isExpired(GitHubContributor gitHubContributor)
		throws Exception {

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(gitHubContributor.getModifiedDate());

		_gitHubServiceConfiguration =
			GitHubServiceConfigurationUtil.getGitHubServiceConfiguration();

		int updateIntervalHours =
			_gitHubServiceConfiguration.updateIntervalHours();

		calendar.add(Calendar.HOUR_OF_DAY, updateIntervalHours);

		calendar.getTime();

		Date gitHubContributorExpirationTime = calendar.getTime();

		if (gitHubContributorExpirationTime.compareTo(new Date()) < 0) {
			return true;
		}

		return false;
	}

	protected List<GitHubContributor> updateGitHubContributors(
			long userId, long gitHubRepositoryId, String owner, String name,
			String apiKey, int count)
		throws Exception {

		List<GitHubContributor> gitHubContributors = null;

		while (gitHubContributors == null) {
			Lock lock = _lockLocalService.lock(
				GitHubContributor.class.getName(),
				String.valueOf(gitHubRepositoryId),
				GitHubContributorLocalServiceImpl.class.getName());

			if (lock.isNew() || lock.isExpired()) {
				_gitHubServiceConfiguration =
					GitHubServiceConfigurationUtil.
						getGitHubServiceConfiguration();

				_lockLocalService.refresh(
					lock.getUuid(), lock.getCompanyId(),
					_gitHubServiceConfiguration.updateWindowMilliseconds());
			}
			else {
				continue;
			}

			try {
				gitHubContributorPersistence.removeByGitHubRepositoryId(
					gitHubRepositoryId);

				gitHubContributors = addGitHubContributors(
					userId, gitHubRepositoryId, owner, name, apiKey, count);
			}
			finally {
				_lockLocalService.unlock(
					GitHubContributor.class.getName(),
					String.valueOf(gitHubRepositoryId),
					GitHubContributorLocalServiceImpl.class.getName());
			}
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

	private GitHubServiceConfiguration _gitHubServiceConfiguration;

	@ServiceReference(type = LockLocalService.class)
	private LockLocalService _lockLocalService;

}