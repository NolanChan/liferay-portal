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

package com.liferay.portal.cache.ehcache;

import com.liferay.portal.cache.cluster.EhcachePortalCacheClusterReplicatorFactory;
import com.liferay.portal.kernel.test.CodeCoverageAssertor;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.test.AdviseWith;
import com.liferay.portal.test.AspectJMockingNewClassLoaderJUnitTestRunner;

import java.io.File;

import java.net.URI;
import java.net.URL;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.Configuration;
import net.sf.ehcache.config.ConfigurationFactory;
import net.sf.ehcache.config.FactoryConfiguration;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Tina Tian
 */
@RunWith(AspectJMockingNewClassLoaderJUnitTestRunner.class)
public class EhcacheConfigurationUtilTest {

	@ClassRule
	public static CodeCoverageAssertor codeCoverageAssertor =
		new CodeCoverageAssertor();

	@Before
	public void setUp() {
		File file = new File(
			"portal-impl/test/unit/com/liferay/portal/cache/ehcache/" +
				"test-multi-ehcache-config.xml");

		try {
			URI uri = file.toURI();

			_configurationURL = uri.toURL();
		}
		catch (Exception e) {
			Assert.fail();
		}

		_configuration = ConfigurationFactory.parseConfiguration(
			_configurationURL);
	}

	@Test
	public void testMisc() {
		Configuration configuration = EhcacheConfigurationUtil.getConfiguration(
			"WrongConfigurationPath");

		Assert.assertNull(configuration);

		configuration = EhcacheConfigurationUtil.getConfiguration(
			StringPool.BLANK, true);

		Assert.assertNull(configuration);

		configuration = EhcacheConfigurationUtil.getConfiguration(
			StringPool.BLANK, true, true);

		Assert.assertNull(configuration);

		File file = new File(
			"portal-impl/test/unit/com/liferay/portal/cache/ehcache/" +
				"test-single-ehcache-config.xml");

		URL configurationURL = null;

		try {
			URI uri = file.toURI();

			configurationURL = uri.toURL();
		}
		catch (Exception e) {
			Assert.fail();
		}

		configuration = EhcacheConfigurationUtil.getConfiguration(
			configurationURL);

		Assert.assertNotNull(configuration);

		configuration = EhcacheConfigurationUtil.getConfiguration(
			configurationURL, true);

		Assert.assertNotNull(configuration);

		// For code coverage

		new EhcacheConfigurationUtil();
	}

	@AdviseWith(adviceClasses = {DisableEhcacheBootStrapAdvice.class})
	@Test
	public void testWithBootStrapDisabled() {
		Configuration configuration1 =
			EhcacheConfigurationUtil.getConfiguration(
				_configurationURL, false, false);
		Configuration configuration2 =
			EhcacheConfigurationUtil.getConfiguration(
				_configurationURL, false, true);
		Configuration configuration3 =
			EhcacheConfigurationUtil.getConfiguration(
				_configurationURL, true, false);
		Configuration configuration4 =
			EhcacheConfigurationUtil.getConfiguration(
				_configurationURL, true, true);

		_assertBootStrap(configuration1, false);
		_assertBootStrap(configuration2, false);
		_assertBootStrap(configuration3, false);
		_assertBootStrap(configuration4, false);
	}

	@AdviseWith(adviceClasses = {EnableEhcacheBootStrapAdvice.class})
	@Test
	public void testWithBootStrapEnabled() {
		Configuration configuration1 =
			EhcacheConfigurationUtil.getConfiguration(
				_configurationURL, false, false);
		Configuration configuration2 =
			EhcacheConfigurationUtil.getConfiguration(
				_configurationURL, false, true);
		Configuration configuration3 =
			EhcacheConfigurationUtil.getConfiguration(
				_configurationURL, true, false);
		Configuration configuration4 =
			EhcacheConfigurationUtil.getConfiguration(
				_configurationURL, true, true);

		_assertBootStrap(configuration1, true);
		_assertBootStrap(configuration2, true);
		_assertBootStrap(configuration3, true);
		_assertBootStrap(configuration4, true);
	}

