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

package com.liferay.portal.test;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.servlet.ServletContextPool;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.servlet.MainServlet;
import com.liferay.portal.test.mock.AutoDeployMockServletContext;
import com.liferay.portal.util.test.TestPropsValues;

import javax.servlet.ServletException;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.mock.web.MockServletContext;

/**
 * @author Miguel Pastor
 * @author Shuyang Zhou
 */
public class MainServletTestRule implements TestRule {

	@Override
	public Statement apply(final Statement statement, Description description) {
		return new Statement() {

			@Override
			public void evaluate() throws Throwable {
				before();

				try {
					statement.evaluate();
				}
				finally {
					after();
				}
			}

		};
	}

	protected void after() {
		ServiceTestUtil.destroyServices();

		try {
			SearchEngineUtil.removeCompany(TestPropsValues.getCompanyId());
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	protected void before() {
		ServiceTestUtil.initServices();

		ServiceTestUtil.initPermissions();

		if (_mainServlet == null) {
			MockServletContext mockServletContext =
				new AutoDeployMockServletContext(
					new FileSystemResourceLoader());

			ServletContextPool.put(StringPool.BLANK, mockServletContext);

			MockServletConfig mockServletConfig = new MockServletConfig(
				mockServletContext);

			_mainServlet = new MainServlet();

			try {
				_mainServlet.init(mockServletConfig);
			}
			catch (ServletException se) {
				throw new RuntimeException(
					"The main servlet could not be initialized");
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		MainServletTestRule.class);

	private static MainServlet _mainServlet;

}