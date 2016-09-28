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

import com.liferay.osb.lcs.configuration.OSBLCSConfiguration;
import com.liferay.osb.lcs.storage.StorageManager;
import com.liferay.osb.lcs.util.ApplicationProfile;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class FileSystemStorageManagerImpl implements StorageManager {

	@Override
	public Map<String, Long> getFileNamesSizes() {
		Map<String, Long> patchFileNamesSizes = new HashMap<>();

		File patchesDir = new File(_path);

		String[] patchFileNames = patchesDir.list();

		if ((patchFileNames == null) &&
			(_getApplicationProfile() ==
				ApplicationProfile.LOCAL_DEVELOPMENT)) {

			return Collections.emptyMap();
		}

		for (String patchFileName : patchFileNames) {
			File patchFile = new File(_path + File.separator + patchFileName);

			patchFileNamesSizes.put(patchFileName, patchFile.length());
		}

		return patchFileNamesSizes;
	}

	@Override
	public Map<String, Long> getFileNamesSizes(Date sinceModifiedDate) {
		Map<String, Long> patchFileNamesSizes = new HashMap<>();

		File patchesDir = new File(_path);

		if (!patchesDir.exists()) {
			if (_log.isWarnEnabled()) {
				_log.warn("Directory" + _path + "does not exist");
			}

			return Collections.emptyMap();
		}

		FileFilter fileFilter = new SinceModifiedDateFileFilter(
			sinceModifiedDate);

		File[] patchFiles = patchesDir.listFiles(fileFilter);

		for (File patchFile : patchFiles) {
			patchFileNamesSizes.put(patchFile.getName(), patchFile.length());
		}

		return patchFileNamesSizes;
	}

	@Override
	public Map<String, Long> getFileNamesSizes(String nameFragment) {
		Map<String, Long> patchFileNamesSizes = new HashMap<>();

		File patchesDir = new File(_path);

		FilenameFilter filenameFilter = new NameFragmentFilenameFilter(
			nameFragment);

		File[] patchFiles = patchesDir.listFiles(filenameFilter);

		for (File patchFile : patchFiles) {
			patchFileNamesSizes.put(patchFile.getName(), patchFile.length());
		}

		return patchFileNamesSizes;
	}

	@Override
	public void setBucketName(String bucketName) {
	}

	@Override
	public void setCredentials(String accessKey, String secretKey) {
	}

	@Override
	public void setPath(String path) {
		_path = path;
	}

	@Override
	public void setPrefix(String prefix) {
	}

	@Override
	public void writeFile(File patchFile) {
		File destinationPatchFile = new File(
			_path.concat(File.separator).concat(patchFile.getName()));

		try {
			FileUtil.copyFile(patchFile, destinationPatchFile);
		}
		catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}
	}

	@Override
	public void writeFile(String key, String message, String throwable) {
		StringBundler sb = new StringBundler(_path);

		sb.append(File.separator);
		sb.append(key);
		sb.append("_");
		sb.append(String.valueOf(System.currentTimeMillis()));
		sb.append(".log");

		File file = new File(sb.toString());

		try {
			FileUtil.write(file, "[" + key + "]-", true, true);
			FileUtil.write(file, message + "-", true, true);
			FileUtil.write(file, throwable, true, true);
		}
		catch (Exception e) {
			_log.error(e.getMessage());

			throw new RuntimeException(e);
		}
	}

	private ApplicationProfile _getApplicationProfile() {
		if (_applicationProfile != null) {
			return _applicationProfile;
		}

		OSBLCSConfiguration osbLCSConfiguration =
			ConfigurableUtil.createConfigurable(
				OSBLCSConfiguration.class, Collections.emptyMap());

		_applicationProfile = osbLCSConfiguration.applicationProfile();

		return _applicationProfile;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		FileSystemStorageManagerImpl.class);

	private ApplicationProfile _applicationProfile;
	private String _path;

	private class NameFragmentFilenameFilter implements FilenameFilter {

		public NameFragmentFilenameFilter(String nameFragment) {
			_nameFragment = nameFragment;
		}

		@Override
		public boolean accept(File dir, String name) {
			if (name.contains(_nameFragment)) {
				return true;
			}

			return false;
		}

		private final String _nameFragment;

	}

	private class SinceModifiedDateFileFilter implements FileFilter {

		public SinceModifiedDateFileFilter(Date sinceModifiedDate) {
			_sinceModifiedDate = sinceModifiedDate;
		}

		@Override
		public boolean accept(File pathname) {
			long lastModified = pathname.lastModified();

			if (lastModified >= _sinceModifiedDate.getTime()) {
				return true;
			}

			return false;
		}

		private final Date _sinceModifiedDate;

	}

}