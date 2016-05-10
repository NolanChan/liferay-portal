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

package com.liferay.comment.page.comments.web.upgrade;

import com.liferay.comment.page.comments.web.constants.PageCommentsPortletKeys;
import com.liferay.portal.kernel.upgrade.BaseUpgradePortletId;
import com.liferay.portal.kernel.upgrade.DummyUpgradeStep;
import com.liferay.portal.kernel.upgrade.UpgradeStep;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.osgi.service.component.annotations.Component;

/**
 * @author Adolfo Pérez
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class PageCommentsWebUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register(
			"com.liferay.comment.page.comments.web", "0.0.0", "1.0.0",
			new DummyUpgradeStep());

		UpgradeStep upgradePortletId = new BaseUpgradePortletId() {

			@Override
			protected String[][] getRenamePortletIdsArray() {
				return new String[][] {
					new String[] {"107", PageCommentsPortletKeys.PAGE_COMMENTS}
				};
			}

		};

		registry.register(
			"com.liferay.comment.page.comments.web", "0.0.1", "1.0.0",
			upgradePortletId);
	}

}