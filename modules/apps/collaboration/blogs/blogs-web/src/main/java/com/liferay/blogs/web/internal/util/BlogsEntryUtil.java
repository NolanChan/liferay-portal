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

package com.liferay.blogs.web.internal.util;

import com.liferay.blogs.kernel.model.BlogsEntry;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ResourceBundle;

/**
 * @author Alejandro Tardín
 */
public class BlogsEntryUtil {

	public static String getDisplayTitle(
		ResourceBundle resourceBundle, BlogsEntry entry) {

		return getDisplayTitle(resourceBundle, entry, true);
	}

	public static String getDisplayTitle(
		ResourceBundle resourceBundle, BlogsEntry entry, Boolean escape) {

		if (Validator.isNull(entry.getTitle())) {
			return HtmlUtil.escape(
				LanguageUtil.get(resourceBundle, "untitled-entry"));
		}

		if (escape) {
			return HtmlUtil.escape(entry.getTitle());
		}
		else {
			return entry.getTitle();
		}
	}

}