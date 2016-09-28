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

package com.liferay.osb.lcs.nosql.service;

import com.liferay.osb.lcs.nosql.model.LCSClusterEntryPropertyDifferences;

import java.util.List;
import java.util.Map;

/**
 * @author Matija Petanjek
 */
public interface LCSClusterEntryPropertyDifferencesService {

	public LCSClusterEntryPropertyDifferences
		addLCSClusterEntryPropertyDifferences(
			long lcsClusterEntryId, String propertyName,
			Map<String, String> propertyValues);

	public void addLCSClusterEntryPropertyDifferencesMap(
		long lcsClusterEntryId,
		Map<String, Map<String, String>> lcsClusterEntryPropertyDifferencesMap);

	public void deleteLCSClusterEntryPropertyDifferences(
		long lcsClusterEntryId, String propertyName);

	public LCSClusterEntryPropertyDifferences
		fetchLCSClusterEntryPropertyDifferences(
			long lcsClusterEntryId, String propertyName);

	public List<LCSClusterEntryPropertyDifferences>
		getLCSClusterEntryPropertyDifferencesList(long lcsClusterEntryId);

	public Map<String, Map<String, String>>
		getLCSClusterEntryPropertyDifferencesMap(long lcsClusterEntryId);

	public LCSClusterEntryPropertyDifferences
		updateLCSClusterEntryPropertyDifferences(
			LCSClusterEntryPropertyDifferences
				lcsClusterEntryPropertyDifferences,
			Map<String, String> propertyValues);

}