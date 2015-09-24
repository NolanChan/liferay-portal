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
@Meta.OCD(
	id = "com.liferay.portal.ldap.configuration.LDAPConfiguration",
	localization = "content/Language"
)
public interface LDAPConfiguration {

	@Meta.AD(deflt = "false", required = false)
	public boolean exportEnabled();

	@Meta.AD(deflt = "true", required = false)
	public boolean exportGroupEnabled();

	@Meta.AD(deflt = "com.sun.jndi.ldap.LdapCtxFactory", required = false)
	public String factoryInitial();

	@Meta.AD(deflt = "false", required = false)
	public boolean importCreateRolePerGroup();

	@Meta.AD(deflt = "false", required = false)
	public boolean importEnabled();

	@Meta.AD(deflt = "true", required = false)
	public boolean importGroupCacheEnabled();

	@Meta.AD(deflt = "true", required = false)
	public boolean importGroupSearchFilterEnabled();

	@Meta.AD(deflt = "10", required = false)
	public int importInterval();

	@Meta.AD(deflt = "86400000", required = false)
	public long importLockExpirationTime();

	@Meta.AD(deflt = "user", optionValues = {"group", "user"}, required = false)
	public String importMethod();

	@Meta.AD(deflt = "false", required = false)
	public boolean importOnStartup();

	@Meta.AD(deflt = "false", required = false)
	public boolean importUserPasswordAutogenerated();

	@Meta.AD(deflt = "test", required = false)
	public String importUserPasswordDefault();

	@Meta.AD(deflt = "true", required = false)
	public boolean importUserPasswordEnabled();

	@Meta.AD(
		deflt = "auth-type", optionValues = {"auth-type", "uuid"},
		required = false
	)
	public String importUserSyncStrategy();

	@Meta.AD(deflt = "1000", required = false)
	public int pageSize();

	@Meta.AD(deflt = "false", required = false)
	public boolean passwordPolicyEnabled();

	@Meta.AD(deflt = "1000", required = false)
	public int rangeSize();

	@Meta.AD(
		deflt = "follow", optionValues = {"follow", "ignore", "throws"},
		required = false
	)
	public String referral();

	@Meta.AD(deflt = "", required = false)
	public String[] userIgnoreAttributes();

}