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

package com.liferay.saml.web.hook.action;

import com.liferay.portal.kernel.struts.StrutsAction;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.saml.runtime.metadata.MetadataManager;
import com.liferay.saml.util.OpenSamlUtil;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.opensaml.saml2.metadata.EntityDescriptor;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Mika Koivisto
 */
@Component(
	immediate = true, property = {"path=/portal/saml/metadata"},
	service = StrutsAction.class
)
public class MetadataAction extends BaseSamlStrutsAction {

	@Override
	protected String doExecute(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		response.setContentType(ContentTypes.TEXT_XML);

		EntityDescriptor entityDescriptor =
			_metadataManager.getEntityDescriptor(request);

		String metadata = OpenSamlUtil.marshall(entityDescriptor);

		PrintWriter printWriter = response.getWriter();

		printWriter.print(metadata);

		return null;
	}

	@Reference
	private MetadataManager _metadataManager;

}