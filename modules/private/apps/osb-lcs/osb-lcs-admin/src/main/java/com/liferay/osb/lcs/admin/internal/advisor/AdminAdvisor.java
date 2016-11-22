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

package com.liferay.osb.lcs.admin.internal.advisor;

import com.liferay.lcs.util.LCSClusterNodeStatus;
import com.liferay.osb.lcs.cache.ActiveLCSCLusterNodeCacheManager;
import com.liferay.osb.lcs.cache.LCSClusterNodeLoggingCacheManager;
import com.liferay.osb.lcs.cache.MessageForwardCacheManager;
import com.liferay.osb.lcs.configuration.OSBLCSConfiguration;
import com.liferay.osb.lcs.constants.LCSRoleConstants;
import com.liferay.osb.lcs.constants.OSBLCSPortletKeys;
import com.liferay.osb.lcs.exception.NoSuchLCSClusterNodeException;
import com.liferay.osb.lcs.model.LCSClusterEntry;
import com.liferay.osb.lcs.model.LCSClusterNode;
import com.liferay.osb.lcs.model.LCSProject;
import com.liferay.osb.lcs.model.LCSRole;
import com.liferay.osb.lcs.nosql.model.LCSClusterNodeDetails;
import com.liferay.osb.lcs.nosql.model.LCSClusterNodeInstallationEnvironment;
import com.liferay.osb.lcs.nosql.model.LCSClusterNodeJVMMetrics;
import com.liferay.osb.lcs.nosql.model.LCSClusterNodePatches;
import com.liferay.osb.lcs.nosql.service.LCSClusterNodeDetailsService;
import com.liferay.osb.lcs.nosql.service.LCSClusterNodeInstallationEnvironmentService;
import com.liferay.osb.lcs.nosql.service.LCSClusterNodeJVMMetricsService;
import com.liferay.osb.lcs.nosql.service.LCSClusterNodePatchesService;
import com.liferay.osb.lcs.service.LCSClusterEntryLocalService;
import com.liferay.osb.lcs.service.LCSClusterNodeLocalService;
import com.liferay.osb.lcs.service.LCSProjectLocalService;
import com.liferay.osb.lcs.service.LCSRoleLocalService;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.service.PortletPreferencesLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UniqueList;
import com.liferay.portal.kernel.util.Validator;

import java.text.NumberFormat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.portlet.PortletPreferences;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Igor Beslic
 * @author Marko Cikos
 */
@Component(
	configurationPid = "com.liferay.osb.lcs.configuration.OSBLCSConfiguration",
	configurationPolicy = ConfigurationPolicy.REQUIRE, immediate = true,
	service = AdminAdvisor.class
)
public class AdminAdvisor {

	public void deleteCache(String key) {
		_activeLCSCLusterNodeCacheManager.remove("ACTIVE_" + key);
	}

	public void enableLCSClusterNodeLogging(String key, boolean enableLogging) {
		if (enableLogging) {
			_lcsClusterNodeLoggingCacheManager.put(key, true);
		}
		else {
			_lcsClusterNodeLoggingCacheManager.remove(key);
		}
	}

	public void enableMessageForward(
		String queuePrefix, boolean enableMessageForward) {

		if (enableMessageForward) {
			_messageForwardCacheManager.put(queuePrefix, true);
		}
		else {
			_messageForwardCacheManager.remove(queuePrefix);
		}
	}

