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

package com.liferay.portal.workflow.kaleo.forms.web.workflow;

import com.liferay.dynamic.data.lists.model.DDLRecord;
import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.dynamic.data.lists.model.DDLRecordVersion;
import com.liferay.dynamic.data.lists.service.DDLRecordLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.WorkflowDefinitionLink;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.workflow.BaseWorkflowHandler;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowHandler;
import com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess;
import com.liferay.portal.workflow.kaleo.forms.service.KaleoProcessLocalService;
import com.liferay.portal.workflow.kaleo.forms.web.constants.KaleoFormsPortletKeys;

import java.io.Serializable;

import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.WindowState;
import javax.portlet.WindowStateException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marcellus Tavares
 */
@Component(immediate = true, service = WorkflowHandler.class)
public class KaleoProcessWorkflowHandler
	extends BaseWorkflowHandler<DDLRecord> {

	public static final String CLASS_NAME = KaleoProcess.class.getName();

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	public String getTitle(long classPK, Locale locale) {
		try {
			DDLRecord ddlRecord = _ddlRecordLocalService.getDDLRecord(classPK);

			KaleoProcess kaleoProcess =
				_kaleoProcessLocalService.getDDLRecordSetKaleoProcess(
					ddlRecord.getRecordSetId());

			DDLRecordSet ddlRecordSet = kaleoProcess.getDDLRecordSet();

			return ddlRecordSet.getName(locale);
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}
		}

		return null;
	}

	@Override
	public String getType(Locale locale) {
		return ResourceActionsUtil.getModelResource(locale, CLASS_NAME);
	}

	@Override
	public String getURLEditWorkflowTask(
			long workflowTaskId, ServiceContext serviceContext)
		throws PortalException {

		try {
			LiferayPortletURL liferayPortletURL = PortletURLFactoryUtil.create(
				serviceContext.getRequest(),
				KaleoFormsPortletKeys.KALEO_FORMS_DISPLAY,
				serviceContext.getPlid(), PortletRequest.RENDER_PHASE);

			String currentURL = liferayPortletURL.toString();

			liferayPortletURL.setParameter("tabs2", "edit-workflow-task");
			liferayPortletURL.setParameter("backURL", currentURL);
			liferayPortletURL.setParameter(
				"workflowTaskId", String.valueOf(workflowTaskId));
			liferayPortletURL.setWindowState(WindowState.NORMAL);

			return liferayPortletURL.toString();
		}
		catch (WindowStateException wse) {
			throw new PortalException(wse);
		}
	}

	@Override
	public WorkflowDefinitionLink getWorkflowDefinitionLink(
			long companyId, long groupId, long classPK)
		throws PortalException {

		DDLRecord ddlRecord = _ddlRecordLocalService.getRecord(classPK);

		KaleoProcess kaleoProcess =
			_kaleoProcessLocalService.getDDLRecordSetKaleoProcess(
				ddlRecord.getRecordSetId());

		return _workflowDefinitionLinkLocalService.fetchWorkflowDefinitionLink(
			companyId, groupId, getClassName(),
			kaleoProcess.getKaleoProcessId(), 0);
	}

	@Override
	public boolean isAssetTypeSearchable() {
		return false;
	}

	@Override
	public boolean isVisible() {
		return false;
	}

	@Override
	public DDLRecord updateStatus(
			int status, Map<String, Serializable> workflowContext)
		throws PortalException {

		long userId = GetterUtil.getLong(
			(String)workflowContext.get(WorkflowConstants.CONTEXT_USER_ID));

		long ddlRecordId = GetterUtil.getLong(
			(String)workflowContext.get(
				WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));

		DDLRecord record = _ddlRecordLocalService.getRecord(ddlRecordId);

		DDLRecordVersion recordVersion = record.getRecordVersion();

		ServiceContext serviceContext = (ServiceContext)workflowContext.get(
			"serviceContext");

		return _ddlRecordLocalService.updateStatus(
			userId, recordVersion.getRecordVersionId(), status, serviceContext);
	}

	@Reference(unbind = "-")
	protected void setDDLRecordLocalService(
		DDLRecordLocalService ddlRecordLocalService) {

		_ddlRecordLocalService = ddlRecordLocalService;
	}

	@Reference(unbind = "-")
	protected void setKaleoProcessLocalService(
		KaleoProcessLocalService kaleoProcessLocalService) {

		_kaleoProcessLocalService = kaleoProcessLocalService;
	}

	@Reference(unbind = "-")
	protected void setWorkflowDefinitionLinkLocalService(
		WorkflowDefinitionLinkLocalService workflowDefinitionLinkLocalService) {

		_workflowDefinitionLinkLocalService =
			workflowDefinitionLinkLocalService;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		KaleoProcessWorkflowHandler.class);

	private DDLRecordLocalService _ddlRecordLocalService;
	private KaleoProcessLocalService _kaleoProcessLocalService;
	private WorkflowDefinitionLinkLocalService
		_workflowDefinitionLinkLocalService;

}