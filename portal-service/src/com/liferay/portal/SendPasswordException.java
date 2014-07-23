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
import com.liferay.portal.model.Company;

/**
 * @author Brian Wing Shun Chan
 * @author Jose M. Navarro
 */
public class SendPasswordException extends PortalException {

	public static class MustBeEnabled extends SendPasswordException {

		public MustBeEnabled(Company company) {
			super(String.format(
				"The 'forgot password' notification must be enabled for " +
					"company %s",
				company));

			_company = company;
		}

		public Company getCompany() {
			return _company;
		}

		private Company _company;

	}

	/**
	 * @deprecated As of 7.0.0, replaced by the inner classes
	 */
	public SendPasswordException() {
		super();
	}

	/**
	 * @deprecated As of 7.0.0, replaced by the inner classes
	 */
	public SendPasswordException(String msg, Throwable cause) {
		super(msg, cause);
	}

	/**
	 * @deprecated As of 7.0.0, replaced by the inner classes
	 */
	public SendPasswordException(Throwable cause) {
		super(cause);
	}

	protected SendPasswordException(String msg) {
		super(msg);
	}

}