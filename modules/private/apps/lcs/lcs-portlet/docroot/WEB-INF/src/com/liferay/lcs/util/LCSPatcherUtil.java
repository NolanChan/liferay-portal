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

package com.liferay.lcs.util;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.patcher.PatcherUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.io.File;

import java.lang.reflect.Field;

/**
 * @author Matija Petanjek
 */
public class LCSPatcherUtil {

	public static String[] getFixedIssues() {
		_resetProperties();

		return PatcherUtil.getFixedIssues();
	}

	public static String[] getInstalledPatches() {
		_resetProperties();

		return PatcherUtil.getInstalledPatches();
	}

	public static File getPatchDirectory() {
		_resetProperties();

		return PatcherUtil.getPatchDirectory();
	}

	public static int getPatchingToolVersion() {
		_resetProperties();

		return PatcherUtil.getPatchingToolVersion();
	}

	public static boolean isConfigured() {
		return PatcherUtil.isConfigured();
	}

	private static int _getInstalledPatchVersion() {
		String[] installedPatches = PatcherUtil.getInstalledPatches();

		if (installedPatches.length == 0) {
			return 0;
		}

		String[] parts = installedPatches[0].split(StringPool.DASH);

		return GetterUtil.getInteger(parts[1]);
	}

	private static void _resetProperties() {
		if (_PATCHER_IMPL_FIX_PATCH_VERSION >= _getInstalledPatchVersion()) {
			return;
		}

		try {
			Object patcherUtil = PortalBeanLocatorUtil.locate(
				"com.liferay.portal.kernel.patcher.PatcherUtil");

			Class<?> clazz = patcherUtil.getClass();

			Field patcherField = clazz.getDeclaredField("_patcher");

			patcherField.setAccessible(true);

			Object patcherImpl = patcherField.get(patcherUtil);

			clazz = patcherImpl.getClass();

			Field propertiesField = clazz.getDeclaredField("_properties");

			propertiesField.setAccessible(true);

			propertiesField.set(patcherImpl, null);
		}
		catch (Exception e) {
			_log.error(e.getMessage(), e);
		}
	}

	private static final int _PATCHER_IMPL_FIX_PATCH_VERSION = 8;

	private static final Log _log = LogFactoryUtil.getLog(LCSPatcherUtil.class);

}