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

package com.liferay.portal.kernel.workflow.comparator;

import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.workflow.WorkflowInstance;

import java.util.Date;

/**
 * @author Shuyang Zhou
 */
public class WorkflowInstanceEndDateComparator
	extends OrderByComparator<WorkflowInstance> {

	public WorkflowInstanceEndDateComparator(
		boolean ascending, String orderByAsc, String orderByDesc,
		String[] orderByFields) {

		_ascending = ascending;
		_orderByAsc = orderByAsc;
		_orderByDesc = orderByDesc;
		_orderByFields = orderByFields;
	}

	@Override
	public int compare(
		WorkflowInstance workflowInstance1,
		WorkflowInstance workflowInstance2) {

		Date endDate1 = workflowInstance1.getEndDate();
		Date endDate2 = workflowInstance2.getEndDate();

		int value = endDate1.compareTo(endDate2);

		if (value == 0) {
			Long workflowInstanceId1 =
				workflowInstance1.getWorkflowInstanceId();
			Long workflowInstanceId2 =
				workflowInstance2.getWorkflowInstanceId();

			value = workflowInstanceId1.compareTo(workflowInstanceId2);
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
		if (isAscending()) {
			return _orderByAsc;
		}
		else {
			return _orderByDesc;
		}
	}

	@Override
	public String[] getOrderByFields() {
		return _orderByFields;
	}

	@Override
	public boolean isAscending() {
		return _ascending;
	}

	private boolean _ascending;
	private String _orderByAsc;
	private String _orderByDesc;
	private String[] _orderByFields;

}