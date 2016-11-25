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

import com.liferay.osb.lcs.exception.NoSuchLCSSubscriptionEntryException;
import com.liferay.osb.lcs.model.LCSSubscriptionEntry;
import com.liferay.osb.lcs.model.impl.LCSSubscriptionEntryImpl;
import com.liferay.osb.lcs.model.impl.LCSSubscriptionEntryModelImpl;
import com.liferay.osb.lcs.service.persistence.LCSSubscriptionEntryPersistence;

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
 * The persistence implementation for the l c s subscription entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Igor Beslic
 * @see LCSSubscriptionEntryPersistence
 * @see com.liferay.osb.lcs.service.persistence.LCSSubscriptionEntryUtil
 * @generated
 */
@ProviderType
public class LCSSubscriptionEntryPersistenceImpl extends BasePersistenceImpl<LCSSubscriptionEntry>
	implements LCSSubscriptionEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link LCSSubscriptionEntryUtil} to access the l c s subscription entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = LCSSubscriptionEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(LCSSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED,
			LCSSubscriptionEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(LCSSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED,
			LCSSubscriptionEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(LCSSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_LCSPROJECTID =
		new FinderPath(LCSSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED,
			LCSSubscriptionEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByLCSProjectId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LCSPROJECTID =
		new FinderPath(LCSSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED,
			LCSSubscriptionEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByLCSProjectId",
			new String[] { Long.class.getName() },
			LCSSubscriptionEntryModelImpl.LCSPROJECTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_LCSPROJECTID = new FinderPath(LCSSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByLCSProjectId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the l c s subscription entries where lcsProjectId = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @return the matching l c s subscription entries
	 */
	@Override
	public List<LCSSubscriptionEntry> findByLCSProjectId(long lcsProjectId) {
		return findByLCSProjectId(lcsProjectId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the l c s subscription entries where lcsProjectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param start the lower bound of the range of l c s subscription entries
	 * @param end the upper bound of the range of l c s subscription entries (not inclusive)
	 * @return the range of matching l c s subscription entries
	 */
	@Override
	public List<LCSSubscriptionEntry> findByLCSProjectId(long lcsProjectId,
		int start, int end) {
		return findByLCSProjectId(lcsProjectId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the l c s subscription entries where lcsProjectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param start the lower bound of the range of l c s subscription entries
	 * @param end the upper bound of the range of l c s subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching l c s subscription entries
	 */
	@Override
	public List<LCSSubscriptionEntry> findByLCSProjectId(long lcsProjectId,
		int start, int end,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator) {
		return findByLCSProjectId(lcsProjectId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the l c s subscription entries where lcsProjectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param start the lower bound of the range of l c s subscription entries
	 * @param end the upper bound of the range of l c s subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching l c s subscription entries
	 */
	@Override
	public List<LCSSubscriptionEntry> findByLCSProjectId(long lcsProjectId,
		int start, int end,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator,
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

		List<LCSSubscriptionEntry> list = null;

		if (retrieveFromCache) {
			list = (List<LCSSubscriptionEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LCSSubscriptionEntry lcsSubscriptionEntry : list) {
					if ((lcsProjectId != lcsSubscriptionEntry.getLcsProjectId())) {
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

			query.append(_SQL_SELECT_LCSSUBSCRIPTIONENTRY_WHERE);

			query.append(_FINDER_COLUMN_LCSPROJECTID_LCSPROJECTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LCSSubscriptionEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(lcsProjectId);

				if (!pagination) {
					list = (List<LCSSubscriptionEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSSubscriptionEntry>)QueryUtil.list(q,
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
	 * Returns the first l c s subscription entry in the ordered set where lcsProjectId = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s subscription entry
	 * @throws NoSuchLCSSubscriptionEntryException if a matching l c s subscription entry could not be found
	 */
	@Override
	public LCSSubscriptionEntry findByLCSProjectId_First(long lcsProjectId,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator)
		throws NoSuchLCSSubscriptionEntryException {
		LCSSubscriptionEntry lcsSubscriptionEntry = fetchByLCSProjectId_First(lcsProjectId,
				orderByComparator);

		if (lcsSubscriptionEntry != null) {
			return lcsSubscriptionEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("lcsProjectId=");
		msg.append(lcsProjectId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSSubscriptionEntryException(msg.toString());
	}

	/**
	 * Returns the first l c s subscription entry in the ordered set where lcsProjectId = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s subscription entry, or <code>null</code> if a matching l c s subscription entry could not be found
	 */
	@Override
	public LCSSubscriptionEntry fetchByLCSProjectId_First(long lcsProjectId,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator) {
		List<LCSSubscriptionEntry> list = findByLCSProjectId(lcsProjectId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last l c s subscription entry in the ordered set where lcsProjectId = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s subscription entry
	 * @throws NoSuchLCSSubscriptionEntryException if a matching l c s subscription entry could not be found
	 */
	@Override
	public LCSSubscriptionEntry findByLCSProjectId_Last(long lcsProjectId,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator)
		throws NoSuchLCSSubscriptionEntryException {
		LCSSubscriptionEntry lcsSubscriptionEntry = fetchByLCSProjectId_Last(lcsProjectId,
				orderByComparator);

		if (lcsSubscriptionEntry != null) {
			return lcsSubscriptionEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("lcsProjectId=");
		msg.append(lcsProjectId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSSubscriptionEntryException(msg.toString());
	}

	/**
	 * Returns the last l c s subscription entry in the ordered set where lcsProjectId = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s subscription entry, or <code>null</code> if a matching l c s subscription entry could not be found
	 */
	@Override
	public LCSSubscriptionEntry fetchByLCSProjectId_Last(long lcsProjectId,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator) {
		int count = countByLCSProjectId(lcsProjectId);

		if (count == 0) {
			return null;
		}

		List<LCSSubscriptionEntry> list = findByLCSProjectId(lcsProjectId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the l c s subscription entries before and after the current l c s subscription entry in the ordered set where lcsProjectId = &#63;.
	 *
	 * @param lcsSubscriptionEntryId the primary key of the current l c s subscription entry
	 * @param lcsProjectId the lcs project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next l c s subscription entry
	 * @throws NoSuchLCSSubscriptionEntryException if a l c s subscription entry with the primary key could not be found
	 */
	@Override
	public LCSSubscriptionEntry[] findByLCSProjectId_PrevAndNext(
		long lcsSubscriptionEntryId, long lcsProjectId,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator)
		throws NoSuchLCSSubscriptionEntryException {
		LCSSubscriptionEntry lcsSubscriptionEntry = findByPrimaryKey(lcsSubscriptionEntryId);

		Session session = null;

		try {
			session = openSession();

			LCSSubscriptionEntry[] array = new LCSSubscriptionEntryImpl[3];

			array[0] = getByLCSProjectId_PrevAndNext(session,
					lcsSubscriptionEntry, lcsProjectId, orderByComparator, true);

			array[1] = lcsSubscriptionEntry;

			array[2] = getByLCSProjectId_PrevAndNext(session,
					lcsSubscriptionEntry, lcsProjectId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LCSSubscriptionEntry getByLCSProjectId_PrevAndNext(
		Session session, LCSSubscriptionEntry lcsSubscriptionEntry,
		long lcsProjectId,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LCSSUBSCRIPTIONENTRY_WHERE);

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
			query.append(LCSSubscriptionEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(lcsProjectId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(lcsSubscriptionEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LCSSubscriptionEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the l c s subscription entries where lcsProjectId = &#63; from the database.
	 *
	 * @param lcsProjectId the lcs project ID
	 */
	@Override
	public void removeByLCSProjectId(long lcsProjectId) {
		for (LCSSubscriptionEntry lcsSubscriptionEntry : findByLCSProjectId(
				lcsProjectId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(lcsSubscriptionEntry);
		}
	}

	/**
	 * Returns the number of l c s subscription entries where lcsProjectId = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @return the number of matching l c s subscription entries
	 */
	@Override
	public int countByLCSProjectId(long lcsProjectId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_LCSPROJECTID;

		Object[] finderArgs = new Object[] { lcsProjectId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LCSSUBSCRIPTIONENTRY_WHERE);

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

	private static final String _FINDER_COLUMN_LCSPROJECTID_LCSPROJECTID_2 = "lcsSubscriptionEntry.lcsProjectId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVE = new FinderPath(LCSSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED,
			LCSSubscriptionEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByActive",
			new String[] {
				Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVE =
		new FinderPath(LCSSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED,
			LCSSubscriptionEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByActive",
			new String[] { Boolean.class.getName() },
			LCSSubscriptionEntryModelImpl.ACTIVE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ACTIVE = new FinderPath(LCSSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByActive",
			new String[] { Boolean.class.getName() });

	/**
	 * Returns all the l c s subscription entries where active = &#63;.
	 *
	 * @param active the active
	 * @return the matching l c s subscription entries
	 */
	@Override
	public List<LCSSubscriptionEntry> findByActive(boolean active) {
		return findByActive(active, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the l c s subscription entries where active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param active the active
	 * @param start the lower bound of the range of l c s subscription entries
	 * @param end the upper bound of the range of l c s subscription entries (not inclusive)
	 * @return the range of matching l c s subscription entries
	 */
	@Override
	public List<LCSSubscriptionEntry> findByActive(boolean active, int start,
		int end) {
		return findByActive(active, start, end, null);
	}

	/**
	 * Returns an ordered range of all the l c s subscription entries where active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param active the active
	 * @param start the lower bound of the range of l c s subscription entries
	 * @param end the upper bound of the range of l c s subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching l c s subscription entries
	 */
	@Override
	public List<LCSSubscriptionEntry> findByActive(boolean active, int start,
		int end, OrderByComparator<LCSSubscriptionEntry> orderByComparator) {
		return findByActive(active, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the l c s subscription entries where active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param active the active
	 * @param start the lower bound of the range of l c s subscription entries
	 * @param end the upper bound of the range of l c s subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching l c s subscription entries
	 */
	@Override
	public List<LCSSubscriptionEntry> findByActive(boolean active, int start,
		int end, OrderByComparator<LCSSubscriptionEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVE;
			finderArgs = new Object[] { active };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVE;
			finderArgs = new Object[] { active, start, end, orderByComparator };
		}

		List<LCSSubscriptionEntry> list = null;

		if (retrieveFromCache) {
			list = (List<LCSSubscriptionEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LCSSubscriptionEntry lcsSubscriptionEntry : list) {
					if ((active != lcsSubscriptionEntry.getActive())) {
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

			query.append(_SQL_SELECT_LCSSUBSCRIPTIONENTRY_WHERE);

			query.append(_FINDER_COLUMN_ACTIVE_ACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LCSSubscriptionEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(active);

				if (!pagination) {
					list = (List<LCSSubscriptionEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSSubscriptionEntry>)QueryUtil.list(q,
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
	 * Returns the first l c s subscription entry in the ordered set where active = &#63;.
	 *
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s subscription entry
	 * @throws NoSuchLCSSubscriptionEntryException if a matching l c s subscription entry could not be found
	 */
	@Override
	public LCSSubscriptionEntry findByActive_First(boolean active,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator)
		throws NoSuchLCSSubscriptionEntryException {
		LCSSubscriptionEntry lcsSubscriptionEntry = fetchByActive_First(active,
				orderByComparator);

		if (lcsSubscriptionEntry != null) {
			return lcsSubscriptionEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("active=");
		msg.append(active);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSSubscriptionEntryException(msg.toString());
	}

	/**
	 * Returns the first l c s subscription entry in the ordered set where active = &#63;.
	 *
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s subscription entry, or <code>null</code> if a matching l c s subscription entry could not be found
	 */
	@Override
	public LCSSubscriptionEntry fetchByActive_First(boolean active,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator) {
		List<LCSSubscriptionEntry> list = findByActive(active, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last l c s subscription entry in the ordered set where active = &#63;.
	 *
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s subscription entry
	 * @throws NoSuchLCSSubscriptionEntryException if a matching l c s subscription entry could not be found
	 */
	@Override
	public LCSSubscriptionEntry findByActive_Last(boolean active,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator)
		throws NoSuchLCSSubscriptionEntryException {
		LCSSubscriptionEntry lcsSubscriptionEntry = fetchByActive_Last(active,
				orderByComparator);

		if (lcsSubscriptionEntry != null) {
			return lcsSubscriptionEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("active=");
		msg.append(active);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSSubscriptionEntryException(msg.toString());
	}

	/**
	 * Returns the last l c s subscription entry in the ordered set where active = &#63;.
	 *
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s subscription entry, or <code>null</code> if a matching l c s subscription entry could not be found
	 */
	@Override
	public LCSSubscriptionEntry fetchByActive_Last(boolean active,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator) {
		int count = countByActive(active);

		if (count == 0) {
			return null;
		}

		List<LCSSubscriptionEntry> list = findByActive(active, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the l c s subscription entries before and after the current l c s subscription entry in the ordered set where active = &#63;.
	 *
	 * @param lcsSubscriptionEntryId the primary key of the current l c s subscription entry
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next l c s subscription entry
	 * @throws NoSuchLCSSubscriptionEntryException if a l c s subscription entry with the primary key could not be found
	 */
	@Override
	public LCSSubscriptionEntry[] findByActive_PrevAndNext(
		long lcsSubscriptionEntryId, boolean active,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator)
		throws NoSuchLCSSubscriptionEntryException {
		LCSSubscriptionEntry lcsSubscriptionEntry = findByPrimaryKey(lcsSubscriptionEntryId);

		Session session = null;

		try {
			session = openSession();

			LCSSubscriptionEntry[] array = new LCSSubscriptionEntryImpl[3];

			array[0] = getByActive_PrevAndNext(session, lcsSubscriptionEntry,
					active, orderByComparator, true);

			array[1] = lcsSubscriptionEntry;

			array[2] = getByActive_PrevAndNext(session, lcsSubscriptionEntry,
					active, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LCSSubscriptionEntry getByActive_PrevAndNext(Session session,
		LCSSubscriptionEntry lcsSubscriptionEntry, boolean active,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LCSSUBSCRIPTIONENTRY_WHERE);

		query.append(_FINDER_COLUMN_ACTIVE_ACTIVE_2);

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
			query.append(LCSSubscriptionEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(active);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(lcsSubscriptionEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LCSSubscriptionEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the l c s subscription entries where active = &#63; from the database.
	 *
	 * @param active the active
	 */
	@Override
	public void removeByActive(boolean active) {
		for (LCSSubscriptionEntry lcsSubscriptionEntry : findByActive(active,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(lcsSubscriptionEntry);
		}
	}

	/**
	 * Returns the number of l c s subscription entries where active = &#63;.
	 *
	 * @param active the active
	 * @return the number of matching l c s subscription entries
	 */
	@Override
	public int countByActive(boolean active) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ACTIVE;

		Object[] finderArgs = new Object[] { active };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LCSSUBSCRIPTIONENTRY_WHERE);

			query.append(_FINDER_COLUMN_ACTIVE_ACTIVE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(active);

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

	private static final String _FINDER_COLUMN_ACTIVE_ACTIVE_2 = "lcsSubscriptionEntry.active = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_LPI_A = new FinderPath(LCSSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED,
			LCSSubscriptionEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByLPI_A",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LPI_A = new FinderPath(LCSSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED,
			LCSSubscriptionEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByLPI_A",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			LCSSubscriptionEntryModelImpl.LCSPROJECTID_COLUMN_BITMASK |
			LCSSubscriptionEntryModelImpl.ACTIVE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_LPI_A = new FinderPath(LCSSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByLPI_A",
			new String[] { Long.class.getName(), Boolean.class.getName() });

	/**
	 * Returns all the l c s subscription entries where lcsProjectId = &#63; and active = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param active the active
	 * @return the matching l c s subscription entries
	 */
	@Override
	public List<LCSSubscriptionEntry> findByLPI_A(long lcsProjectId,
		boolean active) {
		return findByLPI_A(lcsProjectId, active, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the l c s subscription entries where lcsProjectId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param active the active
	 * @param start the lower bound of the range of l c s subscription entries
	 * @param end the upper bound of the range of l c s subscription entries (not inclusive)
	 * @return the range of matching l c s subscription entries
	 */
	@Override
	public List<LCSSubscriptionEntry> findByLPI_A(long lcsProjectId,
		boolean active, int start, int end) {
		return findByLPI_A(lcsProjectId, active, start, end, null);
	}

	/**
	 * Returns an ordered range of all the l c s subscription entries where lcsProjectId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param active the active
	 * @param start the lower bound of the range of l c s subscription entries
	 * @param end the upper bound of the range of l c s subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching l c s subscription entries
	 */
	@Override
	public List<LCSSubscriptionEntry> findByLPI_A(long lcsProjectId,
		boolean active, int start, int end,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator) {
		return findByLPI_A(lcsProjectId, active, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the l c s subscription entries where lcsProjectId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param active the active
	 * @param start the lower bound of the range of l c s subscription entries
	 * @param end the upper bound of the range of l c s subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching l c s subscription entries
	 */
	@Override
	public List<LCSSubscriptionEntry> findByLPI_A(long lcsProjectId,
		boolean active, int start, int end,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LPI_A;
			finderArgs = new Object[] { lcsProjectId, active };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_LPI_A;
			finderArgs = new Object[] {
					lcsProjectId, active,
					
					start, end, orderByComparator
				};
		}

		List<LCSSubscriptionEntry> list = null;

		if (retrieveFromCache) {
			list = (List<LCSSubscriptionEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LCSSubscriptionEntry lcsSubscriptionEntry : list) {
					if ((lcsProjectId != lcsSubscriptionEntry.getLcsProjectId()) ||
							(active != lcsSubscriptionEntry.getActive())) {
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

			query.append(_SQL_SELECT_LCSSUBSCRIPTIONENTRY_WHERE);

			query.append(_FINDER_COLUMN_LPI_A_LCSPROJECTID_2);

			query.append(_FINDER_COLUMN_LPI_A_ACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LCSSubscriptionEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(lcsProjectId);

				qPos.add(active);

				if (!pagination) {
					list = (List<LCSSubscriptionEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSSubscriptionEntry>)QueryUtil.list(q,
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
	 * Returns the first l c s subscription entry in the ordered set where lcsProjectId = &#63; and active = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s subscription entry
	 * @throws NoSuchLCSSubscriptionEntryException if a matching l c s subscription entry could not be found
	 */
	@Override
	public LCSSubscriptionEntry findByLPI_A_First(long lcsProjectId,
		boolean active,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator)
		throws NoSuchLCSSubscriptionEntryException {
		LCSSubscriptionEntry lcsSubscriptionEntry = fetchByLPI_A_First(lcsProjectId,
				active, orderByComparator);

		if (lcsSubscriptionEntry != null) {
			return lcsSubscriptionEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("lcsProjectId=");
		msg.append(lcsProjectId);

		msg.append(", active=");
		msg.append(active);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSSubscriptionEntryException(msg.toString());
	}

	/**
	 * Returns the first l c s subscription entry in the ordered set where lcsProjectId = &#63; and active = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s subscription entry, or <code>null</code> if a matching l c s subscription entry could not be found
	 */
	@Override
	public LCSSubscriptionEntry fetchByLPI_A_First(long lcsProjectId,
		boolean active,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator) {
		List<LCSSubscriptionEntry> list = findByLPI_A(lcsProjectId, active, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last l c s subscription entry in the ordered set where lcsProjectId = &#63; and active = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s subscription entry
	 * @throws NoSuchLCSSubscriptionEntryException if a matching l c s subscription entry could not be found
	 */
	@Override
	public LCSSubscriptionEntry findByLPI_A_Last(long lcsProjectId,
		boolean active,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator)
		throws NoSuchLCSSubscriptionEntryException {
		LCSSubscriptionEntry lcsSubscriptionEntry = fetchByLPI_A_Last(lcsProjectId,
				active, orderByComparator);

		if (lcsSubscriptionEntry != null) {
			return lcsSubscriptionEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("lcsProjectId=");
		msg.append(lcsProjectId);

		msg.append(", active=");
		msg.append(active);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSSubscriptionEntryException(msg.toString());
	}

	/**
	 * Returns the last l c s subscription entry in the ordered set where lcsProjectId = &#63; and active = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s subscription entry, or <code>null</code> if a matching l c s subscription entry could not be found
	 */
	@Override
	public LCSSubscriptionEntry fetchByLPI_A_Last(long lcsProjectId,
		boolean active,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator) {
		int count = countByLPI_A(lcsProjectId, active);

		if (count == 0) {
			return null;
		}

		List<LCSSubscriptionEntry> list = findByLPI_A(lcsProjectId, active,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the l c s subscription entries before and after the current l c s subscription entry in the ordered set where lcsProjectId = &#63; and active = &#63;.
	 *
	 * @param lcsSubscriptionEntryId the primary key of the current l c s subscription entry
	 * @param lcsProjectId the lcs project ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next l c s subscription entry
	 * @throws NoSuchLCSSubscriptionEntryException if a l c s subscription entry with the primary key could not be found
	 */
	@Override
	public LCSSubscriptionEntry[] findByLPI_A_PrevAndNext(
		long lcsSubscriptionEntryId, long lcsProjectId, boolean active,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator)
		throws NoSuchLCSSubscriptionEntryException {
		LCSSubscriptionEntry lcsSubscriptionEntry = findByPrimaryKey(lcsSubscriptionEntryId);

		Session session = null;

		try {
			session = openSession();

			LCSSubscriptionEntry[] array = new LCSSubscriptionEntryImpl[3];

			array[0] = getByLPI_A_PrevAndNext(session, lcsSubscriptionEntry,
					lcsProjectId, active, orderByComparator, true);

			array[1] = lcsSubscriptionEntry;

			array[2] = getByLPI_A_PrevAndNext(session, lcsSubscriptionEntry,
					lcsProjectId, active, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LCSSubscriptionEntry getByLPI_A_PrevAndNext(Session session,
		LCSSubscriptionEntry lcsSubscriptionEntry, long lcsProjectId,
		boolean active,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_LCSSUBSCRIPTIONENTRY_WHERE);

		query.append(_FINDER_COLUMN_LPI_A_LCSPROJECTID_2);

		query.append(_FINDER_COLUMN_LPI_A_ACTIVE_2);

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
			query.append(LCSSubscriptionEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(lcsProjectId);

		qPos.add(active);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(lcsSubscriptionEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LCSSubscriptionEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the l c s subscription entries where lcsProjectId = &#63; and active = &#63; from the database.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param active the active
	 */
	@Override
	public void removeByLPI_A(long lcsProjectId, boolean active) {
		for (LCSSubscriptionEntry lcsSubscriptionEntry : findByLPI_A(
				lcsProjectId, active, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(lcsSubscriptionEntry);
		}
	}

	/**
	 * Returns the number of l c s subscription entries where lcsProjectId = &#63; and active = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param active the active
	 * @return the number of matching l c s subscription entries
	 */
	@Override
	public int countByLPI_A(long lcsProjectId, boolean active) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_LPI_A;

		Object[] finderArgs = new Object[] { lcsProjectId, active };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LCSSUBSCRIPTIONENTRY_WHERE);

			query.append(_FINDER_COLUMN_LPI_A_LCSPROJECTID_2);

			query.append(_FINDER_COLUMN_LPI_A_ACTIVE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(lcsProjectId);

				qPos.add(active);

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

	private static final String _FINDER_COLUMN_LPI_A_LCSPROJECTID_2 = "lcsSubscriptionEntry.lcsProjectId = ? AND ";
	private static final String _FINDER_COLUMN_LPI_A_ACTIVE_2 = "lcsSubscriptionEntry.active = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_LPI_T_A = new FinderPath(LCSSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED,
			LCSSubscriptionEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByLPI_T_A",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LPI_T_A =
		new FinderPath(LCSSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED,
			LCSSubscriptionEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByLPI_T_A",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName()
			},
			LCSSubscriptionEntryModelImpl.LCSPROJECTID_COLUMN_BITMASK |
			LCSSubscriptionEntryModelImpl.TYPE_COLUMN_BITMASK |
			LCSSubscriptionEntryModelImpl.ACTIVE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_LPI_T_A = new FinderPath(LCSSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByLPI_T_A",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName()
			});

	/**
	 * Returns all the l c s subscription entries where lcsProjectId = &#63; and type = &#63; and active = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param type the type
	 * @param active the active
	 * @return the matching l c s subscription entries
	 */
	@Override
	public List<LCSSubscriptionEntry> findByLPI_T_A(long lcsProjectId,
		String type, boolean active) {
		return findByLPI_T_A(lcsProjectId, type, active, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the l c s subscription entries where lcsProjectId = &#63; and type = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param type the type
	 * @param active the active
	 * @param start the lower bound of the range of l c s subscription entries
	 * @param end the upper bound of the range of l c s subscription entries (not inclusive)
	 * @return the range of matching l c s subscription entries
	 */
	@Override
	public List<LCSSubscriptionEntry> findByLPI_T_A(long lcsProjectId,
		String type, boolean active, int start, int end) {
		return findByLPI_T_A(lcsProjectId, type, active, start, end, null);
	}

	/**
	 * Returns an ordered range of all the l c s subscription entries where lcsProjectId = &#63; and type = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param type the type
	 * @param active the active
	 * @param start the lower bound of the range of l c s subscription entries
	 * @param end the upper bound of the range of l c s subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching l c s subscription entries
	 */
	@Override
	public List<LCSSubscriptionEntry> findByLPI_T_A(long lcsProjectId,
		String type, boolean active, int start, int end,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator) {
		return findByLPI_T_A(lcsProjectId, type, active, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the l c s subscription entries where lcsProjectId = &#63; and type = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param type the type
	 * @param active the active
	 * @param start the lower bound of the range of l c s subscription entries
	 * @param end the upper bound of the range of l c s subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching l c s subscription entries
	 */
	@Override
	public List<LCSSubscriptionEntry> findByLPI_T_A(long lcsProjectId,
		String type, boolean active, int start, int end,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LPI_T_A;
			finderArgs = new Object[] { lcsProjectId, type, active };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_LPI_T_A;
			finderArgs = new Object[] {
					lcsProjectId, type, active,
					
					start, end, orderByComparator
				};
		}

		List<LCSSubscriptionEntry> list = null;

		if (retrieveFromCache) {
			list = (List<LCSSubscriptionEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LCSSubscriptionEntry lcsSubscriptionEntry : list) {
					if ((lcsProjectId != lcsSubscriptionEntry.getLcsProjectId()) ||
							!Objects.equals(type, lcsSubscriptionEntry.getType()) ||
							(active != lcsSubscriptionEntry.getActive())) {
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

			query.append(_SQL_SELECT_LCSSUBSCRIPTIONENTRY_WHERE);

			query.append(_FINDER_COLUMN_LPI_T_A_LCSPROJECTID_2);

			boolean bindType = false;

			if (type == null) {
				query.append(_FINDER_COLUMN_LPI_T_A_TYPE_1);
			}
			else if (type.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_LPI_T_A_TYPE_3);
			}
			else {
				bindType = true;

				query.append(_FINDER_COLUMN_LPI_T_A_TYPE_2);
			}

			query.append(_FINDER_COLUMN_LPI_T_A_ACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LCSSubscriptionEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(lcsProjectId);

				if (bindType) {
					qPos.add(type);
				}

				qPos.add(active);

				if (!pagination) {
					list = (List<LCSSubscriptionEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSSubscriptionEntry>)QueryUtil.list(q,
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
	 * Returns the first l c s subscription entry in the ordered set where lcsProjectId = &#63; and type = &#63; and active = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param type the type
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s subscription entry
	 * @throws NoSuchLCSSubscriptionEntryException if a matching l c s subscription entry could not be found
	 */
	@Override
	public LCSSubscriptionEntry findByLPI_T_A_First(long lcsProjectId,
		String type, boolean active,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator)
		throws NoSuchLCSSubscriptionEntryException {
		LCSSubscriptionEntry lcsSubscriptionEntry = fetchByLPI_T_A_First(lcsProjectId,
				type, active, orderByComparator);

		if (lcsSubscriptionEntry != null) {
			return lcsSubscriptionEntry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("lcsProjectId=");
		msg.append(lcsProjectId);

		msg.append(", type=");
		msg.append(type);

		msg.append(", active=");
		msg.append(active);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSSubscriptionEntryException(msg.toString());
	}

	/**
	 * Returns the first l c s subscription entry in the ordered set where lcsProjectId = &#63; and type = &#63; and active = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param type the type
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s subscription entry, or <code>null</code> if a matching l c s subscription entry could not be found
	 */
	@Override
	public LCSSubscriptionEntry fetchByLPI_T_A_First(long lcsProjectId,
		String type, boolean active,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator) {
		List<LCSSubscriptionEntry> list = findByLPI_T_A(lcsProjectId, type,
				active, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last l c s subscription entry in the ordered set where lcsProjectId = &#63; and type = &#63; and active = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param type the type
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s subscription entry
	 * @throws NoSuchLCSSubscriptionEntryException if a matching l c s subscription entry could not be found
	 */
	@Override
	public LCSSubscriptionEntry findByLPI_T_A_Last(long lcsProjectId,
		String type, boolean active,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator)
		throws NoSuchLCSSubscriptionEntryException {
		LCSSubscriptionEntry lcsSubscriptionEntry = fetchByLPI_T_A_Last(lcsProjectId,
				type, active, orderByComparator);

		if (lcsSubscriptionEntry != null) {
			return lcsSubscriptionEntry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("lcsProjectId=");
		msg.append(lcsProjectId);

		msg.append(", type=");
		msg.append(type);

		msg.append(", active=");
		msg.append(active);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSSubscriptionEntryException(msg.toString());
	}

	/**
	 * Returns the last l c s subscription entry in the ordered set where lcsProjectId = &#63; and type = &#63; and active = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param type the type
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s subscription entry, or <code>null</code> if a matching l c s subscription entry could not be found
	 */
	@Override
	public LCSSubscriptionEntry fetchByLPI_T_A_Last(long lcsProjectId,
		String type, boolean active,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator) {
		int count = countByLPI_T_A(lcsProjectId, type, active);

		if (count == 0) {
			return null;
		}

		List<LCSSubscriptionEntry> list = findByLPI_T_A(lcsProjectId, type,
				active, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the l c s subscription entries before and after the current l c s subscription entry in the ordered set where lcsProjectId = &#63; and type = &#63; and active = &#63;.
	 *
	 * @param lcsSubscriptionEntryId the primary key of the current l c s subscription entry
	 * @param lcsProjectId the lcs project ID
	 * @param type the type
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next l c s subscription entry
	 * @throws NoSuchLCSSubscriptionEntryException if a l c s subscription entry with the primary key could not be found
	 */
	@Override
	public LCSSubscriptionEntry[] findByLPI_T_A_PrevAndNext(
		long lcsSubscriptionEntryId, long lcsProjectId, String type,
		boolean active,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator)
		throws NoSuchLCSSubscriptionEntryException {
		LCSSubscriptionEntry lcsSubscriptionEntry = findByPrimaryKey(lcsSubscriptionEntryId);

		Session session = null;

		try {
			session = openSession();

			LCSSubscriptionEntry[] array = new LCSSubscriptionEntryImpl[3];

			array[0] = getByLPI_T_A_PrevAndNext(session, lcsSubscriptionEntry,
					lcsProjectId, type, active, orderByComparator, true);

			array[1] = lcsSubscriptionEntry;

			array[2] = getByLPI_T_A_PrevAndNext(session, lcsSubscriptionEntry,
					lcsProjectId, type, active, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LCSSubscriptionEntry getByLPI_T_A_PrevAndNext(Session session,
		LCSSubscriptionEntry lcsSubscriptionEntry, long lcsProjectId,
		String type, boolean active,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator,
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

		query.append(_SQL_SELECT_LCSSUBSCRIPTIONENTRY_WHERE);

		query.append(_FINDER_COLUMN_LPI_T_A_LCSPROJECTID_2);

		boolean bindType = false;

		if (type == null) {
			query.append(_FINDER_COLUMN_LPI_T_A_TYPE_1);
		}
		else if (type.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_LPI_T_A_TYPE_3);
		}
		else {
			bindType = true;

			query.append(_FINDER_COLUMN_LPI_T_A_TYPE_2);
		}

		query.append(_FINDER_COLUMN_LPI_T_A_ACTIVE_2);

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
			query.append(LCSSubscriptionEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(lcsProjectId);

		if (bindType) {
			qPos.add(type);
		}

		qPos.add(active);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(lcsSubscriptionEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LCSSubscriptionEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the l c s subscription entries where lcsProjectId = &#63; and type = &#63; and active = &#63; from the database.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param type the type
	 * @param active the active
	 */
	@Override
	public void removeByLPI_T_A(long lcsProjectId, String type, boolean active) {
		for (LCSSubscriptionEntry lcsSubscriptionEntry : findByLPI_T_A(
				lcsProjectId, type, active, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(lcsSubscriptionEntry);
		}
	}

	/**
	 * Returns the number of l c s subscription entries where lcsProjectId = &#63; and type = &#63; and active = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param type the type
	 * @param active the active
	 * @return the number of matching l c s subscription entries
	 */
	@Override
	public int countByLPI_T_A(long lcsProjectId, String type, boolean active) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_LPI_T_A;

		Object[] finderArgs = new Object[] { lcsProjectId, type, active };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_LCSSUBSCRIPTIONENTRY_WHERE);

			query.append(_FINDER_COLUMN_LPI_T_A_LCSPROJECTID_2);

			boolean bindType = false;

			if (type == null) {
				query.append(_FINDER_COLUMN_LPI_T_A_TYPE_1);
			}
			else if (type.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_LPI_T_A_TYPE_3);
			}
			else {
				bindType = true;

				query.append(_FINDER_COLUMN_LPI_T_A_TYPE_2);
			}

			query.append(_FINDER_COLUMN_LPI_T_A_ACTIVE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(lcsProjectId);

				if (bindType) {
					qPos.add(type);
				}

				qPos.add(active);

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

	private static final String _FINDER_COLUMN_LPI_T_A_LCSPROJECTID_2 = "lcsSubscriptionEntry.lcsProjectId = ? AND ";
	private static final String _FINDER_COLUMN_LPI_T_A_TYPE_1 = "lcsSubscriptionEntry.type IS NULL AND ";
	private static final String _FINDER_COLUMN_LPI_T_A_TYPE_2 = "lcsSubscriptionEntry.type = ? AND ";
	private static final String _FINDER_COLUMN_LPI_T_A_TYPE_3 = "(lcsSubscriptionEntry.type IS NULL OR lcsSubscriptionEntry.type = '') AND ";
	private static final String _FINDER_COLUMN_LPI_T_A_ACTIVE_2 = "lcsSubscriptionEntry.active = ?";

	public LCSSubscriptionEntryPersistenceImpl() {
		setModelClass(LCSSubscriptionEntry.class);
	}

	/**
	 * Caches the l c s subscription entry in the entity cache if it is enabled.
	 *
	 * @param lcsSubscriptionEntry the l c s subscription entry
	 */
	@Override
	public void cacheResult(LCSSubscriptionEntry lcsSubscriptionEntry) {
		entityCache.putResult(LCSSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSSubscriptionEntryImpl.class,
			lcsSubscriptionEntry.getPrimaryKey(), lcsSubscriptionEntry);

		lcsSubscriptionEntry.resetOriginalValues();
	}

	/**
	 * Caches the l c s subscription entries in the entity cache if it is enabled.
	 *
	 * @param lcsSubscriptionEntries the l c s subscription entries
	 */
	@Override
	public void cacheResult(List<LCSSubscriptionEntry> lcsSubscriptionEntries) {
		for (LCSSubscriptionEntry lcsSubscriptionEntry : lcsSubscriptionEntries) {
			if (entityCache.getResult(
						LCSSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
						LCSSubscriptionEntryImpl.class,
						lcsSubscriptionEntry.getPrimaryKey()) == null) {
				cacheResult(lcsSubscriptionEntry);
			}
			else {
				lcsSubscriptionEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all l c s subscription entries.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(LCSSubscriptionEntryImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the l c s subscription entry.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LCSSubscriptionEntry lcsSubscriptionEntry) {
		entityCache.removeResult(LCSSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSSubscriptionEntryImpl.class, lcsSubscriptionEntry.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<LCSSubscriptionEntry> lcsSubscriptionEntries) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (LCSSubscriptionEntry lcsSubscriptionEntry : lcsSubscriptionEntries) {
			entityCache.removeResult(LCSSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
				LCSSubscriptionEntryImpl.class,
				lcsSubscriptionEntry.getPrimaryKey());
		}
	}

	/**
	 * Creates a new l c s subscription entry with the primary key. Does not add the l c s subscription entry to the database.
	 *
	 * @param lcsSubscriptionEntryId the primary key for the new l c s subscription entry
	 * @return the new l c s subscription entry
	 */
	@Override
	public LCSSubscriptionEntry create(long lcsSubscriptionEntryId) {
		LCSSubscriptionEntry lcsSubscriptionEntry = new LCSSubscriptionEntryImpl();

		lcsSubscriptionEntry.setNew(true);
		lcsSubscriptionEntry.setPrimaryKey(lcsSubscriptionEntryId);

		return lcsSubscriptionEntry;
	}

	/**
	 * Removes the l c s subscription entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param lcsSubscriptionEntryId the primary key of the l c s subscription entry
	 * @return the l c s subscription entry that was removed
	 * @throws NoSuchLCSSubscriptionEntryException if a l c s subscription entry with the primary key could not be found
	 */
	@Override
	public LCSSubscriptionEntry remove(long lcsSubscriptionEntryId)
		throws NoSuchLCSSubscriptionEntryException {
		return remove((Serializable)lcsSubscriptionEntryId);
	}

	/**
	 * Removes the l c s subscription entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the l c s subscription entry
	 * @return the l c s subscription entry that was removed
	 * @throws NoSuchLCSSubscriptionEntryException if a l c s subscription entry with the primary key could not be found
	 */
	@Override
	public LCSSubscriptionEntry remove(Serializable primaryKey)
		throws NoSuchLCSSubscriptionEntryException {
		Session session = null;

		try {
			session = openSession();

			LCSSubscriptionEntry lcsSubscriptionEntry = (LCSSubscriptionEntry)session.get(LCSSubscriptionEntryImpl.class,
					primaryKey);

			if (lcsSubscriptionEntry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLCSSubscriptionEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(lcsSubscriptionEntry);
		}
		catch (NoSuchLCSSubscriptionEntryException nsee) {
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
	protected LCSSubscriptionEntry removeImpl(
		LCSSubscriptionEntry lcsSubscriptionEntry) {
		lcsSubscriptionEntry = toUnwrappedModel(lcsSubscriptionEntry);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(lcsSubscriptionEntry)) {
				lcsSubscriptionEntry = (LCSSubscriptionEntry)session.get(LCSSubscriptionEntryImpl.class,
						lcsSubscriptionEntry.getPrimaryKeyObj());
			}

			if (lcsSubscriptionEntry != null) {
				session.delete(lcsSubscriptionEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (lcsSubscriptionEntry != null) {
			clearCache(lcsSubscriptionEntry);
		}

		return lcsSubscriptionEntry;
	}

	@Override
	public LCSSubscriptionEntry updateImpl(
		LCSSubscriptionEntry lcsSubscriptionEntry) {
		lcsSubscriptionEntry = toUnwrappedModel(lcsSubscriptionEntry);

		boolean isNew = lcsSubscriptionEntry.isNew();

		LCSSubscriptionEntryModelImpl lcsSubscriptionEntryModelImpl = (LCSSubscriptionEntryModelImpl)lcsSubscriptionEntry;

		Session session = null;

		try {
			session = openSession();

			if (lcsSubscriptionEntry.isNew()) {
				session.save(lcsSubscriptionEntry);

				lcsSubscriptionEntry.setNew(false);
			}
			else {
				lcsSubscriptionEntry = (LCSSubscriptionEntry)session.merge(lcsSubscriptionEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !LCSSubscriptionEntryModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((lcsSubscriptionEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LCSPROJECTID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						lcsSubscriptionEntryModelImpl.getOriginalLcsProjectId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_LCSPROJECTID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LCSPROJECTID,
					args);

				args = new Object[] {
						lcsSubscriptionEntryModelImpl.getLcsProjectId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_LCSPROJECTID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LCSPROJECTID,
					args);
			}

			if ((lcsSubscriptionEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						lcsSubscriptionEntryModelImpl.getOriginalActive()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACTIVE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVE,
					args);

				args = new Object[] { lcsSubscriptionEntryModelImpl.getActive() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACTIVE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVE,
					args);
			}

			if ((lcsSubscriptionEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LPI_A.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						lcsSubscriptionEntryModelImpl.getOriginalLcsProjectId(),
						lcsSubscriptionEntryModelImpl.getOriginalActive()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_LPI_A, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LPI_A,
					args);

				args = new Object[] {
						lcsSubscriptionEntryModelImpl.getLcsProjectId(),
						lcsSubscriptionEntryModelImpl.getActive()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_LPI_A, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LPI_A,
					args);
			}

			if ((lcsSubscriptionEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LPI_T_A.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						lcsSubscriptionEntryModelImpl.getOriginalLcsProjectId(),
						lcsSubscriptionEntryModelImpl.getOriginalType(),
						lcsSubscriptionEntryModelImpl.getOriginalActive()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_LPI_T_A, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LPI_T_A,
					args);

				args = new Object[] {
						lcsSubscriptionEntryModelImpl.getLcsProjectId(),
						lcsSubscriptionEntryModelImpl.getType(),
						lcsSubscriptionEntryModelImpl.getActive()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_LPI_T_A, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LPI_T_A,
					args);
			}
		}

		entityCache.putResult(LCSSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSSubscriptionEntryImpl.class,
			lcsSubscriptionEntry.getPrimaryKey(), lcsSubscriptionEntry, false);

		lcsSubscriptionEntry.resetOriginalValues();

		return lcsSubscriptionEntry;
	}

	protected LCSSubscriptionEntry toUnwrappedModel(
		LCSSubscriptionEntry lcsSubscriptionEntry) {
		if (lcsSubscriptionEntry instanceof LCSSubscriptionEntryImpl) {
			return lcsSubscriptionEntry;
		}

		LCSSubscriptionEntryImpl lcsSubscriptionEntryImpl = new LCSSubscriptionEntryImpl();

		lcsSubscriptionEntryImpl.setNew(lcsSubscriptionEntry.isNew());
		lcsSubscriptionEntryImpl.setPrimaryKey(lcsSubscriptionEntry.getPrimaryKey());

		lcsSubscriptionEntryImpl.setLcsSubscriptionEntryId(lcsSubscriptionEntry.getLcsSubscriptionEntryId());
		lcsSubscriptionEntryImpl.setLcsProjectId(lcsSubscriptionEntry.getLcsProjectId());
		lcsSubscriptionEntryImpl.setActualPrice(lcsSubscriptionEntry.getActualPrice());
		lcsSubscriptionEntryImpl.setCurrencyCode(lcsSubscriptionEntry.getCurrencyCode());
		lcsSubscriptionEntryImpl.setInstanceSize(lcsSubscriptionEntry.getInstanceSize());
		lcsSubscriptionEntryImpl.setType(lcsSubscriptionEntry.getType());
		lcsSubscriptionEntryImpl.setPlatform(lcsSubscriptionEntry.getPlatform());
		lcsSubscriptionEntryImpl.setPlatformVersion(lcsSubscriptionEntry.getPlatformVersion());
		lcsSubscriptionEntryImpl.setProcessorCoresAllowed(lcsSubscriptionEntry.getProcessorCoresAllowed());
		lcsSubscriptionEntryImpl.setProduct(lcsSubscriptionEntry.getProduct());
		lcsSubscriptionEntryImpl.setProductVersion(lcsSubscriptionEntry.getProductVersion());
		lcsSubscriptionEntryImpl.setServersAllowed(lcsSubscriptionEntry.getServersAllowed());
		lcsSubscriptionEntryImpl.setServersUsed(lcsSubscriptionEntry.getServersUsed());
		lcsSubscriptionEntryImpl.setStartDate(lcsSubscriptionEntry.getStartDate());
		lcsSubscriptionEntryImpl.setEndDate(lcsSubscriptionEntry.getEndDate());
		lcsSubscriptionEntryImpl.setSupportStartDate(lcsSubscriptionEntry.getSupportStartDate());
		lcsSubscriptionEntryImpl.setSupportEndDate(lcsSubscriptionEntry.getSupportEndDate());
		lcsSubscriptionEntryImpl.setActive(lcsSubscriptionEntry.isActive());

		return lcsSubscriptionEntryImpl;
	}

	/**
	 * Returns the l c s subscription entry with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the l c s subscription entry
	 * @return the l c s subscription entry
	 * @throws NoSuchLCSSubscriptionEntryException if a l c s subscription entry with the primary key could not be found
	 */
	@Override
	public LCSSubscriptionEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLCSSubscriptionEntryException {
		LCSSubscriptionEntry lcsSubscriptionEntry = fetchByPrimaryKey(primaryKey);

		if (lcsSubscriptionEntry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLCSSubscriptionEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return lcsSubscriptionEntry;
	}

	/**
	 * Returns the l c s subscription entry with the primary key or throws a {@link NoSuchLCSSubscriptionEntryException} if it could not be found.
	 *
	 * @param lcsSubscriptionEntryId the primary key of the l c s subscription entry
	 * @return the l c s subscription entry
	 * @throws NoSuchLCSSubscriptionEntryException if a l c s subscription entry with the primary key could not be found
	 */
	@Override
	public LCSSubscriptionEntry findByPrimaryKey(long lcsSubscriptionEntryId)
		throws NoSuchLCSSubscriptionEntryException {
		return findByPrimaryKey((Serializable)lcsSubscriptionEntryId);
	}

	/**
	 * Returns the l c s subscription entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the l c s subscription entry
	 * @return the l c s subscription entry, or <code>null</code> if a l c s subscription entry with the primary key could not be found
	 */
	@Override
	public LCSSubscriptionEntry fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(LCSSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
				LCSSubscriptionEntryImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		LCSSubscriptionEntry lcsSubscriptionEntry = (LCSSubscriptionEntry)serializable;

		if (lcsSubscriptionEntry == null) {
			Session session = null;

			try {
				session = openSession();

				lcsSubscriptionEntry = (LCSSubscriptionEntry)session.get(LCSSubscriptionEntryImpl.class,
						primaryKey);

				if (lcsSubscriptionEntry != null) {
					cacheResult(lcsSubscriptionEntry);
				}
				else {
					entityCache.putResult(LCSSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
						LCSSubscriptionEntryImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(LCSSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
					LCSSubscriptionEntryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return lcsSubscriptionEntry;
	}

	/**
	 * Returns the l c s subscription entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param lcsSubscriptionEntryId the primary key of the l c s subscription entry
	 * @return the l c s subscription entry, or <code>null</code> if a l c s subscription entry with the primary key could not be found
	 */
	@Override
	public LCSSubscriptionEntry fetchByPrimaryKey(long lcsSubscriptionEntryId) {
		return fetchByPrimaryKey((Serializable)lcsSubscriptionEntryId);
	}

	@Override
	public Map<Serializable, LCSSubscriptionEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, LCSSubscriptionEntry> map = new HashMap<Serializable, LCSSubscriptionEntry>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			LCSSubscriptionEntry lcsSubscriptionEntry = fetchByPrimaryKey(primaryKey);

			if (lcsSubscriptionEntry != null) {
				map.put(primaryKey, lcsSubscriptionEntry);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(LCSSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
					LCSSubscriptionEntryImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (LCSSubscriptionEntry)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_LCSSUBSCRIPTIONENTRY_WHERE_PKS_IN);

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

			for (LCSSubscriptionEntry lcsSubscriptionEntry : (List<LCSSubscriptionEntry>)q.list()) {
				map.put(lcsSubscriptionEntry.getPrimaryKeyObj(),
					lcsSubscriptionEntry);

				cacheResult(lcsSubscriptionEntry);

				uncachedPrimaryKeys.remove(lcsSubscriptionEntry.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(LCSSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
					LCSSubscriptionEntryImpl.class, primaryKey, nullModel);
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
	 * Returns all the l c s subscription entries.
	 *
	 * @return the l c s subscription entries
	 */
	@Override
	public List<LCSSubscriptionEntry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the l c s subscription entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of l c s subscription entries
	 * @param end the upper bound of the range of l c s subscription entries (not inclusive)
	 * @return the range of l c s subscription entries
	 */
	@Override
	public List<LCSSubscriptionEntry> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the l c s subscription entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of l c s subscription entries
	 * @param end the upper bound of the range of l c s subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of l c s subscription entries
	 */
	@Override
	public List<LCSSubscriptionEntry> findAll(int start, int end,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the l c s subscription entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of l c s subscription entries
	 * @param end the upper bound of the range of l c s subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of l c s subscription entries
	 */
	@Override
	public List<LCSSubscriptionEntry> findAll(int start, int end,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator,
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

		List<LCSSubscriptionEntry> list = null;

		if (retrieveFromCache) {
			list = (List<LCSSubscriptionEntry>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_LCSSUBSCRIPTIONENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_LCSSUBSCRIPTIONENTRY;

				if (pagination) {
					sql = sql.concat(LCSSubscriptionEntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<LCSSubscriptionEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSSubscriptionEntry>)QueryUtil.list(q,
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
	 * Removes all the l c s subscription entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (LCSSubscriptionEntry lcsSubscriptionEntry : findAll()) {
			remove(lcsSubscriptionEntry);
		}
	}

	/**
	 * Returns the number of l c s subscription entries.
	 *
	 * @return the number of l c s subscription entries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_LCSSUBSCRIPTIONENTRY);

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
		return LCSSubscriptionEntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the l c s subscription entry persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(LCSSubscriptionEntryImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_LCSSUBSCRIPTIONENTRY = "SELECT lcsSubscriptionEntry FROM LCSSubscriptionEntry lcsSubscriptionEntry";
	private static final String _SQL_SELECT_LCSSUBSCRIPTIONENTRY_WHERE_PKS_IN = "SELECT lcsSubscriptionEntry FROM LCSSubscriptionEntry lcsSubscriptionEntry WHERE lcsSubscriptionEntryId IN (";
	private static final String _SQL_SELECT_LCSSUBSCRIPTIONENTRY_WHERE = "SELECT lcsSubscriptionEntry FROM LCSSubscriptionEntry lcsSubscriptionEntry WHERE ";
	private static final String _SQL_COUNT_LCSSUBSCRIPTIONENTRY = "SELECT COUNT(lcsSubscriptionEntry) FROM LCSSubscriptionEntry lcsSubscriptionEntry";
	private static final String _SQL_COUNT_LCSSUBSCRIPTIONENTRY_WHERE = "SELECT COUNT(lcsSubscriptionEntry) FROM LCSSubscriptionEntry lcsSubscriptionEntry WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "lcsSubscriptionEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No LCSSubscriptionEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No LCSSubscriptionEntry exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(LCSSubscriptionEntryPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"type", "active"
			});
}