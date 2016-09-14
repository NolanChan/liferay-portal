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
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * {@link UserLCSMessageServiceUtil} service utility. The
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
 * @see UserLCSMessageServiceSoap
 * @see HttpPrincipal
 * @see UserLCSMessageServiceUtil
 * @generated
 */
@ProviderType
public class UserLCSMessageServiceHttp {
	public static com.liferay.osb.lcs.model.UserLCSMessage deleteUserLCSMessage(
		HttpPrincipal httpPrincipal, long userLCSMessageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(UserLCSMessageServiceUtil.class,
					"deleteUserLCSMessage", _deleteUserLCSMessageParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					userLCSMessageId);

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

			return (com.liferay.osb.lcs.model.UserLCSMessage)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deleteUserLCSMessages(HttpPrincipal httpPrincipal)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(UserLCSMessageServiceUtil.class,
					"deleteUserLCSMessages",
					_deleteUserLCSMessagesParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey);

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

	public static java.util.List<com.liferay.osb.lcs.model.UserLCSMessage> getUserLCSMessages(
		HttpPrincipal httpPrincipal, boolean hidden)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(UserLCSMessageServiceUtil.class,
					"getUserLCSMessages", _getUserLCSMessagesParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey, hidden);

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

			return (java.util.List<com.liferay.osb.lcs.model.UserLCSMessage>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.osb.lcs.model.UserLCSMessage> getUserLCSMessages(
		HttpPrincipal httpPrincipal, int max)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(UserLCSMessageServiceUtil.class,
					"getUserLCSMessages", _getUserLCSMessagesParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey, max);

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

			return (java.util.List<com.liferay.osb.lcs.model.UserLCSMessage>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getUserLCSMessagesCount(HttpPrincipal httpPrincipal)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(UserLCSMessageServiceUtil.class,
					"getUserLCSMessagesCount",
					_getUserLCSMessagesCountParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey);

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

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.lcs.model.UserLCSMessage updateRead(
		HttpPrincipal httpPrincipal, long userLCSMessageId, boolean read)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(UserLCSMessageServiceUtil.class,
					"updateRead", _updateReadParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					userLCSMessageId, read);

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

			return (com.liferay.osb.lcs.model.UserLCSMessage)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(UserLCSMessageServiceHttp.class);
	private static final Class<?>[] _deleteUserLCSMessageParameterTypes0 = new Class[] {
			long.class
		};
	private static final Class<?>[] _deleteUserLCSMessagesParameterTypes1 = new Class[] {
			
		};
	private static final Class<?>[] _getUserLCSMessagesParameterTypes2 = new Class[] {
			boolean.class
		};
	private static final Class<?>[] _getUserLCSMessagesParameterTypes3 = new Class[] {
			int.class
		};
	private static final Class<?>[] _getUserLCSMessagesCountParameterTypes4 = new Class[] {
			
		};
	private static final Class<?>[] _updateReadParameterTypes5 = new Class[] {
			long.class, boolean.class
		};
}