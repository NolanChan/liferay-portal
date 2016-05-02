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

package com.liferay.osb.lcs.nosql.model.impl;

import java.util.Date;

import com.liferay.osb.lcs.nosql.model.LCSStatsSparkMetadata;

/**
 * @author Riccardo Ferrari
 */
public abstract class LCSStatsSparkMetadataBaseImpl
	extends LCSStatsBaseImpl implements LCSStatsSparkMetadata {

	@Override
	public Date getJobDate() {
		return _jobDate;
	}

	@Override
	public String getJobIdentifier() {
		return _jobIdentifier;
	}

	@Override
	public void setJobDate(Date jobdate) {
		_jobDate = jobdate;
	}

	@Override
	public void setJobIdentifier(String jobIdentifier) {
		_jobIdentifier = jobIdentifier;
	}

	private Date _jobDate;
	private String _jobIdentifier;

}
