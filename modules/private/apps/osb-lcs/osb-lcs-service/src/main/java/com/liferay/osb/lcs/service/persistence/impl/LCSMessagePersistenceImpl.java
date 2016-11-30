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

import com.liferay.osb.lcs.exception.NoSuchLCSMessageException;
import com.liferay.osb.lcs.model.LCSMessage;
import com.liferay.osb.lcs.model.impl.LCSMessageImpl;
import com.liferay.osb.lcs.model.impl.LCSMessageModelImpl;
import com.liferay.osb.lcs.service.persistence.LCSMessagePersistence;

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
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.sql.Timestamp;

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
 * The persistence implementation for the l c s message service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Igor Beslic
 * @see LCSMessagePersistence
 * @see com.liferay.osb.lcs.service.persistence.LCSMessageUtil
 * @generated
 */
@ProviderType
public class LCSMessagePersistenceImpl extends BasePersistenceImpl<LCSMessage>
	implements LCSMessagePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link LCSMessageUtil} to access the l c s message persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = LCSMessageImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(LCSMessageModelImpl.ENTITY_CACHE_ENABLED,
			LCSMessageModelImpl.FINDER_CACHE_ENABLED, LCSMessageImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(LCSMessageModelImpl.ENTITY_CACHE_ENABLED,
			LCSMessageModelImpl.FINDER_CACHE_ENABLED, LCSMessageImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(LCSMessageModelImpl.ENTITY_CACHE_ENABLED,
			LCSMessageModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C = new FinderPath(LCSMessageModelImpl.ENTITY_CACHE_ENABLED,
			LCSMessageModelImpl.FINDER_CACHE_ENABLED, LCSMessageImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_C",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C = new FinderPath(LCSMessageModelImpl.ENTITY_CACHE_ENABLED,
			LCSMessageModelImpl.FINDER_CACHE_ENABLED, LCSMessageImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C",
			new String[] { Long.class.getName(), Long.class.getName() },
			LCSMessageModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			LCSMessageModelImpl.CLASSPK_COLUMN_BITMASK |
			LCSMessageModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C = new FinderPath(LCSMessageModelImpl.ENTITY_CACHE_ENABLED,
			LCSMessageModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the l c s messages where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the matching l c s messages
	 */
	@Override
	public List<LCSMessage> findByC_C(long classNameId, long classPK) {
		return findByC_C(classNameId, classPK, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the l c s messages where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param start the lower bound of the range of l c s messages
	 * @param end the upper bound of the range of l c s messages (not inclusive)
	 * @return the range of matching l c s messages
	 */
	@Override
	public List<LCSMessage> findByC_C(long classNameId, long classPK,
		int start, int end) {
		return findByC_C(classNameId, classPK, start, end, null);
	}

	/**
	 * Returns an ordered range of all the l c s messages where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param start the lower bound of the range of l c s messages
	 * @param end the upper bound of the range of l c s messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching l c s messages
	 */
	@Override
	public List<LCSMessage> findByC_C(long classNameId, long classPK,
		int start, int end, OrderByComparator<LCSMessage> orderByComparator) {
		return findByC_C(classNameId, classPK, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the l c s messages where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param start the lower bound of the range of l c s messages
	 * @param end the upper bound of the range of l c s messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching l c s messages
	 */
	@Override
	public List<LCSMessage> findByC_C(long classNameId, long classPK,
		int start, int end, OrderByComparator<LCSMessage> orderByComparator,
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

		List<LCSMessage> list = null;

		if (retrieveFromCache) {
			list = (List<LCSMessage>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LCSMessage lcsMessage : list) {
					if ((classNameId != lcsMessage.getClassNameId()) ||
							(classPK != lcsMessage.getClassPK())) {
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

			query.append(_SQL_SELECT_LCSMESSAGE_WHERE);

			query.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_CLASSPK_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LCSMessageModelImpl.ORDER_BY_JPQL);
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
					list = (List<LCSMessage>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSMessage>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first l c s message in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s message
	 * @throws NoSuchLCSMessageException if a matching l c s message could not be found
	 */
	@Override
	public LCSMessage findByC_C_First(long classNameId, long classPK,
		OrderByComparator<LCSMessage> orderByComparator)
		throws NoSuchLCSMessageException {
		LCSMessage lcsMessage = fetchByC_C_First(classNameId, classPK,
				orderByComparator);

		if (lcsMessage != null) {
			return lcsMessage;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSMessageException(msg.toString());
	}

	/**
	 * Returns the first l c s message in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s message, or <code>null</code> if a matching l c s message could not be found
	 */
	@Override
	public LCSMessage fetchByC_C_First(long classNameId, long classPK,
		OrderByComparator<LCSMessage> orderByComparator) {
		List<LCSMessage> list = findByC_C(classNameId, classPK, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last l c s message in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s message
	 * @throws NoSuchLCSMessageException if a matching l c s message could not be found
	 */
	@Override
	public LCSMessage findByC_C_Last(long classNameId, long classPK,
		OrderByComparator<LCSMessage> orderByComparator)
		throws NoSuchLCSMessageException {
		LCSMessage lcsMessage = fetchByC_C_Last(classNameId, classPK,
				orderByComparator);

		if (lcsMessage != null) {
			return lcsMessage;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSMessageException(msg.toString());
	}

	/**
	 * Returns the last l c s message in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s message, or <code>null</code> if a matching l c s message could not be found
	 */
	@Override
	public LCSMessage fetchByC_C_Last(long classNameId, long classPK,
		OrderByComparator<LCSMessage> orderByComparator) {
		int count = countByC_C(classNameId, classPK);

		if (count == 0) {
			return null;
		}

		List<LCSMessage> list = findByC_C(classNameId, classPK, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the l c s messages before and after the current l c s message in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param lcsMessageId the primary key of the current l c s message
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next l c s message
	 * @throws NoSuchLCSMessageException if a l c s message with the primary key could not be found
	 */
	@Override
	public LCSMessage[] findByC_C_PrevAndNext(long lcsMessageId,
		long classNameId, long classPK,
		OrderByComparator<LCSMessage> orderByComparator)
		throws NoSuchLCSMessageException {
		LCSMessage lcsMessage = findByPrimaryKey(lcsMessageId);

		Session session = null;

		try {
			session = openSession();

			LCSMessage[] array = new LCSMessageImpl[3];

			array[0] = getByC_C_PrevAndNext(session, lcsMessage, classNameId,
					classPK, orderByComparator, true);

			array[1] = lcsMessage;

			array[2] = getByC_C_PrevAndNext(session, lcsMessage, classNameId,
					classPK, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LCSMessage getByC_C_PrevAndNext(Session session,
		LCSMessage lcsMessage, long classNameId, long classPK,
		OrderByComparator<LCSMessage> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_LCSMESSAGE_WHERE);

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
			query.append(LCSMessageModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(classNameId);

		qPos.add(classPK);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(lcsMessage);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LCSMessage> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the l c s messages where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 */
	@Override
	public void removeByC_C(long classNameId, long classPK) {
		for (LCSMessage lcsMessage : findByC_C(classNameId, classPK,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(lcsMessage);
		}
	}

	/**
	 * Returns the number of l c s messages where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the number of matching l c s messages
	 */
	@Override
	public int countByC_C(long classNameId, long classPK) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_C;

		Object[] finderArgs = new Object[] { classNameId, classPK };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LCSMESSAGE_WHERE);

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

	private static final String _FINDER_COLUMN_C_C_CLASSNAMEID_2 = "lcsMessage.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_CLASSPK_2 = "lcsMessage.classPK = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ED_G = new FinderPath(LCSMessageModelImpl.ENTITY_CACHE_ENABLED,
			LCSMessageModelImpl.FINDER_CACHE_ENABLED, LCSMessageImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByED_G",
			new String[] {
				Date.class.getName(), Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_ED_G = new FinderPath(LCSMessageModelImpl.ENTITY_CACHE_ENABLED,
			LCSMessageModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByED_G",
			new String[] { Date.class.getName(), Boolean.class.getName() });

	/**
	 * Returns all the l c s messages where endDate &ge; &#63; and global = &#63;.
	 *
	 * @param endDate the end date
	 * @param global the global
	 * @return the matching l c s messages
	 */
	@Override
	public List<LCSMessage> findByED_G(Date endDate, boolean global) {
		return findByED_G(endDate, global, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the l c s messages where endDate &ge; &#63; and global = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param endDate the end date
	 * @param global the global
	 * @param start the lower bound of the range of l c s messages
	 * @param end the upper bound of the range of l c s messages (not inclusive)
	 * @return the range of matching l c s messages
	 */
	@Override
	public List<LCSMessage> findByED_G(Date endDate, boolean global, int start,
		int end) {
		return findByED_G(endDate, global, start, end, null);
	}

	/**
	 * Returns an ordered range of all the l c s messages where endDate &ge; &#63; and global = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param endDate the end date
	 * @param global the global
	 * @param start the lower bound of the range of l c s messages
	 * @param end the upper bound of the range of l c s messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching l c s messages
	 */
	@Override
	public List<LCSMessage> findByED_G(Date endDate, boolean global, int start,
		int end, OrderByComparator<LCSMessage> orderByComparator) {
		return findByED_G(endDate, global, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the l c s messages where endDate &ge; &#63; and global = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param endDate the end date
	 * @param global the global
	 * @param start the lower bound of the range of l c s messages
	 * @param end the upper bound of the range of l c s messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching l c s messages
	 */
	@Override
	public List<LCSMessage> findByED_G(Date endDate, boolean global, int start,
		int end, OrderByComparator<LCSMessage> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ED_G;
		finderArgs = new Object[] { endDate, global, start, end, orderByComparator };

		List<LCSMessage> list = null;

		if (retrieveFromCache) {
			list = (List<LCSMessage>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LCSMessage lcsMessage : list) {
					if ((endDate.getTime() > lcsMessage.getEndDate().getTime()) ||
							(global != lcsMessage.getGlobal())) {
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

			query.append(_SQL_SELECT_LCSMESSAGE_WHERE);

			boolean bindEndDate = false;

			if (endDate == null) {
				query.append(_FINDER_COLUMN_ED_G_ENDDATE_1);
			}
			else {
				bindEndDate = true;

				query.append(_FINDER_COLUMN_ED_G_ENDDATE_2);
			}

			query.append(_FINDER_COLUMN_ED_G_GLOBAL_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LCSMessageModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindEndDate) {
					qPos.add(new Timestamp(endDate.getTime()));
				}

				qPos.add(global);

				if (!pagination) {
					list = (List<LCSMessage>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSMessage>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first l c s message in the ordered set where endDate &ge; &#63; and global = &#63;.
	 *
	 * @param endDate the end date
	 * @param global the global
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s message
	 * @throws NoSuchLCSMessageException if a matching l c s message could not be found
	 */
	@Override
	public LCSMessage findByED_G_First(Date endDate, boolean global,
		OrderByComparator<LCSMessage> orderByComparator)
		throws NoSuchLCSMessageException {
		LCSMessage lcsMessage = fetchByED_G_First(endDate, global,
				orderByComparator);

		if (lcsMessage != null) {
			return lcsMessage;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("endDate=");
		msg.append(endDate);

		msg.append(", global=");
		msg.append(global);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSMessageException(msg.toString());
	}

	/**
	 * Returns the first l c s message in the ordered set where endDate &ge; &#63; and global = &#63;.
	 *
	 * @param endDate the end date
	 * @param global the global
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s message, or <code>null</code> if a matching l c s message could not be found
	 */
	@Override
	public LCSMessage fetchByED_G_First(Date endDate, boolean global,
		OrderByComparator<LCSMessage> orderByComparator) {
		List<LCSMessage> list = findByED_G(endDate, global, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last l c s message in the ordered set where endDate &ge; &#63; and global = &#63;.
	 *
	 * @param endDate the end date
	 * @param global the global
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s message
	 * @throws NoSuchLCSMessageException if a matching l c s message could not be found
	 */
	@Override
	public LCSMessage findByED_G_Last(Date endDate, boolean global,
		OrderByComparator<LCSMessage> orderByComparator)
		throws NoSuchLCSMessageException {
		LCSMessage lcsMessage = fetchByED_G_Last(endDate, global,
				orderByComparator);

		if (lcsMessage != null) {
			return lcsMessage;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("endDate=");
		msg.append(endDate);

		msg.append(", global=");
		msg.append(global);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSMessageException(msg.toString());
	}

	/**
	 * Returns the last l c s message in the ordered set where endDate &ge; &#63; and global = &#63;.
	 *
	 * @param endDate the end date
	 * @param global the global
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s message, or <code>null</code> if a matching l c s message could not be found
	 */
	@Override
	public LCSMessage fetchByED_G_Last(Date endDate, boolean global,
		OrderByComparator<LCSMessage> orderByComparator) {
		int count = countByED_G(endDate, global);

		if (count == 0) {
			return null;
		}

		List<LCSMessage> list = findByED_G(endDate, global, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the l c s messages before and after the current l c s message in the ordered set where endDate &ge; &#63; and global = &#63;.
	 *
	 * @param lcsMessageId the primary key of the current l c s message
	 * @param endDate the end date
	 * @param global the global
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next l c s message
	 * @throws NoSuchLCSMessageException if a l c s message with the primary key could not be found
	 */
	@Override
	public LCSMessage[] findByED_G_PrevAndNext(long lcsMessageId, Date endDate,
		boolean global, OrderByComparator<LCSMessage> orderByComparator)
		throws NoSuchLCSMessageException {
		LCSMessage lcsMessage = findByPrimaryKey(lcsMessageId);

		Session session = null;

		try {
			session = openSession();

			LCSMessage[] array = new LCSMessageImpl[3];

			array[0] = getByED_G_PrevAndNext(session, lcsMessage, endDate,
					global, orderByComparator, true);

			array[1] = lcsMessage;

			array[2] = getByED_G_PrevAndNext(session, lcsMessage, endDate,
					global, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LCSMessage getByED_G_PrevAndNext(Session session,
		LCSMessage lcsMessage, Date endDate, boolean global,
		OrderByComparator<LCSMessage> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_LCSMESSAGE_WHERE);

		boolean bindEndDate = false;

		if (endDate == null) {
			query.append(_FINDER_COLUMN_ED_G_ENDDATE_1);
		}
		else {
			bindEndDate = true;

			query.append(_FINDER_COLUMN_ED_G_ENDDATE_2);
		}

		query.append(_FINDER_COLUMN_ED_G_GLOBAL_2);

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
			query.append(LCSMessageModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindEndDate) {
			qPos.add(new Timestamp(endDate.getTime()));
		}

		qPos.add(global);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(lcsMessage);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LCSMessage> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the l c s messages where endDate &ge; &#63; and global = &#63; from the database.
	 *
	 * @param endDate the end date
	 * @param global the global
	 */
	@Override
	public void removeByED_G(Date endDate, boolean global) {
		for (LCSMessage lcsMessage : findByED_G(endDate, global,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(lcsMessage);
		}
	}

	/**
	 * Returns the number of l c s messages where endDate &ge; &#63; and global = &#63;.
	 *
	 * @param endDate the end date
	 * @param global the global
	 * @return the number of matching l c s messages
	 */
	@Override
	public int countByED_G(Date endDate, boolean global) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_ED_G;

		Object[] finderArgs = new Object[] { endDate, global };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LCSMESSAGE_WHERE);

			boolean bindEndDate = false;

			if (endDate == null) {
				query.append(_FINDER_COLUMN_ED_G_ENDDATE_1);
			}
			else {
				bindEndDate = true;

				query.append(_FINDER_COLUMN_ED_G_ENDDATE_2);
			}

			query.append(_FINDER_COLUMN_ED_G_GLOBAL_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindEndDate) {
					qPos.add(new Timestamp(endDate.getTime()));
				}

				qPos.add(global);

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

	private static final String _FINDER_COLUMN_ED_G_ENDDATE_1 = "lcsMessage.endDate IS NULL AND ";
	private static final String _FINDER_COLUMN_ED_G_ENDDATE_2 = "lcsMessage.endDate >= ? AND ";
	private static final String _FINDER_COLUMN_ED_G_GLOBAL_2 = "lcsMessage.global = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_S_C_C = new FinderPath(LCSMessageModelImpl.ENTITY_CACHE_ENABLED,
			LCSMessageModelImpl.FINDER_CACHE_ENABLED, LCSMessageImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByS_C_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_S_C_C = new FinderPath(LCSMessageModelImpl.ENTITY_CACHE_ENABLED,
			LCSMessageModelImpl.FINDER_CACHE_ENABLED, LCSMessageImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByS_C_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Long.class.getName()
			},
			LCSMessageModelImpl.SOURCESYSTEMNAME_COLUMN_BITMASK |
			LCSMessageModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			LCSMessageModelImpl.CLASSPK_COLUMN_BITMASK |
			LCSMessageModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_S_C_C = new FinderPath(LCSMessageModelImpl.ENTITY_CACHE_ENABLED,
			LCSMessageModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByS_C_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Long.class.getName()
			});

	/**
	 * Returns all the l c s messages where sourceSystemName = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param sourceSystemName the source system name
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the matching l c s messages
	 */
	@Override
	public List<LCSMessage> findByS_C_C(String sourceSystemName,
		long classNameId, long classPK) {
		return findByS_C_C(sourceSystemName, classNameId, classPK,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the l c s messages where sourceSystemName = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param sourceSystemName the source system name
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param start the lower bound of the range of l c s messages
	 * @param end the upper bound of the range of l c s messages (not inclusive)
	 * @return the range of matching l c s messages
	 */
	@Override
	public List<LCSMessage> findByS_C_C(String sourceSystemName,
		long classNameId, long classPK, int start, int end) {
		return findByS_C_C(sourceSystemName, classNameId, classPK, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the l c s messages where sourceSystemName = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param sourceSystemName the source system name
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param start the lower bound of the range of l c s messages
	 * @param end the upper bound of the range of l c s messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching l c s messages
	 */
	@Override
	public List<LCSMessage> findByS_C_C(String sourceSystemName,
		long classNameId, long classPK, int start, int end,
		OrderByComparator<LCSMessage> orderByComparator) {
		return findByS_C_C(sourceSystemName, classNameId, classPK, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the l c s messages where sourceSystemName = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param sourceSystemName the source system name
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param start the lower bound of the range of l c s messages
	 * @param end the upper bound of the range of l c s messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching l c s messages
	 */
	@Override
	public List<LCSMessage> findByS_C_C(String sourceSystemName,
		long classNameId, long classPK, int start, int end,
		OrderByComparator<LCSMessage> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_S_C_C;
			finderArgs = new Object[] { sourceSystemName, classNameId, classPK };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_S_C_C;
			finderArgs = new Object[] {
					sourceSystemName, classNameId, classPK,
					
					start, end, orderByComparator
				};
		}

		List<LCSMessage> list = null;

		if (retrieveFromCache) {
			list = (List<LCSMessage>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LCSMessage lcsMessage : list) {
					if (!Objects.equals(sourceSystemName,
								lcsMessage.getSourceSystemName()) ||
							(classNameId != lcsMessage.getClassNameId()) ||
							(classPK != lcsMessage.getClassPK())) {
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

			query.append(_SQL_SELECT_LCSMESSAGE_WHERE);

			boolean bindSourceSystemName = false;

			if (sourceSystemName == null) {
				query.append(_FINDER_COLUMN_S_C_C_SOURCESYSTEMNAME_1);
			}
			else if (sourceSystemName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_S_C_C_SOURCESYSTEMNAME_3);
			}
			else {
				bindSourceSystemName = true;

				query.append(_FINDER_COLUMN_S_C_C_SOURCESYSTEMNAME_2);
			}

			query.append(_FINDER_COLUMN_S_C_C_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_S_C_C_CLASSPK_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LCSMessageModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindSourceSystemName) {
					qPos.add(sourceSystemName);
				}

				qPos.add(classNameId);

				qPos.add(classPK);

				if (!pagination) {
					list = (List<LCSMessage>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSMessage>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first l c s message in the ordered set where sourceSystemName = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param sourceSystemName the source system name
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s message
	 * @throws NoSuchLCSMessageException if a matching l c s message could not be found
	 */
	@Override
	public LCSMessage findByS_C_C_First(String sourceSystemName,
		long classNameId, long classPK,
		OrderByComparator<LCSMessage> orderByComparator)
		throws NoSuchLCSMessageException {
		LCSMessage lcsMessage = fetchByS_C_C_First(sourceSystemName,
				classNameId, classPK, orderByComparator);

		if (lcsMessage != null) {
			return lcsMessage;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("sourceSystemName=");
		msg.append(sourceSystemName);

		msg.append(", classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSMessageException(msg.toString());
	}

	/**
	 * Returns the first l c s message in the ordered set where sourceSystemName = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param sourceSystemName the source system name
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s message, or <code>null</code> if a matching l c s message could not be found
	 */
	@Override
	public LCSMessage fetchByS_C_C_First(String sourceSystemName,
		long classNameId, long classPK,
		OrderByComparator<LCSMessage> orderByComparator) {
		List<LCSMessage> list = findByS_C_C(sourceSystemName, classNameId,
				classPK, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last l c s message in the ordered set where sourceSystemName = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param sourceSystemName the source system name
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s message
	 * @throws NoSuchLCSMessageException if a matching l c s message could not be found
	 */
	@Override
	public LCSMessage findByS_C_C_Last(String sourceSystemName,
		long classNameId, long classPK,
		OrderByComparator<LCSMessage> orderByComparator)
		throws NoSuchLCSMessageException {
		LCSMessage lcsMessage = fetchByS_C_C_Last(sourceSystemName,
				classNameId, classPK, orderByComparator);

		if (lcsMessage != null) {
			return lcsMessage;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("sourceSystemName=");
		msg.append(sourceSystemName);

		msg.append(", classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSMessageException(msg.toString());
	}

	/**
	 * Returns the last l c s message in the ordered set where sourceSystemName = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param sourceSystemName the source system name
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s message, or <code>null</code> if a matching l c s message could not be found
	 */
	@Override
	public LCSMessage fetchByS_C_C_Last(String sourceSystemName,
		long classNameId, long classPK,
		OrderByComparator<LCSMessage> orderByComparator) {
		int count = countByS_C_C(sourceSystemName, classNameId, classPK);

		if (count == 0) {
			return null;
		}

		List<LCSMessage> list = findByS_C_C(sourceSystemName, classNameId,
				classPK, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the l c s messages before and after the current l c s message in the ordered set where sourceSystemName = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param lcsMessageId the primary key of the current l c s message
	 * @param sourceSystemName the source system name
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next l c s message
	 * @throws NoSuchLCSMessageException if a l c s message with the primary key could not be found
	 */
	@Override
	public LCSMessage[] findByS_C_C_PrevAndNext(long lcsMessageId,
		String sourceSystemName, long classNameId, long classPK,
		OrderByComparator<LCSMessage> orderByComparator)
		throws NoSuchLCSMessageException {
		LCSMessage lcsMessage = findByPrimaryKey(lcsMessageId);

		Session session = null;

		try {
			session = openSession();

			LCSMessage[] array = new LCSMessageImpl[3];

			array[0] = getByS_C_C_PrevAndNext(session, lcsMessage,
					sourceSystemName, classNameId, classPK, orderByComparator,
					true);

			array[1] = lcsMessage;

			array[2] = getByS_C_C_PrevAndNext(session, lcsMessage,
					sourceSystemName, classNameId, classPK, orderByComparator,
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

	protected LCSMessage getByS_C_C_PrevAndNext(Session session,
		LCSMessage lcsMessage, String sourceSystemName, long classNameId,
		long classPK, OrderByComparator<LCSMessage> orderByComparator,
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

		query.append(_SQL_SELECT_LCSMESSAGE_WHERE);

		boolean bindSourceSystemName = false;

		if (sourceSystemName == null) {
			query.append(_FINDER_COLUMN_S_C_C_SOURCESYSTEMNAME_1);
		}
		else if (sourceSystemName.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_S_C_C_SOURCESYSTEMNAME_3);
		}
		else {
			bindSourceSystemName = true;

			query.append(_FINDER_COLUMN_S_C_C_SOURCESYSTEMNAME_2);
		}

		query.append(_FINDER_COLUMN_S_C_C_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_S_C_C_CLASSPK_2);

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
			query.append(LCSMessageModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindSourceSystemName) {
			qPos.add(sourceSystemName);
		}

		qPos.add(classNameId);

		qPos.add(classPK);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(lcsMessage);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LCSMessage> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the l c s messages where sourceSystemName = &#63; and classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param sourceSystemName the source system name
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 */
	@Override
	public void removeByS_C_C(String sourceSystemName, long classNameId,
		long classPK) {
		for (LCSMessage lcsMessage : findByS_C_C(sourceSystemName, classNameId,
				classPK, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(lcsMessage);
		}
	}

	/**
	 * Returns the number of l c s messages where sourceSystemName = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param sourceSystemName the source system name
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the number of matching l c s messages
	 */
	@Override
	public int countByS_C_C(String sourceSystemName, long classNameId,
		long classPK) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_S_C_C;

		Object[] finderArgs = new Object[] {
				sourceSystemName, classNameId, classPK
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_LCSMESSAGE_WHERE);

			boolean bindSourceSystemName = false;

			if (sourceSystemName == null) {
				query.append(_FINDER_COLUMN_S_C_C_SOURCESYSTEMNAME_1);
			}
			else if (sourceSystemName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_S_C_C_SOURCESYSTEMNAME_3);
			}
			else {
				bindSourceSystemName = true;

				query.append(_FINDER_COLUMN_S_C_C_SOURCESYSTEMNAME_2);
			}

			query.append(_FINDER_COLUMN_S_C_C_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_S_C_C_CLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindSourceSystemName) {
					qPos.add(sourceSystemName);
				}

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

	private static final String _FINDER_COLUMN_S_C_C_SOURCESYSTEMNAME_1 = "lcsMessage.sourceSystemName IS NULL AND ";
	private static final String _FINDER_COLUMN_S_C_C_SOURCESYSTEMNAME_2 = "lcsMessage.sourceSystemName = ? AND ";
	private static final String _FINDER_COLUMN_S_C_C_SOURCESYSTEMNAME_3 = "(lcsMessage.sourceSystemName IS NULL OR lcsMessage.sourceSystemName = '') AND ";
	private static final String _FINDER_COLUMN_S_C_C_CLASSNAMEID_2 = "lcsMessage.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_S_C_C_CLASSPK_2 = "lcsMessage.classPK = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_T = new FinderPath(LCSMessageModelImpl.ENTITY_CACHE_ENABLED,
			LCSMessageModelImpl.FINDER_CACHE_ENABLED, LCSMessageImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_C_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_T = new FinderPath(LCSMessageModelImpl.ENTITY_CACHE_ENABLED,
			LCSMessageModelImpl.FINDER_CACHE_ENABLED, LCSMessageImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			LCSMessageModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			LCSMessageModelImpl.CLASSPK_COLUMN_BITMASK |
			LCSMessageModelImpl.TYPE_COLUMN_BITMASK |
			LCSMessageModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C_T = new FinderPath(LCSMessageModelImpl.ENTITY_CACHE_ENABLED,
			LCSMessageModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the l c s messages where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @return the matching l c s messages
	 */
	@Override
	public List<LCSMessage> findByC_C_T(long classNameId, long classPK, int type) {
		return findByC_C_T(classNameId, classPK, type, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the l c s messages where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param start the lower bound of the range of l c s messages
	 * @param end the upper bound of the range of l c s messages (not inclusive)
	 * @return the range of matching l c s messages
	 */
	@Override
	public List<LCSMessage> findByC_C_T(long classNameId, long classPK,
		int type, int start, int end) {
		return findByC_C_T(classNameId, classPK, type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the l c s messages where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param start the lower bound of the range of l c s messages
	 * @param end the upper bound of the range of l c s messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching l c s messages
	 */
	@Override
	public List<LCSMessage> findByC_C_T(long classNameId, long classPK,
		int type, int start, int end,
		OrderByComparator<LCSMessage> orderByComparator) {
		return findByC_C_T(classNameId, classPK, type, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the l c s messages where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param start the lower bound of the range of l c s messages
	 * @param end the upper bound of the range of l c s messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching l c s messages
	 */
	@Override
	public List<LCSMessage> findByC_C_T(long classNameId, long classPK,
		int type, int start, int end,
		OrderByComparator<LCSMessage> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_T;
			finderArgs = new Object[] { classNameId, classPK, type };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_T;
			finderArgs = new Object[] {
					classNameId, classPK, type,
					
					start, end, orderByComparator
				};
		}

		List<LCSMessage> list = null;

		if (retrieveFromCache) {
			list = (List<LCSMessage>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LCSMessage lcsMessage : list) {
					if ((classNameId != lcsMessage.getClassNameId()) ||
							(classPK != lcsMessage.getClassPK()) ||
							(type != lcsMessage.getType())) {
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

			query.append(_SQL_SELECT_LCSMESSAGE_WHERE);

			query.append(_FINDER_COLUMN_C_C_T_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_T_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_T_TYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LCSMessageModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(type);

				if (!pagination) {
					list = (List<LCSMessage>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSMessage>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first l c s message in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s message
	 * @throws NoSuchLCSMessageException if a matching l c s message could not be found
	 */
	@Override
	public LCSMessage findByC_C_T_First(long classNameId, long classPK,
		int type, OrderByComparator<LCSMessage> orderByComparator)
		throws NoSuchLCSMessageException {
		LCSMessage lcsMessage = fetchByC_C_T_First(classNameId, classPK, type,
				orderByComparator);

		if (lcsMessage != null) {
			return lcsMessage;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSMessageException(msg.toString());
	}

	/**
	 * Returns the first l c s message in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s message, or <code>null</code> if a matching l c s message could not be found
	 */
	@Override
	public LCSMessage fetchByC_C_T_First(long classNameId, long classPK,
		int type, OrderByComparator<LCSMessage> orderByComparator) {
		List<LCSMessage> list = findByC_C_T(classNameId, classPK, type, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last l c s message in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s message
	 * @throws NoSuchLCSMessageException if a matching l c s message could not be found
	 */
	@Override
	public LCSMessage findByC_C_T_Last(long classNameId, long classPK,
		int type, OrderByComparator<LCSMessage> orderByComparator)
		throws NoSuchLCSMessageException {
		LCSMessage lcsMessage = fetchByC_C_T_Last(classNameId, classPK, type,
				orderByComparator);

		if (lcsMessage != null) {
			return lcsMessage;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSMessageException(msg.toString());
	}

	/**
	 * Returns the last l c s message in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s message, or <code>null</code> if a matching l c s message could not be found
	 */
	@Override
	public LCSMessage fetchByC_C_T_Last(long classNameId, long classPK,
		int type, OrderByComparator<LCSMessage> orderByComparator) {
		int count = countByC_C_T(classNameId, classPK, type);

		if (count == 0) {
			return null;
		}

		List<LCSMessage> list = findByC_C_T(classNameId, classPK, type,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the l c s messages before and after the current l c s message in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param lcsMessageId the primary key of the current l c s message
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next l c s message
	 * @throws NoSuchLCSMessageException if a l c s message with the primary key could not be found
	 */
	@Override
	public LCSMessage[] findByC_C_T_PrevAndNext(long lcsMessageId,
		long classNameId, long classPK, int type,
		OrderByComparator<LCSMessage> orderByComparator)
		throws NoSuchLCSMessageException {
		LCSMessage lcsMessage = findByPrimaryKey(lcsMessageId);

		Session session = null;

		try {
			session = openSession();

			LCSMessage[] array = new LCSMessageImpl[3];

			array[0] = getByC_C_T_PrevAndNext(session, lcsMessage, classNameId,
					classPK, type, orderByComparator, true);

			array[1] = lcsMessage;

			array[2] = getByC_C_T_PrevAndNext(session, lcsMessage, classNameId,
					classPK, type, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LCSMessage getByC_C_T_PrevAndNext(Session session,
		LCSMessage lcsMessage, long classNameId, long classPK, int type,
		OrderByComparator<LCSMessage> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_LCSMESSAGE_WHERE);

		query.append(_FINDER_COLUMN_C_C_T_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_C_C_T_CLASSPK_2);

		query.append(_FINDER_COLUMN_C_C_T_TYPE_2);

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
			query.append(LCSMessageModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(classNameId);

		qPos.add(classPK);

		qPos.add(type);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(lcsMessage);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LCSMessage> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the l c s messages where classNameId = &#63; and classPK = &#63; and type = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 */
	@Override
	public void removeByC_C_T(long classNameId, long classPK, int type) {
		for (LCSMessage lcsMessage : findByC_C_T(classNameId, classPK, type,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(lcsMessage);
		}
	}

	/**
	 * Returns the number of l c s messages where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @return the number of matching l c s messages
	 */
	@Override
	public int countByC_C_T(long classNameId, long classPK, int type) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_C_T;

		Object[] finderArgs = new Object[] { classNameId, classPK, type };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_LCSMESSAGE_WHERE);

			query.append(_FINDER_COLUMN_C_C_T_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_T_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

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

	private static final String _FINDER_COLUMN_C_C_T_CLASSNAMEID_2 = "lcsMessage.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_T_CLASSPK_2 = "lcsMessage.classPK = ? AND ";
	private static final String _FINDER_COLUMN_C_C_T_TYPE_2 = "lcsMessage.type = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_S_S_C_C = new FinderPath(LCSMessageModelImpl.ENTITY_CACHE_ENABLED,
			LCSMessageModelImpl.FINDER_CACHE_ENABLED, LCSMessageImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByS_S_C_C",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Long.class.getName(), Long.class.getName()
			},
			LCSMessageModelImpl.SOURCEMESSAGEID_COLUMN_BITMASK |
			LCSMessageModelImpl.SOURCESYSTEMNAME_COLUMN_BITMASK |
			LCSMessageModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			LCSMessageModelImpl.CLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_S_S_C_C = new FinderPath(LCSMessageModelImpl.ENTITY_CACHE_ENABLED,
			LCSMessageModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByS_S_C_C",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Long.class.getName(), Long.class.getName()
			});

	/**
	 * Returns the l c s message where sourceMessageId = &#63; and sourceSystemName = &#63; and classNameId = &#63; and classPK = &#63; or throws a {@link NoSuchLCSMessageException} if it could not be found.
	 *
	 * @param sourceMessageId the source message ID
	 * @param sourceSystemName the source system name
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the matching l c s message
	 * @throws NoSuchLCSMessageException if a matching l c s message could not be found
	 */
	@Override
	public LCSMessage findByS_S_C_C(long sourceMessageId,
		String sourceSystemName, long classNameId, long classPK)
		throws NoSuchLCSMessageException {
		LCSMessage lcsMessage = fetchByS_S_C_C(sourceMessageId,
				sourceSystemName, classNameId, classPK);

		if (lcsMessage == null) {
			StringBundler msg = new StringBundler(10);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("sourceMessageId=");
			msg.append(sourceMessageId);

			msg.append(", sourceSystemName=");
			msg.append(sourceSystemName);

			msg.append(", classNameId=");
			msg.append(classNameId);

			msg.append(", classPK=");
			msg.append(classPK);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchLCSMessageException(msg.toString());
		}

		return lcsMessage;
	}

	/**
	 * Returns the l c s message where sourceMessageId = &#63; and sourceSystemName = &#63; and classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param sourceMessageId the source message ID
	 * @param sourceSystemName the source system name
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the matching l c s message, or <code>null</code> if a matching l c s message could not be found
	 */
	@Override
	public LCSMessage fetchByS_S_C_C(long sourceMessageId,
		String sourceSystemName, long classNameId, long classPK) {
		return fetchByS_S_C_C(sourceMessageId, sourceSystemName, classNameId,
			classPK, true);
	}

	/**
	 * Returns the l c s message where sourceMessageId = &#63; and sourceSystemName = &#63; and classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param sourceMessageId the source message ID
	 * @param sourceSystemName the source system name
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching l c s message, or <code>null</code> if a matching l c s message could not be found
	 */
	@Override
	public LCSMessage fetchByS_S_C_C(long sourceMessageId,
		String sourceSystemName, long classNameId, long classPK,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] {
				sourceMessageId, sourceSystemName, classNameId, classPK
			};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_S_S_C_C,
					finderArgs, this);
		}

		if (result instanceof LCSMessage) {
			LCSMessage lcsMessage = (LCSMessage)result;

			if ((sourceMessageId != lcsMessage.getSourceMessageId()) ||
					!Objects.equals(sourceSystemName,
						lcsMessage.getSourceSystemName()) ||
					(classNameId != lcsMessage.getClassNameId()) ||
					(classPK != lcsMessage.getClassPK())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_SELECT_LCSMESSAGE_WHERE);

			query.append(_FINDER_COLUMN_S_S_C_C_SOURCEMESSAGEID_2);

			boolean bindSourceSystemName = false;

			if (sourceSystemName == null) {
				query.append(_FINDER_COLUMN_S_S_C_C_SOURCESYSTEMNAME_1);
			}
			else if (sourceSystemName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_S_S_C_C_SOURCESYSTEMNAME_3);
			}
			else {
				bindSourceSystemName = true;

				query.append(_FINDER_COLUMN_S_S_C_C_SOURCESYSTEMNAME_2);
			}

			query.append(_FINDER_COLUMN_S_S_C_C_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_S_S_C_C_CLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(sourceMessageId);

				if (bindSourceSystemName) {
					qPos.add(sourceSystemName);
				}

				qPos.add(classNameId);

				qPos.add(classPK);

				List<LCSMessage> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_S_S_C_C,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"LCSMessagePersistenceImpl.fetchByS_S_C_C(long, String, long, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					LCSMessage lcsMessage = list.get(0);

					result = lcsMessage;

					cacheResult(lcsMessage);

					if ((lcsMessage.getSourceMessageId() != sourceMessageId) ||
							(lcsMessage.getSourceSystemName() == null) ||
							!lcsMessage.getSourceSystemName()
										   .equals(sourceSystemName) ||
							(lcsMessage.getClassNameId() != classNameId) ||
							(lcsMessage.getClassPK() != classPK)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_S_S_C_C,
							finderArgs, lcsMessage);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_S_S_C_C,
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
			return (LCSMessage)result;
		}
	}

	/**
	 * Removes the l c s message where sourceMessageId = &#63; and sourceSystemName = &#63; and classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param sourceMessageId the source message ID
	 * @param sourceSystemName the source system name
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the l c s message that was removed
	 */
	@Override
	public LCSMessage removeByS_S_C_C(long sourceMessageId,
		String sourceSystemName, long classNameId, long classPK)
		throws NoSuchLCSMessageException {
		LCSMessage lcsMessage = findByS_S_C_C(sourceMessageId,
				sourceSystemName, classNameId, classPK);

		return remove(lcsMessage);
	}

	/**
	 * Returns the number of l c s messages where sourceMessageId = &#63; and sourceSystemName = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param sourceMessageId the source message ID
	 * @param sourceSystemName the source system name
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the number of matching l c s messages
	 */
	@Override
	public int countByS_S_C_C(long sourceMessageId, String sourceSystemName,
		long classNameId, long classPK) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_S_S_C_C;

		Object[] finderArgs = new Object[] {
				sourceMessageId, sourceSystemName, classNameId, classPK
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_LCSMESSAGE_WHERE);

			query.append(_FINDER_COLUMN_S_S_C_C_SOURCEMESSAGEID_2);

			boolean bindSourceSystemName = false;

			if (sourceSystemName == null) {
				query.append(_FINDER_COLUMN_S_S_C_C_SOURCESYSTEMNAME_1);
			}
			else if (sourceSystemName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_S_S_C_C_SOURCESYSTEMNAME_3);
			}
			else {
				bindSourceSystemName = true;

				query.append(_FINDER_COLUMN_S_S_C_C_SOURCESYSTEMNAME_2);
			}

			query.append(_FINDER_COLUMN_S_S_C_C_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_S_S_C_C_CLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(sourceMessageId);

				if (bindSourceSystemName) {
					qPos.add(sourceSystemName);
				}

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

	private static final String _FINDER_COLUMN_S_S_C_C_SOURCEMESSAGEID_2 = "lcsMessage.sourceMessageId = ? AND ";
	private static final String _FINDER_COLUMN_S_S_C_C_SOURCESYSTEMNAME_1 = "lcsMessage.sourceSystemName IS NULL AND ";
	private static final String _FINDER_COLUMN_S_S_C_C_SOURCESYSTEMNAME_2 = "lcsMessage.sourceSystemName = ? AND ";
	private static final String _FINDER_COLUMN_S_S_C_C_SOURCESYSTEMNAME_3 = "(lcsMessage.sourceSystemName IS NULL OR lcsMessage.sourceSystemName = '') AND ";
	private static final String _FINDER_COLUMN_S_S_C_C_CLASSNAMEID_2 = "lcsMessage.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_S_S_C_C_CLASSPK_2 = "lcsMessage.classPK = ?";

	public LCSMessagePersistenceImpl() {
		setModelClass(LCSMessage.class);
	}

	/**
	 * Caches the l c s message in the entity cache if it is enabled.
	 *
	 * @param lcsMessage the l c s message
	 */
	@Override
	public void cacheResult(LCSMessage lcsMessage) {
		entityCache.putResult(LCSMessageModelImpl.ENTITY_CACHE_ENABLED,
			LCSMessageImpl.class, lcsMessage.getPrimaryKey(), lcsMessage);

		finderCache.putResult(FINDER_PATH_FETCH_BY_S_S_C_C,
			new Object[] {
				lcsMessage.getSourceMessageId(),
				lcsMessage.getSourceSystemName(), lcsMessage.getClassNameId(),
				lcsMessage.getClassPK()
			}, lcsMessage);

		lcsMessage.resetOriginalValues();
	}

	/**
	 * Caches the l c s messages in the entity cache if it is enabled.
	 *
	 * @param lcsMessages the l c s messages
	 */
	@Override
	public void cacheResult(List<LCSMessage> lcsMessages) {
		for (LCSMessage lcsMessage : lcsMessages) {
			if (entityCache.getResult(
						LCSMessageModelImpl.ENTITY_CACHE_ENABLED,
						LCSMessageImpl.class, lcsMessage.getPrimaryKey()) == null) {
				cacheResult(lcsMessage);
			}
			else {
				lcsMessage.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all l c s messages.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(LCSMessageImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the l c s message.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LCSMessage lcsMessage) {
		entityCache.removeResult(LCSMessageModelImpl.ENTITY_CACHE_ENABLED,
			LCSMessageImpl.class, lcsMessage.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((LCSMessageModelImpl)lcsMessage);
	}

	@Override
	public void clearCache(List<LCSMessage> lcsMessages) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (LCSMessage lcsMessage : lcsMessages) {
			entityCache.removeResult(LCSMessageModelImpl.ENTITY_CACHE_ENABLED,
				LCSMessageImpl.class, lcsMessage.getPrimaryKey());

			clearUniqueFindersCache((LCSMessageModelImpl)lcsMessage);
		}
	}

	protected void cacheUniqueFindersCache(
		LCSMessageModelImpl lcsMessageModelImpl, boolean isNew) {
		if (isNew) {
			Object[] args = new Object[] {
					lcsMessageModelImpl.getSourceMessageId(),
					lcsMessageModelImpl.getSourceSystemName(),
					lcsMessageModelImpl.getClassNameId(),
					lcsMessageModelImpl.getClassPK()
				};

			finderCache.putResult(FINDER_PATH_COUNT_BY_S_S_C_C, args,
				Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_S_S_C_C, args,
				lcsMessageModelImpl);
		}
		else {
			if ((lcsMessageModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_S_S_C_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						lcsMessageModelImpl.getSourceMessageId(),
						lcsMessageModelImpl.getSourceSystemName(),
						lcsMessageModelImpl.getClassNameId(),
						lcsMessageModelImpl.getClassPK()
					};

				finderCache.putResult(FINDER_PATH_COUNT_BY_S_S_C_C, args,
					Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_S_S_C_C, args,
					lcsMessageModelImpl);
			}
		}
	}

	protected void clearUniqueFindersCache(
		LCSMessageModelImpl lcsMessageModelImpl) {
		Object[] args = new Object[] {
				lcsMessageModelImpl.getSourceMessageId(),
				lcsMessageModelImpl.getSourceSystemName(),
				lcsMessageModelImpl.getClassNameId(),
				lcsMessageModelImpl.getClassPK()
			};

		finderCache.removeResult(FINDER_PATH_COUNT_BY_S_S_C_C, args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_S_S_C_C, args);

		if ((lcsMessageModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_S_S_C_C.getColumnBitmask()) != 0) {
			args = new Object[] {
					lcsMessageModelImpl.getOriginalSourceMessageId(),
					lcsMessageModelImpl.getOriginalSourceSystemName(),
					lcsMessageModelImpl.getOriginalClassNameId(),
					lcsMessageModelImpl.getOriginalClassPK()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_S_S_C_C, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_S_S_C_C, args);
		}
	}

	/**
	 * Creates a new l c s message with the primary key. Does not add the l c s message to the database.
	 *
	 * @param lcsMessageId the primary key for the new l c s message
	 * @return the new l c s message
	 */
	@Override
	public LCSMessage create(long lcsMessageId) {
		LCSMessage lcsMessage = new LCSMessageImpl();

		lcsMessage.setNew(true);
		lcsMessage.setPrimaryKey(lcsMessageId);

		return lcsMessage;
	}

	/**
	 * Removes the l c s message with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param lcsMessageId the primary key of the l c s message
	 * @return the l c s message that was removed
	 * @throws NoSuchLCSMessageException if a l c s message with the primary key could not be found
	 */
	@Override
	public LCSMessage remove(long lcsMessageId)
		throws NoSuchLCSMessageException {
		return remove((Serializable)lcsMessageId);
	}

	/**
	 * Removes the l c s message with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the l c s message
	 * @return the l c s message that was removed
	 * @throws NoSuchLCSMessageException if a l c s message with the primary key could not be found
	 */
	@Override
	public LCSMessage remove(Serializable primaryKey)
		throws NoSuchLCSMessageException {
		Session session = null;

		try {
			session = openSession();

			LCSMessage lcsMessage = (LCSMessage)session.get(LCSMessageImpl.class,
					primaryKey);

			if (lcsMessage == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLCSMessageException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(lcsMessage);
		}
		catch (NoSuchLCSMessageException nsee) {
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
	protected LCSMessage removeImpl(LCSMessage lcsMessage) {
		lcsMessage = toUnwrappedModel(lcsMessage);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(lcsMessage)) {
				lcsMessage = (LCSMessage)session.get(LCSMessageImpl.class,
						lcsMessage.getPrimaryKeyObj());
			}

			if (lcsMessage != null) {
				session.delete(lcsMessage);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (lcsMessage != null) {
			clearCache(lcsMessage);
		}

		return lcsMessage;
	}

	@Override
	public LCSMessage updateImpl(LCSMessage lcsMessage) {
		lcsMessage = toUnwrappedModel(lcsMessage);

		boolean isNew = lcsMessage.isNew();

		LCSMessageModelImpl lcsMessageModelImpl = (LCSMessageModelImpl)lcsMessage;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (lcsMessage.getCreateDate() == null)) {
			if (serviceContext == null) {
				lcsMessage.setCreateDate(now);
			}
			else {
				lcsMessage.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!lcsMessageModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				lcsMessage.setModifiedDate(now);
			}
			else {
				lcsMessage.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (lcsMessage.isNew()) {
				session.save(lcsMessage);

				lcsMessage.setNew(false);
			}
			else {
				lcsMessage = (LCSMessage)session.merge(lcsMessage);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !LCSMessageModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((lcsMessageModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						lcsMessageModelImpl.getOriginalClassNameId(),
						lcsMessageModelImpl.getOriginalClassPK()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C,
					args);

				args = new Object[] {
						lcsMessageModelImpl.getClassNameId(),
						lcsMessageModelImpl.getClassPK()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C,
					args);
			}

			if ((lcsMessageModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_S_C_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						lcsMessageModelImpl.getOriginalSourceSystemName(),
						lcsMessageModelImpl.getOriginalClassNameId(),
						lcsMessageModelImpl.getOriginalClassPK()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_S_C_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_S_C_C,
					args);

				args = new Object[] {
						lcsMessageModelImpl.getSourceSystemName(),
						lcsMessageModelImpl.getClassNameId(),
						lcsMessageModelImpl.getClassPK()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_S_C_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_S_C_C,
					args);
			}

			if ((lcsMessageModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						lcsMessageModelImpl.getOriginalClassNameId(),
						lcsMessageModelImpl.getOriginalClassPK(),
						lcsMessageModelImpl.getOriginalType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C_T, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_T,
					args);

				args = new Object[] {
						lcsMessageModelImpl.getClassNameId(),
						lcsMessageModelImpl.getClassPK(),
						lcsMessageModelImpl.getType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C_T, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_T,
					args);
			}
		}

		entityCache.putResult(LCSMessageModelImpl.ENTITY_CACHE_ENABLED,
			LCSMessageImpl.class, lcsMessage.getPrimaryKey(), lcsMessage, false);

		clearUniqueFindersCache(lcsMessageModelImpl);
		cacheUniqueFindersCache(lcsMessageModelImpl, isNew);

		lcsMessage.resetOriginalValues();

		return lcsMessage;
	}

	protected LCSMessage toUnwrappedModel(LCSMessage lcsMessage) {
		if (lcsMessage instanceof LCSMessageImpl) {
			return lcsMessage;
		}

		LCSMessageImpl lcsMessageImpl = new LCSMessageImpl();

		lcsMessageImpl.setNew(lcsMessage.isNew());
		lcsMessageImpl.setPrimaryKey(lcsMessage.getPrimaryKey());

		lcsMessageImpl.setLcsMessageId(lcsMessage.getLcsMessageId());
		lcsMessageImpl.setCreateDate(lcsMessage.getCreateDate());
		lcsMessageImpl.setModifiedDate(lcsMessage.getModifiedDate());
		lcsMessageImpl.setSourceMessageId(lcsMessage.getSourceMessageId());
		lcsMessageImpl.setSourceSystemName(lcsMessage.getSourceSystemName());
		lcsMessageImpl.setClassNameId(lcsMessage.getClassNameId());
		lcsMessageImpl.setClassPK(lcsMessage.getClassPK());
		lcsMessageImpl.setContent(lcsMessage.getContent());
		lcsMessageImpl.setEndDate(lcsMessage.getEndDate());
		lcsMessageImpl.setGlobal(lcsMessage.isGlobal());
		lcsMessageImpl.setSeverityLevel(lcsMessage.getSeverityLevel());
		lcsMessageImpl.setType(lcsMessage.getType());

		return lcsMessageImpl;
	}

	/**
	 * Returns the l c s message with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the l c s message
	 * @return the l c s message
	 * @throws NoSuchLCSMessageException if a l c s message with the primary key could not be found
	 */
	@Override
	public LCSMessage findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLCSMessageException {
		LCSMessage lcsMessage = fetchByPrimaryKey(primaryKey);

		if (lcsMessage == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLCSMessageException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return lcsMessage;
	}

	/**
	 * Returns the l c s message with the primary key or throws a {@link NoSuchLCSMessageException} if it could not be found.
	 *
	 * @param lcsMessageId the primary key of the l c s message
	 * @return the l c s message
	 * @throws NoSuchLCSMessageException if a l c s message with the primary key could not be found
	 */
	@Override
	public LCSMessage findByPrimaryKey(long lcsMessageId)
		throws NoSuchLCSMessageException {
		return findByPrimaryKey((Serializable)lcsMessageId);
	}

	/**
	 * Returns the l c s message with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the l c s message
	 * @return the l c s message, or <code>null</code> if a l c s message with the primary key could not be found
	 */
	@Override
	public LCSMessage fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(LCSMessageModelImpl.ENTITY_CACHE_ENABLED,
				LCSMessageImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		LCSMessage lcsMessage = (LCSMessage)serializable;

		if (lcsMessage == null) {
			Session session = null;

			try {
				session = openSession();

				lcsMessage = (LCSMessage)session.get(LCSMessageImpl.class,
						primaryKey);

				if (lcsMessage != null) {
					cacheResult(lcsMessage);
				}
				else {
					entityCache.putResult(LCSMessageModelImpl.ENTITY_CACHE_ENABLED,
						LCSMessageImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(LCSMessageModelImpl.ENTITY_CACHE_ENABLED,
					LCSMessageImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return lcsMessage;
	}

	/**
	 * Returns the l c s message with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param lcsMessageId the primary key of the l c s message
	 * @return the l c s message, or <code>null</code> if a l c s message with the primary key could not be found
	 */
	@Override
	public LCSMessage fetchByPrimaryKey(long lcsMessageId) {
		return fetchByPrimaryKey((Serializable)lcsMessageId);
	}

	@Override
	public Map<Serializable, LCSMessage> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, LCSMessage> map = new HashMap<Serializable, LCSMessage>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			LCSMessage lcsMessage = fetchByPrimaryKey(primaryKey);

			if (lcsMessage != null) {
				map.put(primaryKey, lcsMessage);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(LCSMessageModelImpl.ENTITY_CACHE_ENABLED,
					LCSMessageImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (LCSMessage)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_LCSMESSAGE_WHERE_PKS_IN);

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

			for (LCSMessage lcsMessage : (List<LCSMessage>)q.list()) {
				map.put(lcsMessage.getPrimaryKeyObj(), lcsMessage);

				cacheResult(lcsMessage);

				uncachedPrimaryKeys.remove(lcsMessage.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(LCSMessageModelImpl.ENTITY_CACHE_ENABLED,
					LCSMessageImpl.class, primaryKey, nullModel);
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
	 * Returns all the l c s messages.
	 *
	 * @return the l c s messages
	 */
	@Override
	public List<LCSMessage> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the l c s messages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of l c s messages
	 * @param end the upper bound of the range of l c s messages (not inclusive)
	 * @return the range of l c s messages
	 */
	@Override
	public List<LCSMessage> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the l c s messages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of l c s messages
	 * @param end the upper bound of the range of l c s messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of l c s messages
	 */
	@Override
	public List<LCSMessage> findAll(int start, int end,
		OrderByComparator<LCSMessage> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the l c s messages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of l c s messages
	 * @param end the upper bound of the range of l c s messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of l c s messages
	 */
	@Override
	public List<LCSMessage> findAll(int start, int end,
		OrderByComparator<LCSMessage> orderByComparator,
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

		List<LCSMessage> list = null;

		if (retrieveFromCache) {
			list = (List<LCSMessage>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_LCSMESSAGE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_LCSMESSAGE;

				if (pagination) {
					sql = sql.concat(LCSMessageModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<LCSMessage>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSMessage>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the l c s messages from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (LCSMessage lcsMessage : findAll()) {
			remove(lcsMessage);
		}
	}

	/**
	 * Returns the number of l c s messages.
	 *
	 * @return the number of l c s messages
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_LCSMESSAGE);

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
		return LCSMessageModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the l c s message persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(LCSMessageImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_LCSMESSAGE = "SELECT lcsMessage FROM LCSMessage lcsMessage";
	private static final String _SQL_SELECT_LCSMESSAGE_WHERE_PKS_IN = "SELECT lcsMessage FROM LCSMessage lcsMessage WHERE lcsMessageId IN (";
	private static final String _SQL_SELECT_LCSMESSAGE_WHERE = "SELECT lcsMessage FROM LCSMessage lcsMessage WHERE ";
	private static final String _SQL_COUNT_LCSMESSAGE = "SELECT COUNT(lcsMessage) FROM LCSMessage lcsMessage";
	private static final String _SQL_COUNT_LCSMESSAGE_WHERE = "SELECT COUNT(lcsMessage) FROM LCSMessage lcsMessage WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "lcsMessage.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No LCSMessage exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No LCSMessage exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(LCSMessagePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"type"
			});
}