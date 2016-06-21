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

package com.liferay.wiki.engine.mediawiki.translator;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Roberto Díaz
 */
public class WikiTitleChangeMediaWikiTranslatorTest {

	@Test
	public void testImage() {
		String content = "This is a test [[Image:ORIGINAL_NAME/image.jpg]]";

		content = _wikiTitleChangeMediaWikiTranslator.translate(
			content, "ORIGINAL_NAME", "FINAL_NAME");

		Assert.assertEquals(
			"This is a test [[Image:FINAL_NAME/image.jpg]]", content);
	}

	private final WikiTitleChangeMediaWikiTranslator
		_wikiTitleChangeMediaWikiTranslator =
			new WikiTitleChangeMediaWikiTranslatorStub();

	private class WikiTitleChangeMediaWikiTranslatorStub
		extends WikiTitleChangeMediaWikiTranslator {

		public WikiTitleChangeMediaWikiTranslatorStub() {
			activate();
		}

	}

}