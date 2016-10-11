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

import com.liferay.osb.lcs.service.LCSRoleServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * {@link LCSRoleServiceUtil} service utility. The
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
 * @see LCSRoleServiceSoap
 * @see HttpPrincipal
 * @see LCSRoleServiceUtil
 * @generated
 */
@ProviderType
public class LCSRoleServiceHttp {
	public static com.liferay.osb.lcs.model.LCSRole addLCSRole(
		HttpPrincipal httpPrincipal, long userId, long lcsProjectId,
		long lcsClusterEntryId, int role)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSRoleServiceUtil.class,
					"addLCSRole", _addLCSRoleParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey, userId,
					lcsProjectId, lcsClusterEntryId, role);

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

			return (com.liferay.osb.lcs.model.LCSRole)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.lcs.model.LCSRole addPendingLCSRole(
		HttpPrincipal httpPrincipal, long lcsProjectId, long lcsClusterEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSRoleServiceUtil.class,
					"addPendingLCSRole", _addPendingLCSRoleParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					lcsProjectId, lcsClusterEntryId);

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

			return (com.liferay.osb.lcs.model.LCSRole)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.lcs.model.LCSRole deleteLCSRole(
		HttpPrincipal httpPrincipal, long lcsRoleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSRoleServiceUtil.class,
					"deleteLCSRole", _deleteLCSRoleParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey, lcsRoleId);

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

			return (com.liferay.osb.lcs.model.LCSRole)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSRole> getLCSProjectLCSRoles(
		HttpPrincipal httpPrincipal, long lcsProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSRoleServiceUtil.class,
					"getLCSProjectLCSRoles",
					_getLCSProjectLCSRolesParameterTypes3);

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

			return (java.util.List<com.liferay.osb.lcs.model.LCSRole>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSRole> getUserLCSRoles(
		HttpPrincipal httpPrincipal, long lcsProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSRoleServiceUtil.class,
					"getUserLCSRoles", _getUserLCSRolesParameterTypes4);

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

			return (java.util.List<com.liferay.osb.lcs.model.LCSRole>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSRole> getUserLCSRoles(
		HttpPrincipal httpPrincipal, long lcsProjectId, int role)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSRoleServiceUtil.class,
					"getUserLCSRoles", _getUserLCSRolesParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					lcsProjectId, role);

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

			return (java.util.List<com.liferay.osb.lcs.model.LCSRole>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static boolean hasUserLCSAdministratorLCSRole(
		HttpPrincipal httpPrincipal, long lcsProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSRoleServiceUtil.class,
					"hasUserLCSAdministratorLCSRole",
					_hasUserLCSAdministratorLCSRoleParameterTypes6);

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

	public static boolean hasUserLCSRole(HttpPrincipal httpPrincipal,
		long lcsProjectId, boolean manageLCSClusterEntry)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSRoleServiceUtil.class,
					"hasUserLCSRole", _hasUserLCSRoleParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					lcsProjectId, manageLCSClusterEntry);

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

	private static Log _log = LogFactoryUtil.getLog(LCSRoleServiceHttp.class);
	private static final Class<?>[] _addLCSRoleParameterTypes0 = new Class[] {
			long.class, long.class, long.class, int.class
		};
	private static final Class<?>[] _addPendingLCSRoleParameterTypes1 = new Class[] {
			long.class, long.class
		};
	private static final Class<?>[] _deleteLCSRoleParameterTypes2 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getLCSProjectLCSRolesParameterTypes3 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getUserLCSRolesParameterTypes4 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getUserLCSRolesParameterTypes5 = new Class[] {
			long.class, int.class
		};
	private static final Class<?>[] _hasUserLCSAdministratorLCSRoleParameterTypes6 =
		new Class[] { long.class };
	private static final Class<?>[] _hasUserLCSRoleParameterTypes7 = new Class[] {
			long.class, boolean.class
		};
}