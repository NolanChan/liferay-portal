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

package com.liferay.portal.kernel.security.permission;

import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.portal.kernel.exception.NoSuchResourceActionException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.PasswordPolicy;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroup;

import java.io.InputStream;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 * @author Daeyoung Song
 */
public interface ResourceActions {

	/**
	 * @deprecated As of 6.2.0, replaced by {@link #getActionNamePrefix}
	 */
	@Deprecated
	public static final String ACTION_NAME_PREFIX = "action.";

	/**
	 * @deprecated As of 6.2.0, replaced by {@link #getModelResourceNamePrefix}
	 */
	@Deprecated
	public static final String MODEL_RESOURCE_NAME_PREFIX = "model.resource.";

	/**
	 * @deprecated As of 6.2.0, replaced by {@link
	 *             #getOrganizationModelResources}
	 */
	@Deprecated
	public static final String[] ORGANIZATION_MODEL_RESOURCES = {
		Organization.class.getName(), PasswordPolicy.class.getName(),
		User.class.getName()
	};

	/**
	 * @deprecated As of 6.2.0, replaced by {@link #getPortalModelResources}
	 */
	@Deprecated
	public static final String[] PORTAL_MODEL_RESOURCES = {
		ExpandoColumn.class.getName(), Organization.class.getName(),
		PasswordPolicy.class.getName(), Role.class.getName(),
		User.class.getName(), UserGroup.class.getName()
	};

	public void checkAction(String name, String actionId)
		throws NoSuchResourceActionException;

	public String getAction(HttpServletRequest request, String action);

	public String getAction(Locale locale, String action);

	public String getActionNamePrefix();

	/**
	 * @deprecated As of 7.0.0
	 */
	@Deprecated
	public List<String> getActionsNames(
		HttpServletRequest request, List<String> actions);

	/**
	 * @deprecated As of 7.0.0
	 */
	@Deprecated
	public List<String> getActionsNames(
		HttpServletRequest request, String name, long actionIds);

	public String getCompositeModelNameSeparator();

	public List<String> getModelNames();

	public List<String> getModelPortletResources(String name);

	public String getModelResource(HttpServletRequest request, String name);

	public String getModelResource(Locale locale, String name);

	public List<String> getModelResourceActions(String name);

	public List<String> getModelResourceGroupDefaultActions(String name);

	public List<String> getModelResourceGuestDefaultActions(String name);

	public List<String> getModelResourceGuestUnsupportedActions(String name);

	public String getModelResourceNamePrefix();

	public List<String> getModelResourceOwnerDefaultActions(String name);

	public Double getModelResourceWeight(String name);

	public String[] getOrganizationModelResources();

	public String[] getPortalModelResources();

	public String getPortletBaseResource(String portletName);

	public List<String> getPortletModelResources(String portletName);

	public List<String> getPortletNames();

	public List<String> getPortletResourceActions(Portlet portlet);

	public List<String> getPortletResourceActions(String name);

	public List<String> getPortletResourceGroupDefaultActions(String name);

	public List<String> getPortletResourceGuestDefaultActions(String name);

	public List<String> getPortletResourceGuestUnsupportedActions(String name);

	public List<String> getPortletResourceLayoutManagerActions(String name);

	public String getPortletRootModelResource(String portletName);

	public List<String> getResourceActions(String name);

	public List<String> getResourceActions(
		String portletResource, String modelResource);

	public List<String> getResourceGroupDefaultActions(String name);

	public List<String> getResourceGuestUnsupportedActions(
		String portletResource, String modelResource);

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getRoles(long, Group,
	 *             String, int[])}
	 */
	@Deprecated
	public List<Role> getRoles(
		long companyId, Group group, String modelResource);

	public List<Role> getRoles(
		long companyId, Group group, String modelResource, int[] roleTypes);

	public boolean hasModelResourceActions(String name);

	public boolean isOrganizationModelResource(String modelResource);

	public boolean isPortalModelResource(String modelResource);

	public void read(
			String servletContextName, ClassLoader classLoader, String source)
		throws Exception;

	/**
	 * @deprecated As of 7.0.0
	 */
	@Deprecated
	public void read(String servletContextName, InputStream inputStream)
		throws Exception;

}