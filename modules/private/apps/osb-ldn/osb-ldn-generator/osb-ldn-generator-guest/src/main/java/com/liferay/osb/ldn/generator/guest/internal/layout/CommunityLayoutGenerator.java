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
import com.liferay.portal.kernel.model.LayoutConstants;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Yury Butrymovich
 */
@Component(
	immediate = true,
	property = {
		"osb.ldn.layout.description=", "osb.ldn.layout.friendly.url=/community",
		"osb.ldn.layout.hidden:Boolean=false", "osb.ldn.layout.name=Community",
		"osb.ldn.layout.order:Integer=4", "osb.ldn.layout.title=Community",
		"osb.ldn.layout.type=" + LayoutConstants.TYPE_PORTLET,
		"osb.ldn.site.generator.key=" + GuestSiteConstants.GUEST_SITE_KEY
	},
	service = LayoutGenerator.class
)
public class CommunityLayoutGenerator extends BaseLayoutGenerator {

	public static final int LAYOUT_VERSION = 1;

	@Override
	public int getLayoutVersion() {
		return LAYOUT_VERSION;
	}

	@Override
	protected void doGenerate(long plid) throws Exception {
	}

	@Reference
	private void setLayoutVersion(LayoutVersion layoutVersion) {
		this.layoutVersion = layoutVersion;
	}

}