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

package com.liferay.frontend.editors.web.editor.configuration;

import com.liferay.portal.kernel.editor.configuration.EditorConfigContributor;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.servlet.BrowserSnifferUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.TextFormatter;
import com.liferay.portal.theme.ThemeDisplay;

import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

/**
 * @author Ambrin Chaudhary
 */
@Component(
	property = {"editor.name=tinymce"}, service = EditorConfigContributor.class
)
public class TinyMCEEditorConfigContributor
	extends BaseTinyMCEEditorConfigConfigurator {

	@Override
	public void populateConfigJSONObject(
		JSONObject jsonObject, Map<String, Object> inputEditorTaglibAttributes,
		ThemeDisplay themeDisplay,
		LiferayPortletResponse liferayPortletResponse) {

		super.populateConfigJSONObject(
			jsonObject, inputEditorTaglibAttributes, themeDisplay,
			liferayPortletResponse);

		jsonObject.put("mode", "exact");
		jsonObject.put(
			"plugins", getPluginsJSONArray(inputEditorTaglibAttributes));
		jsonObject.put(
			"style_formats",
			getStyleFormatsJSONArray(themeDisplay.getLocale()));
		jsonObject.put(
			"toolbar",
			getToolbarJSONArray(inputEditorTaglibAttributes, themeDisplay));
	}

	protected JSONArray getPluginsJSONArray(
		Map<String, Object> inputEditorTaglibAttributes) {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		jsonArray.put(
			"advlist autolink autosave link image lists charmap print " +
				"preview hr anchor");
		jsonArray.put("searchreplace wordcount fullscreen media");

		if (isShowSource(inputEditorTaglibAttributes)) {
			jsonArray.put("code");
		}

		jsonArray.put(
			"table contextmenu emoticons textcolor paste fullpage textcolor " +
				"colorpicker textpattern");

		return jsonArray;
	}

	protected JSONObject getStyleFormatJSONObject(
		String styleFormatName, String type, String element,
		String cssClasses) {

		JSONObject styleJSONObject = JSONFactoryUtil.createJSONObject();

		styleJSONObject.put(type, element);
		styleJSONObject.put("title", styleFormatName);
		styleJSONObject.put("classes", cssClasses);

		return styleJSONObject;
	}

	protected JSONArray getStyleFormatsJSONArray(Locale locale) {
		JSONArray styleFormatsJSONArray = JSONFactoryUtil.createJSONArray();

		styleFormatsJSONArray.put(
			getStyleFormatJSONObject(
				LanguageUtil.get(locale, "normal"), "inline", "p", null));
		styleFormatsJSONArray.put(
			getStyleFormatJSONObject(
				LanguageUtil.format(locale, "heading-x", "1"), "block", "h1",
				null));
		styleFormatsJSONArray.put(
			getStyleFormatJSONObject(
				LanguageUtil.format(locale, "heading-x", "2"), "block", "h2",
				null));
		styleFormatsJSONArray.put(
			getStyleFormatJSONObject(
				LanguageUtil.format(locale, "heading-x", "3"), "block", "h3",
				null));
		styleFormatsJSONArray.put(
			getStyleFormatJSONObject(
				LanguageUtil.format(locale, "heading-x", "4"), "block", "h4",
				null));
		styleFormatsJSONArray.put(
			getStyleFormatJSONObject(
				LanguageUtil.get(locale, "preformatted-text"), "block", "pre",
				null));
		styleFormatsJSONArray.put(
			getStyleFormatJSONObject(
				LanguageUtil.get(locale, "cited-work"), "inline", "cite",
				null));
		styleFormatsJSONArray.put(
			getStyleFormatJSONObject(
				LanguageUtil.get(locale, "computer-code"), "inline", "code",
				null));
		styleFormatsJSONArray.put(
			getStyleFormatJSONObject(
				LanguageUtil.get(locale, "info-message"), "block", "div",
				"portlet-msg-info"));
		styleFormatsJSONArray.put(
			getStyleFormatJSONObject(
				LanguageUtil.get(locale, "alert-message"), "block", "div",
				"portlet-msg-alert"));
		styleFormatsJSONArray.put(
			getStyleFormatJSONObject(
				LanguageUtil.get(locale, "error-message"), "block", "div",
				"portlet-msg-error"));

		return styleFormatsJSONArray;
	}

	protected JSONArray getToolbarJSONArray(
		Map<String, Object> inputEditorTaglibAttributes,
		ThemeDisplay themeDisplay) {

		JSONObject toolbarsJSONObject = getToolbarsJSONObject(
			inputEditorTaglibAttributes);

		String toolbarSet = (String)inputEditorTaglibAttributes.get(
			"liferay-ui:input-editor:toolbarSet");

		String currentToolbarSet = TextFormatter.format(
			HtmlUtil.escapeJS(toolbarSet), TextFormatter.M);

		if (BrowserSnifferUtil.isMobile(themeDisplay.getRequest())) {
			currentToolbarSet = "phone";
		}

		JSONArray toolbarJSONArray = toolbarsJSONObject.getJSONArray(
			currentToolbarSet);

		if (toolbarJSONArray == null) {
			toolbarJSONArray = toolbarsJSONObject.getJSONArray("liferay");
		}

		return toolbarJSONArray;
	}

	protected JSONArray getToolbarsEmailJSONArray(
		Map<String, Object> inputEditorTaglibAttributes) {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		jsonArray.put(
			"fontselect fontsizeselect | forecolor backcolor | bold italic " +
				"underline strikethrough | alignleft aligncenter alignright " +
					"alignjustify");

		String buttons =
			"cut copy paste bullist numlist | blockquote | undo redo | link " +
				"unlink image ";

		if (isShowSource(inputEditorTaglibAttributes)) {
			buttons += "code ";
		}

		buttons += "| hr removeformat | preview print fullscreen";

		jsonArray.put(buttons);

		return jsonArray;
	}

	protected JSONObject getToolbarsJSONObject(
		Map<String, Object> inputEditorTaglibAttributes) {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put(
			"email", getToolbarsEmailJSONArray(inputEditorTaglibAttributes));
		jsonObject.put(
			"liferay",
			getToolbarsLiferayJSONArray(inputEditorTaglibAttributes));
		jsonObject.put("phone", getToolbarsPhoneJSONArray());
		jsonObject.put(
			"simple", getToolbarsSimpleJSONArray(inputEditorTaglibAttributes));
		jsonObject.put(
			"tablet", getToolbarsTabletJSONArray(inputEditorTaglibAttributes));

		return jsonObject;
	}

	protected JSONArray getToolbarsLiferayJSONArray(
		Map<String, Object> inputEditorTaglibAttributes) {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		jsonArray.put(
			"styleselect fontselect fontsizeselect | forecolor backcolor | " +
				"bold italic underline strikethrough | alignleft aligncenter " +
					"alignright alignjustify");

		String buttons =
			"cut copy paste searchreplace bullist numlist | outdent indent " +
				"blockquote | undo redo | link unlink anchor image media ";

		if (isShowSource(inputEditorTaglibAttributes)) {
			buttons += "code";
		}

		jsonArray.put(buttons);

		jsonArray.put(
			"table | hr removeformat | subscript superscript | charmap " +
				"emoticons | preview print fullscreen");

		return jsonArray;
	}

	protected JSONArray getToolbarsPhoneJSONArray() {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		jsonArray.put("bold italic underline | bullist numlist");
		jsonArray.put("link unlink image");

		return jsonArray;
	}

	protected JSONArray getToolbarsSimpleJSONArray(
		Map<String, Object> inputEditorTaglibAttributes) {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		String buttons =
			"bold italic underline strikethrough | bullist numlist | table | " +
				"link unlink image";

		if (isShowSource(inputEditorTaglibAttributes)) {
			buttons += " code";
		}

		jsonArray.put(buttons);

		return jsonArray;
	}

	protected JSONArray getToolbarsTabletJSONArray(
		Map<String, Object> inputEditorTaglibAttributes) {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		jsonArray.put(
			"styleselect fontselect fontsizeselect | bold italic underline " +
				"strikethrough | alignleft aligncenter alignright " +
					"alignjustify");

		String buttons = "bullist numlist | link unlink image";

		if (isShowSource(inputEditorTaglibAttributes)) {
			buttons += " code";
		}

		jsonArray.put(buttons);

		return jsonArray;
	}

}