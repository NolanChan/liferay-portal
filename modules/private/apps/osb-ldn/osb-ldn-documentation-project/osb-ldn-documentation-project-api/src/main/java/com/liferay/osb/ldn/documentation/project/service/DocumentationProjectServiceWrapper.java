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

package com.liferay.osb.ldn.documentation.project.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link DocumentationProjectService}.
 *
 * @author Ryan Park
 * @see DocumentationProjectService
 * @generated
 */
@ProviderType
public class DocumentationProjectServiceWrapper
	implements DocumentationProjectService,
		ServiceWrapper<DocumentationProjectService> {
	public DocumentationProjectServiceWrapper(
		DocumentationProjectService documentationProjectService) {
		_documentationProjectService = documentationProjectService;
	}

	@Override
	public com.liferay.osb.ldn.documentation.project.model.DocumentationProject addDocumentationProject(
		java.lang.String name, java.lang.String description,
		java.lang.String iconFileName, java.io.File iconFile, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _documentationProjectService.addDocumentationProject(name,
			description, iconFileName, iconFile, status, serviceContext);
	}

	@Override
	public com.liferay.osb.ldn.documentation.project.model.DocumentationProject updateDocumentationProject(
		long documentationProjectId, java.lang.String name,
		java.lang.String description, java.lang.String iconFileName,
		java.io.File iconFile, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _documentationProjectService.updateDocumentationProject(documentationProjectId,
			name, description, iconFileName, iconFile, status, serviceContext);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _documentationProjectService.getOSGiServiceIdentifier();
	}

	@Override
	public DocumentationProjectService getWrappedService() {
		return _documentationProjectService;
	}

	@Override
	public void setWrappedService(
		DocumentationProjectService documentationProjectService) {
		_documentationProjectService = documentationProjectService;
	}

	private DocumentationProjectService _documentationProjectService;
}