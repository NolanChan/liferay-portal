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

package com.liferay.item.selector.taglib.servlet.taglib;

import com.liferay.item.selector.taglib.servlet.ServletContextUtil;
import com.liferay.item.selector.taglib.servlet.item.selector.ItemSelectorUtil;
import com.liferay.item.selector.web.constants.ItemSelectorPortletKeys;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.RequestBackedPortletURLFactory;
import com.liferay.portlet.RequestBackedPortletURLFactoryUtil;
import com.liferay.portlet.usersadmin.search.GroupSearch;
import com.liferay.taglib.util.IncludeTag;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

/**
 * @author Roberto Díaz
 */
public class GroupSelectorTag extends IncludeTag {

	public void setGroupCount(int groupCount) {
		_groupCount = groupCount;
	}

	public void setGroups(List<Group> groups) {
		_groups = groups;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		servletContext = ServletContextUtil.getServletContext();
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_groupCount = 0;
		_groups = null;
	}

	protected long[] getClassNameIds() {
		return new long[] {
			ClassNameLocalServiceUtil.getClassNameId(Company.class),
			ClassNameLocalServiceUtil.getClassNameId(Group.class),
			ClassNameLocalServiceUtil.getClassNameId(Organization.class)
		};
	}

	protected int getGroupCount(HttpServletRequest request) {
		if ((_groupCount > 0) || (_groups != null)) {
			return _groupCount;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		User user = themeDisplay.getUser();

		String keywords = ParamUtil.getString(request, "keywords");

		if (Validator.isNotNull(keywords)) {
			LinkedHashMap<String, Object> groupParams = new LinkedHashMap<>();

			groupParams.put("site", Boolean.TRUE);
			groupParams.put("usersGroups", Long.valueOf(user.getUserId()));

			return GroupLocalServiceUtil.searchCount(
				themeDisplay.getCompanyId(), getClassNameIds(), keywords,
				groupParams);
		}

		try {
			List<Group> groups = user.getMySiteGroups(null, QueryUtil.ALL_POS);

			return groups.size();
		}
		catch (Exception e) {
			return 0;
		}
	}

	protected List<Group> getGroups(HttpServletRequest request) {
		if (_groups != null) {
			return _groups;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		PortletRequest portletRequest = (PortletRequest)request.getAttribute(
			JavaConstants.JAVAX_PORTLET_REQUEST);

		User user = themeDisplay.getUser();

		String keywords = ParamUtil.getString(request, "keywords");

		RequestBackedPortletURLFactory requestBackedPortletURLFactory =
			RequestBackedPortletURLFactoryUtil.create(request);

		PortletURL viewGroupSelectorURL =
			requestBackedPortletURLFactory.createRenderURL(
				ItemSelectorPortletKeys.ITEM_SELECTOR);

		SearchContainer searchContainer = new GroupSearch(
			portletRequest, viewGroupSelectorURL);

		if (Validator.isNotNull(keywords)) {
			LinkedHashMap<String, Object> groupParams = new LinkedHashMap<>();

			groupParams.put("site", Boolean.TRUE);
			groupParams.put("usersGroups", Long.valueOf(user.getUserId()));

			return GroupLocalServiceUtil.search(
				themeDisplay.getCompanyId(), getClassNameIds(), keywords,
				groupParams, searchContainer.getStart(),
				searchContainer.getEnd(), null);
		}

		try {
			List<Group> groups = user.getMySiteGroups(null, QueryUtil.ALL_POS);

			return ListUtil.subList(
				groups, searchContainer.getStart(), searchContainer.getEnd());
		}
		catch (Exception e) {
			return new ArrayList<>();
		}
	}

	@Override
	protected String getPage() {
		return "/group_selector/page.jsp";
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute(
			"liferay-item-selector:group-selector:groupCount",
			getGroupCount(request));
		request.setAttribute(
			"liferay-item-selector:group-selector:groups", getGroups(request));
		request.setAttribute(
			"liferay-item-selector:group-selector:itemSelector",
			ItemSelectorUtil.getItemSelector());
	}

	private int _groupCount;
	private List<Group> _groups = null;

}