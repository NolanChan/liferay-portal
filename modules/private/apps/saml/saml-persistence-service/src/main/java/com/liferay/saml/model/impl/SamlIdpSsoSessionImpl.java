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

package com.liferay.saml.model.impl;

import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.spring.extender.service.ServiceReference;
import com.liferay.saml.metadata.MetadataManager;

import java.util.Date;

/**
 * @author Mika Koivisto
 */
public class SamlIdpSsoSessionImpl extends SamlIdpSsoSessionBaseImpl {

	public SamlIdpSsoSessionImpl() {
	}

	@Override
	public boolean isExpired() {
		long samlIdpSessionMaximumAge = _metadataManager.getSessionMaximumAge();
		long samlIdpSessionTimeout = _metadataManager.getSessionTimeout();

		if (samlIdpSessionMaximumAge > 0) {
			Date createDate = getCreateDate();

			long expirationTime =
				createDate.getTime() + samlIdpSessionMaximumAge * Time.SECOND;

			if (System.currentTimeMillis() > expirationTime) {
				return true;
			}
		}

		if (samlIdpSessionTimeout <= 0) {
			return false;
		}

		Date modifiedDate = getModifiedDate();

		long expirationTime =
			modifiedDate.getTime() + samlIdpSessionTimeout * Time.SECOND;

		if (System.currentTimeMillis() > expirationTime) {
			return true;
		}
		else {
			return false;
		}
	}

	@ServiceReference(type = MetadataManager.class)
	private MetadataManager _metadataManager;

}