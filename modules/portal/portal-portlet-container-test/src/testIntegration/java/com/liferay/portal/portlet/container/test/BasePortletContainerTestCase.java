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

package com.liferay.portal.portlet.container.test;

import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.HashMapDictionary;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.util.test.LayoutTestUtil;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.portlet.Portlet;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceRegistration;

/**
 * @author Raymond Augé
 */
public class BasePortletContainerTestCase {

	@Before
	public void setUp() throws Exception {
		group = GroupTestUtil.addGroup();

		layout = LayoutTestUtil.addLayout(group);

		PermissionChecker permissionChecker =
			PermissionCheckerFactoryUtil.create(TestPropsValues.getUser());

		PermissionThreadLocal.setPermissionChecker(permissionChecker);

		testPortlet = new TestPortlet(map);
	}

	@After
	public void tearDown() throws Exception {
		PermissionThreadLocal.setPermissionChecker(null);

		for (ServiceRegistration<?> serviceRegistration :
				serviceRegistrations) {

			serviceRegistration.unregister();
		}

		serviceRegistrations.clear();
	}

	protected void setUpPortlet(
			Portlet portlet, Dictionary<String, Object> properties,
			String portletName)
		throws Exception {

		setUpPortlet(portlet, properties, portletName, true);
	}

	protected void setUpPortlet(
			Portlet portlet, Dictionary<String, Object> properties,
			String portletName, boolean addToLayout)
		throws Exception {

		Assert.assertNotNull(properties);

		properties.put("javax.portlet.name", portletName);

		Bundle bundle = FrameworkUtil.getBundle(getClass());

		BundleContext bundleContext = bundle.getBundleContext();

		serviceRegistrations.add(
			bundleContext.registerService(
				new String[] {Object.class.getName(), Portlet.class.getName()},
				portlet, properties));

		if (addToLayout) {
			LayoutTestUtil.addPortletToLayout(
				TestPropsValues.getUserId(), layout, portletName, "column-1",
				new HashMap<String, String[]>());
		}
	}

	protected static final String TEST_PORTLET_ID = "TEST_PORTLET_ID";

	@DeleteAfterTestRun
	protected Group group;

	protected Layout layout;
	protected Map<String, String> map = new ConcurrentHashMap<>();
	protected Dictionary<String, Object> properties = new HashMapDictionary<>();
	protected List<ServiceRegistration<?>> serviceRegistrations =
		new CopyOnWriteArrayList<>();
	protected TestPortlet testPortlet;

}