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

package com.liferay.osb.lcs.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.lcs.service.LCSMembersServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link LCSMembersServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
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
 * @author Igor Beslic
 * @see LCSMembersServiceHttp
 * @see LCSMembersServiceUtil
 * @generated
 */
@ProviderType
public class LCSMembersServiceSoap {
	/**
	* Publishes a monitoring unavailable event that targets all watchers of the
	* portal instance specified by the key.
	*
	* @param key the portal instance key provided by the LCS key generator
	* @throws PortalException if a portal exception occurred
	* @since LCS 1.3
	*/
	public static void sendMonitoringUnavailableEmail(java.lang.String key)
		throws RemoteException {
		try {
			LCSMembersServiceUtil.sendMonitoringUnavailableEmail(key);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* Publishes a patching tool unavailable event that targets all watchers of
	* the portal instance specified by the key.
	*
	* @param key the portal instance key provided by the LCS key generator
	* @throws PortalException if a portal exception occurred
	* @since LCS 1.3
	*/
	public static void sendPatchingToolUnavailableEmail(java.lang.String key)
		throws RemoteException {
		try {
			LCSMembersServiceUtil.sendPatchingToolUnavailableEmail(key);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* Publishes a server manually shut down event that targets all watchers of
	* the portal instance identified by the key.
	*
	* @param key the portal instance key provided by the LCS key generator
	* @throws PortalException if a portal exception occurred
	* @since LCS 1.3
	*/
	public static void sendServerManuallyShutdownEmail(java.lang.String key)
		throws RemoteException {
		try {
			LCSMembersServiceUtil.sendServerManuallyShutdownEmail(key);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* Publishes a server unexpectedly shut down event that targets all watchers
	* of the portal instance specified by the key.
	*
	* @param key the portal instance key provided by the LCS key generator
	* @throws PortalException if a portal exception occurred
	* @since LCS 1.3
	*/
	public static void sendServerUnexpectedlyShutdownEmail(java.lang.String key)
		throws RemoteException {
		try {
			LCSMembersServiceUtil.sendServerUnexpectedlyShutdownEmail(key);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(LCSMembersServiceSoap.class);
}