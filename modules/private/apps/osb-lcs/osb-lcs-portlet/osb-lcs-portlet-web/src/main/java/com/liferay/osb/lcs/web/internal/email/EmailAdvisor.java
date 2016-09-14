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

package com.liferay.osb.lcs.web.internal.email;

import com.liferay.lcs.notification.LCSEventType;
import com.liferay.osb.lcs.admin.util.AdminUtil;
import com.liferay.osb.lcs.model.LCSRole;
import com.liferay.osb.lcs.model.LCSRoleConstants;
import com.liferay.osb.lcs.service.LCSNotificationAuditEntryLocalServiceUtil;
import com.liferay.osb.lcs.service.LCSRoleLocalServiceUtil;
import com.liferay.osb.lcs.util.PortletKeys;
import com.liferay.osb.lcs.util.PortletPropsValues;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.SubscriptionSender;
import com.liferay.util.ContentUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletPreferences;

/**
 * @author Matija Petanjek
 */
public class EmailAdvisor {

	public void sendEmail(EmailContext emailContext) throws PortalException {
		EmailTemplate emailTemplate = getEmailTemplate(emailContext);

		User toUser = emailContext.getUser();

		Object[] mailIds = {emailContext.getLCSProjectId(), toUser.getUserId()};

		sendEmail(
			emailTemplate.getSubjectMap(getPortletPreferences()),
			emailTemplate.getBodyMap(getPortletPreferences()),
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

	public void sendToLCSProjectAdminsEmail(EmailContext emailContext)
		throws PortalException {

		EmailTemplate emailTemplate = getEmailTemplate(emailContext);

		List<User> lcsProjectAdministrators = getLCSProjectAdmins(emailContext);

		for (User lcsProjectAdministrator : lcsProjectAdministrators) {
			Object[] contextAttributes = emailTemplate.getContextAttributes();

			contextAttributes[5] = emailContext.translate(
				"dear-x", lcsProjectAdministrator.getFullName());

			Object[] mailIds = {
				emailContext.getLCSProjectId(),
				lcsProjectAdministrator.getUserId()
			};

			sendEmail(
				emailTemplate.getSubjectMap(getPortletPreferences()),
				emailTemplate.getBodyMap(getPortletPreferences()),
				contextAttributes, emailTemplate.getPopPrefix(), mailIds, null,
				lcsProjectAdministrator);
		}
	}

	protected Map<Locale, String> getLocalizationMap(
		String templatePath, String parameter,
		PortletPreferences portletPreferences) {

		Map<Locale, String> map = LocalizationUtil.getLocalizationMap(
			portletPreferences, parameter);

		Locale defaultLocale = LocaleUtil.getDefault();

		String defaultValue = map.get(defaultLocale);

		if (Validator.isNotNull(defaultValue)) {
			return map;
		}

		map.put(defaultLocale, ContentUtil.get(templatePath));

		return map;
	}

	protected PortletPreferences getPortletPreferences() {
		long companyId = CompanyConstants.SYSTEM;
		long ownerId = CompanyConstants.SYSTEM;
		int ownerType = PortletKeys.PREFS_OWNER_TYPE_COMPANY;
		long plid = PortletKeys.PREFS_PLID_SHARED;
		String portletId = PortletKeys.MEMBERS;
		String defaultPreferences = null;

		return PortletPreferencesLocalServiceUtil.getPreferences(
			companyId, ownerId, ownerType, plid, portletId, defaultPreferences);
	}

	protected void sendEmail(
		Map<Locale, String> subjectMap, Map<Locale, String> bodyMap,
		Object[] contextAttributes, String popPortletPrefix, Object[] mailIds,
		String emailAddress, User toUser) {

		try {
			if (!AdminUtil.isSendingEmailsEnabled()) {
				if (_log.isWarnEnabled()) {
					_log.warn("Disabled sending emails");
				}

				return;
			}
		}
		catch (SystemException se) {
			_log.error(se.getMessage(), se);

			return;
		}

		SubscriptionSender subscriptionSender = new SubscriptionSender();

		subscriptionSender.setCompanyId(toUser.getCompanyId());
		subscriptionSender.setContextAttributes(contextAttributes);
		subscriptionSender.setFrom(
			PortletPropsValues.OSB_LCS_PORTLET_MEMBERS_EMAIL_FROM_ADDRESS,
			PortletPropsValues.OSB_LCS_PORTLET_MEMBERS_EMAIL_FROM_NAME);
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

	private EmailTemplate getEmailTemplate(EmailContext emailContext) {
		if (emailContext.getLCSEventType() ==
				LCSEventType.LCS_CLUSTER_NODE_CLUSTER_LINK_FAILED) {

			return new LCSClusterNodeClusterLinkFailedEmailTemplate(
				emailContext);
		}
		else if ((emailContext.getLCSEventType() ==
					LCSEventType.MEMBERSHIP_INVITATION_ACCEPTED) &&
				 _EMAIL_MEMBERSHIP_INVITATION_ACCEPTED_ENABLED) {

			return new MembershipInvitationAcceptedEmailTemplate(emailContext);
		}
		else if ((emailContext.getLCSEventType() ==
					LCSEventType.MEMBERSHIP_REQUEST_ACCEPTED) &&
				 _EMAIL_MEMBERSHIP_REQUEST_ACCEPTED_ENABLED) {

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
				 _EMAIL_MEMBERSHIP_INVITATION_ENABLED) {

			return new MembershipInvitationEmailTemplate(emailContext);
		}
		else if ((emailContext.getLCSEventType() ==
					LCSEventType.NEW_MEMBERSHIP_REQUEST) &&
				 _EMAIL_MEMBERSHIP_REQUEST_ENABLED) {

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

	private List<User> getLCSProjectAdmins(EmailContext emailContext)
		throws PortalException {

		List<User> lcsProjectAdmins = new ArrayList<>();

		List<LCSRole> lcsProjectLCSRoles =
			LCSRoleLocalServiceUtil.getLCSProjectLCSRoles(
				emailContext.getLCSProjectId(),
				LCSRoleConstants.ROLE_LCS_ADMINISTRATOR);

		for (LCSRole lcsProjectLCSRole : lcsProjectLCSRoles) {
			lcsProjectAdmins.add(
				UserLocalServiceUtil.getUserById(
					lcsProjectLCSRole.getUserId()));
		}

		return lcsProjectAdmins;
	}

	private static final boolean _EMAIL_MEMBERSHIP_INVITATION_ACCEPTED_ENABLED =
		PortletPropsValues.
			OSB_LCS_PORTLET_MEMBERS_EMAIL_MEMBERSHIP_INVITATION_ACCEPTED_ENABLED;

	private static final boolean _EMAIL_MEMBERSHIP_INVITATION_ENABLED =
		PortletPropsValues.
			OSB_LCS_PORTLET_MEMBERS_EMAIL_MEMBERSHIP_INVITATION_ENABLED;

	private static final boolean _EMAIL_MEMBERSHIP_REQUEST_ACCEPTED_ENABLED =
		PortletPropsValues.
			OSB_LCS_PORTLET_MEMBERS_EMAIL_MEMBERSHIP_REQUEST_ACCEPTED_ENABLED;

	private static final boolean _EMAIL_MEMBERSHIP_REQUEST_ENABLED =
		PortletPropsValues.
			OSB_LCS_PORTLET_MEMBERS_EMAIL_MEMBERSHIP_REQUEST_ENABLED;

	private static final Log _log = LogFactoryUtil.getLog(EmailAdvisor.class);

}