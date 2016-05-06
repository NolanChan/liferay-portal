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

package com.liferay.oauth.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.service.InvokableService;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the remote service utility for OAuthApplication. This utility wraps
 * {@link com.liferay.oauth.service.impl.OAuthApplicationServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Ivica Cardic
 * @see OAuthApplicationService
 * @see com.liferay.oauth.service.base.OAuthApplicationServiceBaseImpl
 * @see com.liferay.oauth.service.impl.OAuthApplicationServiceImpl
 * @generated
 */
@ProviderType
public class OAuthApplicationServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.oauth.service.impl.OAuthApplicationServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.oauth.model.OAuthApplication addOAuthApplication(
		java.lang.String name, java.lang.String description, int accessLevel,
		boolean shareableAccessToken, java.lang.String callbackURI,
		java.lang.String websiteURL,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addOAuthApplication(name, description, accessLevel,
			shareableAccessToken, callbackURI, websiteURL, serviceContext);
	}

	public static com.liferay.oauth.model.OAuthApplication deleteOAuthApplication(
		long oAuthApplicationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteOAuthApplication(oAuthApplicationId);
	}

	public static com.liferay.oauth.model.OAuthApplication updateLogo(
		long oAuthApplicationId, java.io.InputStream inputStream)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateLogo(oAuthApplicationId, inputStream);
	}

	public static com.liferay.oauth.model.OAuthApplication updateOAuthApplication(
		long oAuthApplicationId, java.lang.String name,
		java.lang.String description, boolean shareableAccessToken,
		java.lang.String callbackURI, java.lang.String websiteURL,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateOAuthApplication(oAuthApplicationId, name,
			description, shareableAccessToken, callbackURI, websiteURL,
			serviceContext);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static void deleteLogo(long oAuthApplicationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteLogo(oAuthApplicationId);
	}

	public static void clearService() {
		_service = null;
	}

	public static OAuthApplicationService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					OAuthApplicationService.class.getName());

			if (invokableService instanceof OAuthApplicationService) {
				_service = (OAuthApplicationService)invokableService;
			}
			else {
				_service = new OAuthApplicationServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(OAuthApplicationServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static OAuthApplicationService _service;
}