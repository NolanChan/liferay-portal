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

package com.liferay.saml.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.saml.model.SamlSpAuthRequest;
import com.liferay.saml.service.base.SamlSpAuthRequestLocalServiceBaseImpl;
import com.liferay.saml.util.PortletPropsValues;

import java.util.Date;

/**
 * @author Mika Koivisto
 */
public class SamlSpAuthRequestLocalServiceImpl
	extends SamlSpAuthRequestLocalServiceBaseImpl {

	@Override
	public SamlSpAuthRequest addSamlSpAuthRequest(
		String samlIdpEntityId, String samlSpAuthRequestKey,
		ServiceContext serviceContext) {

		long samlSpAuthRequestId = counterLocalService.increment(
			SamlSpAuthRequest.class.getName());

		SamlSpAuthRequest samlSpAuthRequest =
			samlSpAuthRequestPersistence.create(samlSpAuthRequestId);

		samlSpAuthRequest.setCompanyId(serviceContext.getCompanyId());
		samlSpAuthRequest.setCreateDate(new Date());
		samlSpAuthRequest.setSamlIdpEntityId(samlIdpEntityId);
		samlSpAuthRequest.setSamlSpAuthRequestKey(samlSpAuthRequestKey);

		samlSpAuthRequestPersistence.update(samlSpAuthRequest);

		return samlSpAuthRequest;
	}

	@Override
	public void deleteExpiredSamlSpAuthRequests() {
		Date createDate = new Date();

		createDate.setTime(
			createDate.getTime() -
				PortletPropsValues.SAML_SP_AUTH_REQUEST_MAX_AGE);

		samlSpAuthRequestPersistence.removeByCreateDate(createDate);
	}

	@Override
	public SamlSpAuthRequest fetchSamlSpAuthRequest(
		String samlIdpEntityId, String samlSpAuthRequestKey) {

		return samlSpAuthRequestPersistence.fetchBySIEI_SSARK(
			samlIdpEntityId, samlSpAuthRequestKey);
	}

	@Override
	public SamlSpAuthRequest getSamlSpAuthRequest(
			String samlIdpEntityId, String samlSpAuthRequestKey)
		throws PortalException {

		return samlSpAuthRequestPersistence.findBySIEI_SSARK(
			samlIdpEntityId, samlSpAuthRequestKey);
	}

}