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

package com.liferay.image.editor.web.portlet.action;

import com.liferay.image.editor.capability.ImageEditorCapability;
import com.liferay.image.editor.web.constants.ImageEditorPortletKeys;
import com.liferay.image.editor.web.portlet.tracker.ImageEditorCapabilityTracker;
import com.liferay.image.editor.web.portlet.tracker.ImageEditorCapabilityTracker.ImageEditorCapabilityInformation;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.template.Template;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Bruno Basto
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + ImageEditorPortletKeys.IMAGE_EDITOR,
		"mvc.command.name=/", "mvc.command.name=View"
	},
	service = MVCRenderCommand.class
)
public class ViewMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
		RenderRequest renderRequest, RenderResponse renderResponse) {

		Template template = getTemplate(renderRequest);

		Map<String, Object> capabilitiesContext = new HashMap<>();

		HttpServletRequest httpServletRequest =
			PortalUtil.getHttpServletRequest(renderRequest);

		String imageUrl = ParamUtil.getString(
			httpServletRequest, ImageEditorPortletKeys.IMAGE_EDITOR_URL);

		capabilitiesContext.put(
			"tools", getImageEditorToolsContext(renderRequest));

		ThemeDisplay themeDisplay = (ThemeDisplay)httpServletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		template.put("capabilities", capabilitiesContext);
		template.put("image", imageUrl);
		template.put("pathThemeImages", themeDisplay.getPathThemeImages());

		return "ImageEditor";
	}

	protected List<Map<String, Object>> getImageEditorToolsContext(
		RenderRequest renderRequest) {

		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		List<Map<String, Object>> imageEditorToolsContext = new ArrayList();

		List<ImageEditorCapabilityInformation> imageEditorToolInformations =
			_imageEditorCapabilityTracker.getCapabilities("tool");

		if (imageEditorToolInformations != null) {
			List<List<ImageEditorCapabilityInformation>> toolCategories =
				groupCapabilities(imageEditorToolInformations);

			for (List<ImageEditorCapabilityInformation> toolCategory :
					toolCategories) {

				Map<String, Object> toolContext = new HashMap<>();

				List<Map<String, Object>> categoryControls = new ArrayList();
				String categoryIcon = StringPool.BLANK;

				for (ImageEditorCapabilityInformation
						imageEditorCapabilityInformation :
							toolCategory) {

					Map<String, Object> capabilityProperties =
						imageEditorCapabilityInformation.getProperties();

					String icon = GetterUtil.getString(
						capabilityProperties.get(
							"com.liferay.image.editor.capability.icon"));

					categoryIcon = icon;

					ImageEditorCapability imageEditorCapability =
						imageEditorCapabilityInformation.
							getImageEditorCapability();

					String label = imageEditorCapability.getLabel(
						themeDisplay.getLocale());

					ServletContext imageEditorCapabilityServletContext =
						imageEditorCapability.getServletContext();

					String variant = GetterUtil.getString(
						capabilityProperties.get(
							"com.liferay.image.editor.capability.controls"));

					Map<String, Object> controlContext = new HashMap<>();

					controlContext.put("label", label);
					controlContext.put(
						"modulePath",
						imageEditorCapabilityServletContext.getContextPath());
					controlContext.put("variant", variant);

					HttpServletRequest httpServletRequest =
						PortalUtil.getHttpServletRequest(renderRequest);

					imageEditorCapability.prepareContext(
						controlContext, httpServletRequest);

					categoryControls.add(controlContext);
				}

				toolContext.put("controls", categoryControls);
				toolContext.put("icon", categoryIcon);

				imageEditorToolsContext.add(toolContext);
			}
		}

		return imageEditorToolsContext;
	}

	protected Template getTemplate(RenderRequest renderRequest) {
		return (Template)renderRequest.getAttribute(WebKeys.TEMPLATE);
	}

	protected List<List<ImageEditorCapabilityInformation>> groupCapabilities(
		List<ImageEditorCapabilityInformation> imageEditorCapabilities) {

		Map<String, List<ImageEditorCapabilityInformation>>
			groupedCapabilities = new HashMap<>();

		for (ImageEditorCapabilityInformation imageEditorCapability :
				imageEditorCapabilities) {

			Map<String, Object> capabilityProperties =
				imageEditorCapability.getProperties();

			String imageEditorCapabilityCategory = GetterUtil.getString(
				capabilityProperties.get(
					"com.liferay.image.editor.capability.category"));

			groupedCapabilities.putIfAbsent(
				imageEditorCapabilityCategory,
				new ArrayList<ImageEditorCapabilityInformation>());

			List<ImageEditorCapabilityInformation> imageEditorCapabilityList =
				groupedCapabilities.get(imageEditorCapabilityCategory);

			imageEditorCapabilityList.add(imageEditorCapability);
		}

		return new ArrayList<>(groupedCapabilities.values());
	}

	@Reference
	private ImageEditorCapabilityTracker _imageEditorCapabilityTracker;

}