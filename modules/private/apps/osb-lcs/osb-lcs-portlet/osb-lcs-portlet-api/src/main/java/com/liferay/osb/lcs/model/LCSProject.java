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
 * The extended model interface for the LCSProject service. Represents a row in the &quot;OSBLCS_LCSProject&quot; database table, with each column mapped to a property of this class.
 *
 * @author Igor Beslic
 * @see LCSProjectModel
 * @see com.liferay.osb.lcs.model.impl.LCSProjectImpl
 * @see com.liferay.osb.lcs.model.impl.LCSProjectModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.osb.lcs.model.impl.LCSProjectImpl")
@ProviderType
public interface LCSProject extends LCSProjectModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.lcs.model.impl.LCSProjectImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<LCSProject, Long> LCS_PROJECT_ID_ACCESSOR = new Accessor<LCSProject, Long>() {
			@Override
			public Long get(LCSProject lcsProject) {
				return lcsProject.getLcsProjectId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<LCSProject> getTypeClass() {
				return LCSProject.class;
			}
		};
}