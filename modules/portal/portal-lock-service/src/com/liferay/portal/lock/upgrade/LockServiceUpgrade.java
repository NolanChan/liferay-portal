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

package com.liferay.portal.lock.upgrade;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.lock.upgrade.v1_0_0.UpgradeLock;
import com.liferay.portal.service.ReleaseLocalService;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import org.springframework.context.ApplicationContext;

/**
 * @author Miguel Pastor
 */
@Component(immediate = true, service = LockServiceUpgrade.class)
public class LockServiceUpgrade {

	@Reference(
		target =
			"(org.springframework.context.service.name=" +
				"com.liferay.portal.lock.service)",
		unbind = "-"
	)
	protected void setApplicationContext(
		ApplicationContext applicationContext) {
	}

	@Reference(unbind = "-")
	protected void setReleaseLocalService(
		ReleaseLocalService releaseLocalService) {

		_releaseLocalService = releaseLocalService;
	}

	@Activate
	protected void upgrade() throws PortalException {
		List<UpgradeProcess> upgradeProcesses = new ArrayList<>();

		upgradeProcesses.add(new UpgradeLock());

		_releaseLocalService.updateRelease(
			"com.liferay.portal.lock.service", upgradeProcesses, 1, 1, false);
	}

	private ReleaseLocalService _releaseLocalService;

}