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

import com.liferay.osb.lcs.exception.NoSuchLCSClusterEntryException;
import com.liferay.osb.lcs.model.LCSClusterEntry;
import com.liferay.osb.lcs.model.impl.LCSClusterEntryImpl;
import com.liferay.osb.lcs.model.impl.LCSClusterEntryModelImpl;
import com.liferay.osb.lcs.service.persistence.LCSClusterEntryPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the l c s cluster entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Igor Beslic
 * @see LCSClusterEntryPersistence
 * @see com.liferay.osb.lcs.service.persistence.LCSClusterEntryUtil
 * @generated
 */
@ProviderType
public class LCSClusterEntryPersistenceImpl extends BasePersistenceImpl<LCSClusterEntry>
	implements LCSClusterEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link LCSClusterEntryUtil} to access the l c s cluster entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = LCSClusterEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(LCSClusterEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterEntryModelImpl.FINDER_CACHE_ENABLED,
			LCSClusterEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(LCSClusterEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterEntryModelImpl.FINDER_CACHE_ENABLED,
			LCSClusterEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(LCSClusterEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_LCSPROJECTID =
		new FinderPath(LCSClusterEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterEntryModelImpl.FINDER_CACHE_ENABLED,
			LCSClusterEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByLCSProjectId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LCSPROJECTID =
		new FinderPath(LCSClusterEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterEntryModelImpl.FINDER_CACHE_ENABLED,
			LCSClusterEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByLCSProjectId",
			new String[] { Long.class.getName() },
			LCSClusterEntryModelImpl.LCSPROJECTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_LCSPROJECTID = new FinderPath(LCSClusterEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByLCSProjectId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the l c s cluster entries where lcsProjectId = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @return the matching l c s cluster entries
	 */
	@Override
	public List<LCSClusterEntry> findByLCSProjectId(long lcsProjectId) {
		return findByLCSProjectId(lcsProjectId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the l c s cluster entries where lcsProjectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param start the lower bound of the range of l c s cluster entries
	 * @param end the upper bound of the range of l c s cluster entries (not inclusive)
	 * @return the range of matching l c s cluster entries
	 */
	@Override
	public List<LCSClusterEntry> findByLCSProjectId(long lcsProjectId,
		int start, int end) {
		return findByLCSProjectId(lcsProjectId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the l c s cluster entries where lcsProjectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param start the lower bound of the range of l c s cluster entries
	 * @param end the upper bound of the range of l c s cluster entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching l c s cluster entries
	 */
	@Override
	public List<LCSClusterEntry> findByLCSProjectId(long lcsProjectId,
		int start, int end, OrderByComparator<LCSClusterEntry> orderByComparator) {
		return findByLCSProjectId(lcsProjectId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the l c s cluster entries where lcsProjectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param start the lower bound of the range of l c s cluster entries
	 * @param end the upper bound of the range of l c s cluster entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching l c s cluster entries
	 */
	@Override
	public List<LCSClusterEntry> findByLCSProjectId(long lcsProjectId,
		int start, int end,
		OrderByComparator<LCSClusterEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LCSPROJECTID;
			finderArgs = new Object[] { lcsProjectId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_LCSPROJECTID;
			finderArgs = new Object[] {
					lcsProjectId,
					
					start, end, orderByComparator
				};
		}

		List<LCSClusterEntry> list = null;

		if (retrieveFromCache) {
			list = (List<LCSClusterEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LCSClusterEntry lcsClusterEntry : list) {
					if ((lcsProjectId != lcsClusterEntry.getLcsProjectId())) {
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

			query.append(_SQL_SELECT_LCSCLUSTERENTRY_WHERE);

			query.append(_FINDER_COLUMN_LCSPROJECTID_LCSPROJECTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LCSClusterEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(lcsProjectId);

				if (!pagination) {
					list = (List<LCSClusterEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSClusterEntry>)QueryUtil.list(q,
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
	 * Returns the first l c s cluster entry in the ordered set where lcsProjectId = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s cluster entry
	 * @throws NoSuchLCSClusterEntryException if a matching l c s cluster entry could not be found
	 */
	@Override
	public LCSClusterEntry findByLCSProjectId_First(long lcsProjectId,
		OrderByComparator<LCSClusterEntry> orderByComparator)
		throws NoSuchLCSClusterEntryException {
		LCSClusterEntry lcsClusterEntry = fetchByLCSProjectId_First(lcsProjectId,
				orderByComparator);

		if (lcsClusterEntry != null) {
			return lcsClusterEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("lcsProjectId=");
		msg.append(lcsProjectId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSClusterEntryException(msg.toString());
	}

	/**
	 * Returns the first l c s cluster entry in the ordered set where lcsProjectId = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s cluster entry, or <code>null</code> if a matching l c s cluster entry could not be found
	 */
	@Override
	public LCSClusterEntry fetchByLCSProjectId_First(long lcsProjectId,
		OrderByComparator<LCSClusterEntry> orderByComparator) {
		List<LCSClusterEntry> list = findByLCSProjectId(lcsProjectId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last l c s cluster entry in the ordered set where lcsProjectId = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s cluster entry
	 * @throws NoSuchLCSClusterEntryException if a matching l c s cluster entry could not be found
	 */
	@Override
	public LCSClusterEntry findByLCSProjectId_Last(long lcsProjectId,
		OrderByComparator<LCSClusterEntry> orderByComparator)
		throws NoSuchLCSClusterEntryException {
		LCSClusterEntry lcsClusterEntry = fetchByLCSProjectId_Last(lcsProjectId,
				orderByComparator);

		if (lcsClusterEntry != null) {
			return lcsClusterEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("lcsProjectId=");
		msg.append(lcsProjectId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSClusterEntryException(msg.toString());
	}

	/**
	 * Returns the last l c s cluster entry in the ordered set where lcsProjectId = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s cluster entry, or <code>null</code> if a matching l c s cluster entry could not be found
	 */
	@Override
	public LCSClusterEntry fetchByLCSProjectId_Last(long lcsProjectId,
		OrderByComparator<LCSClusterEntry> orderByComparator) {
		int count = countByLCSProjectId(lcsProjectId);

		if (count == 0) {
			return null;
		}

		List<LCSClusterEntry> list = findByLCSProjectId(lcsProjectId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the l c s cluster entries before and after the current l c s cluster entry in the ordered set where lcsProjectId = &#63;.
	 *
	 * @param lcsClusterEntryId the primary key of the current l c s cluster entry
	 * @param lcsProjectId the lcs project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next l c s cluster entry
	 * @throws NoSuchLCSClusterEntryException if a l c s cluster entry with the primary key could not be found
	 */
	@Override
	public LCSClusterEntry[] findByLCSProjectId_PrevAndNext(
		long lcsClusterEntryId, long lcsProjectId,
		OrderByComparator<LCSClusterEntry> orderByComparator)
		throws NoSuchLCSClusterEntryException {
		LCSClusterEntry lcsClusterEntry = findByPrimaryKey(lcsClusterEntryId);

		Session session = null;

		try {
			session = openSession();

			LCSClusterEntry[] array = new LCSClusterEntryImpl[3];

			array[0] = getByLCSProjectId_PrevAndNext(session, lcsClusterEntry,
					lcsProjectId, orderByComparator, true);

			array[1] = lcsClusterEntry;

			array[2] = getByLCSProjectId_PrevAndNext(session, lcsClusterEntry,
					lcsProjectId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LCSClusterEntry getByLCSProjectId_PrevAndNext(Session session,
		LCSClusterEntry lcsClusterEntry, long lcsProjectId,
		OrderByComparator<LCSClusterEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LCSCLUSTERENTRY_WHERE);

		query.append(_FINDER_COLUMN_LCSPROJECTID_LCSPROJECTID_2);

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
			query.append(LCSClusterEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(lcsProjectId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(lcsClusterEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LCSClusterEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the l c s cluster entries where lcsProjectId = &#63; from the database.
	 *
	 * @param lcsProjectId the lcs project ID
	 */
	@Override
	public void removeByLCSProjectId(long lcsProjectId) {
		for (LCSClusterEntry lcsClusterEntry : findByLCSProjectId(
				lcsProjectId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(lcsClusterEntry);
		}
	}

	/**
	 * Returns the number of l c s cluster entries where lcsProjectId = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @return the number of matching l c s cluster entries
	 */
	@Override
	public int countByLCSProjectId(long lcsProjectId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_LCSPROJECTID;

		Object[] finderArgs = new Object[] { lcsProjectId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LCSCLUSTERENTRY_WHERE);

			query.append(_FINDER_COLUMN_LCSPROJECTID_LCSPROJECTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(lcsProjectId);

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

	private static final String _FINDER_COLUMN_LCSPROJECTID_LCSPROJECTID_2 = "lcsClusterEntry.lcsProjectId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_LPI_N = new FinderPath(LCSClusterEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterEntryModelImpl.FINDER_CACHE_ENABLED,
			LCSClusterEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByLPI_N",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LPI_N = new FinderPath(LCSClusterEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterEntryModelImpl.FINDER_CACHE_ENABLED,
			LCSClusterEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByLPI_N",
			new String[] { Long.class.getName(), String.class.getName() },
			LCSClusterEntryModelImpl.LCSPROJECTID_COLUMN_BITMASK |
			LCSClusterEntryModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_LPI_N = new FinderPath(LCSClusterEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByLPI_N",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the l c s cluster entries where lcsProjectId = &#63; and name = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param name the name
	 * @return the matching l c s cluster entries
	 */
	@Override
	public List<LCSClusterEntry> findByLPI_N(long lcsProjectId, String name) {
		return findByLPI_N(lcsProjectId, name, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the l c s cluster entries where lcsProjectId = &#63; and name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param name the name
	 * @param start the lower bound of the range of l c s cluster entries
	 * @param end the upper bound of the range of l c s cluster entries (not inclusive)
	 * @return the range of matching l c s cluster entries
	 */
	@Override
	public List<LCSClusterEntry> findByLPI_N(long lcsProjectId, String name,
		int start, int end) {
		return findByLPI_N(lcsProjectId, name, start, end, null);
	}

	/**
	 * Returns an ordered range of all the l c s cluster entries where lcsProjectId = &#63; and name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param name the name
	 * @param start the lower bound of the range of l c s cluster entries
	 * @param end the upper bound of the range of l c s cluster entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching l c s cluster entries
	 */
	@Override
	public List<LCSClusterEntry> findByLPI_N(long lcsProjectId, String name,
		int start, int end, OrderByComparator<LCSClusterEntry> orderByComparator) {
		return findByLPI_N(lcsProjectId, name, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the l c s cluster entries where lcsProjectId = &#63; and name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param name the name
	 * @param start the lower bound of the range of l c s cluster entries
	 * @param end the upper bound of the range of l c s cluster entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching l c s cluster entries
	 */
	@Override
	public List<LCSClusterEntry> findByLPI_N(long lcsProjectId, String name,
		int start, int end,
		OrderByComparator<LCSClusterEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LPI_N;
			finderArgs = new Object[] { lcsProjectId, name };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_LPI_N;
			finderArgs = new Object[] {
					lcsProjectId, name,
					
					start, end, orderByComparator
				};
		}

		List<LCSClusterEntry> list = null;

		if (retrieveFromCache) {
			list = (List<LCSClusterEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LCSClusterEntry lcsClusterEntry : list) {
					if ((lcsProjectId != lcsClusterEntry.getLcsProjectId()) ||
							!Objects.equals(name, lcsClusterEntry.getName())) {
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

			query.append(_SQL_SELECT_LCSCLUSTERENTRY_WHERE);

			query.append(_FINDER_COLUMN_LPI_N_LCSPROJECTID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_LPI_N_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_LPI_N_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_LPI_N_NAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LCSClusterEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(lcsProjectId);

				if (bindName) {
					qPos.add(name);
				}

				if (!pagination) {
					list = (List<LCSClusterEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSClusterEntry>)QueryUtil.list(q,
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
	 * Returns the first l c s cluster entry in the ordered set where lcsProjectId = &#63; and name = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s cluster entry
	 * @throws NoSuchLCSClusterEntryException if a matching l c s cluster entry could not be found
	 */
	@Override
	public LCSClusterEntry findByLPI_N_First(long lcsProjectId, String name,
		OrderByComparator<LCSClusterEntry> orderByComparator)
		throws NoSuchLCSClusterEntryException {
		LCSClusterEntry lcsClusterEntry = fetchByLPI_N_First(lcsProjectId,
				name, orderByComparator);

		if (lcsClusterEntry != null) {
			return lcsClusterEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("lcsProjectId=");
		msg.append(lcsProjectId);

		msg.append(", name=");
		msg.append(name);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSClusterEntryException(msg.toString());
	}

	/**
	 * Returns the first l c s cluster entry in the ordered set where lcsProjectId = &#63; and name = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s cluster entry, or <code>null</code> if a matching l c s cluster entry could not be found
	 */
	@Override
	public LCSClusterEntry fetchByLPI_N_First(long lcsProjectId, String name,
		OrderByComparator<LCSClusterEntry> orderByComparator) {
		List<LCSClusterEntry> list = findByLPI_N(lcsProjectId, name, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last l c s cluster entry in the ordered set where lcsProjectId = &#63; and name = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s cluster entry
	 * @throws NoSuchLCSClusterEntryException if a matching l c s cluster entry could not be found
	 */
	@Override
	public LCSClusterEntry findByLPI_N_Last(long lcsProjectId, String name,
		OrderByComparator<LCSClusterEntry> orderByComparator)
		throws NoSuchLCSClusterEntryException {
		LCSClusterEntry lcsClusterEntry = fetchByLPI_N_Last(lcsProjectId, name,
				orderByComparator);

		if (lcsClusterEntry != null) {
			return lcsClusterEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("lcsProjectId=");
		msg.append(lcsProjectId);

		msg.append(", name=");
		msg.append(name);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSClusterEntryException(msg.toString());
	}

	/**
	 * Returns the last l c s cluster entry in the ordered set where lcsProjectId = &#63; and name = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s cluster entry, or <code>null</code> if a matching l c s cluster entry could not be found
	 */
	@Override
	public LCSClusterEntry fetchByLPI_N_Last(long lcsProjectId, String name,
		OrderByComparator<LCSClusterEntry> orderByComparator) {
		int count = countByLPI_N(lcsProjectId, name);

		if (count == 0) {
			return null;
		}

		List<LCSClusterEntry> list = findByLPI_N(lcsProjectId, name, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the l c s cluster entries before and after the current l c s cluster entry in the ordered set where lcsProjectId = &#63; and name = &#63;.
	 *
	 * @param lcsClusterEntryId the primary key of the current l c s cluster entry
	 * @param lcsProjectId the lcs project ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next l c s cluster entry
	 * @throws NoSuchLCSClusterEntryException if a l c s cluster entry with the primary key could not be found
	 */
	@Override
	public LCSClusterEntry[] findByLPI_N_PrevAndNext(long lcsClusterEntryId,
		long lcsProjectId, String name,
		OrderByComparator<LCSClusterEntry> orderByComparator)
		throws NoSuchLCSClusterEntryException {
		LCSClusterEntry lcsClusterEntry = findByPrimaryKey(lcsClusterEntryId);

		Session session = null;

		try {
			session = openSession();

			LCSClusterEntry[] array = new LCSClusterEntryImpl[3];

			array[0] = getByLPI_N_PrevAndNext(session, lcsClusterEntry,
					lcsProjectId, name, orderByComparator, true);

			array[1] = lcsClusterEntry;

			array[2] = getByLPI_N_PrevAndNext(session, lcsClusterEntry,
					lcsProjectId, name, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LCSClusterEntry getByLPI_N_PrevAndNext(Session session,
		LCSClusterEntry lcsClusterEntry, long lcsProjectId, String name,
		OrderByComparator<LCSClusterEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_LCSCLUSTERENTRY_WHERE);

		query.append(_FINDER_COLUMN_LPI_N_LCSPROJECTID_2);

		boolean bindName = false;

		if (name == null) {
			query.append(_FINDER_COLUMN_LPI_N_NAME_1);
		}
		else if (name.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_LPI_N_NAME_3);
		}
		else {
			bindName = true;

			query.append(_FINDER_COLUMN_LPI_N_NAME_2);
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
			query.append(LCSClusterEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(lcsProjectId);

		if (bindName) {
			qPos.add(name);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(lcsClusterEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LCSClusterEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the l c s cluster entries where lcsProjectId = &#63; and name = &#63; from the database.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param name the name
	 */
	@Override
	public void removeByLPI_N(long lcsProjectId, String name) {
		for (LCSClusterEntry lcsClusterEntry : findByLPI_N(lcsProjectId, name,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(lcsClusterEntry);
		}
	}

	/**
	 * Returns the number of l c s cluster entries where lcsProjectId = &#63; and name = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param name the name
	 * @return the number of matching l c s cluster entries
	 */
	@Override
	public int countByLPI_N(long lcsProjectId, String name) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_LPI_N;

		Object[] finderArgs = new Object[] { lcsProjectId, name };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LCSCLUSTERENTRY_WHERE);

			query.append(_FINDER_COLUMN_LPI_N_LCSPROJECTID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_LPI_N_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_LPI_N_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_LPI_N_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(lcsProjectId);

				if (bindName) {
					qPos.add(name);
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

	private static final String _FINDER_COLUMN_LPI_N_LCSPROJECTID_2 = "lcsClusterEntry.lcsProjectId = ? AND ";
	private static final String _FINDER_COLUMN_LPI_N_NAME_1 = "lcsClusterEntry.name IS NULL";
	private static final String _FINDER_COLUMN_LPI_N_NAME_2 = "lcsClusterEntry.name = ?";
	private static final String _FINDER_COLUMN_LPI_N_NAME_3 = "(lcsClusterEntry.name IS NULL OR lcsClusterEntry.name = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_LPI_ST = new FinderPath(LCSClusterEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterEntryModelImpl.FINDER_CACHE_ENABLED,
			LCSClusterEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByLPI_ST",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LPI_ST =
		new FinderPath(LCSClusterEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterEntryModelImpl.FINDER_CACHE_ENABLED,
			LCSClusterEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByLPI_ST",
			new String[] { Long.class.getName(), String.class.getName() },
			LCSClusterEntryModelImpl.LCSPROJECTID_COLUMN_BITMASK |
			LCSClusterEntryModelImpl.SUBSCRIPTIONTYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_LPI_ST = new FinderPath(LCSClusterEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByLPI_ST",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the l c s cluster entries where lcsProjectId = &#63; and subscriptionType = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param subscriptionType the subscription type
	 * @return the matching l c s cluster entries
	 */
	@Override
	public List<LCSClusterEntry> findByLPI_ST(long lcsProjectId,
		String subscriptionType) {
		return findByLPI_ST(lcsProjectId, subscriptionType, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the l c s cluster entries where lcsProjectId = &#63; and subscriptionType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param subscriptionType the subscription type
	 * @param start the lower bound of the range of l c s cluster entries
	 * @param end the upper bound of the range of l c s cluster entries (not inclusive)
	 * @return the range of matching l c s cluster entries
	 */
	@Override
	public List<LCSClusterEntry> findByLPI_ST(long lcsProjectId,
		String subscriptionType, int start, int end) {
		return findByLPI_ST(lcsProjectId, subscriptionType, start, end, null);
	}

	/**
	 * Returns an ordered range of all the l c s cluster entries where lcsProjectId = &#63; and subscriptionType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param subscriptionType the subscription type
	 * @param start the lower bound of the range of l c s cluster entries
	 * @param end the upper bound of the range of l c s cluster entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching l c s cluster entries
	 */
	@Override
	public List<LCSClusterEntry> findByLPI_ST(long lcsProjectId,
		String subscriptionType, int start, int end,
		OrderByComparator<LCSClusterEntry> orderByComparator) {
		return findByLPI_ST(lcsProjectId, subscriptionType, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the l c s cluster entries where lcsProjectId = &#63; and subscriptionType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param subscriptionType the subscription type
	 * @param start the lower bound of the range of l c s cluster entries
	 * @param end the upper bound of the range of l c s cluster entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching l c s cluster entries
	 */
	@Override
	public List<LCSClusterEntry> findByLPI_ST(long lcsProjectId,
		String subscriptionType, int start, int end,
		OrderByComparator<LCSClusterEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LPI_ST;
			finderArgs = new Object[] { lcsProjectId, subscriptionType };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_LPI_ST;
			finderArgs = new Object[] {
					lcsProjectId, subscriptionType,
					
					start, end, orderByComparator
				};
		}

		List<LCSClusterEntry> list = null;

		if (retrieveFromCache) {
			list = (List<LCSClusterEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LCSClusterEntry lcsClusterEntry : list) {
					if ((lcsProjectId != lcsClusterEntry.getLcsProjectId()) ||
							!Objects.equals(subscriptionType,
								lcsClusterEntry.getSubscriptionType())) {
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

			query.append(_SQL_SELECT_LCSCLUSTERENTRY_WHERE);

			query.append(_FINDER_COLUMN_LPI_ST_LCSPROJECTID_2);

			boolean bindSubscriptionType = false;

			if (subscriptionType == null) {
				query.append(_FINDER_COLUMN_LPI_ST_SUBSCRIPTIONTYPE_1);
			}
			else if (subscriptionType.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_LPI_ST_SUBSCRIPTIONTYPE_3);
			}
			else {
				bindSubscriptionType = true;

				query.append(_FINDER_COLUMN_LPI_ST_SUBSCRIPTIONTYPE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LCSClusterEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(lcsProjectId);

				if (bindSubscriptionType) {
					qPos.add(subscriptionType);
				}

				if (!pagination) {
					list = (List<LCSClusterEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSClusterEntry>)QueryUtil.list(q,
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
	 * Returns the first l c s cluster entry in the ordered set where lcsProjectId = &#63; and subscriptionType = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param subscriptionType the subscription type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s cluster entry
	 * @throws NoSuchLCSClusterEntryException if a matching l c s cluster entry could not be found
	 */
	@Override
	public LCSClusterEntry findByLPI_ST_First(long lcsProjectId,
		String subscriptionType,
		OrderByComparator<LCSClusterEntry> orderByComparator)
		throws NoSuchLCSClusterEntryException {
		LCSClusterEntry lcsClusterEntry = fetchByLPI_ST_First(lcsProjectId,
				subscriptionType, orderByComparator);

		if (lcsClusterEntry != null) {
			return lcsClusterEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("lcsProjectId=");
		msg.append(lcsProjectId);

		msg.append(", subscriptionType=");
		msg.append(subscriptionType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSClusterEntryException(msg.toString());
	}

	/**
	 * Returns the first l c s cluster entry in the ordered set where lcsProjectId = &#63; and subscriptionType = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param subscriptionType the subscription type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s cluster entry, or <code>null</code> if a matching l c s cluster entry could not be found
	 */
	@Override
	public LCSClusterEntry fetchByLPI_ST_First(long lcsProjectId,
		String subscriptionType,
		OrderByComparator<LCSClusterEntry> orderByComparator) {
		List<LCSClusterEntry> list = findByLPI_ST(lcsProjectId,
				subscriptionType, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last l c s cluster entry in the ordered set where lcsProjectId = &#63; and subscriptionType = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param subscriptionType the subscription type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s cluster entry
	 * @throws NoSuchLCSClusterEntryException if a matching l c s cluster entry could not be found
	 */
	@Override
	public LCSClusterEntry findByLPI_ST_Last(long lcsProjectId,
		String subscriptionType,
		OrderByComparator<LCSClusterEntry> orderByComparator)
		throws NoSuchLCSClusterEntryException {
		LCSClusterEntry lcsClusterEntry = fetchByLPI_ST_Last(lcsProjectId,
				subscriptionType, orderByComparator);

		if (lcsClusterEntry != null) {
			return lcsClusterEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("lcsProjectId=");
		msg.append(lcsProjectId);

		msg.append(", subscriptionType=");
		msg.append(subscriptionType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSClusterEntryException(msg.toString());
	}

	/**
	 * Returns the last l c s cluster entry in the ordered set where lcsProjectId = &#63; and subscriptionType = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param subscriptionType the subscription type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s cluster entry, or <code>null</code> if a matching l c s cluster entry could not be found
	 */
	@Override
	public LCSClusterEntry fetchByLPI_ST_Last(long lcsProjectId,
		String subscriptionType,
		OrderByComparator<LCSClusterEntry> orderByComparator) {
		int count = countByLPI_ST(lcsProjectId, subscriptionType);

		if (count == 0) {
			return null;
		}

		List<LCSClusterEntry> list = findByLPI_ST(lcsProjectId,
				subscriptionType, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the l c s cluster entries before and after the current l c s cluster entry in the ordered set where lcsProjectId = &#63; and subscriptionType = &#63;.
	 *
	 * @param lcsClusterEntryId the primary key of the current l c s cluster entry
	 * @param lcsProjectId the lcs project ID
	 * @param subscriptionType the subscription type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next l c s cluster entry
	 * @throws NoSuchLCSClusterEntryException if a l c s cluster entry with the primary key could not be found
	 */
	@Override
	public LCSClusterEntry[] findByLPI_ST_PrevAndNext(long lcsClusterEntryId,
		long lcsProjectId, String subscriptionType,
		OrderByComparator<LCSClusterEntry> orderByComparator)
		throws NoSuchLCSClusterEntryException {
		LCSClusterEntry lcsClusterEntry = findByPrimaryKey(lcsClusterEntryId);

		Session session = null;

		try {
			session = openSession();

			LCSClusterEntry[] array = new LCSClusterEntryImpl[3];

			array[0] = getByLPI_ST_PrevAndNext(session, lcsClusterEntry,
					lcsProjectId, subscriptionType, orderByComparator, true);

			array[1] = lcsClusterEntry;

			array[2] = getByLPI_ST_PrevAndNext(session, lcsClusterEntry,
					lcsProjectId, subscriptionType, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LCSClusterEntry getByLPI_ST_PrevAndNext(Session session,
		LCSClusterEntry lcsClusterEntry, long lcsProjectId,
		String subscriptionType,
		OrderByComparator<LCSClusterEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_LCSCLUSTERENTRY_WHERE);

		query.append(_FINDER_COLUMN_LPI_ST_LCSPROJECTID_2);

		boolean bindSubscriptionType = false;

		if (subscriptionType == null) {
			query.append(_FINDER_COLUMN_LPI_ST_SUBSCRIPTIONTYPE_1);
		}
		else if (subscriptionType.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_LPI_ST_SUBSCRIPTIONTYPE_3);
		}
		else {
			bindSubscriptionType = true;

			query.append(_FINDER_COLUMN_LPI_ST_SUBSCRIPTIONTYPE_2);
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
			query.append(LCSClusterEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(lcsProjectId);

		if (bindSubscriptionType) {
			qPos.add(subscriptionType);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(lcsClusterEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LCSClusterEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the l c s cluster entries where lcsProjectId = &#63; and subscriptionType = &#63; from the database.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param subscriptionType the subscription type
	 */
	@Override
	public void removeByLPI_ST(long lcsProjectId, String subscriptionType) {
		for (LCSClusterEntry lcsClusterEntry : findByLPI_ST(lcsProjectId,
				subscriptionType, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(lcsClusterEntry);
		}
	}

	/**
	 * Returns the number of l c s cluster entries where lcsProjectId = &#63; and subscriptionType = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param subscriptionType the subscription type
	 * @return the number of matching l c s cluster entries
	 */
	@Override
	public int countByLPI_ST(long lcsProjectId, String subscriptionType) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_LPI_ST;

		Object[] finderArgs = new Object[] { lcsProjectId, subscriptionType };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LCSCLUSTERENTRY_WHERE);

			query.append(_FINDER_COLUMN_LPI_ST_LCSPROJECTID_2);

			boolean bindSubscriptionType = false;

			if (subscriptionType == null) {
				query.append(_FINDER_COLUMN_LPI_ST_SUBSCRIPTIONTYPE_1);
			}
			else if (subscriptionType.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_LPI_ST_SUBSCRIPTIONTYPE_3);
			}
			else {
				bindSubscriptionType = true;

				query.append(_FINDER_COLUMN_LPI_ST_SUBSCRIPTIONTYPE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(lcsProjectId);

				if (bindSubscriptionType) {
					qPos.add(subscriptionType);
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

	private static final String _FINDER_COLUMN_LPI_ST_LCSPROJECTID_2 = "lcsClusterEntry.lcsProjectId = ? AND ";
	private static final String _FINDER_COLUMN_LPI_ST_SUBSCRIPTIONTYPE_1 = "lcsClusterEntry.subscriptionType IS NULL";
	private static final String _FINDER_COLUMN_LPI_ST_SUBSCRIPTIONTYPE_2 = "lcsClusterEntry.subscriptionType = ?";
	private static final String _FINDER_COLUMN_LPI_ST_SUBSCRIPTIONTYPE_3 = "(lcsClusterEntry.subscriptionType IS NULL OR lcsClusterEntry.subscriptionType = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_LPI_N_A = new FinderPath(LCSClusterEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterEntryModelImpl.FINDER_CACHE_ENABLED,
			LCSClusterEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByLPI_N_A",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LPI_N_A =
		new FinderPath(LCSClusterEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterEntryModelImpl.FINDER_CACHE_ENABLED,
			LCSClusterEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByLPI_N_A",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName()
			},
			LCSClusterEntryModelImpl.LCSPROJECTID_COLUMN_BITMASK |
			LCSClusterEntryModelImpl.NAME_COLUMN_BITMASK |
			LCSClusterEntryModelImpl.ARCHIVED_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_LPI_N_A = new FinderPath(LCSClusterEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByLPI_N_A",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName()
			});

	/**
	 * Returns all the l c s cluster entries where lcsProjectId = &#63; and name = &#63; and archived = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param name the name
	 * @param archived the archived
	 * @return the matching l c s cluster entries
	 */
	@Override
	public List<LCSClusterEntry> findByLPI_N_A(long lcsProjectId, String name,
		boolean archived) {
		return findByLPI_N_A(lcsProjectId, name, archived, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the l c s cluster entries where lcsProjectId = &#63; and name = &#63; and archived = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param name the name
	 * @param archived the archived
	 * @param start the lower bound of the range of l c s cluster entries
	 * @param end the upper bound of the range of l c s cluster entries (not inclusive)
	 * @return the range of matching l c s cluster entries
	 */
	@Override
	public List<LCSClusterEntry> findByLPI_N_A(long lcsProjectId, String name,
		boolean archived, int start, int end) {
		return findByLPI_N_A(lcsProjectId, name, archived, start, end, null);
	}

	/**
	 * Returns an ordered range of all the l c s cluster entries where lcsProjectId = &#63; and name = &#63; and archived = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param name the name
	 * @param archived the archived
	 * @param start the lower bound of the range of l c s cluster entries
	 * @param end the upper bound of the range of l c s cluster entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching l c s cluster entries
	 */
	@Override
	public List<LCSClusterEntry> findByLPI_N_A(long lcsProjectId, String name,
		boolean archived, int start, int end,
		OrderByComparator<LCSClusterEntry> orderByComparator) {
		return findByLPI_N_A(lcsProjectId, name, archived, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the l c s cluster entries where lcsProjectId = &#63; and name = &#63; and archived = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param name the name
	 * @param archived the archived
	 * @param start the lower bound of the range of l c s cluster entries
	 * @param end the upper bound of the range of l c s cluster entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching l c s cluster entries
	 */
	@Override
	public List<LCSClusterEntry> findByLPI_N_A(long lcsProjectId, String name,
		boolean archived, int start, int end,
		OrderByComparator<LCSClusterEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LPI_N_A;
			finderArgs = new Object[] { lcsProjectId, name, archived };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_LPI_N_A;
			finderArgs = new Object[] {
					lcsProjectId, name, archived,
					
					start, end, orderByComparator
				};
		}

		List<LCSClusterEntry> list = null;

		if (retrieveFromCache) {
			list = (List<LCSClusterEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LCSClusterEntry lcsClusterEntry : list) {
					if ((lcsProjectId != lcsClusterEntry.getLcsProjectId()) ||
							!Objects.equals(name, lcsClusterEntry.getName()) ||
							(archived != lcsClusterEntry.getArchived())) {
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

			query.append(_SQL_SELECT_LCSCLUSTERENTRY_WHERE);

			query.append(_FINDER_COLUMN_LPI_N_A_LCSPROJECTID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_LPI_N_A_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_LPI_N_A_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_LPI_N_A_NAME_2);
			}

			query.append(_FINDER_COLUMN_LPI_N_A_ARCHIVED_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LCSClusterEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(lcsProjectId);

				if (bindName) {
					qPos.add(name);
				}

				qPos.add(archived);

				if (!pagination) {
					list = (List<LCSClusterEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSClusterEntry>)QueryUtil.list(q,
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
	 * Returns the first l c s cluster entry in the ordered set where lcsProjectId = &#63; and name = &#63; and archived = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param name the name
	 * @param archived the archived
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s cluster entry
	 * @throws NoSuchLCSClusterEntryException if a matching l c s cluster entry could not be found
	 */
	@Override
	public LCSClusterEntry findByLPI_N_A_First(long lcsProjectId, String name,
		boolean archived, OrderByComparator<LCSClusterEntry> orderByComparator)
		throws NoSuchLCSClusterEntryException {
		LCSClusterEntry lcsClusterEntry = fetchByLPI_N_A_First(lcsProjectId,
				name, archived, orderByComparator);

		if (lcsClusterEntry != null) {
			return lcsClusterEntry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("lcsProjectId=");
		msg.append(lcsProjectId);

		msg.append(", name=");
		msg.append(name);

		msg.append(", archived=");
		msg.append(archived);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSClusterEntryException(msg.toString());
	}

	/**
	 * Returns the first l c s cluster entry in the ordered set where lcsProjectId = &#63; and name = &#63; and archived = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param name the name
	 * @param archived the archived
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s cluster entry, or <code>null</code> if a matching l c s cluster entry could not be found
	 */
	@Override
	public LCSClusterEntry fetchByLPI_N_A_First(long lcsProjectId, String name,
		boolean archived, OrderByComparator<LCSClusterEntry> orderByComparator) {
		List<LCSClusterEntry> list = findByLPI_N_A(lcsProjectId, name,
				archived, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last l c s cluster entry in the ordered set where lcsProjectId = &#63; and name = &#63; and archived = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param name the name
	 * @param archived the archived
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s cluster entry
	 * @throws NoSuchLCSClusterEntryException if a matching l c s cluster entry could not be found
	 */
	@Override
	public LCSClusterEntry findByLPI_N_A_Last(long lcsProjectId, String name,
		boolean archived, OrderByComparator<LCSClusterEntry> orderByComparator)
		throws NoSuchLCSClusterEntryException {
		LCSClusterEntry lcsClusterEntry = fetchByLPI_N_A_Last(lcsProjectId,
				name, archived, orderByComparator);

		if (lcsClusterEntry != null) {
			return lcsClusterEntry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("lcsProjectId=");
		msg.append(lcsProjectId);

		msg.append(", name=");
		msg.append(name);

		msg.append(", archived=");
		msg.append(archived);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSClusterEntryException(msg.toString());
	}

	/**
	 * Returns the last l c s cluster entry in the ordered set where lcsProjectId = &#63; and name = &#63; and archived = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param name the name
	 * @param archived the archived
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s cluster entry, or <code>null</code> if a matching l c s cluster entry could not be found
	 */
	@Override
	public LCSClusterEntry fetchByLPI_N_A_Last(long lcsProjectId, String name,
		boolean archived, OrderByComparator<LCSClusterEntry> orderByComparator) {
		int count = countByLPI_N_A(lcsProjectId, name, archived);

		if (count == 0) {
			return null;
		}

		List<LCSClusterEntry> list = findByLPI_N_A(lcsProjectId, name,
				archived, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the l c s cluster entries before and after the current l c s cluster entry in the ordered set where lcsProjectId = &#63; and name = &#63; and archived = &#63;.
	 *
	 * @param lcsClusterEntryId the primary key of the current l c s cluster entry
	 * @param lcsProjectId the lcs project ID
	 * @param name the name
	 * @param archived the archived
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next l c s cluster entry
	 * @throws NoSuchLCSClusterEntryException if a l c s cluster entry with the primary key could not be found
	 */
	@Override
	public LCSClusterEntry[] findByLPI_N_A_PrevAndNext(long lcsClusterEntryId,
		long lcsProjectId, String name, boolean archived,
		OrderByComparator<LCSClusterEntry> orderByComparator)
		throws NoSuchLCSClusterEntryException {
		LCSClusterEntry lcsClusterEntry = findByPrimaryKey(lcsClusterEntryId);

		Session session = null;

		try {
			session = openSession();

			LCSClusterEntry[] array = new LCSClusterEntryImpl[3];

			array[0] = getByLPI_N_A_PrevAndNext(session, lcsClusterEntry,
					lcsProjectId, name, archived, orderByComparator, true);

			array[1] = lcsClusterEntry;

			array[2] = getByLPI_N_A_PrevAndNext(session, lcsClusterEntry,
					lcsProjectId, name, archived, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LCSClusterEntry getByLPI_N_A_PrevAndNext(Session session,
		LCSClusterEntry lcsClusterEntry, long lcsProjectId, String name,
		boolean archived, OrderByComparator<LCSClusterEntry> orderByComparator,
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

		query.append(_SQL_SELECT_LCSCLUSTERENTRY_WHERE);

		query.append(_FINDER_COLUMN_LPI_N_A_LCSPROJECTID_2);

		boolean bindName = false;

		if (name == null) {
			query.append(_FINDER_COLUMN_LPI_N_A_NAME_1);
		}
		else if (name.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_LPI_N_A_NAME_3);
		}
		else {
			bindName = true;

			query.append(_FINDER_COLUMN_LPI_N_A_NAME_2);
		}

		query.append(_FINDER_COLUMN_LPI_N_A_ARCHIVED_2);

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
			query.append(LCSClusterEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(lcsProjectId);

		if (bindName) {
			qPos.add(name);
		}

		qPos.add(archived);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(lcsClusterEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LCSClusterEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the l c s cluster entries where lcsProjectId = &#63; and name = &#63; and archived = &#63; from the database.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param name the name
	 * @param archived the archived
	 */
	@Override
	public void removeByLPI_N_A(long lcsProjectId, String name, boolean archived) {
		for (LCSClusterEntry lcsClusterEntry : findByLPI_N_A(lcsProjectId,
				name, archived, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(lcsClusterEntry);
		}
	}

	/**
	 * Returns the number of l c s cluster entries where lcsProjectId = &#63; and name = &#63; and archived = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param name the name
	 * @param archived the archived
	 * @return the number of matching l c s cluster entries
	 */
	@Override
	public int countByLPI_N_A(long lcsProjectId, String name, boolean archived) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_LPI_N_A;

		Object[] finderArgs = new Object[] { lcsProjectId, name, archived };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_LCSCLUSTERENTRY_WHERE);

			query.append(_FINDER_COLUMN_LPI_N_A_LCSPROJECTID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_LPI_N_A_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_LPI_N_A_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_LPI_N_A_NAME_2);
			}

			query.append(_FINDER_COLUMN_LPI_N_A_ARCHIVED_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(lcsProjectId);

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

	private static final String _FINDER_COLUMN_LPI_N_A_LCSPROJECTID_2 = "lcsClusterEntry.lcsProjectId = ? AND ";
	private static final String _FINDER_COLUMN_LPI_N_A_NAME_1 = "lcsClusterEntry.name IS NULL AND ";
	private static final String _FINDER_COLUMN_LPI_N_A_NAME_2 = "lcsClusterEntry.name = ? AND ";
	private static final String _FINDER_COLUMN_LPI_N_A_NAME_3 = "(lcsClusterEntry.name IS NULL OR lcsClusterEntry.name = '') AND ";
	private static final String _FINDER_COLUMN_LPI_N_A_ARCHIVED_2 = "lcsClusterEntry.archived = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_LPI_ST_A = new FinderPath(LCSClusterEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterEntryModelImpl.FINDER_CACHE_ENABLED,
			LCSClusterEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByLPI_ST_A",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LPI_ST_A =
		new FinderPath(LCSClusterEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterEntryModelImpl.FINDER_CACHE_ENABLED,
			LCSClusterEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByLPI_ST_A",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName()
			},
			LCSClusterEntryModelImpl.LCSPROJECTID_COLUMN_BITMASK |
			LCSClusterEntryModelImpl.SUBSCRIPTIONTYPE_COLUMN_BITMASK |
			LCSClusterEntryModelImpl.ARCHIVED_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_LPI_ST_A = new FinderPath(LCSClusterEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByLPI_ST_A",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName()
			});

	/**
	 * Returns all the l c s cluster entries where lcsProjectId = &#63; and subscriptionType = &#63; and archived = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param subscriptionType the subscription type
	 * @param archived the archived
	 * @return the matching l c s cluster entries
	 */
	@Override
	public List<LCSClusterEntry> findByLPI_ST_A(long lcsProjectId,
		String subscriptionType, boolean archived) {
		return findByLPI_ST_A(lcsProjectId, subscriptionType, archived,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the l c s cluster entries where lcsProjectId = &#63; and subscriptionType = &#63; and archived = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param subscriptionType the subscription type
	 * @param archived the archived
	 * @param start the lower bound of the range of l c s cluster entries
	 * @param end the upper bound of the range of l c s cluster entries (not inclusive)
	 * @return the range of matching l c s cluster entries
	 */
	@Override
	public List<LCSClusterEntry> findByLPI_ST_A(long lcsProjectId,
		String subscriptionType, boolean archived, int start, int end) {
		return findByLPI_ST_A(lcsProjectId, subscriptionType, archived, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the l c s cluster entries where lcsProjectId = &#63; and subscriptionType = &#63; and archived = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param subscriptionType the subscription type
	 * @param archived the archived
	 * @param start the lower bound of the range of l c s cluster entries
	 * @param end the upper bound of the range of l c s cluster entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching l c s cluster entries
	 */
	@Override
	public List<LCSClusterEntry> findByLPI_ST_A(long lcsProjectId,
		String subscriptionType, boolean archived, int start, int end,
		OrderByComparator<LCSClusterEntry> orderByComparator) {
		return findByLPI_ST_A(lcsProjectId, subscriptionType, archived, start,
			end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the l c s cluster entries where lcsProjectId = &#63; and subscriptionType = &#63; and archived = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param subscriptionType the subscription type
	 * @param archived the archived
	 * @param start the lower bound of the range of l c s cluster entries
	 * @param end the upper bound of the range of l c s cluster entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching l c s cluster entries
	 */
	@Override
	public List<LCSClusterEntry> findByLPI_ST_A(long lcsProjectId,
		String subscriptionType, boolean archived, int start, int end,
		OrderByComparator<LCSClusterEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LPI_ST_A;
			finderArgs = new Object[] { lcsProjectId, subscriptionType, archived };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_LPI_ST_A;
			finderArgs = new Object[] {
					lcsProjectId, subscriptionType, archived,
					
					start, end, orderByComparator
				};
		}

		List<LCSClusterEntry> list = null;

		if (retrieveFromCache) {
			list = (List<LCSClusterEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LCSClusterEntry lcsClusterEntry : list) {
					if ((lcsProjectId != lcsClusterEntry.getLcsProjectId()) ||
							!Objects.equals(subscriptionType,
								lcsClusterEntry.getSubscriptionType()) ||
							(archived != lcsClusterEntry.getArchived())) {
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

			query.append(_SQL_SELECT_LCSCLUSTERENTRY_WHERE);

			query.append(_FINDER_COLUMN_LPI_ST_A_LCSPROJECTID_2);

			boolean bindSubscriptionType = false;

			if (subscriptionType == null) {
				query.append(_FINDER_COLUMN_LPI_ST_A_SUBSCRIPTIONTYPE_1);
			}
			else if (subscriptionType.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_LPI_ST_A_SUBSCRIPTIONTYPE_3);
			}
			else {
				bindSubscriptionType = true;

				query.append(_FINDER_COLUMN_LPI_ST_A_SUBSCRIPTIONTYPE_2);
			}

			query.append(_FINDER_COLUMN_LPI_ST_A_ARCHIVED_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LCSClusterEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(lcsProjectId);

				if (bindSubscriptionType) {
					qPos.add(subscriptionType);
				}

				qPos.add(archived);

				if (!pagination) {
					list = (List<LCSClusterEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSClusterEntry>)QueryUtil.list(q,
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
	 * Returns the first l c s cluster entry in the ordered set where lcsProjectId = &#63; and subscriptionType = &#63; and archived = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param subscriptionType the subscription type
	 * @param archived the archived
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s cluster entry
	 * @throws NoSuchLCSClusterEntryException if a matching l c s cluster entry could not be found
	 */
	@Override
	public LCSClusterEntry findByLPI_ST_A_First(long lcsProjectId,
		String subscriptionType, boolean archived,
		OrderByComparator<LCSClusterEntry> orderByComparator)
		throws NoSuchLCSClusterEntryException {
		LCSClusterEntry lcsClusterEntry = fetchByLPI_ST_A_First(lcsProjectId,
				subscriptionType, archived, orderByComparator);

		if (lcsClusterEntry != null) {
			return lcsClusterEntry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("lcsProjectId=");
		msg.append(lcsProjectId);

		msg.append(", subscriptionType=");
		msg.append(subscriptionType);

		msg.append(", archived=");
		msg.append(archived);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSClusterEntryException(msg.toString());
	}

	/**
	 * Returns the first l c s cluster entry in the ordered set where lcsProjectId = &#63; and subscriptionType = &#63; and archived = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param subscriptionType the subscription type
	 * @param archived the archived
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s cluster entry, or <code>null</code> if a matching l c s cluster entry could not be found
	 */
	@Override
	public LCSClusterEntry fetchByLPI_ST_A_First(long lcsProjectId,
		String subscriptionType, boolean archived,
		OrderByComparator<LCSClusterEntry> orderByComparator) {
		List<LCSClusterEntry> list = findByLPI_ST_A(lcsProjectId,
				subscriptionType, archived, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last l c s cluster entry in the ordered set where lcsProjectId = &#63; and subscriptionType = &#63; and archived = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param subscriptionType the subscription type
	 * @param archived the archived
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s cluster entry
	 * @throws NoSuchLCSClusterEntryException if a matching l c s cluster entry could not be found
	 */
	@Override
	public LCSClusterEntry findByLPI_ST_A_Last(long lcsProjectId,
		String subscriptionType, boolean archived,
		OrderByComparator<LCSClusterEntry> orderByComparator)
		throws NoSuchLCSClusterEntryException {
		LCSClusterEntry lcsClusterEntry = fetchByLPI_ST_A_Last(lcsProjectId,
				subscriptionType, archived, orderByComparator);

		if (lcsClusterEntry != null) {
			return lcsClusterEntry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("lcsProjectId=");
		msg.append(lcsProjectId);

		msg.append(", subscriptionType=");
		msg.append(subscriptionType);

		msg.append(", archived=");
		msg.append(archived);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSClusterEntryException(msg.toString());
	}

	/**
	 * Returns the last l c s cluster entry in the ordered set where lcsProjectId = &#63; and subscriptionType = &#63; and archived = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param subscriptionType the subscription type
	 * @param archived the archived
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s cluster entry, or <code>null</code> if a matching l c s cluster entry could not be found
	 */
	@Override
	public LCSClusterEntry fetchByLPI_ST_A_Last(long lcsProjectId,
		String subscriptionType, boolean archived,
		OrderByComparator<LCSClusterEntry> orderByComparator) {
		int count = countByLPI_ST_A(lcsProjectId, subscriptionType, archived);

		if (count == 0) {
			return null;
		}

		List<LCSClusterEntry> list = findByLPI_ST_A(lcsProjectId,
				subscriptionType, archived, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the l c s cluster entries before and after the current l c s cluster entry in the ordered set where lcsProjectId = &#63; and subscriptionType = &#63; and archived = &#63;.
	 *
	 * @param lcsClusterEntryId the primary key of the current l c s cluster entry
	 * @param lcsProjectId the lcs project ID
	 * @param subscriptionType the subscription type
	 * @param archived the archived
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next l c s cluster entry
	 * @throws NoSuchLCSClusterEntryException if a l c s cluster entry with the primary key could not be found
	 */
	@Override
	public LCSClusterEntry[] findByLPI_ST_A_PrevAndNext(
		long lcsClusterEntryId, long lcsProjectId, String subscriptionType,
		boolean archived, OrderByComparator<LCSClusterEntry> orderByComparator)
		throws NoSuchLCSClusterEntryException {
		LCSClusterEntry lcsClusterEntry = findByPrimaryKey(lcsClusterEntryId);

		Session session = null;

		try {
			session = openSession();

			LCSClusterEntry[] array = new LCSClusterEntryImpl[3];

			array[0] = getByLPI_ST_A_PrevAndNext(session, lcsClusterEntry,
					lcsProjectId, subscriptionType, archived,
					orderByComparator, true);

			array[1] = lcsClusterEntry;

			array[2] = getByLPI_ST_A_PrevAndNext(session, lcsClusterEntry,
					lcsProjectId, subscriptionType, archived,
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

	protected LCSClusterEntry getByLPI_ST_A_PrevAndNext(Session session,
		LCSClusterEntry lcsClusterEntry, long lcsProjectId,
		String subscriptionType, boolean archived,
		OrderByComparator<LCSClusterEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_LCSCLUSTERENTRY_WHERE);

		query.append(_FINDER_COLUMN_LPI_ST_A_LCSPROJECTID_2);

		boolean bindSubscriptionType = false;

		if (subscriptionType == null) {
			query.append(_FINDER_COLUMN_LPI_ST_A_SUBSCRIPTIONTYPE_1);
		}
		else if (subscriptionType.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_LPI_ST_A_SUBSCRIPTIONTYPE_3);
		}
		else {
			bindSubscriptionType = true;

			query.append(_FINDER_COLUMN_LPI_ST_A_SUBSCRIPTIONTYPE_2);
		}

		query.append(_FINDER_COLUMN_LPI_ST_A_ARCHIVED_2);

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
			query.append(LCSClusterEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(lcsProjectId);

		if (bindSubscriptionType) {
			qPos.add(subscriptionType);
		}

		qPos.add(archived);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(lcsClusterEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LCSClusterEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the l c s cluster entries where lcsProjectId = &#63; and subscriptionType = &#63; and archived = &#63; from the database.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param subscriptionType the subscription type
	 * @param archived the archived
	 */
	@Override
	public void removeByLPI_ST_A(long lcsProjectId, String subscriptionType,
		boolean archived) {
		for (LCSClusterEntry lcsClusterEntry : findByLPI_ST_A(lcsProjectId,
				subscriptionType, archived, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(lcsClusterEntry);
		}
	}

	/**
	 * Returns the number of l c s cluster entries where lcsProjectId = &#63; and subscriptionType = &#63; and archived = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param subscriptionType the subscription type
	 * @param archived the archived
	 * @return the number of matching l c s cluster entries
	 */
	@Override
	public int countByLPI_ST_A(long lcsProjectId, String subscriptionType,
		boolean archived) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_LPI_ST_A;

		Object[] finderArgs = new Object[] {
				lcsProjectId, subscriptionType, archived
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_LCSCLUSTERENTRY_WHERE);

			query.append(_FINDER_COLUMN_LPI_ST_A_LCSPROJECTID_2);

			boolean bindSubscriptionType = false;

			if (subscriptionType == null) {
				query.append(_FINDER_COLUMN_LPI_ST_A_SUBSCRIPTIONTYPE_1);
			}
			else if (subscriptionType.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_LPI_ST_A_SUBSCRIPTIONTYPE_3);
			}
			else {
				bindSubscriptionType = true;

				query.append(_FINDER_COLUMN_LPI_ST_A_SUBSCRIPTIONTYPE_2);
			}

			query.append(_FINDER_COLUMN_LPI_ST_A_ARCHIVED_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(lcsProjectId);

				if (bindSubscriptionType) {
					qPos.add(subscriptionType);
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

	private static final String _FINDER_COLUMN_LPI_ST_A_LCSPROJECTID_2 = "lcsClusterEntry.lcsProjectId = ? AND ";
	private static final String _FINDER_COLUMN_LPI_ST_A_SUBSCRIPTIONTYPE_1 = "lcsClusterEntry.subscriptionType IS NULL AND ";
	private static final String _FINDER_COLUMN_LPI_ST_A_SUBSCRIPTIONTYPE_2 = "lcsClusterEntry.subscriptionType = ? AND ";
	private static final String _FINDER_COLUMN_LPI_ST_A_SUBSCRIPTIONTYPE_3 = "(lcsClusterEntry.subscriptionType IS NULL OR lcsClusterEntry.subscriptionType = '') AND ";
	private static final String _FINDER_COLUMN_LPI_ST_A_ARCHIVED_2 = "lcsClusterEntry.archived = ?";

	public LCSClusterEntryPersistenceImpl() {
		setModelClass(LCSClusterEntry.class);
	}

	/**
	 * Caches the l c s cluster entry in the entity cache if it is enabled.
	 *
	 * @param lcsClusterEntry the l c s cluster entry
	 */
	@Override
	public void cacheResult(LCSClusterEntry lcsClusterEntry) {
		entityCache.putResult(LCSClusterEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterEntryImpl.class, lcsClusterEntry.getPrimaryKey(),
			lcsClusterEntry);

		lcsClusterEntry.resetOriginalValues();
	}

	/**
	 * Caches the l c s cluster entries in the entity cache if it is enabled.
	 *
	 * @param lcsClusterEntries the l c s cluster entries
	 */
	@Override
	public void cacheResult(List<LCSClusterEntry> lcsClusterEntries) {
		for (LCSClusterEntry lcsClusterEntry : lcsClusterEntries) {
			if (entityCache.getResult(
						LCSClusterEntryModelImpl.ENTITY_CACHE_ENABLED,
						LCSClusterEntryImpl.class,
						lcsClusterEntry.getPrimaryKey()) == null) {
				cacheResult(lcsClusterEntry);
			}
			else {
				lcsClusterEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all l c s cluster entries.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(LCSClusterEntryImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the l c s cluster entry.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LCSClusterEntry lcsClusterEntry) {
		entityCache.removeResult(LCSClusterEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterEntryImpl.class, lcsClusterEntry.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<LCSClusterEntry> lcsClusterEntries) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (LCSClusterEntry lcsClusterEntry : lcsClusterEntries) {
			entityCache.removeResult(LCSClusterEntryModelImpl.ENTITY_CACHE_ENABLED,
				LCSClusterEntryImpl.class, lcsClusterEntry.getPrimaryKey());
		}
	}

	/**
	 * Creates a new l c s cluster entry with the primary key. Does not add the l c s cluster entry to the database.
	 *
	 * @param lcsClusterEntryId the primary key for the new l c s cluster entry
	 * @return the new l c s cluster entry
	 */
	@Override
	public LCSClusterEntry create(long lcsClusterEntryId) {
		LCSClusterEntry lcsClusterEntry = new LCSClusterEntryImpl();

		lcsClusterEntry.setNew(true);
		lcsClusterEntry.setPrimaryKey(lcsClusterEntryId);

		return lcsClusterEntry;
	}

	/**
	 * Removes the l c s cluster entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param lcsClusterEntryId the primary key of the l c s cluster entry
	 * @return the l c s cluster entry that was removed
	 * @throws NoSuchLCSClusterEntryException if a l c s cluster entry with the primary key could not be found
	 */
	@Override
	public LCSClusterEntry remove(long lcsClusterEntryId)
		throws NoSuchLCSClusterEntryException {
		return remove((Serializable)lcsClusterEntryId);
	}

	/**
	 * Removes the l c s cluster entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the l c s cluster entry
	 * @return the l c s cluster entry that was removed
	 * @throws NoSuchLCSClusterEntryException if a l c s cluster entry with the primary key could not be found
	 */
	@Override
	public LCSClusterEntry remove(Serializable primaryKey)
		throws NoSuchLCSClusterEntryException {
		Session session = null;

		try {
			session = openSession();

			LCSClusterEntry lcsClusterEntry = (LCSClusterEntry)session.get(LCSClusterEntryImpl.class,
					primaryKey);

			if (lcsClusterEntry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLCSClusterEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(lcsClusterEntry);
		}
		catch (NoSuchLCSClusterEntryException nsee) {
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
	protected LCSClusterEntry removeImpl(LCSClusterEntry lcsClusterEntry) {
		lcsClusterEntry = toUnwrappedModel(lcsClusterEntry);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(lcsClusterEntry)) {
				lcsClusterEntry = (LCSClusterEntry)session.get(LCSClusterEntryImpl.class,
						lcsClusterEntry.getPrimaryKeyObj());
			}

			if (lcsClusterEntry != null) {
				session.delete(lcsClusterEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (lcsClusterEntry != null) {
			clearCache(lcsClusterEntry);
		}

		return lcsClusterEntry;
	}

	@Override
	public LCSClusterEntry updateImpl(LCSClusterEntry lcsClusterEntry) {
		lcsClusterEntry = toUnwrappedModel(lcsClusterEntry);

		boolean isNew = lcsClusterEntry.isNew();

		LCSClusterEntryModelImpl lcsClusterEntryModelImpl = (LCSClusterEntryModelImpl)lcsClusterEntry;

		Session session = null;

		try {
			session = openSession();

			if (lcsClusterEntry.isNew()) {
				session.save(lcsClusterEntry);

				lcsClusterEntry.setNew(false);
			}
			else {
				lcsClusterEntry = (LCSClusterEntry)session.merge(lcsClusterEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !LCSClusterEntryModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((lcsClusterEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LCSPROJECTID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						lcsClusterEntryModelImpl.getOriginalLcsProjectId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_LCSPROJECTID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LCSPROJECTID,
					args);

				args = new Object[] { lcsClusterEntryModelImpl.getLcsProjectId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_LCSPROJECTID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LCSPROJECTID,
					args);
			}

			if ((lcsClusterEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LPI_N.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						lcsClusterEntryModelImpl.getOriginalLcsProjectId(),
						lcsClusterEntryModelImpl.getOriginalName()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_LPI_N, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LPI_N,
					args);

				args = new Object[] {
						lcsClusterEntryModelImpl.getLcsProjectId(),
						lcsClusterEntryModelImpl.getName()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_LPI_N, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LPI_N,
					args);
			}

			if ((lcsClusterEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LPI_ST.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						lcsClusterEntryModelImpl.getOriginalLcsProjectId(),
						lcsClusterEntryModelImpl.getOriginalSubscriptionType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_LPI_ST, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LPI_ST,
					args);

				args = new Object[] {
						lcsClusterEntryModelImpl.getLcsProjectId(),
						lcsClusterEntryModelImpl.getSubscriptionType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_LPI_ST, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LPI_ST,
					args);
			}

			if ((lcsClusterEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LPI_N_A.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						lcsClusterEntryModelImpl.getOriginalLcsProjectId(),
						lcsClusterEntryModelImpl.getOriginalName(),
						lcsClusterEntryModelImpl.getOriginalArchived()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_LPI_N_A, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LPI_N_A,
					args);

				args = new Object[] {
						lcsClusterEntryModelImpl.getLcsProjectId(),
						lcsClusterEntryModelImpl.getName(),
						lcsClusterEntryModelImpl.getArchived()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_LPI_N_A, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LPI_N_A,
					args);
			}

			if ((lcsClusterEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LPI_ST_A.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						lcsClusterEntryModelImpl.getOriginalLcsProjectId(),
						lcsClusterEntryModelImpl.getOriginalSubscriptionType(),
						lcsClusterEntryModelImpl.getOriginalArchived()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_LPI_ST_A, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LPI_ST_A,
					args);

				args = new Object[] {
						lcsClusterEntryModelImpl.getLcsProjectId(),
						lcsClusterEntryModelImpl.getSubscriptionType(),
						lcsClusterEntryModelImpl.getArchived()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_LPI_ST_A, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LPI_ST_A,
					args);
			}
		}

		entityCache.putResult(LCSClusterEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterEntryImpl.class, lcsClusterEntry.getPrimaryKey(),
			lcsClusterEntry, false);

		lcsClusterEntry.resetOriginalValues();

		return lcsClusterEntry;
	}

	protected LCSClusterEntry toUnwrappedModel(LCSClusterEntry lcsClusterEntry) {
		if (lcsClusterEntry instanceof LCSClusterEntryImpl) {
			return lcsClusterEntry;
		}

		LCSClusterEntryImpl lcsClusterEntryImpl = new LCSClusterEntryImpl();

		lcsClusterEntryImpl.setNew(lcsClusterEntry.isNew());
		lcsClusterEntryImpl.setPrimaryKey(lcsClusterEntry.getPrimaryKey());

		lcsClusterEntryImpl.setLcsClusterEntryId(lcsClusterEntry.getLcsClusterEntryId());
		lcsClusterEntryImpl.setLcsProjectId(lcsClusterEntry.getLcsProjectId());
		lcsClusterEntryImpl.setName(lcsClusterEntry.getName());
		lcsClusterEntryImpl.setDescription(lcsClusterEntry.getDescription());
		lcsClusterEntryImpl.setElastic(lcsClusterEntry.isElastic());
		lcsClusterEntryImpl.setHighPageLoadTime(lcsClusterEntry.getHighPageLoadTime());
		lcsClusterEntryImpl.setLocation(lcsClusterEntry.getLocation());
		lcsClusterEntryImpl.setMediumPageLoadTime(lcsClusterEntry.getMediumPageLoadTime());
		lcsClusterEntryImpl.setSubscriptionType(lcsClusterEntry.getSubscriptionType());
		lcsClusterEntryImpl.setType(lcsClusterEntry.getType());
		lcsClusterEntryImpl.setArchived(lcsClusterEntry.isArchived());

		return lcsClusterEntryImpl;
	}

	/**
	 * Returns the l c s cluster entry with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the l c s cluster entry
	 * @return the l c s cluster entry
	 * @throws NoSuchLCSClusterEntryException if a l c s cluster entry with the primary key could not be found
	 */
	@Override
	public LCSClusterEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLCSClusterEntryException {
		LCSClusterEntry lcsClusterEntry = fetchByPrimaryKey(primaryKey);

		if (lcsClusterEntry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLCSClusterEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return lcsClusterEntry;
	}

	/**
	 * Returns the l c s cluster entry with the primary key or throws a {@link NoSuchLCSClusterEntryException} if it could not be found.
	 *
	 * @param lcsClusterEntryId the primary key of the l c s cluster entry
	 * @return the l c s cluster entry
	 * @throws NoSuchLCSClusterEntryException if a l c s cluster entry with the primary key could not be found
	 */
	@Override
	public LCSClusterEntry findByPrimaryKey(long lcsClusterEntryId)
		throws NoSuchLCSClusterEntryException {
		return findByPrimaryKey((Serializable)lcsClusterEntryId);
	}

	/**
	 * Returns the l c s cluster entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the l c s cluster entry
	 * @return the l c s cluster entry, or <code>null</code> if a l c s cluster entry with the primary key could not be found
	 */
	@Override
	public LCSClusterEntry fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(LCSClusterEntryModelImpl.ENTITY_CACHE_ENABLED,
				LCSClusterEntryImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		LCSClusterEntry lcsClusterEntry = (LCSClusterEntry)serializable;

		if (lcsClusterEntry == null) {
			Session session = null;

			try {
				session = openSession();

				lcsClusterEntry = (LCSClusterEntry)session.get(LCSClusterEntryImpl.class,
						primaryKey);

				if (lcsClusterEntry != null) {
					cacheResult(lcsClusterEntry);
				}
				else {
					entityCache.putResult(LCSClusterEntryModelImpl.ENTITY_CACHE_ENABLED,
						LCSClusterEntryImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(LCSClusterEntryModelImpl.ENTITY_CACHE_ENABLED,
					LCSClusterEntryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return lcsClusterEntry;
	}

	/**
	 * Returns the l c s cluster entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param lcsClusterEntryId the primary key of the l c s cluster entry
	 * @return the l c s cluster entry, or <code>null</code> if a l c s cluster entry with the primary key could not be found
	 */
	@Override
	public LCSClusterEntry fetchByPrimaryKey(long lcsClusterEntryId) {
		return fetchByPrimaryKey((Serializable)lcsClusterEntryId);
	}

	@Override
	public Map<Serializable, LCSClusterEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, LCSClusterEntry> map = new HashMap<Serializable, LCSClusterEntry>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			LCSClusterEntry lcsClusterEntry = fetchByPrimaryKey(primaryKey);

			if (lcsClusterEntry != null) {
				map.put(primaryKey, lcsClusterEntry);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(LCSClusterEntryModelImpl.ENTITY_CACHE_ENABLED,
					LCSClusterEntryImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (LCSClusterEntry)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_LCSCLUSTERENTRY_WHERE_PKS_IN);

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

			for (LCSClusterEntry lcsClusterEntry : (List<LCSClusterEntry>)q.list()) {
				map.put(lcsClusterEntry.getPrimaryKeyObj(), lcsClusterEntry);

				cacheResult(lcsClusterEntry);

				uncachedPrimaryKeys.remove(lcsClusterEntry.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(LCSClusterEntryModelImpl.ENTITY_CACHE_ENABLED,
					LCSClusterEntryImpl.class, primaryKey, nullModel);
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
	 * Returns all the l c s cluster entries.
	 *
	 * @return the l c s cluster entries
	 */
	@Override
	public List<LCSClusterEntry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the l c s cluster entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of l c s cluster entries
	 * @param end the upper bound of the range of l c s cluster entries (not inclusive)
	 * @return the range of l c s cluster entries
	 */
	@Override
	public List<LCSClusterEntry> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the l c s cluster entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of l c s cluster entries
	 * @param end the upper bound of the range of l c s cluster entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of l c s cluster entries
	 */
	@Override
	public List<LCSClusterEntry> findAll(int start, int end,
		OrderByComparator<LCSClusterEntry> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the l c s cluster entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of l c s cluster entries
	 * @param end the upper bound of the range of l c s cluster entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of l c s cluster entries
	 */
	@Override
	public List<LCSClusterEntry> findAll(int start, int end,
		OrderByComparator<LCSClusterEntry> orderByComparator,
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

		List<LCSClusterEntry> list = null;

		if (retrieveFromCache) {
			list = (List<LCSClusterEntry>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_LCSCLUSTERENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_LCSCLUSTERENTRY;

				if (pagination) {
					sql = sql.concat(LCSClusterEntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<LCSClusterEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSClusterEntry>)QueryUtil.list(q,
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
	 * Removes all the l c s cluster entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (LCSClusterEntry lcsClusterEntry : findAll()) {
			remove(lcsClusterEntry);
		}
	}

	/**
	 * Returns the number of l c s cluster entries.
	 *
	 * @return the number of l c s cluster entries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_LCSCLUSTERENTRY);

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
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return LCSClusterEntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the l c s cluster entry persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(LCSClusterEntryImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_LCSCLUSTERENTRY = "SELECT lcsClusterEntry FROM LCSClusterEntry lcsClusterEntry";
	private static final String _SQL_SELECT_LCSCLUSTERENTRY_WHERE_PKS_IN = "SELECT lcsClusterEntry FROM LCSClusterEntry lcsClusterEntry WHERE lcsClusterEntryId IN (";
	private static final String _SQL_SELECT_LCSCLUSTERENTRY_WHERE = "SELECT lcsClusterEntry FROM LCSClusterEntry lcsClusterEntry WHERE ";
	private static final String _SQL_COUNT_LCSCLUSTERENTRY = "SELECT COUNT(lcsClusterEntry) FROM LCSClusterEntry lcsClusterEntry";
	private static final String _SQL_COUNT_LCSCLUSTERENTRY_WHERE = "SELECT COUNT(lcsClusterEntry) FROM LCSClusterEntry lcsClusterEntry WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "lcsClusterEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No LCSClusterEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No LCSClusterEntry exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(LCSClusterEntryPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"type"
			});
}