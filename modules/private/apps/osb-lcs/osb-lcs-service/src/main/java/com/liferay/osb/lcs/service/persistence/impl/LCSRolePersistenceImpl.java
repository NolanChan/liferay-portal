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

import com.liferay.osb.lcs.exception.NoSuchLCSRoleException;
import com.liferay.osb.lcs.model.LCSRole;
import com.liferay.osb.lcs.model.impl.LCSRoleImpl;
import com.liferay.osb.lcs.model.impl.LCSRoleModelImpl;
import com.liferay.osb.lcs.service.persistence.LCSRolePersistence;

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
import java.util.Set;

/**
 * The persistence implementation for the l c s role service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Igor Beslic
 * @see LCSRolePersistence
 * @see com.liferay.osb.lcs.service.persistence.LCSRoleUtil
 * @generated
 */
@ProviderType
public class LCSRolePersistenceImpl extends BasePersistenceImpl<LCSRole>
	implements LCSRolePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link LCSRoleUtil} to access the l c s role persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = LCSRoleImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(LCSRoleModelImpl.ENTITY_CACHE_ENABLED,
			LCSRoleModelImpl.FINDER_CACHE_ENABLED, LCSRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(LCSRoleModelImpl.ENTITY_CACHE_ENABLED,
			LCSRoleModelImpl.FINDER_CACHE_ENABLED, LCSRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(LCSRoleModelImpl.ENTITY_CACHE_ENABLED,
			LCSRoleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(LCSRoleModelImpl.ENTITY_CACHE_ENABLED,
			LCSRoleModelImpl.FINDER_CACHE_ENABLED, LCSRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(LCSRoleModelImpl.ENTITY_CACHE_ENABLED,
			LCSRoleModelImpl.FINDER_CACHE_ENABLED, LCSRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] { Long.class.getName() },
			LCSRoleModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(LCSRoleModelImpl.ENTITY_CACHE_ENABLED,
			LCSRoleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the l c s roles where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching l c s roles
	 */
	@Override
	public List<LCSRole> findByUserId(long userId) {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the l c s roles where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of l c s roles
	 * @param end the upper bound of the range of l c s roles (not inclusive)
	 * @return the range of matching l c s roles
	 */
	@Override
	public List<LCSRole> findByUserId(long userId, int start, int end) {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the l c s roles where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of l c s roles
	 * @param end the upper bound of the range of l c s roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching l c s roles
	 */
	@Override
	public List<LCSRole> findByUserId(long userId, int start, int end,
		OrderByComparator<LCSRole> orderByComparator) {
		return findByUserId(userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the l c s roles where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of l c s roles
	 * @param end the upper bound of the range of l c s roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching l c s roles
	 */
	@Override
	public List<LCSRole> findByUserId(long userId, int start, int end,
		OrderByComparator<LCSRole> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId, start, end, orderByComparator };
		}

		List<LCSRole> list = null;

		if (retrieveFromCache) {
			list = (List<LCSRole>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (LCSRole lcsRole : list) {
					if ((userId != lcsRole.getUserId())) {
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

			query.append(_SQL_SELECT_LCSROLE_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LCSRoleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (!pagination) {
					list = (List<LCSRole>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSRole>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first l c s role in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s role
	 * @throws NoSuchLCSRoleException if a matching l c s role could not be found
	 */
	@Override
	public LCSRole findByUserId_First(long userId,
		OrderByComparator<LCSRole> orderByComparator)
		throws NoSuchLCSRoleException {
		LCSRole lcsRole = fetchByUserId_First(userId, orderByComparator);

		if (lcsRole != null) {
			return lcsRole;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSRoleException(msg.toString());
	}

	/**
	 * Returns the first l c s role in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s role, or <code>null</code> if a matching l c s role could not be found
	 */
	@Override
	public LCSRole fetchByUserId_First(long userId,
		OrderByComparator<LCSRole> orderByComparator) {
		List<LCSRole> list = findByUserId(userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last l c s role in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s role
	 * @throws NoSuchLCSRoleException if a matching l c s role could not be found
	 */
	@Override
	public LCSRole findByUserId_Last(long userId,
		OrderByComparator<LCSRole> orderByComparator)
		throws NoSuchLCSRoleException {
		LCSRole lcsRole = fetchByUserId_Last(userId, orderByComparator);

		if (lcsRole != null) {
			return lcsRole;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSRoleException(msg.toString());
	}

	/**
	 * Returns the last l c s role in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s role, or <code>null</code> if a matching l c s role could not be found
	 */
	@Override
	public LCSRole fetchByUserId_Last(long userId,
		OrderByComparator<LCSRole> orderByComparator) {
		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<LCSRole> list = findByUserId(userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the l c s roles before and after the current l c s role in the ordered set where userId = &#63;.
	 *
	 * @param lcsRoleId the primary key of the current l c s role
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next l c s role
	 * @throws NoSuchLCSRoleException if a l c s role with the primary key could not be found
	 */
	@Override
	public LCSRole[] findByUserId_PrevAndNext(long lcsRoleId, long userId,
		OrderByComparator<LCSRole> orderByComparator)
		throws NoSuchLCSRoleException {
		LCSRole lcsRole = findByPrimaryKey(lcsRoleId);

		Session session = null;

		try {
			session = openSession();

			LCSRole[] array = new LCSRoleImpl[3];

			array[0] = getByUserId_PrevAndNext(session, lcsRole, userId,
					orderByComparator, true);

			array[1] = lcsRole;

			array[2] = getByUserId_PrevAndNext(session, lcsRole, userId,
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

	protected LCSRole getByUserId_PrevAndNext(Session session, LCSRole lcsRole,
		long userId, OrderByComparator<LCSRole> orderByComparator,
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

		query.append(_SQL_SELECT_LCSROLE_WHERE);

		query.append(_FINDER_COLUMN_USERID_USERID_2);

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
			query.append(LCSRoleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(lcsRole);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LCSRole> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the l c s roles where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByUserId(long userId) {
		for (LCSRole lcsRole : findByUserId(userId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(lcsRole);
		}
	}

	/**
	 * Returns the number of l c s roles where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching l c s roles
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_USERID;

		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LCSROLE_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

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

	private static final String _FINDER_COLUMN_USERID_USERID_2 = "lcsRole.userId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_LCSPROJECTID =
		new FinderPath(LCSRoleModelImpl.ENTITY_CACHE_ENABLED,
			LCSRoleModelImpl.FINDER_CACHE_ENABLED, LCSRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByLCSProjectId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LCSPROJECTID =
		new FinderPath(LCSRoleModelImpl.ENTITY_CACHE_ENABLED,
			LCSRoleModelImpl.FINDER_CACHE_ENABLED, LCSRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByLCSProjectId",
			new String[] { Long.class.getName() },
			LCSRoleModelImpl.LCSPROJECTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_LCSPROJECTID = new FinderPath(LCSRoleModelImpl.ENTITY_CACHE_ENABLED,
			LCSRoleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByLCSProjectId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the l c s roles where lcsProjectId = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @return the matching l c s roles
	 */
	@Override
	public List<LCSRole> findByLCSProjectId(long lcsProjectId) {
		return findByLCSProjectId(lcsProjectId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the l c s roles where lcsProjectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param start the lower bound of the range of l c s roles
	 * @param end the upper bound of the range of l c s roles (not inclusive)
	 * @return the range of matching l c s roles
	 */
	@Override
	public List<LCSRole> findByLCSProjectId(long lcsProjectId, int start,
		int end) {
		return findByLCSProjectId(lcsProjectId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the l c s roles where lcsProjectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param start the lower bound of the range of l c s roles
	 * @param end the upper bound of the range of l c s roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching l c s roles
	 */
	@Override
	public List<LCSRole> findByLCSProjectId(long lcsProjectId, int start,
		int end, OrderByComparator<LCSRole> orderByComparator) {
		return findByLCSProjectId(lcsProjectId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the l c s roles where lcsProjectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param start the lower bound of the range of l c s roles
	 * @param end the upper bound of the range of l c s roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching l c s roles
	 */
	@Override
	public List<LCSRole> findByLCSProjectId(long lcsProjectId, int start,
		int end, OrderByComparator<LCSRole> orderByComparator,
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

		List<LCSRole> list = null;

		if (retrieveFromCache) {
			list = (List<LCSRole>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (LCSRole lcsRole : list) {
					if ((lcsProjectId != lcsRole.getLcsProjectId())) {
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

			query.append(_SQL_SELECT_LCSROLE_WHERE);

			query.append(_FINDER_COLUMN_LCSPROJECTID_LCSPROJECTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LCSRoleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(lcsProjectId);

				if (!pagination) {
					list = (List<LCSRole>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSRole>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first l c s role in the ordered set where lcsProjectId = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s role
	 * @throws NoSuchLCSRoleException if a matching l c s role could not be found
	 */
	@Override
	public LCSRole findByLCSProjectId_First(long lcsProjectId,
		OrderByComparator<LCSRole> orderByComparator)
		throws NoSuchLCSRoleException {
		LCSRole lcsRole = fetchByLCSProjectId_First(lcsProjectId,
				orderByComparator);

		if (lcsRole != null) {
			return lcsRole;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("lcsProjectId=");
		msg.append(lcsProjectId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSRoleException(msg.toString());
	}

	/**
	 * Returns the first l c s role in the ordered set where lcsProjectId = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s role, or <code>null</code> if a matching l c s role could not be found
	 */
	@Override
	public LCSRole fetchByLCSProjectId_First(long lcsProjectId,
		OrderByComparator<LCSRole> orderByComparator) {
		List<LCSRole> list = findByLCSProjectId(lcsProjectId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last l c s role in the ordered set where lcsProjectId = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s role
	 * @throws NoSuchLCSRoleException if a matching l c s role could not be found
	 */
	@Override
	public LCSRole findByLCSProjectId_Last(long lcsProjectId,
		OrderByComparator<LCSRole> orderByComparator)
		throws NoSuchLCSRoleException {
		LCSRole lcsRole = fetchByLCSProjectId_Last(lcsProjectId,
				orderByComparator);

		if (lcsRole != null) {
			return lcsRole;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("lcsProjectId=");
		msg.append(lcsProjectId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSRoleException(msg.toString());
	}

	/**
	 * Returns the last l c s role in the ordered set where lcsProjectId = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s role, or <code>null</code> if a matching l c s role could not be found
	 */
	@Override
	public LCSRole fetchByLCSProjectId_Last(long lcsProjectId,
		OrderByComparator<LCSRole> orderByComparator) {
		int count = countByLCSProjectId(lcsProjectId);

		if (count == 0) {
			return null;
		}

		List<LCSRole> list = findByLCSProjectId(lcsProjectId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the l c s roles before and after the current l c s role in the ordered set where lcsProjectId = &#63;.
	 *
	 * @param lcsRoleId the primary key of the current l c s role
	 * @param lcsProjectId the lcs project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next l c s role
	 * @throws NoSuchLCSRoleException if a l c s role with the primary key could not be found
	 */
	@Override
	public LCSRole[] findByLCSProjectId_PrevAndNext(long lcsRoleId,
		long lcsProjectId, OrderByComparator<LCSRole> orderByComparator)
		throws NoSuchLCSRoleException {
		LCSRole lcsRole = findByPrimaryKey(lcsRoleId);

		Session session = null;

		try {
			session = openSession();

			LCSRole[] array = new LCSRoleImpl[3];

			array[0] = getByLCSProjectId_PrevAndNext(session, lcsRole,
					lcsProjectId, orderByComparator, true);

			array[1] = lcsRole;

			array[2] = getByLCSProjectId_PrevAndNext(session, lcsRole,
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

	protected LCSRole getByLCSProjectId_PrevAndNext(Session session,
		LCSRole lcsRole, long lcsProjectId,
		OrderByComparator<LCSRole> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LCSROLE_WHERE);

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
			query.append(LCSRoleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(lcsProjectId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(lcsRole);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LCSRole> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the l c s roles where lcsProjectId = &#63; from the database.
	 *
	 * @param lcsProjectId the lcs project ID
	 */
	@Override
	public void removeByLCSProjectId(long lcsProjectId) {
		for (LCSRole lcsRole : findByLCSProjectId(lcsProjectId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(lcsRole);
		}
	}

	/**
	 * Returns the number of l c s roles where lcsProjectId = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @return the number of matching l c s roles
	 */
	@Override
	public int countByLCSProjectId(long lcsProjectId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_LCSPROJECTID;

		Object[] finderArgs = new Object[] { lcsProjectId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LCSROLE_WHERE);

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

	private static final String _FINDER_COLUMN_LCSPROJECTID_LCSPROJECTID_2 = "lcsRole.lcsProjectId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_LCSCLUSTERENTRYID =
		new FinderPath(LCSRoleModelImpl.ENTITY_CACHE_ENABLED,
			LCSRoleModelImpl.FINDER_CACHE_ENABLED, LCSRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByLCSClusterEntryId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LCSCLUSTERENTRYID =
		new FinderPath(LCSRoleModelImpl.ENTITY_CACHE_ENABLED,
			LCSRoleModelImpl.FINDER_CACHE_ENABLED, LCSRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByLCSClusterEntryId", new String[] { Long.class.getName() },
			LCSRoleModelImpl.LCSCLUSTERENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_LCSCLUSTERENTRYID = new FinderPath(LCSRoleModelImpl.ENTITY_CACHE_ENABLED,
			LCSRoleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByLCSClusterEntryId", new String[] { Long.class.getName() });

	/**
	 * Returns all the l c s roles where lcsClusterEntryId = &#63;.
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @return the matching l c s roles
	 */
	@Override
	public List<LCSRole> findByLCSClusterEntryId(long lcsClusterEntryId) {
		return findByLCSClusterEntryId(lcsClusterEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the l c s roles where lcsClusterEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @param start the lower bound of the range of l c s roles
	 * @param end the upper bound of the range of l c s roles (not inclusive)
	 * @return the range of matching l c s roles
	 */
	@Override
	public List<LCSRole> findByLCSClusterEntryId(long lcsClusterEntryId,
		int start, int end) {
		return findByLCSClusterEntryId(lcsClusterEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the l c s roles where lcsClusterEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @param start the lower bound of the range of l c s roles
	 * @param end the upper bound of the range of l c s roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching l c s roles
	 */
	@Override
	public List<LCSRole> findByLCSClusterEntryId(long lcsClusterEntryId,
		int start, int end, OrderByComparator<LCSRole> orderByComparator) {
		return findByLCSClusterEntryId(lcsClusterEntryId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the l c s roles where lcsClusterEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @param start the lower bound of the range of l c s roles
	 * @param end the upper bound of the range of l c s roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching l c s roles
	 */
	@Override
	public List<LCSRole> findByLCSClusterEntryId(long lcsClusterEntryId,
		int start, int end, OrderByComparator<LCSRole> orderByComparator,
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

		List<LCSRole> list = null;

		if (retrieveFromCache) {
			list = (List<LCSRole>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (LCSRole lcsRole : list) {
					if ((lcsClusterEntryId != lcsRole.getLcsClusterEntryId())) {
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

			query.append(_SQL_SELECT_LCSROLE_WHERE);

			query.append(_FINDER_COLUMN_LCSCLUSTERENTRYID_LCSCLUSTERENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LCSRoleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(lcsClusterEntryId);

				if (!pagination) {
					list = (List<LCSRole>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSRole>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first l c s role in the ordered set where lcsClusterEntryId = &#63;.
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s role
	 * @throws NoSuchLCSRoleException if a matching l c s role could not be found
	 */
	@Override
	public LCSRole findByLCSClusterEntryId_First(long lcsClusterEntryId,
		OrderByComparator<LCSRole> orderByComparator)
		throws NoSuchLCSRoleException {
		LCSRole lcsRole = fetchByLCSClusterEntryId_First(lcsClusterEntryId,
				orderByComparator);

		if (lcsRole != null) {
			return lcsRole;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("lcsClusterEntryId=");
		msg.append(lcsClusterEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSRoleException(msg.toString());
	}

	/**
	 * Returns the first l c s role in the ordered set where lcsClusterEntryId = &#63;.
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s role, or <code>null</code> if a matching l c s role could not be found
	 */
	@Override
	public LCSRole fetchByLCSClusterEntryId_First(long lcsClusterEntryId,
		OrderByComparator<LCSRole> orderByComparator) {
		List<LCSRole> list = findByLCSClusterEntryId(lcsClusterEntryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last l c s role in the ordered set where lcsClusterEntryId = &#63;.
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s role
	 * @throws NoSuchLCSRoleException if a matching l c s role could not be found
	 */
	@Override
	public LCSRole findByLCSClusterEntryId_Last(long lcsClusterEntryId,
		OrderByComparator<LCSRole> orderByComparator)
		throws NoSuchLCSRoleException {
		LCSRole lcsRole = fetchByLCSClusterEntryId_Last(lcsClusterEntryId,
				orderByComparator);

		if (lcsRole != null) {
			return lcsRole;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("lcsClusterEntryId=");
		msg.append(lcsClusterEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSRoleException(msg.toString());
	}

	/**
	 * Returns the last l c s role in the ordered set where lcsClusterEntryId = &#63;.
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s role, or <code>null</code> if a matching l c s role could not be found
	 */
	@Override
	public LCSRole fetchByLCSClusterEntryId_Last(long lcsClusterEntryId,
		OrderByComparator<LCSRole> orderByComparator) {
		int count = countByLCSClusterEntryId(lcsClusterEntryId);

		if (count == 0) {
			return null;
		}

		List<LCSRole> list = findByLCSClusterEntryId(lcsClusterEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the l c s roles before and after the current l c s role in the ordered set where lcsClusterEntryId = &#63;.
	 *
	 * @param lcsRoleId the primary key of the current l c s role
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next l c s role
	 * @throws NoSuchLCSRoleException if a l c s role with the primary key could not be found
	 */
	@Override
	public LCSRole[] findByLCSClusterEntryId_PrevAndNext(long lcsRoleId,
		long lcsClusterEntryId, OrderByComparator<LCSRole> orderByComparator)
		throws NoSuchLCSRoleException {
		LCSRole lcsRole = findByPrimaryKey(lcsRoleId);

		Session session = null;

		try {
			session = openSession();

			LCSRole[] array = new LCSRoleImpl[3];

			array[0] = getByLCSClusterEntryId_PrevAndNext(session, lcsRole,
					lcsClusterEntryId, orderByComparator, true);

			array[1] = lcsRole;

			array[2] = getByLCSClusterEntryId_PrevAndNext(session, lcsRole,
					lcsClusterEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LCSRole getByLCSClusterEntryId_PrevAndNext(Session session,
		LCSRole lcsRole, long lcsClusterEntryId,
		OrderByComparator<LCSRole> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LCSROLE_WHERE);

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
			query.append(LCSRoleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(lcsClusterEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(lcsRole);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LCSRole> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the l c s roles where lcsClusterEntryId = &#63; from the database.
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 */
	@Override
	public void removeByLCSClusterEntryId(long lcsClusterEntryId) {
		for (LCSRole lcsRole : findByLCSClusterEntryId(lcsClusterEntryId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(lcsRole);
		}
	}

	/**
	 * Returns the number of l c s roles where lcsClusterEntryId = &#63;.
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @return the number of matching l c s roles
	 */
	@Override
	public int countByLCSClusterEntryId(long lcsClusterEntryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_LCSCLUSTERENTRYID;

		Object[] finderArgs = new Object[] { lcsClusterEntryId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LCSROLE_WHERE);

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

	private static final String _FINDER_COLUMN_LCSCLUSTERENTRYID_LCSCLUSTERENTRYID_2 =
		"lcsRole.lcsClusterEntryId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_U_LPI = new FinderPath(LCSRoleModelImpl.ENTITY_CACHE_ENABLED,
			LCSRoleModelImpl.FINDER_CACHE_ENABLED, LCSRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByU_LPI",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_LPI = new FinderPath(LCSRoleModelImpl.ENTITY_CACHE_ENABLED,
			LCSRoleModelImpl.FINDER_CACHE_ENABLED, LCSRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByU_LPI",
			new String[] { Long.class.getName(), Long.class.getName() },
			LCSRoleModelImpl.USERID_COLUMN_BITMASK |
			LCSRoleModelImpl.LCSPROJECTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_LPI = new FinderPath(LCSRoleModelImpl.ENTITY_CACHE_ENABLED,
			LCSRoleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_LPI",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the l c s roles where userId = &#63; and lcsProjectId = &#63;.
	 *
	 * @param userId the user ID
	 * @param lcsProjectId the lcs project ID
	 * @return the matching l c s roles
	 */
	@Override
	public List<LCSRole> findByU_LPI(long userId, long lcsProjectId) {
		return findByU_LPI(userId, lcsProjectId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the l c s roles where userId = &#63; and lcsProjectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param lcsProjectId the lcs project ID
	 * @param start the lower bound of the range of l c s roles
	 * @param end the upper bound of the range of l c s roles (not inclusive)
	 * @return the range of matching l c s roles
	 */
	@Override
	public List<LCSRole> findByU_LPI(long userId, long lcsProjectId, int start,
		int end) {
		return findByU_LPI(userId, lcsProjectId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the l c s roles where userId = &#63; and lcsProjectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param lcsProjectId the lcs project ID
	 * @param start the lower bound of the range of l c s roles
	 * @param end the upper bound of the range of l c s roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching l c s roles
	 */
	@Override
	public List<LCSRole> findByU_LPI(long userId, long lcsProjectId, int start,
		int end, OrderByComparator<LCSRole> orderByComparator) {
		return findByU_LPI(userId, lcsProjectId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the l c s roles where userId = &#63; and lcsProjectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param lcsProjectId the lcs project ID
	 * @param start the lower bound of the range of l c s roles
	 * @param end the upper bound of the range of l c s roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching l c s roles
	 */
	@Override
	public List<LCSRole> findByU_LPI(long userId, long lcsProjectId, int start,
		int end, OrderByComparator<LCSRole> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_LPI;
			finderArgs = new Object[] { userId, lcsProjectId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_U_LPI;
			finderArgs = new Object[] {
					userId, lcsProjectId,
					
					start, end, orderByComparator
				};
		}

		List<LCSRole> list = null;

		if (retrieveFromCache) {
			list = (List<LCSRole>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (LCSRole lcsRole : list) {
					if ((userId != lcsRole.getUserId()) ||
							(lcsProjectId != lcsRole.getLcsProjectId())) {
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

			query.append(_SQL_SELECT_LCSROLE_WHERE);

			query.append(_FINDER_COLUMN_U_LPI_USERID_2);

			query.append(_FINDER_COLUMN_U_LPI_LCSPROJECTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LCSRoleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(lcsProjectId);

				if (!pagination) {
					list = (List<LCSRole>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSRole>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first l c s role in the ordered set where userId = &#63; and lcsProjectId = &#63;.
	 *
	 * @param userId the user ID
	 * @param lcsProjectId the lcs project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s role
	 * @throws NoSuchLCSRoleException if a matching l c s role could not be found
	 */
	@Override
	public LCSRole findByU_LPI_First(long userId, long lcsProjectId,
		OrderByComparator<LCSRole> orderByComparator)
		throws NoSuchLCSRoleException {
		LCSRole lcsRole = fetchByU_LPI_First(userId, lcsProjectId,
				orderByComparator);

		if (lcsRole != null) {
			return lcsRole;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", lcsProjectId=");
		msg.append(lcsProjectId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSRoleException(msg.toString());
	}

	/**
	 * Returns the first l c s role in the ordered set where userId = &#63; and lcsProjectId = &#63;.
	 *
	 * @param userId the user ID
	 * @param lcsProjectId the lcs project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s role, or <code>null</code> if a matching l c s role could not be found
	 */
	@Override
	public LCSRole fetchByU_LPI_First(long userId, long lcsProjectId,
		OrderByComparator<LCSRole> orderByComparator) {
		List<LCSRole> list = findByU_LPI(userId, lcsProjectId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last l c s role in the ordered set where userId = &#63; and lcsProjectId = &#63;.
	 *
	 * @param userId the user ID
	 * @param lcsProjectId the lcs project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s role
	 * @throws NoSuchLCSRoleException if a matching l c s role could not be found
	 */
	@Override
	public LCSRole findByU_LPI_Last(long userId, long lcsProjectId,
		OrderByComparator<LCSRole> orderByComparator)
		throws NoSuchLCSRoleException {
		LCSRole lcsRole = fetchByU_LPI_Last(userId, lcsProjectId,
				orderByComparator);

		if (lcsRole != null) {
			return lcsRole;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", lcsProjectId=");
		msg.append(lcsProjectId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSRoleException(msg.toString());
	}

	/**
	 * Returns the last l c s role in the ordered set where userId = &#63; and lcsProjectId = &#63;.
	 *
	 * @param userId the user ID
	 * @param lcsProjectId the lcs project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s role, or <code>null</code> if a matching l c s role could not be found
	 */
	@Override
	public LCSRole fetchByU_LPI_Last(long userId, long lcsProjectId,
		OrderByComparator<LCSRole> orderByComparator) {
		int count = countByU_LPI(userId, lcsProjectId);

		if (count == 0) {
			return null;
		}

		List<LCSRole> list = findByU_LPI(userId, lcsProjectId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the l c s roles before and after the current l c s role in the ordered set where userId = &#63; and lcsProjectId = &#63;.
	 *
	 * @param lcsRoleId the primary key of the current l c s role
	 * @param userId the user ID
	 * @param lcsProjectId the lcs project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next l c s role
	 * @throws NoSuchLCSRoleException if a l c s role with the primary key could not be found
	 */
	@Override
	public LCSRole[] findByU_LPI_PrevAndNext(long lcsRoleId, long userId,
		long lcsProjectId, OrderByComparator<LCSRole> orderByComparator)
		throws NoSuchLCSRoleException {
		LCSRole lcsRole = findByPrimaryKey(lcsRoleId);

		Session session = null;

		try {
			session = openSession();

			LCSRole[] array = new LCSRoleImpl[3];

			array[0] = getByU_LPI_PrevAndNext(session, lcsRole, userId,
					lcsProjectId, orderByComparator, true);

			array[1] = lcsRole;

			array[2] = getByU_LPI_PrevAndNext(session, lcsRole, userId,
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

	protected LCSRole getByU_LPI_PrevAndNext(Session session, LCSRole lcsRole,
		long userId, long lcsProjectId,
		OrderByComparator<LCSRole> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_LCSROLE_WHERE);

		query.append(_FINDER_COLUMN_U_LPI_USERID_2);

		query.append(_FINDER_COLUMN_U_LPI_LCSPROJECTID_2);

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
			query.append(LCSRoleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		qPos.add(lcsProjectId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(lcsRole);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LCSRole> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the l c s roles where userId = &#63; and lcsProjectId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param lcsProjectId the lcs project ID
	 */
	@Override
	public void removeByU_LPI(long userId, long lcsProjectId) {
		for (LCSRole lcsRole : findByU_LPI(userId, lcsProjectId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(lcsRole);
		}
	}

	/**
	 * Returns the number of l c s roles where userId = &#63; and lcsProjectId = &#63;.
	 *
	 * @param userId the user ID
	 * @param lcsProjectId the lcs project ID
	 * @return the number of matching l c s roles
	 */
	@Override
	public int countByU_LPI(long userId, long lcsProjectId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_U_LPI;

		Object[] finderArgs = new Object[] { userId, lcsProjectId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LCSROLE_WHERE);

			query.append(_FINDER_COLUMN_U_LPI_USERID_2);

			query.append(_FINDER_COLUMN_U_LPI_LCSPROJECTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

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

	private static final String _FINDER_COLUMN_U_LPI_USERID_2 = "lcsRole.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_LPI_LCSPROJECTID_2 = "lcsRole.lcsProjectId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_U_R = new FinderPath(LCSRoleModelImpl.ENTITY_CACHE_ENABLED,
			LCSRoleModelImpl.FINDER_CACHE_ENABLED, LCSRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByU_R",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_R = new FinderPath(LCSRoleModelImpl.ENTITY_CACHE_ENABLED,
			LCSRoleModelImpl.FINDER_CACHE_ENABLED, LCSRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByU_R",
			new String[] { Long.class.getName(), Integer.class.getName() },
			LCSRoleModelImpl.USERID_COLUMN_BITMASK |
			LCSRoleModelImpl.ROLE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_R = new FinderPath(LCSRoleModelImpl.ENTITY_CACHE_ENABLED,
			LCSRoleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_R",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the l c s roles where userId = &#63; and role = &#63;.
	 *
	 * @param userId the user ID
	 * @param role the role
	 * @return the matching l c s roles
	 */
	@Override
	public List<LCSRole> findByU_R(long userId, int role) {
		return findByU_R(userId, role, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the l c s roles where userId = &#63; and role = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param role the role
	 * @param start the lower bound of the range of l c s roles
	 * @param end the upper bound of the range of l c s roles (not inclusive)
	 * @return the range of matching l c s roles
	 */
	@Override
	public List<LCSRole> findByU_R(long userId, int role, int start, int end) {
		return findByU_R(userId, role, start, end, null);
	}

	/**
	 * Returns an ordered range of all the l c s roles where userId = &#63; and role = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param role the role
	 * @param start the lower bound of the range of l c s roles
	 * @param end the upper bound of the range of l c s roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching l c s roles
	 */
	@Override
	public List<LCSRole> findByU_R(long userId, int role, int start, int end,
		OrderByComparator<LCSRole> orderByComparator) {
		return findByU_R(userId, role, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the l c s roles where userId = &#63; and role = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param role the role
	 * @param start the lower bound of the range of l c s roles
	 * @param end the upper bound of the range of l c s roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching l c s roles
	 */
	@Override
	public List<LCSRole> findByU_R(long userId, int role, int start, int end,
		OrderByComparator<LCSRole> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_R;
			finderArgs = new Object[] { userId, role };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_U_R;
			finderArgs = new Object[] {
					userId, role,
					
					start, end, orderByComparator
				};
		}

		List<LCSRole> list = null;

		if (retrieveFromCache) {
			list = (List<LCSRole>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (LCSRole lcsRole : list) {
					if ((userId != lcsRole.getUserId()) ||
							(role != lcsRole.getRole())) {
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

			query.append(_SQL_SELECT_LCSROLE_WHERE);

			query.append(_FINDER_COLUMN_U_R_USERID_2);

			query.append(_FINDER_COLUMN_U_R_ROLE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LCSRoleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(role);

				if (!pagination) {
					list = (List<LCSRole>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSRole>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first l c s role in the ordered set where userId = &#63; and role = &#63;.
	 *
	 * @param userId the user ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s role
	 * @throws NoSuchLCSRoleException if a matching l c s role could not be found
	 */
	@Override
	public LCSRole findByU_R_First(long userId, int role,
		OrderByComparator<LCSRole> orderByComparator)
		throws NoSuchLCSRoleException {
		LCSRole lcsRole = fetchByU_R_First(userId, role, orderByComparator);

		if (lcsRole != null) {
			return lcsRole;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", role=");
		msg.append(role);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSRoleException(msg.toString());
	}

	/**
	 * Returns the first l c s role in the ordered set where userId = &#63; and role = &#63;.
	 *
	 * @param userId the user ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s role, or <code>null</code> if a matching l c s role could not be found
	 */
	@Override
	public LCSRole fetchByU_R_First(long userId, int role,
		OrderByComparator<LCSRole> orderByComparator) {
		List<LCSRole> list = findByU_R(userId, role, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last l c s role in the ordered set where userId = &#63; and role = &#63;.
	 *
	 * @param userId the user ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s role
	 * @throws NoSuchLCSRoleException if a matching l c s role could not be found
	 */
	@Override
	public LCSRole findByU_R_Last(long userId, int role,
		OrderByComparator<LCSRole> orderByComparator)
		throws NoSuchLCSRoleException {
		LCSRole lcsRole = fetchByU_R_Last(userId, role, orderByComparator);

		if (lcsRole != null) {
			return lcsRole;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", role=");
		msg.append(role);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSRoleException(msg.toString());
	}

	/**
	 * Returns the last l c s role in the ordered set where userId = &#63; and role = &#63;.
	 *
	 * @param userId the user ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s role, or <code>null</code> if a matching l c s role could not be found
	 */
	@Override
	public LCSRole fetchByU_R_Last(long userId, int role,
		OrderByComparator<LCSRole> orderByComparator) {
		int count = countByU_R(userId, role);

		if (count == 0) {
			return null;
		}

		List<LCSRole> list = findByU_R(userId, role, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the l c s roles before and after the current l c s role in the ordered set where userId = &#63; and role = &#63;.
	 *
	 * @param lcsRoleId the primary key of the current l c s role
	 * @param userId the user ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next l c s role
	 * @throws NoSuchLCSRoleException if a l c s role with the primary key could not be found
	 */
	@Override
	public LCSRole[] findByU_R_PrevAndNext(long lcsRoleId, long userId,
		int role, OrderByComparator<LCSRole> orderByComparator)
		throws NoSuchLCSRoleException {
		LCSRole lcsRole = findByPrimaryKey(lcsRoleId);

		Session session = null;

		try {
			session = openSession();

			LCSRole[] array = new LCSRoleImpl[3];

			array[0] = getByU_R_PrevAndNext(session, lcsRole, userId, role,
					orderByComparator, true);

			array[1] = lcsRole;

			array[2] = getByU_R_PrevAndNext(session, lcsRole, userId, role,
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

	protected LCSRole getByU_R_PrevAndNext(Session session, LCSRole lcsRole,
		long userId, int role, OrderByComparator<LCSRole> orderByComparator,
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

		query.append(_SQL_SELECT_LCSROLE_WHERE);

		query.append(_FINDER_COLUMN_U_R_USERID_2);

		query.append(_FINDER_COLUMN_U_R_ROLE_2);

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
			query.append(LCSRoleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		qPos.add(role);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(lcsRole);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LCSRole> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the l c s roles where userId = &#63; and role = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param role the role
	 */
	@Override
	public void removeByU_R(long userId, int role) {
		for (LCSRole lcsRole : findByU_R(userId, role, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(lcsRole);
		}
	}

	/**
	 * Returns the number of l c s roles where userId = &#63; and role = &#63;.
	 *
	 * @param userId the user ID
	 * @param role the role
	 * @return the number of matching l c s roles
	 */
	@Override
	public int countByU_R(long userId, int role) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_U_R;

		Object[] finderArgs = new Object[] { userId, role };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LCSROLE_WHERE);

			query.append(_FINDER_COLUMN_U_R_USERID_2);

			query.append(_FINDER_COLUMN_U_R_ROLE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(role);

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

	private static final String _FINDER_COLUMN_U_R_USERID_2 = "lcsRole.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_R_ROLE_2 = "lcsRole.role = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_LPI_R = new FinderPath(LCSRoleModelImpl.ENTITY_CACHE_ENABLED,
			LCSRoleModelImpl.FINDER_CACHE_ENABLED, LCSRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByLPI_R",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LPI_R = new FinderPath(LCSRoleModelImpl.ENTITY_CACHE_ENABLED,
			LCSRoleModelImpl.FINDER_CACHE_ENABLED, LCSRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByLPI_R",
			new String[] { Long.class.getName(), Integer.class.getName() },
			LCSRoleModelImpl.LCSPROJECTID_COLUMN_BITMASK |
			LCSRoleModelImpl.ROLE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_LPI_R = new FinderPath(LCSRoleModelImpl.ENTITY_CACHE_ENABLED,
			LCSRoleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByLPI_R",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the l c s roles where lcsProjectId = &#63; and role = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param role the role
	 * @return the matching l c s roles
	 */
	@Override
	public List<LCSRole> findByLPI_R(long lcsProjectId, int role) {
		return findByLPI_R(lcsProjectId, role, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the l c s roles where lcsProjectId = &#63; and role = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param role the role
	 * @param start the lower bound of the range of l c s roles
	 * @param end the upper bound of the range of l c s roles (not inclusive)
	 * @return the range of matching l c s roles
	 */
	@Override
	public List<LCSRole> findByLPI_R(long lcsProjectId, int role, int start,
		int end) {
		return findByLPI_R(lcsProjectId, role, start, end, null);
	}

	/**
	 * Returns an ordered range of all the l c s roles where lcsProjectId = &#63; and role = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param role the role
	 * @param start the lower bound of the range of l c s roles
	 * @param end the upper bound of the range of l c s roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching l c s roles
	 */
	@Override
	public List<LCSRole> findByLPI_R(long lcsProjectId, int role, int start,
		int end, OrderByComparator<LCSRole> orderByComparator) {
		return findByLPI_R(lcsProjectId, role, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the l c s roles where lcsProjectId = &#63; and role = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param role the role
	 * @param start the lower bound of the range of l c s roles
	 * @param end the upper bound of the range of l c s roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching l c s roles
	 */
	@Override
	public List<LCSRole> findByLPI_R(long lcsProjectId, int role, int start,
		int end, OrderByComparator<LCSRole> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LPI_R;
			finderArgs = new Object[] { lcsProjectId, role };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_LPI_R;
			finderArgs = new Object[] {
					lcsProjectId, role,
					
					start, end, orderByComparator
				};
		}

		List<LCSRole> list = null;

		if (retrieveFromCache) {
			list = (List<LCSRole>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (LCSRole lcsRole : list) {
					if ((lcsProjectId != lcsRole.getLcsProjectId()) ||
							(role != lcsRole.getRole())) {
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

			query.append(_SQL_SELECT_LCSROLE_WHERE);

			query.append(_FINDER_COLUMN_LPI_R_LCSPROJECTID_2);

			query.append(_FINDER_COLUMN_LPI_R_ROLE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LCSRoleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(lcsProjectId);

				qPos.add(role);

				if (!pagination) {
					list = (List<LCSRole>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSRole>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first l c s role in the ordered set where lcsProjectId = &#63; and role = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s role
	 * @throws NoSuchLCSRoleException if a matching l c s role could not be found
	 */
	@Override
	public LCSRole findByLPI_R_First(long lcsProjectId, int role,
		OrderByComparator<LCSRole> orderByComparator)
		throws NoSuchLCSRoleException {
		LCSRole lcsRole = fetchByLPI_R_First(lcsProjectId, role,
				orderByComparator);

		if (lcsRole != null) {
			return lcsRole;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("lcsProjectId=");
		msg.append(lcsProjectId);

		msg.append(", role=");
		msg.append(role);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSRoleException(msg.toString());
	}

	/**
	 * Returns the first l c s role in the ordered set where lcsProjectId = &#63; and role = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s role, or <code>null</code> if a matching l c s role could not be found
	 */
	@Override
	public LCSRole fetchByLPI_R_First(long lcsProjectId, int role,
		OrderByComparator<LCSRole> orderByComparator) {
		List<LCSRole> list = findByLPI_R(lcsProjectId, role, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last l c s role in the ordered set where lcsProjectId = &#63; and role = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s role
	 * @throws NoSuchLCSRoleException if a matching l c s role could not be found
	 */
	@Override
	public LCSRole findByLPI_R_Last(long lcsProjectId, int role,
		OrderByComparator<LCSRole> orderByComparator)
		throws NoSuchLCSRoleException {
		LCSRole lcsRole = fetchByLPI_R_Last(lcsProjectId, role,
				orderByComparator);

		if (lcsRole != null) {
			return lcsRole;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("lcsProjectId=");
		msg.append(lcsProjectId);

		msg.append(", role=");
		msg.append(role);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSRoleException(msg.toString());
	}

	/**
	 * Returns the last l c s role in the ordered set where lcsProjectId = &#63; and role = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s role, or <code>null</code> if a matching l c s role could not be found
	 */
	@Override
	public LCSRole fetchByLPI_R_Last(long lcsProjectId, int role,
		OrderByComparator<LCSRole> orderByComparator) {
		int count = countByLPI_R(lcsProjectId, role);

		if (count == 0) {
			return null;
		}

		List<LCSRole> list = findByLPI_R(lcsProjectId, role, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the l c s roles before and after the current l c s role in the ordered set where lcsProjectId = &#63; and role = &#63;.
	 *
	 * @param lcsRoleId the primary key of the current l c s role
	 * @param lcsProjectId the lcs project ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next l c s role
	 * @throws NoSuchLCSRoleException if a l c s role with the primary key could not be found
	 */
	@Override
	public LCSRole[] findByLPI_R_PrevAndNext(long lcsRoleId, long lcsProjectId,
		int role, OrderByComparator<LCSRole> orderByComparator)
		throws NoSuchLCSRoleException {
		LCSRole lcsRole = findByPrimaryKey(lcsRoleId);

		Session session = null;

		try {
			session = openSession();

			LCSRole[] array = new LCSRoleImpl[3];

			array[0] = getByLPI_R_PrevAndNext(session, lcsRole, lcsProjectId,
					role, orderByComparator, true);

			array[1] = lcsRole;

			array[2] = getByLPI_R_PrevAndNext(session, lcsRole, lcsProjectId,
					role, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LCSRole getByLPI_R_PrevAndNext(Session session, LCSRole lcsRole,
		long lcsProjectId, int role,
		OrderByComparator<LCSRole> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_LCSROLE_WHERE);

		query.append(_FINDER_COLUMN_LPI_R_LCSPROJECTID_2);

		query.append(_FINDER_COLUMN_LPI_R_ROLE_2);

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
			query.append(LCSRoleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(lcsProjectId);

		qPos.add(role);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(lcsRole);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LCSRole> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the l c s roles where lcsProjectId = &#63; and role = &#63; from the database.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param role the role
	 */
	@Override
	public void removeByLPI_R(long lcsProjectId, int role) {
		for (LCSRole lcsRole : findByLPI_R(lcsProjectId, role,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(lcsRole);
		}
	}

	/**
	 * Returns the number of l c s roles where lcsProjectId = &#63; and role = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param role the role
	 * @return the number of matching l c s roles
	 */
	@Override
	public int countByLPI_R(long lcsProjectId, int role) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_LPI_R;

		Object[] finderArgs = new Object[] { lcsProjectId, role };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LCSROLE_WHERE);

			query.append(_FINDER_COLUMN_LPI_R_LCSPROJECTID_2);

			query.append(_FINDER_COLUMN_LPI_R_ROLE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(lcsProjectId);

				qPos.add(role);

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

	private static final String _FINDER_COLUMN_LPI_R_LCSPROJECTID_2 = "lcsRole.lcsProjectId = ? AND ";
	private static final String _FINDER_COLUMN_LPI_R_ROLE_2 = "lcsRole.role = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_U_LPI_LCEI = new FinderPath(LCSRoleModelImpl.ENTITY_CACHE_ENABLED,
			LCSRoleModelImpl.FINDER_CACHE_ENABLED, LCSRoleImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByU_LPI_LCEI",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			LCSRoleModelImpl.USERID_COLUMN_BITMASK |
			LCSRoleModelImpl.LCSPROJECTID_COLUMN_BITMASK |
			LCSRoleModelImpl.LCSCLUSTERENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_LPI_LCEI = new FinderPath(LCSRoleModelImpl.ENTITY_CACHE_ENABLED,
			LCSRoleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_LPI_LCEI",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});

	/**
	 * Returns the l c s role where userId = &#63; and lcsProjectId = &#63; and lcsClusterEntryId = &#63; or throws a {@link NoSuchLCSRoleException} if it could not be found.
	 *
	 * @param userId the user ID
	 * @param lcsProjectId the lcs project ID
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @return the matching l c s role
	 * @throws NoSuchLCSRoleException if a matching l c s role could not be found
	 */
	@Override
	public LCSRole findByU_LPI_LCEI(long userId, long lcsProjectId,
		long lcsClusterEntryId) throws NoSuchLCSRoleException {
		LCSRole lcsRole = fetchByU_LPI_LCEI(userId, lcsProjectId,
				lcsClusterEntryId);

		if (lcsRole == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(", lcsProjectId=");
			msg.append(lcsProjectId);

			msg.append(", lcsClusterEntryId=");
			msg.append(lcsClusterEntryId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchLCSRoleException(msg.toString());
		}

		return lcsRole;
	}

	/**
	 * Returns the l c s role where userId = &#63; and lcsProjectId = &#63; and lcsClusterEntryId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @param lcsProjectId the lcs project ID
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @return the matching l c s role, or <code>null</code> if a matching l c s role could not be found
	 */
	@Override
	public LCSRole fetchByU_LPI_LCEI(long userId, long lcsProjectId,
		long lcsClusterEntryId) {
		return fetchByU_LPI_LCEI(userId, lcsProjectId, lcsClusterEntryId, true);
	}

	/**
	 * Returns the l c s role where userId = &#63; and lcsProjectId = &#63; and lcsClusterEntryId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param lcsProjectId the lcs project ID
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching l c s role, or <code>null</code> if a matching l c s role could not be found
	 */
	@Override
	public LCSRole fetchByU_LPI_LCEI(long userId, long lcsProjectId,
		long lcsClusterEntryId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] {
				userId, lcsProjectId, lcsClusterEntryId
			};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_U_LPI_LCEI,
					finderArgs, this);
		}

		if (result instanceof LCSRole) {
			LCSRole lcsRole = (LCSRole)result;

			if ((userId != lcsRole.getUserId()) ||
					(lcsProjectId != lcsRole.getLcsProjectId()) ||
					(lcsClusterEntryId != lcsRole.getLcsClusterEntryId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_LCSROLE_WHERE);

			query.append(_FINDER_COLUMN_U_LPI_LCEI_USERID_2);

			query.append(_FINDER_COLUMN_U_LPI_LCEI_LCSPROJECTID_2);

			query.append(_FINDER_COLUMN_U_LPI_LCEI_LCSCLUSTERENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(lcsProjectId);

				qPos.add(lcsClusterEntryId);

				List<LCSRole> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_U_LPI_LCEI,
						finderArgs, list);
				}
				else {
					LCSRole lcsRole = list.get(0);

					result = lcsRole;

					cacheResult(lcsRole);

					if ((lcsRole.getUserId() != userId) ||
							(lcsRole.getLcsProjectId() != lcsProjectId) ||
							(lcsRole.getLcsClusterEntryId() != lcsClusterEntryId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_U_LPI_LCEI,
							finderArgs, lcsRole);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_U_LPI_LCEI,
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
			return (LCSRole)result;
		}
	}

	/**
	 * Removes the l c s role where userId = &#63; and lcsProjectId = &#63; and lcsClusterEntryId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param lcsProjectId the lcs project ID
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @return the l c s role that was removed
	 */
	@Override
	public LCSRole removeByU_LPI_LCEI(long userId, long lcsProjectId,
		long lcsClusterEntryId) throws NoSuchLCSRoleException {
		LCSRole lcsRole = findByU_LPI_LCEI(userId, lcsProjectId,
				lcsClusterEntryId);

		return remove(lcsRole);
	}

	/**
	 * Returns the number of l c s roles where userId = &#63; and lcsProjectId = &#63; and lcsClusterEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param lcsProjectId the lcs project ID
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @return the number of matching l c s roles
	 */
	@Override
	public int countByU_LPI_LCEI(long userId, long lcsProjectId,
		long lcsClusterEntryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_U_LPI_LCEI;

		Object[] finderArgs = new Object[] {
				userId, lcsProjectId, lcsClusterEntryId
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_LCSROLE_WHERE);

			query.append(_FINDER_COLUMN_U_LPI_LCEI_USERID_2);

			query.append(_FINDER_COLUMN_U_LPI_LCEI_LCSPROJECTID_2);

			query.append(_FINDER_COLUMN_U_LPI_LCEI_LCSCLUSTERENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(lcsProjectId);

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

	private static final String _FINDER_COLUMN_U_LPI_LCEI_USERID_2 = "lcsRole.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_LPI_LCEI_LCSPROJECTID_2 = "lcsRole.lcsProjectId = ? AND ";
	private static final String _FINDER_COLUMN_U_LPI_LCEI_LCSCLUSTERENTRYID_2 = "lcsRole.lcsClusterEntryId = ?";

	public LCSRolePersistenceImpl() {
		setModelClass(LCSRole.class);
	}

	/**
	 * Caches the l c s role in the entity cache if it is enabled.
	 *
	 * @param lcsRole the l c s role
	 */
	@Override
	public void cacheResult(LCSRole lcsRole) {
		entityCache.putResult(LCSRoleModelImpl.ENTITY_CACHE_ENABLED,
			LCSRoleImpl.class, lcsRole.getPrimaryKey(), lcsRole);

		finderCache.putResult(FINDER_PATH_FETCH_BY_U_LPI_LCEI,
			new Object[] {
				lcsRole.getUserId(), lcsRole.getLcsProjectId(),
				lcsRole.getLcsClusterEntryId()
			}, lcsRole);

		lcsRole.resetOriginalValues();
	}

	/**
	 * Caches the l c s roles in the entity cache if it is enabled.
	 *
	 * @param lcsRoles the l c s roles
	 */
	@Override
	public void cacheResult(List<LCSRole> lcsRoles) {
		for (LCSRole lcsRole : lcsRoles) {
			if (entityCache.getResult(LCSRoleModelImpl.ENTITY_CACHE_ENABLED,
						LCSRoleImpl.class, lcsRole.getPrimaryKey()) == null) {
				cacheResult(lcsRole);
			}
			else {
				lcsRole.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all l c s roles.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(LCSRoleImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the l c s role.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LCSRole lcsRole) {
		entityCache.removeResult(LCSRoleModelImpl.ENTITY_CACHE_ENABLED,
			LCSRoleImpl.class, lcsRole.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((LCSRoleModelImpl)lcsRole, true);
	}

	@Override
	public void clearCache(List<LCSRole> lcsRoles) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (LCSRole lcsRole : lcsRoles) {
			entityCache.removeResult(LCSRoleModelImpl.ENTITY_CACHE_ENABLED,
				LCSRoleImpl.class, lcsRole.getPrimaryKey());

			clearUniqueFindersCache((LCSRoleModelImpl)lcsRole, true);
		}
	}

	protected void cacheUniqueFindersCache(LCSRoleModelImpl lcsRoleModelImpl) {
		Object[] args = new Object[] {
				lcsRoleModelImpl.getUserId(), lcsRoleModelImpl.getLcsProjectId(),
				lcsRoleModelImpl.getLcsClusterEntryId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_U_LPI_LCEI, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_U_LPI_LCEI, args,
			lcsRoleModelImpl, false);
	}

	protected void clearUniqueFindersCache(LCSRoleModelImpl lcsRoleModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					lcsRoleModelImpl.getUserId(),
					lcsRoleModelImpl.getLcsProjectId(),
					lcsRoleModelImpl.getLcsClusterEntryId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_U_LPI_LCEI, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_U_LPI_LCEI, args);
		}

		if ((lcsRoleModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_U_LPI_LCEI.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					lcsRoleModelImpl.getOriginalUserId(),
					lcsRoleModelImpl.getOriginalLcsProjectId(),
					lcsRoleModelImpl.getOriginalLcsClusterEntryId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_U_LPI_LCEI, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_U_LPI_LCEI, args);
		}
	}

	/**
	 * Creates a new l c s role with the primary key. Does not add the l c s role to the database.
	 *
	 * @param lcsRoleId the primary key for the new l c s role
	 * @return the new l c s role
	 */
	@Override
	public LCSRole create(long lcsRoleId) {
		LCSRole lcsRole = new LCSRoleImpl();

		lcsRole.setNew(true);
		lcsRole.setPrimaryKey(lcsRoleId);

		return lcsRole;
	}

	/**
	 * Removes the l c s role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param lcsRoleId the primary key of the l c s role
	 * @return the l c s role that was removed
	 * @throws NoSuchLCSRoleException if a l c s role with the primary key could not be found
	 */
	@Override
	public LCSRole remove(long lcsRoleId) throws NoSuchLCSRoleException {
		return remove((Serializable)lcsRoleId);
	}

	/**
	 * Removes the l c s role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the l c s role
	 * @return the l c s role that was removed
	 * @throws NoSuchLCSRoleException if a l c s role with the primary key could not be found
	 */
	@Override
	public LCSRole remove(Serializable primaryKey)
		throws NoSuchLCSRoleException {
		Session session = null;

		try {
			session = openSession();

			LCSRole lcsRole = (LCSRole)session.get(LCSRoleImpl.class, primaryKey);

			if (lcsRole == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLCSRoleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(lcsRole);
		}
		catch (NoSuchLCSRoleException nsee) {
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
	protected LCSRole removeImpl(LCSRole lcsRole) {
		lcsRole = toUnwrappedModel(lcsRole);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(lcsRole)) {
				lcsRole = (LCSRole)session.get(LCSRoleImpl.class,
						lcsRole.getPrimaryKeyObj());
			}

			if (lcsRole != null) {
				session.delete(lcsRole);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (lcsRole != null) {
			clearCache(lcsRole);
		}

		return lcsRole;
	}

	@Override
	public LCSRole updateImpl(LCSRole lcsRole) {
		lcsRole = toUnwrappedModel(lcsRole);

		boolean isNew = lcsRole.isNew();

		LCSRoleModelImpl lcsRoleModelImpl = (LCSRoleModelImpl)lcsRole;

		Session session = null;

		try {
			session = openSession();

			if (lcsRole.isNew()) {
				session.save(lcsRole);

				lcsRole.setNew(false);
			}
			else {
				lcsRole = (LCSRole)session.merge(lcsRole);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !LCSRoleModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((lcsRoleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						lcsRoleModelImpl.getOriginalUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] { lcsRoleModelImpl.getUserId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}

			if ((lcsRoleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LCSPROJECTID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						lcsRoleModelImpl.getOriginalLcsProjectId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_LCSPROJECTID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LCSPROJECTID,
					args);

				args = new Object[] { lcsRoleModelImpl.getLcsProjectId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_LCSPROJECTID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LCSPROJECTID,
					args);
			}

			if ((lcsRoleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LCSCLUSTERENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						lcsRoleModelImpl.getOriginalLcsClusterEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_LCSCLUSTERENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LCSCLUSTERENTRYID,
					args);

				args = new Object[] { lcsRoleModelImpl.getLcsClusterEntryId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_LCSCLUSTERENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LCSCLUSTERENTRYID,
					args);
			}

			if ((lcsRoleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_LPI.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						lcsRoleModelImpl.getOriginalUserId(),
						lcsRoleModelImpl.getOriginalLcsProjectId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_U_LPI, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_LPI,
					args);

				args = new Object[] {
						lcsRoleModelImpl.getUserId(),
						lcsRoleModelImpl.getLcsProjectId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_U_LPI, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_LPI,
					args);
			}

			if ((lcsRoleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_R.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						lcsRoleModelImpl.getOriginalUserId(),
						lcsRoleModelImpl.getOriginalRole()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_U_R, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_R,
					args);

				args = new Object[] {
						lcsRoleModelImpl.getUserId(), lcsRoleModelImpl.getRole()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_U_R, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_R,
					args);
			}

			if ((lcsRoleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LPI_R.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						lcsRoleModelImpl.getOriginalLcsProjectId(),
						lcsRoleModelImpl.getOriginalRole()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_LPI_R, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LPI_R,
					args);

				args = new Object[] {
						lcsRoleModelImpl.getLcsProjectId(),
						lcsRoleModelImpl.getRole()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_LPI_R, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LPI_R,
					args);
			}
		}

		entityCache.putResult(LCSRoleModelImpl.ENTITY_CACHE_ENABLED,
			LCSRoleImpl.class, lcsRole.getPrimaryKey(), lcsRole, false);

		clearUniqueFindersCache(lcsRoleModelImpl, false);
		cacheUniqueFindersCache(lcsRoleModelImpl);

		lcsRole.resetOriginalValues();

		return lcsRole;
	}

	protected LCSRole toUnwrappedModel(LCSRole lcsRole) {
		if (lcsRole instanceof LCSRoleImpl) {
			return lcsRole;
		}

		LCSRoleImpl lcsRoleImpl = new LCSRoleImpl();

		lcsRoleImpl.setNew(lcsRole.isNew());
		lcsRoleImpl.setPrimaryKey(lcsRole.getPrimaryKey());

		lcsRoleImpl.setLcsRoleId(lcsRole.getLcsRoleId());
		lcsRoleImpl.setUserId(lcsRole.getUserId());
		lcsRoleImpl.setLcsProjectId(lcsRole.getLcsProjectId());
		lcsRoleImpl.setLcsClusterEntryId(lcsRole.getLcsClusterEntryId());
		lcsRoleImpl.setRole(lcsRole.getRole());

		return lcsRoleImpl;
	}

	/**
	 * Returns the l c s role with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the l c s role
	 * @return the l c s role
	 * @throws NoSuchLCSRoleException if a l c s role with the primary key could not be found
	 */
	@Override
	public LCSRole findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLCSRoleException {
		LCSRole lcsRole = fetchByPrimaryKey(primaryKey);

		if (lcsRole == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLCSRoleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return lcsRole;
	}

	/**
	 * Returns the l c s role with the primary key or throws a {@link NoSuchLCSRoleException} if it could not be found.
	 *
	 * @param lcsRoleId the primary key of the l c s role
	 * @return the l c s role
	 * @throws NoSuchLCSRoleException if a l c s role with the primary key could not be found
	 */
	@Override
	public LCSRole findByPrimaryKey(long lcsRoleId)
		throws NoSuchLCSRoleException {
		return findByPrimaryKey((Serializable)lcsRoleId);
	}

	/**
	 * Returns the l c s role with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the l c s role
	 * @return the l c s role, or <code>null</code> if a l c s role with the primary key could not be found
	 */
	@Override
	public LCSRole fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(LCSRoleModelImpl.ENTITY_CACHE_ENABLED,
				LCSRoleImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		LCSRole lcsRole = (LCSRole)serializable;

		if (lcsRole == null) {
			Session session = null;

			try {
				session = openSession();

				lcsRole = (LCSRole)session.get(LCSRoleImpl.class, primaryKey);

				if (lcsRole != null) {
					cacheResult(lcsRole);
				}
				else {
					entityCache.putResult(LCSRoleModelImpl.ENTITY_CACHE_ENABLED,
						LCSRoleImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(LCSRoleModelImpl.ENTITY_CACHE_ENABLED,
					LCSRoleImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return lcsRole;
	}

	/**
	 * Returns the l c s role with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param lcsRoleId the primary key of the l c s role
	 * @return the l c s role, or <code>null</code> if a l c s role with the primary key could not be found
	 */
	@Override
	public LCSRole fetchByPrimaryKey(long lcsRoleId) {
		return fetchByPrimaryKey((Serializable)lcsRoleId);
	}

	@Override
	public Map<Serializable, LCSRole> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, LCSRole> map = new HashMap<Serializable, LCSRole>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			LCSRole lcsRole = fetchByPrimaryKey(primaryKey);

			if (lcsRole != null) {
				map.put(primaryKey, lcsRole);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(LCSRoleModelImpl.ENTITY_CACHE_ENABLED,
					LCSRoleImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (LCSRole)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_LCSROLE_WHERE_PKS_IN);

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

			for (LCSRole lcsRole : (List<LCSRole>)q.list()) {
				map.put(lcsRole.getPrimaryKeyObj(), lcsRole);

				cacheResult(lcsRole);

				uncachedPrimaryKeys.remove(lcsRole.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(LCSRoleModelImpl.ENTITY_CACHE_ENABLED,
					LCSRoleImpl.class, primaryKey, nullModel);
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
	 * Returns all the l c s roles.
	 *
	 * @return the l c s roles
	 */
	@Override
	public List<LCSRole> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the l c s roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of l c s roles
	 * @param end the upper bound of the range of l c s roles (not inclusive)
	 * @return the range of l c s roles
	 */
	@Override
	public List<LCSRole> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the l c s roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of l c s roles
	 * @param end the upper bound of the range of l c s roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of l c s roles
	 */
	@Override
	public List<LCSRole> findAll(int start, int end,
		OrderByComparator<LCSRole> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the l c s roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of l c s roles
	 * @param end the upper bound of the range of l c s roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of l c s roles
	 */
	@Override
	public List<LCSRole> findAll(int start, int end,
		OrderByComparator<LCSRole> orderByComparator, boolean retrieveFromCache) {
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

		List<LCSRole> list = null;

		if (retrieveFromCache) {
			list = (List<LCSRole>)finderCache.getResult(finderPath, finderArgs,
					this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_LCSROLE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_LCSROLE;

				if (pagination) {
					sql = sql.concat(LCSRoleModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<LCSRole>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSRole>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the l c s roles from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (LCSRole lcsRole : findAll()) {
			remove(lcsRole);
		}
	}

	/**
	 * Returns the number of l c s roles.
	 *
	 * @return the number of l c s roles
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_LCSROLE);

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
		return LCSRoleModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the l c s role persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(LCSRoleImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_LCSROLE = "SELECT lcsRole FROM LCSRole lcsRole";
	private static final String _SQL_SELECT_LCSROLE_WHERE_PKS_IN = "SELECT lcsRole FROM LCSRole lcsRole WHERE lcsRoleId IN (";
	private static final String _SQL_SELECT_LCSROLE_WHERE = "SELECT lcsRole FROM LCSRole lcsRole WHERE ";
	private static final String _SQL_COUNT_LCSROLE = "SELECT COUNT(lcsRole) FROM LCSRole lcsRole";
	private static final String _SQL_COUNT_LCSROLE_WHERE = "SELECT COUNT(lcsRole) FROM LCSRole lcsRole WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "lcsRole.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No LCSRole exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No LCSRole exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(LCSRolePersistenceImpl.class);
}