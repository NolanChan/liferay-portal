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

import com.liferay.osb.lcs.service.UserLCSMessageServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link UserLCSMessageServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.osb.lcs.model.UserLCSMessageSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.osb.lcs.model.UserLCSMessage}, that is translated to a
 * {@link com.liferay.osb.lcs.model.UserLCSMessageSoap}. Methods that SOAP cannot
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
 * @author Igor Beslic
 * @see UserLCSMessageServiceHttp
 * @see com.liferay.osb.lcs.model.UserLCSMessageSoap
 * @see UserLCSMessageServiceUtil
 * @generated
 */
@ProviderType
public class UserLCSMessageServiceSoap {
	public static com.liferay.osb.lcs.model.UserLCSMessageSoap deleteUserLCSMessage(
		long userLCSMessageId) throws RemoteException {
		try {
			com.liferay.osb.lcs.model.UserLCSMessage returnValue = UserLCSMessageServiceUtil.deleteUserLCSMessage(userLCSMessageId);

			return com.liferay.osb.lcs.model.UserLCSMessageSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteUserLCSMessages() throws RemoteException {
		try {
			UserLCSMessageServiceUtil.deleteUserLCSMessages();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.lcs.model.UserLCSMessageSoap[] getUserLCSMessages(
		boolean hidden) throws RemoteException {
		try {
			java.util.List<com.liferay.osb.lcs.model.UserLCSMessage> returnValue =
				UserLCSMessageServiceUtil.getUserLCSMessages(hidden);

			return com.liferay.osb.lcs.model.UserLCSMessageSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.lcs.model.UserLCSMessageSoap[] getUserLCSMessages(
		int max) throws RemoteException {
		try {
			java.util.List<com.liferay.osb.lcs.model.UserLCSMessage> returnValue =
				UserLCSMessageServiceUtil.getUserLCSMessages(max);

			return com.liferay.osb.lcs.model.UserLCSMessageSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getUserLCSMessagesCount() throws RemoteException {
		try {
			int returnValue = UserLCSMessageServiceUtil.getUserLCSMessagesCount();

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.lcs.model.UserLCSMessageSoap updateRead(
		long userLCSMessageId, boolean read) throws RemoteException {
		try {
			com.liferay.osb.lcs.model.UserLCSMessage returnValue = UserLCSMessageServiceUtil.updateRead(userLCSMessageId,
					read);

			return com.liferay.osb.lcs.model.UserLCSMessageSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(UserLCSMessageServiceSoap.class);
}