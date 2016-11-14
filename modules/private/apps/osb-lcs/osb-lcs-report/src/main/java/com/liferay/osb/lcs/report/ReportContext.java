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

package com.liferay.osb.lcs.report;

import com.liferay.lcs.util.LCSConstants;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Locale;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class ReportContext {

	public String formatDate(Date date) {
		return _simpleDateFormat.format(date);
	}

	public String formatNumber(double number) {
		return _numberFormat.format(number);
	}

	public String formatTime(long time) {
		return _simpleDateTimeFormat.format(time);
	}

	public long getLcsClusterEntryId() {
		return _lcsClusterEntryId;
	}

	public long getLcsClusterNodeId() {
		return _lcsClusterNodeId;
	}

	public long getLcsProjectId() {
		return _lcsProjectId;
	}

	public String getLineSeparator() {
		return _lineSeparator;
	}

	public Locale getLocale() {
		return _locale;
	}

	public int getMonth() {
		return _month;
	}

	public String getReportDependenciesPath() {
		return _reportDependenciesPath;
	}

	public long getUserId() {
		return _userId;
	}

	public int getYear() {
		return _year;
	}

	public static class ReportContextBuilder {

		public ReportContext build() {
			return new ReportContext(this);
		}

		public ReportContextBuilder dateFormat(String dateFormat) {
			_dateFormat = dateFormat;

			return this;
		}

		public ReportContextBuilder dateTimeFormat(String dateTimeFormat) {
			_dateTimeFormat = dateTimeFormat;

			return this;
		}

		public ReportContextBuilder lcsClusterEntryId(long lcsClusterEntryId) {
			_lcsClusterEntryId = lcsClusterEntryId;

			return this;
		}

		public ReportContextBuilder lcsClusterNodeId(long lcsClusterNodeId) {
			_lcsClusterNodeId = lcsClusterNodeId;

			return this;
		}

		public ReportContextBuilder lcsProjectId(long lcsProjectId) {
			_lcsProjectId = lcsProjectId;

			return this;
		}

		public ReportContextBuilder lineSeparator(String lineSeparator) {
			_lineSeparator = lineSeparator;

			return this;
		}

		public ReportContextBuilder locale(Locale locale) {
			_locale = locale;

			return this;
		}

		public ReportContextBuilder month(int month) {
			_month = month;

			return this;
		}

		public ReportContextBuilder numberFormat(String numberFormat) {
			_numberformat = numberFormat;

			return this;
		}

		public ReportContextBuilder reportDependenciesPath(
			String reportDependenciesPath) {

			_reportDependenciesPath = reportDependenciesPath;

			return this;
		}

		public ReportContextBuilder userId(long userId) {
			_userId = userId;

			return this;
		}

		public ReportContextBuilder year(int year) {
			_year = year;

			return this;
		}

		private String _dateFormat;
		private String _dateTimeFormat;
		private long _lcsClusterEntryId;
		private long _lcsClusterNodeId;
		private long _lcsProjectId;
		private String _lineSeparator;
		private Locale _locale;
		private int _month;
		private String _numberformat;
		private String _reportDependenciesPath;
		private long _userId;
		private int _year;

	}

	private ReportContext(ReportContextBuilder reportContextBuilder) {
		_lcsClusterEntryId = LCSConstants.ALL_LCS_CLUSTER_OBJECTS_ID;

		if (reportContextBuilder._lcsClusterEntryId != 0) {
			_lcsClusterEntryId = reportContextBuilder._lcsClusterEntryId;
		}

		_lcsClusterNodeId = LCSConstants.ALL_LCS_CLUSTER_OBJECTS_ID;

		if (reportContextBuilder._lcsClusterNodeId != 0) {
			_lcsClusterNodeId = reportContextBuilder._lcsClusterNodeId;
		}

		_lcsProjectId = LCSConstants.ALL_LCS_CLUSTER_OBJECTS_ID;

		if (reportContextBuilder._lcsProjectId != 0) {
			_lcsProjectId = reportContextBuilder._lcsProjectId;
		}

		_lineSeparator = reportContextBuilder._lineSeparator;
		_locale = reportContextBuilder._locale;

		_month = LCSConstants.ALL_LCS_CLUSTER_OBJECTS_ID;

		if (reportContextBuilder._month != 0) {
			_month = reportContextBuilder._month;
		}

		_numberFormat = NumberFormat.getInstance();

		_numberFormat.setMaximumFractionDigits(2);
		_numberFormat.setMinimumFractionDigits(2);

		if (reportContextBuilder._dateFormat != null) {
			_simpleDateFormat = new SimpleDateFormat(
				reportContextBuilder._dateFormat);
		}
		else {
			_simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		}

		if (reportContextBuilder._dateTimeFormat != null) {
			_simpleDateTimeFormat = new SimpleDateFormat(
				reportContextBuilder._dateTimeFormat);
		}
		else {
			_simpleDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}

		_userId = reportContextBuilder._userId;

		_year = LCSConstants.ALL_LCS_CLUSTER_OBJECTS_ID;

		if (reportContextBuilder._year != 0) {
			_year = reportContextBuilder._year;
		}
	}

	private long _lcsClusterEntryId;
	private long _lcsClusterNodeId;
	private long _lcsProjectId;
	private String _lineSeparator;
	private Locale _locale;
	private int _month;
	private final NumberFormat _numberFormat;
	private String _reportDependenciesPath;
	private final SimpleDateFormat _simpleDateFormat;
	private final SimpleDateFormat _simpleDateTimeFormat;
	private long _userId;
	private int _year;

}