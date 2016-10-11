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

import com.liferay.osb.lcs.exception.NoSuchLCSNotificationAuditEntryException;
import com.liferay.osb.lcs.model.LCSNotificationAuditEntry;
import com.liferay.osb.lcs.model.impl.LCSNotificationAuditEntryImpl;
import com.liferay.osb.lcs.model.impl.LCSNotificationAuditEntryModelImpl;
import com.liferay.osb.lcs.service.persistence.LCSNotificationAuditEntryPersistence;

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
import com.liferay.portal.kernel.util.SetUtil;
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
 * The persistence implementation for the l c s notification audit entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Igor Beslic
 * @see LCSNotificationAuditEntryPersistence
 * @see com.liferay.osb.lcs.service.persistence.LCSNotificationAuditEntryUtil
 * @generated
 */
@ProviderType
public class LCSNotificationAuditEntryPersistenceImpl
	extends BasePersistenceImpl<LCSNotificationAuditEntry>
	implements LCSNotificationAuditEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link LCSNotificationAuditEntryUtil} to access the l c s notification audit entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = LCSNotificationAuditEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(LCSNotificationAuditEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSNotificationAuditEntryModelImpl.FINDER_CACHE_ENABLED,
			LCSNotificationAuditEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(LCSNotificationAuditEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSNotificationAuditEntryModelImpl.FINDER_CACHE_ENABLED,
			LCSNotificationAuditEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(LCSNotificationAuditEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSNotificationAuditEntryModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_LCSCLUSTERNODEID =
		new FinderPath(LCSNotificationAuditEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSNotificationAuditEntryModelImpl.FINDER_CACHE_ENABLED,
			LCSNotificationAuditEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByLCSClusterNodeId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LCSCLUSTERNODEID =
		new FinderPath(LCSNotificationAuditEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSNotificationAuditEntryModelImpl.FINDER_CACHE_ENABLED,
			LCSNotificationAuditEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByLCSClusterNodeId", new String[] { Long.class.getName() },
			LCSNotificationAuditEntryModelImpl.LCSCLUSTERNODEID_COLUMN_BITMASK |
			LCSNotificationAuditEntryModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_LCSCLUSTERNODEID = new FinderPath(LCSNotificationAuditEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSNotificationAuditEntryModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByLCSClusterNodeId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_LCSCLUSTERNODEID =
		new FinderPath(LCSNotificationAuditEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSNotificationAuditEntryModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"countByLCSClusterNodeId", new String[] { Long.class.getName() });

	/**
	 * Returns all the l c s notification audit entries where lcsClusterNodeId = &#63;.
	 *
	 * @param lcsClusterNodeId the lcs cluster node ID
	 * @return the matching l c s notification audit entries
	 */
	@Override
	public List<LCSNotificationAuditEntry> findByLCSClusterNodeId(
		long lcsClusterNodeId) {
		return findByLCSClusterNodeId(lcsClusterNodeId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the l c s notification audit entries where lcsClusterNodeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSNotificationAuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsClusterNodeId the lcs cluster node ID
	 * @param start the lower bound of the range of l c s notification audit entries
	 * @param end the upper bound of the range of l c s notification audit entries (not inclusive)
	 * @return the range of matching l c s notification audit entries
	 */
	@Override
	public List<LCSNotificationAuditEntry> findByLCSClusterNodeId(
		long lcsClusterNodeId, int start, int end) {
		return findByLCSClusterNodeId(lcsClusterNodeId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the l c s notification audit entries where lcsClusterNodeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSNotificationAuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsClusterNodeId the lcs cluster node ID
	 * @param start the lower bound of the range of l c s notification audit entries
	 * @param end the upper bound of the range of l c s notification audit entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching l c s notification audit entries
	 */
	@Override
	public List<LCSNotificationAuditEntry> findByLCSClusterNodeId(
		long lcsClusterNodeId, int start, int end,
		OrderByComparator<LCSNotificationAuditEntry> orderByComparator) {
		return findByLCSClusterNodeId(lcsClusterNodeId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the l c s notification audit entries where lcsClusterNodeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSNotificationAuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsClusterNodeId the lcs cluster node ID
	 * @param start the lower bound of the range of l c s notification audit entries
	 * @param end the upper bound of the range of l c s notification audit entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching l c s notification audit entries
	 */
	@Override
	public List<LCSNotificationAuditEntry> findByLCSClusterNodeId(
		long lcsClusterNodeId, int start, int end,
		OrderByComparator<LCSNotificationAuditEntry> orderByComparator,
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

		List<LCSNotificationAuditEntry> list = null;

		if (retrieveFromCache) {
			list = (List<LCSNotificationAuditEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LCSNotificationAuditEntry lcsNotificationAuditEntry : list) {
					if ((lcsClusterNodeId != lcsNotificationAuditEntry.getLcsClusterNodeId())) {
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

			query.append(_SQL_SELECT_LCSNOTIFICATIONAUDITENTRY_WHERE);

			query.append(_FINDER_COLUMN_LCSCLUSTERNODEID_LCSCLUSTERNODEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LCSNotificationAuditEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(lcsClusterNodeId);

				if (!pagination) {
					list = (List<LCSNotificationAuditEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSNotificationAuditEntry>)QueryUtil.list(q,
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
	 * Returns the first l c s notification audit entry in the ordered set where lcsClusterNodeId = &#63;.
	 *
	 * @param lcsClusterNodeId the lcs cluster node ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s notification audit entry
	 * @throws NoSuchLCSNotificationAuditEntryException if a matching l c s notification audit entry could not be found
	 */
	@Override
	public LCSNotificationAuditEntry findByLCSClusterNodeId_First(
		long lcsClusterNodeId,
		OrderByComparator<LCSNotificationAuditEntry> orderByComparator)
		throws NoSuchLCSNotificationAuditEntryException {
		LCSNotificationAuditEntry lcsNotificationAuditEntry = fetchByLCSClusterNodeId_First(lcsClusterNodeId,
				orderByComparator);

		if (lcsNotificationAuditEntry != null) {
			return lcsNotificationAuditEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("lcsClusterNodeId=");
		msg.append(lcsClusterNodeId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSNotificationAuditEntryException(msg.toString());
	}

	/**
	 * Returns the first l c s notification audit entry in the ordered set where lcsClusterNodeId = &#63;.
	 *
	 * @param lcsClusterNodeId the lcs cluster node ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s notification audit entry, or <code>null</code> if a matching l c s notification audit entry could not be found
	 */
	@Override
	public LCSNotificationAuditEntry fetchByLCSClusterNodeId_First(
		long lcsClusterNodeId,
		OrderByComparator<LCSNotificationAuditEntry> orderByComparator) {
		List<LCSNotificationAuditEntry> list = findByLCSClusterNodeId(lcsClusterNodeId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last l c s notification audit entry in the ordered set where lcsClusterNodeId = &#63;.
	 *
	 * @param lcsClusterNodeId the lcs cluster node ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s notification audit entry
	 * @throws NoSuchLCSNotificationAuditEntryException if a matching l c s notification audit entry could not be found
	 */
	@Override
	public LCSNotificationAuditEntry findByLCSClusterNodeId_Last(
		long lcsClusterNodeId,
		OrderByComparator<LCSNotificationAuditEntry> orderByComparator)
		throws NoSuchLCSNotificationAuditEntryException {
		LCSNotificationAuditEntry lcsNotificationAuditEntry = fetchByLCSClusterNodeId_Last(lcsClusterNodeId,
				orderByComparator);

		if (lcsNotificationAuditEntry != null) {
			return lcsNotificationAuditEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("lcsClusterNodeId=");
		msg.append(lcsClusterNodeId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSNotificationAuditEntryException(msg.toString());
	}

	/**
	 * Returns the last l c s notification audit entry in the ordered set where lcsClusterNodeId = &#63;.
	 *
	 * @param lcsClusterNodeId the lcs cluster node ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s notification audit entry, or <code>null</code> if a matching l c s notification audit entry could not be found
	 */
	@Override
	public LCSNotificationAuditEntry fetchByLCSClusterNodeId_Last(
		long lcsClusterNodeId,
		OrderByComparator<LCSNotificationAuditEntry> orderByComparator) {
		int count = countByLCSClusterNodeId(lcsClusterNodeId);

		if (count == 0) {
			return null;
		}

		List<LCSNotificationAuditEntry> list = findByLCSClusterNodeId(lcsClusterNodeId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the l c s notification audit entries before and after the current l c s notification audit entry in the ordered set where lcsClusterNodeId = &#63;.
	 *
	 * @param lcsNotificationAuditEntryId the primary key of the current l c s notification audit entry
	 * @param lcsClusterNodeId the lcs cluster node ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next l c s notification audit entry
	 * @throws NoSuchLCSNotificationAuditEntryException if a l c s notification audit entry with the primary key could not be found
	 */
	@Override
	public LCSNotificationAuditEntry[] findByLCSClusterNodeId_PrevAndNext(
		long lcsNotificationAuditEntryId, long lcsClusterNodeId,
		OrderByComparator<LCSNotificationAuditEntry> orderByComparator)
		throws NoSuchLCSNotificationAuditEntryException {
		LCSNotificationAuditEntry lcsNotificationAuditEntry = findByPrimaryKey(lcsNotificationAuditEntryId);

		Session session = null;

		try {
			session = openSession();

			LCSNotificationAuditEntry[] array = new LCSNotificationAuditEntryImpl[3];

			array[0] = getByLCSClusterNodeId_PrevAndNext(session,
					lcsNotificationAuditEntry, lcsClusterNodeId,
					orderByComparator, true);

			array[1] = lcsNotificationAuditEntry;

			array[2] = getByLCSClusterNodeId_PrevAndNext(session,
					lcsNotificationAuditEntry, lcsClusterNodeId,
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

	protected LCSNotificationAuditEntry getByLCSClusterNodeId_PrevAndNext(
		Session session, LCSNotificationAuditEntry lcsNotificationAuditEntry,
		long lcsClusterNodeId,
		OrderByComparator<LCSNotificationAuditEntry> orderByComparator,
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

		query.append(_SQL_SELECT_LCSNOTIFICATIONAUDITENTRY_WHERE);

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
			query.append(LCSNotificationAuditEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(lcsClusterNodeId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(lcsNotificationAuditEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LCSNotificationAuditEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the l c s notification audit entries where lcsClusterNodeId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSNotificationAuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsClusterNodeIds the lcs cluster node IDs
	 * @return the matching l c s notification audit entries
	 */
	@Override
	public List<LCSNotificationAuditEntry> findByLCSClusterNodeId(
		long[] lcsClusterNodeIds) {
		return findByLCSClusterNodeId(lcsClusterNodeIds, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the l c s notification audit entries where lcsClusterNodeId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSNotificationAuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsClusterNodeIds the lcs cluster node IDs
	 * @param start the lower bound of the range of l c s notification audit entries
	 * @param end the upper bound of the range of l c s notification audit entries (not inclusive)
	 * @return the range of matching l c s notification audit entries
	 */
	@Override
	public List<LCSNotificationAuditEntry> findByLCSClusterNodeId(
		long[] lcsClusterNodeIds, int start, int end) {
		return findByLCSClusterNodeId(lcsClusterNodeIds, start, end, null);
	}

	/**
	 * Returns an ordered range of all the l c s notification audit entries where lcsClusterNodeId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSNotificationAuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsClusterNodeIds the lcs cluster node IDs
	 * @param start the lower bound of the range of l c s notification audit entries
	 * @param end the upper bound of the range of l c s notification audit entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching l c s notification audit entries
	 */
	@Override
	public List<LCSNotificationAuditEntry> findByLCSClusterNodeId(
		long[] lcsClusterNodeIds, int start, int end,
		OrderByComparator<LCSNotificationAuditEntry> orderByComparator) {
		return findByLCSClusterNodeId(lcsClusterNodeIds, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the l c s notification audit entries where lcsClusterNodeId = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSNotificationAuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsClusterNodeId the lcs cluster node ID
	 * @param start the lower bound of the range of l c s notification audit entries
	 * @param end the upper bound of the range of l c s notification audit entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching l c s notification audit entries
	 */
	@Override
	public List<LCSNotificationAuditEntry> findByLCSClusterNodeId(
		long[] lcsClusterNodeIds, int start, int end,
		OrderByComparator<LCSNotificationAuditEntry> orderByComparator,
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

		List<LCSNotificationAuditEntry> list = null;

		if (retrieveFromCache) {
			list = (List<LCSNotificationAuditEntry>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_LCSCLUSTERNODEID,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LCSNotificationAuditEntry lcsNotificationAuditEntry : list) {
					if (!ArrayUtil.contains(lcsClusterNodeIds,
								lcsNotificationAuditEntry.getLcsClusterNodeId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_LCSNOTIFICATIONAUDITENTRY_WHERE);

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
				query.append(LCSNotificationAuditEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<LCSNotificationAuditEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSNotificationAuditEntry>)QueryUtil.list(q,
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
	 * Removes all the l c s notification audit entries where lcsClusterNodeId = &#63; from the database.
	 *
	 * @param lcsClusterNodeId the lcs cluster node ID
	 */
	@Override
	public void removeByLCSClusterNodeId(long lcsClusterNodeId) {
		for (LCSNotificationAuditEntry lcsNotificationAuditEntry : findByLCSClusterNodeId(
				lcsClusterNodeId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(lcsNotificationAuditEntry);
		}
	}

	/**
	 * Returns the number of l c s notification audit entries where lcsClusterNodeId = &#63;.
	 *
	 * @param lcsClusterNodeId the lcs cluster node ID
	 * @return the number of matching l c s notification audit entries
	 */
	@Override
	public int countByLCSClusterNodeId(long lcsClusterNodeId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_LCSCLUSTERNODEID;

		Object[] finderArgs = new Object[] { lcsClusterNodeId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LCSNOTIFICATIONAUDITENTRY_WHERE);

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
	 * Returns the number of l c s notification audit entries where lcsClusterNodeId = any &#63;.
	 *
	 * @param lcsClusterNodeIds the lcs cluster node IDs
	 * @return the number of matching l c s notification audit entries
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

			query.append(_SQL_COUNT_LCSNOTIFICATIONAUDITENTRY_WHERE);

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
		"lcsNotificationAuditEntry.lcsClusterNodeId = ?";
	private static final String _FINDER_COLUMN_LCSCLUSTERNODEID_LCSCLUSTERNODEID_7 =
		"lcsNotificationAuditEntry.lcsClusterNodeId IN (";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_U_LCNI_T = new FinderPath(LCSNotificationAuditEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSNotificationAuditEntryModelImpl.FINDER_CACHE_ENABLED,
			LCSNotificationAuditEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByU_LCNI_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_LCNI_T =
		new FinderPath(LCSNotificationAuditEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSNotificationAuditEntryModelImpl.FINDER_CACHE_ENABLED,
			LCSNotificationAuditEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByU_LCNI_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			LCSNotificationAuditEntryModelImpl.USERID_COLUMN_BITMASK |
			LCSNotificationAuditEntryModelImpl.LCSCLUSTERNODEID_COLUMN_BITMASK |
			LCSNotificationAuditEntryModelImpl.TYPE_COLUMN_BITMASK |
			LCSNotificationAuditEntryModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_LCNI_T = new FinderPath(LCSNotificationAuditEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSNotificationAuditEntryModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByU_LCNI_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the l c s notification audit entries where userId = &#63; and lcsClusterNodeId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param lcsClusterNodeId the lcs cluster node ID
	 * @param type the type
	 * @return the matching l c s notification audit entries
	 */
	@Override
	public List<LCSNotificationAuditEntry> findByU_LCNI_T(long userId,
		long lcsClusterNodeId, int type) {
		return findByU_LCNI_T(userId, lcsClusterNodeId, type,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the l c s notification audit entries where userId = &#63; and lcsClusterNodeId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSNotificationAuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param lcsClusterNodeId the lcs cluster node ID
	 * @param type the type
	 * @param start the lower bound of the range of l c s notification audit entries
	 * @param end the upper bound of the range of l c s notification audit entries (not inclusive)
	 * @return the range of matching l c s notification audit entries
	 */
	@Override
	public List<LCSNotificationAuditEntry> findByU_LCNI_T(long userId,
		long lcsClusterNodeId, int type, int start, int end) {
		return findByU_LCNI_T(userId, lcsClusterNodeId, type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the l c s notification audit entries where userId = &#63; and lcsClusterNodeId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSNotificationAuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param lcsClusterNodeId the lcs cluster node ID
	 * @param type the type
	 * @param start the lower bound of the range of l c s notification audit entries
	 * @param end the upper bound of the range of l c s notification audit entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching l c s notification audit entries
	 */
	@Override
	public List<LCSNotificationAuditEntry> findByU_LCNI_T(long userId,
		long lcsClusterNodeId, int type, int start, int end,
		OrderByComparator<LCSNotificationAuditEntry> orderByComparator) {
		return findByU_LCNI_T(userId, lcsClusterNodeId, type, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the l c s notification audit entries where userId = &#63; and lcsClusterNodeId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSNotificationAuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param lcsClusterNodeId the lcs cluster node ID
	 * @param type the type
	 * @param start the lower bound of the range of l c s notification audit entries
	 * @param end the upper bound of the range of l c s notification audit entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching l c s notification audit entries
	 */
	@Override
	public List<LCSNotificationAuditEntry> findByU_LCNI_T(long userId,
		long lcsClusterNodeId, int type, int start, int end,
		OrderByComparator<LCSNotificationAuditEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_LCNI_T;
			finderArgs = new Object[] { userId, lcsClusterNodeId, type };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_U_LCNI_T;
			finderArgs = new Object[] {
					userId, lcsClusterNodeId, type,
					
					start, end, orderByComparator
				};
		}

		List<LCSNotificationAuditEntry> list = null;

		if (retrieveFromCache) {
			list = (List<LCSNotificationAuditEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LCSNotificationAuditEntry lcsNotificationAuditEntry : list) {
					if ((userId != lcsNotificationAuditEntry.getUserId()) ||
							(lcsClusterNodeId != lcsNotificationAuditEntry.getLcsClusterNodeId()) ||
							(type != lcsNotificationAuditEntry.getType())) {
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

			query.append(_SQL_SELECT_LCSNOTIFICATIONAUDITENTRY_WHERE);

			query.append(_FINDER_COLUMN_U_LCNI_T_USERID_2);

			query.append(_FINDER_COLUMN_U_LCNI_T_LCSCLUSTERNODEID_2);

			query.append(_FINDER_COLUMN_U_LCNI_T_TYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LCSNotificationAuditEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(lcsClusterNodeId);

				qPos.add(type);

				if (!pagination) {
					list = (List<LCSNotificationAuditEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSNotificationAuditEntry>)QueryUtil.list(q,
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
	 * Returns the first l c s notification audit entry in the ordered set where userId = &#63; and lcsClusterNodeId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param lcsClusterNodeId the lcs cluster node ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s notification audit entry
	 * @throws NoSuchLCSNotificationAuditEntryException if a matching l c s notification audit entry could not be found
	 */
	@Override
	public LCSNotificationAuditEntry findByU_LCNI_T_First(long userId,
		long lcsClusterNodeId, int type,
		OrderByComparator<LCSNotificationAuditEntry> orderByComparator)
		throws NoSuchLCSNotificationAuditEntryException {
		LCSNotificationAuditEntry lcsNotificationAuditEntry = fetchByU_LCNI_T_First(userId,
				lcsClusterNodeId, type, orderByComparator);

		if (lcsNotificationAuditEntry != null) {
			return lcsNotificationAuditEntry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", lcsClusterNodeId=");
		msg.append(lcsClusterNodeId);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSNotificationAuditEntryException(msg.toString());
	}

	/**
	 * Returns the first l c s notification audit entry in the ordered set where userId = &#63; and lcsClusterNodeId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param lcsClusterNodeId the lcs cluster node ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s notification audit entry, or <code>null</code> if a matching l c s notification audit entry could not be found
	 */
	@Override
	public LCSNotificationAuditEntry fetchByU_LCNI_T_First(long userId,
		long lcsClusterNodeId, int type,
		OrderByComparator<LCSNotificationAuditEntry> orderByComparator) {
		List<LCSNotificationAuditEntry> list = findByU_LCNI_T(userId,
				lcsClusterNodeId, type, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last l c s notification audit entry in the ordered set where userId = &#63; and lcsClusterNodeId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param lcsClusterNodeId the lcs cluster node ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s notification audit entry
	 * @throws NoSuchLCSNotificationAuditEntryException if a matching l c s notification audit entry could not be found
	 */
	@Override
	public LCSNotificationAuditEntry findByU_LCNI_T_Last(long userId,
		long lcsClusterNodeId, int type,
		OrderByComparator<LCSNotificationAuditEntry> orderByComparator)
		throws NoSuchLCSNotificationAuditEntryException {
		LCSNotificationAuditEntry lcsNotificationAuditEntry = fetchByU_LCNI_T_Last(userId,
				lcsClusterNodeId, type, orderByComparator);

		if (lcsNotificationAuditEntry != null) {
			return lcsNotificationAuditEntry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", lcsClusterNodeId=");
		msg.append(lcsClusterNodeId);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSNotificationAuditEntryException(msg.toString());
	}

	/**
	 * Returns the last l c s notification audit entry in the ordered set where userId = &#63; and lcsClusterNodeId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param lcsClusterNodeId the lcs cluster node ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s notification audit entry, or <code>null</code> if a matching l c s notification audit entry could not be found
	 */
	@Override
	public LCSNotificationAuditEntry fetchByU_LCNI_T_Last(long userId,
		long lcsClusterNodeId, int type,
		OrderByComparator<LCSNotificationAuditEntry> orderByComparator) {
		int count = countByU_LCNI_T(userId, lcsClusterNodeId, type);

		if (count == 0) {
			return null;
		}

		List<LCSNotificationAuditEntry> list = findByU_LCNI_T(userId,
				lcsClusterNodeId, type, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the l c s notification audit entries before and after the current l c s notification audit entry in the ordered set where userId = &#63; and lcsClusterNodeId = &#63; and type = &#63;.
	 *
	 * @param lcsNotificationAuditEntryId the primary key of the current l c s notification audit entry
	 * @param userId the user ID
	 * @param lcsClusterNodeId the lcs cluster node ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next l c s notification audit entry
	 * @throws NoSuchLCSNotificationAuditEntryException if a l c s notification audit entry with the primary key could not be found
	 */
	@Override
	public LCSNotificationAuditEntry[] findByU_LCNI_T_PrevAndNext(
		long lcsNotificationAuditEntryId, long userId, long lcsClusterNodeId,
		int type, OrderByComparator<LCSNotificationAuditEntry> orderByComparator)
		throws NoSuchLCSNotificationAuditEntryException {
		LCSNotificationAuditEntry lcsNotificationAuditEntry = findByPrimaryKey(lcsNotificationAuditEntryId);

		Session session = null;

		try {
			session = openSession();

			LCSNotificationAuditEntry[] array = new LCSNotificationAuditEntryImpl[3];

			array[0] = getByU_LCNI_T_PrevAndNext(session,
					lcsNotificationAuditEntry, userId, lcsClusterNodeId, type,
					orderByComparator, true);

			array[1] = lcsNotificationAuditEntry;

			array[2] = getByU_LCNI_T_PrevAndNext(session,
					lcsNotificationAuditEntry, userId, lcsClusterNodeId, type,
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

	protected LCSNotificationAuditEntry getByU_LCNI_T_PrevAndNext(
		Session session, LCSNotificationAuditEntry lcsNotificationAuditEntry,
		long userId, long lcsClusterNodeId, int type,
		OrderByComparator<LCSNotificationAuditEntry> orderByComparator,
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

		query.append(_SQL_SELECT_LCSNOTIFICATIONAUDITENTRY_WHERE);

		query.append(_FINDER_COLUMN_U_LCNI_T_USERID_2);

		query.append(_FINDER_COLUMN_U_LCNI_T_LCSCLUSTERNODEID_2);

		query.append(_FINDER_COLUMN_U_LCNI_T_TYPE_2);

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
			query.append(LCSNotificationAuditEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		qPos.add(lcsClusterNodeId);

		qPos.add(type);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(lcsNotificationAuditEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LCSNotificationAuditEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the l c s notification audit entries where userId = &#63; and lcsClusterNodeId = &#63; and type = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param lcsClusterNodeId the lcs cluster node ID
	 * @param type the type
	 */
	@Override
	public void removeByU_LCNI_T(long userId, long lcsClusterNodeId, int type) {
		for (LCSNotificationAuditEntry lcsNotificationAuditEntry : findByU_LCNI_T(
				userId, lcsClusterNodeId, type, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(lcsNotificationAuditEntry);
		}
	}

	/**
	 * Returns the number of l c s notification audit entries where userId = &#63; and lcsClusterNodeId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param lcsClusterNodeId the lcs cluster node ID
	 * @param type the type
	 * @return the number of matching l c s notification audit entries
	 */
	@Override
	public int countByU_LCNI_T(long userId, long lcsClusterNodeId, int type) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_U_LCNI_T;

		Object[] finderArgs = new Object[] { userId, lcsClusterNodeId, type };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_LCSNOTIFICATIONAUDITENTRY_WHERE);

			query.append(_FINDER_COLUMN_U_LCNI_T_USERID_2);

			query.append(_FINDER_COLUMN_U_LCNI_T_LCSCLUSTERNODEID_2);

			query.append(_FINDER_COLUMN_U_LCNI_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(lcsClusterNodeId);

				qPos.add(type);

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

	private static final String _FINDER_COLUMN_U_LCNI_T_USERID_2 = "lcsNotificationAuditEntry.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_LCNI_T_LCSCLUSTERNODEID_2 = "lcsNotificationAuditEntry.lcsClusterNodeId = ? AND ";
	private static final String _FINDER_COLUMN_U_LCNI_T_TYPE_2 = "lcsNotificationAuditEntry.type = ?";

	public LCSNotificationAuditEntryPersistenceImpl() {
		setModelClass(LCSNotificationAuditEntry.class);
	}

	/**
	 * Caches the l c s notification audit entry in the entity cache if it is enabled.
	 *
	 * @param lcsNotificationAuditEntry the l c s notification audit entry
	 */
	@Override
	public void cacheResult(LCSNotificationAuditEntry lcsNotificationAuditEntry) {
		entityCache.putResult(LCSNotificationAuditEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSNotificationAuditEntryImpl.class,
			lcsNotificationAuditEntry.getPrimaryKey(), lcsNotificationAuditEntry);

		lcsNotificationAuditEntry.resetOriginalValues();
	}

	/**
	 * Caches the l c s notification audit entries in the entity cache if it is enabled.
	 *
	 * @param lcsNotificationAuditEntries the l c s notification audit entries
	 */
	@Override
	public void cacheResult(
		List<LCSNotificationAuditEntry> lcsNotificationAuditEntries) {
		for (LCSNotificationAuditEntry lcsNotificationAuditEntry : lcsNotificationAuditEntries) {
			if (entityCache.getResult(
						LCSNotificationAuditEntryModelImpl.ENTITY_CACHE_ENABLED,
						LCSNotificationAuditEntryImpl.class,
						lcsNotificationAuditEntry.getPrimaryKey()) == null) {
				cacheResult(lcsNotificationAuditEntry);
			}
			else {
				lcsNotificationAuditEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all l c s notification audit entries.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(LCSNotificationAuditEntryImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the l c s notification audit entry.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LCSNotificationAuditEntry lcsNotificationAuditEntry) {
		entityCache.removeResult(LCSNotificationAuditEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSNotificationAuditEntryImpl.class,
			lcsNotificationAuditEntry.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<LCSNotificationAuditEntry> lcsNotificationAuditEntries) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (LCSNotificationAuditEntry lcsNotificationAuditEntry : lcsNotificationAuditEntries) {
			entityCache.removeResult(LCSNotificationAuditEntryModelImpl.ENTITY_CACHE_ENABLED,
				LCSNotificationAuditEntryImpl.class,
				lcsNotificationAuditEntry.getPrimaryKey());
		}
	}

	/**
	 * Creates a new l c s notification audit entry with the primary key. Does not add the l c s notification audit entry to the database.
	 *
	 * @param lcsNotificationAuditEntryId the primary key for the new l c s notification audit entry
	 * @return the new l c s notification audit entry
	 */
	@Override
	public LCSNotificationAuditEntry create(long lcsNotificationAuditEntryId) {
		LCSNotificationAuditEntry lcsNotificationAuditEntry = new LCSNotificationAuditEntryImpl();

		lcsNotificationAuditEntry.setNew(true);
		lcsNotificationAuditEntry.setPrimaryKey(lcsNotificationAuditEntryId);

		return lcsNotificationAuditEntry;
	}

	/**
	 * Removes the l c s notification audit entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param lcsNotificationAuditEntryId the primary key of the l c s notification audit entry
	 * @return the l c s notification audit entry that was removed
	 * @throws NoSuchLCSNotificationAuditEntryException if a l c s notification audit entry with the primary key could not be found
	 */
	@Override
	public LCSNotificationAuditEntry remove(long lcsNotificationAuditEntryId)
		throws NoSuchLCSNotificationAuditEntryException {
		return remove((Serializable)lcsNotificationAuditEntryId);
	}

	/**
	 * Removes the l c s notification audit entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the l c s notification audit entry
	 * @return the l c s notification audit entry that was removed
	 * @throws NoSuchLCSNotificationAuditEntryException if a l c s notification audit entry with the primary key could not be found
	 */
	@Override
	public LCSNotificationAuditEntry remove(Serializable primaryKey)
		throws NoSuchLCSNotificationAuditEntryException {
		Session session = null;

		try {
			session = openSession();

			LCSNotificationAuditEntry lcsNotificationAuditEntry = (LCSNotificationAuditEntry)session.get(LCSNotificationAuditEntryImpl.class,
					primaryKey);

			if (lcsNotificationAuditEntry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLCSNotificationAuditEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(lcsNotificationAuditEntry);
		}
		catch (NoSuchLCSNotificationAuditEntryException nsee) {
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
	protected LCSNotificationAuditEntry removeImpl(
		LCSNotificationAuditEntry lcsNotificationAuditEntry) {
		lcsNotificationAuditEntry = toUnwrappedModel(lcsNotificationAuditEntry);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(lcsNotificationAuditEntry)) {
				lcsNotificationAuditEntry = (LCSNotificationAuditEntry)session.get(LCSNotificationAuditEntryImpl.class,
						lcsNotificationAuditEntry.getPrimaryKeyObj());
			}

			if (lcsNotificationAuditEntry != null) {
				session.delete(lcsNotificationAuditEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (lcsNotificationAuditEntry != null) {
			clearCache(lcsNotificationAuditEntry);
		}

		return lcsNotificationAuditEntry;
	}

	@Override
	public LCSNotificationAuditEntry updateImpl(
		LCSNotificationAuditEntry lcsNotificationAuditEntry) {
		lcsNotificationAuditEntry = toUnwrappedModel(lcsNotificationAuditEntry);

		boolean isNew = lcsNotificationAuditEntry.isNew();

		LCSNotificationAuditEntryModelImpl lcsNotificationAuditEntryModelImpl = (LCSNotificationAuditEntryModelImpl)lcsNotificationAuditEntry;

		Session session = null;

		try {
			session = openSession();

			if (lcsNotificationAuditEntry.isNew()) {
				session.save(lcsNotificationAuditEntry);

				lcsNotificationAuditEntry.setNew(false);
			}
			else {
				lcsNotificationAuditEntry = (LCSNotificationAuditEntry)session.merge(lcsNotificationAuditEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew ||
				!LCSNotificationAuditEntryModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((lcsNotificationAuditEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LCSCLUSTERNODEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						lcsNotificationAuditEntryModelImpl.getOriginalLcsClusterNodeId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_LCSCLUSTERNODEID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LCSCLUSTERNODEID,
					args);

				args = new Object[] {
						lcsNotificationAuditEntryModelImpl.getLcsClusterNodeId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_LCSCLUSTERNODEID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LCSCLUSTERNODEID,
					args);
			}

			if ((lcsNotificationAuditEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_LCNI_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						lcsNotificationAuditEntryModelImpl.getOriginalUserId(),
						lcsNotificationAuditEntryModelImpl.getOriginalLcsClusterNodeId(),
						lcsNotificationAuditEntryModelImpl.getOriginalType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_U_LCNI_T, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_LCNI_T,
					args);

				args = new Object[] {
						lcsNotificationAuditEntryModelImpl.getUserId(),
						lcsNotificationAuditEntryModelImpl.getLcsClusterNodeId(),
						lcsNotificationAuditEntryModelImpl.getType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_U_LCNI_T, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_LCNI_T,
					args);
			}
		}

		entityCache.putResult(LCSNotificationAuditEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSNotificationAuditEntryImpl.class,
			lcsNotificationAuditEntry.getPrimaryKey(),
			lcsNotificationAuditEntry, false);

		lcsNotificationAuditEntry.resetOriginalValues();

		return lcsNotificationAuditEntry;
	}

	protected LCSNotificationAuditEntry toUnwrappedModel(
		LCSNotificationAuditEntry lcsNotificationAuditEntry) {
		if (lcsNotificationAuditEntry instanceof LCSNotificationAuditEntryImpl) {
			return lcsNotificationAuditEntry;
		}

		LCSNotificationAuditEntryImpl lcsNotificationAuditEntryImpl = new LCSNotificationAuditEntryImpl();

		lcsNotificationAuditEntryImpl.setNew(lcsNotificationAuditEntry.isNew());
		lcsNotificationAuditEntryImpl.setPrimaryKey(lcsNotificationAuditEntry.getPrimaryKey());

		lcsNotificationAuditEntryImpl.setLcsNotificationAuditEntryId(lcsNotificationAuditEntry.getLcsNotificationAuditEntryId());
		lcsNotificationAuditEntryImpl.setUserId(lcsNotificationAuditEntry.getUserId());
		lcsNotificationAuditEntryImpl.setCreateDate(lcsNotificationAuditEntry.getCreateDate());
		lcsNotificationAuditEntryImpl.setLcsClusterNodeId(lcsNotificationAuditEntry.getLcsClusterNodeId());
		lcsNotificationAuditEntryImpl.setType(lcsNotificationAuditEntry.getType());

		return lcsNotificationAuditEntryImpl;
	}

	/**
	 * Returns the l c s notification audit entry with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the l c s notification audit entry
	 * @return the l c s notification audit entry
	 * @throws NoSuchLCSNotificationAuditEntryException if a l c s notification audit entry with the primary key could not be found
	 */
	@Override
	public LCSNotificationAuditEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLCSNotificationAuditEntryException {
		LCSNotificationAuditEntry lcsNotificationAuditEntry = fetchByPrimaryKey(primaryKey);

		if (lcsNotificationAuditEntry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLCSNotificationAuditEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return lcsNotificationAuditEntry;
	}

	/**
	 * Returns the l c s notification audit entry with the primary key or throws a {@link NoSuchLCSNotificationAuditEntryException} if it could not be found.
	 *
	 * @param lcsNotificationAuditEntryId the primary key of the l c s notification audit entry
	 * @return the l c s notification audit entry
	 * @throws NoSuchLCSNotificationAuditEntryException if a l c s notification audit entry with the primary key could not be found
	 */
	@Override
	public LCSNotificationAuditEntry findByPrimaryKey(
		long lcsNotificationAuditEntryId)
		throws NoSuchLCSNotificationAuditEntryException {
		return findByPrimaryKey((Serializable)lcsNotificationAuditEntryId);
	}

	/**
	 * Returns the l c s notification audit entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the l c s notification audit entry
	 * @return the l c s notification audit entry, or <code>null</code> if a l c s notification audit entry with the primary key could not be found
	 */
	@Override
	public LCSNotificationAuditEntry fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(LCSNotificationAuditEntryModelImpl.ENTITY_CACHE_ENABLED,
				LCSNotificationAuditEntryImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		LCSNotificationAuditEntry lcsNotificationAuditEntry = (LCSNotificationAuditEntry)serializable;

		if (lcsNotificationAuditEntry == null) {
			Session session = null;

			try {
				session = openSession();

				lcsNotificationAuditEntry = (LCSNotificationAuditEntry)session.get(LCSNotificationAuditEntryImpl.class,
						primaryKey);

				if (lcsNotificationAuditEntry != null) {
					cacheResult(lcsNotificationAuditEntry);
				}
				else {
					entityCache.putResult(LCSNotificationAuditEntryModelImpl.ENTITY_CACHE_ENABLED,
						LCSNotificationAuditEntryImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(LCSNotificationAuditEntryModelImpl.ENTITY_CACHE_ENABLED,
					LCSNotificationAuditEntryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return lcsNotificationAuditEntry;
	}

	/**
	 * Returns the l c s notification audit entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param lcsNotificationAuditEntryId the primary key of the l c s notification audit entry
	 * @return the l c s notification audit entry, or <code>null</code> if a l c s notification audit entry with the primary key could not be found
	 */
	@Override
	public LCSNotificationAuditEntry fetchByPrimaryKey(
		long lcsNotificationAuditEntryId) {
		return fetchByPrimaryKey((Serializable)lcsNotificationAuditEntryId);
	}

	@Override
	public Map<Serializable, LCSNotificationAuditEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, LCSNotificationAuditEntry> map = new HashMap<Serializable, LCSNotificationAuditEntry>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			LCSNotificationAuditEntry lcsNotificationAuditEntry = fetchByPrimaryKey(primaryKey);

			if (lcsNotificationAuditEntry != null) {
				map.put(primaryKey, lcsNotificationAuditEntry);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(LCSNotificationAuditEntryModelImpl.ENTITY_CACHE_ENABLED,
					LCSNotificationAuditEntryImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (LCSNotificationAuditEntry)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_LCSNOTIFICATIONAUDITENTRY_WHERE_PKS_IN);

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

			for (LCSNotificationAuditEntry lcsNotificationAuditEntry : (List<LCSNotificationAuditEntry>)q.list()) {
				map.put(lcsNotificationAuditEntry.getPrimaryKeyObj(),
					lcsNotificationAuditEntry);

				cacheResult(lcsNotificationAuditEntry);

				uncachedPrimaryKeys.remove(lcsNotificationAuditEntry.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(LCSNotificationAuditEntryModelImpl.ENTITY_CACHE_ENABLED,
					LCSNotificationAuditEntryImpl.class, primaryKey, nullModel);
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
	 * Returns all the l c s notification audit entries.
	 *
	 * @return the l c s notification audit entries
	 */
	@Override
	public List<LCSNotificationAuditEntry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the l c s notification audit entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSNotificationAuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of l c s notification audit entries
	 * @param end the upper bound of the range of l c s notification audit entries (not inclusive)
	 * @return the range of l c s notification audit entries
	 */
	@Override
	public List<LCSNotificationAuditEntry> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the l c s notification audit entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSNotificationAuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of l c s notification audit entries
	 * @param end the upper bound of the range of l c s notification audit entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of l c s notification audit entries
	 */
	@Override
	public List<LCSNotificationAuditEntry> findAll(int start, int end,
		OrderByComparator<LCSNotificationAuditEntry> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the l c s notification audit entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSNotificationAuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of l c s notification audit entries
	 * @param end the upper bound of the range of l c s notification audit entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of l c s notification audit entries
	 */
	@Override
	public List<LCSNotificationAuditEntry> findAll(int start, int end,
		OrderByComparator<LCSNotificationAuditEntry> orderByComparator,
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

		List<LCSNotificationAuditEntry> list = null;

		if (retrieveFromCache) {
			list = (List<LCSNotificationAuditEntry>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_LCSNOTIFICATIONAUDITENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_LCSNOTIFICATIONAUDITENTRY;

				if (pagination) {
					sql = sql.concat(LCSNotificationAuditEntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<LCSNotificationAuditEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSNotificationAuditEntry>)QueryUtil.list(q,
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
	 * Removes all the l c s notification audit entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (LCSNotificationAuditEntry lcsNotificationAuditEntry : findAll()) {
			remove(lcsNotificationAuditEntry);
		}
	}

	/**
	 * Returns the number of l c s notification audit entries.
	 *
	 * @return the number of l c s notification audit entries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_LCSNOTIFICATIONAUDITENTRY);

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
		return LCSNotificationAuditEntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the l c s notification audit entry persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(LCSNotificationAuditEntryImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_LCSNOTIFICATIONAUDITENTRY = "SELECT lcsNotificationAuditEntry FROM LCSNotificationAuditEntry lcsNotificationAuditEntry";
	private static final String _SQL_SELECT_LCSNOTIFICATIONAUDITENTRY_WHERE_PKS_IN =
		"SELECT lcsNotificationAuditEntry FROM LCSNotificationAuditEntry lcsNotificationAuditEntry WHERE lcsNotificationAuditEntryId IN (";
	private static final String _SQL_SELECT_LCSNOTIFICATIONAUDITENTRY_WHERE = "SELECT lcsNotificationAuditEntry FROM LCSNotificationAuditEntry lcsNotificationAuditEntry WHERE ";
	private static final String _SQL_COUNT_LCSNOTIFICATIONAUDITENTRY = "SELECT COUNT(lcsNotificationAuditEntry) FROM LCSNotificationAuditEntry lcsNotificationAuditEntry";
	private static final String _SQL_COUNT_LCSNOTIFICATIONAUDITENTRY_WHERE = "SELECT COUNT(lcsNotificationAuditEntry) FROM LCSNotificationAuditEntry lcsNotificationAuditEntry WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "lcsNotificationAuditEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No LCSNotificationAuditEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No LCSNotificationAuditEntry exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(LCSNotificationAuditEntryPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"type"
			});
}