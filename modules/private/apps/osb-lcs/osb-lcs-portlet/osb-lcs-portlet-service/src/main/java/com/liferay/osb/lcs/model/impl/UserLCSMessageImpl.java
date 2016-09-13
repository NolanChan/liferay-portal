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

package com.liferay.osb.lcs.model.impl;

import aQute.bnd.annotation.ProviderType;
import com.liferay.osb.lcs.model.LCSMessage;
import com.liferay.osb.lcs.service.LCSMessageLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Igor Beslic
 */
@ProviderType
public class UserLCSMessageImpl extends UserLCSMessageBaseImpl {

	public UserLCSMessageImpl() {
	}

	public LCSMessage getLcsMessage() throws PortalException {
		if (_lcsMessage != null) {
			return _lcsMessage;
		}

		_lcsMessage = LCSMessageLocalServiceUtil.getLCSMessage(
			getLcsMessageId());

		return _lcsMessage;
	}

	private LCSMessage _lcsMessage;

}