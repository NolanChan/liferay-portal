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

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for LCSMessage. This utility wraps
 * {@link com.liferay.osb.lcs.service.impl.LCSMessageServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Igor Beslic
 * @see LCSMessageService
 * @see com.liferay.osb.lcs.service.base.LCSMessageServiceBaseImpl
 * @see com.liferay.osb.lcs.service.impl.LCSMessageServiceImpl
 * @generated
 */
@ProviderType
public class LCSMessageServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.lcs.service.impl.LCSMessageServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.osb.lcs.model.LCSMessage addCorpProjectLCSMessage(
		long corpProjectId, long sourceMessageId, java.lang.String content,
		int type) throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addCorpProjectLCSMessage(corpProjectId, sourceMessageId,
			content, type);
	}

	public static com.liferay.osb.lcs.model.LCSMessage addLCSProjectLCSMessage(
		long lcsProjectId, long sourceMessageId,
		java.lang.String sourceSystemName, java.lang.String content,
		java.util.Date endDate, boolean global, int severityLevel, int type,
		boolean adminsOnly, boolean generateUserLCSMessages)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addLCSProjectLCSMessage(lcsProjectId, sourceMessageId,
			sourceSystemName, content, endDate, global, severityLevel, type,
			adminsOnly, generateUserLCSMessages);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSMessage> getLCSMessages(
		java.util.Date modifyDateGT, java.util.Date modifyDateLT)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLCSMessages(modifyDateGT, modifyDateLT);
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSMessage> getLCSProjectLCSMessages(
		long lcsProjectId, java.lang.String sourceSystemName)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getLCSProjectLCSMessages(lcsProjectId, sourceSystemName);
	}

	public static void deleteCorpProjectLCSMessage(long corpProjectId,
		long sourceMessageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteCorpProjectLCSMessage(corpProjectId, sourceMessageId);
	}

	public static void deleteLCSProjectLCSMessage(long lcsProjectId,
		long sourceMessageId, java.lang.String sourceSystemName)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.deleteLCSProjectLCSMessage(lcsProjectId, sourceMessageId,
			sourceSystemName);
	}

	public static LCSMessageService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LCSMessageService, LCSMessageService> _serviceTracker =
		ServiceTrackerFactory.open(LCSMessageService.class);
}