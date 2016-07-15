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

package com.liferay.saml.web.path;

import org.osgi.service.component.annotations.Component;

/**
 * @author Mika Koivisto
 */
@Component(
	immediate = true,
	property = {
		"auth.public.path=/portal/saml/acs",
		"auth.public.path=/portal/saml/auth_redirect",
		"auth.public.path=/portal/saml/keep_alive",
		"auth.public.path=/portal/saml/metadata",
		"auth.public.path=/portal/saml/slo_logout",
		"auth.public.path=/portal/saml/slo_redirect",
		"auth.public.path=/portal/saml/slo_soap",
		"auth.public.path=/portal/saml/sso"
	},
	service = Object.class
)
public class AuthPublicPath {
}