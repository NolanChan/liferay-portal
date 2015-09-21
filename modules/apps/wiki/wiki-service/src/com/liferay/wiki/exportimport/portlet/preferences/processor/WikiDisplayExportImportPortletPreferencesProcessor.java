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

package com.liferay.wiki.exportimport.portlet.preferences.processor;

import com.liferay.exportimport.portlet.preferences.processor.Capability;
import com.liferay.exportimport.portlet.preferences.processor.ExportImportPortletPreferencesProcessor;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portlet.exportimport.lar.PortletDataContext;
import com.liferay.portlet.exportimport.lar.PortletDataException;
import com.liferay.portlet.exportimport.lar.StagedModelDataHandlerUtil;
import com.liferay.wiki.constants.WikiPortletKeys;
import com.liferay.wiki.model.WikiNode;
import com.liferay.wiki.model.WikiPage;
import com.liferay.wiki.service.WikiPageLocalServiceUtil;
import com.liferay.wiki.service.permission.WikiResourcePermissionChecker;
import com.liferay.wiki.service.persistence.WikiNodeUtil;

import java.util.List;
import java.util.Map;

import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;

import org.osgi.service.component.annotations.Component;

/**
 * @author Mate Thurzo
 */
@Component(
	immediate = true,
	property = {"javax.portlet.name=" + WikiPortletKeys.WIKI_DISPLAY},
	service = ExportImportPortletPreferencesProcessor.class
)
public class WikiDisplayExportImportPortletPreferencesProcessor
	implements ExportImportPortletPreferencesProcessor {

	@Override
	public List<Capability> getExportCapabilities() {
		return null;
	}

	@Override
	public List<Capability> getImportCapabilities() {
		return null;
	}

	@Override
	public PortletPreferences processExportPortletPreferences(
			PortletDataContext portletDataContext,
			PortletPreferences portletPreferences)
		throws PortletDataException {

		String portletId = portletDataContext.getPortletId();

		long nodeId = GetterUtil.getLong(
			portletPreferences.getValue("nodeId", StringPool.BLANK));

		if (nodeId <= 0) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"No node id found in preferences of portlet " + portletId);
			}

			return portletPreferences;
		}

		String title = portletPreferences.getValue("title", null);

		if (title == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"No title found in preferences of portlet " + portletId);
			}

			return portletPreferences;
		}

		WikiNode node = WikiNodeUtil.fetchByPrimaryKey(nodeId);

		if (node == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to find wiki node");
			}

			return portletPreferences;
		}

		try {
			portletDataContext.addPortletPermissions(
				WikiResourcePermissionChecker.RESOURCE_NAME);
		}
		catch (PortalException pe) {
			throw new PortletDataException(
				"Unable to export portlet permissions", pe);
		}

		StagedModelDataHandlerUtil.exportReferenceStagedModel(
			portletDataContext, portletId, node);

		ActionableDynamicQuery actionableDynamicQuery =
			getPageActionableDynamicQuery(
				portletDataContext, node.getNodeId(), portletId);

		try {
			actionableDynamicQuery.performActions();
		}
		catch (PortalException pe) {
			throw new PortletDataException(
				"Unable to export referenced pages", pe);
		}

		return portletPreferences;
	}

	@Override
	public PortletPreferences processImportPortletPreferences(
			PortletDataContext portletDataContext,
			PortletPreferences portletPreferences)
		throws PortletDataException {

		try {
			portletDataContext.importPortletPermissions(
				WikiResourcePermissionChecker.RESOURCE_NAME);
		}
		catch (PortalException pe) {
			throw new PortletDataException(
				"Unable to import portlet permissions", pe);
		}

		StagedModelDataHandlerUtil.importReferenceStagedModels(
			portletDataContext, WikiNode.class);

		StagedModelDataHandlerUtil.importReferenceStagedModels(
			portletDataContext, WikiPage.class);

		long nodeId = GetterUtil.getLong(
			portletPreferences.getValue("nodeId", StringPool.BLANK));

		if (nodeId > 0) {
			Map<Long, Long> nodeIds =
				(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
					WikiNode.class);

			nodeId = MapUtil.getLong(nodeIds, nodeId, nodeId);

			try {
				portletPreferences.setValue("nodeId", String.valueOf(nodeId));
			}
			catch (ReadOnlyException roe) {
				throw new PortletDataException(
					"Unable to update portlet preferences during import", roe);
			}
		}

		return portletPreferences;
	}

	protected ActionableDynamicQuery getPageActionableDynamicQuery(
		final PortletDataContext portletDataContext, final long nodeId,
		final String portletId) {

		ActionableDynamicQuery actionableDynamicQuery =
			WikiPageLocalServiceUtil.getExportActionableDynamicQuery(
				portletDataContext);

		final ActionableDynamicQuery.AddCriteriaMethod addCriteriaMethod =
			actionableDynamicQuery.getAddCriteriaMethod();

		actionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {

				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					addCriteriaMethod.addCriteria(dynamicQuery);

					Property property = PropertyFactoryUtil.forName("nodeId");

					dynamicQuery.add(property.eq(nodeId));
				}

		});

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod() {

				@Override
				public void performAction(Object object)
					throws PortalException {

					WikiPage page = (WikiPage)object;

					StagedModelDataHandlerUtil.exportReferenceStagedModel(
						portletDataContext, portletId, page);
				}

		});

		return actionableDynamicQuery;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		WikiDisplayExportImportPortletPreferencesProcessor.class);

}