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

package com.liferay.portal.security.permission;

import com.liferay.portal.kernel.util.AutoResetThreadLocal;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public class PermissionThreadLocal {

	public static PermissionChecker getPermissionChecker() {
		return _permissionChecker.get();
	}

	public static boolean isAddResource() {
		return _addResource.get();
	}

	public static boolean isFlushResourceBlockEnabled() {
		return _flushResourceBlockEnabled.get();
	}

	public static boolean isFlushResourcePermissionEnabled() {
		return _flushResourcePermissionEnabled.get();
	}

	public static void setAddResource(boolean addResource) {
		_addResource.set(addResource);
	}

	public static void setFlushResourceBlockEnabled(boolean enabled) {
		_flushResourceBlockEnabled.set(enabled);
	}

	public static void setFlushResourcePermissionEnabled(boolean enabled) {
		_flushResourcePermissionEnabled.set(enabled);
	}

	public static void setPermissionChecker(
		PermissionChecker permissionChecker) {

		_permissionChecker.set(permissionChecker);
	}

	private static final ThreadLocal<Boolean> _addResource =
		new AutoResetThreadLocal<>(
			PermissionThreadLocal.class + "._addResource", true);
	private static final ThreadLocal<Boolean> _flushResourceBlockEnabled =
		new AutoResetThreadLocal<>(
			PermissionThreadLocal.class + "._flushResourceBlockEnabled", true);
	private static final ThreadLocal<Boolean>
		_flushResourcePermissionEnabled = new AutoResetThreadLocal<>(
			PermissionThreadLocal.class +
				"._flushResourcePermissionEnabled",
			true);

	private static final ThreadLocal<PermissionChecker> _permissionChecker =
		new AutoResetThreadLocal<PermissionChecker>(
			PermissionThreadLocal.class + "._permissionChecker") {

				@Override
				protected PermissionChecker copy(
					PermissionChecker permissionChecker) {

					return permissionChecker;
				}

			};

}