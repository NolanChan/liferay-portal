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

package com.liferay.saml.runtime.internal.metadata;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.concurrent.ReadWriteLockKey;
import com.liferay.portal.kernel.concurrent.ReadWriteLockRegistry;
import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.saml.model.SamlIdpSpConnection;
import com.liferay.saml.model.SamlSpIdpConnection;
import com.liferay.saml.provider.CachingChainingMetadataProvider;
import com.liferay.saml.provider.DBMetadataProvider;
import com.liferay.saml.provider.DBMetadataProviderFactory;
import com.liferay.saml.provider.ReinitializingFilesystemMetadataProvider;
import com.liferay.saml.provider.ReinitializingHttpMetadataProvider;
import com.liferay.saml.runtime.configuration.SAMLConfiguration;
import com.liferay.saml.runtime.metadata.MetadataManager;
import com.liferay.saml.service.SamlIdpSpConnectionLocalService;
import com.liferay.saml.service.SamlSpIdpConnectionLocalService;
import com.liferay.saml.util.PortletPrefsPropsUtil;
import com.liferay.saml.util.PortletPropsKeys;
import com.liferay.saml.util.SamlUtil;

import java.io.File;

import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.HttpClient;

import org.opensaml.Configuration;
import org.opensaml.common.binding.security.SAMLProtocolMessageXMLSignatureSecurityPolicyRule;
import org.opensaml.common.xml.SAMLConstants;
import org.opensaml.saml2.binding.security.SAML2HTTPPostSimpleSignRule;
import org.opensaml.saml2.binding.security.SAML2HTTPRedirectDeflateSignatureRule;
import org.opensaml.saml2.core.NameIDType;
import org.opensaml.saml2.metadata.EntityDescriptor;
import org.opensaml.saml2.metadata.provider.BaseMetadataProvider;
import org.opensaml.saml2.metadata.provider.FilesystemMetadataProvider;
import org.opensaml.saml2.metadata.provider.HTTPMetadataProvider;
import org.opensaml.saml2.metadata.provider.MetadataProvider;
import org.opensaml.saml2.metadata.provider.MetadataProviderException;
import org.opensaml.security.MetadataCredentialResolver;
import org.opensaml.ws.security.SecurityPolicy;
import org.opensaml.ws.security.SecurityPolicyResolver;
import org.opensaml.ws.security.SecurityPolicyRule;
import org.opensaml.ws.security.provider.BasicSecurityPolicy;
import org.opensaml.ws.security.provider.HTTPRule;
import org.opensaml.ws.security.provider.MandatoryAuthenticatedMessageRule;
import org.opensaml.ws.security.provider.MandatoryIssuerRule;
import org.opensaml.ws.security.provider.StaticSecurityPolicyResolver;
import org.opensaml.xml.parse.ParserPool;
import org.opensaml.xml.security.CriteriaSet;
import org.opensaml.xml.security.SecurityConfiguration;
import org.opensaml.xml.security.SecurityException;
import org.opensaml.xml.security.credential.Credential;
import org.opensaml.xml.security.credential.CredentialResolver;
import org.opensaml.xml.security.criteria.EntityIDCriteria;
import org.opensaml.xml.security.keyinfo.KeyInfoCredentialResolver;
import org.opensaml.xml.signature.SignatureTrustEngine;
import org.opensaml.xml.signature.impl.ChainingSignatureTrustEngine;
import org.opensaml.xml.signature.impl.ExplicitKeySignatureTrustEngine;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Mika Koivisto
 */
@Component(
	configurationPid = "com.liferay.saml.runtime.configuration.SAMLConfiguration",
	configurationPolicy = ConfigurationPolicy.OPTIONAL, immediate = true
)
public class MetadataManagerImpl implements MetadataManager {

	public static final int SAML_IDP_ASSERTION_LIFETIME_DEFAULT = 1800;

	@Override
	public int getAssertionLifetime(String entityId) {
		long companyId = CompanyThreadLocal.getCompanyId();

		try {
			SamlIdpSpConnection samlIdpSpConnection =
				_samlIdpSpConnectionLocalService.getSamlIdpSpConnection(
					companyId, entityId);

			return samlIdpSpConnection.getAssertionLifetime();
		}
		catch (Exception e) {
		}

		String samlIdpAssertionLifetime = PortletPrefsPropsUtil.getString(
			PortletPropsKeys.SAML_IDP_ASSERTION_LIFETIME, new Filter(entityId));

		return GetterUtil.getInteger(
			samlIdpAssertionLifetime, SAML_IDP_ASSERTION_LIFETIME_DEFAULT);
	}

