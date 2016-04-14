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

package com.liferay.portal.workflow.kaleo.forms.web.portlet;

import com.liferay.dynamic.data.lists.exception.RecordSetDDMStructureIdException;
import com.liferay.dynamic.data.lists.exception.RecordSetNameException;
import com.liferay.dynamic.data.lists.exporter.DDLExporter;
import com.liferay.dynamic.data.lists.exporter.DDLExporterFactory;
import com.liferay.dynamic.data.lists.model.DDLRecord;
import com.liferay.dynamic.data.lists.service.DDLRecordService;
import com.liferay.dynamic.data.lists.util.DDL;
import com.liferay.dynamic.data.mapping.exception.RequiredStructureException;
import com.liferay.dynamic.data.mapping.exception.StructureDefinitionException;
import com.liferay.dynamic.data.mapping.io.DDMFormJSONDeserializer;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.storage.StorageEngine;
import com.liferay.dynamic.data.mapping.util.DDMDisplayRegistry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.WorkflowInstanceLink;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalService;
import com.liferay.portal.kernel.service.WorkflowInstanceLinkLocalService;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.TransactionConfig;
import com.liferay.portal.kernel.transaction.TransactionInvokerUtil;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.RequiredWorkflowDefinitionException;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowDefinitionManagerUtil;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;
import com.liferay.portal.kernel.workflow.WorkflowInstance;
import com.liferay.portal.kernel.workflow.WorkflowInstanceManagerUtil;
import com.liferay.portal.kernel.workflow.WorkflowTask;
import com.liferay.portal.kernel.workflow.WorkflowTaskManagerUtil;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.workflow.kaleo.designer.exception.DuplicateKaleoDraftDefinitionNameException;
import com.liferay.portal.workflow.kaleo.designer.exception.KaleoDraftDefinitionContentException;
import com.liferay.portal.workflow.kaleo.designer.exception.KaleoDraftDefinitionNameException;
import com.liferay.portal.workflow.kaleo.designer.exception.NoSuchKaleoDraftDefinitionException;
import com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition;
import com.liferay.portal.workflow.kaleo.designer.service.KaleoDraftDefinitionService;
import com.liferay.portal.workflow.kaleo.forms.constants.KaleoFormsWebKeys;
import com.liferay.portal.workflow.kaleo.forms.exception.KaleoProcessDDMTemplateIdException;
import com.liferay.portal.workflow.kaleo.forms.exception.NoSuchKaleoProcessException;
import com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess;
import com.liferay.portal.workflow.kaleo.forms.model.KaleoTaskFormPairs;
import com.liferay.portal.workflow.kaleo.forms.service.KaleoProcessService;
import com.liferay.portal.workflow.kaleo.forms.service.permission.KaleoProcessPermission;
import com.liferay.portal.workflow.kaleo.forms.web.constants.KaleoFormsPortletKeys;
import com.liferay.portal.workflow.kaleo.forms.web.display.context.KaleoFormsAdminDisplayContext;

import java.io.IOException;

import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marcellus Tavares
 * @author Eduardo Lundgren
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.css-class-wrapper=kaleo-forms-admin-portlet",
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.footer-portal-javascript=/o/dynamic-data-mapping-web/js/custom_fields.js",
		"com.liferay.portlet.footer-portal-javascript=/o/dynamic-data-mapping-web/js/main.js",
		"com.liferay.portlet.footer-portlet-javascript=/admin/js/components.js",
		"com.liferay.portlet.footer-portlet-javascript=/admin/js/main.js",
		"com.liferay.portlet.header-portal-css=/o/dynamic-data-mapping-web/css/main.css",
		"com.liferay.portlet.header-portlet-css=/admin/css/main.css",
		"com.liferay.portlet.preferences-owned-by-group=true",
		"com.liferay.portlet.private-request-attributes=false",
		"com.liferay.portlet.render-weight=12",
		"com.liferay.portlet.use-default-template=true",
		"javax.portlet.display-name=Kaleo Forms Admin Web",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.copy-request-parameters=true",
		"javax.portlet.init-param.template-path=/admin/",
		"javax.portlet.init-param.view-template=/admin/view.jsp",
		"javax.portlet.name="+ KaleoFormsPortletKeys.KALEO_FORMS_ADMIN,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator,power-user",
		"javax.portlet.supports.mime-type=text/html"
	},
	service = Portlet.class
)
public class KaleoFormsAdminPortlet extends MVCPortlet {

