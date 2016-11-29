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

package com.liferay.osb.lcs.service.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.lcs.constants.OSBLCSActionKeys;
import com.liferay.osb.lcs.exception.NoSuchLCSClusterEntryTokenException;
import com.liferay.osb.lcs.model.LCSClusterEntryToken;
import com.liferay.osb.lcs.service.base.LCSClusterEntryTokenServiceBaseImpl;
import com.liferay.osb.lcs.service.permission.LCSClusterEntryPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

import java.util.Map;

/**
 * @author Igor Beslic
 * @see    LCSClusterEntryTokenServiceBaseImpl
 * @see    com.liferay.osb.lcs.service.LCSClusterEntryTokenServiceUtil
 */
@ProviderType
public class LCSClusterEntryTokenServiceImpl
	extends LCSClusterEntryTokenServiceBaseImpl {

	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	@Override
	public LCSClusterEntryToken addLCSClusterEntryToken(
			long lcsClusterEntryId,
			Map<String, String> lcsServicesConfiguration)
		throws PortalException {

		LCSClusterEntryPermission.check(
			getPermissionChecker(), lcsClusterEntryId,
			OSBLCSActionKeys.MANAGE_ENTRY);

		return lcsClusterEntryTokenLocalService.addLCSClusterEntryToken(
			getUserId(), lcsClusterEntryId, lcsServicesConfiguration);
	}

	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	@Override
	public LCSClusterEntryToken deleteLCSClusterEntryToken(
			long lcsClusterEntryTokenId)
		throws PortalException {

		LCSClusterEntryToken lcsClusterEntryToken =
			lcsClusterEntryTokenPersistence.findByPrimaryKey(
				lcsClusterEntryTokenId);

		LCSClusterEntryPermission.check(
			getPermissionChecker(), lcsClusterEntryToken.getLcsClusterEntryId(),
			OSBLCSActionKeys.MANAGE_ENTRY);

		return lcsClusterEntryTokenLocalService.deleteLCSClusterEntryToken(
			lcsClusterEntryTokenId);
	}

	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	@Override
	public LCSClusterEntryToken fetchLCSClusterEntryLCSClusterEntryToken(
			long lcsClusterEntryId)
		throws PortalException {

		LCSClusterEntryPermission.check(
			getPermissionChecker(), lcsClusterEntryId,
			OSBLCSActionKeys.MANAGE_ENTRY);

		return lcsClusterEntryTokenLocalService.
			fetchLCSClusterEntryLCSClusterEntryToken(lcsClusterEntryId);
	}

	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	@Override
	public LCSClusterEntryToken fetchLCSClusterEntryToken(
			long lcsClusterEntryTokenId)
		throws PortalException {

		PermissionChecker permissionChecker = getPermissionChecker();

		LCSClusterEntryToken lcsClusterEntryToken =
			lcsClusterEntryTokenLocalService.fetchLCSClusterEntryToken(
				lcsClusterEntryTokenId);

		if (lcsClusterEntryToken == null) {
			throw new NoSuchLCSClusterEntryTokenException();
		}

		LCSClusterEntryPermission.check(
			permissionChecker, lcsClusterEntryToken.getLcsClusterEntryId(),
			OSBLCSActionKeys.MANAGE_ENTRY);

		return lcsClusterEntryToken;
	}

	@Override
	public boolean isValid(long lcsClusterEntryTokenId) throws PortalException {
		PermissionChecker permissionChecker = getPermissionChecker();

		if (!permissionChecker.isSignedIn()) {
			throw new PrincipalException();
		}

		LCSClusterEntryToken lcsClusterEntryToken =
			lcsClusterEntryTokenPersistence.fetchByPrimaryKey(
				lcsClusterEntryTokenId);

		if (lcsClusterEntryToken == null) {
			return false;
		}

		LCSClusterEntryPermission.check(
			permissionChecker, lcsClusterEntryToken.getLcsClusterEntryId(),
			OSBLCSActionKeys.MANAGE_ENTRY);

		return true;
	}

	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	@Override
	public LCSClusterEntryToken regenerateLCSClusterEntryToken(
			long lcsClusterEntryId,
			Map<String, String> lcsServicesConfiguration)
		throws PortalException {

		LCSClusterEntryPermission.check(
			getPermissionChecker(), lcsClusterEntryId,
			OSBLCSActionKeys.MANAGE_ENTRY);

		return lcsClusterEntryTokenLocalService.regenerateLCSClusterEntryToken(
			getUserId(), lcsClusterEntryId, lcsServicesConfiguration);
	}

}