	@Override
	public String[] getAttributeNames(String entityId) {
		long companyId = CompanyThreadLocal.getCompanyId();

		try {
			SamlIdpSpConnection samlIdpSpConnection =
				_samlIdpSpConnectionLocalService.getSamlIdpSpConnection(
					companyId, entityId);

			return StringUtil.splitLines(
				samlIdpSpConnection.getAttributeNames());
		}
		catch (Exception e) {
		}

		StringBundler sb = new StringBundler(4);

		sb.append(PortletPropsKeys.SAML_IDP_METADATA_ATTRIBUTE_NAMES);
		sb.append("[");
		sb.append(entityId);
		sb.append("]");

		String attributeNames = PortletPrefsPropsUtil.getString(sb.toString());

		if (Validator.isNotNull(attributeNames)) {
			return StringUtil.splitLines(attributeNames);
		}

		attributeNames = PropsUtil.get(
			PortletPropsKeys.SAML_IDP_METADATA_ATTRIBUTE_NAMES,
			new Filter(entityId));

		if (Validator.isNotNull(attributeNames)) {
			return StringUtil.split(attributeNames);
		}

		return PropsUtil.getArray(
			PortletPropsKeys.SAML_IDP_METADATA_ATTRIBUTE_NAMES);
	}

	@Override
	public long getClockSkew() {
		return PortletPrefsPropsUtil.getInteger(
			PortletPropsKeys.SAML_SP_CLOCK_SKEW, 3000);
	}

	@Override
	public String getDefaultIdpEntityId() {
		return PortletPrefsPropsUtil.getString(
			PortletPropsKeys.SAML_SP_DEFAULT_IDP_ENTITY_ID);
	}

	@Override
	public EntityDescriptor getEntityDescriptor(HttpServletRequest request)
		throws MetadataProviderException {

		try {
			if (SamlUtil.isRoleIdp()) {
				return MetadataGeneratorUtil.buildIdpEntityDescriptor(
					request, getLocalEntityId(), isWantAuthnRequestSigned(),
					isSignMetadata(), isSSLRequired(), getSigningCredential());
			}
			else if (SamlUtil.isRoleSp()) {
				return MetadataGeneratorUtil.buildSpEntityDescriptor(
					request, getLocalEntityId(), isSignAuthnRequests(),
					isSignMetadata(), isSSLRequired(), isWantAssertionsSigned(),
					getSigningCredential());
			}

			return null;
		}
		catch (Exception e) {
			throw new MetadataProviderException(e);
		}
	}

	@Override
	public String getLocalEntityId() {
		return PortletPrefsPropsUtil.getString(PortletPropsKeys.SAML_ENTITY_ID);
	}

	@Override
	public MetadataProvider getMetadataProvider()
		throws MetadataProviderException {

		long companyId = CompanyThreadLocal.getCompanyId();

		ReadWriteLockKey<Long> readWriteLockKey = new ReadWriteLockKey<>(
			companyId, true);

		Lock lock = _readWriteLockRegistry.acquireLock(readWriteLockKey);

		lock.lock();

		try {
			MetadataProvider metadataProvider = _metadataProviders.get(
				companyId);

			if (metadataProvider != null) {
				return metadataProvider;
			}

			CachingChainingMetadataProvider cachingChainingMetadataProvider =
				new CachingChainingMetadataProvider();

			metadataProvider = cachingChainingMetadataProvider;

			DBMetadataProvider dbMetadataProvider =
				_dbMetadataProviderFactory.createDBMetadataProvider(
					_parserPool);

			cachingChainingMetadataProvider.addMetadataProvider(
				dbMetadataProvider);

			String[] paths = PropsUtil.getArray(
				PortletPropsKeys.SAML_METADATA_PATHS);

			for (String path : paths) {
				if (path.startsWith("http://") || path.startsWith("https://")) {
					HTTPMetadataProvider httpMetadataProvider =
						new ReinitializingHttpMetadataProvider(
							_timer, _httpClient, path);

					httpMetadataProvider.setFailFastInitialization(false);
					httpMetadataProvider.setMaxRefreshDelay(
						_samlConfiguration.getMetadataMaxRefreshDelay());
					httpMetadataProvider.setMinRefreshDelay(
						_samlConfiguration.getMetadataMinRefreshDelay());
					httpMetadataProvider.setParserPool(_parserPool);

					try {
						httpMetadataProvider.initialize();
					}
					catch (MetadataProviderException mpe) {
						if (_log.isWarnEnabled()) {
							_log.warn("Unable to initialize provider " + path);
						}
					}

					cachingChainingMetadataProvider.addMetadataProvider(
						httpMetadataProvider);
				}
				else {
					FilesystemMetadataProvider filesystemMetadataProvider =
						new ReinitializingFilesystemMetadataProvider(
							_timer, new File(path));

					filesystemMetadataProvider.setFailFastInitialization(false);
					filesystemMetadataProvider.setMaxRefreshDelay(
						_samlConfiguration.getMetadataMaxRefreshDelay());
					filesystemMetadataProvider.setMinRefreshDelay(
						_samlConfiguration.getMetadataMinRefreshDelay());
					filesystemMetadataProvider.setParserPool(_parserPool);

					try {
						filesystemMetadataProvider.initialize();
					}
					catch (MetadataProviderException mpe) {
						if (_log.isWarnEnabled()) {
							_log.warn("Unable to initialize provider " + path);
						}
					}

					cachingChainingMetadataProvider.addMetadataProvider(
						filesystemMetadataProvider);
				}
			}

			_metadataProviders.put(companyId, metadataProvider);

			return metadataProvider;
		}
		finally {
			lock.unlock();

			if (readWriteLockKey != null) {
				_readWriteLockRegistry.releaseLock(readWriteLockKey);
			}
		}
	}

