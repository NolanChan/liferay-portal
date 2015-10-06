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

package com.liferay.portal.dao.orm.common;

import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBFactoryUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.io.unsync.UnsyncStringReader;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.io.InputStream;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Daniel Sanz
 * @author Shuyang Zhou
 */
public class SQLTransformerCastClobTextOracleTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@BeforeClass
	public static void setUpClass() throws Exception {
		_db = DBFactoryUtil.getDB();

		String dbType = _db.getType();

		if (!dbType.equals(DB.TYPE_ORACLE)) {
			return;
		}

		_db.runSQL(_SQL_CREATE_TABLE);

		try (Connection connection = DataAccess.getConnection();
			PreparedStatement preparedStatement =
				connection.prepareStatement(
					"insert into TestCastClobText values (?, ?)")) {

			preparedStatement.setLong(1, 1);
			preparedStatement.setClob(
				2, new UnsyncStringReader(_BIG_TEXT_A_3999));

			Assert.assertEquals(1, preparedStatement.executeUpdate());

			preparedStatement.setLong(1, 2);
			preparedStatement.setClob(
				2, new UnsyncStringReader(_BIG_TEXT_A_4000));

			Assert.assertEquals(1, preparedStatement.executeUpdate());

			preparedStatement.setLong(1, 3);
			preparedStatement.setClob(
				2, new UnsyncStringReader(_BIG_TEXT_A_4001));

			Assert.assertEquals(1, preparedStatement.executeUpdate());

			preparedStatement.setLong(1, 4);
			preparedStatement.setClob(
				2, new UnsyncStringReader(_BIG_TEXT_A_3999_B_1));

			Assert.assertEquals(1, preparedStatement.executeUpdate());

			preparedStatement.setLong(1, 5);
			preparedStatement.setClob(
				2, new UnsyncStringReader(_BIG_TEXT_A_3999_B_2));

			Assert.assertEquals(1, preparedStatement.executeUpdate());

			preparedStatement.setLong(1, 6);
			preparedStatement.setClob(
				2, new UnsyncStringReader(_BIG_TEXT_A_4000_B_1));

			Assert.assertEquals(1, preparedStatement.executeUpdate());
		}
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
		String dbType = _db.getType();

		if (!dbType.equals(DB.TYPE_ORACLE)) {
			return;
		}

		_db.runSQL("drop table TestCastClobText");
	}

	@Before
	public void setUp() {
		String dbType = _db.getType();

		Assume.assumeTrue(dbType.equals(DB.TYPE_ORACLE));
	}

	@Test
	public void testSelectBigText_3999() throws Exception {
		Assert.assertEquals(
			Arrays.asList(_BIG_TEXT_A_3999), select(_BIG_TEXT_A_3999));
	}

	@Test
	public void testSelectBigText_4000() throws Exception {
		Assert.assertEquals(
			Arrays.asList(
				_BIG_TEXT_A_4000, _BIG_TEXT_A_4001, _BIG_TEXT_A_4000_B_1),
			select(_BIG_TEXT_A_4000));
		Assert.assertEquals(
			Arrays.asList(_BIG_TEXT_A_3999_B_1, _BIG_TEXT_A_3999_B_2),
			select(_BIG_TEXT_A_3999_B_1));
	}

	@Test
	public void testSelectBigText_4001() throws Exception {
		List<String> values = select(_BIG_TEXT_A_4000, _A);

		Assert.assertTrue(values.toString(), values.isEmpty());

		values = select(_BIG_TEXT_A_3999_B_1, _B);

		Assert.assertTrue(values.toString(), values.isEmpty());

		values = select(_BIG_TEXT_A_4000, _B);

		Assert.assertTrue(values.toString(), values.isEmpty());
	}

	@Test
	public void testSelectText_1() throws Exception {
		List<String> values = select(_A);

		Assert.assertTrue(values.toString(), values.isEmpty());

		values = select(_B);

		Assert.assertTrue(values.toString(), values.isEmpty());
	}

	protected List<String> select(String data) throws Exception {
		return select(data, StringPool.BLANK);
	}

	protected List<String> select(String data1, String data2)
		throws Exception {

		try (Connection connection = DataAccess.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
				SQLTransformer.transform(_SQL_SELECT_COMPARE_STRINGS))) {

			Clob clob1 = connection.createClob();

			clob1.setString(1, data1);

			preparedStatement.setClob(1, clob1);

			Clob clob2 = connection.createClob();

			clob2.setString(1, data2);

			preparedStatement.setClob(2, clob2);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				List<String> values = new ArrayList<>();

				while (resultSet.next()) {
					Clob clob = resultSet.getClob(1);

					try (InputStream inputStream = clob.getAsciiStream()) {
						values.add(StringUtil.read(inputStream));
					}
				}

				return values;
			}
		}
	}

	private static final String _A = "a";

	private static final String _B = "b";

	private static final String _BIG_TEXT_A_3999;

	private static final String _BIG_TEXT_A_3999_B_1;

	private static final String _BIG_TEXT_A_3999_B_2;

	private static final String _BIG_TEXT_A_4000;

	private static final String _BIG_TEXT_A_4000_B_1;

	private static final String _BIG_TEXT_A_4001;

	private static final String _SQL_CREATE_TABLE =
		"create table TestCastClobText (id LONG not null primary key, " +
			"data TEXT null)";

	private static final String _SQL_SELECT_COMPARE_STRINGS =
		"SELECT data FROM TestCastClobText WHERE " +
			"DBMS_LOB.COMPARE(CAST_CLOB_TEXT(TestCastClobText.data), ? || ?) " +
				"= 0 ORDER BY id";

	private static DB _db;

	static {
		char[] data = new char[3999];

		Arrays.fill(data, CharPool.LOWER_CASE_A);

		_BIG_TEXT_A_3999 = new String(data);

		_BIG_TEXT_A_3999_B_1 = _BIG_TEXT_A_3999 + _B;

		_BIG_TEXT_A_3999_B_2 = _BIG_TEXT_A_3999_B_1 + _B;

		_BIG_TEXT_A_4000 = _BIG_TEXT_A_3999 + _A;

		_BIG_TEXT_A_4000_B_1 = _BIG_TEXT_A_4000 + _B;

		_BIG_TEXT_A_4001 = _BIG_TEXT_A_4000 + _A;
	}

}