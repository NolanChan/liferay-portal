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

package com.liferay.osb.ldn.github.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.ldn.github.exception.NoSuchGitHubContributorException;
import com.liferay.osb.ldn.github.model.GitHubContributor;
import com.liferay.osb.ldn.github.model.impl.GitHubContributorImpl;
import com.liferay.osb.ldn.github.model.impl.GitHubContributorModelImpl;
import com.liferay.osb.ldn.github.service.persistence.GitHubContributorPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the git hub contributor service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Howie Chou
 * @see GitHubContributorPersistence
 * @see com.liferay.osb.ldn.github.service.persistence.GitHubContributorUtil
 * @generated
 */
@ProviderType
public class GitHubContributorPersistenceImpl extends BasePersistenceImpl<GitHubContributor>
	implements GitHubContributorPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link GitHubContributorUtil} to access the git hub contributor persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = GitHubContributorImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(GitHubContributorModelImpl.ENTITY_CACHE_ENABLED,
			GitHubContributorModelImpl.FINDER_CACHE_ENABLED,
			GitHubContributorImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(GitHubContributorModelImpl.ENTITY_CACHE_ENABLED,
			GitHubContributorModelImpl.FINDER_CACHE_ENABLED,
			GitHubContributorImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(GitHubContributorModelImpl.ENTITY_CACHE_ENABLED,
			GitHubContributorModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GITHUBREPOSITORYID =
		new FinderPath(GitHubContributorModelImpl.ENTITY_CACHE_ENABLED,
			GitHubContributorModelImpl.FINDER_CACHE_ENABLED,
			GitHubContributorImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGitHubRepositoryId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GITHUBREPOSITORYID =
		new FinderPath(GitHubContributorModelImpl.ENTITY_CACHE_ENABLED,
			GitHubContributorModelImpl.FINDER_CACHE_ENABLED,
			GitHubContributorImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByGitHubRepositoryId", new String[] { Long.class.getName() },
			GitHubContributorModelImpl.GITHUBREPOSITORYID_COLUMN_BITMASK |
			GitHubContributorModelImpl.CONTRIBUTIONS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GITHUBREPOSITORYID = new FinderPath(GitHubContributorModelImpl.ENTITY_CACHE_ENABLED,
			GitHubContributorModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByGitHubRepositoryId", new String[] { Long.class.getName() });

	/**
	 * Returns all the git hub contributors where gitHubRepositoryId = &#63;.
	 *
	 * @param gitHubRepositoryId the git hub repository ID
	 * @return the matching git hub contributors
	 */
	@Override
	public List<GitHubContributor> findByGitHubRepositoryId(
		long gitHubRepositoryId) {
		return findByGitHubRepositoryId(gitHubRepositoryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<GitHubContributor> findByGitHubRepositoryId(
		long gitHubRepositoryId, int start, int end) {
		return findByGitHubRepositoryId(gitHubRepositoryId, start, end, null);
	}

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
	@Override
	public List<GitHubContributor> findByGitHubRepositoryId(
		long gitHubRepositoryId, int start, int end,
		OrderByComparator<GitHubContributor> orderByComparator) {
		return findByGitHubRepositoryId(gitHubRepositoryId, start, end,
			orderByComparator, true);
	}

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
	@Override
	public List<GitHubContributor> findByGitHubRepositoryId(
		long gitHubRepositoryId, int start, int end,
		OrderByComparator<GitHubContributor> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GITHUBREPOSITORYID;
			finderArgs = new Object[] { gitHubRepositoryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GITHUBREPOSITORYID;
			finderArgs = new Object[] {
					gitHubRepositoryId,
					
					start, end, orderByComparator
				};
		}

		List<GitHubContributor> list = null;

		if (retrieveFromCache) {
			list = (List<GitHubContributor>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (GitHubContributor gitHubContributor : list) {
					if ((gitHubRepositoryId != gitHubContributor.getGitHubRepositoryId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_GITHUBCONTRIBUTOR_WHERE);

			query.append(_FINDER_COLUMN_GITHUBREPOSITORYID_GITHUBREPOSITORYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(GitHubContributorModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(gitHubRepositoryId);

				if (!pagination) {
					list = (List<GitHubContributor>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<GitHubContributor>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first git hub contributor in the ordered set where gitHubRepositoryId = &#63;.
	 *
	 * @param gitHubRepositoryId the git hub repository ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching git hub contributor
	 * @throws NoSuchGitHubContributorException if a matching git hub contributor could not be found
	 */
	@Override
	public GitHubContributor findByGitHubRepositoryId_First(
		long gitHubRepositoryId,
		OrderByComparator<GitHubContributor> orderByComparator)
		throws NoSuchGitHubContributorException {
		GitHubContributor gitHubContributor = fetchByGitHubRepositoryId_First(gitHubRepositoryId,
				orderByComparator);

		if (gitHubContributor != null) {
			return gitHubContributor;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("gitHubRepositoryId=");
		msg.append(gitHubRepositoryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchGitHubContributorException(msg.toString());
	}

	/**
	 * Returns the first git hub contributor in the ordered set where gitHubRepositoryId = &#63;.
	 *
	 * @param gitHubRepositoryId the git hub repository ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching git hub contributor, or <code>null</code> if a matching git hub contributor could not be found
	 */
	@Override
	public GitHubContributor fetchByGitHubRepositoryId_First(
		long gitHubRepositoryId,
		OrderByComparator<GitHubContributor> orderByComparator) {
		List<GitHubContributor> list = findByGitHubRepositoryId(gitHubRepositoryId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last git hub contributor in the ordered set where gitHubRepositoryId = &#63;.
	 *
	 * @param gitHubRepositoryId the git hub repository ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching git hub contributor
	 * @throws NoSuchGitHubContributorException if a matching git hub contributor could not be found
	 */
	@Override
	public GitHubContributor findByGitHubRepositoryId_Last(
		long gitHubRepositoryId,
		OrderByComparator<GitHubContributor> orderByComparator)
		throws NoSuchGitHubContributorException {
		GitHubContributor gitHubContributor = fetchByGitHubRepositoryId_Last(gitHubRepositoryId,
				orderByComparator);

		if (gitHubContributor != null) {
			return gitHubContributor;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("gitHubRepositoryId=");
		msg.append(gitHubRepositoryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchGitHubContributorException(msg.toString());
	}

	/**
	 * Returns the last git hub contributor in the ordered set where gitHubRepositoryId = &#63;.
	 *
	 * @param gitHubRepositoryId the git hub repository ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching git hub contributor, or <code>null</code> if a matching git hub contributor could not be found
	 */
	@Override
	public GitHubContributor fetchByGitHubRepositoryId_Last(
		long gitHubRepositoryId,
		OrderByComparator<GitHubContributor> orderByComparator) {
		int count = countByGitHubRepositoryId(gitHubRepositoryId);

		if (count == 0) {
			return null;
		}

		List<GitHubContributor> list = findByGitHubRepositoryId(gitHubRepositoryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the git hub contributors before and after the current git hub contributor in the ordered set where gitHubRepositoryId = &#63;.
	 *
	 * @param gitHubContributorId the primary key of the current git hub contributor
	 * @param gitHubRepositoryId the git hub repository ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next git hub contributor
	 * @throws NoSuchGitHubContributorException if a git hub contributor with the primary key could not be found
	 */
	@Override
	public GitHubContributor[] findByGitHubRepositoryId_PrevAndNext(
		long gitHubContributorId, long gitHubRepositoryId,
		OrderByComparator<GitHubContributor> orderByComparator)
		throws NoSuchGitHubContributorException {
		GitHubContributor gitHubContributor = findByPrimaryKey(gitHubContributorId);

		Session session = null;

		try {
			session = openSession();

			GitHubContributor[] array = new GitHubContributorImpl[3];

			array[0] = getByGitHubRepositoryId_PrevAndNext(session,
					gitHubContributor, gitHubRepositoryId, orderByComparator,
					true);

			array[1] = gitHubContributor;

			array[2] = getByGitHubRepositoryId_PrevAndNext(session,
					gitHubContributor, gitHubRepositoryId, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected GitHubContributor getByGitHubRepositoryId_PrevAndNext(
		Session session, GitHubContributor gitHubContributor,
		long gitHubRepositoryId,
		OrderByComparator<GitHubContributor> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_GITHUBCONTRIBUTOR_WHERE);

		query.append(_FINDER_COLUMN_GITHUBREPOSITORYID_GITHUBREPOSITORYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(GitHubContributorModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(gitHubRepositoryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(gitHubContributor);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<GitHubContributor> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the git hub contributors where gitHubRepositoryId = &#63; from the database.
	 *
	 * @param gitHubRepositoryId the git hub repository ID
	 */
	@Override
	public void removeByGitHubRepositoryId(long gitHubRepositoryId) {
		for (GitHubContributor gitHubContributor : findByGitHubRepositoryId(
				gitHubRepositoryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(gitHubContributor);
		}
	}

	/**
	 * Returns the number of git hub contributors where gitHubRepositoryId = &#63;.
	 *
	 * @param gitHubRepositoryId the git hub repository ID
	 * @return the number of matching git hub contributors
	 */
	@Override
	public int countByGitHubRepositoryId(long gitHubRepositoryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GITHUBREPOSITORYID;

		Object[] finderArgs = new Object[] { gitHubRepositoryId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_GITHUBCONTRIBUTOR_WHERE);

			query.append(_FINDER_COLUMN_GITHUBREPOSITORYID_GITHUBREPOSITORYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(gitHubRepositoryId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_GITHUBREPOSITORYID_GITHUBREPOSITORYID_2 =
		"gitHubContributor.gitHubRepositoryId = ?";

	public GitHubContributorPersistenceImpl() {
		setModelClass(GitHubContributor.class);
	}

	/**
	 * Caches the git hub contributor in the entity cache if it is enabled.
	 *
	 * @param gitHubContributor the git hub contributor
	 */
	@Override
	public void cacheResult(GitHubContributor gitHubContributor) {
		entityCache.putResult(GitHubContributorModelImpl.ENTITY_CACHE_ENABLED,
			GitHubContributorImpl.class, gitHubContributor.getPrimaryKey(),
			gitHubContributor);

		gitHubContributor.resetOriginalValues();
	}

	/**
	 * Caches the git hub contributors in the entity cache if it is enabled.
	 *
	 * @param gitHubContributors the git hub contributors
	 */
	@Override
	public void cacheResult(List<GitHubContributor> gitHubContributors) {
		for (GitHubContributor gitHubContributor : gitHubContributors) {
			if (entityCache.getResult(
						GitHubContributorModelImpl.ENTITY_CACHE_ENABLED,
						GitHubContributorImpl.class,
						gitHubContributor.getPrimaryKey()) == null) {
				cacheResult(gitHubContributor);
			}
			else {
				gitHubContributor.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all git hub contributors.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(GitHubContributorImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the git hub contributor.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(GitHubContributor gitHubContributor) {
		entityCache.removeResult(GitHubContributorModelImpl.ENTITY_CACHE_ENABLED,
			GitHubContributorImpl.class, gitHubContributor.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<GitHubContributor> gitHubContributors) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (GitHubContributor gitHubContributor : gitHubContributors) {
			entityCache.removeResult(GitHubContributorModelImpl.ENTITY_CACHE_ENABLED,
				GitHubContributorImpl.class, gitHubContributor.getPrimaryKey());
		}
	}

	/**
	 * Creates a new git hub contributor with the primary key. Does not add the git hub contributor to the database.
	 *
	 * @param gitHubContributorId the primary key for the new git hub contributor
	 * @return the new git hub contributor
	 */
	@Override
	public GitHubContributor create(long gitHubContributorId) {
		GitHubContributor gitHubContributor = new GitHubContributorImpl();

		gitHubContributor.setNew(true);
		gitHubContributor.setPrimaryKey(gitHubContributorId);

		gitHubContributor.setCompanyId(companyProvider.getCompanyId());

		return gitHubContributor;
	}

	/**
	 * Removes the git hub contributor with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param gitHubContributorId the primary key of the git hub contributor
	 * @return the git hub contributor that was removed
	 * @throws NoSuchGitHubContributorException if a git hub contributor with the primary key could not be found
	 */
	@Override
	public GitHubContributor remove(long gitHubContributorId)
		throws NoSuchGitHubContributorException {
		return remove((Serializable)gitHubContributorId);
	}

	/**
	 * Removes the git hub contributor with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the git hub contributor
	 * @return the git hub contributor that was removed
	 * @throws NoSuchGitHubContributorException if a git hub contributor with the primary key could not be found
	 */
	@Override
	public GitHubContributor remove(Serializable primaryKey)
		throws NoSuchGitHubContributorException {
		Session session = null;

		try {
			session = openSession();

			GitHubContributor gitHubContributor = (GitHubContributor)session.get(GitHubContributorImpl.class,
					primaryKey);

			if (gitHubContributor == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchGitHubContributorException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(gitHubContributor);
		}
		catch (NoSuchGitHubContributorException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected GitHubContributor removeImpl(GitHubContributor gitHubContributor) {
		gitHubContributor = toUnwrappedModel(gitHubContributor);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(gitHubContributor)) {
				gitHubContributor = (GitHubContributor)session.get(GitHubContributorImpl.class,
						gitHubContributor.getPrimaryKeyObj());
			}

			if (gitHubContributor != null) {
				session.delete(gitHubContributor);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (gitHubContributor != null) {
			clearCache(gitHubContributor);
		}

		return gitHubContributor;
	}

	@Override
	public GitHubContributor updateImpl(GitHubContributor gitHubContributor) {
		gitHubContributor = toUnwrappedModel(gitHubContributor);

		boolean isNew = gitHubContributor.isNew();

		GitHubContributorModelImpl gitHubContributorModelImpl = (GitHubContributorModelImpl)gitHubContributor;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (gitHubContributor.getCreateDate() == null)) {
			if (serviceContext == null) {
				gitHubContributor.setCreateDate(now);
			}
			else {
				gitHubContributor.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!gitHubContributorModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				gitHubContributor.setModifiedDate(now);
			}
			else {
				gitHubContributor.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (gitHubContributor.isNew()) {
				session.save(gitHubContributor);

				gitHubContributor.setNew(false);
			}
			else {
				gitHubContributor = (GitHubContributor)session.merge(gitHubContributor);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !GitHubContributorModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((gitHubContributorModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GITHUBREPOSITORYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						gitHubContributorModelImpl.getOriginalGitHubRepositoryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GITHUBREPOSITORYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GITHUBREPOSITORYID,
					args);

				args = new Object[] {
						gitHubContributorModelImpl.getGitHubRepositoryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GITHUBREPOSITORYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GITHUBREPOSITORYID,
					args);
			}
		}

		entityCache.putResult(GitHubContributorModelImpl.ENTITY_CACHE_ENABLED,
			GitHubContributorImpl.class, gitHubContributor.getPrimaryKey(),
			gitHubContributor, false);

		gitHubContributor.resetOriginalValues();

		return gitHubContributor;
	}

	protected GitHubContributor toUnwrappedModel(
		GitHubContributor gitHubContributor) {
		if (gitHubContributor instanceof GitHubContributorImpl) {
			return gitHubContributor;
		}

		GitHubContributorImpl gitHubContributorImpl = new GitHubContributorImpl();

		gitHubContributorImpl.setNew(gitHubContributor.isNew());
		gitHubContributorImpl.setPrimaryKey(gitHubContributor.getPrimaryKey());

		gitHubContributorImpl.setGitHubContributorId(gitHubContributor.getGitHubContributorId());
		gitHubContributorImpl.setCompanyId(gitHubContributor.getCompanyId());
		gitHubContributorImpl.setUserId(gitHubContributor.getUserId());
		gitHubContributorImpl.setUserName(gitHubContributor.getUserName());
		gitHubContributorImpl.setCreateDate(gitHubContributor.getCreateDate());
		gitHubContributorImpl.setModifiedDate(gitHubContributor.getModifiedDate());
		gitHubContributorImpl.setGitHubRepositoryId(gitHubContributor.getGitHubRepositoryId());
		gitHubContributorImpl.setName(gitHubContributor.getName());
		gitHubContributorImpl.setAvatarURL(gitHubContributor.getAvatarURL());
		gitHubContributorImpl.setContributions(gitHubContributor.getContributions());
		gitHubContributorImpl.setProfileURL(gitHubContributor.getProfileURL());

		return gitHubContributorImpl;
	}

	/**
	 * Returns the git hub contributor with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the git hub contributor
	 * @return the git hub contributor
	 * @throws NoSuchGitHubContributorException if a git hub contributor with the primary key could not be found
	 */
	@Override
	public GitHubContributor findByPrimaryKey(Serializable primaryKey)
		throws NoSuchGitHubContributorException {
		GitHubContributor gitHubContributor = fetchByPrimaryKey(primaryKey);

		if (gitHubContributor == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchGitHubContributorException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return gitHubContributor;
	}

	/**
	 * Returns the git hub contributor with the primary key or throws a {@link NoSuchGitHubContributorException} if it could not be found.
	 *
	 * @param gitHubContributorId the primary key of the git hub contributor
	 * @return the git hub contributor
	 * @throws NoSuchGitHubContributorException if a git hub contributor with the primary key could not be found
	 */
	@Override
	public GitHubContributor findByPrimaryKey(long gitHubContributorId)
		throws NoSuchGitHubContributorException {
		return findByPrimaryKey((Serializable)gitHubContributorId);
	}

	/**
	 * Returns the git hub contributor with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the git hub contributor
	 * @return the git hub contributor, or <code>null</code> if a git hub contributor with the primary key could not be found
	 */
	@Override
	public GitHubContributor fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(GitHubContributorModelImpl.ENTITY_CACHE_ENABLED,
				GitHubContributorImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		GitHubContributor gitHubContributor = (GitHubContributor)serializable;

		if (gitHubContributor == null) {
			Session session = null;

			try {
				session = openSession();

				gitHubContributor = (GitHubContributor)session.get(GitHubContributorImpl.class,
						primaryKey);

				if (gitHubContributor != null) {
					cacheResult(gitHubContributor);
				}
				else {
					entityCache.putResult(GitHubContributorModelImpl.ENTITY_CACHE_ENABLED,
						GitHubContributorImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(GitHubContributorModelImpl.ENTITY_CACHE_ENABLED,
					GitHubContributorImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return gitHubContributor;
	}

	/**
	 * Returns the git hub contributor with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param gitHubContributorId the primary key of the git hub contributor
	 * @return the git hub contributor, or <code>null</code> if a git hub contributor with the primary key could not be found
	 */
	@Override
	public GitHubContributor fetchByPrimaryKey(long gitHubContributorId) {
		return fetchByPrimaryKey((Serializable)gitHubContributorId);
	}

	@Override
	public Map<Serializable, GitHubContributor> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, GitHubContributor> map = new HashMap<Serializable, GitHubContributor>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			GitHubContributor gitHubContributor = fetchByPrimaryKey(primaryKey);

			if (gitHubContributor != null) {
				map.put(primaryKey, gitHubContributor);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(GitHubContributorModelImpl.ENTITY_CACHE_ENABLED,
					GitHubContributorImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (GitHubContributor)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_GITHUBCONTRIBUTOR_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append(String.valueOf(primaryKey));

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (GitHubContributor gitHubContributor : (List<GitHubContributor>)q.list()) {
				map.put(gitHubContributor.getPrimaryKeyObj(), gitHubContributor);

				cacheResult(gitHubContributor);

				uncachedPrimaryKeys.remove(gitHubContributor.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(GitHubContributorModelImpl.ENTITY_CACHE_ENABLED,
					GitHubContributorImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the git hub contributors.
	 *
	 * @return the git hub contributors
	 */
	@Override
	public List<GitHubContributor> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<GitHubContributor> findAll(int start, int end) {
		return findAll(start, end, null);
	}

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
	@Override
	public List<GitHubContributor> findAll(int start, int end,
		OrderByComparator<GitHubContributor> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

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
	@Override
	public List<GitHubContributor> findAll(int start, int end,
		OrderByComparator<GitHubContributor> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<GitHubContributor> list = null;

		if (retrieveFromCache) {
			list = (List<GitHubContributor>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_GITHUBCONTRIBUTOR);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_GITHUBCONTRIBUTOR;

				if (pagination) {
					sql = sql.concat(GitHubContributorModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<GitHubContributor>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<GitHubContributor>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the git hub contributors from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (GitHubContributor gitHubContributor : findAll()) {
			remove(gitHubContributor);
		}
	}

	/**
	 * Returns the number of git hub contributors.
	 *
	 * @return the number of git hub contributors
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_GITHUBCONTRIBUTOR);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return GitHubContributorModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the git hub contributor persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(GitHubContributorImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_GITHUBCONTRIBUTOR = "SELECT gitHubContributor FROM GitHubContributor gitHubContributor";
	private static final String _SQL_SELECT_GITHUBCONTRIBUTOR_WHERE_PKS_IN = "SELECT gitHubContributor FROM GitHubContributor gitHubContributor WHERE gitHubContributorId IN (";
	private static final String _SQL_SELECT_GITHUBCONTRIBUTOR_WHERE = "SELECT gitHubContributor FROM GitHubContributor gitHubContributor WHERE ";
	private static final String _SQL_COUNT_GITHUBCONTRIBUTOR = "SELECT COUNT(gitHubContributor) FROM GitHubContributor gitHubContributor";
	private static final String _SQL_COUNT_GITHUBCONTRIBUTOR_WHERE = "SELECT COUNT(gitHubContributor) FROM GitHubContributor gitHubContributor WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "gitHubContributor.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No GitHubContributor exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No GitHubContributor exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(GitHubContributorPersistenceImpl.class);
}