	@Override
	public String getNameIdAttribute(String entityId) {
		long companyId = CompanyThreadLocal.getCompanyId();

		String nameIdAttributeName = StringPool.BLANK;

		try {
			SamlIdpSpConnection samlIdpSpConnection =
				_samlIdpSpConnectionLocalService.getSamlIdpSpConnection(
					companyId, entityId);

			nameIdAttributeName = samlIdpSpConnection.getNameIdAttribute();
		}
		catch (Exception e) {
		}

		if (Validator.isNotNull(nameIdAttributeName)) {
			return nameIdAttributeName;
		}

		nameIdAttributeName = PortletPrefsPropsUtil.getString(
			PortletPropsKeys.SAML_IDP_METADATA_NAME_ID_ATTRIBUTE,
			new Filter(entityId));

		if (Validator.isNull(nameIdAttributeName)) {
			nameIdAttributeName = "emailAddress";
		}

		return nameIdAttributeName;
	}

	@Override
	public String getNameIdFormat(String entityId) {
		long companyId = CompanyThreadLocal.getCompanyId();

		String nameIdFormat = StringPool.BLANK;

		if (SamlUtil.isRoleIdp()) {
			try {
				SamlIdpSpConnection samlIdpSpConnection =
					_samlIdpSpConnectionLocalService.getSamlIdpSpConnection(
						companyId, entityId);

				nameIdFormat = samlIdpSpConnection.getNameIdFormat();
			}
			catch (Exception e) {
			}

			if (Validator.isNotNull(nameIdFormat)) {
				return nameIdFormat;
			}

			nameIdFormat = PortletPrefsPropsUtil.getString(
				PortletPropsKeys.SAML_IDP_METADATA_NAME_ID_FORMAT,
				new Filter(entityId));
		}
		else if (SamlUtil.isRoleSp()) {
			try {
				SamlSpIdpConnection samlSpIdpConnection =
					_samlSpIdpConnectionLocalService.getSamlSpIdpConnection(
						companyId, entityId);

				nameIdFormat = samlSpIdpConnection.getNameIdFormat();
			}
			catch (Exception e) {
			}

			if (Validator.isNotNull(nameIdFormat)) {
				return nameIdFormat;
			}

			nameIdFormat = PortletPrefsPropsUtil.getString(
				PortletPropsKeys.SAML_SP_NAME_ID_FORMAT, new Filter(entityId));
		}

		if (Validator.isNull(nameIdFormat)) {
			nameIdFormat = NameIDType.EMAIL;
		}

		return nameIdFormat;
	}

