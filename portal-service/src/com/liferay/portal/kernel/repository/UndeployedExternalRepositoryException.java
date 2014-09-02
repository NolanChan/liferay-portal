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

package com.liferay.portal.kernel.repository;

import com.liferay.portal.model.ClassName;

/**
 * @author Ivan Zaera
 */
public class UndeployedExternalRepositoryException extends RepositoryException {

	public UndeployedExternalRepositoryException(ClassName className) {
		super(
			"Unable to load external repository of class " +
				className.getClassName() + " because its plugin is currently " +
				"not deployed");

		_className = className;
	}

	public ClassName getClassName() {
		return _className;
	}

	private ClassName _className;

}