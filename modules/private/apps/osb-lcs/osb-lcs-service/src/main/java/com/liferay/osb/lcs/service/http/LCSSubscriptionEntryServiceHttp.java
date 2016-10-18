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
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * {@link LCSSubscriptionEntryServiceUtil} service utility. The
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
 * @see LCSSubscriptionEntryServiceSoap
 * @see HttpPrincipal
 * @see LCSSubscriptionEntryServiceUtil
 * @generated
 */
@ProviderType
public class LCSSubscriptionEntryServiceHttp {
	public static void addCorpProjectLCSSubscriptionEntries(
		HttpPrincipal httpPrincipal, long corpProjectId,
		java.lang.String lcsSubscriptionEntriesJSON)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSSubscriptionEntryServiceUtil.class,
					"addCorpProjectLCSSubscriptionEntries",
					_addCorpProjectLCSSubscriptionEntriesParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					corpProjectId, lcsSubscriptionEntriesJSON);

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

	public static void addLCSSubscriptionEntries(HttpPrincipal httpPrincipal,
		long lcsProjectId, java.lang.String lcsSubscriptionEntriesJSON)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSSubscriptionEntryServiceUtil.class,
					"addLCSSubscriptionEntries",
					_addLCSSubscriptionEntriesParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					lcsProjectId, lcsSubscriptionEntriesJSON);

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

	public static com.liferay.osb.lcs.model.LCSSubscriptionEntry fetchLCSClusterNodeActiveLCSSubscriptionEntry(
		HttpPrincipal httpPrincipal, java.lang.String key)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSSubscriptionEntryServiceUtil.class,
					"fetchLCSClusterNodeActiveLCSSubscriptionEntry",
					_fetchLCSClusterNodeActiveLCSSubscriptionEntryParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey, key);

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

			return (com.liferay.osb.lcs.model.LCSSubscriptionEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSSubscriptionEntry> getLCSProjectLCSSubscriptionEntries(
		HttpPrincipal httpPrincipal, long lcsProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSSubscriptionEntryServiceUtil.class,
					"getLCSProjectLCSSubscriptionEntries",
					_getLCSProjectLCSSubscriptionEntriesParameterTypes3);

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

			return (java.util.List<com.liferay.osb.lcs.model.LCSSubscriptionEntry>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSSubscriptionEntry> getLCSProjectLCSSubscriptionEntries(
		HttpPrincipal httpPrincipal, long lcsProjectId, boolean status)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSSubscriptionEntryServiceUtil.class,
					"getLCSProjectLCSSubscriptionEntries",
					_getLCSProjectLCSSubscriptionEntriesParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					lcsProjectId, status);

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

			return (java.util.List<com.liferay.osb.lcs.model.LCSSubscriptionEntry>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static boolean hasLCSProjectElasticLCSSubscriptionEntry(
		HttpPrincipal httpPrincipal, long lcsProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSSubscriptionEntryServiceUtil.class,
					"hasLCSProjectElasticLCSSubscriptionEntry",
					_hasLCSProjectElasticLCSSubscriptionEntryParameterTypes5);

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

			return ((Boolean)returnObj).booleanValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void refreshLCSProjectLCSSubscriptionEntries(
		HttpPrincipal httpPrincipal)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSSubscriptionEntryServiceUtil.class,
					"refreshLCSProjectLCSSubscriptionEntries",
					_refreshLCSProjectLCSSubscriptionEntriesParameterTypes6);

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

	public static void refreshLCSProjectLCSSubscriptionEntries(
		HttpPrincipal httpPrincipal, long lcsProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSSubscriptionEntryServiceUtil.class,
					"refreshLCSProjectLCSSubscriptionEntries",
					_refreshLCSProjectLCSSubscriptionEntriesParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					lcsProjectId);

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

	private static Log _log = LogFactoryUtil.getLog(LCSSubscriptionEntryServiceHttp.class);
	private static final Class<?>[] _addCorpProjectLCSSubscriptionEntriesParameterTypes0 =
		new Class[] { long.class, java.lang.String.class };
	private static final Class<?>[] _addLCSSubscriptionEntriesParameterTypes1 = new Class[] {
			long.class, java.lang.String.class
		};
	private static final Class<?>[] _fetchLCSClusterNodeActiveLCSSubscriptionEntryParameterTypes2 =
		new Class[] { java.lang.String.class };
	private static final Class<?>[] _getLCSProjectLCSSubscriptionEntriesParameterTypes3 =
		new Class[] { long.class };
	private static final Class<?>[] _getLCSProjectLCSSubscriptionEntriesParameterTypes4 =
		new Class[] { long.class, boolean.class };
	private static final Class<?>[] _hasLCSProjectElasticLCSSubscriptionEntryParameterTypes5 =
		new Class[] { long.class };
	private static final Class<?>[] _refreshLCSProjectLCSSubscriptionEntriesParameterTypes6 =
		new Class[] {  };
	private static final Class<?>[] _refreshLCSProjectLCSSubscriptionEntriesParameterTypes7 =
		new Class[] { long.class };
}