	@Override
	public SecurityPolicyResolver getSecurityPolicyResolver(
			String communicationProfileId, boolean requireSignature)
		throws MetadataProviderException {

		SecurityPolicy securityPolicy = new BasicSecurityPolicy();

		List<SecurityPolicyRule> securityPolicyRules =
			securityPolicy.getPolicyRules();

		if (requireSignature) {
			SignatureTrustEngine signatureTrustEngine =
				getSignatureTrustEngine();

			if (communicationProfileId.equals(
					SAMLConstants.SAML2_REDIRECT_BINDING_URI)) {

				SecurityPolicyRule securityPolicyRule =
					new SAML2HTTPRedirectDeflateSignatureRule(
						signatureTrustEngine);

				securityPolicyRules.add(securityPolicyRule);
			}
			else if (communicationProfileId.equals(
						SAMLConstants.SAML2_POST_SIMPLE_SIGN_BINDING_URI)) {

				SecurityConfiguration securityConfiguration =
					Configuration.getGlobalSecurityConfiguration();

				KeyInfoCredentialResolver keyInfoCredentialResolver =
					securityConfiguration.getDefaultKeyInfoCredentialResolver();

				SecurityPolicyRule securityPolicyRule =
					new SAML2HTTPPostSimpleSignRule(
						signatureTrustEngine, _parserPool,
						keyInfoCredentialResolver);

				securityPolicyRules.add(securityPolicyRule);
			}
			else {
				SecurityPolicyRule securityPolicyRule =
					new SAMLProtocolMessageXMLSignatureSecurityPolicyRule(
						signatureTrustEngine);

				securityPolicyRules.add(securityPolicyRule);
			}

			MandatoryAuthenticatedMessageRule
				mandatoryAuthenticatedMessageRule =
					new MandatoryAuthenticatedMessageRule();

			securityPolicyRules.add(mandatoryAuthenticatedMessageRule);
		}

		HTTPRule httpRule = new HTTPRule(null, null, isSSLRequired());

		securityPolicyRules.add(httpRule);

		MandatoryIssuerRule mandatoryIssuerRule = new MandatoryIssuerRule();

		securityPolicyRules.add(mandatoryIssuerRule);

		StaticSecurityPolicyResolver securityPolicyResolver =
			new StaticSecurityPolicyResolver(securityPolicy);

		return securityPolicyResolver;
	}

	@Override
	public String getSessionKeepAliveURL(String entityId) {
		return PropsUtil.get(
			PortletPropsKeys.SAML_IDP_METADATA_SESSION_KEEP_ALIVE_URL,
			new Filter(entityId));
	}

	@Override
	public long getSessionMaximumAge() {
		return PortletPrefsPropsUtil.getLong(
			PortletPropsKeys.SAML_IDP_SESSION_MAXIMUM_AGE);
	}

	@Override
	public long getSessionTimeout() {
		return PortletPrefsPropsUtil.getLong(
			PortletPropsKeys.SAML_IDP_SESSION_TIMEOUT);
	}

	@Override
	public SignatureTrustEngine getSignatureTrustEngine()
		throws MetadataProviderException {

		ChainingSignatureTrustEngine chainingSignatureTrustEngine =
			new ChainingSignatureTrustEngine();

		List<SignatureTrustEngine> signatureTrustEngines =
			chainingSignatureTrustEngine.getChain();

		MetadataCredentialResolver metadataCredentialResolver =
			new MetadataCredentialResolver(getMetadataProvider());

		SecurityConfiguration securityConfiguration =
			Configuration.getGlobalSecurityConfiguration();

		KeyInfoCredentialResolver keyInfoCredentialResolver =
			securityConfiguration.getDefaultKeyInfoCredentialResolver();

		SignatureTrustEngine signatureTrustEngine =
			new ExplicitKeySignatureTrustEngine(
				metadataCredentialResolver, keyInfoCredentialResolver);

		signatureTrustEngines.add(signatureTrustEngine);

		signatureTrustEngine = new ExplicitKeySignatureTrustEngine(
			_credentialResolver, keyInfoCredentialResolver);

		signatureTrustEngines.add(signatureTrustEngine);

		return chainingSignatureTrustEngine;
	}

	@Override
	public Credential getSigningCredential() throws SecurityException {
		CriteriaSet criteriaSet = new CriteriaSet();

		String entityId = getLocalEntityId();

		if (Validator.isNull(entityId)) {
			return null;
		}

		EntityIDCriteria entityIdCriteria = new EntityIDCriteria(entityId);

		criteriaSet.add(entityIdCriteria);

		return _credentialResolver.resolveSingle(criteriaSet);
	}

	@Override
	public String getUserAttributeMappings(String entityId) {
		long companyId = CompanyThreadLocal.getCompanyId();

		try {
			SamlSpIdpConnection samlSpIdpConnection =
				_samlSpIdpConnectionLocalService.getSamlSpIdpConnection(
					companyId, entityId);

			return samlSpIdpConnection.getUserAttributeMappings();
		}
		catch (Exception e) {
		}

		return PropsUtil.get(PortletPropsKeys.SAML_SP_USER_ATTRIBUTE_MAPPINGS);
	}

