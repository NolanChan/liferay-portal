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

package com.liferay.osb.ldn.documentation.project.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the DocumentationProject service. Represents a row in the &quot;OSB_LDN_DocumentationProject&quot; database table, with each column mapped to a property of this class.
 *
 * @author Ryan Park
 * @see DocumentationProjectModel
 * @see com.liferay.osb.ldn.documentation.project.model.impl.DocumentationProjectImpl
 * @see com.liferay.osb.ldn.documentation.project.model.impl.DocumentationProjectModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.osb.ldn.documentation.project.model.impl.DocumentationProjectImpl")
@ProviderType
public interface DocumentationProject extends DocumentationProjectModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.ldn.documentation.project.model.impl.DocumentationProjectImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<DocumentationProject, Long> DOCUMENTATION_PROJECT_ID_ACCESSOR =
		new Accessor<DocumentationProject, Long>() {
			@Override
			public Long get(DocumentationProject documentationProject) {
				return documentationProject.getDocumentationProjectId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<DocumentationProject> getTypeClass() {
				return DocumentationProject.class;
			}
		};

	public DocumentationProjectTypeSettings getDocumentationProjectTypeSettings();

	public java.io.InputStream getIconInputStream()
		throws com.liferay.portal.kernel.exception.PortalException;
}