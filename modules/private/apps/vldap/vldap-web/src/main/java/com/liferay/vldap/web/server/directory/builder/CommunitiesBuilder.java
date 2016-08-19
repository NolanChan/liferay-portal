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

import com.liferay.portal.kernel.model.Company;
import com.liferay.vldap.web.server.directory.FilterConstraint;
import com.liferay.vldap.web.server.directory.SearchBase;
import com.liferay.vldap.web.server.directory.ldap.CommunitiesDirectory;
import com.liferay.vldap.web.server.directory.ldap.Directory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public class CommunitiesBuilder extends DirectoryBuilder {

	public CommunitiesBuilder() {
		attributeValidator.addValidAttributeValues(
			"objectclass", "organizationalUnit", "top", "*");
		attributeValidator.addValidAttributeValues("ou", "Communities", "*");
	}

	@Override
	protected List<Directory> buildDirectories(
		SearchBase searchBase, List<FilterConstraint> filterConstraints) {

		List<Directory> directories = new ArrayList<>();

		for (Company company : searchBase.getCompanies()) {
			if (filterConstraints.isEmpty()) {
				Directory directory = new CommunitiesDirectory(
					searchBase.getTop(), company);

				directories.add(directory);
			}
			else {
				for (FilterConstraint filterConstraint : filterConstraints) {
					if (!isValidFilterConstraint(filterConstraint)) {
						continue;
					}

					Directory directory = new CommunitiesDirectory(
						searchBase.getTop(), company);

					directories.add(directory);

					break;
				}
			}
		}

		return directories;
	}

}