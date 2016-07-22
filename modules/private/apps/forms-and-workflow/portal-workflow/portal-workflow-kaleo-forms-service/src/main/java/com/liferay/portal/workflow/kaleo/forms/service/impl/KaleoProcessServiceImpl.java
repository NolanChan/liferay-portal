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

package com.liferay.portal.workflow.kaleo.forms.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.workflow.kaleo.forms.constants.KaleoFormsActionKeys;
import com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess;
import com.liferay.portal.workflow.kaleo.forms.model.KaleoTaskFormPairs;
import com.liferay.portal.workflow.kaleo.forms.service.base.KaleoProcessServiceBaseImpl;
import com.liferay.portal.workflow.kaleo.forms.service.permission.KaleoFormsPermission;
import com.liferay.portal.workflow.kaleo.forms.service.permission.KaleoProcessPermission;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Provides the remote service for accessing, adding, deleting, and updating
 * kaleo processes. Its methods include permission checks.
 *
 * @author Marcellus Tavares
 */
public class KaleoProcessServiceImpl extends KaleoProcessServiceBaseImpl {

	/**
	 * Adds a kaleo process.
	 *
	 * @param  groupId the primary key of the kaleo process's group
	 * @param  ddmStructureId the primary key of the kaleo process's DDM
	 *         structure
	 * @param  nameMap the kaleo process's locales and localized names
	 * @param  descriptionMap the kaleo process's locales and localized
	 *         descriptions
	 * @param  ddmTemplateId the primary key of the kaleo process's DDM template
	 * @param  workflowDefinitionName the name of kaleo process's workflow
	 *         definition
	 * @param  workflowDefinitionVersion the version of kaleo process's workflow
	 *         definition
	 * @param  kaleoTaskFormPairs the kaleo task form pairs. For more
	 *         information see the
	 *         <code>com.liferay.portal.workflow.kaleo.forms.api</code> module's
	 *         <code>KaleoTaskFormPairs</code> class.
	 * @param  serviceContext the service context to be applied. This can set
	 *         guest permissions and group permissions for the kaleo process.
	 * @return the kaleo process
	 * @throws PortalException if a portal exception occurred
	 */
	@Override
	public KaleoProcess addKaleoProcess(
			long groupId, long ddmStructureId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, long ddmTemplateId,
			String workflowDefinitionName, int workflowDefinitionVersion,
			KaleoTaskFormPairs kaleoTaskFormPairs,
			ServiceContext serviceContext)
		throws PortalException {

		KaleoFormsPermission.check(
			getPermissionChecker(), groupId, KaleoFormsActionKeys.ADD_PROCESS);

		return kaleoProcessLocalService.addKaleoProcess(
			getUserId(), groupId, ddmStructureId, nameMap, descriptionMap,
			ddmTemplateId, workflowDefinitionName, workflowDefinitionVersion,
			kaleoTaskFormPairs, serviceContext);
	}

	/**
	 * Deletes the kaleo process and its resources.
	 *
	 * @param  kaleoProcessId the primary key of the kaleo process to be deleted
	 * @return the deleted kaleo process
	 * @throws PortalException if a portal exception occurred
	 */
	@Override
	public KaleoProcess deleteKaleoProcess(long kaleoProcessId)
		throws PortalException {

		KaleoProcessPermission.check(
			getPermissionChecker(), kaleoProcessId, ActionKeys.DELETE);

		return kaleoProcessLocalService.deleteKaleoProcess(kaleoProcessId);
	}

	/**
	 * Returns the kaleo process with the primary key.
	 *
	 * @param  kaleoProcessId the primary key of the kaleo process
	 * @return the kaleo process
	 * @throws PortalException if a kaleo process with the primary key could not
	 *         be found
	 */
	@Override
	public KaleoProcess getKaleoProcess(long kaleoProcessId)
		throws PortalException {

		if (!KaleoProcessPermission.contains(
				getPermissionChecker(), kaleoProcessId, ActionKeys.VIEW)) {

			KaleoProcessPermission.check(
				getPermissionChecker(), kaleoProcessId, ActionKeys.SUBMIT);
		}

		return kaleoProcessLocalService.getKaleoProcess(kaleoProcessId);
	}

