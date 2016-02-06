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

package com.liferay.portlet.documentlibrary.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.document.library.kernel.service.DLFileVersionServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link DLFileVersionServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.document.library.kernel.model.DLFileVersionSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.document.library.kernel.model.DLFileVersion}, that is translated to a
 * {@link com.liferay.document.library.kernel.model.DLFileVersionSoap}. Methods that SOAP cannot
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
 * @author Brian Wing Shun Chan
 * @see DLFileVersionServiceHttp
 * @see com.liferay.document.library.kernel.model.DLFileVersionSoap
 * @see DLFileVersionServiceUtil
 * @generated
 */
@ProviderType
public class DLFileVersionServiceSoap {
	public static com.liferay.document.library.kernel.model.DLFileVersionSoap getFileVersion(
		long fileVersionId) throws RemoteException {
		try {
			com.liferay.document.library.kernel.model.DLFileVersion returnValue = DLFileVersionServiceUtil.getFileVersion(fileVersionId);

			return com.liferay.document.library.kernel.model.DLFileVersionSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.document.library.kernel.model.DLFileVersionSoap[] getFileVersions(
		long fileEntryId, int status) throws RemoteException {
		try {
			java.util.List<com.liferay.document.library.kernel.model.DLFileVersion> returnValue =
				DLFileVersionServiceUtil.getFileVersions(fileEntryId, status);

			return com.liferay.document.library.kernel.model.DLFileVersionSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getFileVersionsCount(long fileEntryId, int status)
		throws RemoteException {
		try {
			int returnValue = DLFileVersionServiceUtil.getFileVersionsCount(fileEntryId,
					status);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.document.library.kernel.model.DLFileVersionSoap getLatestFileVersion(
		long fileEntryId) throws RemoteException {
		try {
			com.liferay.document.library.kernel.model.DLFileVersion returnValue = DLFileVersionServiceUtil.getLatestFileVersion(fileEntryId);

			return com.liferay.document.library.kernel.model.DLFileVersionSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.document.library.kernel.model.DLFileVersionSoap getLatestFileVersion(
		long fileEntryId, boolean excludeWorkingCopy) throws RemoteException {
		try {
			com.liferay.document.library.kernel.model.DLFileVersion returnValue = DLFileVersionServiceUtil.getLatestFileVersion(fileEntryId,
					excludeWorkingCopy);

			return com.liferay.document.library.kernel.model.DLFileVersionSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(DLFileVersionServiceSoap.class);
}