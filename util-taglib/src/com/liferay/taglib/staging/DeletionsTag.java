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

package com.liferay.taglib.staging;

import com.liferay.taglib.util.IncludeTag;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Levente Hudák
 */
public class DeletionsTag extends IncludeTag {

	public void setCmd(String cmd) {
		_cmd = cmd;
	}

	public void setDisableInputs(boolean disableInputs) {
		_disableInputs = disableInputs;
	}

	@Override
	protected void cleanUp() {
		_cmd = null;
		_disableInputs = false;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute("liferay-staging:deletions:cmd", _cmd);
		request.setAttribute(
			"liferay-staging:deletions:disableInputs", _disableInputs);
	}

	private static final String _PAGE =
		"/html/taglib/staging/deletions/page.jsp";

	private String _cmd;
	private boolean _disableInputs;

}