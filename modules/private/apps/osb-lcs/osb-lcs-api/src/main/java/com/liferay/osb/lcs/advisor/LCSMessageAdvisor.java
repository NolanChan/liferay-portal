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

package com.liferay.osb.lcs.advisor;

import com.liferay.lcs.notification.LCSEventType;
import com.liferay.osb.lcs.model.LCSClusterNode;
import com.liferay.osb.lcs.model.LCSMessage;
import com.liferay.portal.kernel.exception.PortalException;

import org.osgi.service.component.annotations.Component;

/**
 * @author Igor Beslic
 */
@Component(immediate = true)
public interface LCSMessageAdvisor {

	public void addClusterLCSMessage(
			boolean cluster, LCSClusterNode lcsClusterNode,
			LCSEventType lcsEventType)
		throws PortalException;

	public LCSMessage addLCSClusterEntryLCSMessage(
			boolean generateUserLCSMessages, long lcsClusterEntryId,
			LCSEventType lcsEventType)
		throws PortalException;

	public void addLCSMessage(
			boolean cluster, LCSClusterNode lcsClusterNode,
			LCSEventType lcsEventType)
		throws PortalException;

	public LCSMessage addLCSProjectLCSMessage(
			boolean adminsOnly, String content, boolean generateUserLCSMessages,
			LCSEventType lcsEventType, long lcsProjectId)
		throws PortalException;

}