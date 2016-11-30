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

package com.liferay.osb.lcs.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.lcs.model.LCSClusterEntryToken;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.util.Map;

/**
 * Provides the remote service interface for LCSClusterEntryToken. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Igor Beslic
 * @see LCSClusterEntryTokenServiceUtil
 * @see com.liferay.osb.lcs.service.base.LCSClusterEntryTokenServiceBaseImpl
 * @see com.liferay.osb.lcs.service.impl.LCSClusterEntryTokenServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@OSGiBeanProperties(property =  {
	"json.web.service.context.name=osblcs", "json.web.service.context.path=LCSClusterEntryToken"}, service = LCSClusterEntryTokenService.class)
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface LCSClusterEntryTokenService extends BaseService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LCSClusterEntryTokenServiceUtil} to access the l c s cluster entry token remote service. Add custom service methods to {@link com.liferay.osb.lcs.service.impl.LCSClusterEntryTokenServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isValid(long lcsClusterEntryTokenId)
		throws PortalException;

	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	public LCSClusterEntryToken addLCSClusterEntryToken(
		long lcsClusterEntryId,
		Map<java.lang.String, java.lang.String> lcsServicesConfiguration)
		throws PortalException;

	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	public LCSClusterEntryToken deleteLCSClusterEntryToken(
		long lcsClusterEntryTokenId) throws PortalException;

	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LCSClusterEntryToken fetchLCSClusterEntryLCSClusterEntryToken(
		long lcsClusterEntryId) throws PortalException;

	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LCSClusterEntryToken fetchLCSClusterEntryToken(
		long lcsClusterEntryTokenId) throws PortalException;

	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	public LCSClusterEntryToken regenerateLCSClusterEntryToken(
		long lcsClusterEntryId,
		Map<java.lang.String, java.lang.String> lcsServicesConfiguration)
		throws PortalException;

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();
}