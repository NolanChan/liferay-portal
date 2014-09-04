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

package com.liferay.portlet.wiki.service;

import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.test.AssertUtils;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.test.DeleteAfterTestRun;
import com.liferay.portal.test.Sync;
import com.liferay.portal.test.SynchronousDestinationExecutionTestListener;
import com.liferay.portal.test.listeners.MainServletExecutionTestListener;
import com.liferay.portal.test.runners.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.test.GroupTestUtil;
import com.liferay.portal.util.test.RandomTestUtil;
import com.liferay.portal.util.test.ServiceContextTestUtil;
import com.liferay.portal.util.test.TestPropsValues;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetLink;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetLinkLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetTagLocalServiceUtil;
import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.model.ExpandoColumn;
import com.liferay.portlet.expando.model.ExpandoColumnConstants;
import com.liferay.portlet.expando.model.ExpandoValue;
import com.liferay.portlet.expando.util.test.ExpandoTestUtil;
import com.liferay.portlet.wiki.DuplicatePageException;
import com.liferay.portlet.wiki.NoSuchPageResourceException;
import com.liferay.portlet.wiki.NodeChangeException;
import com.liferay.portlet.wiki.model.WikiNode;
import com.liferay.portlet.wiki.model.WikiPage;
import com.liferay.portlet.wiki.util.test.WikiTestUtil;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Manuel de la Peña
 * @author Roberto Díaz
 */
@ExecutionTestListeners(
	listeners = {
		MainServletExecutionTestListener.class,
		SynchronousDestinationExecutionTestListener.class
	})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
@Sync
public class WikiPageLocalServiceTest {

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();

