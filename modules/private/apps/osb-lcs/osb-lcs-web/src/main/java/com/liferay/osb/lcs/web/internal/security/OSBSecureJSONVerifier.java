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

package com.liferay.osb.lcs.web.internal.security;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.AccessControlContext;
import com.liferay.portal.kernel.security.auth.AuthException;
import com.liferay.portal.kernel.security.auth.verifier.AuthVerifier;
import com.liferay.portal.kernel.security.auth.verifier.AuthVerifierResult;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.PortalUtil;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ivica Cardic
 */
@Component(
	immediate = true,
	property = {
		"auth.verifier.OSBSecureJSONVerifier.api.token=jujUBL6GSnjfNmmDAs",
		"auth.verifier.OSBSecureJSONVerifier.urls.includes=/api/jsonws/osb-lcs-portlet.lcsclusternode/update-build-number,/api/jsonws/osb-lcs-portlet.lcsclusternode/verify-lcs-cluster-node-cluster-link,/api/jsonws/osb-lcs-portlet.lcsclusternodeuptime/*,/api/jsonws/osb-lcs-portlet.lcspatchingadvisor/*,/api/jsonws/osb-lcs-portlet.lcsmembers/*,/api/jsonws/osb-lcs-portlet.lcsmessage/*,/api/jsonws/osb-lcs-portlet.lcssubscriptionentry/add-corp-project-lcs-subscription-entries"
	}
)
public class OSBSecureJSONVerifier implements AuthVerifier {

	@Override
	public String getAuthType() {
		return OSBSecureJSONVerifier.class.getName();
	}

	@Override
	public AuthVerifierResult verify(
			AccessControlContext accessControlContext, Properties properties)
		throws AuthException {

		AuthVerifierResult authVerifierResult = new AuthVerifierResult();

		HttpServletRequest request = accessControlContext.getRequest();

		String requestToken = request.getHeader("OSB_LCS_API_Token");

		if (requestToken == null) {
			return authVerifierResult;
		}

		String apiToken = properties.getProperty("api.token");

		if (requestToken.equals(apiToken)) {
			authVerifierResult.setState(AuthVerifierResult.State.SUCCESS);

			User user = null;

			try {
				user = _userLocalService.fetchUserByEmailAddress(
					PortalUtil.getDefaultCompanyId(), "system@liferay.com");
			}
			catch (Exception e) {
				throw new AuthException(e);
			}

			authVerifierResult.setUserId(user.getUserId());
		}
		else {
			authVerifierResult.setState(
				AuthVerifierResult.State.INVALID_CREDENTIALS);
		}

		return authVerifierResult;
	}

	@Reference(unbind = "-")
	protected void setUserLocalService(UserLocalService userLocalService) {
		_userLocalService = userLocalService;
	}

	private UserLocalService _userLocalService;

}