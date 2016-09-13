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

import com.liferay.osb.lcs.exception.NoSuchLCSInvitationException;
import com.liferay.osb.lcs.model.LCSInvitation;
import com.liferay.osb.lcs.model.impl.LCSInvitationImpl;
import com.liferay.osb.lcs.model.impl.LCSInvitationModelImpl;
import com.liferay.osb.lcs.service.persistence.LCSInvitationPersistence;

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
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the l c s invitation service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Igor Beslic
 * @see LCSInvitationPersistence
 * @see com.liferay.osb.lcs.service.persistence.LCSInvitationUtil
 * @generated
 */
@ProviderType
public class LCSInvitationPersistenceImpl extends BasePersistenceImpl<LCSInvitation>
	implements LCSInvitationPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link LCSInvitationUtil} to access the l c s invitation persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = LCSInvitationImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(LCSInvitationModelImpl.ENTITY_CACHE_ENABLED,
			LCSInvitationModelImpl.FINDER_CACHE_ENABLED,
			LCSInvitationImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(LCSInvitationModelImpl.ENTITY_CACHE_ENABLED,
			LCSInvitationModelImpl.FINDER_CACHE_ENABLED,
			LCSInvitationImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(LCSInvitationModelImpl.ENTITY_CACHE_ENABLED,
			LCSInvitationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_LCSPROJECTID =
		new FinderPath(LCSInvitationModelImpl.ENTITY_CACHE_ENABLED,
			LCSInvitationModelImpl.FINDER_CACHE_ENABLED,
			LCSInvitationImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByLCSProjectId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LCSPROJECTID =
		new FinderPath(LCSInvitationModelImpl.ENTITY_CACHE_ENABLED,
			LCSInvitationModelImpl.FINDER_CACHE_ENABLED,
			LCSInvitationImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByLCSProjectId", new String[] { Long.class.getName() },
			LCSInvitationModelImpl.LCSPROJECTID_COLUMN_BITMASK |
			LCSInvitationModelImpl.EMAILADDRESS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_LCSPROJECTID = new FinderPath(LCSInvitationModelImpl.ENTITY_CACHE_ENABLED,
			LCSInvitationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByLCSProjectId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the l c s invitations where lcsProjectId = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @return the matching l c s invitations
	 */
	@Override
	public List<LCSInvitation> findByLCSProjectId(long lcsProjectId) {
		return findByLCSProjectId(lcsProjectId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the l c s invitations where lcsProjectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSInvitationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param start the lower bound of the range of l c s invitations
	 * @param end the upper bound of the range of l c s invitations (not inclusive)
	 * @return the range of matching l c s invitations
	 */
	@Override
	public List<LCSInvitation> findByLCSProjectId(long lcsProjectId, int start,
		int end) {
		return findByLCSProjectId(lcsProjectId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the l c s invitations where lcsProjectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSInvitationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param start the lower bound of the range of l c s invitations
	 * @param end the upper bound of the range of l c s invitations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching l c s invitations
	 */
	@Override
	public List<LCSInvitation> findByLCSProjectId(long lcsProjectId, int start,
		int end, OrderByComparator<LCSInvitation> orderByComparator) {
		return findByLCSProjectId(lcsProjectId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the l c s invitations where lcsProjectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSInvitationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param start the lower bound of the range of l c s invitations
	 * @param end the upper bound of the range of l c s invitations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching l c s invitations
	 */
	@Override
	public List<LCSInvitation> findByLCSProjectId(long lcsProjectId, int start,
		int end, OrderByComparator<LCSInvitation> orderByComparator,
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

		List<LCSInvitation> list = null;

		if (retrieveFromCache) {
			list = (List<LCSInvitation>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LCSInvitation lcsInvitation : list) {
					if ((lcsProjectId != lcsInvitation.getLcsProjectId())) {
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

			query.append(_SQL_SELECT_LCSINVITATION_WHERE);

			query.append(_FINDER_COLUMN_LCSPROJECTID_LCSPROJECTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LCSInvitationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(lcsProjectId);

				if (!pagination) {
					list = (List<LCSInvitation>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSInvitation>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first l c s invitation in the ordered set where lcsProjectId = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s invitation
	 * @throws NoSuchLCSInvitationException if a matching l c s invitation could not be found
	 */
	@Override
	public LCSInvitation findByLCSProjectId_First(long lcsProjectId,
		OrderByComparator<LCSInvitation> orderByComparator)
		throws NoSuchLCSInvitationException {
		LCSInvitation lcsInvitation = fetchByLCSProjectId_First(lcsProjectId,
				orderByComparator);

		if (lcsInvitation != null) {
			return lcsInvitation;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("lcsProjectId=");
		msg.append(lcsProjectId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSInvitationException(msg.toString());
	}

	/**
	 * Returns the first l c s invitation in the ordered set where lcsProjectId = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s invitation, or <code>null</code> if a matching l c s invitation could not be found
	 */
	@Override
	public LCSInvitation fetchByLCSProjectId_First(long lcsProjectId,
		OrderByComparator<LCSInvitation> orderByComparator) {
		List<LCSInvitation> list = findByLCSProjectId(lcsProjectId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last l c s invitation in the ordered set where lcsProjectId = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s invitation
	 * @throws NoSuchLCSInvitationException if a matching l c s invitation could not be found
	 */
	@Override
	public LCSInvitation findByLCSProjectId_Last(long lcsProjectId,
		OrderByComparator<LCSInvitation> orderByComparator)
		throws NoSuchLCSInvitationException {
		LCSInvitation lcsInvitation = fetchByLCSProjectId_Last(lcsProjectId,
				orderByComparator);

		if (lcsInvitation != null) {
			return lcsInvitation;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("lcsProjectId=");
		msg.append(lcsProjectId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSInvitationException(msg.toString());
	}

	/**
	 * Returns the last l c s invitation in the ordered set where lcsProjectId = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s invitation, or <code>null</code> if a matching l c s invitation could not be found
	 */
	@Override
	public LCSInvitation fetchByLCSProjectId_Last(long lcsProjectId,
		OrderByComparator<LCSInvitation> orderByComparator) {
		int count = countByLCSProjectId(lcsProjectId);

		if (count == 0) {
			return null;
		}

		List<LCSInvitation> list = findByLCSProjectId(lcsProjectId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the l c s invitations before and after the current l c s invitation in the ordered set where lcsProjectId = &#63;.
	 *
	 * @param lcsInvitationId the primary key of the current l c s invitation
	 * @param lcsProjectId the lcs project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next l c s invitation
	 * @throws NoSuchLCSInvitationException if a l c s invitation with the primary key could not be found
	 */
	@Override
	public LCSInvitation[] findByLCSProjectId_PrevAndNext(
		long lcsInvitationId, long lcsProjectId,
		OrderByComparator<LCSInvitation> orderByComparator)
		throws NoSuchLCSInvitationException {
		LCSInvitation lcsInvitation = findByPrimaryKey(lcsInvitationId);

		Session session = null;

		try {
			session = openSession();

			LCSInvitation[] array = new LCSInvitationImpl[3];

			array[0] = getByLCSProjectId_PrevAndNext(session, lcsInvitation,
					lcsProjectId, orderByComparator, true);

			array[1] = lcsInvitation;

			array[2] = getByLCSProjectId_PrevAndNext(session, lcsInvitation,
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

	protected LCSInvitation getByLCSProjectId_PrevAndNext(Session session,
		LCSInvitation lcsInvitation, long lcsProjectId,
		OrderByComparator<LCSInvitation> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LCSINVITATION_WHERE);

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
			query.append(LCSInvitationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(lcsProjectId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(lcsInvitation);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LCSInvitation> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the l c s invitations where lcsProjectId = &#63; from the database.
	 *
	 * @param lcsProjectId the lcs project ID
	 */
	@Override
	public void removeByLCSProjectId(long lcsProjectId) {
		for (LCSInvitation lcsInvitation : findByLCSProjectId(lcsProjectId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(lcsInvitation);
		}
	}

	/**
	 * Returns the number of l c s invitations where lcsProjectId = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @return the number of matching l c s invitations
	 */
	@Override
	public int countByLCSProjectId(long lcsProjectId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_LCSPROJECTID;

		Object[] finderArgs = new Object[] { lcsProjectId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LCSINVITATION_WHERE);

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

	private static final String _FINDER_COLUMN_LCSPROJECTID_LCSPROJECTID_2 = "lcsInvitation.lcsProjectId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_EMAILADDRESS =
		new FinderPath(LCSInvitationModelImpl.ENTITY_CACHE_ENABLED,
			LCSInvitationModelImpl.FINDER_CACHE_ENABLED,
			LCSInvitationImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByEmailAddress",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EMAILADDRESS =
		new FinderPath(LCSInvitationModelImpl.ENTITY_CACHE_ENABLED,
			LCSInvitationModelImpl.FINDER_CACHE_ENABLED,
			LCSInvitationImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByEmailAddress", new String[] { String.class.getName() },
			LCSInvitationModelImpl.EMAILADDRESS_COLUMN_BITMASK |
			LCSInvitationModelImpl.LCSPROJECTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_EMAILADDRESS = new FinderPath(LCSInvitationModelImpl.ENTITY_CACHE_ENABLED,
			LCSInvitationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByEmailAddress",
			new String[] { String.class.getName() });

	/**
	 * Returns all the l c s invitations where emailAddress = &#63;.
	 *
	 * @param emailAddress the email address
	 * @return the matching l c s invitations
	 */
	@Override
	public List<LCSInvitation> findByEmailAddress(String emailAddress) {
		return findByEmailAddress(emailAddress, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the l c s invitations where emailAddress = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSInvitationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param emailAddress the email address
	 * @param start the lower bound of the range of l c s invitations
	 * @param end the upper bound of the range of l c s invitations (not inclusive)
	 * @return the range of matching l c s invitations
	 */
	@Override
	public List<LCSInvitation> findByEmailAddress(String emailAddress,
		int start, int end) {
		return findByEmailAddress(emailAddress, start, end, null);
	}

	/**
	 * Returns an ordered range of all the l c s invitations where emailAddress = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSInvitationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param emailAddress the email address
	 * @param start the lower bound of the range of l c s invitations
	 * @param end the upper bound of the range of l c s invitations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching l c s invitations
	 */
	@Override
	public List<LCSInvitation> findByEmailAddress(String emailAddress,
		int start, int end, OrderByComparator<LCSInvitation> orderByComparator) {
		return findByEmailAddress(emailAddress, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the l c s invitations where emailAddress = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSInvitationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param emailAddress the email address
	 * @param start the lower bound of the range of l c s invitations
	 * @param end the upper bound of the range of l c s invitations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching l c s invitations
	 */
	@Override
	public List<LCSInvitation> findByEmailAddress(String emailAddress,
		int start, int end, OrderByComparator<LCSInvitation> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EMAILADDRESS;
			finderArgs = new Object[] { emailAddress };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_EMAILADDRESS;
			finderArgs = new Object[] {
					emailAddress,
					
					start, end, orderByComparator
				};
		}

		List<LCSInvitation> list = null;

		if (retrieveFromCache) {
			list = (List<LCSInvitation>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LCSInvitation lcsInvitation : list) {
					if (!Objects.equals(emailAddress,
								lcsInvitation.getEmailAddress())) {
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

			query.append(_SQL_SELECT_LCSINVITATION_WHERE);

			boolean bindEmailAddress = false;

			if (emailAddress == null) {
				query.append(_FINDER_COLUMN_EMAILADDRESS_EMAILADDRESS_1);
			}
			else if (emailAddress.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_EMAILADDRESS_EMAILADDRESS_3);
			}
			else {
				bindEmailAddress = true;

				query.append(_FINDER_COLUMN_EMAILADDRESS_EMAILADDRESS_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LCSInvitationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindEmailAddress) {
					qPos.add(emailAddress);
				}

				if (!pagination) {
					list = (List<LCSInvitation>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSInvitation>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first l c s invitation in the ordered set where emailAddress = &#63;.
	 *
	 * @param emailAddress the email address
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s invitation
	 * @throws NoSuchLCSInvitationException if a matching l c s invitation could not be found
	 */
	@Override
	public LCSInvitation findByEmailAddress_First(String emailAddress,
		OrderByComparator<LCSInvitation> orderByComparator)
		throws NoSuchLCSInvitationException {
		LCSInvitation lcsInvitation = fetchByEmailAddress_First(emailAddress,
				orderByComparator);

		if (lcsInvitation != null) {
			return lcsInvitation;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("emailAddress=");
		msg.append(emailAddress);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSInvitationException(msg.toString());
	}

	/**
	 * Returns the first l c s invitation in the ordered set where emailAddress = &#63;.
	 *
	 * @param emailAddress the email address
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s invitation, or <code>null</code> if a matching l c s invitation could not be found
	 */
	@Override
	public LCSInvitation fetchByEmailAddress_First(String emailAddress,
		OrderByComparator<LCSInvitation> orderByComparator) {
		List<LCSInvitation> list = findByEmailAddress(emailAddress, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last l c s invitation in the ordered set where emailAddress = &#63;.
	 *
	 * @param emailAddress the email address
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s invitation
	 * @throws NoSuchLCSInvitationException if a matching l c s invitation could not be found
	 */
	@Override
	public LCSInvitation findByEmailAddress_Last(String emailAddress,
		OrderByComparator<LCSInvitation> orderByComparator)
		throws NoSuchLCSInvitationException {
		LCSInvitation lcsInvitation = fetchByEmailAddress_Last(emailAddress,
				orderByComparator);

		if (lcsInvitation != null) {
			return lcsInvitation;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("emailAddress=");
		msg.append(emailAddress);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSInvitationException(msg.toString());
	}

	/**
	 * Returns the last l c s invitation in the ordered set where emailAddress = &#63;.
	 *
	 * @param emailAddress the email address
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s invitation, or <code>null</code> if a matching l c s invitation could not be found
	 */
	@Override
	public LCSInvitation fetchByEmailAddress_Last(String emailAddress,
		OrderByComparator<LCSInvitation> orderByComparator) {
		int count = countByEmailAddress(emailAddress);

		if (count == 0) {
			return null;
		}

		List<LCSInvitation> list = findByEmailAddress(emailAddress, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the l c s invitations before and after the current l c s invitation in the ordered set where emailAddress = &#63;.
	 *
	 * @param lcsInvitationId the primary key of the current l c s invitation
	 * @param emailAddress the email address
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next l c s invitation
	 * @throws NoSuchLCSInvitationException if a l c s invitation with the primary key could not be found
	 */
	@Override
	public LCSInvitation[] findByEmailAddress_PrevAndNext(
		long lcsInvitationId, String emailAddress,
		OrderByComparator<LCSInvitation> orderByComparator)
		throws NoSuchLCSInvitationException {
		LCSInvitation lcsInvitation = findByPrimaryKey(lcsInvitationId);

		Session session = null;

		try {
			session = openSession();

			LCSInvitation[] array = new LCSInvitationImpl[3];

			array[0] = getByEmailAddress_PrevAndNext(session, lcsInvitation,
					emailAddress, orderByComparator, true);

			array[1] = lcsInvitation;

			array[2] = getByEmailAddress_PrevAndNext(session, lcsInvitation,
					emailAddress, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LCSInvitation getByEmailAddress_PrevAndNext(Session session,
		LCSInvitation lcsInvitation, String emailAddress,
		OrderByComparator<LCSInvitation> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LCSINVITATION_WHERE);

		boolean bindEmailAddress = false;

		if (emailAddress == null) {
			query.append(_FINDER_COLUMN_EMAILADDRESS_EMAILADDRESS_1);
		}
		else if (emailAddress.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_EMAILADDRESS_EMAILADDRESS_3);
		}
		else {
			bindEmailAddress = true;

			query.append(_FINDER_COLUMN_EMAILADDRESS_EMAILADDRESS_2);
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
			query.append(LCSInvitationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindEmailAddress) {
			qPos.add(emailAddress);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(lcsInvitation);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LCSInvitation> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the l c s invitations where emailAddress = &#63; from the database.
	 *
	 * @param emailAddress the email address
	 */
	@Override
	public void removeByEmailAddress(String emailAddress) {
		for (LCSInvitation lcsInvitation : findByEmailAddress(emailAddress,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(lcsInvitation);
		}
	}

	/**
	 * Returns the number of l c s invitations where emailAddress = &#63;.
	 *
	 * @param emailAddress the email address
	 * @return the number of matching l c s invitations
	 */
	@Override
	public int countByEmailAddress(String emailAddress) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_EMAILADDRESS;

		Object[] finderArgs = new Object[] { emailAddress };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LCSINVITATION_WHERE);

			boolean bindEmailAddress = false;

			if (emailAddress == null) {
				query.append(_FINDER_COLUMN_EMAILADDRESS_EMAILADDRESS_1);
			}
			else if (emailAddress.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_EMAILADDRESS_EMAILADDRESS_3);
			}
			else {
				bindEmailAddress = true;

				query.append(_FINDER_COLUMN_EMAILADDRESS_EMAILADDRESS_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindEmailAddress) {
					qPos.add(emailAddress);
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

	private static final String _FINDER_COLUMN_EMAILADDRESS_EMAILADDRESS_1 = "lcsInvitation.emailAddress IS NULL";
	private static final String _FINDER_COLUMN_EMAILADDRESS_EMAILADDRESS_2 = "lcsInvitation.emailAddress = ?";
	private static final String _FINDER_COLUMN_EMAILADDRESS_EMAILADDRESS_3 = "(lcsInvitation.emailAddress IS NULL OR lcsInvitation.emailAddress = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_LPI_EA = new FinderPath(LCSInvitationModelImpl.ENTITY_CACHE_ENABLED,
			LCSInvitationModelImpl.FINDER_CACHE_ENABLED,
			LCSInvitationImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByLPI_EA",
			new String[] { Long.class.getName(), String.class.getName() },
			LCSInvitationModelImpl.LCSPROJECTID_COLUMN_BITMASK |
			LCSInvitationModelImpl.EMAILADDRESS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_LPI_EA = new FinderPath(LCSInvitationModelImpl.ENTITY_CACHE_ENABLED,
			LCSInvitationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByLPI_EA",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the l c s invitation where lcsProjectId = &#63; and emailAddress = &#63; or throws a {@link NoSuchLCSInvitationException} if it could not be found.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param emailAddress the email address
	 * @return the matching l c s invitation
	 * @throws NoSuchLCSInvitationException if a matching l c s invitation could not be found
	 */
	@Override
	public LCSInvitation findByLPI_EA(long lcsProjectId, String emailAddress)
		throws NoSuchLCSInvitationException {
		LCSInvitation lcsInvitation = fetchByLPI_EA(lcsProjectId, emailAddress);

		if (lcsInvitation == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("lcsProjectId=");
			msg.append(lcsProjectId);

			msg.append(", emailAddress=");
			msg.append(emailAddress);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchLCSInvitationException(msg.toString());
		}

		return lcsInvitation;
	}

	/**
	 * Returns the l c s invitation where lcsProjectId = &#63; and emailAddress = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param emailAddress the email address
	 * @return the matching l c s invitation, or <code>null</code> if a matching l c s invitation could not be found
	 */
	@Override
	public LCSInvitation fetchByLPI_EA(long lcsProjectId, String emailAddress) {
		return fetchByLPI_EA(lcsProjectId, emailAddress, true);
	}

	/**
	 * Returns the l c s invitation where lcsProjectId = &#63; and emailAddress = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param emailAddress the email address
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching l c s invitation, or <code>null</code> if a matching l c s invitation could not be found
	 */
	@Override
	public LCSInvitation fetchByLPI_EA(long lcsProjectId, String emailAddress,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { lcsProjectId, emailAddress };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_LPI_EA,
					finderArgs, this);
		}

		if (result instanceof LCSInvitation) {
			LCSInvitation lcsInvitation = (LCSInvitation)result;

			if ((lcsProjectId != lcsInvitation.getLcsProjectId()) ||
					!Objects.equals(emailAddress,
						lcsInvitation.getEmailAddress())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_LCSINVITATION_WHERE);

			query.append(_FINDER_COLUMN_LPI_EA_LCSPROJECTID_2);

			boolean bindEmailAddress = false;

			if (emailAddress == null) {
				query.append(_FINDER_COLUMN_LPI_EA_EMAILADDRESS_1);
			}
			else if (emailAddress.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_LPI_EA_EMAILADDRESS_3);
			}
			else {
				bindEmailAddress = true;

				query.append(_FINDER_COLUMN_LPI_EA_EMAILADDRESS_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(lcsProjectId);

				if (bindEmailAddress) {
					qPos.add(emailAddress);
				}

				List<LCSInvitation> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_LPI_EA,
						finderArgs, list);
				}
				else {
					LCSInvitation lcsInvitation = list.get(0);

					result = lcsInvitation;

					cacheResult(lcsInvitation);

					if ((lcsInvitation.getLcsProjectId() != lcsProjectId) ||
							(lcsInvitation.getEmailAddress() == null) ||
							!lcsInvitation.getEmailAddress().equals(emailAddress)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_LPI_EA,
							finderArgs, lcsInvitation);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_LPI_EA, finderArgs);

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
			return (LCSInvitation)result;
		}
	}

	/**
	 * Removes the l c s invitation where lcsProjectId = &#63; and emailAddress = &#63; from the database.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param emailAddress the email address
	 * @return the l c s invitation that was removed
	 */
	@Override
	public LCSInvitation removeByLPI_EA(long lcsProjectId, String emailAddress)
		throws NoSuchLCSInvitationException {
		LCSInvitation lcsInvitation = findByLPI_EA(lcsProjectId, emailAddress);

		return remove(lcsInvitation);
	}

	/**
	 * Returns the number of l c s invitations where lcsProjectId = &#63; and emailAddress = &#63;.
	 *
	 * @param lcsProjectId the lcs project ID
	 * @param emailAddress the email address
	 * @return the number of matching l c s invitations
	 */
	@Override
	public int countByLPI_EA(long lcsProjectId, String emailAddress) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_LPI_EA;

		Object[] finderArgs = new Object[] { lcsProjectId, emailAddress };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LCSINVITATION_WHERE);

			query.append(_FINDER_COLUMN_LPI_EA_LCSPROJECTID_2);

			boolean bindEmailAddress = false;

			if (emailAddress == null) {
				query.append(_FINDER_COLUMN_LPI_EA_EMAILADDRESS_1);
			}
			else if (emailAddress.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_LPI_EA_EMAILADDRESS_3);
			}
			else {
				bindEmailAddress = true;

				query.append(_FINDER_COLUMN_LPI_EA_EMAILADDRESS_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(lcsProjectId);

				if (bindEmailAddress) {
					qPos.add(emailAddress);
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

	private static final String _FINDER_COLUMN_LPI_EA_LCSPROJECTID_2 = "lcsInvitation.lcsProjectId = ? AND ";
	private static final String _FINDER_COLUMN_LPI_EA_EMAILADDRESS_1 = "lcsInvitation.emailAddress IS NULL";
	private static final String _FINDER_COLUMN_LPI_EA_EMAILADDRESS_2 = "lcsInvitation.emailAddress = ?";
	private static final String _FINDER_COLUMN_LPI_EA_EMAILADDRESS_3 = "(lcsInvitation.emailAddress IS NULL OR lcsInvitation.emailAddress = '')";

	public LCSInvitationPersistenceImpl() {
		setModelClass(LCSInvitation.class);
	}

	/**
	 * Caches the l c s invitation in the entity cache if it is enabled.
	 *
	 * @param lcsInvitation the l c s invitation
	 */
	@Override
	public void cacheResult(LCSInvitation lcsInvitation) {
		entityCache.putResult(LCSInvitationModelImpl.ENTITY_CACHE_ENABLED,
			LCSInvitationImpl.class, lcsInvitation.getPrimaryKey(),
			lcsInvitation);

		finderCache.putResult(FINDER_PATH_FETCH_BY_LPI_EA,
			new Object[] {
				lcsInvitation.getLcsProjectId(), lcsInvitation.getEmailAddress()
			}, lcsInvitation);

		lcsInvitation.resetOriginalValues();
	}

	/**
	 * Caches the l c s invitations in the entity cache if it is enabled.
	 *
	 * @param lcsInvitations the l c s invitations
	 */
	@Override
	public void cacheResult(List<LCSInvitation> lcsInvitations) {
		for (LCSInvitation lcsInvitation : lcsInvitations) {
			if (entityCache.getResult(
						LCSInvitationModelImpl.ENTITY_CACHE_ENABLED,
						LCSInvitationImpl.class, lcsInvitation.getPrimaryKey()) == null) {
				cacheResult(lcsInvitation);
			}
			else {
				lcsInvitation.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all l c s invitations.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(LCSInvitationImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the l c s invitation.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LCSInvitation lcsInvitation) {
		entityCache.removeResult(LCSInvitationModelImpl.ENTITY_CACHE_ENABLED,
			LCSInvitationImpl.class, lcsInvitation.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((LCSInvitationModelImpl)lcsInvitation);
	}

	@Override
	public void clearCache(List<LCSInvitation> lcsInvitations) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (LCSInvitation lcsInvitation : lcsInvitations) {
			entityCache.removeResult(LCSInvitationModelImpl.ENTITY_CACHE_ENABLED,
				LCSInvitationImpl.class, lcsInvitation.getPrimaryKey());

			clearUniqueFindersCache((LCSInvitationModelImpl)lcsInvitation);
		}
	}

	protected void cacheUniqueFindersCache(
		LCSInvitationModelImpl lcsInvitationModelImpl, boolean isNew) {
		if (isNew) {
			Object[] args = new Object[] {
					lcsInvitationModelImpl.getLcsProjectId(),
					lcsInvitationModelImpl.getEmailAddress()
				};

			finderCache.putResult(FINDER_PATH_COUNT_BY_LPI_EA, args,
				Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_LPI_EA, args,
				lcsInvitationModelImpl);
		}
		else {
			if ((lcsInvitationModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_LPI_EA.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						lcsInvitationModelImpl.getLcsProjectId(),
						lcsInvitationModelImpl.getEmailAddress()
					};

				finderCache.putResult(FINDER_PATH_COUNT_BY_LPI_EA, args,
					Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_LPI_EA, args,
					lcsInvitationModelImpl);
			}
		}
	}

	protected void clearUniqueFindersCache(
		LCSInvitationModelImpl lcsInvitationModelImpl) {
		Object[] args = new Object[] {
				lcsInvitationModelImpl.getLcsProjectId(),
				lcsInvitationModelImpl.getEmailAddress()
			};

		finderCache.removeResult(FINDER_PATH_COUNT_BY_LPI_EA, args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_LPI_EA, args);

		if ((lcsInvitationModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_LPI_EA.getColumnBitmask()) != 0) {
			args = new Object[] {
					lcsInvitationModelImpl.getOriginalLcsProjectId(),
					lcsInvitationModelImpl.getOriginalEmailAddress()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_LPI_EA, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_LPI_EA, args);
		}
	}

	/**
	 * Creates a new l c s invitation with the primary key. Does not add the l c s invitation to the database.
	 *
	 * @param lcsInvitationId the primary key for the new l c s invitation
	 * @return the new l c s invitation
	 */
	@Override
	public LCSInvitation create(long lcsInvitationId) {
		LCSInvitation lcsInvitation = new LCSInvitationImpl();

		lcsInvitation.setNew(true);
		lcsInvitation.setPrimaryKey(lcsInvitationId);

		return lcsInvitation;
	}

	/**
	 * Removes the l c s invitation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param lcsInvitationId the primary key of the l c s invitation
	 * @return the l c s invitation that was removed
	 * @throws NoSuchLCSInvitationException if a l c s invitation with the primary key could not be found
	 */
	@Override
	public LCSInvitation remove(long lcsInvitationId)
		throws NoSuchLCSInvitationException {
		return remove((Serializable)lcsInvitationId);
	}

	/**
	 * Removes the l c s invitation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the l c s invitation
	 * @return the l c s invitation that was removed
	 * @throws NoSuchLCSInvitationException if a l c s invitation with the primary key could not be found
	 */
	@Override
	public LCSInvitation remove(Serializable primaryKey)
		throws NoSuchLCSInvitationException {
		Session session = null;

		try {
			session = openSession();

			LCSInvitation lcsInvitation = (LCSInvitation)session.get(LCSInvitationImpl.class,
					primaryKey);

			if (lcsInvitation == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLCSInvitationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(lcsInvitation);
		}
		catch (NoSuchLCSInvitationException nsee) {
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
	protected LCSInvitation removeImpl(LCSInvitation lcsInvitation) {
		lcsInvitation = toUnwrappedModel(lcsInvitation);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(lcsInvitation)) {
				lcsInvitation = (LCSInvitation)session.get(LCSInvitationImpl.class,
						lcsInvitation.getPrimaryKeyObj());
			}

			if (lcsInvitation != null) {
				session.delete(lcsInvitation);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (lcsInvitation != null) {
			clearCache(lcsInvitation);
		}

		return lcsInvitation;
	}

	@Override
	public LCSInvitation updateImpl(LCSInvitation lcsInvitation) {
		lcsInvitation = toUnwrappedModel(lcsInvitation);

		boolean isNew = lcsInvitation.isNew();

		LCSInvitationModelImpl lcsInvitationModelImpl = (LCSInvitationModelImpl)lcsInvitation;

		Session session = null;

		try {
			session = openSession();

			if (lcsInvitation.isNew()) {
				session.save(lcsInvitation);

				lcsInvitation.setNew(false);
			}
			else {
				lcsInvitation = (LCSInvitation)session.merge(lcsInvitation);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !LCSInvitationModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((lcsInvitationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LCSPROJECTID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						lcsInvitationModelImpl.getOriginalLcsProjectId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_LCSPROJECTID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LCSPROJECTID,
					args);

				args = new Object[] { lcsInvitationModelImpl.getLcsProjectId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_LCSPROJECTID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LCSPROJECTID,
					args);
			}

			if ((lcsInvitationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EMAILADDRESS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						lcsInvitationModelImpl.getOriginalEmailAddress()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_EMAILADDRESS, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EMAILADDRESS,
					args);

				args = new Object[] { lcsInvitationModelImpl.getEmailAddress() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_EMAILADDRESS, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EMAILADDRESS,
					args);
			}
		}

		entityCache.putResult(LCSInvitationModelImpl.ENTITY_CACHE_ENABLED,
			LCSInvitationImpl.class, lcsInvitation.getPrimaryKey(),
			lcsInvitation, false);

		clearUniqueFindersCache(lcsInvitationModelImpl);
		cacheUniqueFindersCache(lcsInvitationModelImpl, isNew);

		lcsInvitation.resetOriginalValues();

		return lcsInvitation;
	}

	protected LCSInvitation toUnwrappedModel(LCSInvitation lcsInvitation) {
		if (lcsInvitation instanceof LCSInvitationImpl) {
			return lcsInvitation;
		}

		LCSInvitationImpl lcsInvitationImpl = new LCSInvitationImpl();

		lcsInvitationImpl.setNew(lcsInvitation.isNew());
		lcsInvitationImpl.setPrimaryKey(lcsInvitation.getPrimaryKey());

		lcsInvitationImpl.setLcsInvitationId(lcsInvitation.getLcsInvitationId());
		lcsInvitationImpl.setUserId(lcsInvitation.getUserId());
		lcsInvitationImpl.setCreateDate(lcsInvitation.getCreateDate());
		lcsInvitationImpl.setLcsProjectId(lcsInvitation.getLcsProjectId());
		lcsInvitationImpl.setEmailAddress(lcsInvitation.getEmailAddress());
		lcsInvitationImpl.setLcsClusterEntryId(lcsInvitation.getLcsClusterEntryId());
		lcsInvitationImpl.setRole(lcsInvitation.getRole());

		return lcsInvitationImpl;
	}

	/**
	 * Returns the l c s invitation with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the l c s invitation
	 * @return the l c s invitation
	 * @throws NoSuchLCSInvitationException if a l c s invitation with the primary key could not be found
	 */
	@Override
	public LCSInvitation findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLCSInvitationException {
		LCSInvitation lcsInvitation = fetchByPrimaryKey(primaryKey);

		if (lcsInvitation == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLCSInvitationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return lcsInvitation;
	}

	/**
	 * Returns the l c s invitation with the primary key or throws a {@link NoSuchLCSInvitationException} if it could not be found.
	 *
	 * @param lcsInvitationId the primary key of the l c s invitation
	 * @return the l c s invitation
	 * @throws NoSuchLCSInvitationException if a l c s invitation with the primary key could not be found
	 */
	@Override
	public LCSInvitation findByPrimaryKey(long lcsInvitationId)
		throws NoSuchLCSInvitationException {
		return findByPrimaryKey((Serializable)lcsInvitationId);
	}

	/**
	 * Returns the l c s invitation with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the l c s invitation
	 * @return the l c s invitation, or <code>null</code> if a l c s invitation with the primary key could not be found
	 */
	@Override
	public LCSInvitation fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(LCSInvitationModelImpl.ENTITY_CACHE_ENABLED,
				LCSInvitationImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		LCSInvitation lcsInvitation = (LCSInvitation)serializable;

		if (lcsInvitation == null) {
			Session session = null;

			try {
				session = openSession();

				lcsInvitation = (LCSInvitation)session.get(LCSInvitationImpl.class,
						primaryKey);

				if (lcsInvitation != null) {
					cacheResult(lcsInvitation);
				}
				else {
					entityCache.putResult(LCSInvitationModelImpl.ENTITY_CACHE_ENABLED,
						LCSInvitationImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(LCSInvitationModelImpl.ENTITY_CACHE_ENABLED,
					LCSInvitationImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return lcsInvitation;
	}

	/**
	 * Returns the l c s invitation with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param lcsInvitationId the primary key of the l c s invitation
	 * @return the l c s invitation, or <code>null</code> if a l c s invitation with the primary key could not be found
	 */
	@Override
	public LCSInvitation fetchByPrimaryKey(long lcsInvitationId) {
		return fetchByPrimaryKey((Serializable)lcsInvitationId);
	}

	@Override
	public Map<Serializable, LCSInvitation> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, LCSInvitation> map = new HashMap<Serializable, LCSInvitation>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			LCSInvitation lcsInvitation = fetchByPrimaryKey(primaryKey);

			if (lcsInvitation != null) {
				map.put(primaryKey, lcsInvitation);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(LCSInvitationModelImpl.ENTITY_CACHE_ENABLED,
					LCSInvitationImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (LCSInvitation)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_LCSINVITATION_WHERE_PKS_IN);

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

			for (LCSInvitation lcsInvitation : (List<LCSInvitation>)q.list()) {
				map.put(lcsInvitation.getPrimaryKeyObj(), lcsInvitation);

				cacheResult(lcsInvitation);

				uncachedPrimaryKeys.remove(lcsInvitation.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(LCSInvitationModelImpl.ENTITY_CACHE_ENABLED,
					LCSInvitationImpl.class, primaryKey, nullModel);
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
	 * Returns all the l c s invitations.
	 *
	 * @return the l c s invitations
	 */
	@Override
	public List<LCSInvitation> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the l c s invitations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSInvitationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of l c s invitations
	 * @param end the upper bound of the range of l c s invitations (not inclusive)
	 * @return the range of l c s invitations
	 */
	@Override
	public List<LCSInvitation> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the l c s invitations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSInvitationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of l c s invitations
	 * @param end the upper bound of the range of l c s invitations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of l c s invitations
	 */
	@Override
	public List<LCSInvitation> findAll(int start, int end,
		OrderByComparator<LCSInvitation> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the l c s invitations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSInvitationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of l c s invitations
	 * @param end the upper bound of the range of l c s invitations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of l c s invitations
	 */
	@Override
	public List<LCSInvitation> findAll(int start, int end,
		OrderByComparator<LCSInvitation> orderByComparator,
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

		List<LCSInvitation> list = null;

		if (retrieveFromCache) {
			list = (List<LCSInvitation>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_LCSINVITATION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_LCSINVITATION;

				if (pagination) {
					sql = sql.concat(LCSInvitationModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<LCSInvitation>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSInvitation>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the l c s invitations from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (LCSInvitation lcsInvitation : findAll()) {
			remove(lcsInvitation);
		}
	}

	/**
	 * Returns the number of l c s invitations.
	 *
	 * @return the number of l c s invitations
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_LCSINVITATION);

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
		return LCSInvitationModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the l c s invitation persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(LCSInvitationImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_LCSINVITATION = "SELECT lcsInvitation FROM LCSInvitation lcsInvitation";
	private static final String _SQL_SELECT_LCSINVITATION_WHERE_PKS_IN = "SELECT lcsInvitation FROM LCSInvitation lcsInvitation WHERE lcsInvitationId IN (";
	private static final String _SQL_SELECT_LCSINVITATION_WHERE = "SELECT lcsInvitation FROM LCSInvitation lcsInvitation WHERE ";
	private static final String _SQL_COUNT_LCSINVITATION = "SELECT COUNT(lcsInvitation) FROM LCSInvitation lcsInvitation";
	private static final String _SQL_COUNT_LCSINVITATION_WHERE = "SELECT COUNT(lcsInvitation) FROM LCSInvitation lcsInvitation WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "lcsInvitation.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No LCSInvitation exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No LCSInvitation exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(LCSInvitationPersistenceImpl.class);
}