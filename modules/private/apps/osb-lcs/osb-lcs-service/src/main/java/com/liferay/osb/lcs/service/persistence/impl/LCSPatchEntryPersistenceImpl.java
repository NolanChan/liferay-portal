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

import com.liferay.osb.lcs.exception.NoSuchLCSPatchEntryException;
import com.liferay.osb.lcs.model.LCSPatchEntry;
import com.liferay.osb.lcs.model.impl.LCSPatchEntryImpl;
import com.liferay.osb.lcs.model.impl.LCSPatchEntryModelImpl;
import com.liferay.osb.lcs.service.persistence.LCSPatchEntryPersistence;

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
 * The persistence implementation for the l c s patch entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Igor Beslic
 * @see LCSPatchEntryPersistence
 * @see com.liferay.osb.lcs.service.persistence.LCSPatchEntryUtil
 * @generated
 */
@ProviderType
public class LCSPatchEntryPersistenceImpl extends BasePersistenceImpl<LCSPatchEntry>
	implements LCSPatchEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link LCSPatchEntryUtil} to access the l c s patch entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = LCSPatchEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(LCSPatchEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSPatchEntryModelImpl.FINDER_CACHE_ENABLED,
			LCSPatchEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(LCSPatchEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSPatchEntryModelImpl.FINDER_CACHE_ENABLED,
			LCSPatchEntryImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(LCSPatchEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSPatchEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_PATCHID = new FinderPath(LCSPatchEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSPatchEntryModelImpl.FINDER_CACHE_ENABLED,
			LCSPatchEntryImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByPatchId", new String[] { String.class.getName() },
			LCSPatchEntryModelImpl.PATCHID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PATCHID = new FinderPath(LCSPatchEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSPatchEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPatchId",
			new String[] { String.class.getName() });

	/**
	 * Returns the l c s patch entry where patchId = &#63; or throws a {@link NoSuchLCSPatchEntryException} if it could not be found.
	 *
	 * @param patchId the patch ID
	 * @return the matching l c s patch entry
	 * @throws NoSuchLCSPatchEntryException if a matching l c s patch entry could not be found
	 */
	@Override
	public LCSPatchEntry findByPatchId(String patchId)
		throws NoSuchLCSPatchEntryException {
		LCSPatchEntry lcsPatchEntry = fetchByPatchId(patchId);

		if (lcsPatchEntry == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("patchId=");
			msg.append(patchId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchLCSPatchEntryException(msg.toString());
		}

		return lcsPatchEntry;
	}

	/**
	 * Returns the l c s patch entry where patchId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param patchId the patch ID
	 * @return the matching l c s patch entry, or <code>null</code> if a matching l c s patch entry could not be found
	 */
	@Override
	public LCSPatchEntry fetchByPatchId(String patchId) {
		return fetchByPatchId(patchId, true);
	}

	/**
	 * Returns the l c s patch entry where patchId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param patchId the patch ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching l c s patch entry, or <code>null</code> if a matching l c s patch entry could not be found
	 */
	@Override
	public LCSPatchEntry fetchByPatchId(String patchId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { patchId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_PATCHID,
					finderArgs, this);
		}

		if (result instanceof LCSPatchEntry) {
			LCSPatchEntry lcsPatchEntry = (LCSPatchEntry)result;

			if (!Objects.equals(patchId, lcsPatchEntry.getPatchId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_LCSPATCHENTRY_WHERE);

			boolean bindPatchId = false;

			if (patchId == null) {
				query.append(_FINDER_COLUMN_PATCHID_PATCHID_1);
			}
			else if (patchId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PATCHID_PATCHID_3);
			}
			else {
				bindPatchId = true;

				query.append(_FINDER_COLUMN_PATCHID_PATCHID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPatchId) {
					qPos.add(patchId);
				}

				List<LCSPatchEntry> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_PATCHID,
						finderArgs, list);
				}
				else {
					LCSPatchEntry lcsPatchEntry = list.get(0);

					result = lcsPatchEntry;

					cacheResult(lcsPatchEntry);

					if ((lcsPatchEntry.getPatchId() == null) ||
							!lcsPatchEntry.getPatchId().equals(patchId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_PATCHID,
							finderArgs, lcsPatchEntry);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_PATCHID,
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
			return (LCSPatchEntry)result;
		}
	}

	/**
	 * Removes the l c s patch entry where patchId = &#63; from the database.
	 *
	 * @param patchId the patch ID
	 * @return the l c s patch entry that was removed
	 */
	@Override
	public LCSPatchEntry removeByPatchId(String patchId)
		throws NoSuchLCSPatchEntryException {
		LCSPatchEntry lcsPatchEntry = findByPatchId(patchId);

		return remove(lcsPatchEntry);
	}

	/**
	 * Returns the number of l c s patch entries where patchId = &#63;.
	 *
	 * @param patchId the patch ID
	 * @return the number of matching l c s patch entries
	 */
	@Override
	public int countByPatchId(String patchId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PATCHID;

		Object[] finderArgs = new Object[] { patchId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LCSPATCHENTRY_WHERE);

			boolean bindPatchId = false;

			if (patchId == null) {
				query.append(_FINDER_COLUMN_PATCHID_PATCHID_1);
			}
			else if (patchId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PATCHID_PATCHID_3);
			}
			else {
				bindPatchId = true;

				query.append(_FINDER_COLUMN_PATCHID_PATCHID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPatchId) {
					qPos.add(patchId);
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

	private static final String _FINDER_COLUMN_PATCHID_PATCHID_1 = "lcsPatchEntry.patchId IS NULL";
	private static final String _FINDER_COLUMN_PATCHID_PATCHID_2 = "lcsPatchEntry.patchId = ?";
	private static final String _FINDER_COLUMN_PATCHID_PATCHID_3 = "(lcsPatchEntry.patchId IS NULL OR lcsPatchEntry.patchId = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PTV_PRODUCT =
		new FinderPath(LCSPatchEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSPatchEntryModelImpl.FINDER_CACHE_ENABLED,
			LCSPatchEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByPTV_Product",
			new String[] {
				Integer.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_PTV_PRODUCT =
		new FinderPath(LCSPatchEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSPatchEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByPTV_Product",
			new String[] { Integer.class.getName(), String.class.getName() });

	/**
	 * Returns all the l c s patch entries where patchingToolVersion &le; &#63; and product = &#63;.
	 *
	 * @param patchingToolVersion the patching tool version
	 * @param product the product
	 * @return the matching l c s patch entries
	 */
	@Override
	public List<LCSPatchEntry> findByPTV_Product(int patchingToolVersion,
		String product) {
		return findByPTV_Product(patchingToolVersion, product,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the l c s patch entries where patchingToolVersion &le; &#63; and product = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSPatchEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param patchingToolVersion the patching tool version
	 * @param product the product
	 * @param start the lower bound of the range of l c s patch entries
	 * @param end the upper bound of the range of l c s patch entries (not inclusive)
	 * @return the range of matching l c s patch entries
	 */
	@Override
	public List<LCSPatchEntry> findByPTV_Product(int patchingToolVersion,
		String product, int start, int end) {
		return findByPTV_Product(patchingToolVersion, product, start, end, null);
	}

	/**
	 * Returns an ordered range of all the l c s patch entries where patchingToolVersion &le; &#63; and product = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSPatchEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param patchingToolVersion the patching tool version
	 * @param product the product
	 * @param start the lower bound of the range of l c s patch entries
	 * @param end the upper bound of the range of l c s patch entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching l c s patch entries
	 */
	@Override
	public List<LCSPatchEntry> findByPTV_Product(int patchingToolVersion,
		String product, int start, int end,
		OrderByComparator<LCSPatchEntry> orderByComparator) {
		return findByPTV_Product(patchingToolVersion, product, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the l c s patch entries where patchingToolVersion &le; &#63; and product = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSPatchEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param patchingToolVersion the patching tool version
	 * @param product the product
	 * @param start the lower bound of the range of l c s patch entries
	 * @param end the upper bound of the range of l c s patch entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching l c s patch entries
	 */
	@Override
	public List<LCSPatchEntry> findByPTV_Product(int patchingToolVersion,
		String product, int start, int end,
		OrderByComparator<LCSPatchEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PTV_PRODUCT;
		finderArgs = new Object[] {
				patchingToolVersion, product,
				
				start, end, orderByComparator
			};

		List<LCSPatchEntry> list = null;

		if (retrieveFromCache) {
			list = (List<LCSPatchEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LCSPatchEntry lcsPatchEntry : list) {
					if ((patchingToolVersion < lcsPatchEntry.getPatchingToolVersion()) ||
							!Objects.equals(product, lcsPatchEntry.getProduct())) {
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

			query.append(_SQL_SELECT_LCSPATCHENTRY_WHERE);

			query.append(_FINDER_COLUMN_PTV_PRODUCT_PATCHINGTOOLVERSION_2);

			boolean bindProduct = false;

			if (product == null) {
				query.append(_FINDER_COLUMN_PTV_PRODUCT_PRODUCT_1);
			}
			else if (product.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PTV_PRODUCT_PRODUCT_3);
			}
			else {
				bindProduct = true;

				query.append(_FINDER_COLUMN_PTV_PRODUCT_PRODUCT_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LCSPatchEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(patchingToolVersion);

				if (bindProduct) {
					qPos.add(product);
				}

				if (!pagination) {
					list = (List<LCSPatchEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSPatchEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first l c s patch entry in the ordered set where patchingToolVersion &le; &#63; and product = &#63;.
	 *
	 * @param patchingToolVersion the patching tool version
	 * @param product the product
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s patch entry
	 * @throws NoSuchLCSPatchEntryException if a matching l c s patch entry could not be found
	 */
	@Override
	public LCSPatchEntry findByPTV_Product_First(int patchingToolVersion,
		String product, OrderByComparator<LCSPatchEntry> orderByComparator)
		throws NoSuchLCSPatchEntryException {
		LCSPatchEntry lcsPatchEntry = fetchByPTV_Product_First(patchingToolVersion,
				product, orderByComparator);

		if (lcsPatchEntry != null) {
			return lcsPatchEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("patchingToolVersion=");
		msg.append(patchingToolVersion);

		msg.append(", product=");
		msg.append(product);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSPatchEntryException(msg.toString());
	}

	/**
	 * Returns the first l c s patch entry in the ordered set where patchingToolVersion &le; &#63; and product = &#63;.
	 *
	 * @param patchingToolVersion the patching tool version
	 * @param product the product
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching l c s patch entry, or <code>null</code> if a matching l c s patch entry could not be found
	 */
	@Override
	public LCSPatchEntry fetchByPTV_Product_First(int patchingToolVersion,
		String product, OrderByComparator<LCSPatchEntry> orderByComparator) {
		List<LCSPatchEntry> list = findByPTV_Product(patchingToolVersion,
				product, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last l c s patch entry in the ordered set where patchingToolVersion &le; &#63; and product = &#63;.
	 *
	 * @param patchingToolVersion the patching tool version
	 * @param product the product
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s patch entry
	 * @throws NoSuchLCSPatchEntryException if a matching l c s patch entry could not be found
	 */
	@Override
	public LCSPatchEntry findByPTV_Product_Last(int patchingToolVersion,
		String product, OrderByComparator<LCSPatchEntry> orderByComparator)
		throws NoSuchLCSPatchEntryException {
		LCSPatchEntry lcsPatchEntry = fetchByPTV_Product_Last(patchingToolVersion,
				product, orderByComparator);

		if (lcsPatchEntry != null) {
			return lcsPatchEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("patchingToolVersion=");
		msg.append(patchingToolVersion);

		msg.append(", product=");
		msg.append(product);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLCSPatchEntryException(msg.toString());
	}

	/**
	 * Returns the last l c s patch entry in the ordered set where patchingToolVersion &le; &#63; and product = &#63;.
	 *
	 * @param patchingToolVersion the patching tool version
	 * @param product the product
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching l c s patch entry, or <code>null</code> if a matching l c s patch entry could not be found
	 */
	@Override
	public LCSPatchEntry fetchByPTV_Product_Last(int patchingToolVersion,
		String product, OrderByComparator<LCSPatchEntry> orderByComparator) {
		int count = countByPTV_Product(patchingToolVersion, product);

		if (count == 0) {
			return null;
		}

		List<LCSPatchEntry> list = findByPTV_Product(patchingToolVersion,
				product, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the l c s patch entries before and after the current l c s patch entry in the ordered set where patchingToolVersion &le; &#63; and product = &#63;.
	 *
	 * @param lcsPatchEntryId the primary key of the current l c s patch entry
	 * @param patchingToolVersion the patching tool version
	 * @param product the product
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next l c s patch entry
	 * @throws NoSuchLCSPatchEntryException if a l c s patch entry with the primary key could not be found
	 */
	@Override
	public LCSPatchEntry[] findByPTV_Product_PrevAndNext(long lcsPatchEntryId,
		int patchingToolVersion, String product,
		OrderByComparator<LCSPatchEntry> orderByComparator)
		throws NoSuchLCSPatchEntryException {
		LCSPatchEntry lcsPatchEntry = findByPrimaryKey(lcsPatchEntryId);

		Session session = null;

		try {
			session = openSession();

			LCSPatchEntry[] array = new LCSPatchEntryImpl[3];

			array[0] = getByPTV_Product_PrevAndNext(session, lcsPatchEntry,
					patchingToolVersion, product, orderByComparator, true);

			array[1] = lcsPatchEntry;

			array[2] = getByPTV_Product_PrevAndNext(session, lcsPatchEntry,
					patchingToolVersion, product, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LCSPatchEntry getByPTV_Product_PrevAndNext(Session session,
		LCSPatchEntry lcsPatchEntry, int patchingToolVersion, String product,
		OrderByComparator<LCSPatchEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_LCSPATCHENTRY_WHERE);

		query.append(_FINDER_COLUMN_PTV_PRODUCT_PATCHINGTOOLVERSION_2);

		boolean bindProduct = false;

		if (product == null) {
			query.append(_FINDER_COLUMN_PTV_PRODUCT_PRODUCT_1);
		}
		else if (product.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_PTV_PRODUCT_PRODUCT_3);
		}
		else {
			bindProduct = true;

			query.append(_FINDER_COLUMN_PTV_PRODUCT_PRODUCT_2);
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
			query.append(LCSPatchEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(patchingToolVersion);

		if (bindProduct) {
			qPos.add(product);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(lcsPatchEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LCSPatchEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the l c s patch entries where patchingToolVersion &le; &#63; and product = &#63; from the database.
	 *
	 * @param patchingToolVersion the patching tool version
	 * @param product the product
	 */
	@Override
	public void removeByPTV_Product(int patchingToolVersion, String product) {
		for (LCSPatchEntry lcsPatchEntry : findByPTV_Product(
				patchingToolVersion, product, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(lcsPatchEntry);
		}
	}

	/**
	 * Returns the number of l c s patch entries where patchingToolVersion &le; &#63; and product = &#63;.
	 *
	 * @param patchingToolVersion the patching tool version
	 * @param product the product
	 * @return the number of matching l c s patch entries
	 */
	@Override
	public int countByPTV_Product(int patchingToolVersion, String product) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_PTV_PRODUCT;

		Object[] finderArgs = new Object[] { patchingToolVersion, product };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LCSPATCHENTRY_WHERE);

			query.append(_FINDER_COLUMN_PTV_PRODUCT_PATCHINGTOOLVERSION_2);

			boolean bindProduct = false;

			if (product == null) {
				query.append(_FINDER_COLUMN_PTV_PRODUCT_PRODUCT_1);
			}
			else if (product.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PTV_PRODUCT_PRODUCT_3);
			}
			else {
				bindProduct = true;

				query.append(_FINDER_COLUMN_PTV_PRODUCT_PRODUCT_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(patchingToolVersion);

				if (bindProduct) {
					qPos.add(product);
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

	private static final String _FINDER_COLUMN_PTV_PRODUCT_PATCHINGTOOLVERSION_2 =
		"lcsPatchEntry.patchingToolVersion <= ? AND ";
	private static final String _FINDER_COLUMN_PTV_PRODUCT_PRODUCT_1 = "lcsPatchEntry.product IS NULL";
	private static final String _FINDER_COLUMN_PTV_PRODUCT_PRODUCT_2 = "lcsPatchEntry.product = ?";
	private static final String _FINDER_COLUMN_PTV_PRODUCT_PRODUCT_3 = "(lcsPatchEntry.product IS NULL OR lcsPatchEntry.product = '')";

	public LCSPatchEntryPersistenceImpl() {
		setModelClass(LCSPatchEntry.class);
	}

	/**
	 * Caches the l c s patch entry in the entity cache if it is enabled.
	 *
	 * @param lcsPatchEntry the l c s patch entry
	 */
	@Override
	public void cacheResult(LCSPatchEntry lcsPatchEntry) {
		entityCache.putResult(LCSPatchEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSPatchEntryImpl.class, lcsPatchEntry.getPrimaryKey(),
			lcsPatchEntry);

		finderCache.putResult(FINDER_PATH_FETCH_BY_PATCHID,
			new Object[] { lcsPatchEntry.getPatchId() }, lcsPatchEntry);

		lcsPatchEntry.resetOriginalValues();
	}

	/**
	 * Caches the l c s patch entries in the entity cache if it is enabled.
	 *
	 * @param lcsPatchEntries the l c s patch entries
	 */
	@Override
	public void cacheResult(List<LCSPatchEntry> lcsPatchEntries) {
		for (LCSPatchEntry lcsPatchEntry : lcsPatchEntries) {
			if (entityCache.getResult(
						LCSPatchEntryModelImpl.ENTITY_CACHE_ENABLED,
						LCSPatchEntryImpl.class, lcsPatchEntry.getPrimaryKey()) == null) {
				cacheResult(lcsPatchEntry);
			}
			else {
				lcsPatchEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all l c s patch entries.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(LCSPatchEntryImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the l c s patch entry.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LCSPatchEntry lcsPatchEntry) {
		entityCache.removeResult(LCSPatchEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSPatchEntryImpl.class, lcsPatchEntry.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((LCSPatchEntryModelImpl)lcsPatchEntry, true);
	}

	@Override
	public void clearCache(List<LCSPatchEntry> lcsPatchEntries) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (LCSPatchEntry lcsPatchEntry : lcsPatchEntries) {
			entityCache.removeResult(LCSPatchEntryModelImpl.ENTITY_CACHE_ENABLED,
				LCSPatchEntryImpl.class, lcsPatchEntry.getPrimaryKey());

			clearUniqueFindersCache((LCSPatchEntryModelImpl)lcsPatchEntry, true);
		}
	}

	protected void cacheUniqueFindersCache(
		LCSPatchEntryModelImpl lcsPatchEntryModelImpl) {
		Object[] args = new Object[] { lcsPatchEntryModelImpl.getPatchId() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_PATCHID, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_PATCHID, args,
			lcsPatchEntryModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		LCSPatchEntryModelImpl lcsPatchEntryModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] { lcsPatchEntryModelImpl.getPatchId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PATCHID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_PATCHID, args);
		}

		if ((lcsPatchEntryModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_PATCHID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					lcsPatchEntryModelImpl.getOriginalPatchId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PATCHID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_PATCHID, args);
		}
	}

	/**
	 * Creates a new l c s patch entry with the primary key. Does not add the l c s patch entry to the database.
	 *
	 * @param lcsPatchEntryId the primary key for the new l c s patch entry
	 * @return the new l c s patch entry
	 */
	@Override
	public LCSPatchEntry create(long lcsPatchEntryId) {
		LCSPatchEntry lcsPatchEntry = new LCSPatchEntryImpl();

		lcsPatchEntry.setNew(true);
		lcsPatchEntry.setPrimaryKey(lcsPatchEntryId);

		return lcsPatchEntry;
	}

	/**
	 * Removes the l c s patch entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param lcsPatchEntryId the primary key of the l c s patch entry
	 * @return the l c s patch entry that was removed
	 * @throws NoSuchLCSPatchEntryException if a l c s patch entry with the primary key could not be found
	 */
	@Override
	public LCSPatchEntry remove(long lcsPatchEntryId)
		throws NoSuchLCSPatchEntryException {
		return remove((Serializable)lcsPatchEntryId);
	}

	/**
	 * Removes the l c s patch entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the l c s patch entry
	 * @return the l c s patch entry that was removed
	 * @throws NoSuchLCSPatchEntryException if a l c s patch entry with the primary key could not be found
	 */
	@Override
	public LCSPatchEntry remove(Serializable primaryKey)
		throws NoSuchLCSPatchEntryException {
		Session session = null;

		try {
			session = openSession();

			LCSPatchEntry lcsPatchEntry = (LCSPatchEntry)session.get(LCSPatchEntryImpl.class,
					primaryKey);

			if (lcsPatchEntry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLCSPatchEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(lcsPatchEntry);
		}
		catch (NoSuchLCSPatchEntryException nsee) {
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
	protected LCSPatchEntry removeImpl(LCSPatchEntry lcsPatchEntry) {
		lcsPatchEntry = toUnwrappedModel(lcsPatchEntry);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(lcsPatchEntry)) {
				lcsPatchEntry = (LCSPatchEntry)session.get(LCSPatchEntryImpl.class,
						lcsPatchEntry.getPrimaryKeyObj());
			}

			if (lcsPatchEntry != null) {
				session.delete(lcsPatchEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (lcsPatchEntry != null) {
			clearCache(lcsPatchEntry);
		}

		return lcsPatchEntry;
	}

	@Override
	public LCSPatchEntry updateImpl(LCSPatchEntry lcsPatchEntry) {
		lcsPatchEntry = toUnwrappedModel(lcsPatchEntry);

		boolean isNew = lcsPatchEntry.isNew();

		LCSPatchEntryModelImpl lcsPatchEntryModelImpl = (LCSPatchEntryModelImpl)lcsPatchEntry;

		Session session = null;

		try {
			session = openSession();

			if (lcsPatchEntry.isNew()) {
				session.save(lcsPatchEntry);

				lcsPatchEntry.setNew(false);
			}
			else {
				lcsPatchEntry = (LCSPatchEntry)session.merge(lcsPatchEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !LCSPatchEntryModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		entityCache.putResult(LCSPatchEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSPatchEntryImpl.class, lcsPatchEntry.getPrimaryKey(),
			lcsPatchEntry, false);

		clearUniqueFindersCache(lcsPatchEntryModelImpl, false);
		cacheUniqueFindersCache(lcsPatchEntryModelImpl);

		lcsPatchEntry.resetOriginalValues();

		return lcsPatchEntry;
	}

	protected LCSPatchEntry toUnwrappedModel(LCSPatchEntry lcsPatchEntry) {
		if (lcsPatchEntry instanceof LCSPatchEntryImpl) {
			return lcsPatchEntry;
		}

		LCSPatchEntryImpl lcsPatchEntryImpl = new LCSPatchEntryImpl();

		lcsPatchEntryImpl.setNew(lcsPatchEntry.isNew());
		lcsPatchEntryImpl.setPrimaryKey(lcsPatchEntry.getPrimaryKey());

		lcsPatchEntryImpl.setLcsPatchEntryId(lcsPatchEntry.getLcsPatchEntryId());
		lcsPatchEntryImpl.setPatchId(lcsPatchEntry.getPatchId());
		lcsPatchEntryImpl.setName(lcsPatchEntry.getName());
		lcsPatchEntryImpl.setDescription(lcsPatchEntry.getDescription());
		lcsPatchEntryImpl.setPatchingToolVersion(lcsPatchEntry.getPatchingToolVersion());
		lcsPatchEntryImpl.setIncremental(lcsPatchEntry.isIncremental());
		lcsPatchEntryImpl.setSingular(lcsPatchEntry.isSingular());
		lcsPatchEntryImpl.setVersion(lcsPatchEntry.getVersion());
		lcsPatchEntryImpl.setSize(lcsPatchEntry.getSize());
		lcsPatchEntryImpl.setRank(lcsPatchEntry.getRank());
		lcsPatchEntryImpl.setRequirements(lcsPatchEntry.getRequirements());
		lcsPatchEntryImpl.setComponent(lcsPatchEntry.getComponent());
		lcsPatchEntryImpl.setCompatibleBuild(lcsPatchEntry.getCompatibleBuild());
		lcsPatchEntryImpl.setProduct(lcsPatchEntry.getProduct());
		lcsPatchEntryImpl.setFixedIssues(lcsPatchEntry.getFixedIssues());
		lcsPatchEntryImpl.setModuleName(lcsPatchEntry.getModuleName());
		lcsPatchEntryImpl.setModuleId(lcsPatchEntry.getModuleId());
		lcsPatchEntryImpl.setTunnelWeb(lcsPatchEntry.isTunnelWeb());
		lcsPatchEntryImpl.setBuildDate(lcsPatchEntry.getBuildDate());
		lcsPatchEntryImpl.setBuiltFor(lcsPatchEntry.getBuiltFor());

		return lcsPatchEntryImpl;
	}

	/**
	 * Returns the l c s patch entry with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the l c s patch entry
	 * @return the l c s patch entry
	 * @throws NoSuchLCSPatchEntryException if a l c s patch entry with the primary key could not be found
	 */
	@Override
	public LCSPatchEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLCSPatchEntryException {
		LCSPatchEntry lcsPatchEntry = fetchByPrimaryKey(primaryKey);

		if (lcsPatchEntry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLCSPatchEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return lcsPatchEntry;
	}

	/**
	 * Returns the l c s patch entry with the primary key or throws a {@link NoSuchLCSPatchEntryException} if it could not be found.
	 *
	 * @param lcsPatchEntryId the primary key of the l c s patch entry
	 * @return the l c s patch entry
	 * @throws NoSuchLCSPatchEntryException if a l c s patch entry with the primary key could not be found
	 */
	@Override
	public LCSPatchEntry findByPrimaryKey(long lcsPatchEntryId)
		throws NoSuchLCSPatchEntryException {
		return findByPrimaryKey((Serializable)lcsPatchEntryId);
	}

	/**
	 * Returns the l c s patch entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the l c s patch entry
	 * @return the l c s patch entry, or <code>null</code> if a l c s patch entry with the primary key could not be found
	 */
	@Override
	public LCSPatchEntry fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(LCSPatchEntryModelImpl.ENTITY_CACHE_ENABLED,
				LCSPatchEntryImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		LCSPatchEntry lcsPatchEntry = (LCSPatchEntry)serializable;

		if (lcsPatchEntry == null) {
			Session session = null;

			try {
				session = openSession();

				lcsPatchEntry = (LCSPatchEntry)session.get(LCSPatchEntryImpl.class,
						primaryKey);

				if (lcsPatchEntry != null) {
					cacheResult(lcsPatchEntry);
				}
				else {
					entityCache.putResult(LCSPatchEntryModelImpl.ENTITY_CACHE_ENABLED,
						LCSPatchEntryImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(LCSPatchEntryModelImpl.ENTITY_CACHE_ENABLED,
					LCSPatchEntryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return lcsPatchEntry;
	}

	/**
	 * Returns the l c s patch entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param lcsPatchEntryId the primary key of the l c s patch entry
	 * @return the l c s patch entry, or <code>null</code> if a l c s patch entry with the primary key could not be found
	 */
	@Override
	public LCSPatchEntry fetchByPrimaryKey(long lcsPatchEntryId) {
		return fetchByPrimaryKey((Serializable)lcsPatchEntryId);
	}

	@Override
	public Map<Serializable, LCSPatchEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, LCSPatchEntry> map = new HashMap<Serializable, LCSPatchEntry>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			LCSPatchEntry lcsPatchEntry = fetchByPrimaryKey(primaryKey);

			if (lcsPatchEntry != null) {
				map.put(primaryKey, lcsPatchEntry);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(LCSPatchEntryModelImpl.ENTITY_CACHE_ENABLED,
					LCSPatchEntryImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (LCSPatchEntry)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_LCSPATCHENTRY_WHERE_PKS_IN);

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

			for (LCSPatchEntry lcsPatchEntry : (List<LCSPatchEntry>)q.list()) {
				map.put(lcsPatchEntry.getPrimaryKeyObj(), lcsPatchEntry);

				cacheResult(lcsPatchEntry);

				uncachedPrimaryKeys.remove(lcsPatchEntry.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(LCSPatchEntryModelImpl.ENTITY_CACHE_ENABLED,
					LCSPatchEntryImpl.class, primaryKey, nullModel);
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
	 * Returns all the l c s patch entries.
	 *
	 * @return the l c s patch entries
	 */
	@Override
	public List<LCSPatchEntry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the l c s patch entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSPatchEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of l c s patch entries
	 * @param end the upper bound of the range of l c s patch entries (not inclusive)
	 * @return the range of l c s patch entries
	 */
	@Override
	public List<LCSPatchEntry> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the l c s patch entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSPatchEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of l c s patch entries
	 * @param end the upper bound of the range of l c s patch entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of l c s patch entries
	 */
	@Override
	public List<LCSPatchEntry> findAll(int start, int end,
		OrderByComparator<LCSPatchEntry> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the l c s patch entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSPatchEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of l c s patch entries
	 * @param end the upper bound of the range of l c s patch entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of l c s patch entries
	 */
	@Override
	public List<LCSPatchEntry> findAll(int start, int end,
		OrderByComparator<LCSPatchEntry> orderByComparator,
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

		List<LCSPatchEntry> list = null;

		if (retrieveFromCache) {
			list = (List<LCSPatchEntry>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_LCSPATCHENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_LCSPATCHENTRY;

				if (pagination) {
					sql = sql.concat(LCSPatchEntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<LCSPatchEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSPatchEntry>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the l c s patch entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (LCSPatchEntry lcsPatchEntry : findAll()) {
			remove(lcsPatchEntry);
		}
	}

	/**
	 * Returns the number of l c s patch entries.
	 *
	 * @return the number of l c s patch entries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_LCSPATCHENTRY);

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
		return LCSPatchEntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the l c s patch entry persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(LCSPatchEntryImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_LCSPATCHENTRY = "SELECT lcsPatchEntry FROM LCSPatchEntry lcsPatchEntry";
	private static final String _SQL_SELECT_LCSPATCHENTRY_WHERE_PKS_IN = "SELECT lcsPatchEntry FROM LCSPatchEntry lcsPatchEntry WHERE lcsPatchEntryId IN (";
	private static final String _SQL_SELECT_LCSPATCHENTRY_WHERE = "SELECT lcsPatchEntry FROM LCSPatchEntry lcsPatchEntry WHERE ";
	private static final String _SQL_COUNT_LCSPATCHENTRY = "SELECT COUNT(lcsPatchEntry) FROM LCSPatchEntry lcsPatchEntry";
	private static final String _SQL_COUNT_LCSPATCHENTRY_WHERE = "SELECT COUNT(lcsPatchEntry) FROM LCSPatchEntry lcsPatchEntry WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "lcsPatchEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No LCSPatchEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No LCSPatchEntry exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(LCSPatchEntryPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"size"
			});
}