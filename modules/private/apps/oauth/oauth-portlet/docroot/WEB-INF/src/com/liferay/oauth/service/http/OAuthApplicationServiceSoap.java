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

package com.liferay.oauth.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.oauth.service.OAuthApplicationServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link OAuthApplicationServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.oauth.model.OAuthApplicationSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.oauth.model.OAuthApplication}, that is translated to a
 * {@link com.liferay.oauth.model.OAuthApplicationSoap}. Methods that SOAP cannot
 * safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Ivica Cardic
 * @see OAuthApplicationServiceHttp
 * @see com.liferay.oauth.model.OAuthApplicationSoap
 * @see OAuthApplicationServiceUtil
 * @generated
 */
@ProviderType
public class OAuthApplicationServiceSoap {
	public static com.liferay.oauth.model.OAuthApplicationSoap addOAuthApplication(
		java.lang.String name, java.lang.String description, int accessLevel,
		boolean shareableAccessToken, java.lang.String callbackURI,
		java.lang.String websiteURL,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.oauth.model.OAuthApplication returnValue = OAuthApplicationServiceUtil.addOAuthApplication(name,
					description, accessLevel, shareableAccessToken,
					callbackURI, websiteURL, serviceContext);

			return com.liferay.oauth.model.OAuthApplicationSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteLogo(long oAuthApplicationId)
		throws RemoteException {
		try {
			OAuthApplicationServiceUtil.deleteLogo(oAuthApplicationId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.oauth.model.OAuthApplicationSoap deleteOAuthApplication(
		long oAuthApplicationId) throws RemoteException {
		try {
			com.liferay.oauth.model.OAuthApplication returnValue = OAuthApplicationServiceUtil.deleteOAuthApplication(oAuthApplicationId);

			return com.liferay.oauth.model.OAuthApplicationSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.oauth.model.OAuthApplicationSoap updateOAuthApplication(
		long oAuthApplicationId, java.lang.String name,
		java.lang.String description, boolean shareableAccessToken,
		java.lang.String callbackURI, java.lang.String websiteURL,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.oauth.model.OAuthApplication returnValue = OAuthApplicationServiceUtil.updateOAuthApplication(oAuthApplicationId,
					name, description, shareableAccessToken, callbackURI,
					websiteURL, serviceContext);

			return com.liferay.oauth.model.OAuthApplicationSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(OAuthApplicationServiceSoap.class);
}