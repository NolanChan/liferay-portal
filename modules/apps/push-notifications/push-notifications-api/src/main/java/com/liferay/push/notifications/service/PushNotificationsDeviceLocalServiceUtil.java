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

package com.liferay.push.notifications.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for PushNotificationsDevice. This utility wraps
 * {@link com.liferay.push.notifications.service.impl.PushNotificationsDeviceLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Bruno Farache
 * @see PushNotificationsDeviceLocalService
 * @see com.liferay.push.notifications.service.base.PushNotificationsDeviceLocalServiceBaseImpl
 * @see com.liferay.push.notifications.service.impl.PushNotificationsDeviceLocalServiceImpl
 * @generated
 */
@ProviderType
public class PushNotificationsDeviceLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.push.notifications.service.impl.PushNotificationsDeviceLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the push notifications device to the database. Also notifies the appropriate model listeners.
	*
	* @param pushNotificationsDevice the push notifications device
	* @return the push notifications device that was added
	*/
	public static com.liferay.push.notifications.model.PushNotificationsDevice addPushNotificationsDevice(
		com.liferay.push.notifications.model.PushNotificationsDevice pushNotificationsDevice) {
		return getService().addPushNotificationsDevice(pushNotificationsDevice);
	}

	public static com.liferay.push.notifications.model.PushNotificationsDevice addPushNotificationsDevice(
		long userId, java.lang.String platform, java.lang.String token)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().addPushNotificationsDevice(userId, platform, token);
	}

	/**
	* Creates a new push notifications device with the primary key. Does not add the push notifications device to the database.
	*
	* @param pushNotificationsDeviceId the primary key for the new push notifications device
	* @return the new push notifications device
	*/
	public static com.liferay.push.notifications.model.PushNotificationsDevice createPushNotificationsDevice(
		long pushNotificationsDeviceId) {
		return getService()
				   .createPushNotificationsDevice(pushNotificationsDeviceId);
	}

	/**
	* Deletes the push notifications device from the database. Also notifies the appropriate model listeners.
	*
	* @param pushNotificationsDevice the push notifications device
	* @return the push notifications device that was removed
	*/
	public static com.liferay.push.notifications.model.PushNotificationsDevice deletePushNotificationsDevice(
		com.liferay.push.notifications.model.PushNotificationsDevice pushNotificationsDevice) {
		return getService()
				   .deletePushNotificationsDevice(pushNotificationsDevice);
	}

	public static com.liferay.push.notifications.model.PushNotificationsDevice deletePushNotificationsDevice(
		java.lang.String token)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePushNotificationsDevice(token);
	}

	/**
	* Deletes the push notifications device with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param pushNotificationsDeviceId the primary key of the push notifications device
	* @return the push notifications device that was removed
	* @throws PortalException if a push notifications device with the primary key could not be found
	*/
	public static com.liferay.push.notifications.model.PushNotificationsDevice deletePushNotificationsDevice(
		long pushNotificationsDeviceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .deletePushNotificationsDevice(pushNotificationsDeviceId);
	}

	public static com.liferay.push.notifications.model.PushNotificationsDevice fetchPushNotificationsDevice(
		long pushNotificationsDeviceId) {
		return getService()
				   .fetchPushNotificationsDevice(pushNotificationsDeviceId);
	}

	/**
	* Returns the push notifications device with the primary key.
	*
	* @param pushNotificationsDeviceId the primary key of the push notifications device
	* @return the push notifications device
	* @throws PortalException if a push notifications device with the primary key could not be found
	*/
	public static com.liferay.push.notifications.model.PushNotificationsDevice getPushNotificationsDevice(
		long pushNotificationsDeviceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPushNotificationsDevice(pushNotificationsDeviceId);
	}

	/**
	* Updates the push notifications device in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param pushNotificationsDevice the push notifications device
	* @return the push notifications device that was updated
	*/
	public static com.liferay.push.notifications.model.PushNotificationsDevice updatePushNotificationsDevice(
		com.liferay.push.notifications.model.PushNotificationsDevice pushNotificationsDevice) {
		return getService()
				   .updatePushNotificationsDevice(pushNotificationsDevice);
	}

	/**
	* Returns the number of push notifications devices.
	*
	* @return the number of push notifications devices
	*/
	public static int getPushNotificationsDevicesCount() {
		return getService().getPushNotificationsDevicesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.push.notifications.model.impl.PushNotificationsDeviceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.push.notifications.model.impl.PushNotificationsDeviceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns a range of all the push notifications devices.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.push.notifications.model.impl.PushNotificationsDeviceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of push notifications devices
	* @param end the upper bound of the range of push notifications devices (not inclusive)
	* @return the range of push notifications devices
	*/
	public static java.util.List<com.liferay.push.notifications.model.PushNotificationsDevice> getPushNotificationsDevices(
		int start, int end) {
		return getService().getPushNotificationsDevices(start, end);
	}

	public static java.util.List<com.liferay.push.notifications.model.PushNotificationsDevice> getPushNotificationsDevices(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.push.notifications.model.PushNotificationsDevice> orderByComparator) {
		return getService()
				   .getPushNotificationsDevices(start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static void sendPushNotification(java.lang.String platform,
		java.util.List<java.lang.String> tokens,
		com.liferay.portal.kernel.json.JSONObject payloadJSONObject)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().sendPushNotification(platform, tokens, payloadJSONObject);
	}

	public static void sendPushNotification(long[] toUserIds,
		com.liferay.portal.kernel.json.JSONObject payloadJSONObject)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().sendPushNotification(toUserIds, payloadJSONObject);
	}

	public static void updateToken(java.lang.String oldToken,
		java.lang.String newToken)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().updateToken(oldToken, newToken);
	}

	public static PushNotificationsDeviceLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<PushNotificationsDeviceLocalService, PushNotificationsDeviceLocalService> _serviceTracker =
		ServiceTrackerFactory.open(PushNotificationsDeviceLocalService.class);
}