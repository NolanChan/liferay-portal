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
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import com.liferay.osb.lcs.storage.StorageManager;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class S3StorageManagerImpl implements StorageManager {

	@Override
	public Map<String, Long> getFileNamesSizes() {
		Map<String, Long> patchFileNamesSizes = new HashMap<>();

		ObjectListing objectListing = _amazonS3.listObjects(
			_bucketName, _prefix);

		List<S3ObjectSummary> s3ObjectSummaries =
			objectListing.getObjectSummaries();

		for (S3ObjectSummary s3ObjectSummary : s3ObjectSummaries) {
			patchFileNamesSizes.put(
				getFileName(s3ObjectSummary), s3ObjectSummary.getSize());
		}

		return patchFileNamesSizes;
	}

	@Override
	public Map<String, Long> getFileNamesSizes(Date sinceModifiedDate) {
		Map<String, Long> fileNames = new HashMap<>();

		ObjectListing objectListing = _amazonS3.listObjects(
			_bucketName, _prefix);

		List<S3ObjectSummary> s3ObjectSummaries =
			objectListing.getObjectSummaries();

		for (S3ObjectSummary s3ObjectSummary : s3ObjectSummaries) {
			Date lastModifiedDate = s3ObjectSummary.getLastModified();

			if (lastModifiedDate.before(sinceModifiedDate)) {
				continue;
			}

			String fileName = getFileName(s3ObjectSummary);

			if (fileName == null) {
				continue;
			}

			fileNames.put(fileName, s3ObjectSummary.getSize());
		}

		return fileNames;
	}

	@Override
	public Map<String, Long> getFileNamesSizes(String nameFragment) {
		Map<String, Long> filteredFileNameSizes = new HashMap<>();

		Map<String, Long> fileNameSizes = getFileNamesSizes();

		for (Map.Entry<String, Long> fileNameSizeEntry :
				fileNameSizes.entrySet()) {

			String fileName = fileNameSizeEntry.getKey();

			if (!fileName.contains(nameFragment)) {
				continue;
			}

			fileNameSizes.put(
				fileNameSizeEntry.getKey(), fileNameSizeEntry.getValue());
		}

		return filteredFileNameSizes;
	}

	@Override
	public void setBucketName(String bucketName) {
		_bucketName = bucketName;
	}

	@Override
	public void setCredentials(String accessKey, String secretKey) {
		AWSCredentials awsCredentials = new BasicAWSCredentials(
			accessKey, secretKey);

		_amazonS3 = new AmazonS3Client(awsCredentials);
	}

	@Override
	public void setPath(String path) {
	}

	@Override
	public void setPrefix(String prefix) {
		_prefix = prefix;
	}

	@Override
	public void writeFile(File file) {
		PutObjectRequest putObjectRequest = new PutObjectRequest(
			_bucketName,
			_prefix.concat(StringPool.SLASH).concat(file.getName()), file);

		_amazonS3.putObject(putObjectRequest);
	}

	@Override
	public void writeFile(String key, String message, String throwable) {
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

			writeFile(tempFile);
		}
		catch (Exception e) {
			_log.error(e.getLocalizedMessage());

			throw new RuntimeException(e);
		}
		finally {
			FileUtil.delete(tempFile);
		}
	}

	protected String getFileName(S3ObjectSummary s3ObjectSummary) {
		String key = s3ObjectSummary.getKey();

		String dirName = _prefix.concat(StringPool.SLASH);

		return key.replace(dirName, StringPool.BLANK);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		S3StorageManagerImpl.class);

	private AmazonS3 _amazonS3;
	private String _bucketName;
	private String _prefix;

}