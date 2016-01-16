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

package com.liferay.workflow.kaleo.forms.portlet;

import com.liferay.dynamic.data.lists.model.DDLRecord;
import com.liferay.dynamic.data.lists.service.DDLRecordService;
import com.liferay.dynamic.data.mapping.service.DDMStructureService;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowInstanceManagerUtil;
import com.liferay.portal.kernel.workflow.WorkflowTaskDueDateException;
import com.liferay.portal.kernel.workflow.WorkflowTaskManagerUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.WorkflowDefinitionLinkLocalService;
import com.liferay.portal.service.WorkflowInstanceLinkLocalService;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.workflow.kaleo.designer.service.KaleoDraftDefinitionService;
import com.liferay.workflow.kaleo.forms.constants.KaleoFormsActionKeys;
import com.liferay.workflow.kaleo.forms.constants.KaleoFormsPortletKeys;
import com.liferay.workflow.kaleo.forms.service.KaleoProcessService;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marcellus Tavares
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.css-class-wrapper=kaleo-forms-display-portlet",
		"com.liferay.portlet.display-category=category.workflow",
		"com.liferay.portlet.footer-portal-javascript=/o/workflow-task-web/js/main.js",
		"com.liferay.portlet.footer-portlet-javascript=/display/js/main.js",
		"com.liferay.portlet.header-portal-css=/o/workflow-definition-web/css/main.css",
		"com.liferay.portlet.header-portlet-css=/display/css/main.css",
		"com.liferay.portlet.preferences-owned-by-group=true",
		"com.liferay.portlet.private-request-attributes=false",
		"com.liferay.portlet.private-session-attributes=false",
		"com.liferay.portlet.use-default-template=true",
		"javax.portlet.display-name=Kaleo Forms Web",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.copy-request-parameters=true",
		"javax.portlet.init-param.template-path=/display/",
		"javax.portlet.init-param.view-template=/display/view.jsp",
		"javax.portlet.name="+ KaleoFormsPortletKeys.KALEO_FORMS_DISPLAY,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator,power-user",
		"javax.portlet.supports.mime-type=text/html"
	},
	service = Portlet.class
)
public class KaleoFormsDisplayPortlet extends KaleoFormsAdminPortlet {

	public void assignWorkflowTask(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long workflowTaskId = ParamUtil.getLong(
			actionRequest, "workflowTaskId");
		long assigneeUserId = ParamUtil.getLong(
			actionRequest, "assigneeUserId");
		String comment = HtmlUtil.stripHtml(
			ParamUtil.getString(actionRequest, "comment"));

		WorkflowTaskManagerUtil.assignWorkflowTaskToUser(
			themeDisplay.getCompanyId(), themeDisplay.getUserId(),
			workflowTaskId, assigneeUserId, comment, null, null);
	}

	public void completeForm(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			DDLRecord.class.getName(), uploadPortletRequest);

		checkKaleoProcessPermission(
			serviceContext, KaleoFormsActionKeys.COMPLETE_FORM);

		updateDDLRecord(serviceContext);

		long workflowTaskId = ParamUtil.getLong(
			uploadPortletRequest, "workflowTaskId");

		List<String> transitionNames =
			WorkflowTaskManagerUtil.getNextTransitionNames(
				serviceContext.getCompanyId(), serviceContext.getUserId(),
				workflowTaskId);

		if (transitionNames.size() == 1) {
			WorkflowTaskManagerUtil.completeWorkflowTask(
				serviceContext.getCompanyId(), serviceContext.getUserId(),
				workflowTaskId, null, null, null);
		}
	}

	public void completeWorkflowTask(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long workflowTaskId = ParamUtil.getLong(
			actionRequest, "workflowTaskId");

		String transitionName = ParamUtil.getString(
			actionRequest, "transitionName");
		String comment = HtmlUtil.stripHtml(
			ParamUtil.getString(actionRequest, "comment"));

		WorkflowTaskManagerUtil.completeWorkflowTask(
			themeDisplay.getCompanyId(), themeDisplay.getUserId(),
			workflowTaskId, transitionName, comment, null);
	}

	public void deleteWorkflowInstance(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long workflowInstanceId = ParamUtil.getLong(
			actionRequest, "workflowInstanceId");

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			themeDisplay.getCompanyId(), workflowInstanceId);
	}

	public void updateWorkflowTask(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long workflowTaskId = ParamUtil.getLong(
			actionRequest, "workflowTaskId");

		String comment = HtmlUtil.stripHtml(
			ParamUtil.getString(actionRequest, "comment"));

		int dueDateMonth = ParamUtil.getInteger(actionRequest, "dueDateMonth");
		int dueDateDay = ParamUtil.getInteger(actionRequest, "dueDateDay");
		int dueDateYear = ParamUtil.getInteger(actionRequest, "dueDateYear");
		int dueDateHour = ParamUtil.getInteger(actionRequest, "dueDateHour");
		int dueDateMinute = ParamUtil.getInteger(
			actionRequest, "dueDateMinute");
		int dueDateAmPm = ParamUtil.getInteger(actionRequest, "dueDateAmPm");

		if (dueDateAmPm == Calendar.PM) {
			dueDateHour += 12;
		}

		Date dueDate = PortalUtil.getDate(
			dueDateMonth, dueDateDay, dueDateYear, dueDateHour, dueDateMinute,
			WorkflowTaskDueDateException.class);

		WorkflowTaskManagerUtil.updateDueDate(
			themeDisplay.getCompanyId(), themeDisplay.getUserId(),
			workflowTaskId, comment, dueDate);
	}

	@Override
	@Reference(unbind = "-")
	protected void setDDLRecordService(DDLRecordService ddlRecordService) {
		super.setDDLRecordService(ddlRecordService);
	}

	@Override
	@Reference(unbind = "-")
	protected void setDDMStructureService(
		DDMStructureService ddmStructureService) {

		super.setDDMStructureService(ddmStructureService);
	}

	@Override
	@Reference(unbind = "-")
	protected void setKaleoDraftDefinitionService(
		KaleoDraftDefinitionService kaleoDraftDefinitionService) {

		super.setKaleoDraftDefinitionService(kaleoDraftDefinitionService);
	}

	@Override
	@Reference(unbind = "-")
	protected void setKaleoProcessService(
		KaleoProcessService kaleoProcessService) {

		super.setKaleoProcessService(kaleoProcessService);
	}

	@Override
	@Reference(unbind = "-")
	protected void setWorkflowDefinitionLinkLocalService(
		WorkflowDefinitionLinkLocalService workflowDefinitionLinkLocalService) {

		super.setWorkflowDefinitionLinkLocalService(
			workflowDefinitionLinkLocalService);
	}

	@Override
	@Reference(unbind = "-")
	protected void setWorkflowInstanceLinkLocalService(
		WorkflowInstanceLinkLocalService workflowInstanceLinkLocalService) {

		super.setWorkflowInstanceLinkLocalService(
			workflowInstanceLinkLocalService);
	}

}