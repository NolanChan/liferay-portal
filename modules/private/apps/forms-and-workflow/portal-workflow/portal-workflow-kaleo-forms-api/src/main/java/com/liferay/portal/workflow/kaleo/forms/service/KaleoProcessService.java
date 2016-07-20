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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess;
import com.liferay.portal.workflow.kaleo.forms.model.KaleoTaskFormPairs;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Provides the remote service interface for KaleoProcess. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Marcellus Tavares
 * @see KaleoProcessServiceUtil
 * @see com.liferay.portal.workflow.kaleo.forms.service.base.KaleoProcessServiceBaseImpl
 * @see com.liferay.portal.workflow.kaleo.forms.service.impl.KaleoProcessServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@OSGiBeanProperties(property =  {
	"json.web.service.context.name=kaleoforms", "json.web.service.context.path=KaleoProcess"}, service = KaleoProcessService.class)
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface KaleoProcessService extends BaseService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link KaleoProcessServiceUtil} to access the kaleo process remote service. Add custom service methods to {@link com.liferay.portal.workflow.kaleo.forms.service.impl.KaleoProcessServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

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
	public KaleoProcess addKaleoProcess(long groupId, long ddmStructureId,
		Map<Locale, java.lang.String> nameMap,
		Map<Locale, java.lang.String> descriptionMap, long ddmTemplateId,
		java.lang.String workflowDefinitionName, int workflowDefinitionVersion,
		KaleoTaskFormPairs kaleoTaskFormPairs, ServiceContext serviceContext)
		throws PortalException;

	/**
	* Deletes the kaleo process and its resources.
	*
	* @param kaleoProcessId the primary key of the kaleo process to be deleted
	* @return the kaleo process that was removed
	* @throws PortalException if a portal exception occurred
	*/
	public KaleoProcess deleteKaleoProcess(long kaleoProcessId)
		throws PortalException;

	/**
	* Returns the kaleo process with the primary key.
	*
	* @param kaleoProcessId the primary key of the kaleo process
	* @return the kaleo process
	* @throws PortalException if a kaleo process with the primary key could not
	be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KaleoProcess getKaleoProcess(long kaleoProcessId)
		throws PortalException;

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
	public KaleoProcess updateKaleoProcess(long kaleoProcessId,
		long ddmStructureId, Map<Locale, java.lang.String> nameMap,
		Map<Locale, java.lang.String> descriptionMap, long ddmTemplateId,
		java.lang.String workflowDefinitionName, int workflowDefinitionVersion,
		KaleoTaskFormPairs kaleoTaskFormPairs, ServiceContext serviceContext)
		throws PortalException;

	/**
	* Returns the number of kaleo processes n a group, matching the keywords parameter. The
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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int searchCount(long groupId, java.lang.String keywords);

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	/**
	* Returns an ordered range of all kaleo processes in a group, matching the keywords parameter. The
	* keywords parameter matches string values to the kaleo processes'
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
	each kaleo process name or description (optionally
	<code>null</code>). If the keywords value is not
	<code>null</code>, the search uses the OR operator in connecting
	query criteria; otherwise it uses the AND operator.
	* @param start the lower bound of the range of kaleo processes to return
	* @param end the upper bound of the range of kaleo processes to return
	(not inclusive)
	* @param orderByComparator the comparator to order the kaleo processes
	* @return the range of matching kaleo processes ordered by the comparator
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KaleoProcess> search(long groupId, java.lang.String keywords,
		int start, int end, OrderByComparator orderByComparator);
}