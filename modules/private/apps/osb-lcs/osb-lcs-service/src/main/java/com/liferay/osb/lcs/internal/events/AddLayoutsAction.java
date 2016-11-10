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

import com.liferay.osb.lcs.util.ActionKeys;
import com.liferay.osb.lcs.util.PortletKeys;
import com.liferay.osb.lcs.util.PortletPropsValues;
import com.liferay.osb.model.CorpProject;
import com.liferay.portal.NoSuchLayoutException;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.SimpleAction;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutConstants;
import com.liferay.portal.model.LayoutSet;
import com.liferay.portal.model.LayoutTypePortlet;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.LayoutSetLocalServiceUtil;
import com.liferay.portal.service.ResourcePermissionServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.util.portlet.PortletProps;

import java.util.List;

/**
 * @author Igor Beslic
 * @see    com.liferay.portal.events.AddDefaultLayoutPrototypesAction
 * @see    com.liferay.portal.events.AddDefaultLayoutSetPrototypesAction
 */
public class AddLayoutsAction extends SimpleAction {

	@Override
	public void run(String[] ids) throws ActionException {
		long companyId = GetterUtil.getLong(ids[0]);

		try {
			User user = getUser(companyId);

			PermissionChecker permissionChecker =
				PermissionCheckerFactoryUtil.create(user);

			PermissionThreadLocal.setPermissionChecker(permissionChecker);

			doRun(companyId, user.getUserId());
		}
		catch (Exception e) {
			throw new ActionException(e);
		}
		finally {
			PermissionThreadLocal.setPermissionChecker(null);
		}
	}

	protected Layout addLayout(
			LayoutSet layoutSet, String name, String friendlyURL,
			String layouteTemplateId)
		throws Exception {

		Group group = layoutSet.getGroup();

		ServiceContext serviceContext = new ServiceContext();

		Layout layout = LayoutLocalServiceUtil.addLayout(
			group.getCreatorUserId(), group.getGroupId(),
			layoutSet.isPrivateLayout(),
			LayoutConstants.DEFAULT_PARENT_LAYOUT_ID, name, StringPool.BLANK,
			StringPool.BLANK, LayoutConstants.TYPE_PORTLET, false, friendlyURL,
			serviceContext);

		LayoutTypePortlet layoutTypePortlet =
			(LayoutTypePortlet)layout.getLayoutType();

		layoutTypePortlet.setLayoutTemplateId(0, layouteTemplateId, false);

		LayoutLocalServiceUtil.updateLayout(layout);

		return layout;
	}

