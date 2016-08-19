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

/**
 * @author Riccardo Ferrari
 */
public interface LCSClusterNodeSite extends LCSClusterNode {

	public long getCompanyId();

	public String getFriendlyURL();

	public long getGroupId();

	public String getGroupUUID();

	public long getInstallationId();

	public String getName();

	public int getType();

	public void setCompanyId(long companyId);

	public void setFriendlyURL(String friendlyURL);

	public void setGroupId(long groupId);

	public void setGroupUUID(String groupUUID);

	public void setInstallationId(long installationId);

	public void setName(String name);

	public void setType(int type);

}