	public void deactivateWorkflowDefinition(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String name = ParamUtil.getString(actionRequest, "name");
		int version = ParamUtil.getInteger(actionRequest, "version");

		try {
			WorkflowDefinitionManagerUtil.updateActive(
				themeDisplay.getCompanyId(), themeDisplay.getUserId(), name,
				version, false);
		}
		catch (Exception e) {
			if (isSessionErrorException(e)) {
				if (_log.isDebugEnabled()) {
					_log.debug(e, e);
				}

				SessionErrors.add(actionRequest, e.getClass(), e);

				sendRedirect(actionRequest, actionResponse);
			}
			else {
				throw e;
			}
		}
	}

	public void deleteDDLRecord(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		final long ddlRecordId = ParamUtil.getLong(
			actionRequest, "ddlRecordId");

		final long workflowInstanceLinkId = getDDLRecordWorkfowInstanceLinkId(
			themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(),
			ddlRecordId);

		try {
			Callable<Void> callable = new Callable<Void>() {

				@Override
				public Void call() throws Exception {
					_ddlRecordService.deleteRecord(ddlRecordId);

					_workflowInstanceLinkLocalService.
						deleteWorkflowInstanceLink(workflowInstanceLinkId);

					return null;
				}

			};

			TransactionInvokerUtil.invoke(_transactionConfig, callable);
		}
		catch (Throwable t) {
			if (t instanceof PortalException) {
				throw (PortalException)t;
			}

			throw new SystemException(t);
		}
	}

	public void deleteKaleoDraftDefinition(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String name = ParamUtil.getString(actionRequest, "name");
		int version = ParamUtil.getInteger(actionRequest, "version");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			actionRequest);

