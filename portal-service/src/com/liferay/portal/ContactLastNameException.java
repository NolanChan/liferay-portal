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

package com.liferay.portal;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 */
public class ContactLastNameException extends PortalException {

	/**
	 * @deprecated As of 7.0.0, replaced by the inner class in
	 * com.liferay.portal.ContactNameException
	 */
	public ContactLastNameException() {
	}

	/**
	 * @deprecated As of 7.0.0, replaced by the inner class in
	 * com.liferay.portal.ContactNameException
	 */
	public ContactLastNameException(String msg) {
		super(msg);
	}

	/**
	 * @deprecated As of 7.0.0, replaced by the inner class in
	 * com.liferay.portal.ContactNameException
	 */
	public ContactLastNameException(String msg, Throwable cause) {
		super(msg, cause);
	}

	/**
	 * @deprecated As of 7.0.0, replaced by the inner class in
	 * com.liferay.portal.ContactNameException
	 */
	public ContactLastNameException(Throwable cause) {
		super(cause);
	}

}