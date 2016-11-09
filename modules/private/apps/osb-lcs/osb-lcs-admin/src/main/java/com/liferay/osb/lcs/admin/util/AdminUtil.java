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

package com.liferay.osb.lcs.admin.util;

import com.liferay.lcs.util.LCSClusterNodeStatus;
import com.liferay.osb.lcs.NoSuchLCSClusterNodeException;
import com.liferay.osb.lcs.amazon.EC2Instance;
import com.liferay.osb.lcs.cache.ActiveLCSCLusterNodeCacheManager;
import com.liferay.osb.lcs.cache.LCSClusterNodeLoggingCacheManager;
import com.liferay.osb.lcs.cache.MessageForwardCacheManager;
import com.liferay.osb.lcs.management.MBeanAttributeProvider;
import com.liferay.osb.lcs.model.LCSClusterEntry;
import com.liferay.osb.lcs.model.LCSClusterNode;
import com.liferay.osb.lcs.model.LCSProject;
import com.liferay.osb.lcs.model.LCSRole;
import com.liferay.osb.lcs.model.LCSRoleConstants;
import com.liferay.osb.lcs.nosql.model.LCSClusterNodeDetails;
import com.liferay.osb.lcs.nosql.model.LCSClusterNodeInstallationEnvironment;
import com.liferay.osb.lcs.nosql.model.LCSClusterNodeJVMMetrics;
import com.liferay.osb.lcs.nosql.model.LCSClusterNodePatches;
import com.liferay.osb.lcs.nosql.service.LCSClusterNodeDetailsService;
import com.liferay.osb.lcs.nosql.service.LCSClusterNodeInstallationEnvironmentService;
import com.liferay.osb.lcs.nosql.service.LCSClusterNodeJVMMetricsService;
import com.liferay.osb.lcs.nosql.service.LCSClusterNodePatchesService;
import com.liferay.osb.lcs.service.LCSClusterEntryLocalServiceUtil;
import com.liferay.osb.lcs.service.LCSClusterNodeLocalServiceUtil;
import com.liferay.osb.lcs.service.LCSProjectLocalServiceUtil;
import com.liferay.osb.lcs.service.LCSRoleLocalServiceUtil;
import com.liferay.osb.lcs.util.ApplicationProfile;
import com.liferay.osb.lcs.util.PortletKeys;
import com.liferay.osb.lcs.util.PortletPropsValues;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UniqueList;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

import java.text.NumberFormat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.portlet.PortletPreferences;

/**
 * @author Igor Beslic
 * @author Marko Cikos
 */
public class AdminUtil {

	public static void deleteCache(String key) {
		_activeLCSCLusterNodeCacheManager.remove("ACTIVE_" + key);
	}

	public static void enableLCSClusterNodeLogging(
		String key, boolean enableLogging) {

		if (enableLogging) {
			_lcsClusterNodeLoggingCacheManager.put(key, true);
		}
		else {
			_lcsClusterNodeLoggingCacheManager.remove(key);
		}
	}

	public static void enableMessageForward(
		String queuePrefix, boolean enableMessageForward) {

		if (enableMessageForward) {
			_messageForwardCacheManager.put(queuePrefix, true);
		}
		else {
			_messageForwardCacheManager.remove(queuePrefix);
		}
	}

	public static Map<String, String> getEC2IpAddresses() {
		if (PortletPropsValues.APPLICATION_PROFILE ==
				ApplicationProfile.LOCAL_DEVELOPMENT) {

			Map<String, String> ec2IpAddresses = new HashMap<String, String>();

			ec2IpAddresses.put("127.0.0.1", "localhost");

			return ec2IpAddresses;
		}
		else {
			Map<String, String> ec2IpAddresses =
				_ec2Instance.getPrivateIpAddressesNames();

			Set<Map.Entry<String, String>> set = ec2IpAddresses.entrySet();

			Iterator<Map.Entry<String, String>> iterator = set.iterator();

			while (iterator.hasNext()) {
				Map.Entry<String, String> entry = iterator.next();

				String key = entry.getKey();

				if (PortletPropsValues.APPLICATION_PROFILE ==
						ApplicationProfile.PRODUCTION) {

					if (!StringUtil.startsWith(key, "prod")) {
						iterator.remove();
					}
				}
				else if (PortletPropsValues.APPLICATION_PROFILE ==
							ApplicationProfile.QUALITY_ASSURANCE) {

					if (!StringUtil.startsWith(key, "qa")) {
						iterator.remove();
					}
				}
				else if (PortletPropsValues.APPLICATION_PROFILE ==
							ApplicationProfile.REMOTE_DEVELOPMENT) {

					if (!StringUtil.startsWith(key, "dev")) {
						iterator.remove();
					}
				}
			}

			return ec2IpAddresses;
		}
	}

