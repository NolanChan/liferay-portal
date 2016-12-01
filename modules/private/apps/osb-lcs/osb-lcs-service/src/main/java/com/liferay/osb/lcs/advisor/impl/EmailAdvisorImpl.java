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
import com.liferay.osb.lcs.advisor.EmailAdvisor;
import com.liferay.osb.lcs.configuration.OSBLCSConfiguration;
import com.liferay.osb.lcs.constants.LCSRoleConstants;
import com.liferay.osb.lcs.email.EmailContext;
import com.liferay.osb.lcs.email.EmailTemplate;
import com.liferay.osb.lcs.internal.email.LCSClusterNodeClusterLinkFailedEmailTemplate;
import com.liferay.osb.lcs.internal.email.MembershipInvitationAcceptedEmailTemplate;
import com.liferay.osb.lcs.internal.email.MembershipInvitationEmailTemplate;
import com.liferay.osb.lcs.internal.email.MembershipRequestAcceptedEmailTemplate;
import com.liferay.osb.lcs.internal.email.MembershipRequestEmailTemplate;
import com.liferay.osb.lcs.internal.email.MonitoringUnavailableEmailTemplate;
import com.liferay.osb.lcs.internal.email.NewLCSPortletAvailableEmailTemplate;
import com.liferay.osb.lcs.internal.email.NewPatchAvailableEmailTemplate;
import com.liferay.osb.lcs.internal.email.NewPatchingToolAvailableEmailTemplate;
import com.liferay.osb.lcs.internal.email.NewProjectMemberEmailTemplate;
import com.liferay.osb.lcs.internal.email.PatchingToolUnavailableEmailTemplate;
import com.liferay.osb.lcs.internal.email.ServerManuallyShutdownEmailTemplate;
import com.liferay.osb.lcs.internal.email.ServerUnexpectedlyShutdownEmailTemplate;
import com.liferay.osb.lcs.model.LCSRole;
import com.liferay.osb.lcs.service.LCSNotificationAuditEntryLocalServiceUtil;
import com.liferay.osb.lcs.service.LCSRoleLocalServiceUtil;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.SubscriptionSender;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Matija Petanjek
 */
@Component(immediate = true, service = EmailAdvisor.class)
public class EmailAdvisorImpl implements EmailAdvisor {

	@Override
	public void sendEmail(EmailContext emailContext) throws PortalException {
		EmailTemplate emailTemplate = _getEmailTemplate(emailContext);

		User toUser = emailContext.getUser();

		Object[] mailIds = {emailContext.getLCSProjectId(), toUser.getUserId()};

		sendEmail(
			emailTemplate.getSubjectMap(), emailTemplate.getBodyMap(),
			emailTemplate.getContextAttributes(), emailTemplate.getPopPrefix(),
			mailIds, emailContext.getEmailAddress(), toUser);

		LCSEventType lcsEventType = emailContext.getLCSEventType();

		if ((lcsEventType == LCSEventType.NEW_MEMBERSHIP_INVITATION) ||
			(lcsEventType == LCSEventType.MEMBERSHIP_REQUEST_ACCEPTED)) {

			return;
		}

		long lcsCLusterNodeId =
			emailContext.getLCSClusterNode().getLcsClusterNodeId();

		if (_log.isInfoEnabled()) {
			_log.info(
				lcsEventType.name() + " notification sent for node " +
					lcsCLusterNodeId);
		}

		LCSNotificationAuditEntryLocalServiceUtil.addLCSNotificationAuditEntry(
			toUser.getUserId(), lcsCLusterNodeId, lcsEventType.getType());
	}

	@Override
	public void sendToLCSProjectAdminsEmail(EmailContext emailContext)
		throws PortalException {

		EmailTemplate emailTemplate = _getEmailTemplate(emailContext);

		List<User> lcsProjectAdministrators = _getLCSProjectAdmins(
			emailContext);

		for (User lcsProjectAdministrator : lcsProjectAdministrators) {
			Object[] contextAttributes = emailTemplate.getContextAttributes();

			contextAttributes[5] = translate(
				emailContext, "dear-x", lcsProjectAdministrator.getFullName());

			Object[] mailIds = {
				emailContext.getLCSProjectId(),
				lcsProjectAdministrator.getUserId()
			};

			sendEmail(
				emailTemplate.getSubjectMap(), emailTemplate.getBodyMap(),
				contextAttributes, emailTemplate.getPopPrefix(), mailIds, null,
				lcsProjectAdministrator);
		}
	}

