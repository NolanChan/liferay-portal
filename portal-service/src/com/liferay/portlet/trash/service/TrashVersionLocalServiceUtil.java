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

package com.liferay.portlet.trash.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the local service utility for TrashVersion. This utility wraps
 * {@link com.liferay.portlet.trash.service.impl.TrashVersionLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see TrashVersionLocalService
 * @see com.liferay.portlet.trash.service.base.TrashVersionLocalServiceBaseImpl
 * @see com.liferay.portlet.trash.service.impl.TrashVersionLocalServiceImpl
 * @generated
 */
@ProviderType
public class TrashVersionLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portlet.trash.service.impl.TrashVersionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portlet.trash.model.TrashVersion addTrashVersion(
		long trashEntryId, java.lang.String className, long classPK,
		int status,
		com.liferay.portal.kernel.util.UnicodeProperties typeSettingsProperties) {
		return getService()
				   .addTrashVersion(trashEntryId, className, classPK, status,
			typeSettingsProperties);
	}

	/**
	* Adds the trash version to the database. Also notifies the appropriate model listeners.
	*
	* @param trashVersion the trash version
	* @return the trash version that was added
	*/
	public static com.liferay.portlet.trash.model.TrashVersion addTrashVersion(
		com.liferay.portlet.trash.model.TrashVersion trashVersion) {
		return getService().addTrashVersion(trashVersion);
	}

	/**
	* Creates a new trash version with the primary key. Does not add the trash version to the database.
	*
	* @param versionId the primary key for the new trash version
	* @return the new trash version
	*/
	public static com.liferay.portlet.trash.model.TrashVersion createTrashVersion(
		long versionId) {
		return getService().createTrashVersion(versionId);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portlet.trash.model.TrashVersion deleteTrashVersion(
		java.lang.String className, long classPK) {
		return getService().deleteTrashVersion(className, classPK);
	}

	/**
	* Deletes the trash version from the database. Also notifies the appropriate model listeners.
	*
	* @param trashVersion the trash version
	* @return the trash version that was removed
	*/
	public static com.liferay.portlet.trash.model.TrashVersion deleteTrashVersion(
		com.liferay.portlet.trash.model.TrashVersion trashVersion) {
		return getService().deleteTrashVersion(trashVersion);
	}

	/**
	* Deletes the trash version with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param versionId the primary key of the trash version
	* @return the trash version that was removed
	* @throws PortalException if a trash version with the primary key could not be found
	*/
	public static com.liferay.portlet.trash.model.TrashVersion deleteTrashVersion(
		long versionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteTrashVersion(versionId);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.trash.model.impl.TrashVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.trash.model.impl.TrashVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.portlet.trash.model.TrashVersion fetchTrashVersion(
		long versionId) {
		return getService().fetchTrashVersion(versionId);
	}

	public static com.liferay.portlet.trash.model.TrashVersion fetchVersion(
		java.lang.String className, long classPK) {
		return getService().fetchVersion(className, classPK);
	}

	/**
	* @deprecated As of 7.0.0, replaced by {@link #fetchVersion(String, long)}
	*/
	@Deprecated
	public static com.liferay.portlet.trash.model.TrashVersion fetchVersion(
		long entryId, java.lang.String className, long classPK) {
		return getService().fetchVersion(entryId, className, classPK);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the trash version with the primary key.
	*
	* @param versionId the primary key of the trash version
	* @return the trash version
	* @throws PortalException if a trash version with the primary key could not be found
	*/
	public static com.liferay.portlet.trash.model.TrashVersion getTrashVersion(
		long versionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getTrashVersion(versionId);
	}

	/**
	* Returns a range of all the trash versions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.trash.model.impl.TrashVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of trash versions
	* @param end the upper bound of the range of trash versions (not inclusive)
	* @return the range of trash versions
	*/
	public static java.util.List<com.liferay.portlet.trash.model.TrashVersion> getTrashVersions(
		int start, int end) {
		return getService().getTrashVersions(start, end);
	}

	/**
	* Returns the number of trash versions.
	*
	* @return the number of trash versions
	*/
	public static int getTrashVersionsCount() {
		return getService().getTrashVersionsCount();
	}

	public static java.util.List<com.liferay.portlet.trash.model.TrashVersion> getVersions(
		long entryId) {
		return getService().getVersions(entryId);
	}

	public static java.util.List<com.liferay.portlet.trash.model.TrashVersion> getVersions(
		long entryId, java.lang.String className) {
		return getService().getVersions(entryId, className);
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	/**
	* Updates the trash version in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param trashVersion the trash version
	* @return the trash version that was updated
	*/
	public static com.liferay.portlet.trash.model.TrashVersion updateTrashVersion(
		com.liferay.portlet.trash.model.TrashVersion trashVersion) {
		return getService().updateTrashVersion(trashVersion);
	}

	public static TrashVersionLocalService getService() {
		if (_service == null) {
			_service = (TrashVersionLocalService)PortalBeanLocatorUtil.locate(TrashVersionLocalService.class.getName());

			ReferenceRegistry.registerReference(TrashVersionLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setService(TrashVersionLocalService service) {
	}

	private static TrashVersionLocalService _service;
}