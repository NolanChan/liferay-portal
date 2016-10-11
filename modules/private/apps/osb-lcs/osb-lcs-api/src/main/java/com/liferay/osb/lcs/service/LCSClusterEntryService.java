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

import com.liferay.osb.lcs.model.LCSClusterEntry;

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

import java.util.List;

/**
 * Provides the remote service interface for LCSClusterEntry. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Igor Beslic
 * @see LCSClusterEntryServiceUtil
 * @see com.liferay.osb.lcs.service.base.LCSClusterEntryServiceBaseImpl
 * @see com.liferay.osb.lcs.service.impl.LCSClusterEntryServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@OSGiBeanProperties(property =  {
	"json.web.service.context.name=osblcs", "json.web.service.context.path=LCSClusterEntry"}, service = LCSClusterEntryService.class)
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface LCSClusterEntryService extends BaseService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LCSClusterEntryServiceUtil} to access the l c s cluster entry remote service. Add custom service methods to {@link com.liferay.osb.lcs.service.impl.LCSClusterEntryServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public byte[] exportLCSClusterEntryToken(long lcsProjectId,
		java.lang.String lcsClusterEntryName,
		java.lang.String subscriptionType, int type) throws PortalException;

	@java.lang.Deprecated
	public LCSClusterEntry addLCSClusterEntry(long lcsProjectId,
		java.lang.String name, java.lang.String description,
		java.lang.String location, int type) throws PortalException;

	public LCSClusterEntry addLCSClusterEntry(long lcsProjectId,
		java.lang.String name, java.lang.String description,
		java.lang.String location, java.lang.String subscriptionType, int type)
		throws PortalException;

	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	public LCSClusterEntry deleteLCSClusterEntry(long lcsClusterEntryId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LCSClusterEntry getLCSClusterEntry(long lcsClusterEntryId)
		throws PortalException;

	public LCSClusterEntry updateElastic(long lcsClusterEntryId, boolean elastic)
		throws PortalException;

	public LCSClusterEntry updateHighPageLoadTime(long lcsClusterEntryId,
		int highPageLoadTime) throws PortalException;

	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	public LCSClusterEntry updateLCSClusterEntry(long lcsClusterEntryId,
		java.lang.String name, java.lang.String description,
		java.lang.String location) throws PortalException;

	public LCSClusterEntry updateMediumPageLoadTime(long lcsClusterEntryId,
		int mediumPageLoadTime) throws PortalException;

	public LCSClusterEntry updateSubscriptionType(long lcsClusterEntryId,
		java.lang.String subscriptionType) throws PortalException;

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSClusterEntry> getArchivedLCSProjectLCSClusterEntries(
		long lcsProjectId, java.lang.String subscriptionType)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSClusterEntry> getLCSProjectLCSClusterEntries(
		long lcsProjectId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSClusterEntry> getLCSProjectLCSClusterEntries(
		long lcsProjectId, java.lang.String subscriptionType)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSClusterEntry> getLCSProjectManageableLCSClusterEntries(
		long lcsProjectId) throws PortalException;

	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSClusterEntry> getUserLCSClusterEntries()
		throws PortalException;

	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSClusterEntry> getUserLCSClusterEntries(long lcsProjectId)
		throws PortalException;

	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	public void deleteLCSProjectClusters(long lcsProjectId)
		throws PortalException;
}