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

package com.liferay.portal.workflow.kaleo.forms.service.persistence.impl;

import com.liferay.portal.dao.orm.custom.sql.CustomSQLUtil;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess;
import com.liferay.portal.workflow.kaleo.forms.model.impl.KaleoProcessImpl;
import com.liferay.portal.workflow.kaleo.forms.service.persistence.KaleoProcessFinder;

import java.util.Iterator;
import java.util.List;

/**
 * @author Inácio Nery
 */
public class KaleoProcessFinderImpl
	extends KaleoProcessFinderBaseImpl implements KaleoProcessFinder {

	public static final String COUNT_BY_G_N_D =
		KaleoProcessFinder.class.getName() + ".countByG_N_D";

	public static final String FIND_BY_G_N_D =
		KaleoProcessFinder.class.getName() + ".findByG_N_D";

	@Override
	public int countByKeywords(long groupId, String keywords) {
		return doCountByKeywords(groupId, keywords, false);
	}

	@Override
	public int countByG_N_D(
		long groupId, String name, String description, boolean andOperator) {

		String[] names = CustomSQLUtil.keywords(name);
		String[] descriptions = CustomSQLUtil.keywords(description, false);

		return doCountByG_N_D(groupId, names, descriptions, andOperator, false);
	}

	@Override
	public int filterCountByKeywords(long groupId, String keywords) {
		return doCountByKeywords(groupId, keywords, true);
	}

	@Override
	public int filterCountByG_N_D(
		long groupId, String name, String description, boolean andOperator) {

		String[] names = CustomSQLUtil.keywords(name);
		String[] descriptions = CustomSQLUtil.keywords(description, false);

		return doCountByG_N_D(groupId, names, descriptions, andOperator, true);
	}

	@Override
	public List<KaleoProcess> filterFindByKeywords(
		long groupId, String keywords, int start, int end,
		OrderByComparator<KaleoProcess> orderByComparator) {

		String[] names = null;
		String[] descriptions = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			names = CustomSQLUtil.keywords(keywords);
			descriptions = CustomSQLUtil.keywords(keywords, false);
		}
		else {
			andOperator = true;
		}

		return filterFindByG_N_D(
			groupId, names, descriptions, andOperator, start, end,
			orderByComparator);
	}

	@Override
	public List<KaleoProcess> filterFindByG_N_D(
		long groupId, String name, String description, boolean andOperator,
		int start, int end, OrderByComparator<KaleoProcess> orderByComparator) {

		String[] names = CustomSQLUtil.keywords(name);
		String[] descriptions = CustomSQLUtil.keywords(description, false);

		return filterFindByG_N_D(
			groupId, names, descriptions, andOperator, start, end,
			orderByComparator);
	}

	@Override
	public List<KaleoProcess> filterFindByG_N_D(
		long groupId, String[] names, String[] descriptions,
		boolean andOperator, int start, int end,
		OrderByComparator<KaleoProcess> orderByComparator) {

		return doFindByG_N_D(
			groupId, names, descriptions, andOperator, start, end,
			orderByComparator, true);
	}

	@Override
	public List<KaleoProcess> findByKeywords(
		long groupId, String keywords, int start, int end,
		OrderByComparator<KaleoProcess> orderByComparator) {

		String[] names = null;
		String[] descriptions = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			names = CustomSQLUtil.keywords(keywords);
			descriptions = CustomSQLUtil.keywords(keywords, false);
		}
		else {
			andOperator = true;
		}

		return findByG_N_D(
			groupId, names, descriptions, andOperator, start, end,
			orderByComparator);
	}

	@Override
	public List<KaleoProcess> findByG_N_D(
		long groupId, String name, String description, boolean andOperator,
		int start, int end, OrderByComparator<KaleoProcess> orderByComparator) {

		String[] names = CustomSQLUtil.keywords(name);
		String[] descriptions = CustomSQLUtil.keywords(description, false);

		return findByG_N_D(
			groupId, names, descriptions, andOperator, start, end,
			orderByComparator);
	}

	@Override
	public List<KaleoProcess> findByG_N_D(
		long groupId, String[] names, String[] descriptions,
		boolean andOperator, int start, int end,
		OrderByComparator<KaleoProcess> orderByComparator) {

		return doFindByG_N_D(
			groupId, names, descriptions, andOperator, start, end,
			orderByComparator, false);
	}

	protected int doCountByG_N_D(
		long groupId, String[] names, String[] descriptions,
		boolean andOperator, boolean inlineSQLHelper) {

		names = CustomSQLUtil.keywords(names);
		descriptions = CustomSQLUtil.keywords(descriptions, false);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(getClass(), COUNT_BY_G_N_D);

			if (inlineSQLHelper) {
				sql = InlineSQLHelperUtil.replacePermissionCheck(
					sql, KaleoProcess.class.getName(),
					"KaleoProcess.kaleoProcessId", groupId);
			}

			if (groupId <= 0) {
				sql = StringUtil.replace(
					sql, "(KaleoProcess.groupId = ?) AND", StringPool.BLANK);
			}

			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(DDLRecordSet.name)", StringPool.LIKE, false, names);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "DDLRecordSet.description", StringPool.LIKE, true,
				descriptions);
			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			if (groupId > 0) {
				qPos.add(groupId);
			}

			qPos.add(names, 2);
			qPos.add(descriptions, 2);

			Iterator<Long> itr = q.iterate();

			if (itr.hasNext()) {
				Long count = itr.next();

				if (count != null) {
					return count.intValue();
				}
			}

			return 0;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected int doCountByKeywords(
		long groupId, String keywords, boolean inlineSQLHelper) {

		String[] names = null;
		String[] descriptions = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			names = CustomSQLUtil.keywords(keywords);
			descriptions = CustomSQLUtil.keywords(keywords, false);
		}
		else {
			andOperator = true;
		}

		return doCountByG_N_D(
			groupId, names, descriptions, andOperator, inlineSQLHelper);
	}

	protected List<KaleoProcess> doFindByG_N_D(
		long groupId, String[] names, String[] descriptions,
		boolean andOperator, int start, int end,
		OrderByComparator<KaleoProcess> orderByComparator,
		boolean inlineSQLHelper) {

		names = CustomSQLUtil.keywords(names);
		descriptions = CustomSQLUtil.keywords(descriptions, false);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(getClass(), FIND_BY_G_N_D);

			if (inlineSQLHelper) {
				sql = InlineSQLHelperUtil.replacePermissionCheck(
					sql, KaleoProcess.class.getName(),
					"KaleoProcess.kaleoProcessId", groupId);
			}

			if (groupId <= 0) {
				sql = StringUtil.replace(
					sql, "(KaleoProcess.groupId = ?) AND", StringPool.BLANK);
			}

			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(DDLRecordSet.name)", StringPool.LIKE, false, names);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "DDLRecordSet.description", StringPool.LIKE, true,
				descriptions);
			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);
			sql = CustomSQLUtil.replaceOrderBy(sql, orderByComparator);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("KaleoProcess", KaleoProcessImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			if (groupId > 0) {
				qPos.add(groupId);
			}

			qPos.add(names, 2);
			qPos.add(descriptions, 2);

			return (List<KaleoProcess>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

}