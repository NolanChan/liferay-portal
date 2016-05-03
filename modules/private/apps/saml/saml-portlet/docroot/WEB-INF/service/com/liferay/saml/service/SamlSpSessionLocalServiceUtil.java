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

package com.liferay.saml.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.service.InvokableLocalService;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the local service utility for SamlSpSession. This utility wraps
 * {@link com.liferay.saml.service.impl.SamlSpSessionLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Mika Koivisto
 * @see SamlSpSessionLocalService
 * @see com.liferay.saml.service.base.SamlSpSessionLocalServiceBaseImpl
 * @see com.liferay.saml.service.impl.SamlSpSessionLocalServiceImpl
 * @generated
 */
@ProviderType
public class SamlSpSessionLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.saml.service.impl.SamlSpSessionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
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
	* Adds the saml sp session to the database. Also notifies the appropriate model listeners.
	*
	* @param samlSpSession the saml sp session
	* @return the saml sp session that was added
	*/
	public static com.liferay.saml.model.SamlSpSession addSamlSpSession(
		com.liferay.saml.model.SamlSpSession samlSpSession) {
		return getService().addSamlSpSession(samlSpSession);
	}

	public static com.liferay.saml.model.SamlSpSession addSamlSpSession(
		java.lang.String samlSpSessionKey, java.lang.String assertionXml,
		java.lang.String jSessionId, java.lang.String nameIdFormat,
		java.lang.String nameIdNameQualifier,
		java.lang.String nameIdSPNameQualifier, java.lang.String nameIdValue,
		java.lang.String sessionIndex,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addSamlSpSession(samlSpSessionKey, assertionXml,
			jSessionId, nameIdFormat, nameIdNameQualifier,
			nameIdSPNameQualifier, nameIdValue, sessionIndex, serviceContext);
	}

	/**
	* Creates a new saml sp session with the primary key. Does not add the saml sp session to the database.
	*
	* @param samlSpSessionId the primary key for the new saml sp session
	* @return the new saml sp session
	*/
	public static com.liferay.saml.model.SamlSpSession createSamlSpSession(
		long samlSpSessionId) {
		return getService().createSamlSpSession(samlSpSessionId);
	}

	/**
	* Deletes the saml sp session from the database. Also notifies the appropriate model listeners.
	*
	* @param samlSpSession the saml sp session
	* @return the saml sp session that was removed
	*/
	public static com.liferay.saml.model.SamlSpSession deleteSamlSpSession(
		com.liferay.saml.model.SamlSpSession samlSpSession) {
		return getService().deleteSamlSpSession(samlSpSession);
	}

	/**
	* Deletes the saml sp session with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param samlSpSessionId the primary key of the saml sp session
	* @return the saml sp session that was removed
	* @throws PortalException if a saml sp session with the primary key could not be found
	*/
	public static com.liferay.saml.model.SamlSpSession deleteSamlSpSession(
		long samlSpSessionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteSamlSpSession(samlSpSessionId);
	}

	public static com.liferay.saml.model.SamlSpSession fetchSamlSpSession(
		long samlSpSessionId) {
		return getService().fetchSamlSpSession(samlSpSessionId);
	}

	public static com.liferay.saml.model.SamlSpSession fetchSamlSpSessionByJSessionId(
		java.lang.String jSessionId) {
		return getService().fetchSamlSpSessionByJSessionId(jSessionId);
	}

	public static com.liferay.saml.model.SamlSpSession fetchSamlSpSessionBySamlSpSessionKey(
		java.lang.String samlSpSessionKey) {
		return getService()
				   .fetchSamlSpSessionBySamlSpSessionKey(samlSpSessionKey);
	}

	public static com.liferay.saml.model.SamlSpSession fetchSamlSpSessionBySessionIndex(
		java.lang.String sessionIndex) {
		return getService().fetchSamlSpSessionBySessionIndex(sessionIndex);
	}

	/**
	* Returns the saml sp session with the primary key.
	*
	* @param samlSpSessionId the primary key of the saml sp session
	* @return the saml sp session
	* @throws PortalException if a saml sp session with the primary key could not be found
	*/
	public static com.liferay.saml.model.SamlSpSession getSamlSpSession(
		long samlSpSessionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getSamlSpSession(samlSpSessionId);
	}

	public static com.liferay.saml.model.SamlSpSession getSamlSpSessionByJSessionId(
		java.lang.String jSessionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getSamlSpSessionByJSessionId(jSessionId);
	}

	public static com.liferay.saml.model.SamlSpSession getSamlSpSessionBySamlSpSessionKey(
		java.lang.String samlSpSessionKey)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getSamlSpSessionBySamlSpSessionKey(samlSpSessionKey);
	}

	public static com.liferay.saml.model.SamlSpSession getSamlSpSessionBySessionIndex(
		java.lang.String sessionIndex)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getSamlSpSessionBySessionIndex(sessionIndex);
	}

	/**
	* Updates the saml sp session in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param samlSpSession the saml sp session
	* @return the saml sp session that was updated
	*/
	public static com.liferay.saml.model.SamlSpSession updateSamlSpSession(
		com.liferay.saml.model.SamlSpSession samlSpSession) {
		return getService().updateSamlSpSession(samlSpSession);
	}

	public static com.liferay.saml.model.SamlSpSession updateSamlSpSession(
		long samlSpSessionId, java.lang.String jSessionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateSamlSpSession(samlSpSessionId, jSessionId);
	}

	public static com.liferay.saml.model.SamlSpSession updateSamlSpSession(
		long samlSpSessionId, java.lang.String samlSpSessionKey,
		java.lang.String assertionXml, java.lang.String jSessionId,
		java.lang.String nameIdFormat, java.lang.String nameIdNameQualifier,
		java.lang.String nameIdSPNameQualifier, java.lang.String nameIdValue,
		java.lang.String sessionIndex,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateSamlSpSession(samlSpSessionId, samlSpSessionKey,
			assertionXml, jSessionId, nameIdFormat, nameIdNameQualifier,
			nameIdSPNameQualifier, nameIdValue, sessionIndex, serviceContext);
	}

	/**
	* Returns the number of saml sp sessions.
	*
	* @return the number of saml sp sessions
	*/
	public static int getSamlSpSessionsCount() {
		return getService().getSamlSpSessionsCount();
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.saml.model.impl.SamlSpSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.saml.model.impl.SamlSpSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the saml sp sessions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.saml.model.impl.SamlSpSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of saml sp sessions
	* @param end the upper bound of the range of saml sp sessions (not inclusive)
	* @return the range of saml sp sessions
	*/
	public static java.util.List<com.liferay.saml.model.SamlSpSession> getSamlSpSessions(
		int start, int end) {
		return getService().getSamlSpSessions(start, end);
	}

	public static java.util.List<com.liferay.saml.model.SamlSpSession> getSamlSpSessions(
		java.lang.String nameIdValue) {
		return getService().getSamlSpSessions(nameIdValue);
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

	public static void clearService() {
		_service = null;
	}

	public static SamlSpSessionLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					SamlSpSessionLocalService.class.getName());

			if (invokableLocalService instanceof SamlSpSessionLocalService) {
				_service = (SamlSpSessionLocalService)invokableLocalService;
			}
			else {
				_service = new SamlSpSessionLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(SamlSpSessionLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static SamlSpSessionLocalService _service;
}