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

package com.liferay.osb.lcs.nosql.model;

import java.util.Date;

/**
 * @author Riccardo Ferrari
 */
public interface LCSStats {

	public long getCompanyId();

	public long getGroupId();

	public String getKey();

	public Date getModifiedDate();

	public String getPartitionKey();

	public boolean isNew();

	public void setCompanyId(long companyId);

	public void setGroupId(long groupId);

	public void setKey(String key);

	public void setModifiedDate(Date modifiedDate);

	public void setNew(boolean n);

	public void setPartitionKey(String partitionKey);

}