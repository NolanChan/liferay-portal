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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Kevin Yen
 */
public class TopLevelBuild extends BaseBuild {

	@Override
	public String getStatusReport(int indentSize) {
		return super.getStatusReport(indentSize) + "Update duration: " +
			_updateDuration + " milliseconds.";
	}

	@Override
	public void update() {
		long start = System.currentTimeMillis();
		super.update();
		_updateDuration = System.currentTimeMillis() - start;
	}

	protected TopLevelBuild(String url) throws Exception {
		this(url, null);
	}

	protected TopLevelBuild(String url, TopLevelBuild parent) throws Exception {
		super(url, parent);
	}

	@Override
	protected ExecutorService getExecutorService() {
		return Executors.newFixedThreadPool(100);
	}

	private long _updateDuration;

}