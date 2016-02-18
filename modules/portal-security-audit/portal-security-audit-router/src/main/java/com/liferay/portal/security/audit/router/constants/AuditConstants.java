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

package com.liferay.portal.security.audit.router.constants;

import com.liferay.portal.security.audit.router.JSONLogMessageFormatter;

/**
 * @author Brian Greenwald
 * @author Prathima Shreenath
 */
public class AuditConstants {

	public static final String CSV = "CSV";

	public static final String EVENT_TYPES = "eventTypes";

	public static final String JSON = "JSON";

	public static final String CSVLogMessageFormatter =
		"com.liferay.portal.security.audit.router.CSVLogMessageFormatter";
	public static final String JSONLogMessageFormatter =
		"com.liferay.portal.security.audit.router.JSONLogMessageFormatter";

}