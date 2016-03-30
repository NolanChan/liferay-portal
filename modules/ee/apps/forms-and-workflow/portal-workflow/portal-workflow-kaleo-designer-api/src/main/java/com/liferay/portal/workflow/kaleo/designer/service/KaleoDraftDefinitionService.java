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

package com.liferay.portal.workflow.kaleo.designer.service;

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
import com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Provides the remote service interface for KaleoDraftDefinition. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Eduardo Lundgren
 * @see KaleoDraftDefinitionServiceUtil
 * @see com.liferay.portal.workflow.kaleo.designer.service.base.KaleoDraftDefinitionServiceBaseImpl
 * @see com.liferay.portal.workflow.kaleo.designer.service.impl.KaleoDraftDefinitionServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@OSGiBeanProperties(property =  {
	"json.web.service.context.name=kaleodesigner", "json.web.service.context.path=KaleoDraftDefinition"}, service = KaleoDraftDefinitionService.class)
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface KaleoDraftDefinitionService extends BaseService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link KaleoDraftDefinitionServiceUtil} to access the kaleo draft definition remote service. Add custom service methods to {@link com.liferay.portal.workflow.kaleo.designer.service.impl.KaleoDraftDefinitionServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public KaleoDraftDefinition addKaleoDraftDefinition(long userId,
		long groupId, java.lang.String name,
		Map<Locale, java.lang.String> titleMap, java.lang.String content,
		int version, int draftVersion, ServiceContext serviceContext)
		throws PortalException;

	public void deleteKaleoDraftDefinitions(java.lang.String name, int version,
		ServiceContext serviceContext) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KaleoDraftDefinition getKaleoDraftDefinition(java.lang.String name,
		int version, int draftVersion, ServiceContext serviceContext)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KaleoDraftDefinition> getKaleoDraftDefinitions()
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KaleoDraftDefinition getLatestKaleoDraftDefinition(
		java.lang.String name, int version, ServiceContext serviceContext)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KaleoDraftDefinition> getLatestKaleoDraftDefinitions(
		long companyId, java.lang.String keywords, int version, int start,
		int end, OrderByComparator orderByComparator) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KaleoDraftDefinition> getLatestKaleoDraftDefinitions(
		long companyId, int version, int start, int end,
		OrderByComparator orderByComparator) throws PortalException;

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	public KaleoDraftDefinition publishKaleoDraftDefinition(long userId,
		long groupId, java.lang.String name,
		Map<Locale, java.lang.String> titleMap, java.lang.String content,
		ServiceContext serviceContext) throws PortalException;

	public KaleoDraftDefinition updateKaleoDraftDefinition(long userId,
		java.lang.String name, Map<Locale, java.lang.String> titleMap,
		java.lang.String content, int version, ServiceContext serviceContext)
		throws PortalException;
}