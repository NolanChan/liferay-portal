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

package com.liferay.portal.security.ldap.exportimport.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;
import com.liferay.portal.security.ldap.configuration.CompanyScopedConfiguration;

/**
 * @author Michael C. Han
 */
@ExtendedObjectClassDefinition(
	category = "foundation", factoryInstanceLabelAttribute = "companyId",
	scope = ExtendedObjectClassDefinition.Scope.COMPANY
)
@Meta.OCD(
	factory = true,
	id = "com.liferay.portal.security.ldap.exportimport.configuration.LDAPImportConfiguration",
	localization = "content/Language", name = "ldap.import.configuration.name"
)
public interface LDAPImportConfiguration extends CompanyScopedConfiguration {

	@Meta.AD(deflt = "0", required = false)
	@Override
	public long companyId();

	@Meta.AD(deflt = "false", name = "enable-import", required = false)
	public boolean importEnabled();

	@Meta.AD(
		deflt = "false", name = "enable-import-on-startup", required = false
	)
	public boolean importOnStartup();

	@Meta.AD(deflt = "10", name = "import-interval", required = false)
	public int importInterval();

	@Meta.AD(
		deflt = "user", description = "import-method-help",
		name = "import-method", optionValues = {"group", "user"},
		required = false
	)
	public String importMethod();

	@Meta.AD(
		deflt = "86400000", description = "import-lock-expiration-time-help",
		name = "lock-expiration-time", required = false
	)
	public long importLockExpirationTime();

	@Meta.AD(
		deflt = "auth-type", optionValues = {"auth-type", "uuid"},
		required = false
	)
	public String importUserSyncStrategy();

	@Meta.AD(deflt = "true", name = "enable-user-password", required = false)
	public boolean importUserPasswordEnabled();

	@Meta.AD(
		deflt = "false", name = "autogenerate-user-password", required = false
	)
	public boolean importUserPasswordAutogenerated();

	@Meta.AD(deflt = "test", name = "default-user-password", required = false)
	public String importUserPasswordDefault();

	@Meta.AD(
		deflt = "true", description = "import-group-cache-enabled-help",
		name = "enable-group-cache", required = false
	)
	public boolean importGroupCacheEnabled();

	@Meta.AD(
		deflt = "false", description = "import-create-role-per-group-help",
		name = "create-role-per-group", required = false
	)
	public boolean importCreateRolePerGroup();

}