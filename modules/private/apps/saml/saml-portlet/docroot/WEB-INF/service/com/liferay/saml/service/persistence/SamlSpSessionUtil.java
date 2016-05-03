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

package com.liferay.saml.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import com.liferay.saml.model.SamlSpSession;

import java.util.List;

/**
 * The persistence utility for the saml sp session service. This utility wraps {@link com.liferay.saml.service.persistence.impl.SamlSpSessionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Mika Koivisto
 * @see SamlSpSessionPersistence
 * @see com.liferay.saml.service.persistence.impl.SamlSpSessionPersistenceImpl
 * @generated
 */
@ProviderType
public class SamlSpSessionUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(SamlSpSession samlSpSession) {
		getPersistence().clearCache(samlSpSession);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<SamlSpSession> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SamlSpSession> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SamlSpSession> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<SamlSpSession> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static SamlSpSession update(SamlSpSession samlSpSession) {
		return getPersistence().update(samlSpSession);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static SamlSpSession update(SamlSpSession samlSpSession,
		ServiceContext serviceContext) {
		return getPersistence().update(samlSpSession, serviceContext);
	}

	/**
	* Returns the saml sp session where samlSpSessionKey = &#63; or throws a {@link NoSuchSpSessionException} if it could not be found.
	*
	* @param samlSpSessionKey the saml sp session key
	* @return the matching saml sp session
	* @throws NoSuchSpSessionException if a matching saml sp session could not be found
	*/
	public static SamlSpSession findBySamlSpSessionKey(
		java.lang.String samlSpSessionKey)
		throws com.liferay.saml.exception.NoSuchSpSessionException {
		return getPersistence().findBySamlSpSessionKey(samlSpSessionKey);
	}

	/**
	* Returns the saml sp session where samlSpSessionKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param samlSpSessionKey the saml sp session key
	* @return the matching saml sp session, or <code>null</code> if a matching saml sp session could not be found
	*/
	public static SamlSpSession fetchBySamlSpSessionKey(
		java.lang.String samlSpSessionKey) {
		return getPersistence().fetchBySamlSpSessionKey(samlSpSessionKey);
	}

	/**
	* Returns the saml sp session where samlSpSessionKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param samlSpSessionKey the saml sp session key
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching saml sp session, or <code>null</code> if a matching saml sp session could not be found
	*/
	public static SamlSpSession fetchBySamlSpSessionKey(
		java.lang.String samlSpSessionKey, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchBySamlSpSessionKey(samlSpSessionKey, retrieveFromCache);
	}

	/**
	* Removes the saml sp session where samlSpSessionKey = &#63; from the database.
	*
	* @param samlSpSessionKey the saml sp session key
	* @return the saml sp session that was removed
	*/
	public static SamlSpSession removeBySamlSpSessionKey(
		java.lang.String samlSpSessionKey)
		throws com.liferay.saml.exception.NoSuchSpSessionException {
		return getPersistence().removeBySamlSpSessionKey(samlSpSessionKey);
	}

	/**
	* Returns the number of saml sp sessions where samlSpSessionKey = &#63;.
	*
	* @param samlSpSessionKey the saml sp session key
	* @return the number of matching saml sp sessions
	*/
	public static int countBySamlSpSessionKey(java.lang.String samlSpSessionKey) {
		return getPersistence().countBySamlSpSessionKey(samlSpSessionKey);
	}

	/**
	* Returns the saml sp session where jSessionId = &#63; or throws a {@link NoSuchSpSessionException} if it could not be found.
	*
	* @param jSessionId the j session ID
	* @return the matching saml sp session
	* @throws NoSuchSpSessionException if a matching saml sp session could not be found
	*/
	public static SamlSpSession findByJSessionId(java.lang.String jSessionId)
		throws com.liferay.saml.exception.NoSuchSpSessionException {
		return getPersistence().findByJSessionId(jSessionId);
	}

	/**
	* Returns the saml sp session where jSessionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param jSessionId the j session ID
	* @return the matching saml sp session, or <code>null</code> if a matching saml sp session could not be found
	*/
	public static SamlSpSession fetchByJSessionId(java.lang.String jSessionId) {
		return getPersistence().fetchByJSessionId(jSessionId);
	}

	/**
	* Returns the saml sp session where jSessionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param jSessionId the j session ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching saml sp session, or <code>null</code> if a matching saml sp session could not be found
	*/
	public static SamlSpSession fetchByJSessionId(java.lang.String jSessionId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByJSessionId(jSessionId, retrieveFromCache);
	}

	/**
	* Removes the saml sp session where jSessionId = &#63; from the database.
	*
	* @param jSessionId the j session ID
	* @return the saml sp session that was removed
	*/
	public static SamlSpSession removeByJSessionId(java.lang.String jSessionId)
		throws com.liferay.saml.exception.NoSuchSpSessionException {
		return getPersistence().removeByJSessionId(jSessionId);
	}

	/**
	* Returns the number of saml sp sessions where jSessionId = &#63;.
	*
	* @param jSessionId the j session ID
	* @return the number of matching saml sp sessions
	*/
	public static int countByJSessionId(java.lang.String jSessionId) {
		return getPersistence().countByJSessionId(jSessionId);
	}

	/**
	* Returns all the saml sp sessions where nameIdValue = &#63;.
	*
	* @param nameIdValue the name ID value
	* @return the matching saml sp sessions
	*/
	public static List<SamlSpSession> findByNameIdValue(
		java.lang.String nameIdValue) {
		return getPersistence().findByNameIdValue(nameIdValue);
	}

	/**
	* Returns a range of all the saml sp sessions where nameIdValue = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SamlSpSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param nameIdValue the name ID value
	* @param start the lower bound of the range of saml sp sessions
	* @param end the upper bound of the range of saml sp sessions (not inclusive)
	* @return the range of matching saml sp sessions
	*/
	public static List<SamlSpSession> findByNameIdValue(
		java.lang.String nameIdValue, int start, int end) {
		return getPersistence().findByNameIdValue(nameIdValue, start, end);
	}

	/**
	* Returns an ordered range of all the saml sp sessions where nameIdValue = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SamlSpSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param nameIdValue the name ID value
	* @param start the lower bound of the range of saml sp sessions
	* @param end the upper bound of the range of saml sp sessions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching saml sp sessions
	*/
	public static List<SamlSpSession> findByNameIdValue(
		java.lang.String nameIdValue, int start, int end,
		OrderByComparator<SamlSpSession> orderByComparator) {
		return getPersistence()
				   .findByNameIdValue(nameIdValue, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the saml sp sessions where nameIdValue = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SamlSpSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param nameIdValue the name ID value
	* @param start the lower bound of the range of saml sp sessions
	* @param end the upper bound of the range of saml sp sessions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching saml sp sessions
	*/
	public static List<SamlSpSession> findByNameIdValue(
		java.lang.String nameIdValue, int start, int end,
		OrderByComparator<SamlSpSession> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByNameIdValue(nameIdValue, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first saml sp session in the ordered set where nameIdValue = &#63;.
	*
	* @param nameIdValue the name ID value
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching saml sp session
	* @throws NoSuchSpSessionException if a matching saml sp session could not be found
	*/
	public static SamlSpSession findByNameIdValue_First(
		java.lang.String nameIdValue,
		OrderByComparator<SamlSpSession> orderByComparator)
		throws com.liferay.saml.exception.NoSuchSpSessionException {
		return getPersistence()
				   .findByNameIdValue_First(nameIdValue, orderByComparator);
	}

	/**
	* Returns the first saml sp session in the ordered set where nameIdValue = &#63;.
	*
	* @param nameIdValue the name ID value
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching saml sp session, or <code>null</code> if a matching saml sp session could not be found
	*/
	public static SamlSpSession fetchByNameIdValue_First(
		java.lang.String nameIdValue,
		OrderByComparator<SamlSpSession> orderByComparator) {
		return getPersistence()
				   .fetchByNameIdValue_First(nameIdValue, orderByComparator);
	}

	/**
	* Returns the last saml sp session in the ordered set where nameIdValue = &#63;.
	*
	* @param nameIdValue the name ID value
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching saml sp session
	* @throws NoSuchSpSessionException if a matching saml sp session could not be found
	*/
	public static SamlSpSession findByNameIdValue_Last(
		java.lang.String nameIdValue,
		OrderByComparator<SamlSpSession> orderByComparator)
		throws com.liferay.saml.exception.NoSuchSpSessionException {
		return getPersistence()
				   .findByNameIdValue_Last(nameIdValue, orderByComparator);
	}

	/**
	* Returns the last saml sp session in the ordered set where nameIdValue = &#63;.
	*
	* @param nameIdValue the name ID value
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching saml sp session, or <code>null</code> if a matching saml sp session could not be found
	*/
	public static SamlSpSession fetchByNameIdValue_Last(
		java.lang.String nameIdValue,
		OrderByComparator<SamlSpSession> orderByComparator) {
		return getPersistence()
				   .fetchByNameIdValue_Last(nameIdValue, orderByComparator);
	}

	/**
	* Returns the saml sp sessions before and after the current saml sp session in the ordered set where nameIdValue = &#63;.
	*
	* @param samlSpSessionId the primary key of the current saml sp session
	* @param nameIdValue the name ID value
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next saml sp session
	* @throws NoSuchSpSessionException if a saml sp session with the primary key could not be found
	*/
	public static SamlSpSession[] findByNameIdValue_PrevAndNext(
		long samlSpSessionId, java.lang.String nameIdValue,
		OrderByComparator<SamlSpSession> orderByComparator)
		throws com.liferay.saml.exception.NoSuchSpSessionException {
		return getPersistence()
				   .findByNameIdValue_PrevAndNext(samlSpSessionId, nameIdValue,
			orderByComparator);
	}

	/**
	* Removes all the saml sp sessions where nameIdValue = &#63; from the database.
	*
	* @param nameIdValue the name ID value
	*/
	public static void removeByNameIdValue(java.lang.String nameIdValue) {
		getPersistence().removeByNameIdValue(nameIdValue);
	}

	/**
	* Returns the number of saml sp sessions where nameIdValue = &#63;.
	*
	* @param nameIdValue the name ID value
	* @return the number of matching saml sp sessions
	*/
	public static int countByNameIdValue(java.lang.String nameIdValue) {
		return getPersistence().countByNameIdValue(nameIdValue);
	}

	/**
	* Returns the saml sp session where sessionIndex = &#63; or throws a {@link NoSuchSpSessionException} if it could not be found.
	*
	* @param sessionIndex the session index
	* @return the matching saml sp session
	* @throws NoSuchSpSessionException if a matching saml sp session could not be found
	*/
	public static SamlSpSession findBySessionIndex(
		java.lang.String sessionIndex)
		throws com.liferay.saml.exception.NoSuchSpSessionException {
		return getPersistence().findBySessionIndex(sessionIndex);
	}

	/**
	* Returns the saml sp session where sessionIndex = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param sessionIndex the session index
	* @return the matching saml sp session, or <code>null</code> if a matching saml sp session could not be found
	*/
	public static SamlSpSession fetchBySessionIndex(
		java.lang.String sessionIndex) {
		return getPersistence().fetchBySessionIndex(sessionIndex);
	}

	/**
	* Returns the saml sp session where sessionIndex = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param sessionIndex the session index
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching saml sp session, or <code>null</code> if a matching saml sp session could not be found
	*/
	public static SamlSpSession fetchBySessionIndex(
		java.lang.String sessionIndex, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchBySessionIndex(sessionIndex, retrieveFromCache);
	}

	/**
	* Removes the saml sp session where sessionIndex = &#63; from the database.
	*
	* @param sessionIndex the session index
	* @return the saml sp session that was removed
	*/
	public static SamlSpSession removeBySessionIndex(
		java.lang.String sessionIndex)
		throws com.liferay.saml.exception.NoSuchSpSessionException {
		return getPersistence().removeBySessionIndex(sessionIndex);
	}

	/**
	* Returns the number of saml sp sessions where sessionIndex = &#63;.
	*
	* @param sessionIndex the session index
	* @return the number of matching saml sp sessions
	*/
	public static int countBySessionIndex(java.lang.String sessionIndex) {
		return getPersistence().countBySessionIndex(sessionIndex);
	}

	/**
	* Caches the saml sp session in the entity cache if it is enabled.
	*
	* @param samlSpSession the saml sp session
	*/
	public static void cacheResult(SamlSpSession samlSpSession) {
		getPersistence().cacheResult(samlSpSession);
	}

	/**
	* Caches the saml sp sessions in the entity cache if it is enabled.
	*
	* @param samlSpSessions the saml sp sessions
	*/
	public static void cacheResult(List<SamlSpSession> samlSpSessions) {
		getPersistence().cacheResult(samlSpSessions);
	}

	/**
	* Creates a new saml sp session with the primary key. Does not add the saml sp session to the database.
	*
	* @param samlSpSessionId the primary key for the new saml sp session
	* @return the new saml sp session
	*/
	public static SamlSpSession create(long samlSpSessionId) {
		return getPersistence().create(samlSpSessionId);
	}

	/**
	* Removes the saml sp session with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param samlSpSessionId the primary key of the saml sp session
	* @return the saml sp session that was removed
	* @throws NoSuchSpSessionException if a saml sp session with the primary key could not be found
	*/
	public static SamlSpSession remove(long samlSpSessionId)
		throws com.liferay.saml.exception.NoSuchSpSessionException {
		return getPersistence().remove(samlSpSessionId);
	}

	public static SamlSpSession updateImpl(SamlSpSession samlSpSession) {
		return getPersistence().updateImpl(samlSpSession);
	}

	/**
	* Returns the saml sp session with the primary key or throws a {@link NoSuchSpSessionException} if it could not be found.
	*
	* @param samlSpSessionId the primary key of the saml sp session
	* @return the saml sp session
	* @throws NoSuchSpSessionException if a saml sp session with the primary key could not be found
	*/
	public static SamlSpSession findByPrimaryKey(long samlSpSessionId)
		throws com.liferay.saml.exception.NoSuchSpSessionException {
		return getPersistence().findByPrimaryKey(samlSpSessionId);
	}

	/**
	* Returns the saml sp session with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param samlSpSessionId the primary key of the saml sp session
	* @return the saml sp session, or <code>null</code> if a saml sp session with the primary key could not be found
	*/
	public static SamlSpSession fetchByPrimaryKey(long samlSpSessionId) {
		return getPersistence().fetchByPrimaryKey(samlSpSessionId);
	}

	public static java.util.Map<java.io.Serializable, SamlSpSession> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the saml sp sessions.
	*
	* @return the saml sp sessions
	*/
	public static List<SamlSpSession> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the saml sp sessions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SamlSpSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of saml sp sessions
	* @param end the upper bound of the range of saml sp sessions (not inclusive)
	* @return the range of saml sp sessions
	*/
	public static List<SamlSpSession> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the saml sp sessions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SamlSpSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of saml sp sessions
	* @param end the upper bound of the range of saml sp sessions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of saml sp sessions
	*/
	public static List<SamlSpSession> findAll(int start, int end,
		OrderByComparator<SamlSpSession> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the saml sp sessions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SamlSpSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of saml sp sessions
	* @param end the upper bound of the range of saml sp sessions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of saml sp sessions
	*/
	public static List<SamlSpSession> findAll(int start, int end,
		OrderByComparator<SamlSpSession> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the saml sp sessions from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of saml sp sessions.
	*
	* @return the number of saml sp sessions
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static SamlSpSessionPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (SamlSpSessionPersistence)PortletBeanLocatorUtil.locate(com.liferay.saml.service.ClpSerializer.getServletContextName(),
					SamlSpSessionPersistence.class.getName());

			ReferenceRegistry.registerReference(SamlSpSessionUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	private static SamlSpSessionPersistence _persistence;
}