	@Override
	public boolean isAttributesEnabled(String entityId) {
		long companyId = CompanyThreadLocal.getCompanyId();

		try {
			SamlIdpSpConnection samlIdpSpConnection =
				_samlIdpSpConnectionLocalService.getSamlIdpSpConnection(
					companyId, entityId);

			return samlIdpSpConnection.isAttributesEnabled();
		}
		catch (Exception e) {
		}

		String attributesEnabled = PortletPrefsPropsUtil.getString(
			PortletPropsKeys.SAML_IDP_METADATA_ATTRIBUTES_ENABLED,
			new Filter(entityId));

		return GetterUtil.getBoolean(attributesEnabled);
	}

	@Override
	public boolean isAttributesNamespaceEnabled(String entityId) {
		long companyId = CompanyThreadLocal.getCompanyId();

		try {
			SamlIdpSpConnection samlIdpSpConnection =
				_samlIdpSpConnectionLocalService.getSamlIdpSpConnection(
					companyId, entityId);

			return samlIdpSpConnection.isAttributesNamespaceEnabled();
		}
		catch (Exception e) {
		}

		String attributesNamespaceEnabled = PortletPrefsPropsUtil.getString(
			PortletPropsKeys.SAML_IDP_METADATA_ATTRIBUTES_NAMESPACE_ENABLED,
			new Filter(entityId));

		return GetterUtil.getBoolean(attributesNamespaceEnabled);
	}

	@Override
	public boolean isSignAuthnRequests() {
		return PortletPrefsPropsUtil.getBoolean(
			PortletPropsKeys.SAML_SP_SIGN_AUTHN_REQUEST);
	}

	@Override
	public boolean isSignMetadata() {
		return PortletPrefsPropsUtil.getBoolean(
			PortletPropsKeys.SAML_SIGN_METADATA);
	}

	@Override
	public boolean isSSLRequired() {
		return PortletPrefsPropsUtil.getBoolean(
			PortletPropsKeys.SAML_SSL_REQUIRED);
	}

	@Override
	public boolean isWantAssertionsSigned() {
		return PortletPrefsPropsUtil.getBoolean(
			PortletPropsKeys.SAML_SP_ASSERTION_SIGNATURE_REQUIRED);
	}

	@Override
	public boolean isWantAuthnRequestSigned() {
		return PortletPrefsPropsUtil.getBoolean(
			PortletPropsKeys.SAML_IDP_AUTHN_REQUEST_SIGNATURE_REQUIRED);
	}

	@Reference(unbind = "-")
	public void setCredentialResolver(CredentialResolver credentialResolver) {
		_credentialResolver = credentialResolver;
	}

	@Reference(unbind = "-")
	public void setDbMetadataProviderFactory(
		DBMetadataProviderFactory dbMetadataProviderFactory) {

		_dbMetadataProviderFactory = dbMetadataProviderFactory;
	}

	@Reference(unbind = "-")
	public void setHttpClient(HttpClient httpClient) {
		_httpClient = httpClient;
	}

	@Reference(unbind = "-")
	public void setParserPool(ParserPool parserPool) {
		_parserPool = parserPool;
	}

	@Deactivate
	public void shutdown() {
		for (MetadataProvider metadataProvider : _metadataProviders.values()) {
			if (metadataProvider instanceof BaseMetadataProvider) {
				BaseMetadataProvider baseMetadataProvider =
					(BaseMetadataProvider)metadataProvider;

				baseMetadataProvider.destroy();
			}
		}

		_metadataProviders.clear();
		_timer.cancel();
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_samlConfiguration = ConfigurableUtil.createConfigurable(
			SAMLConfiguration.class, properties);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		MetadataManagerImpl.class);

	private CredentialResolver _credentialResolver;
	private DBMetadataProviderFactory _dbMetadataProviderFactory;
	private HttpClient _httpClient;
	private final ConcurrentHashMap<Long, MetadataProvider> _metadataProviders =
		new ConcurrentHashMap<>();
	private ParserPool _parserPool;
	private final ReadWriteLockRegistry _readWriteLockRegistry =
		new ReadWriteLockRegistry();
	private SAMLConfiguration _samlConfiguration;

	@Reference
	private SamlIdpSpConnectionLocalService _samlIdpSpConnectionLocalService;

	@Reference
	private SamlSpIdpConnectionLocalService _samlSpIdpConnectionLocalService;

	private final Timer _timer = new Timer(true);

}