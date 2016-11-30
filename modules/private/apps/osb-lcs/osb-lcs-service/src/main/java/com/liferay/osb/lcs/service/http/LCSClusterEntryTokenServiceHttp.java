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

import com.liferay.osb.lcs.service.LCSClusterEntryTokenServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * {@link LCSClusterEntryTokenServiceUtil} service utility. The
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
 * @see LCSClusterEntryTokenServiceSoap
 * @see HttpPrincipal
 * @see LCSClusterEntryTokenServiceUtil
 * @generated
 */
@ProviderType
public class LCSClusterEntryTokenServiceHttp {
	public static com.liferay.osb.lcs.model.LCSClusterEntryToken addLCSClusterEntryToken(
		HttpPrincipal httpPrincipal, long lcsClusterEntryId,
		java.util.Map<java.lang.String, java.lang.String> lcsServicesConfiguration)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSClusterEntryTokenServiceUtil.class,
					"addLCSClusterEntryToken",
					_addLCSClusterEntryTokenParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					lcsClusterEntryId, lcsServicesConfiguration);

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

			return (com.liferay.osb.lcs.model.LCSClusterEntryToken)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.lcs.model.LCSClusterEntryToken deleteLCSClusterEntryToken(
		HttpPrincipal httpPrincipal, long lcsClusterEntryTokenId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSClusterEntryTokenServiceUtil.class,
					"deleteLCSClusterEntryToken",
					_deleteLCSClusterEntryTokenParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					lcsClusterEntryTokenId);

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

			return (com.liferay.osb.lcs.model.LCSClusterEntryToken)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.lcs.model.LCSClusterEntryToken fetchLCSClusterEntryLCSClusterEntryToken(
		HttpPrincipal httpPrincipal, long lcsClusterEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSClusterEntryTokenServiceUtil.class,
					"fetchLCSClusterEntryLCSClusterEntryToken",
					_fetchLCSClusterEntryLCSClusterEntryTokenParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					lcsClusterEntryId);

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

			return (com.liferay.osb.lcs.model.LCSClusterEntryToken)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.lcs.model.LCSClusterEntryToken fetchLCSClusterEntryToken(
		HttpPrincipal httpPrincipal, long lcsClusterEntryTokenId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSClusterEntryTokenServiceUtil.class,
					"fetchLCSClusterEntryToken",
					_fetchLCSClusterEntryTokenParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					lcsClusterEntryTokenId);

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

			return (com.liferay.osb.lcs.model.LCSClusterEntryToken)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static boolean isValid(HttpPrincipal httpPrincipal,
		long lcsClusterEntryTokenId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSClusterEntryTokenServiceUtil.class,
					"isValid", _isValidParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					lcsClusterEntryTokenId);

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

			return ((Boolean)returnObj).booleanValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.lcs.model.LCSClusterEntryToken regenerateLCSClusterEntryToken(
		HttpPrincipal httpPrincipal, long lcsClusterEntryId,
		java.util.Map<java.lang.String, java.lang.String> lcsServicesConfiguration)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSClusterEntryTokenServiceUtil.class,
					"regenerateLCSClusterEntryToken",
					_regenerateLCSClusterEntryTokenParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					lcsClusterEntryId, lcsServicesConfiguration);

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

			return (com.liferay.osb.lcs.model.LCSClusterEntryToken)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(LCSClusterEntryTokenServiceHttp.class);
	private static final Class<?>[] _addLCSClusterEntryTokenParameterTypes0 = new Class[] {
			long.class, java.util.Map.class
		};
	private static final Class<?>[] _deleteLCSClusterEntryTokenParameterTypes1 = new Class[] {
			long.class
		};
	private static final Class<?>[] _fetchLCSClusterEntryLCSClusterEntryTokenParameterTypes2 =
		new Class[] { long.class };
	private static final Class<?>[] _fetchLCSClusterEntryTokenParameterTypes3 = new Class[] {
			long.class
		};
	private static final Class<?>[] _isValidParameterTypes4 = new Class[] {
			long.class
		};
	private static final Class<?>[] _regenerateLCSClusterEntryTokenParameterTypes5 =
		new Class[] { long.class, java.util.Map.class };
}