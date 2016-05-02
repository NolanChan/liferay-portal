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

import java.util.List;
import java.util.Map;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public interface LCSClusterNodePatches extends LCSClusterNode {

	public String getError();

	public List<String> getFixedIssues();

	public String getHashCode();

	public List<String> getInstallablePatchIds();

	public Map<String, Integer> getPatchIdsStatuses();

	public void setError(String error);

	public void setFixedIssues(List<String> fixedIssues);

	public void setHashCode(String hashCode);

	public void setInstallablePatchIds(List<String> installablePatchIds);

	public void setPatchIdsStatuses(Map<String, Integer> patchIdsStatuses);

}