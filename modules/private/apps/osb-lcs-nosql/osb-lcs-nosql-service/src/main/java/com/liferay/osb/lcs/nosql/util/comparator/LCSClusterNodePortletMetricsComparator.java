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

package com.liferay.osb.lcs.nosql.util.comparator;

import com.liferay.osb.lcs.nosql.model.LCSClusterNodePortletMetrics;

import java.util.Comparator;

/**
 * @author Ivica Cardic
 */
public class LCSClusterNodePortletMetricsComparator
	implements Comparator<LCSClusterNodePortletMetrics> {

	public LCSClusterNodePortletMetricsComparator(
		String orderByCol, String orderByType) {

		_orderByCol = orderByCol;
		_orderByType = orderByType;
	}

	@Override
	public int compare(
		LCSClusterNodePortletMetrics lcsClusterNodePortletMetrics1,
		LCSClusterNodePortletMetrics lcsClusterNodePortletMetrics2) {

		int value = 0;

		if ((_orderByCol == null) || _orderByCol.equals("frequency")) {
			Integer frequency1 = lcsClusterNodePortletMetrics1.getFrequency();
			Integer frequency2 = lcsClusterNodePortletMetrics2.getFrequency();

			value = frequency1.compareTo(frequency2);
		}
		else if (_orderByCol.equals("name")) {
			String name1 = lcsClusterNodePortletMetrics1.getDisplayName();
			String name2 = lcsClusterNodePortletMetrics2.getDisplayName();

			value = name1.compareTo(name2);
		}
		else if (_orderByCol.startsWith("average-load-time")) {
			Integer averageLoadTime1 =
				lcsClusterNodePortletMetrics1.getAverageLoadTime();
			Integer averageLoadTime2 =
				lcsClusterNodePortletMetrics2.getAverageLoadTime();

			value = averageLoadTime1.compareTo(averageLoadTime2);
		}

		if ((_orderByType == null) || _orderByType.equals("desc")) {
			return -value;
		}
		else {
			return value;
		}
	}

	private String _orderByCol;
	private String _orderByType;

}