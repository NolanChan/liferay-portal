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

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;

import com.liferay.osb.lcs.storage.LogStorageManager;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * @author Mladen Cikara
 */
public class LogS3StorageManagerImpl
	extends BaseStorageManagerImpl implements LogStorageManager {

	@Override
	public void afterPropertiesSet() {
		AWSCredentials awsCredentials = new BasicAWSCredentials(
			_accessKey, _secretKey);

		_amazonS3 = new AmazonS3Client(awsCredentials);
	}

	@Override
	public void setAccessKey(String accessKey) {
		_accessKey = accessKey;
	}

	public void setBucketName(String bucketName) {
		_bucketName = bucketName;
	}

	@Override
	public void setPrefix(String prefix) {
		_prefix = prefix;
	}

	@Override
	public void setSecretKey(String secretKey) {
		_secretKey = secretKey;
	}

	@Override
	public void writeLogFile(String key, String message, String throwable) {
		File tempFile = FileUtil.createTempFile(
			key.concat("_").concat(String.valueOf(System.currentTimeMillis())),
			"log");

		try {
			BufferedWriter bufferedWriter = new BufferedWriter(
				new FileWriter(tempFile));

			bufferedWriter.write("[" + key + "]-");
			bufferedWriter.write(message + "-");
			bufferedWriter.write(throwable);
			bufferedWriter.write("[END]");

			bufferedWriter.close();

			PutObjectRequest putObjectRequest = new PutObjectRequest(
				_bucketName,
				_prefix.concat(StringPool.SLASH).concat(tempFile.getName()),
				tempFile);

			_amazonS3.putObject(putObjectRequest);
		}
		catch (Exception e) {
			_log.error(e.getLocalizedMessage());

			throw new RuntimeException(e);
		}
		finally {
			FileUtil.delete(tempFile);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LogS3StorageManagerImpl.class);

	private String _accessKey;
	private AmazonS3 _amazonS3;
	private String _bucketName;
	private String _prefix;
	private String _secretKey;

}