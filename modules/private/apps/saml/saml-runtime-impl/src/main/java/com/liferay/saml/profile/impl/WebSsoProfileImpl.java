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

package com.liferay.saml.profile.impl;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.saml.SamlException;
import com.liferay.saml.SamlSsoRequestContext;
import com.liferay.saml.binding.SamlBinding;
import com.liferay.saml.configuration.SAMLConfiguration;
import com.liferay.saml.exception.AssertionException;
import com.liferay.saml.exception.AudienceException;
import com.liferay.saml.exception.DestinationException;
import com.liferay.saml.exception.ExpiredException;
import com.liferay.saml.exception.InResponseToException;
import com.liferay.saml.exception.IssuerException;
import com.liferay.saml.exception.NoSuchIdpSpSessionException;
import com.liferay.saml.exception.NoSuchSpIdpConnectionException;
import com.liferay.saml.exception.ReplayException;
import com.liferay.saml.exception.SignatureException;
import com.liferay.saml.exception.StatusException;
import com.liferay.saml.exception.SubjectException;
import com.liferay.saml.metadata.MetadataManager;
import com.liferay.saml.model.SamlIdpSsoSession;
import com.liferay.saml.model.SamlSpAuthRequest;
import com.liferay.saml.model.SamlSpIdpConnection;
import com.liferay.saml.model.SamlSpMessage;
import com.liferay.saml.model.SamlSpSession;
import com.liferay.saml.profile.WebSsoProfile;
import com.liferay.saml.resolver.AttributeResolver;
import com.liferay.saml.resolver.NameIdResolver;
import com.liferay.saml.resolver.UserResolver;
import com.liferay.saml.service.SamlIdpSpSessionLocalService;
import com.liferay.saml.service.SamlIdpSsoSessionLocalService;
import com.liferay.saml.service.SamlSpAuthRequestLocalService;
import com.liferay.saml.service.SamlSpIdpConnectionLocalService;
import com.liferay.saml.service.SamlSpMessageLocalService;
import com.liferay.saml.service.SamlSpSessionLocalService;
import com.liferay.saml.util.OpenSamlUtil;
import com.liferay.saml.util.PortletWebKeys;
import com.liferay.saml.util.SamlUtil;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import org.opensaml.common.IdentifierGenerator;
import org.opensaml.common.SAMLObject;
import org.opensaml.common.SAMLVersion;
import org.opensaml.common.binding.SAMLMessageContext;
import org.opensaml.common.xml.SAMLConstants;
import org.opensaml.saml2.core.Assertion;
import org.opensaml.saml2.core.Attribute;
import org.opensaml.saml2.core.AttributeStatement;
import org.opensaml.saml2.core.Audience;
import org.opensaml.saml2.core.AudienceRestriction;
import org.opensaml.saml2.core.AuthnContext;
import org.opensaml.saml2.core.AuthnContextClassRef;
import org.opensaml.saml2.core.AuthnRequest;
import org.opensaml.saml2.core.AuthnStatement;
import org.opensaml.saml2.core.Conditions;
import org.opensaml.saml2.core.Issuer;
import org.opensaml.saml2.core.NameID;
import org.opensaml.saml2.core.NameIDPolicy;
import org.opensaml.saml2.core.NameIDType;
import org.opensaml.saml2.core.Response;
import org.opensaml.saml2.core.Status;
import org.opensaml.saml2.core.StatusCode;
import org.opensaml.saml2.core.Subject;
import org.opensaml.saml2.core.SubjectConfirmation;
import org.opensaml.saml2.core.SubjectConfirmationData;
import org.opensaml.saml2.metadata.AssertionConsumerService;
import org.opensaml.saml2.metadata.IDPSSODescriptor;
import org.opensaml.saml2.metadata.SPSSODescriptor;
import org.opensaml.saml2.metadata.SingleSignOnService;
import org.opensaml.security.MetadataCriteria;
import org.opensaml.security.SAMLSignatureProfileValidator;
import org.opensaml.xml.security.CriteriaSet;
import org.opensaml.xml.security.SecurityException;
import org.opensaml.xml.security.credential.Credential;
import org.opensaml.xml.security.credential.UsageType;
import org.opensaml.xml.security.criteria.EntityIDCriteria;
import org.opensaml.xml.security.criteria.UsageCriteria;
import org.opensaml.xml.security.trust.TrustEngine;
import org.opensaml.xml.signature.Signature;
import org.opensaml.xml.signature.SignatureTrustEngine;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Mika Koivisto
 */
@Component(
	configurationPid = "com.liferay.saml.configuration.SAMLConfiguration",
	configurationPolicy = ConfigurationPolicy.OPTIONAL, immediate = true
)
public class WebSsoProfileImpl extends BaseProfile implements WebSsoProfile {

	@Override
	public void processAuthnRequest(
			HttpServletRequest request, HttpServletResponse response)
		throws PortalException {

		try {
			doProcessAuthnRequest(request, response);
		}
		catch (Exception e) {
			if (e instanceof PortalException) {
				throw (PortalException)e;
			}
			else if (e instanceof SystemException) {
				throw (SystemException)e;
			}
			else {
				throw new SamlException(e);
			}
		}
	}

	@Override
	public void processResponse(
			HttpServletRequest request, HttpServletResponse response)
		throws PortalException {

		try {
			doProcessResponse(request, response);
		}
		catch (Exception e) {
			if (e instanceof PortalException) {
				throw (PortalException)e;
			}
			else if (e instanceof SystemException) {
				throw (SystemException)e;
			}
			else {
				throw new SamlException(e);
			}
		}
	}

	@Override
	public void sendAuthnRequest(
			HttpServletRequest request, HttpServletResponse response,
			String relayState)
		throws PortalException {

		try {
			doSendAuthnRequest(request, response, relayState);
		}
		catch (Exception e) {
			if (e instanceof PortalException) {
				throw (PortalException)e;
			}
			else if (e instanceof SystemException) {
				throw (SystemException)e;
			}
			else {
				throw new SamlException(e);
			}
		}
	}

