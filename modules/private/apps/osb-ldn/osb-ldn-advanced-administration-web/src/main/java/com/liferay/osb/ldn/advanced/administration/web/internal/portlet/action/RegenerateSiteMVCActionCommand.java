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

package com.liferay.osb.ldn.advanced.administration.web.internal.portlet.action;

import com.liferay.osb.ldn.advanced.administration.web.internal.constants.AdvancedAdministrationPortletKeys;
import com.liferay.osb.ldn.generator.site.SiteGenerator;
import com.liferay.osb.ldn.generator.site.SiteGeneratorRegistry;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * @author Ryan Park
 */
@Component(
	property = {
		"javax.portlet.name=" + AdvancedAdministrationPortletKeys.ADVANCED_ADMINISTRATION,
		"mvc.command.name=/regenerate_site"
	},
	service = MVCActionCommand.class
)
public class RegenerateSiteMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long groupId = ParamUtil.getLong(actionRequest, "groupId");
		String siteGeneratorKey = ParamUtil.getString(
			actionRequest, "siteGeneratorKey");

		SiteGenerator siteGenerator = _siteGeneratorRegistry.getSiteGenerator(
			siteGeneratorKey);

		siteGenerator.generate(groupId);
	}

	@Reference
	private SiteGeneratorRegistry _siteGeneratorRegistry;

}
