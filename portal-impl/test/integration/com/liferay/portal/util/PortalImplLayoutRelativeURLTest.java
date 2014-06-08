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

package com.liferay.portal.util;

import com.liferay.portal.NoSuchLayoutException;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.model.LayoutSet;
import com.liferay.portal.service.VirtualHostLocalServiceUtil;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.MainServletExecutionTestListener;
import com.liferay.portal.test.ResetDatabaseExecutionTestListener;
import com.liferay.portal.theme.ThemeDisplay;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.mock.web.MockHttpServletRequest;

/**
 * @author Akos Thurzo
 */
@ExecutionTestListeners(
	listeners = {
		MainServletExecutionTestListener.class,
		ResetDatabaseExecutionTestListener.class
	})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class PortalImplLayoutRelativeURLTest extends PortalImplBaseURLTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		LayoutSet publicLayoutSet = publicLayout.getLayoutSet();

		VirtualHostLocalServiceUtil.updateVirtualHost(
			company.getCompanyId(), publicLayoutSet.getLayoutSetId(),
			VIRTUAL_HOSTNAME);

		privateLayoutRelativeURL =
			PropsValues.LAYOUT_FRIENDLY_URL_PRIVATE_GROUP_SERVLET_MAPPING +
				group.getFriendlyURL() + privateLayout.getFriendlyURL();
		publicLayoutRelativeURL =
			PropsValues.LAYOUT_FRIENDLY_URL_PUBLIC_SERVLET_MAPPING +
				group.getFriendlyURL() + publicLayout.getFriendlyURL();
	}

	@Test(expected = NoSuchLayoutException.class)
	public void testPrivateLayoutFromCompanyVirtualHostRefererPlidNotExists()
		throws Exception {

		ThemeDisplay themeDisplay = initThemeDisplay(
			company, group, privateLayout, LOCALHOST);

		addParameterToThemeDisplayRequest(themeDisplay, "refererPlid", "1");

		PortalUtil.getLayoutRelativeURL(privateLayout, themeDisplay);

		Assert.fail("An exception should have been thrown");
	}

	@Test
	public void testPrivateLayoutFromCompanyVirtualHost() throws Exception {
		ThemeDisplay themeDisplay = initThemeDisplay(
			company, group, privateLayout, LOCALHOST);

		Assert.assertEquals(
			privateLayoutRelativeURL,
			PortalUtil.getLayoutRelativeURL(privateLayout, themeDisplay));
	}

	@Test
	public void testPrivateLayoutFromCompanyVirtualHostRefererPlidInvalid()
		throws Exception {

		ThemeDisplay themeDisplay = initThemeDisplay(
			company, group, privateLayout, LOCALHOST);

		addParameterToThemeDisplayRequest(themeDisplay, "refererPlid", "foo");

		Assert.assertEquals(
			privateLayoutRelativeURL,
			PortalUtil.getLayoutRelativeURL(privateLayout, themeDisplay));
	}

	@Test(expected = NoSuchLayoutException.class)
	public void
			testPrivateLayoutURLFromPublicLayoutSetVirtualHostRefererPlidNotExists()
		throws Exception {

		ThemeDisplay themeDisplay = initThemeDisplay(
			company, group, privateLayout, LOCALHOST, VIRTUAL_HOSTNAME);

		addParameterToThemeDisplayRequest(themeDisplay, "refererPlid", "1");

		PortalUtil.getLayoutRelativeURL(privateLayout, themeDisplay);

		Assert.fail("An exception should have been thrown");
	}

	@Test
	public void
			testPrivateLayoutURLFromPublicLayoutSetVirtualHostReferer()
		throws Exception {

		ThemeDisplay themeDisplay = initThemeDisplay(
			company, group, privateLayout, LOCALHOST, VIRTUAL_HOSTNAME);

		Assert.assertEquals(
			privateLayoutRelativeURL,
			PortalUtil.getLayoutRelativeURL(privateLayout, themeDisplay));
	}

	@Test
	public void
			testPrivateLayoutURLFromPublicLayoutSetVirtualHostRefererPlidInvalid()
		throws Exception {

		ThemeDisplay themeDisplay = initThemeDisplay(
			company, group, privateLayout, LOCALHOST, VIRTUAL_HOSTNAME);

		addParameterToThemeDisplayRequest(themeDisplay, "refererPlid", "foo");

		Assert.assertEquals(
			privateLayoutRelativeURL,
			PortalUtil.getLayoutRelativeURL(privateLayout, themeDisplay));
	}

	@Test(expected = NoSuchLayoutException.class)
	public void testPublicLayoutFromCompanyVirtualHostRefererPlidNotExists()
		throws Exception {

		ThemeDisplay themeDisplay = initThemeDisplay(
			company, group, publicLayout, LOCALHOST);

		addParameterToThemeDisplayRequest(themeDisplay, "refererPlid", "1");

		PortalUtil.getLayoutRelativeURL(publicLayout, themeDisplay);

		Assert.fail("An exception should have been thrown");
	}

	@Test
	public void testPublicLayoutFromCompanyVirtualHost() throws Exception {
		ThemeDisplay themeDisplay = initThemeDisplay(
			company, group, publicLayout, LOCALHOST);

		Assert.assertEquals(
			publicLayoutRelativeURL,
			PortalUtil.getLayoutRelativeURL(publicLayout, themeDisplay));
	}

	@Test
	public void testPublicLayoutFromCompanyVirtualHostRefererPlidInvalid()
		throws Exception {

		ThemeDisplay themeDisplay = initThemeDisplay(
			company, group, publicLayout, LOCALHOST);

		addParameterToThemeDisplayRequest(themeDisplay, "refererPlid", "foo");

		Assert.assertEquals(
			publicLayoutRelativeURL,
			PortalUtil.getLayoutRelativeURL(publicLayout, themeDisplay));
	}

	@Test
	public void testPublicLayoutURLFromPublicLayoutSetVirtualHost()
		throws Exception {

		ThemeDisplay themeDisplay = initThemeDisplay(
			company, group, publicLayout, LOCALHOST, VIRTUAL_HOSTNAME);

		String publicLayoutFriendlyURL = publicLayout.getFriendlyURL();
		String layoutRelativeURL = PortalUtil.getLayoutRelativeURL(
			publicLayout, themeDisplay);

		Assert.assertTrue(
			publicLayoutFriendlyURL.equals(layoutRelativeURL) ||
			publicLayoutRelativeURL.equals(layoutRelativeURL));
	}

	protected void addParameterToThemeDisplayRequest(
		ThemeDisplay themeDisplay, String paramName, String paramValue) {

		if (themeDisplay.getRequest() == null) {
			themeDisplay.setRequest(new MockHttpServletRequest());
		}

		MockHttpServletRequest request =
			(MockHttpServletRequest)themeDisplay.getRequest();

		request.addParameter(paramName, paramValue);
	}

	protected String privateLayoutRelativeURL;
	protected String publicLayoutRelativeURL;

}