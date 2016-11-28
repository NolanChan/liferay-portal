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

package com.liferay.osb.lcs.advisor.impl;

import com.liferay.lcs.notification.LCSEventType;
import com.liferay.lcs.util.LCSClusterNodeStatus;
import com.liferay.lcs.util.LCSConstants;
import com.liferay.lcs.util.PatchUtil;
import com.liferay.osb.lcs.advisor.CommandMessageAdvisor;
import com.liferay.osb.lcs.advisor.PatchAdvisor;
import com.liferay.osb.lcs.advisor.StringAdvisor;
import com.liferay.osb.lcs.configuration.OSBLCSConfiguration;
import com.liferay.osb.lcs.model.LCSClusterEntry;
import com.liferay.osb.lcs.model.LCSClusterNode;
import com.liferay.osb.lcs.nosql.model.LCSClusterNodePatches;
import com.liferay.osb.lcs.nosql.service.LCSClusterNodePatchesService;
import com.liferay.osb.lcs.service.LCSClusterEntryLocalServiceUtil;
import com.liferay.osb.lcs.service.LCSClusterNodeLocalService;
import com.liferay.osb.lcs.service.LCSClusterNodeLocalServiceUtil;
import com.liferay.osb.lcs.service.LCSPatchingAdvisorService;
import com.liferay.osb.lcs.storage.DownloadURLResolver;
import com.liferay.osb.lcs.storage.StorageManager;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.File;

import java.net.URL;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Igor Beslic
 */
@Component(
	configurationPid = "com.liferay.osb.lcs.configuration.OSBLCSConfiguration",
	configurationPolicy = ConfigurationPolicy.REQUIRE, immediate = true,
	service = PatchAdvisor.class
)
public class PatchAdvisorImpl implements PatchAdvisor {

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

			Map<String, String> patchNamesURLs = new HashMap<>();

			URL patchURL = getPatchAsURL(patchName);

			patchNamesURLs.put(patchName, patchURL.toString());

			_commandMessageAdvisor.downloadPatches(
				lcsClusterNode, patchNamesURLs);

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

	@Override
	public URL getPatchAsURL(String patchFileName) {
		return _downloadURLResolver.getAsURL(patchFileName);
	}

	@Override
	public List<String> getPatchFileNames(Date sinceModifiedDate) {
		List<String> filteredPatchFileNames = new ArrayList<>();

		Map<String, Long> patchFileNamesSizes =
			_storageManager.getFileNamesSizes(sinceModifiedDate);

		for (String patchFileName : patchFileNamesSizes.keySet()) {
			filteredPatchFileNames.add(patchFileName);
		}

		return filteredPatchFileNames;
	}

	@Override
	public List<String> getPatchFileNames(int buildNumber) {
		List<String> filteredPatchFileNames = new ArrayList<>();

		for (String fileName : _patchFileNamesSizes.keySet()) {
			if (!fileName.contains(String.valueOf(buildNumber))) {
				continue;
			}

			filteredPatchFileNames.add(fileName);
		}

		return filteredPatchFileNames;
	}

	@Override
	public Map<String, Long> getPatchFileNamesSizes() {
		if (!_patchFileNamesSizes.isEmpty()) {
			return _patchFileNamesSizes;
		}

		_patchFileNamesSizes = _storageManager.getFileNamesSizes();

		return _patchFileNamesSizes;
	}

	@Override
	public void resetActivePatchFileNames() {
	}

	@Override
	public void resetInstallablePatches() throws PortalException {
		Set<Long> processedLCSClusterEntries = new HashSet<>();

		int count = LCSClusterNodeLocalServiceUtil.getLCSClusterNodesCount();

		for (int start = 0, end = 20; start < count; end += 20, start += 20) {
			List<LCSClusterNode> lcsClusterNodes =
				LCSClusterNodeLocalServiceUtil.getLCSClusterNodes(
					start, end, true);

			for (LCSClusterNode lcsClusterNode : lcsClusterNodes) {
				if (!_resetInstallablePatches(lcsClusterNode)) {
					continue;
				}

				if (processedLCSClusterEntries.contains(
						lcsClusterNode.getLcsClusterEntryId())) {

					continue;
				}

				Message message = new Message();

				LCSClusterEntry lcsClusterEntry =
					LCSClusterEntryLocalServiceUtil.getLCSClusterEntry(
						lcsClusterNode.getLcsClusterEntryId());

				if (lcsClusterEntry.isCluster()) {
					message.put("cluster", true);

					processedLCSClusterEntries.add(
						lcsClusterEntry.getLcsClusterEntryId());
				}
				else {
					message.put("cluster", false);
				}

				message.put("key", lcsClusterNode.getKey());

				message.setPayload(LCSEventType.NEW_PATCH_AVAILABLE);

				MessageBusUtil.sendMessage("liferay/osb_lcs_events", message);
			}
		}
	}

	@Reference(bind = "-", unbind = "-")
	public void setCommandMessageAdvisor(
		CommandMessageAdvisor commandMessageAdvisor) {

		_commandMessageAdvisor = commandMessageAdvisor;
	}

	@Reference(bind = "-", unbind = "-")
	public void setDownloadURLResolver(
		DownloadURLResolver downloadURLResolver) {

		_downloadURLResolver = downloadURLResolver;
	}

