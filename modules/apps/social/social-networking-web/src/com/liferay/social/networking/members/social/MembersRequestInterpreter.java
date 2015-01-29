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

package com.liferay.social.networking.members.social;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalService;
import com.liferay.portal.service.OrganizationLocalService;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.model.BaseSocialRequestInterpreter;
import com.liferay.portlet.social.model.SocialRequest;
import com.liferay.portlet.social.model.SocialRequestConstants;
import com.liferay.portlet.social.model.SocialRequestFeedEntry;
import com.liferay.portlet.social.model.SocialRequestInterpreter;
import com.liferay.portlet.social.service.SocialActivityLocalService;
import com.liferay.social.networking.util.PortletKeys;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Wing Shun Chan
 * @author M Murali Krishna Reddy
 */
@Component(
	property = {"javax.portlet.name=" + PortletKeys.MEMBERS},
	service = SocialRequestInterpreter.class
)
public class MembersRequestInterpreter extends BaseSocialRequestInterpreter {

	@Override
	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	@Override
	protected SocialRequestFeedEntry doInterpret(
			SocialRequest request, ThemeDisplay themeDisplay)
		throws Exception {

		String creatorUserName = getUserName(request.getUserId(), themeDisplay);

		User creatorUser = UserLocalServiceUtil.getUserById(
			request.getUserId());

		int requestType = request.getType();

		Group group = null;

		String className = request.getClassName();

		if (className.equals(Group.class.getName())) {
			group = _groupLocalService.getGroup(request.getClassPK());
		}
		else {
			Organization organization =
				_organizationLocalService.getOrganization(request.getClassPK());

			group = organization.getGroup();
		}

		// Title

		String title = StringPool.BLANK;

		if (requestType == MembersRequestKeys.ADD_MEMBER) {
			StringBuilder sb = new StringBuilder();

			sb.append("<a href=\"");
			sb.append(themeDisplay.getPortalURL());
			sb.append(themeDisplay.getPathFriendlyURLPublic());
			sb.append(StringPool.SLASH);
			sb.append(creatorUser.getScreenName());
			sb.append("/profile\">");
			sb.append(creatorUserName);
			sb.append("</a>");

			String creatorUserNameURL = sb.toString();

			sb = new StringBuilder();

			sb.append("<a href=\"");
			sb.append(themeDisplay.getPortalURL());
			sb.append(themeDisplay.getPathFriendlyURLPublic());
			sb.append(group.getFriendlyURL());
			sb.append("/profile\">");
			sb.append(group.getDescriptiveName(themeDisplay.getLocale()));
			sb.append("</a>");

			String organizationNameURL = sb.toString();

			title = themeDisplay.translate(
				"request-social-networking-summary-join-organization",
				new Object[] {creatorUserNameURL, organizationNameURL});
		}

		// Body

		String body = StringPool.BLANK;

		return new SocialRequestFeedEntry(title, body);
	}

	@Override
	protected boolean doProcessConfirmation(
		SocialRequest request, ThemeDisplay themeDisplay) {

		try {
			String className = request.getClassName();

			if (className.equals(Group.class.getName())) {
				UserLocalServiceUtil.addGroupUsers(
					request.getClassPK(), new long[] {request.getUserId()});
			}
			else {
				UserLocalServiceUtil.addOrganizationUsers(
					request.getClassPK(), new long[] {request.getUserId()});
			}

			_socialActivityLocalService.addActivity(
				request.getUserId(), 0, className, request.getClassPK(),
				MembersActivityKeys.ADD_MEMBER, StringPool.BLANK, 0);

			processDuplicateRequestsFromUser(
				request, SocialRequestConstants.STATUS_PENDING);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return true;
	}

	@Reference(unbind = "-")
	protected void setGroupLocalService(GroupLocalService groupLocalService) {
		_groupLocalService = groupLocalService;
	}

	@Reference(unbind = "-")
	protected void setOrganizationLocalService(
		OrganizationLocalService organizationLocalService) {

		_organizationLocalService = organizationLocalService;
	}

	@Reference(unbind = "-")
	protected void setSocialActivityLocalService(
		SocialActivityLocalService socialActivityLocalService) {

		_socialActivityLocalService = socialActivityLocalService;
	}

	private static final String[] _CLASS_NAMES = new String[] {
		Group.class.getName(), Organization.class.getName()
	};

	private static final Log _log = LogFactoryUtil.getLog(
		MembersRequestInterpreter.class);

	private GroupLocalService _groupLocalService;
	private OrganizationLocalService _organizationLocalService;
	private SocialActivityLocalService _socialActivityLocalService;

}