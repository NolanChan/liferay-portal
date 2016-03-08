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

package com.liferay.calendar.upgrade.v1_0_3;

import com.liferay.calendar.constants.CalendarPortletKeys;
import com.liferay.portal.kernel.util.LoggingTimer;

/**
 * @author Mate Thurzo
 */
public class UpgradeLastPublishDate
	extends com.liferay.portal.upgrade.v7_0_0.UpgradeLastPublishDate {

	@Override
	protected void doUpgrade() throws Exception {
		updateTables();

		updateLastPublishDates(CalendarPortletKeys.CALENDAR, "Calendar");
		updateLastPublishDates(CalendarPortletKeys.CALENDAR, "CalendarBooking");
		updateLastPublishDates(
			CalendarPortletKeys.CALENDAR, "CalendarNotificationTemplate");
		updateLastPublishDates(
			CalendarPortletKeys.CALENDAR, "CalendarResource");
	}

	protected void updateTables() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			runSQL("alter table Calendar add lastPublishDate DATE null");
			runSQL("alter table CalendarBooking add lastPublishDate DATE null");
			runSQL(
				"alter table CalendarNotificationTemplate add lastPublishDate" +
					" DATE null");
			runSQL(
				"alter table CalendarResource add lastPublishDate DATE null");
		}
	}

}