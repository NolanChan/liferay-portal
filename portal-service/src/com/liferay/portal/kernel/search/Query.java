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

package com.liferay.portal.kernel.search;

import com.liferay.portal.kernel.search.query.QueryVisitor;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 */
public interface Query extends Serializable {

	public static final float DEFAULT_BOOST = 1.0f;

	public <T> T accept(QueryVisitor<T> queryVisitor);

	public float getBoost();

	public QueryConfig getQueryConfig();

	public Object getWrappedQuery();

	public boolean isDefaultBoost();

	public void setBoost(float boost);

	public void setQueryConfig(QueryConfig queryConfig);

}