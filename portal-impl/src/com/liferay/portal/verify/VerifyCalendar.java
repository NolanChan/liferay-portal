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

package com.liferay.portal.verify;

import com.liferay.portal.kernel.cal.TZSRecurrence;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.List;

import org.jabsorb.JSONSerializer;

/**
 * @author Juan Fernández
 * @author Matthew Kong
 * @author Mate Thurzo
 */
public class VerifyCalendar extends VerifyProcess {

	@Override
	protected void doVerify() throws Exception {
		verifyEndDate();
		verifyNoAssets();
		verifyRecurrence();
	}

	protected void updateEvent(long eventId, String recurrence)
		throws Exception {

		PreparedStatement ps = null;

		try {
			ps = connection.prepareStatement(
				"update CalEvent set recurrence = ? where eventId = ?");

			ps.setString(1, recurrence);
			ps.setLong(2, eventId);

			ps.executeUpdate();
		}
		finally {
			DataAccess.cleanUp(ps);
		}
	}

	protected void verifyEndDate() throws Exception {
		runSQL(
			"update CalEvent set endDate = null where endDate is not null " +
				"and (recurrence like '%\"until\":null%' or recurrence like " +
					"'null')");
	}

	@SuppressWarnings("deprecation")
	protected void verifyNoAssets() throws Exception {
		List<com.liferay.portlet.calendar.model.CalEvent> events =
			com.liferay.portlet.calendar.service.CalEventLocalServiceUtil.
				getNoAssetEvents();

		if (_log.isDebugEnabled()) {
			_log.debug("Processing " + events.size() + " events with no asset");
		}

		for (com.liferay.portlet.calendar.model.CalEvent event : events) {
			try {
				com.liferay.portlet.calendar.service.CalEventLocalServiceUtil.
					updateAsset(event.getUserId(), event, null, null, null);
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to update asset for event " +
							event.getEventId() + ": " + e.getMessage());
				}
			}
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Assets verified for events");
		}
	}

	protected void verifyRecurrence() throws Exception {
		JSONSerializer jsonSerializer = new JSONSerializer();

		jsonSerializer.registerDefaultSerializers();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(
				"select eventId, recurrence from CalEvent where (CAST_TEXT(" +
					"recurrence) != '') and recurrence not like " +
						"'%serializable%'");

			rs = ps.executeQuery();

			while (rs.next()) {
				long eventId = rs.getLong("eventId");
				String recurrence = rs.getString("recurrence");

				TZSRecurrence recurrenceObj = null;

				if (Validator.isNotNull(recurrence)) {
					recurrenceObj = (TZSRecurrence)jsonSerializer.fromJSON(
						recurrence);
				}

				String newRecurrence = JSONFactoryUtil.serialize(recurrenceObj);

				updateEvent(eventId, newRecurrence);
			}
		}
		finally {
			DataAccess.cleanUp(null, ps, rs);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(VerifyCalendar.class);

}