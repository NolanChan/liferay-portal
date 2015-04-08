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

package com.liferay.portal.servlet.jsp.compiler.internal;

import java.net.URL;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Raymond Augé
 */
public class JspCompilerTest {

	@Test
	public void testGetTldUri1() throws Exception {
		JspCompiler jspCompiler = new JspCompiler();

		URL url = JspCompilerTest.class.getResource("dependencies/test_1.tld");

		Assert.assertEquals("This is a test.", jspCompiler.getTldUri(url));
	}

	@Test
	public void testGetTldUri2() throws Exception {
		JspCompiler jspCompiler = new JspCompiler();

		URL url = JspCompilerTest.class.getResource("dependencies/test_2.tld");

		Assert.assertEquals("This is a test.", jspCompiler.getTldUri(url));
	}

	@Test
	public void testGetTldUri3() throws Exception {
		JspCompiler jspCompiler = new JspCompiler();

		URL url = JspCompilerTest.class.getResource("dependencies/test_3.tld");

		Assert.assertNull(jspCompiler.getTldUri(url));
	}

	@Test
	public void testGetTldUri4() throws Exception {
		JspCompiler jspCompiler = new JspCompiler();

		URL url = JspCompilerTest.class.getResource("dependencies/test_4.tld");

		Assert.assertNull(jspCompiler.getTldUri(url));
	}

}