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

package com.liferay.osb.lcs.model.impl;

import aQute.bnd.annotation.ProviderType;

/**
 * @author Igor Beslic
 */
@ProviderType
public class LCSClusterNodeUptimeImpl extends LCSClusterNodeUptimeBaseImpl {

	public LCSClusterNodeUptimeImpl() {
	}

	public double getAmount() {
		return _amount;
	}

	public long getLcsClusterEntryId() {
		return _lcsClusterEntryId;
	}

	public String getLcsClusterEntryName() {
		return _lcsClusterEntryName;
	}

	public String getLcsClusterNodeKey() {
		return _lcsClusterNodeKey;
	}

	public String getLcsClusterNodeName() {
		return _lcsClusterNodeName;
	}

	public String getLcsProjectName() {
		return _lcsProjectName;
	}

	public double getRate() {
		return _rate;
	}

	public double getUptime() {
		return _uptime;
	}

	public void setAmount(double amount) {
		_amount = amount;
	}

	public void setLcsClusterEntryId(long lcsClusterEntryId) {
		_lcsClusterEntryId = lcsClusterEntryId;
	}

	public void setLcsClusterEntryName(String lcsClusterEntryName) {
		_lcsClusterEntryName = lcsClusterEntryName;
	}

	public void setLcsClusterNodeKey(String lcsClusterNodeKey) {
		_lcsClusterNodeKey = lcsClusterNodeKey;
	}

	public void setLcsClusterNodeName(String lcsClusterNodeName) {
		_lcsClusterNodeName = lcsClusterNodeName;
	}

	public void setLcsProjectName(String lcsProjectName) {
		_lcsProjectName = lcsProjectName;
	}

	public void setRate(double rate) {
		_rate = rate;
	}

	public void setUptime(double uptime) {
		_uptime = uptime;
	}

	private double _amount;
	private long _lcsClusterEntryId;
	private String _lcsClusterEntryName;
	private String _lcsClusterNodeKey;
	private String _lcsClusterNodeName;
	private String _lcsProjectName;
	private double _rate;
	private double _uptime;

}