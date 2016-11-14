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

package com.liferay.osb.lcs.internal.report;

import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.portal.kernel.exception.SystemException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ivica Cardic
 */
@Component(immediate = true, service = InvoiceNumberGenerator.class)
public class DefaultInvoiceNumberGenerator implements InvoiceNumberGenerator {

	@Override
	public long getInvoiceNumber() {
		try {
			return _counterLocalService.increment(
				InvoiceNumberGenerator.class.getName());
		}
		catch (SystemException se) {
			throw new RuntimeException(se);
		}
	}

	@Reference(unbind = "-")
	public void setCounterLocalService(
		CounterLocalService counterLocalService) {

		_counterLocalService = counterLocalService;
	}

	private CounterLocalService _counterLocalService;

}