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
public class LCSNotificationAuditEntryImpl
	extends LCSNotificationAuditEntryBaseImpl {

	public LCSNotificationAuditEntryImpl() {
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

	public String getUserEmailAddress() {
		return _userEmailAddress;
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

	public void setUserEmailAddress(String userEmailAddress) {
		_userEmailAddress = userEmailAddress;
	}

	private String _lcsClusterEntryName;
	private String _lcsClusterNodeKey;
	private String _lcsClusterNodeName;
	private String _lcsProjectName;
	private String _userEmailAddress;

}