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

import com.liferay.osb.lcs.model.LCSSubscriptionEntry;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.util.List;

/**
 * Provides the remote service interface for LCSSubscriptionEntry. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Igor Beslic
 * @see LCSSubscriptionEntryServiceUtil
 * @see com.liferay.osb.lcs.service.base.LCSSubscriptionEntryServiceBaseImpl
 * @see com.liferay.osb.lcs.service.impl.LCSSubscriptionEntryServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@OSGiBeanProperties(property =  {
	"json.web.service.context.name=osblcs", "json.web.service.context.path=LCSSubscriptionEntry"}, service = LCSSubscriptionEntryService.class)
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface LCSSubscriptionEntryService extends BaseService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LCSSubscriptionEntryServiceUtil} to access the l c s subscription entry remote service. Add custom service methods to {@link com.liferay.osb.lcs.service.impl.LCSSubscriptionEntryServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasLCSProjectElasticLCSSubscriptionEntry(long lcsProjectId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LCSSubscriptionEntry fetchLCSClusterNodeActiveLCSSubscriptionEntry(
		java.lang.String key) throws PortalException;

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	@java.lang.Deprecated
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSSubscriptionEntry> getCorpProjectLCSSubscriptionEntries(
		long corpProjectId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSSubscriptionEntry> getLCSProjectLCSSubscriptionEntries(
		long lcsProjectId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSSubscriptionEntry> getLCSProjectLCSSubscriptionEntries(
		long lcsProjectId, boolean status) throws PortalException;

	public void addCorpProjectLCSSubscriptionEntries(long corpProjectId,
		java.lang.String lcsSubscriptionEntriesJSON) throws PortalException;

	public void addLCSSubscriptionEntries(long lcsProjectId,
		java.lang.String lcsSubscriptionEntriesJSON) throws PortalException;

	public void refreshLCSProjectLCSSubscriptionEntries()
		throws PortalException;

	public void refreshLCSProjectLCSSubscriptionEntries(long lcsProjectId)
		throws PortalException;
}