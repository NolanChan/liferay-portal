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

import com.liferay.osb.lcs.configuration.OSBLCSConfiguration;
import com.liferay.osb.lcs.constants.OSBLCSActionKeys;
import com.liferay.osb.lcs.model.CorpProject;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.events.LifecycleEvent;
import com.liferay.portal.kernel.exception.NoSuchLayoutException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.model.LayoutTypePortlet;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.LayoutSetLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Igor Beslic
 */
@Component(
	configurationPid = "com.liferay.osb.lcs.configuration.OSBLCSConfiguration",
	configurationPolicy = ConfigurationPolicy.REQUIRE, immediate = true,
	property = {"key=application.startup.events"},
	service = LifecycleAction.class
)
public class AddLayoutsAction implements LifecycleAction {

	@Override
	public void processLifecycleEvent(LifecycleEvent lifecycleEvent)
		throws ActionException {

		String[] ids = lifecycleEvent.getIds();

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

	@Reference(bind = "-", unbind = "-")
	public void setClassNameLocalService(
		ClassNameLocalService classNameLocalService) {

		_classNameLocalService = classNameLocalService;
	}

	@Reference(bind = "-", unbind = "-")
	public void setGroupLocalService(GroupLocalService groupLocalService) {
		_groupLocalService = groupLocalService;
	}

	@Reference(bind = "-", unbind = "-")
	public void setLayoutLocalService(LayoutLocalService layoutLocalService) {
		_layoutLocalService = layoutLocalService;
	}

	@Reference(bind = "-", unbind = "-")
	public void setLayoutSetLocalService(
		LayoutSetLocalService layoutSetLocalService) {

		_layoutSetLocalService = layoutSetLocalService;
	}

	@Reference(bind = "-", unbind = "-")
	public void setResourcePermissionService(
		ResourcePermissionService resourcePermissionService) {

		_resourcePermissionService = resourcePermissionService;
	}

	@Reference(bind = "-", unbind = "-")
	public void setRoleLocalService(RoleLocalService roleLocalService) {
		_roleLocalService = roleLocalService;
	}

	@Reference(bind = "-", unbind = "-")
	public void setUserLocalService(UserLocalService userLocalService) {
		_userLocalService = userLocalService;
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_osbLCSConfiguration = ConfigurableUtil.createConfigurable(
			OSBLCSConfiguration.class, properties);

		System.out.println("Component activated");
	}

	protected Layout addLayout(
			LayoutSet layoutSet, String name, String friendlyURL,
			String layouteTemplateId)
		throws Exception {

		Group group = layoutSet.getGroup();

		ServiceContext serviceContext = new ServiceContext();

		Layout layout = _layoutLocalService.addLayout(
			group.getCreatorUserId(), group.getGroupId(),
			layoutSet.isPrivateLayout(),
			LayoutConstants.DEFAULT_PARENT_LAYOUT_ID, name, StringPool.BLANK,
			StringPool.BLANK, LayoutConstants.TYPE_PORTLET, false, friendlyURL,
			serviceContext);

		LayoutTypePortlet layoutTypePortlet =
			(LayoutTypePortlet)layout.getLayoutType();

		layoutTypePortlet.setLayoutTemplateId(0, layouteTemplateId, false);

		_layoutLocalService.updateLayout(layout);

		return layout;
	}

	protected String addPortletId(
			long userId, Layout layout, String portletId, String columnId)
		throws Exception {

		LayoutTypePortlet layoutTypePortlet =
			(LayoutTypePortlet)layout.getLayoutType();

		portletId = layoutTypePortlet.addPortletId(
			userId, portletId, columnId, -1, false);

		_layoutLocalService.updateLayout(layout);

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Added portlet " + portletId + " to layout " +
					layout.getPlid());
		}

