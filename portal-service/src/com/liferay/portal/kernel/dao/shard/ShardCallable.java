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

package com.liferay.portal.kernel.dao.shard;

import java.util.concurrent.Callable;

/**
 * @author Alexander Chow
 */
public abstract class ShardCallable<V> implements Callable<V> {

	public ShardCallable(long companyId) {
		_companyId = companyId;
	}

	@Override
	public V call() throws Exception {
		ShardUtil.pushCompanyService(_companyId);

		try {
			return doCall();
		}
		finally {
			ShardUtil.popCompanyService();
		}
	}

	protected abstract V doCall() throws Exception;

	private final long _companyId;

}