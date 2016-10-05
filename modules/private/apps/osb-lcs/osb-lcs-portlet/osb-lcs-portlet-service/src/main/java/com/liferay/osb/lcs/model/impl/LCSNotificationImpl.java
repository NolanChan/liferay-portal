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

package com.liferay.osb.lcs.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.lcs.model.LCSClusterEntry;
import com.liferay.osb.lcs.model.LCSClusterNode;
import com.liferay.osb.lcs.model.LCSProject;
import com.liferay.portal.kernel.util.PortalUtil;

/**
 * @author Igor Beslic
 */
@ProviderType
public class LCSNotificationImpl extends LCSNotificationBaseImpl {

	public LCSNotificationImpl() {
	}

	@Override
	public boolean isLCSClusterEntryNotification() {
		return hasClassName(LCSClusterEntry.class);
	}

	@Override
	public boolean isLCSClusterNodeNotification() {
		return hasClassName(LCSClusterNode.class);
	}

	@Override
	public boolean isLCSProjectNotification() {
		return hasClassName(LCSProject.class);
	}

	protected boolean hasClassName(Class<?> clazz) {
		long classNameId = getClassNameId();

		if (classNameId == PortalUtil.getClassNameId(clazz)) {
			return true;
		}
		else {
			return false;
		}
	}

}