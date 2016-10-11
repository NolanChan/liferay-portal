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

import com.liferay.osb.lcs.service.LCSClusterEntryServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link LCSClusterEntryServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.osb.lcs.model.LCSClusterEntrySoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.osb.lcs.model.LCSClusterEntry}, that is translated to a
 * {@link com.liferay.osb.lcs.model.LCSClusterEntrySoap}. Methods that SOAP cannot
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
 * @see LCSClusterEntryServiceHttp
 * @see com.liferay.osb.lcs.model.LCSClusterEntrySoap
 * @see LCSClusterEntryServiceUtil
 * @generated
 */
@ProviderType
public class LCSClusterEntryServiceSoap {
	@Deprecated
	public static com.liferay.osb.lcs.model.LCSClusterEntrySoap addLCSClusterEntry(
		long lcsProjectId, java.lang.String name, java.lang.String description,
		java.lang.String location, int type) throws RemoteException {
		try {
			com.liferay.osb.lcs.model.LCSClusterEntry returnValue = LCSClusterEntryServiceUtil.addLCSClusterEntry(lcsProjectId,
					name, description, location, type);

			return com.liferay.osb.lcs.model.LCSClusterEntrySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.lcs.model.LCSClusterEntrySoap addLCSClusterEntry(
		long lcsProjectId, java.lang.String name, java.lang.String description,
		java.lang.String location, java.lang.String subscriptionType, int type)
		throws RemoteException {
		try {
			com.liferay.osb.lcs.model.LCSClusterEntry returnValue = LCSClusterEntryServiceUtil.addLCSClusterEntry(lcsProjectId,
					name, description, location, subscriptionType, type);

			return com.liferay.osb.lcs.model.LCSClusterEntrySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.lcs.model.LCSClusterEntrySoap deleteLCSClusterEntry(
		long lcsClusterEntryId) throws RemoteException {
		try {
			com.liferay.osb.lcs.model.LCSClusterEntry returnValue = LCSClusterEntryServiceUtil.deleteLCSClusterEntry(lcsClusterEntryId);

			return com.liferay.osb.lcs.model.LCSClusterEntrySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteLCSProjectClusters(long lcsProjectId)
		throws RemoteException {
		try {
			LCSClusterEntryServiceUtil.deleteLCSProjectClusters(lcsProjectId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static byte[] exportLCSClusterEntryToken(long lcsProjectId,
		java.lang.String lcsClusterEntryName,
		java.lang.String subscriptionType, int type) throws RemoteException {
		try {
			byte[] returnValue = LCSClusterEntryServiceUtil.exportLCSClusterEntryToken(lcsProjectId,
					lcsClusterEntryName, subscriptionType, type);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.lcs.model.LCSClusterEntrySoap[] getArchivedLCSProjectLCSClusterEntries(
		long lcsProjectId, java.lang.String subscriptionType)
		throws RemoteException {
		try {
			java.util.List<com.liferay.osb.lcs.model.LCSClusterEntry> returnValue =
				LCSClusterEntryServiceUtil.getArchivedLCSProjectLCSClusterEntries(lcsProjectId,
					subscriptionType);

			return com.liferay.osb.lcs.model.LCSClusterEntrySoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.lcs.model.LCSClusterEntrySoap getLCSClusterEntry(
		long lcsClusterEntryId) throws RemoteException {
		try {
			com.liferay.osb.lcs.model.LCSClusterEntry returnValue = LCSClusterEntryServiceUtil.getLCSClusterEntry(lcsClusterEntryId);

			return com.liferay.osb.lcs.model.LCSClusterEntrySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.lcs.model.LCSClusterEntrySoap[] getLCSProjectLCSClusterEntries(
		long lcsProjectId) throws RemoteException {
		try {
			java.util.List<com.liferay.osb.lcs.model.LCSClusterEntry> returnValue =
				LCSClusterEntryServiceUtil.getLCSProjectLCSClusterEntries(lcsProjectId);

			return com.liferay.osb.lcs.model.LCSClusterEntrySoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.lcs.model.LCSClusterEntrySoap[] getLCSProjectLCSClusterEntries(
		long lcsProjectId, java.lang.String subscriptionType)
		throws RemoteException {
		try {
			java.util.List<com.liferay.osb.lcs.model.LCSClusterEntry> returnValue =
				LCSClusterEntryServiceUtil.getLCSProjectLCSClusterEntries(lcsProjectId,
					subscriptionType);

			return com.liferay.osb.lcs.model.LCSClusterEntrySoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.lcs.model.LCSClusterEntrySoap[] getLCSProjectManageableLCSClusterEntries(
		long lcsProjectId) throws RemoteException {
		try {
			java.util.List<com.liferay.osb.lcs.model.LCSClusterEntry> returnValue =
				LCSClusterEntryServiceUtil.getLCSProjectManageableLCSClusterEntries(lcsProjectId);

			return com.liferay.osb.lcs.model.LCSClusterEntrySoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.lcs.model.LCSClusterEntrySoap[] getUserLCSClusterEntries()
		throws RemoteException {
		try {
			java.util.List<com.liferay.osb.lcs.model.LCSClusterEntry> returnValue =
				LCSClusterEntryServiceUtil.getUserLCSClusterEntries();

			return com.liferay.osb.lcs.model.LCSClusterEntrySoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.lcs.model.LCSClusterEntrySoap[] getUserLCSClusterEntries(
		long lcsProjectId) throws RemoteException {
		try {
			java.util.List<com.liferay.osb.lcs.model.LCSClusterEntry> returnValue =
				LCSClusterEntryServiceUtil.getUserLCSClusterEntries(lcsProjectId);

			return com.liferay.osb.lcs.model.LCSClusterEntrySoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.lcs.model.LCSClusterEntrySoap updateElastic(
		long lcsClusterEntryId, boolean elastic) throws RemoteException {
		try {
			com.liferay.osb.lcs.model.LCSClusterEntry returnValue = LCSClusterEntryServiceUtil.updateElastic(lcsClusterEntryId,
					elastic);

			return com.liferay.osb.lcs.model.LCSClusterEntrySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.lcs.model.LCSClusterEntrySoap updateHighPageLoadTime(
		long lcsClusterEntryId, int highPageLoadTime) throws RemoteException {
		try {
			com.liferay.osb.lcs.model.LCSClusterEntry returnValue = LCSClusterEntryServiceUtil.updateHighPageLoadTime(lcsClusterEntryId,
					highPageLoadTime);

			return com.liferay.osb.lcs.model.LCSClusterEntrySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.lcs.model.LCSClusterEntrySoap updateLCSClusterEntry(
		long lcsClusterEntryId, java.lang.String name,
		java.lang.String description, java.lang.String location)
		throws RemoteException {
		try {
			com.liferay.osb.lcs.model.LCSClusterEntry returnValue = LCSClusterEntryServiceUtil.updateLCSClusterEntry(lcsClusterEntryId,
					name, description, location);

			return com.liferay.osb.lcs.model.LCSClusterEntrySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.lcs.model.LCSClusterEntrySoap updateMediumPageLoadTime(
		long lcsClusterEntryId, int mediumPageLoadTime)
		throws RemoteException {
		try {
			com.liferay.osb.lcs.model.LCSClusterEntry returnValue = LCSClusterEntryServiceUtil.updateMediumPageLoadTime(lcsClusterEntryId,
					mediumPageLoadTime);

			return com.liferay.osb.lcs.model.LCSClusterEntrySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.lcs.model.LCSClusterEntrySoap updateSubscriptionType(
		long lcsClusterEntryId, java.lang.String subscriptionType)
		throws RemoteException {
		try {
			com.liferay.osb.lcs.model.LCSClusterEntry returnValue = LCSClusterEntryServiceUtil.updateSubscriptionType(lcsClusterEntryId,
					subscriptionType);

			return com.liferay.osb.lcs.model.LCSClusterEntrySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(LCSClusterEntryServiceSoap.class);
}