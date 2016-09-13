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

import com.liferay.osb.lcs.util.ApplicationProfile;
import com.liferay.osb.lcs.util.PortletPropsValues;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.ListUtil;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ivica Cardic
 */
public class PatchFileSystemStorageManagerImpl
	extends BasePatchStorageManager implements PatchStorageManager {

	@Override
	public List<String> getPatchFileNames(Date sinceModifiedDate) {
		List<String> patchFileNames = new ArrayList<>();

		File patchesDir = new File(_path);

		if (!patchesDir.exists()) {
			if (_log.isWarnEnabled()) {
				_log.warn("Directory" + _path + "does not exist");
			}

			return Collections.emptyList();
		}

		FileFilter fileFilter = new SinceModifiedDateFileFilter(
			sinceModifiedDate);

		File[] patchFiles = patchesDir.listFiles(fileFilter);

		for (File patchFile : patchFiles) {
			patchFileNames.add(patchFile.getName());
		}

		return patchFileNames;
	}

	@Override
	public List<String> getPatchFileNames(final int buildNumber) {
		File patchesDir = new File(_path);

		FilenameFilter filenameFilter = new BuildNumberFilenameFilter(
			buildNumber);

		String[] patchFileNames = patchesDir.list(filenameFilter);

		return ListUtil.toList(patchFileNames);
	}

	@Override
	public Map<String, Long> getPatchFileNamesSizes() {
		Map<String, Long> patchFileNamesSizes = new HashMap<>();

		File patchesDir = new File(_path);

		String[] patchFileNames = patchesDir.list();

		if ((patchFileNames == null) &&
			(PortletPropsValues.APPLICATION_PROFILE ==
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
	public void setPath(String path) {
		_path = path;
	}

	@Override
	public void writePatchFile(File patchFile) {
		File destinationPatchFile = new File(
			_path.concat(File.separator).concat(patchFile.getName()));

		try {
			FileUtil.copyFile(patchFile, destinationPatchFile);
		}
		catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PatchFileSystemStorageManagerImpl.class);

	private String _path;

	private class BuildNumberFilenameFilter implements FilenameFilter {

		public BuildNumberFilenameFilter(int buildNumber) {
			_buildNumber = buildNumber;
		}

		@Override
		public boolean accept(File dir, String name) {
			if (isPatch(name) && name.contains(String.valueOf(_buildNumber))) {
				return true;
			}

			return false;
		}

		private final int _buildNumber;

	}

	private class SinceModifiedDateFileFilter implements FileFilter {

		public SinceModifiedDateFileFilter(Date sinceModifiedDate) {
			_sinceModifiedDate = sinceModifiedDate;
		}

		@Override
		public boolean accept(File pathname) {
			long lastModified = pathname.lastModified();

			if (isPatch(pathname.getName()) &&
				(lastModified >= _sinceModifiedDate.getTime())) {

				return true;
			}

			return false;
		}

		private final Date _sinceModifiedDate;

	}

}