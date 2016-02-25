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

package com.liferay.portal.upgrade.v6_1_0;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.upgrade.v6_1_0.util.JournalArticleTable;
import com.liferay.portal.upgrade.v6_1_0.util.JournalStructureTable;
import com.liferay.portal.upgrade.v6_1_0.util.JournalTemplateTable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Juan Fernández
 * @author Sergio González
 */
public class UpgradeJournal extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		alterColumnType(JournalArticleTable.class, "title", "STRING null");

		alterColumnType(
			JournalStructureTable.class, new String[] {"name", "STRING null"},
			new String[] {"description", "STRING null"});

		alterColumnType(
			JournalTemplateTable.class, new String[] {"name", "STRING null"},
			new String[] {"description", "STRING null"});

		updateStructureXsd();
	}

	protected void updateStructureXsd() throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			runSQL(
				"update JournalStructure set xsd = replace(xsd, " +
					"'image_gallery', 'document_library') where xsd like " +
						"'%image_gallery%'");
		}
		catch (Exception e) {
			ps = connection.prepareStatement(
				"select id_, xsd from JournalStructure where xsd like " +
					"'%image_gallery%'");

			rs = ps.executeQuery();

			while (rs.next()) {
				long id = rs.getLong("id_");
				String xsd = rs.getString("xsd");

				xsd = StringUtil.replace(
					xsd, "image_gallery", "document_library");

				updateStructureXsd(id, xsd);
			}
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

	protected void updateStructureXsd(long id, String xsd) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(
				"update JournalStructure set xsd = ? where id_ = ?");

			ps.setString(1, xsd);
			ps.setLong(2, id);

			ps.executeUpdate();
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

}