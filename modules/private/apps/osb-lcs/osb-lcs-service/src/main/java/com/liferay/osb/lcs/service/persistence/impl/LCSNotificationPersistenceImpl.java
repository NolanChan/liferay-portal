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

import com.liferay.osb.lcs.exception.NoSuchLCSNotificationException;
import com.liferay.osb.lcs.model.LCSNotification;
import com.liferay.osb.lcs.model.impl.LCSNotificationImpl;
import com.liferay.osb.lcs.model.impl.LCSNotificationModelImpl;
import com.liferay.osb.lcs.service.persistence.LCSNotificationPersistence;

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
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
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
 * The persistence implementation for the l c s notification service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Igor Beslic
 * @see LCSNotificationPersistence
 * @see com.liferay.osb.lcs.service.persistence.LCSNotificationUtil
 * @generated
 */
@ProviderType
public class LCSNotificationPersistenceImpl extends BasePersistenceImpl<LCSNotification>
	implements LCSNotificationPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link LCSNotificationUtil} to access the l c s notification persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = LCSNotificationImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(LCSNotificationModelImpl.ENTITY_CACHE_ENABLED,
			LCSNotificationModelImpl.FINDER_CACHE_ENABLED,
			LCSNotificationImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(LCSNotificationModelImpl.ENTITY_CACHE_ENABLED,
			LCSNotificationModelImpl.FINDER_CACHE_ENABLED,
			LCSNotificationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(LCSNotificationModelImpl.ENTITY_CACHE_ENABLED,
			LCSNotificationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(LCSNotificationModelImpl.ENTITY_CACHE_ENABLED,
			LCSNotificationModelImpl.FINDER_CACHE_ENABLED,
			LCSNotificationImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUserId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(LCSNotificationModelImpl.ENTITY_CACHE_ENABLED,
			LCSNotificationModelImpl.FINDER_CACHE_ENABLED,
			LCSNotificationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] { Long.class.getName() },
			LCSNotificationModelImpl.USERID_COLUMN_BITMASK |
			LCSNotificationModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			LCSNotificationModelImpl.CLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(LCSNotificationModelImpl.ENTITY_CACHE_ENABLED,
			LCSNotificationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the l c s notifications where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching l c s notifications
	 */
	@Override
	public List<LCSNotification> findByUserId(long userId) {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the l c s notifications where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSNotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of l c s notifications
	 * @param end the upper bound of the range of l c s notifications (not inclusive)
	 * @return the range of matching l c s notifications
	 */
	@Override
	public List<LCSNotification> findByUserId(long userId, int start, int end) {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the l c s notifications where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSNotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of l c s notifications
	 * @param end the upper bound of the range of l c s notifications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching l c s notifications
	 */
	@Override
	public List<LCSNotification> findByUserId(long userId, int start, int end,
		OrderByComparator<LCSNotification> orderByComparator) {
		return findByUserId(userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the l c s notifications where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSNotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of l c s notifications
	 * @param end the upper bound of the range of l c s notifications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching l c s notifications
	 */
	@Override
	public List<LCSNotification> findByUserId(long userId, int start, int end,
		OrderByComparator<LCSNotification> orderByComparator,
		boolean retrieveFromCache) {
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

		List<LCSNotification> list = null;

		if (retrieveFromCache) {
			list = (List<LCSNotification>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LCSNotification lcsNotification : list) {
					if ((userId != lcsNotification.getUserId())) {
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

			query.append(_SQL_SELECT_LCSNOTIFICATION_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LCSNotificationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (!pagination) {
					list = (List<LCSNotification>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSNotification>)QueryUtil.list(q,
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
	 * Returns the first l c s notification in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s notification
	 * @throws NoSuchLCSNotificationException if a matching l c s notification could not be found
	 */
	@Override
	public LCSNotification findByUserId_First(long userId,
		OrderByComparator<LCSNotification> orderByComparator)
		throws NoSuchLCSNotificationException {
		LCSNotification lcsNotification = fetchByUserId_First(userId,
				orderByComparator);

		if (lcsNotification != null) {
			return lcsNotification;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSNotificationException(msg.toString());
	}

	/**
	 * Returns the first l c s notification in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s notification, or <code>null</code> if a matching l c s notification could not be found
	 */
	@Override
	public LCSNotification fetchByUserId_First(long userId,
		OrderByComparator<LCSNotification> orderByComparator) {
		List<LCSNotification> list = findByUserId(userId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last l c s notification in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s notification
	 * @throws NoSuchLCSNotificationException if a matching l c s notification could not be found
	 */
	@Override
	public LCSNotification findByUserId_Last(long userId,
		OrderByComparator<LCSNotification> orderByComparator)
		throws NoSuchLCSNotificationException {
		LCSNotification lcsNotification = fetchByUserId_Last(userId,
				orderByComparator);

		if (lcsNotification != null) {
			return lcsNotification;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSNotificationException(msg.toString());
	}

	/**
	 * Returns the last l c s notification in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s notification, or <code>null</code> if a matching l c s notification could not be found
	 */
	@Override
	public LCSNotification fetchByUserId_Last(long userId,
		OrderByComparator<LCSNotification> orderByComparator) {
		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<LCSNotification> list = findByUserId(userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the l c s notifications before and after the current l c s notification in the ordered set where userId = &#63;.
	 *
	 * @param lcsNotificationId the primary key of the current l c s notification
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next l c s notification
	 * @throws NoSuchLCSNotificationException if a l c s notification with the primary key could not be found
	 */
	@Override
	public LCSNotification[] findByUserId_PrevAndNext(long lcsNotificationId,
		long userId, OrderByComparator<LCSNotification> orderByComparator)
		throws NoSuchLCSNotificationException {
		LCSNotification lcsNotification = findByPrimaryKey(lcsNotificationId);

		Session session = null;

		try {
			session = openSession();

			LCSNotification[] array = new LCSNotificationImpl[3];

			array[0] = getByUserId_PrevAndNext(session, lcsNotification,
					userId, orderByComparator, true);

			array[1] = lcsNotification;

			array[2] = getByUserId_PrevAndNext(session, lcsNotification,
					userId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LCSNotification getByUserId_PrevAndNext(Session session,
		LCSNotification lcsNotification, long userId,
		OrderByComparator<LCSNotification> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LCSNOTIFICATION_WHERE);

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
			query.append(LCSNotificationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(lcsNotification);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LCSNotification> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the l c s notifications where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByUserId(long userId) {
		for (LCSNotification lcsNotification : findByUserId(userId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(lcsNotification);
		}
	}

	/**
	 * Returns the number of l c s notifications where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching l c s notifications
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_USERID;

		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LCSNOTIFICATION_WHERE);

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

	private static final String _FINDER_COLUMN_USERID_USERID_2 = "lcsNotification.userId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C = new FinderPath(LCSNotificationModelImpl.ENTITY_CACHE_ENABLED,
			LCSNotificationModelImpl.FINDER_CACHE_ENABLED,
			LCSNotificationImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByC_C",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C = new FinderPath(LCSNotificationModelImpl.ENTITY_CACHE_ENABLED,
			LCSNotificationModelImpl.FINDER_CACHE_ENABLED,
			LCSNotificationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C",
			new String[] { Long.class.getName(), Long.class.getName() },
			LCSNotificationModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			LCSNotificationModelImpl.CLASSPK_COLUMN_BITMASK |
			LCSNotificationModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C = new FinderPath(LCSNotificationModelImpl.ENTITY_CACHE_ENABLED,
			LCSNotificationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the l c s notifications where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the matching l c s notifications
	 */
	@Override
	public List<LCSNotification> findByC_C(long classNameId, long classPK) {
		return findByC_C(classNameId, classPK, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the l c s notifications where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSNotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param start the lower bound of the range of l c s notifications
	 * @param end the upper bound of the range of l c s notifications (not inclusive)
	 * @return the range of matching l c s notifications
	 */
	@Override
	public List<LCSNotification> findByC_C(long classNameId, long classPK,
		int start, int end) {
		return findByC_C(classNameId, classPK, start, end, null);
	}

	/**
	 * Returns an ordered range of all the l c s notifications where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSNotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param start the lower bound of the range of l c s notifications
	 * @param end the upper bound of the range of l c s notifications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching l c s notifications
	 */
	@Override
	public List<LCSNotification> findByC_C(long classNameId, long classPK,
		int start, int end, OrderByComparator<LCSNotification> orderByComparator) {
		return findByC_C(classNameId, classPK, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the l c s notifications where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSNotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param start the lower bound of the range of l c s notifications
	 * @param end the upper bound of the range of l c s notifications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching l c s notifications
	 */
	@Override
	public List<LCSNotification> findByC_C(long classNameId, long classPK,
		int start, int end,
		OrderByComparator<LCSNotification> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C;
			finderArgs = new Object[] { classNameId, classPK };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C;
			finderArgs = new Object[] {
					classNameId, classPK,
					
					start, end, orderByComparator
				};
		}

		List<LCSNotification> list = null;

		if (retrieveFromCache) {
			list = (List<LCSNotification>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LCSNotification lcsNotification : list) {
					if ((classNameId != lcsNotification.getClassNameId()) ||
							(classPK != lcsNotification.getClassPK())) {
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

			query.append(_SQL_SELECT_LCSNOTIFICATION_WHERE);

			query.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_CLASSPK_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LCSNotificationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				if (!pagination) {
					list = (List<LCSNotification>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSNotification>)QueryUtil.list(q,
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
	 * Returns the first l c s notification in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s notification
	 * @throws NoSuchLCSNotificationException if a matching l c s notification could not be found
	 */
	@Override
	public LCSNotification findByC_C_First(long classNameId, long classPK,
		OrderByComparator<LCSNotification> orderByComparator)
		throws NoSuchLCSNotificationException {
		LCSNotification lcsNotification = fetchByC_C_First(classNameId,
				classPK, orderByComparator);

		if (lcsNotification != null) {
			return lcsNotification;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSNotificationException(msg.toString());
	}

	/**
	 * Returns the first l c s notification in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s notification, or <code>null</code> if a matching l c s notification could not be found
	 */
	@Override
	public LCSNotification fetchByC_C_First(long classNameId, long classPK,
		OrderByComparator<LCSNotification> orderByComparator) {
		List<LCSNotification> list = findByC_C(classNameId, classPK, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last l c s notification in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s notification
	 * @throws NoSuchLCSNotificationException if a matching l c s notification could not be found
	 */
	@Override
	public LCSNotification findByC_C_Last(long classNameId, long classPK,
		OrderByComparator<LCSNotification> orderByComparator)
		throws NoSuchLCSNotificationException {
		LCSNotification lcsNotification = fetchByC_C_Last(classNameId, classPK,
				orderByComparator);

		if (lcsNotification != null) {
			return lcsNotification;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSNotificationException(msg.toString());
	}

	/**
	 * Returns the last l c s notification in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s notification, or <code>null</code> if a matching l c s notification could not be found
	 */
	@Override
	public LCSNotification fetchByC_C_Last(long classNameId, long classPK,
		OrderByComparator<LCSNotification> orderByComparator) {
		int count = countByC_C(classNameId, classPK);

		if (count == 0) {
			return null;
		}

		List<LCSNotification> list = findByC_C(classNameId, classPK, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the l c s notifications before and after the current l c s notification in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param lcsNotificationId the primary key of the current l c s notification
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next l c s notification
	 * @throws NoSuchLCSNotificationException if a l c s notification with the primary key could not be found
	 */
	@Override
	public LCSNotification[] findByC_C_PrevAndNext(long lcsNotificationId,
		long classNameId, long classPK,
		OrderByComparator<LCSNotification> orderByComparator)
		throws NoSuchLCSNotificationException {
		LCSNotification lcsNotification = findByPrimaryKey(lcsNotificationId);

		Session session = null;

		try {
			session = openSession();

			LCSNotification[] array = new LCSNotificationImpl[3];

			array[0] = getByC_C_PrevAndNext(session, lcsNotification,
					classNameId, classPK, orderByComparator, true);

			array[1] = lcsNotification;

			array[2] = getByC_C_PrevAndNext(session, lcsNotification,
					classNameId, classPK, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LCSNotification getByC_C_PrevAndNext(Session session,
		LCSNotification lcsNotification, long classNameId, long classPK,
		OrderByComparator<LCSNotification> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_LCSNOTIFICATION_WHERE);

		query.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_C_C_CLASSPK_2);

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
			query.append(LCSNotificationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(classNameId);

		qPos.add(classPK);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(lcsNotification);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LCSNotification> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the l c s notifications where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 */
	@Override
	public void removeByC_C(long classNameId, long classPK) {
		for (LCSNotification lcsNotification : findByC_C(classNameId, classPK,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(lcsNotification);
		}
	}

	/**
	 * Returns the number of l c s notifications where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the number of matching l c s notifications
	 */
	@Override
	public int countByC_C(long classNameId, long classPK) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_C;

		Object[] finderArgs = new Object[] { classNameId, classPK };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LCSNOTIFICATION_WHERE);

			query.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_CLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

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

	private static final String _FINDER_COLUMN_C_C_CLASSNAMEID_2 = "lcsNotification.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_CLASSPK_2 = "lcsNotification.classPK = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_U_C_C = new FinderPath(LCSNotificationModelImpl.ENTITY_CACHE_ENABLED,
			LCSNotificationModelImpl.FINDER_CACHE_ENABLED,
			LCSNotificationImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByU_C_C",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_C_C = new FinderPath(LCSNotificationModelImpl.ENTITY_CACHE_ENABLED,
			LCSNotificationModelImpl.FINDER_CACHE_ENABLED,
			LCSNotificationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByU_C_C",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			LCSNotificationModelImpl.USERID_COLUMN_BITMASK |
			LCSNotificationModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			LCSNotificationModelImpl.CLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_C_C = new FinderPath(LCSNotificationModelImpl.ENTITY_CACHE_ENABLED,
			LCSNotificationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_C_C",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});

	/**
	 * Returns all the l c s notifications where userId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the matching l c s notifications
	 */
	@Override
	public List<LCSNotification> findByU_C_C(long userId, long classNameId,
		long classPK) {
		return findByU_C_C(userId, classNameId, classPK, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the l c s notifications where userId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSNotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param start the lower bound of the range of l c s notifications
	 * @param end the upper bound of the range of l c s notifications (not inclusive)
	 * @return the range of matching l c s notifications
	 */
	@Override
	public List<LCSNotification> findByU_C_C(long userId, long classNameId,
		long classPK, int start, int end) {
		return findByU_C_C(userId, classNameId, classPK, start, end, null);
	}

	/**
	 * Returns an ordered range of all the l c s notifications where userId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSNotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param start the lower bound of the range of l c s notifications
	 * @param end the upper bound of the range of l c s notifications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching l c s notifications
	 */
	@Override
	public List<LCSNotification> findByU_C_C(long userId, long classNameId,
		long classPK, int start, int end,
		OrderByComparator<LCSNotification> orderByComparator) {
		return findByU_C_C(userId, classNameId, classPK, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the l c s notifications where userId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSNotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param start the lower bound of the range of l c s notifications
	 * @param end the upper bound of the range of l c s notifications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching l c s notifications
	 */
	@Override
	public List<LCSNotification> findByU_C_C(long userId, long classNameId,
		long classPK, int start, int end,
		OrderByComparator<LCSNotification> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_C_C;
			finderArgs = new Object[] { userId, classNameId, classPK };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_U_C_C;
			finderArgs = new Object[] {
					userId, classNameId, classPK,
					
					start, end, orderByComparator
				};
		}

		List<LCSNotification> list = null;

		if (retrieveFromCache) {
			list = (List<LCSNotification>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LCSNotification lcsNotification : list) {
					if ((userId != lcsNotification.getUserId()) ||
							(classNameId != lcsNotification.getClassNameId()) ||
							(classPK != lcsNotification.getClassPK())) {
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

			query.append(_SQL_SELECT_LCSNOTIFICATION_WHERE);

			query.append(_FINDER_COLUMN_U_C_C_USERID_2);

			query.append(_FINDER_COLUMN_U_C_C_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_U_C_C_CLASSPK_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LCSNotificationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(classNameId);

				qPos.add(classPK);

				if (!pagination) {
					list = (List<LCSNotification>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSNotification>)QueryUtil.list(q,
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
	 * Returns the first l c s notification in the ordered set where userId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s notification
	 * @throws NoSuchLCSNotificationException if a matching l c s notification could not be found
	 */
	@Override
	public LCSNotification findByU_C_C_First(long userId, long classNameId,
		long classPK, OrderByComparator<LCSNotification> orderByComparator)
		throws NoSuchLCSNotificationException {
		LCSNotification lcsNotification = fetchByU_C_C_First(userId,
				classNameId, classPK, orderByComparator);

		if (lcsNotification != null) {
			return lcsNotification;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSNotificationException(msg.toString());
	}

	/**
	 * Returns the first l c s notification in the ordered set where userId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s notification, or <code>null</code> if a matching l c s notification could not be found
	 */
	@Override
	public LCSNotification fetchByU_C_C_First(long userId, long classNameId,
		long classPK, OrderByComparator<LCSNotification> orderByComparator) {
		List<LCSNotification> list = findByU_C_C(userId, classNameId, classPK,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last l c s notification in the ordered set where userId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s notification
	 * @throws NoSuchLCSNotificationException if a matching l c s notification could not be found
	 */
	@Override
	public LCSNotification findByU_C_C_Last(long userId, long classNameId,
		long classPK, OrderByComparator<LCSNotification> orderByComparator)
		throws NoSuchLCSNotificationException {
		LCSNotification lcsNotification = fetchByU_C_C_Last(userId,
				classNameId, classPK, orderByComparator);

		if (lcsNotification != null) {
			return lcsNotification;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSNotificationException(msg.toString());
	}

	/**
	 * Returns the last l c s notification in the ordered set where userId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s notification, or <code>null</code> if a matching l c s notification could not be found
	 */
	@Override
	public LCSNotification fetchByU_C_C_Last(long userId, long classNameId,
		long classPK, OrderByComparator<LCSNotification> orderByComparator) {
		int count = countByU_C_C(userId, classNameId, classPK);

		if (count == 0) {
			return null;
		}

		List<LCSNotification> list = findByU_C_C(userId, classNameId, classPK,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the l c s notifications before and after the current l c s notification in the ordered set where userId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param lcsNotificationId the primary key of the current l c s notification
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next l c s notification
	 * @throws NoSuchLCSNotificationException if a l c s notification with the primary key could not be found
	 */
	@Override
	public LCSNotification[] findByU_C_C_PrevAndNext(long lcsNotificationId,
		long userId, long classNameId, long classPK,
		OrderByComparator<LCSNotification> orderByComparator)
		throws NoSuchLCSNotificationException {
		LCSNotification lcsNotification = findByPrimaryKey(lcsNotificationId);

		Session session = null;

		try {
			session = openSession();

			LCSNotification[] array = new LCSNotificationImpl[3];

			array[0] = getByU_C_C_PrevAndNext(session, lcsNotification, userId,
					classNameId, classPK, orderByComparator, true);

			array[1] = lcsNotification;

			array[2] = getByU_C_C_PrevAndNext(session, lcsNotification, userId,
					classNameId, classPK, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LCSNotification getByU_C_C_PrevAndNext(Session session,
		LCSNotification lcsNotification, long userId, long classNameId,
		long classPK, OrderByComparator<LCSNotification> orderByComparator,
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

		query.append(_SQL_SELECT_LCSNOTIFICATION_WHERE);

		query.append(_FINDER_COLUMN_U_C_C_USERID_2);

		query.append(_FINDER_COLUMN_U_C_C_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_U_C_C_CLASSPK_2);

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
			query.append(LCSNotificationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		qPos.add(classNameId);

		qPos.add(classPK);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(lcsNotification);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LCSNotification> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the l c s notifications where userId = &#63; and classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 */
	@Override
	public void removeByU_C_C(long userId, long classNameId, long classPK) {
		for (LCSNotification lcsNotification : findByU_C_C(userId, classNameId,
				classPK, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(lcsNotification);
		}
	}

	/**
	 * Returns the number of l c s notifications where userId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the number of matching l c s notifications
	 */
	@Override
	public int countByU_C_C(long userId, long classNameId, long classPK) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_U_C_C;

		Object[] finderArgs = new Object[] { userId, classNameId, classPK };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_LCSNOTIFICATION_WHERE);

			query.append(_FINDER_COLUMN_U_C_C_USERID_2);

			query.append(_FINDER_COLUMN_U_C_C_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_U_C_C_CLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(classNameId);

				qPos.add(classPK);

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

	private static final String _FINDER_COLUMN_U_C_C_USERID_2 = "lcsNotification.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_C_C_CLASSNAMEID_2 = "lcsNotification.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_U_C_C_CLASSPK_2 = "lcsNotification.classPK = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_U_C_C_T = new FinderPath(LCSNotificationModelImpl.ENTITY_CACHE_ENABLED,
			LCSNotificationModelImpl.FINDER_CACHE_ENABLED,
			LCSNotificationImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByU_C_C_T",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			LCSNotificationModelImpl.USERID_COLUMN_BITMASK |
			LCSNotificationModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			LCSNotificationModelImpl.CLASSPK_COLUMN_BITMASK |
			LCSNotificationModelImpl.TYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_C_C_T = new FinderPath(LCSNotificationModelImpl.ENTITY_CACHE_ENABLED,
			LCSNotificationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_C_C_T",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns the l c s notification where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; or throws a {@link NoSuchLCSNotificationException} if it could not be found.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @return the matching l c s notification
	 * @throws NoSuchLCSNotificationException if a matching l c s notification could not be found
	 */
	@Override
	public LCSNotification findByU_C_C_T(long userId, long classNameId,
		long classPK, int type) throws NoSuchLCSNotificationException {
		LCSNotification lcsNotification = fetchByU_C_C_T(userId, classNameId,
				classPK, type);

		if (lcsNotification == null) {
			StringBundler msg = new StringBundler(10);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(", classNameId=");
			msg.append(classNameId);

			msg.append(", classPK=");
			msg.append(classPK);

			msg.append(", type=");
			msg.append(type);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchLCSNotificationException(msg.toString());
		}

		return lcsNotification;
	}

	/**
	 * Returns the l c s notification where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @return the matching l c s notification, or <code>null</code> if a matching l c s notification could not be found
	 */
	@Override
	public LCSNotification fetchByU_C_C_T(long userId, long classNameId,
		long classPK, int type) {
		return fetchByU_C_C_T(userId, classNameId, classPK, type, true);
	}

	/**
	 * Returns the l c s notification where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching l c s notification, or <code>null</code> if a matching l c s notification could not be found
	 */
	@Override
	public LCSNotification fetchByU_C_C_T(long userId, long classNameId,
		long classPK, int type, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { userId, classNameId, classPK, type };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_U_C_C_T,
					finderArgs, this);
		}

		if (result instanceof LCSNotification) {
			LCSNotification lcsNotification = (LCSNotification)result;

			if ((userId != lcsNotification.getUserId()) ||
					(classNameId != lcsNotification.getClassNameId()) ||
					(classPK != lcsNotification.getClassPK()) ||
					(type != lcsNotification.getType())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_SELECT_LCSNOTIFICATION_WHERE);

			query.append(_FINDER_COLUMN_U_C_C_T_USERID_2);

			query.append(_FINDER_COLUMN_U_C_C_T_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_U_C_C_T_CLASSPK_2);

			query.append(_FINDER_COLUMN_U_C_C_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(type);

				List<LCSNotification> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_U_C_C_T,
						finderArgs, list);
				}
				else {
					LCSNotification lcsNotification = list.get(0);

					result = lcsNotification;

					cacheResult(lcsNotification);

					if ((lcsNotification.getUserId() != userId) ||
							(lcsNotification.getClassNameId() != classNameId) ||
							(lcsNotification.getClassPK() != classPK) ||
							(lcsNotification.getType() != type)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_U_C_C_T,
							finderArgs, lcsNotification);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_U_C_C_T,
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
			return (LCSNotification)result;
		}
	}

	/**
	 * Removes the l c s notification where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @return the l c s notification that was removed
	 */
	@Override
	public LCSNotification removeByU_C_C_T(long userId, long classNameId,
		long classPK, int type) throws NoSuchLCSNotificationException {
		LCSNotification lcsNotification = findByU_C_C_T(userId, classNameId,
				classPK, type);

		return remove(lcsNotification);
	}

	/**
	 * Returns the number of l c s notifications where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @return the number of matching l c s notifications
	 */
	@Override
	public int countByU_C_C_T(long userId, long classNameId, long classPK,
		int type) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_U_C_C_T;

		Object[] finderArgs = new Object[] { userId, classNameId, classPK, type };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_LCSNOTIFICATION_WHERE);

			query.append(_FINDER_COLUMN_U_C_C_T_USERID_2);

			query.append(_FINDER_COLUMN_U_C_C_T_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_U_C_C_T_CLASSPK_2);

			query.append(_FINDER_COLUMN_U_C_C_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(classNameId);

				qPos.add(classPK);

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

	private static final String _FINDER_COLUMN_U_C_C_T_USERID_2 = "lcsNotification.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_C_C_T_CLASSNAMEID_2 = "lcsNotification.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_U_C_C_T_CLASSPK_2 = "lcsNotification.classPK = ? AND ";
	private static final String _FINDER_COLUMN_U_C_C_T_TYPE_2 = "lcsNotification.type = ?";

	public LCSNotificationPersistenceImpl() {
		setModelClass(LCSNotification.class);
	}

	/**
	 * Caches the l c s notification in the entity cache if it is enabled.
	 *
	 * @param lcsNotification the l c s notification
	 */
	@Override
	public void cacheResult(LCSNotification lcsNotification) {
		entityCache.putResult(LCSNotificationModelImpl.ENTITY_CACHE_ENABLED,
			LCSNotificationImpl.class, lcsNotification.getPrimaryKey(),
			lcsNotification);

		finderCache.putResult(FINDER_PATH_FETCH_BY_U_C_C_T,
			new Object[] {
				lcsNotification.getUserId(), lcsNotification.getClassNameId(),
				lcsNotification.getClassPK(), lcsNotification.getType()
			}, lcsNotification);

		lcsNotification.resetOriginalValues();
	}

	/**
	 * Caches the l c s notifications in the entity cache if it is enabled.
	 *
	 * @param lcsNotifications the l c s notifications
	 */
	@Override
	public void cacheResult(List<LCSNotification> lcsNotifications) {
		for (LCSNotification lcsNotification : lcsNotifications) {
			if (entityCache.getResult(
						LCSNotificationModelImpl.ENTITY_CACHE_ENABLED,
						LCSNotificationImpl.class,
						lcsNotification.getPrimaryKey()) == null) {
				cacheResult(lcsNotification);
			}
			else {
				lcsNotification.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all l c s notifications.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(LCSNotificationImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the l c s notification.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LCSNotification lcsNotification) {
		entityCache.removeResult(LCSNotificationModelImpl.ENTITY_CACHE_ENABLED,
			LCSNotificationImpl.class, lcsNotification.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((LCSNotificationModelImpl)lcsNotification, true);
	}

	@Override
	public void clearCache(List<LCSNotification> lcsNotifications) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (LCSNotification lcsNotification : lcsNotifications) {
			entityCache.removeResult(LCSNotificationModelImpl.ENTITY_CACHE_ENABLED,
				LCSNotificationImpl.class, lcsNotification.getPrimaryKey());

			clearUniqueFindersCache((LCSNotificationModelImpl)lcsNotification,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		LCSNotificationModelImpl lcsNotificationModelImpl) {
		Object[] args = new Object[] {
				lcsNotificationModelImpl.getUserId(),
				lcsNotificationModelImpl.getClassNameId(),
				lcsNotificationModelImpl.getClassPK(),
				lcsNotificationModelImpl.getType()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_U_C_C_T, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_U_C_C_T, args,
			lcsNotificationModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		LCSNotificationModelImpl lcsNotificationModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					lcsNotificationModelImpl.getUserId(),
					lcsNotificationModelImpl.getClassNameId(),
					lcsNotificationModelImpl.getClassPK(),
					lcsNotificationModelImpl.getType()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_U_C_C_T, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_U_C_C_T, args);
		}

		if ((lcsNotificationModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_U_C_C_T.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					lcsNotificationModelImpl.getOriginalUserId(),
					lcsNotificationModelImpl.getOriginalClassNameId(),
					lcsNotificationModelImpl.getOriginalClassPK(),
					lcsNotificationModelImpl.getOriginalType()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_U_C_C_T, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_U_C_C_T, args);
		}
	}

	/**
	 * Creates a new l c s notification with the primary key. Does not add the l c s notification to the database.
	 *
	 * @param lcsNotificationId the primary key for the new l c s notification
	 * @return the new l c s notification
	 */
	@Override
	public LCSNotification create(long lcsNotificationId) {
		LCSNotification lcsNotification = new LCSNotificationImpl();

		lcsNotification.setNew(true);
		lcsNotification.setPrimaryKey(lcsNotificationId);

		return lcsNotification;
	}

	/**
	 * Removes the l c s notification with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param lcsNotificationId the primary key of the l c s notification
	 * @return the l c s notification that was removed
	 * @throws NoSuchLCSNotificationException if a l c s notification with the primary key could not be found
	 */
	@Override
	public LCSNotification remove(long lcsNotificationId)
		throws NoSuchLCSNotificationException {
		return remove((Serializable)lcsNotificationId);
	}

	/**
	 * Removes the l c s notification with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the l c s notification
	 * @return the l c s notification that was removed
	 * @throws NoSuchLCSNotificationException if a l c s notification with the primary key could not be found
	 */
	@Override
	public LCSNotification remove(Serializable primaryKey)
		throws NoSuchLCSNotificationException {
		Session session = null;

		try {
			session = openSession();

			LCSNotification lcsNotification = (LCSNotification)session.get(LCSNotificationImpl.class,
					primaryKey);

			if (lcsNotification == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLCSNotificationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(lcsNotification);
		}
		catch (NoSuchLCSNotificationException nsee) {
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
	protected LCSNotification removeImpl(LCSNotification lcsNotification) {
		lcsNotification = toUnwrappedModel(lcsNotification);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(lcsNotification)) {
				lcsNotification = (LCSNotification)session.get(LCSNotificationImpl.class,
						lcsNotification.getPrimaryKeyObj());
			}

			if (lcsNotification != null) {
				session.delete(lcsNotification);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (lcsNotification != null) {
			clearCache(lcsNotification);
		}

		return lcsNotification;
	}

	@Override
	public LCSNotification updateImpl(LCSNotification lcsNotification) {
		lcsNotification = toUnwrappedModel(lcsNotification);

		boolean isNew = lcsNotification.isNew();

		LCSNotificationModelImpl lcsNotificationModelImpl = (LCSNotificationModelImpl)lcsNotification;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (lcsNotification.getCreateDate() == null)) {
			if (serviceContext == null) {
				lcsNotification.setCreateDate(now);
			}
			else {
				lcsNotification.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!lcsNotificationModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				lcsNotification.setModifiedDate(now);
			}
			else {
				lcsNotification.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (lcsNotification.isNew()) {
				session.save(lcsNotification);

				lcsNotification.setNew(false);
			}
			else {
				lcsNotification = (LCSNotification)session.merge(lcsNotification);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !LCSNotificationModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((lcsNotificationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						lcsNotificationModelImpl.getOriginalUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] { lcsNotificationModelImpl.getUserId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}

			if ((lcsNotificationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						lcsNotificationModelImpl.getOriginalClassNameId(),
						lcsNotificationModelImpl.getOriginalClassPK()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C,
					args);

				args = new Object[] {
						lcsNotificationModelImpl.getClassNameId(),
						lcsNotificationModelImpl.getClassPK()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C,
					args);
			}

			if ((lcsNotificationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_C_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						lcsNotificationModelImpl.getOriginalUserId(),
						lcsNotificationModelImpl.getOriginalClassNameId(),
						lcsNotificationModelImpl.getOriginalClassPK()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_U_C_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_C_C,
					args);

				args = new Object[] {
						lcsNotificationModelImpl.getUserId(),
						lcsNotificationModelImpl.getClassNameId(),
						lcsNotificationModelImpl.getClassPK()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_U_C_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_C_C,
					args);
			}
		}

		entityCache.putResult(LCSNotificationModelImpl.ENTITY_CACHE_ENABLED,
			LCSNotificationImpl.class, lcsNotification.getPrimaryKey(),
			lcsNotification, false);

		clearUniqueFindersCache(lcsNotificationModelImpl, false);
		cacheUniqueFindersCache(lcsNotificationModelImpl);

		lcsNotification.resetOriginalValues();

		return lcsNotification;
	}

	protected LCSNotification toUnwrappedModel(LCSNotification lcsNotification) {
		if (lcsNotification instanceof LCSNotificationImpl) {
			return lcsNotification;
		}

		LCSNotificationImpl lcsNotificationImpl = new LCSNotificationImpl();

		lcsNotificationImpl.setNew(lcsNotification.isNew());
		lcsNotificationImpl.setPrimaryKey(lcsNotification.getPrimaryKey());

		lcsNotificationImpl.setLcsNotificationId(lcsNotification.getLcsNotificationId());
		lcsNotificationImpl.setUserId(lcsNotification.getUserId());
		lcsNotificationImpl.setCreateDate(lcsNotification.getCreateDate());
		lcsNotificationImpl.setModifiedDate(lcsNotification.getModifiedDate());
		lcsNotificationImpl.setClassNameId(lcsNotification.getClassNameId());
		lcsNotificationImpl.setClassPK(lcsNotification.getClassPK());
		lcsNotificationImpl.setEnabled(lcsNotification.isEnabled());
		lcsNotificationImpl.setType(lcsNotification.getType());

		return lcsNotificationImpl;
	}

	/**
	 * Returns the l c s notification with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the l c s notification
	 * @return the l c s notification
	 * @throws NoSuchLCSNotificationException if a l c s notification with the primary key could not be found
	 */
	@Override
	public LCSNotification findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLCSNotificationException {
		LCSNotification lcsNotification = fetchByPrimaryKey(primaryKey);

		if (lcsNotification == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLCSNotificationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return lcsNotification;
	}

	/**
	 * Returns the l c s notification with the primary key or throws a {@link NoSuchLCSNotificationException} if it could not be found.
	 *
	 * @param lcsNotificationId the primary key of the l c s notification
	 * @return the l c s notification
	 * @throws NoSuchLCSNotificationException if a l c s notification with the primary key could not be found
	 */
	@Override
	public LCSNotification findByPrimaryKey(long lcsNotificationId)
		throws NoSuchLCSNotificationException {
		return findByPrimaryKey((Serializable)lcsNotificationId);
	}

	/**
	 * Returns the l c s notification with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the l c s notification
	 * @return the l c s notification, or <code>null</code> if a l c s notification with the primary key could not be found
	 */
	@Override
	public LCSNotification fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(LCSNotificationModelImpl.ENTITY_CACHE_ENABLED,
				LCSNotificationImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		LCSNotification lcsNotification = (LCSNotification)serializable;

		if (lcsNotification == null) {
			Session session = null;

			try {
				session = openSession();

				lcsNotification = (LCSNotification)session.get(LCSNotificationImpl.class,
						primaryKey);

				if (lcsNotification != null) {
					cacheResult(lcsNotification);
				}
				else {
					entityCache.putResult(LCSNotificationModelImpl.ENTITY_CACHE_ENABLED,
						LCSNotificationImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(LCSNotificationModelImpl.ENTITY_CACHE_ENABLED,
					LCSNotificationImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return lcsNotification;
	}

	/**
	 * Returns the l c s notification with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param lcsNotificationId the primary key of the l c s notification
	 * @return the l c s notification, or <code>null</code> if a l c s notification with the primary key could not be found
	 */
	@Override
	public LCSNotification fetchByPrimaryKey(long lcsNotificationId) {
		return fetchByPrimaryKey((Serializable)lcsNotificationId);
	}

	@Override
	public Map<Serializable, LCSNotification> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, LCSNotification> map = new HashMap<Serializable, LCSNotification>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			LCSNotification lcsNotification = fetchByPrimaryKey(primaryKey);

			if (lcsNotification != null) {
				map.put(primaryKey, lcsNotification);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(LCSNotificationModelImpl.ENTITY_CACHE_ENABLED,
					LCSNotificationImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (LCSNotification)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_LCSNOTIFICATION_WHERE_PKS_IN);

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

			for (LCSNotification lcsNotification : (List<LCSNotification>)q.list()) {
				map.put(lcsNotification.getPrimaryKeyObj(), lcsNotification);

				cacheResult(lcsNotification);

				uncachedPrimaryKeys.remove(lcsNotification.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(LCSNotificationModelImpl.ENTITY_CACHE_ENABLED,
					LCSNotificationImpl.class, primaryKey, nullModel);
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
	 * Returns all the l c s notifications.
	 *
	 * @return the l c s notifications
	 */
	@Override
	public List<LCSNotification> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the l c s notifications.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSNotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of l c s notifications
	 * @param end the upper bound of the range of l c s notifications (not inclusive)
	 * @return the range of l c s notifications
	 */
	@Override
	public List<LCSNotification> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the l c s notifications.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSNotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of l c s notifications
	 * @param end the upper bound of the range of l c s notifications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of l c s notifications
	 */
	@Override
	public List<LCSNotification> findAll(int start, int end,
		OrderByComparator<LCSNotification> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the l c s notifications.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSNotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of l c s notifications
	 * @param end the upper bound of the range of l c s notifications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of l c s notifications
	 */
	@Override
	public List<LCSNotification> findAll(int start, int end,
		OrderByComparator<LCSNotification> orderByComparator,
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

		List<LCSNotification> list = null;

		if (retrieveFromCache) {
			list = (List<LCSNotification>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_LCSNOTIFICATION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_LCSNOTIFICATION;

				if (pagination) {
					sql = sql.concat(LCSNotificationModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<LCSNotification>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSNotification>)QueryUtil.list(q,
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
	 * Removes all the l c s notifications from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (LCSNotification lcsNotification : findAll()) {
			remove(lcsNotification);
		}
	}

	/**
	 * Returns the number of l c s notifications.
	 *
	 * @return the number of l c s notifications
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_LCSNOTIFICATION);

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
		return LCSNotificationModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the l c s notification persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(LCSNotificationImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_LCSNOTIFICATION = "SELECT lcsNotification FROM LCSNotification lcsNotification";
	private static final String _SQL_SELECT_LCSNOTIFICATION_WHERE_PKS_IN = "SELECT lcsNotification FROM LCSNotification lcsNotification WHERE lcsNotificationId IN (";
	private static final String _SQL_SELECT_LCSNOTIFICATION_WHERE = "SELECT lcsNotification FROM LCSNotification lcsNotification WHERE ";
	private static final String _SQL_COUNT_LCSNOTIFICATION = "SELECT COUNT(lcsNotification) FROM LCSNotification lcsNotification";
	private static final String _SQL_COUNT_LCSNOTIFICATION_WHERE = "SELECT COUNT(lcsNotification) FROM LCSNotification lcsNotification WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "lcsNotification.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No LCSNotification exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No LCSNotification exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(LCSNotificationPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"type"
			});
}