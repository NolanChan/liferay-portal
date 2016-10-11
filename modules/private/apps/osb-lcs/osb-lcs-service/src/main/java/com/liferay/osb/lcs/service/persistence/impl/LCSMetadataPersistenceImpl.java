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

import com.liferay.osb.lcs.exception.NoSuchLCSMetadataException;
import com.liferay.osb.lcs.model.LCSMetadata;
import com.liferay.osb.lcs.model.impl.LCSMetadataImpl;
import com.liferay.osb.lcs.model.impl.LCSMetadataModelImpl;
import com.liferay.osb.lcs.service.persistence.LCSMetadataPersistence;

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
 * The persistence implementation for the l c s metadata service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Igor Beslic
 * @see LCSMetadataPersistence
 * @see com.liferay.osb.lcs.service.persistence.LCSMetadataUtil
 * @generated
 */
@ProviderType
public class LCSMetadataPersistenceImpl extends BasePersistenceImpl<LCSMetadata>
	implements LCSMetadataPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link LCSMetadataUtil} to access the l c s metadata persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = LCSMetadataImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(LCSMetadataModelImpl.ENTITY_CACHE_ENABLED,
			LCSMetadataModelImpl.FINDER_CACHE_ENABLED, LCSMetadataImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(LCSMetadataModelImpl.ENTITY_CACHE_ENABLED,
			LCSMetadataModelImpl.FINDER_CACHE_ENABLED, LCSMetadataImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(LCSMetadataModelImpl.ENTITY_CACHE_ENABLED,
			LCSMetadataModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_BN_PE = new FinderPath(LCSMetadataModelImpl.ENTITY_CACHE_ENABLED,
			LCSMetadataModelImpl.FINDER_CACHE_ENABLED, LCSMetadataImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByBN_PE",
			new String[] {
				Integer.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BN_PE = new FinderPath(LCSMetadataModelImpl.ENTITY_CACHE_ENABLED,
			LCSMetadataModelImpl.FINDER_CACHE_ENABLED, LCSMetadataImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByBN_PE",
			new String[] { Integer.class.getName(), String.class.getName() },
			LCSMetadataModelImpl.BUILDNUMBER_COLUMN_BITMASK |
			LCSMetadataModelImpl.PORTALEDITION_COLUMN_BITMASK |
			LCSMetadataModelImpl.GITTAG_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_BN_PE = new FinderPath(LCSMetadataModelImpl.ENTITY_CACHE_ENABLED,
			LCSMetadataModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByBN_PE",
			new String[] { Integer.class.getName(), String.class.getName() });

	/**
	 * Returns all the l c s metadatas where buildNumber = &#63; and portalEdition = &#63;.
	 *
	 * @param buildNumber the build number
	 * @param portalEdition the portal edition
	 * @return the matching l c s metadatas
	 */
	@Override
	public List<LCSMetadata> findByBN_PE(int buildNumber, String portalEdition) {
		return findByBN_PE(buildNumber, portalEdition, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the l c s metadatas where buildNumber = &#63; and portalEdition = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSMetadataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param buildNumber the build number
	 * @param portalEdition the portal edition
	 * @param start the lower bound of the range of l c s metadatas
	 * @param end the upper bound of the range of l c s metadatas (not inclusive)
	 * @return the range of matching l c s metadatas
	 */
	@Override
	public List<LCSMetadata> findByBN_PE(int buildNumber, String portalEdition,
		int start, int end) {
		return findByBN_PE(buildNumber, portalEdition, start, end, null);
	}

	/**
	 * Returns an ordered range of all the l c s metadatas where buildNumber = &#63; and portalEdition = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSMetadataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param buildNumber the build number
	 * @param portalEdition the portal edition
	 * @param start the lower bound of the range of l c s metadatas
	 * @param end the upper bound of the range of l c s metadatas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching l c s metadatas
	 */
	@Override
	public List<LCSMetadata> findByBN_PE(int buildNumber, String portalEdition,
		int start, int end, OrderByComparator<LCSMetadata> orderByComparator) {
		return findByBN_PE(buildNumber, portalEdition, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the l c s metadatas where buildNumber = &#63; and portalEdition = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSMetadataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param buildNumber the build number
	 * @param portalEdition the portal edition
	 * @param start the lower bound of the range of l c s metadatas
	 * @param end the upper bound of the range of l c s metadatas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching l c s metadatas
	 */
	@Override
	public List<LCSMetadata> findByBN_PE(int buildNumber, String portalEdition,
		int start, int end, OrderByComparator<LCSMetadata> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BN_PE;
			finderArgs = new Object[] { buildNumber, portalEdition };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_BN_PE;
			finderArgs = new Object[] {
					buildNumber, portalEdition,
					
					start, end, orderByComparator
				};
		}

		List<LCSMetadata> list = null;

		if (retrieveFromCache) {
			list = (List<LCSMetadata>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LCSMetadata lcsMetadata : list) {
					if ((buildNumber != lcsMetadata.getBuildNumber()) ||
							!Objects.equals(portalEdition,
								lcsMetadata.getPortalEdition())) {
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

			query.append(_SQL_SELECT_LCSMETADATA_WHERE);

			query.append(_FINDER_COLUMN_BN_PE_BUILDNUMBER_2);

			boolean bindPortalEdition = false;

			if (portalEdition == null) {
				query.append(_FINDER_COLUMN_BN_PE_PORTALEDITION_1);
			}
			else if (portalEdition.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_BN_PE_PORTALEDITION_3);
			}
			else {
				bindPortalEdition = true;

				query.append(_FINDER_COLUMN_BN_PE_PORTALEDITION_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LCSMetadataModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(buildNumber);

				if (bindPortalEdition) {
					qPos.add(portalEdition);
				}

				if (!pagination) {
					list = (List<LCSMetadata>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSMetadata>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first l c s metadata in the ordered set where buildNumber = &#63; and portalEdition = &#63;.
	 *
	 * @param buildNumber the build number
	 * @param portalEdition the portal edition
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s metadata
	 * @throws NoSuchLCSMetadataException if a matching l c s metadata could not be found
	 */
	@Override
	public LCSMetadata findByBN_PE_First(int buildNumber, String portalEdition,
		OrderByComparator<LCSMetadata> orderByComparator)
		throws NoSuchLCSMetadataException {
		LCSMetadata lcsMetadata = fetchByBN_PE_First(buildNumber,
				portalEdition, orderByComparator);

		if (lcsMetadata != null) {
			return lcsMetadata;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("buildNumber=");
		msg.append(buildNumber);

		msg.append(", portalEdition=");
		msg.append(portalEdition);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSMetadataException(msg.toString());
	}

	/**
	 * Returns the first l c s metadata in the ordered set where buildNumber = &#63; and portalEdition = &#63;.
	 *
	 * @param buildNumber the build number
	 * @param portalEdition the portal edition
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s metadata, or <code>null</code> if a matching l c s metadata could not be found
	 */
	@Override
	public LCSMetadata fetchByBN_PE_First(int buildNumber,
		String portalEdition, OrderByComparator<LCSMetadata> orderByComparator) {
		List<LCSMetadata> list = findByBN_PE(buildNumber, portalEdition, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last l c s metadata in the ordered set where buildNumber = &#63; and portalEdition = &#63;.
	 *
	 * @param buildNumber the build number
	 * @param portalEdition the portal edition
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s metadata
	 * @throws NoSuchLCSMetadataException if a matching l c s metadata could not be found
	 */
	@Override
	public LCSMetadata findByBN_PE_Last(int buildNumber, String portalEdition,
		OrderByComparator<LCSMetadata> orderByComparator)
		throws NoSuchLCSMetadataException {
		LCSMetadata lcsMetadata = fetchByBN_PE_Last(buildNumber, portalEdition,
				orderByComparator);

		if (lcsMetadata != null) {
			return lcsMetadata;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("buildNumber=");
		msg.append(buildNumber);

		msg.append(", portalEdition=");
		msg.append(portalEdition);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSMetadataException(msg.toString());
	}

	/**
	 * Returns the last l c s metadata in the ordered set where buildNumber = &#63; and portalEdition = &#63;.
	 *
	 * @param buildNumber the build number
	 * @param portalEdition the portal edition
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s metadata, or <code>null</code> if a matching l c s metadata could not be found
	 */
	@Override
	public LCSMetadata fetchByBN_PE_Last(int buildNumber, String portalEdition,
		OrderByComparator<LCSMetadata> orderByComparator) {
		int count = countByBN_PE(buildNumber, portalEdition);

		if (count == 0) {
			return null;
		}

		List<LCSMetadata> list = findByBN_PE(buildNumber, portalEdition,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the l c s metadatas before and after the current l c s metadata in the ordered set where buildNumber = &#63; and portalEdition = &#63;.
	 *
	 * @param lcsMetadataId the primary key of the current l c s metadata
	 * @param buildNumber the build number
	 * @param portalEdition the portal edition
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next l c s metadata
	 * @throws NoSuchLCSMetadataException if a l c s metadata with the primary key could not be found
	 */
	@Override
	public LCSMetadata[] findByBN_PE_PrevAndNext(long lcsMetadataId,
		int buildNumber, String portalEdition,
		OrderByComparator<LCSMetadata> orderByComparator)
		throws NoSuchLCSMetadataException {
		LCSMetadata lcsMetadata = findByPrimaryKey(lcsMetadataId);

		Session session = null;

		try {
			session = openSession();

			LCSMetadata[] array = new LCSMetadataImpl[3];

			array[0] = getByBN_PE_PrevAndNext(session, lcsMetadata,
					buildNumber, portalEdition, orderByComparator, true);

			array[1] = lcsMetadata;

			array[2] = getByBN_PE_PrevAndNext(session, lcsMetadata,
					buildNumber, portalEdition, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LCSMetadata getByBN_PE_PrevAndNext(Session session,
		LCSMetadata lcsMetadata, int buildNumber, String portalEdition,
		OrderByComparator<LCSMetadata> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_LCSMETADATA_WHERE);

		query.append(_FINDER_COLUMN_BN_PE_BUILDNUMBER_2);

		boolean bindPortalEdition = false;

		if (portalEdition == null) {
			query.append(_FINDER_COLUMN_BN_PE_PORTALEDITION_1);
		}
		else if (portalEdition.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_BN_PE_PORTALEDITION_3);
		}
		else {
			bindPortalEdition = true;

			query.append(_FINDER_COLUMN_BN_PE_PORTALEDITION_2);
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
			query.append(LCSMetadataModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(buildNumber);

		if (bindPortalEdition) {
			qPos.add(portalEdition);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(lcsMetadata);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LCSMetadata> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the l c s metadatas where buildNumber = &#63; and portalEdition = &#63; from the database.
	 *
	 * @param buildNumber the build number
	 * @param portalEdition the portal edition
	 */
	@Override
	public void removeByBN_PE(int buildNumber, String portalEdition) {
		for (LCSMetadata lcsMetadata : findByBN_PE(buildNumber, portalEdition,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(lcsMetadata);
		}
	}

	/**
	 * Returns the number of l c s metadatas where buildNumber = &#63; and portalEdition = &#63;.
	 *
	 * @param buildNumber the build number
	 * @param portalEdition the portal edition
	 * @return the number of matching l c s metadatas
	 */
	@Override
	public int countByBN_PE(int buildNumber, String portalEdition) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_BN_PE;

		Object[] finderArgs = new Object[] { buildNumber, portalEdition };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LCSMETADATA_WHERE);

			query.append(_FINDER_COLUMN_BN_PE_BUILDNUMBER_2);

			boolean bindPortalEdition = false;

			if (portalEdition == null) {
				query.append(_FINDER_COLUMN_BN_PE_PORTALEDITION_1);
			}
			else if (portalEdition.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_BN_PE_PORTALEDITION_3);
			}
			else {
				bindPortalEdition = true;

				query.append(_FINDER_COLUMN_BN_PE_PORTALEDITION_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(buildNumber);

				if (bindPortalEdition) {
					qPos.add(portalEdition);
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

	private static final String _FINDER_COLUMN_BN_PE_BUILDNUMBER_2 = "lcsMetadata.buildNumber = ? AND ";
	private static final String _FINDER_COLUMN_BN_PE_PORTALEDITION_1 = "lcsMetadata.portalEdition IS NULL";
	private static final String _FINDER_COLUMN_BN_PE_PORTALEDITION_2 = "lcsMetadata.portalEdition = ?";
	private static final String _FINDER_COLUMN_BN_PE_PORTALEDITION_3 = "(lcsMetadata.portalEdition IS NULL OR lcsMetadata.portalEdition = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_BN_GT_PE = new FinderPath(LCSMetadataModelImpl.ENTITY_CACHE_ENABLED,
			LCSMetadataModelImpl.FINDER_CACHE_ENABLED, LCSMetadataImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByBN_GT_PE",
			new String[] {
				Integer.class.getName(), String.class.getName(),
				String.class.getName()
			},
			LCSMetadataModelImpl.BUILDNUMBER_COLUMN_BITMASK |
			LCSMetadataModelImpl.GITTAG_COLUMN_BITMASK |
			LCSMetadataModelImpl.PORTALEDITION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_BN_GT_PE = new FinderPath(LCSMetadataModelImpl.ENTITY_CACHE_ENABLED,
			LCSMetadataModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByBN_GT_PE",
			new String[] {
				Integer.class.getName(), String.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns the l c s metadata where buildNumber = &#63; and gitTag = &#63; and portalEdition = &#63; or throws a {@link NoSuchLCSMetadataException} if it could not be found.
	 *
	 * @param buildNumber the build number
	 * @param gitTag the git tag
	 * @param portalEdition the portal edition
	 * @return the matching l c s metadata
	 * @throws NoSuchLCSMetadataException if a matching l c s metadata could not be found
	 */
	@Override
	public LCSMetadata findByBN_GT_PE(int buildNumber, String gitTag,
		String portalEdition) throws NoSuchLCSMetadataException {
		LCSMetadata lcsMetadata = fetchByBN_GT_PE(buildNumber, gitTag,
				portalEdition);

		if (lcsMetadata == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("buildNumber=");
			msg.append(buildNumber);

			msg.append(", gitTag=");
			msg.append(gitTag);

			msg.append(", portalEdition=");
			msg.append(portalEdition);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchLCSMetadataException(msg.toString());
		}

		return lcsMetadata;
	}

	/**
	 * Returns the l c s metadata where buildNumber = &#63; and gitTag = &#63; and portalEdition = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param buildNumber the build number
	 * @param gitTag the git tag
	 * @param portalEdition the portal edition
	 * @return the matching l c s metadata, or <code>null</code> if a matching l c s metadata could not be found
	 */
	@Override
	public LCSMetadata fetchByBN_GT_PE(int buildNumber, String gitTag,
		String portalEdition) {
		return fetchByBN_GT_PE(buildNumber, gitTag, portalEdition, true);
	}

	/**
	 * Returns the l c s metadata where buildNumber = &#63; and gitTag = &#63; and portalEdition = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param buildNumber the build number
	 * @param gitTag the git tag
	 * @param portalEdition the portal edition
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching l c s metadata, or <code>null</code> if a matching l c s metadata could not be found
	 */
	@Override
	public LCSMetadata fetchByBN_GT_PE(int buildNumber, String gitTag,
		String portalEdition, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { buildNumber, gitTag, portalEdition };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_BN_GT_PE,
					finderArgs, this);
		}

		if (result instanceof LCSMetadata) {
			LCSMetadata lcsMetadata = (LCSMetadata)result;

			if ((buildNumber != lcsMetadata.getBuildNumber()) ||
					!Objects.equals(gitTag, lcsMetadata.getGitTag()) ||
					!Objects.equals(portalEdition,
						lcsMetadata.getPortalEdition())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_LCSMETADATA_WHERE);

			query.append(_FINDER_COLUMN_BN_GT_PE_BUILDNUMBER_2);

			boolean bindGitTag = false;

			if (gitTag == null) {
				query.append(_FINDER_COLUMN_BN_GT_PE_GITTAG_1);
			}
			else if (gitTag.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_BN_GT_PE_GITTAG_3);
			}
			else {
				bindGitTag = true;

				query.append(_FINDER_COLUMN_BN_GT_PE_GITTAG_2);
			}

			boolean bindPortalEdition = false;

			if (portalEdition == null) {
				query.append(_FINDER_COLUMN_BN_GT_PE_PORTALEDITION_1);
			}
			else if (portalEdition.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_BN_GT_PE_PORTALEDITION_3);
			}
			else {
				bindPortalEdition = true;

				query.append(_FINDER_COLUMN_BN_GT_PE_PORTALEDITION_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(buildNumber);

				if (bindGitTag) {
					qPos.add(gitTag);
				}

				if (bindPortalEdition) {
					qPos.add(portalEdition);
				}

				List<LCSMetadata> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_BN_GT_PE,
						finderArgs, list);
				}
				else {
					LCSMetadata lcsMetadata = list.get(0);

					result = lcsMetadata;

					cacheResult(lcsMetadata);

					if ((lcsMetadata.getBuildNumber() != buildNumber) ||
							(lcsMetadata.getGitTag() == null) ||
							!lcsMetadata.getGitTag().equals(gitTag) ||
							(lcsMetadata.getPortalEdition() == null) ||
							!lcsMetadata.getPortalEdition().equals(portalEdition)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_BN_GT_PE,
							finderArgs, lcsMetadata);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_BN_GT_PE,
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
			return (LCSMetadata)result;
		}
	}

	/**
	 * Removes the l c s metadata where buildNumber = &#63; and gitTag = &#63; and portalEdition = &#63; from the database.
	 *
	 * @param buildNumber the build number
	 * @param gitTag the git tag
	 * @param portalEdition the portal edition
	 * @return the l c s metadata that was removed
	 */
	@Override
	public LCSMetadata removeByBN_GT_PE(int buildNumber, String gitTag,
		String portalEdition) throws NoSuchLCSMetadataException {
		LCSMetadata lcsMetadata = findByBN_GT_PE(buildNumber, gitTag,
				portalEdition);

		return remove(lcsMetadata);
	}

	/**
	 * Returns the number of l c s metadatas where buildNumber = &#63; and gitTag = &#63; and portalEdition = &#63;.
	 *
	 * @param buildNumber the build number
	 * @param gitTag the git tag
	 * @param portalEdition the portal edition
	 * @return the number of matching l c s metadatas
	 */
	@Override
	public int countByBN_GT_PE(int buildNumber, String gitTag,
		String portalEdition) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_BN_GT_PE;

		Object[] finderArgs = new Object[] { buildNumber, gitTag, portalEdition };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_LCSMETADATA_WHERE);

			query.append(_FINDER_COLUMN_BN_GT_PE_BUILDNUMBER_2);

			boolean bindGitTag = false;

			if (gitTag == null) {
				query.append(_FINDER_COLUMN_BN_GT_PE_GITTAG_1);
			}
			else if (gitTag.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_BN_GT_PE_GITTAG_3);
			}
			else {
				bindGitTag = true;

				query.append(_FINDER_COLUMN_BN_GT_PE_GITTAG_2);
			}

			boolean bindPortalEdition = false;

			if (portalEdition == null) {
				query.append(_FINDER_COLUMN_BN_GT_PE_PORTALEDITION_1);
			}
			else if (portalEdition.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_BN_GT_PE_PORTALEDITION_3);
			}
			else {
				bindPortalEdition = true;

				query.append(_FINDER_COLUMN_BN_GT_PE_PORTALEDITION_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(buildNumber);

				if (bindGitTag) {
					qPos.add(gitTag);
				}

				if (bindPortalEdition) {
					qPos.add(portalEdition);
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

	private static final String _FINDER_COLUMN_BN_GT_PE_BUILDNUMBER_2 = "lcsMetadata.buildNumber = ? AND ";
	private static final String _FINDER_COLUMN_BN_GT_PE_GITTAG_1 = "lcsMetadata.gitTag IS NULL AND ";
	private static final String _FINDER_COLUMN_BN_GT_PE_GITTAG_2 = "lcsMetadata.gitTag = ? AND ";
	private static final String _FINDER_COLUMN_BN_GT_PE_GITTAG_3 = "(lcsMetadata.gitTag IS NULL OR lcsMetadata.gitTag = '') AND ";
	private static final String _FINDER_COLUMN_BN_GT_PE_PORTALEDITION_1 = "lcsMetadata.portalEdition IS NULL";
	private static final String _FINDER_COLUMN_BN_GT_PE_PORTALEDITION_2 = "lcsMetadata.portalEdition = ?";
	private static final String _FINDER_COLUMN_BN_GT_PE_PORTALEDITION_3 = "(lcsMetadata.portalEdition IS NULL OR lcsMetadata.portalEdition = '')";

	public LCSMetadataPersistenceImpl() {
		setModelClass(LCSMetadata.class);
	}

	/**
	 * Caches the l c s metadata in the entity cache if it is enabled.
	 *
	 * @param lcsMetadata the l c s metadata
	 */
	@Override
	public void cacheResult(LCSMetadata lcsMetadata) {
		entityCache.putResult(LCSMetadataModelImpl.ENTITY_CACHE_ENABLED,
			LCSMetadataImpl.class, lcsMetadata.getPrimaryKey(), lcsMetadata);

		finderCache.putResult(FINDER_PATH_FETCH_BY_BN_GT_PE,
			new Object[] {
				lcsMetadata.getBuildNumber(), lcsMetadata.getGitTag(),
				lcsMetadata.getPortalEdition()
			}, lcsMetadata);

		lcsMetadata.resetOriginalValues();
	}

	/**
	 * Caches the l c s metadatas in the entity cache if it is enabled.
	 *
	 * @param lcsMetadatas the l c s metadatas
	 */
	@Override
	public void cacheResult(List<LCSMetadata> lcsMetadatas) {
		for (LCSMetadata lcsMetadata : lcsMetadatas) {
			if (entityCache.getResult(
						LCSMetadataModelImpl.ENTITY_CACHE_ENABLED,
						LCSMetadataImpl.class, lcsMetadata.getPrimaryKey()) == null) {
				cacheResult(lcsMetadata);
			}
			else {
				lcsMetadata.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all l c s metadatas.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(LCSMetadataImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the l c s metadata.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LCSMetadata lcsMetadata) {
		entityCache.removeResult(LCSMetadataModelImpl.ENTITY_CACHE_ENABLED,
			LCSMetadataImpl.class, lcsMetadata.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((LCSMetadataModelImpl)lcsMetadata);
	}

	@Override
	public void clearCache(List<LCSMetadata> lcsMetadatas) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (LCSMetadata lcsMetadata : lcsMetadatas) {
			entityCache.removeResult(LCSMetadataModelImpl.ENTITY_CACHE_ENABLED,
				LCSMetadataImpl.class, lcsMetadata.getPrimaryKey());

			clearUniqueFindersCache((LCSMetadataModelImpl)lcsMetadata);
		}
	}

	protected void cacheUniqueFindersCache(
		LCSMetadataModelImpl lcsMetadataModelImpl, boolean isNew) {
		if (isNew) {
			Object[] args = new Object[] {
					lcsMetadataModelImpl.getBuildNumber(),
					lcsMetadataModelImpl.getGitTag(),
					lcsMetadataModelImpl.getPortalEdition()
				};

			finderCache.putResult(FINDER_PATH_COUNT_BY_BN_GT_PE, args,
				Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_BN_GT_PE, args,
				lcsMetadataModelImpl);
		}
		else {
			if ((lcsMetadataModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_BN_GT_PE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						lcsMetadataModelImpl.getBuildNumber(),
						lcsMetadataModelImpl.getGitTag(),
						lcsMetadataModelImpl.getPortalEdition()
					};

				finderCache.putResult(FINDER_PATH_COUNT_BY_BN_GT_PE, args,
					Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_BN_GT_PE, args,
					lcsMetadataModelImpl);
			}
		}
	}

	protected void clearUniqueFindersCache(
		LCSMetadataModelImpl lcsMetadataModelImpl) {
		Object[] args = new Object[] {
				lcsMetadataModelImpl.getBuildNumber(),
				lcsMetadataModelImpl.getGitTag(),
				lcsMetadataModelImpl.getPortalEdition()
			};

		finderCache.removeResult(FINDER_PATH_COUNT_BY_BN_GT_PE, args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_BN_GT_PE, args);

		if ((lcsMetadataModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_BN_GT_PE.getColumnBitmask()) != 0) {
			args = new Object[] {
					lcsMetadataModelImpl.getOriginalBuildNumber(),
					lcsMetadataModelImpl.getOriginalGitTag(),
					lcsMetadataModelImpl.getOriginalPortalEdition()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_BN_GT_PE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_BN_GT_PE, args);
		}
	}

	/**
	 * Creates a new l c s metadata with the primary key. Does not add the l c s metadata to the database.
	 *
	 * @param lcsMetadataId the primary key for the new l c s metadata
	 * @return the new l c s metadata
	 */
	@Override
	public LCSMetadata create(long lcsMetadataId) {
		LCSMetadata lcsMetadata = new LCSMetadataImpl();

		lcsMetadata.setNew(true);
		lcsMetadata.setPrimaryKey(lcsMetadataId);

		return lcsMetadata;
	}

	/**
	 * Removes the l c s metadata with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param lcsMetadataId the primary key of the l c s metadata
	 * @return the l c s metadata that was removed
	 * @throws NoSuchLCSMetadataException if a l c s metadata with the primary key could not be found
	 */
	@Override
	public LCSMetadata remove(long lcsMetadataId)
		throws NoSuchLCSMetadataException {
		return remove((Serializable)lcsMetadataId);
	}

	/**
	 * Removes the l c s metadata with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the l c s metadata
	 * @return the l c s metadata that was removed
	 * @throws NoSuchLCSMetadataException if a l c s metadata with the primary key could not be found
	 */
	@Override
	public LCSMetadata remove(Serializable primaryKey)
		throws NoSuchLCSMetadataException {
		Session session = null;

		try {
			session = openSession();

			LCSMetadata lcsMetadata = (LCSMetadata)session.get(LCSMetadataImpl.class,
					primaryKey);

			if (lcsMetadata == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLCSMetadataException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(lcsMetadata);
		}
		catch (NoSuchLCSMetadataException nsee) {
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
	protected LCSMetadata removeImpl(LCSMetadata lcsMetadata) {
		lcsMetadata = toUnwrappedModel(lcsMetadata);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(lcsMetadata)) {
				lcsMetadata = (LCSMetadata)session.get(LCSMetadataImpl.class,
						lcsMetadata.getPrimaryKeyObj());
			}

			if (lcsMetadata != null) {
				session.delete(lcsMetadata);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (lcsMetadata != null) {
			clearCache(lcsMetadata);
		}

		return lcsMetadata;
	}

	@Override
	public LCSMetadata updateImpl(LCSMetadata lcsMetadata) {
		lcsMetadata = toUnwrappedModel(lcsMetadata);

		boolean isNew = lcsMetadata.isNew();

		LCSMetadataModelImpl lcsMetadataModelImpl = (LCSMetadataModelImpl)lcsMetadata;

		Session session = null;

		try {
			session = openSession();

			if (lcsMetadata.isNew()) {
				session.save(lcsMetadata);

				lcsMetadata.setNew(false);
			}
			else {
				lcsMetadata = (LCSMetadata)session.merge(lcsMetadata);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !LCSMetadataModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((lcsMetadataModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BN_PE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						lcsMetadataModelImpl.getOriginalBuildNumber(),
						lcsMetadataModelImpl.getOriginalPortalEdition()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_BN_PE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BN_PE,
					args);

				args = new Object[] {
						lcsMetadataModelImpl.getBuildNumber(),
						lcsMetadataModelImpl.getPortalEdition()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_BN_PE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BN_PE,
					args);
			}
		}

		entityCache.putResult(LCSMetadataModelImpl.ENTITY_CACHE_ENABLED,
			LCSMetadataImpl.class, lcsMetadata.getPrimaryKey(), lcsMetadata,
			false);

		clearUniqueFindersCache(lcsMetadataModelImpl);
		cacheUniqueFindersCache(lcsMetadataModelImpl, isNew);

		lcsMetadata.resetOriginalValues();

		return lcsMetadata;
	}

	protected LCSMetadata toUnwrappedModel(LCSMetadata lcsMetadata) {
		if (lcsMetadata instanceof LCSMetadataImpl) {
			return lcsMetadata;
		}

		LCSMetadataImpl lcsMetadataImpl = new LCSMetadataImpl();

		lcsMetadataImpl.setNew(lcsMetadata.isNew());
		lcsMetadataImpl.setPrimaryKey(lcsMetadata.getPrimaryKey());

		lcsMetadataImpl.setLcsMetadataId(lcsMetadata.getLcsMetadataId());
		lcsMetadataImpl.setAvailabilityIndex(lcsMetadata.getAvailabilityIndex());
		lcsMetadataImpl.setBuildNumber(lcsMetadata.getBuildNumber());
		lcsMetadataImpl.setGitTag(lcsMetadata.getGitTag());
		lcsMetadataImpl.setPortalEdition(lcsMetadata.getPortalEdition());
		lcsMetadataImpl.setSupportedLCSPortlet(lcsMetadata.getSupportedLCSPortlet());
		lcsMetadataImpl.setSupportedPatchingTool(lcsMetadata.getSupportedPatchingTool());

		return lcsMetadataImpl;
	}

	/**
	 * Returns the l c s metadata with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the l c s metadata
	 * @return the l c s metadata
	 * @throws NoSuchLCSMetadataException if a l c s metadata with the primary key could not be found
	 */
	@Override
	public LCSMetadata findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLCSMetadataException {
		LCSMetadata lcsMetadata = fetchByPrimaryKey(primaryKey);

		if (lcsMetadata == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLCSMetadataException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return lcsMetadata;
	}

	/**
	 * Returns the l c s metadata with the primary key or throws a {@link NoSuchLCSMetadataException} if it could not be found.
	 *
	 * @param lcsMetadataId the primary key of the l c s metadata
	 * @return the l c s metadata
	 * @throws NoSuchLCSMetadataException if a l c s metadata with the primary key could not be found
	 */
	@Override
	public LCSMetadata findByPrimaryKey(long lcsMetadataId)
		throws NoSuchLCSMetadataException {
		return findByPrimaryKey((Serializable)lcsMetadataId);
	}

	/**
	 * Returns the l c s metadata with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the l c s metadata
	 * @return the l c s metadata, or <code>null</code> if a l c s metadata with the primary key could not be found
	 */
	@Override
	public LCSMetadata fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(LCSMetadataModelImpl.ENTITY_CACHE_ENABLED,
				LCSMetadataImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		LCSMetadata lcsMetadata = (LCSMetadata)serializable;

		if (lcsMetadata == null) {
			Session session = null;

			try {
				session = openSession();

				lcsMetadata = (LCSMetadata)session.get(LCSMetadataImpl.class,
						primaryKey);

				if (lcsMetadata != null) {
					cacheResult(lcsMetadata);
				}
				else {
					entityCache.putResult(LCSMetadataModelImpl.ENTITY_CACHE_ENABLED,
						LCSMetadataImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(LCSMetadataModelImpl.ENTITY_CACHE_ENABLED,
					LCSMetadataImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return lcsMetadata;
	}

	/**
	 * Returns the l c s metadata with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param lcsMetadataId the primary key of the l c s metadata
	 * @return the l c s metadata, or <code>null</code> if a l c s metadata with the primary key could not be found
	 */
	@Override
	public LCSMetadata fetchByPrimaryKey(long lcsMetadataId) {
		return fetchByPrimaryKey((Serializable)lcsMetadataId);
	}

	@Override
	public Map<Serializable, LCSMetadata> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, LCSMetadata> map = new HashMap<Serializable, LCSMetadata>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			LCSMetadata lcsMetadata = fetchByPrimaryKey(primaryKey);

			if (lcsMetadata != null) {
				map.put(primaryKey, lcsMetadata);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(LCSMetadataModelImpl.ENTITY_CACHE_ENABLED,
					LCSMetadataImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (LCSMetadata)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_LCSMETADATA_WHERE_PKS_IN);

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

			for (LCSMetadata lcsMetadata : (List<LCSMetadata>)q.list()) {
				map.put(lcsMetadata.getPrimaryKeyObj(), lcsMetadata);

				cacheResult(lcsMetadata);

				uncachedPrimaryKeys.remove(lcsMetadata.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(LCSMetadataModelImpl.ENTITY_CACHE_ENABLED,
					LCSMetadataImpl.class, primaryKey, nullModel);
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
	 * Returns all the l c s metadatas.
	 *
	 * @return the l c s metadatas
	 */
	@Override
	public List<LCSMetadata> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the l c s metadatas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSMetadataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of l c s metadatas
	 * @param end the upper bound of the range of l c s metadatas (not inclusive)
	 * @return the range of l c s metadatas
	 */
	@Override
	public List<LCSMetadata> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the l c s metadatas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSMetadataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of l c s metadatas
	 * @param end the upper bound of the range of l c s metadatas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of l c s metadatas
	 */
	@Override
	public List<LCSMetadata> findAll(int start, int end,
		OrderByComparator<LCSMetadata> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the l c s metadatas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSMetadataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of l c s metadatas
	 * @param end the upper bound of the range of l c s metadatas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of l c s metadatas
	 */
	@Override
	public List<LCSMetadata> findAll(int start, int end,
		OrderByComparator<LCSMetadata> orderByComparator,
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

		List<LCSMetadata> list = null;

		if (retrieveFromCache) {
			list = (List<LCSMetadata>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_LCSMETADATA);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_LCSMETADATA;

				if (pagination) {
					sql = sql.concat(LCSMetadataModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<LCSMetadata>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSMetadata>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the l c s metadatas from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (LCSMetadata lcsMetadata : findAll()) {
			remove(lcsMetadata);
		}
	}

	/**
	 * Returns the number of l c s metadatas.
	 *
	 * @return the number of l c s metadatas
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_LCSMETADATA);

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
		return LCSMetadataModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the l c s metadata persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(LCSMetadataImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_LCSMETADATA = "SELECT lcsMetadata FROM LCSMetadata lcsMetadata";
	private static final String _SQL_SELECT_LCSMETADATA_WHERE_PKS_IN = "SELECT lcsMetadata FROM LCSMetadata lcsMetadata WHERE lcsMetadataId IN (";
	private static final String _SQL_SELECT_LCSMETADATA_WHERE = "SELECT lcsMetadata FROM LCSMetadata lcsMetadata WHERE ";
	private static final String _SQL_COUNT_LCSMETADATA = "SELECT COUNT(lcsMetadata) FROM LCSMetadata lcsMetadata";
	private static final String _SQL_COUNT_LCSMETADATA_WHERE = "SELECT COUNT(lcsMetadata) FROM LCSMetadata lcsMetadata WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "lcsMetadata.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No LCSMetadata exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No LCSMetadata exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(LCSMetadataPersistenceImpl.class);
}