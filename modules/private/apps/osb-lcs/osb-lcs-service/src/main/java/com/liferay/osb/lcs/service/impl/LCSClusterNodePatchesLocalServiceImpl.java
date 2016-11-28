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

package com.liferay.osb.lcs.service.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.lcs.util.LCSClusterNodeStatus;
import com.liferay.lcs.util.LCSConstants;
import com.liferay.lcs.util.PatchUtil;
import com.liferay.osb.lcs.advisor.CommandMessageAdvisor;
import com.liferay.osb.lcs.model.LCSClusterNode;
import com.liferay.osb.lcs.nosql.model.LCSClusterNodePatches;
import com.liferay.osb.lcs.nosql.service.LCSClusterNodePatchesService;
import com.liferay.osb.lcs.service.LCSClusterNodeLocalService;
import com.liferay.osb.lcs.service.base.LCSClusterNodePatchesLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Igor Beslic
 * @see    LCSClusterNodePatchesLocalServiceBaseImpl
 * @see    com.liferay.osb.lcs.service.LCSClusterNodePatchesLocalServiceUtil
 */
@ProviderType
public class LCSClusterNodePatchesLocalServiceImpl
	extends LCSClusterNodePatchesLocalServiceBaseImpl {

	@Override
	public void downloadPatch(long[] lcsClusterNodeIds, String patchName)
		throws PortalException {

		Map<String, Map<String, Integer>> keysPatchIdsStatuses =
			new HashMap<>();

		for (long lcsClusterNodeId : lcsClusterNodeIds) {
			LCSClusterNode lcsClusterNode =
				_lcsClusterNodeLocalService.getLCSClusterNode(
					lcsClusterNodeId, true);

			String patchId = PatchUtil.getPatchId(patchName);

			if (LCSClusterNodeStatus.isInactive(lcsClusterNode.getStatus())) {
				StringBundler sb = new StringBundler(5);

				sb.append("Unable to download patch ");
				sb.append(patchId);
				sb.append(" because LCS cluster node ");
				sb.append(lcsClusterNodeId);
				sb.append(" is offline");

				throw new PortalException(sb.toString());
			}

			_commandMessageAdvisor.downloadPatches(
				lcsClusterNode, Arrays.asList(patchName));

			LCSClusterNodePatches lcsClusterNodePatches =
				_lcsClusterNodePatchesService.fetchLCSClusterNodePatches(
					lcsClusterNode.getKey());

			Map<String, Integer> patchIdsStatuses = new HashMap<>(
				lcsClusterNodePatches.getPatchIdsStatuses());

			if (patchIdsStatuses.containsKey(patchId)) {
				if (patchIdsStatuses.get(patchId) >=
						LCSConstants.PATCHES_DOWNLOAD_INITIATED) {

					continue;
				}
			}

			patchIdsStatuses.put(
				patchId, LCSConstants.PATCHES_DOWNLOAD_INITIATED);

			keysPatchIdsStatuses.put(lcsClusterNode.getKey(), patchIdsStatuses);
		}

		_lcsClusterNodePatchesService.updateLCSClusterNodePatchesList(
			keysPatchIdsStatuses);
	}

	@Override
	public String getDownloadPatchStatus(
		long[] lcsClusterNodeIds, String lcsClusterNodeKeys, String patchId) {

		for (long lcsClusterNodeId : lcsClusterNodeIds) {
			LCSClusterNode lcsClusterNode =
				_lcsClusterNodeLocalService.getLCSClusterNode(
					lcsClusterNodeId, true);

			if (LCSClusterNodeStatus.isActive(lcsClusterNode.getStatus())) {
				continue;
			}

			LCSClusterNodePatches lcsClusterNodePatches =
				_lcsClusterNodePatchesService.fetchLCSClusterNodePatches(
					lcsClusterNode.getKey());

			Map<String, Integer> patchIdsStatuses = new HashMap<>(
				lcsClusterNodePatches.getPatchIdsStatuses());

			patchIdsStatuses.put(patchId, LCSConstants.PATCHES_ERROR);

			Map<String, Map<String, Integer>> keysPatchIdsStatuses =
				new HashMap<>();

			keysPatchIdsStatuses.put(lcsClusterNode.getKey(), patchIdsStatuses);

			_lcsClusterNodePatchesService.updateLCSClusterNodePatchesList(
				keysPatchIdsStatuses);

			StringBundler sb = new StringBundler(4);

			sb.append("LCS cluster node ");
			sb.append(lcsClusterNodeId);
			sb.append(" went down while downloading patch ");
			sb.append(patchId);

			_log.error(sb.toString());

			return String.valueOf(LCSConstants.PATCHES_ERROR);
		}

		return String.valueOf(
			_lcsClusterNodePatchesService.getPatchStatus(
				lcsClusterNodeKeys.split(StringPool.COMMA), patchId));
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LCSClusterNodePatchesLocalServiceImpl.class);

	@ServiceReference(type = CommandMessageAdvisor.class)
	private CommandMessageAdvisor _commandMessageAdvisor;

	@ServiceReference(type = LCSClusterNodeLocalService.class)
	private LCSClusterNodeLocalService _lcsClusterNodeLocalService;

	@ServiceReference(type = LCSClusterNodePatchesService.class)
	private LCSClusterNodePatchesService _lcsClusterNodePatchesService;

}