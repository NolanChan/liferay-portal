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

package com.liferay.osb.lcs.advisor.impl;

import com.liferay.osb.lcs.advisor.LCSClusterNodeAdvisor;
import com.liferay.osb.lcs.advisor.PortalPropertiesAdvisor;
import com.liferay.osb.lcs.model.LCSClusterNode;
import com.liferay.osb.lcs.service.LCSClusterNodeService;
import com.liferay.portal.kernel.exception.PortalException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Igor Beslic
 */
@Component(immediate = true, service = LCSClusterNodeAdvisor.class)
public class LCSClusterNodeAdvisorImpl implements LCSClusterNodeAdvisor {

	@Override
	public LCSClusterNode deleteLCSClusterNode(long lcsClusterNodeId)
		throws PortalException {

		LCSClusterNode lcsClusterNode =
			_lcsClusterNodeService.deleteLCSClusterNode(lcsClusterNodeId);

		_portalPropertiesAdvisor.processLCSClusterEntryPropertyDifferences(
			lcsClusterNode);

		return lcsClusterNode;
	}

	@Reference(bind = "-")
	private LCSClusterNodeService _lcsClusterNodeService;

	@Reference
	private PortalPropertiesAdvisor _portalPropertiesAdvisor;

}