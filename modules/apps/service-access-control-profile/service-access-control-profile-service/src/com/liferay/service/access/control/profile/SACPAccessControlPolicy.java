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

package com.liferay.service.access.control.profile;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.access.control.AccessControlPolicy;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.security.access.control.BaseAccessControlPolicy;
import com.liferay.portal.kernel.security.access.control.profile.ServiceAccessControlProfileThreadLocal;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.auth.CompanyThreadLocal;
import com.liferay.service.access.control.profile.model.SACPEntry;
import com.liferay.service.access.control.profile.service.SACPEntryLocalService;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Mika Koivisto
 */
@Component(service = AccessControlPolicy.class)
public class SACPAccessControlPolicy extends BaseAccessControlPolicy {

	@Override
	public void onServiceRemoteAccess(
			Method method, Object[] arguments,
			AccessControlled accessControlled)
		throws SecurityException {

		List<String> serviceAccessControlProfileNames =
			ServiceAccessControlProfileThreadLocal.
				getActiveServiceAccessControlProfileNames();

		if (serviceAccessControlProfileNames == null) {
			return;
		}

		long companyId = CompanyThreadLocal.getCompanyId();

		Set<String> allowedServices = new HashSet<>();

		for (String name : serviceAccessControlProfileNames) {
			try {
				SACPEntry sacpEntry = _sacpEntryLocalService.getSACPEntry(
					companyId, name);

				allowedServices.addAll(sacpEntry.getAllowedServicesList());
			}
			catch (PortalException pe) {
				throw new SecurityException(pe);
			}
		}

		Class<?> clazz = method.getDeclaringClass();

		String className = clazz.getName();

		if (allowedServices.contains(className)) {
			return;
		}

		String methodName = method.getName();

		String classNameAndMethodName = className.concat(
			StringPool.POUND).concat(methodName);

		if (allowedServices.contains(classNameAndMethodName)) {
			return;
		}

		for (String allowedService : allowedServices) {
			if (matches(className, methodName, allowedService)) {
				return;
			}
		}

		throw new SecurityException(
			"Access denied to " + classNameAndMethodName);
	}

	protected boolean matches(
		String className, String methodName, String allowedService) {

		String allowedClass = null;
		String allowedMethod = null;

		int index = allowedService.indexOf(CharPool.POUND);

		if (index > -1) {
			allowedClass = allowedService.substring(0, index);
			allowedMethod = allowedService.substring(index + 1);
		}
		else {
			allowedClass = allowedService;
		}

		boolean wildcardMatchClass = false;

		if (Validator.isNotNull(allowedClass) &&
			allowedClass.endsWith(StringPool.STAR)) {

			wildcardMatchClass = true;
			allowedClass = allowedClass.substring(0, allowedClass.length() - 1);
		}

		boolean wildcardMatchMethod = false;

		if (Validator.isNotNull(allowedMethod) &&
			allowedMethod.endsWith(StringPool.STAR)) {

			wildcardMatchMethod = true;
			allowedMethod = allowedMethod.substring(
				0, allowedMethod.length() - 1);
		}

		if (Validator.isNotNull(allowedClass) &&
			Validator.isNotNull(allowedMethod)) {

			if (wildcardMatchClass && !className.startsWith(allowedClass)) {
				return false;
			}
			else if (!wildcardMatchClass && !className.equals(allowedClass)) {
				return false;
			}

			if (wildcardMatchMethod && !methodName.startsWith(allowedMethod)) {
				return false;
			}
			else if (!wildcardMatchMethod &&
					 !methodName.equals(allowedMethod)) {

				return false;
			}

			return true;
		}
		else if (Validator.isNotNull(allowedClass)) {
			if (wildcardMatchClass && !className.startsWith(allowedClass)) {
				return false;
			}
			else if (!wildcardMatchClass && !className.equals(allowedClass)) {
				return false;
			}

			return true;
		}
		else if (Validator.isNotNull(allowedMethod)) {
			if (wildcardMatchMethod && !methodName.startsWith(allowedMethod)) {
				return false;
			}
			else if (!wildcardMatchMethod &&
					 !methodName.equals(allowedMethod)) {

				return false;
			}

			return true;
		}
		else if (wildcardMatchClass && Validator.isNull(allowedClass)) {
			return true;
		}

		return false;
	}

	@Reference(unbind = "-")
	protected void setSACPEntryLocalService(
		SACPEntryLocalService sacpEntryLocalService) {

		_sacpEntryLocalService = sacpEntryLocalService;
	}

	private SACPEntryLocalService _sacpEntryLocalService;

}