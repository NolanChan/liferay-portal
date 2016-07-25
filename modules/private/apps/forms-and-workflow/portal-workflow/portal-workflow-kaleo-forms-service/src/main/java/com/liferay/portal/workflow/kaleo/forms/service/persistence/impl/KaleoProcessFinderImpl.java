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
 * Implements custom finders for kaleo processes.
 *
 * @author Inácio Nery
 */
public class KaleoProcessFinderImpl
	extends KaleoProcessFinderBaseImpl implements KaleoProcessFinder {

	public static final String COUNT_BY_G_N_D =
		KaleoProcessFinder.class.getName() + ".countByG_N_D";

	public static final String FIND_BY_G_N_D =
		KaleoProcessFinder.class.getName() + ".findByG_N_D";

	/**
	 * Returns the number of kaleo processes in a group, matching the string
	 * value of the keywords parameter to the processes' titles or descriptions.
	 *
	 * @param  groupId the primary key of the kaleo process's group
	 * @param  keywords the keywords (space separated) to look for and match in
	 *         the kaleo process name or description
	 * @return the number of matching kaleo processes
	 */
	@Override
	public int countByKeywords(long groupId, String keywords) {
		return doCountByKeywords(groupId, keywords, false);
	}

	/**
	 * Returns the number of kaleo processes in the group, matching the name
	 * and/or description parameters. Whether both the name and description must
	 * match, or only one must match, depends on the andOperator parameter.
	 *
	 * @param  groupId the primary key of the kaleo process's group
	 * @param  name the name to look for and match in the kaleo process name
	 * @param  description the description to look for and match in the kaleo
	 *         process description
	 * @param  andOperator whether every field must match its keywords, or just
	 *         one field
	 * @return the number of matching kaleo processes
	 */
	@Override
	public int countByG_N_D(
		long groupId, String name, String description, boolean andOperator) {

		String[] names = CustomSQLUtil.keywords(name);
		String[] descriptions = CustomSQLUtil.keywords(description, false);

		return doCountByG_N_D(groupId, names, descriptions, andOperator, false);
	}

	/**
	 * Returns the number of kaleo processes in the group, matching the keywords
	 * parameter. The keywords parameter is
	 * for matching string values to the kaleo processes' names or descriptions. Only kaleo processes the user has permission to view will be counted.
	 *
	 * @param  groupId the primary key of the kaleo processes' group
	 * @param  keywords the keywords (space separated) to look for and match in
	 *         each kaleo process's name or description
	 * @return the number of matching kaleo processes
	 */
	@Override
	public int filterCountByKeywords(long groupId, String keywords) {
		return doCountByKeywords(groupId, keywords, true);
	}

	/**
	 * Returns the number of kaleo processes in the group, matching the name or
	 * description parameters. Whether both
	 * the name and description must match, or only one must match, depends on
	 * the andOperator parameter. Only kaleo processes the user has permission to view will be counted.
	 *
	 * @param  groupId the primary key of the kaleo processes' group
	 * @param  andOperator whether every field must match its keywords, or just
	 *         one field
	 * @return the number of matching kaleo processes
	 */
	@Override
	public int filterCountByG_N_D(
		long groupId, String name, String description, boolean andOperator) {

		String[] names = CustomSQLUtil.keywords(name);
		String[] descriptions = CustomSQLUtil.keywords(description, false);

		return doCountByG_N_D(groupId, names, descriptions, andOperator, true);
	}

	/**
	 * Returns an ordered range of all kaleo processes in the group whose name
	 * or description matches the string values of the keywords parameter.
	 * Only kaleo processes the user has permission to view will be returned.
	 *
	 * @param  groupId the primary key of the kaleo processes' group
	 * @param  keywords the keywords (space separated) to look for and match in
	 *         each kaleo process's name or description
	 * @param  start the lower bound of the range of kaleo processes to return
	 * @param  end the upper bound of the range of kaleo processes to return
	 *         (not inclusive)
	 * @param  orderByComparator the comparator to order the kaleo processes
	 * @return the range of matching kaleo processes ordered by the comparator
	 */
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

	/**
	 * Returns an ordered range of all kaleo processes in the group, matching
	 * the kaleo process's name and/or description.
	 * Whether both the name and description must match, or only one
	 * must match, depends on the andOperator parameter. Only kaleo processes the user has permission to view will be returned.
	 *
	 * @param  groupId the primary key of the kaleo process's group
	 * @param  name the name to look for and match in the kaleo process name
	 * @param  description the description to look for and match in the kaleo
	 *         process description
	 * @param  andOperator whether every field must match its keywords, or just
	 *         one field
	 * @param  start the lower bound of the range of kaleo processes to return
	 * @param  end the upper bound of the range of kaleo processes to return
	 *         (not inclusive)
	 * @param  orderByComparator the comparator to order the kaleo processes
	 * @return the range of matching kaleo processes ordered by the comparator
	 */
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

	/**
	 * Returns an ordered range of all kaleo processes matching the name and/or
	 * description parameter. Whether both
	 * the name and description must match, or only one must match, depends on
	 * the andOperator parameter. Only kaleo processes the user has permission to view will be returned.
	 *
	 * @param  groupId the primary key of the kaleo process's group
	 * @param  names the names to look for and match in the kaleo process name
	 * @param  descriptions the descriptions to look for and match in the kaleo
	 *         process description
	 * @param  andOperator whether every field must match its keywords, or just
	 *         one field
	 * @param  start the lower bound of the range of kaleo processes to return
	 * @param  end the upper bound of the range of kaleo processes to return
	 *         (not inclusive)
	 * @param  orderByComparator the comparator to order the kaleo processes
	 * @return the range of matching kaleo processes ordered by the comparator
	 */
	@Override
	public List<KaleoProcess> filterFindByG_N_D(
		long groupId, String[] names, String[] descriptions,
		boolean andOperator, int start, int end,
		OrderByComparator<KaleoProcess> orderByComparator) {

		return doFindByG_N_D(
			groupId, names, descriptions, andOperator, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all kaleo processes in the group matching the
	 * parameters. The keywords parameter is used for matching string values to
	 * the kaleo processes' names or descriptions.
	 *
	 * @param  groupId the primary key of the kaleo processes' group
	 * @param  keywords the keywords (space separated) to look for and match in
	 *         each kaleo process's name or description
	 * @param  start the lower bound of the range of kaleo processes to return
	 * @param  end the upper bound of the range of kaleo processes to return
	 *         (not inclusive)
	 * @param  orderByComparator the comparator to order the kaleo processes
	 * @return the range of matching kaleo processes ordered by the comparator
	 */
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

	/**
	 * Returns an ordered range of all kaleo processes in the group, matching
	 * the name and/or description parameter. Whether both the name and
	 * description must match, or only one must match, depends on the
	 * andOperator parameter.
	 *
	 * @param  groupId the primary key of the kaleo process's group
	 * @param  name the name to look for and match in the kaleo process name
	 * @param  description the description to look for and match in the kaleo
	 *         process description
	 * @param  andOperator whether every field must match its keywords, or just
	 *         one field
	 * @param  start the lower bound of the range of kaleo processes to return
	 * @param  end the upper bound of the range of kaleo processes to return
	 *         (not inclusive)
	 * @param  orderByComparator the comparator to order the kaleo processes
	 * @return the range of matching kaleo processes ordered by the comparator
	 */
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

	/**
	 * Returns an ordered range of all kaleo processes in the group, matching
	 * the entered names and/or descriptions. Whether both the name and
	 * description must match, or only one must match, depends on the
	 * andOperator parameter.
	 *
	 * @param  groupId the primary key of the kaleo process's group
	 * @param  names the names to look for and match in the kaleo process name
	 * @param  descriptions the descriptions to look for and match in the kaleo
	 *         process description
	 * @param  andOperator whether every field must match its keywords, or just
	 *         one field
	 * @param  start the lower bound of the range of kaleo processes to return
	 * @param  end the upper bound of the range of kaleo processes to return
	 *         (not inclusive)
	 * @param  orderByComparator the comparator to order the kaleo processes
	 * @return the range of matching kaleo processes ordered by the comparator
	 */
	@Override
	public List<KaleoProcess> findByG_N_D(
		long groupId, String[] names, String[] descriptions,
		boolean andOperator, int start, int end,
		OrderByComparator<KaleoProcess> orderByComparator) {

		return doFindByG_N_D(
			groupId, names, descriptions, andOperator, start, end,
			orderByComparator, false);
	}

	/**
	 * Returns the number of kaleo processes in the group matching the
	 * parameters, including a keywords parameter for matching string values to
	 * the kaleo processes' names or descriptions.
	 *
	 * @param  groupId the primary key of the kaleo process's group
	 * @param  keywords the keywords (space separated) to look for and match in
	 *         the kaleo process name or description
	 * @param  inlineSQLHelper whether checks the permission
	 * @return the number of matching kaleo processes
	 */
	protected int doCountByKeywords(
		long groupId, String keywords, boolean inlineSQLHelper) {

		String[] names = null;
		String[] descriptions = null;
		boolean andOperator = true;

		if (Validator.isNotNull(keywords)) {
			names = CustomSQLUtil.keywords(keywords);
			descriptions = CustomSQLUtil.keywords(keywords, false);
			andOperator = false;
		}

		return doCountByG_N_D(
			groupId, names, descriptions, andOperator, inlineSQLHelper);
	}

	/**
	 * Returns the number of kaleo processes in the group, matching the entered
	 * names or descriptions. Whether both the name and description must match,
	 * or only one must match, depends on the andOperator parameter.
	 *
	 * @param  groupId the primary key of the kaleo process's group
	 * @param  names the names to look for and match in the kaleo process name
	 * @param  descriptions the descriptions to look for and match in the kaleo
	 *         process description
	 * @param  andOperator whether every field must match its keywords, or just
	 *         one field
	 * @param  inlineSQLHelper whether checks the permission
	 * @return the number of matching kaleo processes
	 */
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

	/**
	 * Returns an ordered range of all kaleo processes in the group, matching
	 * the entered names or descriptions. Whether both the name and description
	 * must match, or only one must match, depends on the andOperator parameter.
	 *
	 * @param  groupId the primary key of the kaleo process's group
	 * @param  names the names to look for and match in the kaleo process name
	 * @param  descriptions the descriptions to look for and match in the kaleo
	 *         process description
	 * @param  andOperator whether every field must match its keywords, or just
	 *         one field
	 * @param  start the lower bound of the range of kaleo processes to return
	 * @param  end the upper bound of the range of kaleo processes to return
	 *         (not inclusive)
	 * @param  orderByComparator the comparator to order the kaleo processes
	 * @param  inlineSQLHelper whether checks the permission
	 * @return the range of matching kaleo processes ordered by the comparator
	 */
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