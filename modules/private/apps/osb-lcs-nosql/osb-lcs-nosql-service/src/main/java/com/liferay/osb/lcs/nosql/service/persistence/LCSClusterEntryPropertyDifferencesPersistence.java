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

import com.liferay.osb.lcs.nosql.model.LCSClusterEntryPropertyDifferences;

import java.util.List;

/**
 * @author Matija Petanjek
 */
public interface LCSClusterEntryPropertyDifferencesPersistence {

	public LCSClusterEntryPropertyDifferences create();

	public void delete(long lcsClusterEntryId, String propertyName);

	public LCSClusterEntryPropertyDifferences fetchByLCEI_PN(
		long lcsClusterEntryId, String propertyName);

	public List<LCSClusterEntryPropertyDifferences> findByLCSClusterEntryId(
		long lcsClusterEntryId);

	public LCSClusterEntryPropertyDifferences update(
		LCSClusterEntryPropertyDifferences lcsClusterEntryPropertiesDifference);

}