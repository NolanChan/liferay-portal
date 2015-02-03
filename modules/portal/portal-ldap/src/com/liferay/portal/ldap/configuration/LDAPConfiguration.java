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

package com.liferay.portal.ldap.configuration;

import aQute.bnd.annotation.metatype.Meta;

/**
 * @author Michael C. Han
 */
@Meta.OCD(id = "com.liferay.portal.ldap", localization = "content.Language")
public interface LDAPConfiguration {

	@Meta.AD(deflt = "false", id = "export.enabled", required = false)
	public boolean exportEnabled();

	@Meta.AD(deflt = "true", id = "export.group.enabled", required = false)
	public boolean exportGroupEnabled();

	@Meta.AD(
		deflt = "com.sun.jndi.ldap.LdapCtxFactory", id = "factory.initial",
		required = false
	)
	public String factoryInitial();

	@Meta.AD(
		deflt = "false", id = "import.create.role.per.group", required = false
	)
	public boolean importCreateRolePerGroup();

	@Meta.AD(deflt = "false", id = "import.enabled", required = false)
	public boolean importEnabled();

	@Meta.AD(
		deflt = "true", id = "import.group.cache.enabled", required = false
	)
	public boolean importGroupCacheEnabled();

	@Meta.AD(
		deflt = "true", id = "import.group.search.filter.enabled",
		required = false
	)
	public boolean importGroupSearchFilterEnabled();

	@Meta.AD(deflt = "10", id = "import.interval", required = false)
	public int importInterval();

	@Meta.AD(
		deflt = "86400000", id = "import.lock.expiration.time", required = false
	)
	public long importLockExpirationTime();

	@Meta.AD(
		deflt = "user", id = "import.method", optionValues = {"group", "user"},
		required = false
	)
	public String importMethod();

	@Meta.AD(deflt = "false", id = "import.on.startup", required = false)
	public boolean importOnStartup();

	@Meta.AD(
		deflt = "false",id = "import.user.password.autogenerated",
		required = false
	)
	public boolean importUserPasswordAutogenerated();

	@Meta.AD(
		deflt = "test", id = "import.user.password.default", required = false
	)
	public String importUserPasswordDefault();

	@Meta.AD(
		deflt = "true", id = "import.user.password.enabled", required = false
	)
	public boolean importUserPasswordEnabled();

	@Meta.AD(deflt = "1000", id = "page.size", required = false)
	public int pageSize();

	@Meta.AD(
		deflt = "false", id = "password.policy.enabled", required = false
	)
	public boolean passwordPolicyEnabled();

	@Meta.AD(deflt = "1000", id = "range.size", required = false)
	public int rangeSize();

	@Meta.AD(
		deflt = "follow", id = "referral",
		optionValues = {"follow", "ingore", "throws"}, required = false
	)
	public String referral();

	@Meta.AD(id = "user.ignore.attributes", required = false)
	public String[] userIgnoreAttributes();

}