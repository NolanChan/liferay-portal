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

import com.liferay.osb.ldn.github.model.GithubContributor;
import com.liferay.osb.ldn.github.model.GithubRepository;
import com.liferay.osb.ldn.github.service.GithubContributorLocalServiceUtil;
import com.liferay.osb.ldn.github.service.GithubRepositoryLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.Http.Options;
import com.liferay.portal.kernel.util.Http.Response;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Howie Chou
 */
public class GithubCommunicatorUtil {

	public static GithubRepository getRepository(String owner, String name)
		throws Exception {

		Http.Options options = new Http.Options();

		options.setLocation(_API_CALL_PREFIX + owner + StringPool.SLASH + name);

		options.setPost(false);

		String content = HttpUtil.URLtoString(options);

		Http.Response response = options.getResponse();

		if (response.getResponseCode() != HttpServletResponse.SC_OK) {
			throw new Exception(
				"Cannot get repository " + name + " from GitHub");
		}

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(content);

		String createAt = jsonObject.getString("created_at");

		Calendar cal = Calendar.getInstance();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

		sdf.setCalendar(cal);

		cal.setTime(sdf.parse(createAt));

		Date repositoryCreateDate = cal.getTime();

		String url = jsonObject.getString("html_url");
		int openIssues = jsonObject.getInt("open_issues");
		int stars = jsonObject.getInt("stargazers_count");

		int commits = getCommits(owner, name, options, response);

		GithubRepository githubRepository =
			GithubRepositoryLocalServiceUtil.createGithubRepository(0);

		githubRepository.setCommits(commits);
		githubRepository.setOpenIssues(openIssues);
		githubRepository.setRepositoryCreateDate(repositoryCreateDate);
		githubRepository.setStars(stars);
		githubRepository.setUrl(url);

		return githubRepository;
	}

	public static List<GithubContributor> getTopContributors(
			String owner, String name, int count)
		throws Exception {

		Http.Options options = new Http.Options();

		options.setLocation(
			_API_CALL_PREFIX + owner + StringPool.SLASH + name +
				"/contributors");

		options.setPost(false);

		String content = HttpUtil.URLtoString(options);

		Http.Response response = options.getResponse();

		if (response.getResponseCode() != HttpServletResponse.SC_OK) {
			throw new Exception(
				"Cannot get contributors from GitHub repository " + name);
		}

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray(content);

		JSONObject jsonObject = null;

		GithubContributor githubContributor = null;

		List<GithubContributor> githubContributors = new LinkedList<>();

		for (int i = 0; i < count; i++) {
			jsonObject = jsonArray.getJSONObject(i);

			String avatarURL = jsonObject.getString("avatar_url");
			int contributions = jsonObject.getInt("contributions");
			String contributorName = jsonObject.getString("login");

			githubContributor =
				GithubContributorLocalServiceUtil.createGithubContributor(0);

			githubContributor.setAvatarURL(avatarURL);
			githubContributor.setContributions(contributions);
			githubContributor.setName(contributorName);

			githubContributors.add(githubContributor);
		}

		return githubContributors;
	}

	protected static int getCommits(
			String owner, String name, Options options, Response response)
		throws Exception {

		options.setLocation(
			_API_CALL_PREFIX + owner + StringPool.SLASH + name +
				"/commits?per_page=1");

		HttpUtil.URLtoString(options);

		response = options.getResponse();

		if (response.getResponseCode() != 200) {
			throw new Exception(
				"Cannot get total commits from GitHub repository " + name);
		}

		String pagenationInfo = response.getHeader("Link");

		int begin = pagenationInfo.lastIndexOf("per_page=1");

		int end = pagenationInfo.lastIndexOf("rel=");

		String commits = pagenationInfo.substring(begin + 16, end - 3);

		return Integer.valueOf(commits);
	}

	private static final String _API_CALL_PREFIX =
		"https://api.github.com/repos/";

}