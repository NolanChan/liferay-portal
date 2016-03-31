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

package com.liferay.portal.workflow.kaleo.forms.web.asset;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseJSPAssetRenderer;
import com.liferay.dynamic.data.lists.constants.DDLWebKeys;
import com.liferay.dynamic.data.lists.model.DDLRecord;
import com.liferay.dynamic.data.lists.model.DDLRecordVersion;
import com.liferay.portal.kernel.dao.search.ResultRow;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowTask;
import com.liferay.portal.workflow.kaleo.forms.constants.KaleoFormsActionKeys;
import com.liferay.portal.workflow.kaleo.forms.constants.KaleoFormsWebKeys;
import com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess;
import com.liferay.portal.workflow.kaleo.forms.service.permission.KaleoProcessPermission;
import com.liferay.portal.workflow.kaleo.forms.web.constants.KaleoFormsPortletKeys;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Inácio Nery
 */
public class KaleoProcessAssetRenderer
	extends BaseJSPAssetRenderer<KaleoProcess> {

	public KaleoProcessAssetRenderer(
		KaleoProcess kaleoProcess, DDLRecord record,
		DDLRecordVersion recordVersion) {

		_kaleoProcess = kaleoProcess;
		_record = record;
		_recordVersion = recordVersion;
	}

	@Override
	public KaleoProcess getAssetObject() {
		return _kaleoProcess;
	}

	@Override
	public AssetRendererFactory<KaleoProcess> getAssetRendererFactory() {
		return new KaleoProcessAssetRendererFactory();
	}

	@Override
	public String getClassName() {
		return KaleoProcess.class.getName();
	}

	@Override
	public long getClassPK() {
		return _kaleoProcess.getKaleoProcessId();
	}

	@Override
	public long getGroupId() {
		return _kaleoProcess.getGroupId();
	}

	@Override
	public String getJspPath(HttpServletRequest request, String template) {
		if (template.equals(TEMPLATE_ABSTRACT) ||
			template.equals(TEMPLATE_FULL_CONTENT)) {

			return "/asset/full_content.jsp";
		}
		else {
			return null;
		}
	}

	@Override
	public int getStatus() {
		return _recordVersion.getStatus();
	}

	@Override
	public String getSummary(
		PortletRequest portletRequest, PortletResponse portletResponse) {

		return StringPool.BLANK;
	}

	@Override
	public String getTitle(Locale locale) {
		String kaleoProcessName = StringPool.BLANK;

		try {
			kaleoProcessName = _kaleoProcess.getName(locale);
		}
		catch (PortalException pe) {
			if (_log.isWarnEnabled()) {
				_log.warn(pe, pe);
			}
		}

		return kaleoProcessName;
	}

	@Override
	public PortletURL getURLEdit(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse)
		throws Exception {

		PortletURL portletURL = PortalUtil.getControlPanelPortletURL(
			liferayPortletRequest, KaleoFormsPortletKeys.KALEO_FORMS_ADMIN,
			PortletRequest.RENDER_PHASE);

		portletURL.setParameter(
			"kaleoProcessId",
			String.valueOf(_kaleoProcess.getKaleoProcessId()));
		portletURL.setParameter("mvcPath", "/admin/edit_record.jsp");
		portletURL.setParameter(
			"ddlRecordId", String.valueOf(_record.getRecordId()));

		return portletURL;
	}

	@Override
	public String getURLViewInContext(
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse,
		String noSuchEntryRedirect) {

		try {
			String currentURL = PortalUtil.getCurrentURL(liferayPortletRequest);

			PortletURL portletURL = liferayPortletResponse.createRenderURL();

			AssetRendererFactory<KaleoProcess> assetRendererFactory =
				getAssetRendererFactory();

			AssetEntry assetEntry = assetRendererFactory.getAssetEntry(
				getClassName(), getClassPK());

			ResultRow resultRow = (ResultRow)liferayPortletRequest.getAttribute(
				WebKeys.SEARCH_CONTAINER_RESULT_ROW);

			if (Validator.isNotNull(resultRow)) {
				ThemeDisplay themeDisplay =
					(ThemeDisplay)liferayPortletRequest.getAttribute(
						WebKeys.THEME_DISPLAY);

				WorkflowTask workflowTask =
					(WorkflowTask)resultRow.getParameter("workflowTask");

				if ((workflowTask.getAssigneeUserId() ==
						themeDisplay.getUserId()) &&
					!workflowTask.isCompleted()) {

					portletURL.setParameter("showEditURL", "true");
				}
			}

			portletURL.setParameter("mvcPath", "/view_content.jsp");
			portletURL.setParameter("redirect", currentURL);
			portletURL.setParameter(
				"assetEntryId", String.valueOf(assetEntry.getEntryId()));
			portletURL.setParameter(
				"assetEntryClassPK", String.valueOf(_record.getRecordId()));
			portletURL.setParameter(
				"assetEntryVersionId",
				String.valueOf(_recordVersion.getRecordId()));
			portletURL.setParameter("type", assetRendererFactory.getType());

			return portletURL.toString();
		}
		catch (PortalException pe) {
			if (_log.isWarnEnabled()) {
				_log.warn(pe, pe);
			}
		}

		return noSuchEntryRedirect;
	}

	@Override
	public long getUserId() {
		return _kaleoProcess.getUserId();
	}

	@Override
	public String getUserName() {
		return _kaleoProcess.getUserName();
	}

	@Override
	public String getUuid() {
		return _kaleoProcess.getUuid();
	}

	@Override
	public boolean hasEditPermission(PermissionChecker permissionChecker) {
		return KaleoProcessPermission.contains(
			permissionChecker, _kaleoProcess,
			KaleoFormsActionKeys.COMPLETE_FORM);
	}

	@Override
	public boolean hasViewPermission(PermissionChecker permissionChecker) {
		return KaleoProcessPermission.contains(
			permissionChecker, _kaleoProcess,
			KaleoFormsActionKeys.COMPLETE_FORM);
	}

	@Override
	public boolean include(
			HttpServletRequest request, HttpServletResponse response,
			String template)
		throws Exception {

		request.setAttribute(DDLWebKeys.DYNAMIC_DATA_LISTS_RECORD, _record);
		request.setAttribute(
			DDLWebKeys.DYNAMIC_DATA_LISTS_RECORD_VERSION, _recordVersion);
		request.setAttribute(KaleoFormsWebKeys.KALEO_PROCESS, _kaleoProcess);

		return super.include(request, response, template);
	}

	@Override
	public boolean isPreviewInContext() {
		return true;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		KaleoProcessAssetRenderer.class);

	private final KaleoProcess _kaleoProcess;
	private final DDLRecord _record;
	private final DDLRecordVersion _recordVersion;

}