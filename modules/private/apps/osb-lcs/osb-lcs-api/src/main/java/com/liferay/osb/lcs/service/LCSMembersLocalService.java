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

import com.liferay.lcs.notification.LCSEventType;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Transactional;

/**
 * Provides the local service interface for LCSMembers. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Igor Beslic
 * @see LCSMembersLocalServiceUtil
 * @see com.liferay.osb.lcs.service.base.LCSMembersLocalServiceBaseImpl
 * @see com.liferay.osb.lcs.service.impl.LCSMembersLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface LCSMembersLocalService extends BaseLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LCSMembersLocalServiceUtil} to access the l c s members local service. Add custom service methods to {@link com.liferay.osb.lcs.service.impl.LCSMembersLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	/**
	* Publishes a message to the LCS event queue at the portal's message bus.
	*
	* @param key the portal instance key provided by the LCS key generator
	* @param lcsEventType the event type that occurred in the portal instance
	* @since LCS 1.3
	*/
	public void fireLCSEvent(java.lang.String key, LCSEventType lcsEventType);

	public void invalidateLCSSiteMembership(long companyId, long userId)
		throws PortalException;

	public void validateCorpProjectUsers(long corpProjectId, long[] userIds)
		throws PortalException;

	public void validateLCSSiteMembership(long companyId, long userId)
		throws PortalException;
}