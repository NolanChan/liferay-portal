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

package com.liferay.osb.ldn.generator.layout;

import com.liferay.portal.kernel.util.GetterUtil;

import java.util.Map;

import org.osgi.framework.BundleContext;

/**
 * @author Yury Butrymovich
 * @author Ryan Park
 */
public abstract class BaseLayoutGenerator implements LayoutGenerator {

	@Override
	public void generate(long plid) throws Exception {
		if (layoutVersion.getLayoutVersion(plid) > getLayoutVersion()) {
			return;
		}

		doGenerate(plid);

		layoutVersion.setLayoutVersion(plid, getLayoutVersion());
	}

	@Override
	public String getLayoutDescription() {
		return _layoutDescription;
	}

	@Override
	public String getLayoutFriendlyURL() {
		return _layoutFriendlyURL;
	}

	@Override
	public boolean getLayoutHidden() {
		return _layoutHidden;
	}

	@Override
	public String getLayoutName() {
		return _layoutName;
	}

	@Override
	public String getLayoutTitle() {
		return _layoutTitle;
	}

	@Override
	public String getLayoutType() {
		return _layoutType;
	}

	public abstract int getLayoutVersion() throws Exception;

	protected void activate(
		BundleContext bundleContext, Map<String, Object> config) {

		this.bundleContext = bundleContext;

		_layoutDescription = GetterUtil.getString(
			config.get("osb.ldn.layout.description"));
		_layoutFriendlyURL = GetterUtil.getString(
			config.get("osb.ldn.layout.friendly.url"));
		_layoutHidden = GetterUtil.getBoolean(
			config.get("osb.ldn.layout.hidden"));
		_layoutName = GetterUtil.getString(config.get("osb.ldn.layout.name"));
		_layoutTitle = GetterUtil.getString(config.get("osb.ldn.layout.title"));
		_layoutType = GetterUtil.getString(config.get("osb.ldn.layout.type"));
	}

	protected abstract void doGenerate(long plid) throws Exception;

	protected BundleContext bundleContext;
	protected LayoutVersion layoutVersion;

	private String _layoutDescription;
	private String _layoutFriendlyURL;
	private boolean _layoutHidden;
	private String _layoutName;
	private String _layoutTitle;
	private String _layoutType;

}