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

package com.liferay.exportimport.resources.importer.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutTypePortlet;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.net.URL;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceRegistration;

/**
 * @author Daniel Kocsis
 */
@RunWith(Arquillian.class)
public class ResourcesImporterTest {

	public static final String TEST_CONTEXT_NAME = "test-resource-importer";

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		Bundle testBundle = FrameworkUtil.getBundle(
			ResourcesImporterTest.class);

		_bundleContext = testBundle.getBundleContext();

		URL resource = ResourcesImporterTest.class.getResource(
			"dependencies/test.war");

		resource = new URL(
			resource.toExternalForm() + "?Web-ContextPath=/" +
				TEST_CONTEXT_NAME);

		URL url = new URL("webbundle", null, resource.toString());

		_wabBundle = _bundleContext.installBundle(url.toString());

		Dictionary<String, Object> properties = new Hashtable<>();

		properties.put("destination.name", "liferay/resources_importer");

		final CountDownLatch countDownLatch = new CountDownLatch(1);

		ServiceRegistration<MessageListener> serviceRegistration =
			_bundleContext.registerService(
				MessageListener.class,
				new MessageListener() {

					public void receive(Message message)
						throws MessageListenerException {

						countDownLatch.countDown();
					}

				},
				properties);

		_wabBundle.start();

		countDownLatch.await(1, TimeUnit.MINUTES);

		serviceRegistration.unregister();
	}

	@After
	public void tearDown() throws Exception {
		if (_wabBundle != null) {
			_wabBundle.uninstall();
		}
	}

	@Test
	public void testImportResourcesWAR() throws Exception {
		_importedGroup = GroupLocalServiceUtil.fetchGroup(
			TestPropsValues.getCompanyId(), "ResourcesImporterTest");

		// Verify the deployment

		Assert.assertNotNull(_importedGroup);

		long privateLayoutsCount = LayoutLocalServiceUtil.getLayoutsCount(
			_importedGroup, true);

		Assert.assertEquals(0, privateLayoutsCount);

		long publicLayoutsCount = LayoutLocalServiceUtil.getLayoutsCount(
			_importedGroup, false);

		Assert.assertEquals(4, publicLayoutsCount);

		long journalArticlesCount =
			JournalArticleLocalServiceUtil.getArticlesCount(
				_importedGroup.getGroupId());

		Assert.assertEquals(2, journalArticlesCount);

		long fileEntriesCount = DLFileEntryLocalServiceUtil.getFileEntriesCount(
			_importedGroup.getGroupId(),
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID);

		Assert.assertEquals(2, fileEntriesCount);

		Layout homeLayout = LayoutLocalServiceUtil.getFriendlyURLLayout(
			_importedGroup.getGroupId(), false, "/home");

		Assert.assertTrue(homeLayout.isTypePortlet());

		LayoutTypePortlet layoutTypePortlet =
			(LayoutTypePortlet)homeLayout.getLayoutType();

		List<Portlet> portlets = layoutTypePortlet.getAllPortlets();

		Assert.assertEquals(2, portlets.size());
	}

	private BundleContext _bundleContext;

	@DeleteAfterTestRun
	private Group _importedGroup;

	private Bundle _wabBundle;

}