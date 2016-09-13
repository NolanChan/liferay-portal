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

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.servlet.BrowserSnifferUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.portlet.PortletConfig;
import javax.portlet.PortletContext;
import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Ivica Cardic
 */
public class ReportContext {

	public ReportContext(
		PortletConfig portletConfig, PortletContext portletContext,
		PortletRequest portletRequest) {

		_portletConfig = portletConfig;
		_portletContext = portletContext;
		_portletRequest = portletRequest;

		_numberformat.setMaximumFractionDigits(2);
		_numberformat.setMinimumFractionDigits(2);

		_simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		_simpleDateTimeFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
	}

	public String formatDate(Date date) {
		return _simpleDateFormat.format(date);
	}

	public String formatNumber(double number) {
		return _numberformat.format(number);
	}

	public String formatTime(long time) {
		return _simpleDateTimeFormat.format(time);
	}

	public long getCompanyId() {
		ThemeDisplay themeDisplay = getThemeDisplay();

		return themeDisplay.getCompanyId();
	}

	public String getLanguage(String key) {
		return LanguageUtil.get(
			_portletConfig, _portletRequest.getLocale(), key);
	}

	public String getLineSeparator() {
		if (_lineSeparator == null) {
			HttpServletRequest httpServletRequest =
				PortalUtil.getHttpServletRequest(_portletRequest);

			if (BrowserSnifferUtil.isWindows(httpServletRequest)) {
				_lineSeparator = StringPool.RETURN_NEW_LINE;
			}

			_lineSeparator = StringPool.NEW_LINE;
		}

		return _lineSeparator;
	}

	public Locale getLocale() {
		ThemeDisplay themeDisplay = getThemeDisplay();

		return themeDisplay.getLocale();
	}

	public String getParameter(String key) {
		return _portletRequest.getParameter(key);
	}

	public PortletConfig getPortletConfig() {
		return _portletConfig;
	}

	public String getRealPath(String path) {
		return _portletContext.getRealPath(path);
	}

	public ThemeDisplay getThemeDisplay() {
		return (ThemeDisplay)_portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);
	}

	public long getUserId() {
		ThemeDisplay themeDisplay = getThemeDisplay();

		return themeDisplay.getUserId();
	}

	private String _lineSeparator;
	private NumberFormat _numberformat = NumberFormat.getInstance();
	private PortletConfig _portletConfig;
	private PortletContext _portletContext;
	private PortletRequest _portletRequest;
	private SimpleDateFormat _simpleDateFormat = new SimpleDateFormat(
		"yyyy-MM-dd");
	private SimpleDateFormat _simpleDateTimeFormat = new SimpleDateFormat(
		"yyyy-MM-dd HH:mm:ss");

}