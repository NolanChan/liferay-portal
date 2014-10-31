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

package com.liferay.socialnetworking.social;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.model.BaseSocialRequestInterpreter;
import com.liferay.portlet.social.model.SocialRelationConstants;
import com.liferay.portlet.social.model.SocialRequest;
import com.liferay.portlet.social.model.SocialRequestFeedEntry;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;
import com.liferay.portlet.social.service.SocialRelationLocalServiceUtil;
import com.liferay.socialnetworking.friends.social.FriendsActivityKeys;
import com.liferay.socialnetworking.friends.social.FriendsRequestKeys;

/**
 * @author Adolfo Pérez
 */
public class BaseSocialNetworkingRequestInterpreter
	extends BaseSocialRequestInterpreter {

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

		// Title

		String title = StringPool.BLANK;

		if (requestType == FriendsRequestKeys.ADD_FRIEND) {
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

			title = themeDisplay.translate(
				"request-social-networking-summary-add-friend",
				new Object[] {creatorUserNameURL});
		}

		// Body

		String body = StringPool.BLANK;

		String extraData = request.getExtraData();

		try {
			JSONObject extraDataJSONObject = JSONFactoryUtil.createJSONObject(
				extraData);

			body = extraDataJSONObject.getString("addFriendMessage");

			if (Validator.isNotNull(body)) {
				body = StringUtil.quote(body);
				body = HtmlUtil.escape(body);
			}
		}
		catch (JSONException jsone) {
			_log.error("Unable to create JSON object from " + extraData);
		}

		return new SocialRequestFeedEntry(title, body);
	}

	@Override
	protected boolean doProcessConfirmation(
		SocialRequest request, ThemeDisplay themeDisplay) {

		try {
			SocialRelationLocalServiceUtil.addRelation(
				request.getUserId(), request.getReceiverUserId(),
				SocialRelationConstants.TYPE_BI_FRIEND);

			SocialActivityLocalServiceUtil.addActivity(
				request.getUserId(), 0, User.class.getName(),
				request.getUserId(), FriendsActivityKeys.ADD_FRIEND,
				StringPool.BLANK, request.getReceiverUserId());
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return true;
	}

	private static final String[] _CLASS_NAMES = new String[] {
		User.class.getName()
	};

	private static Log _log = LogFactoryUtil.getLog(
		BaseSocialNetworkingRequestInterpreter.class.getName());

}