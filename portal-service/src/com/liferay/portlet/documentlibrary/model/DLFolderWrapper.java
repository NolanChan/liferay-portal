/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.portlet.documentlibrary.model;

import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link DLFolder}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       DLFolder
 * @generated
 */
public class DLFolderWrapper implements DLFolder, ModelWrapper<DLFolder> {
	public DLFolderWrapper(DLFolder dlFolder) {
		_dlFolder = dlFolder;
	}

	public Class<?> getModelClass() {
		return DLFolder.class;
	}

	public String getModelClassName() {
		return DLFolder.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("folderId", getFolderId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("repositoryId", getRepositoryId());
		attributes.put("mountPoint", getMountPoint());
		attributes.put("parentFolderId", getParentFolderId());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("lastPostDate", getLastPostDate());
		attributes.put("defaultFileEntryTypeId", getDefaultFileEntryTypeId());
		attributes.put("overrideFileEntryTypes", getOverrideFileEntryTypes());
		attributes.put("status", getStatus());
		attributes.put("statusByUserId", getStatusByUserId());
		attributes.put("statusByUserName", getStatusByUserName());
		attributes.put("statusDate", getStatusDate());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long folderId = (Long)attributes.get("folderId");

		if (folderId != null) {
			setFolderId(folderId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long repositoryId = (Long)attributes.get("repositoryId");

		if (repositoryId != null) {
			setRepositoryId(repositoryId);
		}

		Boolean mountPoint = (Boolean)attributes.get("mountPoint");

		if (mountPoint != null) {
			setMountPoint(mountPoint);
		}

		Long parentFolderId = (Long)attributes.get("parentFolderId");

		if (parentFolderId != null) {
			setParentFolderId(parentFolderId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Date lastPostDate = (Date)attributes.get("lastPostDate");

		if (lastPostDate != null) {
			setLastPostDate(lastPostDate);
		}

		Long defaultFileEntryTypeId = (Long)attributes.get(
				"defaultFileEntryTypeId");

		if (defaultFileEntryTypeId != null) {
			setDefaultFileEntryTypeId(defaultFileEntryTypeId);
		}

		Boolean overrideFileEntryTypes = (Boolean)attributes.get(
				"overrideFileEntryTypes");

		if (overrideFileEntryTypes != null) {
			setOverrideFileEntryTypes(overrideFileEntryTypes);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Long statusByUserId = (Long)attributes.get("statusByUserId");

		if (statusByUserId != null) {
			setStatusByUserId(statusByUserId);
		}

		String statusByUserName = (String)attributes.get("statusByUserName");

		if (statusByUserName != null) {
			setStatusByUserName(statusByUserName);
		}

		Date statusDate = (Date)attributes.get("statusDate");

		if (statusDate != null) {
			setStatusDate(statusDate);
		}
	}

	/**
	* Returns the primary key of this document library folder.
	*
	* @return the primary key of this document library folder
	*/
	public long getPrimaryKey() {
		return _dlFolder.getPrimaryKey();
	}

	/**
	* Sets the primary key of this document library folder.
	*
	* @param primaryKey the primary key of this document library folder
	*/
	public void setPrimaryKey(long primaryKey) {
		_dlFolder.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this document library folder.
	*
	* @return the uuid of this document library folder
	*/
	public java.lang.String getUuid() {
		return _dlFolder.getUuid();
	}

	/**
	* Sets the uuid of this document library folder.
	*
	* @param uuid the uuid of this document library folder
	*/
	public void setUuid(java.lang.String uuid) {
		_dlFolder.setUuid(uuid);
	}

	/**
	* Returns the folder ID of this document library folder.
	*
	* @return the folder ID of this document library folder
	*/
	public long getFolderId() {
		return _dlFolder.getFolderId();
	}

	/**
	* Sets the folder ID of this document library folder.
	*
	* @param folderId the folder ID of this document library folder
	*/
	public void setFolderId(long folderId) {
		_dlFolder.setFolderId(folderId);
	}

	/**
	* Returns the group ID of this document library folder.
	*
	* @return the group ID of this document library folder
	*/
	public long getGroupId() {
		return _dlFolder.getGroupId();
	}

	/**
	* Sets the group ID of this document library folder.
	*
	* @param groupId the group ID of this document library folder
	*/
	public void setGroupId(long groupId) {
		_dlFolder.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this document library folder.
	*
	* @return the company ID of this document library folder
	*/
	public long getCompanyId() {
		return _dlFolder.getCompanyId();
	}

	/**
	* Sets the company ID of this document library folder.
	*
	* @param companyId the company ID of this document library folder
	*/
	public void setCompanyId(long companyId) {
		_dlFolder.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this document library folder.
	*
	* @return the user ID of this document library folder
	*/
	public long getUserId() {
		return _dlFolder.getUserId();
	}

	/**
	* Sets the user ID of this document library folder.
	*
	* @param userId the user ID of this document library folder
	*/
	public void setUserId(long userId) {
		_dlFolder.setUserId(userId);
	}

	/**
	* Returns the user uuid of this document library folder.
	*
	* @return the user uuid of this document library folder
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dlFolder.getUserUuid();
	}

	/**
	* Sets the user uuid of this document library folder.
	*
	* @param userUuid the user uuid of this document library folder
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_dlFolder.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this document library folder.
	*
	* @return the user name of this document library folder
	*/
	public java.lang.String getUserName() {
		return _dlFolder.getUserName();
	}

	/**
	* Sets the user name of this document library folder.
	*
	* @param userName the user name of this document library folder
	*/
	public void setUserName(java.lang.String userName) {
		_dlFolder.setUserName(userName);
	}

	/**
	* Returns the create date of this document library folder.
	*
	* @return the create date of this document library folder
	*/
	public java.util.Date getCreateDate() {
		return _dlFolder.getCreateDate();
	}

	/**
	* Sets the create date of this document library folder.
	*
	* @param createDate the create date of this document library folder
	*/
	public void setCreateDate(java.util.Date createDate) {
		_dlFolder.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this document library folder.
	*
	* @return the modified date of this document library folder
	*/
	public java.util.Date getModifiedDate() {
		return _dlFolder.getModifiedDate();
	}

	/**
	* Sets the modified date of this document library folder.
	*
	* @param modifiedDate the modified date of this document library folder
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_dlFolder.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the repository ID of this document library folder.
	*
	* @return the repository ID of this document library folder
	*/
	public long getRepositoryId() {
		return _dlFolder.getRepositoryId();
	}

	/**
	* Sets the repository ID of this document library folder.
	*
	* @param repositoryId the repository ID of this document library folder
	*/
	public void setRepositoryId(long repositoryId) {
		_dlFolder.setRepositoryId(repositoryId);
	}

	/**
	* Returns the mount point of this document library folder.
	*
	* @return the mount point of this document library folder
	*/
	public boolean getMountPoint() {
		return _dlFolder.getMountPoint();
	}

	/**
	* Returns <code>true</code> if this document library folder is mount point.
	*
	* @return <code>true</code> if this document library folder is mount point; <code>false</code> otherwise
	*/
	public boolean isMountPoint() {
		return _dlFolder.isMountPoint();
	}

	/**
	* Sets whether this document library folder is mount point.
	*
	* @param mountPoint the mount point of this document library folder
	*/
	public void setMountPoint(boolean mountPoint) {
		_dlFolder.setMountPoint(mountPoint);
	}

	/**
	* Returns the parent folder ID of this document library folder.
	*
	* @return the parent folder ID of this document library folder
	*/
	public long getParentFolderId() {
		return _dlFolder.getParentFolderId();
	}

	/**
	* Sets the parent folder ID of this document library folder.
	*
	* @param parentFolderId the parent folder ID of this document library folder
	*/
	public void setParentFolderId(long parentFolderId) {
		_dlFolder.setParentFolderId(parentFolderId);
	}

	/**
	* Returns the name of this document library folder.
	*
	* @return the name of this document library folder
	*/
	public java.lang.String getName() {
		return _dlFolder.getName();
	}

	/**
	* Sets the name of this document library folder.
	*
	* @param name the name of this document library folder
	*/
	public void setName(java.lang.String name) {
		_dlFolder.setName(name);
	}

	/**
	* Returns the description of this document library folder.
	*
	* @return the description of this document library folder
	*/
	public java.lang.String getDescription() {
		return _dlFolder.getDescription();
	}

	/**
	* Sets the description of this document library folder.
	*
	* @param description the description of this document library folder
	*/
	public void setDescription(java.lang.String description) {
		_dlFolder.setDescription(description);
	}

	/**
	* Returns the last post date of this document library folder.
	*
	* @return the last post date of this document library folder
	*/
	public java.util.Date getLastPostDate() {
		return _dlFolder.getLastPostDate();
	}

	/**
	* Sets the last post date of this document library folder.
	*
	* @param lastPostDate the last post date of this document library folder
	*/
	public void setLastPostDate(java.util.Date lastPostDate) {
		_dlFolder.setLastPostDate(lastPostDate);
	}

	/**
	* Returns the default file entry type ID of this document library folder.
	*
	* @return the default file entry type ID of this document library folder
	*/
	public long getDefaultFileEntryTypeId() {
		return _dlFolder.getDefaultFileEntryTypeId();
	}

	/**
	* Sets the default file entry type ID of this document library folder.
	*
	* @param defaultFileEntryTypeId the default file entry type ID of this document library folder
	*/
	public void setDefaultFileEntryTypeId(long defaultFileEntryTypeId) {
		_dlFolder.setDefaultFileEntryTypeId(defaultFileEntryTypeId);
	}

	/**
	* Returns the override file entry types of this document library folder.
	*
	* @return the override file entry types of this document library folder
	*/
	public boolean getOverrideFileEntryTypes() {
		return _dlFolder.getOverrideFileEntryTypes();
	}

	/**
	* Returns <code>true</code> if this document library folder is override file entry types.
	*
	* @return <code>true</code> if this document library folder is override file entry types; <code>false</code> otherwise
	*/
	public boolean isOverrideFileEntryTypes() {
		return _dlFolder.isOverrideFileEntryTypes();
	}

	/**
	* Sets whether this document library folder is override file entry types.
	*
	* @param overrideFileEntryTypes the override file entry types of this document library folder
	*/
	public void setOverrideFileEntryTypes(boolean overrideFileEntryTypes) {
		_dlFolder.setOverrideFileEntryTypes(overrideFileEntryTypes);
	}

	/**
	* Returns the status of this document library folder.
	*
	* @return the status of this document library folder
	*/
	public int getStatus() {
		return _dlFolder.getStatus();
	}

	/**
	* Sets the status of this document library folder.
	*
	* @param status the status of this document library folder
	*/
	public void setStatus(int status) {
		_dlFolder.setStatus(status);
	}

	/**
	* Returns the status by user ID of this document library folder.
	*
	* @return the status by user ID of this document library folder
	*/
	public long getStatusByUserId() {
		return _dlFolder.getStatusByUserId();
	}

	/**
	* Sets the status by user ID of this document library folder.
	*
	* @param statusByUserId the status by user ID of this document library folder
	*/
	public void setStatusByUserId(long statusByUserId) {
		_dlFolder.setStatusByUserId(statusByUserId);
	}

	/**
	* Returns the status by user uuid of this document library folder.
	*
	* @return the status by user uuid of this document library folder
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getStatusByUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dlFolder.getStatusByUserUuid();
	}

	/**
	* Sets the status by user uuid of this document library folder.
	*
	* @param statusByUserUuid the status by user uuid of this document library folder
	*/
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_dlFolder.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Returns the status by user name of this document library folder.
	*
	* @return the status by user name of this document library folder
	*/
	public java.lang.String getStatusByUserName() {
		return _dlFolder.getStatusByUserName();
	}

	/**
	* Sets the status by user name of this document library folder.
	*
	* @param statusByUserName the status by user name of this document library folder
	*/
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_dlFolder.setStatusByUserName(statusByUserName);
	}

	/**
	* Returns the status date of this document library folder.
	*
	* @return the status date of this document library folder
	*/
	public java.util.Date getStatusDate() {
		return _dlFolder.getStatusDate();
	}

	/**
	* Sets the status date of this document library folder.
	*
	* @param statusDate the status date of this document library folder
	*/
	public void setStatusDate(java.util.Date statusDate) {
		_dlFolder.setStatusDate(statusDate);
	}

	/**
	* @deprecated Renamed to {@link #isApproved()}
	*/
	public boolean getApproved() {
		return _dlFolder.getApproved();
	}

	/**
	* Returns <code>true</code> if this document library folder is approved.
	*
	* @return <code>true</code> if this document library folder is approved; <code>false</code> otherwise
	*/
	public boolean isApproved() {
		return _dlFolder.isApproved();
	}

	/**
	* Returns <code>true</code> if this document library folder is denied.
	*
	* @return <code>true</code> if this document library folder is denied; <code>false</code> otherwise
	*/
	public boolean isDenied() {
		return _dlFolder.isDenied();
	}

	/**
	* Returns <code>true</code> if this document library folder is a draft.
	*
	* @return <code>true</code> if this document library folder is a draft; <code>false</code> otherwise
	*/
	public boolean isDraft() {
		return _dlFolder.isDraft();
	}

	/**
	* Returns <code>true</code> if this document library folder is expired.
	*
	* @return <code>true</code> if this document library folder is expired; <code>false</code> otherwise
	*/
	public boolean isExpired() {
		return _dlFolder.isExpired();
	}

	/**
	* Returns <code>true</code> if this document library folder is inactive.
	*
	* @return <code>true</code> if this document library folder is inactive; <code>false</code> otherwise
	*/
	public boolean isInactive() {
		return _dlFolder.isInactive();
	}

	/**
	* Returns <code>true</code> if this document library folder is incomplete.
	*
	* @return <code>true</code> if this document library folder is incomplete; <code>false</code> otherwise
	*/
	public boolean isIncomplete() {
		return _dlFolder.isIncomplete();
	}

	/**
	* Returns <code>true</code> if this document library folder is in the Recycle Bin.
	*
	* @return <code>true</code> if this document library folder is in the Recycle Bin; <code>false</code> otherwise
	*/
	public boolean isInTrash() {
		return _dlFolder.isInTrash();
	}

	/**
	* Returns <code>true</code> if this document library folder is pending.
	*
	* @return <code>true</code> if this document library folder is pending; <code>false</code> otherwise
	*/
	public boolean isPending() {
		return _dlFolder.isPending();
	}

	/**
	* Returns <code>true</code> if this document library folder is scheduled.
	*
	* @return <code>true</code> if this document library folder is scheduled; <code>false</code> otherwise
	*/
	public boolean isScheduled() {
		return _dlFolder.isScheduled();
	}

	/**
	* Returns the container model ID of this document library folder.
	*
	* @return the container model ID of this document library folder
	*/
	public long getContainerModelId() {
		return _dlFolder.getContainerModelId();
	}

	/**
	* Sets the container model ID of this document library folder.
	*
	* @param container model ID of this document library folder
	*/
	public void setContainerModelId(long containerModelId) {
		_dlFolder.setContainerModelId(containerModelId);
	}

	/**
	* Returns the container name of this document library folder.
	*
	* @return the container name of this document library folder
	*/
	public java.lang.String getContainerName() {
		return _dlFolder.getContainerName();
	}

	/**
	* Returns the parent container model ID of this document library folder.
	*
	* @return the parent container model ID of this document library folder
	*/
	public long getParentContainerModelId() {
		return _dlFolder.getParentContainerModelId();
	}

	/**
	* Sets the parent container model ID of this document library folder.
	*
	* @param parent container model ID of this document library folder
	*/
	public void setParentContainerModelId(long parentContainerModelId) {
		_dlFolder.setParentContainerModelId(parentContainerModelId);
	}

	public boolean isNew() {
		return _dlFolder.isNew();
	}

	public void setNew(boolean n) {
		_dlFolder.setNew(n);
	}

	public boolean isCachedModel() {
		return _dlFolder.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_dlFolder.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _dlFolder.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _dlFolder.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_dlFolder.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _dlFolder.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_dlFolder.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new DLFolderWrapper((DLFolder)_dlFolder.clone());
	}

	public int compareTo(
		com.liferay.portlet.documentlibrary.model.DLFolder dlFolder) {
		return _dlFolder.compareTo(dlFolder);
	}

	@Override
	public int hashCode() {
		return _dlFolder.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.portlet.documentlibrary.model.DLFolder> toCacheModel() {
		return _dlFolder.toCacheModel();
	}

	public com.liferay.portlet.documentlibrary.model.DLFolder toEscapedModel() {
		return new DLFolderWrapper(_dlFolder.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _dlFolder.toString();
	}

	public java.lang.String toXmlString() {
		return _dlFolder.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_dlFolder.persist();
	}

	public java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> getAncestors()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlFolder.getAncestors();
	}

	public com.liferay.portlet.documentlibrary.model.DLFolder getParentFolder()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlFolder.getParentFolder();
	}

	public java.lang.String getPath()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlFolder.getPath();
	}

	public java.lang.String[] getPathArray()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlFolder.getPathArray();
	}

	public com.liferay.portlet.documentlibrary.model.DLFolder getTrashFolder() {
		return _dlFolder.getTrashFolder();
	}

	public boolean hasInheritableLock() {
		return _dlFolder.hasInheritableLock();
	}

	public boolean hasLock() {
		return _dlFolder.hasLock();
	}

	public boolean isInTrashFolder() {
		return _dlFolder.isInTrashFolder();
	}

	public boolean isLocked() {
		return _dlFolder.isLocked();
	}

	public boolean isRoot() {
		return _dlFolder.isRoot();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public DLFolder getWrappedDLFolder() {
		return _dlFolder;
	}

	public DLFolder getWrappedModel() {
		return _dlFolder;
	}

	public void resetOriginalValues() {
		_dlFolder.resetOriginalValues();
	}

	private DLFolder _dlFolder;
}