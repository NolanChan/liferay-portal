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

package com.liferay.osb.lcs.hook.messaging;

import com.liferay.expando.kernel.model.ExpandoTableConstants;
import com.liferay.expando.kernel.model.ExpandoValue;
import com.liferay.expando.kernel.service.ExpandoValueLocalService;
import com.liferay.osb.lcs.constants.LCSRoleConstants;
import com.liferay.osb.lcs.model.CorpProject;
import com.liferay.osb.lcs.model.LCSProject;
import com.liferay.osb.lcs.model.LCSRole;
import com.liferay.osb.lcs.osbportlet.service.OSBPortletService;
import com.liferay.osb.lcs.service.LCSClusterEntryLocalService;
import com.liferay.osb.lcs.service.LCSNotificationLocalService;
import com.liferay.osb.lcs.service.LCSProjectLocalService;
import com.liferay.osb.lcs.service.LCSRoleLocalService;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseSchedulerEntryMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import com.liferay.portal.kernel.scheduler.TriggerFactoryUtil;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.text.Format;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Igor Beslic
 * @author Matija Petanjek
 */
@Component(
	immediate = true, service = CheckCorruptedLCSProjectMessageListener.class
)
public class CheckCorruptedLCSProjectMessageListener
	extends BaseSchedulerEntryMessageListener {

	@Reference(unbind = "-")
	public void setClassNameLocalService(
		ClassNameLocalService classNameLocalService) {

		_classNameLocalService = classNameLocalService;
	}

	@Reference(unbind = "-")
	public void setExpandoValueLocalService(
		ExpandoValueLocalService expandoValueLocalService) {

		_expandoValueLocalService = expandoValueLocalService;
	}

	@Reference(unbind = "-")
	public void setLCSClusterEntryLocalService(
		LCSClusterEntryLocalService lcsClusterEntryLocalService) {

		_lcsClusterEntryLocalService = lcsClusterEntryLocalService;
	}

	@Reference(unbind = "-")
	public void setLCSNotificationLocalService(
		LCSNotificationLocalService lcsNotificationLocalService) {

		_lcsNotificationLocalService = lcsNotificationLocalService;
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

	@Activate
	protected void activate() {
		schedulerEntryImpl.setTrigger(
			TriggerFactoryUtil.createTrigger(
				getEventListenerClass(), getEventListenerClass(), 12,
				TimeUnit.HOUR));

		_schedulerEngineHelper.register(
			this, schedulerEntryImpl, DestinationNames.SCHEDULER_DISPATCH);
	}

	@Deactivate
	protected void deactivate() {
		_schedulerEngineHelper.unregister(this);
	}

	protected void deleteLCSProject(LCSProject lcsProject)
		throws PortalException {

		_lcsProjectLocalService.deleteLCSProject(lcsProject);

		_lcsClusterEntryLocalService.deleteLCSProjectClusters(
			lcsProject.getLcsProjectId());

		_lcsNotificationLocalService.deleteLCSProjectLCSNotification(
			lcsProject.getLcsProjectId());

		long companyId = PortalUtil.getDefaultCompanyId();
		long classNameId = _classNameLocalService.getClassNameId(User.class);

		List<ExpandoValue> expandoValues =
			_expandoValueLocalService.getColumnValues(
				companyId, classNameId,
				ExpandoTableConstants.DEFAULT_TABLE_NAME, "defaultLCSProjectId",
				String.valueOf(lcsProject.getLcsProjectId()), -1, -1);

		for (ExpandoValue expandoValue : expandoValues) {
			_expandoValueLocalService.deleteExpandoValue(expandoValue);
		}

		if (_log.isInfoEnabled()) {
			_log.info(
				"Deleted corrupted LCS project " +
					lcsProject.getLcsProjectId());
		}
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		if (_log.isInfoEnabled()) {
			_log.info(
				"Check for corrupted LCS projects since " +
					_dateFormatDateTime.format(_lastCheckDate));
		}

		_lastCheckDate = new Date();

		ActionableDynamicQuery actionableDynamicQuery =
			_lcsProjectLocalService.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<LCSProject>() {

			@Override
			public void performAction(LCSProject lcsProject)
				throws PortalException {

				CorpProject corpProject = _osbPortletService.fetchCorpProject(
					lcsProject.getCorpProjectId());

				if (corpProject == null) {
					deleteLCSProject(lcsProject);

					return;
				}

				int count = _lcsRoleLocalService.getLCSProjectLCSRolesCount(
					lcsProject.getLcsProjectId());

				if (count != 0) {
					List<LCSRole> lcsRoles =
						_lcsRoleLocalService.getLCSProjectLCSRoles(
							lcsProject.getLcsProjectId(),
							LCSRoleConstants.ROLE_LCS_ADMINISTRATOR);

					if (!lcsRoles.isEmpty()) {
						return;
					}
				}

				deleteLCSProject(lcsProject);
			}

		});

		actionableDynamicQuery.performActions();
	}

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	@Reference(unbind = "-")
	protected void setOsbPortletService(OSBPortletService osbPortletService) {
		_osbPortletService = osbPortletService;
	}

	@Reference(unbind = "-")
	protected void setSchedulerEngineHelper(
		SchedulerEngineHelper schedulerEngineHelper) {

		_schedulerEngineHelper = schedulerEngineHelper;
	}

	@Reference(unbind = "-")
	protected void setTriggerFactory(TriggerFactory triggerFactory) {
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CheckCorruptedLCSProjectMessageListener.class);

	private ClassNameLocalService _classNameLocalService;
	private final Format _dateFormatDateTime =
		FastDateFormatFactoryUtil.getSimpleDateFormat("MMM d, yyyy - hh:mm:ss");
	private ExpandoValueLocalService _expandoValueLocalService;
	private Date _lastCheckDate = new Date();
	private LCSClusterEntryLocalService _lcsClusterEntryLocalService;
	private LCSNotificationLocalService _lcsNotificationLocalService;
	private LCSProjectLocalService _lcsProjectLocalService;
	private LCSRoleLocalService _lcsRoleLocalService;
	private OSBPortletService _osbPortletService;
	private SchedulerEngineHelper _schedulerEngineHelper;

}