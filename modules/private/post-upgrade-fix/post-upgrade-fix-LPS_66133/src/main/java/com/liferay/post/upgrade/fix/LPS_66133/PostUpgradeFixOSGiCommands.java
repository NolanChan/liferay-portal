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

package com.liferay.post.upgrade.fix.LPS_66133;

import com.liferay.message.boards.kernel.model.MBCategoryConstants;
import com.liferay.message.boards.kernel.model.MBDiscussion;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.IOException;

import java.sql.SQLException;

import org.osgi.service.component.annotations.Component;

/**
 * <p>
 * Post upgrade fix for https://issues.liferay.com/browse/LPS-66133
 * </p>
 *
 * @author Jorge Díaz
 */
@Component(
	immediate = true,
	property = {
		"osgi.command.function=LPS_66133", "osgi.command.scope=postUpgradeFix"
	},
	service = PostUpgradeFixOSGiCommands.class
)
public class PostUpgradeFixOSGiCommands {

	public void LPS_66133() {
		if (_log.isInfoEnabled()) {
			_log.info("Executing postUpgradeFix:LPS_66133");
		}

		try {
			long classNameId = PortalUtil.getClassNameId(
				MBDiscussion.class.getName());

			StringBundler sb = new StringBundler(6);

			sb.append("delete from AssetEntry where classPK in (");
			sb.append("select messageId from MBMessage where threadId in (");
			sb.append("select threadId from MBThread where categoryId = ");
			sb.append(classNameId);
			sb.append(" and messageCount = 1)) and classNameId = ");
			sb.append(classNameId);

			runSQL(sb.toString());

			sb = new StringBundler(4);

			sb.append("delete from MBDiscussion where threadId in (select ");
			sb.append("threadId from MBThread where categoryId = ");
			sb.append(MBCategoryConstants.DISCUSSION_CATEGORY_ID);
			sb.append(" and messageCount = 1)");

			runSQL(sb.toString());

			sb = new StringBundler(4);

			sb.append("delete from MBMessage where threadId in (select ");
			sb.append("threadId from MBThread where categoryId = ");
			sb.append(MBCategoryConstants.DISCUSSION_CATEGORY_ID);
			sb.append(" and messageCount = 1)");

			runSQL(sb.toString());

			runSQL(
				"delete from MBThread where categoryId = " +
					MBCategoryConstants.DISCUSSION_CATEGORY_ID +
						" and messageCount = 1");

			if (_log.isInfoEnabled()) {
				_log.info("Finished executing postUpgradeFix:LPS_66133");
			}
		}
		catch (Exception e) {
			_log.error(
				"An exception was thrown while executing postUpgradeFix:" +
					"LPS_66133 ",
				e);
		}
	}

	public void runSQL(String template) throws IOException, SQLException {
		DB db = DBManagerUtil.getDB();

		db.runSQL(template);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PostUpgradeFixOSGiCommands.class);

}