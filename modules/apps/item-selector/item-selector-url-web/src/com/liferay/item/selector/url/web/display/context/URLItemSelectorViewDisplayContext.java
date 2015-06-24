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

package com.liferay.item.selector.url.web.display.context;

import com.liferay.item.selector.ItemSelectorCriterion;
import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.url.web.image.URLImageItemSelectorView;

import java.util.Locale;

/**
 * @author Roberto Díaz
 */
public class URLItemSelectorViewDisplayContext
	<T extends ItemSelectorCriterion, S extends ItemSelectorReturnType> {

	public URLItemSelectorViewDisplayContext(
		URLImageItemSelectorView urlImageItemSelectorView,
		String itemSelectedEventName) {

		_urlImageItemSelectorView = urlImageItemSelectorView;
		_itemSelectedEventName = itemSelectedEventName;
	}

	public String getItemSelectedEventName() {
		return _itemSelectedEventName;
	}

	public String getTitle(Locale locale) {
		return _urlImageItemSelectorView.getTitle(locale);
	}

	private final String _itemSelectedEventName;
	private final URLImageItemSelectorView _urlImageItemSelectorView;

}