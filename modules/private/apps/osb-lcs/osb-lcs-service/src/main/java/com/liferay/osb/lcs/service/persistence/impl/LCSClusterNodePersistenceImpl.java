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

import com.liferay.osb.lcs.exception.NoSuchLCSClusterNodeException;
import com.liferay.osb.lcs.model.LCSClusterNode;
import com.liferay.osb.lcs.model.impl.LCSClusterNodeImpl;
import com.liferay.osb.lcs.model.impl.LCSClusterNodeModelImpl;
import com.liferay.osb.lcs.service.persistence.LCSClusterNodePersistence;

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
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the l c s cluster node service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Igor Beslic
 * @see LCSClusterNodePersistence
 * @see com.liferay.osb.lcs.service.persistence.LCSClusterNodeUtil
 * @generated
 */
@ProviderType
public class LCSClusterNodePersistenceImpl extends BasePersistenceImpl<LCSClusterNode>
	implements LCSClusterNodePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link LCSClusterNodeUtil} to access the l c s cluster node persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = LCSClusterNodeImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(LCSClusterNodeModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterNodeModelImpl.FINDER_CACHE_ENABLED,
			LCSClusterNodeImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(LCSClusterNodeModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterNodeModelImpl.FINDER_CACHE_ENABLED,
			LCSClusterNodeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(LCSClusterNodeModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterNodeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_LCSCLUSTERENTRYID =
		new FinderPath(LCSClusterNodeModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterNodeModelImpl.FINDER_CACHE_ENABLED,
			LCSClusterNodeImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByLCSClusterEntryId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LCSCLUSTERENTRYID =
		new FinderPath(LCSClusterNodeModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterNodeModelImpl.FINDER_CACHE_ENABLED,
			LCSClusterNodeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByLCSClusterEntryId", new String[] { Long.class.getName() },
			LCSClusterNodeModelImpl.LCSCLUSTERENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_LCSCLUSTERENTRYID = new FinderPath(LCSClusterNodeModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterNodeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByLCSClusterEntryId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_LCSCLUSTERENTRYID =
		new FinderPath(LCSClusterNodeModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterNodeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByLCSClusterEntryId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the l c s cluster nodes where lcsClusterEntryId = &#63;.
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @return the matching l c s cluster nodes
	 */
	@Override
	public List<LCSClusterNode> findByLCSClusterEntryId(long lcsClusterEntryId) {
		return findByLCSClusterEntryId(lcsClusterEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the l c s cluster nodes where lcsClusterEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @param start the lower bound of the range of l c s cluster nodes
	 * @param end the upper bound of the range of l c s cluster nodes (not inclusive)
	 * @return the range of matching l c s cluster nodes
	 */
	@Override
	public List<LCSClusterNode> findByLCSClusterEntryId(
		long lcsClusterEntryId, int start, int end) {
		return findByLCSClusterEntryId(lcsClusterEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the l c s cluster nodes where lcsClusterEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @param start the lower bound of the range of l c s cluster nodes
	 * @param end the upper bound of the range of l c s cluster nodes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching l c s cluster nodes
	 */
	@Override
	public List<LCSClusterNode> findByLCSClusterEntryId(
		long lcsClusterEntryId, int start, int end,
		OrderByComparator<LCSClusterNode> orderByComparator) {
		return findByLCSClusterEntryId(lcsClusterEntryId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the l c s cluster nodes where lcsClusterEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @param start the lower bound of the range of l c s cluster nodes
	 * @param end the upper bound of the range of l c s cluster nodes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching l c s cluster nodes
	 */
	@Override
	public List<LCSClusterNode> findByLCSClusterEntryId(
		long lcsClusterEntryId, int start, int end,
		OrderByComparator<LCSClusterNode> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LCSCLUSTERENTRYID;
			finderArgs = new Object[] { lcsClusterEntryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_LCSCLUSTERENTRYID;
			finderArgs = new Object[] {
					lcsClusterEntryId,
					
					start, end, orderByComparator
				};
		}

		List<LCSClusterNode> list = null;

		if (retrieveFromCache) {
			list = (List<LCSClusterNode>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LCSClusterNode lcsClusterNode : list) {
					if ((lcsClusterEntryId != lcsClusterNode.getLcsClusterEntryId())) {
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

			query.append(_SQL_SELECT_LCSCLUSTERNODE_WHERE);

			query.append(_FINDER_COLUMN_LCSCLUSTERENTRYID_LCSCLUSTERENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LCSClusterNodeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(lcsClusterEntryId);

				if (!pagination) {
					list = (List<LCSClusterNode>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSClusterNode>)QueryUtil.list(q,
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
	 * Returns the first l c s cluster node in the ordered set where lcsClusterEntryId = &#63;.
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s cluster node
	 * @throws NoSuchLCSClusterNodeException if a matching l c s cluster node could not be found
	 */
	@Override
	public LCSClusterNode findByLCSClusterEntryId_First(
		long lcsClusterEntryId,
		OrderByComparator<LCSClusterNode> orderByComparator)
		throws NoSuchLCSClusterNodeException {
		LCSClusterNode lcsClusterNode = fetchByLCSClusterEntryId_First(lcsClusterEntryId,
				orderByComparator);

		if (lcsClusterNode != null) {
			return lcsClusterNode;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("lcsClusterEntryId=");
		msg.append(lcsClusterEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSClusterNodeException(msg.toString());
	}

	/**
	 * Returns the first l c s cluster node in the ordered set where lcsClusterEntryId = &#63;.
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s cluster node, or <code>null</code> if a matching l c s cluster node could not be found
	 */
	@Override
	public LCSClusterNode fetchByLCSClusterEntryId_First(
		long lcsClusterEntryId,
		OrderByComparator<LCSClusterNode> orderByComparator) {
		List<LCSClusterNode> list = findByLCSClusterEntryId(lcsClusterEntryId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last l c s cluster node in the ordered set where lcsClusterEntryId = &#63;.
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s cluster node
	 * @throws NoSuchLCSClusterNodeException if a matching l c s cluster node could not be found
	 */
	@Override
	public LCSClusterNode findByLCSClusterEntryId_Last(long lcsClusterEntryId,
		OrderByComparator<LCSClusterNode> orderByComparator)
		throws NoSuchLCSClusterNodeException {
		LCSClusterNode lcsClusterNode = fetchByLCSClusterEntryId_Last(lcsClusterEntryId,
				orderByComparator);

		if (lcsClusterNode != null) {
			return lcsClusterNode;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("lcsClusterEntryId=");
		msg.append(lcsClusterEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSClusterNodeException(msg.toString());
	}

	/**
	 * Returns the last l c s cluster node in the ordered set where lcsClusterEntryId = &#63;.
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s cluster node, or <code>null</code> if a matching l c s cluster node could not be found
	 */
	@Override
	public LCSClusterNode fetchByLCSClusterEntryId_Last(
		long lcsClusterEntryId,
		OrderByComparator<LCSClusterNode> orderByComparator) {
		int count = countByLCSClusterEntryId(lcsClusterEntryId);

		if (count == 0) {
			return null;
		}

		List<LCSClusterNode> list = findByLCSClusterEntryId(lcsClusterEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the l c s cluster nodes before and after the current l c s cluster node in the ordered set where lcsClusterEntryId = &#63;.
	 *
	 * @param lcsClusterNodeId the primary key of the current l c s cluster node
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next l c s cluster node
	 * @throws NoSuchLCSClusterNodeException if a l c s cluster node with the primary key could not be found
	 */
	@Override
	public LCSClusterNode[] findByLCSClusterEntryId_PrevAndNext(
		long lcsClusterNodeId, long lcsClusterEntryId,
		OrderByComparator<LCSClusterNode> orderByComparator)
		throws NoSuchLCSClusterNodeException {
		LCSClusterNode lcsClusterNode = findByPrimaryKey(lcsClusterNodeId);

		Session session = null;

		try {
			session = openSession();

			LCSClusterNode[] array = new LCSClusterNodeImpl[3];

			array[0] = getByLCSClusterEntryId_PrevAndNext(session,
					lcsClusterNode, lcsClusterEntryId, orderByComparator, true);

			array[1] = lcsClusterNode;

			array[2] = getByLCSClusterEntryId_PrevAndNext(session,
					lcsClusterNode, lcsClusterEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LCSClusterNode getByLCSClusterEntryId_PrevAndNext(
		Session session, LCSClusterNode lcsClusterNode, long lcsClusterEntryId,
		OrderByComparator<LCSClusterNode> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LCSCLUSTERNODE_WHERE);

		query.append(_FINDER_COLUMN_LCSCLUSTERENTRYID_LCSCLUSTERENTRYID_2);

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
			query.append(LCSClusterNodeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(lcsClusterEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(lcsClusterNode);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LCSClusterNode> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the l c s cluster nodes where lcsClusterEntryId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsClusterEntryIds the lcs cluster entry IDs
	 * @return the matching l c s cluster nodes
	 */
	@Override
	public List<LCSClusterNode> findByLCSClusterEntryId(
		long[] lcsClusterEntryIds) {
		return findByLCSClusterEntryId(lcsClusterEntryIds, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the l c s cluster nodes where lcsClusterEntryId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsClusterEntryIds the lcs cluster entry IDs
	 * @param start the lower bound of the range of l c s cluster nodes
	 * @param end the upper bound of the range of l c s cluster nodes (not inclusive)
	 * @return the range of matching l c s cluster nodes
	 */
	@Override
	public List<LCSClusterNode> findByLCSClusterEntryId(
		long[] lcsClusterEntryIds, int start, int end) {
		return findByLCSClusterEntryId(lcsClusterEntryIds, start, end, null);
	}

	/**
	 * Returns an ordered range of all the l c s cluster nodes where lcsClusterEntryId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsClusterEntryIds the lcs cluster entry IDs
	 * @param start the lower bound of the range of l c s cluster nodes
	 * @param end the upper bound of the range of l c s cluster nodes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching l c s cluster nodes
	 */
	@Override
	public List<LCSClusterNode> findByLCSClusterEntryId(
		long[] lcsClusterEntryIds, int start, int end,
		OrderByComparator<LCSClusterNode> orderByComparator) {
		return findByLCSClusterEntryId(lcsClusterEntryIds, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the l c s cluster nodes where lcsClusterEntryId = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @param start the lower bound of the range of l c s cluster nodes
	 * @param end the upper bound of the range of l c s cluster nodes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching l c s cluster nodes
	 */
	@Override
	public List<LCSClusterNode> findByLCSClusterEntryId(
		long[] lcsClusterEntryIds, int start, int end,
		OrderByComparator<LCSClusterNode> orderByComparator,
		boolean retrieveFromCache) {
		if (lcsClusterEntryIds == null) {
			lcsClusterEntryIds = new long[0];
		}
		else if (lcsClusterEntryIds.length > 1) {
			lcsClusterEntryIds = ArrayUtil.unique(lcsClusterEntryIds);

			Arrays.sort(lcsClusterEntryIds);
		}

		if (lcsClusterEntryIds.length == 1) {
			return findByLCSClusterEntryId(lcsClusterEntryIds[0], start, end,
				orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] { StringUtil.merge(lcsClusterEntryIds) };
		}
		else {
			finderArgs = new Object[] {
					StringUtil.merge(lcsClusterEntryIds),
					
					start, end, orderByComparator
				};
		}

		List<LCSClusterNode> list = null;

		if (retrieveFromCache) {
			list = (List<LCSClusterNode>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_LCSCLUSTERENTRYID,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LCSClusterNode lcsClusterNode : list) {
					if (!ArrayUtil.contains(lcsClusterEntryIds,
								lcsClusterNode.getLcsClusterEntryId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_LCSCLUSTERNODE_WHERE);

			if (lcsClusterEntryIds.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_LCSCLUSTERENTRYID_LCSCLUSTERENTRYID_7);

				query.append(StringUtil.merge(lcsClusterEntryIds));

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
				query.append(LCSClusterNodeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<LCSClusterNode>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSClusterNode>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_LCSCLUSTERENTRYID,
					finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_LCSCLUSTERENTRYID,
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
	 * Removes all the l c s cluster nodes where lcsClusterEntryId = &#63; from the database.
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 */
	@Override
	public void removeByLCSClusterEntryId(long lcsClusterEntryId) {
		for (LCSClusterNode lcsClusterNode : findByLCSClusterEntryId(
				lcsClusterEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(lcsClusterNode);
		}
	}

	/**
	 * Returns the number of l c s cluster nodes where lcsClusterEntryId = &#63;.
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @return the number of matching l c s cluster nodes
	 */
	@Override
	public int countByLCSClusterEntryId(long lcsClusterEntryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_LCSCLUSTERENTRYID;

		Object[] finderArgs = new Object[] { lcsClusterEntryId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LCSCLUSTERNODE_WHERE);

			query.append(_FINDER_COLUMN_LCSCLUSTERENTRYID_LCSCLUSTERENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(lcsClusterEntryId);

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
	 * Returns the number of l c s cluster nodes where lcsClusterEntryId = any &#63;.
	 *
	 * @param lcsClusterEntryIds the lcs cluster entry IDs
	 * @return the number of matching l c s cluster nodes
	 */
	@Override
	public int countByLCSClusterEntryId(long[] lcsClusterEntryIds) {
		if (lcsClusterEntryIds == null) {
			lcsClusterEntryIds = new long[0];
		}
		else if (lcsClusterEntryIds.length > 1) {
			lcsClusterEntryIds = ArrayUtil.unique(lcsClusterEntryIds);

			Arrays.sort(lcsClusterEntryIds);
		}

		Object[] finderArgs = new Object[] { StringUtil.merge(lcsClusterEntryIds) };

		Long count = (Long)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_LCSCLUSTERENTRYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_LCSCLUSTERNODE_WHERE);

			if (lcsClusterEntryIds.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_LCSCLUSTERENTRYID_LCSCLUSTERENTRYID_7);

				query.append(StringUtil.merge(lcsClusterEntryIds));

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

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_LCSCLUSTERENTRYID,
					finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_LCSCLUSTERENTRYID,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_LCSCLUSTERENTRYID_LCSCLUSTERENTRYID_2 =
		"lcsClusterNode.lcsClusterEntryId = ?";
	private static final String _FINDER_COLUMN_LCSCLUSTERENTRYID_LCSCLUSTERENTRYID_7 =
		"lcsClusterNode.lcsClusterEntryId IN (";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_BUILDNUMBER =
		new FinderPath(LCSClusterNodeModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterNodeModelImpl.FINDER_CACHE_ENABLED,
			LCSClusterNodeImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByBuildNumber",
			new String[] {
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUILDNUMBER =
		new FinderPath(LCSClusterNodeModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterNodeModelImpl.FINDER_CACHE_ENABLED,
			LCSClusterNodeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByBuildNumber",
			new String[] { Integer.class.getName() },
			LCSClusterNodeModelImpl.BUILDNUMBER_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_BUILDNUMBER = new FinderPath(LCSClusterNodeModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterNodeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByBuildNumber",
			new String[] { Integer.class.getName() });

	/**
	 * Returns all the l c s cluster nodes where buildNumber = &#63;.
	 *
	 * @param buildNumber the build number
	 * @return the matching l c s cluster nodes
	 */
	@Override
	public List<LCSClusterNode> findByBuildNumber(int buildNumber) {
		return findByBuildNumber(buildNumber, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the l c s cluster nodes where buildNumber = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param buildNumber the build number
	 * @param start the lower bound of the range of l c s cluster nodes
	 * @param end the upper bound of the range of l c s cluster nodes (not inclusive)
	 * @return the range of matching l c s cluster nodes
	 */
	@Override
	public List<LCSClusterNode> findByBuildNumber(int buildNumber, int start,
		int end) {
		return findByBuildNumber(buildNumber, start, end, null);
	}

	/**
	 * Returns an ordered range of all the l c s cluster nodes where buildNumber = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param buildNumber the build number
	 * @param start the lower bound of the range of l c s cluster nodes
	 * @param end the upper bound of the range of l c s cluster nodes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching l c s cluster nodes
	 */
	@Override
	public List<LCSClusterNode> findByBuildNumber(int buildNumber, int start,
		int end, OrderByComparator<LCSClusterNode> orderByComparator) {
		return findByBuildNumber(buildNumber, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the l c s cluster nodes where buildNumber = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param buildNumber the build number
	 * @param start the lower bound of the range of l c s cluster nodes
	 * @param end the upper bound of the range of l c s cluster nodes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching l c s cluster nodes
	 */
	@Override
	public List<LCSClusterNode> findByBuildNumber(int buildNumber, int start,
		int end, OrderByComparator<LCSClusterNode> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUILDNUMBER;
			finderArgs = new Object[] { buildNumber };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_BUILDNUMBER;
			finderArgs = new Object[] { buildNumber, start, end, orderByComparator };
		}

		List<LCSClusterNode> list = null;

		if (retrieveFromCache) {
			list = (List<LCSClusterNode>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LCSClusterNode lcsClusterNode : list) {
					if ((buildNumber != lcsClusterNode.getBuildNumber())) {
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

			query.append(_SQL_SELECT_LCSCLUSTERNODE_WHERE);

			query.append(_FINDER_COLUMN_BUILDNUMBER_BUILDNUMBER_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LCSClusterNodeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(buildNumber);

				if (!pagination) {
					list = (List<LCSClusterNode>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSClusterNode>)QueryUtil.list(q,
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
	 * Returns the first l c s cluster node in the ordered set where buildNumber = &#63;.
	 *
	 * @param buildNumber the build number
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s cluster node
	 * @throws NoSuchLCSClusterNodeException if a matching l c s cluster node could not be found
	 */
	@Override
	public LCSClusterNode findByBuildNumber_First(int buildNumber,
		OrderByComparator<LCSClusterNode> orderByComparator)
		throws NoSuchLCSClusterNodeException {
		LCSClusterNode lcsClusterNode = fetchByBuildNumber_First(buildNumber,
				orderByComparator);

		if (lcsClusterNode != null) {
			return lcsClusterNode;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("buildNumber=");
		msg.append(buildNumber);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSClusterNodeException(msg.toString());
	}

	/**
	 * Returns the first l c s cluster node in the ordered set where buildNumber = &#63;.
	 *
	 * @param buildNumber the build number
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s cluster node, or <code>null</code> if a matching l c s cluster node could not be found
	 */
	@Override
	public LCSClusterNode fetchByBuildNumber_First(int buildNumber,
		OrderByComparator<LCSClusterNode> orderByComparator) {
		List<LCSClusterNode> list = findByBuildNumber(buildNumber, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last l c s cluster node in the ordered set where buildNumber = &#63;.
	 *
	 * @param buildNumber the build number
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s cluster node
	 * @throws NoSuchLCSClusterNodeException if a matching l c s cluster node could not be found
	 */
	@Override
	public LCSClusterNode findByBuildNumber_Last(int buildNumber,
		OrderByComparator<LCSClusterNode> orderByComparator)
		throws NoSuchLCSClusterNodeException {
		LCSClusterNode lcsClusterNode = fetchByBuildNumber_Last(buildNumber,
				orderByComparator);

		if (lcsClusterNode != null) {
			return lcsClusterNode;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("buildNumber=");
		msg.append(buildNumber);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSClusterNodeException(msg.toString());
	}

	/**
	 * Returns the last l c s cluster node in the ordered set where buildNumber = &#63;.
	 *
	 * @param buildNumber the build number
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s cluster node, or <code>null</code> if a matching l c s cluster node could not be found
	 */
	@Override
	public LCSClusterNode fetchByBuildNumber_Last(int buildNumber,
		OrderByComparator<LCSClusterNode> orderByComparator) {
		int count = countByBuildNumber(buildNumber);

		if (count == 0) {
			return null;
		}

		List<LCSClusterNode> list = findByBuildNumber(buildNumber, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the l c s cluster nodes before and after the current l c s cluster node in the ordered set where buildNumber = &#63;.
	 *
	 * @param lcsClusterNodeId the primary key of the current l c s cluster node
	 * @param buildNumber the build number
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next l c s cluster node
	 * @throws NoSuchLCSClusterNodeException if a l c s cluster node with the primary key could not be found
	 */
	@Override
	public LCSClusterNode[] findByBuildNumber_PrevAndNext(
		long lcsClusterNodeId, int buildNumber,
		OrderByComparator<LCSClusterNode> orderByComparator)
		throws NoSuchLCSClusterNodeException {
		LCSClusterNode lcsClusterNode = findByPrimaryKey(lcsClusterNodeId);

		Session session = null;

		try {
			session = openSession();

			LCSClusterNode[] array = new LCSClusterNodeImpl[3];

			array[0] = getByBuildNumber_PrevAndNext(session, lcsClusterNode,
					buildNumber, orderByComparator, true);

			array[1] = lcsClusterNode;

			array[2] = getByBuildNumber_PrevAndNext(session, lcsClusterNode,
					buildNumber, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LCSClusterNode getByBuildNumber_PrevAndNext(Session session,
		LCSClusterNode lcsClusterNode, int buildNumber,
		OrderByComparator<LCSClusterNode> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LCSCLUSTERNODE_WHERE);

		query.append(_FINDER_COLUMN_BUILDNUMBER_BUILDNUMBER_2);

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
			query.append(LCSClusterNodeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(buildNumber);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(lcsClusterNode);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LCSClusterNode> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the l c s cluster nodes where buildNumber = &#63; from the database.
	 *
	 * @param buildNumber the build number
	 */
	@Override
	public void removeByBuildNumber(int buildNumber) {
		for (LCSClusterNode lcsClusterNode : findByBuildNumber(buildNumber,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(lcsClusterNode);
		}
	}

	/**
	 * Returns the number of l c s cluster nodes where buildNumber = &#63;.
	 *
	 * @param buildNumber the build number
	 * @return the number of matching l c s cluster nodes
	 */
	@Override
	public int countByBuildNumber(int buildNumber) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_BUILDNUMBER;

		Object[] finderArgs = new Object[] { buildNumber };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LCSCLUSTERNODE_WHERE);

			query.append(_FINDER_COLUMN_BUILDNUMBER_BUILDNUMBER_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(buildNumber);

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

	private static final String _FINDER_COLUMN_BUILDNUMBER_BUILDNUMBER_2 = "lcsClusterNode.buildNumber = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_KEY = new FinderPath(LCSClusterNodeModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterNodeModelImpl.FINDER_CACHE_ENABLED,
			LCSClusterNodeImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByKey",
			new String[] { String.class.getName() },
			LCSClusterNodeModelImpl.KEY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KEY = new FinderPath(LCSClusterNodeModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterNodeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByKey",
			new String[] { String.class.getName() });

	/**
	 * Returns the l c s cluster node where key = &#63; or throws a {@link NoSuchLCSClusterNodeException} if it could not be found.
	 *
	 * @param key the key
	 * @return the matching l c s cluster node
	 * @throws NoSuchLCSClusterNodeException if a matching l c s cluster node could not be found
	 */
	@Override
	public LCSClusterNode findByKey(String key)
		throws NoSuchLCSClusterNodeException {
		LCSClusterNode lcsClusterNode = fetchByKey(key);

		if (lcsClusterNode == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("key=");
			msg.append(key);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchLCSClusterNodeException(msg.toString());
		}

		return lcsClusterNode;
	}

	/**
	 * Returns the l c s cluster node where key = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param key the key
	 * @return the matching l c s cluster node, or <code>null</code> if a matching l c s cluster node could not be found
	 */
	@Override
	public LCSClusterNode fetchByKey(String key) {
		return fetchByKey(key, true);
	}

	/**
	 * Returns the l c s cluster node where key = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param key the key
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching l c s cluster node, or <code>null</code> if a matching l c s cluster node could not be found
	 */
	@Override
	public LCSClusterNode fetchByKey(String key, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { key };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_KEY,
					finderArgs, this);
		}

		if (result instanceof LCSClusterNode) {
			LCSClusterNode lcsClusterNode = (LCSClusterNode)result;

			if (!Objects.equals(key, lcsClusterNode.getKey())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_LCSCLUSTERNODE_WHERE);

			boolean bindKey = false;

			if (key == null) {
				query.append(_FINDER_COLUMN_KEY_KEY_1);
			}
			else if (key.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_KEY_KEY_3);
			}
			else {
				bindKey = true;

				query.append(_FINDER_COLUMN_KEY_KEY_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindKey) {
					qPos.add(key);
				}

				List<LCSClusterNode> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_KEY, finderArgs,
						list);
				}
				else {
					LCSClusterNode lcsClusterNode = list.get(0);

					result = lcsClusterNode;

					cacheResult(lcsClusterNode);

					if ((lcsClusterNode.getKey() == null) ||
							!lcsClusterNode.getKey().equals(key)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_KEY,
							finderArgs, lcsClusterNode);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_KEY, finderArgs);

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
			return (LCSClusterNode)result;
		}
	}

	/**
	 * Removes the l c s cluster node where key = &#63; from the database.
	 *
	 * @param key the key
	 * @return the l c s cluster node that was removed
	 */
	@Override
	public LCSClusterNode removeByKey(String key)
		throws NoSuchLCSClusterNodeException {
		LCSClusterNode lcsClusterNode = findByKey(key);

		return remove(lcsClusterNode);
	}

	/**
	 * Returns the number of l c s cluster nodes where key = &#63;.
	 *
	 * @param key the key
	 * @return the number of matching l c s cluster nodes
	 */
	@Override
	public int countByKey(String key) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_KEY;

		Object[] finderArgs = new Object[] { key };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LCSCLUSTERNODE_WHERE);

			boolean bindKey = false;

			if (key == null) {
				query.append(_FINDER_COLUMN_KEY_KEY_1);
			}
			else if (key.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_KEY_KEY_3);
			}
			else {
				bindKey = true;

				query.append(_FINDER_COLUMN_KEY_KEY_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindKey) {
					qPos.add(key);
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

	private static final String _FINDER_COLUMN_KEY_KEY_1 = "lcsClusterNode.key IS NULL";
	private static final String _FINDER_COLUMN_KEY_KEY_2 = "lcsClusterNode.key = ?";
	private static final String _FINDER_COLUMN_KEY_KEY_3 = "(lcsClusterNode.key IS NULL OR lcsClusterNode.key = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_LCEI_N = new FinderPath(LCSClusterNodeModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterNodeModelImpl.FINDER_CACHE_ENABLED,
			LCSClusterNodeImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByLCEI_N",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LCEI_N =
		new FinderPath(LCSClusterNodeModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterNodeModelImpl.FINDER_CACHE_ENABLED,
			LCSClusterNodeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByLCEI_N",
			new String[] { Long.class.getName(), String.class.getName() },
			LCSClusterNodeModelImpl.LCSCLUSTERENTRYID_COLUMN_BITMASK |
			LCSClusterNodeModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_LCEI_N = new FinderPath(LCSClusterNodeModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterNodeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByLCEI_N",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the l c s cluster nodes where lcsClusterEntryId = &#63; and name = &#63;.
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @param name the name
	 * @return the matching l c s cluster nodes
	 */
	@Override
	public List<LCSClusterNode> findByLCEI_N(long lcsClusterEntryId, String name) {
		return findByLCEI_N(lcsClusterEntryId, name, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the l c s cluster nodes where lcsClusterEntryId = &#63; and name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @param name the name
	 * @param start the lower bound of the range of l c s cluster nodes
	 * @param end the upper bound of the range of l c s cluster nodes (not inclusive)
	 * @return the range of matching l c s cluster nodes
	 */
	@Override
	public List<LCSClusterNode> findByLCEI_N(long lcsClusterEntryId,
		String name, int start, int end) {
		return findByLCEI_N(lcsClusterEntryId, name, start, end, null);
	}

	/**
	 * Returns an ordered range of all the l c s cluster nodes where lcsClusterEntryId = &#63; and name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @param name the name
	 * @param start the lower bound of the range of l c s cluster nodes
	 * @param end the upper bound of the range of l c s cluster nodes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching l c s cluster nodes
	 */
	@Override
	public List<LCSClusterNode> findByLCEI_N(long lcsClusterEntryId,
		String name, int start, int end,
		OrderByComparator<LCSClusterNode> orderByComparator) {
		return findByLCEI_N(lcsClusterEntryId, name, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the l c s cluster nodes where lcsClusterEntryId = &#63; and name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @param name the name
	 * @param start the lower bound of the range of l c s cluster nodes
	 * @param end the upper bound of the range of l c s cluster nodes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching l c s cluster nodes
	 */
	@Override
	public List<LCSClusterNode> findByLCEI_N(long lcsClusterEntryId,
		String name, int start, int end,
		OrderByComparator<LCSClusterNode> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LCEI_N;
			finderArgs = new Object[] { lcsClusterEntryId, name };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_LCEI_N;
			finderArgs = new Object[] {
					lcsClusterEntryId, name,
					
					start, end, orderByComparator
				};
		}

		List<LCSClusterNode> list = null;

		if (retrieveFromCache) {
			list = (List<LCSClusterNode>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LCSClusterNode lcsClusterNode : list) {
					if ((lcsClusterEntryId != lcsClusterNode.getLcsClusterEntryId()) ||
							!Objects.equals(name, lcsClusterNode.getName())) {
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

			query.append(_SQL_SELECT_LCSCLUSTERNODE_WHERE);

			query.append(_FINDER_COLUMN_LCEI_N_LCSCLUSTERENTRYID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_LCEI_N_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_LCEI_N_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_LCEI_N_NAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LCSClusterNodeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(lcsClusterEntryId);

				if (bindName) {
					qPos.add(name);
				}

				if (!pagination) {
					list = (List<LCSClusterNode>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSClusterNode>)QueryUtil.list(q,
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
	 * Returns the first l c s cluster node in the ordered set where lcsClusterEntryId = &#63; and name = &#63;.
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s cluster node
	 * @throws NoSuchLCSClusterNodeException if a matching l c s cluster node could not be found
	 */
	@Override
	public LCSClusterNode findByLCEI_N_First(long lcsClusterEntryId,
		String name, OrderByComparator<LCSClusterNode> orderByComparator)
		throws NoSuchLCSClusterNodeException {
		LCSClusterNode lcsClusterNode = fetchByLCEI_N_First(lcsClusterEntryId,
				name, orderByComparator);

		if (lcsClusterNode != null) {
			return lcsClusterNode;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("lcsClusterEntryId=");
		msg.append(lcsClusterEntryId);

		msg.append(", name=");
		msg.append(name);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSClusterNodeException(msg.toString());
	}

	/**
	 * Returns the first l c s cluster node in the ordered set where lcsClusterEntryId = &#63; and name = &#63;.
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s cluster node, or <code>null</code> if a matching l c s cluster node could not be found
	 */
	@Override
	public LCSClusterNode fetchByLCEI_N_First(long lcsClusterEntryId,
		String name, OrderByComparator<LCSClusterNode> orderByComparator) {
		List<LCSClusterNode> list = findByLCEI_N(lcsClusterEntryId, name, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last l c s cluster node in the ordered set where lcsClusterEntryId = &#63; and name = &#63;.
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s cluster node
	 * @throws NoSuchLCSClusterNodeException if a matching l c s cluster node could not be found
	 */
	@Override
	public LCSClusterNode findByLCEI_N_Last(long lcsClusterEntryId,
		String name, OrderByComparator<LCSClusterNode> orderByComparator)
		throws NoSuchLCSClusterNodeException {
		LCSClusterNode lcsClusterNode = fetchByLCEI_N_Last(lcsClusterEntryId,
				name, orderByComparator);

		if (lcsClusterNode != null) {
			return lcsClusterNode;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("lcsClusterEntryId=");
		msg.append(lcsClusterEntryId);

		msg.append(", name=");
		msg.append(name);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSClusterNodeException(msg.toString());
	}

	/**
	 * Returns the last l c s cluster node in the ordered set where lcsClusterEntryId = &#63; and name = &#63;.
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s cluster node, or <code>null</code> if a matching l c s cluster node could not be found
	 */
	@Override
	public LCSClusterNode fetchByLCEI_N_Last(long lcsClusterEntryId,
		String name, OrderByComparator<LCSClusterNode> orderByComparator) {
		int count = countByLCEI_N(lcsClusterEntryId, name);

		if (count == 0) {
			return null;
		}

		List<LCSClusterNode> list = findByLCEI_N(lcsClusterEntryId, name,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the l c s cluster nodes before and after the current l c s cluster node in the ordered set where lcsClusterEntryId = &#63; and name = &#63;.
	 *
	 * @param lcsClusterNodeId the primary key of the current l c s cluster node
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next l c s cluster node
	 * @throws NoSuchLCSClusterNodeException if a l c s cluster node with the primary key could not be found
	 */
	@Override
	public LCSClusterNode[] findByLCEI_N_PrevAndNext(long lcsClusterNodeId,
		long lcsClusterEntryId, String name,
		OrderByComparator<LCSClusterNode> orderByComparator)
		throws NoSuchLCSClusterNodeException {
		LCSClusterNode lcsClusterNode = findByPrimaryKey(lcsClusterNodeId);

		Session session = null;

		try {
			session = openSession();

			LCSClusterNode[] array = new LCSClusterNodeImpl[3];

			array[0] = getByLCEI_N_PrevAndNext(session, lcsClusterNode,
					lcsClusterEntryId, name, orderByComparator, true);

			array[1] = lcsClusterNode;

			array[2] = getByLCEI_N_PrevAndNext(session, lcsClusterNode,
					lcsClusterEntryId, name, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LCSClusterNode getByLCEI_N_PrevAndNext(Session session,
		LCSClusterNode lcsClusterNode, long lcsClusterEntryId, String name,
		OrderByComparator<LCSClusterNode> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_LCSCLUSTERNODE_WHERE);

		query.append(_FINDER_COLUMN_LCEI_N_LCSCLUSTERENTRYID_2);

		boolean bindName = false;

		if (name == null) {
			query.append(_FINDER_COLUMN_LCEI_N_NAME_1);
		}
		else if (name.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_LCEI_N_NAME_3);
		}
		else {
			bindName = true;

			query.append(_FINDER_COLUMN_LCEI_N_NAME_2);
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
			query.append(LCSClusterNodeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(lcsClusterEntryId);

		if (bindName) {
			qPos.add(name);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(lcsClusterNode);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LCSClusterNode> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the l c s cluster nodes where lcsClusterEntryId = &#63; and name = &#63; from the database.
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @param name the name
	 */
	@Override
	public void removeByLCEI_N(long lcsClusterEntryId, String name) {
		for (LCSClusterNode lcsClusterNode : findByLCEI_N(lcsClusterEntryId,
				name, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(lcsClusterNode);
		}
	}

	/**
	 * Returns the number of l c s cluster nodes where lcsClusterEntryId = &#63; and name = &#63;.
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @param name the name
	 * @return the number of matching l c s cluster nodes
	 */
	@Override
	public int countByLCEI_N(long lcsClusterEntryId, String name) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_LCEI_N;

		Object[] finderArgs = new Object[] { lcsClusterEntryId, name };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LCSCLUSTERNODE_WHERE);

			query.append(_FINDER_COLUMN_LCEI_N_LCSCLUSTERENTRYID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_LCEI_N_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_LCEI_N_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_LCEI_N_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(lcsClusterEntryId);

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

	private static final String _FINDER_COLUMN_LCEI_N_LCSCLUSTERENTRYID_2 = "lcsClusterNode.lcsClusterEntryId = ? AND ";
	private static final String _FINDER_COLUMN_LCEI_N_NAME_1 = "lcsClusterNode.name IS NULL";
	private static final String _FINDER_COLUMN_LCEI_N_NAME_2 = "lcsClusterNode.name = ?";
	private static final String _FINDER_COLUMN_LCEI_N_NAME_3 = "(lcsClusterNode.name IS NULL OR lcsClusterNode.name = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_LCEI_A = new FinderPath(LCSClusterNodeModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterNodeModelImpl.FINDER_CACHE_ENABLED,
			LCSClusterNodeImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByLCEI_A",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LCEI_A =
		new FinderPath(LCSClusterNodeModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterNodeModelImpl.FINDER_CACHE_ENABLED,
			LCSClusterNodeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByLCEI_A",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			LCSClusterNodeModelImpl.LCSCLUSTERENTRYID_COLUMN_BITMASK |
			LCSClusterNodeModelImpl.ARCHIVED_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_LCEI_A = new FinderPath(LCSClusterNodeModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterNodeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByLCEI_A",
			new String[] { Long.class.getName(), Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_LCEI_A = new FinderPath(LCSClusterNodeModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterNodeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByLCEI_A",
			new String[] { Long.class.getName(), Boolean.class.getName() });

	/**
	 * Returns all the l c s cluster nodes where lcsClusterEntryId = &#63; and archived = &#63;.
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @param archived the archived
	 * @return the matching l c s cluster nodes
	 */
	@Override
	public List<LCSClusterNode> findByLCEI_A(long lcsClusterEntryId,
		boolean archived) {
		return findByLCEI_A(lcsClusterEntryId, archived, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the l c s cluster nodes where lcsClusterEntryId = &#63; and archived = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @param archived the archived
	 * @param start the lower bound of the range of l c s cluster nodes
	 * @param end the upper bound of the range of l c s cluster nodes (not inclusive)
	 * @return the range of matching l c s cluster nodes
	 */
	@Override
	public List<LCSClusterNode> findByLCEI_A(long lcsClusterEntryId,
		boolean archived, int start, int end) {
		return findByLCEI_A(lcsClusterEntryId, archived, start, end, null);
	}

	/**
	 * Returns an ordered range of all the l c s cluster nodes where lcsClusterEntryId = &#63; and archived = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @param archived the archived
	 * @param start the lower bound of the range of l c s cluster nodes
	 * @param end the upper bound of the range of l c s cluster nodes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching l c s cluster nodes
	 */
	@Override
	public List<LCSClusterNode> findByLCEI_A(long lcsClusterEntryId,
		boolean archived, int start, int end,
		OrderByComparator<LCSClusterNode> orderByComparator) {
		return findByLCEI_A(lcsClusterEntryId, archived, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the l c s cluster nodes where lcsClusterEntryId = &#63; and archived = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @param archived the archived
	 * @param start the lower bound of the range of l c s cluster nodes
	 * @param end the upper bound of the range of l c s cluster nodes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching l c s cluster nodes
	 */
	@Override
	public List<LCSClusterNode> findByLCEI_A(long lcsClusterEntryId,
		boolean archived, int start, int end,
		OrderByComparator<LCSClusterNode> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LCEI_A;
			finderArgs = new Object[] { lcsClusterEntryId, archived };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_LCEI_A;
			finderArgs = new Object[] {
					lcsClusterEntryId, archived,
					
					start, end, orderByComparator
				};
		}

		List<LCSClusterNode> list = null;

		if (retrieveFromCache) {
			list = (List<LCSClusterNode>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LCSClusterNode lcsClusterNode : list) {
					if ((lcsClusterEntryId != lcsClusterNode.getLcsClusterEntryId()) ||
							(archived != lcsClusterNode.getArchived())) {
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

			query.append(_SQL_SELECT_LCSCLUSTERNODE_WHERE);

			query.append(_FINDER_COLUMN_LCEI_A_LCSCLUSTERENTRYID_2);

			query.append(_FINDER_COLUMN_LCEI_A_ARCHIVED_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LCSClusterNodeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(lcsClusterEntryId);

				qPos.add(archived);

				if (!pagination) {
					list = (List<LCSClusterNode>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSClusterNode>)QueryUtil.list(q,
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
	 * Returns the first l c s cluster node in the ordered set where lcsClusterEntryId = &#63; and archived = &#63;.
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @param archived the archived
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s cluster node
	 * @throws NoSuchLCSClusterNodeException if a matching l c s cluster node could not be found
	 */
	@Override
	public LCSClusterNode findByLCEI_A_First(long lcsClusterEntryId,
		boolean archived, OrderByComparator<LCSClusterNode> orderByComparator)
		throws NoSuchLCSClusterNodeException {
		LCSClusterNode lcsClusterNode = fetchByLCEI_A_First(lcsClusterEntryId,
				archived, orderByComparator);

		if (lcsClusterNode != null) {
			return lcsClusterNode;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("lcsClusterEntryId=");
		msg.append(lcsClusterEntryId);

		msg.append(", archived=");
		msg.append(archived);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSClusterNodeException(msg.toString());
	}

	/**
	 * Returns the first l c s cluster node in the ordered set where lcsClusterEntryId = &#63; and archived = &#63;.
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @param archived the archived
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s cluster node, or <code>null</code> if a matching l c s cluster node could not be found
	 */
	@Override
	public LCSClusterNode fetchByLCEI_A_First(long lcsClusterEntryId,
		boolean archived, OrderByComparator<LCSClusterNode> orderByComparator) {
		List<LCSClusterNode> list = findByLCEI_A(lcsClusterEntryId, archived,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last l c s cluster node in the ordered set where lcsClusterEntryId = &#63; and archived = &#63;.
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @param archived the archived
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s cluster node
	 * @throws NoSuchLCSClusterNodeException if a matching l c s cluster node could not be found
	 */
	@Override
	public LCSClusterNode findByLCEI_A_Last(long lcsClusterEntryId,
		boolean archived, OrderByComparator<LCSClusterNode> orderByComparator)
		throws NoSuchLCSClusterNodeException {
		LCSClusterNode lcsClusterNode = fetchByLCEI_A_Last(lcsClusterEntryId,
				archived, orderByComparator);

		if (lcsClusterNode != null) {
			return lcsClusterNode;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("lcsClusterEntryId=");
		msg.append(lcsClusterEntryId);

		msg.append(", archived=");
		msg.append(archived);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSClusterNodeException(msg.toString());
	}

	/**
	 * Returns the last l c s cluster node in the ordered set where lcsClusterEntryId = &#63; and archived = &#63;.
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @param archived the archived
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s cluster node, or <code>null</code> if a matching l c s cluster node could not be found
	 */
	@Override
	public LCSClusterNode fetchByLCEI_A_Last(long lcsClusterEntryId,
		boolean archived, OrderByComparator<LCSClusterNode> orderByComparator) {
		int count = countByLCEI_A(lcsClusterEntryId, archived);

		if (count == 0) {
			return null;
		}

		List<LCSClusterNode> list = findByLCEI_A(lcsClusterEntryId, archived,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the l c s cluster nodes before and after the current l c s cluster node in the ordered set where lcsClusterEntryId = &#63; and archived = &#63;.
	 *
	 * @param lcsClusterNodeId the primary key of the current l c s cluster node
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @param archived the archived
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next l c s cluster node
	 * @throws NoSuchLCSClusterNodeException if a l c s cluster node with the primary key could not be found
	 */
	@Override
	public LCSClusterNode[] findByLCEI_A_PrevAndNext(long lcsClusterNodeId,
		long lcsClusterEntryId, boolean archived,
		OrderByComparator<LCSClusterNode> orderByComparator)
		throws NoSuchLCSClusterNodeException {
		LCSClusterNode lcsClusterNode = findByPrimaryKey(lcsClusterNodeId);

		Session session = null;

		try {
			session = openSession();

			LCSClusterNode[] array = new LCSClusterNodeImpl[3];

			array[0] = getByLCEI_A_PrevAndNext(session, lcsClusterNode,
					lcsClusterEntryId, archived, orderByComparator, true);

			array[1] = lcsClusterNode;

			array[2] = getByLCEI_A_PrevAndNext(session, lcsClusterNode,
					lcsClusterEntryId, archived, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LCSClusterNode getByLCEI_A_PrevAndNext(Session session,
		LCSClusterNode lcsClusterNode, long lcsClusterEntryId,
		boolean archived, OrderByComparator<LCSClusterNode> orderByComparator,
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

		query.append(_SQL_SELECT_LCSCLUSTERNODE_WHERE);

		query.append(_FINDER_COLUMN_LCEI_A_LCSCLUSTERENTRYID_2);

		query.append(_FINDER_COLUMN_LCEI_A_ARCHIVED_2);

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
			query.append(LCSClusterNodeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(lcsClusterEntryId);

		qPos.add(archived);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(lcsClusterNode);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LCSClusterNode> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the l c s cluster nodes where lcsClusterEntryId = any &#63; and archived = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsClusterEntryIds the lcs cluster entry IDs
	 * @param archived the archived
	 * @return the matching l c s cluster nodes
	 */
	@Override
	public List<LCSClusterNode> findByLCEI_A(long[] lcsClusterEntryIds,
		boolean archived) {
		return findByLCEI_A(lcsClusterEntryIds, archived, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the l c s cluster nodes where lcsClusterEntryId = any &#63; and archived = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsClusterEntryIds the lcs cluster entry IDs
	 * @param archived the archived
	 * @param start the lower bound of the range of l c s cluster nodes
	 * @param end the upper bound of the range of l c s cluster nodes (not inclusive)
	 * @return the range of matching l c s cluster nodes
	 */
	@Override
	public List<LCSClusterNode> findByLCEI_A(long[] lcsClusterEntryIds,
		boolean archived, int start, int end) {
		return findByLCEI_A(lcsClusterEntryIds, archived, start, end, null);
	}

	/**
	 * Returns an ordered range of all the l c s cluster nodes where lcsClusterEntryId = any &#63; and archived = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsClusterEntryIds the lcs cluster entry IDs
	 * @param archived the archived
	 * @param start the lower bound of the range of l c s cluster nodes
	 * @param end the upper bound of the range of l c s cluster nodes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching l c s cluster nodes
	 */
	@Override
	public List<LCSClusterNode> findByLCEI_A(long[] lcsClusterEntryIds,
		boolean archived, int start, int end,
		OrderByComparator<LCSClusterNode> orderByComparator) {
		return findByLCEI_A(lcsClusterEntryIds, archived, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the l c s cluster nodes where lcsClusterEntryId = &#63; and archived = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @param archived the archived
	 * @param start the lower bound of the range of l c s cluster nodes
	 * @param end the upper bound of the range of l c s cluster nodes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching l c s cluster nodes
	 */
	@Override
	public List<LCSClusterNode> findByLCEI_A(long[] lcsClusterEntryIds,
		boolean archived, int start, int end,
		OrderByComparator<LCSClusterNode> orderByComparator,
		boolean retrieveFromCache) {
		if (lcsClusterEntryIds == null) {
			lcsClusterEntryIds = new long[0];
		}
		else if (lcsClusterEntryIds.length > 1) {
			lcsClusterEntryIds = ArrayUtil.unique(lcsClusterEntryIds);

			Arrays.sort(lcsClusterEntryIds);
		}

		if (lcsClusterEntryIds.length == 1) {
			return findByLCEI_A(lcsClusterEntryIds[0], archived, start, end,
				orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] {
					StringUtil.merge(lcsClusterEntryIds), archived
				};
		}
		else {
			finderArgs = new Object[] {
					StringUtil.merge(lcsClusterEntryIds), archived,
					
					start, end, orderByComparator
				};
		}

		List<LCSClusterNode> list = null;

		if (retrieveFromCache) {
			list = (List<LCSClusterNode>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_LCEI_A,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LCSClusterNode lcsClusterNode : list) {
					if (!ArrayUtil.contains(lcsClusterEntryIds,
								lcsClusterNode.getLcsClusterEntryId()) ||
							(archived != lcsClusterNode.getArchived())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_LCSCLUSTERNODE_WHERE);

			if (lcsClusterEntryIds.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_LCEI_A_LCSCLUSTERENTRYID_7);

				query.append(StringUtil.merge(lcsClusterEntryIds));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_LCEI_A_ARCHIVED_2);

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LCSClusterNodeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(archived);

				if (!pagination) {
					list = (List<LCSClusterNode>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSClusterNode>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_LCEI_A,
					finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_LCEI_A,
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
	 * Removes all the l c s cluster nodes where lcsClusterEntryId = &#63; and archived = &#63; from the database.
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @param archived the archived
	 */
	@Override
	public void removeByLCEI_A(long lcsClusterEntryId, boolean archived) {
		for (LCSClusterNode lcsClusterNode : findByLCEI_A(lcsClusterEntryId,
				archived, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(lcsClusterNode);
		}
	}

	/**
	 * Returns the number of l c s cluster nodes where lcsClusterEntryId = &#63; and archived = &#63;.
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @param archived the archived
	 * @return the number of matching l c s cluster nodes
	 */
	@Override
	public int countByLCEI_A(long lcsClusterEntryId, boolean archived) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_LCEI_A;

		Object[] finderArgs = new Object[] { lcsClusterEntryId, archived };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LCSCLUSTERNODE_WHERE);

			query.append(_FINDER_COLUMN_LCEI_A_LCSCLUSTERENTRYID_2);

			query.append(_FINDER_COLUMN_LCEI_A_ARCHIVED_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(lcsClusterEntryId);

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

	/**
	 * Returns the number of l c s cluster nodes where lcsClusterEntryId = any &#63; and archived = &#63;.
	 *
	 * @param lcsClusterEntryIds the lcs cluster entry IDs
	 * @param archived the archived
	 * @return the number of matching l c s cluster nodes
	 */
	@Override
	public int countByLCEI_A(long[] lcsClusterEntryIds, boolean archived) {
		if (lcsClusterEntryIds == null) {
			lcsClusterEntryIds = new long[0];
		}
		else if (lcsClusterEntryIds.length > 1) {
			lcsClusterEntryIds = ArrayUtil.unique(lcsClusterEntryIds);

			Arrays.sort(lcsClusterEntryIds);
		}

		Object[] finderArgs = new Object[] {
				StringUtil.merge(lcsClusterEntryIds), archived
			};

		Long count = (Long)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_LCEI_A,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_LCSCLUSTERNODE_WHERE);

			if (lcsClusterEntryIds.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_LCEI_A_LCSCLUSTERENTRYID_7);

				query.append(StringUtil.merge(lcsClusterEntryIds));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_LCEI_A_ARCHIVED_2);

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(archived);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_LCEI_A,
					finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_LCEI_A,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_LCEI_A_LCSCLUSTERENTRYID_2 = "lcsClusterNode.lcsClusterEntryId = ? AND ";
	private static final String _FINDER_COLUMN_LCEI_A_LCSCLUSTERENTRYID_7 = "lcsClusterNode.lcsClusterEntryId IN (";
	private static final String _FINDER_COLUMN_LCEI_A_ARCHIVED_2 = "lcsClusterNode.archived = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_BN_A = new FinderPath(LCSClusterNodeModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterNodeModelImpl.FINDER_CACHE_ENABLED,
			LCSClusterNodeImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByBN_A",
			new String[] {
				Integer.class.getName(), Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BN_A = new FinderPath(LCSClusterNodeModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterNodeModelImpl.FINDER_CACHE_ENABLED,
			LCSClusterNodeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByBN_A",
			new String[] { Integer.class.getName(), Boolean.class.getName() },
			LCSClusterNodeModelImpl.BUILDNUMBER_COLUMN_BITMASK |
			LCSClusterNodeModelImpl.ARCHIVED_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_BN_A = new FinderPath(LCSClusterNodeModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterNodeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByBN_A",
			new String[] { Integer.class.getName(), Boolean.class.getName() });

	/**
	 * Returns all the l c s cluster nodes where buildNumber = &#63; and archived = &#63;.
	 *
	 * @param buildNumber the build number
	 * @param archived the archived
	 * @return the matching l c s cluster nodes
	 */
	@Override
	public List<LCSClusterNode> findByBN_A(int buildNumber, boolean archived) {
		return findByBN_A(buildNumber, archived, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the l c s cluster nodes where buildNumber = &#63; and archived = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param buildNumber the build number
	 * @param archived the archived
	 * @param start the lower bound of the range of l c s cluster nodes
	 * @param end the upper bound of the range of l c s cluster nodes (not inclusive)
	 * @return the range of matching l c s cluster nodes
	 */
	@Override
	public List<LCSClusterNode> findByBN_A(int buildNumber, boolean archived,
		int start, int end) {
		return findByBN_A(buildNumber, archived, start, end, null);
	}

	/**
	 * Returns an ordered range of all the l c s cluster nodes where buildNumber = &#63; and archived = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param buildNumber the build number
	 * @param archived the archived
	 * @param start the lower bound of the range of l c s cluster nodes
	 * @param end the upper bound of the range of l c s cluster nodes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching l c s cluster nodes
	 */
	@Override
	public List<LCSClusterNode> findByBN_A(int buildNumber, boolean archived,
		int start, int end, OrderByComparator<LCSClusterNode> orderByComparator) {
		return findByBN_A(buildNumber, archived, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the l c s cluster nodes where buildNumber = &#63; and archived = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param buildNumber the build number
	 * @param archived the archived
	 * @param start the lower bound of the range of l c s cluster nodes
	 * @param end the upper bound of the range of l c s cluster nodes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching l c s cluster nodes
	 */
	@Override
	public List<LCSClusterNode> findByBN_A(int buildNumber, boolean archived,
		int start, int end,
		OrderByComparator<LCSClusterNode> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BN_A;
			finderArgs = new Object[] { buildNumber, archived };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_BN_A;
			finderArgs = new Object[] {
					buildNumber, archived,
					
					start, end, orderByComparator
				};
		}

		List<LCSClusterNode> list = null;

		if (retrieveFromCache) {
			list = (List<LCSClusterNode>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LCSClusterNode lcsClusterNode : list) {
					if ((buildNumber != lcsClusterNode.getBuildNumber()) ||
							(archived != lcsClusterNode.getArchived())) {
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

			query.append(_SQL_SELECT_LCSCLUSTERNODE_WHERE);

			query.append(_FINDER_COLUMN_BN_A_BUILDNUMBER_2);

			query.append(_FINDER_COLUMN_BN_A_ARCHIVED_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LCSClusterNodeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(buildNumber);

				qPos.add(archived);

				if (!pagination) {
					list = (List<LCSClusterNode>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSClusterNode>)QueryUtil.list(q,
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
	 * Returns the first l c s cluster node in the ordered set where buildNumber = &#63; and archived = &#63;.
	 *
	 * @param buildNumber the build number
	 * @param archived the archived
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s cluster node
	 * @throws NoSuchLCSClusterNodeException if a matching l c s cluster node could not be found
	 */
	@Override
	public LCSClusterNode findByBN_A_First(int buildNumber, boolean archived,
		OrderByComparator<LCSClusterNode> orderByComparator)
		throws NoSuchLCSClusterNodeException {
		LCSClusterNode lcsClusterNode = fetchByBN_A_First(buildNumber,
				archived, orderByComparator);

		if (lcsClusterNode != null) {
			return lcsClusterNode;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("buildNumber=");
		msg.append(buildNumber);

		msg.append(", archived=");
		msg.append(archived);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSClusterNodeException(msg.toString());
	}

	/**
	 * Returns the first l c s cluster node in the ordered set where buildNumber = &#63; and archived = &#63;.
	 *
	 * @param buildNumber the build number
	 * @param archived the archived
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s cluster node, or <code>null</code> if a matching l c s cluster node could not be found
	 */
	@Override
	public LCSClusterNode fetchByBN_A_First(int buildNumber, boolean archived,
		OrderByComparator<LCSClusterNode> orderByComparator) {
		List<LCSClusterNode> list = findByBN_A(buildNumber, archived, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last l c s cluster node in the ordered set where buildNumber = &#63; and archived = &#63;.
	 *
	 * @param buildNumber the build number
	 * @param archived the archived
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s cluster node
	 * @throws NoSuchLCSClusterNodeException if a matching l c s cluster node could not be found
	 */
	@Override
	public LCSClusterNode findByBN_A_Last(int buildNumber, boolean archived,
		OrderByComparator<LCSClusterNode> orderByComparator)
		throws NoSuchLCSClusterNodeException {
		LCSClusterNode lcsClusterNode = fetchByBN_A_Last(buildNumber, archived,
				orderByComparator);

		if (lcsClusterNode != null) {
			return lcsClusterNode;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("buildNumber=");
		msg.append(buildNumber);

		msg.append(", archived=");
		msg.append(archived);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSClusterNodeException(msg.toString());
	}

	/**
	 * Returns the last l c s cluster node in the ordered set where buildNumber = &#63; and archived = &#63;.
	 *
	 * @param buildNumber the build number
	 * @param archived the archived
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s cluster node, or <code>null</code> if a matching l c s cluster node could not be found
	 */
	@Override
	public LCSClusterNode fetchByBN_A_Last(int buildNumber, boolean archived,
		OrderByComparator<LCSClusterNode> orderByComparator) {
		int count = countByBN_A(buildNumber, archived);

		if (count == 0) {
			return null;
		}

		List<LCSClusterNode> list = findByBN_A(buildNumber, archived,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the l c s cluster nodes before and after the current l c s cluster node in the ordered set where buildNumber = &#63; and archived = &#63;.
	 *
	 * @param lcsClusterNodeId the primary key of the current l c s cluster node
	 * @param buildNumber the build number
	 * @param archived the archived
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next l c s cluster node
	 * @throws NoSuchLCSClusterNodeException if a l c s cluster node with the primary key could not be found
	 */
	@Override
	public LCSClusterNode[] findByBN_A_PrevAndNext(long lcsClusterNodeId,
		int buildNumber, boolean archived,
		OrderByComparator<LCSClusterNode> orderByComparator)
		throws NoSuchLCSClusterNodeException {
		LCSClusterNode lcsClusterNode = findByPrimaryKey(lcsClusterNodeId);

		Session session = null;

		try {
			session = openSession();

			LCSClusterNode[] array = new LCSClusterNodeImpl[3];

			array[0] = getByBN_A_PrevAndNext(session, lcsClusterNode,
					buildNumber, archived, orderByComparator, true);

			array[1] = lcsClusterNode;

			array[2] = getByBN_A_PrevAndNext(session, lcsClusterNode,
					buildNumber, archived, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LCSClusterNode getByBN_A_PrevAndNext(Session session,
		LCSClusterNode lcsClusterNode, int buildNumber, boolean archived,
		OrderByComparator<LCSClusterNode> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_LCSCLUSTERNODE_WHERE);

		query.append(_FINDER_COLUMN_BN_A_BUILDNUMBER_2);

		query.append(_FINDER_COLUMN_BN_A_ARCHIVED_2);

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
			query.append(LCSClusterNodeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(buildNumber);

		qPos.add(archived);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(lcsClusterNode);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LCSClusterNode> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the l c s cluster nodes where buildNumber = &#63; and archived = &#63; from the database.
	 *
	 * @param buildNumber the build number
	 * @param archived the archived
	 */
	@Override
	public void removeByBN_A(int buildNumber, boolean archived) {
		for (LCSClusterNode lcsClusterNode : findByBN_A(buildNumber, archived,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(lcsClusterNode);
		}
	}

	/**
	 * Returns the number of l c s cluster nodes where buildNumber = &#63; and archived = &#63;.
	 *
	 * @param buildNumber the build number
	 * @param archived the archived
	 * @return the number of matching l c s cluster nodes
	 */
	@Override
	public int countByBN_A(int buildNumber, boolean archived) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_BN_A;

		Object[] finderArgs = new Object[] { buildNumber, archived };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LCSCLUSTERNODE_WHERE);

			query.append(_FINDER_COLUMN_BN_A_BUILDNUMBER_2);

			query.append(_FINDER_COLUMN_BN_A_ARCHIVED_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(buildNumber);

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

	private static final String _FINDER_COLUMN_BN_A_BUILDNUMBER_2 = "lcsClusterNode.buildNumber = ? AND ";
	private static final String _FINDER_COLUMN_BN_A_ARCHIVED_2 = "lcsClusterNode.archived = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_LCEI_N_A = new FinderPath(LCSClusterNodeModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterNodeModelImpl.FINDER_CACHE_ENABLED,
			LCSClusterNodeImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByLCEI_N_A",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LCEI_N_A =
		new FinderPath(LCSClusterNodeModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterNodeModelImpl.FINDER_CACHE_ENABLED,
			LCSClusterNodeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByLCEI_N_A",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName()
			},
			LCSClusterNodeModelImpl.LCSCLUSTERENTRYID_COLUMN_BITMASK |
			LCSClusterNodeModelImpl.NAME_COLUMN_BITMASK |
			LCSClusterNodeModelImpl.ARCHIVED_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_LCEI_N_A = new FinderPath(LCSClusterNodeModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterNodeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByLCEI_N_A",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName()
			});

	/**
	 * Returns all the l c s cluster nodes where lcsClusterEntryId = &#63; and name = &#63; and archived = &#63;.
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @param name the name
	 * @param archived the archived
	 * @return the matching l c s cluster nodes
	 */
	@Override
	public List<LCSClusterNode> findByLCEI_N_A(long lcsClusterEntryId,
		String name, boolean archived) {
		return findByLCEI_N_A(lcsClusterEntryId, name, archived,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the l c s cluster nodes where lcsClusterEntryId = &#63; and name = &#63; and archived = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @param name the name
	 * @param archived the archived
	 * @param start the lower bound of the range of l c s cluster nodes
	 * @param end the upper bound of the range of l c s cluster nodes (not inclusive)
	 * @return the range of matching l c s cluster nodes
	 */
	@Override
	public List<LCSClusterNode> findByLCEI_N_A(long lcsClusterEntryId,
		String name, boolean archived, int start, int end) {
		return findByLCEI_N_A(lcsClusterEntryId, name, archived, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the l c s cluster nodes where lcsClusterEntryId = &#63; and name = &#63; and archived = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @param name the name
	 * @param archived the archived
	 * @param start the lower bound of the range of l c s cluster nodes
	 * @param end the upper bound of the range of l c s cluster nodes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching l c s cluster nodes
	 */
	@Override
	public List<LCSClusterNode> findByLCEI_N_A(long lcsClusterEntryId,
		String name, boolean archived, int start, int end,
		OrderByComparator<LCSClusterNode> orderByComparator) {
		return findByLCEI_N_A(lcsClusterEntryId, name, archived, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the l c s cluster nodes where lcsClusterEntryId = &#63; and name = &#63; and archived = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @param name the name
	 * @param archived the archived
	 * @param start the lower bound of the range of l c s cluster nodes
	 * @param end the upper bound of the range of l c s cluster nodes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching l c s cluster nodes
	 */
	@Override
	public List<LCSClusterNode> findByLCEI_N_A(long lcsClusterEntryId,
		String name, boolean archived, int start, int end,
		OrderByComparator<LCSClusterNode> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LCEI_N_A;
			finderArgs = new Object[] { lcsClusterEntryId, name, archived };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_LCEI_N_A;
			finderArgs = new Object[] {
					lcsClusterEntryId, name, archived,
					
					start, end, orderByComparator
				};
		}

		List<LCSClusterNode> list = null;

		if (retrieveFromCache) {
			list = (List<LCSClusterNode>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LCSClusterNode lcsClusterNode : list) {
					if ((lcsClusterEntryId != lcsClusterNode.getLcsClusterEntryId()) ||
							!Objects.equals(name, lcsClusterNode.getName()) ||
							(archived != lcsClusterNode.getArchived())) {
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

			query.append(_SQL_SELECT_LCSCLUSTERNODE_WHERE);

			query.append(_FINDER_COLUMN_LCEI_N_A_LCSCLUSTERENTRYID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_LCEI_N_A_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_LCEI_N_A_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_LCEI_N_A_NAME_2);
			}

			query.append(_FINDER_COLUMN_LCEI_N_A_ARCHIVED_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LCSClusterNodeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(lcsClusterEntryId);

				if (bindName) {
					qPos.add(name);
				}

				qPos.add(archived);

				if (!pagination) {
					list = (List<LCSClusterNode>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSClusterNode>)QueryUtil.list(q,
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
	 * Returns the first l c s cluster node in the ordered set where lcsClusterEntryId = &#63; and name = &#63; and archived = &#63;.
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @param name the name
	 * @param archived the archived
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s cluster node
	 * @throws NoSuchLCSClusterNodeException if a matching l c s cluster node could not be found
	 */
	@Override
	public LCSClusterNode findByLCEI_N_A_First(long lcsClusterEntryId,
		String name, boolean archived,
		OrderByComparator<LCSClusterNode> orderByComparator)
		throws NoSuchLCSClusterNodeException {
		LCSClusterNode lcsClusterNode = fetchByLCEI_N_A_First(lcsClusterEntryId,
				name, archived, orderByComparator);

		if (lcsClusterNode != null) {
			return lcsClusterNode;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("lcsClusterEntryId=");
		msg.append(lcsClusterEntryId);

		msg.append(", name=");
		msg.append(name);

		msg.append(", archived=");
		msg.append(archived);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSClusterNodeException(msg.toString());
	}

	/**
	 * Returns the first l c s cluster node in the ordered set where lcsClusterEntryId = &#63; and name = &#63; and archived = &#63;.
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @param name the name
	 * @param archived the archived
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s cluster node, or <code>null</code> if a matching l c s cluster node could not be found
	 */
	@Override
	public LCSClusterNode fetchByLCEI_N_A_First(long lcsClusterEntryId,
		String name, boolean archived,
		OrderByComparator<LCSClusterNode> orderByComparator) {
		List<LCSClusterNode> list = findByLCEI_N_A(lcsClusterEntryId, name,
				archived, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last l c s cluster node in the ordered set where lcsClusterEntryId = &#63; and name = &#63; and archived = &#63;.
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @param name the name
	 * @param archived the archived
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s cluster node
	 * @throws NoSuchLCSClusterNodeException if a matching l c s cluster node could not be found
	 */
	@Override
	public LCSClusterNode findByLCEI_N_A_Last(long lcsClusterEntryId,
		String name, boolean archived,
		OrderByComparator<LCSClusterNode> orderByComparator)
		throws NoSuchLCSClusterNodeException {
		LCSClusterNode lcsClusterNode = fetchByLCEI_N_A_Last(lcsClusterEntryId,
				name, archived, orderByComparator);

		if (lcsClusterNode != null) {
			return lcsClusterNode;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("lcsClusterEntryId=");
		msg.append(lcsClusterEntryId);

		msg.append(", name=");
		msg.append(name);

		msg.append(", archived=");
		msg.append(archived);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSClusterNodeException(msg.toString());
	}

	/**
	 * Returns the last l c s cluster node in the ordered set where lcsClusterEntryId = &#63; and name = &#63; and archived = &#63;.
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @param name the name
	 * @param archived the archived
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s cluster node, or <code>null</code> if a matching l c s cluster node could not be found
	 */
	@Override
	public LCSClusterNode fetchByLCEI_N_A_Last(long lcsClusterEntryId,
		String name, boolean archived,
		OrderByComparator<LCSClusterNode> orderByComparator) {
		int count = countByLCEI_N_A(lcsClusterEntryId, name, archived);

		if (count == 0) {
			return null;
		}

		List<LCSClusterNode> list = findByLCEI_N_A(lcsClusterEntryId, name,
				archived, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the l c s cluster nodes before and after the current l c s cluster node in the ordered set where lcsClusterEntryId = &#63; and name = &#63; and archived = &#63;.
	 *
	 * @param lcsClusterNodeId the primary key of the current l c s cluster node
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @param name the name
	 * @param archived the archived
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next l c s cluster node
	 * @throws NoSuchLCSClusterNodeException if a l c s cluster node with the primary key could not be found
	 */
	@Override
	public LCSClusterNode[] findByLCEI_N_A_PrevAndNext(long lcsClusterNodeId,
		long lcsClusterEntryId, String name, boolean archived,
		OrderByComparator<LCSClusterNode> orderByComparator)
		throws NoSuchLCSClusterNodeException {
		LCSClusterNode lcsClusterNode = findByPrimaryKey(lcsClusterNodeId);

		Session session = null;

		try {
			session = openSession();

			LCSClusterNode[] array = new LCSClusterNodeImpl[3];

			array[0] = getByLCEI_N_A_PrevAndNext(session, lcsClusterNode,
					lcsClusterEntryId, name, archived, orderByComparator, true);

			array[1] = lcsClusterNode;

			array[2] = getByLCEI_N_A_PrevAndNext(session, lcsClusterNode,
					lcsClusterEntryId, name, archived, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LCSClusterNode getByLCEI_N_A_PrevAndNext(Session session,
		LCSClusterNode lcsClusterNode, long lcsClusterEntryId, String name,
		boolean archived, OrderByComparator<LCSClusterNode> orderByComparator,
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

		query.append(_SQL_SELECT_LCSCLUSTERNODE_WHERE);

		query.append(_FINDER_COLUMN_LCEI_N_A_LCSCLUSTERENTRYID_2);

		boolean bindName = false;

		if (name == null) {
			query.append(_FINDER_COLUMN_LCEI_N_A_NAME_1);
		}
		else if (name.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_LCEI_N_A_NAME_3);
		}
		else {
			bindName = true;

			query.append(_FINDER_COLUMN_LCEI_N_A_NAME_2);
		}

		query.append(_FINDER_COLUMN_LCEI_N_A_ARCHIVED_2);

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
			query.append(LCSClusterNodeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(lcsClusterEntryId);

		if (bindName) {
			qPos.add(name);
		}

		qPos.add(archived);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(lcsClusterNode);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LCSClusterNode> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the l c s cluster nodes where lcsClusterEntryId = &#63; and name = &#63; and archived = &#63; from the database.
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @param name the name
	 * @param archived the archived
	 */
	@Override
	public void removeByLCEI_N_A(long lcsClusterEntryId, String name,
		boolean archived) {
		for (LCSClusterNode lcsClusterNode : findByLCEI_N_A(lcsClusterEntryId,
				name, archived, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(lcsClusterNode);
		}
	}

	/**
	 * Returns the number of l c s cluster nodes where lcsClusterEntryId = &#63; and name = &#63; and archived = &#63;.
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @param name the name
	 * @param archived the archived
	 * @return the number of matching l c s cluster nodes
	 */
	@Override
	public int countByLCEI_N_A(long lcsClusterEntryId, String name,
		boolean archived) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_LCEI_N_A;

		Object[] finderArgs = new Object[] { lcsClusterEntryId, name, archived };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_LCSCLUSTERNODE_WHERE);

			query.append(_FINDER_COLUMN_LCEI_N_A_LCSCLUSTERENTRYID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_LCEI_N_A_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_LCEI_N_A_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_LCEI_N_A_NAME_2);
			}

			query.append(_FINDER_COLUMN_LCEI_N_A_ARCHIVED_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(lcsClusterEntryId);

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

	private static final String _FINDER_COLUMN_LCEI_N_A_LCSCLUSTERENTRYID_2 = "lcsClusterNode.lcsClusterEntryId = ? AND ";
	private static final String _FINDER_COLUMN_LCEI_N_A_NAME_1 = "lcsClusterNode.name IS NULL AND ";
	private static final String _FINDER_COLUMN_LCEI_N_A_NAME_2 = "lcsClusterNode.name = ? AND ";
	private static final String _FINDER_COLUMN_LCEI_N_A_NAME_3 = "(lcsClusterNode.name IS NULL OR lcsClusterNode.name = '') AND ";
	private static final String _FINDER_COLUMN_LCEI_N_A_ARCHIVED_2 = "lcsClusterNode.archived = ?";

	public LCSClusterNodePersistenceImpl() {
		setModelClass(LCSClusterNode.class);
	}

	/**
	 * Caches the l c s cluster node in the entity cache if it is enabled.
	 *
	 * @param lcsClusterNode the l c s cluster node
	 */
	@Override
	public void cacheResult(LCSClusterNode lcsClusterNode) {
		entityCache.putResult(LCSClusterNodeModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterNodeImpl.class, lcsClusterNode.getPrimaryKey(),
			lcsClusterNode);

		finderCache.putResult(FINDER_PATH_FETCH_BY_KEY,
			new Object[] { lcsClusterNode.getKey() }, lcsClusterNode);

		lcsClusterNode.resetOriginalValues();
	}

	/**
	 * Caches the l c s cluster nodes in the entity cache if it is enabled.
	 *
	 * @param lcsClusterNodes the l c s cluster nodes
	 */
	@Override
	public void cacheResult(List<LCSClusterNode> lcsClusterNodes) {
		for (LCSClusterNode lcsClusterNode : lcsClusterNodes) {
			if (entityCache.getResult(
						LCSClusterNodeModelImpl.ENTITY_CACHE_ENABLED,
						LCSClusterNodeImpl.class, lcsClusterNode.getPrimaryKey()) == null) {
				cacheResult(lcsClusterNode);
			}
			else {
				lcsClusterNode.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all l c s cluster nodes.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(LCSClusterNodeImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the l c s cluster node.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LCSClusterNode lcsClusterNode) {
		entityCache.removeResult(LCSClusterNodeModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterNodeImpl.class, lcsClusterNode.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((LCSClusterNodeModelImpl)lcsClusterNode, true);
	}

	@Override
	public void clearCache(List<LCSClusterNode> lcsClusterNodes) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (LCSClusterNode lcsClusterNode : lcsClusterNodes) {
			entityCache.removeResult(LCSClusterNodeModelImpl.ENTITY_CACHE_ENABLED,
				LCSClusterNodeImpl.class, lcsClusterNode.getPrimaryKey());

			clearUniqueFindersCache((LCSClusterNodeModelImpl)lcsClusterNode,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		LCSClusterNodeModelImpl lcsClusterNodeModelImpl) {
		Object[] args = new Object[] { lcsClusterNodeModelImpl.getKey() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_KEY, args, Long.valueOf(1),
			false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_KEY, args,
			lcsClusterNodeModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		LCSClusterNodeModelImpl lcsClusterNodeModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] { lcsClusterNodeModelImpl.getKey() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_KEY, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_KEY, args);
		}

		if ((lcsClusterNodeModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_KEY.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					lcsClusterNodeModelImpl.getOriginalKey()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_KEY, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_KEY, args);
		}
	}

	/**
	 * Creates a new l c s cluster node with the primary key. Does not add the l c s cluster node to the database.
	 *
	 * @param lcsClusterNodeId the primary key for the new l c s cluster node
	 * @return the new l c s cluster node
	 */
	@Override
	public LCSClusterNode create(long lcsClusterNodeId) {
		LCSClusterNode lcsClusterNode = new LCSClusterNodeImpl();

		lcsClusterNode.setNew(true);
		lcsClusterNode.setPrimaryKey(lcsClusterNodeId);

		return lcsClusterNode;
	}

	/**
	 * Removes the l c s cluster node with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param lcsClusterNodeId the primary key of the l c s cluster node
	 * @return the l c s cluster node that was removed
	 * @throws NoSuchLCSClusterNodeException if a l c s cluster node with the primary key could not be found
	 */
	@Override
	public LCSClusterNode remove(long lcsClusterNodeId)
		throws NoSuchLCSClusterNodeException {
		return remove((Serializable)lcsClusterNodeId);
	}

	/**
	 * Removes the l c s cluster node with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the l c s cluster node
	 * @return the l c s cluster node that was removed
	 * @throws NoSuchLCSClusterNodeException if a l c s cluster node with the primary key could not be found
	 */
	@Override
	public LCSClusterNode remove(Serializable primaryKey)
		throws NoSuchLCSClusterNodeException {
		Session session = null;

		try {
			session = openSession();

			LCSClusterNode lcsClusterNode = (LCSClusterNode)session.get(LCSClusterNodeImpl.class,
					primaryKey);

			if (lcsClusterNode == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLCSClusterNodeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(lcsClusterNode);
		}
		catch (NoSuchLCSClusterNodeException nsee) {
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
	protected LCSClusterNode removeImpl(LCSClusterNode lcsClusterNode) {
		lcsClusterNode = toUnwrappedModel(lcsClusterNode);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(lcsClusterNode)) {
				lcsClusterNode = (LCSClusterNode)session.get(LCSClusterNodeImpl.class,
						lcsClusterNode.getPrimaryKeyObj());
			}

			if (lcsClusterNode != null) {
				session.delete(lcsClusterNode);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (lcsClusterNode != null) {
			clearCache(lcsClusterNode);
		}

		return lcsClusterNode;
	}

	@Override
	public LCSClusterNode updateImpl(LCSClusterNode lcsClusterNode) {
		lcsClusterNode = toUnwrappedModel(lcsClusterNode);

		boolean isNew = lcsClusterNode.isNew();

		LCSClusterNodeModelImpl lcsClusterNodeModelImpl = (LCSClusterNodeModelImpl)lcsClusterNode;

		Session session = null;

		try {
			session = openSession();

			if (lcsClusterNode.isNew()) {
				session.save(lcsClusterNode);

				lcsClusterNode.setNew(false);
			}
			else {
				lcsClusterNode = (LCSClusterNode)session.merge(lcsClusterNode);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !LCSClusterNodeModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((lcsClusterNodeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LCSCLUSTERENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						lcsClusterNodeModelImpl.getOriginalLcsClusterEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_LCSCLUSTERENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LCSCLUSTERENTRYID,
					args);

				args = new Object[] {
						lcsClusterNodeModelImpl.getLcsClusterEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_LCSCLUSTERENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LCSCLUSTERENTRYID,
					args);
			}

			if ((lcsClusterNodeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUILDNUMBER.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						lcsClusterNodeModelImpl.getOriginalBuildNumber()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_BUILDNUMBER, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUILDNUMBER,
					args);

				args = new Object[] { lcsClusterNodeModelImpl.getBuildNumber() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_BUILDNUMBER, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUILDNUMBER,
					args);
			}

			if ((lcsClusterNodeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LCEI_N.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						lcsClusterNodeModelImpl.getOriginalLcsClusterEntryId(),
						lcsClusterNodeModelImpl.getOriginalName()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_LCEI_N, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LCEI_N,
					args);

				args = new Object[] {
						lcsClusterNodeModelImpl.getLcsClusterEntryId(),
						lcsClusterNodeModelImpl.getName()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_LCEI_N, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LCEI_N,
					args);
			}

			if ((lcsClusterNodeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LCEI_A.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						lcsClusterNodeModelImpl.getOriginalLcsClusterEntryId(),
						lcsClusterNodeModelImpl.getOriginalArchived()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_LCEI_A, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LCEI_A,
					args);

				args = new Object[] {
						lcsClusterNodeModelImpl.getLcsClusterEntryId(),
						lcsClusterNodeModelImpl.getArchived()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_LCEI_A, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LCEI_A,
					args);
			}

			if ((lcsClusterNodeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BN_A.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						lcsClusterNodeModelImpl.getOriginalBuildNumber(),
						lcsClusterNodeModelImpl.getOriginalArchived()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_BN_A, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BN_A,
					args);

				args = new Object[] {
						lcsClusterNodeModelImpl.getBuildNumber(),
						lcsClusterNodeModelImpl.getArchived()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_BN_A, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BN_A,
					args);
			}

			if ((lcsClusterNodeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LCEI_N_A.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						lcsClusterNodeModelImpl.getOriginalLcsClusterEntryId(),
						lcsClusterNodeModelImpl.getOriginalName(),
						lcsClusterNodeModelImpl.getOriginalArchived()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_LCEI_N_A, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LCEI_N_A,
					args);

				args = new Object[] {
						lcsClusterNodeModelImpl.getLcsClusterEntryId(),
						lcsClusterNodeModelImpl.getName(),
						lcsClusterNodeModelImpl.getArchived()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_LCEI_N_A, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LCEI_N_A,
					args);
			}
		}

		entityCache.putResult(LCSClusterNodeModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterNodeImpl.class, lcsClusterNode.getPrimaryKey(),
			lcsClusterNode, false);

		clearUniqueFindersCache(lcsClusterNodeModelImpl, false);
		cacheUniqueFindersCache(lcsClusterNodeModelImpl);

		lcsClusterNode.resetOriginalValues();

		return lcsClusterNode;
	}

	protected LCSClusterNode toUnwrappedModel(LCSClusterNode lcsClusterNode) {
		if (lcsClusterNode instanceof LCSClusterNodeImpl) {
			return lcsClusterNode;
		}

		LCSClusterNodeImpl lcsClusterNodeImpl = new LCSClusterNodeImpl();

		lcsClusterNodeImpl.setNew(lcsClusterNode.isNew());
		lcsClusterNodeImpl.setPrimaryKey(lcsClusterNode.getPrimaryKey());

		lcsClusterNodeImpl.setLcsClusterNodeId(lcsClusterNode.getLcsClusterNodeId());
		lcsClusterNodeImpl.setLcsClusterEntryId(lcsClusterNode.getLcsClusterEntryId());
		lcsClusterNodeImpl.setInstallationId(lcsClusterNode.getInstallationId());
		lcsClusterNodeImpl.setName(lcsClusterNode.getName());
		lcsClusterNodeImpl.setDescription(lcsClusterNode.getDescription());
		lcsClusterNodeImpl.setBuildNumber(lcsClusterNode.getBuildNumber());
		lcsClusterNodeImpl.setKey(lcsClusterNode.getKey());
		lcsClusterNodeImpl.setLocation(lcsClusterNode.getLocation());
		lcsClusterNodeImpl.setProcessorCoresTotal(lcsClusterNode.getProcessorCoresTotal());
		lcsClusterNodeImpl.setArchived(lcsClusterNode.isArchived());
		lcsClusterNodeImpl.setStatus(lcsClusterNode.getStatus());

		return lcsClusterNodeImpl;
	}

	/**
	 * Returns the l c s cluster node with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the l c s cluster node
	 * @return the l c s cluster node
	 * @throws NoSuchLCSClusterNodeException if a l c s cluster node with the primary key could not be found
	 */
	@Override
	public LCSClusterNode findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLCSClusterNodeException {
		LCSClusterNode lcsClusterNode = fetchByPrimaryKey(primaryKey);

		if (lcsClusterNode == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLCSClusterNodeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return lcsClusterNode;
	}

	/**
	 * Returns the l c s cluster node with the primary key or throws a {@link NoSuchLCSClusterNodeException} if it could not be found.
	 *
	 * @param lcsClusterNodeId the primary key of the l c s cluster node
	 * @return the l c s cluster node
	 * @throws NoSuchLCSClusterNodeException if a l c s cluster node with the primary key could not be found
	 */
	@Override
	public LCSClusterNode findByPrimaryKey(long lcsClusterNodeId)
		throws NoSuchLCSClusterNodeException {
		return findByPrimaryKey((Serializable)lcsClusterNodeId);
	}

	/**
	 * Returns the l c s cluster node with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the l c s cluster node
	 * @return the l c s cluster node, or <code>null</code> if a l c s cluster node with the primary key could not be found
	 */
	@Override
	public LCSClusterNode fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(LCSClusterNodeModelImpl.ENTITY_CACHE_ENABLED,
				LCSClusterNodeImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		LCSClusterNode lcsClusterNode = (LCSClusterNode)serializable;

		if (lcsClusterNode == null) {
			Session session = null;

			try {
				session = openSession();

				lcsClusterNode = (LCSClusterNode)session.get(LCSClusterNodeImpl.class,
						primaryKey);

				if (lcsClusterNode != null) {
					cacheResult(lcsClusterNode);
				}
				else {
					entityCache.putResult(LCSClusterNodeModelImpl.ENTITY_CACHE_ENABLED,
						LCSClusterNodeImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(LCSClusterNodeModelImpl.ENTITY_CACHE_ENABLED,
					LCSClusterNodeImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return lcsClusterNode;
	}

	/**
	 * Returns the l c s cluster node with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param lcsClusterNodeId the primary key of the l c s cluster node
	 * @return the l c s cluster node, or <code>null</code> if a l c s cluster node with the primary key could not be found
	 */
	@Override
	public LCSClusterNode fetchByPrimaryKey(long lcsClusterNodeId) {
		return fetchByPrimaryKey((Serializable)lcsClusterNodeId);
	}

	@Override
	public Map<Serializable, LCSClusterNode> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, LCSClusterNode> map = new HashMap<Serializable, LCSClusterNode>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			LCSClusterNode lcsClusterNode = fetchByPrimaryKey(primaryKey);

			if (lcsClusterNode != null) {
				map.put(primaryKey, lcsClusterNode);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(LCSClusterNodeModelImpl.ENTITY_CACHE_ENABLED,
					LCSClusterNodeImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (LCSClusterNode)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_LCSCLUSTERNODE_WHERE_PKS_IN);

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

			for (LCSClusterNode lcsClusterNode : (List<LCSClusterNode>)q.list()) {
				map.put(lcsClusterNode.getPrimaryKeyObj(), lcsClusterNode);

				cacheResult(lcsClusterNode);

				uncachedPrimaryKeys.remove(lcsClusterNode.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(LCSClusterNodeModelImpl.ENTITY_CACHE_ENABLED,
					LCSClusterNodeImpl.class, primaryKey, nullModel);
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
	 * Returns all the l c s cluster nodes.
	 *
	 * @return the l c s cluster nodes
	 */
	@Override
	public List<LCSClusterNode> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the l c s cluster nodes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of l c s cluster nodes
	 * @param end the upper bound of the range of l c s cluster nodes (not inclusive)
	 * @return the range of l c s cluster nodes
	 */
	@Override
	public List<LCSClusterNode> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the l c s cluster nodes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of l c s cluster nodes
	 * @param end the upper bound of the range of l c s cluster nodes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of l c s cluster nodes
	 */
	@Override
	public List<LCSClusterNode> findAll(int start, int end,
		OrderByComparator<LCSClusterNode> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the l c s cluster nodes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of l c s cluster nodes
	 * @param end the upper bound of the range of l c s cluster nodes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of l c s cluster nodes
	 */
	@Override
	public List<LCSClusterNode> findAll(int start, int end,
		OrderByComparator<LCSClusterNode> orderByComparator,
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

		List<LCSClusterNode> list = null;

		if (retrieveFromCache) {
			list = (List<LCSClusterNode>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_LCSCLUSTERNODE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_LCSCLUSTERNODE;

				if (pagination) {
					sql = sql.concat(LCSClusterNodeModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<LCSClusterNode>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSClusterNode>)QueryUtil.list(q,
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
	 * Removes all the l c s cluster nodes from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (LCSClusterNode lcsClusterNode : findAll()) {
			remove(lcsClusterNode);
		}
	}

	/**
	 * Returns the number of l c s cluster nodes.
	 *
	 * @return the number of l c s cluster nodes
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_LCSCLUSTERNODE);

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
		return LCSClusterNodeModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the l c s cluster node persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(LCSClusterNodeImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_LCSCLUSTERNODE = "SELECT lcsClusterNode FROM LCSClusterNode lcsClusterNode";
	private static final String _SQL_SELECT_LCSCLUSTERNODE_WHERE_PKS_IN = "SELECT lcsClusterNode FROM LCSClusterNode lcsClusterNode WHERE lcsClusterNodeId IN (";
	private static final String _SQL_SELECT_LCSCLUSTERNODE_WHERE = "SELECT lcsClusterNode FROM LCSClusterNode lcsClusterNode WHERE ";
	private static final String _SQL_COUNT_LCSCLUSTERNODE = "SELECT COUNT(lcsClusterNode) FROM LCSClusterNode lcsClusterNode";
	private static final String _SQL_COUNT_LCSCLUSTERNODE_WHERE = "SELECT COUNT(lcsClusterNode) FROM LCSClusterNode lcsClusterNode WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "lcsClusterNode.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No LCSClusterNode exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No LCSClusterNode exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(LCSClusterNodePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"key"
			});
}