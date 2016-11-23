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

import com.liferay.osb.lcs.nosql.model.LCSClusterNodePatches;
import com.liferay.osb.lcs.nosql.service.LCSClusterNodePatchesService;
import com.liferay.osb.lcs.nosql.service.persistence.LCSClusterNodePatchesEventsPersistence;
import com.liferay.osb.lcs.nosql.service.persistence.LCSClusterNodePatchesPersistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Ivica Cardic
 */
public class LCSClusterNodePatchesServiceImpl
	implements LCSClusterNodePatchesService {

	@Override
	public LCSClusterNodePatches addLCSClusterNodePatches(
		String error, List<String> fixedIssues, String hashCode,
		List<String> installablePatchIds, String key, Date modifiedDate,
		Map<String, Integer> patchIdsStatuses) {

		LCSClusterNodePatches lcsClusterNodePatches =
			_lcsClusterNodePatchesPersistence.create();

		lcsClusterNodePatches.setError(error);
		lcsClusterNodePatches.setFixedIssues(fixedIssues);
		lcsClusterNodePatches.setHashCode(hashCode);
		lcsClusterNodePatches.setInstallablePatchIds(installablePatchIds);
		lcsClusterNodePatches.setKey(key);
		lcsClusterNodePatches.setModifiedDate(modifiedDate);
		lcsClusterNodePatches.setPatchIdsStatuses(patchIdsStatuses);

		_lcsClusterNodePatchesPersistence.update(lcsClusterNodePatches);

		lcsClusterNodePatches.setNew(true);

		_lcsClusterNodePatchesEventsPersistence.update(lcsClusterNodePatches);

		return lcsClusterNodePatches;
	}

	@Override
	public LCSClusterNodePatches fetchLCSClusterNodePatches(String key) {
		LCSClusterNodePatches lcsClusterNodePatches =
			_lcsClusterNodePatchesPersistence.fetchByKey(key);

		return lcsClusterNodePatches;
	}

	@Override
	public List<LCSClusterNodePatches> getLCSClusterNodesPatchesList(
		List<String> keys) {

		List<LCSClusterNodePatches> lcsClusterNodePatchesList =
			new ArrayList<>();

		for (String key : keys) {
			LCSClusterNodePatches lcsClusterNodePatches =
				fetchLCSClusterNodePatches(key);

			if (lcsClusterNodePatches == null) {
				continue;
			}

			lcsClusterNodePatchesList.add(lcsClusterNodePatches);
		}

		return lcsClusterNodePatchesList;
	}

	@Override
	public Integer getPatchStatus(String[] keys, String patchId) {
		Integer patchStatus = null;

		for (String key : keys) {
			LCSClusterNodePatches lcsClusterNodePatches =
				_lcsClusterNodePatchesPersistence.fetchByKey(key);

			Map<String, Integer> patchIdsStatuses =
				lcsClusterNodePatches.getPatchIdsStatuses();

			Integer curPatchStatus = patchIdsStatuses.get(patchId);

			if ((patchStatus == null) || (curPatchStatus < patchStatus)) {
				patchStatus = curPatchStatus;
			}
		}

		return patchStatus;
	}

	public void setLCSClusterNodePatchesEventsPersistence(
		LCSClusterNodePatchesEventsPersistence
			lcsClusterNodePatchesEventsPersistence) {

		_lcsClusterNodePatchesEventsPersistence =
			lcsClusterNodePatchesEventsPersistence;
	}

	public void setLCSClusterNodePatchesPersistence(
		LCSClusterNodePatchesPersistence lcsClusterNodePatchesPersistence) {

		_lcsClusterNodePatchesPersistence = lcsClusterNodePatchesPersistence;
	}

	@Override
	public LCSClusterNodePatches updateLCSClusterNodePatches(
		LCSClusterNodePatches lcsClusterNodePatches) {

		lcsClusterNodePatches.setModifiedDate(new Date());

		_lcsClusterNodePatchesPersistence.update(lcsClusterNodePatches);

		lcsClusterNodePatches.setNew(true);

		_lcsClusterNodePatchesEventsPersistence.update(lcsClusterNodePatches);

		return lcsClusterNodePatches;
	}

	@Override
	public LCSClusterNodePatches updateLCSClusterNodePatches(
		String error, String key, Map<String, Integer> patchIdsStatuses) {

		_lcsClusterNodePatchesPersistence.updateErrorColumn(key, error);
		_lcsClusterNodePatchesPersistence.updateModifiedDateColumn(
			key, new Date());

		LCSClusterNodePatches lcsClusterNodePatches =
			fetchLCSClusterNodePatches(key);

		Map<String, Integer> oldPatchIdsStatuses =
			lcsClusterNodePatches.getPatchIdsStatuses();

		Set<String> patchIds = patchIdsStatuses.keySet();

		for (String patchId : patchIds) {
			if (oldPatchIdsStatuses.containsKey(patchId)) {
				int patchStatus = oldPatchIdsStatuses.get(patchId);
				int newPatchStatus = patchIdsStatuses.get(patchId);

				if ((newPatchStatus > patchStatus) || (newPatchStatus == -1)) {
					_lcsClusterNodePatchesPersistence.
						updatePatchIdsStatusesColumn(
							key, patchId, newPatchStatus);
				}
			}
			else {
				_lcsClusterNodePatchesPersistence.updatePatchIdsStatusesColumn(
					key, patchId, patchIdsStatuses.get(patchId));
			}
		}

		lcsClusterNodePatches = _lcsClusterNodePatchesPersistence.fetchByKey(
			lcsClusterNodePatches.getKey());

		lcsClusterNodePatches.setNew(true);

		return _lcsClusterNodePatchesEventsPersistence.update(
			lcsClusterNodePatches);
	}

	@Override
	public void updateLCSClusterNodePatchesList(
		Map<String, Map<String, Integer>> keysPatchIdsStatuses) {

		List<LCSClusterNodePatches> lcsClusterNodePatchesList =
			new ArrayList<>();

		Set<String> keys = keysPatchIdsStatuses.keySet();

		for (String key : keys) {
			lcsClusterNodePatchesList.add(
				updateLCSClusterNodePatches(
					null, key, keysPatchIdsStatuses.get(key)));
		}
	}

	private LCSClusterNodePatchesEventsPersistence
		_lcsClusterNodePatchesEventsPersistence;
	private LCSClusterNodePatchesPersistence _lcsClusterNodePatchesPersistence;

}