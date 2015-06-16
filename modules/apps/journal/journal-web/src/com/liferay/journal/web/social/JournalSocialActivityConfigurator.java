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

package com.liferay.journal.web.social;

import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portlet.social.util.SocialConfigurationUtil;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eduardo Garcia
 */
@Component(immediate = true, service = JournalSocialActivityConfigurator.class)
public class JournalSocialActivityConfigurator {

	@Activate
	protected void activate() throws Exception {
		String xml = new String(
			FileUtil.getBytes(
				getClass(), "/META-INF/social/liferay-social.xml"));

		SocialConfigurationUtil.read(
			getClass().getClassLoader(), new String[] {xml});
	}

	@Reference(target = "(original.bean=*)", unbind = "-")
	protected void setServletContext(ServletContext servletContext) {
	}

}