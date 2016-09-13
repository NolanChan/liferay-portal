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

package com.liferay.osb.lcs.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the LCSClusterNode service. Represents a row in the &quot;OSBLCS_LCSClusterNode&quot; database table, with each column mapped to a property of this class.
 *
 * @author Igor Beslic
 * @see LCSClusterNodeModel
 * @see com.liferay.osb.lcs.model.impl.LCSClusterNodeImpl
 * @see com.liferay.osb.lcs.model.impl.LCSClusterNodeModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.osb.lcs.model.impl.LCSClusterNodeImpl")
@ProviderType
public interface LCSClusterNode extends LCSClusterNodeModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.lcs.model.impl.LCSClusterNodeImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<LCSClusterNode, Long> LCS_CLUSTER_NODE_ID_ACCESSOR =
		new Accessor<LCSClusterNode, Long>() {
			@Override
			public Long get(LCSClusterNode lcsClusterNode) {
				return lcsClusterNode.getLcsClusterNodeId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<LCSClusterNode> getTypeClass() {
				return LCSClusterNode.class;
			}
		};
}