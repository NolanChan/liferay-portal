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

import com.liferay.osb.lcs.service.LCSInvitationServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * {@link LCSInvitationServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * {@link HttpPrincipal} parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Igor Beslic
 * @see LCSInvitationServiceSoap
 * @see HttpPrincipal
 * @see LCSInvitationServiceUtil
 * @generated
 */
@ProviderType
public class LCSInvitationServiceHttp {
	public static com.liferay.osb.lcs.model.LCSInvitation addLCSInvitation(
		HttpPrincipal httpPrincipal, long lcsProjectId,
		java.lang.String emailAddress, long lcsClusterEntryId, int role)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSInvitationServiceUtil.class,
					"addLCSInvitation", _addLCSInvitationParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					lcsProjectId, emailAddress, lcsClusterEntryId, role);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.osb.lcs.model.LCSInvitation)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.lcs.model.LCSInvitation deleteLCSInvitation(
		HttpPrincipal httpPrincipal, long lcsInvitationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSInvitationServiceUtil.class,
					"deleteLCSInvitation", _deleteLCSInvitationParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					lcsInvitationId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.osb.lcs.model.LCSInvitation)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.lcs.model.LCSInvitation getLCSProjectLCSInvitation(
		HttpPrincipal httpPrincipal, long lcsProjectId,
		java.lang.String emailAddress)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSInvitationServiceUtil.class,
					"getLCSProjectLCSInvitation",
					_getLCSProjectLCSInvitationParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					lcsProjectId, emailAddress);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.osb.lcs.model.LCSInvitation)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSInvitation> getLCSProjectLCSInvitations(
		HttpPrincipal httpPrincipal, long lcsProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSInvitationServiceUtil.class,
					"getLCSProjectLCSInvitations",
					_getLCSProjectLCSInvitationsParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					lcsProjectId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.osb.lcs.model.LCSInvitation>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(LCSInvitationServiceHttp.class);
	private static final Class<?>[] _addLCSInvitationParameterTypes0 = new Class[] {
			long.class, java.lang.String.class, long.class, int.class
		};
	private static final Class<?>[] _deleteLCSInvitationParameterTypes1 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getLCSProjectLCSInvitationParameterTypes2 = new Class[] {
			long.class, java.lang.String.class
		};
	private static final Class<?>[] _getLCSProjectLCSInvitationsParameterTypes3 = new Class[] {
			long.class
		};
}