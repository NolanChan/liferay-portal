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

package com.liferay.portal.configuration.persistence.test;

import com.liferay.portal.configuration.persistence.ConfigurationPersistenceManager;
import com.liferay.portal.kernel.test.rule.Sync;

import java.io.IOException;

import java.util.Dictionary;
import java.util.Hashtable;

import org.apache.felix.cm.PersistenceManager;

import org.jboss.arquillian.junit.Arquillian;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.util.tracker.ServiceTracker;

/**
 * @author Raymond Augé
 */
@RunWith(Arquillian.class)
@Sync
public class ConfigurationPersistenceManagerTest {

	@Before
	public void setUp() throws Exception {
		Bundle bundle = FrameworkUtil.getBundle(
			ConfigurationPersistenceManagerTest.class);

		BundleContext bundleContext = bundle.getBundleContext();

		_configurationAdminServiceTracker = new ServiceTracker<>(
			bundleContext, ConfigurationAdmin.class, null);

		_configurationAdminServiceTracker.open();

		_configurationAdmin =
			_configurationAdminServiceTracker.waitForService(5000);

		_persistenceManagerServiceTracker = new ServiceTracker<>(
			bundleContext, PersistenceManager.class, null);

		_persistenceManagerServiceTracker.open();

		_persistenceManager =
			_persistenceManagerServiceTracker.waitForService(5000);
	}

	@After
	public void tearDown() throws Exception {
		_configurationAdminServiceTracker.close();

		_persistenceManagerServiceTracker.close();
	}

	@Test
	public void testExists() throws Exception {
		Configuration configuration = _configurationAdmin.getConfiguration(
			"test.pid");

		Assert.assertTrue(_persistenceManager.exists("test.pid"));

		configuration.delete();

		Assert.assertFalse(_persistenceManager.exists("test.pid"));
	}

	@Test
	public void testConfigurationPersistenceManager() throws Exception {
		Assert.assertEquals(
			ConfigurationPersistenceManager.class,
			_persistenceManager.getClass());
	}

	@Test
	public void testGetConfiguration() throws Exception {
		Configuration configuration = _configurationAdmin.getConfiguration(
			"test.pid");

		assertConfiguration(configuration);
	}

	@Test
	public void testCreateFactoryConfiguration() throws Exception {
		Configuration configuration =
			_configurationAdmin.createFactoryConfiguration("test.pid");

		assertConfiguration(configuration);
	}

	protected void assertConfiguration(Configuration configuration)
		throws IOException {

		String pid = configuration.getPid();

		Dictionary<String, Object> properties = new Hashtable<>();

		properties.put("foo", "bar");

		configuration.update(properties);

		Assert.assertTrue(_persistenceManager.exists(pid));

		properties = _persistenceManager.load(pid);

		Assert.assertEquals("bar", properties.get("foo"));

		properties.put("fee", "fum");

		configuration.update(properties);

		properties = _persistenceManager.load(pid);

		Assert.assertEquals("bar", properties.get("foo"));
		Assert.assertEquals("fum", properties.get("fee"));

		configuration.delete();

		Assert.assertFalse(_persistenceManager.exists(pid));
	}

	private ServiceTracker<ConfigurationAdmin, ConfigurationAdmin>
		_configurationAdminServiceTracker;
	private ConfigurationAdmin _configurationAdmin;
	private PersistenceManager _persistenceManager;
	private ServiceTracker<PersistenceManager, PersistenceManager>
		_persistenceManagerServiceTracker;

}