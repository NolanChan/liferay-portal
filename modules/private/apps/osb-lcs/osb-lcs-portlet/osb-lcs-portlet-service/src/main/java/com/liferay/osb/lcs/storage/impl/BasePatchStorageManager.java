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

package com.liferay.osb.lcs.storage.impl;

import com.liferay.osb.lcs.storage.PatchStorageManager;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.StringPool;

import java.net.URL;

import java.nio.charset.StandardCharsets;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

/**
 * @author Ivica Cardic
 */
public abstract class BasePatchStorageManager
	extends BaseStorageManagerImpl implements PatchStorageManager {

	@Override
	public void afterPropertiesSet() {
		PoolingClientConnectionManager poolingClientConnectionManager =
			new PoolingClientConnectionManager();

		poolingClientConnectionManager.setMaxTotal(20);

		_defaultHttpClient = new DefaultHttpClient(
			poolingClientConnectionManager);

		HttpParams httpParams = _defaultHttpClient.getParams();

		httpParams.setIntParameter(
			CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
	}

	@Override
	public void destroy() {
		ClientConnectionManager clientConnectionManager =
			_defaultHttpClient.getConnectionManager();

		clientConnectionManager.shutdown();
	}

	@Override
	public URL getPatchAsURL(String patchFileName) {
		HttpPost httpPost = new HttpPost(
			_contextPath + StringPool.SLASH + patchFileName);

		try {
			HttpHost httpHost = new HttpHost(_hostName, _hostPort);

			HttpResponse httpResponse = _defaultHttpClient.execute(
				httpHost, httpPost);

			StatusLine statusLine = httpResponse.getStatusLine();

			if (statusLine.getStatusCode() ==
					HttpServletResponse.SC_NOT_FOUND) {

				if (_log.isWarnEnabled()) {
					_log.warn("Status code " + statusLine.getStatusCode());
				}

				return null;
			}

			String key = EntityUtils.toString(
				httpResponse.getEntity(), StandardCharsets.UTF_8);

			String keyPath = _contextPath.concat(StringPool.SLASH).concat(key);

			URL url = new URL(
				_protocol, _downloadHostName, _downloadHostPort, keyPath);

			return url;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		finally {
			httpPost.releaseConnection();
		}
	}

	@Override
	public List<String> getPatchFileNames(Date sinceModifiedDate) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<String> getPatchFileNames(int buildNumber) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setAccessKey(String accessKey) {
	}

	@Override
	public void setBucketName(String bucketName) {
	}

	@Override
	public void setContextPath(String contextPath) {
		if (contextPath == null) {
			contextPath = StringPool.BLANK;
		}

		_contextPath = contextPath;
	}

	@Override
	public void setDownloadHostName(String downloadHostName) {
		_downloadHostName = downloadHostName;
	}

	@Override
	public void setDownloadHostPort(int downloadHostPort) {
		_downloadHostPort = downloadHostPort;
	}

	@Override
	public void setHostName(String hostName) {
		_hostName = hostName;
	}

	@Override
	public void setHostPort(int hostPort) {
		_hostPort = hostPort;
	}

	@Override
	public void setPath(String path) {
	}

	@Override
	public void setPrefix(String prefix) {
	}

	@Override
	public void setProtocol(String protocol) {
		_protocol = protocol;
	}

	@Override
	public void setSecretKey(String secretKey) {
	}

	protected boolean isPatch(String fileName) {
		if (!fileName.endsWith(".zip")) {
			return false;
		}

		if (!fileName.contains("fix-pack") && !fileName.contains("hotfix")) {
			return false;
		}

		return true;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		BasePatchStorageManager.class);

	private String _contextPath;
	private DefaultHttpClient _defaultHttpClient;
	private String _downloadHostName;
	private int _downloadHostPort;
	private String _hostName;
	private int _hostPort;
	private String _protocol = Http.HTTP;

}