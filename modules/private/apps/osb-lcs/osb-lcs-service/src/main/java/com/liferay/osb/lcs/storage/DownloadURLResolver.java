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

package com.liferay.osb.lcs.storage;

import com.liferay.osb.lcs.configuration.OSBLCSConfiguration;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.StringPool;

import java.net.URL;

import java.nio.charset.StandardCharsets;

import java.util.Map;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Igor Beslic
 */
@Component(
	configurationPid = "com.liferay.osb.lcs.configuration.OSBLCSConfiguration",
	configurationPolicy = ConfigurationPolicy.REQUIRE, immediate = true
)
public class DownloadURLResolver {

	public URL getAsURL(String fileName) {
		HttpPost httpPost = new HttpPost(
			_contextPath + StringPool.SLASH + fileName);

		httpPost.setConfig(_requestConfig);

		try {
			HttpHost httpHost = new HttpHost(
				_osbLCSConfiguration.patchStorageManagerHostName(),
				_osbLCSConfiguration.patchStorageManagerHostPort());

			HttpResponse httpResponse = _httpClient.execute(httpHost, httpPost);

			StatusLine statusLine = httpResponse.getStatusLine();

			if (statusLine.getStatusCode() == HttpStatus.SC_NOT_FOUND) {
				if (_log.isWarnEnabled()) {
					_log.warn("Status code " + statusLine.getStatusCode());
				}

				return null;
			}

			String key = EntityUtils.toString(
				httpResponse.getEntity(), StandardCharsets.UTF_8);

			String keyPath = _contextPath.concat(StringPool.SLASH).concat(key);

			URL url = new URL(
				Http.HTTP,
				_osbLCSConfiguration.patchStorageManagerDownloadHostName(),
				_osbLCSConfiguration.patchStorageManagerDownloadHostPort(),
				keyPath);

			return url;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		finally {
			httpPost.releaseConnection();
		}
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_osbLCSConfiguration = ConfigurableUtil.createConfigurable(
			OSBLCSConfiguration.class, properties);

		_contextPath = _osbLCSConfiguration.patchStorageManagerContextPath();

		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

		PoolingHttpClientConnectionManager poolingHttpClientConnectionManager =
			new PoolingHttpClientConnectionManager();

		poolingHttpClientConnectionManager.setMaxTotal(20);

		httpClientBuilder.setConnectionManager(
			poolingHttpClientConnectionManager);

		_httpClient = httpClientBuilder.build();

		RequestConfig.Builder requestConfigBuilder = RequestConfig.custom();

		requestConfigBuilder.setConnectTimeout(30000);

		_requestConfig = requestConfigBuilder.build();
	}

	@Deactivate
	protected void deactivate() {
		_osbLCSConfiguration = null;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DownloadURLResolver.class);

	private static volatile OSBLCSConfiguration _osbLCSConfiguration;

	private String _contextPath;
	private HttpClient _httpClient;
	private RequestConfig _requestConfig;

}