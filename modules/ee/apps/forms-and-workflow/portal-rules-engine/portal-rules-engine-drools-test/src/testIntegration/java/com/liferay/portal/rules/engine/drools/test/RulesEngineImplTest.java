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

package com.liferay.portal.rules.engine.drools.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.resource.StringResourceRetriever;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.rules.engine.Fact;
import com.liferay.portal.rules.engine.Query;
import com.liferay.portal.rules.engine.RulesEngine;
import com.liferay.portal.rules.engine.RulesResourceRetriever;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import org.osgi.framework.ServiceReference;

/**
 * @author Michael C. Han
 * @author Brian Wing Shun Chan
 */
@RunWith(Arquillian.class)
public class RulesEngineImplTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		Bundle bundle = FrameworkUtil.getBundle(getClass());

		_bundleContext = bundle.getBundleContext();

		int count = 0;

		do {
			Collection<ServiceReference<RulesEngine>> serviceReferences =
				_bundleContext.getServiceReferences(
					RulesEngine.class, "(proxy.bean=false)");

			if (serviceReferences.isEmpty()) {
				count++;

				if (count >= 5) {
					throw new IllegalStateException(
						"Unable to a reference to a rules engine");
				}

				Thread.sleep(500);
			}

			Iterator<ServiceReference<RulesEngine>> iterator =
				serviceReferences.iterator();

			_serviceReference = iterator.next();
		}
		while (_serviceReference == null);

		_rulesEngine = _bundleContext.getService(_serviceReference);

		String rules = read("test.drl");

		_rulesResourceRetriever = new RulesResourceRetriever(
			new StringResourceRetriever(rules));
	}

	@After
	public void tearDown() throws Exception {
		_bundleContext.ungetService(_serviceReference);

		_bundleContext = null;
	}

	@Test
	public void testAdd() throws Exception {
		_rulesEngine.add("testDomainName", _rulesResourceRetriever);

		Assert.assertTrue(_rulesEngine.containsRuleDomain("testDomainName"));
	}

	@Test
	public void testExecuteWithPrecompiledRuleAge30() throws Exception {
		UserProfile userProfile = new UserProfile();

		userProfile.setAge(18);

		List<Fact<?>> facts = new ArrayList<>();

		facts.add(new Fact<UserProfile>("userProfile", userProfile));

		_rulesEngine.add("testDomainName", _rulesResourceRetriever);

		_rulesEngine.execute("testDomainName", facts);

		Assert.assertEquals(30, userProfile.getAge());
	}

	@Test
	public void testExecuteWithPrecompiledRuleAge50() throws Exception {
		_rulesEngine.add("testDomainName", _rulesResourceRetriever);

		UserProfile userProfile = new UserProfile();

		userProfile.setAge(50);

		List<Fact<?>> facts = new ArrayList<>();

		facts.add(new Fact<UserProfile>("userProfile", userProfile));

		_rulesEngine.execute("testDomainName", facts);

		Assert.assertEquals(50, userProfile.getAge());
	}

	@Test
	public void testExecuteWithResultsWithTemporaryRuleAge30()
		throws Exception {

		UserProfile userProfile = new UserProfile();

		userProfile.setAge(18);

		List<Fact<?>> facts = new ArrayList<>();

		facts.add(new Fact<UserProfile>("userProfile", userProfile));

		Map<String, ?> results = _rulesEngine.execute(
			_rulesResourceRetriever, facts, Query.createStandardQuery());

		Assert.assertEquals(1, results.size());

		userProfile = (UserProfile)results.get("userProfile");

		Assert.assertEquals(30, userProfile.getAge());
	}

	@Test
	public void testExecuteWithResultsWithTemporaryRuleAge50()
		throws Exception {

		UserProfile userProfile = new UserProfile();

		userProfile.setAge(50);

		List<Fact<?>> facts = new ArrayList<>();

		facts.add(new Fact<UserProfile>("userProfile", userProfile));

		Map<String, ?> results = _rulesEngine.execute(
			_rulesResourceRetriever, facts, Query.createStandardQuery());

		Assert.assertEquals(1, results.size());

		userProfile = (UserProfile)results.get("userProfile");

		Assert.assertEquals(50, userProfile.getAge());
	}

	@Test
	public void testExecuteWithTemporaryRuleAge30() throws Exception {
		UserProfile userProfile = new UserProfile();

		userProfile.setAge(18);

		List<Fact<?>> facts = new ArrayList<>();

		facts.add(new Fact<UserProfile>("userProfile", userProfile));

		_rulesEngine.execute(_rulesResourceRetriever, facts);

		Assert.assertEquals(30, userProfile.getAge());
	}

	@Test
	public void testExecuteWithTemporaryRuleAge50() throws Exception {
		UserProfile userProfile = new UserProfile();

		userProfile.setAge(50);

		List<Fact<?>> facts = new ArrayList<>();

		facts.add(new Fact<UserProfile>("userProfile", userProfile));

		_rulesEngine.execute(_rulesResourceRetriever, facts);

		Assert.assertEquals(50, userProfile.getAge());
	}

	protected String read(String fileName) throws Exception {
		Class<?> clazz = getClass();

		return StringUtil.read(
			clazz.getClassLoader(),
			"com/liferay/portal/rules/engine/drools/dependencies/" + fileName);
	}

	private BundleContext _bundleContext;
	private RulesEngine _rulesEngine;
	private RulesResourceRetriever _rulesResourceRetriever;
	private ServiceReference<RulesEngine> _serviceReference;

}