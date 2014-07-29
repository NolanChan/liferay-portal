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

package com.liferay.portlet.dynamicdatamapping.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;
import com.liferay.portlet.dynamicdatamapping.storage.Field;
import com.liferay.portlet.dynamicdatamapping.storage.Fields;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Eduardo Lundgren
 * @author Brian Wing Shun Chan
 */
public class DDMXSDUtil {

	public static DDMXSD getDDMXSD() {
		PortalRuntimePermission.checkGetBeanProperty(DDMXSDUtil.class);

		return _ddmXSD;
	}

	public static String getFieldHTMLByName(
			HttpServletRequest request, HttpServletResponse response,
			long classNameId, long classPK, String fieldName, Fields fields,
			String portletNamespace, String namespace, String mode,
			boolean readOnly, Locale locale)
		throws Exception {

		return getDDMXSD().getFieldHTMLByName(
			request, response, classNameId, classPK, fieldName, fields,
			portletNamespace, namespace, mode, readOnly, locale);
	}

	public static String getHTML(
			HttpServletRequest request, HttpServletResponse response,
			DDMStructure ddmStructure, Fields fields, String portletNamespace,
			String namespace, boolean readOnly, Locale locale)
		throws Exception {

		return getDDMXSD().getHTML(
			request, response, ddmStructure, fields, portletNamespace,
			namespace, readOnly, locale);
	}

	public static String getHTML(
			HttpServletRequest request, HttpServletResponse response,
			DDMTemplate ddmTemplate, Fields fields, String portletNamespace,
			String namespace, boolean readOnly, Locale locale)
		throws Exception {

		return getDDMXSD().getHTML(
			request, response, ddmTemplate, fields, portletNamespace, namespace,
			readOnly, locale);
	}

	public static String getHTML(
			HttpServletRequest request, HttpServletResponse response,
			String xml, Fields fields, String portletNamespace, Locale locale)
		throws Exception {

		return getDDMXSD().getHTML(
			request, response, xml, fields, portletNamespace, locale);
	}

	public static String getHTML(
			HttpServletRequest request, HttpServletResponse response,
			String xml, Fields fields, String portletNamespace,
			String namespace, boolean readOnly, Locale locale)
		throws Exception {

		return getDDMXSD().getHTML(
			request, response, xml, fields, portletNamespace, namespace,
			readOnly, locale);
	}

	public static String getHTML(
			HttpServletRequest request, HttpServletResponse response,
			String xml, Fields fields, String portletNamespace,
			String namespace, Locale locale)
		throws Exception {

		return getDDMXSD().getHTML(
			request, response, xml, fields, portletNamespace, namespace,
			locale);
	}

	public static String getHTML(
			HttpServletRequest request, HttpServletResponse response,
			String xml, Fields fields, String portletNamespace,
			String namespace, String mode, boolean readOnly, Locale locale)
		throws Exception {

		return getDDMXSD().getHTML(
			request, response, xml, fields, portletNamespace, namespace, mode,
			readOnly, locale);
	}

	public static String getHTML(
			HttpServletRequest request, HttpServletResponse response,
			String xml, String portletNamespace, Locale locale)
		throws Exception {

		return getDDMXSD().getHTML(
			request, response, xml, portletNamespace, locale);
	}

	public static JSONArray getJSONArray(DDMStructure structure, String xsd)
		throws PortalException {

		return getDDMXSD().getJSONArray(structure, xsd);
	}

	public static JSONArray getJSONArray(Document document)
		throws PortalException {

		return getDDMXSD().getJSONArray(document);
	}

	public static JSONArray getJSONArray(Element element)
		throws PortalException {

		return getDDMXSD().getJSONArray(element);
	}

	public static JSONArray getJSONArray(String xml) throws PortalException {
		return getDDMXSD().getJSONArray(xml);
	}

	public static String getSimpleFieldHTML(
			HttpServletRequest request, HttpServletResponse response,
			Element element, Field field, String portletNamespace,
			String namespace, String mode, boolean readOnly, Locale locale)
		throws Exception {

		return getDDMXSD().getSimpleFieldHTML(
			request, response, element, field, portletNamespace, namespace,
			mode, readOnly, locale);
	}

	public static String getSimpleFieldHTMLByName(
			HttpServletRequest request, HttpServletResponse response,
			long classNameId, long classPK, Field field,
			String portletNamespace, String namespace, String mode,
			boolean readOnly, Locale locale)
		throws Exception {

		return getDDMXSD().getSimpleFieldHTMLByName(
			request, response, classNameId, classPK, field, portletNamespace,
			namespace, mode, readOnly, locale);
	}

	public static String getXSD(long classNameId, long classPK)
		throws PortalException {

		return getDDMXSD().getXSD(classNameId, classPK);
	}

	public static String getXSD(String json) throws PortalException {
		return getDDMXSD().getXSD(json);
	}

	public void setDDMXSD(DDMXSD ddmXSD) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_ddmXSD = ddmXSD;
	}

	private static DDMXSD _ddmXSD;

}