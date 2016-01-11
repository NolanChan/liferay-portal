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

package com.liferay.workflow.kaleo.forms.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.security.auth.HttpPrincipal;
import com.liferay.portal.service.http.TunnelUtil;

import com.liferay.workflow.kaleo.forms.service.KaleoProcessServiceUtil;

/**
 * Provides the HTTP utility for the
 * {@link KaleoProcessServiceUtil} service utility. The
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
 * @author Marcellus Tavares
 * @see KaleoProcessServiceSoap
 * @see HttpPrincipal
 * @see KaleoProcessServiceUtil
 * @generated
 */
@ProviderType
public class KaleoProcessServiceHttp {
	public static com.liferay.workflow.kaleo.forms.model.KaleoProcess addKaleoProcess(
		HttpPrincipal httpPrincipal, long groupId, long ddmStructureId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		long ddmTemplateId, java.lang.String workflowDefinitionName,
		int workflowDefinitionVersion,
		com.liferay.workflow.kaleo.forms.constants.TaskFormPairs taskFormPairs,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(KaleoProcessServiceUtil.class,
					"addKaleoProcess", _addKaleoProcessParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					ddmStructureId, nameMap, descriptionMap, ddmTemplateId,
					workflowDefinitionName, workflowDefinitionVersion,
					taskFormPairs, serviceContext);

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

			return (com.liferay.workflow.kaleo.forms.model.KaleoProcess)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.workflow.kaleo.forms.model.KaleoProcess deleteKaleoProcess(
		HttpPrincipal httpPrincipal, long kaleoProcessId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(KaleoProcessServiceUtil.class,
					"deleteKaleoProcess", _deleteKaleoProcessParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					kaleoProcessId);

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

			return (com.liferay.workflow.kaleo.forms.model.KaleoProcess)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.workflow.kaleo.forms.model.KaleoProcess getKaleoProcess(
		HttpPrincipal httpPrincipal, long kaleoProcessId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(KaleoProcessServiceUtil.class,
					"getKaleoProcess", _getKaleoProcessParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					kaleoProcessId);

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

			return (com.liferay.workflow.kaleo.forms.model.KaleoProcess)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.workflow.kaleo.forms.model.KaleoProcess> getKaleoProcesses(
		HttpPrincipal httpPrincipal, long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator) {
		try {
			MethodKey methodKey = new MethodKey(KaleoProcessServiceUtil.class,
					"getKaleoProcesses", _getKaleoProcessesParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					start, end, orderByComparator);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.workflow.kaleo.forms.model.KaleoProcess>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getKaleoProcessesCount(HttpPrincipal httpPrincipal,
		long groupId) {
		try {
			MethodKey methodKey = new MethodKey(KaleoProcessServiceUtil.class,
					"getKaleoProcessesCount",
					_getKaleoProcessesCountParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.workflow.kaleo.forms.model.KaleoProcess updateKaleoProcess(
		HttpPrincipal httpPrincipal, long kaleoProcessId, long ddmStructureId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		long ddmTemplateId, java.lang.String workflowDefinitionName,
		int workflowDefinitionVersion,
		com.liferay.workflow.kaleo.forms.constants.TaskFormPairs taskFormPairs,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(KaleoProcessServiceUtil.class,
					"updateKaleoProcess", _updateKaleoProcessParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					kaleoProcessId, ddmStructureId, nameMap, descriptionMap,
					ddmTemplateId, workflowDefinitionName,
					workflowDefinitionVersion, taskFormPairs, serviceContext);

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

			return (com.liferay.workflow.kaleo.forms.model.KaleoProcess)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(KaleoProcessServiceHttp.class);
	private static final Class<?>[] _addKaleoProcessParameterTypes0 = new Class[] {
			long.class, long.class, java.util.Map.class, java.util.Map.class,
			long.class, java.lang.String.class, int.class,
			com.liferay.workflow.kaleo.forms.constants.TaskFormPairs.class,
			com.liferay.portal.service.ServiceContext.class
		};
	private static final Class<?>[] _deleteKaleoProcessParameterTypes1 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getKaleoProcessParameterTypes2 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getKaleoProcessesParameterTypes3 = new Class[] {
			long.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getKaleoProcessesCountParameterTypes4 = new Class[] {
			long.class
		};
	private static final Class<?>[] _updateKaleoProcessParameterTypes5 = new Class[] {
			long.class, long.class, java.util.Map.class, java.util.Map.class,
			long.class, java.lang.String.class, int.class,
			com.liferay.workflow.kaleo.forms.constants.TaskFormPairs.class,
			com.liferay.portal.service.ServiceContext.class
		};
}