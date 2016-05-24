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

package com.liferay.knowledge.base.util.comparator;

import com.liferay.knowledge.base.model.KBTemplate;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class KBTemplateUserNameComparator
	extends OrderByComparator<KBTemplate> {

	public static final String ORDER_BY_ASC = "KBTemplate.userName ASC";

	public static final String ORDER_BY_DESC = "KBTemplate.userName DESC";

	public static final String[] ORDER_BY_FIELDS = {"userName", "title"};

	public KBTemplateUserNameComparator() {
		this(false);
	}

	public KBTemplateUserNameComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(KBTemplate kbTemplate1, KBTemplate kbTemplate2) {
		int value = StringUtil.toLowerCase(kbTemplate1.getUserName()).compareTo(
			StringUtil.toLowerCase(kbTemplate2.getUserName()));

		if (value == 0) {
			String title1 = kbTemplate1.getTitle();
			String title2 = kbTemplate1.getTitle();

			value = title1.compareToIgnoreCase(title2);
		}

		if (_ascending) {
			return value;
		}
		else {
			return -value;
		}
	}

	@Override
	public String getOrderBy() {
		if (_ascending) {
			return ORDER_BY_ASC;
		}
		else {
			return ORDER_BY_DESC;
		}
	}

	@Override
	public String[] getOrderByFields() {
		return ORDER_BY_FIELDS;
	}

	@Override
	public boolean isAscending() {
		return _ascending;
	}

	private final boolean _ascending;

}