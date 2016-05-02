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

package com.liferay.osb.lcs.nosql.service.persistence;

import com.liferay.osb.lcs.nosql.model.LCSClusterNodeLayoutMetrics;

import java.util.List;

/**
 * @author Ivica Cardic
 */
public interface LCSClusterNodeLayoutMetricsPersistence {

	public LCSClusterNodeLayoutMetrics create();

	public void delete(String key);

	public LCSClusterNodeLayoutMetrics fetchByC_G_K_N(
		long companyId, long groupId, String key, String name);

	public List<LCSClusterNodeLayoutMetrics> findByC_G_K(
		long companyId, long groupId, String key);

	public List<LCSClusterNodeLayoutMetrics> findByKey(String key);

	public LCSClusterNodeLayoutMetrics update(
		LCSClusterNodeLayoutMetrics lcsClusterNodeLayoutMetrics);

}