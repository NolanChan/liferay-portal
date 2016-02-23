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

package com.liferay.reports.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.service.InvokableService;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the remote service utility for Source. This utility wraps
 * {@link com.liferay.reports.service.impl.SourceServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see SourceService
 * @see com.liferay.reports.service.base.SourceServiceBaseImpl
 * @see com.liferay.reports.service.impl.SourceServiceImpl
 * @generated
 */
@ProviderType
public class SourceServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.reports.service.impl.SourceServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.reports.model.Source addSource(long groupId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.lang.String driverClassName, java.lang.String driverUrl,
		java.lang.String driverUserName, java.lang.String driverPassword,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addSource(groupId, nameMap, driverClassName, driverUrl,
			driverUserName, driverPassword, serviceContext);
	}

	public static com.liferay.reports.model.Source deleteSource(long sourceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteSource(sourceId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.reports.model.Source getSource(long sourceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getSource(sourceId);
	}

	public static java.util.List<com.liferay.reports.model.Source> getSources(
		long groupId, java.lang.String name, java.lang.String driverUrl,
		boolean andSearch, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getSources(groupId, name, driverUrl, andSearch, start, end,
			orderByComparator);
	}

	public static int getSourcesCount(long groupId, java.lang.String name,
		java.lang.String driverUrl, boolean andSearch) {
		return getService().getSourcesCount(groupId, name, driverUrl, andSearch);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static com.liferay.reports.model.Source updateSource(long sourceId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.lang.String driverClassName, java.lang.String driverUrl,
		java.lang.String driverUserName, java.lang.String driverPassword,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateSource(sourceId, nameMap, driverClassName, driverUrl,
			driverUserName, driverPassword, serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static SourceService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					SourceService.class.getName());

			if (invokableService instanceof SourceService) {
				_service = (SourceService)invokableService;
			}
			else {
				_service = new SourceServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(SourceServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static SourceService _service;
}