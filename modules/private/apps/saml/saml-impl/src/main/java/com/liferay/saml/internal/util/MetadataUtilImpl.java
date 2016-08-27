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

package com.liferay.saml.internal.util;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayOutputStream;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.saml.runtime.configuration.MetadataUtilConfiguration;
import com.liferay.saml.util.MetadataUtil;
import com.liferay.saml.util.SamlUtil;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;

import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;

import org.opensaml.saml2.metadata.EntityDescriptor;
import org.opensaml.xml.XMLObject;
import org.opensaml.xml.parse.ParserPool;
import org.opensaml.xml.util.XMLObjectHelper;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Mika Koivisto
 */
@Component(
	configurationPid = "com.liferay.saml.util.MetadataUtilConfiguration",
	configurationPolicy = ConfigurationPolicy.OPTIONAL, immediate = true,
	service = MetadataUtil.class
)
public class MetadataUtilImpl implements MetadataUtil {

	public InputStream getMetadata(String url) {
		GetMethod getMethod = new GetMethod(url);

		try {
			_httpClient.executeMethod(getMethod);

			if (getMethod.getStatusCode() != HttpStatus.SC_OK) {
				return null;
			}

			InputStream inputStream = getMethod.getResponseBodyAsStream();

			Header header = getMethod.getResponseHeader(
				HttpHeaders.CONTENT_ENCODING);

			if (header != null) {
				String contentEncoding = header.getValue();

				if (StringUtil.equalsIgnoreCase(contentEncoding, "deflate")) {
					inputStream = new InflaterInputStream(inputStream);
				}
				else if (StringUtil.equalsIgnoreCase(contentEncoding, "gzip")) {
					inputStream = new GZIPInputStream(inputStream);
				}
			}

			UnsyncByteArrayOutputStream unsyncByteArrayOutputStream =
				new UnsyncByteArrayOutputStream();

			StreamUtil.transfer(inputStream, unsyncByteArrayOutputStream);

			byte[] bytes = unsyncByteArrayOutputStream.toByteArray();

			return new ByteArrayInputStream(bytes);
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to get metadata from " + url, e);
			}
		}
		finally {
			getMethod.releaseConnection();
		}

		return null;
	}

	public String parseMetadataXml(InputStream inputStream, String entityId)
		throws Exception {

		try {
			XMLObject xmlObject = XMLObjectHelper.unmarshallFromInputStream(
				parserPool, inputStream);

			EntityDescriptor entityDescriptor =
				SamlUtil.getEntityDescriptorById(entityId, xmlObject);

			if (entityDescriptor == null) {
				return null;
			}

			StringWriter stringWriter = new StringWriter();

			XMLObjectHelper.marshallToWriter(entityDescriptor, stringWriter);

			return stringWriter.toString();
		}
		finally {
			if (inputStream != null) {
				StreamUtil.cleanUp(inputStream);
			}
		}
	}

	@Activate
	protected void activate(
		BundleContext bundleContext, Map<String, Object> properties) {

		MetadataUtilConfiguration metadataUtilConfiguration =
			ConfigurableUtil.createConfigurable(
				MetadataUtilConfiguration.class, properties);

		HttpClientParams httpClientParams = new HttpClientParams();

		httpClientParams.setConnectionManagerTimeout(
			metadataUtilConfiguration.getConnectionManagerTimeout());
		httpClientParams.setSoTimeout(metadataUtilConfiguration.getSoTimeout());

		_httpClient = new HttpClient(
			httpClientParams, new MultiThreadedHttpConnectionManager());

		_httpClientServiceRegistration = bundleContext.registerService(
			HttpClient.class, _httpClient, null);
	}

	@Deactivate
	protected void deactivate() {
		_httpClientServiceRegistration.unregister();
	}

	@Reference
	protected ParserPool parserPool;

	private static final Log _log = LogFactoryUtil.getLog(
		MetadataUtilImpl.class);

	private HttpClient _httpClient;
	private ServiceRegistration<HttpClient> _httpClientServiceRegistration;

}