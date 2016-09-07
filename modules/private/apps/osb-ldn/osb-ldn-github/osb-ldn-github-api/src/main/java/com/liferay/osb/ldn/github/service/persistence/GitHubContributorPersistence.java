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

package com.liferay.osb.ldn.github.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.ldn.github.exception.NoSuchGitHubContributorException;
import com.liferay.osb.ldn.github.model.GitHubContributor;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the git hub contributor service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Howie Chou
 * @see com.liferay.osb.ldn.github.service.persistence.impl.GitHubContributorPersistenceImpl
 * @see GitHubContributorUtil
 * @generated
 */
@ProviderType
public interface GitHubContributorPersistence extends BasePersistence<GitHubContributor> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link GitHubContributorUtil} to access the git hub contributor persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the git hub contributors where gitHubRepositoryId = &#63;.
	*
	* @param gitHubRepositoryId the git hub repository ID
	* @return the matching git hub contributors
	*/
	public java.util.List<GitHubContributor> findByGitHubRepositoryId(
		long gitHubRepositoryId);

	/**
	* Returns a range of all the git hub contributors where gitHubRepositoryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GitHubContributorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param gitHubRepositoryId the git hub repository ID
	* @param start the lower bound of the range of git hub contributors
	* @param end the upper bound of the range of git hub contributors (not inclusive)
	* @return the range of matching git hub contributors
	*/
	public java.util.List<GitHubContributor> findByGitHubRepositoryId(
		long gitHubRepositoryId, int start, int end);

	/**
	* Returns an ordered range of all the git hub contributors where gitHubRepositoryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GitHubContributorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param gitHubRepositoryId the git hub repository ID
	* @param start the lower bound of the range of git hub contributors
	* @param end the upper bound of the range of git hub contributors (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching git hub contributors
	*/
	public java.util.List<GitHubContributor> findByGitHubRepositoryId(
		long gitHubRepositoryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GitHubContributor> orderByComparator);

	/**
	* Returns an ordered range of all the git hub contributors where gitHubRepositoryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GitHubContributorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param gitHubRepositoryId the git hub repository ID
	* @param start the lower bound of the range of git hub contributors
	* @param end the upper bound of the range of git hub contributors (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching git hub contributors
	*/
	public java.util.List<GitHubContributor> findByGitHubRepositoryId(
		long gitHubRepositoryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GitHubContributor> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first git hub contributor in the ordered set where gitHubRepositoryId = &#63;.
	*
	* @param gitHubRepositoryId the git hub repository ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching git hub contributor
	* @throws NoSuchGitHubContributorException if a matching git hub contributor could not be found
	*/
	public GitHubContributor findByGitHubRepositoryId_First(
		long gitHubRepositoryId,
		com.liferay.portal.kernel.util.OrderByComparator<GitHubContributor> orderByComparator)
		throws NoSuchGitHubContributorException;

	/**
	* Returns the first git hub contributor in the ordered set where gitHubRepositoryId = &#63;.
	*
	* @param gitHubRepositoryId the git hub repository ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching git hub contributor, or <code>null</code> if a matching git hub contributor could not be found
	*/
	public GitHubContributor fetchByGitHubRepositoryId_First(
		long gitHubRepositoryId,
		com.liferay.portal.kernel.util.OrderByComparator<GitHubContributor> orderByComparator);

	/**
	* Returns the last git hub contributor in the ordered set where gitHubRepositoryId = &#63;.
	*
	* @param gitHubRepositoryId the git hub repository ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching git hub contributor
	* @throws NoSuchGitHubContributorException if a matching git hub contributor could not be found
	*/
	public GitHubContributor findByGitHubRepositoryId_Last(
		long gitHubRepositoryId,
		com.liferay.portal.kernel.util.OrderByComparator<GitHubContributor> orderByComparator)
		throws NoSuchGitHubContributorException;

	/**
	* Returns the last git hub contributor in the ordered set where gitHubRepositoryId = &#63;.
	*
	* @param gitHubRepositoryId the git hub repository ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching git hub contributor, or <code>null</code> if a matching git hub contributor could not be found
	*/
	public GitHubContributor fetchByGitHubRepositoryId_Last(
		long gitHubRepositoryId,
		com.liferay.portal.kernel.util.OrderByComparator<GitHubContributor> orderByComparator);

	/**
	* Returns the git hub contributors before and after the current git hub contributor in the ordered set where gitHubRepositoryId = &#63;.
	*
	* @param gitHubContributorId the primary key of the current git hub contributor
	* @param gitHubRepositoryId the git hub repository ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next git hub contributor
	* @throws NoSuchGitHubContributorException if a git hub contributor with the primary key could not be found
	*/
	public GitHubContributor[] findByGitHubRepositoryId_PrevAndNext(
		long gitHubContributorId, long gitHubRepositoryId,
		com.liferay.portal.kernel.util.OrderByComparator<GitHubContributor> orderByComparator)
		throws NoSuchGitHubContributorException;

	/**
	* Removes all the git hub contributors where gitHubRepositoryId = &#63; from the database.
	*
	* @param gitHubRepositoryId the git hub repository ID
	*/
	public void removeByGitHubRepositoryId(long gitHubRepositoryId);

	/**
	* Returns the number of git hub contributors where gitHubRepositoryId = &#63;.
	*
	* @param gitHubRepositoryId the git hub repository ID
	* @return the number of matching git hub contributors
	*/
	public int countByGitHubRepositoryId(long gitHubRepositoryId);

	/**
	* Caches the git hub contributor in the entity cache if it is enabled.
	*
	* @param gitHubContributor the git hub contributor
	*/
	public void cacheResult(GitHubContributor gitHubContributor);

	/**
	* Caches the git hub contributors in the entity cache if it is enabled.
	*
	* @param gitHubContributors the git hub contributors
	*/
	public void cacheResult(
		java.util.List<GitHubContributor> gitHubContributors);

	/**
	* Creates a new git hub contributor with the primary key. Does not add the git hub contributor to the database.
	*
	* @param gitHubContributorId the primary key for the new git hub contributor
	* @return the new git hub contributor
	*/
	public GitHubContributor create(long gitHubContributorId);

	/**
	* Removes the git hub contributor with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param gitHubContributorId the primary key of the git hub contributor
	* @return the git hub contributor that was removed
	* @throws NoSuchGitHubContributorException if a git hub contributor with the primary key could not be found
	*/
	public GitHubContributor remove(long gitHubContributorId)
		throws NoSuchGitHubContributorException;

	public GitHubContributor updateImpl(GitHubContributor gitHubContributor);

	/**
	* Returns the git hub contributor with the primary key or throws a {@link NoSuchGitHubContributorException} if it could not be found.
	*
	* @param gitHubContributorId the primary key of the git hub contributor
	* @return the git hub contributor
	* @throws NoSuchGitHubContributorException if a git hub contributor with the primary key could not be found
	*/
	public GitHubContributor findByPrimaryKey(long gitHubContributorId)
		throws NoSuchGitHubContributorException;

	/**
	* Returns the git hub contributor with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param gitHubContributorId the primary key of the git hub contributor
	* @return the git hub contributor, or <code>null</code> if a git hub contributor with the primary key could not be found
	*/
	public GitHubContributor fetchByPrimaryKey(long gitHubContributorId);

	@Override
	public java.util.Map<java.io.Serializable, GitHubContributor> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the git hub contributors.
	*
	* @return the git hub contributors
	*/
	public java.util.List<GitHubContributor> findAll();

	/**
	* Returns a range of all the git hub contributors.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GitHubContributorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of git hub contributors
	* @param end the upper bound of the range of git hub contributors (not inclusive)
	* @return the range of git hub contributors
	*/
	public java.util.List<GitHubContributor> findAll(int start, int end);

	/**
	* Returns an ordered range of all the git hub contributors.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GitHubContributorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of git hub contributors
	* @param end the upper bound of the range of git hub contributors (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of git hub contributors
	*/
	public java.util.List<GitHubContributor> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GitHubContributor> orderByComparator);

	/**
	* Returns an ordered range of all the git hub contributors.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GitHubContributorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of git hub contributors
	* @param end the upper bound of the range of git hub contributors (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of git hub contributors
	*/
	public java.util.List<GitHubContributor> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GitHubContributor> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the git hub contributors from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of git hub contributors.
	*
	* @return the number of git hub contributors
	*/
	public int countAll();
}