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

package com.liferay.osb.lcs.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.lcs.model.LCSMessage;

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

import java.io.Serializable;

import java.util.Date;
import java.util.List;

/**
 * Provides the local service interface for LCSMessage. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Igor Beslic
 * @see LCSMessageLocalServiceUtil
 * @see com.liferay.osb.lcs.service.base.LCSMessageLocalServiceBaseImpl
 * @see com.liferay.osb.lcs.service.impl.LCSMessageLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface LCSMessageLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LCSMessageLocalServiceUtil} to access the l c s message local service. Add custom service methods to {@link com.liferay.osb.lcs.service.impl.LCSMessageLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public LCSMessage addLCSClusterEntryLCSMessage(long lcsClusterEntryId,
		long sourceMessageId, java.lang.String sourceSystemName,
		java.lang.String content, Date endDate, boolean global,
		int severityLevel, int type, boolean addUserLCSMessages)
		throws PortalException;

	public LCSMessage addLCSClusterNodeLCSMessage(long lcsClusterNodeId,
		long sourceMessageId, java.lang.String sourceSystemName,
		java.lang.String content, Date endDate, boolean global,
		int severityLevel, int type) throws PortalException;

	/**
	* Adds the l c s message to the database. Also notifies the appropriate model listeners.
	*
	* @param lcsMessage the l c s message
	* @return the l c s message that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public LCSMessage addLCSMessage(LCSMessage lcsMessage);

	public LCSMessage addLCSMessage(long sourceMessageId,
		java.lang.String sourceSystemName, long classNameId, long classPK,
		java.lang.String content, Date endDate, boolean global,
		int severityLevel, int type);

	public LCSMessage addLCSProjectLCSMessage(long lcsProjectId,
		long sourceMessageId, java.lang.String sourceSystemName,
		java.lang.String content, Date endDate, boolean global,
		int severityLevel, int type, boolean adminsOnly,
		boolean generateUserLCSMessages) throws PortalException;

	/**
	* Creates a new l c s message with the primary key. Does not add the l c s message to the database.
	*
	* @param lcsMessageId the primary key for the new l c s message
	* @return the new l c s message
	*/
	public LCSMessage createLCSMessage(long lcsMessageId);

	/**
	* Deletes the l c s message from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsMessage the l c s message
	* @return the l c s message that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public LCSMessage deleteLCSMessage(LCSMessage lcsMessage);

	/**
	* Deletes the l c s message with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsMessageId the primary key of the l c s message
	* @return the l c s message that was removed
	* @throws PortalException if a l c s message with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public LCSMessage deleteLCSMessage(long lcsMessageId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LCSMessage fetchLCSMessage(long lcsMessageId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LCSMessage fetchLastLCSProjectLCSMessage(long lcsProjectId, int type);

	/**
	* Returns the l c s message with the primary key.
	*
	* @param lcsMessageId the primary key of the l c s message
	* @return the l c s message
	* @throws PortalException if a l c s message with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LCSMessage getLCSMessage(long lcsMessageId)
		throws PortalException;

	/**
	* Updates the l c s message in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param lcsMessage the l c s message
	* @return the l c s message that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public LCSMessage updateLCSMessage(LCSMessage lcsMessage);

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
	* Returns the number of l c s messages.
	*
	* @return the number of l c s messages
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getLCSMessagesCount();

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the l c s messages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s messages
	* @param end the upper bound of the range of l c s messages (not inclusive)
	* @return the range of l c s messages
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSMessage> getLCSMessages(int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSMessage> getLCSMessages(long userId, Date modifyDateGT,
		Date modifyDateLT) throws PortalException;

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

	public void deleteLCSClusterEntryLCSMessages(long lcsClusterEntryId)
		throws PortalException;

	public void deleteLCSClusterNodeLCSMessages(long lcsClusterNodeId)
		throws PortalException;

	public void deleteLCSProjectLCSMessage(long lcsProjectId,
		long sourceMessageId, java.lang.String sourceSystemName,
		long classNameId) throws PortalException;

	public void deleteLCSProjectLCSMessages(long lcsProjectId)
		throws PortalException;
}