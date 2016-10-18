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

import com.liferay.osb.lcs.model.LCSClusterEntry;
import com.liferay.osb.lcs.model.LCSInvitation;
import com.liferay.osb.lcs.model.LCSProject;
import com.liferay.osb.lcs.service.LCSClusterEntryLocalService;
import com.liferay.osb.lcs.service.LCSInvitationLocalService;
import com.liferay.osb.lcs.service.LCSProjectLocalService;
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
import com.liferay.portal.kernel.service.UserLocalService;
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
@Component(
	immediate = true, service = CheckStaleLCSInvitationsMessageListener.class
)
public class CheckStaleLCSInvitationsMessageListener
	extends BaseSchedulerEntryMessageListener {

	@Reference(unbind = "-")
	public void setLCSClusterEntryLocalService(
		LCSClusterEntryLocalService lcsClusterEntryService) {

		_lcsClusterEntryLocalService = lcsClusterEntryService;
	}

	@Reference(unbind = "-")
	public void setLCSInvitationLocalServiceService(
		LCSInvitationLocalService lcsInvitationLocalService) {

		_lcsInvitationLocalService = lcsInvitationLocalService;
	}

	@Reference(unbind = "-")
	public void setLCSProjectLocalService(
		LCSProjectLocalService lcsProjectLocalService) {

		_lcsProjectLocalService = lcsProjectLocalService;
	}

	@Reference(unbind = "-")
	public void setUserLocalService(UserLocalService userLocalService) {
		_userLocalService = userLocalService;
	}

	@Activate
	protected void activate() {
		schedulerEntryImpl.setTrigger(
			TriggerFactoryUtil.createTrigger(
				getEventListenerClass(), getEventListenerClass(), 60,
				TimeUnit.MINUTE));

		_schedulerEngineHelper.register(
			this, schedulerEntryImpl, DestinationNames.SCHEDULER_DISPATCH);
	}

	@Deactivate
	protected void deactivate() {
		_schedulerEngineHelper.unregister(this);
	}

	protected void deleteLCSInvitation(LCSInvitation lcsInvitation)
		throws PortalException {

		_lcsInvitationLocalService.deleteLCSInvitation(
			lcsInvitation.getLcsInvitationId());

		if (_log.isInfoEnabled()) {
			_log.info(
				"Deleted stale LCS invitation for " +
					lcsInvitation.getEmailAddress());
		}
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		if (_log.isInfoEnabled()) {
			_log.info(
				"Check for stale LCS invitations since " +
					_dateFormatDateTime.format(_lastCheckDate));
		}

		_lastCheckDate = new Date();

		ActionableDynamicQuery actionableDynamicQuery =
			_lcsInvitationLocalService.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<LCSInvitation>() {

				@Override
				public void performAction(LCSInvitation lcsInvitation)
					throws PortalException {

					LCSProject lcsProject =
						_lcsProjectLocalService.fetchLCSProject(
							lcsInvitation.getLcsProjectId());

					if ((lcsProject == null) || lcsProject.isArchived()) {
						deleteLCSInvitation(lcsInvitation);

						return;
					}

					User user = _userLocalService.fetchUser(
						lcsInvitation.getUserId());

					if (user == null) {
						deleteLCSInvitation(lcsInvitation);

						return;
					}

					if (lcsInvitation.getLcsClusterEntryId() == 0) {
						return;
					}

					LCSClusterEntry lcsClusterEntry =
						_lcsClusterEntryLocalService.fetchLCSClusterEntry(
							lcsInvitation.getLcsClusterEntryId());

					if (lcsClusterEntry == null) {
						deleteLCSInvitation(lcsInvitation);
					}
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
		CheckStaleLCSInvitationsMessageListener.class);

	private final Format _dateFormatDateTime =
		FastDateFormatFactoryUtil.getSimpleDateFormat(
			"MMM d, " + "yyyy - hh:mm:ss");
	private Date _lastCheckDate = new Date();
	private LCSClusterEntryLocalService _lcsClusterEntryLocalService;
	private LCSInvitationLocalService _lcsInvitationLocalService;
	private LCSProjectLocalService _lcsProjectLocalService;
	private SchedulerEngineHelper _schedulerEngineHelper;
	private UserLocalService _userLocalService;

}