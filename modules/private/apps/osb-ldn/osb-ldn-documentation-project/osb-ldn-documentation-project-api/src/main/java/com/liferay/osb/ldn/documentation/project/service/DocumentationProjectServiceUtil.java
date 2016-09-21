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

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for DocumentationProject. This utility wraps
 * {@link com.liferay.osb.ldn.documentation.project.service.impl.DocumentationProjectServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Ryan Park
 * @see DocumentationProjectService
 * @see com.liferay.osb.ldn.documentation.project.service.base.DocumentationProjectServiceBaseImpl
 * @see com.liferay.osb.ldn.documentation.project.service.impl.DocumentationProjectServiceImpl
 * @generated
 */
@ProviderType
public class DocumentationProjectServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.ldn.documentation.project.service.impl.DocumentationProjectServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.osb.ldn.documentation.project.model.DocumentationProject addDocumentationProject(
		java.lang.String name, java.lang.String description,
		java.lang.String iconFileName, java.io.File iconFile, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addDocumentationProject(name, description, iconFileName,
			iconFile, status);
	}

	public static com.liferay.osb.ldn.documentation.project.model.DocumentationProject updateDocumentationProject(
		long documentationProjectId, java.lang.String name,
		java.lang.String description, java.lang.String iconFileName,
		java.io.File iconFile, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateDocumentationProject(documentationProjectId, name,
			description, iconFileName, iconFile, status);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static DocumentationProjectService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DocumentationProjectService, DocumentationProjectService> _serviceTracker =
		ServiceTrackerFactory.open(DocumentationProjectService.class);
}