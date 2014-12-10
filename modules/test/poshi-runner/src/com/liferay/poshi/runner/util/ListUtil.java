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

package com.liferay.poshi.runner.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
public class ListUtil {

	public static <E> List<E> copy(List<? extends E> master) {
		if (master == null) {
			return null;
		}

		return new ArrayList<E>(master);
	}

	public static <E> List<E> sort(List<E> list) {
		return sort(list, null);
	}

	public static <E> List<E> sort(
		List<E> list, Comparator<? super E> comparator) {

		list = copy(list);

		Collections.sort(list, comparator);

		return list;
	}

}