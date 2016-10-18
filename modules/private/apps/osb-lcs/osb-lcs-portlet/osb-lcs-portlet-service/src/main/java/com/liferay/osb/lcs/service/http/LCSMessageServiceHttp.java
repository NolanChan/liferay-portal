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

import com.liferay.osb.lcs.service.LCSMessageServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * {@link LCSMessageServiceUtil} service utility. The
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
 * @see LCSMessageServiceSoap
 * @see HttpPrincipal
 * @see LCSMessageServiceUtil
 * @generated
 */
@ProviderType
public class LCSMessageServiceHttp {
	public static com.liferay.osb.lcs.model.LCSMessage addCorpProjectLCSMessage(
		HttpPrincipal httpPrincipal, long corpProjectId, long sourceMessageId,
		java.lang.String content, int type)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSMessageServiceUtil.class,
					"addCorpProjectLCSMessage",
					_addCorpProjectLCSMessageParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					corpProjectId, sourceMessageId, content, type);

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

			return (com.liferay.osb.lcs.model.LCSMessage)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.lcs.model.LCSMessage addLCSProjectLCSMessage(
		HttpPrincipal httpPrincipal, long lcsProjectId, long sourceMessageId,
		java.lang.String sourceSystemName, java.lang.String content,
		java.util.Date endDate, boolean global, int severityLevel, int type,
		boolean adminsOnly, boolean generateUserLCSMessages)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSMessageServiceUtil.class,
					"addLCSProjectLCSMessage",
					_addLCSProjectLCSMessageParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					lcsProjectId, sourceMessageId, sourceSystemName, content,
					endDate, global, severityLevel, type, adminsOnly,
					generateUserLCSMessages);

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

			return (com.liferay.osb.lcs.model.LCSMessage)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deleteCorpProjectLCSMessage(
		HttpPrincipal httpPrincipal, long corpProjectId, long sourceMessageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSMessageServiceUtil.class,
					"deleteCorpProjectLCSMessage",
					_deleteCorpProjectLCSMessageParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					corpProjectId, sourceMessageId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deleteLCSProjectLCSMessage(HttpPrincipal httpPrincipal,
		long lcsProjectId, long sourceMessageId,
		java.lang.String sourceSystemName)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSMessageServiceUtil.class,
					"deleteLCSProjectLCSMessage",
					_deleteLCSProjectLCSMessageParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					lcsProjectId, sourceMessageId, sourceSystemName);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSMessage> getLCSProjectLCSMessages(
		HttpPrincipal httpPrincipal, long lcsProjectId,
		java.lang.String sourceSystemName)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSMessageServiceUtil.class,
					"getLCSProjectLCSMessages",
					_getLCSProjectLCSMessagesParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					lcsProjectId, sourceSystemName);

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

			return (java.util.List<com.liferay.osb.lcs.model.LCSMessage>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSMessage> getLCSMessages(
		HttpPrincipal httpPrincipal, java.util.Date modifyDateGT,
		java.util.Date modifyDateLT)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSMessageServiceUtil.class,
					"getLCSMessages", _getLCSMessagesParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					modifyDateGT, modifyDateLT);

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

			return (java.util.List<com.liferay.osb.lcs.model.LCSMessage>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(LCSMessageServiceHttp.class);
	private static final Class<?>[] _addCorpProjectLCSMessageParameterTypes0 = new Class[] {
			long.class, long.class, java.lang.String.class, int.class
		};
	private static final Class<?>[] _addLCSProjectLCSMessageParameterTypes1 = new Class[] {
			long.class, long.class, java.lang.String.class,
			java.lang.String.class, java.util.Date.class, boolean.class,
			int.class, int.class, boolean.class, boolean.class
		};
	private static final Class<?>[] _deleteCorpProjectLCSMessageParameterTypes2 = new Class[] {
			long.class, long.class
		};
	private static final Class<?>[] _deleteLCSProjectLCSMessageParameterTypes3 = new Class[] {
			long.class, long.class, java.lang.String.class
		};
	private static final Class<?>[] _getLCSProjectLCSMessagesParameterTypes4 = new Class[] {
			long.class, java.lang.String.class
		};
	private static final Class<?>[] _getLCSMessagesParameterTypes5 = new Class[] {
			java.util.Date.class, java.util.Date.class
		};
}