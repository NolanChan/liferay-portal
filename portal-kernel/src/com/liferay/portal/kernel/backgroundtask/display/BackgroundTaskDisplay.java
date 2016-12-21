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

package com.liferay.portal.kernel.backgroundtask.display;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Andrew Betts
 */
@ProviderType
public interface BackgroundTaskDisplay extends Serializable {

	public String getDisplayName(HttpServletRequest request);

	public int getPercentage();

	public int getStatus();

	public String getStatusLabel();

	public String getStatusLabel(Locale locale);

	public boolean hasPercentage();

	public String renderDisplayTemplate();

	public String renderDisplayTemplate(Locale locale);

}