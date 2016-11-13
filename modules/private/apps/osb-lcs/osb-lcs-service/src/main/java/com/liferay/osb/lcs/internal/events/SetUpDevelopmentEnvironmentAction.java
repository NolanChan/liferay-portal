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

package com.liferay.osb.lcs.internal.events;

import com.liferay.lcs.subscription.SubscriptionType;
import com.liferay.lcs.util.LCSConstants;
import com.liferay.osb.lcs.advisor.DevelopmentAdvisor;
import com.liferay.osb.lcs.advisor.UserAdvisor;
import com.liferay.osb.lcs.configuration.OSBLCSConfiguration;
import com.liferay.osb.lcs.constants.LCSRoleConstants;
import com.liferay.osb.lcs.model.LCSClusterEntry;
import com.liferay.osb.lcs.model.LCSClusterNode;
import com.liferay.osb.lcs.model.LCSProject;
import com.liferay.osb.lcs.model.LCSSubscriptionEntry;
import com.liferay.osb.lcs.model.impl.LCSSubscriptionEntryImpl;
import com.liferay.osb.lcs.nosql.service.LCSClusterNodeDetailsService;
import com.liferay.osb.lcs.service.LCSClusterEntryLocalService;
import com.liferay.osb.lcs.service.LCSClusterEntryLocalServiceUtil;
import com.liferay.osb.lcs.service.LCSClusterEntryServiceUtil;
import com.liferay.osb.lcs.service.LCSClusterNodeLocalServiceUtil;
import com.liferay.osb.lcs.service.LCSMessageLocalServiceUtil;
import com.liferay.osb.lcs.service.LCSProjectServiceUtil;
import com.liferay.osb.lcs.service.LCSRoleServiceUtil;
import com.liferay.osb.lcs.service.LCSSubscriptionEntryLocalServiceUtil;
import com.liferay.osb.lcs.util.ApplicationProfile;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.events.LifecycleEvent;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ReleaseInfo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
@Component(
	configurationPid = "com.liferay.osb.lcs.configuration.OSBLCSConfiguration",
	configurationPolicy = ConfigurationPolicy.REQUIRE, immediate = true,
	property = {"key=application.startup.events"},
	service = LifecycleAction.class
)
public class SetUpDevelopmentEnvironmentAction implements LifecycleAction {

	public static final String[] ORGANIZATION_NAMES = {
		"Delvian", "Human", "Leviathan", "Sebacean"
	};

	public static final int[] PORTAL_BUILD_NUMBERS = {
		6005, 6100, 6101, 6110, 6120, 6200, 7000
	};

	public static final String[] PORTAL_EDITION_ARRAY = {"CE", "EE"};

	public static final String[] USER_NAMES = {
		"Aeryn Sun", "Bialar Crais", "Chiana Nebari", "Fenra Teeg",
		"John Crichton", "Ka Dargo", "Scorpius Grasshopper", "Zotoh Zhaan"
	};

	@Override
	public void processLifecycleEvent(LifecycleEvent lifecycleEvent)
		throws ActionException {

		String[] ids = lifecycleEvent.getIds();

		_originalPermissionChecker =
			PermissionThreadLocal.getPermissionChecker();
		_originalPrincipalThreadLocalName = PrincipalThreadLocal.getName();

		try {
			User user = _userAdvisor.getAdminUser(GetterUtil.getLong(ids[0]));

			PermissionChecker permissionChecker =
				PermissionCheckerFactoryUtil.create(user);

			PermissionThreadLocal.setPermissionChecker(permissionChecker);

			PrincipalThreadLocal.setName(user.getUserId());

			ServiceContext serviceContext = new ServiceContext();

			long companyId = GetterUtil.getLong(ids[0]);

			serviceContext.setCompanyId(companyId);

			ServiceContextThreadLocal.pushServiceContext(serviceContext);

			doRun(companyId);
		}
		catch (Exception e) {
			throw new ActionException(e);
		}
		finally {
			PermissionThreadLocal.setPermissionChecker(
				_originalPermissionChecker);

			PrincipalThreadLocal.setName(_originalPrincipalThreadLocalName);

			ServiceContextThreadLocal.popServiceContext();
		}
	}

	@Reference(bind = "-", unbind = "-")
	public void setDevelopmentAdvisor(DevelopmentAdvisor developmentAdvisor) {
		_developmentAdvisor = developmentAdvisor;
	}

	@Reference(bind = "-", unbind = "-")
	public void setLCSClusterEntryLocalService(
		LCSClusterEntryLocalService lcsClusterEntryLocalService) {

		_lcsClusterEntryLocalService = lcsClusterEntryLocalService;
	}