	@Reference(policyOption = ReferencePolicyOption.GREEDY, unbind = "-")
	public void setAttributeResolver(AttributeResolver attributeResolver) {
		_attributeResolver = attributeResolver;
	}

	@Reference(unbind = "-")
	public void setIdentifierGenerator(
		IdentifierGenerator identifierGenerator) {

		super.setIdentifierGenerator(identifierGenerator);
	}

	@Reference(unbind = "-")
	public void setMetadataManager(MetadataManager metadataManager) {
		super.setMetadataManager(metadataManager);
	}

	@Reference(policyOption = ReferencePolicyOption.GREEDY, unbind = "-")
	public void setNameIdResolver(NameIdResolver nameIdResolver) {
		_nameIdResolver = nameIdResolver;
	}

	@Reference(policyOption = ReferencePolicyOption.GREEDY, unbind = "-")
	public void setSamlBinding(SamlBinding samlBinding) {
		addSamlBinding(samlBinding);
	}

	@Reference(policyOption = ReferencePolicyOption.GREEDY, unbind = "-")
	public void setUserResolver(UserResolver userResolver) {
		_userResolver = userResolver;
	}

	public void unsetSamlBinding(SamlBinding samlBinding) {
		removeSamlBinding(samlBinding);
	}

	@Override
	public void updateSamlSpSession(
		HttpServletRequest request, HttpServletResponse response) {

		SamlSpSession samlSpSession = getSamlSpSession(request);

		HttpSession session = request.getSession();

		String jSessionId = session.getId();

		if ((samlSpSession != null) &&
			!jSessionId.equals(samlSpSession.getJSessionId())) {

			try {
				_samlSpSessionLocalService.updateSamlSpSession(
					samlSpSession.getPrimaryKey(), jSessionId);
			}
			catch (Exception e) {
			}
		}
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_samlConfiguration = ConfigurableUtil.createConfigurable(
			SAMLConfiguration.class, properties);
	}

	protected void addSamlSsoSession(
			HttpServletRequest request, HttpServletResponse response,
			SamlSsoRequestContext samlSsoRequestContext, NameID nameId)
		throws Exception {

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			request);

		SamlIdpSsoSession samlIdpSsoSession =
			_samlIdpSsoSessionLocalService.addSamlIdpSsoSession(
				samlSsoRequestContext.getSamlSsoSessionId(), serviceContext);

		SAMLMessageContext<AuthnRequest, Response, NameID> samlMessageContext =
			samlSsoRequestContext.getSAMLMessageContext();

		_samlIdpSpSessionLocalService.addSamlIdpSpSession(
			samlIdpSsoSession.getSamlIdpSsoSessionId(),
			samlMessageContext.getPeerEntityId(), nameId.getFormat(),
			nameId.getValue(), serviceContext);

