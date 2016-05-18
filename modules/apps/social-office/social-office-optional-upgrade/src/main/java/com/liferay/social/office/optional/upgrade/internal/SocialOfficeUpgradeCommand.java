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

package com.liferay.social.office.optional.upgrade.internal;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutSetPrototype;
import com.liferay.portal.kernel.model.PortletPreferences;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.LayoutSetPrototypeLocalService;
import com.liferay.portal.kernel.service.PortletPreferencesLocalService;

import java.util.concurrent.atomic.AtomicInteger;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Adolfo Pérez
 */
@Component(
	immediate = true,
	property = {
		"osgi.command.function=executeAll",
		"osgi.command.function=hideTasksLayout",
		"osgi.command.function=removeTasksPortlet",
		"osgi.command.function=renameDashboardSiteTemplate",
		"osgi.command.function=renameProfileSiteTemplate",
		"osgi.command.function=updateProfileTheme",
		"osgi.command.function=updateSocialSiteTheme",
		"osgi.command.function=updateDashboardTheme", "osgi.command.scope=so"
	},
	service = Object.class
)
public class SocialOfficeUpgradeCommand {

	public void executeAll() throws PortalException {
		hideTasksLayout();
		removeTasksPortlet();
		renameDashboardSiteTemplate();
		renameProfileSiteTemplate();
		updateDashboardTheme();
		updateProfileTheme();
		updateSocialSiteTheme();
	}

	public void hideTasksLayout() throws PortalException {
		ActionableDynamicQuery actionableDynamicQuery =
			_layoutLocalService.getActionableDynamicQuery();

		actionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {

				public void addCriteria(DynamicQuery dynamicQuery) {
					dynamicQuery.add(
						RestrictionsFactoryUtil.eq("friendlyURL", "/so/tasks"));
					dynamicQuery.add(
						RestrictionsFactoryUtil.eq("hidden", false));
				}

			});

		final AtomicInteger atomicInteger = new AtomicInteger(0);

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<Layout>() {

				public void performAction(Layout layout)
					throws PortalException {

					layout.setHidden(true);

					_layoutLocalService.updateLayout(layout);

					atomicInteger.incrementAndGet();
				}

			});

		actionableDynamicQuery.performActions();

		System.out.printf(
			"[so:hideTasksLayout] %d layouts updated.%n", atomicInteger.get());
	}

	public void removeTasksPortlet() throws PortalException {
		ActionableDynamicQuery actionableDynamicQuery =
			_portletPreferencesLocalService.getActionableDynamicQuery();

		actionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {

				public void addCriteria(DynamicQuery dynamicQuery) {
					dynamicQuery.add(
						RestrictionsFactoryUtil.like(
							"portletId", "%tasksportlet%"));
				}

			});

		final AtomicInteger atomicInteger = new AtomicInteger(0);

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.
				PerformActionMethod<PortletPreferences>() {

				public void performAction(PortletPreferences portletPreferences)
					throws PortalException {

					_portletPreferencesLocalService.deletePersistedModel(
						portletPreferences);

					atomicInteger.incrementAndGet();
				}

			});

		actionableDynamicQuery.performActions();

		System.out.printf(
			"[so:removeTasksPortlet] %d Tasks portlet preferences deleted.%n",
			atomicInteger.get());
	}

	public void renameDashboardSiteTemplate() throws PortalException {
		String successMessage =
			"[so:renameDashboardSiteTemplate] Successfully renamed Dashboard " +
				"site template.";

		_doRenameSiteTemplate(
			"%>Social Office User Home<%", _DASHBOARD_NAME_XML,
			_DASHBOARD_DESCRIPTION_XML, successMessage);
	}

	public void renameProfileSiteTemplate() {
		String successMessage =
			"[so:renameProfileSiteTemplate] Successfully renamed Profile " +
				"site template.";

		_doRenameSiteTemplate(
			"%>Social Office User Profile<%", _PROFILE_NAME_XML,
			_PROFILE_DESCRIPTION_XML, successMessage);
	}

	public void updateDashboardTheme() {
		throw new UnsupportedOperationException();
	}

	public void updateProfileTheme() {
		throw new UnsupportedOperationException();
	}

	public void updateSocialSiteTheme() {
		throw new UnsupportedOperationException();
	}

	@Reference(unbind = "-")
	protected void setLayoutLocalService(
		LayoutLocalService layoutLocalService) {

		_layoutLocalService = layoutLocalService;
	}

	@Reference(unbind = "-")
	protected void setLayoutSetPrototypeService(
		LayoutSetPrototypeLocalService layoutSetPrototypeLocalService) {

		_layoutSetPrototypeLocalService = layoutSetPrototypeLocalService;
	}

	@Reference(unbind = "-")
	protected void setPortletPreferencesLocalService(
		PortletPreferencesLocalService portletPreferencesLocalService) {

		_portletPreferencesLocalService = portletPreferencesLocalService;
	}

	private void _doRenameSiteTemplate(
			final String filter, final String nameXml,
			final String descriptionXml, String successMessage)
		throws PortalException {

		ActionableDynamicQuery actionableDynamicQuery =
			_layoutSetPrototypeLocalService.getActionableDynamicQuery();

		actionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {

				public void addCriteria(DynamicQuery dynamicQuery) {
					dynamicQuery.add(
						RestrictionsFactoryUtil.like("name", filter));
				}

			});

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.
				PerformActionMethod<LayoutSetPrototype>() {

				public void performAction(LayoutSetPrototype layoutSetPrototype)
					throws PortalException {

					layoutSetPrototype.setName(nameXml);
					layoutSetPrototype.setDescription(descriptionXml);

					_layoutSetPrototypeLocalService.updateLayoutSetPrototype(
						layoutSetPrototype);
				}

			});

		actionableDynamicQuery.performActions();

		System.out.println(successMessage);
	}

	private static final String _DASHBOARD_DESCRIPTION_XML =
		"<?xml version='1.0' encoding='UTF-8'?>" +
			"<root available-locales=\"en_US\" default-locale=\"en_US\">" +
				"<Description language-id=\"en_US\">" +
					"Site template for the User Dashboard</Description></root>";

	private static final String _DASHBOARD_NAME_XML =
		"<?xml version='1.0' encoding='UTF-8'?>" +
			"<root available-locales=\"en_US\" default-locale=\"en_US\">" +
				"<Name language-id=\"en_US\">Social Dashboard</Name></root>";

	private static final String _PROFILE_DESCRIPTION_XML =
		"<?xml version='1.0' encoding='UTF-8'?>" +
			"<root available-locales=\"en_US\" default-locale=\"en_US\">" +
				"<Description language-id=\"en_US\">" +
					"Site template for the User Profile</Description></root>";

	private static final String _PROFILE_NAME_XML =
		"<?xml version='1.0' encoding='UTF-8'?>" +
			"<root available-locales=\"en_US\" default-locale=\"en_US\">" +
				"<Name language-id=\"en_US\">Social Profile</Name></root>";

	private LayoutLocalService _layoutLocalService;
	private LayoutSetPrototypeLocalService _layoutSetPrototypeLocalService;
	private PortletPreferencesLocalService _portletPreferencesLocalService;

}