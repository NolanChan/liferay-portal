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

package com.liferay.portal.workflow.kaleo.forms.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link KaleoProcessService}.
 *
 * @author Marcellus Tavares
 * @see KaleoProcessService
 * @generated
 */
@ProviderType
public class KaleoProcessServiceWrapper implements KaleoProcessService,
	ServiceWrapper<KaleoProcessService> {
	public KaleoProcessServiceWrapper(KaleoProcessService kaleoProcessService) {
		_kaleoProcessService = kaleoProcessService;
	}

	/**
	* Adds a kaleo process.
	*
	* @param groupId the primary key of the kaleo process's group
	* @param ddmStructureId the primary key of the kaleo process's DDM
	structure
	* @param nameMap the kaleo process's locales and localized names
	* @param descriptionMap the kaleo process's locales and localized
	descriptions
	* @param ddmTemplateId the primary key of the kaleo process's DDM template
	* @param workflowDefinitionName the name of kaleo process's workflow
	definition
	* @param workflowDefinitionVersion the version of kaleo process's workflow
	definition
	* @param kaleoTaskFormPairs the kaleo task form pairs. See {@link
	KaleoTaskFormPairs}
	* @param serviceContext the service context to be applied. This can set
	guest permissions and group permissions for the kaleo process.
	* @return the kaleo process
	* @throws PortalException if a portal exception occurred
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess addKaleoProcess(
		long groupId, long ddmStructureId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		long ddmTemplateId, java.lang.String workflowDefinitionName,
		int workflowDefinitionVersion,
		com.liferay.portal.workflow.kaleo.forms.model.KaleoTaskFormPairs kaleoTaskFormPairs,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kaleoProcessService.addKaleoProcess(groupId, ddmStructureId,
			nameMap, descriptionMap, ddmTemplateId, workflowDefinitionName,
			workflowDefinitionVersion, kaleoTaskFormPairs, serviceContext);
	}

	/**
	* Deletes the kaleo process and its resources.
	*
	* @param kaleoProcessId the primary key of the kaleo process to be deleted
	* @return the kaleo process that was removed
	* @throws PortalException if a portal exception occurred
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess deleteKaleoProcess(
		long kaleoProcessId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kaleoProcessService.deleteKaleoProcess(kaleoProcessId);
	}

	/**
	* Returns the kaleo process with the primary key.
	*
	* @param kaleoProcessId the primary key of the kaleo process
	* @return the kaleo process
	* @throws PortalException if a kaleo process with the primary key could not
	be found
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess getKaleoProcess(
		long kaleoProcessId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kaleoProcessService.getKaleoProcess(kaleoProcessId);
	}

	/**
	* Updates the kaleo process.
	*
	* @param kaleoProcessId the primary key of the kaleo process
	* @param ddmStructureId the primary key of the kaleo process's DDM
	structure
	* @param nameMap the kaleo process's locales and localized names
	* @param descriptionMap the kaleo process's locales and localized
	descriptions
	* @param ddmTemplateId the primary key of the kaleo process's DDM template
	* @param workflowDefinitionName the name of kaleo process's workflow
	definition
	* @param workflowDefinitionVersion the version of kaleo process's workflow
	definition
	* @param kaleoTaskFormPairs the kaleo task form pairs. See {@link
	KaleoTaskFormPairs}
	* @param serviceContext the service context to be applied. This can set
	guest permissions and group permissions for the kaleo process.
	* @return the kaleo process
	* @throws PortalException if a portal exception occurred
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess updateKaleoProcess(
		long kaleoProcessId, long ddmStructureId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		long ddmTemplateId, java.lang.String workflowDefinitionName,
		int workflowDefinitionVersion,
		com.liferay.portal.workflow.kaleo.forms.model.KaleoTaskFormPairs kaleoTaskFormPairs,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kaleoProcessService.updateKaleoProcess(kaleoProcessId,
			ddmStructureId, nameMap, descriptionMap, ddmTemplateId,
			workflowDefinitionName, workflowDefinitionVersion,
			kaleoTaskFormPairs, serviceContext);
	}

	/**
	* Returns the number of kaleo processes in the group, matching the keywords parameters. The
	* keywords parameter is used for matching string values to the kaleo processes' names or
	* descriptions.
	*
	* @param groupId the primary key of the kaleo processes' group.
	* @param keywords the keywords (space separated) to look for and match in
	each kaleo process's name or description (optionally
	<code>null</code>). If the keywords value is not
	<code>null</code>, the OR operator is used in connecting query
	criteria; otherwise it uses the AND operator.
	* @return the number of matching kaleo processes
	*/
	@Override
	public int searchCount(long groupId, java.lang.String keywords) {
		return _kaleoProcessService.searchCount(groupId, keywords);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _kaleoProcessService.getOSGiServiceIdentifier();
	}

	/**
	* Returns an ordered range of all kaleo processes in a group, matching the keywords parameter. The
	* keywords parameter is usedfor matching string values to the kaleo processes'
	* names or descriptions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to <code>QueryUtil.ALL_POS</code> will return the
	* full result set.
	* </p>
	*
	* @param groupId the primary key of the kaleo processes' group
	* @param keywords the keywords (space separated) to look for and match in
	each kaleo process's name or description (optionally
	<code>null</code>). If the keywords value is not
	<code>null</code>, the search uses the OR operator in connecting
	query criteria; otherwise it uses the AND operator.
	* @param start the lower bound of the range of kaleo processes to return
	* @param end the upper bound of the range of kaleo processes to return
	(not inclusive)
	* @param orderByComparator the comparator to order the kaleo processes
	* @return the range of matching kaleo processes ordered by the comparator
	*/
	@Override
	public java.util.List<com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess> search(
		long groupId, java.lang.String keywords, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator) {
		return _kaleoProcessService.search(groupId, keywords, start, end,
			orderByComparator);
	}

	@Override
	public KaleoProcessService getWrappedService() {
		return _kaleoProcessService;
	}

	@Override
	public void setWrappedService(KaleoProcessService kaleoProcessService) {
		_kaleoProcessService = kaleoProcessService;
	}

	private KaleoProcessService _kaleoProcessService;
}