		addCookie(
			request, response, PortletWebKeys.SAML_SSO_SESSION_ID,
			samlSsoRequestContext.getSamlSsoSessionId(), -1);
	}

	protected SamlSsoRequestContext decodeAuthnRequest(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		HttpSession session = request.getSession();

		SamlSsoRequestContext samlSsoRequestContext =
			(SamlSsoRequestContext)session.getAttribute(
				PortletWebKeys.SAML_SSO_REQUEST_CONTEXT);

		if (samlSsoRequestContext != null) {
			session.removeAttribute(PortletWebKeys.SAML_SSO_REQUEST_CONTEXT);

			SAMLMessageContext<AuthnRequest, Response, NameID>
				samlMessageContext =
					(SAMLMessageContext<AuthnRequest, Response, NameID>)
						getSamlMessageContext(
							request, response,
							samlSsoRequestContext.getPeerEntityId());

			samlSsoRequestContext.setSAMLMessageContext(samlMessageContext);

			String authnRequestXml = samlSsoRequestContext.getAutnRequestXml();

			if (Validator.isNotNull(authnRequestXml)) {
				AuthnRequest authnRequest =
					(AuthnRequest)OpenSamlUtil.unmarshall(authnRequestXml);

				samlMessageContext.setInboundSAMLMessage(authnRequest);
				samlMessageContext.setInboundSAMLMessageId(
					authnRequest.getID());
			}

			String relayState = samlSsoRequestContext.getRelayState();

			samlMessageContext.setRelayState(relayState);

			String samlSsoSessionId = getSamlSsoSessionId(request);

			if (Validator.isNotNull(samlSsoSessionId)) {
				samlSsoRequestContext.setSamlSsoSessionId(samlSsoSessionId);
			}
			else {
				samlSsoRequestContext.setNewSession(true);
				samlSsoRequestContext.setSamlSsoSessionId(
					generateIdentifier(30));
			}

			samlSsoRequestContext.setStage(
				SamlSsoRequestContext.STAGE_AUTHENTICATED);

			long userId = PortalUtil.getUserId(request);

			samlSsoRequestContext.setUserId(userId);

			return samlSsoRequestContext;
		}

		SAMLMessageContext<AuthnRequest, Response, NameID> samlMessageContext =
			null;

		SamlBinding samlBinding = null;

		if (StringUtil.equalsIgnoreCase(request.getMethod(), "GET")) {
			samlBinding = getSamlBinding(
				SAMLConstants.SAML2_REDIRECT_BINDING_URI);
		}
		else {
			samlBinding = getSamlBinding(SAMLConstants.SAML2_POST_BINDING_URI);
		}

		String entityId = ParamUtil.getString(request, "entityId");
		String samlRequest = ParamUtil.getString(request, "SAMLRequest");

		if (Validator.isNotNull(entityId) && Validator.isNull(samlRequest)) {
			samlMessageContext =
				(SAMLMessageContext<AuthnRequest, Response, NameID>)
					getSamlMessageContext(request, response, entityId);

			samlMessageContext.setCommunicationProfileId(
				samlBinding.getCommunicationProfileId());

			String relayState = ParamUtil.getString(request, "RelayState");

			samlMessageContext.setRelayState(relayState);

			samlSsoRequestContext = new SamlSsoRequestContext(
				samlMessageContext.getPeerEntityId(), relayState,
				samlMessageContext);
		}
		else {
			samlMessageContext =
				(SAMLMessageContext<AuthnRequest, Response, NameID>)
					decodeSamlMessage(
						request, response, samlBinding,
						metadataManager.isWantAuthnRequestSigned());

			AuthnRequest authnRequest =
				samlMessageContext.getInboundSAMLMessage();

			String authnRequestXml = OpenSamlUtil.marshall(authnRequest);

			samlSsoRequestContext = new SamlSsoRequestContext(
				authnRequestXml, samlMessageContext.getPeerEntityId(),
				samlMessageContext.getRelayState(), samlMessageContext);
		}

		String samlSsoSessionId = getSamlSsoSessionId(request);

		if (Validator.isNotNull(samlSsoSessionId)) {
			samlSsoRequestContext.setSamlSsoSessionId(samlSsoSessionId);
		}
		else {
			samlSsoRequestContext.setNewSession(true);
			samlSsoRequestContext.setSamlSsoSessionId(generateIdentifier(30));
		}

		samlSsoRequestContext.setStage(SamlSsoRequestContext.STAGE_INITIAL);

		long userId = PortalUtil.getUserId(request);

		samlSsoRequestContext.setUserId(userId);

		return samlSsoRequestContext;
	}

	protected void doProcessAuthnRequest(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		SamlSsoRequestContext samlSsoRequestContext = decodeAuthnRequest(
			request, response);

		SAMLMessageContext<AuthnRequest, Response, NameID> samlMessageContext =
			samlSsoRequestContext.getSAMLMessageContext();

		AuthnRequest authnRequest = samlMessageContext.getInboundSAMLMessage();

		User user = samlSsoRequestContext.getUser();

		if ((authnRequest != null) && authnRequest.isPassive() &&
			(user == null)) {

			sendFailureResponse(
				samlSsoRequestContext, StatusCode.NO_PASSIVE_URI);

			return;
		}

		boolean sessionExpired = false;

		if (!samlSsoRequestContext.isNewSession()) {
			String samlSsoSessionId =
				samlSsoRequestContext.getSamlSsoSessionId();

			SamlIdpSsoSession samlIdpSsoSession =
				_samlIdpSsoSessionLocalService.fetchSamlIdpSso(
					samlSsoSessionId);

			if (samlIdpSsoSession != null) {
				sessionExpired = samlIdpSsoSession.isExpired();
			}
			else {
				samlSsoSessionId = null;

				samlSsoRequestContext.setSamlSsoSessionId(null);
			}

			if (sessionExpired || Validator.isNull(samlSsoSessionId)) {
				addCookie(
					request, response, PortletWebKeys.SAML_SSO_SESSION_ID,
					StringPool.BLANK, 0);

				samlSsoRequestContext.setNewSession(true);
				samlSsoRequestContext.setSamlSsoSessionId(
					generateIdentifier(30));
			}
		}

		if (sessionExpired || (user == null) ||
			((authnRequest != null) && authnRequest.isForceAuthn() &&
			 (user != null) &&
			 (samlSsoRequestContext.getStage() ==
				 SamlSsoRequestContext.STAGE_INITIAL))) {

			boolean forceAuthn = false;

			if (sessionExpired ||
				((authnRequest != null) && authnRequest.isForceAuthn())) {

				forceAuthn = true;
			}

			redirectToLogin(
				request, response, samlSsoRequestContext, forceAuthn);
		}
		else {
			sendSuccessResponse(request, response, samlSsoRequestContext);
		}
	}

	protected void doProcessResponse(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		SAMLMessageContext<Response, SAMLObject, NameID> samlMessageContext =
			(SAMLMessageContext<Response, SAMLObject, NameID>)decodeSamlMessage(
				request, response,
				getSamlBinding(SAMLConstants.SAML2_POST_BINDING_URI), true);

		Response samlResponse = samlMessageContext.getInboundSAMLMessage();

		Status status = samlResponse.getStatus();

		StatusCode statusCode = status.getStatusCode();

		String statusCodeURI = statusCode.getValue();

		if (!statusCodeURI.equals(StatusCode.SUCCESS_URI)) {
			StatusCode childStatusCode = statusCode.getStatusCode();

			if ((childStatusCode != null) &&
				Validator.isNotNull(childStatusCode.getValue())) {

				throw new StatusException(childStatusCode.getValue());
			}
			else {
				throw new StatusException(statusCodeURI);
			}
		}

		verifyInResponseTo(samlResponse);
		verifyDestination(samlMessageContext, samlResponse.getDestination());
		verifyIssuer(samlMessageContext, samlResponse.getIssuer());

		Assertion assertion = null;

		SignatureTrustEngine signatureTrustEngine =
			metadataManager.getSignatureTrustEngine();

		List<Attribute> attributes = new ArrayList<>();

		for (Assertion curAssertion : samlResponse.getAssertions()) {
			try {
				verifyAssertion(
					curAssertion, samlMessageContext, signatureTrustEngine);
			}
			catch (SamlException se) {
				if (_log.isDebugEnabled()) {
					_log.debug(
						"Rejecting assertion " + curAssertion.getID(), se);
				}

				continue;
			}

			List<AuthnStatement> authnStatements =
				curAssertion.getAuthnStatements();

			if (!authnStatements.isEmpty()) {
				Subject subject = curAssertion.getSubject();

				if ((subject != null) &&
					(subject.getSubjectConfirmations() != null)) {

					for (SubjectConfirmation subjectConfirmation :
							subject.getSubjectConfirmations()) {

						if (SubjectConfirmation.METHOD_BEARER.equals(
								subjectConfirmation.getMethod())) {

							assertion = curAssertion;

							break;
						}
					}
				}
			}

			if (assertion != null) {
				for (AttributeStatement attributeStatement :
						curAssertion.getAttributeStatements()) {

					for (Attribute attribute :
							attributeStatement.getAttributes()) {

						attributes.add(attribute);
					}
				}

				break;
			}
		}

		if (assertion == null) {
			throw new AssertionException(
				"Response does not contain any acceptable assertions");
		}

		NameID nameId = samlMessageContext.getSubjectNameIdentifier();

		if (nameId == null) {
			throw new SamlException("Name ID not present in subject");
		}

		if (_log.isDebugEnabled()) {
			_log.debug("SAML authenticated user " + nameId.getValue());
		}

		String assertionXml = OpenSamlUtil.marshall(assertion);

		List<AuthnStatement> authnStatements = assertion.getAuthnStatements();

		AuthnStatement authnStatement = authnStatements.get(0);

		String sessionIndex = authnStatement.getSessionIndex();

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			request);

		User user = _userResolver.resolveUser(
			assertion, samlMessageContext, serviceContext);

		serviceContext.setUserId(user.getUserId());

		HttpSession session = request.getSession();

		SamlSpSession samlSpSession = getSamlSpSession(request);

		if (samlSpSession != null) {
			_samlSpSessionLocalService.updateSamlSpSession(
				samlSpSession.getSamlSpSessionId(),
				samlSpSession.getSamlSpSessionKey(), assertionXml,
				session.getId(), nameId.getFormat(), nameId.getNameQualifier(),
				nameId.getSPNameQualifier(), nameId.getValue(), sessionIndex,
				serviceContext);
		}
		else {
			String samlSpSessionKey = generateIdentifier(30);

			samlSpSession = _samlSpSessionLocalService.addSamlSpSession(
				samlSpSessionKey, assertionXml, session.getId(),
				nameId.getFormat(), nameId.getNameQualifier(),
				nameId.getSPNameQualifier(), nameId.getValue(), sessionIndex,
				serviceContext);
		}

		session.setAttribute(
			PortletWebKeys.SAML_SP_SESSION_KEY,
			samlSpSession.getSamlSpSessionKey());

		addCookie(
			request, response, PortletWebKeys.SAML_SP_SESSION_KEY,
			samlSpSession.getSamlSpSessionKey(), -1);

		StringBundler sb = new StringBundler(3);

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		sb.append(themeDisplay.getPathMain());

		sb.append("/portal/saml/auth_redirect?redirect=");

		String relayState = PortalUtil.escapeRedirect(
			samlMessageContext.getRelayState());

		if (Validator.isNull(relayState)) {
			relayState = PortalUtil.getHomeURL(request);
		}

		sb.append(HttpUtil.encodeURL(relayState));

		response.sendRedirect(sb.toString());
	}

	protected void doSendAuthnRequest(
			HttpServletRequest request, HttpServletResponse response,
			String relayState)
		throws Exception {

		String entityId = metadataManager.getDefaultIdpEntityId();

		SAMLMessageContext<SAMLObject, AuthnRequest, SAMLObject>
			samlMessageContext =
				(SAMLMessageContext<SAMLObject, AuthnRequest, SAMLObject>)
					getSamlMessageContext(request, response, entityId);

		SPSSODescriptor spSsoDescriptor =
			(SPSSODescriptor)samlMessageContext.getLocalEntityRoleMetadata();

		AssertionConsumerService assertionConsumerService =
			SamlUtil.getAssertionConsumerServiceForBinding(
				spSsoDescriptor, SAMLConstants.SAML2_POST_BINDING_URI);

		IDPSSODescriptor idpSsoDescriptor =
			(IDPSSODescriptor)samlMessageContext.getPeerEntityRoleMetadata();

		SingleSignOnService singleSignOnService =
			SamlUtil.resolveSingleSignOnService(
				idpSsoDescriptor, SAMLConstants.SAML2_POST_BINDING_URI);

		NameIDPolicy nameIdPolicy = OpenSamlUtil.buildNameIdPolicy();

		nameIdPolicy.setAllowCreate(true);
		nameIdPolicy.setFormat(metadataManager.getNameIdFormat(entityId));

		AuthnRequest authnRequest = OpenSamlUtil.buildAuthnRequest(
			spSsoDescriptor, assertionConsumerService, singleSignOnService,
			nameIdPolicy);

		authnRequest.setID(generateIdentifier(20));

		long companyId = PortalUtil.getCompanyId(request);

		boolean forceAuthn = false;

		try {
			SamlSpIdpConnection samlSpIdpConnection =
				_samlSpIdpConnectionLocalService.getSamlSpIdpConnection(
					companyId, entityId);

			forceAuthn = samlSpIdpConnection.isForceAuthn();
		}
		catch (NoSuchSpIdpConnectionException nssice) {
		}

		authnRequest.setForceAuthn(forceAuthn);

		samlMessageContext.setOutboundSAMLMessage(authnRequest);

		if (spSsoDescriptor.isAuthnRequestsSigned() ||
			idpSsoDescriptor.getWantAuthnRequestsSigned()) {

			Credential credential = metadataManager.getSigningCredential();

			samlMessageContext.setOutboundSAMLMessageSigningCredential(
				credential);

			OpenSamlUtil.signObject(authnRequest, credential);
		}

		samlMessageContext.setPeerEntityEndpoint(singleSignOnService);
		samlMessageContext.setRelayState(relayState);

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			request);

		_samlSpAuthRequestLocalService.addSamlSpAuthRequest(
			samlMessageContext.getPeerEntityId(), authnRequest.getID(),
			serviceContext);

		sendSamlMessage(samlMessageContext);
	}

	protected Assertion getSuccessAssertion(
		SamlSsoRequestContext samlSsoRequestContext,
		AssertionConsumerService assertionConsumerService, NameID nameId) {

		SAMLMessageContext<AuthnRequest, Response, NameID> samlMessageContext =
			samlSsoRequestContext.getSAMLMessageContext();

		Assertion assertion = OpenSamlUtil.buildAssertion();

		DateTime issueInstantDateTime = new DateTime(DateTimeZone.UTC);

		SubjectConfirmationData subjectConfirmationData =
			getSuccessSubjectConfirmationData(
				samlSsoRequestContext, assertionConsumerService,
				issueInstantDateTime);

		Conditions conditions = getSuccessConditions(
			samlSsoRequestContext, issueInstantDateTime,
			subjectConfirmationData.getNotOnOrAfter());

		assertion.setConditions(conditions);

		assertion.setID(generateIdentifier(20));
		assertion.setIssueInstant(issueInstantDateTime);

		Issuer issuer = OpenSamlUtil.buildIssuer(
			samlMessageContext.getLocalEntityId());

		assertion.setIssuer(issuer);

		Subject subject = getSuccessSubject(
			samlSsoRequestContext, assertionConsumerService, nameId,
			subjectConfirmationData);

		assertion.setSubject(subject);

		assertion.setVersion(SAMLVersion.VERSION_20);

		List<AuthnStatement> authnStatements = assertion.getAuthnStatements();

		authnStatements.add(
			getSuccessAuthnStatement(samlSsoRequestContext, assertion));

		boolean attributesEnabled = metadataManager.isAttributesEnabled(
			samlMessageContext.getPeerEntityId());

		if (!attributesEnabled) {
			return assertion;
		}

		User user = samlSsoRequestContext.getUser();

		List<Attribute> attributes = _attributeResolver.resolve(
			user, samlMessageContext);

		if (attributes.isEmpty()) {
			return assertion;
		}

		List<AttributeStatement> attributeStatements =
			assertion.getAttributeStatements();

		AttributeStatement attributeStatement =
			OpenSamlUtil.buildAttributeStatement();

		attributeStatements.add(attributeStatement);

		List<Attribute> attributeStatementAttributes =
			attributeStatement.getAttributes();

		attributeStatementAttributes.addAll(attributes);

		return assertion;
	}

	protected AudienceRestriction getSuccessAudienceRestriction(
		String entityId) {

		AudienceRestriction audienceRestriction =
			OpenSamlUtil.buildAudienceRestriction();

		List<Audience> audiences = audienceRestriction.getAudiences();

		Audience audience = OpenSamlUtil.buildAudience();

		audience.setAudienceURI(entityId);

		audiences.add(audience);

		return audienceRestriction;
	}

	protected AuthnContext getSuccessAuthnContext() {
		AuthnContext authnContext = OpenSamlUtil.buildAuthnContext();

		AuthnContextClassRef authnContextClassRef =
			OpenSamlUtil.buildAuthnContextClassRef();

		authnContextClassRef.setAuthnContextClassRef(
			AuthnContext.UNSPECIFIED_AUTHN_CTX);

		authnContext.setAuthnContextClassRef(authnContextClassRef);

		return authnContext;
	}

	protected AuthnStatement getSuccessAuthnStatement(
		SamlSsoRequestContext samlSsoRequestContext, Assertion assertion) {

		AuthnStatement authnStatement = OpenSamlUtil.buildAuthnStatement();

		AuthnContext authnContext = getSuccessAuthnContext();

		authnStatement.setAuthnContext(authnContext);

		authnStatement.setAuthnInstant(assertion.getIssueInstant());
		authnStatement.setSessionIndex(
			samlSsoRequestContext.getSamlSsoSessionId());

		return authnStatement;
	}

	protected Conditions getSuccessConditions(
		SamlSsoRequestContext samlSsoRequestContext, DateTime notBeforeDateTime,
		DateTime notOnOrAfterDateTime) {

		Conditions conditions = OpenSamlUtil.buildConditions();

		conditions.setNotBefore(notBeforeDateTime);
		conditions.setNotOnOrAfter(notOnOrAfterDateTime);

		List<AudienceRestriction> audienceRestrictions =
			conditions.getAudienceRestrictions();

		SAMLMessageContext<AuthnRequest, Response, NameID> samlMessageContext =
			samlSsoRequestContext.getSAMLMessageContext();

		AudienceRestriction audienceRestriction = getSuccessAudienceRestriction(
			samlMessageContext.getPeerEntityId());

		audienceRestrictions.add(audienceRestriction);

		return conditions;
	}

	protected NameID getSuccessNameId(
			SamlSsoRequestContext samlSsoRequestContext)
		throws Exception {

		SAMLMessageContext<AuthnRequest, Response, NameID> samlMessageContext =
			samlSsoRequestContext.getSAMLMessageContext();

		User user = samlSsoRequestContext.getUser();

		NameIDPolicy nameIDPolicy = null;

		AuthnRequest authnRequest = samlMessageContext.getInboundSAMLMessage();

		if (authnRequest != null) {
			nameIDPolicy = authnRequest.getNameIDPolicy();
		}

		return _nameIdResolver.resolve(
			user, samlMessageContext.getPeerEntityId(), nameIDPolicy);
	}

	protected Response getSuccessResponse(
		SamlSsoRequestContext samlSsoRequestContext,
		AssertionConsumerService assertionConsumerService,
		Assertion assertion) {

		Response response = OpenSamlUtil.buildResponse();

		response.setDestination(assertionConsumerService.getLocation());
		response.setID(generateIdentifier(20));

		SAMLMessageContext<AuthnRequest, Response, NameID> samlMessageContext =
			samlSsoRequestContext.getSAMLMessageContext();

		if (Validator.isNotNull(samlMessageContext.getInboundSAMLMessageId())) {
			response.setInResponseTo(
				samlMessageContext.getInboundSAMLMessageId());
		}

		response.setIssueInstant(assertion.getIssueInstant());

		Issuer issuer = OpenSamlUtil.buildIssuer(
			samlMessageContext.getLocalEntityId());

		response.setIssuer(issuer);

		StatusCode statusCode = OpenSamlUtil.buildStatusCode(
			StatusCode.SUCCESS_URI);

		Status status = OpenSamlUtil.buildStatus(statusCode);

		response.setStatus(status);

		response.setVersion(SAMLVersion.VERSION_20);

		List<Assertion> assertions = response.getAssertions();

		assertions.add(assertion);

		return response;
	}

	protected Subject getSuccessSubject(
		SamlSsoRequestContext samlSsoRequestContext,
		AssertionConsumerService assertionConsumerService, NameID nameId,
		SubjectConfirmationData subjectConfirmationData) {

		SubjectConfirmation subjectConfirmation =
			OpenSamlUtil.buildSubjectConfirmation();

		subjectConfirmation.setMethod(SubjectConfirmation.METHOD_BEARER);
		subjectConfirmation.setSubjectConfirmationData(subjectConfirmationData);

		Subject subject = OpenSamlUtil.buildSubject(nameId);

		List<SubjectConfirmation> subjectConfirmations =
			subject.getSubjectConfirmations();

		subjectConfirmations.add(subjectConfirmation);

		return subject;
	}

	protected SubjectConfirmationData getSuccessSubjectConfirmationData(
		SamlSsoRequestContext samlSsoRequestContext,
		AssertionConsumerService assertionConsumerService,
		DateTime issueInstantDateTime) {

		SubjectConfirmationData subjectConfirmationData =
			OpenSamlUtil.buildSubjectConfirmationData();

		SAMLMessageContext<AuthnRequest, Response, NameID> samlMessageContext =
			samlSsoRequestContext.getSAMLMessageContext();

		subjectConfirmationData.setInResponseTo(
			samlMessageContext.getInboundSAMLMessageId());

		subjectConfirmationData.setRecipient(
			assertionConsumerService.getLocation());

		int assertionLifetime = metadataManager.getAssertionLifetime(
			samlMessageContext.getPeerEntityId());

		DateTime notOnOrAfterDateTime = issueInstantDateTime.plusSeconds(
			assertionLifetime);

		subjectConfirmationData.setNotOnOrAfter(notOnOrAfterDateTime);

		return subjectConfirmationData;
	}

	protected void redirectToLogin(
		HttpServletRequest request, HttpServletResponse response,
		SamlSsoRequestContext samlSsoRequestContext, boolean forceAuthn) {

		HttpSession session = request.getSession();

		if (forceAuthn) {
			logout(request, response);

			session = request.getSession(true);

			session.setAttribute(
				PortletWebKeys.FORCE_REAUHENTICATION, Boolean.TRUE);
		}

		samlSsoRequestContext.setSAMLMessageContext(null);

		session.setAttribute(
			PortletWebKeys.SAML_SSO_REQUEST_CONTEXT, samlSsoRequestContext);

		response.addHeader(
			HttpHeaders.CACHE_CONTROL,
			HttpHeaders.CACHE_CONTROL_NO_CACHE_VALUE);
		response.addHeader(
			HttpHeaders.PRAGMA, HttpHeaders.PRAGMA_NO_CACHE_VALUE);

		StringBundler sb = new StringBundler(4);

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		sb.append(themeDisplay.getPathMain());

		sb.append("/portal/login?redirect=");
		sb.append(themeDisplay.getPathMain());
		sb.append("/portal/saml/sso");

		String redirect = sb.toString();

		try {
			response.sendRedirect(redirect);
		}
		catch (IOException ioe) {
			throw new SystemException(ioe);
		}
	}

	protected void sendFailureResponse(
			SamlSsoRequestContext samlSsoRequestContext, String statusURI)
		throws PortalException {

		SAMLMessageContext<AuthnRequest, Response, NameID> samlMessageContext =
			samlSsoRequestContext.getSAMLMessageContext();

		SamlBinding samlBinding = getSamlBinding(
			SAMLConstants.SAML2_POST_BINDING_URI);

		AssertionConsumerService assertionConsumerService =
			SamlUtil.resolverAssertionConsumerService(
				samlMessageContext, samlBinding.getCommunicationProfileId());

		samlMessageContext.setPeerEntityEndpoint(assertionConsumerService);

		try {
			Credential credential = metadataManager.getSigningCredential();

			samlMessageContext.setOutboundSAMLMessageSigningCredential(
				credential);
		}
		catch (SecurityException se) {
			throw new SamlException(se);
		}

		Response response = OpenSamlUtil.buildResponse();

		response.setDestination(assertionConsumerService.getLocation());
		response.setInResponseTo(samlMessageContext.getInboundSAMLMessageId());

		DateTime issueInstantDateTime = new DateTime(DateTimeZone.UTC);

		response.setIssueInstant(issueInstantDateTime);

		Issuer issuer = OpenSamlUtil.buildIssuer(
			samlMessageContext.getLocalEntityId());

		response.setIssuer(issuer);

		StatusCode statusCode = OpenSamlUtil.buildStatusCode(statusURI);

		Status status = OpenSamlUtil.buildStatus(statusCode);

		response.setStatus(status);

		samlMessageContext.setOutboundSAMLMessage(response);

		sendSamlMessage(samlMessageContext);
	}

	protected void sendSuccessResponse(
			HttpServletRequest request, HttpServletResponse response,
			SamlSsoRequestContext samlSsoRequestContext)
		throws Exception {

		SAMLMessageContext<AuthnRequest, Response, NameID> samlMessageContext =
			samlSsoRequestContext.getSAMLMessageContext();

		SamlBinding samlBinding = getSamlBinding(
			SAMLConstants.SAML2_POST_BINDING_URI);

		AssertionConsumerService assertionConsumerService =
			SamlUtil.resolverAssertionConsumerService(
				samlMessageContext, samlBinding.getCommunicationProfileId());

		NameID nameId = getSuccessNameId(samlSsoRequestContext);

		Assertion assertion = getSuccessAssertion(
			samlSsoRequestContext, assertionConsumerService, nameId);

		Credential credential = metadataManager.getSigningCredential();

		SPSSODescriptor spSsoDescriptor =
			(SPSSODescriptor)samlMessageContext.getPeerEntityRoleMetadata();

		if (spSsoDescriptor.getWantAssertionsSigned()) {
			OpenSamlUtil.signObject(assertion, credential);
		}

		Response samlResponse = getSuccessResponse(
			samlSsoRequestContext, assertionConsumerService, assertion);

		samlMessageContext.setOutboundSAMLMessage(samlResponse);

		samlMessageContext.setOutboundSAMLMessageSigningCredential(credential);
		samlMessageContext.setOutboundSAMLProtocol(SAMLConstants.SAML20P_NS);

		samlMessageContext.setPeerEntityEndpoint(assertionConsumerService);

		if (samlSsoRequestContext.isNewSession()) {
			addSamlSsoSession(request, response, samlSsoRequestContext, nameId);
		}
		else {
			updateSamlSsoSession(request, samlSsoRequestContext, nameId);
		}

		sendSamlMessage(samlMessageContext);
	}

	protected void updateSamlSsoSession(
			HttpServletRequest request,
			SamlSsoRequestContext samlSsoRequestContext, NameID nameId)
		throws Exception {

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			request);

		SamlIdpSsoSession samlIdpSsoSession =
			_samlIdpSsoSessionLocalService.updateModifiedDate(
				samlSsoRequestContext.getSamlSsoSessionId());

		SAMLMessageContext<AuthnRequest, Response, NameID> samlMessageContext =
			samlSsoRequestContext.getSAMLMessageContext();

		try {
			_samlIdpSpSessionLocalService.updateModifiedDate(
				samlIdpSsoSession.getSamlIdpSsoSessionId(),
				samlMessageContext.getPeerEntityId());
		}
		catch (NoSuchIdpSpSessionException nsisse) {
			_samlIdpSpSessionLocalService.addSamlIdpSpSession(
				samlIdpSsoSession.getSamlIdpSsoSessionId(),
				samlMessageContext.getPeerEntityId(), nameId.getFormat(),
				nameId.getValue(), serviceContext);
		}
	}

	protected void verifyAssertion(
			Assertion assertion,
			SAMLMessageContext<?, ?, NameID> samlMessageContext,
			TrustEngine<Signature> trustEngine)
		throws PortalException {

		verifyReplay(samlMessageContext, assertion);
		verifyIssuer(samlMessageContext, assertion.getIssuer());
		verifyAssertionSignature(
			assertion.getSignature(), samlMessageContext, trustEngine);
		verifyConditions(samlMessageContext, assertion.getConditions());
		verifySubject(samlMessageContext, assertion.getSubject());
	}

	protected void verifyAssertionSignature(
			Signature signature, SAMLMessageContext<?, ?, ?> samlMessageContext,
			TrustEngine<Signature> trustEngine)
		throws PortalException {

		SPSSODescriptor spSsoDescriptor =
			(SPSSODescriptor)samlMessageContext.getLocalEntityRoleMetadata();

		if (signature != null) {
			verifySignature(samlMessageContext, signature, trustEngine);
		}
		else if (spSsoDescriptor.getWantAssertionsSigned() &&
				 (signature == null)) {

			throw new SignatureException("SAML assertion is not signed");
		}
	}

	protected void verifyAudienceRestrictions(
			List<AudienceRestriction> audienceRestrictions,
			SAMLMessageContext<?, ?, ?> samlMessageContext)
		throws PortalException {

		if (audienceRestrictions.isEmpty()) {
			return;
		}

		for (AudienceRestriction audienceRestriction : audienceRestrictions) {
			for (Audience audience : audienceRestriction.getAudiences()) {
				String audienceURI = audience.getAudienceURI();

				if (audienceURI.equals(samlMessageContext.getLocalEntityId())) {
					return;
				}
			}
		}

		throw new AudienceException("Unable verify audience");
	}

	protected void verifyConditions(
			SAMLMessageContext<?, ?, ?> samlMessageContext,
			Conditions conditions)
		throws PortalException {

		verifyAudienceRestrictions(
			conditions.getAudienceRestrictions(), samlMessageContext);

		if (conditions.getNotOnOrAfter() != null) {
			verifyNotOnOrAfterDateTime(
				metadataManager.getClockSkew(), conditions.getNotOnOrAfter());
		}
	}

	protected void verifyDestination(
			SAMLMessageContext<?, ?, ?> samlMessageContext, String destination)
		throws PortalException {

		SPSSODescriptor spSsoDescriptor =
			(SPSSODescriptor)samlMessageContext.getLocalEntityRoleMetadata();

		List<AssertionConsumerService> assertionConsumerServices =
			spSsoDescriptor.getAssertionConsumerServices();

		for (AssertionConsumerService assertionConsumerService :
				assertionConsumerServices) {

			String binding = assertionConsumerService.getBinding();

			if (destination.equals(assertionConsumerService.getLocation()) &&
				binding.equals(
					samlMessageContext.getCommunicationProfileId())) {

				return;
			}
		}

		throw new DestinationException(
			"Destination " + destination + " does not match any assertion " +
				"consumer location with binding " +
					samlMessageContext.getCommunicationProfileId());
	}

	protected void verifyInResponseTo(Response samlResponse)
		throws PortalException {

		if (Validator.isNull(samlResponse.getInResponseTo())) {
			return;
		}

		Issuer issuer = samlResponse.getIssuer();

		String issuerEntityId = issuer.getValue();

		String inResponseTo = samlResponse.getInResponseTo();

		SamlSpAuthRequest samlSpAuthRequest =
			_samlSpAuthRequestLocalService.fetchSamlSpAuthRequest(
				issuerEntityId, inResponseTo);

		if (samlSpAuthRequest != null) {
			_samlSpAuthRequestLocalService.deleteSamlSpAuthRequest(
				samlSpAuthRequest);
		}
		else {
			throw new InResponseToException(
				"Response in response to " + inResponseTo + " does not match " +
					"any authentication requests");
		}
	}

	protected void verifyIssuer(
			SAMLMessageContext<?, ?, ?> samlMessageContext, Issuer issuer)
		throws PortalException {

		String issuerFormat = issuer.getFormat();

		if ((issuerFormat != null) && !issuerFormat.equals(NameIDType.ENTITY)) {
			throw new IssuerException("Invalid issuer format " + issuerFormat);
		}

		String peerEntityId = samlMessageContext.getPeerEntityId();

		if (!peerEntityId.equals(issuer.getValue())) {
			throw new IssuerException(
				"Issuer does not match expected peer entity ID " +
					peerEntityId);
		}
	}

	protected void verifyNotOnOrAfterDateTime(long clockSkew, DateTime dateTime)
		throws PortalException {

		DateTime nowDateTime = new DateTime(DateTimeZone.UTC);

		DateTime upperBoundDateTime = dateTime.plusMillis((int)clockSkew);

		if (upperBoundDateTime.isBefore(nowDateTime)) {
			throw new ExpiredException(
				"Date " + upperBoundDateTime.toString() + " is before " +
					nowDateTime.toString());
		}
	}

	protected void verifyReplay(
			SAMLMessageContext<?, ?, ?> samlMessageContext, Assertion assertion)
		throws PortalException {

		Issuer issuer = assertion.getIssuer();

		String idpEntityId = issuer.getValue();

		String messageKey = assertion.getID();

		DateTime notOnOrAfter = new DateTime();

		notOnOrAfter = notOnOrAfter.plusMillis(
			_samlConfiguration.getReplayChacheDuration());

		try {
			SamlSpMessage samlSpMessage =
				_samlSpMessageLocalService.fetchSamlSpMessage(
					idpEntityId, messageKey);

			if ((samlSpMessage != null) && !samlSpMessage.isExpired()) {
				throw new ReplayException(
					"SAML assertion " + messageKey + " replayed from IdP " +
						idpEntityId);
			}
			else {
				if (samlSpMessage != null) {
					_samlSpMessageLocalService.deleteSamlSpMessage(
						samlSpMessage);
				}

				ServiceContext serviceContext = new ServiceContext();

				long companyId = CompanyThreadLocal.getCompanyId();

				serviceContext.setCompanyId(companyId);

				_samlSpMessageLocalService.addSamlSpMessage(
					idpEntityId, messageKey, notOnOrAfter.toDate(),
					serviceContext);
			}
		}
		catch (SystemException se) {
			throw new SamlException(se);
		}
	}

	protected void verifySignature(
			SAMLMessageContext<?, ?, ?> samlMessageContext, Signature signature,
			TrustEngine<Signature> trustEngine)
		throws PortalException {

		try {
			_samlSignatureProfileValidator.validate(signature);

			CriteriaSet criteriaSet = new CriteriaSet();

			criteriaSet.add(
				new EntityIDCriteria(samlMessageContext.getPeerEntityId()));
			criteriaSet.add(
				new MetadataCriteria(
					IDPSSODescriptor.DEFAULT_ELEMENT_NAME,
					SAMLConstants.SAML20P_NS));
			criteriaSet.add(new UsageCriteria(UsageType.SIGNING));

			if (!trustEngine.validate(signature, criteriaSet)) {
				throw new SignatureException("Unable validate signature trust");
			}
		}
		catch (Exception e) {
			if (e instanceof PortalException) {
				throw (PortalException)e;
			}

			throw new SignatureException("Unable to verify signature", e);
		}
	}

	protected void verifySubject(
			SAMLMessageContext<?, ?, NameID> samlMessageContext,
			Subject subject)
		throws PortalException {

		List<SubjectConfirmation> subjectConfirmations =
			subject.getSubjectConfirmations();

		for (SubjectConfirmation subjectConfirmation : subjectConfirmations) {
			String method = subjectConfirmation.getMethod();

			if (method.equals(SubjectConfirmation.METHOD_BEARER)) {
				SubjectConfirmationData subjectConfirmationData =
					subjectConfirmation.getSubjectConfirmationData();

				if (subjectConfirmationData == null) {
					continue;
				}

				verifyNotOnOrAfterDateTime(
					metadataManager.getClockSkew(),
					subjectConfirmationData.getNotOnOrAfter());

				if (Validator.isNull(subjectConfirmationData.getRecipient())) {
					continue;
				}
				else {
					verifyDestination(
						samlMessageContext,
						subjectConfirmationData.getRecipient());
				}

				NameID nameId = subject.getNameID();

				samlMessageContext.setSubjectNameIdentifier(nameId);

				return;
			}
		}

		throw new SubjectException("Unable to verify subject");
	}

	private static final Log _log = LogFactoryUtil.getLog(
		WebSsoProfileImpl.class);

	private static final SAMLSignatureProfileValidator
		_samlSignatureProfileValidator = new SAMLSignatureProfileValidator();

	private AttributeResolver _attributeResolver;
	private NameIdResolver _nameIdResolver;
	private SAMLConfiguration _samlConfiguration;

	@Reference
	private SamlIdpSpSessionLocalService _samlIdpSpSessionLocalService;

	@Reference
	private SamlIdpSsoSessionLocalService _samlIdpSsoSessionLocalService;

	@Reference
	private SamlSpAuthRequestLocalService _samlSpAuthRequestLocalService;

	@Reference
	private SamlSpIdpConnectionLocalService _samlSpIdpConnectionLocalService;

	@Reference
	private SamlSpMessageLocalService _samlSpMessageLocalService;

	@Reference
	private SamlSpSessionLocalService _samlSpSessionLocalService;

	private UserResolver _userResolver;

}