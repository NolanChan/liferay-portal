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

package com.liferay.frontend.alloy.editor.config;

import com.liferay.portal.kernel.editor.config.EditorConfigContributor;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortletKeys;

import java.util.Locale;
import java.util.Map;

import javax.portlet.WindowStateException;

import org.osgi.service.component.annotations.Component;

/**
 * @author Sergio González
 */
@Component(property = {"editor.name=alloyeditor"})
public class AlloyEditorConfigContributor implements EditorConfigContributor {

	@Override
	public void populateConfigJSONObject(
		JSONObject jsonObject, Map<String, Object> inputEditorTaglibAttributes,
		ThemeDisplay themeDisplay,
		LiferayPortletResponse liferayPortletResponse) {

		String contentsLanguageId = (String)inputEditorTaglibAttributes.get(
			"liferay-ui:input-editor:contentsLanguageId");

		Locale contentsLocale = LocaleUtil.fromLanguageId(contentsLanguageId);

		String contentsLanguageDir = LanguageUtil.get(
			contentsLocale, "lang.dir");

		contentsLanguageId = LocaleUtil.toLanguageId(contentsLocale);

		jsonObject.put(
			"contentsLangDirection", HtmlUtil.escapeJS(contentsLanguageDir));
		jsonObject.put(
			"contentsLanguage", contentsLanguageId.replace("iw_", "he_"));
		jsonObject.put(
			"extraPlugins",
			"autolink,dragresize,dropimages,placeholder,selectionregion," +
				"tableresize,tabletools,uicore");

		String languageId = LocaleUtil.toLanguageId(themeDisplay.getLocale());

		jsonObject.put("language", languageId.replace("iw_", "he_"));
		jsonObject.put(
			"removePlugins", "elementspath,link,liststyle,resize,toolbar");

		if (liferayPortletResponse != null) {
			LiferayPortletURL itemSelectorURL =
				liferayPortletResponse.createRenderURL(
					PortletKeys.ITEM_SELECTOR);

			itemSelectorURL.setParameter("mvcPath", "/view.jsp");
			itemSelectorURL.setParameter(
				"groupId", String.valueOf(themeDisplay.getScopeGroupId()));

			String name =
				liferayPortletResponse.getNamespace() +
					GetterUtil.getString(
						(String)inputEditorTaglibAttributes.get(
							"liferay-ui:input-editor:name"));

			itemSelectorURL.setParameter("eventName", name + "selectDocument");
			itemSelectorURL.setParameter(
				"showGroupsSelector", Boolean.TRUE.toString());

			Map<String, String> fileBrowserParamsMap =
				(Map<String, String>)inputEditorTaglibAttributes.get(
					"liferay-ui:input-editor:fileBrowserParams");

			if (fileBrowserParamsMap != null) {
				for (Map.Entry<String, String> entry :
						fileBrowserParamsMap.entrySet()) {

					itemSelectorURL.setParameter(
						entry.getKey(), entry.getValue());
				}
			}

			try {
				itemSelectorURL.setWindowState(LiferayWindowState.POP_UP);
			}
			catch (WindowStateException wse) {
			}

			jsonObject.put("filebrowserBrowseUrl", itemSelectorURL.toString());
			jsonObject.put(
				"filebrowserFlashBrowseUrl",
				itemSelectorURL.toString() + "&Type=flash");
			jsonObject.put(
				"filebrowserImageBrowseLinkUrl",
				itemSelectorURL.toString() + "&Type=image");
			jsonObject.put(
				"filebrowserImageBrowseUrl",
				itemSelectorURL.toString() + "&Type=image");

			jsonObject.put("srcNode", name);
		}

		jsonObject.put("toolbars", getToolbarsJSONObject());
	}

	@Override
	public void populateOptionsJSONObject(
		JSONObject jsonObject, Map<String, Object> inputEditorTaglibAttributes,
		ThemeDisplay themeDisplay,
		LiferayPortletResponse liferayPortletResponse) {
	}

