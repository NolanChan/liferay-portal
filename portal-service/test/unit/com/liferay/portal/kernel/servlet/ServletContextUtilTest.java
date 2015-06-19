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

package com.liferay.portal.kernel.servlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.servlet.ServletContext;

import org.junit.Assert;
import org.junit.Test;

import org.springframework.mock.web.MockServletContext;

/**
 * @author Laszlo Csontos
 */

public class ServletContextUtilTest {

	@Test
	public void testGetResourceURIWithEmptyPath() throws Exception {
		doTestGetResourceURI(StringPool.BLANK);
	}

	@Test(expected = URISyntaxException.class)
	public void testGetResourceURIWithInvalidCharacters() throws Exception {
		ServletContextUtil.getResourceURI(
			new URL("file://" + _URI_WITH_INVALID_CHARACTERS + "/dummy"));
	}

	@Test
	public void testGetResourceURIWithReservedCharacters() throws Exception {
		doTestGetResourceURI(_URI_WITH_RESERVED_CHARACTERS);
	}

	@Test
	public void testGetResourceURIWithUnreservedCharacters() throws Exception {
		doTestGetResourceURI(_URI_WITH_UNRESERVED_CHARACTERS);
	}

	@Test
	public void testGetRootURIWithEmptyPath() throws Exception {
		getRootURI(StringPool.BLANK, getURI(StringPool.SLASH));
	}

	@Test(expected = MalformedURLException.class)
	public void testGetRootURIWithInvalidCharacters() throws Exception {
		getRootURI(_URI_WITH_INVALID_CHARACTERS, null);
	}

	@Test
	public void testGetRootURIWithReservedCharacters() throws Exception {
		String path = _URI_WITH_RESERVED_CHARACTERS;

		getRootURI(path, getURI(path));
	}

	@Test
	public void testGetRootURIWithUnreservedCharacters() throws Exception {
		String path = _URI_WITH_UNRESERVED_CHARACTERS;

		getRootURI(path, getURI(path));
	}

	protected void doTestGetResourceURI(String resourceURL) throws Exception {
		URL url = new URL("file://" + resourceURL + "/dummy");

		URI uri = ServletContextUtil.getResourceURI(url);

		Assert.assertEquals("file", uri.getScheme());
		Assert.assertEquals(url.getPath(), uri.getSchemeSpecificPart());
		Assert.assertEquals(null, uri.getFragment());
	}

	protected void getRootURI(String path, URI uri) throws Exception {
		ServletContext servletContext = getServletContext(path);

		URI rootURI = ServletContextUtil.getRootURI(servletContext);

		Assert.assertEquals(uri, rootURI);
		Assert.assertEquals(
			uri, servletContext.getAttribute(ServletContextUtil.URI_ATTRIBUTE));
	}

	protected ServletContext getServletContext(final String path) {
		return new MockServletContext() {

			@Override
			public URL getResource(String resourcePath)
				throws MalformedURLException {

				URL url = new URL("file:" + path + resourcePath);

				return url;
			}

		};
	}

	protected URI getURI(String path) {
		URI uri = null;

		try {
			uri = new URI("file", path, null);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return uri;
	}

	private static final String _URI_WITH_INVALID_CHARACTERS = ":?#[]/@";

	private static final String _URI_WITH_RESERVED_CHARACTERS =
		"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_.~";

	private static final String _URI_WITH_UNRESERVED_CHARACTERS =
		"/!$&'()*+,;= ";

	private static final Log _log = LogFactoryUtil.getLog(
		ServletContextUtilTest.class);

}