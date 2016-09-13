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
package com.liferay.osb.lcs.exception;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Igor Beslic
 */
@ProviderType
public class NoSuchLCSProjectException extends NoSuchModelException {

	public NoSuchLCSProjectException() {
	}

	public NoSuchLCSProjectException(String msg) {
		super(msg);
	}

	public NoSuchLCSProjectException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchLCSProjectException(Throwable cause) {
		super(cause);
	}

}