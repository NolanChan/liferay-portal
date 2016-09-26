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

package com.liferay.osb.ldn.documentation.project.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.ldn.documentation.project.model.DocumentationProject;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing DocumentationProject in entity cache.
 *
 * @author Ryan Park
 * @see DocumentationProject
 * @generated
 */
@ProviderType
public class DocumentationProjectCacheModel implements CacheModel<DocumentationProject>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DocumentationProjectCacheModel)) {
			return false;
		}

		DocumentationProjectCacheModel documentationProjectCacheModel = (DocumentationProjectCacheModel)obj;

		if (documentationProjectId == documentationProjectCacheModel.documentationProjectId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, documentationProjectId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", documentationProjectId=");
		sb.append(documentationProjectId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", iconFileName=");
		sb.append(iconFileName);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DocumentationProject toEntityModel() {
		DocumentationProjectImpl documentationProjectImpl = new DocumentationProjectImpl();

		if (uuid == null) {
			documentationProjectImpl.setUuid(StringPool.BLANK);
		}
		else {
			documentationProjectImpl.setUuid(uuid);
		}

		documentationProjectImpl.setDocumentationProjectId(documentationProjectId);
		documentationProjectImpl.setGroupId(groupId);
		documentationProjectImpl.setCompanyId(companyId);
		documentationProjectImpl.setUserId(userId);

		if (userName == null) {
			documentationProjectImpl.setUserName(StringPool.BLANK);
		}
		else {
			documentationProjectImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			documentationProjectImpl.setCreateDate(null);
		}
		else {
			documentationProjectImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			documentationProjectImpl.setModifiedDate(null);
		}
		else {
			documentationProjectImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			documentationProjectImpl.setName(StringPool.BLANK);
		}
		else {
			documentationProjectImpl.setName(name);
		}

		if (description == null) {
			documentationProjectImpl.setDescription(StringPool.BLANK);
		}
		else {
			documentationProjectImpl.setDescription(description);
		}

		if (iconFileName == null) {
			documentationProjectImpl.setIconFileName(StringPool.BLANK);
		}
		else {
			documentationProjectImpl.setIconFileName(iconFileName);
		}

		documentationProjectImpl.setStatus(status);

		documentationProjectImpl.resetOriginalValues();

		return documentationProjectImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		documentationProjectId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
		iconFileName = objectInput.readUTF();

		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(documentationProjectId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (iconFileName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(iconFileName);
		}

		objectOutput.writeInt(status);
	}

	public String uuid;
	public long documentationProjectId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String description;
	public String iconFileName;
	public int status;
}