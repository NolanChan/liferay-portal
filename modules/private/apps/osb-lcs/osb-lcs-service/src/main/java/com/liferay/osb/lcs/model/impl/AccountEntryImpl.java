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

import com.liferay.osb.lcs.model.AccountEntry;

/**
 * @author Matija Petanjek
 */
public class AccountEntryImpl implements AccountEntry {

	public long getAccountEntryId() {
		return _accountEntryId;
	}

	public long getCorpProjectId() {
		return _corpProjectId;
	}

	public String getName() {
		return _name;
	}

	public void setAccountEntryId(long accountEntryId) {
		_accountEntryId = accountEntryId;
	}

	public void setCorpProjectId(long corpProjectId) {
		_corpProjectId = corpProjectId;
	}

	public void setName(String name) {
		_name = name;
	}

	private long _accountEntryId;
	private long _corpProjectId;
	private String _name;

}