	@Reference(bind = "-", unbind = "-")
	public void setUserLocalService(UserLocalService userLocalService) {
		_userLocalService = userLocalService;
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

	protected void sendEmail(
		Map<Locale, String> subjectMap, Map<Locale, String> bodyMap,
		Object[] contextAttributes, String popPortletPrefix, Object[] mailIds,
		String emailAddress, User toUser) {

		if (!_osbLCSConfiguration.sendingEmailsEnabled()) {
			if (_log.isWarnEnabled()) {
				_log.warn("Disabled sending emails");
			}

			return;
		}

		SubscriptionSender subscriptionSender = new SubscriptionSender();

		subscriptionSender.setCompanyId(toUser.getCompanyId());
		subscriptionSender.setContextAttributes(contextAttributes);
		subscriptionSender.setFrom(
			_osbLCSConfiguration.osbLcsPortletMembersEmailFromAddress(),
			_osbLCSConfiguration.osbLcsPortletMembersEmailFromName());
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setLocalizedBodyMap(bodyMap);
		subscriptionSender.setLocalizedSubjectMap(subjectMap);
		subscriptionSender.setMailId(popPortletPrefix, mailIds);
		subscriptionSender.setUserId(toUser.getUserId());

		if (Validator.isNull(emailAddress)) {
			subscriptionSender.addRuntimeSubscribers(
				toUser.getEmailAddress(), toUser.getFullName());
		}
		else {
			subscriptionSender.addRuntimeSubscribers(
				emailAddress, emailAddress);
		}

		subscriptionSender.flushNotificationsAsync();
	}

	protected String translate(
		EmailContext emailContext, String pattern, Object... arguments) {

		return LanguageUtil.format(
			emailContext.getLocale(), pattern, arguments);
	}

	private EmailTemplate _getEmailTemplate(EmailContext emailContext) {
		if (emailContext.getLCSEventType() ==
				LCSEventType.LCS_CLUSTER_NODE_CLUSTER_LINK_FAILED) {

			return new LCSClusterNodeClusterLinkFailedEmailTemplate(
				emailContext);
		}
		else if ((emailContext.getLCSEventType() ==
					LCSEventType.MEMBERSHIP_INVITATION_ACCEPTED) &&
				 _isEmailMembershipInvitationAcceptedEnabled()) {

			return new MembershipInvitationAcceptedEmailTemplate(emailContext);
		}
		else if ((emailContext.getLCSEventType() ==
					LCSEventType.MEMBERSHIP_REQUEST_ACCEPTED) &&
				 _isEmailMembershipRequestAcceptedEnabled()) {

			return new MembershipRequestAcceptedEmailTemplate(emailContext);
		}
		else if (emailContext.getLCSEventType() ==
					LCSEventType.MONITORING_UNAVAILABLE) {

			return new MonitoringUnavailableEmailTemplate(emailContext);
		}
		else if (emailContext.getLCSEventType() ==
					LCSEventType.NEW_LCS_PORTLET_AVAILABLE) {

			return new NewLCSPortletAvailableEmailTemplate(emailContext);
		}
		else if ((emailContext.getLCSEventType() ==
					LCSEventType.NEW_MEMBERSHIP_INVITATION) &&
				 _isEmailMembershipInvitationEnabled()) {

			return new MembershipInvitationEmailTemplate(emailContext);
		}
		else if ((emailContext.getLCSEventType() ==
					LCSEventType.NEW_MEMBERSHIP_REQUEST) &&
				 _isEmailMembershipRequestEnabled()) {

			return new MembershipRequestEmailTemplate(emailContext);
		}
		else if (emailContext.getLCSEventType() ==
					LCSEventType.NEW_PATCH_AVAILABLE) {

			return new NewPatchAvailableEmailTemplate(emailContext);
		}
		else if (emailContext.getLCSEventType() ==
					LCSEventType.NEW_PATCHING_TOOL_AVAILABLE) {

			return new NewPatchingToolAvailableEmailTemplate(emailContext);
		}
		else if (emailContext.getLCSEventType() ==
					LCSEventType.NEW_PROJECT_MEMBER) {

			return new NewProjectMemberEmailTemplate(emailContext);
		}
		else if (emailContext.getLCSEventType() ==
					LCSEventType.PATCHING_TOOL_UNAVAILABLE) {

			return new PatchingToolUnavailableEmailTemplate(emailContext);
		}
		else if (emailContext.getLCSEventType() ==
					LCSEventType.SERVER_MANUALLY_SHUTDOWN) {

			return new ServerManuallyShutdownEmailTemplate(emailContext);
		}
		else if (emailContext.getLCSEventType() ==
					LCSEventType.SERVER_UNEXPECTEDLY_SHUTDOWN) {

			return new ServerUnexpectedlyShutdownEmailTemplate(emailContext);
		}

		throw new UnsupportedOperationException();
	}

	private List<User> _getLCSProjectAdmins(EmailContext emailContext)
		throws PortalException {

		List<User> lcsProjectAdmins = new ArrayList<>();

		List<LCSRole> lcsProjectLCSRoles =
			LCSRoleLocalServiceUtil.getLCSProjectLCSRoles(
				emailContext.getLCSProjectId(),
				LCSRoleConstants.ROLE_LCS_ADMINISTRATOR);

		for (LCSRole lcsProjectLCSRole : lcsProjectLCSRoles) {
			lcsProjectAdmins.add(
				_userLocalService.getUserById(lcsProjectLCSRole.getUserId()));
		}

		return lcsProjectAdmins;
	}

	private boolean _isEmailMembershipInvitationAcceptedEnabled() {
		return _osbLCSConfiguration.
			osbLcsPortletMembersEmailMembershipInvitationAcceptedEnabled();
	}

	private boolean _isEmailMembershipInvitationEnabled() {
		return _osbLCSConfiguration.
			osbLcsPortletMembersEmailMembershipInvitationEnabled();
	}

	private boolean _isEmailMembershipRequestAcceptedEnabled() {
		return _osbLCSConfiguration.
			osbLcsPortletMembersEmailMembershipRequestAcceptedEnabled();
	}

	private boolean _isEmailMembershipRequestEnabled() {
		return _osbLCSConfiguration.
			osbLcsPortletMembersEmailMembershipRequestEnabled();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EmailAdvisorImpl.class);

	private static volatile OSBLCSConfiguration _osbLCSConfiguration;

	private UserLocalService _userLocalService;

}