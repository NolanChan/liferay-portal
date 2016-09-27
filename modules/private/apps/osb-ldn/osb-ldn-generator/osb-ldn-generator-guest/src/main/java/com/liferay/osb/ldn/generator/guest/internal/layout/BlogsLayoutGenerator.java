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

package com.liferay.osb.ldn.generator.guest.internal.layout;

import com.liferay.osb.ldn.generator.guest.site.constants.GuestSiteConstants;
import com.liferay.osb.ldn.generator.layout.BaseLayoutGenerator;
import com.liferay.osb.ldn.generator.layout.LayoutGenerator;
import com.liferay.osb.ldn.generator.layout.LayoutVersion;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.util.StringPool;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Yury Butrymovich
 */
@Component(
	immediate = true,
	property = {
		"osb.ldn.layout.description=", "osb.ldn.layout.friendly.url=/blogs",
		"osb.ldn.layout.hidden:Boolean=false", "osb.ldn.layout.name=Blogs",
		"osb.ldn.layout.order:Integer=5", "osb.ldn.layout.title=Blogs",
		"osb.ldn.layout.type=" + LayoutConstants.TYPE_PORTLET,
		"osb.ldn.site.generator.key=" + GuestSiteConstants.GUEST_SITE_KEY
	},
	service = LayoutGenerator.class
)
public class BlogsLayoutGenerator extends BaseLayoutGenerator {

	public static final int LAYOUT_VERSION = 1;

	@Override
	public int getLayoutVersion() {
		return LAYOUT_VERSION;
	}

	@Override
	protected void doGenerate(long layoutId) throws Exception {
		Layout layout = _layoutLocalService.getLayout(layoutId);

		layout.setTypeSettings(StringPool.BLANK);

		_layoutLocalService.updateLayout(
			layout.getGroupId(), layout.getPrivateLayout(),
			layout.getLayoutId(), layout.getTypeSettings());
	}

	@Reference
	private void setLayoutVersion(LayoutVersion layoutVersion) {
		this.layoutVersion = layoutVersion;
	}

	@Reference
	private LayoutLocalService _layoutLocalService;

}