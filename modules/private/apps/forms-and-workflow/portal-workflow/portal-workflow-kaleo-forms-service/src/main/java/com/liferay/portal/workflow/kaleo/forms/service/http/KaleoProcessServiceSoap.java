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

package com.liferay.portal.workflow.kaleo.forms.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.workflow.kaleo.forms.service.KaleoProcessServiceUtil;

import java.rmi.RemoteException;

import java.util.Locale;
import java.util.Map;

/**
 * Provides the SOAP utility for the
 * {@link KaleoProcessServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess}, that is translated to a
 * {@link com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessSoap}. Methods that SOAP cannot
 * safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Marcellus Tavares
 * @see KaleoProcessServiceHttp
 * @see com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessSoap
 * @see KaleoProcessServiceUtil
 * @generated
 */
@ProviderType
public class KaleoProcessServiceSoap {
	/**
	* Adds a kaleo process.
	*
	* @param groupId the primary key of the kaleo process's group
	* @param ddmStructureId the primary key of the kaleo process's DDM
	structure
	* @param nameMap the kaleo process's locales and localized names
	* @param descriptionMap the kaleo process's locales and localized
	descriptions
	* @param ddmTemplateId the primary key of the kaleo process's DDM template
	* @param workflowDefinitionName the name of kaleo process's workflow
	definition
	* @param workflowDefinitionVersion the version of kaleo process's workflow
	definition
	* @param kaleoTaskFormPairs the kaleo task form pairs. For more
	information see the
	<code>com.liferay.portal.workflow.kaleo.forms.api</code> module's
	<code>KaleoTaskFormPairs</code> class.
	* @param serviceContext the service context to be applied. This can set
	guest permissions and group permissions for the kaleo process.
	* @return the kaleo process
	* @throws PortalException if a portal exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessSoap addKaleoProcess(
		long groupId, long ddmStructureId,
		java.lang.String[] nameMapLanguageIds,
		java.lang.String[] nameMapValues,
		java.lang.String[] descriptionMapLanguageIds,
		java.lang.String[] descriptionMapValues, long ddmTemplateId,
		java.lang.String workflowDefinitionName, int workflowDefinitionVersion,
		com.liferay.portal.workflow.kaleo.forms.model.KaleoTaskFormPairs kaleoTaskFormPairs,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			Map<Locale, String> nameMap = LocalizationUtil.getLocalizationMap(nameMapLanguageIds,
					nameMapValues);
			Map<Locale, String> descriptionMap = LocalizationUtil.getLocalizationMap(descriptionMapLanguageIds,
					descriptionMapValues);

			com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess returnValue =
				KaleoProcessServiceUtil.addKaleoProcess(groupId,
					ddmStructureId, nameMap, descriptionMap, ddmTemplateId,
					workflowDefinitionName, workflowDefinitionVersion,
					kaleoTaskFormPairs, serviceContext);

			return com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* Deletes the kaleo process and its resources.
	*
	* @param kaleoProcessId the primary key of the kaleo process to be deleted
	* @return the deleted kaleo process
	* @throws PortalException if a portal exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessSoap deleteKaleoProcess(
		long kaleoProcessId) throws RemoteException {
		try {
			com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess returnValue =
				KaleoProcessServiceUtil.deleteKaleoProcess(kaleoProcessId);

			return com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* Returns the kaleo process with the primary key.
	*
	* @param kaleoProcessId the primary key of the kaleo process
	* @return the kaleo process
	* @throws PortalException if a kaleo process with the primary key could not
	be found
	*/
	public static com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessSoap getKaleoProcess(
		long kaleoProcessId) throws RemoteException {
		try {
			com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess returnValue =
				KaleoProcessServiceUtil.getKaleoProcess(kaleoProcessId);

			return com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* Returns an ordered range of all kaleo processes matching the parameters,
	* including a keywords parameter for matching string values to the kaleo
	* process's name or description.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link
	* com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param groupId the primary key of the kaleo process's group
	* @param keywords the keywords (space separated) to look for and match in
	the kaleo process name or description (optionally
	<code>null</code>). If the keywords value is not
	<code>null</code>, the search uses the OR operator in connecting
	query criteria; otherwise it uses the AND operator.
	* @param start the lower bound of the range of kaleo processes to return
	* @param end the upper bound of the range of kaleo processes to return
	(not inclusive)
	* @param orderByComparator the comparator to order the kaleo processes
	* @return the range of matching kaleo processes ordered by the comparator
	*/
	public static com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessSoap[] search(
		long groupId, java.lang.String keywords, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws RemoteException {
		try {
			java.util.List<com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess> returnValue =
				KaleoProcessServiceUtil.search(groupId, keywords, start, end,
					orderByComparator);

			return com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* Returns the number of kaleo processes matching the parameters. The
	* keywords parameter is used for matching the kaleo process's name or
	* description.
	*
	* @param groupId the primary key of the kaleo process's group
	* @param keywords the keywords (space separated) to look for and match in
	the kaleo process name or description (optionally
	<code>null</code>). If the keywords value is not
	<code>null</code>, the OR operator is used in connecting query
	criteria; otherwise it uses the AND operator.
	* @return the number of matching kaleo processes
	*/
	public static int searchCount(long groupId, java.lang.String keywords)
		throws RemoteException {
		try {
			int returnValue = KaleoProcessServiceUtil.searchCount(groupId,
					keywords);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* Updates the kaleo process.
	*
	* @param kaleoProcessId the primary key of the kaleo process
	* @param ddmStructureId the primary key of the kaleo process's DDM
	structure
	* @param nameMap the kaleo process's locales and localized names
	* @param descriptionMap the kaleo process's locales and localized
	descriptions
	* @param ddmTemplateId the primary key of the kaleo process's DDM template
	* @param workflowDefinitionName the name of kaleo process's workflow
	definition
	* @param workflowDefinitionVersion the version of kaleo process's workflow
	definition
	* @param kaleoTaskFormPairs the kaleo task form pairs. For more
	information see the
	<code>com.liferay.portal.workflow.kaleo.forms.api</code> module's
	<code>KaleoTaskFormPairs</code> class.
	* @param serviceContext the service context to be applied. This can set
	guest permissions and group permissions for the kaleo process.
	* @return the kaleo process
	* @throws PortalException if a portal exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessSoap updateKaleoProcess(
		long kaleoProcessId, long ddmStructureId,
		java.lang.String[] nameMapLanguageIds,
		java.lang.String[] nameMapValues,
		java.lang.String[] descriptionMapLanguageIds,
		java.lang.String[] descriptionMapValues, long ddmTemplateId,
		java.lang.String workflowDefinitionName, int workflowDefinitionVersion,
		com.liferay.portal.workflow.kaleo.forms.model.KaleoTaskFormPairs kaleoTaskFormPairs,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			Map<Locale, String> nameMap = LocalizationUtil.getLocalizationMap(nameMapLanguageIds,
					nameMapValues);
			Map<Locale, String> descriptionMap = LocalizationUtil.getLocalizationMap(descriptionMapLanguageIds,
					descriptionMapValues);

			com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess returnValue =
				KaleoProcessServiceUtil.updateKaleoProcess(kaleoProcessId,
					ddmStructureId, nameMap, descriptionMap, ddmTemplateId,
					workflowDefinitionName, workflowDefinitionVersion,
					kaleoTaskFormPairs, serviceContext);

			return com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(KaleoProcessServiceSoap.class);
}