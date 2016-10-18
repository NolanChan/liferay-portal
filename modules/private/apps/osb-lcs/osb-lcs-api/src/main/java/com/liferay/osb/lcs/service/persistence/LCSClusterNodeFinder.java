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

package com.liferay.osb.lcs.service.persistence;

import aQute.bnd.annotation.ProviderType;

/**
 * @author Igor Beslic
 * @generated
 */
@ProviderType
public interface LCSClusterNodeFinder {
	public java.util.List<com.liferay.osb.lcs.model.LCSClusterNode> findByLCEN_LPN(
		java.lang.String lcsClusterEntryName, java.lang.String lcsProjectName,
		boolean andOperator, int start, int end);

	public java.util.List<com.liferay.osb.lcs.model.LCSClusterNode> findByU_A(
		long userId, boolean archived, int start, int end);
}