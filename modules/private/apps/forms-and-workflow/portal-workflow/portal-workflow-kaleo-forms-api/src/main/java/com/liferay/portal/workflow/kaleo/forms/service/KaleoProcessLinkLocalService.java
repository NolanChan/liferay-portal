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

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for KaleoProcessLink. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Marcellus Tavares
 * @see KaleoProcessLinkLocalServiceUtil
 * @see com.liferay.portal.workflow.kaleo.forms.service.base.KaleoProcessLinkLocalServiceBaseImpl
 * @see com.liferay.portal.workflow.kaleo.forms.service.impl.KaleoProcessLinkLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface KaleoProcessLinkLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link KaleoProcessLinkLocalServiceUtil} to access the kaleo process link local service. Add custom service methods to {@link com.liferay.portal.workflow.kaleo.forms.service.impl.KaleoProcessLinkLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	public DynamicQuery dynamicQuery();

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
	* Adds the kaleo process link to the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoProcessLink the kaleo process link
	* @return the kaleo process link that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public KaleoProcessLink addKaleoProcessLink(
		KaleoProcessLink kaleoProcessLink);

	/**
	* Adds the kaleo process link referencing the kaleo process.
	*
	* @param kaleoProcessId the primary key of the kaleo process
	* @param workflowTaskName the name of the kaleo process link's workflow
	task
	* @param ddmTemplateId the primary key of the kaleo process link's DDM
	template
	* @return the kaleo process link that was added
	*/
	public KaleoProcessLink addKaleoProcessLink(long kaleoProcessId,
		java.lang.String workflowTaskName, long ddmTemplateId);

	/**
	* Creates a new kaleo process link with the primary key. Does not add the kaleo process link to the database.
	*
	* @param kaleoProcessLinkId the primary key for the new kaleo process link
	* @return the new kaleo process link
	*/
	public KaleoProcessLink createKaleoProcessLink(long kaleoProcessLinkId);

	/**
	* Deletes the kaleo process link from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoProcessLink the kaleo process link
	* @return the kaleo process link that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public KaleoProcessLink deleteKaleoProcessLink(
		KaleoProcessLink kaleoProcessLink);

	/**
	* Deletes the kaleo process link with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoProcessLinkId the primary key of the kaleo process link
	* @return the kaleo process link that was removed
	* @throws PortalException if a kaleo process link with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public KaleoProcessLink deleteKaleoProcessLink(long kaleoProcessLinkId)
		throws PortalException;

	/**
	* Returns the kaleo process link matching the kaleo process and workflow
	* task name.
	*
	* @param kaleoProcessId the primary key of the kaleo process link's kaleo
	process
	* @param workflowTaskName the name of the kaleo process link's workflow
	task
	* @return the kaleo process link matching the kaleo process and workflow
	task name, or <code>null</code> if a matching  kaleo process link
	could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KaleoProcessLink fetchKaleoProcessLink(long kaleoProcessId,
		java.lang.String workflowTaskName);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KaleoProcessLink fetchKaleoProcessLink(long kaleoProcessLinkId);

	/**
	* Returns the kaleo process link with the primary key.
	*
	* @param kaleoProcessLinkId the primary key of the kaleo process link
	* @return the kaleo process link
	* @throws PortalException if a kaleo process link with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KaleoProcessLink getKaleoProcessLink(long kaleoProcessLinkId)
		throws PortalException;

	/**
	* Updates the kaleo process link in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kaleoProcessLink the kaleo process link
	* @return the kaleo process link that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public KaleoProcessLink updateKaleoProcessLink(
		KaleoProcessLink kaleoProcessLink);

	/**
	* Creates or updates the kaleo process link. If no kaleo process link is
	* found matching the primary key of the kaleo process and the workflow task
	* name, a new link is created.
	*
	* @param kaleoProcessId the primary key of the kaleo process
	* @param workflowTaskName the name of the kaleo process link's workflow
	task
	* @param ddmTemplateId the primary key of the kaleo process link's DDM
	template
	* @return the kaleo process link
	*/
	public KaleoProcessLink updateKaleoProcessLink(long kaleoProcessId,
		java.lang.String workflowTaskName, long ddmTemplateId);

	/**
	* Updates the kaleo process link, setting the primary key of the associated
	* kaleo process.
	*
	* @param kaleoProcessLinkId the primary key of the kaleo process link
	* @param kaleoProcessId the primary key of the kaleo process
	* @return the kaleo process link
	* @throws PortalException if a portal exception occurred
	*/
	public KaleoProcessLink updateKaleoProcessLink(long kaleoProcessLinkId,
		long kaleoProcessId) throws PortalException;

	/**
	* Updates the kaleo process link, replacing its values with new ones. New
	* values are set for the primary key of the associated kaleo process, the
	* name of the associated workflow task, and the primary key of the
	* associated DDM Template.
	*
	* @param kaleoProcessLinkId the primary key of the kaleo process link
	* @param kaleoProcessId the primary key of the kaleo process
	* @param workflowTaskName the name of the kaleo process link's workflow
	task
	* @param ddmTemplateId the primary key of the kaleo process link's DDM
	template
	* @return the kaleo process link
	* @throws PortalException if a portal exception occurred
	*/
	public KaleoProcessLink updateKaleoProcessLink(long kaleoProcessLinkId,
		long kaleoProcessId, java.lang.String workflowTaskName,
		long ddmTemplateId) throws PortalException;

	/**
	* Returns the number of kaleo process links.
	*
	* @return the number of kaleo process links
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getKaleoProcessLinksCount();

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.forms.model.impl.KaleoProcessLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.forms.model.impl.KaleoProcessLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the kaleo process links.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.forms.model.impl.KaleoProcessLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo process links
	* @param end the upper bound of the range of kaleo process links (not inclusive)
	* @return the range of kaleo process links
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KaleoProcessLink> getKaleoProcessLinks(int start, int end);

	/**
	* Returns the kaleo process links matching the kaleo process.
	*
	* @param kaleoProcessId the primary key of the kaleo process link's kaleo
	process
	* @return the kaleo process links matching the kaleo process, or
	<code>null</code> if a matching kaleo process link could not be
	found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KaleoProcessLink> getKaleoProcessLinks(long kaleoProcessId);

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

	/**
	* Deletes the kaleo process links associated with the kaleo process, and
	* their resources.
	*
	* @param kaleoProcessId the primary key of the kaleo process from which to
	delete kaleo process links
	*/
	public void deleteKaleoProcessLinks(long kaleoProcessId);
}