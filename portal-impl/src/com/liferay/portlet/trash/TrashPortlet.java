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

package com.liferay.portlet.trash;

import com.liferay.portal.TrashPermissionException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.trash.TrashHandler;
import com.liferay.portal.kernel.trash.TrashHandlerRegistryUtil;
import com.liferay.portal.kernel.trash.TrashRenderer;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.trash.action.ActionUtil;
import com.liferay.portlet.trash.model.TrashEntry;
import com.liferay.portlet.trash.service.TrashEntryServiceUtil;
import com.liferay.portlet.trash.util.TrashUtil;

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

/**
 * @author Eudaldo Alonso
 */
public class TrashPortlet extends MVCPortlet {

	public void deleteEntries(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long trashEntryId = ParamUtil.getLong(actionRequest, "trashEntryId");

		if (trashEntryId > 0) {
			TrashEntryServiceUtil.deleteEntry(trashEntryId);

			return;
		}

		long[] deleteEntryIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "deleteThrashEntryIds"), 0L);

		if (deleteEntryIds.length > 0) {
			for (int i = 0; i < deleteEntryIds.length; i++) {
				TrashEntryServiceUtil.deleteEntry(deleteEntryIds[i]);
			}

			return;
		}

		String className = ParamUtil.getString(actionRequest, "className");
		long classPK = ParamUtil.getLong(actionRequest, "classPK");

		if (Validator.isNotNull(className) && (classPK > 0)) {
			TrashEntryServiceUtil.deleteEntry(className, classPK);
		}

		sendRedirect(actionRequest, actionResponse);
	}

	public void emptyTrash(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long groupId = ParamUtil.getLong(
			actionRequest, "groupId", themeDisplay.getScopeGroupId());

		TrashEntryServiceUtil.deleteEntries(groupId);
	}

	public void moveEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long containerModelId = ParamUtil.getLong(
			actionRequest, "containerModelId");
		String className = ParamUtil.getString(actionRequest, "className");
		long classPK = ParamUtil.getLong(actionRequest, "classPK");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			className, actionRequest);

		TrashEntryServiceUtil.moveEntry(
			className, classPK, containerModelId, serviceContext);

		addRestoreData(actionRequest, getEntryOVPs(className, classPK));

		sendRedirect(actionRequest, actionResponse);
	}

	public void restoreEntries(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		List<ObjectValuePair<String, Long>> entryOVPs = new ArrayList<>();

		long trashEntryId = ParamUtil.getLong(actionRequest, "trashEntryId");

		if (trashEntryId > 0) {
			TrashEntry entry = TrashEntryServiceUtil.restoreEntry(trashEntryId);

			entryOVPs = getEntryOVPs(entry.getClassName(), entry.getClassPK());
		}
		else {
			long[] restoreEntryIds = StringUtil.split(
				ParamUtil.getString(actionRequest, "restoreTrashEntryIds"), 0L);

			for (long restoreEntryId : restoreEntryIds) {
				TrashEntry entry = TrashEntryServiceUtil.restoreEntry(
					restoreEntryId);

				entryOVPs.addAll(
					getEntryOVPs(entry.getClassName(), entry.getClassPK()));
			}
		}

		addRestoreData(actionRequest, entryOVPs);

		sendRedirect(actionRequest, actionResponse);
	}

	public void restoreEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		if (cmd.equals(Constants.RENAME)) {
			restoreRename(actionRequest, actionResponse);
		}
		else if (cmd.equals(Constants.OVERRIDE)) {
			restoreOverride(actionRequest, actionResponse);
		}
	}

	public void restoreOverride(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long trashEntryId = ParamUtil.getLong(actionRequest, "trashEntryId");

		long duplicateEntryId = ParamUtil.getLong(
			actionRequest, "duplicateEntryId");

		TrashEntry entry = TrashEntryServiceUtil.restoreEntry(
			trashEntryId, duplicateEntryId, null);

		addRestoreData(
			actionRequest,
			getEntryOVPs(entry.getClassName(), entry.getClassPK()));

		sendRedirect(actionRequest, actionResponse);
	}

	public void restoreRename(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long trashEntryId = ParamUtil.getLong(actionRequest, "trashEntryId");

		String newName = ParamUtil.getString(actionRequest, "newName");

		if (Validator.isNull(newName)) {
			String oldName = ParamUtil.getString(actionRequest, "oldName");

			newName = TrashUtil.getNewName(themeDisplay, null, 0, oldName);
		}

		TrashEntry entry = TrashEntryServiceUtil.restoreEntry(
			trashEntryId, 0, newName);

		addRestoreData(
			actionRequest,
			getEntryOVPs(entry.getClassName(), entry.getClassPK()));

		sendRedirect(actionRequest, actionResponse);
	}

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException, PortletException {

		String resourceID = GetterUtil.getString(
			resourceRequest.getResourceID());

		if (resourceID.equals("checkEntry")) {
			try {
				JSONObject jsonObject = ActionUtil.checkEntry(resourceRequest);

				writeJSON(resourceRequest, resourceResponse, jsonObject);
			}
			catch (PortalException e) {
			}
		}
		else {
			super.serveResource(resourceRequest, resourceResponse);
		}
	}

	protected void addRestoreData(
			ActionRequest actionRequest,
			List<ObjectValuePair<String, Long>> entryOVPs)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		if ((entryOVPs == null) || (entryOVPs.size() <= 0)) {
			return;
		}

		List<String> restoreClassNames = new ArrayList<>();
		List<String> restoreEntryLinks = new ArrayList<>();
		List<String> restoreEntryMessages = new ArrayList<>();
		List<String> restoreLinks = new ArrayList<>();
		List<String> restoreMessages = new ArrayList<>();

		for (int i = 0; i < entryOVPs.size(); i++) {
			ObjectValuePair<String, Long> entryOVP = entryOVPs.get(i);

			TrashHandler trashHandler =
				TrashHandlerRegistryUtil.getTrashHandler(entryOVP.getKey());

			String restoreEntryLink = trashHandler.getRestoreContainedModelLink(
				actionRequest, entryOVP.getValue());
			String restoreLink = trashHandler.getRestoreContainerModelLink(
				actionRequest, entryOVP.getValue());
			String restoreMessage = trashHandler.getRestoreMessage(
				actionRequest, entryOVP.getValue());

			if (Validator.isNull(restoreLink) ||
				Validator.isNull(restoreMessage)) {

				continue;
			}

			restoreClassNames.add(trashHandler.getClassName());
			restoreEntryLinks.add(restoreEntryLink);

			TrashRenderer trashRenderer = trashHandler.getTrashRenderer(
				entryOVP.getValue());

			String restoreEntryTitle = trashRenderer.getTitle(
				themeDisplay.getLocale());

			restoreEntryMessages.add(restoreEntryTitle);

			restoreLinks.add(restoreLink);
			restoreMessages.add(restoreMessage);
		}

		Map<String, List<String>> data = new HashMap<>();

		data.put("restoreClassNames", restoreClassNames);
		data.put("restoreEntryLinks", restoreEntryLinks);
		data.put("restoreEntryMessages", restoreEntryMessages);
		data.put("restoreLinks", restoreLinks);
		data.put("restoreMessages", restoreMessages);

		SessionMessages.add(
			actionRequest,
			PortalUtil.getPortletId(actionRequest) +
				SessionMessages.KEY_SUFFIX_DELETE_SUCCESS_DATA, data);
	}

	protected List<ObjectValuePair<String, Long>> getEntryOVPs(
		String className, long classPK) {

		List<ObjectValuePair<String, Long>> entryOVPs = new ArrayList<>();

		ObjectValuePair<String, Long> entryOVP = new ObjectValuePair<>(
			className, classPK);

		entryOVPs.add(entryOVP);

		return entryOVPs;
	}

	@Override
	protected boolean isSessionErrorException(Throwable cause) {
		if (cause instanceof RestoreEntryException ||
			cause instanceof TrashPermissionException) {

			return true;
		}

		return false;
	}

}