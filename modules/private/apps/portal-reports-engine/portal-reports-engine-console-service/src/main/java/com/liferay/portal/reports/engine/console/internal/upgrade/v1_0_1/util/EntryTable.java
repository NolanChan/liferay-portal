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

package com.liferay.portal.reports.engine.console.internal.upgrade.v1_0_1.util;

import java.sql.Types;

/**
 * @author Inácio Nery
 */
public class EntryTable {

	public static final Object[][] TABLE_COLUMNS = {
		{"entryId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"definitionId", Types.BIGINT},
		{"format", Types.VARCHAR}, {"scheduleRequest", Types.BOOLEAN},
		{"startDate", Types.TIMESTAMP}, {"endDate", Types.TIMESTAMP},
		{"repeating", Types.BOOLEAN}, {"recurrence", Types.VARCHAR},
		{"emailNotifications", Types.VARCHAR}, {"emailDelivery", Types.VARCHAR},
		{"portletId", Types.VARCHAR}, {"pageURL", Types.VARCHAR},
		{"reportParameters", Types.VARCHAR}, {"status", Types.VARCHAR},
		{"errorMessage", Types.VARCHAR}
	};

	public static final String TABLE_NAME = "Reports_Entry";

	public static final String TABLE_SQL_CREATE = "create table Reports_Entry (entryId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,definitionId LONG,format VARCHAR(75) null,scheduleRequest BOOLEAN,startDate DATE null,endDate DATE null,repeating BOOLEAN,recurrence VARCHAR(75) null,emailNotifications VARCHAR(200) null,emailDelivery VARCHAR(200) null,portletId VARCHAR(75) null,pageURL STRING null,reportParameters VARCHAR(255) null,status VARCHAR(75) null,errorMessage STRING null)";

	public static final String TABLE_SQL_DROP = "drop table Reports_Entry";

}