	@AdviseWith(adviceClasses = {DisableClusterLinkAdvice.class})
	@Test
	public void testWithClusterDisabled() {
		Configuration configuration1 =
			EhcacheConfigurationUtil.getConfiguration(
				_configurationURL, false, false);
		Configuration configuration2 =
			EhcacheConfigurationUtil.getConfiguration(
				_configurationURL, false, true);
		Configuration configuration3 =
			EhcacheConfigurationUtil.getConfiguration(
				_configurationURL, true, false);
		Configuration configuration4 =
			EhcacheConfigurationUtil.getConfiguration(
				_configurationURL, true, true);

		_assertListenerConfigsEquals(_configuration, configuration1);
		_assertListenerConfigsEquals(_configuration, configuration2);
		_assertNoDefaultReplicatorConfigs(configuration3);
		_assertNoListenerConfigs(configuration4);
	}

	@AdviseWith(
		adviceClasses = {
			EnableClusterLinkAdvice.class,
			DisableClusterLinkReplicateAdvice.class
		}
	)
	@Test
	public void testWithClusterEnabled1() {
		Configuration configuration1 =
			EhcacheConfigurationUtil.getConfiguration(
				_configurationURL, false, false);
		Configuration configuration2 =
			EhcacheConfigurationUtil.getConfiguration(
				_configurationURL, false, true);
		Configuration configuration3 =
			EhcacheConfigurationUtil.getConfiguration(
				_configurationURL, true, false);
		Configuration configuration4 =
			EhcacheConfigurationUtil.getConfiguration(
				_configurationURL, true, true);

		_assertListenerConfigsEquals(_configuration, configuration1);
		_assertListenerConfigsEquals(_configuration, configuration2);
		_assertListenerConfigsEquals(_configuration, configuration3);
		_assertListenerConfigsEquals(_configuration, configuration4);
	}

	@AdviseWith(
		adviceClasses = {
			EnableClusterLinkAdvice.class,
			EnableClusterLinkReplicateAdvice.class
		}
	)
	@Test
	public void testWithClusterEnabled2() {
		Configuration configuration1 =
			EhcacheConfigurationUtil.getConfiguration(
				_configurationURL, false, false);
		Configuration configuration2 =
			EhcacheConfigurationUtil.getConfiguration(
				_configurationURL, false, true);
		Configuration configuration3 =
			EhcacheConfigurationUtil.getConfiguration(
				_configurationURL, true, false);
		Configuration configuration4 =
			EhcacheConfigurationUtil.getConfiguration(
				_configurationURL, true, true);

		_assertListenerConfigsEquals(_configuration, configuration1);
		_assertListenerConfigsEquals(_configuration, configuration2);
		_assertClusterLinkReplicatorConfigs(configuration3, false);
		_assertClusterLinkReplicatorConfigs(configuration4, true);
	}

	@Aspect
	public static class DisableClusterLinkAdvice {

		@Around(
			"set(* com.liferay.portal.util.PropsValues.CLUSTER_LINK_ENABLED)")
		public Object disableClusterLink(
				ProceedingJoinPoint proceedingJoinPoint)
			throws Throwable {

			return proceedingJoinPoint.proceed(new Object[] {Boolean.FALSE});
		}

	}

	@Aspect
	public static class DisableClusterLinkReplicateAdvice {

		@Around(
			"set(* com.liferay.portal.util.PropsValues." +
				"EHCACHE_CLUSTER_LINK_REPLICATION_ENABLED)")
		public Object disableClusterLinkReplicate(
				ProceedingJoinPoint proceedingJoinPoint)
			throws Throwable {

			return proceedingJoinPoint.proceed(new Object[] {Boolean.FALSE});
		}

	}

	@Aspect
	public static class DisableEhcacheBootStrapAdvice {

		@Around(
			"set(* com.liferay.portal.util.PropsValues." +
				"EHCACHE_BOOTSTRAP_CACHE_LOADER_ENABLED)")
		public Object disableEhcacheBootStrap(
				ProceedingJoinPoint proceedingJoinPoint)
			throws Throwable {

			return proceedingJoinPoint.proceed(new Object[] {Boolean.FALSE});
		}

	}

	@Aspect
	public static class EnableClusterLinkAdvice {

		@Around(
			"set(* com.liferay.portal.util.PropsValues.CLUSTER_LINK_ENABLED)")
		public Object enableClusterLink(ProceedingJoinPoint proceedingJoinPoint)
			throws Throwable {

			return proceedingJoinPoint.proceed(new Object[] {Boolean.TRUE});
		}

	}

