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

package com.liferay.portlet.dynamicdatalists.util.test;

import com.liferay.portal.kernel.test.DependenciesTestUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.test.TestPropsValues;

/**
 * @author Marcellus Tavares
 * @author André de Oliveira
 */
public class DDLRecordTestUtil {

	public static String getBasePath() {
		return "com/liferay/portlet/dynamicdatalists/dependencies/";
	}

	public static ServiceContext getServiceContext(int workflowAction)
		throws Exception {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);
		serviceContext.setUserId(TestPropsValues.getUserId());
		serviceContext.setWorkflowAction(workflowAction);

		return serviceContext;
	}

	public static String readText(Class<?> testClass, String fileName)
		throws Exception {

		return DependenciesTestUtil.readText(
			testClass, getBasePath() + fileName);
	}

}