/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */

package com.liferay.contacts.hook.upgrade.v2_0_0;

import com.liferay.contacts.model.Entry;
import com.liferay.contacts.service.EntryLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.util.List;

/**
 * @author Jonathan Lee
 */
public class UpgradeEntry extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		List<Entry> entries = EntryLocalServiceUtil.getEntries(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (Entry entry : entries) {
			try {
				UserLocalServiceUtil.getUserByEmailAddress(
					entry.getCompanyId(), entry.getEmailAddress());

				EntryLocalServiceUtil.deleteEntry(entry);
			}
			catch (Exception e) {
			}
		}
	}

}