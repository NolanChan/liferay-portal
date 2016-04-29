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

package com.liferay.portal.reports.engine.console.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.reports.engine.console.service.SourceServiceUtil;

import java.rmi.RemoteException;

import java.util.Locale;
import java.util.Map;

/**
 * Provides the SOAP utility for the
 * {@link SourceServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.portal.reports.engine.console.model.SourceSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.portal.reports.engine.console.model.Source}, that is translated to a
 * {@link com.liferay.portal.reports.engine.console.model.SourceSoap}. Methods that SOAP cannot
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
 * @see SourceServiceHttp
 * @see com.liferay.portal.reports.engine.console.model.SourceSoap
 * @see SourceServiceUtil
 * @generated
 */
@ProviderType
public class SourceServiceSoap {
	public static com.liferay.portal.reports.engine.console.model.SourceSoap addSource(
		long groupId, java.lang.String[] nameMapLanguageIds,
		java.lang.String[] nameMapValues, java.lang.String driverClassName,
		java.lang.String driverUrl, java.lang.String driverUserName,
		java.lang.String driverPassword,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			Map<Locale, String> nameMap = LocalizationUtil.getLocalizationMap(nameMapLanguageIds,
					nameMapValues);

			com.liferay.portal.reports.engine.console.model.Source returnValue = SourceServiceUtil.addSource(groupId,
					nameMap, driverClassName, driverUrl, driverUserName,
					driverPassword, serviceContext);

			return com.liferay.portal.reports.engine.console.model.SourceSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portal.reports.engine.console.model.SourceSoap deleteSource(
		long sourceId) throws RemoteException {
		try {
			com.liferay.portal.reports.engine.console.model.Source returnValue = SourceServiceUtil.deleteSource(sourceId);

			return com.liferay.portal.reports.engine.console.model.SourceSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portal.reports.engine.console.model.SourceSoap getSource(
		long sourceId) throws RemoteException {
		try {
			com.liferay.portal.reports.engine.console.model.Source returnValue = SourceServiceUtil.getSource(sourceId);

			return com.liferay.portal.reports.engine.console.model.SourceSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portal.reports.engine.console.model.SourceSoap[] getSources(
		long groupId, java.lang.String name, java.lang.String driverUrl,
		boolean andSearch, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws RemoteException {
		try {
			java.util.List<com.liferay.portal.reports.engine.console.model.Source> returnValue =
				SourceServiceUtil.getSources(groupId, name, driverUrl,
					andSearch, start, end, orderByComparator);

			return com.liferay.portal.reports.engine.console.model.SourceSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getSourcesCount(long groupId, java.lang.String name,
		java.lang.String driverUrl, boolean andSearch)
		throws RemoteException {
		try {
			int returnValue = SourceServiceUtil.getSourcesCount(groupId, name,
					driverUrl, andSearch);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portal.reports.engine.console.model.SourceSoap updateSource(
		long sourceId, java.lang.String[] nameMapLanguageIds,
		java.lang.String[] nameMapValues, java.lang.String driverClassName,
		java.lang.String driverUrl, java.lang.String driverUserName,
		java.lang.String driverPassword,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			Map<Locale, String> nameMap = LocalizationUtil.getLocalizationMap(nameMapLanguageIds,
					nameMapValues);

			com.liferay.portal.reports.engine.console.model.Source returnValue = SourceServiceUtil.updateSource(sourceId,
					nameMap, driverClassName, driverUrl, driverUserName,
					driverPassword, serviceContext);

			return com.liferay.portal.reports.engine.console.model.SourceSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(SourceServiceSoap.class);
}