	public List<Object[]> getLCSClusterNodeObjectArrays()
		throws PortalException {

		return getLCSClusterNodeObjectArrays(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	public List<Object[]> getLCSClusterNodeObjectArrays(int start, int end)
		throws PortalException {

		List<Object[]> lcsClusterNodeObjectArrays = new ArrayList<>();

		List<LCSClusterNode> lcsClusterNodes =
			_lcsClusterNodeLocalService.getLCSClusterNodes(start, end);

		for (LCSClusterNode lcsClusterNode : lcsClusterNodes) {
			lcsClusterNodeObjectArrays.add(getColumns(lcsClusterNode));
		}

		return lcsClusterNodeObjectArrays;
	}

	public List<Object[]> getLCSClusterNodeObjectArrays(
			String lcsClusterNodeKey)
		throws PortalException {

		List<Object[]> lcsClusterNodeObjectArrays = new ArrayList<>();

		try {
			LCSClusterNode lcsClusterNode =
				_lcsClusterNodeLocalService.getLCSClusterNode(
					lcsClusterNodeKey);

			lcsClusterNodeObjectArrays.add(getColumns(lcsClusterNode));
		}
		catch (NoSuchLCSClusterNodeException nslcscne) {
		}

		return lcsClusterNodeObjectArrays;
	}

	public List<Object[]> getLCSClusterNodeObjectArrays(
			String lcsClusterEntryName, String lcsClusterNodeStatusName,
			String lcsProjectName, boolean andOperator)
		throws PortalException {

		List<Object[]> lcsClusterNodeObjectArrays = new ArrayList<>();

		Set<LCSClusterNode> lcsClusterNodes = new HashSet<>();

		if (Validator.isNull(lcsProjectName) &&
			Validator.isNull(lcsClusterEntryName)) {

			lcsClusterNodes.addAll(
				_lcsClusterNodeLocalService.getLCSClusterNodes(
					QueryUtil.ALL_POS, QueryUtil.ALL_POS, true));
		}
		else {
			lcsClusterNodes.addAll(
				_lcsClusterNodeLocalService.getLCSClusterNodes(
					lcsClusterEntryName, lcsProjectName, andOperator, true));
		}

		LCSClusterNodeStatus lcsClusterNodeStatus = null;

		if (Validator.isNotNull(lcsClusterNodeStatusName)) {
			lcsClusterNodeStatus = LCSClusterNodeStatus.valueOf(
				lcsClusterNodeStatusName);
		}

		for (LCSClusterNode lcsClusterNode : lcsClusterNodes) {
			if (andOperator && (lcsClusterNodeStatus != null) &&
				!lcsClusterNodeStatus.hasStatus(
					lcsClusterNode.getStatus())) {

				continue;
			}

			lcsClusterNodeObjectArrays.add(getColumns(lcsClusterNode));
		}

		if ((lcsClusterNodeStatus == null) || andOperator ||
			(Validator.isNull(lcsClusterEntryName) &&
			 Validator.isNull(lcsProjectName))) {

			return lcsClusterNodeObjectArrays;
		}

		Set<LCSClusterNode> allLCSClusterNodes = new HashSet<>();

		allLCSClusterNodes.addAll(
			_lcsClusterNodeLocalService.getLCSClusterNodes(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, true));

		allLCSClusterNodes.removeAll(lcsClusterNodes);

		for (LCSClusterNode lcsClusterNode : allLCSClusterNodes) {
			if (!lcsClusterNodeStatus.hasStatus(lcsClusterNode.getStatus())) {
				continue;
			}

			lcsClusterNodeObjectArrays.add(getColumns(lcsClusterNode));
		}

		return lcsClusterNodeObjectArrays;
	}

	public PortletPreferences getPortletPreferences() {
		return _portletPreferencesLocalService.getPreferences(
			CompanyConstants.SYSTEM, CompanyConstants.SYSTEM,
			PortletKeys.PREFS_OWNER_TYPE_COMPANY, PortletKeys.PREFS_PLID_SHARED,
			OSBLCSPortletKeys.ADMIN, null);
	}

	public boolean isBillingEnabled() {
		PortletPreferences portletPreferences = getPortletPreferences();

		return GetterUtil.getBoolean(
			portletPreferences.getValue("billing-enabled", null));
	}

	public boolean isLCSClusterNodeLoggingEnabled(String key) {
		return GetterUtil.getBoolean(
			_lcsClusterNodeLoggingCacheManager.isEnabled(key));
	}

	public boolean isMessageForwardEnabled(String queuePrefix) {
		return _messageForwardCacheManager.isEnabled(queuePrefix);
	}

	public boolean isSendingEmailsEnabled() {
		PortletPreferences portletPreferences = getPortletPreferences();

		return GetterUtil.getBoolean(
			portletPreferences.getValue("sending-emails-enabled", null));
	}

	public boolean isSubscriptionsEnabled() {
		PortletPreferences portletPreferences = getPortletPreferences();

		return GetterUtil.getBoolean(
			portletPreferences.getValue("subscriptions-enabled", null));
	}

	@Reference(unbind = "-")
	public void setActiveLCSCLusterNodeCacheManager(
		ActiveLCSCLusterNodeCacheManager activeLCSCLusterNodeCacheManager) {

		_activeLCSCLusterNodeCacheManager = activeLCSCLusterNodeCacheManager;
	}

	@Reference(unbind = "-")
	public void setLCSClusterEntryLocalService(
		LCSClusterEntryLocalService lcsClusterEntryLocalService) {

		_lcsClusterEntryLocalService = lcsClusterEntryLocalService;
	}

	@Reference(unbind = "-")
	public void setLCSClusterNodeDetailsService(
		LCSClusterNodeDetailsService lcsClusterNodeDetailsService) {

		_lcsClusterNodeDetailsService = lcsClusterNodeDetailsService;
	}

	@Reference(unbind = "-")
	public void setLCSClusterNodeInstallationEnvironmentService(
		LCSClusterNodeInstallationEnvironmentService
			lcsClusterNodeInstallationEnvironmentService) {

		_lcsClusterNodeInstallationEnvironmentService =
			lcsClusterNodeInstallationEnvironmentService;
	}

	@Reference(unbind = "-")
	public void setLcsClusterNodeJVMMetricsService(
		LCSClusterNodeJVMMetricsService lcsClusterNodeJVMMetricsService) {

		_lcsClusterNodeJVMMetricsService = lcsClusterNodeJVMMetricsService;
	}

	@Reference(unbind = "-")
	public void setLCSClusterNodeLocalService(
		LCSClusterNodeLocalService lcsClusterNodeLocalService) {

		_lcsClusterNodeLocalService = lcsClusterNodeLocalService;
	}

	@Reference(unbind = "-")
	public void setLCSClusterNodeLoggingCacheManager(
		LCSClusterNodeLoggingCacheManager lcsClusterNodeLoggingCacheManager) {

		_lcsClusterNodeLoggingCacheManager = lcsClusterNodeLoggingCacheManager;
	}

	@Reference(unbind = "-")
	public void setLCSClusterNodePatchesService(
		LCSClusterNodePatchesService lcsClusterNodePatchesService) {

		_lcsClusterNodePatchesService = lcsClusterNodePatchesService;
	}

	@Reference(unbind = "-")
	public void setLCSProjectLocalService(
		LCSProjectLocalService lcsProjectLocalService) {

		_lcsProjectLocalService = lcsProjectLocalService;
	}

	@Reference(unbind = "-")
	public void setLCSRoleLocalService(
		LCSRoleLocalService lcsRoleLocalService) {

		_lcsRoleLocalService = lcsRoleLocalService;
	}

	@Reference(unbind = "-")
	public void setMessageForwardCacheManager(
		MessageForwardCacheManager messageForwardCacheManager) {

		_messageForwardCacheManager = messageForwardCacheManager;
	}

	@Reference(unbind = "-")
	public void setPortletPreferencesLocalService(
		PortletPreferencesLocalService portletPreferencesLocalService) {

		_portletPreferencesLocalService = portletPreferencesLocalService;
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_osbLCSConfiguration = ConfigurableUtil.createConfigurable(
			OSBLCSConfiguration.class, properties);
	}

	@Deactivate
	protected void deactivate() {
		_osbLCSConfiguration = null;
	}

	protected Object[] getColumns(LCSClusterNode lcsClusterNode)
		throws PortalException {

		Object[] columns = new Object[14];

		LCSClusterEntry lcsClusterEntry =
			_lcsClusterEntryLocalService.getLCSClusterEntry(
				lcsClusterNode.getLcsClusterEntryId());

		LCSProject lcsProject = _lcsProjectLocalService.getLCSProject(
			lcsClusterEntry.getLcsProjectId());

		columns[0] = lcsProject.getName();

		columns[1] = lcsClusterEntry;

		columns[2] = lcsClusterNode.getKey();
		columns[3] = lcsClusterNode.getName();

		columns[4] = Integer.valueOf(0);

		LCSClusterNodeDetails lcsClusterNodeDetails =
			_lcsClusterNodeDetailsService.fetchLCSClusterNodeDetails(
				lcsClusterNode.getKey());

		if (lcsClusterNodeDetails == null) {
			return columns;
		}

		columns[4] = Integer.valueOf(lcsClusterNodeDetails.getStatus());
		columns[5] = lcsClusterNodeDetails.getPortalEdition();
		columns[6] = lcsClusterNodeDetails.getBuildNumber();
		columns[7] = lcsClusterNodeDetails.getLastHeartbeat();
		columns[8] = lcsClusterNodeDetails.getModifiedDate();

		LCSClusterNodeJVMMetrics lcsClusterNodeJVMMetrics =
			_lcsClusterNodeJVMMetricsService.fetchLCSClusterNodeJVMMetrics(
				lcsClusterNode.getKey());

		if (lcsClusterNodeJVMMetrics != null) {
			columns[9] = lcsClusterNodeJVMMetrics.getModifiedDate();
		}

		LCSClusterNodePatches lcsClusterNodePatches =
			_lcsClusterNodePatchesService.fetchLCSClusterNodePatches(
				lcsClusterNode.getKey());

		if (lcsClusterNodePatches != null) {
			columns[10] = lcsClusterNodePatches.getModifiedDate();
		}

		LCSClusterNodeInstallationEnvironment
			lcsClusterNodeInstallationEnvironment =
				_lcsClusterNodeInstallationEnvironmentService.
					fetchLCSClusterNodeInstallationEnvironment(
						lcsClusterNode.getKey());

		if (lcsClusterNodeInstallationEnvironment != null) {
			Map<String, String> softwareMetadata =
				lcsClusterNodeInstallationEnvironment.getSoftwareMetadata();

			if (softwareMetadata != null) {
				columns[11] = softwareMetadata.get("lcs.portlet.build.number");
			}
		}

		List<String> emailAddresses = new UniqueList<>();

		List<LCSRole> lcsRoles = _lcsRoleLocalService.getLCSProjectLCSRoles(
			lcsProject.getLcsProjectId(),
			LCSRoleConstants.ROLE_LCS_ADMINISTRATOR);

		for (LCSRole lcsRole : lcsRoles) {
			String emailAddress = PortalUtil.getUserEmailAddress(
				lcsRole.getUserId());

			emailAddresses.add(emailAddress);
		}

		columns[12] = StringUtil.merge(emailAddresses, StringPool.COMMA);

		if (_activeLCSCLusterNodeCacheManager.contains(
				lcsClusterNode.getKey())) {

			columns[13] = true;
		}

		return columns;
	}

	private static final NumberFormat _numberFormat =
		NumberFormat.getInstance();
	private static volatile OSBLCSConfiguration _osbLCSConfiguration;
	private static final Pattern _pattern = Pattern.compile(
		"(com.liferay.osb.lcs:name=(Gateway|Portlet|Processor))");

	static {
		_numberFormat.setMaximumFractionDigits(2);
		_numberFormat.setMinimumFractionDigits(2);
	}

	private ActiveLCSCLusterNodeCacheManager _activeLCSCLusterNodeCacheManager;
	private LCSClusterEntryLocalService _lcsClusterEntryLocalService;
	private LCSClusterNodeDetailsService _lcsClusterNodeDetailsService;
	private LCSClusterNodeInstallationEnvironmentService
		_lcsClusterNodeInstallationEnvironmentService;
	private LCSClusterNodeJVMMetricsService _lcsClusterNodeJVMMetricsService;
	private LCSClusterNodeLocalService _lcsClusterNodeLocalService;
	private LCSClusterNodeLoggingCacheManager
		_lcsClusterNodeLoggingCacheManager;
	private LCSClusterNodePatchesService _lcsClusterNodePatchesService;
	private LCSProjectLocalService _lcsProjectLocalService;
	private LCSRoleLocalService _lcsRoleLocalService;
	private MessageForwardCacheManager _messageForwardCacheManager;
	private PortletPreferencesLocalService _portletPreferencesLocalService;

}