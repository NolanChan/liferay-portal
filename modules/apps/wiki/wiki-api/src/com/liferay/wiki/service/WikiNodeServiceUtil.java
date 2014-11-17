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

package com.liferay.wiki.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for WikiNode. This utility wraps
 * {@link com.liferay.wiki.service.impl.WikiNodeServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see WikiNodeService
 * @see com.liferay.wiki.service.base.WikiNodeServiceBaseImpl
 * @see com.liferay.wiki.service.impl.WikiNodeServiceImpl
 * @generated
 */
@ProviderType
public class WikiNodeServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.wiki.service.impl.WikiNodeServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.wiki.model.WikiNode addNode(
		java.lang.String name, java.lang.String description,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().addNode(name, description, serviceContext);
	}

	public static void deleteNode(long nodeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteNode(nodeId);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	public static com.liferay.wiki.model.WikiNode getNode(long groupId,
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getNode(groupId, name);
	}

	public static com.liferay.wiki.model.WikiNode getNode(long nodeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getNode(nodeId);
	}

	public static java.util.List<com.liferay.wiki.model.WikiNode> getNodes(
		long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getNodes(groupId);
	}

	public static java.util.List<com.liferay.wiki.model.WikiNode> getNodes(
		long groupId, int start, int end) {
		return getService().getNodes(groupId, start, end);
	}

	public static java.util.List<com.liferay.wiki.model.WikiNode> getNodes(
		long groupId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getNodes(groupId, status);
	}

	public static java.util.List<com.liferay.wiki.model.WikiNode> getNodes(
		long groupId, int status, int start, int end) {
		return getService().getNodes(groupId, status, start, end);
	}

	public static int getNodesCount(long groupId) {
		return getService().getNodesCount(groupId);
	}

	public static int getNodesCount(long groupId, int status) {
		return getService().getNodesCount(groupId, status);
	}

	public static void importPages(long nodeId, java.lang.String importer,
		java.io.InputStream[] inputStreams,
		java.util.Map<java.lang.String, java.lang.String[]> options)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().importPages(nodeId, importer, inputStreams, options);
	}

	public static com.liferay.wiki.model.WikiNode moveNodeToTrash(long nodeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().moveNodeToTrash(nodeId);
	}

	public static void restoreNodeFromTrash(long nodeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().restoreNodeFromTrash(nodeId);
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static void subscribeNode(long nodeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().subscribeNode(nodeId);
	}

	public static void unsubscribeNode(long nodeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().unsubscribeNode(nodeId);
	}

	public static com.liferay.wiki.model.WikiNode updateNode(long nodeId,
		java.lang.String name, java.lang.String description,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateNode(nodeId, name, description, serviceContext);
	}

	public static WikiNodeService getService() {
		return _serviceTracker.getService();
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setService(WikiNodeService service) {
	}

	private static ServiceTracker<WikiNodeService, WikiNodeService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(WikiNodeServiceUtil.class);

		_serviceTracker = new ServiceTracker<WikiNodeService, WikiNodeService>(bundle.getBundleContext(),
				WikiNodeService.class, null);

		_serviceTracker.open();
	}
}