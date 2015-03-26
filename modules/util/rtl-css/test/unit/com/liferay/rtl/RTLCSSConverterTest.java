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

package com.liferay.rtl;

import com.helger.commons.charset.CCharset;
import com.helger.css.ECSSVersion;
import com.helger.css.decl.CSSStyleRule;
import com.helger.css.decl.CascadingStyleSheet;
import com.helger.css.reader.CSSReader;
import com.helger.css.writer.CSSWriterSettings;

import com.liferay.portal.kernel.util.StringBundler;

import java.io.InputStream;

import java.util.List;

import org.apache.commons.io.IOUtils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author David Truong
 */
public class RTLCSSConverterTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAsterick() throws Exception {
		RTLCSSConverter rtlCssConverter = new RTLCSSConverter();

		Assert.assertNotNull(rtlCssConverter);

		String css = "a{*margin-right: 5px}";
		String expected = "a{*margin-left:5px}";
		String result = rtlCssConverter.process(css);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testBGPosition() throws Exception {
		RTLCSSConverter rtlCssConverter = new RTLCSSConverter();

		Assert.assertNotNull(rtlCssConverter);

		String css =
			"a{background-position:right top}b{background-position:10%}";
		String expected =
			"a{background-position:left top}b{background-position:right 10%}";
		String result = rtlCssConverter.process(css);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testPortalCss() throws Exception {
		RTLCSSConverter rtlCssConverter = new RTLCSSConverter();

		Assert.assertNotNull(rtlCssConverter);

		String css = read("main.css");
		String expected = formatCss(read("main_rtl.css"));
		String result = rtlCssConverter.process(css);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testReplacement() throws Exception {
		RTLCSSConverter rtlCssConverter = new RTLCSSConverter();

		Assert.assertNotNull(rtlCssConverter);

		String css = "a{left:5px;right:15px}b{margin-left:5px}";
		String expected = "a{right:5px;left:15px}b{margin-right:5px}";
		String result = rtlCssConverter.process(css);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testReverse() throws Exception {
		RTLCSSConverter rtlCssConverter = new RTLCSSConverter();

		Assert.assertNotNull(rtlCssConverter);

		String css = "a{text-align:left}";
		String expected = "a{text-align:right}";
		String result = rtlCssConverter.process(css);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testShortHand() throws Exception {
		RTLCSSConverter rtlCssConverter = new RTLCSSConverter();

		Assert.assertNotNull(rtlCssConverter);

		String css = "a{padding:1px 2px 3px 4px}";
		String expected = "a{padding:1px 4px 3px 2px}";
		String result = rtlCssConverter.process(css);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testShortHandRadius() throws Exception {
		RTLCSSConverter rtlCssConverter = new RTLCSSConverter();

		Assert.assertNotNull(rtlCssConverter);

		String css =
			"a{border-radius:1px 2px 3px 4px}b{border-radius:5px 10px 20px}";
		String expected =
			"a{border-radius:2px 1px 4px 3px}" +
			"b{border-radius:10px 5px 10px 20px}";
		String result = rtlCssConverter.process(css);

		Assert.assertEquals(expected, result);
	}

	protected String formatCss(String css) {
		CascadingStyleSheet cssObject = CSSReader.readFromString(
			css, CCharset.CHARSET_UTF_8_OBJ, ECSSVersion.CSS30);

		List<CSSStyleRule> styleRules = cssObject.getAllStyleRules();

		StringBundler sb = new StringBundler(styleRules.size());

		CSSWriterSettings writerSettings = new CSSWriterSettings(
			ECSSVersion.CSS30, true);

		for (CSSStyleRule styleRule : styleRules) {
			sb.append(styleRule.getAsCSSString(writerSettings, 1));
		}

		return sb.toString();
	}

	protected String read(InputStream inputStream) throws Exception {
		return IOUtils.toString(inputStream, "UTF-8");
	}

	protected String read(String fileName) throws Exception {
		return read(
			this.getClass().getResourceAsStream("dependencies/" + fileName));
	}

}