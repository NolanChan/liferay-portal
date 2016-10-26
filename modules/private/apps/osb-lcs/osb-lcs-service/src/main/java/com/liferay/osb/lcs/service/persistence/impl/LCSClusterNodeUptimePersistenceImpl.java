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

import com.liferay.osb.lcs.exception.NoSuchLCSClusterNodeUptimeException;
import com.liferay.osb.lcs.model.LCSClusterNodeUptime;
import com.liferay.osb.lcs.model.impl.LCSClusterNodeUptimeImpl;
import com.liferay.osb.lcs.model.impl.LCSClusterNodeUptimeModelImpl;
import com.liferay.osb.lcs.service.persistence.LCSClusterNodeUptimePersistence;

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
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the l c s cluster node uptime service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Igor Beslic
 * @see LCSClusterNodeUptimePersistence
 * @see com.liferay.osb.lcs.service.persistence.LCSClusterNodeUptimeUtil
 * @generated
 */
@ProviderType
public class LCSClusterNodeUptimePersistenceImpl extends BasePersistenceImpl<LCSClusterNodeUptime>
	implements LCSClusterNodeUptimePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link LCSClusterNodeUptimeUtil} to access the l c s cluster node uptime persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = LCSClusterNodeUptimeImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(LCSClusterNodeUptimeModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterNodeUptimeModelImpl.FINDER_CACHE_ENABLED,
			LCSClusterNodeUptimeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(LCSClusterNodeUptimeModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterNodeUptimeModelImpl.FINDER_CACHE_ENABLED,
			LCSClusterNodeUptimeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(LCSClusterNodeUptimeModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterNodeUptimeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_LCSCLUSTERNODEID =
		new FinderPath(LCSClusterNodeUptimeModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterNodeUptimeModelImpl.FINDER_CACHE_ENABLED,
			LCSClusterNodeUptimeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByLCSClusterNodeId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LCSCLUSTERNODEID =
		new FinderPath(LCSClusterNodeUptimeModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterNodeUptimeModelImpl.FINDER_CACHE_ENABLED,
			LCSClusterNodeUptimeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByLCSClusterNodeId", new String[] { Long.class.getName() },
			LCSClusterNodeUptimeModelImpl.LCSCLUSTERNODEID_COLUMN_BITMASK |
			LCSClusterNodeUptimeModelImpl.STARTTIME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_LCSCLUSTERNODEID = new FinderPath(LCSClusterNodeUptimeModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterNodeUptimeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByLCSClusterNodeId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_LCSCLUSTERNODEID =
		new FinderPath(LCSClusterNodeUptimeModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterNodeUptimeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByLCSClusterNodeId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the l c s cluster node uptimes where lcsClusterNodeId = &#63;.
	 *
	 * @param lcsClusterNodeId the lcs cluster node ID
	 * @return the matching l c s cluster node uptimes
	 */
	@Override
	public List<LCSClusterNodeUptime> findByLCSClusterNodeId(
		long lcsClusterNodeId) {
		return findByLCSClusterNodeId(lcsClusterNodeId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the l c s cluster node uptimes where lcsClusterNodeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeUptimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsClusterNodeId the lcs cluster node ID
	 * @param start the lower bound of the range of l c s cluster node uptimes
	 * @param end the upper bound of the range of l c s cluster node uptimes (not inclusive)
	 * @return the range of matching l c s cluster node uptimes
	 */
	@Override
	public List<LCSClusterNodeUptime> findByLCSClusterNodeId(
		long lcsClusterNodeId, int start, int end) {
		return findByLCSClusterNodeId(lcsClusterNodeId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the l c s cluster node uptimes where lcsClusterNodeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeUptimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsClusterNodeId the lcs cluster node ID
	 * @param start the lower bound of the range of l c s cluster node uptimes
	 * @param end the upper bound of the range of l c s cluster node uptimes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching l c s cluster node uptimes
	 */
	@Override
	public List<LCSClusterNodeUptime> findByLCSClusterNodeId(
		long lcsClusterNodeId, int start, int end,
		OrderByComparator<LCSClusterNodeUptime> orderByComparator) {
		return findByLCSClusterNodeId(lcsClusterNodeId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the l c s cluster node uptimes where lcsClusterNodeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeUptimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsClusterNodeId the lcs cluster node ID
	 * @param start the lower bound of the range of l c s cluster node uptimes
	 * @param end the upper bound of the range of l c s cluster node uptimes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching l c s cluster node uptimes
	 */
	@Override
	public List<LCSClusterNodeUptime> findByLCSClusterNodeId(
		long lcsClusterNodeId, int start, int end,
		OrderByComparator<LCSClusterNodeUptime> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LCSCLUSTERNODEID;
			finderArgs = new Object[] { lcsClusterNodeId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_LCSCLUSTERNODEID;
			finderArgs = new Object[] {
					lcsClusterNodeId,
					
					start, end, orderByComparator
				};
		}

		List<LCSClusterNodeUptime> list = null;

		if (retrieveFromCache) {
			list = (List<LCSClusterNodeUptime>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LCSClusterNodeUptime lcsClusterNodeUptime : list) {
					if ((lcsClusterNodeId != lcsClusterNodeUptime.getLcsClusterNodeId())) {
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

			query.append(_SQL_SELECT_LCSCLUSTERNODEUPTIME_WHERE);

			query.append(_FINDER_COLUMN_LCSCLUSTERNODEID_LCSCLUSTERNODEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LCSClusterNodeUptimeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(lcsClusterNodeId);

				if (!pagination) {
					list = (List<LCSClusterNodeUptime>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSClusterNodeUptime>)QueryUtil.list(q,
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
	 * Returns the first l c s cluster node uptime in the ordered set where lcsClusterNodeId = &#63;.
	 *
	 * @param lcsClusterNodeId the lcs cluster node ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s cluster node uptime
	 * @throws NoSuchLCSClusterNodeUptimeException if a matching l c s cluster node uptime could not be found
	 */
	@Override
	public LCSClusterNodeUptime findByLCSClusterNodeId_First(
		long lcsClusterNodeId,
		OrderByComparator<LCSClusterNodeUptime> orderByComparator)
		throws NoSuchLCSClusterNodeUptimeException {
		LCSClusterNodeUptime lcsClusterNodeUptime = fetchByLCSClusterNodeId_First(lcsClusterNodeId,
				orderByComparator);

		if (lcsClusterNodeUptime != null) {
			return lcsClusterNodeUptime;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("lcsClusterNodeId=");
		msg.append(lcsClusterNodeId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSClusterNodeUptimeException(msg.toString());
	}

	/**
	 * Returns the first l c s cluster node uptime in the ordered set where lcsClusterNodeId = &#63;.
	 *
	 * @param lcsClusterNodeId the lcs cluster node ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s cluster node uptime, or <code>null</code> if a matching l c s cluster node uptime could not be found
	 */
	@Override
	public LCSClusterNodeUptime fetchByLCSClusterNodeId_First(
		long lcsClusterNodeId,
		OrderByComparator<LCSClusterNodeUptime> orderByComparator) {
		List<LCSClusterNodeUptime> list = findByLCSClusterNodeId(lcsClusterNodeId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last l c s cluster node uptime in the ordered set where lcsClusterNodeId = &#63;.
	 *
	 * @param lcsClusterNodeId the lcs cluster node ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s cluster node uptime
	 * @throws NoSuchLCSClusterNodeUptimeException if a matching l c s cluster node uptime could not be found
	 */
	@Override
	public LCSClusterNodeUptime findByLCSClusterNodeId_Last(
		long lcsClusterNodeId,
		OrderByComparator<LCSClusterNodeUptime> orderByComparator)
		throws NoSuchLCSClusterNodeUptimeException {
		LCSClusterNodeUptime lcsClusterNodeUptime = fetchByLCSClusterNodeId_Last(lcsClusterNodeId,
				orderByComparator);

		if (lcsClusterNodeUptime != null) {
			return lcsClusterNodeUptime;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("lcsClusterNodeId=");
		msg.append(lcsClusterNodeId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSClusterNodeUptimeException(msg.toString());
	}

	/**
	 * Returns the last l c s cluster node uptime in the ordered set where lcsClusterNodeId = &#63;.
	 *
	 * @param lcsClusterNodeId the lcs cluster node ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s cluster node uptime, or <code>null</code> if a matching l c s cluster node uptime could not be found
	 */
	@Override
	public LCSClusterNodeUptime fetchByLCSClusterNodeId_Last(
		long lcsClusterNodeId,
		OrderByComparator<LCSClusterNodeUptime> orderByComparator) {
		int count = countByLCSClusterNodeId(lcsClusterNodeId);

		if (count == 0) {
			return null;
		}

		List<LCSClusterNodeUptime> list = findByLCSClusterNodeId(lcsClusterNodeId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the l c s cluster node uptimes before and after the current l c s cluster node uptime in the ordered set where lcsClusterNodeId = &#63;.
	 *
	 * @param lcsClusterNodeUptimeId the primary key of the current l c s cluster node uptime
	 * @param lcsClusterNodeId the lcs cluster node ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next l c s cluster node uptime
	 * @throws NoSuchLCSClusterNodeUptimeException if a l c s cluster node uptime with the primary key could not be found
	 */
	@Override
	public LCSClusterNodeUptime[] findByLCSClusterNodeId_PrevAndNext(
		long lcsClusterNodeUptimeId, long lcsClusterNodeId,
		OrderByComparator<LCSClusterNodeUptime> orderByComparator)
		throws NoSuchLCSClusterNodeUptimeException {
		LCSClusterNodeUptime lcsClusterNodeUptime = findByPrimaryKey(lcsClusterNodeUptimeId);

		Session session = null;

		try {
			session = openSession();

			LCSClusterNodeUptime[] array = new LCSClusterNodeUptimeImpl[3];

			array[0] = getByLCSClusterNodeId_PrevAndNext(session,
					lcsClusterNodeUptime, lcsClusterNodeId, orderByComparator,
					true);

			array[1] = lcsClusterNodeUptime;

			array[2] = getByLCSClusterNodeId_PrevAndNext(session,
					lcsClusterNodeUptime, lcsClusterNodeId, orderByComparator,
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

	protected LCSClusterNodeUptime getByLCSClusterNodeId_PrevAndNext(
		Session session, LCSClusterNodeUptime lcsClusterNodeUptime,
		long lcsClusterNodeId,
		OrderByComparator<LCSClusterNodeUptime> orderByComparator,
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

		query.append(_SQL_SELECT_LCSCLUSTERNODEUPTIME_WHERE);

		query.append(_FINDER_COLUMN_LCSCLUSTERNODEID_LCSCLUSTERNODEID_2);

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
			query.append(LCSClusterNodeUptimeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(lcsClusterNodeId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(lcsClusterNodeUptime);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LCSClusterNodeUptime> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the l c s cluster node uptimes where lcsClusterNodeId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeUptimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsClusterNodeIds the lcs cluster node IDs
	 * @return the matching l c s cluster node uptimes
	 */
	@Override
	public List<LCSClusterNodeUptime> findByLCSClusterNodeId(
		long[] lcsClusterNodeIds) {
		return findByLCSClusterNodeId(lcsClusterNodeIds, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the l c s cluster node uptimes where lcsClusterNodeId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeUptimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsClusterNodeIds the lcs cluster node IDs
	 * @param start the lower bound of the range of l c s cluster node uptimes
	 * @param end the upper bound of the range of l c s cluster node uptimes (not inclusive)
	 * @return the range of matching l c s cluster node uptimes
	 */
	@Override
	public List<LCSClusterNodeUptime> findByLCSClusterNodeId(
		long[] lcsClusterNodeIds, int start, int end) {
		return findByLCSClusterNodeId(lcsClusterNodeIds, start, end, null);
	}

	/**
	 * Returns an ordered range of all the l c s cluster node uptimes where lcsClusterNodeId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeUptimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsClusterNodeIds the lcs cluster node IDs
	 * @param start the lower bound of the range of l c s cluster node uptimes
	 * @param end the upper bound of the range of l c s cluster node uptimes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching l c s cluster node uptimes
	 */
	@Override
	public List<LCSClusterNodeUptime> findByLCSClusterNodeId(
		long[] lcsClusterNodeIds, int start, int end,
		OrderByComparator<LCSClusterNodeUptime> orderByComparator) {
		return findByLCSClusterNodeId(lcsClusterNodeIds, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the l c s cluster node uptimes where lcsClusterNodeId = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeUptimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsClusterNodeId the lcs cluster node ID
	 * @param start the lower bound of the range of l c s cluster node uptimes
	 * @param end the upper bound of the range of l c s cluster node uptimes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching l c s cluster node uptimes
	 */
	@Override
	public List<LCSClusterNodeUptime> findByLCSClusterNodeId(
		long[] lcsClusterNodeIds, int start, int end,
		OrderByComparator<LCSClusterNodeUptime> orderByComparator,
		boolean retrieveFromCache) {
		if (lcsClusterNodeIds == null) {
			lcsClusterNodeIds = new long[0];
		}
		else if (lcsClusterNodeIds.length > 1) {
			lcsClusterNodeIds = ArrayUtil.unique(lcsClusterNodeIds);

			Arrays.sort(lcsClusterNodeIds);
		}

		if (lcsClusterNodeIds.length == 1) {
			return findByLCSClusterNodeId(lcsClusterNodeIds[0], start, end,
				orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] { StringUtil.merge(lcsClusterNodeIds) };
		}
		else {
			finderArgs = new Object[] {
					StringUtil.merge(lcsClusterNodeIds),
					
					start, end, orderByComparator
				};
		}

		List<LCSClusterNodeUptime> list = null;

		if (retrieveFromCache) {
			list = (List<LCSClusterNodeUptime>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_LCSCLUSTERNODEID,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LCSClusterNodeUptime lcsClusterNodeUptime : list) {
					if (!ArrayUtil.contains(lcsClusterNodeIds,
								lcsClusterNodeUptime.getLcsClusterNodeId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_LCSCLUSTERNODEUPTIME_WHERE);

			if (lcsClusterNodeIds.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_LCSCLUSTERNODEID_LCSCLUSTERNODEID_7);

				query.append(StringUtil.merge(lcsClusterNodeIds));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LCSClusterNodeUptimeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<LCSClusterNodeUptime>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSClusterNodeUptime>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_LCSCLUSTERNODEID,
					finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_LCSCLUSTERNODEID,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the l c s cluster node uptimes where lcsClusterNodeId = &#63; from the database.
	 *
	 * @param lcsClusterNodeId the lcs cluster node ID
	 */
	@Override
	public void removeByLCSClusterNodeId(long lcsClusterNodeId) {
		for (LCSClusterNodeUptime lcsClusterNodeUptime : findByLCSClusterNodeId(
				lcsClusterNodeId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(lcsClusterNodeUptime);
		}
	}

	/**
	 * Returns the number of l c s cluster node uptimes where lcsClusterNodeId = &#63;.
	 *
	 * @param lcsClusterNodeId the lcs cluster node ID
	 * @return the number of matching l c s cluster node uptimes
	 */
	@Override
	public int countByLCSClusterNodeId(long lcsClusterNodeId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_LCSCLUSTERNODEID;

		Object[] finderArgs = new Object[] { lcsClusterNodeId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LCSCLUSTERNODEUPTIME_WHERE);

			query.append(_FINDER_COLUMN_LCSCLUSTERNODEID_LCSCLUSTERNODEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(lcsClusterNodeId);

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

	/**
	 * Returns the number of l c s cluster node uptimes where lcsClusterNodeId = any &#63;.
	 *
	 * @param lcsClusterNodeIds the lcs cluster node IDs
	 * @return the number of matching l c s cluster node uptimes
	 */
	@Override
	public int countByLCSClusterNodeId(long[] lcsClusterNodeIds) {
		if (lcsClusterNodeIds == null) {
			lcsClusterNodeIds = new long[0];
		}
		else if (lcsClusterNodeIds.length > 1) {
			lcsClusterNodeIds = ArrayUtil.unique(lcsClusterNodeIds);

			Arrays.sort(lcsClusterNodeIds);
		}

		Object[] finderArgs = new Object[] { StringUtil.merge(lcsClusterNodeIds) };

		Long count = (Long)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_LCSCLUSTERNODEID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_LCSCLUSTERNODEUPTIME_WHERE);

			if (lcsClusterNodeIds.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_LCSCLUSTERNODEID_LCSCLUSTERNODEID_7);

				query.append(StringUtil.merge(lcsClusterNodeIds));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_LCSCLUSTERNODEID,
					finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_LCSCLUSTERNODEID,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_LCSCLUSTERNODEID_LCSCLUSTERNODEID_2 =
		"lcsClusterNodeUptime.lcsClusterNodeId = ?";
	private static final String _FINDER_COLUMN_LCSCLUSTERNODEID_LCSCLUSTERNODEID_7 =
		"lcsClusterNodeUptime.lcsClusterNodeId IN (";
	public static final FinderPath FINDER_PATH_FETCH_BY_LCNI_ST = new FinderPath(LCSClusterNodeUptimeModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterNodeUptimeModelImpl.FINDER_CACHE_ENABLED,
			LCSClusterNodeUptimeImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByLCNI_ST",
			new String[] { Long.class.getName(), Long.class.getName() },
			LCSClusterNodeUptimeModelImpl.LCSCLUSTERNODEID_COLUMN_BITMASK |
			LCSClusterNodeUptimeModelImpl.STARTTIME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_LCNI_ST = new FinderPath(LCSClusterNodeUptimeModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterNodeUptimeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByLCNI_ST",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns the l c s cluster node uptime where lcsClusterNodeId = &#63; and startTime = &#63; or throws a {@link NoSuchLCSClusterNodeUptimeException} if it could not be found.
	 *
	 * @param lcsClusterNodeId the lcs cluster node ID
	 * @param startTime the start time
	 * @return the matching l c s cluster node uptime
	 * @throws NoSuchLCSClusterNodeUptimeException if a matching l c s cluster node uptime could not be found
	 */
	@Override
	public LCSClusterNodeUptime findByLCNI_ST(long lcsClusterNodeId,
		long startTime) throws NoSuchLCSClusterNodeUptimeException {
		LCSClusterNodeUptime lcsClusterNodeUptime = fetchByLCNI_ST(lcsClusterNodeId,
				startTime);

		if (lcsClusterNodeUptime == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("lcsClusterNodeId=");
			msg.append(lcsClusterNodeId);

			msg.append(", startTime=");
			msg.append(startTime);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchLCSClusterNodeUptimeException(msg.toString());
		}

		return lcsClusterNodeUptime;
	}

	/**
	 * Returns the l c s cluster node uptime where lcsClusterNodeId = &#63; and startTime = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param lcsClusterNodeId the lcs cluster node ID
	 * @param startTime the start time
	 * @return the matching l c s cluster node uptime, or <code>null</code> if a matching l c s cluster node uptime could not be found
	 */
	@Override
	public LCSClusterNodeUptime fetchByLCNI_ST(long lcsClusterNodeId,
		long startTime) {
		return fetchByLCNI_ST(lcsClusterNodeId, startTime, true);
	}

	/**
	 * Returns the l c s cluster node uptime where lcsClusterNodeId = &#63; and startTime = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param lcsClusterNodeId the lcs cluster node ID
	 * @param startTime the start time
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching l c s cluster node uptime, or <code>null</code> if a matching l c s cluster node uptime could not be found
	 */
	@Override
	public LCSClusterNodeUptime fetchByLCNI_ST(long lcsClusterNodeId,
		long startTime, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { lcsClusterNodeId, startTime };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_LCNI_ST,
					finderArgs, this);
		}

		if (result instanceof LCSClusterNodeUptime) {
			LCSClusterNodeUptime lcsClusterNodeUptime = (LCSClusterNodeUptime)result;

			if ((lcsClusterNodeId != lcsClusterNodeUptime.getLcsClusterNodeId()) ||
					(startTime != lcsClusterNodeUptime.getStartTime())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_LCSCLUSTERNODEUPTIME_WHERE);

			query.append(_FINDER_COLUMN_LCNI_ST_LCSCLUSTERNODEID_2);

			query.append(_FINDER_COLUMN_LCNI_ST_STARTTIME_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(lcsClusterNodeId);

				qPos.add(startTime);

				List<LCSClusterNodeUptime> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_LCNI_ST,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"LCSClusterNodeUptimePersistenceImpl.fetchByLCNI_ST(long, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					LCSClusterNodeUptime lcsClusterNodeUptime = list.get(0);

					result = lcsClusterNodeUptime;

					cacheResult(lcsClusterNodeUptime);

					if ((lcsClusterNodeUptime.getLcsClusterNodeId() != lcsClusterNodeId) ||
							(lcsClusterNodeUptime.getStartTime() != startTime)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_LCNI_ST,
							finderArgs, lcsClusterNodeUptime);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_LCNI_ST,
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
			return (LCSClusterNodeUptime)result;
		}
	}

	/**
	 * Removes the l c s cluster node uptime where lcsClusterNodeId = &#63; and startTime = &#63; from the database.
	 *
	 * @param lcsClusterNodeId the lcs cluster node ID
	 * @param startTime the start time
	 * @return the l c s cluster node uptime that was removed
	 */
	@Override
	public LCSClusterNodeUptime removeByLCNI_ST(long lcsClusterNodeId,
		long startTime) throws NoSuchLCSClusterNodeUptimeException {
		LCSClusterNodeUptime lcsClusterNodeUptime = findByLCNI_ST(lcsClusterNodeId,
				startTime);

		return remove(lcsClusterNodeUptime);
	}

	/**
	 * Returns the number of l c s cluster node uptimes where lcsClusterNodeId = &#63; and startTime = &#63;.
	 *
	 * @param lcsClusterNodeId the lcs cluster node ID
	 * @param startTime the start time
	 * @return the number of matching l c s cluster node uptimes
	 */
	@Override
	public int countByLCNI_ST(long lcsClusterNodeId, long startTime) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_LCNI_ST;

		Object[] finderArgs = new Object[] { lcsClusterNodeId, startTime };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LCSCLUSTERNODEUPTIME_WHERE);

			query.append(_FINDER_COLUMN_LCNI_ST_LCSCLUSTERNODEID_2);

			query.append(_FINDER_COLUMN_LCNI_ST_STARTTIME_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(lcsClusterNodeId);

				qPos.add(startTime);

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

	private static final String _FINDER_COLUMN_LCNI_ST_LCSCLUSTERNODEID_2 = "lcsClusterNodeUptime.lcsClusterNodeId = ? AND ";
	private static final String _FINDER_COLUMN_LCNI_ST_STARTTIME_2 = "lcsClusterNodeUptime.startTime = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_LCNI_ET = new FinderPath(LCSClusterNodeUptimeModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterNodeUptimeModelImpl.FINDER_CACHE_ENABLED,
			LCSClusterNodeUptimeImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByLCNI_ET",
			new String[] { Long.class.getName(), Long.class.getName() },
			LCSClusterNodeUptimeModelImpl.LCSCLUSTERNODEID_COLUMN_BITMASK |
			LCSClusterNodeUptimeModelImpl.ENDTIME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_LCNI_ET = new FinderPath(LCSClusterNodeUptimeModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterNodeUptimeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByLCNI_ET",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns the l c s cluster node uptime where lcsClusterNodeId = &#63; and endTime = &#63; or throws a {@link NoSuchLCSClusterNodeUptimeException} if it could not be found.
	 *
	 * @param lcsClusterNodeId the lcs cluster node ID
	 * @param endTime the end time
	 * @return the matching l c s cluster node uptime
	 * @throws NoSuchLCSClusterNodeUptimeException if a matching l c s cluster node uptime could not be found
	 */
	@Override
	public LCSClusterNodeUptime findByLCNI_ET(long lcsClusterNodeId,
		long endTime) throws NoSuchLCSClusterNodeUptimeException {
		LCSClusterNodeUptime lcsClusterNodeUptime = fetchByLCNI_ET(lcsClusterNodeId,
				endTime);

		if (lcsClusterNodeUptime == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("lcsClusterNodeId=");
			msg.append(lcsClusterNodeId);

			msg.append(", endTime=");
			msg.append(endTime);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchLCSClusterNodeUptimeException(msg.toString());
		}

		return lcsClusterNodeUptime;
	}

	/**
	 * Returns the l c s cluster node uptime where lcsClusterNodeId = &#63; and endTime = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param lcsClusterNodeId the lcs cluster node ID
	 * @param endTime the end time
	 * @return the matching l c s cluster node uptime, or <code>null</code> if a matching l c s cluster node uptime could not be found
	 */
	@Override
	public LCSClusterNodeUptime fetchByLCNI_ET(long lcsClusterNodeId,
		long endTime) {
		return fetchByLCNI_ET(lcsClusterNodeId, endTime, true);
	}

	/**
	 * Returns the l c s cluster node uptime where lcsClusterNodeId = &#63; and endTime = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param lcsClusterNodeId the lcs cluster node ID
	 * @param endTime the end time
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching l c s cluster node uptime, or <code>null</code> if a matching l c s cluster node uptime could not be found
	 */
	@Override
	public LCSClusterNodeUptime fetchByLCNI_ET(long lcsClusterNodeId,
		long endTime, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { lcsClusterNodeId, endTime };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_LCNI_ET,
					finderArgs, this);
		}

		if (result instanceof LCSClusterNodeUptime) {
			LCSClusterNodeUptime lcsClusterNodeUptime = (LCSClusterNodeUptime)result;

			if ((lcsClusterNodeId != lcsClusterNodeUptime.getLcsClusterNodeId()) ||
					(endTime != lcsClusterNodeUptime.getEndTime())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_LCSCLUSTERNODEUPTIME_WHERE);

			query.append(_FINDER_COLUMN_LCNI_ET_LCSCLUSTERNODEID_2);

			query.append(_FINDER_COLUMN_LCNI_ET_ENDTIME_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(lcsClusterNodeId);

				qPos.add(endTime);

				List<LCSClusterNodeUptime> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_LCNI_ET,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"LCSClusterNodeUptimePersistenceImpl.fetchByLCNI_ET(long, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					LCSClusterNodeUptime lcsClusterNodeUptime = list.get(0);

					result = lcsClusterNodeUptime;

					cacheResult(lcsClusterNodeUptime);

					if ((lcsClusterNodeUptime.getLcsClusterNodeId() != lcsClusterNodeId) ||
							(lcsClusterNodeUptime.getEndTime() != endTime)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_LCNI_ET,
							finderArgs, lcsClusterNodeUptime);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_LCNI_ET,
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
			return (LCSClusterNodeUptime)result;
		}
	}

	/**
	 * Removes the l c s cluster node uptime where lcsClusterNodeId = &#63; and endTime = &#63; from the database.
	 *
	 * @param lcsClusterNodeId the lcs cluster node ID
	 * @param endTime the end time
	 * @return the l c s cluster node uptime that was removed
	 */
	@Override
	public LCSClusterNodeUptime removeByLCNI_ET(long lcsClusterNodeId,
		long endTime) throws NoSuchLCSClusterNodeUptimeException {
		LCSClusterNodeUptime lcsClusterNodeUptime = findByLCNI_ET(lcsClusterNodeId,
				endTime);

		return remove(lcsClusterNodeUptime);
	}

	/**
	 * Returns the number of l c s cluster node uptimes where lcsClusterNodeId = &#63; and endTime = &#63;.
	 *
	 * @param lcsClusterNodeId the lcs cluster node ID
	 * @param endTime the end time
	 * @return the number of matching l c s cluster node uptimes
	 */
	@Override
	public int countByLCNI_ET(long lcsClusterNodeId, long endTime) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_LCNI_ET;

		Object[] finderArgs = new Object[] { lcsClusterNodeId, endTime };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LCSCLUSTERNODEUPTIME_WHERE);

			query.append(_FINDER_COLUMN_LCNI_ET_LCSCLUSTERNODEID_2);

			query.append(_FINDER_COLUMN_LCNI_ET_ENDTIME_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(lcsClusterNodeId);

				qPos.add(endTime);

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

	private static final String _FINDER_COLUMN_LCNI_ET_LCSCLUSTERNODEID_2 = "lcsClusterNodeUptime.lcsClusterNodeId = ? AND ";
	private static final String _FINDER_COLUMN_LCNI_ET_ENDTIME_2 = "lcsClusterNodeUptime.endTime = ?";

	public LCSClusterNodeUptimePersistenceImpl() {
		setModelClass(LCSClusterNodeUptime.class);
	}

	/**
	 * Caches the l c s cluster node uptime in the entity cache if it is enabled.
	 *
	 * @param lcsClusterNodeUptime the l c s cluster node uptime
	 */
	@Override
	public void cacheResult(LCSClusterNodeUptime lcsClusterNodeUptime) {
		entityCache.putResult(LCSClusterNodeUptimeModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterNodeUptimeImpl.class,
			lcsClusterNodeUptime.getPrimaryKey(), lcsClusterNodeUptime);

		finderCache.putResult(FINDER_PATH_FETCH_BY_LCNI_ST,
			new Object[] {
				lcsClusterNodeUptime.getLcsClusterNodeId(),
				lcsClusterNodeUptime.getStartTime()
			}, lcsClusterNodeUptime);

		finderCache.putResult(FINDER_PATH_FETCH_BY_LCNI_ET,
			new Object[] {
				lcsClusterNodeUptime.getLcsClusterNodeId(),
				lcsClusterNodeUptime.getEndTime()
			}, lcsClusterNodeUptime);

		lcsClusterNodeUptime.resetOriginalValues();
	}

	/**
	 * Caches the l c s cluster node uptimes in the entity cache if it is enabled.
	 *
	 * @param lcsClusterNodeUptimes the l c s cluster node uptimes
	 */
	@Override
	public void cacheResult(List<LCSClusterNodeUptime> lcsClusterNodeUptimes) {
		for (LCSClusterNodeUptime lcsClusterNodeUptime : lcsClusterNodeUptimes) {
			if (entityCache.getResult(
						LCSClusterNodeUptimeModelImpl.ENTITY_CACHE_ENABLED,
						LCSClusterNodeUptimeImpl.class,
						lcsClusterNodeUptime.getPrimaryKey()) == null) {
				cacheResult(lcsClusterNodeUptime);
			}
			else {
				lcsClusterNodeUptime.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all l c s cluster node uptimes.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(LCSClusterNodeUptimeImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the l c s cluster node uptime.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LCSClusterNodeUptime lcsClusterNodeUptime) {
		entityCache.removeResult(LCSClusterNodeUptimeModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterNodeUptimeImpl.class, lcsClusterNodeUptime.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((LCSClusterNodeUptimeModelImpl)lcsClusterNodeUptime);
	}

	@Override
	public void clearCache(List<LCSClusterNodeUptime> lcsClusterNodeUptimes) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (LCSClusterNodeUptime lcsClusterNodeUptime : lcsClusterNodeUptimes) {
			entityCache.removeResult(LCSClusterNodeUptimeModelImpl.ENTITY_CACHE_ENABLED,
				LCSClusterNodeUptimeImpl.class,
				lcsClusterNodeUptime.getPrimaryKey());

			clearUniqueFindersCache((LCSClusterNodeUptimeModelImpl)lcsClusterNodeUptime);
		}
	}

	protected void cacheUniqueFindersCache(
		LCSClusterNodeUptimeModelImpl lcsClusterNodeUptimeModelImpl,
		boolean isNew) {
		if (isNew) {
			Object[] args = new Object[] {
					lcsClusterNodeUptimeModelImpl.getLcsClusterNodeId(),
					lcsClusterNodeUptimeModelImpl.getStartTime()
				};

			finderCache.putResult(FINDER_PATH_COUNT_BY_LCNI_ST, args,
				Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_LCNI_ST, args,
				lcsClusterNodeUptimeModelImpl);

			args = new Object[] {
					lcsClusterNodeUptimeModelImpl.getLcsClusterNodeId(),
					lcsClusterNodeUptimeModelImpl.getEndTime()
				};

			finderCache.putResult(FINDER_PATH_COUNT_BY_LCNI_ET, args,
				Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_LCNI_ET, args,
				lcsClusterNodeUptimeModelImpl);
		}
		else {
			if ((lcsClusterNodeUptimeModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_LCNI_ST.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						lcsClusterNodeUptimeModelImpl.getLcsClusterNodeId(),
						lcsClusterNodeUptimeModelImpl.getStartTime()
					};

				finderCache.putResult(FINDER_PATH_COUNT_BY_LCNI_ST, args,
					Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_LCNI_ST, args,
					lcsClusterNodeUptimeModelImpl);
			}

			if ((lcsClusterNodeUptimeModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_LCNI_ET.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						lcsClusterNodeUptimeModelImpl.getLcsClusterNodeId(),
						lcsClusterNodeUptimeModelImpl.getEndTime()
					};

				finderCache.putResult(FINDER_PATH_COUNT_BY_LCNI_ET, args,
					Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_LCNI_ET, args,
					lcsClusterNodeUptimeModelImpl);
			}
		}
	}

	protected void clearUniqueFindersCache(
		LCSClusterNodeUptimeModelImpl lcsClusterNodeUptimeModelImpl) {
		Object[] args = new Object[] {
				lcsClusterNodeUptimeModelImpl.getLcsClusterNodeId(),
				lcsClusterNodeUptimeModelImpl.getStartTime()
			};

		finderCache.removeResult(FINDER_PATH_COUNT_BY_LCNI_ST, args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_LCNI_ST, args);

		if ((lcsClusterNodeUptimeModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_LCNI_ST.getColumnBitmask()) != 0) {
			args = new Object[] {
					lcsClusterNodeUptimeModelImpl.getOriginalLcsClusterNodeId(),
					lcsClusterNodeUptimeModelImpl.getOriginalStartTime()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_LCNI_ST, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_LCNI_ST, args);
		}

		args = new Object[] {
				lcsClusterNodeUptimeModelImpl.getLcsClusterNodeId(),
				lcsClusterNodeUptimeModelImpl.getEndTime()
			};

		finderCache.removeResult(FINDER_PATH_COUNT_BY_LCNI_ET, args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_LCNI_ET, args);

		if ((lcsClusterNodeUptimeModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_LCNI_ET.getColumnBitmask()) != 0) {
			args = new Object[] {
					lcsClusterNodeUptimeModelImpl.getOriginalLcsClusterNodeId(),
					lcsClusterNodeUptimeModelImpl.getOriginalEndTime()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_LCNI_ET, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_LCNI_ET, args);
		}
	}

	/**
	 * Creates a new l c s cluster node uptime with the primary key. Does not add the l c s cluster node uptime to the database.
	 *
	 * @param lcsClusterNodeUptimeId the primary key for the new l c s cluster node uptime
	 * @return the new l c s cluster node uptime
	 */
	@Override
	public LCSClusterNodeUptime create(long lcsClusterNodeUptimeId) {
		LCSClusterNodeUptime lcsClusterNodeUptime = new LCSClusterNodeUptimeImpl();

		lcsClusterNodeUptime.setNew(true);
		lcsClusterNodeUptime.setPrimaryKey(lcsClusterNodeUptimeId);

		return lcsClusterNodeUptime;
	}

	/**
	 * Removes the l c s cluster node uptime with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param lcsClusterNodeUptimeId the primary key of the l c s cluster node uptime
	 * @return the l c s cluster node uptime that was removed
	 * @throws NoSuchLCSClusterNodeUptimeException if a l c s cluster node uptime with the primary key could not be found
	 */
	@Override
	public LCSClusterNodeUptime remove(long lcsClusterNodeUptimeId)
		throws NoSuchLCSClusterNodeUptimeException {
		return remove((Serializable)lcsClusterNodeUptimeId);
	}

	/**
	 * Removes the l c s cluster node uptime with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the l c s cluster node uptime
	 * @return the l c s cluster node uptime that was removed
	 * @throws NoSuchLCSClusterNodeUptimeException if a l c s cluster node uptime with the primary key could not be found
	 */
	@Override
	public LCSClusterNodeUptime remove(Serializable primaryKey)
		throws NoSuchLCSClusterNodeUptimeException {
		Session session = null;

		try {
			session = openSession();

			LCSClusterNodeUptime lcsClusterNodeUptime = (LCSClusterNodeUptime)session.get(LCSClusterNodeUptimeImpl.class,
					primaryKey);

			if (lcsClusterNodeUptime == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLCSClusterNodeUptimeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(lcsClusterNodeUptime);
		}
		catch (NoSuchLCSClusterNodeUptimeException nsee) {
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
	protected LCSClusterNodeUptime removeImpl(
		LCSClusterNodeUptime lcsClusterNodeUptime) {
		lcsClusterNodeUptime = toUnwrappedModel(lcsClusterNodeUptime);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(lcsClusterNodeUptime)) {
				lcsClusterNodeUptime = (LCSClusterNodeUptime)session.get(LCSClusterNodeUptimeImpl.class,
						lcsClusterNodeUptime.getPrimaryKeyObj());
			}

			if (lcsClusterNodeUptime != null) {
				session.delete(lcsClusterNodeUptime);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (lcsClusterNodeUptime != null) {
			clearCache(lcsClusterNodeUptime);
		}

		return lcsClusterNodeUptime;
	}

	@Override
	public LCSClusterNodeUptime updateImpl(
		LCSClusterNodeUptime lcsClusterNodeUptime) {
		lcsClusterNodeUptime = toUnwrappedModel(lcsClusterNodeUptime);

		boolean isNew = lcsClusterNodeUptime.isNew();

		LCSClusterNodeUptimeModelImpl lcsClusterNodeUptimeModelImpl = (LCSClusterNodeUptimeModelImpl)lcsClusterNodeUptime;

		Session session = null;

		try {
			session = openSession();

			if (lcsClusterNodeUptime.isNew()) {
				session.save(lcsClusterNodeUptime);

				lcsClusterNodeUptime.setNew(false);
			}
			else {
				lcsClusterNodeUptime = (LCSClusterNodeUptime)session.merge(lcsClusterNodeUptime);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !LCSClusterNodeUptimeModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((lcsClusterNodeUptimeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LCSCLUSTERNODEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						lcsClusterNodeUptimeModelImpl.getOriginalLcsClusterNodeId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_LCSCLUSTERNODEID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LCSCLUSTERNODEID,
					args);

				args = new Object[] {
						lcsClusterNodeUptimeModelImpl.getLcsClusterNodeId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_LCSCLUSTERNODEID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LCSCLUSTERNODEID,
					args);
			}
		}

		entityCache.putResult(LCSClusterNodeUptimeModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterNodeUptimeImpl.class,
			lcsClusterNodeUptime.getPrimaryKey(), lcsClusterNodeUptime, false);

		clearUniqueFindersCache(lcsClusterNodeUptimeModelImpl);
		cacheUniqueFindersCache(lcsClusterNodeUptimeModelImpl, isNew);

		lcsClusterNodeUptime.resetOriginalValues();

		return lcsClusterNodeUptime;
	}

	protected LCSClusterNodeUptime toUnwrappedModel(
		LCSClusterNodeUptime lcsClusterNodeUptime) {
		if (lcsClusterNodeUptime instanceof LCSClusterNodeUptimeImpl) {
			return lcsClusterNodeUptime;
		}

		LCSClusterNodeUptimeImpl lcsClusterNodeUptimeImpl = new LCSClusterNodeUptimeImpl();

		lcsClusterNodeUptimeImpl.setNew(lcsClusterNodeUptime.isNew());
		lcsClusterNodeUptimeImpl.setPrimaryKey(lcsClusterNodeUptime.getPrimaryKey());

		lcsClusterNodeUptimeImpl.setLcsClusterNodeUptimeId(lcsClusterNodeUptime.getLcsClusterNodeUptimeId());
		lcsClusterNodeUptimeImpl.setLcsClusterNodeId(lcsClusterNodeUptime.getLcsClusterNodeId());
		lcsClusterNodeUptimeImpl.setStartTime(lcsClusterNodeUptime.getStartTime());
		lcsClusterNodeUptimeImpl.setEndTime(lcsClusterNodeUptime.getEndTime());

		return lcsClusterNodeUptimeImpl;
	}

	/**
	 * Returns the l c s cluster node uptime with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the l c s cluster node uptime
	 * @return the l c s cluster node uptime
	 * @throws NoSuchLCSClusterNodeUptimeException if a l c s cluster node uptime with the primary key could not be found
	 */
	@Override
	public LCSClusterNodeUptime findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLCSClusterNodeUptimeException {
		LCSClusterNodeUptime lcsClusterNodeUptime = fetchByPrimaryKey(primaryKey);

		if (lcsClusterNodeUptime == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLCSClusterNodeUptimeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return lcsClusterNodeUptime;
	}

	/**
	 * Returns the l c s cluster node uptime with the primary key or throws a {@link NoSuchLCSClusterNodeUptimeException} if it could not be found.
	 *
	 * @param lcsClusterNodeUptimeId the primary key of the l c s cluster node uptime
	 * @return the l c s cluster node uptime
	 * @throws NoSuchLCSClusterNodeUptimeException if a l c s cluster node uptime with the primary key could not be found
	 */
	@Override
	public LCSClusterNodeUptime findByPrimaryKey(long lcsClusterNodeUptimeId)
		throws NoSuchLCSClusterNodeUptimeException {
		return findByPrimaryKey((Serializable)lcsClusterNodeUptimeId);
	}

	/**
	 * Returns the l c s cluster node uptime with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the l c s cluster node uptime
	 * @return the l c s cluster node uptime, or <code>null</code> if a l c s cluster node uptime with the primary key could not be found
	 */
	@Override
	public LCSClusterNodeUptime fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(LCSClusterNodeUptimeModelImpl.ENTITY_CACHE_ENABLED,
				LCSClusterNodeUptimeImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		LCSClusterNodeUptime lcsClusterNodeUptime = (LCSClusterNodeUptime)serializable;

		if (lcsClusterNodeUptime == null) {
			Session session = null;

			try {
				session = openSession();

				lcsClusterNodeUptime = (LCSClusterNodeUptime)session.get(LCSClusterNodeUptimeImpl.class,
						primaryKey);

				if (lcsClusterNodeUptime != null) {
					cacheResult(lcsClusterNodeUptime);
				}
				else {
					entityCache.putResult(LCSClusterNodeUptimeModelImpl.ENTITY_CACHE_ENABLED,
						LCSClusterNodeUptimeImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(LCSClusterNodeUptimeModelImpl.ENTITY_CACHE_ENABLED,
					LCSClusterNodeUptimeImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return lcsClusterNodeUptime;
	}

	/**
	 * Returns the l c s cluster node uptime with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param lcsClusterNodeUptimeId the primary key of the l c s cluster node uptime
	 * @return the l c s cluster node uptime, or <code>null</code> if a l c s cluster node uptime with the primary key could not be found
	 */
	@Override
	public LCSClusterNodeUptime fetchByPrimaryKey(long lcsClusterNodeUptimeId) {
		return fetchByPrimaryKey((Serializable)lcsClusterNodeUptimeId);
	}

	@Override
	public Map<Serializable, LCSClusterNodeUptime> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, LCSClusterNodeUptime> map = new HashMap<Serializable, LCSClusterNodeUptime>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			LCSClusterNodeUptime lcsClusterNodeUptime = fetchByPrimaryKey(primaryKey);

			if (lcsClusterNodeUptime != null) {
				map.put(primaryKey, lcsClusterNodeUptime);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(LCSClusterNodeUptimeModelImpl.ENTITY_CACHE_ENABLED,
					LCSClusterNodeUptimeImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (LCSClusterNodeUptime)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_LCSCLUSTERNODEUPTIME_WHERE_PKS_IN);

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

			for (LCSClusterNodeUptime lcsClusterNodeUptime : (List<LCSClusterNodeUptime>)q.list()) {
				map.put(lcsClusterNodeUptime.getPrimaryKeyObj(),
					lcsClusterNodeUptime);

				cacheResult(lcsClusterNodeUptime);

				uncachedPrimaryKeys.remove(lcsClusterNodeUptime.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(LCSClusterNodeUptimeModelImpl.ENTITY_CACHE_ENABLED,
					LCSClusterNodeUptimeImpl.class, primaryKey, nullModel);
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
	 * Returns all the l c s cluster node uptimes.
	 *
	 * @return the l c s cluster node uptimes
	 */
	@Override
	public List<LCSClusterNodeUptime> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the l c s cluster node uptimes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeUptimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of l c s cluster node uptimes
	 * @param end the upper bound of the range of l c s cluster node uptimes (not inclusive)
	 * @return the range of l c s cluster node uptimes
	 */
	@Override
	public List<LCSClusterNodeUptime> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the l c s cluster node uptimes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeUptimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of l c s cluster node uptimes
	 * @param end the upper bound of the range of l c s cluster node uptimes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of l c s cluster node uptimes
	 */
	@Override
	public List<LCSClusterNodeUptime> findAll(int start, int end,
		OrderByComparator<LCSClusterNodeUptime> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the l c s cluster node uptimes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeUptimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of l c s cluster node uptimes
	 * @param end the upper bound of the range of l c s cluster node uptimes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of l c s cluster node uptimes
	 */
	@Override
	public List<LCSClusterNodeUptime> findAll(int start, int end,
		OrderByComparator<LCSClusterNodeUptime> orderByComparator,
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

		List<LCSClusterNodeUptime> list = null;

		if (retrieveFromCache) {
			list = (List<LCSClusterNodeUptime>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_LCSCLUSTERNODEUPTIME);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_LCSCLUSTERNODEUPTIME;

				if (pagination) {
					sql = sql.concat(LCSClusterNodeUptimeModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<LCSClusterNodeUptime>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSClusterNodeUptime>)QueryUtil.list(q,
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
	 * Removes all the l c s cluster node uptimes from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (LCSClusterNodeUptime lcsClusterNodeUptime : findAll()) {
			remove(lcsClusterNodeUptime);
		}
	}

	/**
	 * Returns the number of l c s cluster node uptimes.
	 *
	 * @return the number of l c s cluster node uptimes
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_LCSCLUSTERNODEUPTIME);

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
		return LCSClusterNodeUptimeModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the l c s cluster node uptime persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(LCSClusterNodeUptimeImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_LCSCLUSTERNODEUPTIME = "SELECT lcsClusterNodeUptime FROM LCSClusterNodeUptime lcsClusterNodeUptime";
	private static final String _SQL_SELECT_LCSCLUSTERNODEUPTIME_WHERE_PKS_IN = "SELECT lcsClusterNodeUptime FROM LCSClusterNodeUptime lcsClusterNodeUptime WHERE lcsClusterNodeUptimeId IN (";
	private static final String _SQL_SELECT_LCSCLUSTERNODEUPTIME_WHERE = "SELECT lcsClusterNodeUptime FROM LCSClusterNodeUptime lcsClusterNodeUptime WHERE ";
	private static final String _SQL_COUNT_LCSCLUSTERNODEUPTIME = "SELECT COUNT(lcsClusterNodeUptime) FROM LCSClusterNodeUptime lcsClusterNodeUptime";
	private static final String _SQL_COUNT_LCSCLUSTERNODEUPTIME_WHERE = "SELECT COUNT(lcsClusterNodeUptime) FROM LCSClusterNodeUptime lcsClusterNodeUptime WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "lcsClusterNodeUptime.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No LCSClusterNodeUptime exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No LCSClusterNodeUptime exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(LCSClusterNodeUptimePersistenceImpl.class);
}