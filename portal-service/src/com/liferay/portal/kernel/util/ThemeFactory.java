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

package com.liferay.portal.kernel.util;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Theme;

/**
 * @author Harrison Schueler
 */
public interface ThemeFactory {

	public Theme getDefaultRegularTheme(long companyId);

	public String getDefaultRegularThemeId(long companyId);

	public Theme getDefaultWapTheme(long companyId);

	public String getDefaultWapThemeId(long companyId);

	public Theme getTheme();

	public Theme getTheme(String themeId);

	public Theme getTheme(String themeId, String name);

}