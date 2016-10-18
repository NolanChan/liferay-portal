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
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * {@link LCSClusterEntryServiceUtil} service utility. The
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
 * @see LCSClusterEntryServiceSoap
 * @see HttpPrincipal
 * @see LCSClusterEntryServiceUtil
 * @generated
 */
@ProviderType
public class LCSClusterEntryServiceHttp {
	public static com.liferay.osb.lcs.model.LCSClusterEntry addLCSClusterEntry(
		HttpPrincipal httpPrincipal, long lcsProjectId, java.lang.String name,
		java.lang.String description, java.lang.String location,
		java.lang.String subscriptionType, int type)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSClusterEntryServiceUtil.class,
					"addLCSClusterEntry", _addLCSClusterEntryParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					lcsProjectId, name, description, location,
					subscriptionType, type);

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

			return (com.liferay.osb.lcs.model.LCSClusterEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.lcs.model.LCSClusterEntry deleteLCSClusterEntry(
		HttpPrincipal httpPrincipal, long lcsClusterEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSClusterEntryServiceUtil.class,
					"deleteLCSClusterEntry",
					_deleteLCSClusterEntryParameterTypes1);

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

			return (com.liferay.osb.lcs.model.LCSClusterEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deleteLCSProjectClusters(HttpPrincipal httpPrincipal,
		long lcsProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSClusterEntryServiceUtil.class,
					"deleteLCSProjectClusters",
					_deleteLCSProjectClustersParameterTypes2);

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

	public static byte[] exportLCSClusterEntryToken(
		HttpPrincipal httpPrincipal, long lcsProjectId,
		java.lang.String lcsClusterEntryName,
		java.lang.String subscriptionType, int type)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSClusterEntryServiceUtil.class,
					"exportLCSClusterEntryToken",
					_exportLCSClusterEntryTokenParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					lcsProjectId, lcsClusterEntryName, subscriptionType, type);

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

			return (byte[])returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSClusterEntry> getArchivedLCSProjectLCSClusterEntries(
		HttpPrincipal httpPrincipal, long lcsProjectId,
		java.lang.String subscriptionType)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSClusterEntryServiceUtil.class,
					"getArchivedLCSProjectLCSClusterEntries",
					_getArchivedLCSProjectLCSClusterEntriesParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					lcsProjectId, subscriptionType);

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

			return (java.util.List<com.liferay.osb.lcs.model.LCSClusterEntry>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.lcs.model.LCSClusterEntry getLCSClusterEntry(
		HttpPrincipal httpPrincipal, long lcsClusterEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSClusterEntryServiceUtil.class,
					"getLCSClusterEntry", _getLCSClusterEntryParameterTypes5);

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

			return (com.liferay.osb.lcs.model.LCSClusterEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSClusterEntry> getLCSProjectLCSClusterEntries(
		HttpPrincipal httpPrincipal, long lcsProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSClusterEntryServiceUtil.class,
					"getLCSProjectLCSClusterEntries",
					_getLCSProjectLCSClusterEntriesParameterTypes6);

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

			return (java.util.List<com.liferay.osb.lcs.model.LCSClusterEntry>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSClusterEntry> getLCSProjectLCSClusterEntries(
		HttpPrincipal httpPrincipal, long lcsProjectId,
		java.lang.String subscriptionType)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSClusterEntryServiceUtil.class,
					"getLCSProjectLCSClusterEntries",
					_getLCSProjectLCSClusterEntriesParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					lcsProjectId, subscriptionType);

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

			return (java.util.List<com.liferay.osb.lcs.model.LCSClusterEntry>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSClusterEntry> getLCSProjectManageableLCSClusterEntries(
		HttpPrincipal httpPrincipal, long lcsProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSClusterEntryServiceUtil.class,
					"getLCSProjectManageableLCSClusterEntries",
					_getLCSProjectManageableLCSClusterEntriesParameterTypes8);

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

			return (java.util.List<com.liferay.osb.lcs.model.LCSClusterEntry>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSClusterEntry> getUserLCSClusterEntries(
		HttpPrincipal httpPrincipal)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSClusterEntryServiceUtil.class,
					"getUserLCSClusterEntries",
					_getUserLCSClusterEntriesParameterTypes9);

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

			return (java.util.List<com.liferay.osb.lcs.model.LCSClusterEntry>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSClusterEntry> getUserLCSClusterEntries(
		HttpPrincipal httpPrincipal, long lcsProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSClusterEntryServiceUtil.class,
					"getUserLCSClusterEntries",
					_getUserLCSClusterEntriesParameterTypes10);

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

			return (java.util.List<com.liferay.osb.lcs.model.LCSClusterEntry>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.lcs.model.LCSClusterEntry updateElastic(
		HttpPrincipal httpPrincipal, long lcsClusterEntryId, boolean elastic)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSClusterEntryServiceUtil.class,
					"updateElastic", _updateElasticParameterTypes11);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					lcsClusterEntryId, elastic);

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

			return (com.liferay.osb.lcs.model.LCSClusterEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.lcs.model.LCSClusterEntry updateHighPageLoadTime(
		HttpPrincipal httpPrincipal, long lcsClusterEntryId,
		int highPageLoadTime)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSClusterEntryServiceUtil.class,
					"updateHighPageLoadTime",
					_updateHighPageLoadTimeParameterTypes12);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					lcsClusterEntryId, highPageLoadTime);

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

			return (com.liferay.osb.lcs.model.LCSClusterEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.lcs.model.LCSClusterEntry updateLCSClusterEntry(
		HttpPrincipal httpPrincipal, long lcsClusterEntryId,
		java.lang.String name, java.lang.String description,
		java.lang.String location)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSClusterEntryServiceUtil.class,
					"updateLCSClusterEntry",
					_updateLCSClusterEntryParameterTypes13);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					lcsClusterEntryId, name, description, location);

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

			return (com.liferay.osb.lcs.model.LCSClusterEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.lcs.model.LCSClusterEntry updateMediumPageLoadTime(
		HttpPrincipal httpPrincipal, long lcsClusterEntryId,
		int mediumPageLoadTime)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSClusterEntryServiceUtil.class,
					"updateMediumPageLoadTime",
					_updateMediumPageLoadTimeParameterTypes14);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					lcsClusterEntryId, mediumPageLoadTime);

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

			return (com.liferay.osb.lcs.model.LCSClusterEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.lcs.model.LCSClusterEntry updateSubscriptionType(
		HttpPrincipal httpPrincipal, long lcsClusterEntryId,
		java.lang.String subscriptionType)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSClusterEntryServiceUtil.class,
					"updateSubscriptionType",
					_updateSubscriptionTypeParameterTypes15);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					lcsClusterEntryId, subscriptionType);

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

			return (com.liferay.osb.lcs.model.LCSClusterEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(LCSClusterEntryServiceHttp.class);
	private static final Class<?>[] _addLCSClusterEntryParameterTypes0 = new Class[] {
			long.class, java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class, int.class
		};
	private static final Class<?>[] _deleteLCSClusterEntryParameterTypes1 = new Class[] {
			long.class
		};
	private static final Class<?>[] _deleteLCSProjectClustersParameterTypes2 = new Class[] {
			long.class
		};
	private static final Class<?>[] _exportLCSClusterEntryTokenParameterTypes3 = new Class[] {
			long.class, java.lang.String.class, java.lang.String.class,
			int.class
		};
	private static final Class<?>[] _getArchivedLCSProjectLCSClusterEntriesParameterTypes4 =
		new Class[] { long.class, java.lang.String.class };
	private static final Class<?>[] _getLCSClusterEntryParameterTypes5 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getLCSProjectLCSClusterEntriesParameterTypes6 =
		new Class[] { long.class };
	private static final Class<?>[] _getLCSProjectLCSClusterEntriesParameterTypes7 =
		new Class[] { long.class, java.lang.String.class };
	private static final Class<?>[] _getLCSProjectManageableLCSClusterEntriesParameterTypes8 =
		new Class[] { long.class };
	private static final Class<?>[] _getUserLCSClusterEntriesParameterTypes9 = new Class[] {
			
		};
	private static final Class<?>[] _getUserLCSClusterEntriesParameterTypes10 = new Class[] {
			long.class
		};
	private static final Class<?>[] _updateElasticParameterTypes11 = new Class[] {
			long.class, boolean.class
		};
	private static final Class<?>[] _updateHighPageLoadTimeParameterTypes12 = new Class[] {
			long.class, int.class
		};
	private static final Class<?>[] _updateLCSClusterEntryParameterTypes13 = new Class[] {
			long.class, java.lang.String.class, java.lang.String.class,
			java.lang.String.class
		};
	private static final Class<?>[] _updateMediumPageLoadTimeParameterTypes14 = new Class[] {
			long.class, int.class
		};
	private static final Class<?>[] _updateSubscriptionTypeParameterTypes15 = new Class[] {
			long.class, java.lang.String.class
		};
}