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

package com.liferay.portal.servlet;

import static org.mockito.Mockito.verify;

import com.liferay.portal.cache.SingleVMPoolImpl;
import com.liferay.portal.cache.memory.MemoryPortalCacheManager;
import com.liferay.portal.kernel.cache.SingleVMPoolUtil;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.PortletApp;
import com.liferay.portal.service.PortletLocalService;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.util.PortletKeys;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import java.net.URI;
import java.net.URL;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;

import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.stubbing.answers.CallsRealMethods;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import org.springframework.mock.web.MockServletConfig;
import org.springframework.mock.web.MockServletContext;

/**
 * @author Carlos Sierra Andrés
 * @author Raymond Augé
 */
@PrepareForTest({PortletLocalServiceUtil.class})
@RunWith(PowerMockRunner.class)
public class ComboServletTest extends PowerMockito {

	@BeforeClass
	public static void setUpClass() throws Exception {
		MemoryPortalCacheManager<Serializable, Serializable>
			memoryPortalCacheManager =
				new MemoryPortalCacheManager<Serializable, Serializable>();

		memoryPortalCacheManager.afterPropertiesSet();

		SingleVMPoolImpl singleVMPoolImpl = new SingleVMPoolImpl();

		singleVMPoolImpl.setPortalCacheManager(memoryPortalCacheManager);

		SingleVMPoolUtil singleVMPoolUtil = new SingleVMPoolUtil();

		singleVMPoolUtil.setSingleVMPool(singleVMPoolImpl);
	}

	@Before
	public void setUp() throws IOException, ServletException {
		MockitoAnnotations.initMocks(this);

		when(
			_portletLocalService.getPortletById(
				Matchers.anyString()
			)
		).thenAnswer(
			new Answer<Portlet>() {

				@Override
				public Portlet answer(InvocationOnMock invocation)
					throws Throwable {

					Object[] args = invocation.getArguments();

					if ("75".equals(args[0])) {
						return _75Portlet;
					}
					else if (PortletKeys.PORTAL.equals(args[0])) {
						return _portletPortal;
					}

					return _portletUndeployed;
				}

			}
		);

		mockStatic(PortletLocalServiceUtil.class, new CallsRealMethods());

		stub(
			method(PortletLocalServiceUtil.class, "getService")
		).toReturn(
			_portletLocalService
		);

		_comboServlet = new ComboServlet();

		_portalServletContext = spy(new MockServletContext());

		ServletConfig servletConfig = new MockServletConfig(
			_portalServletContext);

		_portalServletContext.setContextPath("portal");

		File tempFile = _temporaryFolder.newFile();

		URI tempFileURI = tempFile.toURI();

		when(
			_portalServletContext.getResource(Mockito.anyString())
		).thenReturn(
			tempFileURI.toURL()
		);

		when(
			_portletAppPortal.getServletContext()
		).thenReturn(
			_portalServletContext
		);

		when(
			_portletPortal.getPortletApp()
		).thenReturn(
			_portletAppPortal
		);

		when(
			_portletPortal.getRootPortletId()
		).thenReturn(
			PortletKeys.PORTAL
		);

		_comboServlet.init(servletConfig);

		_pluginServletContext = spy(new MockServletContext());

		when(
			_pluginServletContext.getResource(Mockito.anyString())
		).thenReturn(
			tempFileURI.toURL()
		);

		when(
			_75PortletApp.getServletContext()
		).thenReturn(
			_pluginServletContext
		);

		when(
			_75Portlet.getPortletApp()
		).thenReturn(
			_75PortletApp
		);

		when(
			_75Portlet.getRootPortletId()
		).thenReturn(
			"75"
		);

		when(
			_portletUndeployed.isUndeployedPortlet()
		).thenReturn(
			true
		);
	}

	@Test
	public void testGetResourceWithNonexistingPortletId() throws Exception {
		URL url = _comboServlet.getResourceURL("2345678:/js/javascript.js");

		Assert.assertNull(url);
	}

	@Test
	public void testGetResourceWithPortletId() throws Exception {
		_comboServlet.getResourceURL("75:/js/javascript.js");

		verify(_pluginServletContext);

		_pluginServletContext.getResource("/js/javascript.js");
	}

	@Test
	public void testGetResourceWithoutPortletId() throws Exception {
		String path = "/js/javascript.js";

		_comboServlet.getResourceURL("/js/javascript.js");

		verify(_portalServletContext);

		_portalServletContext.getResource(path);
	}

	private ComboServlet _comboServlet;
	private MockServletContext _pluginServletContext;
	private MockServletContext _portalServletContext;

	@Mock
	private Portlet _portletPortal;

	@Mock
	private Portlet _75Portlet;

	@Mock
	private Portlet _portletUndeployed;

	@Mock
	private PortletApp _portletAppPortal;

	@Mock
	private PortletApp _75PortletApp;

	@Mock
	private PortletLocalService _portletLocalService;

	@Rule
	private TemporaryFolder _temporaryFolder = new TemporaryFolder();

}