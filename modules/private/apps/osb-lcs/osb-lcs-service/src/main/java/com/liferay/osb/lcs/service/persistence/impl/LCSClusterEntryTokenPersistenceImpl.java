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

import com.liferay.osb.lcs.exception.NoSuchLCSClusterEntryTokenException;
import com.liferay.osb.lcs.model.LCSClusterEntryToken;
import com.liferay.osb.lcs.model.impl.LCSClusterEntryTokenImpl;
import com.liferay.osb.lcs.model.impl.LCSClusterEntryTokenModelImpl;
import com.liferay.osb.lcs.service.persistence.LCSClusterEntryTokenPersistence;

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
 * The persistence implementation for the l c s cluster entry token service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Igor Beslic
 * @see LCSClusterEntryTokenPersistence
 * @see com.liferay.osb.lcs.service.persistence.LCSClusterEntryTokenUtil
 * @generated
 */
@ProviderType
public class LCSClusterEntryTokenPersistenceImpl extends BasePersistenceImpl<LCSClusterEntryToken>
	implements LCSClusterEntryTokenPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link LCSClusterEntryTokenUtil} to access the l c s cluster entry token persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = LCSClusterEntryTokenImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(LCSClusterEntryTokenModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterEntryTokenModelImpl.FINDER_CACHE_ENABLED,
			LCSClusterEntryTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(LCSClusterEntryTokenModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterEntryTokenModelImpl.FINDER_CACHE_ENABLED,
			LCSClusterEntryTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(LCSClusterEntryTokenModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterEntryTokenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_LCSCLUSTERENTRYID = new FinderPath(LCSClusterEntryTokenModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterEntryTokenModelImpl.FINDER_CACHE_ENABLED,
			LCSClusterEntryTokenImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByLCSClusterEntryId", new String[] { Long.class.getName() },
			LCSClusterEntryTokenModelImpl.LCSCLUSTERENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_LCSCLUSTERENTRYID = new FinderPath(LCSClusterEntryTokenModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterEntryTokenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByLCSClusterEntryId", new String[] { Long.class.getName() });

	/**
	 * Returns the l c s cluster entry token where lcsClusterEntryId = &#63; or throws a {@link NoSuchLCSClusterEntryTokenException} if it could not be found.
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @return the matching l c s cluster entry token
	 * @throws NoSuchLCSClusterEntryTokenException if a matching l c s cluster entry token could not be found
	 */
	@Override
	public LCSClusterEntryToken findByLCSClusterEntryId(long lcsClusterEntryId)
		throws NoSuchLCSClusterEntryTokenException {
		LCSClusterEntryToken lcsClusterEntryToken = fetchByLCSClusterEntryId(lcsClusterEntryId);

		if (lcsClusterEntryToken == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("lcsClusterEntryId=");
			msg.append(lcsClusterEntryId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchLCSClusterEntryTokenException(msg.toString());
		}

		return lcsClusterEntryToken;
	}

	/**
	 * Returns the l c s cluster entry token where lcsClusterEntryId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @return the matching l c s cluster entry token, or <code>null</code> if a matching l c s cluster entry token could not be found
	 */
	@Override
	public LCSClusterEntryToken fetchByLCSClusterEntryId(long lcsClusterEntryId) {
		return fetchByLCSClusterEntryId(lcsClusterEntryId, true);
	}

	/**
	 * Returns the l c s cluster entry token where lcsClusterEntryId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching l c s cluster entry token, or <code>null</code> if a matching l c s cluster entry token could not be found
	 */
	@Override
	public LCSClusterEntryToken fetchByLCSClusterEntryId(
		long lcsClusterEntryId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { lcsClusterEntryId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_LCSCLUSTERENTRYID,
					finderArgs, this);
		}

		if (result instanceof LCSClusterEntryToken) {
			LCSClusterEntryToken lcsClusterEntryToken = (LCSClusterEntryToken)result;

			if ((lcsClusterEntryId != lcsClusterEntryToken.getLcsClusterEntryId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_LCSCLUSTERENTRYTOKEN_WHERE);

			query.append(_FINDER_COLUMN_LCSCLUSTERENTRYID_LCSCLUSTERENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(lcsClusterEntryId);

				List<LCSClusterEntryToken> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_LCSCLUSTERENTRYID,
						finderArgs, list);
				}
				else {
					LCSClusterEntryToken lcsClusterEntryToken = list.get(0);

					result = lcsClusterEntryToken;

					cacheResult(lcsClusterEntryToken);

					if ((lcsClusterEntryToken.getLcsClusterEntryId() != lcsClusterEntryId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_LCSCLUSTERENTRYID,
							finderArgs, lcsClusterEntryToken);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_LCSCLUSTERENTRYID,
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
			return (LCSClusterEntryToken)result;
		}
	}

	/**
	 * Removes the l c s cluster entry token where lcsClusterEntryId = &#63; from the database.
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @return the l c s cluster entry token that was removed
	 */
	@Override
	public LCSClusterEntryToken removeByLCSClusterEntryId(
		long lcsClusterEntryId) throws NoSuchLCSClusterEntryTokenException {
		LCSClusterEntryToken lcsClusterEntryToken = findByLCSClusterEntryId(lcsClusterEntryId);

		return remove(lcsClusterEntryToken);
	}

	/**
	 * Returns the number of l c s cluster entry tokens where lcsClusterEntryId = &#63;.
	 *
	 * @param lcsClusterEntryId the lcs cluster entry ID
	 * @return the number of matching l c s cluster entry tokens
	 */
	@Override
	public int countByLCSClusterEntryId(long lcsClusterEntryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_LCSCLUSTERENTRYID;

		Object[] finderArgs = new Object[] { lcsClusterEntryId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LCSCLUSTERENTRYTOKEN_WHERE);

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
		"lcsClusterEntryToken.lcsClusterEntryId = ?";

	public LCSClusterEntryTokenPersistenceImpl() {
		setModelClass(LCSClusterEntryToken.class);
	}

	/**
	 * Caches the l c s cluster entry token in the entity cache if it is enabled.
	 *
	 * @param lcsClusterEntryToken the l c s cluster entry token
	 */
	@Override
	public void cacheResult(LCSClusterEntryToken lcsClusterEntryToken) {
		entityCache.putResult(LCSClusterEntryTokenModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterEntryTokenImpl.class,
			lcsClusterEntryToken.getPrimaryKey(), lcsClusterEntryToken);

		finderCache.putResult(FINDER_PATH_FETCH_BY_LCSCLUSTERENTRYID,
			new Object[] { lcsClusterEntryToken.getLcsClusterEntryId() },
			lcsClusterEntryToken);

		lcsClusterEntryToken.resetOriginalValues();
	}

	/**
	 * Caches the l c s cluster entry tokens in the entity cache if it is enabled.
	 *
	 * @param lcsClusterEntryTokens the l c s cluster entry tokens
	 */
	@Override
	public void cacheResult(List<LCSClusterEntryToken> lcsClusterEntryTokens) {
		for (LCSClusterEntryToken lcsClusterEntryToken : lcsClusterEntryTokens) {
			if (entityCache.getResult(
						LCSClusterEntryTokenModelImpl.ENTITY_CACHE_ENABLED,
						LCSClusterEntryTokenImpl.class,
						lcsClusterEntryToken.getPrimaryKey()) == null) {
				cacheResult(lcsClusterEntryToken);
			}
			else {
				lcsClusterEntryToken.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all l c s cluster entry tokens.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(LCSClusterEntryTokenImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the l c s cluster entry token.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LCSClusterEntryToken lcsClusterEntryToken) {
		entityCache.removeResult(LCSClusterEntryTokenModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterEntryTokenImpl.class, lcsClusterEntryToken.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((LCSClusterEntryTokenModelImpl)lcsClusterEntryToken,
			true);
	}

	@Override
	public void clearCache(List<LCSClusterEntryToken> lcsClusterEntryTokens) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (LCSClusterEntryToken lcsClusterEntryToken : lcsClusterEntryTokens) {
			entityCache.removeResult(LCSClusterEntryTokenModelImpl.ENTITY_CACHE_ENABLED,
				LCSClusterEntryTokenImpl.class,
				lcsClusterEntryToken.getPrimaryKey());

			clearUniqueFindersCache((LCSClusterEntryTokenModelImpl)lcsClusterEntryToken,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		LCSClusterEntryTokenModelImpl lcsClusterEntryTokenModelImpl) {
		Object[] args = new Object[] {
				lcsClusterEntryTokenModelImpl.getLcsClusterEntryId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_LCSCLUSTERENTRYID, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_LCSCLUSTERENTRYID, args,
			lcsClusterEntryTokenModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		LCSClusterEntryTokenModelImpl lcsClusterEntryTokenModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					lcsClusterEntryTokenModelImpl.getLcsClusterEntryId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_LCSCLUSTERENTRYID,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_LCSCLUSTERENTRYID,
				args);
		}

		if ((lcsClusterEntryTokenModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_LCSCLUSTERENTRYID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					lcsClusterEntryTokenModelImpl.getOriginalLcsClusterEntryId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_LCSCLUSTERENTRYID,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_LCSCLUSTERENTRYID,
				args);
		}
	}

	/**
	 * Creates a new l c s cluster entry token with the primary key. Does not add the l c s cluster entry token to the database.
	 *
	 * @param lcsClusterEntryTokenId the primary key for the new l c s cluster entry token
	 * @return the new l c s cluster entry token
	 */
	@Override
	public LCSClusterEntryToken create(long lcsClusterEntryTokenId) {
		LCSClusterEntryToken lcsClusterEntryToken = new LCSClusterEntryTokenImpl();

		lcsClusterEntryToken.setNew(true);
		lcsClusterEntryToken.setPrimaryKey(lcsClusterEntryTokenId);

		return lcsClusterEntryToken;
	}

	/**
	 * Removes the l c s cluster entry token with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param lcsClusterEntryTokenId the primary key of the l c s cluster entry token
	 * @return the l c s cluster entry token that was removed
	 * @throws NoSuchLCSClusterEntryTokenException if a l c s cluster entry token with the primary key could not be found
	 */
	@Override
	public LCSClusterEntryToken remove(long lcsClusterEntryTokenId)
		throws NoSuchLCSClusterEntryTokenException {
		return remove((Serializable)lcsClusterEntryTokenId);
	}

	/**
	 * Removes the l c s cluster entry token with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the l c s cluster entry token
	 * @return the l c s cluster entry token that was removed
	 * @throws NoSuchLCSClusterEntryTokenException if a l c s cluster entry token with the primary key could not be found
	 */
	@Override
	public LCSClusterEntryToken remove(Serializable primaryKey)
		throws NoSuchLCSClusterEntryTokenException {
		Session session = null;

		try {
			session = openSession();

			LCSClusterEntryToken lcsClusterEntryToken = (LCSClusterEntryToken)session.get(LCSClusterEntryTokenImpl.class,
					primaryKey);

			if (lcsClusterEntryToken == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLCSClusterEntryTokenException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(lcsClusterEntryToken);
		}
		catch (NoSuchLCSClusterEntryTokenException nsee) {
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
	protected LCSClusterEntryToken removeImpl(
		LCSClusterEntryToken lcsClusterEntryToken) {
		lcsClusterEntryToken = toUnwrappedModel(lcsClusterEntryToken);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(lcsClusterEntryToken)) {
				lcsClusterEntryToken = (LCSClusterEntryToken)session.get(LCSClusterEntryTokenImpl.class,
						lcsClusterEntryToken.getPrimaryKeyObj());
			}

			if (lcsClusterEntryToken != null) {
				session.delete(lcsClusterEntryToken);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (lcsClusterEntryToken != null) {
			clearCache(lcsClusterEntryToken);
		}

		return lcsClusterEntryToken;
	}

	@Override
	public LCSClusterEntryToken updateImpl(
		LCSClusterEntryToken lcsClusterEntryToken) {
		lcsClusterEntryToken = toUnwrappedModel(lcsClusterEntryToken);

		boolean isNew = lcsClusterEntryToken.isNew();

		LCSClusterEntryTokenModelImpl lcsClusterEntryTokenModelImpl = (LCSClusterEntryTokenModelImpl)lcsClusterEntryToken;

		Session session = null;

		try {
			session = openSession();

			if (lcsClusterEntryToken.isNew()) {
				session.save(lcsClusterEntryToken);

				lcsClusterEntryToken.setNew(false);
			}
			else {
				lcsClusterEntryToken = (LCSClusterEntryToken)session.merge(lcsClusterEntryToken);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !LCSClusterEntryTokenModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		entityCache.putResult(LCSClusterEntryTokenModelImpl.ENTITY_CACHE_ENABLED,
			LCSClusterEntryTokenImpl.class,
			lcsClusterEntryToken.getPrimaryKey(), lcsClusterEntryToken, false);

		clearUniqueFindersCache(lcsClusterEntryTokenModelImpl, false);
		cacheUniqueFindersCache(lcsClusterEntryTokenModelImpl);

		lcsClusterEntryToken.resetOriginalValues();

		return lcsClusterEntryToken;
	}

	protected LCSClusterEntryToken toUnwrappedModel(
		LCSClusterEntryToken lcsClusterEntryToken) {
		if (lcsClusterEntryToken instanceof LCSClusterEntryTokenImpl) {
			return lcsClusterEntryToken;
		}

		LCSClusterEntryTokenImpl lcsClusterEntryTokenImpl = new LCSClusterEntryTokenImpl();

		lcsClusterEntryTokenImpl.setNew(lcsClusterEntryToken.isNew());
		lcsClusterEntryTokenImpl.setPrimaryKey(lcsClusterEntryToken.getPrimaryKey());

		lcsClusterEntryTokenImpl.setLcsClusterEntryTokenId(lcsClusterEntryToken.getLcsClusterEntryTokenId());
		lcsClusterEntryTokenImpl.setUserId(lcsClusterEntryToken.getUserId());
		lcsClusterEntryTokenImpl.setCreateDate(lcsClusterEntryToken.getCreateDate());
		lcsClusterEntryTokenImpl.setLcsClusterEntryId(lcsClusterEntryToken.getLcsClusterEntryId());
		lcsClusterEntryTokenImpl.setContent(lcsClusterEntryToken.getContent());

		return lcsClusterEntryTokenImpl;
	}

	/**
	 * Returns the l c s cluster entry token with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the l c s cluster entry token
	 * @return the l c s cluster entry token
	 * @throws NoSuchLCSClusterEntryTokenException if a l c s cluster entry token with the primary key could not be found
	 */
	@Override
	public LCSClusterEntryToken findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLCSClusterEntryTokenException {
		LCSClusterEntryToken lcsClusterEntryToken = fetchByPrimaryKey(primaryKey);

		if (lcsClusterEntryToken == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLCSClusterEntryTokenException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return lcsClusterEntryToken;
	}

	/**
	 * Returns the l c s cluster entry token with the primary key or throws a {@link NoSuchLCSClusterEntryTokenException} if it could not be found.
	 *
	 * @param lcsClusterEntryTokenId the primary key of the l c s cluster entry token
	 * @return the l c s cluster entry token
	 * @throws NoSuchLCSClusterEntryTokenException if a l c s cluster entry token with the primary key could not be found
	 */
	@Override
	public LCSClusterEntryToken findByPrimaryKey(long lcsClusterEntryTokenId)
		throws NoSuchLCSClusterEntryTokenException {
		return findByPrimaryKey((Serializable)lcsClusterEntryTokenId);
	}

	/**
	 * Returns the l c s cluster entry token with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the l c s cluster entry token
	 * @return the l c s cluster entry token, or <code>null</code> if a l c s cluster entry token with the primary key could not be found
	 */
	@Override
	public LCSClusterEntryToken fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(LCSClusterEntryTokenModelImpl.ENTITY_CACHE_ENABLED,
				LCSClusterEntryTokenImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		LCSClusterEntryToken lcsClusterEntryToken = (LCSClusterEntryToken)serializable;

		if (lcsClusterEntryToken == null) {
			Session session = null;

			try {
				session = openSession();

				lcsClusterEntryToken = (LCSClusterEntryToken)session.get(LCSClusterEntryTokenImpl.class,
						primaryKey);

				if (lcsClusterEntryToken != null) {
					cacheResult(lcsClusterEntryToken);
				}
				else {
					entityCache.putResult(LCSClusterEntryTokenModelImpl.ENTITY_CACHE_ENABLED,
						LCSClusterEntryTokenImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(LCSClusterEntryTokenModelImpl.ENTITY_CACHE_ENABLED,
					LCSClusterEntryTokenImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return lcsClusterEntryToken;
	}

	/**
	 * Returns the l c s cluster entry token with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param lcsClusterEntryTokenId the primary key of the l c s cluster entry token
	 * @return the l c s cluster entry token, or <code>null</code> if a l c s cluster entry token with the primary key could not be found
	 */
	@Override
	public LCSClusterEntryToken fetchByPrimaryKey(long lcsClusterEntryTokenId) {
		return fetchByPrimaryKey((Serializable)lcsClusterEntryTokenId);
	}

	@Override
	public Map<Serializable, LCSClusterEntryToken> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, LCSClusterEntryToken> map = new HashMap<Serializable, LCSClusterEntryToken>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			LCSClusterEntryToken lcsClusterEntryToken = fetchByPrimaryKey(primaryKey);

			if (lcsClusterEntryToken != null) {
				map.put(primaryKey, lcsClusterEntryToken);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(LCSClusterEntryTokenModelImpl.ENTITY_CACHE_ENABLED,
					LCSClusterEntryTokenImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (LCSClusterEntryToken)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_LCSCLUSTERENTRYTOKEN_WHERE_PKS_IN);

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

			for (LCSClusterEntryToken lcsClusterEntryToken : (List<LCSClusterEntryToken>)q.list()) {
				map.put(lcsClusterEntryToken.getPrimaryKeyObj(),
					lcsClusterEntryToken);

				cacheResult(lcsClusterEntryToken);

				uncachedPrimaryKeys.remove(lcsClusterEntryToken.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(LCSClusterEntryTokenModelImpl.ENTITY_CACHE_ENABLED,
					LCSClusterEntryTokenImpl.class, primaryKey, nullModel);
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
	 * Returns all the l c s cluster entry tokens.
	 *
	 * @return the l c s cluster entry tokens
	 */
	@Override
	public List<LCSClusterEntryToken> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the l c s cluster entry tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterEntryTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of l c s cluster entry tokens
	 * @param end the upper bound of the range of l c s cluster entry tokens (not inclusive)
	 * @return the range of l c s cluster entry tokens
	 */
	@Override
	public List<LCSClusterEntryToken> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the l c s cluster entry tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterEntryTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of l c s cluster entry tokens
	 * @param end the upper bound of the range of l c s cluster entry tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of l c s cluster entry tokens
	 */
	@Override
	public List<LCSClusterEntryToken> findAll(int start, int end,
		OrderByComparator<LCSClusterEntryToken> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the l c s cluster entry tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterEntryTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of l c s cluster entry tokens
	 * @param end the upper bound of the range of l c s cluster entry tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of l c s cluster entry tokens
	 */
	@Override
	public List<LCSClusterEntryToken> findAll(int start, int end,
		OrderByComparator<LCSClusterEntryToken> orderByComparator,
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

		List<LCSClusterEntryToken> list = null;

		if (retrieveFromCache) {
			list = (List<LCSClusterEntryToken>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_LCSCLUSTERENTRYTOKEN);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_LCSCLUSTERENTRYTOKEN;

				if (pagination) {
					sql = sql.concat(LCSClusterEntryTokenModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<LCSClusterEntryToken>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSClusterEntryToken>)QueryUtil.list(q,
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
	 * Removes all the l c s cluster entry tokens from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (LCSClusterEntryToken lcsClusterEntryToken : findAll()) {
			remove(lcsClusterEntryToken);
		}
	}

	/**
	 * Returns the number of l c s cluster entry tokens.
	 *
	 * @return the number of l c s cluster entry tokens
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_LCSCLUSTERENTRYTOKEN);

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
		return LCSClusterEntryTokenModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the l c s cluster entry token persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(LCSClusterEntryTokenImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_LCSCLUSTERENTRYTOKEN = "SELECT lcsClusterEntryToken FROM LCSClusterEntryToken lcsClusterEntryToken";
	private static final String _SQL_SELECT_LCSCLUSTERENTRYTOKEN_WHERE_PKS_IN = "SELECT lcsClusterEntryToken FROM LCSClusterEntryToken lcsClusterEntryToken WHERE lcsClusterEntryTokenId IN (";
	private static final String _SQL_SELECT_LCSCLUSTERENTRYTOKEN_WHERE = "SELECT lcsClusterEntryToken FROM LCSClusterEntryToken lcsClusterEntryToken WHERE ";
	private static final String _SQL_COUNT_LCSCLUSTERENTRYTOKEN = "SELECT COUNT(lcsClusterEntryToken) FROM LCSClusterEntryToken lcsClusterEntryToken";
	private static final String _SQL_COUNT_LCSCLUSTERENTRYTOKEN_WHERE = "SELECT COUNT(lcsClusterEntryToken) FROM LCSClusterEntryToken lcsClusterEntryToken WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "lcsClusterEntryToken.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No LCSClusterEntryToken exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No LCSClusterEntryToken exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(LCSClusterEntryTokenPersistenceImpl.class);
}