		return portletId;
	}

	protected void checkCorpProjectClassName() throws Exception {
		_classNameLocalService.addClassName(CorpProject.class.getName());
	}

	protected void checkLayoutSet(
			long userId, Group group, boolean privateLayout)
		throws Exception {

		LayoutSet layoutSet = _layoutSetLocalService.getLayoutSet(
			group.getGroupId(), privateLayout);

		String[] names = _osbLCSConfiguration.osbLcsPortalPublicLayoutNames();

		if (privateLayout) {
			names = _osbLCSConfiguration.osbLcsPortalPrivateLayoutNames();
		}

		for (int i = 0; i < names.length; i++) {
			String name = names[i];

			String friendlyURL =
				StringPool.SLASH + StringUtil.toLowerCase(name);

			String normalizedFriendlyURL = FriendlyURLNormalizerUtil.normalize(
				friendlyURL);

			Layout layout = null;

			try {
				layout = _layoutLocalService.getFriendlyURLLayout(
					group.getGroupId(), privateLayout, normalizedFriendlyURL);
			}
			catch (NoSuchLayoutException nsle) {
				String layoutTemplateId =
					_osbLCSConfiguration.osbLcsPortalPublicLayoutTemplateId();

				if (privateLayout) {
					layoutTemplateId =
						_osbLCSConfiguration.
							osbLcsPortalPrivateLayoutTemplateId();
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
				portletIds =
					_osbLCSConfiguration.osbLcsPortalPrivateLayoutPortlets();
			}
			else {
				portletIds =
					_osbLCSConfiguration.osbLcsPortalPublicLayoutPortlets();
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
		Group group = _groupLocalService.getGroup(
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
		Role role = _roleLocalService.getRole(
			companyId, RoleConstants.ADMINISTRATOR);

		List<User> users = _userLocalService.getRoleUsers(role.getRoleId());

		return users.get(0);
	}

	protected void setRolePermissions(Layout layout) throws Exception {
		Role role = _roleLocalService.getRole(
			layout.getCompanyId(), RoleConstants.GUEST);

		_resourcePermissionService.setIndividualResourcePermissions(
			layout.getGroupId(), layout.getCompanyId(), Layout.class.getName(),
			String.valueOf(layout.getPlid()), role.getRoleId(), new String[0]);

		role = _roleLocalService.getRole(
			layout.getCompanyId(), RoleConstants.POWER_USER);

		_resourcePermissionService.setIndividualResourcePermissions(
			layout.getGroupId(), layout.getCompanyId(), Layout.class.getName(),
			String.valueOf(layout.getPlid()), role.getRoleId(),
			new String[] {OSBLCSActionKeys.VIEW});

		role = _roleLocalService.getRole(
			layout.getCompanyId(), RoleConstants.USER);

		_resourcePermissionService.setIndividualResourcePermissions(
			layout.getGroupId(), layout.getCompanyId(), Layout.class.getName(),
			String.valueOf(layout.getPlid()), role.getRoleId(),
			new String[] {OSBLCSActionKeys.VIEW});
	}

	protected void updateLookAndFeel(long companyId) throws Exception {
		Group group = _groupLocalService.getGroup(
			companyId, GroupConstants.GUEST);

		_layoutSetLocalService.updateLookAndFeel(
			group.getGroupId(), "osblcs_WAR_osblcstheme", "01",
			StringPool.BLANK);
	}

	protected void updateWebGuestHomeLayout(long groupId, long userId)
		throws Exception {

		try {
			Layout layout = _layoutLocalService.getFriendlyURLLayout(
				groupId, false, StringPool.SLASH + "home");

			layout = _layoutLocalService.updateLookAndFeel(
				groupId, false, layout.getLayoutId(), "osblcs_WAR_osblcstheme",
				"01", StringPool.BLANK);

			LayoutTypePortlet layoutTypePortlet =
				(LayoutTypePortlet)layout.getLayoutType();

			String[] layoutPortletIds = getLayoutPortletIds(layout);

			for (String portletId : layoutPortletIds) {
				layoutTypePortlet.removePortletId(userId, portletId);
			}

			layoutTypePortlet.setLayoutTemplateId(userId, "1_column", false);

			layout = _layoutLocalService.updateLayout(layout);

			addPortletId(userId, layout, PortletKeys.LOGIN, "column-1");
		}
		catch (NoSuchLayoutException nsle) {
			_log.error("No default layout", nsle);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AddLayoutsAction.class);

	private static volatile OSBLCSConfiguration _osbLCSConfiguration;

	private ClassNameLocalService _classNameLocalService;
	private GroupLocalService _groupLocalService;
	private LayoutLocalService _layoutLocalService;
	private LayoutSetLocalService _layoutSetLocalService;
	private ResourcePermissionService _resourcePermissionService;
	private RoleLocalService _roleLocalService;
	private UserLocalService _userLocalService;

}