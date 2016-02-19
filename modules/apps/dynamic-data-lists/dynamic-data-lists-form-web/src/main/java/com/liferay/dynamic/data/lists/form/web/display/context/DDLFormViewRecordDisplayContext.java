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

package com.liferay.dynamic.data.lists.form.web.display.context;

import com.liferay.dynamic.data.lists.constants.DDLWebKeys;
import com.liferay.dynamic.data.lists.form.web.constants.DDLFormPortletKeys;
import com.liferay.dynamic.data.lists.form.web.display.context.util.DDLFormAdminRequestHelper;
import com.liferay.dynamic.data.lists.model.DDLFormRecord;
import com.liferay.dynamic.data.lists.model.DDLRecord;
import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.dynamic.data.lists.service.DDLRecordLocalService;
import com.liferay.dynamic.data.mapping.form.renderer.DDMFormRenderer;
import com.liferay.dynamic.data.mapping.form.renderer.DDMFormRenderingContext;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.DDMFormLayout;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Marcellus Tavares
 */
public class DDLFormViewRecordDisplayContext {

	public DDLFormViewRecordDisplayContext(
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse,
		DDLRecordLocalService ddlRecordLocalService,
		DDMFormRenderer ddmFormRenderer,
		DDMStructureLocalService ddmStructureLocalService) {

		_httpServletRequest = httpServletRequest;
		_httpServletResponse = httpServletResponse;
		_ddlRecordLocalService = ddlRecordLocalService;
		_ddmFormRenderer = ddmFormRenderer;
		_ddmStructureLocalService = ddmStructureLocalService;

		_ddlFormAdminRequestHelper = new DDLFormAdminRequestHelper(
			httpServletRequest);
	}

	public String getDDMFormHTML() throws PortalException {
		DDLRecord record = getRecord();

		DDMFormRenderingContext ddmFormRenderingContext =
			createDDMFormRenderingContext();

		ddmFormRenderingContext.setDDMFormValues(record.getDDMFormValues());

		DDMStructure ddmStructure = getDDMStructure();

		DDMForm ddmForm = ddmStructure.getDDMForm();

		for (DDMFormField ddmFormField : ddmForm.getDDMFormFields()) {
			setDDMFormFieldReadOnly(ddmFormField);
		}

		DDMFormLayout ddmFormLayout = ddmStructure.getDDMFormLayout();

		return _ddmFormRenderer.render(
			ddmForm, ddmFormLayout, ddmFormRenderingContext);
	}

	protected DDMFormRenderingContext createDDMFormRenderingContext() {
		DDMFormRenderingContext ddmFormRenderingContext =
			new DDMFormRenderingContext();

		ddmFormRenderingContext.setHttpServletRequest(_httpServletRequest);
		ddmFormRenderingContext.setHttpServletResponse(_httpServletResponse);
		ddmFormRenderingContext.setLocale(
			_ddlFormAdminRequestHelper.getLocale());
		ddmFormRenderingContext.setPortletNamespace(
			PortalUtil.getPortletNamespace(
				DDLFormPortletKeys.DYNAMIC_DATA_LISTS_FORM_ADMIN));
		ddmFormRenderingContext.setReadOnly(true);

		return ddmFormRenderingContext;
	}

	protected DDMStructure getDDMStructure() throws PortalException {
		if (_ddmStucture != null) {
			return _ddmStucture;
		}

		DDLRecordSet recordSet = getRecordSet();

		if (recordSet == null) {
			return null;
		}

		_ddmStucture = _ddmStructureLocalService.getStructure(
			recordSet.getDDMStructureId());

		return _ddmStucture;
	}

	protected DDLRecord getRecord() throws PortalException {
		long recordId = ParamUtil.getLong(_httpServletRequest, "recordId");

		if (recordId > 0) {
			return _ddlRecordLocalService.fetchRecord(recordId);
		}

		HttpServletRequest httpServletRequest =
			_ddlFormAdminRequestHelper.getRequest();

		Object record = httpServletRequest.getAttribute(
			DDLWebKeys.DYNAMIC_DATA_LISTS_RECORD);

		if (record instanceof DDLFormRecord) {
			DDLFormRecord formRecord = (DDLFormRecord)record;

			return formRecord.getDDLRecord();
		}
		else {
			return (DDLRecord)record;
		}
	}

	protected DDLRecordSet getRecordSet() throws PortalException {
		DDLRecord record = getRecord();

		if (record == null) {
			return null;
		}

		return record.getRecordSet();
	}

	protected void setDDMFormFieldReadOnly(DDMFormField ddmFormField) {
		ddmFormField.setReadOnly(true);

		for (DDMFormField nestedDDMFormField :
				ddmFormField.getNestedDDMFormFields()) {

			setDDMFormFieldReadOnly(nestedDDMFormField);
		}
	}

	private final DDLFormAdminRequestHelper _ddlFormAdminRequestHelper;
	private final DDLRecordLocalService _ddlRecordLocalService;
	private final DDMFormRenderer _ddmFormRenderer;
	private final DDMStructureLocalService _ddmStructureLocalService;
	private DDMStructure _ddmStucture;
	private final HttpServletRequest _httpServletRequest;
	private final HttpServletResponse _httpServletResponse;

}