	@Aspect
	public static class EnableClusterLinkReplicateAdvice {

		@Around(
			"set(* com.liferay.portal.util.PropsValues." +
				"EHCACHE_CLUSTER_LINK_REPLICATION_ENABLED)")
		public Object enableClusterLinkReplicate(
				ProceedingJoinPoint proceedingJoinPoint)
			throws Throwable {

			return proceedingJoinPoint.proceed(new Object[] {Boolean.TRUE});
		}

	}

	@Aspect
	public static class EnableEhcacheBootStrapAdvice {

		@Around(
			"set(* com.liferay.portal.util.PropsValues." +
				"EHCACHE_BOOTSTRAP_CACHE_LOADER_ENABLED)")
		public Object enableEhcacheBootstrap(
				ProceedingJoinPoint proceedingJoinPoint)
			throws Throwable {

			return proceedingJoinPoint.proceed(new Object[] {Boolean.TRUE});
		}

	}

	private void _assertBootStrap(
		Configuration configuration, boolean enabled) {

		List<CacheConfiguration> cacheConfigurations =
			_getAllCacheConfigurations(configuration);

		for (CacheConfiguration cacheConfiguration : cacheConfigurations) {
			if (enabled) {
				Assert.assertNotNull(
					cacheConfiguration.
						getBootstrapCacheLoaderFactoryConfiguration());
			}
			else {
				Assert.assertNull(
					cacheConfiguration.
						getBootstrapCacheLoaderFactoryConfiguration());
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void _assertClusterLinkReplicatorConfigs(
		Configuration configuration, boolean onlyReplicator) {

		List<?> peerListenerFactoryConfigurations =
			configuration.getCacheManagerPeerListenerFactoryConfigurations();
		List<?> peerProviderFactoryConfigurations =
			configuration.getCacheManagerPeerProviderFactoryConfiguration();

		Assert.assertTrue(peerListenerFactoryConfigurations.isEmpty());
		Assert.assertTrue(peerProviderFactoryConfigurations.isEmpty());

		List<CacheConfiguration> cacheConfigurations =
			_getAllCacheConfigurations(configuration);

		for (CacheConfiguration cacheConfiguration : cacheConfigurations) {
			List<FactoryConfiguration<?>> factoryConfigurations =
				(List<FactoryConfiguration<?>>)
					cacheConfiguration.getCacheEventListenerConfigurations();

			if (onlyReplicator) {
				Assert.assertEquals(1, factoryConfigurations.size());
			}
			else {
				Assert.assertTrue(factoryConfigurations.size() > 1);
			}

			boolean exist = false;

			for (FactoryConfiguration<?> factoryConfiguration :
					factoryConfigurations) {

				String fullyQualifiedClassPath =
					factoryConfiguration. getFullyQualifiedClassPath();

				if (fullyQualifiedClassPath.equals(_CLUSTER_LINK_REPLICATOR)) {
					Assert.assertEquals(
						_CACHE_EVENT_LISTENER_PROPERTIES,
						factoryConfiguration.getProperties());

					exist = true;

					break;
				}
			}

			if (!exist) {
				Assert.fail();
			}
		}
	}

	private void _assertListenerConfigsEquals(
		Configuration configuration1, Configuration configuration2) {

		Assert.assertEquals(
			configuration1.getCacheManagerPeerProviderFactoryConfiguration(),
			configuration2.getCacheManagerPeerProviderFactoryConfiguration());

		Assert.assertEquals(
			configuration1.getCacheManagerPeerListenerFactoryConfigurations(),
			configuration2.getCacheManagerPeerListenerFactoryConfigurations());

		CacheConfiguration cacheConfiguration1 =
			configuration1.getDefaultCacheConfiguration();
		CacheConfiguration cacheConfiguration2 =
			configuration2.getDefaultCacheConfiguration();

		Assert.assertEquals(
			cacheConfiguration1.getCacheEventListenerConfigurations(),
			cacheConfiguration2.getCacheEventListenerConfigurations());

		_assertListenerConfigsEquals(
			configuration1.getCacheConfigurations(),
			configuration1.getCacheConfigurations());
	}

	private void _assertListenerConfigsEquals(
		Map<String, CacheConfiguration> cacheConfigurations1,
		Map<String, CacheConfiguration> cacheConfigurations2) {

		if (cacheConfigurations1 == cacheConfigurations2) {
			return;
		}

		Assert.assertEquals(
			cacheConfigurations1.size(), cacheConfigurations1.size());

		if (cacheConfigurations1.isEmpty()) {
			return;
		}

		try {
			Iterator<Entry<String, CacheConfiguration>> iterator =
				cacheConfigurations1.entrySet().iterator();

			while (iterator.hasNext()) {
				Entry<String, CacheConfiguration> entry = iterator.next();

				String key = entry.getKey();
				CacheConfiguration value1 = entry.getValue();

				CacheConfiguration value2 = cacheConfigurations2.get(key);

				if (value1 == null) {
					if ((value2 != null) ||
						cacheConfigurations2.containsKey(key)) {

						Assert.fail();
					}
				}
				else {
					Assert.assertEquals(
						value1.getCacheEventListenerConfigurations(),
						value2.getCacheEventListenerConfigurations());
				}
			}
		}
		catch (Exception e) {
			Assert.fail();
		}
	}

	@SuppressWarnings("unchecked")
	private void _assertNoDefaultReplicatorConfigs(
		Configuration configuration) {

		List<?> peerListenerFactoryConfigurations =
			configuration.getCacheManagerPeerListenerFactoryConfigurations();
		List<?> peerProviderFactoryConfigurations =
			configuration.getCacheManagerPeerProviderFactoryConfiguration();

		Assert.assertTrue(peerListenerFactoryConfigurations.isEmpty());
		Assert.assertTrue(peerProviderFactoryConfigurations.isEmpty());

		List<CacheConfiguration> cacheConfigurations =
			_getAllCacheConfigurations(configuration);

		for (CacheConfiguration cacheConfiguration : cacheConfigurations) {
			List<FactoryConfiguration<?>> factoryConfigurations =
				(List<FactoryConfiguration<?>>)
					cacheConfiguration.getCacheEventListenerConfigurations();

			for (FactoryConfiguration<?> factoryConfiguration :
					factoryConfigurations) {

				String fullyQualifiedClassPath =
					factoryConfiguration.getFullyQualifiedClassPath();

				if (fullyQualifiedClassPath.contains(
						"LiferayCacheEventListenerFactory") ||
					fullyQualifiedClassPath.contains(
						"net.sf.ehcache.distribution")) {

					Assert.fail();
				}
			}
		}
	}

	private void _assertNoListenerConfigs(Configuration configuration) {
		List<?> peerListenerFactoryConfigurations =
			configuration.getCacheManagerPeerListenerFactoryConfigurations();
		List<?> peerProviderFactoryConfigurations =
			configuration.getCacheManagerPeerProviderFactoryConfiguration();

		Assert.assertTrue(peerListenerFactoryConfigurations.isEmpty());
		Assert.assertTrue(peerProviderFactoryConfigurations.isEmpty());

		List<CacheConfiguration> cacheConfigurations =
			_getAllCacheConfigurations(configuration);

		for (CacheConfiguration cacheConfiguration : cacheConfigurations) {
			List<?> cacheEventListenerConfigurations =
				cacheConfiguration.getCacheEventListenerConfigurations();

			Assert.assertTrue(cacheEventListenerConfigurations.isEmpty());
		}
	}

	private List<CacheConfiguration> _getAllCacheConfigurations(
		Configuration configuration) {

		List<CacheConfiguration> cacheConfigurations =
			new ArrayList<CacheConfiguration>();

		CacheConfiguration defaultCacheConfiguration =
			configuration.getDefaultCacheConfiguration();

		if (defaultCacheConfiguration != null) {
			cacheConfigurations.add(defaultCacheConfiguration);
		}

		Map<String, CacheConfiguration> cacheConfigurationsMap =
			configuration.getCacheConfigurations();

		for (CacheConfiguration cacheConfiguration :
				cacheConfigurationsMap.values()) {

			cacheConfigurations.add(cacheConfiguration);
		}

		return cacheConfigurations;
	}

	private static final String _CACHE_EVENT_LISTENER_PROPERTIES =
		"testKey=testValue";

	private static final String _CLUSTER_LINK_REPLICATOR =
		EhcachePortalCacheClusterReplicatorFactory.class.getName();

	private Configuration _configuration;
	private URL _configurationURL;

}