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

import com.liferay.osb.lcs.service.LCSSubscriptionEntryServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link LCSSubscriptionEntryServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.osb.lcs.model.LCSSubscriptionEntrySoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.osb.lcs.model.LCSSubscriptionEntry}, that is translated to a
 * {@link com.liferay.osb.lcs.model.LCSSubscriptionEntrySoap}. Methods that SOAP cannot
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
 * @see LCSSubscriptionEntryServiceHttp
 * @see com.liferay.osb.lcs.model.LCSSubscriptionEntrySoap
 * @see LCSSubscriptionEntryServiceUtil
 * @generated
 */
@ProviderType
public class LCSSubscriptionEntryServiceSoap {
	public static void addCorpProjectLCSSubscriptionEntries(
		long corpProjectId, java.lang.String lcsSubscriptionEntriesJSON)
		throws RemoteException {
		try {
			LCSSubscriptionEntryServiceUtil.addCorpProjectLCSSubscriptionEntries(corpProjectId,
				lcsSubscriptionEntriesJSON);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void addLCSSubscriptionEntries(long lcsProjectId,
		java.lang.String lcsSubscriptionEntriesJSON) throws RemoteException {
		try {
			LCSSubscriptionEntryServiceUtil.addLCSSubscriptionEntries(lcsProjectId,
				lcsSubscriptionEntriesJSON);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.lcs.model.LCSSubscriptionEntrySoap fetchLCSClusterNodeActiveLCSSubscriptionEntry(
		java.lang.String key) throws RemoteException {
		try {
			com.liferay.osb.lcs.model.LCSSubscriptionEntry returnValue = LCSSubscriptionEntryServiceUtil.fetchLCSClusterNodeActiveLCSSubscriptionEntry(key);

			return com.liferay.osb.lcs.model.LCSSubscriptionEntrySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.lcs.model.LCSSubscriptionEntrySoap[] getLCSProjectLCSSubscriptionEntries(
		long lcsProjectId) throws RemoteException {
		try {
			java.util.List<com.liferay.osb.lcs.model.LCSSubscriptionEntry> returnValue =
				LCSSubscriptionEntryServiceUtil.getLCSProjectLCSSubscriptionEntries(lcsProjectId);

			return com.liferay.osb.lcs.model.LCSSubscriptionEntrySoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.lcs.model.LCSSubscriptionEntrySoap[] getLCSProjectLCSSubscriptionEntries(
		long lcsProjectId, boolean status) throws RemoteException {
		try {
			java.util.List<com.liferay.osb.lcs.model.LCSSubscriptionEntry> returnValue =
				LCSSubscriptionEntryServiceUtil.getLCSProjectLCSSubscriptionEntries(lcsProjectId,
					status);

			return com.liferay.osb.lcs.model.LCSSubscriptionEntrySoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static boolean hasLCSProjectElasticLCSSubscriptionEntry(
		long lcsProjectId) throws RemoteException {
		try {
			boolean returnValue = LCSSubscriptionEntryServiceUtil.hasLCSProjectElasticLCSSubscriptionEntry(lcsProjectId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void refreshLCSProjectLCSSubscriptionEntries()
		throws RemoteException {
		try {
			LCSSubscriptionEntryServiceUtil.refreshLCSProjectLCSSubscriptionEntries();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void refreshLCSProjectLCSSubscriptionEntries(
		long lcsProjectId) throws RemoteException {
		try {
			LCSSubscriptionEntryServiceUtil.refreshLCSProjectLCSSubscriptionEntries(lcsProjectId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(LCSSubscriptionEntryServiceSoap.class);
}