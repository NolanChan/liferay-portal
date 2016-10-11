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
 * The extended model interface for the LCSMetadata service. Represents a row in the &quot;OSBLCS_LCSMetadata&quot; database table, with each column mapped to a property of this class.
 *
 * @author Igor Beslic
 * @see LCSMetadataModel
 * @see com.liferay.osb.lcs.model.impl.LCSMetadataImpl
 * @see com.liferay.osb.lcs.model.impl.LCSMetadataModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.osb.lcs.model.impl.LCSMetadataImpl")
@ProviderType
public interface LCSMetadata extends LCSMetadataModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.lcs.model.impl.LCSMetadataImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<LCSMetadata, Long> LCS_METADATA_ID_ACCESSOR = new Accessor<LCSMetadata, Long>() {
			@Override
			public Long get(LCSMetadata lcsMetadata) {
				return lcsMetadata.getLcsMetadataId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<LCSMetadata> getTypeClass() {
				return LCSMetadata.class;
			}
		};
}