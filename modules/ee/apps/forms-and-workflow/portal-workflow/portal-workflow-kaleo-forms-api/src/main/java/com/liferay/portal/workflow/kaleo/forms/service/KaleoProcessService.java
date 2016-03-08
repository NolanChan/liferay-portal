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
	public KaleoProcess addKaleoProcess(long groupId, long ddmStructureId,
		Map<Locale, java.lang.String> nameMap,
		Map<Locale, java.lang.String> descriptionMap, long ddmTemplateId,
		java.lang.String workflowDefinitionName, int workflowDefinitionVersion,
		KaleoTaskFormPairs kaleoTaskFormPairs, ServiceContext serviceContext)
		throws PortalException;

	public KaleoProcess deleteKaleoProcess(long kaleoProcessId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KaleoProcess getKaleoProcess(long kaleoProcessId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KaleoProcess> getKaleoProcesses(long groupId, int start,
		int end, OrderByComparator orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getKaleoProcessesCount(long groupId);

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	public KaleoProcess updateKaleoProcess(long kaleoProcessId,
		long ddmStructureId, Map<Locale, java.lang.String> nameMap,
		Map<Locale, java.lang.String> descriptionMap, long ddmTemplateId,
		java.lang.String workflowDefinitionName, int workflowDefinitionVersion,
		KaleoTaskFormPairs kaleoTaskFormPairs, ServiceContext serviceContext)
		throws PortalException;
}