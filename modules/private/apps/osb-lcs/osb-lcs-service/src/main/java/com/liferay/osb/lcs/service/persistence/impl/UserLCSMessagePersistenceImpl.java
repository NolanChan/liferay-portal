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

import com.liferay.osb.lcs.exception.NoSuchUserLCSMessageException;
import com.liferay.osb.lcs.model.UserLCSMessage;
import com.liferay.osb.lcs.model.impl.UserLCSMessageImpl;
import com.liferay.osb.lcs.model.impl.UserLCSMessageModelImpl;
import com.liferay.osb.lcs.service.persistence.UserLCSMessagePersistence;

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
import java.util.Set;

/**
 * The persistence implementation for the user l c s message service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Igor Beslic
 * @see UserLCSMessagePersistence
 * @see com.liferay.osb.lcs.service.persistence.UserLCSMessageUtil
 * @generated
 */
@ProviderType
public class UserLCSMessagePersistenceImpl extends BasePersistenceImpl<UserLCSMessage>
	implements UserLCSMessagePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link UserLCSMessageUtil} to access the user l c s message persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = UserLCSMessageImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(UserLCSMessageModelImpl.ENTITY_CACHE_ENABLED,
			UserLCSMessageModelImpl.FINDER_CACHE_ENABLED,
			UserLCSMessageImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(UserLCSMessageModelImpl.ENTITY_CACHE_ENABLED,
			UserLCSMessageModelImpl.FINDER_CACHE_ENABLED,
			UserLCSMessageImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(UserLCSMessageModelImpl.ENTITY_CACHE_ENABLED,
			UserLCSMessageModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(UserLCSMessageModelImpl.ENTITY_CACHE_ENABLED,
			UserLCSMessageModelImpl.FINDER_CACHE_ENABLED,
			UserLCSMessageImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUserId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(UserLCSMessageModelImpl.ENTITY_CACHE_ENABLED,
			UserLCSMessageModelImpl.FINDER_CACHE_ENABLED,
			UserLCSMessageImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] { Long.class.getName() },
			UserLCSMessageModelImpl.USERID_COLUMN_BITMASK |
			UserLCSMessageModelImpl.LCSMESSAGEID_COLUMN_BITMASK |
			UserLCSMessageModelImpl.READ_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(UserLCSMessageModelImpl.ENTITY_CACHE_ENABLED,
			UserLCSMessageModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the user l c s messages where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching user l c s messages
	 */
	@Override
	public List<UserLCSMessage> findByUserId(long userId) {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the user l c s messages where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserLCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of user l c s messages
	 * @param end the upper bound of the range of user l c s messages (not inclusive)
	 * @return the range of matching user l c s messages
	 */
	@Override
	public List<UserLCSMessage> findByUserId(long userId, int start, int end) {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the user l c s messages where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserLCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of user l c s messages
	 * @param end the upper bound of the range of user l c s messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user l c s messages
	 */
	@Override
	public List<UserLCSMessage> findByUserId(long userId, int start, int end,
		OrderByComparator<UserLCSMessage> orderByComparator) {
		return findByUserId(userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the user l c s messages where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserLCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of user l c s messages
	 * @param end the upper bound of the range of user l c s messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching user l c s messages
	 */
	@Override
	public List<UserLCSMessage> findByUserId(long userId, int start, int end,
		OrderByComparator<UserLCSMessage> orderByComparator,
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

		List<UserLCSMessage> list = null;

		if (retrieveFromCache) {
			list = (List<UserLCSMessage>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (UserLCSMessage userLCSMessage : list) {
					if ((userId != userLCSMessage.getUserId())) {
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

			query.append(_SQL_SELECT_USERLCSMESSAGE_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(UserLCSMessageModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (!pagination) {
					list = (List<UserLCSMessage>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<UserLCSMessage>)QueryUtil.list(q,
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
	 * Returns the first user l c s message in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user l c s message
	 * @throws NoSuchUserLCSMessageException if a matching user l c s message could not be found
	 */
	@Override
	public UserLCSMessage findByUserId_First(long userId,
		OrderByComparator<UserLCSMessage> orderByComparator)
		throws NoSuchUserLCSMessageException {
		UserLCSMessage userLCSMessage = fetchByUserId_First(userId,
				orderByComparator);

		if (userLCSMessage != null) {
			return userLCSMessage;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserLCSMessageException(msg.toString());
	}

	/**
	 * Returns the first user l c s message in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user l c s message, or <code>null</code> if a matching user l c s message could not be found
	 */
	@Override
	public UserLCSMessage fetchByUserId_First(long userId,
		OrderByComparator<UserLCSMessage> orderByComparator) {
		List<UserLCSMessage> list = findByUserId(userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last user l c s message in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user l c s message
	 * @throws NoSuchUserLCSMessageException if a matching user l c s message could not be found
	 */
	@Override
	public UserLCSMessage findByUserId_Last(long userId,
		OrderByComparator<UserLCSMessage> orderByComparator)
		throws NoSuchUserLCSMessageException {
		UserLCSMessage userLCSMessage = fetchByUserId_Last(userId,
				orderByComparator);

		if (userLCSMessage != null) {
			return userLCSMessage;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserLCSMessageException(msg.toString());
	}

	/**
	 * Returns the last user l c s message in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user l c s message, or <code>null</code> if a matching user l c s message could not be found
	 */
	@Override
	public UserLCSMessage fetchByUserId_Last(long userId,
		OrderByComparator<UserLCSMessage> orderByComparator) {
		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<UserLCSMessage> list = findByUserId(userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the user l c s messages before and after the current user l c s message in the ordered set where userId = &#63;.
	 *
	 * @param userLcsMessageId the primary key of the current user l c s message
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user l c s message
	 * @throws NoSuchUserLCSMessageException if a user l c s message with the primary key could not be found
	 */
	@Override
	public UserLCSMessage[] findByUserId_PrevAndNext(long userLcsMessageId,
		long userId, OrderByComparator<UserLCSMessage> orderByComparator)
		throws NoSuchUserLCSMessageException {
		UserLCSMessage userLCSMessage = findByPrimaryKey(userLcsMessageId);

		Session session = null;

		try {
			session = openSession();

			UserLCSMessage[] array = new UserLCSMessageImpl[3];

			array[0] = getByUserId_PrevAndNext(session, userLCSMessage, userId,
					orderByComparator, true);

			array[1] = userLCSMessage;

			array[2] = getByUserId_PrevAndNext(session, userLCSMessage, userId,
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

	protected UserLCSMessage getByUserId_PrevAndNext(Session session,
		UserLCSMessage userLCSMessage, long userId,
		OrderByComparator<UserLCSMessage> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_USERLCSMESSAGE_WHERE);

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
			query.append(UserLCSMessageModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(userLCSMessage);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<UserLCSMessage> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the user l c s messages where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByUserId(long userId) {
		for (UserLCSMessage userLCSMessage : findByUserId(userId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(userLCSMessage);
		}
	}

	/**
	 * Returns the number of user l c s messages where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching user l c s messages
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_USERID;

		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_USERLCSMESSAGE_WHERE);

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

	private static final String _FINDER_COLUMN_USERID_USERID_2 = "userLCSMessage.userId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_LCSMESSAGEID =
		new FinderPath(UserLCSMessageModelImpl.ENTITY_CACHE_ENABLED,
			UserLCSMessageModelImpl.FINDER_CACHE_ENABLED,
			UserLCSMessageImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByLCSMessageId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LCSMESSAGEID =
		new FinderPath(UserLCSMessageModelImpl.ENTITY_CACHE_ENABLED,
			UserLCSMessageModelImpl.FINDER_CACHE_ENABLED,
			UserLCSMessageImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByLCSMessageId",
			new String[] { Long.class.getName() },
			UserLCSMessageModelImpl.LCSMESSAGEID_COLUMN_BITMASK |
			UserLCSMessageModelImpl.READ_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_LCSMESSAGEID = new FinderPath(UserLCSMessageModelImpl.ENTITY_CACHE_ENABLED,
			UserLCSMessageModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByLCSMessageId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the user l c s messages where lcsMessageId = &#63;.
	 *
	 * @param lcsMessageId the lcs message ID
	 * @return the matching user l c s messages
	 */
	@Override
	public List<UserLCSMessage> findByLCSMessageId(long lcsMessageId) {
		return findByLCSMessageId(lcsMessageId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the user l c s messages where lcsMessageId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserLCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsMessageId the lcs message ID
	 * @param start the lower bound of the range of user l c s messages
	 * @param end the upper bound of the range of user l c s messages (not inclusive)
	 * @return the range of matching user l c s messages
	 */
	@Override
	public List<UserLCSMessage> findByLCSMessageId(long lcsMessageId,
		int start, int end) {
		return findByLCSMessageId(lcsMessageId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the user l c s messages where lcsMessageId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserLCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsMessageId the lcs message ID
	 * @param start the lower bound of the range of user l c s messages
	 * @param end the upper bound of the range of user l c s messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user l c s messages
	 */
	@Override
	public List<UserLCSMessage> findByLCSMessageId(long lcsMessageId,
		int start, int end, OrderByComparator<UserLCSMessage> orderByComparator) {
		return findByLCSMessageId(lcsMessageId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the user l c s messages where lcsMessageId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserLCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsMessageId the lcs message ID
	 * @param start the lower bound of the range of user l c s messages
	 * @param end the upper bound of the range of user l c s messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching user l c s messages
	 */
	@Override
	public List<UserLCSMessage> findByLCSMessageId(long lcsMessageId,
		int start, int end,
		OrderByComparator<UserLCSMessage> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LCSMESSAGEID;
			finderArgs = new Object[] { lcsMessageId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_LCSMESSAGEID;
			finderArgs = new Object[] {
					lcsMessageId,
					
					start, end, orderByComparator
				};
		}

		List<UserLCSMessage> list = null;

		if (retrieveFromCache) {
			list = (List<UserLCSMessage>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (UserLCSMessage userLCSMessage : list) {
					if ((lcsMessageId != userLCSMessage.getLcsMessageId())) {
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

			query.append(_SQL_SELECT_USERLCSMESSAGE_WHERE);

			query.append(_FINDER_COLUMN_LCSMESSAGEID_LCSMESSAGEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(UserLCSMessageModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(lcsMessageId);

				if (!pagination) {
					list = (List<UserLCSMessage>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<UserLCSMessage>)QueryUtil.list(q,
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
	 * Returns the first user l c s message in the ordered set where lcsMessageId = &#63;.
	 *
	 * @param lcsMessageId the lcs message ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user l c s message
	 * @throws NoSuchUserLCSMessageException if a matching user l c s message could not be found
	 */
	@Override
	public UserLCSMessage findByLCSMessageId_First(long lcsMessageId,
		OrderByComparator<UserLCSMessage> orderByComparator)
		throws NoSuchUserLCSMessageException {
		UserLCSMessage userLCSMessage = fetchByLCSMessageId_First(lcsMessageId,
				orderByComparator);

		if (userLCSMessage != null) {
			return userLCSMessage;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("lcsMessageId=");
		msg.append(lcsMessageId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserLCSMessageException(msg.toString());
	}

	/**
	 * Returns the first user l c s message in the ordered set where lcsMessageId = &#63;.
	 *
	 * @param lcsMessageId the lcs message ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user l c s message, or <code>null</code> if a matching user l c s message could not be found
	 */
	@Override
	public UserLCSMessage fetchByLCSMessageId_First(long lcsMessageId,
		OrderByComparator<UserLCSMessage> orderByComparator) {
		List<UserLCSMessage> list = findByLCSMessageId(lcsMessageId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last user l c s message in the ordered set where lcsMessageId = &#63;.
	 *
	 * @param lcsMessageId the lcs message ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user l c s message
	 * @throws NoSuchUserLCSMessageException if a matching user l c s message could not be found
	 */
	@Override
	public UserLCSMessage findByLCSMessageId_Last(long lcsMessageId,
		OrderByComparator<UserLCSMessage> orderByComparator)
		throws NoSuchUserLCSMessageException {
		UserLCSMessage userLCSMessage = fetchByLCSMessageId_Last(lcsMessageId,
				orderByComparator);

		if (userLCSMessage != null) {
			return userLCSMessage;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("lcsMessageId=");
		msg.append(lcsMessageId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserLCSMessageException(msg.toString());
	}

	/**
	 * Returns the last user l c s message in the ordered set where lcsMessageId = &#63;.
	 *
	 * @param lcsMessageId the lcs message ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user l c s message, or <code>null</code> if a matching user l c s message could not be found
	 */
	@Override
	public UserLCSMessage fetchByLCSMessageId_Last(long lcsMessageId,
		OrderByComparator<UserLCSMessage> orderByComparator) {
		int count = countByLCSMessageId(lcsMessageId);

		if (count == 0) {
			return null;
		}

		List<UserLCSMessage> list = findByLCSMessageId(lcsMessageId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the user l c s messages before and after the current user l c s message in the ordered set where lcsMessageId = &#63;.
	 *
	 * @param userLcsMessageId the primary key of the current user l c s message
	 * @param lcsMessageId the lcs message ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user l c s message
	 * @throws NoSuchUserLCSMessageException if a user l c s message with the primary key could not be found
	 */
	@Override
	public UserLCSMessage[] findByLCSMessageId_PrevAndNext(
		long userLcsMessageId, long lcsMessageId,
		OrderByComparator<UserLCSMessage> orderByComparator)
		throws NoSuchUserLCSMessageException {
		UserLCSMessage userLCSMessage = findByPrimaryKey(userLcsMessageId);

		Session session = null;

		try {
			session = openSession();

			UserLCSMessage[] array = new UserLCSMessageImpl[3];

			array[0] = getByLCSMessageId_PrevAndNext(session, userLCSMessage,
					lcsMessageId, orderByComparator, true);

			array[1] = userLCSMessage;

			array[2] = getByLCSMessageId_PrevAndNext(session, userLCSMessage,
					lcsMessageId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected UserLCSMessage getByLCSMessageId_PrevAndNext(Session session,
		UserLCSMessage userLCSMessage, long lcsMessageId,
		OrderByComparator<UserLCSMessage> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_USERLCSMESSAGE_WHERE);

		query.append(_FINDER_COLUMN_LCSMESSAGEID_LCSMESSAGEID_2);

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
			query.append(UserLCSMessageModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(lcsMessageId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(userLCSMessage);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<UserLCSMessage> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the user l c s messages where lcsMessageId = &#63; from the database.
	 *
	 * @param lcsMessageId the lcs message ID
	 */
	@Override
	public void removeByLCSMessageId(long lcsMessageId) {
		for (UserLCSMessage userLCSMessage : findByLCSMessageId(lcsMessageId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(userLCSMessage);
		}
	}

	/**
	 * Returns the number of user l c s messages where lcsMessageId = &#63;.
	 *
	 * @param lcsMessageId the lcs message ID
	 * @return the number of matching user l c s messages
	 */
	@Override
	public int countByLCSMessageId(long lcsMessageId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_LCSMESSAGEID;

		Object[] finderArgs = new Object[] { lcsMessageId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_USERLCSMESSAGE_WHERE);

			query.append(_FINDER_COLUMN_LCSMESSAGEID_LCSMESSAGEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(lcsMessageId);

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

	private static final String _FINDER_COLUMN_LCSMESSAGEID_LCSMESSAGEID_2 = "userLCSMessage.lcsMessageId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_U_LMI = new FinderPath(UserLCSMessageModelImpl.ENTITY_CACHE_ENABLED,
			UserLCSMessageModelImpl.FINDER_CACHE_ENABLED,
			UserLCSMessageImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByU_LMI",
			new String[] { Long.class.getName(), Long.class.getName() },
			UserLCSMessageModelImpl.USERID_COLUMN_BITMASK |
			UserLCSMessageModelImpl.LCSMESSAGEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_LMI = new FinderPath(UserLCSMessageModelImpl.ENTITY_CACHE_ENABLED,
			UserLCSMessageModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_LMI",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns the user l c s message where userId = &#63; and lcsMessageId = &#63; or throws a {@link NoSuchUserLCSMessageException} if it could not be found.
	 *
	 * @param userId the user ID
	 * @param lcsMessageId the lcs message ID
	 * @return the matching user l c s message
	 * @throws NoSuchUserLCSMessageException if a matching user l c s message could not be found
	 */
	@Override
	public UserLCSMessage findByU_LMI(long userId, long lcsMessageId)
		throws NoSuchUserLCSMessageException {
		UserLCSMessage userLCSMessage = fetchByU_LMI(userId, lcsMessageId);

		if (userLCSMessage == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(", lcsMessageId=");
			msg.append(lcsMessageId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchUserLCSMessageException(msg.toString());
		}

		return userLCSMessage;
	}

	/**
	 * Returns the user l c s message where userId = &#63; and lcsMessageId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @param lcsMessageId the lcs message ID
	 * @return the matching user l c s message, or <code>null</code> if a matching user l c s message could not be found
	 */
	@Override
	public UserLCSMessage fetchByU_LMI(long userId, long lcsMessageId) {
		return fetchByU_LMI(userId, lcsMessageId, true);
	}

	/**
	 * Returns the user l c s message where userId = &#63; and lcsMessageId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param lcsMessageId the lcs message ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching user l c s message, or <code>null</code> if a matching user l c s message could not be found
	 */
	@Override
	public UserLCSMessage fetchByU_LMI(long userId, long lcsMessageId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { userId, lcsMessageId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_U_LMI,
					finderArgs, this);
		}

		if (result instanceof UserLCSMessage) {
			UserLCSMessage userLCSMessage = (UserLCSMessage)result;

			if ((userId != userLCSMessage.getUserId()) ||
					(lcsMessageId != userLCSMessage.getLcsMessageId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_USERLCSMESSAGE_WHERE);

			query.append(_FINDER_COLUMN_U_LMI_USERID_2);

			query.append(_FINDER_COLUMN_U_LMI_LCSMESSAGEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(lcsMessageId);

				List<UserLCSMessage> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_U_LMI,
						finderArgs, list);
				}
				else {
					UserLCSMessage userLCSMessage = list.get(0);

					result = userLCSMessage;

					cacheResult(userLCSMessage);

					if ((userLCSMessage.getUserId() != userId) ||
							(userLCSMessage.getLcsMessageId() != lcsMessageId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_U_LMI,
							finderArgs, userLCSMessage);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_U_LMI, finderArgs);

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
			return (UserLCSMessage)result;
		}
	}

	/**
	 * Removes the user l c s message where userId = &#63; and lcsMessageId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param lcsMessageId the lcs message ID
	 * @return the user l c s message that was removed
	 */
	@Override
	public UserLCSMessage removeByU_LMI(long userId, long lcsMessageId)
		throws NoSuchUserLCSMessageException {
		UserLCSMessage userLCSMessage = findByU_LMI(userId, lcsMessageId);

		return remove(userLCSMessage);
	}

	/**
	 * Returns the number of user l c s messages where userId = &#63; and lcsMessageId = &#63;.
	 *
	 * @param userId the user ID
	 * @param lcsMessageId the lcs message ID
	 * @return the number of matching user l c s messages
	 */
	@Override
	public int countByU_LMI(long userId, long lcsMessageId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_U_LMI;

		Object[] finderArgs = new Object[] { userId, lcsMessageId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_USERLCSMESSAGE_WHERE);

			query.append(_FINDER_COLUMN_U_LMI_USERID_2);

			query.append(_FINDER_COLUMN_U_LMI_LCSMESSAGEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(lcsMessageId);

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

	private static final String _FINDER_COLUMN_U_LMI_USERID_2 = "userLCSMessage.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_LMI_LCSMESSAGEID_2 = "userLCSMessage.lcsMessageId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_U_H = new FinderPath(UserLCSMessageModelImpl.ENTITY_CACHE_ENABLED,
			UserLCSMessageModelImpl.FINDER_CACHE_ENABLED,
			UserLCSMessageImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByU_H",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_H = new FinderPath(UserLCSMessageModelImpl.ENTITY_CACHE_ENABLED,
			UserLCSMessageModelImpl.FINDER_CACHE_ENABLED,
			UserLCSMessageImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByU_H",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			UserLCSMessageModelImpl.USERID_COLUMN_BITMASK |
			UserLCSMessageModelImpl.HIDDEN_COLUMN_BITMASK |
			UserLCSMessageModelImpl.LCSMESSAGEID_COLUMN_BITMASK |
			UserLCSMessageModelImpl.READ_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_H = new FinderPath(UserLCSMessageModelImpl.ENTITY_CACHE_ENABLED,
			UserLCSMessageModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_H",
			new String[] { Long.class.getName(), Boolean.class.getName() });

	/**
	 * Returns all the user l c s messages where userId = &#63; and hidden = &#63;.
	 *
	 * @param userId the user ID
	 * @param hidden the hidden
	 * @return the matching user l c s messages
	 */
	@Override
	public List<UserLCSMessage> findByU_H(long userId, boolean hidden) {
		return findByU_H(userId, hidden, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the user l c s messages where userId = &#63; and hidden = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserLCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param hidden the hidden
	 * @param start the lower bound of the range of user l c s messages
	 * @param end the upper bound of the range of user l c s messages (not inclusive)
	 * @return the range of matching user l c s messages
	 */
	@Override
	public List<UserLCSMessage> findByU_H(long userId, boolean hidden,
		int start, int end) {
		return findByU_H(userId, hidden, start, end, null);
	}

	/**
	 * Returns an ordered range of all the user l c s messages where userId = &#63; and hidden = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserLCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param hidden the hidden
	 * @param start the lower bound of the range of user l c s messages
	 * @param end the upper bound of the range of user l c s messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user l c s messages
	 */
	@Override
	public List<UserLCSMessage> findByU_H(long userId, boolean hidden,
		int start, int end, OrderByComparator<UserLCSMessage> orderByComparator) {
		return findByU_H(userId, hidden, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the user l c s messages where userId = &#63; and hidden = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserLCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param hidden the hidden
	 * @param start the lower bound of the range of user l c s messages
	 * @param end the upper bound of the range of user l c s messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching user l c s messages
	 */
	@Override
	public List<UserLCSMessage> findByU_H(long userId, boolean hidden,
		int start, int end,
		OrderByComparator<UserLCSMessage> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_H;
			finderArgs = new Object[] { userId, hidden };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_U_H;
			finderArgs = new Object[] {
					userId, hidden,
					
					start, end, orderByComparator
				};
		}

		List<UserLCSMessage> list = null;

		if (retrieveFromCache) {
			list = (List<UserLCSMessage>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (UserLCSMessage userLCSMessage : list) {
					if ((userId != userLCSMessage.getUserId()) ||
							(hidden != userLCSMessage.getHidden())) {
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

			query.append(_SQL_SELECT_USERLCSMESSAGE_WHERE);

			query.append(_FINDER_COLUMN_U_H_USERID_2);

			query.append(_FINDER_COLUMN_U_H_HIDDEN_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(UserLCSMessageModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(hidden);

				if (!pagination) {
					list = (List<UserLCSMessage>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<UserLCSMessage>)QueryUtil.list(q,
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
	 * Returns the first user l c s message in the ordered set where userId = &#63; and hidden = &#63;.
	 *
	 * @param userId the user ID
	 * @param hidden the hidden
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user l c s message
	 * @throws NoSuchUserLCSMessageException if a matching user l c s message could not be found
	 */
	@Override
	public UserLCSMessage findByU_H_First(long userId, boolean hidden,
		OrderByComparator<UserLCSMessage> orderByComparator)
		throws NoSuchUserLCSMessageException {
		UserLCSMessage userLCSMessage = fetchByU_H_First(userId, hidden,
				orderByComparator);

		if (userLCSMessage != null) {
			return userLCSMessage;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", hidden=");
		msg.append(hidden);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserLCSMessageException(msg.toString());
	}

	/**
	 * Returns the first user l c s message in the ordered set where userId = &#63; and hidden = &#63;.
	 *
	 * @param userId the user ID
	 * @param hidden the hidden
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user l c s message, or <code>null</code> if a matching user l c s message could not be found
	 */
	@Override
	public UserLCSMessage fetchByU_H_First(long userId, boolean hidden,
		OrderByComparator<UserLCSMessage> orderByComparator) {
		List<UserLCSMessage> list = findByU_H(userId, hidden, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last user l c s message in the ordered set where userId = &#63; and hidden = &#63;.
	 *
	 * @param userId the user ID
	 * @param hidden the hidden
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user l c s message
	 * @throws NoSuchUserLCSMessageException if a matching user l c s message could not be found
	 */
	@Override
	public UserLCSMessage findByU_H_Last(long userId, boolean hidden,
		OrderByComparator<UserLCSMessage> orderByComparator)
		throws NoSuchUserLCSMessageException {
		UserLCSMessage userLCSMessage = fetchByU_H_Last(userId, hidden,
				orderByComparator);

		if (userLCSMessage != null) {
			return userLCSMessage;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", hidden=");
		msg.append(hidden);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserLCSMessageException(msg.toString());
	}

	/**
	 * Returns the last user l c s message in the ordered set where userId = &#63; and hidden = &#63;.
	 *
	 * @param userId the user ID
	 * @param hidden the hidden
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user l c s message, or <code>null</code> if a matching user l c s message could not be found
	 */
	@Override
	public UserLCSMessage fetchByU_H_Last(long userId, boolean hidden,
		OrderByComparator<UserLCSMessage> orderByComparator) {
		int count = countByU_H(userId, hidden);

		if (count == 0) {
			return null;
		}

		List<UserLCSMessage> list = findByU_H(userId, hidden, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the user l c s messages before and after the current user l c s message in the ordered set where userId = &#63; and hidden = &#63;.
	 *
	 * @param userLcsMessageId the primary key of the current user l c s message
	 * @param userId the user ID
	 * @param hidden the hidden
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user l c s message
	 * @throws NoSuchUserLCSMessageException if a user l c s message with the primary key could not be found
	 */
	@Override
	public UserLCSMessage[] findByU_H_PrevAndNext(long userLcsMessageId,
		long userId, boolean hidden,
		OrderByComparator<UserLCSMessage> orderByComparator)
		throws NoSuchUserLCSMessageException {
		UserLCSMessage userLCSMessage = findByPrimaryKey(userLcsMessageId);

		Session session = null;

		try {
			session = openSession();

			UserLCSMessage[] array = new UserLCSMessageImpl[3];

			array[0] = getByU_H_PrevAndNext(session, userLCSMessage, userId,
					hidden, orderByComparator, true);

			array[1] = userLCSMessage;

			array[2] = getByU_H_PrevAndNext(session, userLCSMessage, userId,
					hidden, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected UserLCSMessage getByU_H_PrevAndNext(Session session,
		UserLCSMessage userLCSMessage, long userId, boolean hidden,
		OrderByComparator<UserLCSMessage> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_USERLCSMESSAGE_WHERE);

		query.append(_FINDER_COLUMN_U_H_USERID_2);

		query.append(_FINDER_COLUMN_U_H_HIDDEN_2);

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
			query.append(UserLCSMessageModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		qPos.add(hidden);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(userLCSMessage);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<UserLCSMessage> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the user l c s messages where userId = &#63; and hidden = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param hidden the hidden
	 */
	@Override
	public void removeByU_H(long userId, boolean hidden) {
		for (UserLCSMessage userLCSMessage : findByU_H(userId, hidden,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(userLCSMessage);
		}
	}

	/**
	 * Returns the number of user l c s messages where userId = &#63; and hidden = &#63;.
	 *
	 * @param userId the user ID
	 * @param hidden the hidden
	 * @return the number of matching user l c s messages
	 */
	@Override
	public int countByU_H(long userId, boolean hidden) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_U_H;

		Object[] finderArgs = new Object[] { userId, hidden };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_USERLCSMESSAGE_WHERE);

			query.append(_FINDER_COLUMN_U_H_USERID_2);

			query.append(_FINDER_COLUMN_U_H_HIDDEN_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(hidden);

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

	private static final String _FINDER_COLUMN_U_H_USERID_2 = "userLCSMessage.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_H_HIDDEN_2 = "userLCSMessage.hidden = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_U_R = new FinderPath(UserLCSMessageModelImpl.ENTITY_CACHE_ENABLED,
			UserLCSMessageModelImpl.FINDER_CACHE_ENABLED,
			UserLCSMessageImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByU_R",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_R = new FinderPath(UserLCSMessageModelImpl.ENTITY_CACHE_ENABLED,
			UserLCSMessageModelImpl.FINDER_CACHE_ENABLED,
			UserLCSMessageImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByU_R",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			UserLCSMessageModelImpl.USERID_COLUMN_BITMASK |
			UserLCSMessageModelImpl.READ_COLUMN_BITMASK |
			UserLCSMessageModelImpl.LCSMESSAGEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_R = new FinderPath(UserLCSMessageModelImpl.ENTITY_CACHE_ENABLED,
			UserLCSMessageModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_R",
			new String[] { Long.class.getName(), Boolean.class.getName() });

	/**
	 * Returns all the user l c s messages where userId = &#63; and read = &#63;.
	 *
	 * @param userId the user ID
	 * @param read the read
	 * @return the matching user l c s messages
	 */
	@Override
	public List<UserLCSMessage> findByU_R(long userId, boolean read) {
		return findByU_R(userId, read, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the user l c s messages where userId = &#63; and read = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserLCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param read the read
	 * @param start the lower bound of the range of user l c s messages
	 * @param end the upper bound of the range of user l c s messages (not inclusive)
	 * @return the range of matching user l c s messages
	 */
	@Override
	public List<UserLCSMessage> findByU_R(long userId, boolean read, int start,
		int end) {
		return findByU_R(userId, read, start, end, null);
	}

	/**
	 * Returns an ordered range of all the user l c s messages where userId = &#63; and read = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserLCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param read the read
	 * @param start the lower bound of the range of user l c s messages
	 * @param end the upper bound of the range of user l c s messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user l c s messages
	 */
	@Override
	public List<UserLCSMessage> findByU_R(long userId, boolean read, int start,
		int end, OrderByComparator<UserLCSMessage> orderByComparator) {
		return findByU_R(userId, read, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the user l c s messages where userId = &#63; and read = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserLCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param read the read
	 * @param start the lower bound of the range of user l c s messages
	 * @param end the upper bound of the range of user l c s messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching user l c s messages
	 */
	@Override
	public List<UserLCSMessage> findByU_R(long userId, boolean read, int start,
		int end, OrderByComparator<UserLCSMessage> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_R;
			finderArgs = new Object[] { userId, read };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_U_R;
			finderArgs = new Object[] {
					userId, read,
					
					start, end, orderByComparator
				};
		}

		List<UserLCSMessage> list = null;

		if (retrieveFromCache) {
			list = (List<UserLCSMessage>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (UserLCSMessage userLCSMessage : list) {
					if ((userId != userLCSMessage.getUserId()) ||
							(read != userLCSMessage.getRead())) {
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

			query.append(_SQL_SELECT_USERLCSMESSAGE_WHERE);

			query.append(_FINDER_COLUMN_U_R_USERID_2);

			query.append(_FINDER_COLUMN_U_R_READ_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(UserLCSMessageModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(read);

				if (!pagination) {
					list = (List<UserLCSMessage>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<UserLCSMessage>)QueryUtil.list(q,
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
	 * Returns the first user l c s message in the ordered set where userId = &#63; and read = &#63;.
	 *
	 * @param userId the user ID
	 * @param read the read
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user l c s message
	 * @throws NoSuchUserLCSMessageException if a matching user l c s message could not be found
	 */
	@Override
	public UserLCSMessage findByU_R_First(long userId, boolean read,
		OrderByComparator<UserLCSMessage> orderByComparator)
		throws NoSuchUserLCSMessageException {
		UserLCSMessage userLCSMessage = fetchByU_R_First(userId, read,
				orderByComparator);

		if (userLCSMessage != null) {
			return userLCSMessage;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", read=");
		msg.append(read);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserLCSMessageException(msg.toString());
	}

	/**
	 * Returns the first user l c s message in the ordered set where userId = &#63; and read = &#63;.
	 *
	 * @param userId the user ID
	 * @param read the read
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user l c s message, or <code>null</code> if a matching user l c s message could not be found
	 */
	@Override
	public UserLCSMessage fetchByU_R_First(long userId, boolean read,
		OrderByComparator<UserLCSMessage> orderByComparator) {
		List<UserLCSMessage> list = findByU_R(userId, read, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last user l c s message in the ordered set where userId = &#63; and read = &#63;.
	 *
	 * @param userId the user ID
	 * @param read the read
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user l c s message
	 * @throws NoSuchUserLCSMessageException if a matching user l c s message could not be found
	 */
	@Override
	public UserLCSMessage findByU_R_Last(long userId, boolean read,
		OrderByComparator<UserLCSMessage> orderByComparator)
		throws NoSuchUserLCSMessageException {
		UserLCSMessage userLCSMessage = fetchByU_R_Last(userId, read,
				orderByComparator);

		if (userLCSMessage != null) {
			return userLCSMessage;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", read=");
		msg.append(read);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserLCSMessageException(msg.toString());
	}

	/**
	 * Returns the last user l c s message in the ordered set where userId = &#63; and read = &#63;.
	 *
	 * @param userId the user ID
	 * @param read the read
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user l c s message, or <code>null</code> if a matching user l c s message could not be found
	 */
	@Override
	public UserLCSMessage fetchByU_R_Last(long userId, boolean read,
		OrderByComparator<UserLCSMessage> orderByComparator) {
		int count = countByU_R(userId, read);

		if (count == 0) {
			return null;
		}

		List<UserLCSMessage> list = findByU_R(userId, read, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the user l c s messages before and after the current user l c s message in the ordered set where userId = &#63; and read = &#63;.
	 *
	 * @param userLcsMessageId the primary key of the current user l c s message
	 * @param userId the user ID
	 * @param read the read
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user l c s message
	 * @throws NoSuchUserLCSMessageException if a user l c s message with the primary key could not be found
	 */
	@Override
	public UserLCSMessage[] findByU_R_PrevAndNext(long userLcsMessageId,
		long userId, boolean read,
		OrderByComparator<UserLCSMessage> orderByComparator)
		throws NoSuchUserLCSMessageException {
		UserLCSMessage userLCSMessage = findByPrimaryKey(userLcsMessageId);

		Session session = null;

		try {
			session = openSession();

			UserLCSMessage[] array = new UserLCSMessageImpl[3];

			array[0] = getByU_R_PrevAndNext(session, userLCSMessage, userId,
					read, orderByComparator, true);

			array[1] = userLCSMessage;

			array[2] = getByU_R_PrevAndNext(session, userLCSMessage, userId,
					read, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected UserLCSMessage getByU_R_PrevAndNext(Session session,
		UserLCSMessage userLCSMessage, long userId, boolean read,
		OrderByComparator<UserLCSMessage> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_USERLCSMESSAGE_WHERE);

		query.append(_FINDER_COLUMN_U_R_USERID_2);

		query.append(_FINDER_COLUMN_U_R_READ_2);

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
			query.append(UserLCSMessageModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		qPos.add(read);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(userLCSMessage);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<UserLCSMessage> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the user l c s messages where userId = &#63; and read = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param read the read
	 */
	@Override
	public void removeByU_R(long userId, boolean read) {
		for (UserLCSMessage userLCSMessage : findByU_R(userId, read,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(userLCSMessage);
		}
	}

	/**
	 * Returns the number of user l c s messages where userId = &#63; and read = &#63;.
	 *
	 * @param userId the user ID
	 * @param read the read
	 * @return the number of matching user l c s messages
	 */
	@Override
	public int countByU_R(long userId, boolean read) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_U_R;

		Object[] finderArgs = new Object[] { userId, read };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_USERLCSMESSAGE_WHERE);

			query.append(_FINDER_COLUMN_U_R_USERID_2);

			query.append(_FINDER_COLUMN_U_R_READ_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(read);

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

	private static final String _FINDER_COLUMN_U_R_USERID_2 = "userLCSMessage.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_R_READ_2 = "userLCSMessage.read = ?";

	public UserLCSMessagePersistenceImpl() {
		setModelClass(UserLCSMessage.class);
	}

	/**
	 * Caches the user l c s message in the entity cache if it is enabled.
	 *
	 * @param userLCSMessage the user l c s message
	 */
	@Override
	public void cacheResult(UserLCSMessage userLCSMessage) {
		entityCache.putResult(UserLCSMessageModelImpl.ENTITY_CACHE_ENABLED,
			UserLCSMessageImpl.class, userLCSMessage.getPrimaryKey(),
			userLCSMessage);

		finderCache.putResult(FINDER_PATH_FETCH_BY_U_LMI,
			new Object[] {
				userLCSMessage.getUserId(), userLCSMessage.getLcsMessageId()
			}, userLCSMessage);

		userLCSMessage.resetOriginalValues();
	}

	/**
	 * Caches the user l c s messages in the entity cache if it is enabled.
	 *
	 * @param userLCSMessages the user l c s messages
	 */
	@Override
	public void cacheResult(List<UserLCSMessage> userLCSMessages) {
		for (UserLCSMessage userLCSMessage : userLCSMessages) {
			if (entityCache.getResult(
						UserLCSMessageModelImpl.ENTITY_CACHE_ENABLED,
						UserLCSMessageImpl.class, userLCSMessage.getPrimaryKey()) == null) {
				cacheResult(userLCSMessage);
			}
			else {
				userLCSMessage.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all user l c s messages.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(UserLCSMessageImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the user l c s message.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(UserLCSMessage userLCSMessage) {
		entityCache.removeResult(UserLCSMessageModelImpl.ENTITY_CACHE_ENABLED,
			UserLCSMessageImpl.class, userLCSMessage.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((UserLCSMessageModelImpl)userLCSMessage, true);
	}

	@Override
	public void clearCache(List<UserLCSMessage> userLCSMessages) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (UserLCSMessage userLCSMessage : userLCSMessages) {
			entityCache.removeResult(UserLCSMessageModelImpl.ENTITY_CACHE_ENABLED,
				UserLCSMessageImpl.class, userLCSMessage.getPrimaryKey());

			clearUniqueFindersCache((UserLCSMessageModelImpl)userLCSMessage,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		UserLCSMessageModelImpl userLCSMessageModelImpl) {
		Object[] args = new Object[] {
				userLCSMessageModelImpl.getUserId(),
				userLCSMessageModelImpl.getLcsMessageId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_U_LMI, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_U_LMI, args,
			userLCSMessageModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		UserLCSMessageModelImpl userLCSMessageModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					userLCSMessageModelImpl.getUserId(),
					userLCSMessageModelImpl.getLcsMessageId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_U_LMI, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_U_LMI, args);
		}

		if ((userLCSMessageModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_U_LMI.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					userLCSMessageModelImpl.getOriginalUserId(),
					userLCSMessageModelImpl.getOriginalLcsMessageId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_U_LMI, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_U_LMI, args);
		}
	}

	/**
	 * Creates a new user l c s message with the primary key. Does not add the user l c s message to the database.
	 *
	 * @param userLcsMessageId the primary key for the new user l c s message
	 * @return the new user l c s message
	 */
	@Override
	public UserLCSMessage create(long userLcsMessageId) {
		UserLCSMessage userLCSMessage = new UserLCSMessageImpl();

		userLCSMessage.setNew(true);
		userLCSMessage.setPrimaryKey(userLcsMessageId);

		return userLCSMessage;
	}

	/**
	 * Removes the user l c s message with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userLcsMessageId the primary key of the user l c s message
	 * @return the user l c s message that was removed
	 * @throws NoSuchUserLCSMessageException if a user l c s message with the primary key could not be found
	 */
	@Override
	public UserLCSMessage remove(long userLcsMessageId)
		throws NoSuchUserLCSMessageException {
		return remove((Serializable)userLcsMessageId);
	}

	/**
	 * Removes the user l c s message with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the user l c s message
	 * @return the user l c s message that was removed
	 * @throws NoSuchUserLCSMessageException if a user l c s message with the primary key could not be found
	 */
	@Override
	public UserLCSMessage remove(Serializable primaryKey)
		throws NoSuchUserLCSMessageException {
		Session session = null;

		try {
			session = openSession();

			UserLCSMessage userLCSMessage = (UserLCSMessage)session.get(UserLCSMessageImpl.class,
					primaryKey);

			if (userLCSMessage == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchUserLCSMessageException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(userLCSMessage);
		}
		catch (NoSuchUserLCSMessageException nsee) {
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
	protected UserLCSMessage removeImpl(UserLCSMessage userLCSMessage) {
		userLCSMessage = toUnwrappedModel(userLCSMessage);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(userLCSMessage)) {
				userLCSMessage = (UserLCSMessage)session.get(UserLCSMessageImpl.class,
						userLCSMessage.getPrimaryKeyObj());
			}

			if (userLCSMessage != null) {
				session.delete(userLCSMessage);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (userLCSMessage != null) {
			clearCache(userLCSMessage);
		}

		return userLCSMessage;
	}

	@Override
	public UserLCSMessage updateImpl(UserLCSMessage userLCSMessage) {
		userLCSMessage = toUnwrappedModel(userLCSMessage);

		boolean isNew = userLCSMessage.isNew();

		UserLCSMessageModelImpl userLCSMessageModelImpl = (UserLCSMessageModelImpl)userLCSMessage;

		Session session = null;

		try {
			session = openSession();

			if (userLCSMessage.isNew()) {
				session.save(userLCSMessage);

				userLCSMessage.setNew(false);
			}
			else {
				userLCSMessage = (UserLCSMessage)session.merge(userLCSMessage);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !UserLCSMessageModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((userLCSMessageModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						userLCSMessageModelImpl.getOriginalUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] { userLCSMessageModelImpl.getUserId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}

			if ((userLCSMessageModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LCSMESSAGEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						userLCSMessageModelImpl.getOriginalLcsMessageId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_LCSMESSAGEID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LCSMESSAGEID,
					args);

				args = new Object[] { userLCSMessageModelImpl.getLcsMessageId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_LCSMESSAGEID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LCSMESSAGEID,
					args);
			}

			if ((userLCSMessageModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_H.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						userLCSMessageModelImpl.getOriginalUserId(),
						userLCSMessageModelImpl.getOriginalHidden()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_U_H, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_H,
					args);

				args = new Object[] {
						userLCSMessageModelImpl.getUserId(),
						userLCSMessageModelImpl.getHidden()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_U_H, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_H,
					args);
			}

			if ((userLCSMessageModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_R.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						userLCSMessageModelImpl.getOriginalUserId(),
						userLCSMessageModelImpl.getOriginalRead()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_U_R, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_R,
					args);

				args = new Object[] {
						userLCSMessageModelImpl.getUserId(),
						userLCSMessageModelImpl.getRead()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_U_R, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_R,
					args);
			}
		}

		entityCache.putResult(UserLCSMessageModelImpl.ENTITY_CACHE_ENABLED,
			UserLCSMessageImpl.class, userLCSMessage.getPrimaryKey(),
			userLCSMessage, false);

		clearUniqueFindersCache(userLCSMessageModelImpl, false);
		cacheUniqueFindersCache(userLCSMessageModelImpl);

		userLCSMessage.resetOriginalValues();

		return userLCSMessage;
	}

	protected UserLCSMessage toUnwrappedModel(UserLCSMessage userLCSMessage) {
		if (userLCSMessage instanceof UserLCSMessageImpl) {
			return userLCSMessage;
		}

		UserLCSMessageImpl userLCSMessageImpl = new UserLCSMessageImpl();

		userLCSMessageImpl.setNew(userLCSMessage.isNew());
		userLCSMessageImpl.setPrimaryKey(userLCSMessage.getPrimaryKey());

		userLCSMessageImpl.setUserLcsMessageId(userLCSMessage.getUserLcsMessageId());
		userLCSMessageImpl.setUserId(userLCSMessage.getUserId());
		userLCSMessageImpl.setLcsMessageId(userLCSMessage.getLcsMessageId());
		userLCSMessageImpl.setHidden(userLCSMessage.isHidden());
		userLCSMessageImpl.setRead(userLCSMessage.isRead());

		return userLCSMessageImpl;
	}

	/**
	 * Returns the user l c s message with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the user l c s message
	 * @return the user l c s message
	 * @throws NoSuchUserLCSMessageException if a user l c s message with the primary key could not be found
	 */
	@Override
	public UserLCSMessage findByPrimaryKey(Serializable primaryKey)
		throws NoSuchUserLCSMessageException {
		UserLCSMessage userLCSMessage = fetchByPrimaryKey(primaryKey);

		if (userLCSMessage == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchUserLCSMessageException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return userLCSMessage;
	}

	/**
	 * Returns the user l c s message with the primary key or throws a {@link NoSuchUserLCSMessageException} if it could not be found.
	 *
	 * @param userLcsMessageId the primary key of the user l c s message
	 * @return the user l c s message
	 * @throws NoSuchUserLCSMessageException if a user l c s message with the primary key could not be found
	 */
	@Override
	public UserLCSMessage findByPrimaryKey(long userLcsMessageId)
		throws NoSuchUserLCSMessageException {
		return findByPrimaryKey((Serializable)userLcsMessageId);
	}

	/**
	 * Returns the user l c s message with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the user l c s message
	 * @return the user l c s message, or <code>null</code> if a user l c s message with the primary key could not be found
	 */
	@Override
	public UserLCSMessage fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(UserLCSMessageModelImpl.ENTITY_CACHE_ENABLED,
				UserLCSMessageImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		UserLCSMessage userLCSMessage = (UserLCSMessage)serializable;

		if (userLCSMessage == null) {
			Session session = null;

			try {
				session = openSession();

				userLCSMessage = (UserLCSMessage)session.get(UserLCSMessageImpl.class,
						primaryKey);

				if (userLCSMessage != null) {
					cacheResult(userLCSMessage);
				}
				else {
					entityCache.putResult(UserLCSMessageModelImpl.ENTITY_CACHE_ENABLED,
						UserLCSMessageImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(UserLCSMessageModelImpl.ENTITY_CACHE_ENABLED,
					UserLCSMessageImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return userLCSMessage;
	}

	/**
	 * Returns the user l c s message with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param userLcsMessageId the primary key of the user l c s message
	 * @return the user l c s message, or <code>null</code> if a user l c s message with the primary key could not be found
	 */
	@Override
	public UserLCSMessage fetchByPrimaryKey(long userLcsMessageId) {
		return fetchByPrimaryKey((Serializable)userLcsMessageId);
	}

	@Override
	public Map<Serializable, UserLCSMessage> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, UserLCSMessage> map = new HashMap<Serializable, UserLCSMessage>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			UserLCSMessage userLCSMessage = fetchByPrimaryKey(primaryKey);

			if (userLCSMessage != null) {
				map.put(primaryKey, userLCSMessage);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(UserLCSMessageModelImpl.ENTITY_CACHE_ENABLED,
					UserLCSMessageImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (UserLCSMessage)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_USERLCSMESSAGE_WHERE_PKS_IN);

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

			for (UserLCSMessage userLCSMessage : (List<UserLCSMessage>)q.list()) {
				map.put(userLCSMessage.getPrimaryKeyObj(), userLCSMessage);

				cacheResult(userLCSMessage);

				uncachedPrimaryKeys.remove(userLCSMessage.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(UserLCSMessageModelImpl.ENTITY_CACHE_ENABLED,
					UserLCSMessageImpl.class, primaryKey, nullModel);
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
	 * Returns all the user l c s messages.
	 *
	 * @return the user l c s messages
	 */
	@Override
	public List<UserLCSMessage> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the user l c s messages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserLCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of user l c s messages
	 * @param end the upper bound of the range of user l c s messages (not inclusive)
	 * @return the range of user l c s messages
	 */
	@Override
	public List<UserLCSMessage> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the user l c s messages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserLCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of user l c s messages
	 * @param end the upper bound of the range of user l c s messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of user l c s messages
	 */
	@Override
	public List<UserLCSMessage> findAll(int start, int end,
		OrderByComparator<UserLCSMessage> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the user l c s messages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserLCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of user l c s messages
	 * @param end the upper bound of the range of user l c s messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of user l c s messages
	 */
	@Override
	public List<UserLCSMessage> findAll(int start, int end,
		OrderByComparator<UserLCSMessage> orderByComparator,
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

		List<UserLCSMessage> list = null;

		if (retrieveFromCache) {
			list = (List<UserLCSMessage>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_USERLCSMESSAGE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_USERLCSMESSAGE;

				if (pagination) {
					sql = sql.concat(UserLCSMessageModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<UserLCSMessage>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<UserLCSMessage>)QueryUtil.list(q,
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
	 * Removes all the user l c s messages from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (UserLCSMessage userLCSMessage : findAll()) {
			remove(userLCSMessage);
		}
	}

	/**
	 * Returns the number of user l c s messages.
	 *
	 * @return the number of user l c s messages
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_USERLCSMESSAGE);

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
		return UserLCSMessageModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the user l c s message persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(UserLCSMessageImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_USERLCSMESSAGE = "SELECT userLCSMessage FROM UserLCSMessage userLCSMessage";
	private static final String _SQL_SELECT_USERLCSMESSAGE_WHERE_PKS_IN = "SELECT userLCSMessage FROM UserLCSMessage userLCSMessage WHERE userLcsMessageId IN (";
	private static final String _SQL_SELECT_USERLCSMESSAGE_WHERE = "SELECT userLCSMessage FROM UserLCSMessage userLCSMessage WHERE ";
	private static final String _SQL_COUNT_USERLCSMESSAGE = "SELECT COUNT(userLCSMessage) FROM UserLCSMessage userLCSMessage";
	private static final String _SQL_COUNT_USERLCSMESSAGE_WHERE = "SELECT COUNT(userLCSMessage) FROM UserLCSMessage userLCSMessage WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "userLCSMessage.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No UserLCSMessage exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No UserLCSMessage exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(UserLCSMessagePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"hidden", "read"
			});
}