	@Reference(bind = "-", unbind = "-")
	public void setLCSClusterNodeDetailsService(
		LCSClusterNodeDetailsService lcsClusterNodeDetailsService) {

		_lcsClusterNodeDetailsService = lcsClusterNodeDetailsService;
	}

	@Reference(bind = "-", unbind = "-")
	public void setRoleLocalService(RoleLocalService roleLocalService) {
		_roleLocalService = roleLocalService;
	}

	@Reference(bind = "-", unbind = "-")
	public void setUserAdvisor(UserAdvisor userAdvisor) {
		_userAdvisor = userAdvisor;
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_osbLCSConfiguration = ConfigurableUtil.createConfigurable(
			OSBLCSConfiguration.class, properties);
	}

	protected void addLCSClusterEntries() throws Exception {
		if (LCSClusterEntryLocalServiceUtil.getLCSClusterEntriesCount() > 0) {
			return;
		}

		for (LCSProject lcsProject :
				LCSProjectServiceUtil.getUserLCSProjects()) {

			int count = _random.nextInt(6);

			for (int j = 1; j < count; j++) {
				LCSClusterEntry lcsClusterEntry =
					_lcsClusterEntryLocalService.addLCSClusterEntry(
						lcsProject.getLcsProjectId(),
						"Environment " + lcsProject.getLcsProjectId() + j,
						"Description " + lcsProject.getLcsProjectId() + j,
						"Location " + lcsProject.getLcsProjectId() + j,
						SubscriptionType.UNDEFINED.name(), j % 2);

				addLCSClusterNodes(lcsClusterEntry.getLcsClusterEntryId());
			}
		}
	}

	protected void addLCSClusterNodes(long lcsClusterEntryId) throws Exception {
		int count = _random.nextInt(10);

		List<LCSClusterNode> lcsClusterNodes = new ArrayList<>();

		for (int i = 1; i < count; i++) {
			LCSClusterNode lcsClusterNode =
				LCSClusterNodeLocalServiceUtil.addLCSClusterNode(
					lcsClusterEntryId, "Server " + lcsClusterEntryId + i,
					"Description " + lcsClusterEntryId + i,
					ReleaseInfo.getBuildNumber(),
					String.valueOf(_random.nextLong()),
					"Location " + lcsClusterEntryId + i, _random.nextInt(72));

			lcsClusterNodes.add(lcsClusterNode);
		}

		addLCSClusterNodesDetails(lcsClusterNodes);
	}

	protected void addLCSClusterNodesDetails(
		List<LCSClusterNode> lcsClusterNodes) {

		for (LCSClusterNode lcsClusterNode : lcsClusterNodes) {
			int buildNumber = PORTAL_BUILD_NUMBERS[
				_random.nextInt(PORTAL_BUILD_NUMBERS.length)];

			Calendar calendar = new GregorianCalendar();

			Date currentDate = new Date();

			calendar.setTime(currentDate);

			calendar.add(Calendar.YEAR, -1);

			Date modifiedDate = getRandomDate(calendar.getTime(), currentDate);

			String portalEdition = PORTAL_EDITION_ARRAY[
				_random.nextInt(PORTAL_EDITION_ARRAY.length)];

			_lcsClusterNodeDetailsService.addLCSClusterNodeDetails(
				buildNumber, null, null, _random.nextLong(),
				lcsClusterNode.getKey(), _random.nextLong(), modifiedDate, 0,
				portalEdition, null, 0);
		}
	}

