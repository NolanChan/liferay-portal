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

package com.liferay.wiki.engine.mediawiki.processor;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Roberto Díaz
 */
public class WikiPageRenameMediaWikiContentProcessorTest {

	@Test
	public void testProcessContent() {
		String content = "This is a test [[Image:ORIGINAL_NAME/image.jpg]]";

		content = _wikiPageRenameMediaWikiContentProcessor.processContent(
			0, "ORIGINAL_NAME", "FINAL_NAME", content);

		Assert.assertEquals(
			"This is a test [[Image:FINAL_NAME/image.jpg]]", content);
	}

	@Test
	public void testProcessContentDoNotChangeLinks() {
		String content = "This is a test [[ORIGINAL_LINK]]";

		content = _wikiPageRenameMediaWikiContentProcessor.processContent(
			0, "ORIGINAL_LINK", "FINAL_LINK", content);

		Assert.assertEquals("This is a test [[ORIGINAL_LINK]]", content);
	}

	@Test
	public void testProcessContentDoNotChangeOtherImages() {
		String content =
			"This is a test [[Image:ORIGINAL_NAME1/image.jpg]] " +
				"[[Image:ORIGINAL_NAME2/image.jpg]]";

		content = _wikiPageRenameMediaWikiContentProcessor.processContent(
			0, "ORIGINAL_NAME1", "FINAL_NAME1", content);

		Assert.assertEquals(
			"This is a test [[Image:FINAL_NAME1/image.jpg]] " +
				"[[Image:ORIGINAL_NAME2/image.jpg]]",
			content);
	}

	@Test
	public void testProcessContentWithComplexTitle() {
		String content =
			"This is a test [[Image:Complex.,() original title/image.jpg]]";

		content = _wikiPageRenameMediaWikiContentProcessor.processContent(
			0, "Complex.,() original title", "Complex.,() final title",
			content);

		Assert.assertEquals(
			"This is a test [[Image:Complex.,() final title/image.jpg]]",
			content);
	}

	@Test
	public void testProcessContentWithCurlyBracketsInTitle() {
		String content = "This is a test [[Image:{ORIGINAL_NAME}/image.jpg]]";

		content = _wikiPageRenameMediaWikiContentProcessor.processContent(
			0, "{ORIGINAL_NAME}", "{FINAL_NAME}", content);

		Assert.assertEquals(
			"This is a test [[Image:{FINAL_NAME}/image.jpg]]", content);
	}

	@Test
	public void testProcessContentWithNumbersInTitle() {
		String content =
			"This is a test [[Image:ORIGINAL_NAME123456/image.jpg]]";

		content = _wikiPageRenameMediaWikiContentProcessor.processContent(
			0, "ORIGINAL_NAME123456", "FINAL_NAME123456", content);

		Assert.assertEquals(
			"This is a test [[Image:FINAL_NAME123456/image.jpg]]", content);
	}

	@Test
	public void testProcessContentWithParenthesisInTitle() {
		String content = "This is a test [[Image:(ORIGINAL_NAME)/image.jpg]]";

		content = _wikiPageRenameMediaWikiContentProcessor.processContent(
			0, "(ORIGINAL_NAME)", "(FINAL_NAME)", content);

		Assert.assertEquals(
			"This is a test [[Image:(FINAL_NAME)/image.jpg]]", content);
	}

	@Test
	public void testProcessContentWithSpaceInTitle() {
		String content =
			"This is a test [[Image:ORIGINAL NAME PAGE/image.jpg]]";

		content = _wikiPageRenameMediaWikiContentProcessor.processContent(
			0, "ORIGINAL NAME PAGE", "FINAL NAME PAGE", content);

		Assert.assertEquals(
			"This is a test [[Image:FINAL NAME PAGE/image.jpg]]", content);
	}

	private final WikiPageRenameMediaWikiContentProcessor
		_wikiPageRenameMediaWikiContentProcessor =
			new WikiPageRenameMediaWikiContentProcessorStub();

	private class WikiPageRenameMediaWikiContentProcessorStub
		extends WikiPageRenameMediaWikiContentProcessor {

		public WikiPageRenameMediaWikiContentProcessorStub() {
			activate();
		}

	}

}