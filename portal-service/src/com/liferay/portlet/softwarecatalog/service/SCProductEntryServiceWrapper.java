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

package com.liferay.portlet.softwarecatalog.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SCProductEntryService}.
 *
 * @author Brian Wing Shun Chan
 * @see SCProductEntryService
 * @generated
 */
@ProviderType
public class SCProductEntryServiceWrapper implements SCProductEntryService,
	ServiceWrapper<SCProductEntryService> {
	public SCProductEntryServiceWrapper(
		SCProductEntryService scProductEntryService) {
		_scProductEntryService = scProductEntryService;
	}

	@Override
	public com.liferay.portlet.softwarecatalog.model.SCProductEntry addProductEntry(
		java.lang.String name, java.lang.String type, java.lang.String tags,
		java.lang.String shortDescription, java.lang.String longDescription,
		java.lang.String pageURL, java.lang.String author,
		java.lang.String repoGroupId, java.lang.String repoArtifactId,
		long[] licenseIds, java.util.List<byte[]> thumbnails,
		java.util.List<byte[]> fullImages,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _scProductEntryService.addProductEntry(name, type, tags,
			shortDescription, longDescription, pageURL, author, repoGroupId,
			repoArtifactId, licenseIds, thumbnails, fullImages, serviceContext);
	}

	@Override
	public void deleteProductEntry(long productEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_scProductEntryService.deleteProductEntry(productEntryId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _scProductEntryService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portlet.softwarecatalog.model.SCProductEntry getProductEntry(
		long productEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _scProductEntryService.getProductEntry(productEntryId);
	}

	@Override
	public com.liferay.portlet.softwarecatalog.model.SCProductEntry updateProductEntry(
		long productEntryId, java.lang.String name, java.lang.String type,
		java.lang.String tags, java.lang.String shortDescription,
		java.lang.String longDescription, java.lang.String pageURL,
		java.lang.String author, java.lang.String repoGroupId,
		java.lang.String repoArtifactId, long[] licenseIds,
		java.util.List<byte[]> thumbnails, java.util.List<byte[]> fullImages)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _scProductEntryService.updateProductEntry(productEntryId, name,
			type, tags, shortDescription, longDescription, pageURL, author,
			repoGroupId, repoArtifactId, licenseIds, thumbnails, fullImages);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	@Deprecated
	public SCProductEntryService getWrappedSCProductEntryService() {
		return _scProductEntryService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	@Deprecated
	public void setWrappedSCProductEntryService(
		SCProductEntryService scProductEntryService) {
		_scProductEntryService = scProductEntryService;
	}

	@Override
	public SCProductEntryService getWrappedService() {
		return _scProductEntryService;
	}

	@Override
	public void setWrappedService(SCProductEntryService scProductEntryService) {
		_scProductEntryService = scProductEntryService;
	}

	private SCProductEntryService _scProductEntryService;
}