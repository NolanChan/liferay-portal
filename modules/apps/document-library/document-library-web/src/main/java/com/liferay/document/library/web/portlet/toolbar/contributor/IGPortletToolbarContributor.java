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

package com.liferay.document.library.web.portlet.toolbar.contributor;

import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.document.library.web.constants.DLPortletKeys;
import com.liferay.document.library.web.portlet.toolbar.contributor.helper.DLPortletToolbarContributorHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.toolbar.contributor.BasePortletToolbarContributor;
import com.liferay.portal.kernel.portlet.toolbar.contributor.PortletToolbarContributor;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.servlet.taglib.ui.MenuItem;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Roberto Díaz
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + DLPortletKeys.MEDIA_GALLERY_DISPLAY,
		"mvc.render.command.name=-",
		"mvc.render.command.name=/image_gallery_display/view"
	},
	service = {PortletToolbarContributor.class}
)
public class IGPortletToolbarContributor extends BasePortletToolbarContributor {

	@Activate
	protected void activate(BundleContext bundleContext) {
		_dlPortletToolbarContributorHelper =
			new DLPortletToolbarContributorHelper(_dlAppLocalService);
	}

	protected void addPortletTitleAddFileEntryMenuItem(
			List<MenuItem> menuItems, Folder folder, ThemeDisplay themeDisplay,
			PortletRequest portletRequest)
		throws PortalException {

		List<MenuItem> portletTitleAddDocumentMenuItems =
			_dlPortletToolbarContributor.getPortletTitleAddDocumentMenuItems(
				folder, themeDisplay, portletRequest);

		if (ListUtil.isNotNull(portletTitleAddDocumentMenuItems)) {
			menuItems.addAll(portletTitleAddDocumentMenuItems);
		}
	}

	protected void addPortletTitleAddFolderMenuItem(
			List<MenuItem> menuItems, Folder folder, ThemeDisplay themeDisplay,
			PortletRequest portletRequest)
		throws PortalException {

		MenuItem portletTitleAddFolderMenuItem =
			_dlPortletToolbarContributor.getPortletTitleAddFolderMenuItem(
				themeDisplay, portletRequest, folder);

		if (portletTitleAddFolderMenuItem != null) {
			menuItems.add(portletTitleAddFolderMenuItem);
		}
	}

	protected void addPortletTitleAddMulpleFileEntriesMenuItem(
		List<MenuItem> menuItems, Folder folder, ThemeDisplay themeDisplay,
		PortletRequest portletRequest) {

		MenuItem portletTitleAddMultipleDocumentsMenuItem =
			_dlPortletToolbarContributor.
				getPortletTitleAddMultipleDocumentsMenuItem(
					themeDisplay, portletRequest, folder);

		portletTitleAddMultipleDocumentsMenuItem.setLabel(
			LanguageUtil.get(
				PortalUtil.getHttpServletRequest(portletRequest),
				"multiple-media"));

		menuItems.add(portletTitleAddMultipleDocumentsMenuItem);
	}

	@Override
	protected List<MenuItem> getPortletTitleMenuItems(
		PortletRequest portletRequest, PortletResponse portletResponse) {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		List<MenuItem> menuItems = new ArrayList<>();

		Folder folder = _dlPortletToolbarContributorHelper.getFolder(
			themeDisplay, portletRequest);

		try {
			addPortletTitleAddFolderMenuItem(
				menuItems, folder, themeDisplay, portletRequest);
		}
		catch (PortalException pe) {
			_log.error("Unable to add folder menu item", pe);
		}

		try {
			addPortletTitleAddFileEntryMenuItem(
				menuItems, folder, themeDisplay, portletRequest);
		}
		catch (PortalException pe) {
			_log.error("Unable to add file entry menu item", pe);
		}

		addPortletTitleAddMulpleFileEntriesMenuItem(
			menuItems, folder, themeDisplay, portletRequest);

		return menuItems;
	}

	@Reference(unbind = "-")
	protected void setDLAppLocalService(DLAppLocalService dlAppLocalService) {
		_dlAppLocalService = dlAppLocalService;
	}

	@Reference(unbind = "-")
	protected void setDLPortletToolbarContributor(
		DLPortletToolbarContributor dlPortletToolbarContributor) {

		_dlPortletToolbarContributor = dlPortletToolbarContributor;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		IGPortletToolbarContributor.class);

	private DLAppLocalService _dlAppLocalService;
	private DLPortletToolbarContributor _dlPortletToolbarContributor;
	private DLPortletToolbarContributorHelper
		_dlPortletToolbarContributorHelper;

}