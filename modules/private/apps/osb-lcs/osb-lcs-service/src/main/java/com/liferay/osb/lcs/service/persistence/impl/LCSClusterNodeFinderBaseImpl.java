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

package com.liferay.osb.lcs.service.persistence.impl;

import com.liferay.osb.lcs.model.LCSClusterNode;
import com.liferay.osb.lcs.service.persistence.LCSClusterNodePersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;

import java.util.Set;

/**
 * @author Igor Beslic
 * @generated
 */
public class LCSClusterNodeFinderBaseImpl extends BasePersistenceImpl<LCSClusterNode> {
	@Override
	public Set<String> getBadColumnNames() {
		return getLCSClusterNodePersistence().getBadColumnNames();
	}

	/**
	 * Returns the l c s cluster node persistence.
	 *
	 * @return the l c s cluster node persistence
	 */
	public LCSClusterNodePersistence getLCSClusterNodePersistence() {
		return lcsClusterNodePersistence;
	}

	/**
	 * Sets the l c s cluster node persistence.
	 *
	 * @param lcsClusterNodePersistence the l c s cluster node persistence
	 */
	public void setLCSClusterNodePersistence(
		LCSClusterNodePersistence lcsClusterNodePersistence) {
		this.lcsClusterNodePersistence = lcsClusterNodePersistence;
	}

	@BeanReference(type = LCSClusterNodePersistence.class)
	protected LCSClusterNodePersistence lcsClusterNodePersistence;
}