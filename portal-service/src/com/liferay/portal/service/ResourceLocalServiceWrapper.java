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

package com.liferay.portal.service;

import aQute.bnd.annotation.ProviderType;

/**
 * Provides a wrapper for {@link ResourceLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ResourceLocalService
 * @generated
 */
@ProviderType
public class ResourceLocalServiceWrapper implements ResourceLocalService,
	ServiceWrapper<ResourceLocalService> {
	public ResourceLocalServiceWrapper(
		ResourceLocalService resourceLocalService) {
		_resourceLocalService = resourceLocalService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _resourceLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_resourceLocalService.setBeanIdentifier(beanIdentifier);
	}

	/**
	* Adds resources for the model, always creating a resource at the
	* individual scope and only creating resources at the group, group
	* template, and company scope if such resources don't already exist.
	*
	* <ol>
	* <li>
	* If the service context specifies that default group or default guest
	* permissions are to be added, then only default permissions are added. See
	* {@link com.liferay.portal.service.ServiceContext#setAddGroupPermissions(
	* boolean)} and {@link
	* com.liferay.portal.service.ServiceContext#setAddGuestPermissions(
	* boolean)}.
	* </li>
	* <li>
	* Else ...
	* <ol>
	* <li>
	* If the service context specifies to derive default permissions, then
	* default group and guest permissions are derived from the model and
	* added. See {@link
	* com.liferay.portal.service.ServiceContext#setDeriveDefaultPermissions(
	* boolean)}.
	* </li>
	* <li>
	* Lastly group and guest permissions from the service
	* context are applied. See {@link
	* com.liferay.portal.service.ServiceContext#setGroupPermissions(String[])}
	* and {@link
	* com.liferay.portal.service.ServiceContext#setGuestPermissions(String[])}.
	* </li>
	* </ol>
	*
	* </li>
	* </ol>
	*
	* @param auditedModel the model to associate with the resources
	* @param serviceContext the service context to apply. Can set whether to
	add the model's default group and guest permissions, set whether
	to derive default group and guest permissions from the model, set
	group permissions to apply, and set guest permissions to apply.
	* @throws PortalException if no portal actions could be found associated
	with the model or if a portal exception occurred
	*/
	@Override
	public void addModelResources(
		com.liferay.portal.model.AuditedModel auditedModel,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		_resourceLocalService.addModelResources(auditedModel, serviceContext);
	}

	/**
	* Adds resources for the model with the name and primary key, always
	* creating a resource at the individual scope and only creating resources
	* at the group, group template, and company scope if such resources don't
	* already exist.
	*
	* @param companyId the primary key of the portal instance
	* @param groupId the primary key of the group
	* @param userId the primary key of the user adding the resources
	* @param name a name for the resource, typically the model's class name
	* @param primKey the primary key of the model instance, optionally
	<code>0</code> if no instance exists
	* @param groupPermissions the group permissions to be applied
	* @param guestPermissions the guest permissions to be applied
	* @throws PortalException if no portal actions could be found associated
	with the model or if a portal exception occurred
	*/
	@Override
	public void addModelResources(long companyId, long groupId, long userId,
		java.lang.String name, long primKey,
		java.lang.String[] groupPermissions, java.lang.String[] guestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException {
		_resourceLocalService.addModelResources(companyId, groupId, userId,
			name, primKey, groupPermissions, guestPermissions);
	}

	/**
	* Adds resources for the model with the name and primary key string, always
	* creating a resource at the individual scope and only creating resources
	* at the group, group template, and company scope if such resources don't
	* already exist.
	*
	* @param companyId the primary key of the portal instance
	* @param groupId the primary key of the group
	* @param userId the primary key of the user adding the resources
	* @param name a name for the resource, typically the model's class name
	* @param primKey the primary key string of the model instance, optionally
	an empty string if no instance exists
	* @param groupPermissions the group permissions to be applied
	* @param guestPermissions the guest permissions to be applied
	* @throws PortalException if no portal actions could be found associated
	with the model or if a portal exception occurred
	*/
	@Override
	public void addModelResources(long companyId, long groupId, long userId,
		java.lang.String name, java.lang.String primKey,
		java.lang.String[] groupPermissions, java.lang.String[] guestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException {
		_resourceLocalService.addModelResources(companyId, groupId, userId,
			name, primKey, groupPermissions, guestPermissions);
	}

	/**
	* Adds resources for the entity with the name and primary key, always
	* creating a resource at the individual scope and only creating resources
	* at the group, group template, and company scope if such resources don't
	* already exist.
	*
	* @param companyId the primary key of the portal instance
	* @param groupId the primary key of the group
	* @param userId the primary key of the user adding the resources
	* @param name a name for the resource, which should be a portlet ID if the
	resource is a portlet or the resource's class name otherwise
	* @param primKey the primary key of the resource instance, optionally
	<code>0</code> if no instance exists
	* @param portletActions whether to associate portlet actions with the
	resource
	* @param addGroupPermissions whether to add group permissions
	* @param addGuestPermissions whether to add guest permissions
	* @throws PortalException if no portal actions could be found associated
	with the resource or if a portal exception occurred
	*/
	@Override
	public void addResources(long companyId, long groupId, long userId,
		java.lang.String name, long primKey, boolean portletActions,
		boolean addGroupPermissions, boolean addGuestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException {
		_resourceLocalService.addResources(companyId, groupId, userId, name,
			primKey, portletActions, addGroupPermissions, addGuestPermissions);
	}

	/**
	* Adds resources for the entity with the name and primary key string,
	* always creating a resource at the individual scope and only creating
	* resources at the group, group template, and company scope if such
	* resources don't already exist.
	*
	* @param companyId the primary key of the portal instance
	* @param groupId the primary key of the group
	* @param userId the primary key of the user adding the resources
	* @param name a name for the resource, which should be a portlet ID if the
	resource is a portlet or the resource's class name otherwise
	* @param primKey the primary key string of the resource instance,
	optionally an empty string if no instance exists
	* @param portletActions whether to associate portlet actions with the
	resource
	* @param addGroupPermissions whether to add group permissions
	* @param addGuestPermissions whether to add guest permissions
	* @throws PortalException if no portal actions could be found associated
	with the resource or if a portal exception occurred
	*/
	@Override
	public void addResources(long companyId, long groupId, long userId,
		java.lang.String name, java.lang.String primKey,
		boolean portletActions, boolean addGroupPermissions,
		boolean addGuestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException {
		_resourceLocalService.addResources(companyId, groupId, userId, name,
			primKey, portletActions, addGroupPermissions, addGuestPermissions);
	}

	/**
	* Adds resources for the entity with the name. Use this method if the user
	* is unknown or irrelevant and there is no current entity instance.
	*
	* @param companyId the primary key of the portal instance
	* @param groupId the primary key of the group
	* @param name a name for the resource, which should be a portlet ID if the
	resource is a portlet or the resource's class name otherwise
	* @param portletActions whether to associate portlet actions with the
	resource
	* @throws PortalException if no portal actions could be found associated
	with the resource or if a portal exception occurred
	*/
	@Override
	public void addResources(long companyId, long groupId,
		java.lang.String name, boolean portletActions)
		throws com.liferay.portal.kernel.exception.PortalException {
		_resourceLocalService.addResources(companyId, groupId, name,
			portletActions);
	}

	/**
	* Deletes the resource associated with the model at the scope.
	*
	* @param auditedModel the model associated with the resource
	* @param scope the scope of the resource. For more information see {@link
	com.liferay.portal.model.ResourceConstants}.
	* @throws PortalException if a portal exception occurred
	*/
	@Override
	public void deleteResource(
		com.liferay.portal.model.AuditedModel auditedModel, int scope)
		throws com.liferay.portal.kernel.exception.PortalException {
		_resourceLocalService.deleteResource(auditedModel, scope);
	}

	/**
	* Deletes the resource matching the primary key at the scope.
	*
	* @param companyId the primary key of the portal instance
	* @param name the resource's name, which should be a portlet ID if the
	resource is a portlet or the resource's class name otherwise
	* @param scope the scope of the resource. For more information see {@link
	com.liferay.portal.model.ResourceConstants}.
	* @param primKey the primary key of the resource instance
	* @throws PortalException if a portal exception occurred
	*/
	@Override
	public void deleteResource(long companyId, java.lang.String name,
		int scope, long primKey)
		throws com.liferay.portal.kernel.exception.PortalException {
		_resourceLocalService.deleteResource(companyId, name, scope, primKey);
	}

	/**
	* Deletes the resource matching the primary key at the scope.
	*
	* @param companyId the primary key of the portal instance
	* @param name the resource's name, which should be a portlet ID if the
	resource is a portlet or the resource's class name otherwise
	* @param scope the scope of the resource. For more information see {@link
	com.liferay.portal.model.ResourceConstants}.
	* @param primKey the primary key string of the resource instance
	* @throws PortalException if a portal exception occurred
	*/
	@Override
	public void deleteResource(long companyId, java.lang.String name,
		int scope, java.lang.String primKey)
		throws com.liferay.portal.kernel.exception.PortalException {
		_resourceLocalService.deleteResource(companyId, name, scope, primKey);
	}

	/**
	* Returns a new resource with the name and primary key at the scope.
	*
	* @param companyId the primary key of the portal instance
	* @param name a name for the resource, which should be a portlet ID if the
	resource is a portlet or the resource's class name otherwise
	* @param scope the scope of the resource. For more information see {@link
	com.liferay.portal.model.ResourceConstants}.
	* @param primKey the primary key string of the resource
	* @return the new resource
	*/
	@Override
	public com.liferay.portal.model.Resource getResource(long companyId,
		java.lang.String name, int scope, java.lang.String primKey) {
		return _resourceLocalService.getResource(companyId, name, scope, primKey);
	}

	/**
	* Returns <code>true</code> if the roles have permission to perform the
	* action on the resources.
	*
	* @param userId the primary key of the user performing the permission
	check
	* @param resourceId the primary key of the resource, typically the scope
	group ID representing the scope in which the permission check is
	being performed
	* @param resources the resources for which permissions are to be checked
	* @param actionId the primary key of the action to be performed on the
	resources
	* @param roleIds the primary keys of the roles
	* @return <code>true</code> if the roles have permission to perform the
	action on the resources;<code>false</code> otherwise
	* @throws PortalException if any one of the roles with the primary keys
	could not be found or if a resource action with the action ID
	could not be found
	*/
	@Override
	public boolean hasUserPermissions(long userId, long resourceId,
		java.util.List<com.liferay.portal.model.Resource> resources,
		java.lang.String actionId, long[] roleIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _resourceLocalService.hasUserPermissions(userId, resourceId,
			resources, actionId, roleIds);
	}

	/**
	* Updates the resources for the model, replacing their group and guest
	* permissions with new ones from the service context.
	*
	* @param auditedModel the model associated with the resources
	* @param serviceContext the service context to be applied. Can set group
	and guest permissions.
	* @throws PortalException if a portal exception occurred
	*/
	@Override
	public void updateModelResources(
		com.liferay.portal.model.AuditedModel auditedModel,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		_resourceLocalService.updateModelResources(auditedModel, serviceContext);
	}

	/**
	* Updates resources matching the group, name, and primary key at the
	* individual scope, setting new group and guest permissions.
	*
	* @param companyId the primary key of the portal instance
	* @param groupId the primary key of the group
	* @param name the resource's name, which should be a portlet ID if the
	resource is a portlet or the resource's class name otherwise
	* @param primKey the primary key of the resource instance
	* @param groupPermissions the group permissions to be applied
	* @param guestPermissions the guest permissions to be applied
	* @throws PortalException if a portal exception occurred
	*/
	@Override
	public void updateResources(long companyId, long groupId,
		java.lang.String name, long primKey,
		java.lang.String[] groupPermissions, java.lang.String[] guestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException {
		_resourceLocalService.updateResources(companyId, groupId, name,
			primKey, groupPermissions, guestPermissions);
	}

	/**
	* Updates resources matching the group, name, and primary key string at the
	* individual scope, setting new group and guest permissions.
	*
	* @param companyId the primary key of the portal instance
	* @param groupId the primary key of the group
	* @param name the resource's name, which should be a portlet ID if the
	resource is a portlet or the resource's class name otherwise
	* @param primKey the primary key string of the resource instance
	* @param groupPermissions the group permissions to be applied
	* @param guestPermissions the guest permissions to be applied
	* @throws PortalException if a portal exception occurred
	*/
	@Override
	public void updateResources(long companyId, long groupId,
		java.lang.String name, java.lang.String primKey,
		java.lang.String[] groupPermissions, java.lang.String[] guestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException {
		_resourceLocalService.updateResources(companyId, groupId, name,
			primKey, groupPermissions, guestPermissions);
	}

	/**
	* Updates resources matching the name, primary key string and scope,
	* replacing the primary key of their resource permissions with the new
	* primary key.
	*
	* @param companyId the primary key of the portal instance
	* @param name the resource's name, which should be a portlet ID if the
	resource is a portlet or the resource's class name otherwise
	* @param scope the scope of the resource. For more information see {@link
	com.liferay.portal.model.ResourceConstants}.
	* @param primKey the primary key string of the resource instance
	* @param newPrimKey the new primary key string of the resource
	*/
	@Override
	public void updateResources(long companyId, java.lang.String name,
		int scope, java.lang.String primKey, java.lang.String newPrimKey) {
		_resourceLocalService.updateResources(companyId, name, scope, primKey,
			newPrimKey);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	@Deprecated
	public ResourceLocalService getWrappedResourceLocalService() {
		return _resourceLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	@Deprecated
	public void setWrappedResourceLocalService(
		ResourceLocalService resourceLocalService) {
		_resourceLocalService = resourceLocalService;
	}

	@Override
	public ResourceLocalService getWrappedService() {
		return _resourceLocalService;
	}

	@Override
	public void setWrappedService(ResourceLocalService resourceLocalService) {
		_resourceLocalService = resourceLocalService;
	}

	private ResourceLocalService _resourceLocalService;
}