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

package com.liferay.portal.resiliency.spi.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.cluster.Clusterable;
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
import com.liferay.portal.kernel.service.InvokableLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Tuple;
import com.liferay.portal.resiliency.spi.model.SPIDefinition;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for SPIDefinition. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Michael C. Han
 * @see SPIDefinitionLocalServiceUtil
 * @see com.liferay.portal.resiliency.spi.service.base.SPIDefinitionLocalServiceBaseImpl
 * @see com.liferay.portal.resiliency.spi.service.impl.SPIDefinitionLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface SPIDefinitionLocalService extends BaseLocalService,
	InvokableLocalService, PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SPIDefinitionLocalServiceUtil} to access the s p i definition local service. Add custom service methods to {@link com.liferay.portal.resiliency.spi.service.impl.SPIDefinitionLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
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

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Tuple getPortletIdsAndServletContextNames();

	/**
	* Adds the s p i definition to the database. Also notifies the appropriate model listeners.
	*
	* @param spiDefinition the s p i definition
	* @return the s p i definition that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public SPIDefinition addSPIDefinition(SPIDefinition spiDefinition);

	public SPIDefinition addSPIDefinition(long userId, java.lang.String name,
		java.lang.String connectorAddress, int connectorPort,
		java.lang.String description, java.lang.String jvmArguments,
		java.lang.String portletIds, java.lang.String servletContextNames,
		java.lang.String typeSettings, ServiceContext serviceContext)
		throws PortalException;

	/**
	* Creates a new s p i definition with the primary key. Does not add the s p i definition to the database.
	*
	* @param spiDefinitionId the primary key for the new s p i definition
	* @return the new s p i definition
	*/
	public SPIDefinition createSPIDefinition(long spiDefinitionId);

	/**
	* Deletes the s p i definition from the database. Also notifies the appropriate model listeners.
	*
	* @param spiDefinition the s p i definition
	* @return the s p i definition that was removed
	* @throws PortalException
	*/
	@Indexable(type = IndexableType.DELETE)
	public SPIDefinition deleteSPIDefinition(SPIDefinition spiDefinition)
		throws PortalException;

	/**
	* Deletes the s p i definition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param spiDefinitionId the primary key of the s p i definition
	* @return the s p i definition that was removed
	* @throws PortalException if a s p i definition with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public SPIDefinition deleteSPIDefinition(long spiDefinitionId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SPIDefinition fetchSPIDefinition(long spiDefinitionId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SPIDefinition getSPIDefinition(long companyId, java.lang.String name)
		throws PortalException;

	/**
	* Returns the s p i definition with the primary key.
	*
	* @param spiDefinitionId the primary key of the s p i definition
	* @return the s p i definition
	* @throws PortalException if a s p i definition with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SPIDefinition getSPIDefinition(long spiDefinitionId)
		throws PortalException;

	/**
	* Updates the s p i definition in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param spiDefinition the s p i definition
	* @return the s p i definition that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public SPIDefinition updateSPIDefinition(SPIDefinition spiDefinition);

	public SPIDefinition updateSPIDefinition(long spiDefinitionId, int status,
		java.lang.String statusMessage) throws PortalException;

	public SPIDefinition updateSPIDefinition(long userId, long spiDefinitionId,
		java.lang.String connectorAddress, int connectorPort,
		java.lang.String description, java.lang.String jvmArguments,
		java.lang.String portletIds, java.lang.String servletContextNames,
		java.lang.String typeSettings, ServiceContext serviceContext)
		throws PortalException;

	public SPIDefinition updateTypeSettings(long userId, long spiDefinitionId,
		java.lang.String typeSettings, ServiceContext serviceContext)
		throws PortalException;

	/**
	* Returns the number of s p i definitions.
	*
	* @return the number of s p i definitions
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getSPIDefinitionsCount();

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable;

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.resiliency.spi.model.impl.SPIDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.resiliency.spi.model.impl.SPIDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SPIDefinition> getSPIDefinitions();

	/**
	* Returns a range of all the s p i definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.resiliency.spi.model.impl.SPIDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of s p i definitions
	* @param end the upper bound of the range of s p i definitions (not inclusive)
	* @return the range of s p i definitions
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SPIDefinition> getSPIDefinitions(int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SPIDefinition> getSPIDefinitions(long companyId, int status);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SPIDefinition> getSPIDefinitions(long companyId, int[] statuses);

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

	public long startSPIinBackground(long userId, long spiDefinitionId)
		throws PortalException;

	public long startSPIinBackground(long userId, long spiDefinitionId,
		boolean automatedRestart) throws PortalException;

	public long stopSPIinBackground(long userId, long spiDefinitionId)
		throws PortalException;

	@Clusterable
	public void startSPI(long spiDefinitionId) throws PortalException;

	@Clusterable
	public void stopSPI(long spiDefinitionId) throws PortalException;
}