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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link WikiPageService}.
 *
 * @author Brian Wing Shun Chan
 * @see WikiPageService
 * @generated
 */
@ProviderType
public class WikiPageServiceWrapper implements WikiPageService,
	ServiceWrapper<WikiPageService> {
	public WikiPageServiceWrapper(WikiPageService wikiPageService) {
		_wikiPageService = wikiPageService;
	}

	@Override
	public com.liferay.portlet.wiki.model.WikiPage addPage(long nodeId,
		java.lang.String title, java.lang.String content,
		java.lang.String summary, boolean minorEdit, java.lang.String format,
		java.lang.String parentTitle, java.lang.String redirectTitle,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wikiPageService.addPage(nodeId, title, content, summary,
			minorEdit, format, parentTitle, redirectTitle, serviceContext);
	}

	@Override
	public com.liferay.portlet.wiki.model.WikiPage addPage(long nodeId,
		java.lang.String title, java.lang.String content,
		java.lang.String summary, boolean minorEdit,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wikiPageService.addPage(nodeId, title, content, summary,
			minorEdit, serviceContext);
	}

	@Override
	public void addPageAttachment(long nodeId, java.lang.String title,
		java.lang.String fileName, java.io.File file, java.lang.String mimeType)
		throws com.liferay.portal.kernel.exception.PortalException {
		_wikiPageService.addPageAttachment(nodeId, title, fileName, file,
			mimeType);
	}

	@Override
	public void addPageAttachment(long nodeId, java.lang.String title,
		java.lang.String fileName, java.io.InputStream inputStream,
		java.lang.String mimeType)
		throws com.liferay.portal.kernel.exception.PortalException {
		_wikiPageService.addPageAttachment(nodeId, title, fileName,
			inputStream, mimeType);
	}

	@Override
	public void addPageAttachments(long nodeId, java.lang.String title,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.InputStream>> inputStreamOVPs)
		throws com.liferay.portal.kernel.exception.PortalException {
		_wikiPageService.addPageAttachments(nodeId, title, inputStreamOVPs);
	}

	@Override
	public void addTempFileEntry(long nodeId, java.lang.String folderName,
		java.lang.String fileName, java.io.InputStream inputStream,
		java.lang.String mimeType)
		throws com.liferay.portal.kernel.exception.PortalException {
		_wikiPageService.addTempFileEntry(nodeId, folderName, fileName,
			inputStream, mimeType);
	}

	@Override
	public void changeNode(long nodeId, java.lang.String title, long newNodeId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		_wikiPageService.changeNode(nodeId, title, newNodeId, serviceContext);
	}

	@Override
	public void changeParent(long nodeId, java.lang.String title,
		java.lang.String newParentTitle,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		_wikiPageService.changeParent(nodeId, title, newParentTitle,
			serviceContext);
	}

	@Override
	public void copyPageAttachments(long templateNodeId,
		java.lang.String templateTitle, long nodeId, java.lang.String title)
		throws com.liferay.portal.kernel.exception.PortalException {
		_wikiPageService.copyPageAttachments(templateNodeId, templateTitle,
			nodeId, title);
	}

	@Override
	public void deletePage(long nodeId, java.lang.String title)
		throws com.liferay.portal.kernel.exception.PortalException {
		_wikiPageService.deletePage(nodeId, title);
	}

	/**
	* @deprecated As of 6.2.0 replaced by {@link #discardDraft(long, String,
	double)}
	*/
	@Deprecated
	@Override
	public void deletePage(long nodeId, java.lang.String title, double version)
		throws com.liferay.portal.kernel.exception.PortalException {
		_wikiPageService.deletePage(nodeId, title, version);
	}

	@Override
	public void deletePageAttachment(long nodeId, java.lang.String title,
		java.lang.String fileName)
		throws com.liferay.portal.kernel.exception.PortalException {
		_wikiPageService.deletePageAttachment(nodeId, title, fileName);
	}

	@Override
	public void deletePageAttachments(long nodeId, java.lang.String title)
		throws com.liferay.portal.kernel.exception.PortalException {
		_wikiPageService.deletePageAttachments(nodeId, title);
	}

	@Override
	public void deleteTempFileEntry(long nodeId, java.lang.String folderName,
		java.lang.String fileName)
		throws com.liferay.portal.kernel.exception.PortalException {
		_wikiPageService.deleteTempFileEntry(nodeId, folderName, fileName);
	}

	@Override
	public void deleteTrashPageAttachments(long nodeId, java.lang.String title)
		throws com.liferay.portal.kernel.exception.PortalException {
		_wikiPageService.deleteTrashPageAttachments(nodeId, title);
	}

	@Override
	public void discardDraft(long nodeId, java.lang.String title, double version)
		throws com.liferay.portal.kernel.exception.PortalException {
		_wikiPageService.discardDraft(nodeId, title, version);
	}

	@Override
	public com.liferay.portlet.wiki.model.WikiPage fetchPage(long nodeId,
		java.lang.String title, double version)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wikiPageService.fetchPage(nodeId, title, version);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _wikiPageService.getBeanIdentifier();
	}

	@Override
	public java.util.List<com.liferay.portlet.wiki.model.WikiPage> getChildren(
		long groupId, long nodeId, boolean head, java.lang.String parentTitle)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wikiPageService.getChildren(groupId, nodeId, head, parentTitle);
	}

	@Override
	public com.liferay.portlet.wiki.model.WikiPage getDraftPage(long nodeId,
		java.lang.String title)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wikiPageService.getDraftPage(nodeId, title);
	}

	@Override
	public java.util.List<com.liferay.portlet.wiki.model.WikiPage> getNodePages(
		long nodeId, int max)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wikiPageService.getNodePages(nodeId, max);
	}

	/**
	* @deprecated As of 6.2.0, replaced by {@link #getNodePagesRSS(long, int,
	String, double, String, String, String, String)}
	*/
	@Deprecated
	@Override
	public java.lang.String getNodePagesRSS(long nodeId, int max,
		java.lang.String type, double version, java.lang.String displayStyle,
		java.lang.String feedURL, java.lang.String entryURL)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wikiPageService.getNodePagesRSS(nodeId, max, type, version,
			displayStyle, feedURL, entryURL);
	}

	@Override
	public java.lang.String getNodePagesRSS(long nodeId, int max,
		java.lang.String type, double version, java.lang.String displayStyle,
		java.lang.String feedURL, java.lang.String entryURL,
		java.lang.String attachmentURLPrefix)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wikiPageService.getNodePagesRSS(nodeId, max, type, version,
			displayStyle, feedURL, entryURL, attachmentURLPrefix);
	}

	@Override
	public java.util.List<com.liferay.portlet.wiki.model.WikiPage> getOrphans(
		long groupId, long nodeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wikiPageService.getOrphans(groupId, nodeId);
	}

	@Override
	public com.liferay.portlet.wiki.model.WikiPage getPage(long groupId,
		long nodeId, java.lang.String title)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wikiPageService.getPage(groupId, nodeId, title);
	}

	@Override
	public com.liferay.portlet.wiki.model.WikiPage getPage(long nodeId,
		java.lang.String title)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wikiPageService.getPage(nodeId, title);
	}

	@Override
	public com.liferay.portlet.wiki.model.WikiPage getPage(long nodeId,
		java.lang.String title, java.lang.Boolean head)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wikiPageService.getPage(nodeId, title, head);
	}

	@Override
	public com.liferay.portlet.wiki.model.WikiPage getPage(long nodeId,
		java.lang.String title, double version)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wikiPageService.getPage(nodeId, title, version);
	}

	@Override
	public java.util.List<com.liferay.portlet.wiki.model.WikiPage> getPages(
		long groupId, long nodeId, boolean head, int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portlet.wiki.model.WikiPage> obc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wikiPageService.getPages(groupId, nodeId, head, status, start,
			end, obc);
	}

	@Override
	public java.util.List<com.liferay.portlet.wiki.model.WikiPage> getPages(
		long groupId, long userId, long nodeId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wikiPageService.getPages(groupId, userId, nodeId, status,
			start, end);
	}

	@Override
	public int getPagesCount(long groupId, long nodeId, boolean head)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wikiPageService.getPagesCount(groupId, nodeId, head);
	}

	@Override
	public int getPagesCount(long groupId, long userId, long nodeId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wikiPageService.getPagesCount(groupId, userId, nodeId, status);
	}

	@Override
	public java.lang.String getPagesRSS(long companyId, long nodeId,
		java.lang.String title, int max, java.lang.String type, double version,
		java.lang.String displayStyle, java.lang.String feedURL,
		java.lang.String entryURL, java.lang.String attachmentURLPrefix,
		java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wikiPageService.getPagesRSS(companyId, nodeId, title, max,
			type, version, displayStyle, feedURL, entryURL,
			attachmentURLPrefix, locale);
	}

	/**
	* @deprecated As of 6.2.0, replaced by {@link #getPagesRSS(long, long,
	String, int, String, double, String, String, String, String,
	java.util.Locale)}
	*/
	@Deprecated
	@Override
	public java.lang.String getPagesRSS(long companyId, long nodeId,
		java.lang.String title, int max, java.lang.String type, double version,
		java.lang.String displayStyle, java.lang.String feedURL,
		java.lang.String entryURL, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wikiPageService.getPagesRSS(companyId, nodeId, title, max,
			type, version, displayStyle, feedURL, entryURL, locale);
	}

	@Override
	public java.util.List<com.liferay.portlet.wiki.model.WikiPage> getRecentChanges(
		long groupId, long nodeId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wikiPageService.getRecentChanges(groupId, nodeId, start, end);
	}

	@Override
	public int getRecentChangesCount(long groupId, long nodeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wikiPageService.getRecentChangesCount(groupId, nodeId);
	}

	@Override
	public java.lang.String[] getTempFileNames(long nodeId,
		java.lang.String folderName)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wikiPageService.getTempFileNames(nodeId, folderName);
	}

	/**
	* @deprecated As of 6.2.0, replaced by {@link #renamePage(long, String,
	String, ServiceContext)}
	*/
	@Override
	public void movePage(long nodeId, java.lang.String title,
		java.lang.String newTitle,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		_wikiPageService.movePage(nodeId, title, newTitle, serviceContext);
	}

	@Override
	public com.liferay.portal.kernel.repository.model.FileEntry movePageAttachmentToTrash(
		long nodeId, java.lang.String title, java.lang.String fileName)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wikiPageService.movePageAttachmentToTrash(nodeId, title,
			fileName);
	}

	@Override
	public com.liferay.portlet.wiki.model.WikiPage movePageToTrash(
		long nodeId, java.lang.String title)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wikiPageService.movePageToTrash(nodeId, title);
	}

	@Override
	public com.liferay.portlet.wiki.model.WikiPage movePageToTrash(
		long nodeId, java.lang.String title, double version)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wikiPageService.movePageToTrash(nodeId, title, version);
	}

	@Override
	public void renamePage(long nodeId, java.lang.String title,
		java.lang.String newTitle,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		_wikiPageService.renamePage(nodeId, title, newTitle, serviceContext);
	}

	@Override
	public void restorePageAttachmentFromTrash(long nodeId,
		java.lang.String title, java.lang.String fileName)
		throws com.liferay.portal.kernel.exception.PortalException {
		_wikiPageService.restorePageAttachmentFromTrash(nodeId, title, fileName);
	}

	@Override
	public void restorePageFromTrash(long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException {
		_wikiPageService.restorePageFromTrash(resourcePrimKey);
	}

	@Override
	public com.liferay.portlet.wiki.model.WikiPage revertPage(long nodeId,
		java.lang.String title, double version,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wikiPageService.revertPage(nodeId, title, version,
			serviceContext);
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_wikiPageService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public void subscribePage(long nodeId, java.lang.String title)
		throws com.liferay.portal.kernel.exception.PortalException {
		_wikiPageService.subscribePage(nodeId, title);
	}

	@Override
	public void unsubscribePage(long nodeId, java.lang.String title)
		throws com.liferay.portal.kernel.exception.PortalException {
		_wikiPageService.unsubscribePage(nodeId, title);
	}

	@Override
	public com.liferay.portlet.wiki.model.WikiPage updatePage(long nodeId,
		java.lang.String title, double version, java.lang.String content,
		java.lang.String summary, boolean minorEdit, java.lang.String format,
		java.lang.String parentTitle, java.lang.String redirectTitle,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wikiPageService.updatePage(nodeId, title, version, content,
			summary, minorEdit, format, parentTitle, redirectTitle,
			serviceContext);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	@Deprecated
	public WikiPageService getWrappedWikiPageService() {
		return _wikiPageService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	@Deprecated
	public void setWrappedWikiPageService(WikiPageService wikiPageService) {
		_wikiPageService = wikiPageService;
	}

	@Override
	public WikiPageService getWrappedService() {
		return _wikiPageService;
	}

	@Override
	public void setWrappedService(WikiPageService wikiPageService) {
		_wikiPageService = wikiPageService;
	}

	private WikiPageService _wikiPageService;
}