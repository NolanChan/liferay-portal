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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link LCSMessageService}.
 *
 * @author Igor Beslic
 * @see LCSMessageService
 * @generated
 */
@ProviderType
public class LCSMessageServiceWrapper implements LCSMessageService,
	ServiceWrapper<LCSMessageService> {
	public LCSMessageServiceWrapper(LCSMessageService lcsMessageService) {
		_lcsMessageService = lcsMessageService;
	}

	@Override
	public com.liferay.osb.lcs.model.LCSMessage addCorpProjectLCSMessage(
		long corpProjectId, long sourceMessageId, java.lang.String content,
		int type) throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsMessageService.addCorpProjectLCSMessage(corpProjectId,
			sourceMessageId, content, type);
	}

	@Override
	public com.liferay.osb.lcs.model.LCSMessage addLCSProjectLCSMessage(
		long lcsProjectId, long sourceMessageId,
		java.lang.String sourceSystemName, java.lang.String content,
		java.util.Date endDate, boolean global, int severityLevel, int type,
		boolean adminsOnly, boolean generateUserLCSMessages)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsMessageService.addLCSProjectLCSMessage(lcsProjectId,
			sourceMessageId, sourceSystemName, content, endDate, global,
			severityLevel, type, adminsOnly, generateUserLCSMessages);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _lcsMessageService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSMessage> getLCSMessages(
		java.util.Date modifyDateGT, java.util.Date modifyDateLT)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsMessageService.getLCSMessages(modifyDateGT, modifyDateLT);
	}

	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSMessage> getLCSProjectLCSMessages(
		long lcsProjectId, java.lang.String sourceSystemName)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsMessageService.getLCSProjectLCSMessages(lcsProjectId,
			sourceSystemName);
	}

	@Override
	public void deleteCorpProjectLCSMessage(long corpProjectId,
		long sourceMessageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_lcsMessageService.deleteCorpProjectLCSMessage(corpProjectId,
			sourceMessageId);
	}

	@Override
	public void deleteLCSProjectLCSMessage(long lcsProjectId,
		long sourceMessageId, java.lang.String sourceSystemName)
		throws com.liferay.portal.kernel.exception.PortalException {
		_lcsMessageService.deleteLCSProjectLCSMessage(lcsProjectId,
			sourceMessageId, sourceSystemName);
	}

	@Override
	public LCSMessageService getWrappedService() {
		return _lcsMessageService;
	}

	@Override
	public void setWrappedService(LCSMessageService lcsMessageService) {
		_lcsMessageService = lcsMessageService;
	}

	private LCSMessageService _lcsMessageService;
}