	public static List<Object[]> getLCSClusterNodeObjectArrays()
		throws PortalException, SystemException {

		return getLCSClusterNodeObjectArrays(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	public static List<Object[]> getLCSClusterNodeObjectArrays(
			int start, int end)
		throws PortalException, SystemException {

		List<Object[]> lcsClusterNodeObjectArrays = new ArrayList<Object[]>();

		List<LCSClusterNode> lcsClusterNodes =
			LCSClusterNodeLocalServiceUtil.getLCSClusterNodes(start, end);

		for (LCSClusterNode lcsClusterNode : lcsClusterNodes) {
			lcsClusterNodeObjectArrays.add(getColumns(lcsClusterNode));
		}

		return lcsClusterNodeObjectArrays;
	}

	public static List<Object[]> getLCSClusterNodeObjectArrays(
			String lcsClusterNodeKey)
		throws PortalException, SystemException {

		List<Object[]> lcsClusterNodeObjectArrays = new ArrayList<Object[]>();

		try {
			LCSClusterNode lcsClusterNode =
				LCSClusterNodeLocalServiceUtil.getLCSClusterNode(
					lcsClusterNodeKey);

			lcsClusterNodeObjectArrays.add(getColumns(lcsClusterNode));
		}
		catch (NoSuchLCSClusterNodeException se) {
		}

		return lcsClusterNodeObjectArrays;
	}

	public static List<Object[]> getLCSClusterNodeObjectArrays(
			String lcsClusterEntryName, String lcsClusterNodeStatusName,
			String lcsProjectName, boolean andOperator)
		throws PortalException, SystemException {

		List<Object[]> lcsClusterNodeObjectArrays = new ArrayList<Object[]>();

		Set<LCSClusterNode> lcsClusterNodes = new HashSet<LCSClusterNode>();

		if (Validator.isNull(lcsProjectName) &&
			Validator.isNull(lcsClusterEntryName)) {

			lcsClusterNodes.addAll(
				LCSClusterNodeLocalServiceUtil.getLCSClusterNodes(
					QueryUtil.ALL_POS, QueryUtil.ALL_POS, true));
		}
		else {
			lcsClusterNodes.addAll(
				LCSClusterNodeLocalServiceUtil.getLCSClusterNodes(
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

		Set<LCSClusterNode> allLCSClusterNodes = new HashSet<LCSClusterNode>();

		allLCSClusterNodes.addAll(
			LCSClusterNodeLocalServiceUtil.getLCSClusterNodes(
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

	public static Map<String, List<Object[]>>
		getLCSModulePropertiesObjectArraysMap(String hostName) {

		Map<String, List<Object[]>> lcsModulePropertiesObjectArraysMap =
			new HashMap<String, List<Object[]>>();

		Map<String, Object> objectNamesAttributesMap =
			_mBeanAttributeProvider.getObjectNamesAttributes(
				hostName, _pattern, "Properties");

		for (String objectName : objectNamesAttributesMap.keySet()) {
			Map<String, String> propertiesMap =
				(Map<String, String>)objectNamesAttributesMap.get(objectName);

			List<Object[]> objectArrays = new ArrayList<Object[]>();

			for (String key : propertiesMap.keySet()) {
				objectArrays.add(new Object[] {key, propertiesMap.get(key)});
			}

			lcsModulePropertiesObjectArraysMap.put(objectName, objectArrays);
		}

		return lcsModulePropertiesObjectArraysMap;
	}

	public static PortletPreferences getPortletPreferences()
		throws SystemException {

		return PortletPreferencesLocalServiceUtil.getPreferences(
			CompanyConstants.SYSTEM, CompanyConstants.SYSTEM,
			PortletKeys.PREFS_OWNER_TYPE_COMPANY, PortletKeys.PREFS_PLID_SHARED,
			PortletKeys.ADMIN, null);
	}

	public static boolean isBillingEnabled() throws SystemException {
		PortletPreferences portletPreferences = getPortletPreferences();

		return GetterUtil.getBoolean(
			portletPreferences.getValue("billing-enabled", null));
	}

	public static boolean isLCSClusterNodeLoggingEnabled(String key) {
		return GetterUtil.getBoolean(
			_lcsClusterNodeLoggingCacheManager.isEnabled(key), false);
	}

	public static boolean isMessageForwardEnabled(String queuePrefix) {
		return _messageForwardCacheManager.isEnabled(queuePrefix);
	}

	public static boolean isSendingEmailsEnabled() throws SystemException {
		PortletPreferences portletPreferences = getPortletPreferences();

		return GetterUtil.getBoolean(
			portletPreferences.getValue("sending-emails-enabled", null));
	}

	public static boolean isSubscriptionsEnabled() throws SystemException {
		PortletPreferences portletPreferences = getPortletPreferences();

		return GetterUtil.getBoolean(
			portletPreferences.getValue("subscriptions-enabled", null));
	}

	protected static Object[] getColumns(LCSClusterNode lcsClusterNode)
		throws PortalException, SystemException {

		Object[] columns = new Object[14];

		LCSClusterEntry lcsClusterEntry =
			LCSClusterEntryLocalServiceUtil.getLCSClusterEntry(
				lcsClusterNode.getLcsClusterEntryId());

		LCSProject lcsProject = LCSProjectLocalServiceUtil.getLCSProject(
			lcsClusterEntry.getLcsProjectId());

		columns[0] = lcsProject.getName();
		columns[1] = lcsClusterEntry;
		columns[2] = lcsClusterNode.getKey();
		columns[3] = lcsClusterNode.getName();
		columns[4] = new Integer(0);

		LCSClusterNodeDetails lcsClusterNodeDetails =
			_lcsClusterNodeDetailsService.fetchLCSClusterNodeDetails(
				lcsClusterNode.getKey());

		if (lcsClusterNodeDetails == null) {
			return columns;
		}

		columns[4] = new Integer(lcsClusterNodeDetails.getStatus());
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
				lcsClusterNodeInstallationEnvironment. getSoftwareMetadata();

			if (softwareMetadata != null) {
				columns[11] = softwareMetadata.get("lcs.portlet.build.number");
			}
		}

		List<String> emailAddresses = new UniqueList<String>();

		List<LCSRole> lcsRoles =
			LCSRoleLocalServiceUtil.getLCSProjectLCSRoles(
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

	@BeanReference(type = ActiveLCSCLusterNodeCacheManager.class)
	private static ActiveLCSCLusterNodeCacheManager
		_activeLCSCLusterNodeCacheManager;

	@BeanReference(type = EC2Instance.class)
	private static EC2Instance _ec2Instance;

	@BeanReference(type = LCSClusterNodeDetailsService.class)
	private static LCSClusterNodeDetailsService _lcsClusterNodeDetailsService;

	@BeanReference(type = LCSClusterNodeInstallationEnvironmentService.class)
	private static LCSClusterNodeInstallationEnvironmentService
		_lcsClusterNodeInstallationEnvironmentService;

	@BeanReference(type = LCSClusterNodeJVMMetricsService.class)
	private static LCSClusterNodeJVMMetricsService
		_lcsClusterNodeJVMMetricsService;

	@BeanReference(type = LCSClusterNodeLoggingCacheManager.class)
	private static LCSClusterNodeLoggingCacheManager
		_lcsClusterNodeLoggingCacheManager;

	@BeanReference(type = LCSClusterNodePatchesService.class)
	private static LCSClusterNodePatchesService _lcsClusterNodePatchesService;

	@BeanReference(type = MBeanAttributeProvider.class)
	private static MBeanAttributeProvider _mBeanAttributeProvider;

	@BeanReference(type = MessageForwardCacheManager.class)
	private static MessageForwardCacheManager _messageForwardCacheManager;

	private static NumberFormat _numberFormat = NumberFormat.getInstance();
	private static Pattern _pattern = Pattern.compile(
		"(com.liferay.osb.lcs:name=(Gateway|Portlet|Processor))");

	static {
		_numberFormat.setMaximumFractionDigits(2);
		_numberFormat.setMinimumFractionDigits(2);
	}

}