		_kaleoDraftDefinitionService.deleteKaleoDraftDefinitions(
			name, version, serviceContext);
	}

	public void deleteKaleoProcess(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long kaleoProcessId = ParamUtil.getLong(
			actionRequest, "kaleoProcessId");

		_kaleoProcessService.deleteKaleoProcess(kaleoProcessId);
	}

	public void publishKaleoDraftDefinition(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String content = null;

		try {
			String backURL = ParamUtil.getString(actionRequest, "backURL");

			String name = ParamUtil.getString(actionRequest, "name");
			Map<Locale, String> titleMap = LocalizationUtil.getLocalizationMap(
				actionRequest, "title");
			content = ParamUtil.getString(actionRequest, "content");

			if (Validator.isNull(name)) {
				name = getName(
					content, titleMap.get(themeDisplay.getSiteDefaultLocale()));
			}

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				actionRequest);

			KaleoDraftDefinition kaleoDraftDefinition =
				_kaleoDraftDefinitionService.publishKaleoDraftDefinition(
					themeDisplay.getUserId(), themeDisplay.getCompanyGroupId(),
					name, titleMap, content, serviceContext);

			actionRequest.setAttribute(
				KaleoFormsWebKeys.KALEO_DRAFT_DEFINITION_ID,
				kaleoDraftDefinition.getKaleoDraftDefinitionId());
			actionRequest.setAttribute(WebKeys.REDIRECT, backURL);

			saveInPortletSession(actionRequest, kaleoDraftDefinition);
		}
		catch (Exception e) {
			if (isSessionErrorException(e)) {
				if (_log.isDebugEnabled()) {
					_log.debug(e, e);
				}

				SessionErrors.add(actionRequest, e.getClass(), e);

				actionRequest.setAttribute(
					KaleoFormsWebKeys.KALEO_DRAFT_DEFINITION_CONTENT, content);
			}
			else {
				throw e;
			}
		}
	}

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		try {
			setDisplayContext(renderRequest);

			renderKaleoProcess(renderRequest, renderResponse);
		}
		catch (Exception e) {
			if (e instanceof NoSuchKaleoProcessException ||
				e instanceof PrincipalException ||
				e instanceof WorkflowException) {

				SessionErrors.add(renderRequest, e.getClass());
			}
			else {
				throw new PortletException(e);
			}
		}

		super.render(renderRequest, renderResponse);
	}

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException, PortletException {

		try {
			String resourceID = resourceRequest.getResourceID();

			if (resourceID.equals("kaleoDraftDefinitions")) {
				serveKaleoDraftDefinitions(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("kaleoProcess")) {
				serveKaleoProcess(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("saveInPortletSession")) {
				saveInPortletSession(resourceRequest, resourceResponse);
			}
		}
		catch (IOException ioe) {
			throw ioe;
		}
		catch (PortletException pe) {
			throw pe;
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	public void startWorkflowInstance(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			DDLRecord.class.getName(), uploadPortletRequest);

		checkKaleoProcessPermission(serviceContext, ActionKeys.SUBMIT);

		DDLRecord ddlRecord = updateDDLRecord(serviceContext);

		WorkflowHandlerRegistryUtil.startWorkflowInstance(
			serviceContext.getCompanyId(), serviceContext.getScopeGroupId(),
			serviceContext.getUserId(), KaleoProcess.class.getName(),
			ddlRecord.getRecordId(), ddlRecord, serviceContext);
	}

	public void updateKaleoDraftDefinition(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String content = null;

		try {
			long kaleoDraftDefinitionId = ParamUtil.getLong(
				actionRequest, "kaleoDraftDefinitionId");

			Map<Locale, String> titleMap = LocalizationUtil.getLocalizationMap(
				actionRequest, "title");
			content = ParamUtil.getString(actionRequest, "content");
			int version = ParamUtil.getInteger(actionRequest, "version");

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				actionRequest);

			KaleoDraftDefinition kaleoDraftDefinition = null;

			if (kaleoDraftDefinitionId <= 0) {
				String name = getName(
					content, titleMap.get(themeDisplay.getSiteDefaultLocale()));

				kaleoDraftDefinition =
					_kaleoDraftDefinitionService.addKaleoDraftDefinition(
						themeDisplay.getUserId(),
						themeDisplay.getCompanyGroupId(), name, titleMap,
						content, version, 1, serviceContext);
			}
			else {
				String name = ParamUtil.getString(actionRequest, "name");

				kaleoDraftDefinition =
					_kaleoDraftDefinitionService.updateKaleoDraftDefinition(
						themeDisplay.getUserId(), name, titleMap, content,
						version, serviceContext);
			}

			actionRequest.setAttribute(
				KaleoFormsWebKeys.KALEO_DRAFT_DEFINITION_ID,
				kaleoDraftDefinition.getKaleoDraftDefinitionId());
		}
		catch (Exception e) {
			if (isSessionErrorException(e)) {
				if (_log.isDebugEnabled()) {
					_log.debug(e, e);
				}

				SessionErrors.add(actionRequest, e.getClass(), e);

				actionRequest.setAttribute(
					KaleoFormsWebKeys.KALEO_DRAFT_DEFINITION_CONTENT, content);
			}
			else {
				throw e;
			}
		}
	}

	public void updateKaleoProcess(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long kaleoProcessId = ParamUtil.getLong(
			actionRequest, "kaleoProcessId");

		long groupId = ParamUtil.getLong(actionRequest, "groupId");
		long ddmStructureId = ParamUtil.getLong(
			actionRequest, "ddmStructureId");
		Map<Locale, String> nameMap = LocalizationUtil.getLocalizationMap(
			actionRequest, "name");
		Map<Locale, String> descriptionMap =
			LocalizationUtil.getLocalizationMap(actionRequest, "description");
		long ddmTemplateId = ParamUtil.getLong(actionRequest, "ddmTemplateId");
		String workflowDefinitionName = ParamUtil.getString(
			actionRequest, "workflowDefinitionName");
		int workflowDefinitionVersion = ParamUtil.getInteger(
			actionRequest, "workflowDefinitionVersion");
		String kaleoTaskFormPairsData = ParamUtil.getString(
			actionRequest, "kaleoTaskFormPairsData");

		KaleoTaskFormPairs kaleoKaleoTaskFormPairs = KaleoTaskFormPairs.parse(
			kaleoTaskFormPairsData);

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			KaleoProcess.class.getName(), actionRequest);

		KaleoProcess kaleoProcess = null;

		if (kaleoProcessId <= 0) {
			kaleoProcess = _kaleoProcessService.addKaleoProcess(
				groupId, ddmStructureId, nameMap, descriptionMap, ddmTemplateId,
				workflowDefinitionName, workflowDefinitionVersion,
				kaleoKaleoTaskFormPairs, serviceContext);
		}
		else {
			kaleoProcess = _kaleoProcessService.updateKaleoProcess(
				kaleoProcessId, ddmStructureId, nameMap, descriptionMap,
				ddmTemplateId, workflowDefinitionName,
				workflowDefinitionVersion, kaleoKaleoTaskFormPairs,
				serviceContext);
		}

		String workflowDefinition = ParamUtil.getString(
			actionRequest, "workflowDefinition");

		_workflowDefinitionLinkLocalService.updateWorkflowDefinitionLink(
			serviceContext.getUserId(), serviceContext.getCompanyId(), groupId,
			KaleoProcess.class.getName(), kaleoProcess.getKaleoProcessId(), 0,
			workflowDefinition);
	}

	protected void checkKaleoProcessPermission(
			ServiceContext serviceContext, String actionId)
		throws Exception {

		HttpServletRequest request = serviceContext.getRequest();

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		long kaleoProcessId = ParamUtil.getLong(request, "kaleoProcessId");

		KaleoProcessPermission.check(
			permissionChecker, kaleoProcessId, actionId);
	}

	@Override
	protected void doDispatch(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		if (SessionErrors.contains(
				renderRequest, NoSuchKaleoProcessException.class.getName()) ||
			SessionErrors.contains(
				renderRequest, PrincipalException.getNestedClasses()) ||
			SessionErrors.contains(
				renderRequest, WorkflowException.class.getName())) {

			include(templatePath + "error.jsp", renderRequest, renderResponse);
		}
		else {
			super.doDispatch(renderRequest, renderResponse);
		}
	}

	protected long getDDLRecordWorkfowInstanceLinkId(
			long companyId, long groupId, long ddlRecordId)
		throws Exception {

		WorkflowInstanceLink workfowInstanceLink =
			_workflowInstanceLinkLocalService.getWorkflowInstanceLink(
				companyId, groupId, KaleoProcess.class.getName(), ddlRecordId);

		return workfowInstanceLink.getWorkflowInstanceLinkId();
	}

	protected DDMForm getDDMForm(ActionRequest actionRequest) throws Exception {
		String definition = ParamUtil.getString(actionRequest, "definition");

		return _ddmFormJSONDeserializer.deserialize(definition);
	}

	protected String getName(String content, String defaultName) {
		if (Validator.isNull(content)) {
			return defaultName;
		}

		try {
			Document document = SAXReaderUtil.read(content);

			Element rootElement = document.getRootElement();

			return rootElement.elementText("name");
		}
		catch (DocumentException de) {
			return defaultName;
		}
	}

	@Override
	protected boolean isSessionErrorException(Throwable cause) {
		if (cause instanceof DuplicateKaleoDraftDefinitionNameException ||
			cause instanceof KaleoDraftDefinitionContentException ||
			cause instanceof KaleoDraftDefinitionNameException ||
			cause instanceof KaleoProcessDDMTemplateIdException ||
			cause instanceof NoSuchKaleoDraftDefinitionException ||
			cause instanceof RecordSetDDMStructureIdException ||
			cause instanceof RecordSetNameException ||
			cause instanceof RequiredStructureException ||
			cause instanceof RequiredWorkflowDefinitionException ||
			cause instanceof StructureDefinitionException ||
			cause instanceof WorkflowException) {

			return true;
		}

		return false;
	}

	protected void renderKaleoProcess(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long kaleoProcessId = ParamUtil.getLong(
			renderRequest, "kaleoProcessId");

		if (kaleoProcessId > 0) {
			KaleoProcess kaleoProcess = _kaleoProcessService.getKaleoProcess(
				kaleoProcessId);

			renderRequest.setAttribute(
				KaleoFormsWebKeys.KALEO_PROCESS, kaleoProcess);
		}

		long workflowInstanceId = ParamUtil.getLong(
			renderRequest, "workflowInstanceId");

		if (workflowInstanceId > 0) {
			WorkflowInstance workflowInstance =
				WorkflowInstanceManagerUtil.getWorkflowInstance(
					themeDisplay.getCompanyId(), workflowInstanceId);

			renderRequest.setAttribute(
				KaleoFormsWebKeys.WORKFLOW_INSTANCE, workflowInstance);
		}

		long workflowTaskId = ParamUtil.getLong(
			renderRequest, "workflowTaskId");

		if (workflowTaskId > 0) {
			WorkflowTask workflowTask = WorkflowTaskManagerUtil.getWorkflowTask(
				themeDisplay.getCompanyId(), workflowTaskId);

			renderRequest.setAttribute(
				KaleoFormsWebKeys.WORKFLOW_TASK, workflowTask);
		}
	}

	protected void saveInPortletSession(
		ActionRequest actionRequest,
		KaleoDraftDefinition kaleoDraftDefinition) {

		PortletSession portletSession = actionRequest.getPortletSession();

		portletSession.setAttribute(
			"workflowDefinition",
			kaleoDraftDefinition.getName() + StringPool.AT +
				kaleoDraftDefinition.getVersion());
	}

	protected void saveInPortletSession(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		Enumeration<String> enumeration = resourceRequest.getParameterNames();

		while (enumeration.hasMoreElements()) {
			String name = enumeration.nextElement();

			if (name.equals("doAsUserId")) {
				continue;
			}

			PortletSession portletSession = resourceRequest.getPortletSession();

			String value = ParamUtil.getString(resourceRequest, name);

			portletSession.setAttribute(name, value);
		}
	}

	protected void serveKaleoDraftDefinitions(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String name = ParamUtil.getString(resourceRequest, "name");
		int version = ParamUtil.getInteger(resourceRequest, "version");
		int draftVersion = ParamUtil.getInteger(
			resourceRequest, "draftVersion");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			resourceRequest);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		if (Validator.isNotNull(name) && (draftVersion > 0)) {
			KaleoDraftDefinition kaleoDraftDefinition =
				_kaleoDraftDefinitionService.getKaleoDraftDefinition(
					name, version, draftVersion, serviceContext);

			jsonObject.put("content", kaleoDraftDefinition.getContent());
			jsonObject.put(
				"draftVersion", kaleoDraftDefinition.getDraftVersion());
			jsonObject.put("name", kaleoDraftDefinition.getName());
			jsonObject.put(
				"title",
				kaleoDraftDefinition.getTitle(themeDisplay.getLocale()));
			jsonObject.put("version", kaleoDraftDefinition.getVersion());
		}

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

	protected void serveKaleoProcess(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long kaleoProcessId = ParamUtil.getLong(
			resourceRequest, "kaleoProcessId");

		KaleoProcess kaleoProcess = _kaleoProcessService.getKaleoProcess(
			kaleoProcessId);

		String fileExtension = ParamUtil.getString(
			resourceRequest, "fileExtension");

		String fileName =
			kaleoProcess.getName(themeDisplay.getLocale()) + CharPool.PERIOD +
				fileExtension;

		int status = WorkflowConstants.STATUS_ANY;

		boolean exportOnlyApproved = ParamUtil.getBoolean(
			resourceRequest, "exportOnlyApproved");

		if (exportOnlyApproved) {
			status = WorkflowConstants.STATUS_APPROVED;
		}

		DDLExporter ddlExporter = _ddlExporterFactory.getDDLExporter(
			fileExtension);

		ddlExporter.setLocale(themeDisplay.getLocale());

		byte[] bytes = ddlExporter.export(
			kaleoProcess.getDDLRecordSetId(), status);

		String contentType = MimeTypesUtil.getContentType(fileName);

		PortletResponseUtil.sendFile(
			resourceRequest, resourceResponse, fileName, bytes, contentType);
	}

	@Reference(unbind = "-")
	protected void setDDL(DDL ddl) {
		_ddl = ddl;
	}

	@Reference(unbind = "-")
	protected void setDDLExporterFactory(
		DDLExporterFactory ddlExporterFactory) {

		_ddlExporterFactory = ddlExporterFactory;
	}

	@Reference(unbind = "-")
	protected void setDDLRecordService(DDLRecordService ddlRecordService) {
		_ddlRecordService = ddlRecordService;
	}

	@Reference(unbind = "-")
	protected void setDDMDisplayRegistry(
		DDMDisplayRegistry ddmDisplayRegistry) {

		_ddmDisplayRegistry = ddmDisplayRegistry;
	}

	@Reference(unbind = "-")
	protected void setDDMFormJSONDeserializer(
		DDMFormJSONDeserializer ddmFormJSONDeserializer) {

		_ddmFormJSONDeserializer = ddmFormJSONDeserializer;
	}

	protected void setDisplayContext(RenderRequest renderRequest) {
		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			renderRequest);

		KaleoFormsAdminDisplayContext kaleoFormsAdminDisplayContext =
			new KaleoFormsAdminDisplayContext(
				request, _ddmDisplayRegistry, storageEngine);

		renderRequest.setAttribute(
			WebKeys.PORTLET_DISPLAY_CONTEXT, kaleoFormsAdminDisplayContext);
	}

	@Reference(unbind = "-")
	protected void setKaleoDraftDefinitionService(
		KaleoDraftDefinitionService kaleoDraftDefinitionService) {

		_kaleoDraftDefinitionService = kaleoDraftDefinitionService;
	}

	@Reference(unbind = "-")
	protected void setKaleoProcessService(
		KaleoProcessService kaleoProcessService) {

		_kaleoProcessService = kaleoProcessService;
	}

	@Reference(unbind = "-")
	protected void setStorageEngine(StorageEngine storageEngine) {
		this.storageEngine = storageEngine;
	}

	@Reference(unbind = "-")
	protected void setWorkflowDefinitionLinkLocalService(
		WorkflowDefinitionLinkLocalService workflowDefinitionLinkLocalService) {

		_workflowDefinitionLinkLocalService =
			workflowDefinitionLinkLocalService;
	}

	@Reference(unbind = "-")
	protected void setWorkflowInstanceLinkLocalService(
		WorkflowInstanceLinkLocalService workflowInstanceLinkLocalService) {

		_workflowInstanceLinkLocalService = workflowInstanceLinkLocalService;
	}

	protected DDLRecord updateDDLRecord(ServiceContext serviceContext)
		throws Exception {

		HttpServletRequest request = serviceContext.getRequest();

		long ddlRecordId = ParamUtil.getLong(request, "ddlRecordId");

		long ddlRecordSetId = ParamUtil.getLong(request, "ddlRecordSetId");

		return _ddl.updateRecord(
			ddlRecordId, ddlRecordSetId, true, false, serviceContext);
	}

	protected StorageEngine storageEngine;

	private static final Log _log = LogFactoryUtil.getLog(
		KaleoFormsAdminPortlet.class);

	private static final TransactionConfig _transactionConfig =
		TransactionConfig.Factory.create(
			Propagation.REQUIRES_NEW, new Class<?>[] {Exception.class});

	private DDL _ddl;
	private DDLExporterFactory _ddlExporterFactory;
	private DDLRecordService _ddlRecordService;
	private DDMDisplayRegistry _ddmDisplayRegistry;
	private DDMFormJSONDeserializer _ddmFormJSONDeserializer;
	private KaleoDraftDefinitionService _kaleoDraftDefinitionService;
	private KaleoProcessService _kaleoProcessService;
	private WorkflowDefinitionLinkLocalService
		_workflowDefinitionLinkLocalService;
	private WorkflowInstanceLinkLocalService _workflowInstanceLinkLocalService;

}