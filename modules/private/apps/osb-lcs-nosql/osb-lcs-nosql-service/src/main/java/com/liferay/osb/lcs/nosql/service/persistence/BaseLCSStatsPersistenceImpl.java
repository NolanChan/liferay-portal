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

package com.liferay.osb.lcs.nosql.service.persistence;

import com.liferay.osb.lcs.nosql.service.persistence.base.BasePersistenceImpl;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Riccardo Ferrari
 */
public abstract class BaseLCSStatsPersistenceImpl<E>
	extends BasePersistenceImpl<E> {

	public void destroy() {
		_simpleDateFormatThreadLocal.remove();
	}

	public void setTableName(String tableName) {
		_tableName = tableName;
	}

	public void setTimeSlotMinutes(int timeSlotMinutes) {
		_timeSlotMinutes = timeSlotMinutes;
	}

	@Override
	public E update(E entity) {
		throw new UnsupportedOperationException();
	}

	protected String[] getPartitionKeys(Date endDate, Date startDate) {
		if (startDate.after(endDate)) {
			return getPartitionKeys(startDate, endDate);
		}

		String endPartitionKey = _format(endDate);
		String startPartitionKey = _format(startDate);

		if (endPartitionKey.equals(startPartitionKey)) {
			return new String[] {startPartitionKey};
		}

		Set<String> partitionKeys = new LinkedHashSet<>();

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(endDate);

		String partitionKey = endPartitionKey;

		while (!startPartitionKey.equals(partitionKey)) {
			partitionKeys.add(partitionKey);

			calendar.add(Calendar.DATE, -1);

			partitionKey = _format(calendar.getTime());
		}

		partitionKeys.add(startPartitionKey);

		return partitionKeys.toArray(new String[partitionKeys.size()]);
	}

	protected Date getStartDate(Date endDate) {
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(endDate);

		calendar.add(Calendar.MINUTE, -_timeSlotMinutes);

		return calendar.getTime();
	}

	@Override
	protected String getTableName() {
		return _tableName;
	}

	protected int getTimeSlot() {
		return _timeSlotMinutes;
	}

	private String _format(Date date) {
		SimpleDateFormat simpleDateFormat = _simpleDateFormatThreadLocal.get();

		if (simpleDateFormat == null) {
			simpleDateFormat = new SimpleDateFormat("yyyy-MM-w");

			Calendar calendar = Calendar.getInstance();

			calendar.setFirstDayOfWeek(Calendar.MONDAY);
			calendar.setMinimalDaysInFirstWeek(4);

			simpleDateFormat.setCalendar(calendar);

			_simpleDateFormatThreadLocal.set(simpleDateFormat);
		}

		return simpleDateFormat.format(date);
	}

	private static final ThreadLocal<SimpleDateFormat>
		_simpleDateFormatThreadLocal = new ThreadLocal<>();

	private String _tableName;
	private int _timeSlotMinutes;

}