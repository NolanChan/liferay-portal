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

package com.liferay.document.selector;

import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Iván Zaera
 */
public interface ItemSelector {

	public PortletURL getItemSelectorURL(
		PortletRequest portletRequest,
		ItemSelectorCriterion... itemSelectorCriteria);

	public List<ItemSelectorViewRenderer<?>> getItemSelectorViewRenderers(
		Map<String, String[]> parameters);

}