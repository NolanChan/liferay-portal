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

package com.liferay.jenkins.results.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.apache.commons.lang.StringUtils;
import org.apache.tools.ant.Project;

/**
 * @author Kevin Yen
 */
public class TopLevelJob extends BaseJob {

	public static final String COMPLETED_BUILD_URLS_PROPERTY_NAME =
		"completed.build.urls";

	public static final String MAX_STARTING_TIME_PROPERTY_NAME =
		"max.starting.time";

	public static final String MAX_WAIT_TIME_PROPERTY_NAME = "max.wait.time";

	public static final String UPDATE_PERIOD_PROPERTY_NAME = "update.period";

	public TopLevelJob(String url) {
		super(url);

		downstreamJobs = new ArrayList<>();
	}

	public void addDownstreamJob(String invocationURL) throws Exception {
		downstreamJobs.add(new DownstreamJob(invocationURL, this));
	}

	public int getDownstreamJobCount() {
		return downstreamJobs.size();
	}

	public int getDownstreamJobCount(String status) {
		return getDownstreamJobs(status).size();
	}

	public List<DownstreamJob> getDownstreamJobs() {
		return downstreamJobs;
	}

	public List<DownstreamJob> getDownstreamJobs(String status) {
		List<DownstreamJob> downstreamJobWithStatus = new ArrayList<>();

		for (DownstreamJob downstreamJob : downstreamJobs) {
			if (downstreamJob.getStatus().equals(status)) {
				downstreamJobWithStatus.add(downstreamJob);
			}
		}

		return downstreamJobWithStatus;
	}

	public void update() throws Exception {
		for (DownstreamJob downstreamJob : downstreamJobs) {
			downstreamJob.update();
		}
	}

	public void waitForDownstreamJobs(
			long updatePeriod, long maxStartingTime, long maxWaitTime)
		throws Exception {

		long startTime = System.currentTimeMillis();

		while (true) {
			update();

			System.out.print(getDownstreamJobCount("completed"));
			System.out.print(" Completed / ");
			System.out.print(getDownstreamJobCount("running"));
			System.out.print(" Running / ");
			System.out.print(getDownstreamJobCount("queued"));
			System.out.print(" Queued / ");
			System.out.print(getDownstreamJobCount("starting"));
			System.out.print(" Starting / ");
			System.out.print(getDownstreamJobCount());
			System.out.println(" Total");

			long elapsedTime = System.currentTimeMillis() - startTime;

			if ((elapsedTime > maxStartingTime) &&
				(getDownstreamJobCount("starting") > 0)) {

				throw new TimeoutException("Unable to find downstream job");
			}
			else if ((elapsedTime > maxWaitTime) &&
					 (getDownstreamJobCount("completed") <
						 getDownstreamJobCount())) {

				throw new TimeoutException("Downstream job timeout");
			}
			else if (getDownstreamJobCount("completed") ==
						getDownstreamJobCount()) {

				break;
			}
			else {
				Thread.sleep(updatePeriod);
			}
		}
	}

	public void waitForDownstreamJobs(Project project) throws Exception {
		long maxStartingTime = 900000;

		if (project.getProperty(MAX_STARTING_TIME_PROPERTY_NAME) != null) {
			maxStartingTime = Long.parseLong(
				project.getProperty(MAX_STARTING_TIME_PROPERTY_NAME)) * 60000;
		}

		long maxWaitTime = 7200000;

		if (project.getProperty(MAX_WAIT_TIME_PROPERTY_NAME) != null) {
			maxWaitTime = Long.parseLong(
				project.getProperty(MAX_WAIT_TIME_PROPERTY_NAME)) * 60000;
		}

		long updatePeriod = 30000;

		if (project.getProperty(UPDATE_PERIOD_PROPERTY_NAME) != null) {
			updatePeriod = Long.parseLong(
				project.getProperty(UPDATE_PERIOD_PROPERTY_NAME)) * 1000;
		}

		waitForDownstreamJobs(updatePeriod, maxStartingTime, maxWaitTime);

		List<DownstreamJob> completedJobs = getDownstreamJobs("completed");

		String completedDownstreamURLs = StringUtils.join(
			getDownstreamURLs(completedJobs), ",");

		project.setProperty(
			COMPLETED_BUILD_URLS_PROPERTY_NAME, completedDownstreamURLs);
	}

	protected List<DownstreamJob> downstreamJobs;

	private static List<String> getDownstreamURLs(List<DownstreamJob> downstreamJobs)
		throws Exception {

		List<String> downstreamURLs = new ArrayList<>();

		for (DownstreamJob downstreamJob : downstreamJobs) {
			downstreamURLs.add(downstreamJob.getURL());
		}

		return downstreamURLs;
	}

}