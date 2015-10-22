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

package com.liferay.portlet.documentlibrary.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.trash.TrashHandler;
import com.liferay.portal.model.AttachedModel;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.PartitionedModel;
import com.liferay.portal.model.StagedGroupedModel;
import com.liferay.portal.model.TrashedModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.trash.model.TrashEntry;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the DLFileEntry service. Represents a row in the &quot;DLFileEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portlet.documentlibrary.model.impl.DLFileEntryModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portlet.documentlibrary.model.impl.DLFileEntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DLFileEntry
 * @see com.liferay.portlet.documentlibrary.model.impl.DLFileEntryImpl
 * @see com.liferay.portlet.documentlibrary.model.impl.DLFileEntryModelImpl
 * @generated
 */
@ProviderType
public interface DLFileEntryModel extends AttachedModel, BaseModel<DLFileEntry>,
	PartitionedModel, StagedGroupedModel, TrashedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a document library file entry model instance should use the {@link DLFileEntry} interface instead.
	 */

	/**
	 * Returns the primary key of this document library file entry.
	 *
	 * @return the primary key of this document library file entry
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this document library file entry.
	 *
	 * @param primaryKey the primary key of this document library file entry
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this document library file entry.
	 *
	 * @return the uuid of this document library file entry
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this document library file entry.
	 *
	 * @param uuid the uuid of this document library file entry
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the file entry ID of this document library file entry.
	 *
	 * @return the file entry ID of this document library file entry
	 */
	public long getFileEntryId();

	/**
	 * Sets the file entry ID of this document library file entry.
	 *
	 * @param fileEntryId the file entry ID of this document library file entry
	 */
	public void setFileEntryId(long fileEntryId);

	/**
	 * Returns the group ID of this document library file entry.
	 *
	 * @return the group ID of this document library file entry
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this document library file entry.
	 *
	 * @param groupId the group ID of this document library file entry
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this document library file entry.
	 *
	 * @return the company ID of this document library file entry
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this document library file entry.
	 *
	 * @param companyId the company ID of this document library file entry
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this document library file entry.
	 *
	 * @return the user ID of this document library file entry
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this document library file entry.
	 *
	 * @param userId the user ID of this document library file entry
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this document library file entry.
	 *
	 * @return the user uuid of this document library file entry
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this document library file entry.
	 *
	 * @param userUuid the user uuid of this document library file entry
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this document library file entry.
	 *
	 * @return the user name of this document library file entry
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this document library file entry.
	 *
	 * @param userName the user name of this document library file entry
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this document library file entry.
	 *
	 * @return the create date of this document library file entry
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this document library file entry.
	 *
	 * @param createDate the create date of this document library file entry
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this document library file entry.
	 *
	 * @return the modified date of this document library file entry
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this document library file entry.
	 *
	 * @param modifiedDate the modified date of this document library file entry
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the fully qualified class name of this document library file entry.
	 *
	 * @return the fully qualified class name of this document library file entry
	 */
	@Override
	public String getClassName();

	public void setClassName(String className);

	/**
	 * Returns the class name ID of this document library file entry.
	 *
	 * @return the class name ID of this document library file entry
	 */
	@Override
	public long getClassNameId();

	/**
	 * Sets the class name ID of this document library file entry.
	 *
	 * @param classNameId the class name ID of this document library file entry
	 */
	@Override
	public void setClassNameId(long classNameId);

	/**
	 * Returns the class p k of this document library file entry.
	 *
	 * @return the class p k of this document library file entry
	 */
	@Override
	public long getClassPK();

	/**
	 * Sets the class p k of this document library file entry.
	 *
	 * @param classPK the class p k of this document library file entry
	 */
	@Override
	public void setClassPK(long classPK);

	/**
	 * Returns the repository ID of this document library file entry.
	 *
	 * @return the repository ID of this document library file entry
	 */
	public long getRepositoryId();

	/**
	 * Sets the repository ID of this document library file entry.
	 *
	 * @param repositoryId the repository ID of this document library file entry
	 */
	public void setRepositoryId(long repositoryId);

	/**
	 * Returns the folder ID of this document library file entry.
	 *
	 * @return the folder ID of this document library file entry
	 */
	public long getFolderId();

	/**
	 * Sets the folder ID of this document library file entry.
	 *
	 * @param folderId the folder ID of this document library file entry
	 */
	public void setFolderId(long folderId);

	/**
	 * Returns the tree path of this document library file entry.
	 *
	 * @return the tree path of this document library file entry
	 */
	@AutoEscape
	public String getTreePath();

	/**
	 * Sets the tree path of this document library file entry.
	 *
	 * @param treePath the tree path of this document library file entry
	 */
	public void setTreePath(String treePath);

	/**
	 * Returns the name of this document library file entry.
	 *
	 * @return the name of this document library file entry
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this document library file entry.
	 *
	 * @param name the name of this document library file entry
	 */
	public void setName(String name);

	/**
	 * Returns the file name of this document library file entry.
	 *
	 * @return the file name of this document library file entry
	 */
	@AutoEscape
	public String getFileName();

	/**
	 * Sets the file name of this document library file entry.
	 *
	 * @param fileName the file name of this document library file entry
	 */
	public void setFileName(String fileName);

	/**
	 * Returns the extension of this document library file entry.
	 *
	 * @return the extension of this document library file entry
	 */
	@AutoEscape
	public String getExtension();

	/**
	 * Sets the extension of this document library file entry.
	 *
	 * @param extension the extension of this document library file entry
	 */
	public void setExtension(String extension);

	/**
	 * Returns the mime type of this document library file entry.
	 *
	 * @return the mime type of this document library file entry
	 */
	@AutoEscape
	public String getMimeType();

	/**
	 * Sets the mime type of this document library file entry.
	 *
	 * @param mimeType the mime type of this document library file entry
	 */
	public void setMimeType(String mimeType);

	/**
	 * Returns the title of this document library file entry.
	 *
	 * @return the title of this document library file entry
	 */
	@AutoEscape
	public String getTitle();

	/**
	 * Sets the title of this document library file entry.
	 *
	 * @param title the title of this document library file entry
	 */
	public void setTitle(String title);

	/**
	 * Returns the description of this document library file entry.
	 *
	 * @return the description of this document library file entry
	 */
	@AutoEscape
	public String getDescription();

	/**
	 * Sets the description of this document library file entry.
	 *
	 * @param description the description of this document library file entry
	 */
	public void setDescription(String description);

	/**
	 * Returns the extra settings of this document library file entry.
	 *
	 * @return the extra settings of this document library file entry
	 */
	@AutoEscape
	public String getExtraSettings();

	/**
	 * Sets the extra settings of this document library file entry.
	 *
	 * @param extraSettings the extra settings of this document library file entry
	 */
	public void setExtraSettings(String extraSettings);

	/**
	 * Returns the file entry type ID of this document library file entry.
	 *
	 * @return the file entry type ID of this document library file entry
	 */
	public long getFileEntryTypeId();

	/**
	 * Sets the file entry type ID of this document library file entry.
	 *
	 * @param fileEntryTypeId the file entry type ID of this document library file entry
	 */
	public void setFileEntryTypeId(long fileEntryTypeId);

	/**
	 * Returns the version of this document library file entry.
	 *
	 * @return the version of this document library file entry
	 */
	@AutoEscape
	public String getVersion();

	/**
	 * Sets the version of this document library file entry.
	 *
	 * @param version the version of this document library file entry
	 */
	public void setVersion(String version);

	/**
	 * Returns the size of this document library file entry.
	 *
	 * @return the size of this document library file entry
	 */
	public long getSize();

	/**
	 * Sets the size of this document library file entry.
	 *
	 * @param size the size of this document library file entry
	 */
	public void setSize(long size);

	/**
	 * Returns the read count of this document library file entry.
	 *
	 * @return the read count of this document library file entry
	 */
	public int getReadCount();

	/**
	 * Sets the read count of this document library file entry.
	 *
	 * @param readCount the read count of this document library file entry
	 */
	public void setReadCount(int readCount);

	/**
	 * Returns the small image ID of this document library file entry.
	 *
	 * @return the small image ID of this document library file entry
	 */
	public long getSmallImageId();

	/**
	 * Sets the small image ID of this document library file entry.
	 *
	 * @param smallImageId the small image ID of this document library file entry
	 */
	public void setSmallImageId(long smallImageId);

	/**
	 * Returns the large image ID of this document library file entry.
	 *
	 * @return the large image ID of this document library file entry
	 */
	public long getLargeImageId();

	/**
	 * Sets the large image ID of this document library file entry.
	 *
	 * @param largeImageId the large image ID of this document library file entry
	 */
	public void setLargeImageId(long largeImageId);

	/**
	 * Returns the custom1 image ID of this document library file entry.
	 *
	 * @return the custom1 image ID of this document library file entry
	 */
	public long getCustom1ImageId();

	/**
	 * Sets the custom1 image ID of this document library file entry.
	 *
	 * @param custom1ImageId the custom1 image ID of this document library file entry
	 */
	public void setCustom1ImageId(long custom1ImageId);

	/**
	 * Returns the custom2 image ID of this document library file entry.
	 *
	 * @return the custom2 image ID of this document library file entry
	 */
	public long getCustom2ImageId();

	/**
	 * Sets the custom2 image ID of this document library file entry.
	 *
	 * @param custom2ImageId the custom2 image ID of this document library file entry
	 */
	public void setCustom2ImageId(long custom2ImageId);

	/**
	 * Returns the manual check in required of this document library file entry.
	 *
	 * @return the manual check in required of this document library file entry
	 */
	public boolean getManualCheckInRequired();

	/**
	 * Returns <code>true</code> if this document library file entry is manual check in required.
	 *
	 * @return <code>true</code> if this document library file entry is manual check in required; <code>false</code> otherwise
	 */
	public boolean isManualCheckInRequired();

	/**
	 * Sets whether this document library file entry is manual check in required.
	 *
	 * @param manualCheckInRequired the manual check in required of this document library file entry
	 */
	public void setManualCheckInRequired(boolean manualCheckInRequired);

	/**
	 * Returns the last publish date of this document library file entry.
	 *
	 * @return the last publish date of this document library file entry
	 */
	@Override
	public Date getLastPublishDate();

	/**
	 * Sets the last publish date of this document library file entry.
	 *
	 * @param lastPublishDate the last publish date of this document library file entry
	 */
	@Override
	public void setLastPublishDate(Date lastPublishDate);

	/**
	 * Returns the status of this document library file entry.
	 *
	 * @return the status of this document library file entry
	 */
	@Override
	public int getStatus();

	/**
	 * Returns the trash entry created when this document library file entry was moved to the Recycle Bin. The trash entry may belong to one of the ancestors of this document library file entry.
	 *
	 * @return the trash entry created when this document library file entry was moved to the Recycle Bin
	 */
	@Override
	public TrashEntry getTrashEntry() throws PortalException;

	/**
	 * Returns the class primary key of the trash entry for this document library file entry.
	 *
	 * @return the class primary key of the trash entry for this document library file entry
	 */
	@Override
	public long getTrashEntryClassPK();

	/**
	 * Returns the trash handler for this document library file entry.
	 *
	 * @return the trash handler for this document library file entry
	 */
	@Override
	public TrashHandler getTrashHandler();

	/**
	 * Returns <code>true</code> if this document library file entry is in the Recycle Bin.
	 *
	 * @return <code>true</code> if this document library file entry is in the Recycle Bin; <code>false</code> otherwise
	 */
	@Override
	public boolean isInTrash();

	/**
	 * Returns <code>true</code> if the parent of this document library file entry is in the Recycle Bin.
	 *
	 * @return <code>true</code> if the parent of this document library file entry is in the Recycle Bin; <code>false</code> otherwise
	 */
	@Override
	public boolean isInTrashContainer();

	@Override
	public boolean isInTrashExplicitly();

	@Override
	public boolean isInTrashImplicitly();

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
	public int compareTo(
		com.liferay.portlet.documentlibrary.model.DLFileEntry dlFileEntry);

	@Override
	public int hashCode();

	@Override
	public CacheModel<com.liferay.portlet.documentlibrary.model.DLFileEntry> toCacheModel();

	@Override
	public com.liferay.portlet.documentlibrary.model.DLFileEntry toEscapedModel();

	@Override
	public com.liferay.portlet.documentlibrary.model.DLFileEntry toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}