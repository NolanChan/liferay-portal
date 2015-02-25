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

package com.liferay.portal.template;

import com.liferay.portal.cache.SingleVMPoolImpl;
import com.liferay.portal.cache.memory.MemoryPortalCacheManager;
import com.liferay.portal.kernel.cache.SingleVMPoolUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.security.xml.SecureXMLFactoryProviderImpl;
import com.liferay.portal.security.xml.SecureXMLFactoryProviderUtil;
import com.liferay.portal.util.HtmlImpl;
import com.liferay.portlet.PortletPreferencesFactoryImpl;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portlet.PortletPreferencesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author László Csontos
 */
public class TemplatePortletPreferencesTest {

	@BeforeClass
	public static void setUpClass() {
		HtmlUtil htmlUtil = new HtmlUtil();

		htmlUtil.setHtml(new HtmlImpl());

		PortletPreferencesFactoryUtil portletPreferencesFactoryUtil =
			new PortletPreferencesFactoryUtil();

		portletPreferencesFactoryUtil.setPortletPreferencesFactory(
			new PortletPreferencesFactoryImpl());

		SecureXMLFactoryProviderUtil secureXMLFactoryProviderUtil =
			new SecureXMLFactoryProviderUtil();

		secureXMLFactoryProviderUtil.setSecureXMLFactoryProvider(
			new SecureXMLFactoryProviderImpl());

		SingleVMPoolImpl singleVMPoolImpl = new SingleVMPoolImpl();

		singleVMPoolImpl.setPortalCacheManager(
			MemoryPortalCacheManager.createMemoryPortalCacheManager(
				TemplatePortletPreferencesTest.class.getName()));

		SingleVMPoolUtil singleVMPoolUtil = new SingleVMPoolUtil();

		singleVMPoolUtil.setSingleVMPool(singleVMPoolImpl);
	}

	@Before
	public void setUp() throws Exception {
		_executorService = Executors.newFixedThreadPool(_NUM_THREADS);

		_templatePortletPreferences = new TemplatePortletPreferences();
	}

	@After
	public void tearDown() {
		_executorService.shutdownNow();
	}

	@Test
	public void testSetValue() throws Exception {
		Callable<String> callable = new TemplateCallable();

		List<Future<String>> futures = new ArrayList<>(_NUM_THREADS);

		for (int i = 0; i < _NUM_THREADS; i++) {
			futures.add(_executorService.submit(callable));
		}

		for (Future<String> future : futures) {
			String xml = future.get();

			PortletPreferencesImpl portletPreferencesImpl =
				(PortletPreferencesImpl)PortletPreferencesFactoryUtil.
					fromDefaultXML(xml);

			Map<String, String[]> map = portletPreferencesImpl.getMap();

			Assert.assertEquals(1, map.size());
		}
	}

	private static final int _NUM_THREADS =
		Runtime.getRuntime().availableProcessors();

	private ExecutorService _executorService;
	private TemplatePortletPreferences _templatePortletPreferences;

	private class TemplateCallable implements Callable<String> {

		@Override
		public String call() throws Exception {
			String randomString = RandomTestUtil.randomString(8);

			// Synchronization was necessary here in order to make the scenario
			// deterministically fail. Otherwise there would be only statistical
			// ways to prove that the fix indeed eliminates the race condition.

			synchronized (_templatePortletPreferences) {
				_templatePortletPreferences.setValue(
					randomString, randomString);
			}

			String xml = _templatePortletPreferences.toString();

			_templatePortletPreferences.reset();

			return xml;
		}

	}

}