		_node = WikiTestUtil.addNode(_group.getGroupId());
	}

	@Test
	public void testChangeChildPageNode() throws Exception {
		WikiNode destinationNode = WikiTestUtil.addNode(_group.getGroupId());

		WikiTestUtil.addPage(
			TestPropsValues.getUserId(), _group.getGroupId(), _node.getNodeId(),
			"ParentPage", true);

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(_group.getGroupId());

		WikiTestUtil.addPage(
			TestPropsValues.getUserId(), _node.getNodeId(), "ChildPage",
			"content", "ParentPage", true, serviceContext);

		WikiPageLocalServiceUtil.changeNode(
			TestPropsValues.getUserId(), _node.getNodeId(), "ChildPage",
			destinationNode.getNodeId(), serviceContext);

		WikiPage page = WikiPageLocalServiceUtil.getPage(
			_node.getNodeId(), "ParentPage");
		WikiPage childPage = WikiPageLocalServiceUtil.getPage(
			destinationNode.getNodeId(), "ChildPage");

		Assert.assertTrue(page.isApproved());
		Assert.assertTrue(page.getNodeId() == _node.getNodeId());
		Assert.assertTrue(childPage.isApproved());
		Assert.assertTrue(childPage.getNodeId() == destinationNode.getNodeId());
		Assert.assertTrue(Validator.isNull(childPage.getParentTitle()));
	}

	@Test
	public void testChangePageNode() throws Exception {
		WikiPage page = WikiTestUtil.addPage(
			_group.getGroupId(), _node.getNodeId(), true);

		WikiNode destinationNode = WikiTestUtil.addNode(_group.getGroupId());

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(_group.getGroupId());

		WikiPageLocalServiceUtil.changeNode(
			TestPropsValues.getUserId(), _node.getNodeId(), page.getTitle(),
			destinationNode.getNodeId(), serviceContext);

		WikiPageLocalServiceUtil.getPage(
			destinationNode.getNodeId(), page.getTitle());
	}

	@Test
	public void testChangePageNodeWithChildHierarchy() throws Exception {
		WikiNode destinationNode = WikiTestUtil.addNode(_group.getGroupId());

		WikiTestUtil.addPage(
			TestPropsValues.getUserId(), _group.getGroupId(), _node.getNodeId(),
			"ParentPage", true);

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(_group.getGroupId());

		createPageHierarchy("ParentPage", 10, serviceContext);

		WikiPageLocalServiceUtil.changeNode(
			TestPropsValues.getUserId(), _node.getNodeId(), "ParentPage",
			destinationNode.getNodeId(), serviceContext);

		WikiPage page = WikiPageLocalServiceUtil.getPage(
			destinationNode.getNodeId(), "ParentPage");

		Assert.assertTrue(page.isApproved());
		Assert.assertTrue(page.getNodeId() == destinationNode.getNodeId());

		List<WikiPage> childHierarchy = getPageHierarchy(
			destinationNode.getNodeId(), "ParentPage");

		Assert.assertEquals(10, childHierarchy.size());

		for (WikiPage childPage : childHierarchy) {
			Assert.assertTrue(childPage.isApproved());
			Assert.assertTrue(
				childPage.getNodeId() == destinationNode.getNodeId());
		}
	}

	@Test
	public void testChangePageNodeWithChildPageNameDuplication()
		throws Exception {

		WikiNode destinationNode = WikiTestUtil.addNode(_group.getGroupId());

		WikiTestUtil.addPage(
			TestPropsValues.getUserId(), _group.getGroupId(), _node.getNodeId(),
			"ParentPage", true);

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(_group.getGroupId());

		WikiTestUtil.addPage(
			TestPropsValues.getUserId(), _node.getNodeId(),
			"DuplicatedTitlePage", "content", "ParentPage", true,
			serviceContext);

		WikiTestUtil.addPage(
			TestPropsValues.getUserId(), _group.getGroupId(),
			destinationNode.getNodeId(), "DuplicatedTitlePage", true);

		try {
			WikiPageLocalServiceUtil.changeNode(
				TestPropsValues.getUserId(), _node.getNodeId(), "ParentPage",
				destinationNode.getNodeId(), serviceContext);

			Assert.fail(
				"Node change should not be performed due title duplication");
		}
		catch (NodeChangeException nce) {
			Assert.assertEquals("DuplicatedTitlePage", nce.getPageTitle());
			Assert.assertEquals(destinationNode.getName(), nce.getNodeName());
			Assert.assertEquals(
				NodeChangeException.DUPLICATE_PAGE, nce.getType());
		}
	}

	@Test
	public void testChangePageNodeWithPageNameDuplication() throws Exception {
		WikiNode destinationNode = WikiTestUtil.addNode(_group.getGroupId());

		WikiPage page = WikiTestUtil.addPage(
			TestPropsValues.getUserId(), _group.getGroupId(), _node.getNodeId(),
			"DuplicatedTitlePage", true);

		WikiTestUtil.addPage(
			TestPropsValues.getUserId(), _group.getGroupId(),
			destinationNode.getNodeId(), "DuplicatedTitlePage", true);

		try {
			ServiceContext serviceContext =
				ServiceContextTestUtil.getServiceContext(_group.getGroupId());

			WikiPageLocalServiceUtil.changeNode(
				page.getUserId(), _node.getNodeId(), "DuplicatedTitlePage",
				destinationNode.getNodeId(), serviceContext);

			Assert.fail(
				"Node change should not be performed due title duplication");
		}
		catch (NodeChangeException nce) {
			Assert.assertEquals("DuplicatedTitlePage", nce.getPageTitle());
			Assert.assertEquals(destinationNode.getName(), nce.getNodeName());
			Assert.assertEquals(
				NodeChangeException.DUPLICATE_PAGE, nce.getType());
		}
	}

	@Test
	public void testChangePageNodeWithRedirectPage() throws Exception {
		WikiNode destinationNode = WikiTestUtil.addNode(_group.getGroupId());

		WikiTestUtil.addPage(
			TestPropsValues.getUserId(), _group.getGroupId(), _node.getNodeId(),
			"InitialTitlePage", true);

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(_group.getGroupId());

		WikiPageLocalServiceUtil.renamePage(
			TestPropsValues.getUserId(), _node.getNodeId(), "InitialTitlePage",
			"RenamedPage", serviceContext);

		WikiPageLocalServiceUtil.changeNode(
			TestPropsValues.getUserId(), _node.getNodeId(), "RenamedPage",
			destinationNode.getNodeId(), serviceContext);

		WikiPage page = WikiPageLocalServiceUtil.getPage(
			destinationNode.getNodeId(), "RenamedPage");
		WikiPage redirectPage = WikiPageLocalServiceUtil.getPage(
			destinationNode.getNodeId(), "InitialTitlePage");

		Assert.assertTrue(page.isApproved());
		Assert.assertTrue(page.getNodeId() == destinationNode.getNodeId());
		Assert.assertTrue(redirectPage.isApproved());
		Assert.assertTrue(
			redirectPage.getNodeId() == destinationNode.getNodeId());
		Assert.assertTrue(
			redirectPage.getRedirectTitle().equals("RenamedPage"));
	}

	@Test
	public void testChangePageNodeWithRedirectPageNameDuplication()
		throws Exception {

		WikiNode destinationNode = WikiTestUtil.addNode(_group.getGroupId());

		WikiTestUtil.addPage(
			TestPropsValues.getUserId(), _group.getGroupId(), _node.getNodeId(),
			"DuplicatedTitlePage", true);

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(_group.getGroupId());

		WikiPageLocalServiceUtil.renamePage(
			TestPropsValues.getUserId(), _node.getNodeId(),
			"DuplicatedTitlePage", "RenamedPage",  serviceContext);

		WikiTestUtil.addPage(
			TestPropsValues.getUserId(), _group.getGroupId(),
			destinationNode.getNodeId(), "DuplicatedTitlePage", true);

		try {
			WikiPageLocalServiceUtil.changeNode(
				TestPropsValues.getUserId(), _node.getNodeId(), "RenamedPage",
				destinationNode.getNodeId(), serviceContext);

			Assert.fail(
				"Node change should not be performed due title duplication");
		}
		catch (NodeChangeException nce) {
			Assert.assertEquals("DuplicatedTitlePage", nce.getPageTitle());
			Assert.assertEquals(destinationNode.getName(), nce.getNodeName());
			Assert.assertEquals(
				NodeChangeException.DUPLICATE_PAGE, nce.getType());
		}
	}

	@Test
	public void testChangeParent() throws Exception {
		testChangeParent(false);
	}

	@Test
	public void testChangeParentWithExpando() throws Exception {
		testChangeParent(true);
	}

	@Test
	public void testChangeRedirectPageNode() throws Exception {
		WikiNode destinationNode = WikiTestUtil.addNode(_group.getGroupId());

		WikiTestUtil.addPage(
			TestPropsValues.getUserId(), _group.getGroupId(), _node.getNodeId(),
			"InitialTitlePage", true);

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(_group.getGroupId());

		WikiPageLocalServiceUtil.renamePage(
			TestPropsValues.getUserId(), _node.getNodeId(), "InitialTitlePage",
			"RenamedPage",  serviceContext);

		try {
			WikiPageLocalServiceUtil.changeNode(
				TestPropsValues.getUserId(), _node.getNodeId(),
				"InitialTitlePage", destinationNode.getNodeId(),
				serviceContext);

			Assert.fail(
				"Node change should not be performed due title duplication");
		}
		catch (NodeChangeException nce) {
			Assert.assertEquals("InitialTitlePage", nce.getPageTitle());
			Assert.assertEquals(_node.getName(), nce.getNodeName());
			Assert.assertEquals(
				NodeChangeException.REDIRECT_PAGE, nce.getType());
		}
	}

	@Test
	public void testCopyPage() throws Exception {
		WikiPage page = WikiTestUtil.addPage(
			_group.getGroupId(), _node.getNodeId(), true);

		WikiTestUtil.addWikiAttachment(
			page.getUserId(), page.getNodeId(), page.getTitle(), getClass());

		List<FileEntry> attachmentsFileEntries =
			page.getAttachmentsFileEntries();

		WikiPage copyPage = WikiTestUtil.copyPage(
			page, true,
			ServiceContextTestUtil.getServiceContext(_group.getGroupId()));

		List<FileEntry> copyAttachmentsFileEntries =
			copyPage.getAttachmentsFileEntries();

		Assert.assertEquals(
			copyAttachmentsFileEntries.size(), attachmentsFileEntries.size());

		FileEntry fileEntry = attachmentsFileEntries.get(0);
		FileEntry copyFileEntry = copyAttachmentsFileEntries.get(0);

		Assert.assertEquals(
			copyFileEntry.getExtension(), fileEntry.getExtension());
		Assert.assertEquals(
			copyFileEntry.getMimeType(), fileEntry.getMimeType());
		Assert.assertEquals(copyFileEntry.getTitle(), fileEntry.getTitle());
		Assert.assertEquals(copyFileEntry.getSize(), fileEntry.getSize());
	}

	@Test(expected = NoSuchPageResourceException.class)
	public void testDeletePage() throws Exception {
		WikiPage page = WikiTestUtil.addPage(
			TestPropsValues.getUserId(), _group.getGroupId(), _node.getNodeId(),
			"TestPage", true);

		WikiPageLocalServiceUtil.deletePage(page);

		WikiPageLocalServiceUtil.getPage(page.getResourcePrimKey());
	}

	@Test
	public void testDeleteTrashedPageWithExplicitTrashedRedirectPage()
		throws Exception {

		WikiPage[] pages = WikiTestUtil.addRenamedTrashedPage(
			_group.getGroupId(), _node.getNodeId(), true);

		WikiPage page = pages[0];
		WikiPage redirectPage = pages[1];

		WikiPageLocalServiceUtil.deletePage(page);

		try {
			WikiPageLocalServiceUtil.getPage(page.getResourcePrimKey());

			Assert.fail("Page should be deleted");
		}
		catch (NoSuchPageResourceException nspre) {
			redirectPage = WikiPageLocalServiceUtil.getPage(
				redirectPage.getResourcePrimKey());

			Assert.assertNull(redirectPage.fetchRedirectPage());
		}
	}

	@Test(expected = NoSuchPageResourceException.class)
	public void testDeleteTrashedPageWithImplicitTrashedRedirectPage()
		throws Exception {

		WikiPage[] pages = WikiTestUtil.addRenamedTrashedPage(
			_group.getGroupId(), _node.getNodeId(), false);

		WikiPage page = pages[0];
		WikiPage redirectPage = pages[1];

		WikiPageLocalServiceUtil.deletePage(page);

		try {
			WikiPageLocalServiceUtil.getPage(page.getResourcePrimKey());

			Assert.fail("Page should be deleted");
		}
		catch (NoSuchPageResourceException nsrpe) {
			WikiPageLocalServiceUtil.getPage(redirectPage.getResourcePrimKey());
		}
	}

	@Test
	public void testDeleteTrashedPageWithRestoredChildPage() throws Exception {
		WikiPage[] pages = WikiTestUtil.addTrashedPageWithChildPage(
			_group.getGroupId(), _node.getNodeId(), true);

		WikiPage parentPage = pages[0];
		WikiPage childPage = pages[1];

		WikiPageLocalServiceUtil.restorePageFromTrash(
			TestPropsValues.getUserId(), childPage);

		WikiPageLocalServiceUtil.deletePage(parentPage);

		try {
			WikiPageLocalServiceUtil.getPage(parentPage.getResourcePrimKey());

			Assert.fail("Parent page should be deleted");
		}
		catch (NoSuchPageResourceException nspre) {
			childPage = WikiPageLocalServiceUtil.getPage(
				childPage.getResourcePrimKey());

			Assert.assertNull(childPage.fetchParentPage());
			Assert.assertEquals(
				WorkflowConstants.STATUS_APPROVED, childPage.getStatus());
		}
	}

	@Test
	public void testDeleteTrashedPageWithRestoredRedirectPage()
		throws Exception {

		WikiPage[] pages = WikiTestUtil.addRenamedTrashedPage(
			_group.getGroupId(), _node.getNodeId(), true);

		WikiPage page = pages[0];
		WikiPage redirectPage = pages[1];

		WikiPageLocalServiceUtil.restorePageFromTrash(
			TestPropsValues.getUserId(), redirectPage);

		WikiPageLocalServiceUtil.deletePage(page);

		try {
			WikiPageLocalServiceUtil.getPage(page.getResourcePrimKey());

			Assert.fail("Page should be deleted");
		}
		catch (NoSuchPageResourceException nspre) {
			redirectPage = WikiPageLocalServiceUtil.getPageByPageId(
				redirectPage.getPageId());

			Assert.assertNull(redirectPage.fetchRedirectPage());
			Assert.assertEquals(
				WorkflowConstants.STATUS_APPROVED, redirectPage.getStatus());
		}
	}

	@Test
	public void testDeleteTrashedParentPageWithExplicitTrashedChildPage()
		throws Exception {

		WikiPage[] pages = WikiTestUtil.addTrashedPageWithChildPage(
			_group.getGroupId(), _node.getNodeId(), true);

		WikiPage parentPage = pages[0];
		WikiPage childPage = pages[1];

		WikiPageLocalServiceUtil.deletePage(parentPage);

		try {
			WikiPageLocalServiceUtil.getPage(parentPage.getResourcePrimKey());

			Assert.fail("Parent page should be deleted");
		}
		catch (NoSuchPageResourceException nspre) {
			childPage = WikiPageLocalServiceUtil.getPageByPageId(
				childPage.getPageId());

			Assert.assertNull(childPage.fetchParentPage());
		}
	}

	@Test(expected = NoSuchPageResourceException.class)
	public void testDeleteTrashedParentPageWithImplicitTrashedChildPage()
		throws Exception {

		WikiPage[] pages = WikiTestUtil.addTrashedPageWithChildPage(
			_group.getGroupId(), _node.getNodeId(), false);

		WikiPage parentPage = pages[0];
		WikiPage childPage = pages[1];

		WikiPageLocalServiceUtil.deletePage(parentPage);

		try {
			WikiPageLocalServiceUtil.getPage(parentPage.getResourcePrimKey());

			Assert.fail("Parent page should be deleted");
		}
		catch (NoSuchPageResourceException nspre) {
			WikiPageLocalServiceUtil.getPage(childPage.getResourcePrimKey());
		}
	}

	@Test
	public void testGetPage() throws Exception {
		WikiPage page = WikiTestUtil.addPage(
			_group.getGroupId(), _node.getNodeId(), true);

		WikiPage retrievedPage = WikiPageLocalServiceUtil.getPage(
			page.getResourcePrimKey());

		Assert.assertEquals(retrievedPage.getPageId(), page.getPageId());
	}

	@Test
	public void testRenamePage() throws Exception {
		testRenamePage(false);
	}

	@Test(expected = DuplicatePageException.class)
	public void testRenamePageSameName() throws Exception {
		WikiPage page = WikiTestUtil.addPage(
			_group.getGroupId(), _node.getNodeId(), true);

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(_group.getGroupId());

		WikiPageLocalServiceUtil.renamePage(
			TestPropsValues.getUserId(), _node.getNodeId(), page.getTitle(),
			page.getTitle(), true, serviceContext);
	}

	@Test
	public void testRenamePageWithExpando() throws Exception {
		testRenamePage(true);
	}

	@Test
	public void testRenameRenamedPage() throws Exception {
		WikiTestUtil.addPage(
			TestPropsValues.getUserId(), _group.getGroupId(), _node.getNodeId(),
			"A", true);

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(_group.getGroupId());

		WikiPageLocalServiceUtil.renamePage(
			TestPropsValues.getUserId(), _node.getNodeId(), "A", "B", true,
			serviceContext);

		WikiPageLocalServiceUtil.renamePage(
			TestPropsValues.getUserId(), _node.getNodeId(), "A", "C", true,
			serviceContext);

		WikiPage pageA = WikiPageLocalServiceUtil.getPage(
			_node.getNodeId(), "A");
		WikiPage pageB = WikiPageLocalServiceUtil.getPage(
			_node.getNodeId(), "B");
		WikiPage pageC = WikiPageLocalServiceUtil.getPage(
			_node.getNodeId(), "C");

		Assert.assertEquals("C", pageA.getRedirectTitle());
		Assert.assertEquals(StringPool.BLANK, pageB.getRedirectTitle());
		Assert.assertEquals(StringPool.BLANK, pageC.getRedirectTitle());
		Assert.assertEquals("Renamed as C", pageA.getSummary());
		Assert.assertEquals("Summary", pageB.getSummary());
		Assert.assertEquals(StringPool.BLANK, pageC.getSummary());
		Assert.assertEquals("[[C]]", pageA.getContent());
		Assert.assertEquals("[[B]]", pageC.getContent());
	}

	@Test
	public void testRestorePageFromTrash() throws Exception {
		testRestorePageFromTrash(false);
	}

	@Test
	public void testRestorePageFromTrashWithExpando() throws Exception {
		testRestorePageFromTrash(true);
	}

	@Test
	public void testRevertPage() throws Exception {
		testRevertPage(false);
	}

	@Test
	public void testRevertPageWithExpando() throws Exception {
		testRevertPage(true);
	}

	protected void addExpandoValueToPage(WikiPage page) throws Exception {
		ExpandoValue value = ExpandoTestUtil.addValue(
			PortalUtil.getClassNameId(WikiPage.class), page.getPrimaryKey(),
			RandomTestUtil.randomString());

		ExpandoBridge expandoBridge = page.getExpandoBridge();

		ExpandoColumn column = value.getColumn();

		expandoBridge.addAttribute(
			column.getName(), ExpandoColumnConstants.STRING, value.getString());
	}

	protected void checkPopulatedServiceContext(
			ServiceContext serviceContext, WikiPage page,
			boolean hasExpandoValues)
		throws Exception {

		long[] assetCategoryIds = AssetCategoryLocalServiceUtil.getCategoryIds(
			WikiPage.class.getName(), page.getResourcePrimKey());

		Assert.assertEquals(
			serviceContext.getAssetCategoryIds(), assetCategoryIds);

		AssetEntry assetEntry = AssetEntryLocalServiceUtil.getEntry(
			WikiPage.class.getName(), page.getResourcePrimKey());

		List<AssetLink> assetLinks = AssetLinkLocalServiceUtil.getLinks(
			assetEntry.getEntryId());

		long[] assetLinkEntryIds = ListUtil.toLongArray(
			assetLinks, AssetLink.ENTRY_ID2_ACCESSOR);

		Assert.assertEquals(
			serviceContext.getAssetLinkEntryIds(), assetLinkEntryIds);

		String[] assetTagNames = AssetTagLocalServiceUtil.getTagNames(
			WikiPage.class.getName(), page.getResourcePrimKey());

		Assert.assertArrayEquals(
			serviceContext.getAssetTagNames(), assetTagNames);

		if (hasExpandoValues) {
			ExpandoBridge expandoBridge = page.getExpandoBridge();

			AssertUtils.assertEquals(
				expandoBridge.getAttributes(),
				serviceContext.getExpandoBridgeAttributes());
		}
	}

	protected void createPageHierarchy(
			String parentTitle, int level, ServiceContext serviceContext)
		throws Exception {

		if (level == 0) {
			return;
		}

		String title = RandomTestUtil.randomString();

		WikiTestUtil.addPage(
			TestPropsValues.getUserId(), _node.getNodeId(), title, "content",
			parentTitle, true, serviceContext);

		createPageHierarchy(title, --level, serviceContext);
	}

	protected List<WikiPage> getPageHierarchy(long nodeId, String parentTitle) {
		List<WikiPage> childHierarchy = new ArrayList<>();

		_getPageHierarchy(childHierarchy, nodeId, parentTitle);

		return childHierarchy;
	}

	protected void testChangeParent(boolean hasExpandoValues) throws Exception {
		WikiPage page = WikiTestUtil.addPage(
			_group.getGroupId(), _node.getNodeId(), true);

		if (hasExpandoValues) {
			addExpandoValueToPage(page);
		}

		WikiPage parentPage = WikiTestUtil.addPage(
			_group.getGroupId(), _node.getNodeId(), true);

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(_group.getGroupId());

		WikiPageLocalServiceUtil.changeParent(
			TestPropsValues.getUserId(), _node.getNodeId(), page.getTitle(),
			parentPage.getTitle(), serviceContext);

		WikiPage retrievedPage = WikiPageLocalServiceUtil.getPage(
			page.getResourcePrimKey());

		checkPopulatedServiceContext(
			serviceContext, retrievedPage, hasExpandoValues);
	}

	protected void testRenamePage(boolean hasExpandoValues) throws Exception {
		WikiPage page = WikiTestUtil.addPage(
			_group.getGroupId(), _node.getNodeId(), true);

		if (hasExpandoValues) {
			addExpandoValueToPage(page);
		}

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(_group.getGroupId());

		WikiPageLocalServiceUtil.renamePage(
			TestPropsValues.getUserId(), _node.getNodeId(), page.getTitle(),
			"New Title", true, serviceContext);

		WikiPage renamedPage = WikiPageLocalServiceUtil.getPage(
			_node.getNodeId(), "New Title");

		Assert.assertNotNull(renamedPage);

		checkPopulatedServiceContext(
			serviceContext, renamedPage, hasExpandoValues);
	}

	protected void testRestorePageFromTrash(boolean hasExpandoValues)
		throws Exception {

		WikiPage page = WikiTestUtil.addPage(
			_group.getGroupId(), _node.getNodeId(), true);

		if (hasExpandoValues) {
			addExpandoValueToPage(page);
		}

		WikiPageLocalServiceUtil.movePageToTrash(
			TestPropsValues.getUserId(), _node.getNodeId(), page.getTitle());

		WikiPageLocalServiceUtil.restorePageFromTrash(
			TestPropsValues.getUserId(), page);

		WikiPage restoredPage = WikiPageLocalServiceUtil.getPage(
			page.getResourcePrimKey());

		Assert.assertNotNull(restoredPage);

		if (hasExpandoValues) {
			ExpandoBridge expandoBridge = page.getExpandoBridge();

			ExpandoBridge restoredExpandoBridge =
				restoredPage.getExpandoBridge();

			AssertUtils.assertEquals(
				restoredExpandoBridge.getAttributes(),
				expandoBridge.getAttributes());
		}
	}

	protected void testRevertPage(boolean hasExpandoValues) throws Exception {
		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(_group.getGroupId());

		String originalContent = RandomTestUtil.randomString();

		WikiPage originalPage = WikiTestUtil.addPage(
			TestPropsValues.getUserId(), _node.getNodeId(),
			RandomTestUtil.randomString(), originalContent, true,
			serviceContext);

		if (hasExpandoValues) {
			addExpandoValueToPage(originalPage);
		}

		WikiPage updatedPage1 = WikiTestUtil.updatePage(
			originalPage, TestPropsValues.getUserId(),
			originalContent + "\nAdded second line.", serviceContext);

		Assert.assertNotEquals(originalContent, updatedPage1.getContent());

		WikiPage updatedPage2 = WikiTestUtil.updatePage(
			updatedPage1, TestPropsValues.getUserId(),
			updatedPage1.getContent() + "\nAdded third line.", serviceContext);

		Assert.assertNotEquals(originalContent, updatedPage2.getContent());

		WikiPage revertedPage = WikiPageLocalServiceUtil.revertPage(
			TestPropsValues.getUserId(), _node.getNodeId(),
			updatedPage2.getTitle(), originalPage.getVersion(), serviceContext);

		Assert.assertEquals(revertedPage.getContent(), originalContent);

		checkPopulatedServiceContext(
			serviceContext, revertedPage, hasExpandoValues);
	}

	private void _getPageHierarchy(
		List<WikiPage> childHierachy, long nodeId, String parentTitle) {

		List<WikiPage> childPages = WikiPageLocalServiceUtil.getChildren(
			nodeId, true, parentTitle);

		if (childPages.isEmpty()) {
			return;
		}

		childHierachy.addAll(childPages);

		for (WikiPage curPage : childPages) {
			_getPageHierarchy(childHierachy, nodeId, curPage.getTitle());
		}
	}

	@DeleteAfterTestRun
	private Group _group;

	private WikiNode _node;

}