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

package com.liferay.vldap.web.server.directory.builder;

import com.liferay.vldap.web.server.directory.FilterConstraint;
import com.liferay.vldap.web.server.directory.SearchBase;
import com.liferay.vldap.web.server.directory.ldap.Directory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrew Betts
 */
public abstract class SingleDirectoryBuilder extends DirectoryBuilder {

	@Override
	public boolean isValidAttribute(String attributeId, String value) {
		return true;
	}

	@Override
	protected List<Directory> buildDirectories(
		SearchBase searchBase, List<FilterConstraint> filterConstraints) {

		List<Directory> directories = new ArrayList<>();

		Directory directory = getDirectory();

		directories.add(directory);

		return directories;
	}

	protected abstract Directory getDirectory();

}