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

package com.liferay.osb.lcs.web.internal.email;

import com.liferay.portal.kernel.exception.PortalException;

import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletPreferences;

/**
 * @author Matija Petanjek
 * @author Igor Beslic
 */
public interface EmailTemplate {

	public Map<Locale, String> getBodyMap(
		PortletPreferences portletPreferences);

	public Object[] getContextAttributes() throws PortalException;

	public String getPopPrefix();

	public Map<Locale, String> getSubjectMap(
		PortletPreferences portletPreferences);

}