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

import com.liferay.osb.lcs.model.LCSClusterEntry;
import com.liferay.osb.lcs.model.LCSClusterNode;
import com.liferay.osb.lcs.model.LCSNotification;

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

import java.util.List;

/**
 * Provides the local service interface for LCSNotification. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Igor Beslic
 * @see LCSNotificationLocalServiceUtil
 * @see com.liferay.osb.lcs.service.base.LCSNotificationLocalServiceBaseImpl
 * @see com.liferay.osb.lcs.service.impl.LCSNotificationLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface LCSNotificationLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LCSNotificationLocalServiceUtil} to access the l c s notification local service. Add custom service methods to {@link com.liferay.osb.lcs.service.impl.LCSNotificationLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the l c s notification to the database. Also notifies the appropriate model listeners.
	*
	* @param lcsNotification the l c s notification
	* @return the l c s notification that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public LCSNotification addLCSNotification(LCSNotification lcsNotification);

	/**
	* Adds an LCS notification for the LCS cluster entry.
	*
	* @param userId the primary key of the user
	* @param lcsClusterEntry the notification's LCS cluster entry
	* @param enabled whether to enable the notification
	* @param type the notification type as defined in {@link
	com.liferay.osb.lcs.notifications.util.LCSEventType}
	* @return the LCS notification
	*/
	public LCSNotification addLCSNotification(long userId,
		LCSClusterEntry lcsClusterEntry, boolean enabled, int type);

	/**
	* Adds an LCS notification for the LCS cluster node.
	*
	* @param userId the primary key of the user
	* @param lcsClusterNode the notification's LCS cluster node
	* @param enabled whether to enable the notification
	* @param type the notification type as defined in {@link
	com.liferay.osb.lcs.notifications.util.LCSEventType}
	* @return the LCS notification
	*/
	public LCSNotification addLCSNotification(long userId,
		LCSClusterNode lcsClusterNode, boolean enabled, int type);

	/**
	* Adds an LCS notification for the LCS project.
	*
	* @param userId the primary key of the user
	* @param lcsProjectId the primary key of the notification's LCS project
	* @param enabled whether to enable the notification
	* @param type the notification type as defined in {@link
	com.liferay.osb.lcs.notifications.util.LCSEventType}
	* @return the LCS notification
	*/
	public LCSNotification addLCSProjectLCSNotification(long userId,
		long lcsProjectId, boolean enabled, int type);

	/**
	* Creates a new l c s notification with the primary key. Does not add the l c s notification to the database.
	*
	* @param lcsNotificationId the primary key for the new l c s notification
	* @return the new l c s notification
	*/
	public LCSNotification createLCSNotification(long lcsNotificationId);

	/**
	* Deletes the l c s notification from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsNotification the l c s notification
	* @return the l c s notification that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public LCSNotification deleteLCSNotification(
		LCSNotification lcsNotification);

	/**
	* Deletes the l c s notification with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsNotificationId the primary key of the l c s notification
	* @return the l c s notification that was removed
	* @throws PortalException if a l c s notification with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public LCSNotification deleteLCSNotification(long lcsNotificationId)
		throws PortalException;

	/**
	* Returns the LCS notification of the user, LCS cluster entry, and LCS
	* notification type.
	*
	* @param userId the primary key of the user
	* @param lcsClusterEntryId the primary key of the notification's LCS
	cluster entry
	* @param type the notification type as defined in {@link
	com.liferay.osb.lcs.notifications.util.LCSEventType}
	* @return the matching LCS notification
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LCSNotification fetchLCSClusterEntryLCSNotification(long userId,
		long lcsClusterEntryId, int type);

	/**
	* Returns the LCS notification of the user, LCS cluster node, and LCS
	* notification type.
	*
	* @param userId the primary key of the user
	* @param lcsClusterNodeId the primary key of the notification's LCS
	cluster node
	* @param type the notification type as defined in {@link
	com.liferay.osb.lcs.notifications.util.LCSEventType}
	* @return the matching LCS notification
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LCSNotification fetchLCSClusterNodeLCSNotification(long userId,
		long lcsClusterNodeId, int type);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LCSNotification fetchLCSNotification(long lcsNotificationId);

	/**
	* Returns the LCS notification for the user, LCS project, and LCS
	* notification type.
	*
	* @param userId the primary key of the user
	* @param lcsProjectId the primary key of the notification's LCS project
	* @param type the notification type as defined in {@link
	com.liferay.osb.lcs.notifications.util.LCSEventType}
	* @return the matching LCS notification
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LCSNotification fetchLCSProjectLCSNotification(long userId,
		long lcsProjectId, int type);

	/**
	* Returns the l c s notification with the primary key.
	*
	* @param lcsNotificationId the primary key of the l c s notification
	* @return the l c s notification
	* @throws PortalException if a l c s notification with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LCSNotification getLCSNotification(long lcsNotificationId)
		throws PortalException;

	/**
	* Updates the l c s notification in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param lcsNotification the l c s notification
	* @return the l c s notification that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public LCSNotification updateLCSNotification(
		LCSNotification lcsNotification);

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
	* Returns the number of l c s notifications.
	*
	* @return the number of l c s notifications
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getLCSNotificationsCount();

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSNotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSNotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the l c s notifications.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSNotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s notifications
	* @param end the upper bound of the range of l c s notifications (not inclusive)
	* @return the range of l c s notifications
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSNotification> getLCSNotifications(int start, int end);

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
	* Deletes the LCS notification for the LCS cluster entry.
	*
	* @param lcsClusterEntryId the primary key of the LCS cluster entry
	*/
	public void deleteLCSClusterEntryLCSNotification(long lcsClusterEntryId);

	/**
	* Deletes the LCS notification for the LCS cluster node.
	*
	* @param lcsClusterNodeId the primary key of the LCS cluster node
	*/
	public void deleteLCSClusterNodeLCSNotification(long lcsClusterNodeId);

	/**
	* Deletes the LCS notification for the LCS project.
	*
	* @param lcsProjectId the primary key of the LCS project
	*/
	public void deleteLCSProjectLCSNotification(long lcsProjectId);

	/**
	* Deletes the LCS notifications of the user.
	*
	* @param userId the primary key of the user
	*/
	public void deleteUserLCSNotifications(long userId);

	/**
	* Deletes the LCS notifications of the user, for the name and primary key
	* of the class.
	*
	* @param userId the primary key of the user
	* @param classNameValue the class name
	* @param classPK the primary key for the object of class
	*/
	public void deleteUserLCSNotifications(long userId,
		java.lang.String classNameValue, long classPK);
}