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

import com.liferay.osb.lcs.nosql.model.LCSClusterNodeScript;

import java.util.Date;

/**
 * @author Ivica Cardic
 */
public class LCSClusterNodeScriptImpl implements LCSClusterNodeScript {

	@Override
	public String getCommand() {
		return _command;
	}

	@Override
	public String getCorrelationKey() {
		return _correlationKey;
	}

	@Override
	public String getError() {
		return _error;
	}

	@Override
	public String getKey() {
		return _key;
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public String getResult() {
		return _result;
	}

	@Override
	public String getUUID() {
		return _uuid;
	}

	@Override
	public boolean isNew() {
		return _new;
	}

	@Override
	public void setCommand(String command) {
		_command = command;
	}

	@Override
	public void setCorrelationKey(String correlationKey) {
		_correlationKey = correlationKey;
	}

	@Override
	public void setError(String error) {
		_error = error;
	}

	@Override
	public void setKey(String key) {
		_key = key;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	@Override
	public void setNew(boolean n) {
		_new = n;
	}

	@Override
	public void setResult(String result) {
		_result = result;
	}

	@Override
	public void setUUID(String uuid) {
		_uuid = uuid;
	}

	private String _command;
	private String _correlationKey;
	private String _error;
	private String _key;
	private Date _modifiedDate;
	private boolean _new;
	private String _result;
	private String _uuid;

}