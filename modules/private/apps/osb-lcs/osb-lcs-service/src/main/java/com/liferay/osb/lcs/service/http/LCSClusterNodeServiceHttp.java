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

import com.liferay.osb.lcs.service.LCSClusterNodeServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * {@link LCSClusterNodeServiceUtil} service utility. The
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
 * @see LCSClusterNodeServiceSoap
 * @see HttpPrincipal
 * @see LCSClusterNodeServiceUtil
 * @generated
 */
@ProviderType
public class LCSClusterNodeServiceHttp {
	public static com.liferay.osb.lcs.model.LCSClusterNode addLCSClusterNode(
		HttpPrincipal httpPrincipal, long lcsClusterEntryId,
		java.lang.String name, java.lang.String description, int buildNumber,
		java.lang.String key, java.lang.String location, int processorCoresTotal)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSClusterNodeServiceUtil.class,
					"addLCSClusterNode", _addLCSClusterNodeParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					lcsClusterEntryId, name, description, buildNumber, key,
					location, processorCoresTotal);

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

			return (com.liferay.osb.lcs.model.LCSClusterNode)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.lcs.model.LCSClusterNode addLCSClusterNode(
		HttpPrincipal httpPrincipal, java.lang.String siblingKey,
		java.lang.String name, java.lang.String description,
		java.lang.String key, java.lang.String location, int processorCoresTotal)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSClusterNodeServiceUtil.class,
					"addLCSClusterNode", _addLCSClusterNodeParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					siblingKey, name, description, key, location,
					processorCoresTotal);

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

			return (com.liferay.osb.lcs.model.LCSClusterNode)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deleteLCSClusterNode(HttpPrincipal httpPrincipal,
		long lcsClusterNodeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSClusterNodeServiceUtil.class,
					"deleteLCSClusterNode", _deleteLCSClusterNodeParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					lcsClusterNodeId);

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

	public static com.liferay.osb.lcs.model.LCSClusterNode fetchLCSClusterNode(
		HttpPrincipal httpPrincipal, java.lang.String key)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSClusterNodeServiceUtil.class,
					"fetchLCSClusterNode", _fetchLCSClusterNodeParameterTypes3);

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

			return (com.liferay.osb.lcs.model.LCSClusterNode)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.lcs.model.LCSClusterNode fetchRandomSiblingLCSClusterNode(
		HttpPrincipal httpPrincipal, java.lang.String key)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSClusterNodeServiceUtil.class,
					"fetchRandomSiblingLCSClusterNode",
					_fetchRandomSiblingLCSClusterNodeParameterTypes4);

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

			return (com.liferay.osb.lcs.model.LCSClusterNode)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSClusterNode> getLCSClusterEntryLCSClusterNodes(
		HttpPrincipal httpPrincipal, long lcsClusterEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSClusterNodeServiceUtil.class,
					"getLCSClusterEntryLCSClusterNodes",
					_getLCSClusterEntryLCSClusterNodesParameterTypes5);

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

			return (java.util.List<com.liferay.osb.lcs.model.LCSClusterNode>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSClusterNode> getLCSClusterEntryLCSClusterNodes(
		HttpPrincipal httpPrincipal, long lcsClusterEntryId, boolean details)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSClusterNodeServiceUtil.class,
					"getLCSClusterEntryLCSClusterNodes",
					_getLCSClusterEntryLCSClusterNodesParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					lcsClusterEntryId, details);

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

			return (java.util.List<com.liferay.osb.lcs.model.LCSClusterNode>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.lcs.model.LCSClusterNode getLCSClusterNode(
		HttpPrincipal httpPrincipal, long lcsClusterNodeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSClusterNodeServiceUtil.class,
					"getLCSClusterNode", _getLCSClusterNodeParameterTypes7);

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

			return (com.liferay.osb.lcs.model.LCSClusterNode)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.lcs.model.LCSClusterNode getLCSClusterNode(
		HttpPrincipal httpPrincipal, long lcsClusterNodeId, boolean details)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSClusterNodeServiceUtil.class,
					"getLCSClusterNode", _getLCSClusterNodeParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					lcsClusterNodeId, details);

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

			return (com.liferay.osb.lcs.model.LCSClusterNode)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.lcs.model.LCSClusterNode getLCSClusterNode(
		HttpPrincipal httpPrincipal, java.lang.String key)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSClusterNodeServiceUtil.class,
					"getLCSClusterNode", _getLCSClusterNodeParameterTypes9);

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

			return (com.liferay.osb.lcs.model.LCSClusterNode)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.lcs.model.LCSClusterNode getLCSClusterNode(
		HttpPrincipal httpPrincipal, java.lang.String key, boolean details)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSClusterNodeServiceUtil.class,
					"getLCSClusterNode", _getLCSClusterNodeParameterTypes10);

			MethodHandler methodHandler = new MethodHandler(methodKey, key,
					details);

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

			return (com.liferay.osb.lcs.model.LCSClusterNode)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSClusterNode> getLCSClusterNodeSiblingNodes(
		HttpPrincipal httpPrincipal, java.lang.String key)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSClusterNodeServiceUtil.class,
					"getLCSClusterNodeSiblingNodes",
					_getLCSClusterNodeSiblingNodesParameterTypes11);

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

			return (java.util.List<com.liferay.osb.lcs.model.LCSClusterNode>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSClusterNode> getLCSProjectLCSClusterNodes(
		HttpPrincipal httpPrincipal, long lcsProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSClusterNodeServiceUtil.class,
					"getLCSProjectLCSClusterNodes",
					_getLCSProjectLCSClusterNodesParameterTypes12);

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

			return (java.util.List<com.liferay.osb.lcs.model.LCSClusterNode>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSClusterNode> getLCSProjectLCSClusterNodes(
		HttpPrincipal httpPrincipal, long lcsProjectId, boolean details)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSClusterNodeServiceUtil.class,
					"getLCSProjectLCSClusterNodes",
					_getLCSProjectLCSClusterNodesParameterTypes13);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					lcsProjectId, details);

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

			return (java.util.List<com.liferay.osb.lcs.model.LCSClusterNode>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSClusterNode> getUserLCSClusterNodes(
		HttpPrincipal httpPrincipal, boolean details)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSClusterNodeServiceUtil.class,
					"getUserLCSClusterNodes",
					_getUserLCSClusterNodesParameterTypes14);

			MethodHandler methodHandler = new MethodHandler(methodKey, details);

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

			return (java.util.List<com.liferay.osb.lcs.model.LCSClusterNode>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void mergeStatus(HttpPrincipal httpPrincipal,
		java.lang.String key, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSClusterNodeServiceUtil.class,
					"mergeStatus", _mergeStatusParameterTypes15);

			MethodHandler methodHandler = new MethodHandler(methodKey, key,
					status);

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

	public static void updateBuildNumber(HttpPrincipal httpPrincipal,
		long lcsClusterNodeId, int buildNumber)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSClusterNodeServiceUtil.class,
					"updateBuildNumber", _updateBuildNumberParameterTypes16);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					lcsClusterNodeId, buildNumber);

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

	public static void updateBuildNumber(HttpPrincipal httpPrincipal,
		java.lang.String key, int buildNumber)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSClusterNodeServiceUtil.class,
					"updateBuildNumber", _updateBuildNumberParameterTypes17);

			MethodHandler methodHandler = new MethodHandler(methodKey, key,
					buildNumber);

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

	public static com.liferay.osb.lcs.model.LCSClusterNode updateLCSClusterNode(
		HttpPrincipal httpPrincipal, long lcsClusterNodeId,
		java.lang.String name, java.lang.String description,
		java.lang.String location)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSClusterNodeServiceUtil.class,
					"updateLCSClusterNode",
					_updateLCSClusterNodeParameterTypes18);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					lcsClusterNodeId, name, description, location);

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

			return (com.liferay.osb.lcs.model.LCSClusterNode)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void verifyLCSClusterEntryLCSClusterNodesPropertiesDifferences(
		HttpPrincipal httpPrincipal, java.lang.String key)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSClusterNodeServiceUtil.class,
					"verifyLCSClusterEntryLCSClusterNodesPropertiesDifferences",
					_verifyLCSClusterEntryLCSClusterNodesPropertiesDifferencesParameterTypes19);

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

	public static void verifyLCSClusterNodeClusterLink(
		HttpPrincipal httpPrincipal, java.lang.String key,
		java.lang.String siblingKeys)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LCSClusterNodeServiceUtil.class,
					"verifyLCSClusterNodeClusterLink",
					_verifyLCSClusterNodeClusterLinkParameterTypes20);

			MethodHandler methodHandler = new MethodHandler(methodKey, key,
					siblingKeys);

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

	private static Log _log = LogFactoryUtil.getLog(LCSClusterNodeServiceHttp.class);
	private static final Class<?>[] _addLCSClusterNodeParameterTypes0 = new Class[] {
			long.class, java.lang.String.class, java.lang.String.class,
			int.class, java.lang.String.class, java.lang.String.class, int.class
		};
	private static final Class<?>[] _addLCSClusterNodeParameterTypes1 = new Class[] {
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, int.class
		};
	private static final Class<?>[] _deleteLCSClusterNodeParameterTypes2 = new Class[] {
			long.class
		};
	private static final Class<?>[] _fetchLCSClusterNodeParameterTypes3 = new Class[] {
			java.lang.String.class
		};
	private static final Class<?>[] _fetchRandomSiblingLCSClusterNodeParameterTypes4 =
		new Class[] { java.lang.String.class };
	private static final Class<?>[] _getLCSClusterEntryLCSClusterNodesParameterTypes5 =
		new Class[] { long.class };
	private static final Class<?>[] _getLCSClusterEntryLCSClusterNodesParameterTypes6 =
		new Class[] { long.class, boolean.class };
	private static final Class<?>[] _getLCSClusterNodeParameterTypes7 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getLCSClusterNodeParameterTypes8 = new Class[] {
			long.class, boolean.class
		};
	private static final Class<?>[] _getLCSClusterNodeParameterTypes9 = new Class[] {
			java.lang.String.class
		};
	private static final Class<?>[] _getLCSClusterNodeParameterTypes10 = new Class[] {
			java.lang.String.class, boolean.class
		};
	private static final Class<?>[] _getLCSClusterNodeSiblingNodesParameterTypes11 =
		new Class[] { java.lang.String.class };
	private static final Class<?>[] _getLCSProjectLCSClusterNodesParameterTypes12 =
		new Class[] { long.class };
	private static final Class<?>[] _getLCSProjectLCSClusterNodesParameterTypes13 =
		new Class[] { long.class, boolean.class };
	private static final Class<?>[] _getUserLCSClusterNodesParameterTypes14 = new Class[] {
			boolean.class
		};
	private static final Class<?>[] _mergeStatusParameterTypes15 = new Class[] {
			java.lang.String.class, int.class
		};
	private static final Class<?>[] _updateBuildNumberParameterTypes16 = new Class[] {
			long.class, int.class
		};
	private static final Class<?>[] _updateBuildNumberParameterTypes17 = new Class[] {
			java.lang.String.class, int.class
		};
	private static final Class<?>[] _updateLCSClusterNodeParameterTypes18 = new Class[] {
			long.class, java.lang.String.class, java.lang.String.class,
			java.lang.String.class
		};
	private static final Class<?>[] _verifyLCSClusterEntryLCSClusterNodesPropertiesDifferencesParameterTypes19 =
		new Class[] { java.lang.String.class };
	private static final Class<?>[] _verifyLCSClusterNodeClusterLinkParameterTypes20 =
		new Class[] { java.lang.String.class, java.lang.String.class };
}