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

package com.liferay.osb.lcs.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.lcs.exception.NoSuchLCSProjectException;
import com.liferay.osb.lcs.model.LCSProject;
import com.liferay.osb.lcs.model.impl.LCSProjectImpl;
import com.liferay.osb.lcs.model.impl.LCSProjectModelImpl;
import com.liferay.osb.lcs.service.persistence.LCSProjectPersistence;

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
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the l c s project service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Igor Beslic
 * @see LCSProjectPersistence
 * @see com.liferay.osb.lcs.service.persistence.LCSProjectUtil
 * @generated
 */
@ProviderType
public class LCSProjectPersistenceImpl extends BasePersistenceImpl<LCSProject>
	implements LCSProjectPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link LCSProjectUtil} to access the l c s project persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = LCSProjectImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(LCSProjectModelImpl.ENTITY_CACHE_ENABLED,
			LCSProjectModelImpl.FINDER_CACHE_ENABLED, LCSProjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(LCSProjectModelImpl.ENTITY_CACHE_ENABLED,
			LCSProjectModelImpl.FINDER_CACHE_ENABLED, LCSProjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(LCSProjectModelImpl.ENTITY_CACHE_ENABLED,
			LCSProjectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_NAME = new FinderPath(LCSProjectModelImpl.ENTITY_CACHE_ENABLED,
			LCSProjectModelImpl.FINDER_CACHE_ENABLED, LCSProjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByName",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_NAME = new FinderPath(LCSProjectModelImpl.ENTITY_CACHE_ENABLED,
			LCSProjectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByName",
			new String[] { String.class.getName() });

	/**
	 * Returns all the l c s projects where name LIKE &#63;.
	 *
	 * @param name the name
	 * @return the matching l c s projects
	 */
	@Override
	public List<LCSProject> findByName(String name) {
		return findByName(name, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the l c s projects where name LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of l c s projects
	 * @param end the upper bound of the range of l c s projects (not inclusive)
	 * @return the range of matching l c s projects
	 */
	@Override
	public List<LCSProject> findByName(String name, int start, int end) {
		return findByName(name, start, end, null);
	}

	/**
	 * Returns an ordered range of all the l c s projects where name LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of l c s projects
	 * @param end the upper bound of the range of l c s projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching l c s projects
	 */
	@Override
	public List<LCSProject> findByName(String name, int start, int end,
		OrderByComparator<LCSProject> orderByComparator) {
		return findByName(name, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the l c s projects where name LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of l c s projects
	 * @param end the upper bound of the range of l c s projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching l c s projects
	 */
	@Override
	public List<LCSProject> findByName(String name, int start, int end,
		OrderByComparator<LCSProject> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_NAME;
		finderArgs = new Object[] { name, start, end, orderByComparator };

		List<LCSProject> list = null;

		if (retrieveFromCache) {
			list = (List<LCSProject>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LCSProject lcsProject : list) {
					if (!StringUtil.wildcardMatches(lcsProject.getName(), name,
								CharPool.UNDERLINE, CharPool.PERCENT,
								CharPool.BACK_SLASH, false)) {
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

			query.append(_SQL_SELECT_LCSPROJECT_WHERE);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_NAME_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_NAME_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_NAME_NAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LCSProjectModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindName) {
					qPos.add(StringUtil.toLowerCase(name));
				}

				if (!pagination) {
					list = (List<LCSProject>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSProject>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first l c s project in the ordered set where name LIKE &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s project
	 * @throws NoSuchLCSProjectException if a matching l c s project could not be found
	 */
	@Override
	public LCSProject findByName_First(String name,
		OrderByComparator<LCSProject> orderByComparator)
		throws NoSuchLCSProjectException {
		LCSProject lcsProject = fetchByName_First(name, orderByComparator);

		if (lcsProject != null) {
			return lcsProject;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("name=");
		msg.append(name);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSProjectException(msg.toString());
	}

	/**
	 * Returns the first l c s project in the ordered set where name LIKE &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s project, or <code>null</code> if a matching l c s project could not be found
	 */
	@Override
	public LCSProject fetchByName_First(String name,
		OrderByComparator<LCSProject> orderByComparator) {
		List<LCSProject> list = findByName(name, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last l c s project in the ordered set where name LIKE &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s project
	 * @throws NoSuchLCSProjectException if a matching l c s project could not be found
	 */
	@Override
	public LCSProject findByName_Last(String name,
		OrderByComparator<LCSProject> orderByComparator)
		throws NoSuchLCSProjectException {
		LCSProject lcsProject = fetchByName_Last(name, orderByComparator);

		if (lcsProject != null) {
			return lcsProject;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("name=");
		msg.append(name);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSProjectException(msg.toString());
	}

	/**
	 * Returns the last l c s project in the ordered set where name LIKE &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s project, or <code>null</code> if a matching l c s project could not be found
	 */
	@Override
	public LCSProject fetchByName_Last(String name,
		OrderByComparator<LCSProject> orderByComparator) {
		int count = countByName(name);

		if (count == 0) {
			return null;
		}

		List<LCSProject> list = findByName(name, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the l c s projects before and after the current l c s project in the ordered set where name LIKE &#63;.
	 *
	 * @param lcsProjectId the primary key of the current l c s project
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next l c s project
	 * @throws NoSuchLCSProjectException if a l c s project with the primary key could not be found
	 */
	@Override
	public LCSProject[] findByName_PrevAndNext(long lcsProjectId, String name,
		OrderByComparator<LCSProject> orderByComparator)
		throws NoSuchLCSProjectException {
		LCSProject lcsProject = findByPrimaryKey(lcsProjectId);

		Session session = null;

		try {
			session = openSession();

			LCSProject[] array = new LCSProjectImpl[3];

			array[0] = getByName_PrevAndNext(session, lcsProject, name,
					orderByComparator, true);

			array[1] = lcsProject;

			array[2] = getByName_PrevAndNext(session, lcsProject, name,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LCSProject getByName_PrevAndNext(Session session,
		LCSProject lcsProject, String name,
		OrderByComparator<LCSProject> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LCSPROJECT_WHERE);

		boolean bindName = false;

		if (name == null) {
			query.append(_FINDER_COLUMN_NAME_NAME_1);
		}
		else if (name.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_NAME_NAME_3);
		}
		else {
			bindName = true;

			query.append(_FINDER_COLUMN_NAME_NAME_2);
		}

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
			query.append(LCSProjectModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindName) {
			qPos.add(StringUtil.toLowerCase(name));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(lcsProject);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LCSProject> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the l c s projects where name LIKE &#63; from the database.
	 *
	 * @param name the name
	 */
	@Override
	public void removeByName(String name) {
		for (LCSProject lcsProject : findByName(name, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(lcsProject);
		}
	}

	/**
	 * Returns the number of l c s projects where name LIKE &#63;.
	 *
	 * @param name the name
	 * @return the number of matching l c s projects
	 */
	@Override
	public int countByName(String name) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_NAME;

		Object[] finderArgs = new Object[] { name };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LCSPROJECT_WHERE);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_NAME_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_NAME_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_NAME_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindName) {
					qPos.add(StringUtil.toLowerCase(name));
				}

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

	private static final String _FINDER_COLUMN_NAME_NAME_1 = "lcsProject.name IS NULL";
	private static final String _FINDER_COLUMN_NAME_NAME_2 = "lower(lcsProject.name) LIKE ?";
	private static final String _FINDER_COLUMN_NAME_NAME_3 = "(lcsProject.name IS NULL OR lcsProject.name LIKE '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_CORPPROJECTID = new FinderPath(LCSProjectModelImpl.ENTITY_CACHE_ENABLED,
			LCSProjectModelImpl.FINDER_CACHE_ENABLED, LCSProjectImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByCorpProjectId",
			new String[] { Long.class.getName() },
			LCSProjectModelImpl.CORPPROJECTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CORPPROJECTID = new FinderPath(LCSProjectModelImpl.ENTITY_CACHE_ENABLED,
			LCSProjectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCorpProjectId",
			new String[] { Long.class.getName() });

	/**
	 * Returns the l c s project where corpProjectId = &#63; or throws a {@link NoSuchLCSProjectException} if it could not be found.
	 *
	 * @param corpProjectId the corp project ID
	 * @return the matching l c s project
	 * @throws NoSuchLCSProjectException if a matching l c s project could not be found
	 */
	@Override
	public LCSProject findByCorpProjectId(long corpProjectId)
		throws NoSuchLCSProjectException {
		LCSProject lcsProject = fetchByCorpProjectId(corpProjectId);

		if (lcsProject == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("corpProjectId=");
			msg.append(corpProjectId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchLCSProjectException(msg.toString());
		}

		return lcsProject;
	}

	/**
	 * Returns the l c s project where corpProjectId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param corpProjectId the corp project ID
	 * @return the matching l c s project, or <code>null</code> if a matching l c s project could not be found
	 */
	@Override
	public LCSProject fetchByCorpProjectId(long corpProjectId) {
		return fetchByCorpProjectId(corpProjectId, true);
	}

	/**
	 * Returns the l c s project where corpProjectId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param corpProjectId the corp project ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching l c s project, or <code>null</code> if a matching l c s project could not be found
	 */
	@Override
	public LCSProject fetchByCorpProjectId(long corpProjectId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { corpProjectId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_CORPPROJECTID,
					finderArgs, this);
		}

		if (result instanceof LCSProject) {
			LCSProject lcsProject = (LCSProject)result;

			if ((corpProjectId != lcsProject.getCorpProjectId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_LCSPROJECT_WHERE);

			query.append(_FINDER_COLUMN_CORPPROJECTID_CORPPROJECTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(corpProjectId);

				List<LCSProject> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_CORPPROJECTID,
						finderArgs, list);
				}
				else {
					LCSProject lcsProject = list.get(0);

					result = lcsProject;

					cacheResult(lcsProject);

					if ((lcsProject.getCorpProjectId() != corpProjectId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_CORPPROJECTID,
							finderArgs, lcsProject);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_CORPPROJECTID,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (LCSProject)result;
		}
	}

	/**
	 * Removes the l c s project where corpProjectId = &#63; from the database.
	 *
	 * @param corpProjectId the corp project ID
	 * @return the l c s project that was removed
	 */
	@Override
	public LCSProject removeByCorpProjectId(long corpProjectId)
		throws NoSuchLCSProjectException {
		LCSProject lcsProject = findByCorpProjectId(corpProjectId);

		return remove(lcsProject);
	}

	/**
	 * Returns the number of l c s projects where corpProjectId = &#63;.
	 *
	 * @param corpProjectId the corp project ID
	 * @return the number of matching l c s projects
	 */
	@Override
	public int countByCorpProjectId(long corpProjectId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CORPPROJECTID;

		Object[] finderArgs = new Object[] { corpProjectId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LCSPROJECT_WHERE);

			query.append(_FINDER_COLUMN_CORPPROJECTID_CORPPROJECTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(corpProjectId);

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

	private static final String _FINDER_COLUMN_CORPPROJECTID_CORPPROJECTID_2 = "lcsProject.corpProjectId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CPI_A = new FinderPath(LCSProjectModelImpl.ENTITY_CACHE_ENABLED,
			LCSProjectModelImpl.FINDER_CACHE_ENABLED, LCSProjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCPI_A",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CPI_A = new FinderPath(LCSProjectModelImpl.ENTITY_CACHE_ENABLED,
			LCSProjectModelImpl.FINDER_CACHE_ENABLED, LCSProjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCPI_A",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			LCSProjectModelImpl.CORPPROJECTID_COLUMN_BITMASK |
			LCSProjectModelImpl.ARCHIVED_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CPI_A = new FinderPath(LCSProjectModelImpl.ENTITY_CACHE_ENABLED,
			LCSProjectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCPI_A",
			new String[] { Long.class.getName(), Boolean.class.getName() });

	/**
	 * Returns all the l c s projects where corpProjectId = &#63; and archived = &#63;.
	 *
	 * @param corpProjectId the corp project ID
	 * @param archived the archived
	 * @return the matching l c s projects
	 */
	@Override
	public List<LCSProject> findByCPI_A(long corpProjectId, boolean archived) {
		return findByCPI_A(corpProjectId, archived, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the l c s projects where corpProjectId = &#63; and archived = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param corpProjectId the corp project ID
	 * @param archived the archived
	 * @param start the lower bound of the range of l c s projects
	 * @param end the upper bound of the range of l c s projects (not inclusive)
	 * @return the range of matching l c s projects
	 */
	@Override
	public List<LCSProject> findByCPI_A(long corpProjectId, boolean archived,
		int start, int end) {
		return findByCPI_A(corpProjectId, archived, start, end, null);
	}

	/**
	 * Returns an ordered range of all the l c s projects where corpProjectId = &#63; and archived = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param corpProjectId the corp project ID
	 * @param archived the archived
	 * @param start the lower bound of the range of l c s projects
	 * @param end the upper bound of the range of l c s projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching l c s projects
	 */
	@Override
	public List<LCSProject> findByCPI_A(long corpProjectId, boolean archived,
		int start, int end, OrderByComparator<LCSProject> orderByComparator) {
		return findByCPI_A(corpProjectId, archived, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the l c s projects where corpProjectId = &#63; and archived = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param corpProjectId the corp project ID
	 * @param archived the archived
	 * @param start the lower bound of the range of l c s projects
	 * @param end the upper bound of the range of l c s projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching l c s projects
	 */
	@Override
	public List<LCSProject> findByCPI_A(long corpProjectId, boolean archived,
		int start, int end, OrderByComparator<LCSProject> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CPI_A;
			finderArgs = new Object[] { corpProjectId, archived };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CPI_A;
			finderArgs = new Object[] {
					corpProjectId, archived,
					
					start, end, orderByComparator
				};
		}

		List<LCSProject> list = null;

		if (retrieveFromCache) {
			list = (List<LCSProject>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LCSProject lcsProject : list) {
					if ((corpProjectId != lcsProject.getCorpProjectId()) ||
							(archived != lcsProject.getArchived())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_LCSPROJECT_WHERE);

			query.append(_FINDER_COLUMN_CPI_A_CORPPROJECTID_2);

			query.append(_FINDER_COLUMN_CPI_A_ARCHIVED_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LCSProjectModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(corpProjectId);

				qPos.add(archived);

				if (!pagination) {
					list = (List<LCSProject>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSProject>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first l c s project in the ordered set where corpProjectId = &#63; and archived = &#63;.
	 *
	 * @param corpProjectId the corp project ID
	 * @param archived the archived
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s project
	 * @throws NoSuchLCSProjectException if a matching l c s project could not be found
	 */
	@Override
	public LCSProject findByCPI_A_First(long corpProjectId, boolean archived,
		OrderByComparator<LCSProject> orderByComparator)
		throws NoSuchLCSProjectException {
		LCSProject lcsProject = fetchByCPI_A_First(corpProjectId, archived,
				orderByComparator);

		if (lcsProject != null) {
			return lcsProject;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("corpProjectId=");
		msg.append(corpProjectId);

		msg.append(", archived=");
		msg.append(archived);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSProjectException(msg.toString());
	}

	/**
	 * Returns the first l c s project in the ordered set where corpProjectId = &#63; and archived = &#63;.
	 *
	 * @param corpProjectId the corp project ID
	 * @param archived the archived
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s project, or <code>null</code> if a matching l c s project could not be found
	 */
	@Override
	public LCSProject fetchByCPI_A_First(long corpProjectId, boolean archived,
		OrderByComparator<LCSProject> orderByComparator) {
		List<LCSProject> list = findByCPI_A(corpProjectId, archived, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last l c s project in the ordered set where corpProjectId = &#63; and archived = &#63;.
	 *
	 * @param corpProjectId the corp project ID
	 * @param archived the archived
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s project
	 * @throws NoSuchLCSProjectException if a matching l c s project could not be found
	 */
	@Override
	public LCSProject findByCPI_A_Last(long corpProjectId, boolean archived,
		OrderByComparator<LCSProject> orderByComparator)
		throws NoSuchLCSProjectException {
		LCSProject lcsProject = fetchByCPI_A_Last(corpProjectId, archived,
				orderByComparator);

		if (lcsProject != null) {
			return lcsProject;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("corpProjectId=");
		msg.append(corpProjectId);

		msg.append(", archived=");
		msg.append(archived);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSProjectException(msg.toString());
	}

	/**
	 * Returns the last l c s project in the ordered set where corpProjectId = &#63; and archived = &#63;.
	 *
	 * @param corpProjectId the corp project ID
	 * @param archived the archived
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s project, or <code>null</code> if a matching l c s project could not be found
	 */
	@Override
	public LCSProject fetchByCPI_A_Last(long corpProjectId, boolean archived,
		OrderByComparator<LCSProject> orderByComparator) {
		int count = countByCPI_A(corpProjectId, archived);

		if (count == 0) {
			return null;
		}

		List<LCSProject> list = findByCPI_A(corpProjectId, archived, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the l c s projects before and after the current l c s project in the ordered set where corpProjectId = &#63; and archived = &#63;.
	 *
	 * @param lcsProjectId the primary key of the current l c s project
	 * @param corpProjectId the corp project ID
	 * @param archived the archived
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next l c s project
	 * @throws NoSuchLCSProjectException if a l c s project with the primary key could not be found
	 */
	@Override
	public LCSProject[] findByCPI_A_PrevAndNext(long lcsProjectId,
		long corpProjectId, boolean archived,
		OrderByComparator<LCSProject> orderByComparator)
		throws NoSuchLCSProjectException {
		LCSProject lcsProject = findByPrimaryKey(lcsProjectId);

		Session session = null;

		try {
			session = openSession();

			LCSProject[] array = new LCSProjectImpl[3];

			array[0] = getByCPI_A_PrevAndNext(session, lcsProject,
					corpProjectId, archived, orderByComparator, true);

			array[1] = lcsProject;

			array[2] = getByCPI_A_PrevAndNext(session, lcsProject,
					corpProjectId, archived, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LCSProject getByCPI_A_PrevAndNext(Session session,
		LCSProject lcsProject, long corpProjectId, boolean archived,
		OrderByComparator<LCSProject> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_LCSPROJECT_WHERE);

		query.append(_FINDER_COLUMN_CPI_A_CORPPROJECTID_2);

		query.append(_FINDER_COLUMN_CPI_A_ARCHIVED_2);

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
			query.append(LCSProjectModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(corpProjectId);

		qPos.add(archived);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(lcsProject);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LCSProject> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the l c s projects where corpProjectId = &#63; and archived = &#63; from the database.
	 *
	 * @param corpProjectId the corp project ID
	 * @param archived the archived
	 */
	@Override
	public void removeByCPI_A(long corpProjectId, boolean archived) {
		for (LCSProject lcsProject : findByCPI_A(corpProjectId, archived,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(lcsProject);
		}
	}

	/**
	 * Returns the number of l c s projects where corpProjectId = &#63; and archived = &#63;.
	 *
	 * @param corpProjectId the corp project ID
	 * @param archived the archived
	 * @return the number of matching l c s projects
	 */
	@Override
	public int countByCPI_A(long corpProjectId, boolean archived) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CPI_A;

		Object[] finderArgs = new Object[] { corpProjectId, archived };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LCSPROJECT_WHERE);

			query.append(_FINDER_COLUMN_CPI_A_CORPPROJECTID_2);

			query.append(_FINDER_COLUMN_CPI_A_ARCHIVED_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(corpProjectId);

				qPos.add(archived);

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

	private static final String _FINDER_COLUMN_CPI_A_CORPPROJECTID_2 = "lcsProject.corpProjectId = ? AND ";
	private static final String _FINDER_COLUMN_CPI_A_ARCHIVED_2 = "lcsProject.archived = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CPI_N_A = new FinderPath(LCSProjectModelImpl.ENTITY_CACHE_ENABLED,
			LCSProjectModelImpl.FINDER_CACHE_ENABLED, LCSProjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCPI_N_A",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CPI_N_A =
		new FinderPath(LCSProjectModelImpl.ENTITY_CACHE_ENABLED,
			LCSProjectModelImpl.FINDER_CACHE_ENABLED, LCSProjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCPI_N_A",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName()
			},
			LCSProjectModelImpl.CORPPROJECTID_COLUMN_BITMASK |
			LCSProjectModelImpl.NAME_COLUMN_BITMASK |
			LCSProjectModelImpl.ARCHIVED_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CPI_N_A = new FinderPath(LCSProjectModelImpl.ENTITY_CACHE_ENABLED,
			LCSProjectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCPI_N_A",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName()
			});

	/**
	 * Returns all the l c s projects where corpProjectId = &#63; and name = &#63; and archived = &#63;.
	 *
	 * @param corpProjectId the corp project ID
	 * @param name the name
	 * @param archived the archived
	 * @return the matching l c s projects
	 */
	@Override
	public List<LCSProject> findByCPI_N_A(long corpProjectId, String name,
		boolean archived) {
		return findByCPI_N_A(corpProjectId, name, archived, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the l c s projects where corpProjectId = &#63; and name = &#63; and archived = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param corpProjectId the corp project ID
	 * @param name the name
	 * @param archived the archived
	 * @param start the lower bound of the range of l c s projects
	 * @param end the upper bound of the range of l c s projects (not inclusive)
	 * @return the range of matching l c s projects
	 */
	@Override
	public List<LCSProject> findByCPI_N_A(long corpProjectId, String name,
		boolean archived, int start, int end) {
		return findByCPI_N_A(corpProjectId, name, archived, start, end, null);
	}

	/**
	 * Returns an ordered range of all the l c s projects where corpProjectId = &#63; and name = &#63; and archived = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param corpProjectId the corp project ID
	 * @param name the name
	 * @param archived the archived
	 * @param start the lower bound of the range of l c s projects
	 * @param end the upper bound of the range of l c s projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching l c s projects
	 */
	@Override
	public List<LCSProject> findByCPI_N_A(long corpProjectId, String name,
		boolean archived, int start, int end,
		OrderByComparator<LCSProject> orderByComparator) {
		return findByCPI_N_A(corpProjectId, name, archived, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the l c s projects where corpProjectId = &#63; and name = &#63; and archived = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param corpProjectId the corp project ID
	 * @param name the name
	 * @param archived the archived
	 * @param start the lower bound of the range of l c s projects
	 * @param end the upper bound of the range of l c s projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching l c s projects
	 */
	@Override
	public List<LCSProject> findByCPI_N_A(long corpProjectId, String name,
		boolean archived, int start, int end,
		OrderByComparator<LCSProject> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CPI_N_A;
			finderArgs = new Object[] { corpProjectId, name, archived };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CPI_N_A;
			finderArgs = new Object[] {
					corpProjectId, name, archived,
					
					start, end, orderByComparator
				};
		}

		List<LCSProject> list = null;

		if (retrieveFromCache) {
			list = (List<LCSProject>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LCSProject lcsProject : list) {
					if ((corpProjectId != lcsProject.getCorpProjectId()) ||
							!Objects.equals(name, lcsProject.getName()) ||
							(archived != lcsProject.getArchived())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_LCSPROJECT_WHERE);

			query.append(_FINDER_COLUMN_CPI_N_A_CORPPROJECTID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_CPI_N_A_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_CPI_N_A_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_CPI_N_A_NAME_2);
			}

			query.append(_FINDER_COLUMN_CPI_N_A_ARCHIVED_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LCSProjectModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(corpProjectId);

				if (bindName) {
					qPos.add(name);
				}

				qPos.add(archived);

				if (!pagination) {
					list = (List<LCSProject>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSProject>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first l c s project in the ordered set where corpProjectId = &#63; and name = &#63; and archived = &#63;.
	 *
	 * @param corpProjectId the corp project ID
	 * @param name the name
	 * @param archived the archived
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s project
	 * @throws NoSuchLCSProjectException if a matching l c s project could not be found
	 */
	@Override
	public LCSProject findByCPI_N_A_First(long corpProjectId, String name,
		boolean archived, OrderByComparator<LCSProject> orderByComparator)
		throws NoSuchLCSProjectException {
		LCSProject lcsProject = fetchByCPI_N_A_First(corpProjectId, name,
				archived, orderByComparator);

		if (lcsProject != null) {
			return lcsProject;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("corpProjectId=");
		msg.append(corpProjectId);

		msg.append(", name=");
		msg.append(name);

		msg.append(", archived=");
		msg.append(archived);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSProjectException(msg.toString());
	}

	/**
	 * Returns the first l c s project in the ordered set where corpProjectId = &#63; and name = &#63; and archived = &#63;.
	 *
	 * @param corpProjectId the corp project ID
	 * @param name the name
	 * @param archived the archived
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s project, or <code>null</code> if a matching l c s project could not be found
	 */
	@Override
	public LCSProject fetchByCPI_N_A_First(long corpProjectId, String name,
		boolean archived, OrderByComparator<LCSProject> orderByComparator) {
		List<LCSProject> list = findByCPI_N_A(corpProjectId, name, archived, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last l c s project in the ordered set where corpProjectId = &#63; and name = &#63; and archived = &#63;.
	 *
	 * @param corpProjectId the corp project ID
	 * @param name the name
	 * @param archived the archived
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s project
	 * @throws NoSuchLCSProjectException if a matching l c s project could not be found
	 */
	@Override
	public LCSProject findByCPI_N_A_Last(long corpProjectId, String name,
		boolean archived, OrderByComparator<LCSProject> orderByComparator)
		throws NoSuchLCSProjectException {
		LCSProject lcsProject = fetchByCPI_N_A_Last(corpProjectId, name,
				archived, orderByComparator);

		if (lcsProject != null) {
			return lcsProject;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("corpProjectId=");
		msg.append(corpProjectId);

		msg.append(", name=");
		msg.append(name);

		msg.append(", archived=");
		msg.append(archived);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSProjectException(msg.toString());
	}

	/**
	 * Returns the last l c s project in the ordered set where corpProjectId = &#63; and name = &#63; and archived = &#63;.
	 *
	 * @param corpProjectId the corp project ID
	 * @param name the name
	 * @param archived the archived
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s project, or <code>null</code> if a matching l c s project could not be found
	 */
	@Override
	public LCSProject fetchByCPI_N_A_Last(long corpProjectId, String name,
		boolean archived, OrderByComparator<LCSProject> orderByComparator) {
		int count = countByCPI_N_A(corpProjectId, name, archived);

		if (count == 0) {
			return null;
		}

		List<LCSProject> list = findByCPI_N_A(corpProjectId, name, archived,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the l c s projects before and after the current l c s project in the ordered set where corpProjectId = &#63; and name = &#63; and archived = &#63;.
	 *
	 * @param lcsProjectId the primary key of the current l c s project
	 * @param corpProjectId the corp project ID
	 * @param name the name
	 * @param archived the archived
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next l c s project
	 * @throws NoSuchLCSProjectException if a l c s project with the primary key could not be found
	 */
	@Override
	public LCSProject[] findByCPI_N_A_PrevAndNext(long lcsProjectId,
		long corpProjectId, String name, boolean archived,
		OrderByComparator<LCSProject> orderByComparator)
		throws NoSuchLCSProjectException {
		LCSProject lcsProject = findByPrimaryKey(lcsProjectId);

		Session session = null;

		try {
			session = openSession();

			LCSProject[] array = new LCSProjectImpl[3];

			array[0] = getByCPI_N_A_PrevAndNext(session, lcsProject,
					corpProjectId, name, archived, orderByComparator, true);

			array[1] = lcsProject;

			array[2] = getByCPI_N_A_PrevAndNext(session, lcsProject,
					corpProjectId, name, archived, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LCSProject getByCPI_N_A_PrevAndNext(Session session,
		LCSProject lcsProject, long corpProjectId, String name,
		boolean archived, OrderByComparator<LCSProject> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_LCSPROJECT_WHERE);

		query.append(_FINDER_COLUMN_CPI_N_A_CORPPROJECTID_2);

		boolean bindName = false;

		if (name == null) {
			query.append(_FINDER_COLUMN_CPI_N_A_NAME_1);
		}
		else if (name.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_CPI_N_A_NAME_3);
		}
		else {
			bindName = true;

			query.append(_FINDER_COLUMN_CPI_N_A_NAME_2);
		}

		query.append(_FINDER_COLUMN_CPI_N_A_ARCHIVED_2);

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
			query.append(LCSProjectModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(corpProjectId);

		if (bindName) {
			qPos.add(name);
		}

		qPos.add(archived);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(lcsProject);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LCSProject> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the l c s projects where corpProjectId = &#63; and name = &#63; and archived = &#63; from the database.
	 *
	 * @param corpProjectId the corp project ID
	 * @param name the name
	 * @param archived the archived
	 */
	@Override
	public void removeByCPI_N_A(long corpProjectId, String name,
		boolean archived) {
		for (LCSProject lcsProject : findByCPI_N_A(corpProjectId, name,
				archived, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(lcsProject);
		}
	}

	/**
	 * Returns the number of l c s projects where corpProjectId = &#63; and name = &#63; and archived = &#63;.
	 *
	 * @param corpProjectId the corp project ID
	 * @param name the name
	 * @param archived the archived
	 * @return the number of matching l c s projects
	 */
	@Override
	public int countByCPI_N_A(long corpProjectId, String name, boolean archived) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CPI_N_A;

		Object[] finderArgs = new Object[] { corpProjectId, name, archived };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_LCSPROJECT_WHERE);

			query.append(_FINDER_COLUMN_CPI_N_A_CORPPROJECTID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_CPI_N_A_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_CPI_N_A_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_CPI_N_A_NAME_2);
			}

			query.append(_FINDER_COLUMN_CPI_N_A_ARCHIVED_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(corpProjectId);

				if (bindName) {
					qPos.add(name);
				}

				qPos.add(archived);

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

	private static final String _FINDER_COLUMN_CPI_N_A_CORPPROJECTID_2 = "lcsProject.corpProjectId = ? AND ";
	private static final String _FINDER_COLUMN_CPI_N_A_NAME_1 = "lcsProject.name IS NULL AND ";
	private static final String _FINDER_COLUMN_CPI_N_A_NAME_2 = "lcsProject.name = ? AND ";
	private static final String _FINDER_COLUMN_CPI_N_A_NAME_3 = "(lcsProject.name IS NULL OR lcsProject.name = '') AND ";
	private static final String _FINDER_COLUMN_CPI_N_A_ARCHIVED_2 = "lcsProject.archived = ?";

	public LCSProjectPersistenceImpl() {
		setModelClass(LCSProject.class);
	}

	/**
	 * Caches the l c s project in the entity cache if it is enabled.
	 *
	 * @param lcsProject the l c s project
	 */
	@Override
	public void cacheResult(LCSProject lcsProject) {
		entityCache.putResult(LCSProjectModelImpl.ENTITY_CACHE_ENABLED,
			LCSProjectImpl.class, lcsProject.getPrimaryKey(), lcsProject);

		finderCache.putResult(FINDER_PATH_FETCH_BY_CORPPROJECTID,
			new Object[] { lcsProject.getCorpProjectId() }, lcsProject);

		lcsProject.resetOriginalValues();
	}

	/**
	 * Caches the l c s projects in the entity cache if it is enabled.
	 *
	 * @param lcsProjects the l c s projects
	 */
	@Override
	public void cacheResult(List<LCSProject> lcsProjects) {
		for (LCSProject lcsProject : lcsProjects) {
			if (entityCache.getResult(
						LCSProjectModelImpl.ENTITY_CACHE_ENABLED,
						LCSProjectImpl.class, lcsProject.getPrimaryKey()) == null) {
				cacheResult(lcsProject);
			}
			else {
				lcsProject.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all l c s projects.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(LCSProjectImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the l c s project.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LCSProject lcsProject) {
		entityCache.removeResult(LCSProjectModelImpl.ENTITY_CACHE_ENABLED,
			LCSProjectImpl.class, lcsProject.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((LCSProjectModelImpl)lcsProject);
	}

	@Override
	public void clearCache(List<LCSProject> lcsProjects) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (LCSProject lcsProject : lcsProjects) {
			entityCache.removeResult(LCSProjectModelImpl.ENTITY_CACHE_ENABLED,
				LCSProjectImpl.class, lcsProject.getPrimaryKey());

			clearUniqueFindersCache((LCSProjectModelImpl)lcsProject);
		}
	}

	protected void cacheUniqueFindersCache(
		LCSProjectModelImpl lcsProjectModelImpl, boolean isNew) {
		if (isNew) {
			Object[] args = new Object[] { lcsProjectModelImpl.getCorpProjectId() };

			finderCache.putResult(FINDER_PATH_COUNT_BY_CORPPROJECTID, args,
				Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_CORPPROJECTID, args,
				lcsProjectModelImpl);
		}
		else {
			if ((lcsProjectModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_CORPPROJECTID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						lcsProjectModelImpl.getCorpProjectId()
					};

				finderCache.putResult(FINDER_PATH_COUNT_BY_CORPPROJECTID, args,
					Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_CORPPROJECTID, args,
					lcsProjectModelImpl);
			}
		}
	}

	protected void clearUniqueFindersCache(
		LCSProjectModelImpl lcsProjectModelImpl) {
		Object[] args = new Object[] { lcsProjectModelImpl.getCorpProjectId() };

		finderCache.removeResult(FINDER_PATH_COUNT_BY_CORPPROJECTID, args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_CORPPROJECTID, args);

		if ((lcsProjectModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_CORPPROJECTID.getColumnBitmask()) != 0) {
			args = new Object[] { lcsProjectModelImpl.getOriginalCorpProjectId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CORPPROJECTID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_CORPPROJECTID, args);
		}
	}

	/**
	 * Creates a new l c s project with the primary key. Does not add the l c s project to the database.
	 *
	 * @param lcsProjectId the primary key for the new l c s project
	 * @return the new l c s project
	 */
	@Override
	public LCSProject create(long lcsProjectId) {
		LCSProject lcsProject = new LCSProjectImpl();

		lcsProject.setNew(true);
		lcsProject.setPrimaryKey(lcsProjectId);

		return lcsProject;
	}

	/**
	 * Removes the l c s project with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param lcsProjectId the primary key of the l c s project
	 * @return the l c s project that was removed
	 * @throws NoSuchLCSProjectException if a l c s project with the primary key could not be found
	 */
	@Override
	public LCSProject remove(long lcsProjectId)
		throws NoSuchLCSProjectException {
		return remove((Serializable)lcsProjectId);
	}

	/**
	 * Removes the l c s project with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the l c s project
	 * @return the l c s project that was removed
	 * @throws NoSuchLCSProjectException if a l c s project with the primary key could not be found
	 */
	@Override
	public LCSProject remove(Serializable primaryKey)
		throws NoSuchLCSProjectException {
		Session session = null;

		try {
			session = openSession();

			LCSProject lcsProject = (LCSProject)session.get(LCSProjectImpl.class,
					primaryKey);

			if (lcsProject == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLCSProjectException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(lcsProject);
		}
		catch (NoSuchLCSProjectException nsee) {
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
	protected LCSProject removeImpl(LCSProject lcsProject) {
		lcsProject = toUnwrappedModel(lcsProject);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(lcsProject)) {
				lcsProject = (LCSProject)session.get(LCSProjectImpl.class,
						lcsProject.getPrimaryKeyObj());
			}

			if (lcsProject != null) {
				session.delete(lcsProject);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (lcsProject != null) {
			clearCache(lcsProject);
		}

		return lcsProject;
	}

	@Override
	public LCSProject updateImpl(LCSProject lcsProject) {
		lcsProject = toUnwrappedModel(lcsProject);

		boolean isNew = lcsProject.isNew();

		LCSProjectModelImpl lcsProjectModelImpl = (LCSProjectModelImpl)lcsProject;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (lcsProject.getCreateDate() == null)) {
			if (serviceContext == null) {
				lcsProject.setCreateDate(now);
			}
			else {
				lcsProject.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!lcsProjectModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				lcsProject.setModifiedDate(now);
			}
			else {
				lcsProject.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (lcsProject.isNew()) {
				session.save(lcsProject);

				lcsProject.setNew(false);
			}
			else {
				lcsProject = (LCSProject)session.merge(lcsProject);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !LCSProjectModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((lcsProjectModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CPI_A.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						lcsProjectModelImpl.getOriginalCorpProjectId(),
						lcsProjectModelImpl.getOriginalArchived()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CPI_A, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CPI_A,
					args);

				args = new Object[] {
						lcsProjectModelImpl.getCorpProjectId(),
						lcsProjectModelImpl.getArchived()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CPI_A, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CPI_A,
					args);
			}

			if ((lcsProjectModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CPI_N_A.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						lcsProjectModelImpl.getOriginalCorpProjectId(),
						lcsProjectModelImpl.getOriginalName(),
						lcsProjectModelImpl.getOriginalArchived()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CPI_N_A, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CPI_N_A,
					args);

				args = new Object[] {
						lcsProjectModelImpl.getCorpProjectId(),
						lcsProjectModelImpl.getName(),
						lcsProjectModelImpl.getArchived()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CPI_N_A, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CPI_N_A,
					args);
			}
		}

		entityCache.putResult(LCSProjectModelImpl.ENTITY_CACHE_ENABLED,
			LCSProjectImpl.class, lcsProject.getPrimaryKey(), lcsProject, false);

		clearUniqueFindersCache(lcsProjectModelImpl);
		cacheUniqueFindersCache(lcsProjectModelImpl, isNew);

		lcsProject.resetOriginalValues();

		return lcsProject;
	}

	protected LCSProject toUnwrappedModel(LCSProject lcsProject) {
		if (lcsProject instanceof LCSProjectImpl) {
			return lcsProject;
		}

		LCSProjectImpl lcsProjectImpl = new LCSProjectImpl();

		lcsProjectImpl.setNew(lcsProject.isNew());
		lcsProjectImpl.setPrimaryKey(lcsProject.getPrimaryKey());

		lcsProjectImpl.setLcsProjectId(lcsProject.getLcsProjectId());
		lcsProjectImpl.setCreateDate(lcsProject.getCreateDate());
		lcsProjectImpl.setModifiedDate(lcsProject.getModifiedDate());
		lcsProjectImpl.setSourceSystemName(lcsProject.getSourceSystemName());
		lcsProjectImpl.setName(lcsProject.getName());
		lcsProjectImpl.setOrganizationId(lcsProject.getOrganizationId());
		lcsProjectImpl.setAddressId(lcsProject.getAddressId());
		lcsProjectImpl.setAccountEntryId(lcsProject.getAccountEntryId());
		lcsProjectImpl.setCorpProjectId(lcsProject.getCorpProjectId());
		lcsProjectImpl.setContactEmailAddress(lcsProject.getContactEmailAddress());
		lcsProjectImpl.setPhoneNumber(lcsProject.getPhoneNumber());
		lcsProjectImpl.setSubscriptionActive(lcsProject.isSubscriptionActive());
		lcsProjectImpl.setArchived(lcsProject.isArchived());

		return lcsProjectImpl;
	}

	/**
	 * Returns the l c s project with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the l c s project
	 * @return the l c s project
	 * @throws NoSuchLCSProjectException if a l c s project with the primary key could not be found
	 */
	@Override
	public LCSProject findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLCSProjectException {
		LCSProject lcsProject = fetchByPrimaryKey(primaryKey);

		if (lcsProject == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLCSProjectException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return lcsProject;
	}

	/**
	 * Returns the l c s project with the primary key or throws a {@link NoSuchLCSProjectException} if it could not be found.
	 *
	 * @param lcsProjectId the primary key of the l c s project
	 * @return the l c s project
	 * @throws NoSuchLCSProjectException if a l c s project with the primary key could not be found
	 */
	@Override
	public LCSProject findByPrimaryKey(long lcsProjectId)
		throws NoSuchLCSProjectException {
		return findByPrimaryKey((Serializable)lcsProjectId);
	}

	/**
	 * Returns the l c s project with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the l c s project
	 * @return the l c s project, or <code>null</code> if a l c s project with the primary key could not be found
	 */
	@Override
	public LCSProject fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(LCSProjectModelImpl.ENTITY_CACHE_ENABLED,
				LCSProjectImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		LCSProject lcsProject = (LCSProject)serializable;

		if (lcsProject == null) {
			Session session = null;

			try {
				session = openSession();

				lcsProject = (LCSProject)session.get(LCSProjectImpl.class,
						primaryKey);

				if (lcsProject != null) {
					cacheResult(lcsProject);
				}
				else {
					entityCache.putResult(LCSProjectModelImpl.ENTITY_CACHE_ENABLED,
						LCSProjectImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(LCSProjectModelImpl.ENTITY_CACHE_ENABLED,
					LCSProjectImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return lcsProject;
	}

	/**
	 * Returns the l c s project with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param lcsProjectId the primary key of the l c s project
	 * @return the l c s project, or <code>null</code> if a l c s project with the primary key could not be found
	 */
	@Override
	public LCSProject fetchByPrimaryKey(long lcsProjectId) {
		return fetchByPrimaryKey((Serializable)lcsProjectId);
	}

	@Override
	public Map<Serializable, LCSProject> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, LCSProject> map = new HashMap<Serializable, LCSProject>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			LCSProject lcsProject = fetchByPrimaryKey(primaryKey);

			if (lcsProject != null) {
				map.put(primaryKey, lcsProject);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(LCSProjectModelImpl.ENTITY_CACHE_ENABLED,
					LCSProjectImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (LCSProject)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_LCSPROJECT_WHERE_PKS_IN);

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

			for (LCSProject lcsProject : (List<LCSProject>)q.list()) {
				map.put(lcsProject.getPrimaryKeyObj(), lcsProject);

				cacheResult(lcsProject);

				uncachedPrimaryKeys.remove(lcsProject.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(LCSProjectModelImpl.ENTITY_CACHE_ENABLED,
					LCSProjectImpl.class, primaryKey, nullModel);
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
	 * Returns all the l c s projects.
	 *
	 * @return the l c s projects
	 */
	@Override
	public List<LCSProject> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the l c s projects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of l c s projects
	 * @param end the upper bound of the range of l c s projects (not inclusive)
	 * @return the range of l c s projects
	 */
	@Override
	public List<LCSProject> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the l c s projects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of l c s projects
	 * @param end the upper bound of the range of l c s projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of l c s projects
	 */
	@Override
	public List<LCSProject> findAll(int start, int end,
		OrderByComparator<LCSProject> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the l c s projects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of l c s projects
	 * @param end the upper bound of the range of l c s projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of l c s projects
	 */
	@Override
	public List<LCSProject> findAll(int start, int end,
		OrderByComparator<LCSProject> orderByComparator,
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

		List<LCSProject> list = null;

		if (retrieveFromCache) {
			list = (List<LCSProject>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_LCSPROJECT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_LCSPROJECT;

				if (pagination) {
					sql = sql.concat(LCSProjectModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<LCSProject>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSProject>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Removes all the l c s projects from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (LCSProject lcsProject : findAll()) {
			remove(lcsProject);
		}
	}

	/**
	 * Returns the number of l c s projects.
	 *
	 * @return the number of l c s projects
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_LCSPROJECT);

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
		return LCSProjectModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the l c s project persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(LCSProjectImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_LCSPROJECT = "SELECT lcsProject FROM LCSProject lcsProject";
	private static final String _SQL_SELECT_LCSPROJECT_WHERE_PKS_IN = "SELECT lcsProject FROM LCSProject lcsProject WHERE lcsProjectId IN (";
	private static final String _SQL_SELECT_LCSPROJECT_WHERE = "SELECT lcsProject FROM LCSProject lcsProject WHERE ";
	private static final String _SQL_COUNT_LCSPROJECT = "SELECT COUNT(lcsProject) FROM LCSProject lcsProject";
	private static final String _SQL_COUNT_LCSPROJECT_WHERE = "SELECT COUNT(lcsProject) FROM LCSProject lcsProject WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "lcsProject.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No LCSProject exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No LCSProject exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(LCSProjectPersistenceImpl.class);
}