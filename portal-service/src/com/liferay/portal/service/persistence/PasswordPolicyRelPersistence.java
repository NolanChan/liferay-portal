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

package com.liferay.portal.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.model.PasswordPolicyRel;

/**
 * The persistence interface for the password policy rel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PasswordPolicyRelPersistenceImpl
 * @see PasswordPolicyRelUtil
 * @generated
 */
@ProviderType
public interface PasswordPolicyRelPersistence extends BasePersistence<PasswordPolicyRel> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PasswordPolicyRelUtil} to access the password policy rel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the password policy rels where passwordPolicyId = &#63;.
	*
	* @param passwordPolicyId the password policy ID
	* @return the matching password policy rels
	*/
	public java.util.List<com.liferay.portal.model.PasswordPolicyRel> findByPasswordPolicyId(
		long passwordPolicyId);

	/**
	* Returns a range of all the password policy rels where passwordPolicyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.PasswordPolicyRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param passwordPolicyId the password policy ID
	* @param start the lower bound of the range of password policy rels
	* @param end the upper bound of the range of password policy rels (not inclusive)
	* @return the range of matching password policy rels
	*/
	public java.util.List<com.liferay.portal.model.PasswordPolicyRel> findByPasswordPolicyId(
		long passwordPolicyId, int start, int end);

	/**
	* Returns an ordered range of all the password policy rels where passwordPolicyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.PasswordPolicyRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param passwordPolicyId the password policy ID
	* @param start the lower bound of the range of password policy rels
	* @param end the upper bound of the range of password policy rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching password policy rels
	*/
	public java.util.List<com.liferay.portal.model.PasswordPolicyRel> findByPasswordPolicyId(
		long passwordPolicyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.model.PasswordPolicyRel> orderByComparator);

	/**
	* Returns the first password policy rel in the ordered set where passwordPolicyId = &#63;.
	*
	* @param passwordPolicyId the password policy ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching password policy rel
	* @throws com.liferay.portal.NoSuchPasswordPolicyRelException if a matching password policy rel could not be found
	*/
	public com.liferay.portal.model.PasswordPolicyRel findByPasswordPolicyId_First(
		long passwordPolicyId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.model.PasswordPolicyRel> orderByComparator)
		throws com.liferay.portal.NoSuchPasswordPolicyRelException;

	/**
	* Returns the first password policy rel in the ordered set where passwordPolicyId = &#63;.
	*
	* @param passwordPolicyId the password policy ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching password policy rel, or <code>null</code> if a matching password policy rel could not be found
	*/
	public com.liferay.portal.model.PasswordPolicyRel fetchByPasswordPolicyId_First(
		long passwordPolicyId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.model.PasswordPolicyRel> orderByComparator);

	/**
	* Returns the last password policy rel in the ordered set where passwordPolicyId = &#63;.
	*
	* @param passwordPolicyId the password policy ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching password policy rel
	* @throws com.liferay.portal.NoSuchPasswordPolicyRelException if a matching password policy rel could not be found
	*/
	public com.liferay.portal.model.PasswordPolicyRel findByPasswordPolicyId_Last(
		long passwordPolicyId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.model.PasswordPolicyRel> orderByComparator)
		throws com.liferay.portal.NoSuchPasswordPolicyRelException;

	/**
	* Returns the last password policy rel in the ordered set where passwordPolicyId = &#63;.
	*
	* @param passwordPolicyId the password policy ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching password policy rel, or <code>null</code> if a matching password policy rel could not be found
	*/
	public com.liferay.portal.model.PasswordPolicyRel fetchByPasswordPolicyId_Last(
		long passwordPolicyId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.model.PasswordPolicyRel> orderByComparator);

	/**
	* Returns the password policy rels before and after the current password policy rel in the ordered set where passwordPolicyId = &#63;.
	*
	* @param passwordPolicyRelId the primary key of the current password policy rel
	* @param passwordPolicyId the password policy ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next password policy rel
	* @throws com.liferay.portal.NoSuchPasswordPolicyRelException if a password policy rel with the primary key could not be found
	*/
	public com.liferay.portal.model.PasswordPolicyRel[] findByPasswordPolicyId_PrevAndNext(
		long passwordPolicyRelId, long passwordPolicyId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.model.PasswordPolicyRel> orderByComparator)
		throws com.liferay.portal.NoSuchPasswordPolicyRelException;

	/**
	* Removes all the password policy rels where passwordPolicyId = &#63; from the database.
	*
	* @param passwordPolicyId the password policy ID
	*/
	public void removeByPasswordPolicyId(long passwordPolicyId);

	/**
	* Returns the number of password policy rels where passwordPolicyId = &#63;.
	*
	* @param passwordPolicyId the password policy ID
	* @return the number of matching password policy rels
	*/
	public int countByPasswordPolicyId(long passwordPolicyId);

	/**
	* Returns the password policy rel where classNameId = &#63; and classPK = &#63; or throws a {@link com.liferay.portal.NoSuchPasswordPolicyRelException} if it could not be found.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching password policy rel
	* @throws com.liferay.portal.NoSuchPasswordPolicyRelException if a matching password policy rel could not be found
	*/
	public com.liferay.portal.model.PasswordPolicyRel findByC_C(
		long classNameId, long classPK)
		throws com.liferay.portal.NoSuchPasswordPolicyRelException;

	/**
	* Returns the password policy rel where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching password policy rel, or <code>null</code> if a matching password policy rel could not be found
	*/
	public com.liferay.portal.model.PasswordPolicyRel fetchByC_C(
		long classNameId, long classPK);

	/**
	* Returns the password policy rel where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching password policy rel, or <code>null</code> if a matching password policy rel could not be found
	*/
	public com.liferay.portal.model.PasswordPolicyRel fetchByC_C(
		long classNameId, long classPK, boolean retrieveFromCache);

	/**
	* Removes the password policy rel where classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the password policy rel that was removed
	*/
	public com.liferay.portal.model.PasswordPolicyRel removeByC_C(
		long classNameId, long classPK)
		throws com.liferay.portal.NoSuchPasswordPolicyRelException;

	/**
	* Returns the number of password policy rels where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the number of matching password policy rels
	*/
	public int countByC_C(long classNameId, long classPK);

	/**
	* Caches the password policy rel in the entity cache if it is enabled.
	*
	* @param passwordPolicyRel the password policy rel
	*/
	public void cacheResult(
		com.liferay.portal.model.PasswordPolicyRel passwordPolicyRel);

	/**
	* Caches the password policy rels in the entity cache if it is enabled.
	*
	* @param passwordPolicyRels the password policy rels
	*/
	public void cacheResult(
		java.util.List<com.liferay.portal.model.PasswordPolicyRel> passwordPolicyRels);

	/**
	* Creates a new password policy rel with the primary key. Does not add the password policy rel to the database.
	*
	* @param passwordPolicyRelId the primary key for the new password policy rel
	* @return the new password policy rel
	*/
	public com.liferay.portal.model.PasswordPolicyRel create(
		long passwordPolicyRelId);

	/**
	* Removes the password policy rel with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param passwordPolicyRelId the primary key of the password policy rel
	* @return the password policy rel that was removed
	* @throws com.liferay.portal.NoSuchPasswordPolicyRelException if a password policy rel with the primary key could not be found
	*/
	public com.liferay.portal.model.PasswordPolicyRel remove(
		long passwordPolicyRelId)
		throws com.liferay.portal.NoSuchPasswordPolicyRelException;

	public com.liferay.portal.model.PasswordPolicyRel updateImpl(
		com.liferay.portal.model.PasswordPolicyRel passwordPolicyRel);

	/**
	* Returns the password policy rel with the primary key or throws a {@link com.liferay.portal.NoSuchPasswordPolicyRelException} if it could not be found.
	*
	* @param passwordPolicyRelId the primary key of the password policy rel
	* @return the password policy rel
	* @throws com.liferay.portal.NoSuchPasswordPolicyRelException if a password policy rel with the primary key could not be found
	*/
	public com.liferay.portal.model.PasswordPolicyRel findByPrimaryKey(
		long passwordPolicyRelId)
		throws com.liferay.portal.NoSuchPasswordPolicyRelException;

	/**
	* Returns the password policy rel with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param passwordPolicyRelId the primary key of the password policy rel
	* @return the password policy rel, or <code>null</code> if a password policy rel with the primary key could not be found
	*/
	public com.liferay.portal.model.PasswordPolicyRel fetchByPrimaryKey(
		long passwordPolicyRelId);

	@Override
	public java.util.Map<java.io.Serializable, com.liferay.portal.model.PasswordPolicyRel> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the password policy rels.
	*
	* @return the password policy rels
	*/
	public java.util.List<com.liferay.portal.model.PasswordPolicyRel> findAll();

	/**
	* Returns a range of all the password policy rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.PasswordPolicyRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of password policy rels
	* @param end the upper bound of the range of password policy rels (not inclusive)
	* @return the range of password policy rels
	*/
	public java.util.List<com.liferay.portal.model.PasswordPolicyRel> findAll(
		int start, int end);

	/**
	* Returns an ordered range of all the password policy rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.PasswordPolicyRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of password policy rels
	* @param end the upper bound of the range of password policy rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of password policy rels
	*/
	public java.util.List<com.liferay.portal.model.PasswordPolicyRel> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.model.PasswordPolicyRel> orderByComparator);

	/**
	* Removes all the password policy rels from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of password policy rels.
	*
	* @return the number of password policy rels
	*/
	public int countAll();
}