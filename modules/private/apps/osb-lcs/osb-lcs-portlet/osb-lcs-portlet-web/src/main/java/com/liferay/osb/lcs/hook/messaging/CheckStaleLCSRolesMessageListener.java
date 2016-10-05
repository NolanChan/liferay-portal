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

import com.liferay.osb.lcs.constants.LCSRoleConstants;
import com.liferay.osb.lcs.model.LCSProject;
import com.liferay.osb.lcs.model.LCSRole;
import com.liferay.osb.lcs.osbportlet.service.OSBPortletServiceUtil;
import com.liferay.osb.lcs.service.LCSNotificationLocalServiceUtil;
import com.liferay.osb.lcs.service.LCSProjectLocalServiceUtil;
import com.liferay.osb.lcs.service.LCSRoleLocalServiceUtil;
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
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;

import java.text.Format;

import java.util.Date;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Igor Beslic
 * @author Matija Petanjek
 */
@Component(immediate = true, service = CheckStaleLCSRolesMessageListener.class)
public class CheckStaleLCSRolesMessageListener
	extends BaseSchedulerEntryMessageListener {

	@Activate
	protected void activate() {
		schedulerEntryImpl.setTrigger(
			TriggerFactoryUtil.createTrigger(
				getEventListenerClass(), getEventListenerClass(), 30,
				TimeUnit.MINUTE));

		_schedulerEngineHelper.register(
			this, schedulerEntryImpl, DestinationNames.SCHEDULER_DISPATCH);
	}

	@Deactivate
	protected void deactivate() {
		_schedulerEngineHelper.unregister(this);
	}

	protected void deleteLCSRole(LCSRole lcsRole, User user)
		throws PortalException {

		LCSRoleLocalServiceUtil.deleteLCSRole(lcsRole.getLcsRoleId());

		if (_log.isInfoEnabled()) {
			if (user != null) {
				_log.info(
					"Deleted stale LCS role for " + user.getEmailAddress());
			}
			else {
				_log.info("Deleted LCS role " + lcsRole.getLcsRoleId());
			}
		}
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		if (_log.isInfoEnabled()) {
			_log.info(
				"Check for stale LCS roles since " +
					_dateFormatDateTime.format(_lastCheckDate));
		}

		_lastCheckDate = new Date();

		ActionableDynamicQuery actionableDynamicQuery =
			new LCSRoleLocalServiceUtil().getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<LCSRole>() {

				@Override
				public void performAction(LCSRole lcsRole)
					throws PortalException {

					User user = UserLocalServiceUtil.fetchUser(
						lcsRole.getUserId());

					if (user == null) {
						deleteLCSRole(lcsRole, user);

						return;
					}

					LCSProject lcsProject =
						LCSProjectLocalServiceUtil.fetchLCSProject(
							lcsRole.getLcsProjectId());

					if ((lcsProject == null) || lcsProject.isArchived()) {
						deleteLCSRole(lcsRole, user);

						LCSNotificationLocalServiceUtil.
							deleteLCSProjectLCSNotification(
								lcsRole.getLcsProjectId());

						return;
					}

					if (OSBPortletServiceUtil.hasUserCorpProject(
							user.getUserId(), lcsProject.getCorpProjectId())) {

						return;
					}

					if (lcsRole.getRole() ==
							LCSRoleConstants.
								ROLE_LCS_ENVIRONMENT_MEMBERSHIP_PENDING_USER) {

						return;
					}

					deleteLCSRole(lcsRole, user);
				}

			});

		actionableDynamicQuery.performActions();
	}

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
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
		CheckStaleLCSRolesMessageListener.class);

	private final Format _dateFormatDateTime =
		FastDateFormatFactoryUtil.getSimpleDateFormat(
			"MMM d, " + "yyyy - hh:mm:ss");
	private Date _lastCheckDate = new Date();
	private SchedulerEngineHelper _schedulerEngineHelper;

}