	protected String addPortletId(
			long userId, Layout layout, String portletId, String columnId)
		throws Exception {

		LayoutTypePortlet layoutTypePortlet =
			(LayoutTypePortlet)layout.getLayoutType();

		portletId = layoutTypePortlet.addPortletId(
			userId, portletId, columnId, -1, false);

		LayoutLocalServiceUtil.updateLayout(layout);

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Added portlet " + portletId + " to layout " +
					layout.getPlid());
		}

		return portletId;
	}

	protected void checkCorpProjectClassName() throws Exception {
		ClassNameLocalServiceUtil.addClassName(CorpProject.class.getName());
	}

	protected void checkLayoutSet(
			long userId, Group group, boolean privateLayout)
		throws Exception {

		LayoutSet layoutSet = LayoutSetLocalServiceUtil.getLayoutSet(
			group.getGroupId(), privateLayout);

		String[] names = PortletPropsValues.OSB_LCS_PORTAL_PUBLIC_LAYOUT_NAMES;

		if (privateLayout) {
			names = PortletPropsValues.OSB_LCS_PORTAL_PRIVATE_LAYOUT_NAMES;
		}

		for (int i = 0; i < names.length; i++) {
			String name = names[i];

			String friendlyURL =
				StringPool.SLASH + StringUtil.toLowerCase(name);

			String normalizedFriendlyURL = FriendlyURLNormalizerUtil.normalize(
				friendlyURL);

			Layout layout = null;

			try {
				layout = LayoutLocalServiceUtil.getFriendlyURLLayout(
					group.getGroupId(), privateLayout, normalizedFriendlyURL);
			}
			catch (NoSuchLayoutException nsle) {
				String layoutTemplateId =
					PortletPropsValues.OSB_LCS_PORTAL_PUBLIC_LAYOUT_TEMPLATE_ID;

				if (privateLayout) {
					layoutTemplateId =
						PortletPropsValues.
							OSB_LCS_PORTAL_PRIVATE_LAYOUT_TEMPLATE_ID;
				}

				layout = addLayout(
					layoutSet, name, friendlyURL, layoutTemplateId);

				if (_log.isDebugEnabled()) {
					_log.debug(
						"Added layout " + layout.getPlid() +
							" with friendly URL " + friendlyURL);
				}
			}

			LayoutTypePortlet layoutTypePortlet =
				(LayoutTypePortlet)layout.getLayoutType();

			String[] portletIds = null;

			if (privateLayout) {
				portletIds = PortletProps.getArray(
					"osb.lcs.portal.private.layout." + i + ".portlets");
			}
			else {
				portletIds = PortletProps.getArray(
					"osb.lcs.portal.public.layout." + i + ".portlets");
			}

			for (String portletId : portletIds) {
				if (!layoutTypePortlet.hasPortletId(portletId)) {
					addPortletId(userId, layout, portletId, "column-1");
				}
			}

			if (!privateLayout && name.equals("Welcome")) {
				setRolePermissions(layout);

				if (_log.isInfoEnabled()) {
					_log.info(
						"Update permissions for layout " + layout.getPlid());
				}
			}
		}
	}

	protected void doRun(long companyId, long userId) throws Exception {
		Group group = GroupLocalServiceUtil.getGroup(
			companyId, GroupConstants.GUEST);

		checkCorpProjectClassName();

		checkLayoutSet(userId, group, true);
		checkLayoutSet(userId, group, false);

		updateLookAndFeel(companyId);

		updateWebGuestHomeLayout(group.getGroupId(), userId);
	}

	protected String[] getLayoutPortletIds(Layout layout) throws Exception {
		LayoutTypePortlet layoutTypePortlet =
			(LayoutTypePortlet)layout.getLayoutType();

		List<Portlet> portlets = layoutTypePortlet.getAllPortlets();

		String[] portletIds = new String[portlets.size()];

		for (int i = 0; i < portlets.size(); i++) {
			Portlet portlet = portlets.get(i);

			portletIds[i] = portlet.getPortletId();
		}

		return portletIds;
	}

	protected User getUser(long companyId) throws Exception {
		Role role = RoleLocalServiceUtil.getRole(
			companyId, RoleConstants.ADMINISTRATOR);

		List<User> users = UserLocalServiceUtil.getRoleUsers(role.getRoleId());

		return users.get(0);
	}

	protected void setRolePermissions(Layout layout) throws Exception {
		Role role = RoleLocalServiceUtil.getRole(
			layout.getCompanyId(), RoleConstants.GUEST);

		ResourcePermissionServiceUtil.setIndividualResourcePermissions(
			layout.getGroupId(), layout.getCompanyId(), Layout.class.getName(),
			String.valueOf(layout.getPlid()), role.getRoleId(), new String[0]);

		role = RoleLocalServiceUtil.getRole(
			layout.getCompanyId(), RoleConstants.POWER_USER);

		ResourcePermissionServiceUtil.setIndividualResourcePermissions(
			layout.getGroupId(), layout.getCompanyId(), Layout.class.getName(),
			String.valueOf(layout.getPlid()), role.getRoleId(),
			new String[] {ActionKeys.VIEW});

		role = RoleLocalServiceUtil.getRole(
			layout.getCompanyId(), RoleConstants.USER);

		ResourcePermissionServiceUtil.setIndividualResourcePermissions(
			layout.getGroupId(), layout.getCompanyId(), Layout.class.getName(),
			String.valueOf(layout.getPlid()), role.getRoleId(),
			new String[] {ActionKeys.VIEW});
	}

	protected void updateLookAndFeel(long companyId) throws Exception {
		Group group = GroupLocalServiceUtil.getGroup(
			companyId, GroupConstants.GUEST);

		LayoutSetLocalServiceUtil.updateLookAndFeel(
			group.getGroupId(), "osblcs_WAR_osblcstheme", "01",
			StringPool.BLANK, false);
	}

	protected void updateWebGuestHomeLayout(long groupId, long userId)
		throws Exception {

		try {
			Layout layout = LayoutLocalServiceUtil.getFriendlyURLLayout(
				groupId, false, StringPool.SLASH + "home");

			layout = LayoutLocalServiceUtil.updateLookAndFeel(
				groupId, false, layout.getLayoutId(), "osblcs_WAR_osblcstheme",
				"01", StringPool.BLANK, false);

			LayoutTypePortlet layoutTypePortlet =
				(LayoutTypePortlet)layout.getLayoutType();

			String[] layoutPortletIds = getLayoutPortletIds(layout);

			for (String portletId : layoutPortletIds) {
				layoutTypePortlet.removePortletId(userId, portletId);
			}

			layoutTypePortlet.setLayoutTemplateId(userId, "1_column", false);

			layout = LayoutLocalServiceUtil.updateLayout(layout);

			addPortletId(userId, layout, PortletKeys.LOGIN, "column-1");
		}
		catch (NoSuchLayoutException nsle) {
			_log.error("No default layout", nsle);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(AddLayoutsAction.class);

}