	/**
	 * Returns an ordered range of all kaleo processes matching the parameters,
	 * including a keywords parameter for matching string values to the kaleo
	 * process's name or description.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end -
	 * start</code> instances. <code>start</code> and <code>end</code> are not
	 * primary keys, they are indexes in the result set. Thus, <code>0</code>
	 * refers to the first result in the set. Setting both <code>start</code>
	 * and <code>end</code> to {@link
	 * com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	 * result set.
	 * </p>
	 *
	 * @param  groupId the primary key of the kaleo process's group
	 * @param  keywords the keywords (space separated) to look for and match in
	 *         the kaleo process name or description (optionally
	 *         <code>null</code>). If the keywords value is not
	 *         <code>null</code>, the search uses the OR operator in connecting
	 *         query criteria; otherwise it uses the AND operator.
	 * @param  start the lower bound of the range of kaleo processes to return
	 * @param  end the upper bound of the range of kaleo processes to return
	 *         (not inclusive)
	 * @param  orderByComparator the comparator to order the kaleo processes
	 * @return the range of matching kaleo processes ordered by the comparator
	 */
	@Override
	public List<KaleoProcess> search(
		long groupId, String keywords, int start, int end,
		OrderByComparator orderByComparator) {

		return kaleoProcessFinder.filterFindByKeywords(
			groupId, keywords, start, end, orderByComparator);
	}

	/**
	 * Returns the number of kaleo processes matching the parameters. The
	 * keywords parameter is used for matching the kaleo process's name or
	 * description.
	 *
	 * @param  groupId the primary key of the kaleo process's group
	 * @param  keywords the keywords (space separated) to look for and match in
	 *         the kaleo process name or description (optionally
	 *         <code>null</code>). If the keywords value is not
	 *         <code>null</code>, the OR operator is used in connecting query
	 *         criteria; otherwise it uses the AND operator.
	 * @return the number of matching kaleo processes
	 */
	@Override
	public int searchCount(long groupId, String keywords) {
		return kaleoProcessFinder.filterCountByKeywords(groupId, keywords);
	}

	/**
	 * Updates the kaleo process.
	 *
	 * @param  kaleoProcessId the primary key of the kaleo process
	 * @param  ddmStructureId the primary key of the kaleo process's DDM
	 *         structure
	 * @param  nameMap the kaleo process's locales and localized names
	 * @param  descriptionMap the kaleo process's locales and localized
	 *         descriptions
	 * @param  ddmTemplateId the primary key of the kaleo process's DDM template
	 * @param  workflowDefinitionName the name of kaleo process's workflow
	 *         definition
	 * @param  workflowDefinitionVersion the version of kaleo process's workflow
	 *         definition
	 * @param  kaleoTaskFormPairs the kaleo task form pairs. For more
	 *         information see the
	 *         <code>com.liferay.portal.workflow.kaleo.forms.api</code> module's
	 *         <code>KaleoTaskFormPairs</code> class.
	 * @param  serviceContext the service context to be applied. This can set
	 *         guest permissions and group permissions for the kaleo process.
	 * @return the kaleo process
	 * @throws PortalException if a portal exception occurred
	 */
	@Override
	public KaleoProcess updateKaleoProcess(
			long kaleoProcessId, long ddmStructureId,
			Map<Locale, String> nameMap, Map<Locale, String> descriptionMap,
			long ddmTemplateId, String workflowDefinitionName,
			int workflowDefinitionVersion,
			KaleoTaskFormPairs kaleoTaskFormPairs,
			ServiceContext serviceContext)
		throws PortalException {

		KaleoProcessPermission.check(
			getPermissionChecker(), kaleoProcessId, ActionKeys.UPDATE);

		return kaleoProcessLocalService.updateKaleoProcess(
			kaleoProcessId, ddmStructureId, nameMap, descriptionMap,
			ddmTemplateId, workflowDefinitionName, workflowDefinitionVersion,
			kaleoTaskFormPairs, serviceContext);
	}

}