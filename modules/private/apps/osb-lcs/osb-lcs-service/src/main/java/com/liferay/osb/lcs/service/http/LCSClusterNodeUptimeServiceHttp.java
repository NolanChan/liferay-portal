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

import com.liferay.osb.lcs.service.LCSClusterNodeUptimeServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * {@link LCSClusterNodeUptimeServiceUtil} service utility. The
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
 * @see LCSClusterNodeUptimeServiceSoap
 * @see HttpPrincipal
 * @see LCSClusterNodeUptimeServiceUtil
 * @generated
 */
@ProviderType
public class LCSClusterNodeUptimeServiceHttp {
	public static double getMonthlyElasticLCSClusterNodeUptimeTotal(
		HttpPrincipal httpPrincipal, long lcsProjectId, int month, int year)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSClusterNodeUptimeServiceUtil.class,
					"getMonthlyElasticLCSClusterNodeUptimeTotal",
					_getMonthlyElasticLCSClusterNodeUptimeTotalParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					lcsProjectId, month, year);

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

			return ((Double)returnObj).doubleValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.Map<java.util.Date, java.lang.Double> getMonthlyElasticLCSClusterNodeUptimeTotalMap(
		HttpPrincipal httpPrincipal, long lcsProjectId, int startMonth,
		int startYear, int endMonth, int endYear)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSClusterNodeUptimeServiceUtil.class,
					"getMonthlyElasticLCSClusterNodeUptimeTotalMap",
					_getMonthlyElasticLCSClusterNodeUptimeTotalMapParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					lcsProjectId, startMonth, startYear, endMonth, endYear);

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

			return (java.util.Map<java.util.Date, java.lang.Double>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSClusterNodeUptime> getMonthlyElasticTotalLCSClusterNodeUptimes(
		HttpPrincipal httpPrincipal, long lcsProjectId, int month, int year)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSClusterNodeUptimeServiceUtil.class,
					"getMonthlyElasticTotalLCSClusterNodeUptimes",
					_getMonthlyElasticTotalLCSClusterNodeUptimesParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					lcsProjectId, month, year);

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

			return (java.util.List<com.liferay.osb.lcs.model.LCSClusterNodeUptime>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSClusterNodeUptime> getMonthlyLCSClusterNodeUptimes(
		HttpPrincipal httpPrincipal, long lcsClusterEntryId,
		long lcsClusterNodeId, long lcsProjectId, int month, int year,
		boolean details, boolean elastic)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSClusterNodeUptimeServiceUtil.class,
					"getMonthlyLCSClusterNodeUptimes",
					_getMonthlyLCSClusterNodeUptimesParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					lcsClusterEntryId, lcsClusterNodeId, lcsProjectId, month,
					year, details, elastic);

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

			return (java.util.List<com.liferay.osb.lcs.model.LCSClusterNodeUptime>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void updateLCSClusterNodeUptime(HttpPrincipal httpPrincipal,
		java.lang.String key)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSClusterNodeUptimeServiceUtil.class,
					"updateLCSClusterNodeUptime",
					_updateLCSClusterNodeUptimeParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey, key);

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

	public static void updateLCSClusterNodeUptimes(
		HttpPrincipal httpPrincipal, java.lang.String key,
		java.lang.String uptimesJSON)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSClusterNodeUptimeServiceUtil.class,
					"updateLCSClusterNodeUptimes",
					_updateLCSClusterNodeUptimesParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey, key,
					uptimesJSON);

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

	private static Log _log = LogFactoryUtil.getLog(LCSClusterNodeUptimeServiceHttp.class);
	private static final Class<?>[] _getMonthlyElasticLCSClusterNodeUptimeTotalParameterTypes0 =
		new Class[] { long.class, int.class, int.class };
	private static final Class<?>[] _getMonthlyElasticLCSClusterNodeUptimeTotalMapParameterTypes1 =
		new Class[] { long.class, int.class, int.class, int.class, int.class };
	private static final Class<?>[] _getMonthlyElasticTotalLCSClusterNodeUptimesParameterTypes2 =
		new Class[] { long.class, int.class, int.class };
	private static final Class<?>[] _getMonthlyLCSClusterNodeUptimesParameterTypes3 =
		new Class[] {
			long.class, long.class, long.class, int.class, int.class,
			boolean.class, boolean.class
		};
	private static final Class<?>[] _updateLCSClusterNodeUptimeParameterTypes4 = new Class[] {
			java.lang.String.class
		};
	private static final Class<?>[] _updateLCSClusterNodeUptimesParameterTypes5 = new Class[] {
			java.lang.String.class, java.lang.String.class
		};
}