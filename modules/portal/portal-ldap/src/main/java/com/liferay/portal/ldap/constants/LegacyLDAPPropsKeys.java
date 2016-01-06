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

package com.liferay.portal.ldap.constants;

/**
 * @author Michael C. Han
 */
public class LegacyLDAPPropsKeys {

	public static final String LDAP_AUTH_ENABLED = "ldap.auth.enabled";

	public static final String LDAP_AUTH_METHOD = "ldap.auth.method";

	public static final String LDAP_AUTH_PASSWORD_ENCRYPTION_ALGORITHM =
		"ldap.auth.password.encryption.algorithm";

	public static final String LDAP_AUTH_REQUIRED = "ldap.auth.required";

	public static final String LDAP_AUTH_SEARCH_FILTER =
		"ldap.auth.search.filter";

	public static final String LDAP_BASE_DN = "ldap.base.dn";

	public static final String LDAP_BASE_PROVIDER_URL =
		"ldap.base.provider.url";

	public static final String LDAP_CONNECTION_PROPERTY_PREFIX =
		"ldap.connection.";

	public static final String LDAP_CONTACT_CUSTOM_MAPPINGS =
		"ldap.contact.custom.mappings";

	public static final String LDAP_CONTACT_MAPPINGS = "ldap.contact.mappings";

	public static final String LDAP_ERROR_PASSWORD_AGE =
		"ldap.error.password.age";

	public static final String LDAP_ERROR_PASSWORD_EXPIRED =
		"ldap.error.password.expired";

	public static final String LDAP_ERROR_PASSWORD_HISTORY =
		"ldap.error.password.history";

	public static final String LDAP_ERROR_PASSWORD_NOT_CHANGEABLE =
		"ldap.error.password.not.changeable";

	public static final String LDAP_ERROR_PASSWORD_SYNTAX =
		"ldap.error.password.syntax";

	public static final String LDAP_ERROR_PASSWORD_TRIVIAL =
		"ldap.error.password.trivial";

	public static final String LDAP_ERROR_USER_LOCKOUT =
		"ldap.error.user.lockout";

	public static final String LDAP_EXPORT_ENABLED = "ldap.export.enabled";

	public static final String LDAP_EXPORT_GROUP_ENABLED =
		"ldap.export.group.enabled";

	public static final String LDAP_FACTORY_INITIAL = "ldap.factory.initial";

	public static final String LDAP_GROUP_DEFAULT_OBJECT_CLASSES =
		"ldap.group.default.object.classes";

	public static final String LDAP_GROUP_MAPPINGS = "ldap.group.mappings";

	public static final String LDAP_GROUPS_DN = "ldap.groups.dn";

	public static final String LDAP_IMPORT_CREATE_ROLE_PER_GROUP =
		"ldap.import.create.role.per.group";

	public static final String LDAP_IMPORT_ENABLED = "ldap.import.enabled";

	public static final String LDAP_IMPORT_GROUP_CACHE_ENABLED =
		"ldap.import.group.cache.enabled";

	public static final String LDAP_IMPORT_GROUP_SEARCH_FILTER =
		"ldap.import.group.search.filter";

	public static final String LDAP_IMPORT_GROUP_SEARCH_FILTER_ENABLED =
		"ldap.import.group.search.filter.enabled";

	public static final String LDAP_IMPORT_INTERVAL = "ldap.import.interval";

	public static final String LDAP_IMPORT_LOCK_EXPIRATION_TIME =
		"ldap.import.lock.expiration.time";

	public static final String LDAP_IMPORT_METHOD = "ldap.import.method";

	public static final String LDAP_IMPORT_ON_STARTUP =
		"ldap.import.on.startup";

	public static final String LDAP_IMPORT_USER_PASSWORD_AUTOGENERATED =
		"ldap.import.user.password.autogenerated";

	public static final String LDAP_IMPORT_USER_PASSWORD_DEFAULT =
		"ldap.import.user.password.default";

	public static final String LDAP_IMPORT_USER_PASSWORD_ENABLED =
		"ldap.import.user.password.enabled";

	public static final String LDAP_IMPORT_USER_SEARCH_FILTER =
		"ldap.import.user.search.filter";

	public static final String LDAP_IMPORT_USER_SYNC_STRATEGY =
		"ldap.import.user.sync.strategy";

	public static final String LDAP_PAGE_SIZE = "ldap.page.size";

	public static final String LDAP_PASSWORD_POLICY_ENABLED =
		"ldap.password.policy.enabled";

	public static final String LDAP_RANGE_SIZE = "ldap.range.size";

	public static final String LDAP_REFERRAL = "ldap.referral";

	public static final String LDAP_SECURITY_CREDENTIALS =
		"ldap.security.credentials";

	public static final String LDAP_SECURITY_PRINCIPAL =
		"ldap.security.principal";

	public static final String LDAP_SERVER_NAME = "ldap.server.name";

	public static final String LDAP_USER_CUSTOM_MAPPINGS =
		"ldap.user.custom.mappings";

	public static final String LDAP_USER_DEFAULT_OBJECT_CLASSES =
		"ldap.user.default.object.classes";

	public static final String LDAP_USER_IGNORE_ATTRIBUTES =
		"ldap.user.ignore.attributes";

	public static final String LDAP_USER_MAPPINGS = "ldap.user.mappings";

	public static final String LDAP_USERS_DN = "ldap.users.dn";

	public static final String[] NONPOSTFIXED_LDAP_KEYS = {
		LDAP_AUTH_ENABLED, LDAP_AUTH_REQUIRED, LDAP_IMPORT_ENABLED,
		LDAP_IMPORT_ON_STARTUP, LDAP_EXPORT_ENABLED,
		LDAP_PASSWORD_POLICY_ENABLED, LDAP_IMPORT_USER_PASSWORD_AUTOGENERATED
	};

	public static final String[] POSTFIXED_LDAP_KEYS = {
		LDAP_AUTH_SEARCH_FILTER, LDAP_BASE_DN, LDAP_BASE_PROVIDER_URL,
		LDAP_CONTACT_CUSTOM_MAPPINGS, LDAP_CONTACT_MAPPINGS,
		LDAP_GROUP_DEFAULT_OBJECT_CLASSES, LDAP_GROUP_MAPPINGS, LDAP_GROUPS_DN,
		LDAP_IMPORT_GROUP_SEARCH_FILTER, LDAP_IMPORT_USER_SEARCH_FILTER,
		LDAP_SECURITY_CREDENTIALS, LDAP_SECURITY_PRINCIPAL, LDAP_SERVER_NAME,
		LDAP_USER_CUSTOM_MAPPINGS, LDAP_USER_DEFAULT_OBJECT_CLASSES,
		LDAP_USER_MAPPINGS, LDAP_USERS_DN
	};

}