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

package com.liferay.osb.ldn.generator.site;

import com.liferay.portal.kernel.util.GetterUtil;

import java.util.Map;

/**
 * @author Ryan Park
 */
public abstract class BaseSiteGenerator implements SiteGenerator {

	@Override
	public String getKey() {
		return _key;
	}

	@Override
	public String getName() {
		return _name;
	}

	protected void activate(Map<String, Object> config) {
		_key = GetterUtil.getString(config.get("osb.ldn.site.generator.key"));
		_name = GetterUtil.getString(config.get("osb.ldn.site.generator.name"));
	}

	private String _key;
	private String _name;

}