	@Reference(bind = "-", unbind = "-")
	public void setLcsClusterNodeLocalService(
		LCSClusterNodeLocalService lcsClusterNodeLocalService) {

		_lcsClusterNodeLocalService = lcsClusterNodeLocalService;
	}

	@Reference(bind = "-", unbind = "-")
	public void setLCSClusterNodePatchesService(
		LCSClusterNodePatchesService lcsClusterNodePatchesService) {

		_lcsClusterNodePatchesService = lcsClusterNodePatchesService;
	}

	@Reference(bind = "-", unbind = "-")
	public void setLCSPatchingAdvisorService(
		LCSPatchingAdvisorService lcsPatchingAdvisorService) {

		_lcsPatchingAdvisorService = lcsPatchingAdvisorService;
	}

	@Reference(bind = "-", unbind = "-")
	public void setStringAdvisor(StringAdvisor stringAdvisor) {
		_stringAdvisor = stringAdvisor;
	}

	@Override
	public void writePatchFile(File file) {
		_storageManager.writeFile(file);
	}

	protected static String[] getInstalledPatchIds(
		LCSClusterNodePatches lcsClusterNodePatches) {

		Map<String, Integer> patchIdsStatuses =
			lcsClusterNodePatches.getPatchIdsStatuses();

		Set<String> patchIds = patchIdsStatuses.keySet();

		return patchIds.toArray(new String[patchIds.size()]);
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_osbLCSConfiguration = ConfigurableUtil.createConfigurable(
			OSBLCSConfiguration.class, properties);

		try {
			Class<?> storageManagerClass = Class.forName(
				_osbLCSConfiguration.patchStorageManagerClassName());

			_storageManager = (StorageManager)storageManagerClass.newInstance();

			_storageManager.setCredentials(
				_osbLCSConfiguration.storageManagerAccessKey(),
				_osbLCSConfiguration.storageManagerSecretKey());
			_storageManager.setBucketName(
				_osbLCSConfiguration.patchStorageManagerBucketName());
			_storageManager.setPath(
				_osbLCSConfiguration.patchStorageManagerPath());
			_storageManager.setPrefix(
				_osbLCSConfiguration.patchStorageManagerPrefix());

			_patchFileNamesSizes = _storageManager.getFileNamesSizes();
		}
		catch (Exception e) {
			throw new SystemException(
				_stringAdvisor.concat(
					"Unsupported storage manager class", e.getMessage()));
		}
	}

	@Deactivate
	protected void deactivate() {
		_osbLCSConfiguration = null;
	}

	protected void updateLCSClusterNodePatches(
		LCSClusterNodePatches lcsClusterNodePatches) {

		_lcsClusterNodePatchesService.updateLCSClusterNodePatches(
			lcsClusterNodePatches);
	}

	private boolean _resetInstallablePatches(LCSClusterNode lcsClusterNode) {
		int patchingToolStatus = lcsClusterNode.getPatchingToolStatus();

		if (patchingToolStatus == LCSConstants.PATCHING_TOOL_UNAVAILABLE) {
			return false;
		}

		int patchingToolVersion = lcsClusterNode.getPatchingToolVersion();

		if (patchingToolVersion <= 0) {
			return false;
		}

		LCSClusterNodePatches lcsClusterNodePatches =
			_lcsClusterNodePatchesService.fetchLCSClusterNodePatches(
				lcsClusterNode.getKey());

		if (lcsClusterNodePatches == null) {
			List<String> installablePatchIds =
				_lcsPatchingAdvisorService.getInstallablePatchIds(
					lcsClusterNode, lcsClusterNode.getPatchingToolVersion(),
					new String[0]);

			_lcsClusterNodePatchesService.addLCSClusterNodePatches(
				null, null, null, installablePatchIds, lcsClusterNode.getKey(),
				new Date(), null);

			if (installablePatchIds.isEmpty()) {
				return false;
			}

			return true;
		}

		List<String> oldInstallablePatchIds =
			lcsClusterNodePatches.getInstallablePatchIds();

		List<String> newInstallablePatchIds =
			_lcsPatchingAdvisorService.getInstallablePatchIds(
				lcsClusterNode, lcsClusterNode.getPatchingToolVersion(),
				getInstalledPatchIds(lcsClusterNodePatches));

		if ((oldInstallablePatchIds.size() == newInstallablePatchIds.size()) &&
			oldInstallablePatchIds.containsAll(newInstallablePatchIds)) {

			return false;
		}

		lcsClusterNodePatches.setInstallablePatchIds(newInstallablePatchIds);

		updateLCSClusterNodePatches(lcsClusterNodePatches);

		return true;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PatchAdvisorImpl.class);

	private static volatile OSBLCSConfiguration _osbLCSConfiguration;

	private CommandMessageAdvisor _commandMessageAdvisor;
	private DownloadURLResolver _downloadURLResolver;
	private LCSClusterNodeLocalService _lcsClusterNodeLocalService;
	private LCSClusterNodePatchesService _lcsClusterNodePatchesService;
	private LCSPatchingAdvisorService _lcsPatchingAdvisorService;
	private Map<String, Long> _patchFileNamesSizes;
	private StorageManager _storageManager;
	private StringAdvisor _stringAdvisor;

}