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

package com.liferay.portal.resiliency.spi.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.resiliency.spi.exception.NoSuchDefinitionException;
import com.liferay.portal.resiliency.spi.model.SPIDefinition;

/**
 * The persistence interface for the s p i definition service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Michael C. Han
 * @see com.liferay.portal.resiliency.spi.service.persistence.impl.SPIDefinitionPersistenceImpl
 * @see SPIDefinitionUtil
 * @generated
 */
@ProviderType
public interface SPIDefinitionPersistence extends BasePersistence<SPIDefinition> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SPIDefinitionUtil} to access the s p i definition persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the s p i definitions where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching s p i definitions
	*/
	public java.util.List<SPIDefinition> findByCompanyId(long companyId);

	/**
	* Returns a range of all the s p i definitions where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SPIDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of s p i definitions
	* @param end the upper bound of the range of s p i definitions (not inclusive)
	* @return the range of matching s p i definitions
	*/
	public java.util.List<SPIDefinition> findByCompanyId(long companyId,
		int start, int end);

	/**
	* Returns an ordered range of all the s p i definitions where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SPIDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of s p i definitions
	* @param end the upper bound of the range of s p i definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching s p i definitions
	*/
	public java.util.List<SPIDefinition> findByCompanyId(long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SPIDefinition> orderByComparator);

	/**
	* Returns an ordered range of all the s p i definitions where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SPIDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of s p i definitions
	* @param end the upper bound of the range of s p i definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching s p i definitions
	*/
	public java.util.List<SPIDefinition> findByCompanyId(long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SPIDefinition> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first s p i definition in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching s p i definition
	* @throws NoSuchDefinitionException if a matching s p i definition could not be found
	*/
	public SPIDefinition findByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<SPIDefinition> orderByComparator)
		throws NoSuchDefinitionException;

	/**
	* Returns the first s p i definition in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching s p i definition, or <code>null</code> if a matching s p i definition could not be found
	*/
	public SPIDefinition fetchByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<SPIDefinition> orderByComparator);

	/**
	* Returns the last s p i definition in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching s p i definition
	* @throws NoSuchDefinitionException if a matching s p i definition could not be found
	*/
	public SPIDefinition findByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<SPIDefinition> orderByComparator)
		throws NoSuchDefinitionException;

	/**
	* Returns the last s p i definition in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching s p i definition, or <code>null</code> if a matching s p i definition could not be found
	*/
	public SPIDefinition fetchByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<SPIDefinition> orderByComparator);

	/**
	* Returns the s p i definitions before and after the current s p i definition in the ordered set where companyId = &#63;.
	*
	* @param spiDefinitionId the primary key of the current s p i definition
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next s p i definition
	* @throws NoSuchDefinitionException if a s p i definition with the primary key could not be found
	*/
	public SPIDefinition[] findByCompanyId_PrevAndNext(long spiDefinitionId,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<SPIDefinition> orderByComparator)
		throws NoSuchDefinitionException;

	/**
	* Returns all the s p i definitions that the user has permission to view where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching s p i definitions that the user has permission to view
	*/
	public java.util.List<SPIDefinition> filterFindByCompanyId(long companyId);

	/**
	* Returns a range of all the s p i definitions that the user has permission to view where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SPIDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of s p i definitions
	* @param end the upper bound of the range of s p i definitions (not inclusive)
	* @return the range of matching s p i definitions that the user has permission to view
	*/
	public java.util.List<SPIDefinition> filterFindByCompanyId(long companyId,
		int start, int end);

	/**
	* Returns an ordered range of all the s p i definitions that the user has permissions to view where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SPIDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of s p i definitions
	* @param end the upper bound of the range of s p i definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching s p i definitions that the user has permission to view
	*/
	public java.util.List<SPIDefinition> filterFindByCompanyId(long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SPIDefinition> orderByComparator);

	/**
	* Returns the s p i definitions before and after the current s p i definition in the ordered set of s p i definitions that the user has permission to view where companyId = &#63;.
	*
	* @param spiDefinitionId the primary key of the current s p i definition
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next s p i definition
	* @throws NoSuchDefinitionException if a s p i definition with the primary key could not be found
	*/
	public SPIDefinition[] filterFindByCompanyId_PrevAndNext(
		long spiDefinitionId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<SPIDefinition> orderByComparator)
		throws NoSuchDefinitionException;

	/**
	* Removes all the s p i definitions where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	*/
	public void removeByCompanyId(long companyId);

	/**
	* Returns the number of s p i definitions where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching s p i definitions
	*/
	public int countByCompanyId(long companyId);

	/**
	* Returns the number of s p i definitions that the user has permission to view where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching s p i definitions that the user has permission to view
	*/
	public int filterCountByCompanyId(long companyId);

	/**
	* Returns the s p i definition where companyId = &#63; and name = &#63; or throws a {@link NoSuchDefinitionException} if it could not be found.
	*
	* @param companyId the company ID
	* @param name the name
	* @return the matching s p i definition
	* @throws NoSuchDefinitionException if a matching s p i definition could not be found
	*/
	public SPIDefinition findByC_N(long companyId, java.lang.String name)
		throws NoSuchDefinitionException;

	/**
	* Returns the s p i definition where companyId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param companyId the company ID
	* @param name the name
	* @return the matching s p i definition, or <code>null</code> if a matching s p i definition could not be found
	*/
	public SPIDefinition fetchByC_N(long companyId, java.lang.String name);

	/**
	* Returns the s p i definition where companyId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param companyId the company ID
	* @param name the name
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching s p i definition, or <code>null</code> if a matching s p i definition could not be found
	*/
	public SPIDefinition fetchByC_N(long companyId, java.lang.String name,
		boolean retrieveFromCache);

	/**
	* Removes the s p i definition where companyId = &#63; and name = &#63; from the database.
	*
	* @param companyId the company ID
	* @param name the name
	* @return the s p i definition that was removed
	*/
	public SPIDefinition removeByC_N(long companyId, java.lang.String name)
		throws NoSuchDefinitionException;

	/**
	* Returns the number of s p i definitions where companyId = &#63; and name = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @return the number of matching s p i definitions
	*/
	public int countByC_N(long companyId, java.lang.String name);

	/**
	* Returns all the s p i definitions where companyId = &#63; and status = &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @return the matching s p i definitions
	*/
	public java.util.List<SPIDefinition> findByC_S(long companyId, int status);

	/**
	* Returns a range of all the s p i definitions where companyId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SPIDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param status the status
	* @param start the lower bound of the range of s p i definitions
	* @param end the upper bound of the range of s p i definitions (not inclusive)
	* @return the range of matching s p i definitions
	*/
	public java.util.List<SPIDefinition> findByC_S(long companyId, int status,
		int start, int end);

	/**
	* Returns an ordered range of all the s p i definitions where companyId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SPIDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param status the status
	* @param start the lower bound of the range of s p i definitions
	* @param end the upper bound of the range of s p i definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching s p i definitions
	*/
	public java.util.List<SPIDefinition> findByC_S(long companyId, int status,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SPIDefinition> orderByComparator);

	/**
	* Returns an ordered range of all the s p i definitions where companyId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SPIDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param status the status
	* @param start the lower bound of the range of s p i definitions
	* @param end the upper bound of the range of s p i definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching s p i definitions
	*/
	public java.util.List<SPIDefinition> findByC_S(long companyId, int status,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SPIDefinition> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first s p i definition in the ordered set where companyId = &#63; and status = &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching s p i definition
	* @throws NoSuchDefinitionException if a matching s p i definition could not be found
	*/
	public SPIDefinition findByC_S_First(long companyId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<SPIDefinition> orderByComparator)
		throws NoSuchDefinitionException;

	/**
	* Returns the first s p i definition in the ordered set where companyId = &#63; and status = &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching s p i definition, or <code>null</code> if a matching s p i definition could not be found
	*/
	public SPIDefinition fetchByC_S_First(long companyId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<SPIDefinition> orderByComparator);

	/**
	* Returns the last s p i definition in the ordered set where companyId = &#63; and status = &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching s p i definition
	* @throws NoSuchDefinitionException if a matching s p i definition could not be found
	*/
	public SPIDefinition findByC_S_Last(long companyId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<SPIDefinition> orderByComparator)
		throws NoSuchDefinitionException;

	/**
	* Returns the last s p i definition in the ordered set where companyId = &#63; and status = &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching s p i definition, or <code>null</code> if a matching s p i definition could not be found
	*/
	public SPIDefinition fetchByC_S_Last(long companyId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<SPIDefinition> orderByComparator);

	/**
	* Returns the s p i definitions before and after the current s p i definition in the ordered set where companyId = &#63; and status = &#63;.
	*
	* @param spiDefinitionId the primary key of the current s p i definition
	* @param companyId the company ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next s p i definition
	* @throws NoSuchDefinitionException if a s p i definition with the primary key could not be found
	*/
	public SPIDefinition[] findByC_S_PrevAndNext(long spiDefinitionId,
		long companyId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<SPIDefinition> orderByComparator)
		throws NoSuchDefinitionException;

	/**
	* Returns all the s p i definitions that the user has permission to view where companyId = &#63; and status = &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @return the matching s p i definitions that the user has permission to view
	*/
	public java.util.List<SPIDefinition> filterFindByC_S(long companyId,
		int status);

	/**
	* Returns a range of all the s p i definitions that the user has permission to view where companyId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SPIDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param status the status
	* @param start the lower bound of the range of s p i definitions
	* @param end the upper bound of the range of s p i definitions (not inclusive)
	* @return the range of matching s p i definitions that the user has permission to view
	*/
	public java.util.List<SPIDefinition> filterFindByC_S(long companyId,
		int status, int start, int end);

	/**
	* Returns an ordered range of all the s p i definitions that the user has permissions to view where companyId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SPIDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param status the status
	* @param start the lower bound of the range of s p i definitions
	* @param end the upper bound of the range of s p i definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching s p i definitions that the user has permission to view
	*/
	public java.util.List<SPIDefinition> filterFindByC_S(long companyId,
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SPIDefinition> orderByComparator);

	/**
	* Returns the s p i definitions before and after the current s p i definition in the ordered set of s p i definitions that the user has permission to view where companyId = &#63; and status = &#63;.
	*
	* @param spiDefinitionId the primary key of the current s p i definition
	* @param companyId the company ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next s p i definition
	* @throws NoSuchDefinitionException if a s p i definition with the primary key could not be found
	*/
	public SPIDefinition[] filterFindByC_S_PrevAndNext(long spiDefinitionId,
		long companyId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<SPIDefinition> orderByComparator)
		throws NoSuchDefinitionException;

	/**
	* Returns all the s p i definitions that the user has permission to view where companyId = &#63; and status = any &#63;.
	*
	* @param companyId the company ID
	* @param statuses the statuses
	* @return the matching s p i definitions that the user has permission to view
	*/
	public java.util.List<SPIDefinition> filterFindByC_S(long companyId,
		int[] statuses);

	/**
	* Returns a range of all the s p i definitions that the user has permission to view where companyId = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SPIDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param statuses the statuses
	* @param start the lower bound of the range of s p i definitions
	* @param end the upper bound of the range of s p i definitions (not inclusive)
	* @return the range of matching s p i definitions that the user has permission to view
	*/
	public java.util.List<SPIDefinition> filterFindByC_S(long companyId,
		int[] statuses, int start, int end);

	/**
	* Returns an ordered range of all the s p i definitions that the user has permission to view where companyId = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SPIDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param statuses the statuses
	* @param start the lower bound of the range of s p i definitions
	* @param end the upper bound of the range of s p i definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching s p i definitions that the user has permission to view
	*/
	public java.util.List<SPIDefinition> filterFindByC_S(long companyId,
		int[] statuses, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SPIDefinition> orderByComparator);

	/**
	* Returns all the s p i definitions where companyId = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SPIDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param statuses the statuses
	* @return the matching s p i definitions
	*/
	public java.util.List<SPIDefinition> findByC_S(long companyId,
		int[] statuses);

	/**
	* Returns a range of all the s p i definitions where companyId = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SPIDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param statuses the statuses
	* @param start the lower bound of the range of s p i definitions
	* @param end the upper bound of the range of s p i definitions (not inclusive)
	* @return the range of matching s p i definitions
	*/
	public java.util.List<SPIDefinition> findByC_S(long companyId,
		int[] statuses, int start, int end);

	/**
	* Returns an ordered range of all the s p i definitions where companyId = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SPIDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param statuses the statuses
	* @param start the lower bound of the range of s p i definitions
	* @param end the upper bound of the range of s p i definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching s p i definitions
	*/
	public java.util.List<SPIDefinition> findByC_S(long companyId,
		int[] statuses, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SPIDefinition> orderByComparator);

	/**
	* Returns an ordered range of all the s p i definitions where companyId = &#63; and status = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SPIDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param status the status
	* @param start the lower bound of the range of s p i definitions
	* @param end the upper bound of the range of s p i definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching s p i definitions
	*/
	public java.util.List<SPIDefinition> findByC_S(long companyId,
		int[] statuses, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SPIDefinition> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the s p i definitions where companyId = &#63; and status = &#63; from the database.
	*
	* @param companyId the company ID
	* @param status the status
	*/
	public void removeByC_S(long companyId, int status);

	/**
	* Returns the number of s p i definitions where companyId = &#63; and status = &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @return the number of matching s p i definitions
	*/
	public int countByC_S(long companyId, int status);

	/**
	* Returns the number of s p i definitions where companyId = &#63; and status = any &#63;.
	*
	* @param companyId the company ID
	* @param statuses the statuses
	* @return the number of matching s p i definitions
	*/
	public int countByC_S(long companyId, int[] statuses);

	/**
	* Returns the number of s p i definitions that the user has permission to view where companyId = &#63; and status = &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @return the number of matching s p i definitions that the user has permission to view
	*/
	public int filterCountByC_S(long companyId, int status);

	/**
	* Returns the number of s p i definitions that the user has permission to view where companyId = &#63; and status = any &#63;.
	*
	* @param companyId the company ID
	* @param statuses the statuses
	* @return the number of matching s p i definitions that the user has permission to view
	*/
	public int filterCountByC_S(long companyId, int[] statuses);

	/**
	* Returns the s p i definition where connectorAddress = &#63; and connectorPort = &#63; or throws a {@link NoSuchDefinitionException} if it could not be found.
	*
	* @param connectorAddress the connector address
	* @param connectorPort the connector port
	* @return the matching s p i definition
	* @throws NoSuchDefinitionException if a matching s p i definition could not be found
	*/
	public SPIDefinition findByCA_CP(java.lang.String connectorAddress,
		int connectorPort) throws NoSuchDefinitionException;

	/**
	* Returns the s p i definition where connectorAddress = &#63; and connectorPort = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param connectorAddress the connector address
	* @param connectorPort the connector port
	* @return the matching s p i definition, or <code>null</code> if a matching s p i definition could not be found
	*/
	public SPIDefinition fetchByCA_CP(java.lang.String connectorAddress,
		int connectorPort);

	/**
	* Returns the s p i definition where connectorAddress = &#63; and connectorPort = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param connectorAddress the connector address
	* @param connectorPort the connector port
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching s p i definition, or <code>null</code> if a matching s p i definition could not be found
	*/
	public SPIDefinition fetchByCA_CP(java.lang.String connectorAddress,
		int connectorPort, boolean retrieveFromCache);

	/**
	* Removes the s p i definition where connectorAddress = &#63; and connectorPort = &#63; from the database.
	*
	* @param connectorAddress the connector address
	* @param connectorPort the connector port
	* @return the s p i definition that was removed
	*/
	public SPIDefinition removeByCA_CP(java.lang.String connectorAddress,
		int connectorPort) throws NoSuchDefinitionException;

	/**
	* Returns the number of s p i definitions where connectorAddress = &#63; and connectorPort = &#63;.
	*
	* @param connectorAddress the connector address
	* @param connectorPort the connector port
	* @return the number of matching s p i definitions
	*/
	public int countByCA_CP(java.lang.String connectorAddress, int connectorPort);

	/**
	* Caches the s p i definition in the entity cache if it is enabled.
	*
	* @param spiDefinition the s p i definition
	*/
	public void cacheResult(SPIDefinition spiDefinition);

	/**
	* Caches the s p i definitions in the entity cache if it is enabled.
	*
	* @param spiDefinitions the s p i definitions
	*/
	public void cacheResult(java.util.List<SPIDefinition> spiDefinitions);

	/**
	* Creates a new s p i definition with the primary key. Does not add the s p i definition to the database.
	*
	* @param spiDefinitionId the primary key for the new s p i definition
	* @return the new s p i definition
	*/
	public SPIDefinition create(long spiDefinitionId);

	/**
	* Removes the s p i definition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param spiDefinitionId the primary key of the s p i definition
	* @return the s p i definition that was removed
	* @throws NoSuchDefinitionException if a s p i definition with the primary key could not be found
	*/
	public SPIDefinition remove(long spiDefinitionId)
		throws NoSuchDefinitionException;

	public SPIDefinition updateImpl(SPIDefinition spiDefinition);

	/**
	* Returns the s p i definition with the primary key or throws a {@link NoSuchDefinitionException} if it could not be found.
	*
	* @param spiDefinitionId the primary key of the s p i definition
	* @return the s p i definition
	* @throws NoSuchDefinitionException if a s p i definition with the primary key could not be found
	*/
	public SPIDefinition findByPrimaryKey(long spiDefinitionId)
		throws NoSuchDefinitionException;

	/**
	* Returns the s p i definition with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param spiDefinitionId the primary key of the s p i definition
	* @return the s p i definition, or <code>null</code> if a s p i definition with the primary key could not be found
	*/
	public SPIDefinition fetchByPrimaryKey(long spiDefinitionId);

	@Override
	public java.util.Map<java.io.Serializable, SPIDefinition> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the s p i definitions.
	*
	* @return the s p i definitions
	*/
	public java.util.List<SPIDefinition> findAll();

	/**
	* Returns a range of all the s p i definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SPIDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of s p i definitions
	* @param end the upper bound of the range of s p i definitions (not inclusive)
	* @return the range of s p i definitions
	*/
	public java.util.List<SPIDefinition> findAll(int start, int end);

	/**
	* Returns an ordered range of all the s p i definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SPIDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of s p i definitions
	* @param end the upper bound of the range of s p i definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of s p i definitions
	*/
	public java.util.List<SPIDefinition> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SPIDefinition> orderByComparator);

	/**
	* Returns an ordered range of all the s p i definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SPIDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of s p i definitions
	* @param end the upper bound of the range of s p i definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of s p i definitions
	*/
	public java.util.List<SPIDefinition> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SPIDefinition> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the s p i definitions from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of s p i definitions.
	*
	* @return the number of s p i definitions
	*/
	public int countAll();
}