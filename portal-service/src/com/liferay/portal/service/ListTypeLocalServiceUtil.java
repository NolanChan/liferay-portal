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

package com.liferay.portal.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the local service utility for ListType. This utility wraps
 * {@link com.liferay.portal.service.impl.ListTypeLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ListTypeLocalService
 * @see com.liferay.portal.service.base.ListTypeLocalServiceBaseImpl
 * @see com.liferay.portal.service.impl.ListTypeLocalServiceImpl
 * @generated
 */
@ProviderType
public class ListTypeLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portal.service.impl.ListTypeLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the list type to the database. Also notifies the appropriate model listeners.
	*
	* @param listType the list type
	* @return the list type that was added
	*/
	public static com.liferay.portal.model.ListType addListType(
		com.liferay.portal.model.ListType listType) {
		return getService().addListType(listType);
	}

	public static com.liferay.portal.model.ListType addListType(
		java.lang.String name, java.lang.String type) {
		return getService().addListType(name, type);
	}

	/**
	* Creates a new list type with the primary key. Does not add the list type to the database.
	*
	* @param listTypeId the primary key for the new list type
	* @return the new list type
	*/
	public static com.liferay.portal.model.ListType createListType(
		int listTypeId) {
		return getService().createListType(listTypeId);
	}

	/**
	* Deletes the list type from the database. Also notifies the appropriate model listeners.
	*
	* @param listType the list type
	* @return the list type that was removed
	*/
	public static com.liferay.portal.model.ListType deleteListType(
		com.liferay.portal.model.ListType listType) {
		return getService().deleteListType(listType);
	}

	/**
	* Deletes the list type with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param listTypeId the primary key of the list type
	* @return the list type that was removed
	* @throws PortalException if a list type with the primary key could not be found
	*/
	public static com.liferay.portal.model.ListType deleteListType(
		int listTypeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteListType(listTypeId);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.ListTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.ListTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.portal.model.ListType fetchListType(
		int listTypeId) {
		return getService().fetchListType(listTypeId);
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

	/**
	* Returns the list type with the primary key.
	*
	* @param listTypeId the primary key of the list type
	* @return the list type
	* @throws PortalException if a list type with the primary key could not be found
	*/
	public static com.liferay.portal.model.ListType getListType(int listTypeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getListType(listTypeId);
	}

	/**
	* Returns a range of all the list types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.ListTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of list types
	* @param end the upper bound of the range of list types (not inclusive)
	* @return the range of list types
	*/
	public static java.util.List<com.liferay.portal.model.ListType> getListTypes(
		int start, int end) {
		return getService().getListTypes(start, end);
	}

	public static java.util.List<com.liferay.portal.model.ListType> getListTypes(
		java.lang.String type) {
		return getService().getListTypes(type);
	}

	/**
	* Returns the number of list types.
	*
	* @return the number of list types
	*/
	public static int getListTypesCount() {
		return getService().getListTypesCount();
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
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
	* Updates the list type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param listType the list type
	* @return the list type that was updated
	*/
	public static com.liferay.portal.model.ListType updateListType(
		com.liferay.portal.model.ListType listType) {
		return getService().updateListType(listType);
	}

	public static void validate(int listTypeId, long classNameId,
		java.lang.String type)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().validate(listTypeId, classNameId, type);
	}

	public static void validate(int listTypeId, java.lang.String type)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().validate(listTypeId, type);
	}

	public static ListTypeLocalService getService() {
		if (_service == null) {
			_service = (ListTypeLocalService)PortalBeanLocatorUtil.locate(ListTypeLocalService.class.getName());

			ReferenceRegistry.registerReference(ListTypeLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setService(ListTypeLocalService service) {
	}

	private static ListTypeLocalService _service;
}