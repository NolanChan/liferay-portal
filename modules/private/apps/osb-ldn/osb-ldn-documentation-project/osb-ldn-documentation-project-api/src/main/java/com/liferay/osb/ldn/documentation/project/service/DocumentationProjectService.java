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

import com.liferay.osb.ldn.documentation.project.model.DocumentationProject;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.io.File;

/**
 * Provides the remote service interface for DocumentationProject. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Ryan Park
 * @see DocumentationProjectServiceUtil
 * @see com.liferay.osb.ldn.documentation.project.service.base.DocumentationProjectServiceBaseImpl
 * @see com.liferay.osb.ldn.documentation.project.service.impl.DocumentationProjectServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@OSGiBeanProperties(property =  {
	"json.web.service.context.name=osb_ldn", "json.web.service.context.path=DocumentationProject"}, service = DocumentationProjectService.class)
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface DocumentationProjectService extends BaseService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DocumentationProjectServiceUtil} to access the documentation project remote service. Add custom service methods to {@link com.liferay.osb.ldn.documentation.project.service.impl.DocumentationProjectServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public DocumentationProject addDocumentationProject(java.lang.String name,
		java.lang.String description, java.lang.String iconFileName,
		File iconFile, int status) throws PortalException;

	public DocumentationProject updateDocumentationProject(
		long documentationProjectId, java.lang.String name,
		java.lang.String description, java.lang.String iconFileName,
		File iconFile, int status) throws PortalException;

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();
}