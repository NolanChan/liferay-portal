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

	private static void _resetProperties() {
		try {
			Object patcherUtil = PortalBeanLocatorUtil.locate(
				"com.liferay.portal.kernel.patcher.PatcherUtil");

			Class<?> clazz = patcherUtil.getClass();

			Field patcher = clazz.getDeclaredField("_patcher");

			patcher.setAccessible(true);

			Object patcherImpl = patcher.get(patcherUtil);

			clazz = patcherImpl.getClass();

			Field properties = clazz.getDeclaredField("_properties");

			properties.setAccessible(true);
			properties.set(patcherImpl, null);
		}
		catch (Exception e) {
			_log.error(e.getMessage(), e);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(LCSPatcherUtil.class);

}