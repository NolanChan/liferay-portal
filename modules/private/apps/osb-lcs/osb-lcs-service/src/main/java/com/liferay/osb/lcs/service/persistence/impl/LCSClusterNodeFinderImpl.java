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

import com.liferay.osb.lcs.model.LCSClusterNode;
import com.liferay.osb.lcs.model.impl.LCSClusterNodeImpl;
import com.liferay.osb.lcs.service.persistence.LCSClusterNodeFinder;
import com.liferay.portal.dao.orm.custom.sql.CustomSQLUtil;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

/**
 * @author Igor Beslic
 */
public class LCSClusterNodeFinderImpl
	extends LCSClusterNodeFinderBaseImpl implements LCSClusterNodeFinder {

	public static final String FIND_BY_LCEN_LPN =
		LCSClusterNodeFinder.class.getName() + ".findByLCEN_LPN";

	public static final String FIND_BY_U_A =
		LCSClusterNodeFinder.class.getName() + ".findByU_A";

	@Override
	public List<LCSClusterNode> findByLCEN_LPN(
		String lcsClusterEntryName, String lcsProjectName, boolean andOperator,
		int start, int end) {

		String sql = CustomSQLUtil.get(getClass(), FIND_BY_LCEN_LPN);

		if (Validator.isNull(lcsClusterEntryName)) {
			sql = StringUtil.replace(
				sql, "[$LCS_CLUSTER_ENTRY_NAME_TEMPLATE$] [$AND_OR_CONNECTOR$]",
				StringPool.BLANK);
		}
		else {
			sql = StringUtil.replace(
				sql, "[$LCS_CLUSTER_ENTRY_NAME_TEMPLATE$]",
				"OSBLCS_LCSClusterEntry.name LIKE ?");

			lcsClusterEntryName = lcsClusterEntryName + StringPool.PERCENT;
		}

		if (Validator.isNull(lcsProjectName)) {
			sql = StringUtil.replace(
				sql, "[$AND_OR_CONNECTOR$]", StringPool.BLANK);

			sql = StringUtil.replace(
				sql, "[$LCS_PROJECT_NAME_TEMPLATE$]", StringPool.BLANK);
		}
		else {
			sql = StringUtil.replace(
				sql, "[$LCS_PROJECT_NAME_TEMPLATE$]",
				"OSBLCS_LCSProject.name LIKE ?");

			lcsProjectName = lcsProjectName + StringPool.PERCENT;
		}

		sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.setCacheable(false);
			q.addEntity("OSBLCS_LCSClusterNode", LCSClusterNodeImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			if (Validator.isNotNull(lcsClusterEntryName)) {
				qPos.add(lcsClusterEntryName);
			}

			if (Validator.isNotNull(lcsProjectName)) {
				qPos.add(lcsProjectName);
			}

			return (List<LCSClusterNode>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	public List<LCSClusterNode> findByU_A(
		long userId, boolean archived, int start, int end) {

		String sql = CustomSQLUtil.get(getClass(), FIND_BY_U_A);

		sql = StringUtil.replace(
			sql, "[$LCS_CLUSTER_NODE_ACTIVE_TEMPLATE$]",
			"OSBLCS_LCSClusterNode.archived = ?");

		sql = CustomSQLUtil.replaceAndOperator(sql, true);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.setCacheable(false);
			q.addEntity("OSBLCS_LCSClusterNode", LCSClusterNodeImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);
			qPos.add(archived);

			return (List<LCSClusterNode>)QueryUtil.list(
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