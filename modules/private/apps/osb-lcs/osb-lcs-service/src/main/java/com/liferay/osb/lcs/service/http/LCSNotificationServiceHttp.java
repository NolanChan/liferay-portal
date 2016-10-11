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

import com.liferay.osb.lcs.service.LCSNotificationServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * {@link LCSNotificationServiceUtil} service utility. The
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
 * @see LCSNotificationServiceSoap
 * @see HttpPrincipal
 * @see LCSNotificationServiceUtil
 * @generated
 */
@ProviderType
public class LCSNotificationServiceHttp {
	public static com.liferay.osb.lcs.model.LCSNotification addLCSClusterEntryLCSNotification(
		HttpPrincipal httpPrincipal, long lcsClusterEntryId, boolean enabled,
		int type) throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSNotificationServiceUtil.class,
					"addLCSClusterEntryLCSNotification",
					_addLCSClusterEntryLCSNotificationParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					lcsClusterEntryId, enabled, type);

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

			return (com.liferay.osb.lcs.model.LCSNotification)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.lcs.model.LCSNotification addLCSClusterNodeLCSNotification(
		HttpPrincipal httpPrincipal, long lcsClusterNodeId, boolean enabled,
		int type) throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSNotificationServiceUtil.class,
					"addLCSClusterNodeLCSNotification",
					_addLCSClusterNodeLCSNotificationParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					lcsClusterNodeId, enabled, type);

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

			return (com.liferay.osb.lcs.model.LCSNotification)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.lcs.model.LCSNotification addLCSProjectLCSNotification(
		HttpPrincipal httpPrincipal, long lcsProjectId, boolean enabled,
		int type) throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSNotificationServiceUtil.class,
					"addLCSProjectLCSNotification",
					_addLCSProjectLCSNotificationParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					lcsProjectId, enabled, type);

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

			return (com.liferay.osb.lcs.model.LCSNotification)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deleteLCSNotification(HttpPrincipal httpPrincipal,
		long lcsNotificationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSNotificationServiceUtil.class,
					"deleteLCSNotification",
					_deleteLCSNotificationParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					lcsNotificationId);

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

	public static void deleteLCSNotifications(HttpPrincipal httpPrincipal)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSNotificationServiceUtil.class,
					"deleteLCSNotifications",
					_deleteLCSNotificationsParameterTypes4);

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

	public static com.liferay.osb.lcs.model.LCSNotification fetchLCSClusterEntryLCSNotification(
		HttpPrincipal httpPrincipal, long lcsClusterEntryId, int type)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSNotificationServiceUtil.class,
					"fetchLCSClusterEntryLCSNotification",
					_fetchLCSClusterEntryLCSNotificationParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					lcsClusterEntryId, type);

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

			return (com.liferay.osb.lcs.model.LCSNotification)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.lcs.model.LCSNotification fetchLCSClusterNodeLCSNotification(
		HttpPrincipal httpPrincipal, long lcsClusterNodeId, int type)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSNotificationServiceUtil.class,
					"fetchLCSClusterNodeLCSNotification",
					_fetchLCSClusterNodeLCSNotificationParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					lcsClusterNodeId, type);

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

			return (com.liferay.osb.lcs.model.LCSNotification)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.lcs.model.LCSNotification fetchLCSProjectLCSNotification(
		HttpPrincipal httpPrincipal, long lcsProjectId, int type)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSNotificationServiceUtil.class,
					"fetchLCSProjectLCSNotification",
					_fetchLCSProjectLCSNotificationParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					lcsProjectId, type);

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

			return (com.liferay.osb.lcs.model.LCSNotification)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSNotification> getLCSClusterEntryLCSNotifications(
		HttpPrincipal httpPrincipal, long lcsClusterEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSNotificationServiceUtil.class,
					"getLCSClusterEntryLCSNotifications",
					_getLCSClusterEntryLCSNotificationsParameterTypes8);

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

			return (java.util.List<com.liferay.osb.lcs.model.LCSNotification>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSNotification> getLCSClusterNodeLCSNotifications(
		HttpPrincipal httpPrincipal, long lcsClusterNodeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSNotificationServiceUtil.class,
					"getLCSClusterNodeLCSNotifications",
					_getLCSClusterNodeLCSNotificationsParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					lcsClusterNodeId);

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

			return (java.util.List<com.liferay.osb.lcs.model.LCSNotification>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSNotification> getLCSNotifications(
		HttpPrincipal httpPrincipal)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSNotificationServiceUtil.class,
					"getLCSNotifications", _getLCSNotificationsParameterTypes10);

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

			return (java.util.List<com.liferay.osb.lcs.model.LCSNotification>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSNotification> getLCSProjectLCSNotifications(
		HttpPrincipal httpPrincipal, long lcsProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSNotificationServiceUtil.class,
					"getLCSProjectLCSNotifications",
					_getLCSProjectLCSNotificationsParameterTypes11);

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

			return (java.util.List<com.liferay.osb.lcs.model.LCSNotification>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void updateLCSNotification(HttpPrincipal httpPrincipal,
		com.liferay.osb.lcs.model.LCSNotification lcsNotification)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSNotificationServiceUtil.class,
					"updateLCSNotification",
					_updateLCSNotificationParameterTypes12);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					lcsNotification);

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

	private static Log _log = LogFactoryUtil.getLog(LCSNotificationServiceHttp.class);
	private static final Class<?>[] _addLCSClusterEntryLCSNotificationParameterTypes0 =
		new Class[] { long.class, boolean.class, int.class };
	private static final Class<?>[] _addLCSClusterNodeLCSNotificationParameterTypes1 =
		new Class[] { long.class, boolean.class, int.class };
	private static final Class<?>[] _addLCSProjectLCSNotificationParameterTypes2 =
		new Class[] { long.class, boolean.class, int.class };
	private static final Class<?>[] _deleteLCSNotificationParameterTypes3 = new Class[] {
			long.class
		};
	private static final Class<?>[] _deleteLCSNotificationsParameterTypes4 = new Class[] {
			
		};
	private static final Class<?>[] _fetchLCSClusterEntryLCSNotificationParameterTypes5 =
		new Class[] { long.class, int.class };
	private static final Class<?>[] _fetchLCSClusterNodeLCSNotificationParameterTypes6 =
		new Class[] { long.class, int.class };
	private static final Class<?>[] _fetchLCSProjectLCSNotificationParameterTypes7 =
		new Class[] { long.class, int.class };
	private static final Class<?>[] _getLCSClusterEntryLCSNotificationsParameterTypes8 =
		new Class[] { long.class };
	private static final Class<?>[] _getLCSClusterNodeLCSNotificationsParameterTypes9 =
		new Class[] { long.class };
	private static final Class<?>[] _getLCSNotificationsParameterTypes10 = new Class[] {
			
		};
	private static final Class<?>[] _getLCSProjectLCSNotificationsParameterTypes11 =
		new Class[] { long.class };
	private static final Class<?>[] _updateLCSNotificationParameterTypes12 = new Class[] {
			com.liferay.osb.lcs.model.LCSNotification.class
		};
}