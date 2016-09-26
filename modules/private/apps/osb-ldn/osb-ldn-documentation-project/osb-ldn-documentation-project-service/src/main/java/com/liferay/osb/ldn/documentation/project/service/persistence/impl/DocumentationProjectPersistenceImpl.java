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

package com.liferay.osb.ldn.documentation.project.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.ldn.documentation.project.exception.NoSuchDocumentationProjectException;
import com.liferay.osb.ldn.documentation.project.model.DocumentationProject;
import com.liferay.osb.ldn.documentation.project.model.impl.DocumentationProjectImpl;
import com.liferay.osb.ldn.documentation.project.model.impl.DocumentationProjectModelImpl;
import com.liferay.osb.ldn.documentation.project.service.persistence.DocumentationProjectPersistence;

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
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

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
 * The persistence implementation for the documentation project service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ryan Park
 * @see DocumentationProjectPersistence
 * @see com.liferay.osb.ldn.documentation.project.service.persistence.DocumentationProjectUtil
 * @generated
 */
@ProviderType
public class DocumentationProjectPersistenceImpl extends BasePersistenceImpl<DocumentationProject>
	implements DocumentationProjectPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DocumentationProjectUtil} to access the documentation project persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = DocumentationProjectImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DocumentationProjectModelImpl.ENTITY_CACHE_ENABLED,
			DocumentationProjectModelImpl.FINDER_CACHE_ENABLED,
			DocumentationProjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DocumentationProjectModelImpl.ENTITY_CACHE_ENABLED,
			DocumentationProjectModelImpl.FINDER_CACHE_ENABLED,
			DocumentationProjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DocumentationProjectModelImpl.ENTITY_CACHE_ENABLED,
			DocumentationProjectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(DocumentationProjectModelImpl.ENTITY_CACHE_ENABLED,
			DocumentationProjectModelImpl.FINDER_CACHE_ENABLED,
			DocumentationProjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(DocumentationProjectModelImpl.ENTITY_CACHE_ENABLED,
			DocumentationProjectModelImpl.FINDER_CACHE_ENABLED,
			DocumentationProjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			DocumentationProjectModelImpl.UUID_COLUMN_BITMASK |
			DocumentationProjectModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(DocumentationProjectModelImpl.ENTITY_CACHE_ENABLED,
			DocumentationProjectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the documentation projects where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching documentation projects
	 */
	@Override
	public List<DocumentationProject> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the documentation projects where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DocumentationProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of documentation projects
	 * @param end the upper bound of the range of documentation projects (not inclusive)
	 * @return the range of matching documentation projects
	 */
	@Override
	public List<DocumentationProject> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the documentation projects where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DocumentationProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of documentation projects
	 * @param end the upper bound of the range of documentation projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching documentation projects
	 */
	@Override
	public List<DocumentationProject> findByUuid(String uuid, int start,
		int end, OrderByComparator<DocumentationProject> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the documentation projects where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DocumentationProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of documentation projects
	 * @param end the upper bound of the range of documentation projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching documentation projects
	 */
	@Override
	public List<DocumentationProject> findByUuid(String uuid, int start,
		int end, OrderByComparator<DocumentationProject> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid, start, end, orderByComparator };
		}

		List<DocumentationProject> list = null;

		if (retrieveFromCache) {
			list = (List<DocumentationProject>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DocumentationProject documentationProject : list) {
					if (!Objects.equals(uuid, documentationProject.getUuid())) {
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

			query.append(_SQL_SELECT_DOCUMENTATIONPROJECT_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DocumentationProjectModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				if (!pagination) {
					list = (List<DocumentationProject>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DocumentationProject>)QueryUtil.list(q,
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
	 * Returns the first documentation project in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching documentation project
	 * @throws NoSuchDocumentationProjectException if a matching documentation project could not be found
	 */
	@Override
	public DocumentationProject findByUuid_First(String uuid,
		OrderByComparator<DocumentationProject> orderByComparator)
		throws NoSuchDocumentationProjectException {
		DocumentationProject documentationProject = fetchByUuid_First(uuid,
				orderByComparator);

		if (documentationProject != null) {
			return documentationProject;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDocumentationProjectException(msg.toString());
	}

	/**
	 * Returns the first documentation project in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching documentation project, or <code>null</code> if a matching documentation project could not be found
	 */
	@Override
	public DocumentationProject fetchByUuid_First(String uuid,
		OrderByComparator<DocumentationProject> orderByComparator) {
		List<DocumentationProject> list = findByUuid(uuid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last documentation project in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching documentation project
	 * @throws NoSuchDocumentationProjectException if a matching documentation project could not be found
	 */
	@Override
	public DocumentationProject findByUuid_Last(String uuid,
		OrderByComparator<DocumentationProject> orderByComparator)
		throws NoSuchDocumentationProjectException {
		DocumentationProject documentationProject = fetchByUuid_Last(uuid,
				orderByComparator);

		if (documentationProject != null) {
			return documentationProject;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDocumentationProjectException(msg.toString());
	}

	/**
	 * Returns the last documentation project in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching documentation project, or <code>null</code> if a matching documentation project could not be found
	 */
	@Override
	public DocumentationProject fetchByUuid_Last(String uuid,
		OrderByComparator<DocumentationProject> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<DocumentationProject> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the documentation projects before and after the current documentation project in the ordered set where uuid = &#63;.
	 *
	 * @param documentationProjectId the primary key of the current documentation project
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next documentation project
	 * @throws NoSuchDocumentationProjectException if a documentation project with the primary key could not be found
	 */
	@Override
	public DocumentationProject[] findByUuid_PrevAndNext(
		long documentationProjectId, String uuid,
		OrderByComparator<DocumentationProject> orderByComparator)
		throws NoSuchDocumentationProjectException {
		DocumentationProject documentationProject = findByPrimaryKey(documentationProjectId);

		Session session = null;

		try {
			session = openSession();

			DocumentationProject[] array = new DocumentationProjectImpl[3];

			array[0] = getByUuid_PrevAndNext(session, documentationProject,
					uuid, orderByComparator, true);

			array[1] = documentationProject;

			array[2] = getByUuid_PrevAndNext(session, documentationProject,
					uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DocumentationProject getByUuid_PrevAndNext(Session session,
		DocumentationProject documentationProject, String uuid,
		OrderByComparator<DocumentationProject> orderByComparator,
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

		query.append(_SQL_SELECT_DOCUMENTATIONPROJECT_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2);
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
			query.append(DocumentationProjectModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(documentationProject);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DocumentationProject> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the documentation projects where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (DocumentationProject documentationProject : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(documentationProject);
		}
	}

	/**
	 * Returns the number of documentation projects where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching documentation projects
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DOCUMENTATIONPROJECT_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "documentationProject.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "documentationProject.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(documentationProject.uuid IS NULL OR documentationProject.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(DocumentationProjectModelImpl.ENTITY_CACHE_ENABLED,
			DocumentationProjectModelImpl.FINDER_CACHE_ENABLED,
			DocumentationProjectImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			DocumentationProjectModelImpl.UUID_COLUMN_BITMASK |
			DocumentationProjectModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(DocumentationProjectModelImpl.ENTITY_CACHE_ENABLED,
			DocumentationProjectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the documentation project where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDocumentationProjectException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching documentation project
	 * @throws NoSuchDocumentationProjectException if a matching documentation project could not be found
	 */
	@Override
	public DocumentationProject findByUUID_G(String uuid, long groupId)
		throws NoSuchDocumentationProjectException {
		DocumentationProject documentationProject = fetchByUUID_G(uuid, groupId);

		if (documentationProject == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchDocumentationProjectException(msg.toString());
		}

		return documentationProject;
	}

	/**
	 * Returns the documentation project where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching documentation project, or <code>null</code> if a matching documentation project could not be found
	 */
	@Override
	public DocumentationProject fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the documentation project where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching documentation project, or <code>null</code> if a matching documentation project could not be found
	 */
	@Override
	public DocumentationProject fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof DocumentationProject) {
			DocumentationProject documentationProject = (DocumentationProject)result;

			if (!Objects.equals(uuid, documentationProject.getUuid()) ||
					(groupId != documentationProject.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DOCUMENTATIONPROJECT_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				List<DocumentationProject> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					DocumentationProject documentationProject = list.get(0);

					result = documentationProject;

					cacheResult(documentationProject);

					if ((documentationProject.getUuid() == null) ||
							!documentationProject.getUuid().equals(uuid) ||
							(documentationProject.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, documentationProject);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, finderArgs);

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
			return (DocumentationProject)result;
		}
	}

	/**
	 * Removes the documentation project where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the documentation project that was removed
	 */
	@Override
	public DocumentationProject removeByUUID_G(String uuid, long groupId)
		throws NoSuchDocumentationProjectException {
		DocumentationProject documentationProject = findByUUID_G(uuid, groupId);

		return remove(documentationProject);
	}

	/**
	 * Returns the number of documentation projects where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching documentation projects
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOCUMENTATIONPROJECT_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "documentationProject.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "documentationProject.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(documentationProject.uuid IS NULL OR documentationProject.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "documentationProject.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(DocumentationProjectModelImpl.ENTITY_CACHE_ENABLED,
			DocumentationProjectModelImpl.FINDER_CACHE_ENABLED,
			DocumentationProjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(DocumentationProjectModelImpl.ENTITY_CACHE_ENABLED,
			DocumentationProjectModelImpl.FINDER_CACHE_ENABLED,
			DocumentationProjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			DocumentationProjectModelImpl.UUID_COLUMN_BITMASK |
			DocumentationProjectModelImpl.COMPANYID_COLUMN_BITMASK |
			DocumentationProjectModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(DocumentationProjectModelImpl.ENTITY_CACHE_ENABLED,
			DocumentationProjectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the documentation projects where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching documentation projects
	 */
	@Override
	public List<DocumentationProject> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the documentation projects where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DocumentationProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of documentation projects
	 * @param end the upper bound of the range of documentation projects (not inclusive)
	 * @return the range of matching documentation projects
	 */
	@Override
	public List<DocumentationProject> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the documentation projects where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DocumentationProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of documentation projects
	 * @param end the upper bound of the range of documentation projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching documentation projects
	 */
	@Override
	public List<DocumentationProject> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<DocumentationProject> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the documentation projects where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DocumentationProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of documentation projects
	 * @param end the upper bound of the range of documentation projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching documentation projects
	 */
	@Override
	public List<DocumentationProject> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<DocumentationProject> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] { uuid, companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] {
					uuid, companyId,
					
					start, end, orderByComparator
				};
		}

		List<DocumentationProject> list = null;

		if (retrieveFromCache) {
			list = (List<DocumentationProject>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DocumentationProject documentationProject : list) {
					if (!Objects.equals(uuid, documentationProject.getUuid()) ||
							(companyId != documentationProject.getCompanyId())) {
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

			query.append(_SQL_SELECT_DOCUMENTATIONPROJECT_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DocumentationProjectModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				if (!pagination) {
					list = (List<DocumentationProject>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DocumentationProject>)QueryUtil.list(q,
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
	 * Returns the first documentation project in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching documentation project
	 * @throws NoSuchDocumentationProjectException if a matching documentation project could not be found
	 */
	@Override
	public DocumentationProject findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<DocumentationProject> orderByComparator)
		throws NoSuchDocumentationProjectException {
		DocumentationProject documentationProject = fetchByUuid_C_First(uuid,
				companyId, orderByComparator);

		if (documentationProject != null) {
			return documentationProject;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDocumentationProjectException(msg.toString());
	}

	/**
	 * Returns the first documentation project in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching documentation project, or <code>null</code> if a matching documentation project could not be found
	 */
	@Override
	public DocumentationProject fetchByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<DocumentationProject> orderByComparator) {
		List<DocumentationProject> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last documentation project in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching documentation project
	 * @throws NoSuchDocumentationProjectException if a matching documentation project could not be found
	 */
	@Override
	public DocumentationProject findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<DocumentationProject> orderByComparator)
		throws NoSuchDocumentationProjectException {
		DocumentationProject documentationProject = fetchByUuid_C_Last(uuid,
				companyId, orderByComparator);

		if (documentationProject != null) {
			return documentationProject;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDocumentationProjectException(msg.toString());
	}

	/**
	 * Returns the last documentation project in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching documentation project, or <code>null</code> if a matching documentation project could not be found
	 */
	@Override
	public DocumentationProject fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<DocumentationProject> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<DocumentationProject> list = findByUuid_C(uuid, companyId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the documentation projects before and after the current documentation project in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param documentationProjectId the primary key of the current documentation project
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next documentation project
	 * @throws NoSuchDocumentationProjectException if a documentation project with the primary key could not be found
	 */
	@Override
	public DocumentationProject[] findByUuid_C_PrevAndNext(
		long documentationProjectId, String uuid, long companyId,
		OrderByComparator<DocumentationProject> orderByComparator)
		throws NoSuchDocumentationProjectException {
		DocumentationProject documentationProject = findByPrimaryKey(documentationProjectId);

		Session session = null;

		try {
			session = openSession();

			DocumentationProject[] array = new DocumentationProjectImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, documentationProject,
					uuid, companyId, orderByComparator, true);

			array[1] = documentationProject;

			array[2] = getByUuid_C_PrevAndNext(session, documentationProject,
					uuid, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DocumentationProject getByUuid_C_PrevAndNext(Session session,
		DocumentationProject documentationProject, String uuid, long companyId,
		OrderByComparator<DocumentationProject> orderByComparator,
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

		query.append(_SQL_SELECT_DOCUMENTATIONPROJECT_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_1);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

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
			query.append(DocumentationProjectModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(documentationProject);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DocumentationProject> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the documentation projects where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (DocumentationProject documentationProject : findByUuid_C(uuid,
				companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(documentationProject);
		}
	}

	/**
	 * Returns the number of documentation projects where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching documentation projects
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOCUMENTATIONPROJECT_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "documentationProject.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "documentationProject.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(documentationProject.uuid IS NULL OR documentationProject.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "documentationProject.companyId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_NAME = new FinderPath(DocumentationProjectModelImpl.ENTITY_CACHE_ENABLED,
			DocumentationProjectModelImpl.FINDER_CACHE_ENABLED,
			DocumentationProjectImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByName", new String[] { String.class.getName() },
			DocumentationProjectModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_NAME = new FinderPath(DocumentationProjectModelImpl.ENTITY_CACHE_ENABLED,
			DocumentationProjectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByName",
			new String[] { String.class.getName() });

	/**
	 * Returns the documentation project where name = &#63; or throws a {@link NoSuchDocumentationProjectException} if it could not be found.
	 *
	 * @param name the name
	 * @return the matching documentation project
	 * @throws NoSuchDocumentationProjectException if a matching documentation project could not be found
	 */
	@Override
	public DocumentationProject findByName(String name)
		throws NoSuchDocumentationProjectException {
		DocumentationProject documentationProject = fetchByName(name);

		if (documentationProject == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("name=");
			msg.append(name);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchDocumentationProjectException(msg.toString());
		}

		return documentationProject;
	}

	/**
	 * Returns the documentation project where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param name the name
	 * @return the matching documentation project, or <code>null</code> if a matching documentation project could not be found
	 */
	@Override
	public DocumentationProject fetchByName(String name) {
		return fetchByName(name, true);
	}

	/**
	 * Returns the documentation project where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param name the name
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching documentation project, or <code>null</code> if a matching documentation project could not be found
	 */
	@Override
	public DocumentationProject fetchByName(String name,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { name };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_NAME,
					finderArgs, this);
		}

		if (result instanceof DocumentationProject) {
			DocumentationProject documentationProject = (DocumentationProject)result;

			if (!Objects.equals(name, documentationProject.getName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_DOCUMENTATIONPROJECT_WHERE);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_NAME_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_NAME_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_NAME_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindName) {
					qPos.add(name);
				}

				List<DocumentationProject> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_NAME,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"DocumentationProjectPersistenceImpl.fetchByName(String, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					DocumentationProject documentationProject = list.get(0);

					result = documentationProject;

					cacheResult(documentationProject);

					if ((documentationProject.getName() == null) ||
							!documentationProject.getName().equals(name)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_NAME,
							finderArgs, documentationProject);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_NAME, finderArgs);

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
			return (DocumentationProject)result;
		}
	}

	/**
	 * Removes the documentation project where name = &#63; from the database.
	 *
	 * @param name the name
	 * @return the documentation project that was removed
	 */
	@Override
	public DocumentationProject removeByName(String name)
		throws NoSuchDocumentationProjectException {
		DocumentationProject documentationProject = findByName(name);

		return remove(documentationProject);
	}

	/**
	 * Returns the number of documentation projects where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching documentation projects
	 */
	@Override
	public int countByName(String name) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_NAME;

		Object[] finderArgs = new Object[] { name };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DOCUMENTATIONPROJECT_WHERE);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_NAME_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_NAME_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_NAME_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

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

	private static final String _FINDER_COLUMN_NAME_NAME_1 = "documentationProject.name IS NULL";
	private static final String _FINDER_COLUMN_NAME_NAME_2 = "documentationProject.name = ?";
	private static final String _FINDER_COLUMN_NAME_NAME_3 = "(documentationProject.name IS NULL OR documentationProject.name = '')";

	public DocumentationProjectPersistenceImpl() {
		setModelClass(DocumentationProject.class);
	}

	/**
	 * Caches the documentation project in the entity cache if it is enabled.
	 *
	 * @param documentationProject the documentation project
	 */
	@Override
	public void cacheResult(DocumentationProject documentationProject) {
		entityCache.putResult(DocumentationProjectModelImpl.ENTITY_CACHE_ENABLED,
			DocumentationProjectImpl.class,
			documentationProject.getPrimaryKey(), documentationProject);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				documentationProject.getUuid(),
				documentationProject.getGroupId()
			}, documentationProject);

		finderCache.putResult(FINDER_PATH_FETCH_BY_NAME,
			new Object[] { documentationProject.getName() },
			documentationProject);

		documentationProject.resetOriginalValues();
	}

	/**
	 * Caches the documentation projects in the entity cache if it is enabled.
	 *
	 * @param documentationProjects the documentation projects
	 */
	@Override
	public void cacheResult(List<DocumentationProject> documentationProjects) {
		for (DocumentationProject documentationProject : documentationProjects) {
			if (entityCache.getResult(
						DocumentationProjectModelImpl.ENTITY_CACHE_ENABLED,
						DocumentationProjectImpl.class,
						documentationProject.getPrimaryKey()) == null) {
				cacheResult(documentationProject);
			}
			else {
				documentationProject.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all documentation projects.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DocumentationProjectImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the documentation project.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DocumentationProject documentationProject) {
		entityCache.removeResult(DocumentationProjectModelImpl.ENTITY_CACHE_ENABLED,
			DocumentationProjectImpl.class, documentationProject.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((DocumentationProjectModelImpl)documentationProject);
	}

	@Override
	public void clearCache(List<DocumentationProject> documentationProjects) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (DocumentationProject documentationProject : documentationProjects) {
			entityCache.removeResult(DocumentationProjectModelImpl.ENTITY_CACHE_ENABLED,
				DocumentationProjectImpl.class,
				documentationProject.getPrimaryKey());

			clearUniqueFindersCache((DocumentationProjectModelImpl)documentationProject);
		}
	}

	protected void cacheUniqueFindersCache(
		DocumentationProjectModelImpl documentationProjectModelImpl,
		boolean isNew) {
		if (isNew) {
			Object[] args = new Object[] {
					documentationProjectModelImpl.getUuid(),
					documentationProjectModelImpl.getGroupId()
				};

			finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
				Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
				documentationProjectModelImpl);

			args = new Object[] { documentationProjectModelImpl.getName() };

			finderCache.putResult(FINDER_PATH_COUNT_BY_NAME, args,
				Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_NAME, args,
				documentationProjectModelImpl);
		}
		else {
			if ((documentationProjectModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						documentationProjectModelImpl.getUuid(),
						documentationProjectModelImpl.getGroupId()
					};

				finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
					Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
					documentationProjectModelImpl);
			}

			if ((documentationProjectModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_NAME.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						documentationProjectModelImpl.getName()
					};

				finderCache.putResult(FINDER_PATH_COUNT_BY_NAME, args,
					Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_NAME, args,
					documentationProjectModelImpl);
			}
		}
	}

	protected void clearUniqueFindersCache(
		DocumentationProjectModelImpl documentationProjectModelImpl) {
		Object[] args = new Object[] {
				documentationProjectModelImpl.getUuid(),
				documentationProjectModelImpl.getGroupId()
			};

		finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

		if ((documentationProjectModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			args = new Object[] {
					documentationProjectModelImpl.getOriginalUuid(),
					documentationProjectModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		args = new Object[] { documentationProjectModelImpl.getName() };

		finderCache.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_NAME, args);

		if ((documentationProjectModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_NAME.getColumnBitmask()) != 0) {
			args = new Object[] { documentationProjectModelImpl.getOriginalName() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_NAME, args);
		}
	}

	/**
	 * Creates a new documentation project with the primary key. Does not add the documentation project to the database.
	 *
	 * @param documentationProjectId the primary key for the new documentation project
	 * @return the new documentation project
	 */
	@Override
	public DocumentationProject create(long documentationProjectId) {
		DocumentationProject documentationProject = new DocumentationProjectImpl();

		documentationProject.setNew(true);
		documentationProject.setPrimaryKey(documentationProjectId);

		String uuid = PortalUUIDUtil.generate();

		documentationProject.setUuid(uuid);

		documentationProject.setCompanyId(companyProvider.getCompanyId());

		return documentationProject;
	}

	/**
	 * Removes the documentation project with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param documentationProjectId the primary key of the documentation project
	 * @return the documentation project that was removed
	 * @throws NoSuchDocumentationProjectException if a documentation project with the primary key could not be found
	 */
	@Override
	public DocumentationProject remove(long documentationProjectId)
		throws NoSuchDocumentationProjectException {
		return remove((Serializable)documentationProjectId);
	}

	/**
	 * Removes the documentation project with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the documentation project
	 * @return the documentation project that was removed
	 * @throws NoSuchDocumentationProjectException if a documentation project with the primary key could not be found
	 */
	@Override
	public DocumentationProject remove(Serializable primaryKey)
		throws NoSuchDocumentationProjectException {
		Session session = null;

		try {
			session = openSession();

			DocumentationProject documentationProject = (DocumentationProject)session.get(DocumentationProjectImpl.class,
					primaryKey);

			if (documentationProject == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDocumentationProjectException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(documentationProject);
		}
		catch (NoSuchDocumentationProjectException nsee) {
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
	protected DocumentationProject removeImpl(
		DocumentationProject documentationProject) {
		documentationProject = toUnwrappedModel(documentationProject);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(documentationProject)) {
				documentationProject = (DocumentationProject)session.get(DocumentationProjectImpl.class,
						documentationProject.getPrimaryKeyObj());
			}

			if (documentationProject != null) {
				session.delete(documentationProject);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (documentationProject != null) {
			clearCache(documentationProject);
		}

		return documentationProject;
	}

	@Override
	public DocumentationProject updateImpl(
		DocumentationProject documentationProject) {
		documentationProject = toUnwrappedModel(documentationProject);

		boolean isNew = documentationProject.isNew();

		DocumentationProjectModelImpl documentationProjectModelImpl = (DocumentationProjectModelImpl)documentationProject;

		if (Validator.isNull(documentationProject.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			documentationProject.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (documentationProject.getCreateDate() == null)) {
			if (serviceContext == null) {
				documentationProject.setCreateDate(now);
			}
			else {
				documentationProject.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!documentationProjectModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				documentationProject.setModifiedDate(now);
			}
			else {
				documentationProject.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (documentationProject.isNew()) {
				session.save(documentationProject);

				documentationProject.setNew(false);
			}
			else {
				documentationProject = (DocumentationProject)session.merge(documentationProject);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !DocumentationProjectModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((documentationProjectModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						documentationProjectModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { documentationProjectModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((documentationProjectModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						documentationProjectModelImpl.getOriginalUuid(),
						documentationProjectModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						documentationProjectModelImpl.getUuid(),
						documentationProjectModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}
		}

		entityCache.putResult(DocumentationProjectModelImpl.ENTITY_CACHE_ENABLED,
			DocumentationProjectImpl.class,
			documentationProject.getPrimaryKey(), documentationProject, false);

		clearUniqueFindersCache(documentationProjectModelImpl);
		cacheUniqueFindersCache(documentationProjectModelImpl, isNew);

		documentationProject.resetOriginalValues();

		return documentationProject;
	}

	protected DocumentationProject toUnwrappedModel(
		DocumentationProject documentationProject) {
		if (documentationProject instanceof DocumentationProjectImpl) {
			return documentationProject;
		}

		DocumentationProjectImpl documentationProjectImpl = new DocumentationProjectImpl();

		documentationProjectImpl.setNew(documentationProject.isNew());
		documentationProjectImpl.setPrimaryKey(documentationProject.getPrimaryKey());

		documentationProjectImpl.setUuid(documentationProject.getUuid());
		documentationProjectImpl.setDocumentationProjectId(documentationProject.getDocumentationProjectId());
		documentationProjectImpl.setGroupId(documentationProject.getGroupId());
		documentationProjectImpl.setCompanyId(documentationProject.getCompanyId());
		documentationProjectImpl.setUserId(documentationProject.getUserId());
		documentationProjectImpl.setUserName(documentationProject.getUserName());
		documentationProjectImpl.setCreateDate(documentationProject.getCreateDate());
		documentationProjectImpl.setModifiedDate(documentationProject.getModifiedDate());
		documentationProjectImpl.setName(documentationProject.getName());
		documentationProjectImpl.setDescription(documentationProject.getDescription());
		documentationProjectImpl.setIconFileName(documentationProject.getIconFileName());
		documentationProjectImpl.setStatus(documentationProject.getStatus());

		return documentationProjectImpl;
	}

	/**
	 * Returns the documentation project with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the documentation project
	 * @return the documentation project
	 * @throws NoSuchDocumentationProjectException if a documentation project with the primary key could not be found
	 */
	@Override
	public DocumentationProject findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDocumentationProjectException {
		DocumentationProject documentationProject = fetchByPrimaryKey(primaryKey);

		if (documentationProject == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDocumentationProjectException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return documentationProject;
	}

	/**
	 * Returns the documentation project with the primary key or throws a {@link NoSuchDocumentationProjectException} if it could not be found.
	 *
	 * @param documentationProjectId the primary key of the documentation project
	 * @return the documentation project
	 * @throws NoSuchDocumentationProjectException if a documentation project with the primary key could not be found
	 */
	@Override
	public DocumentationProject findByPrimaryKey(long documentationProjectId)
		throws NoSuchDocumentationProjectException {
		return findByPrimaryKey((Serializable)documentationProjectId);
	}

	/**
	 * Returns the documentation project with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the documentation project
	 * @return the documentation project, or <code>null</code> if a documentation project with the primary key could not be found
	 */
	@Override
	public DocumentationProject fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(DocumentationProjectModelImpl.ENTITY_CACHE_ENABLED,
				DocumentationProjectImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		DocumentationProject documentationProject = (DocumentationProject)serializable;

		if (documentationProject == null) {
			Session session = null;

			try {
				session = openSession();

				documentationProject = (DocumentationProject)session.get(DocumentationProjectImpl.class,
						primaryKey);

				if (documentationProject != null) {
					cacheResult(documentationProject);
				}
				else {
					entityCache.putResult(DocumentationProjectModelImpl.ENTITY_CACHE_ENABLED,
						DocumentationProjectImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(DocumentationProjectModelImpl.ENTITY_CACHE_ENABLED,
					DocumentationProjectImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return documentationProject;
	}

	/**
	 * Returns the documentation project with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param documentationProjectId the primary key of the documentation project
	 * @return the documentation project, or <code>null</code> if a documentation project with the primary key could not be found
	 */
	@Override
	public DocumentationProject fetchByPrimaryKey(long documentationProjectId) {
		return fetchByPrimaryKey((Serializable)documentationProjectId);
	}

	@Override
	public Map<Serializable, DocumentationProject> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, DocumentationProject> map = new HashMap<Serializable, DocumentationProject>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			DocumentationProject documentationProject = fetchByPrimaryKey(primaryKey);

			if (documentationProject != null) {
				map.put(primaryKey, documentationProject);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(DocumentationProjectModelImpl.ENTITY_CACHE_ENABLED,
					DocumentationProjectImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (DocumentationProject)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_DOCUMENTATIONPROJECT_WHERE_PKS_IN);

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

			for (DocumentationProject documentationProject : (List<DocumentationProject>)q.list()) {
				map.put(documentationProject.getPrimaryKeyObj(),
					documentationProject);

				cacheResult(documentationProject);

				uncachedPrimaryKeys.remove(documentationProject.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(DocumentationProjectModelImpl.ENTITY_CACHE_ENABLED,
					DocumentationProjectImpl.class, primaryKey, nullModel);
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
	 * Returns all the documentation projects.
	 *
	 * @return the documentation projects
	 */
	@Override
	public List<DocumentationProject> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the documentation projects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DocumentationProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of documentation projects
	 * @param end the upper bound of the range of documentation projects (not inclusive)
	 * @return the range of documentation projects
	 */
	@Override
	public List<DocumentationProject> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the documentation projects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DocumentationProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of documentation projects
	 * @param end the upper bound of the range of documentation projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of documentation projects
	 */
	@Override
	public List<DocumentationProject> findAll(int start, int end,
		OrderByComparator<DocumentationProject> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the documentation projects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DocumentationProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of documentation projects
	 * @param end the upper bound of the range of documentation projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of documentation projects
	 */
	@Override
	public List<DocumentationProject> findAll(int start, int end,
		OrderByComparator<DocumentationProject> orderByComparator,
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

		List<DocumentationProject> list = null;

		if (retrieveFromCache) {
			list = (List<DocumentationProject>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_DOCUMENTATIONPROJECT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DOCUMENTATIONPROJECT;

				if (pagination) {
					sql = sql.concat(DocumentationProjectModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<DocumentationProject>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DocumentationProject>)QueryUtil.list(q,
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
	 * Removes all the documentation projects from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DocumentationProject documentationProject : findAll()) {
			remove(documentationProject);
		}
	}

	/**
	 * Returns the number of documentation projects.
	 *
	 * @return the number of documentation projects
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_DOCUMENTATIONPROJECT);

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
		return DocumentationProjectModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the documentation project persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(DocumentationProjectImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_DOCUMENTATIONPROJECT = "SELECT documentationProject FROM DocumentationProject documentationProject";
	private static final String _SQL_SELECT_DOCUMENTATIONPROJECT_WHERE_PKS_IN = "SELECT documentationProject FROM DocumentationProject documentationProject WHERE documentationProjectId IN (";
	private static final String _SQL_SELECT_DOCUMENTATIONPROJECT_WHERE = "SELECT documentationProject FROM DocumentationProject documentationProject WHERE ";
	private static final String _SQL_COUNT_DOCUMENTATIONPROJECT = "SELECT COUNT(documentationProject) FROM DocumentationProject documentationProject";
	private static final String _SQL_COUNT_DOCUMENTATIONPROJECT_WHERE = "SELECT COUNT(documentationProject) FROM DocumentationProject documentationProject WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "documentationProject.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DocumentationProject exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DocumentationProject exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(DocumentationProjectPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}