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

package com.liferay.portal.kernel.repository.capabilities;

import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Iván Zaera
 */
public class UnsupportedCapabilityException extends SystemException {

	public UnsupportedCapabilityException(
		Class<? extends Capability> capabilityClass,
		String providerDescription) {

		super(
			String.format(
				"%s does not support capability %s", providerDescription,
				capabilityClass.getName()));
	}

}