	protected void addLCSMessages() throws Exception {
		if (LCSMessageLocalServiceUtil.getLCSMessagesCount() > 0) {
			return;
		}

		Calendar calendar = new GregorianCalendar();

		Date currentDate = new Date();

		calendar.add(Calendar.MONTH, 1);

		for (LCSProject lcsProject :
				LCSProjectServiceUtil.getUserLCSProjects()) {

			LCSMessageLocalServiceUtil.addLCSProjectLCSMessage(
				lcsProject.getLcsProjectId(), _random.nextInt(10),
				LCSConstants.SOURCE_SYSTEM_NAME_LCS,
				"A sample LCS project message.",
				getRandomDate(currentDate, calendar.getTime()),
				_random.nextBoolean(), _random.nextInt(4),
				_random.nextInt(2) + 8, false, true);

			List<LCSClusterEntry> lcsClusterEntries =
				LCSClusterEntryLocalServiceUtil.getLCSProjectLCSClusterEntries(
					lcsProject.getLcsProjectId());

			for (LCSClusterEntry lcsClusterEntry : lcsClusterEntries) {
				LCSMessageLocalServiceUtil.addLCSClusterEntryLCSMessage(
					lcsClusterEntry.getLcsClusterEntryId(), _random.nextInt(10),
					LCSConstants.SOURCE_SYSTEM_NAME_LCS,
					"A sample LCS environment message.",
					getRandomDate(currentDate, calendar.getTime()),
					_random.nextBoolean(), _random.nextInt(4), 7, true);

				for (LCSClusterNode lcsClusterNode :
						LCSClusterNodeLocalServiceUtil.
							getLCSClusterEntryLCSClusterNodes(
								lcsClusterEntry.getLcsClusterEntryId())) {

					LCSMessageLocalServiceUtil.addLCSClusterNodeLCSMessage(
						lcsClusterNode.getLcsClusterNodeId(),
						_random.nextInt(10),
						LCSConstants.SOURCE_SYSTEM_NAME_LCS,
						"A sample LCS node message.",
						getRandomDate(currentDate, calendar.getTime()),
						_random.nextBoolean(), _random.nextInt(4),
						_random.nextInt(7));
				}
			}
		}
	}

	protected List<LCSProject> addLCSProjects() throws Exception {
		List<LCSProject> lcsProjects = new ArrayList<>();

		List<LCSProject> userLCSProjects =
			LCSProjectServiceUtil.getUserLCSProjects();

		for (String name : ORGANIZATION_NAMES) {
			LCSProject lcsProject = getLCSProject(userLCSProjects, name);

			if (lcsProject != null) {
				continue;
			}

			lcsProject = LCSProjectServiceUtil.addLCSProject(
				LCSConstants.SOURCE_SYSTEM_NAME_LCS, name);

			lcsProjects.add(lcsProject);
		}

		return lcsProjects;
	}

	protected void addLCSSubscriptionEntries() throws Exception {
		if (LCSSubscriptionEntryLocalServiceUtil.
				getLCSSubscriptionEntriesCount() > 0) {

			return;
		}

		Calendar calendar = new GregorianCalendar();

		Date currentDate = new Date();

		calendar.setTime(currentDate);

		calendar.add(Calendar.YEAR, -1);

		Date minExpirationDate = calendar.getTime();

		calendar.add(Calendar.YEAR, -4);

		Date minStartDate = calendar.getTime();

		calendar.add(Calendar.YEAR, 6);

		Date maxExpirationDate = calendar.getTime();

		List<LCSProject> lcsProjects =
			LCSProjectServiceUtil.getUserLCSProjects();

		for (LCSProject lcsProject : lcsProjects) {
			int maxCount = 22;
			int minCount = 9;
			float multiplier = _random.nextFloat();

			int count = (int)(multiplier * (maxCount - minCount)) + minCount;

			_subscriptionTypeCounts.clear();

			for (int i = 1; i < count; i++) {
				LCSSubscriptionEntry lcsSubscriptionEntry =
					new LCSSubscriptionEntryImpl();

				lcsSubscriptionEntry.setLcsProjectId(
					lcsProject.getLcsProjectId());

				SubscriptionType randomSubscriptionType =
					getRandomSubscriptionType();

				lcsSubscriptionEntry.setType(randomSubscriptionType.name());

				lcsSubscriptionEntry.setPlatform("liferay portal");
				lcsSubscriptionEntry.setPlatformVersion(6210);
				lcsSubscriptionEntry.setProcessorCoresAllowed(
					_random.nextInt(16));
				lcsSubscriptionEntry.setProduct("liferay portal");
				lcsSubscriptionEntry.setProductVersion(6210);
				lcsSubscriptionEntry.setServersAllowed(10);
				lcsSubscriptionEntry.setServersUsed(0);

				Date startDate = getRandomDate(minStartDate, currentDate);

				lcsSubscriptionEntry.setStartDate(startDate);

				Date expirationDate = getRandomDate(
					minExpirationDate, maxExpirationDate);

				if (DateUtil.compareTo(startDate, expirationDate) >= 0) {
					expirationDate = getRandomDate(
						currentDate, maxExpirationDate);
				}

				lcsSubscriptionEntry.setEndDate(expirationDate);

				if ((randomSubscriptionType != SubscriptionType.DEVELOPER) &&
					(randomSubscriptionType !=
						SubscriptionType.DEVELOPER_CLUSTER)) {

					lcsSubscriptionEntry.setSupportEndDate(expirationDate);
				}

				lcsSubscriptionEntry.setActive(_random.nextBoolean());

				LCSSubscriptionEntryLocalServiceUtil.addLCSSubscriptionEntry(
					lcsSubscriptionEntry);

				SubscriptionType[] subscriptionTypes =
					SubscriptionType.values();

				if (_subscriptionTypeCounts.size() ==
						subscriptionTypes.length) {

					break;
				}
			}
		}
	}

