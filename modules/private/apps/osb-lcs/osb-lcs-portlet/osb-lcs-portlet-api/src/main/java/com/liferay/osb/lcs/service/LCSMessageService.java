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

import com.liferay.osb.lcs.model.LCSMessage;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Provides the remote service interface for LCSMessage. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Igor Beslic
 * @see LCSMessageServiceUtil
 * @see com.liferay.osb.lcs.service.base.LCSMessageServiceBaseImpl
 * @see com.liferay.osb.lcs.service.impl.LCSMessageServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@OSGiBeanProperties(property =  {
	"json.web.service.context.name=osblcs", "json.web.service.context.path=LCSMessage"}, service = LCSMessageService.class)
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface LCSMessageService extends BaseService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LCSMessageServiceUtil} to access the l c s message remote service. Add custom service methods to {@link com.liferay.osb.lcs.service.impl.LCSMessageServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public LCSMessage addCorpProjectLCSMessage(long corpProjectId,
		long sourceMessageId, java.lang.String content, int type)
		throws PortalException;

	public LCSMessage addLCSProjectLCSMessage(long lcsProjectId,
		long sourceMessageId, java.lang.String sourceSystemName,
		java.lang.String content, Date endDate, boolean global,
		int severityLevel, int type, boolean adminsOnly,
		boolean generateUserLCSMessages) throws PortalException;

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSMessage> getLCSMessages(Date modifyDateGT, Date modifyDateLT)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSMessage> getLCSProjectLCSMessages(long lcsProjectId,
		java.lang.String sourceSystemName) throws PortalException;

	public void deleteCorpProjectLCSMessage(long corpProjectId,
		long sourceMessageId) throws PortalException;

	public void deleteLCSProjectLCSMessage(long lcsProjectId,
		long sourceMessageId, java.lang.String sourceSystemName)
		throws PortalException;
}