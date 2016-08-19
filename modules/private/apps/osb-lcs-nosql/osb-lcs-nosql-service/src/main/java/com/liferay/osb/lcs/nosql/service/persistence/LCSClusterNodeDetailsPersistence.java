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

package com.liferay.osb.lcs.nosql.service.persistence;

import com.liferay.osb.lcs.nosql.model.LCSClusterNodeDetails;

import java.util.Date;
import java.util.List;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public interface LCSClusterNodeDetailsPersistence {

	public LCSClusterNodeDetails create();

	public void delete(String key);

	public LCSClusterNodeDetails fetchByKey(String key);

	public List<LCSClusterNodeDetails> findByActive();

	public List<LCSClusterNodeDetails> findByStatus(int status);

	public LCSClusterNodeDetails update(
		LCSClusterNodeDetails lcsClusterNodeDetails);

	public void updateConfigurationModifiedDate(
		Date configurationModifiedDate, String key, Date modifiedDate);

	public void updateLastHeartbeatColumn(
		String key, long lastHeartbeat, Date modifiedDate);

	public void updateStatusColumn(String key, Date modifiedDate, int status);

}