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

package com.liferay.dynamic.data.lists.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.dynamic.data.lists.model.DDLRecordSet;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the d d l record set service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.dynamic.data.lists.service.persistence.impl.DDLRecordSetPersistenceImpl
 * @see DDLRecordSetUtil
 * @generated
 */
@ProviderType
public interface DDLRecordSetPersistence extends BasePersistence<DDLRecordSet> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DDLRecordSetUtil} to access the d d l record set persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the d d l record sets where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching d d l record sets
	*/
	public java.util.List<DDLRecordSet> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the d d l record sets where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DDLRecordSetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of d d l record sets
	* @param end the upper bound of the range of d d l record sets (not inclusive)
	* @return the range of matching d d l record sets
	*/
	public java.util.List<DDLRecordSet> findByUuid(java.lang.String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the d d l record sets where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DDLRecordSetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of d d l record sets
	* @param end the upper bound of the range of d d l record sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching d d l record sets
	*/
	public java.util.List<DDLRecordSet> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDLRecordSet> orderByComparator);

	/**
	* Returns an ordered range of all the d d l record sets where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DDLRecordSetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of d d l record sets
	* @param end the upper bound of the range of d d l record sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching d d l record sets
	*/
	public java.util.List<DDLRecordSet> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDLRecordSet> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first d d l record set in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching d d l record set
	* @throws NoSuchRecordSetException if a matching d d l record set could not be found
	*/
	public DDLRecordSet findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DDLRecordSet> orderByComparator)
		throws com.liferay.dynamic.data.lists.exception.NoSuchRecordSetException;

	/**
	* Returns the first d d l record set in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching d d l record set, or <code>null</code> if a matching d d l record set could not be found
	*/
	public DDLRecordSet fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DDLRecordSet> orderByComparator);

	/**
	* Returns the last d d l record set in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching d d l record set
	* @throws NoSuchRecordSetException if a matching d d l record set could not be found
	*/
	public DDLRecordSet findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DDLRecordSet> orderByComparator)
		throws com.liferay.dynamic.data.lists.exception.NoSuchRecordSetException;

	/**
	* Returns the last d d l record set in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching d d l record set, or <code>null</code> if a matching d d l record set could not be found
	*/
	public DDLRecordSet fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DDLRecordSet> orderByComparator);

	/**
	* Returns the d d l record sets before and after the current d d l record set in the ordered set where uuid = &#63;.
	*
	* @param recordSetId the primary key of the current d d l record set
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next d d l record set
	* @throws NoSuchRecordSetException if a d d l record set with the primary key could not be found
	*/
	public DDLRecordSet[] findByUuid_PrevAndNext(long recordSetId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DDLRecordSet> orderByComparator)
		throws com.liferay.dynamic.data.lists.exception.NoSuchRecordSetException;

	/**
	* Removes all the d d l record sets where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of d d l record sets where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching d d l record sets
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the d d l record set where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchRecordSetException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching d d l record set
	* @throws NoSuchRecordSetException if a matching d d l record set could not be found
	*/
	public DDLRecordSet findByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.dynamic.data.lists.exception.NoSuchRecordSetException;

	/**
	* Returns the d d l record set where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching d d l record set, or <code>null</code> if a matching d d l record set could not be found
	*/
	public DDLRecordSet fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the d d l record set where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching d d l record set, or <code>null</code> if a matching d d l record set could not be found
	*/
	public DDLRecordSet fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the d d l record set where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the d d l record set that was removed
	*/
	public DDLRecordSet removeByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.dynamic.data.lists.exception.NoSuchRecordSetException;

	/**
	* Returns the number of d d l record sets where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching d d l record sets
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the d d l record sets where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching d d l record sets
	*/
	public java.util.List<DDLRecordSet> findByUuid_C(java.lang.String uuid,
		long companyId);

	/**
	* Returns a range of all the d d l record sets where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DDLRecordSetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of d d l record sets
	* @param end the upper bound of the range of d d l record sets (not inclusive)
	* @return the range of matching d d l record sets
	*/
	public java.util.List<DDLRecordSet> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the d d l record sets where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DDLRecordSetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of d d l record sets
	* @param end the upper bound of the range of d d l record sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching d d l record sets
	*/
	public java.util.List<DDLRecordSet> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDLRecordSet> orderByComparator);

	/**
	* Returns an ordered range of all the d d l record sets where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DDLRecordSetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of d d l record sets
	* @param end the upper bound of the range of d d l record sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching d d l record sets
	*/
	public java.util.List<DDLRecordSet> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDLRecordSet> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first d d l record set in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching d d l record set
	* @throws NoSuchRecordSetException if a matching d d l record set could not be found
	*/
	public DDLRecordSet findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DDLRecordSet> orderByComparator)
		throws com.liferay.dynamic.data.lists.exception.NoSuchRecordSetException;

	/**
	* Returns the first d d l record set in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching d d l record set, or <code>null</code> if a matching d d l record set could not be found
	*/
	public DDLRecordSet fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DDLRecordSet> orderByComparator);

	/**
	* Returns the last d d l record set in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching d d l record set
	* @throws NoSuchRecordSetException if a matching d d l record set could not be found
	*/
	public DDLRecordSet findByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DDLRecordSet> orderByComparator)
		throws com.liferay.dynamic.data.lists.exception.NoSuchRecordSetException;

	/**
	* Returns the last d d l record set in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching d d l record set, or <code>null</code> if a matching d d l record set could not be found
	*/
	public DDLRecordSet fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DDLRecordSet> orderByComparator);

	/**
	* Returns the d d l record sets before and after the current d d l record set in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param recordSetId the primary key of the current d d l record set
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next d d l record set
	* @throws NoSuchRecordSetException if a d d l record set with the primary key could not be found
	*/
	public DDLRecordSet[] findByUuid_C_PrevAndNext(long recordSetId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DDLRecordSet> orderByComparator)
		throws com.liferay.dynamic.data.lists.exception.NoSuchRecordSetException;

	/**
	* Removes all the d d l record sets where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of d d l record sets where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching d d l record sets
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the d d l record sets where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching d d l record sets
	*/
	public java.util.List<DDLRecordSet> findByGroupId(long groupId);

	/**
	* Returns a range of all the d d l record sets where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DDLRecordSetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of d d l record sets
	* @param end the upper bound of the range of d d l record sets (not inclusive)
	* @return the range of matching d d l record sets
	*/
	public java.util.List<DDLRecordSet> findByGroupId(long groupId, int start,
		int end);

	/**
	* Returns an ordered range of all the d d l record sets where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DDLRecordSetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of d d l record sets
	* @param end the upper bound of the range of d d l record sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching d d l record sets
	*/
	public java.util.List<DDLRecordSet> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDLRecordSet> orderByComparator);

	/**
	* Returns an ordered range of all the d d l record sets where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DDLRecordSetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of d d l record sets
	* @param end the upper bound of the range of d d l record sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching d d l record sets
	*/
	public java.util.List<DDLRecordSet> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDLRecordSet> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first d d l record set in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching d d l record set
	* @throws NoSuchRecordSetException if a matching d d l record set could not be found
	*/
	public DDLRecordSet findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DDLRecordSet> orderByComparator)
		throws com.liferay.dynamic.data.lists.exception.NoSuchRecordSetException;

	/**
	* Returns the first d d l record set in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching d d l record set, or <code>null</code> if a matching d d l record set could not be found
	*/
	public DDLRecordSet fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DDLRecordSet> orderByComparator);

	/**
	* Returns the last d d l record set in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching d d l record set
	* @throws NoSuchRecordSetException if a matching d d l record set could not be found
	*/
	public DDLRecordSet findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DDLRecordSet> orderByComparator)
		throws com.liferay.dynamic.data.lists.exception.NoSuchRecordSetException;

	/**
	* Returns the last d d l record set in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching d d l record set, or <code>null</code> if a matching d d l record set could not be found
	*/
	public DDLRecordSet fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DDLRecordSet> orderByComparator);

	/**
	* Returns the d d l record sets before and after the current d d l record set in the ordered set where groupId = &#63;.
	*
	* @param recordSetId the primary key of the current d d l record set
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next d d l record set
	* @throws NoSuchRecordSetException if a d d l record set with the primary key could not be found
	*/
	public DDLRecordSet[] findByGroupId_PrevAndNext(long recordSetId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DDLRecordSet> orderByComparator)
		throws com.liferay.dynamic.data.lists.exception.NoSuchRecordSetException;

	/**
	* Returns all the d d l record sets that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching d d l record sets that the user has permission to view
	*/
	public java.util.List<DDLRecordSet> filterFindByGroupId(long groupId);

	/**
	* Returns a range of all the d d l record sets that the user has permission to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DDLRecordSetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of d d l record sets
	* @param end the upper bound of the range of d d l record sets (not inclusive)
	* @return the range of matching d d l record sets that the user has permission to view
	*/
	public java.util.List<DDLRecordSet> filterFindByGroupId(long groupId,
		int start, int end);

	/**
	* Returns an ordered range of all the d d l record sets that the user has permissions to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DDLRecordSetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of d d l record sets
	* @param end the upper bound of the range of d d l record sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching d d l record sets that the user has permission to view
	*/
	public java.util.List<DDLRecordSet> filterFindByGroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDLRecordSet> orderByComparator);

	/**
	* Returns the d d l record sets before and after the current d d l record set in the ordered set of d d l record sets that the user has permission to view where groupId = &#63;.
	*
	* @param recordSetId the primary key of the current d d l record set
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next d d l record set
	* @throws NoSuchRecordSetException if a d d l record set with the primary key could not be found
	*/
	public DDLRecordSet[] filterFindByGroupId_PrevAndNext(long recordSetId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DDLRecordSet> orderByComparator)
		throws com.liferay.dynamic.data.lists.exception.NoSuchRecordSetException;

	/**
	* Returns all the d d l record sets that the user has permission to view where groupId = any &#63;.
	*
	* @param groupIds the group IDs
	* @return the matching d d l record sets that the user has permission to view
	*/
	public java.util.List<DDLRecordSet> filterFindByGroupId(long[] groupIds);

	/**
	* Returns a range of all the d d l record sets that the user has permission to view where groupId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DDLRecordSetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupIds the group IDs
	* @param start the lower bound of the range of d d l record sets
	* @param end the upper bound of the range of d d l record sets (not inclusive)
	* @return the range of matching d d l record sets that the user has permission to view
	*/
	public java.util.List<DDLRecordSet> filterFindByGroupId(long[] groupIds,
		int start, int end);

	/**
	* Returns an ordered range of all the d d l record sets that the user has permission to view where groupId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DDLRecordSetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupIds the group IDs
	* @param start the lower bound of the range of d d l record sets
	* @param end the upper bound of the range of d d l record sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching d d l record sets that the user has permission to view
	*/
	public java.util.List<DDLRecordSet> filterFindByGroupId(long[] groupIds,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDLRecordSet> orderByComparator);

	/**
	* Returns all the d d l record sets where groupId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DDLRecordSetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupIds the group IDs
	* @return the matching d d l record sets
	*/
	public java.util.List<DDLRecordSet> findByGroupId(long[] groupIds);

	/**
	* Returns a range of all the d d l record sets where groupId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DDLRecordSetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupIds the group IDs
	* @param start the lower bound of the range of d d l record sets
	* @param end the upper bound of the range of d d l record sets (not inclusive)
	* @return the range of matching d d l record sets
	*/
	public java.util.List<DDLRecordSet> findByGroupId(long[] groupIds,
		int start, int end);

	/**
	* Returns an ordered range of all the d d l record sets where groupId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DDLRecordSetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupIds the group IDs
	* @param start the lower bound of the range of d d l record sets
	* @param end the upper bound of the range of d d l record sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching d d l record sets
	*/
	public java.util.List<DDLRecordSet> findByGroupId(long[] groupIds,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDLRecordSet> orderByComparator);

	/**
	* Returns an ordered range of all the d d l record sets where groupId = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DDLRecordSetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of d d l record sets
	* @param end the upper bound of the range of d d l record sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching d d l record sets
	*/
	public java.util.List<DDLRecordSet> findByGroupId(long[] groupIds,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDLRecordSet> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the d d l record sets where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of d d l record sets where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching d d l record sets
	*/
	public int countByGroupId(long groupId);

	/**
	* Returns the number of d d l record sets where groupId = any &#63;.
	*
	* @param groupIds the group IDs
	* @return the number of matching d d l record sets
	*/
	public int countByGroupId(long[] groupIds);

	/**
	* Returns the number of d d l record sets that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching d d l record sets that the user has permission to view
	*/
	public int filterCountByGroupId(long groupId);

	/**
	* Returns the number of d d l record sets that the user has permission to view where groupId = any &#63;.
	*
	* @param groupIds the group IDs
	* @return the number of matching d d l record sets that the user has permission to view
	*/
	public int filterCountByGroupId(long[] groupIds);

	/**
	* Returns the d d l record set where groupId = &#63; and recordSetKey = &#63; or throws a {@link NoSuchRecordSetException} if it could not be found.
	*
	* @param groupId the group ID
	* @param recordSetKey the record set key
	* @return the matching d d l record set
	* @throws NoSuchRecordSetException if a matching d d l record set could not be found
	*/
	public DDLRecordSet findByG_R(long groupId, java.lang.String recordSetKey)
		throws com.liferay.dynamic.data.lists.exception.NoSuchRecordSetException;

	/**
	* Returns the d d l record set where groupId = &#63; and recordSetKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param recordSetKey the record set key
	* @return the matching d d l record set, or <code>null</code> if a matching d d l record set could not be found
	*/
	public DDLRecordSet fetchByG_R(long groupId, java.lang.String recordSetKey);

	/**
	* Returns the d d l record set where groupId = &#63; and recordSetKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param recordSetKey the record set key
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching d d l record set, or <code>null</code> if a matching d d l record set could not be found
	*/
	public DDLRecordSet fetchByG_R(long groupId, java.lang.String recordSetKey,
		boolean retrieveFromCache);

	/**
	* Removes the d d l record set where groupId = &#63; and recordSetKey = &#63; from the database.
	*
	* @param groupId the group ID
	* @param recordSetKey the record set key
	* @return the d d l record set that was removed
	*/
	public DDLRecordSet removeByG_R(long groupId, java.lang.String recordSetKey)
		throws com.liferay.dynamic.data.lists.exception.NoSuchRecordSetException;

	/**
	* Returns the number of d d l record sets where groupId = &#63; and recordSetKey = &#63;.
	*
	* @param groupId the group ID
	* @param recordSetKey the record set key
	* @return the number of matching d d l record sets
	*/
	public int countByG_R(long groupId, java.lang.String recordSetKey);

	/**
	* Caches the d d l record set in the entity cache if it is enabled.
	*
	* @param ddlRecordSet the d d l record set
	*/
	public void cacheResult(DDLRecordSet ddlRecordSet);

	/**
	* Caches the d d l record sets in the entity cache if it is enabled.
	*
	* @param ddlRecordSets the d d l record sets
	*/
	public void cacheResult(java.util.List<DDLRecordSet> ddlRecordSets);

	/**
	* Creates a new d d l record set with the primary key. Does not add the d d l record set to the database.
	*
	* @param recordSetId the primary key for the new d d l record set
	* @return the new d d l record set
	*/
	public DDLRecordSet create(long recordSetId);

	/**
	* Removes the d d l record set with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param recordSetId the primary key of the d d l record set
	* @return the d d l record set that was removed
	* @throws NoSuchRecordSetException if a d d l record set with the primary key could not be found
	*/
	public DDLRecordSet remove(long recordSetId)
		throws com.liferay.dynamic.data.lists.exception.NoSuchRecordSetException;

	public DDLRecordSet updateImpl(DDLRecordSet ddlRecordSet);

	/**
	* Returns the d d l record set with the primary key or throws a {@link NoSuchRecordSetException} if it could not be found.
	*
	* @param recordSetId the primary key of the d d l record set
	* @return the d d l record set
	* @throws NoSuchRecordSetException if a d d l record set with the primary key could not be found
	*/
	public DDLRecordSet findByPrimaryKey(long recordSetId)
		throws com.liferay.dynamic.data.lists.exception.NoSuchRecordSetException;

	/**
	* Returns the d d l record set with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param recordSetId the primary key of the d d l record set
	* @return the d d l record set, or <code>null</code> if a d d l record set with the primary key could not be found
	*/
	public DDLRecordSet fetchByPrimaryKey(long recordSetId);

	@Override
	public java.util.Map<java.io.Serializable, DDLRecordSet> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the d d l record sets.
	*
	* @return the d d l record sets
	*/
	public java.util.List<DDLRecordSet> findAll();

	/**
	* Returns a range of all the d d l record sets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DDLRecordSetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of d d l record sets
	* @param end the upper bound of the range of d d l record sets (not inclusive)
	* @return the range of d d l record sets
	*/
	public java.util.List<DDLRecordSet> findAll(int start, int end);

	/**
	* Returns an ordered range of all the d d l record sets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DDLRecordSetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of d d l record sets
	* @param end the upper bound of the range of d d l record sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of d d l record sets
	*/
	public java.util.List<DDLRecordSet> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDLRecordSet> orderByComparator);

	/**
	* Returns an ordered range of all the d d l record sets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DDLRecordSetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of d d l record sets
	* @param end the upper bound of the range of d d l record sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of d d l record sets
	*/
	public java.util.List<DDLRecordSet> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDLRecordSet> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the d d l record sets from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of d d l record sets.
	*
	* @return the number of d d l record sets
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}