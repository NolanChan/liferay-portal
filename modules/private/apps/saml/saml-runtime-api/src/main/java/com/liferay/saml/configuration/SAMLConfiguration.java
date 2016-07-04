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

package com.liferay.saml.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Carlos Sierra Andrés
 */
@ExtendedObjectClassDefinition(category = "foundation")
@Meta.OCD(
	id = "com.liferay.saml.configuration.SAMLConfiguration",
	localization = "content/Language", name = "saml.configuration.name"
)
public interface SAMLConfiguration {

	/**
	 * Set the interval in minutes on how often SamlIdpSsoSessionMessageListener
	 * will be run to check for and delete SAML IDP SSO sessions that are older
	 * than the maximum age set in the property "saml.idp.sso.session.max.age".
	 */
	@Meta.AD(name = "saml.idp.sso.session.check.interval", deflt = "60", required = false)
	public int getIdpSsoSessionCheckInterval();

	/**
	 * Set the duration in milliseconds to remove and expire SAML IDP SSO
	 * sessions.
	 */
	@Meta.AD(name = "saml.idp.sso.session.max.age", deflt = "86400000", required = false)
	public int getIdpSsoSessionMaxAge();

	/**
	 * Set the maximum metadata refresh interval is in milliseconds when metadata
	 * has no validity set.
	 */
	@Meta.AD(name = "saml.metadata.max.refresh.delay", deflt = "14400000", required = false)
	public int getMetadataMaxRefreshDelay();

	/**
	 * Set the minimum time in milliseconds to wait after a failed metadata
	 * refresh.
	 */
	@Meta.AD(
		name = "saml.metadata.min.refresh.delay", deflt = "300000",
		required = false
	)
	public int getMetadataMinRefreshDelay();

	@Meta.AD(
		name = "saml.metadata.refresh.interval", deflt = "30", required = false
	)
	public int getMetadataRefreshInterval();

	/**
	 * Set the duration in milliseconds to prevent replaying messages.
	 */
	@Meta.AD(
		name ="saml.replay.cache.duration", deflt = "3600000", required = false
	)
	public int getReplayChacheDuration();

	/**
	 * Set the interval in minutes on how often SamlSpAuthRequestMessageListener
	 * will be run to check for and delete SAML SP authentication requests that
	 * are older than the maximum age set in the property
	 * "saml.sp.auth.request.max.age".
	 */
	@Meta.AD(
		name = "saml.sp.auth.request.check.interval", deflt = "60",
		required = false
	)
	public int getSpAuthRequestCheckInterval();

	/**
	 * Set the duration in milliseconds to remove and expire SAML SP
	 * authentication requests.
	 */
	@Meta.AD(
		name = "saml.sp.auth.request.max.age", deflt = "86400000",
		required = false
	)
	public int getSpAuthRequestMaxAge();

	/**
	 * Set the interval in minutes on how often SamlSpMessageMessageListener
	 * will be run to check for and delete expired SAML SP messages.
	 */
	@Meta.AD(
		name = "saml.sp.message.check.interval", deflt = "60", required = false
	)
	public int getSpMessageCheckInterval();

}