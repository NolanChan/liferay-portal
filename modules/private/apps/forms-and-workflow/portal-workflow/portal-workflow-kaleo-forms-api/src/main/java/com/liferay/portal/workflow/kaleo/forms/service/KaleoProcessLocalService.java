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

package com.liferay.portal.workflow.kaleo.forms.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.exportimport.kernel.lar.PortletDataContext;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess;
import com.liferay.portal.workflow.kaleo.forms.model.KaleoTaskFormPairs;

import java.io.Serializable;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Provides the local service interface for KaleoProcess. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Marcellus Tavares
 * @see KaleoProcessLocalServiceUtil
 * @see com.liferay.portal.workflow.kaleo.forms.service.base.KaleoProcessLocalServiceBaseImpl
 * @see com.liferay.portal.workflow.kaleo.forms.service.impl.KaleoProcessLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface KaleoProcessLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link KaleoProcessLocalServiceUtil} to access the kaleo process local service. Add custom service methods to {@link com.liferay.portal.workflow.kaleo.forms.service.impl.KaleoProcessLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	public DynamicQuery dynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Adds the kaleo process to the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoProcess the kaleo process
	* @return the kaleo process that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public KaleoProcess addKaleoProcess(KaleoProcess kaleoProcess);

	public KaleoProcess addKaleoProcess(long userId, long groupId,
		long ddmStructureId, Map<Locale, java.lang.String> nameMap,
		Map<Locale, java.lang.String> descriptionMap, long ddmTemplateId,
		java.lang.String workflowDefinitionName, int workflowDefinitionVersion,
		KaleoTaskFormPairs kaleoTaskFormPairs, ServiceContext serviceContext)
		throws PortalException;

	/**
	* Creates a new kaleo process with the primary key. Does not add the kaleo process to the database.
	*
	* @param kaleoProcessId the primary key for the new kaleo process
	* @return the new kaleo process
	*/
	public KaleoProcess createKaleoProcess(long kaleoProcessId);

	/**
	* Deletes the kaleo process from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoProcess the kaleo process
	* @return the kaleo process that was removed
	* @throws PortalException
	*/
	@Indexable(type = IndexableType.DELETE)
	public KaleoProcess deleteKaleoProcess(KaleoProcess kaleoProcess)
		throws PortalException;

	/**
	* Deletes the kaleo process with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoProcessId the primary key of the kaleo process
	* @return the kaleo process that was removed
	* @throws PortalException if a kaleo process with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public KaleoProcess deleteKaleoProcess(long kaleoProcessId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KaleoProcess fetchKaleoProcess(long kaleoProcessId);

	/**
	* Returns the kaleo process matching the UUID and group.
	*
	* @param uuid the kaleo process's UUID
	* @param groupId the primary key of the group
	* @return the matching kaleo process, or <code>null</code> if a matching kaleo process could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KaleoProcess fetchKaleoProcessByUuidAndGroupId(
		java.lang.String uuid, long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KaleoProcess getDDLRecordSetKaleoProcess(long ddlRecordSetId)
		throws PortalException;

	/**
	* Returns the kaleo process with the primary key.
	*
	* @param kaleoProcessId the primary key of the kaleo process
	* @return the kaleo process
	* @throws PortalException if a kaleo process with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KaleoProcess getKaleoProcess(long kaleoProcessId)
		throws PortalException;

	/**
	* Returns the kaleo process matching the UUID and group.
	*
	* @param uuid the kaleo process's UUID
	* @param groupId the primary key of the group
	* @return the matching kaleo process
	* @throws PortalException if a matching kaleo process could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KaleoProcess getKaleoProcessByUuidAndGroupId(java.lang.String uuid,
		long groupId) throws PortalException;

	/**
	* Updates the kaleo process in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kaleoProcess the kaleo process
	* @return the kaleo process that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public KaleoProcess updateKaleoProcess(KaleoProcess kaleoProcess);

	public KaleoProcess updateKaleoProcess(long kaleoProcessId,
		long ddmStructureId, Map<Locale, java.lang.String> nameMap,
		Map<Locale, java.lang.String> descriptionMap, long ddmTemplateId,
		java.lang.String workflowDefinitionName, int workflowDefinitionVersion,
		KaleoTaskFormPairs kaleoTaskFormPairs, ServiceContext serviceContext)
		throws PortalException;

	/**
	* Returns the number of kaleo processes.
	*
	* @return the number of kaleo processes
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getKaleoProcessesCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getKaleoProcessesCount(long groupId);

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.forms.model.impl.KaleoProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.forms.model.impl.KaleoProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	/**
	* Returns a range of all the kaleo processes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.forms.model.impl.KaleoProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo processes
	* @param end the upper bound of the range of kaleo processes (not inclusive)
	* @return the range of kaleo processes
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KaleoProcess> getKaleoProcesses(int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KaleoProcess> getKaleoProcesses(long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KaleoProcess> getKaleoProcesses(long groupId, int start,
		int end, OrderByComparator orderByComparator);

	/**
	* Returns all the kaleo processes matching the UUID and company.
	*
	* @param uuid the UUID of the kaleo processes
	* @param companyId the primary key of the company
	* @return the matching kaleo processes, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KaleoProcess> getKaleoProcessesByUuidAndCompanyId(
		java.lang.String uuid, long companyId);

	/**
	* Returns a range of kaleo processes matching the UUID and company.
	*
	* @param uuid the UUID of the kaleo processes
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of kaleo processes
	* @param end the upper bound of the range of kaleo processes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching kaleo processes, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KaleoProcess> getKaleoProcessesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<KaleoProcess> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);
}