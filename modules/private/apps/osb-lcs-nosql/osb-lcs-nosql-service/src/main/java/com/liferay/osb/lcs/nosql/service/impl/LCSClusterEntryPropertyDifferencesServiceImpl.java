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

package com.liferay.osb.lcs.nosql.service.impl;

import com.liferay.osb.lcs.nosql.model.LCSClusterEntryPropertyDifferences;
import com.liferay.osb.lcs.nosql.service.LCSClusterEntryPropertyDifferencesService;
import com.liferay.osb.lcs.nosql.service.persistence.LCSClusterEntryPropertyDifferencesPersistence;

import java.util.List;
import java.util.Map;

/**
 * @author Matija Petanjek
 */
public class LCSClusterEntryPropertyDifferencesServiceImpl
	implements LCSClusterEntryPropertyDifferencesService {

	@Override
	public LCSClusterEntryPropertyDifferences
		addLCSClusterEntryPropertyDifferences(
			long lcsClusterEntryId, String propertyName,
			Map<String, String> propertyValues) {

		LCSClusterEntryPropertyDifferences lcsClusterEntryPropertyDifferences =
			_lcsClusterEntryPropertyDifferencesPersistence.create();

		lcsClusterEntryPropertyDifferences.setLCSClusterEntryId(
			lcsClusterEntryId);
		lcsClusterEntryPropertyDifferences.setPropertyName(propertyName);
		lcsClusterEntryPropertyDifferences.setPropertyValues(propertyValues);

		return _lcsClusterEntryPropertyDifferencesPersistence.update(
			lcsClusterEntryPropertyDifferences);
	}

	@Override
	public LCSClusterEntryPropertyDifferences
		fetchLCSClusterEntryPropertyDifferences(
			long lcsClusterEntryId, String propertyName) {

		return _lcsClusterEntryPropertyDifferencesPersistence.fetchByLCEI_PN(
			lcsClusterEntryId, propertyName);
	}

	@Override
	public List<LCSClusterEntryPropertyDifferences>
		getLCSClusterEntryPropertyDifferencesList(long lcsClusterEntryId) {

		return
			_lcsClusterEntryPropertyDifferencesPersistence.
				findByLCSClusterEntryId(lcsClusterEntryId);
	}

	public void setLCSClusterEntryPropertyDifferencesPersistence(
		LCSClusterEntryPropertyDifferencesPersistence
			lcsClusterEntryPropertyDifferencesPersistence) {

		_lcsClusterEntryPropertyDifferencesPersistence =
			lcsClusterEntryPropertyDifferencesPersistence;
	}

	@Override
	public LCSClusterEntryPropertyDifferences
		updateLCSClusterEntryPropertyDifferences(
			LCSClusterEntryPropertyDifferences
				lcsClusterEntryPropertyDifferences,
			Map<String, String> propertyValues) {

		lcsClusterEntryPropertyDifferences.setPropertyValues(propertyValues);

		return _lcsClusterEntryPropertyDifferencesPersistence.update(
			lcsClusterEntryPropertyDifferences);
	}

	private LCSClusterEntryPropertyDifferencesPersistence
		_lcsClusterEntryPropertyDifferencesPersistence;

}