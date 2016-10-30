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

package com.liferay.osb.ldn.github.internal.util;

import com.liferay.osb.ldn.github.exception.GitHubAPIException;
import com.liferay.osb.ldn.github.exception.GitHubContributorAvatarURLException;
import com.liferay.osb.ldn.github.exception.GitHubContributorContributionsException;
import com.liferay.osb.ldn.github.exception.GitHubContributorCountException;
import com.liferay.osb.ldn.github.exception.GitHubContributorNameException;
import com.liferay.osb.ldn.github.exception.GitHubRepositoryCommitsException;
import com.liferay.osb.ldn.github.exception.GitHubRepositoryOpenIssuesException;
import com.liferay.osb.ldn.github.exception.GitHubRepositoryStarsException;
import com.liferay.osb.ldn.github.exception.GitHubRepositoryURLException;
import com.liferay.osb.ldn.github.model.GitHubContributor;
import com.liferay.osb.ldn.github.model.GitHubRepository;
import com.liferay.osb.ldn.github.service.GitHubContributorLocalServiceUtil;
import com.liferay.osb.ldn.github.service.GitHubRepositoryLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.Http.Options;
import com.liferay.portal.kernel.util.Http.Response;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Howie Chou
 */
public class GitHubCommunicatorUtil {

	public static GitHubRepository getRepository(
			String owner, String name, String apiKey)
		throws Exception {

		validateAPIKey(apiKey);

		Http.Options options = new Http.Options();

		String apiCallURL = _API_CALL_PREFIX + owner + StringPool.SLASH + name;

		apiCallURL = HttpUtil.addParameter(apiCallURL, "access_token", apiKey);

		options.setLocation(apiCallURL);

		options.setPost(false);

		String content = HttpUtil.URLtoString(options);

		Http.Response response = options.getResponse();

		if (response.getResponseCode() != HttpServletResponse.SC_OK) {
			throw new Exception(
				"Unable to get repository " + name + " from GitHub");
		}

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(content);

		String createAt = jsonObject.getString("created_at");

		Calendar calendar = Calendar.getInstance();

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");

		simpleDateFormat.setCalendar(calendar);

		calendar.setTime(simpleDateFormat.parse(createAt));

		Date repositoryCreateDate = calendar.getTime();

		String url = jsonObject.getString("html_url");
		int openIssues = jsonObject.getInt("open_issues");
		int stars = jsonObject.getInt("stargazers_count");

		int commits = getCommits(owner, name, apiKey);

		validateGitHubRepository(commits, openIssues, stars, url);

		GitHubRepository gitHubRepository =
			GitHubRepositoryLocalServiceUtil.createGitHubRepository(0);

		gitHubRepository.setCommits(commits);
		gitHubRepository.setOpenIssues(openIssues);
		gitHubRepository.setRepositoryCreateDate(repositoryCreateDate);
		gitHubRepository.setStars(stars);
		gitHubRepository.setUrl(url);

		return gitHubRepository;
	}

	public static List<GitHubContributor> getTopContributors(
			String owner, String name, int count, String apiKey)
		throws Exception {

		validateCount(count);

		validateAPIKey(apiKey);

		Http.Options options = new Http.Options();

		String apiCallURL =
			_API_CALL_PREFIX + owner + StringPool.SLASH + name +
				"/contributors";

		apiCallURL = HttpUtil.addParameter(apiCallURL, "access_token", apiKey);

		apiCallURL = HttpUtil.addParameter(
			apiCallURL, "per_page", String.valueOf(count));

		options.setLocation(apiCallURL);

		options.setPost(false);

		String content = HttpUtil.URLtoString(options);

		Http.Response response = options.getResponse();

		if (response.getResponseCode() != HttpServletResponse.SC_OK) {
			throw new Exception(
				"Unable to get contributors from GitHub repository " + name);
		}

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray(content);

		JSONObject jsonObject = null;

		GitHubContributor gitHubContributor = null;

		List<GitHubContributor> gitHubContributors = new LinkedList<>();

		for (int i = 0; i < count; i++) {
			jsonObject = jsonArray.getJSONObject(i);

			String avatarURL = jsonObject.getString("avatar_url");
			int contributions = jsonObject.getInt("contributions");
			String contributorName = jsonObject.getString("login");

			validateGitHubContributor(
				contributorName, avatarURL, contributions);

			gitHubContributor =
				GitHubContributorLocalServiceUtil.createGitHubContributor(0);

			gitHubContributor.setAvatarURL(avatarURL);
			gitHubContributor.setContributions(contributions);
			gitHubContributor.setName(contributorName);

			gitHubContributors.add(gitHubContributor);
		}

		return gitHubContributors;
	}

	protected static int getCommits(String owner, String name, String apiKey)
		throws Exception {

		Http.Options options = new Http.Options();

		String apiCallURL =
			_API_CALL_PREFIX + owner + StringPool.SLASH + name + "/commits";

		apiCallURL = HttpUtil.addParameter(apiCallURL, "access_token", apiKey);

		apiCallURL = HttpUtil.addParameter(
			apiCallURL, "per_page", String.valueOf(1));

		options.setLocation(apiCallURL);

		HttpUtil.URLtoString(options);

		Http.Response response = options.getResponse();

		response = options.getResponse();

		if (response.getResponseCode() != 200) {
			throw new Exception(
				"Unable to get total commits from GitHub repository " + name);
		}

		String pagenationInfo = response.getHeader("Link");

		int begin = pagenationInfo.lastIndexOf("per_page=1");

		int end = pagenationInfo.lastIndexOf("rel=");

		String commits = pagenationInfo.substring(begin + 16, end - 3);

		return Integer.valueOf(commits);
	}

	protected static void validateAPIKey(String apiKey) throws Exception {
		if (Validator.isNull(apiKey)) {
			throw new GitHubAPIException("API key is empty");
		}
	}

	protected static void validateCount(int count) throws Exception {
		if (count <= 0) {
			throw new GitHubContributorCountException(
				"GitHub contributor max count must grater than 0");
		}
	}

	protected static void validateGitHubContributor(
			String name, String avatarUrl, int contributions)
		throws Exception {

		if (Validator.isNull(name)) {
			throw new GitHubContributorNameException(
				"Contributor name is empty");
		}

		if (!Validator.isUrl(avatarUrl)) {
			throw new GitHubContributorAvatarURLException(
				"Avatar URL is invalid");
		}

		if (contributions < 0) {
			throw new GitHubContributorContributionsException(
				"Contributions are less than 0");
		}
	}

	protected static void validateGitHubRepository(
			int commits, int openIssues, int stars, String url)
		throws Exception {

		if (commits < 0) {
			throw new GitHubRepositoryCommitsException(
				"Commits are less than 0");
		}

		if (openIssues < 0) {
			throw new GitHubRepositoryOpenIssuesException(
				"Open issues are less than 0");
		}

		if (stars < 0) {
			throw new GitHubRepositoryStarsException("Stars are less than 0");
		}

		if (!Validator.isUrl(url)) {
			throw new GitHubRepositoryURLException("Repository URL is invalid");
		}
	}

	private static final String _API_CALL_PREFIX =
		"https://api.github.com/repos/";

}