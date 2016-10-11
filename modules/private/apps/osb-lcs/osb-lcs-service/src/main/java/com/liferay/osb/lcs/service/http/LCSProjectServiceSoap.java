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

import com.liferay.osb.lcs.service.LCSProjectServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link LCSProjectServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.osb.lcs.model.LCSProjectSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.osb.lcs.model.LCSProject}, that is translated to a
 * {@link com.liferay.osb.lcs.model.LCSProjectSoap}. Methods that SOAP cannot
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
 * @see LCSProjectServiceHttp
 * @see com.liferay.osb.lcs.model.LCSProjectSoap
 * @see LCSProjectServiceUtil
 * @generated
 */
@ProviderType
public class LCSProjectServiceSoap {
	public static com.liferay.osb.lcs.model.LCSProjectSoap addDefaultLCSProject()
		throws RemoteException {
		try {
			com.liferay.osb.lcs.model.LCSProject returnValue = LCSProjectServiceUtil.addDefaultLCSProject();

			return com.liferay.osb.lcs.model.LCSProjectSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.lcs.model.LCSProjectSoap addLCSProject(
		java.lang.String sourceSystemName, java.lang.String name)
		throws RemoteException {
		try {
			com.liferay.osb.lcs.model.LCSProject returnValue = LCSProjectServiceUtil.addLCSProject(sourceSystemName,
					name);

			return com.liferay.osb.lcs.model.LCSProjectSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static boolean checkUserAccountEntryLCSProject()
		throws RemoteException {
		try {
			boolean returnValue = LCSProjectServiceUtil.checkUserAccountEntryLCSProject();

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.lcs.model.LCSProjectSoap deleteLCSProject(
		long lcsProjectId) throws RemoteException {
		try {
			com.liferay.osb.lcs.model.LCSProject returnValue = LCSProjectServiceUtil.deleteLCSProject(lcsProjectId);

			return com.liferay.osb.lcs.model.LCSProjectSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.lcs.model.LCSProjectSoap getLCSProject(
		long lcsProjectId) throws RemoteException {
		try {
			com.liferay.osb.lcs.model.LCSProject returnValue = LCSProjectServiceUtil.getLCSProject(lcsProjectId);

			return com.liferay.osb.lcs.model.LCSProjectSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static java.lang.String getLCSProjectAdministratorEmailAddress(
		long lcsProjectId) throws RemoteException {
		try {
			java.lang.String returnValue = LCSProjectServiceUtil.getLCSProjectAdministratorEmailAddress(lcsProjectId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static long getUserDefaultLCSProjectId() throws RemoteException {
		try {
			long returnValue = LCSProjectServiceUtil.getUserDefaultLCSProjectId();

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.lcs.model.LCSProjectSoap[] getUserDomainLCSProjects()
		throws RemoteException {
		try {
			java.util.List<com.liferay.osb.lcs.model.LCSProject> returnValue = LCSProjectServiceUtil.getUserDomainLCSProjects();

			return com.liferay.osb.lcs.model.LCSProjectSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.lcs.model.LCSProjectSoap[] getUserLCSProjects()
		throws RemoteException {
		try {
			java.util.List<com.liferay.osb.lcs.model.LCSProject> returnValue = LCSProjectServiceUtil.getUserLCSProjects();

			return com.liferay.osb.lcs.model.LCSProjectSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.lcs.model.LCSProjectSoap[] getUserLCSProjects(
		boolean lcsRole) throws RemoteException {
		try {
			java.util.List<com.liferay.osb.lcs.model.LCSProject> returnValue = LCSProjectServiceUtil.getUserLCSProjects(lcsRole);

			return com.liferay.osb.lcs.model.LCSProjectSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.lcs.model.LCSProjectSoap[] getUserLCSProjects(
		boolean lcsRole, int role) throws RemoteException {
		try {
			java.util.List<com.liferay.osb.lcs.model.LCSProject> returnValue = LCSProjectServiceUtil.getUserLCSProjects(lcsRole,
					role);

			return com.liferay.osb.lcs.model.LCSProjectSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.lcs.model.LCSProjectSoap[] getUserManageableLCSProjects()
		throws RemoteException {
		try {
			java.util.List<com.liferay.osb.lcs.model.LCSProject> returnValue = LCSProjectServiceUtil.getUserManageableLCSProjects();

			return com.liferay.osb.lcs.model.LCSProjectSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.lcs.model.LCSProjectSoap updateLCSProjectName(
		long lcsProjectId, java.lang.String name) throws RemoteException {
		try {
			com.liferay.osb.lcs.model.LCSProject returnValue = LCSProjectServiceUtil.updateLCSProjectName(lcsProjectId,
					name);

			return com.liferay.osb.lcs.model.LCSProjectSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(LCSProjectServiceSoap.class);
}