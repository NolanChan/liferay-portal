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

package com.liferay.journal.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.trash.TrashHandler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ContainerModel;
import com.liferay.portal.model.ShardedModel;
import com.liferay.portal.model.StagedGroupedModel;
import com.liferay.portal.model.TrashedModel;
import com.liferay.portal.model.WorkflowedModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.trash.model.TrashEntry;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the JournalFolder service. Represents a row in the &quot;JournalFolder&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.journal.model.impl.JournalFolderModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.journal.model.impl.JournalFolderImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JournalFolder
 * @see com.liferay.journal.model.impl.JournalFolderImpl
 * @see com.liferay.journal.model.impl.JournalFolderModelImpl
 * @generated
 */
@ProviderType
public interface JournalFolderModel extends BaseModel<JournalFolder>,
	ContainerModel, ShardedModel, StagedGroupedModel, TrashedModel,
	WorkflowedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a journal folder model instance should use the {@link JournalFolder} interface instead.
	 */

	/**
	 * Returns the primary key of this journal folder.
	 *
	 * @return the primary key of this journal folder
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this journal folder.
	 *
	 * @param primaryKey the primary key of this journal folder
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this journal folder.
	 *
	 * @return the uuid of this journal folder
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this journal folder.
	 *
	 * @param uuid the uuid of this journal folder
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the folder ID of this journal folder.
	 *
	 * @return the folder ID of this journal folder
	 */
	public long getFolderId();

	/**
	 * Sets the folder ID of this journal folder.
	 *
	 * @param folderId the folder ID of this journal folder
	 */
	public void setFolderId(long folderId);

	/**
	 * Returns the group ID of this journal folder.
	 *
	 * @return the group ID of this journal folder
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this journal folder.
	 *
	 * @param groupId the group ID of this journal folder
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this journal folder.
	 *
	 * @return the company ID of this journal folder
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this journal folder.
	 *
	 * @param companyId the company ID of this journal folder
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this journal folder.
	 *
	 * @return the user ID of this journal folder
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this journal folder.
	 *
	 * @param userId the user ID of this journal folder
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this journal folder.
	 *
	 * @return the user uuid of this journal folder
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this journal folder.
	 *
	 * @param userUuid the user uuid of this journal folder
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this journal folder.
	 *
	 * @return the user name of this journal folder
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this journal folder.
	 *
	 * @param userName the user name of this journal folder
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this journal folder.
	 *
	 * @return the create date of this journal folder
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this journal folder.
	 *
	 * @param createDate the create date of this journal folder
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this journal folder.
	 *
	 * @return the modified date of this journal folder
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this journal folder.
	 *
	 * @param modifiedDate the modified date of this journal folder
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the parent folder ID of this journal folder.
	 *
	 * @return the parent folder ID of this journal folder
	 */
	public long getParentFolderId();

	/**
	 * Sets the parent folder ID of this journal folder.
	 *
	 * @param parentFolderId the parent folder ID of this journal folder
	 */
	public void setParentFolderId(long parentFolderId);

	/**
	 * Returns the tree path of this journal folder.
	 *
	 * @return the tree path of this journal folder
	 */
	@AutoEscape
	public String getTreePath();

	/**
	 * Sets the tree path of this journal folder.
	 *
	 * @param treePath the tree path of this journal folder
	 */
	public void setTreePath(String treePath);

	/**
	 * Returns the name of this journal folder.
	 *
	 * @return the name of this journal folder
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this journal folder.
	 *
	 * @param name the name of this journal folder
	 */
	public void setName(String name);

	/**
	 * Returns the description of this journal folder.
	 *
	 * @return the description of this journal folder
	 */
	@AutoEscape
	public String getDescription();

	/**
	 * Sets the description of this journal folder.
	 *
	 * @param description the description of this journal folder
	 */
	public void setDescription(String description);

	/**
	 * Returns the restriction type of this journal folder.
	 *
	 * @return the restriction type of this journal folder
	 */
	public int getRestrictionType();

	/**
	 * Sets the restriction type of this journal folder.
	 *
	 * @param restrictionType the restriction type of this journal folder
	 */
	public void setRestrictionType(int restrictionType);

	/**
	 * Returns the last publish date of this journal folder.
	 *
	 * @return the last publish date of this journal folder
	 */
	@Override
	public Date getLastPublishDate();

	/**
	 * Sets the last publish date of this journal folder.
	 *
	 * @param lastPublishDate the last publish date of this journal folder
	 */
	@Override
	public void setLastPublishDate(Date lastPublishDate);

	/**
	 * Returns the status of this journal folder.
	 *
	 * @return the status of this journal folder
	 */
	@Override
	public int getStatus();

	/**
	 * Sets the status of this journal folder.
	 *
	 * @param status the status of this journal folder
	 */
	@Override
	public void setStatus(int status);

	/**
	 * Returns the status by user ID of this journal folder.
	 *
	 * @return the status by user ID of this journal folder
	 */
	@Override
	public long getStatusByUserId();

	/**
	 * Sets the status by user ID of this journal folder.
	 *
	 * @param statusByUserId the status by user ID of this journal folder
	 */
	@Override
	public void setStatusByUserId(long statusByUserId);

	/**
	 * Returns the status by user uuid of this journal folder.
	 *
	 * @return the status by user uuid of this journal folder
	 */
	@Override
	public String getStatusByUserUuid();

	/**
	 * Sets the status by user uuid of this journal folder.
	 *
	 * @param statusByUserUuid the status by user uuid of this journal folder
	 */
	@Override
	public void setStatusByUserUuid(String statusByUserUuid);

	/**
	 * Returns the status by user name of this journal folder.
	 *
	 * @return the status by user name of this journal folder
	 */
	@AutoEscape
	@Override
	public String getStatusByUserName();

	/**
	 * Sets the status by user name of this journal folder.
	 *
	 * @param statusByUserName the status by user name of this journal folder
	 */
	@Override
	public void setStatusByUserName(String statusByUserName);

	/**
	 * Returns the status date of this journal folder.
	 *
	 * @return the status date of this journal folder
	 */
	@Override
	public Date getStatusDate();

	/**
	 * Sets the status date of this journal folder.
	 *
	 * @param statusDate the status date of this journal folder
	 */
	@Override
	public void setStatusDate(Date statusDate);

	/**
	 * Returns the trash entry created when this journal folder was moved to the Recycle Bin. The trash entry may belong to one of the ancestors of this journal folder.
	 *
	 * @return the trash entry created when this journal folder was moved to the Recycle Bin
	 */
	@Override
	public TrashEntry getTrashEntry() throws PortalException;

	/**
	 * Returns the class primary key of the trash entry for this journal folder.
	 *
	 * @return the class primary key of the trash entry for this journal folder
	 */
	@Override
	public long getTrashEntryClassPK();

	/**
	 * Returns the trash handler for this journal folder.
	 *
	 * @return the trash handler for this journal folder
	 */
	@Override
	public TrashHandler getTrashHandler();

	/**
	 * Returns <code>true</code> if this journal folder is in the Recycle Bin.
	 *
	 * @return <code>true</code> if this journal folder is in the Recycle Bin; <code>false</code> otherwise
	 */
	@Override
	public boolean isInTrash();

	/**
	 * Returns <code>true</code> if the parent of this journal folder is in the Recycle Bin.
	 *
	 * @return <code>true</code> if the parent of this journal folder is in the Recycle Bin; <code>false</code> otherwise
	 */
	@Override
	public boolean isInTrashContainer();

	@Override
	public boolean isInTrashExplicitly();

	@Override
	public boolean isInTrashImplicitly();

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #isApproved()}
	 */
	@Deprecated
	@Override
	public boolean getApproved();

	/**
	 * Returns <code>true</code> if this journal folder is approved.
	 *
	 * @return <code>true</code> if this journal folder is approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isApproved();

	/**
	 * Returns <code>true</code> if this journal folder is denied.
	 *
	 * @return <code>true</code> if this journal folder is denied; <code>false</code> otherwise
	 */
	@Override
	public boolean isDenied();

	/**
	 * Returns <code>true</code> if this journal folder is a draft.
	 *
	 * @return <code>true</code> if this journal folder is a draft; <code>false</code> otherwise
	 */
	@Override
	public boolean isDraft();

	/**
	 * Returns <code>true</code> if this journal folder is expired.
	 *
	 * @return <code>true</code> if this journal folder is expired; <code>false</code> otherwise
	 */
	@Override
	public boolean isExpired();

	/**
	 * Returns <code>true</code> if this journal folder is inactive.
	 *
	 * @return <code>true</code> if this journal folder is inactive; <code>false</code> otherwise
	 */
	@Override
	public boolean isInactive();

	/**
	 * Returns <code>true</code> if this journal folder is incomplete.
	 *
	 * @return <code>true</code> if this journal folder is incomplete; <code>false</code> otherwise
	 */
	@Override
	public boolean isIncomplete();

	/**
	 * Returns <code>true</code> if this journal folder is pending.
	 *
	 * @return <code>true</code> if this journal folder is pending; <code>false</code> otherwise
	 */
	@Override
	public boolean isPending();

	/**
	 * Returns <code>true</code> if this journal folder is scheduled.
	 *
	 * @return <code>true</code> if this journal folder is scheduled; <code>false</code> otherwise
	 */
	@Override
	public boolean isScheduled();

	/**
	 * Returns the container model ID of this journal folder.
	 *
	 * @return the container model ID of this journal folder
	 */
	@Override
	public long getContainerModelId();

	/**
	 * Sets the container model ID of this journal folder.
	 *
	 * @param containerModelId the container model ID of this journal folder
	 */
	@Override
	public void setContainerModelId(long containerModelId);

	/**
	 * Returns the container name of this journal folder.
	 *
	 * @return the container name of this journal folder
	 */
	@Override
	public String getContainerModelName();

	/**
	 * Returns the parent container model ID of this journal folder.
	 *
	 * @return the parent container model ID of this journal folder
	 */
	@Override
	public long getParentContainerModelId();

	/**
	 * Sets the parent container model ID of this journal folder.
	 *
	 * @param parentContainerModelId the parent container model ID of this journal folder
	 */
	@Override
	public void setParentContainerModelId(long parentContainerModelId);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(com.liferay.journal.model.JournalFolder journalFolder);

	@Override
	public int hashCode();

	@Override
	public CacheModel<com.liferay.journal.model.JournalFolder> toCacheModel();

	@Override
	public com.liferay.journal.model.JournalFolder toEscapedModel();

	@Override
	public com.liferay.journal.model.JournalFolder toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}