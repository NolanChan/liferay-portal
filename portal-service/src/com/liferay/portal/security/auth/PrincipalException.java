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

package com.liferay.portal.security.auth;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 */
public class PrincipalException extends PortalException {

	public static Class<?>[] getNestedClasses() {
		return _NESTED_CLASSES;
	}

	public PrincipalException() {
	}

	public PrincipalException(String msg) {
		super(msg);
	}

	public PrincipalException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public PrincipalException(Throwable cause) {
		super(cause);
	}

	private static final Class<?>[] _NESTED_CLASSES = {
		PrincipalException.class
	};

}