	protected void addUsers(long companyId, List<LCSProject> lcsProjects)
		throws Exception {

		User user = _userAdvisor.getAdminUser(companyId);

		for (String userFullName : USER_NAMES) {
			String[] userFirstLastName = userFullName.split("\\s");

			User mockUser = _developmentAdvisor.addUser(
				user.getUserId(), companyId, userFirstLastName[0],
				userFirstLastName[1]);

			for (LCSProject lcsProject : lcsProjects) {
				if (!_random.nextBoolean()) {
					continue;
				}

				List<LCSClusterEntry> lcsClusterEntries =
					LCSClusterEntryServiceUtil.getLCSProjectLCSClusterEntries(
						lcsProject.getLcsProjectId());

				if (lcsClusterEntries.isEmpty()) {
					continue;
				}

				LCSClusterEntry lcsClusterEntry = lcsClusterEntries.get(
					_random.nextInt(lcsClusterEntries.size()));

				int role =
					LCSRoleConstants.
						ROLE_LCS_ENVIRONMENT_MEMBERSHIP_PENDING_USER;

				if (_random.nextBoolean()) {
					role = LCSRoleConstants.ROLE_LCS_ENVIRONMENT_MANAGER;
				}

				LCSRoleServiceUtil.addLCSRole(
					mockUser.getUserId(), lcsProject.getLcsProjectId(),
					lcsClusterEntry.getLcsClusterEntryId(), role);
			}
		}
	}

	protected void doRun(long companyId) throws Exception {
		if (_osbLCSConfiguration.applicationProfile() !=
				ApplicationProfile.LOCAL_DEVELOPMENT) {

			return;
		}

		_developmentAdvisor.addLCSUserRole(companyId);

		List<LCSProject> lcsProjects = addLCSProjects();

		addLCSClusterEntries();

		addLCSSubscriptionEntries();

		addUsers(companyId, lcsProjects);

		addLCSMessages();
	}

	protected LCSProject getLCSProject(
		List<LCSProject> lcsProjects, String name) {

		for (LCSProject lcsProject : lcsProjects) {
			if (name.equals(lcsProject.getName())) {
				return lcsProject;
			}
		}

		return null;
	}

	protected Date getRandomDate(Date startDate, Date endDate) {
		float multiplier = _random.nextFloat();

		if (multiplier == 0.0f) {
			multiplier = 0.01f;
		}

		long randomTime =
			(long)(multiplier * (endDate.getTime() - startDate.getTime()) +
				startDate.getTime());

		Date randomDate = new Date(randomTime);

		return randomDate;
	}

	protected SubscriptionType getRandomSubscriptionType() {
		int maxSubscriptionTypeCount = 2;
		SubscriptionType[] subscriptionTypes = SubscriptionType.values();

		while (true) {
			SubscriptionType randomSubscriptionType =
				subscriptionTypes[_random.nextInt(subscriptionTypes.length)];

			if (randomSubscriptionType == SubscriptionType.UNDEFINED) {
				continue;
			}

			Integer subscriptionTypeCount = _subscriptionTypeCounts.get(
				randomSubscriptionType);

			if (subscriptionTypeCount == null) {
				subscriptionTypeCount = 0;
			}

			if (subscriptionTypeCount >= maxSubscriptionTypeCount) {
				continue;
			}

			_subscriptionTypeCounts.put(
				randomSubscriptionType, subscriptionTypeCount + 1);

			return randomSubscriptionType;
		}
	}

	private static volatile OSBLCSConfiguration _osbLCSConfiguration;

	private DevelopmentAdvisor _developmentAdvisor;
	private LCSClusterEntryLocalService _lcsClusterEntryLocalService;
	private LCSClusterNodeDetailsService _lcsClusterNodeDetailsService;
	private PermissionChecker _originalPermissionChecker;
	private String _originalPrincipalThreadLocalName;
	private final Random _random = new Random();
	private RoleLocalService _roleLocalService;
	private final Map<SubscriptionType, Integer> _subscriptionTypeCounts =
		new HashMap<>();
	private UserAdvisor _userAdvisor;

}