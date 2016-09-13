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

package com.liferay.osb.lcs.web.internal.storage;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.io.File;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Ivica Cardic
 */
public class PatchS3StorageManagerImpl
	extends BasePatchStorageManager implements PatchStorageManager, Runnable {

	@Override
	public void afterPropertiesSet() {
		super.afterPropertiesSet();

		AWSCredentials awsCredentials = new BasicAWSCredentials(
			_accessKey, _secretKey);

		_amazonS3 = new AmazonS3Client(awsCredentials);

		_scheduledExecutorService.scheduleAtFixedRate(
			this, 0, 300, TimeUnit.SECONDS);
	}

	@Override
	public void destroy() {
		super.destroy();

		_scheduledExecutorService.shutdown();

		try {
			if (!_scheduledExecutorService.awaitTermination(
					5, TimeUnit.SECONDS)) {

				_scheduledExecutorService.shutdownNow();
			}
		}
		catch (final InterruptedException ie) {
			_scheduledExecutorService.shutdownNow();
		}
	}

	@Override
	public List<String> getPatchFileNames(Date sinceModifiedDate) {
		List<String> patchFileNames = new ArrayList<>();

		ObjectListing objectListing = _amazonS3.listObjects(
			_bucketName, _prefix);

		List<S3ObjectSummary> s3ObjectSummaries =
			objectListing.getObjectSummaries();

		boolean newPatchesAvailable = false;

		for (S3ObjectSummary s3ObjectSummary : s3ObjectSummaries) {
			Date lastModifiedDate = s3ObjectSummary.getLastModified();

			if (lastModifiedDate.before(sinceModifiedDate)) {
				continue;
			}

			String patchFileName = getPatchFileName(s3ObjectSummary);

			if (patchFileName == null) {
				continue;
			}

			patchFileNames.add(patchFileName);

			newPatchesAvailable = true;
		}

		if (newPatchesAvailable) {
			resetActivePatchFileNames();
		}

		return patchFileNames;
	}

	@Override
	public List<String> getPatchFileNames(int buildNumber) {
		List<String> patchFileNames = new ArrayList<>();

		for (String patchFileName : _activePatchFileNames) {
			if (!patchFileName.contains(String.valueOf(buildNumber))) {
				continue;
			}

			patchFileNames.add(patchFileName);
		}

		return patchFileNames;
	}

	@Override
	public Map<String, Long> getPatchFileNamesSizes() {
		Map<String, Long> patchFileNamesSizes = new HashMap<>();

		ObjectListing objectListing = _amazonS3.listObjects(
			_bucketName, _prefix);

		List<S3ObjectSummary> s3ObjectSummaries =
			objectListing.getObjectSummaries();

		for (S3ObjectSummary s3ObjectSummary : s3ObjectSummaries) {
			patchFileNamesSizes.put(
				getPatchFileName(s3ObjectSummary), s3ObjectSummary.getSize());
		}

		return patchFileNamesSizes;
	}

	@Override
	public void run() {
		resetActivePatchFileNames();
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
	public void writePatchFile(File patchFile) {
		PutObjectRequest putObjectRequest = new PutObjectRequest(
			_bucketName,
			_prefix.concat(StringPool.SLASH).concat(patchFile.getName()),
			patchFile);

		_amazonS3.putObject(putObjectRequest);
	}

	protected String getPatchFileName(S3ObjectSummary s3ObjectSummary) {
		String key = s3ObjectSummary.getKey();

		if (!isPatch(key)) {
			return null;
		}

		String dirName = _prefix.concat(StringPool.SLASH);

		String patchFileName = key.replace(dirName, StringPool.BLANK);

		return patchFileName;
	}

	protected void resetActivePatchFileNames() {
		if (_log.isDebugEnabled()) {
			_log.debug("Reset a list of active patch file names");
		}

		List<String> patchFileNames = new ArrayList<>();

		ObjectListing objectListing = _amazonS3.listObjects(
			_bucketName, _prefix);

		List<S3ObjectSummary> s3ObjectSummaries =
			objectListing.getObjectSummaries();

		for (S3ObjectSummary s3ObjectSummary : s3ObjectSummaries) {
			String patchFileName = getPatchFileName(s3ObjectSummary);

			if (patchFileName == null) {
				continue;
			}

			patchFileNames.add(patchFileName);
		}

		_activePatchFileNames = patchFileNames;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PatchS3StorageManagerImpl.class);

	private static final ScheduledExecutorService _scheduledExecutorService =
		Executors.newSingleThreadScheduledExecutor();

	private String _accessKey;
	private List<String> _activePatchFileNames;
	private AmazonS3 _amazonS3;
	private String _bucketName;
	private String _prefix;
	private String _secretKey;

}