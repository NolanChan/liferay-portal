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
import com.liferay.osb.ldn.github.exception.GitHubServiceConfigurationException;
import com.liferay.osb.ldn.github.internal.configuration.GitHubServiceConfiguration;
import com.liferay.osb.ldn.github.internal.util.GitHubCommunicatorUtil;
import com.liferay.osb.ldn.github.internal.util.GitHubServiceConfigurationUtil;
import com.liferay.osb.ldn.github.model.GitHubContributor;
import com.liferay.osb.ldn.github.model.GitHubRepository;
import com.liferay.osb.ldn.github.service.base.GitHubContributorLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
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

		long gitHubRepositoryId = gitHubRepository.getGitHubRepositoryId();

		List<GitHubContributor> gitHubContributors =
			gitHubContributorPersistence.findByGitHubRepositoryId(
				gitHubRepositoryId);

		int currentCount = gitHubContributors.size();

		_gitHubServiceConfiguration =
			GitHubServiceConfigurationUtil.getGitHubServiceConfiguration();

		String apiKey = _gitHubServiceConfiguration.apiKey();

		if (currentCount == 0) {
			return getNewGitHubContributors(
				userId, gitHubRepositoryId, owner, name, apiKey, count);
		}

		if (isExpired(gitHubContributors.get(0))) {
			return getUpdatedGitHubContributors(
				userId, gitHubRepositoryId, owner, name, apiKey, count,
				currentCount, gitHubContributors);
		}

		return getExistingGitHubContributors(
			userId, gitHubRepositoryId, owner, name, apiKey, count,
			currentCount, gitHubContributors);
	}

	protected GitHubContributor addGitHubContributor(
			long userId, long gitHubRepositoryId, Date now, String name,
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
				userId, gitHubRepositoryId, now, gitHubContributor.getName(),
				gitHubContributor.getAvatarURL(),
				gitHubContributor.getContributions());

			gitHubContributors.add(gitHubContributor);
		}

		return gitHubContributors;
	}

	protected List<GitHubContributor> filterTopGitHubContributors(
		int count, List<GitHubContributor> gitHubContributors) {

		List<GitHubContributor> topGitHubContributors = new ArrayList<>();

		for (int i = 0; i < count; i++) {
			topGitHubContributors.add(i, gitHubContributors.get(i));
		}

		return topGitHubContributors;
	}

	protected List<GitHubContributor> getExistingGitHubContributors(
			long userId, long gitHubRepositoryId, String owner, String name,
			String apiKey, int count, int currentCount,
			List<GitHubContributor> gitHubContributors)
		throws Exception {

		if (count <= currentCount) {
			if ((count > 0) && (count < currentCount)) {
				return filterTopGitHubContributors(count, gitHubContributors);
			}

			return gitHubContributors;
		}

		do {
			gitHubContributors = updateGitHubContributors(
				userId, gitHubRepositoryId, owner, name, apiKey, count,
				gitHubContributors);
		} while (gitHubContributorPersistence.countByGitHubRepositoryId(
			gitHubRepositoryId) != count);

		return gitHubContributors;
	}

	protected List<GitHubContributor> getNewGitHubContributors(
			long userId, long gitHubRepositoryId, String owner, String name,
			String apiKey, int count)
		throws Exception {

		_gitHubServiceConfiguration =
			GitHubServiceConfigurationUtil.getGitHubServiceConfiguration();

		int topContributorCount =
			_gitHubServiceConfiguration.topContributorCount();

		if (topContributorCount < 0) {
			throw new GitHubServiceConfigurationException(
				"Top countributor count is less than 0");
		}

		if (count <= topContributorCount) {
			List<GitHubContributor> gitHubContributors = addGitHubContributors(
				userId, gitHubRepositoryId, owner, name, apiKey,
				topContributorCount);

			if ((count > 0) && (count < topContributorCount)) {
				return filterTopGitHubContributors(count, gitHubContributors);
			}

			return gitHubContributors;
		}

		return addGitHubContributors(
			userId, gitHubRepositoryId, owner, name, apiKey, count);
	}

	protected List<GitHubContributor> getUpdatedGitHubContributors(
			long userId, long gitHubRepositoryId, String owner, String name,
			String apiKey, int count, int currentCount,
			List<GitHubContributor> gitHubContributors)
		throws Exception {

		if (count <= currentCount) {
			do {
				gitHubContributors = updateGitHubContributors(
					userId, gitHubRepositoryId, owner, name, apiKey,
					currentCount, gitHubContributors);
			} while (gitHubContributorPersistence.countByGitHubRepositoryId(
				gitHubRepositoryId) != currentCount);

			if ((count > 0) && (count < currentCount)) {
				return filterTopGitHubContributors(count, gitHubContributors);
			}

			return gitHubContributors;
		}

		do {
			gitHubContributors = updateGitHubContributors(
				userId, gitHubRepositoryId, owner, name, apiKey, count,
				gitHubContributors);
		} while (gitHubContributorPersistence.countByGitHubRepositoryId(
			gitHubRepositoryId) != count);

		return gitHubContributors;
	}

	protected boolean isExpired(GitHubContributor gitHubContributor)
		throws Exception {

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(gitHubContributor.getModifiedDate());

		_gitHubServiceConfiguration =
			GitHubServiceConfigurationUtil.getGitHubServiceConfiguration();

		int updateIntervalHours =
			_gitHubServiceConfiguration.updateIntervalHours();

		if (updateIntervalHours < 0) {
			throw new GitHubServiceConfigurationException(
				"Update interval hours is less than 0");
		}

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
			String apiKey, int count,
			List<GitHubContributor> gitHubContributors)
		throws Exception {

		Lock lock = _lockLocalService.lock(
			GitHubContributor.class.getName(),
			String.valueOf(gitHubRepositoryId),
			GitHubContributorLocalServiceImpl.class.getName());

		_gitHubServiceConfiguration =
			GitHubServiceConfigurationUtil.getGitHubServiceConfiguration();

		int updateWindowMilliseconds =
			_gitHubServiceConfiguration.updateWindowMilliseconds();

		if (updateWindowMilliseconds < 0) {
			throw new GitHubServiceConfigurationException(
				"Update window milliseconds is less than 0");
		}

		if (lock.isNew()) {
			_lockLocalService.refresh(
				lock.getUuid(), lock.getCompanyId(), updateWindowMilliseconds);

			gitHubContributorPersistence.removeByGitHubRepositoryId(
				gitHubRepositoryId);

			gitHubContributors = addGitHubContributors(
				userId, gitHubRepositoryId, owner, name, apiKey, count);

			_lockLocalService.unlock(
				GitHubContributor.class.getName(),
				String.valueOf(gitHubRepositoryId),
				GitHubContributorLocalServiceImpl.class.getName());
		}
		else if (lock.isExpired()) {
			_lockLocalService.unlock(
				GitHubContributor.class.getName(),
				String.valueOf(gitHubRepositoryId),
				GitHubContributorLocalServiceImpl.class.getName());
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