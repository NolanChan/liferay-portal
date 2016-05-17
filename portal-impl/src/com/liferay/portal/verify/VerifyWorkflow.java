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

package com.liferay.portal.verify;

import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Shinn Lok
 */
public class VerifyWorkflow extends VerifyProcess {

	protected void deleteOrphaned() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			for (String[] orphanedAttachedModel : getOrphanedAttachedModels()) {
				String tableName = orphanedAttachedModel[0];

				if (!hasTable(tableName) ||
					!hasColumn(tableName, "classNameId")) {

					continue;
				}

				String orphanedTableName = orphanedAttachedModel[2];
				String orphanedColumnName = orphanedAttachedModel[3];

				if (!hasTable(orphanedTableName)) {
					continue;
				}

				deleteOrphaned(
					tableName, orphanedTableName, orphanedColumnName);
			}
		}
	}

	protected void deleteOrphaned(
			String tableName, String orphanedTableName,
			String orphanedColumnName)
		throws Exception {

		StringBundler sb = new StringBundler(7);

		sb.append("delete from ");
		sb.append(tableName);
		sb.append(" where classPK not in (select ");
		sb.append(orphanedColumnName);
		sb.append(" from ");
		sb.append(orphanedTableName);
		sb.append(StringPool.CLOSE_PARENTHESIS);

		runSQL(sb.toString());
	}

	@Override
	protected void doVerify() throws Exception {
		deleteOrphaned();
	}

	protected String[][] getOrphanedAttachedModels() {
		return _ORPHANED_ATTACHED_MODELS;
	}

	private static final String[][] _ORPHANED_ATTACHED_MODELS = new String[][] {
		new String[] {
			"KaleoInstance",
			"com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess",
			"DDLRecord", "recordId"
		},
		new String[] {
			"KaleoInstanceToken",
			"com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess",
			"DDLRecord", "recordId"
		},
		new String[] {
			"WorkflowDefinitionLink",
			"com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess",
			"DDLRecord", "recordId"
		},
		new String[] {
			"WorkflowDefinitionLink",
			"com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess",
			"KaleoProcess", "kaleoProcessId"
		}
	};

}