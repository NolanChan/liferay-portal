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

import com.liferay.osb.lcs.nosql.model.LCSStatsData;

/**
 * @author Riccardo Ferrari
 */
public abstract class LCSStatsDataBaseImpl
	extends LCSStatsSparkMetadataBaseImpl implements LCSStatsData {

	@Override
	public int getAverage() {
		return _average;
	}

	@Override
	public int getMax() {
		return _max;
	}

	@Override
	public int getMin() {
		return _min;
	}

	@Override
	public int getSampleCount() {
		return _sampleCount;
	}

	@Override
	public int getStandardDeviation() {
		return _standardDeviation;
	}

	@Override
	public void setAverage(int average) {
		_average = average;
	}

	@Override
	public void setMax(int max) {
		_max = max;
	}

	@Override
	public void setMin(int min) {
		_min = min;
	}

	@Override
	public void setSampleCount(int sampleCount) {
		_sampleCount = sampleCount;
	}

	@Override
	public void setStandardDeviation(int standardDeviation) {
		_standardDeviation = standardDeviation;
	}

	private int _average;
	private int _max;
	private int _min;
	private int _sampleCount;
	private int _standardDeviation;

}