	protected JSONObject getToolbarsAddJSONObject() {
		JSONObject toolbarsAddJSONObject = JSONFactoryUtil.createJSONObject();

		try {
			toolbarsAddJSONObject.put(
				"buttons",
				JSONFactoryUtil.createJSONArray(
					"['imageselector', 'table', 'hline']"));
		}
		catch (JSONException jsone) {
		}

		toolbarsAddJSONObject.put("tabIndex", 2);

		return toolbarsAddJSONObject;
	}

	protected JSONObject getToolbarsJSONObject() {
		JSONObject toolbarsJSONObject = JSONFactoryUtil.createJSONObject();

		toolbarsJSONObject.put("add", getToolbarsAddJSONObject());
		toolbarsJSONObject.put("styles", getToolbarsStylesJSONObject());

		return toolbarsJSONObject;
	}

	protected JSONObject getToolbarsStylesJSONObject() {
		JSONObject toolbarsStylesJSONObject =
			JSONFactoryUtil.createJSONObject();

		toolbarsStylesJSONObject.put(
			"selections", getToolbarsStylesSelectionsJSONArray());
		toolbarsStylesJSONObject.put("tabIndex", 1);

		return toolbarsStylesJSONObject;
	}

	protected JSONArray getToolbarsStylesSelectionsJSONArray() {
		JSONArray toolbarsStylesSelectionsJSONArray =
			JSONFactoryUtil.createJSONArray();

		try {
			JSONObject toolbarsStylesSelectionsLinkJSONObject =
				JSONFactoryUtil.createJSONObject();

			toolbarsStylesSelectionsLinkJSONObject.put(
				"buttons", JSONFactoryUtil.createJSONArray("['linkEdit']"));
			toolbarsStylesSelectionsLinkJSONObject.put("name", "link");
			toolbarsStylesSelectionsLinkJSONObject.put("test", "link");

			toolbarsStylesSelectionsJSONArray.put(
				toolbarsStylesSelectionsLinkJSONObject);

			JSONObject toolbarsStylesSelectionsImageJSONObject =
				JSONFactoryUtil.createJSONObject();

			toolbarsStylesSelectionsImageJSONObject.put(
				"buttons",
				JSONFactoryUtil.createJSONArray("['imageLeft', 'imageRight']"));
			toolbarsStylesSelectionsImageJSONObject.put("name", "image");
			toolbarsStylesSelectionsImageJSONObject.put("test", "image");

			toolbarsStylesSelectionsJSONArray.put(
				toolbarsStylesSelectionsImageJSONObject);

			JSONObject toolbarsStylesSelectionsTextJSONObject =
				JSONFactoryUtil.createJSONObject();

			toolbarsStylesSelectionsTextJSONObject.put(
				"buttons",
				JSONFactoryUtil.createJSONArray(
					"['styles', 'bold', 'italic', 'underline', 'link', " +
						"'twitter']"));
			toolbarsStylesSelectionsTextJSONObject.put("name", "text");
			toolbarsStylesSelectionsTextJSONObject.put("test", "text");

			toolbarsStylesSelectionsJSONArray.put(
				toolbarsStylesSelectionsTextJSONObject);

			JSONObject toolbarsStylesSelectionsTableJSONObject =
				JSONFactoryUtil.createJSONObject();

			toolbarsStylesSelectionsTableJSONObject.put(
				"buttons",
				JSONFactoryUtil.createJSONArray(
					"['tableRow', 'tableColumn', 'tableCell', 'tableRemove']"));
			toolbarsStylesSelectionsTableJSONObject.put(
				"getArrowBoxClasses", "table");
			toolbarsStylesSelectionsTableJSONObject.put("name", "table");
			toolbarsStylesSelectionsTableJSONObject.put("setPosition", "table");
			toolbarsStylesSelectionsTableJSONObject.put("test", "table");

			toolbarsStylesSelectionsJSONArray.put(
				toolbarsStylesSelectionsTableJSONObject);
		}
		catch (JSONException jsone) {
			if (_log.isErrorEnabled()) {
				_log.error("Unable to create a JSON array from string");
			}
		}

		return toolbarsStylesSelectionsJSONArray;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AlloyEditorConfigContributor.class);

}