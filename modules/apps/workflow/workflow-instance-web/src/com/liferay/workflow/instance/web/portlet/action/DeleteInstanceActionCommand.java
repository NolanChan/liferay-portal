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
package com.liferay.workflow.instance.web.portlet.action;

import java.io.Serializable;
import java.util.Map;

import javax.portlet.PortletContext;
import javax.portlet.PortletRequest;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.PortletResponse;
import javax.portlet.PortletSession;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.bridges.mvc.ActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.kernel.workflow.WorkflowHandler;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;
import com.liferay.portal.kernel.workflow.WorkflowInstance;
import com.liferay.portal.kernel.workflow.WorkflowInstanceManagerUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.WorkflowInstanceLinkLocalServiceUtil;
import com.liferay.portal.service.permission.PortletPermissionUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.workflow.instance.web.portlet.constants.WorkflowInstancePortletKeys;

/**
 * @author Leonardo Barros
 */
@Component(immediate = true,
	property = {
		"action.command.name=deleteInstance",
		"javax.portlet.name=" + WorkflowInstancePortletKeys.WORKFLOW_INSTANCE
	},
	service = ActionCommand.class)
public class DeleteInstanceActionCommand extends BaseActionCommand {

	@Override
	protected void doProcessCommand(PortletRequest portletRequest,
			PortletResponse portletResponse) throws Exception {

		try {
			String redirect = deleteInstance(portletRequest);
			portletRequest.setAttribute(WebKeys.REDIRECT, redirect);
		} catch (Exception e) {
			if (e instanceof PrincipalException ||
				e instanceof WorkflowException) {
				
				SessionErrors.add(portletRequest, e.getClass());

				PortletSession portletSession =
					portletRequest.getPortletSession();

				PortletContext portletContext =
					portletSession.getPortletContext();

				PortletRequestDispatcher portletRequestDispatcher =
					portletContext.getRequestDispatcher("/error.jsp");

				portletRequestDispatcher.include(
					portletRequest, portletResponse);
			
			} else {
				throw e;
			}
		}
	}
	
	protected String deleteInstance(PortletRequest portletRequest)
			throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest
			.getAttribute(WebKeys.THEME_DISPLAY);

		long workflowInstanceId = ParamUtil.getLong(portletRequest,
			"workflowInstanceId");

		WorkflowInstance workflowInstance = WorkflowInstanceManagerUtil
				.getWorkflowInstance(themeDisplay.getCompanyId(),
					workflowInstanceId);

		Map<String, Serializable> workflowContext = workflowInstance
			.getWorkflowContext();

		long companyId = GetterUtil.getLong(workflowContext
			.get(WorkflowConstants.CONTEXT_COMPANY_ID));
		long userId = GetterUtil.getLong(workflowContext
			.get(WorkflowConstants.CONTEXT_USER_ID));
		long groupId = GetterUtil.getLong(workflowContext
			.get(WorkflowConstants.CONTEXT_GROUP_ID));
		String className = GetterUtil.getString(workflowContext
			.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_NAME));
		long classPK = GetterUtil.getLong(workflowContext
			.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));

		WorkflowHandler<?> workflowHandler = WorkflowHandlerRegistryUtil
			.getWorkflowHandler(className);

		workflowHandler.updateStatus(WorkflowConstants.STATUS_DRAFT,
			workflowContext);

		WorkflowInstanceLinkLocalServiceUtil.deleteWorkflowInstanceLink(
			companyId, groupId, className, classPK);

		Layout layout = themeDisplay.getLayout();

		Group layoutGroup = layout.getGroup();

		if (layoutGroup.isControlPanel() &&
			(WorkflowInstanceManagerUtil.getWorkflowInstanceCount(
				companyId, userId, null, null, null) == 0)) {

			PermissionChecker permissionChecker = themeDisplay
				.getPermissionChecker();

			String portletId = PortalUtil.getPortletId(portletRequest);

			if (!PortletPermissionUtil.hasControlPanelAccessPermission(
					permissionChecker, groupId, portletId)) {

				return themeDisplay.getURLControlPanel();
			}
		}

		return null;
	}

}