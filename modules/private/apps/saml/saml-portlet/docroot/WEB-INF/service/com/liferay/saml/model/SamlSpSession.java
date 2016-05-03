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

package com.liferay.saml.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the SamlSpSession service. Represents a row in the &quot;SamlSpSession&quot; database table, with each column mapped to a property of this class.
 *
 * @author Mika Koivisto
 * @see SamlSpSessionModel
 * @see com.liferay.saml.model.impl.SamlSpSessionImpl
 * @see com.liferay.saml.model.impl.SamlSpSessionModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.saml.model.impl.SamlSpSessionImpl")
@ProviderType
public interface SamlSpSession extends SamlSpSessionModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.saml.model.impl.SamlSpSessionImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<SamlSpSession, Long> SAML_SP_SESSION_ID_ACCESSOR =
		new Accessor<SamlSpSession, Long>() {
			@Override
			public Long get(SamlSpSession samlSpSession) {
				return samlSpSession.getSamlSpSessionId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<SamlSpSession> getTypeClass() {
				return SamlSpSession.class;
			}
		};
}