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

package com.liferay.osb.lcs.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.lcs.model.LCSClusterNode;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing LCSClusterNode in entity cache.
 *
 * @author Igor Beslic
 * @see LCSClusterNode
 * @generated
 */
@ProviderType
public class LCSClusterNodeCacheModel implements CacheModel<LCSClusterNode>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LCSClusterNodeCacheModel)) {
			return false;
		}

		LCSClusterNodeCacheModel lcsClusterNodeCacheModel = (LCSClusterNodeCacheModel)obj;

		if (lcsClusterNodeId == lcsClusterNodeCacheModel.lcsClusterNodeId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, lcsClusterNodeId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{lcsClusterNodeId=");
		sb.append(lcsClusterNodeId);
		sb.append(", lcsClusterEntryId=");
		sb.append(lcsClusterEntryId);
		sb.append(", installationId=");
		sb.append(installationId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", buildNumber=");
		sb.append(buildNumber);
		sb.append(", key=");
		sb.append(key);
		sb.append(", location=");
		sb.append(location);
		sb.append(", processorCoresTotal=");
		sb.append(processorCoresTotal);
		sb.append(", archived=");
		sb.append(archived);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LCSClusterNode toEntityModel() {
		LCSClusterNodeImpl lcsClusterNodeImpl = new LCSClusterNodeImpl();

		lcsClusterNodeImpl.setLcsClusterNodeId(lcsClusterNodeId);
		lcsClusterNodeImpl.setLcsClusterEntryId(lcsClusterEntryId);
		lcsClusterNodeImpl.setInstallationId(installationId);

		if (name == null) {
			lcsClusterNodeImpl.setName(StringPool.BLANK);
		}
		else {
			lcsClusterNodeImpl.setName(name);
		}

		if (description == null) {
			lcsClusterNodeImpl.setDescription(StringPool.BLANK);
		}
		else {
			lcsClusterNodeImpl.setDescription(description);
		}

		lcsClusterNodeImpl.setBuildNumber(buildNumber);

		if (key == null) {
			lcsClusterNodeImpl.setKey(StringPool.BLANK);
		}
		else {
			lcsClusterNodeImpl.setKey(key);
		}

		if (location == null) {
			lcsClusterNodeImpl.setLocation(StringPool.BLANK);
		}
		else {
			lcsClusterNodeImpl.setLocation(location);
		}

		lcsClusterNodeImpl.setProcessorCoresTotal(processorCoresTotal);
		lcsClusterNodeImpl.setArchived(archived);
		lcsClusterNodeImpl.setStatus(status);

		lcsClusterNodeImpl.resetOriginalValues();

		return lcsClusterNodeImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		lcsClusterNodeId = objectInput.readLong();

		lcsClusterEntryId = objectInput.readLong();

		installationId = objectInput.readLong();
		name = objectInput.readUTF();
		description = objectInput.readUTF();

		buildNumber = objectInput.readInt();
		key = objectInput.readUTF();
		location = objectInput.readUTF();

		processorCoresTotal = objectInput.readInt();

		archived = objectInput.readBoolean();

		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(lcsClusterNodeId);

		objectOutput.writeLong(lcsClusterEntryId);

		objectOutput.writeLong(installationId);

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

		objectOutput.writeInt(buildNumber);

		if (key == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(key);
		}

		if (location == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(location);
		}

		objectOutput.writeInt(processorCoresTotal);

		objectOutput.writeBoolean(archived);

		objectOutput.writeInt(status);
	}

	public long lcsClusterNodeId;
	public long lcsClusterEntryId;
	public long installationId;
	public String name;
	public String description;
	public int buildNumber;
	public String key;
	public String location;
	public int processorCoresTotal;
	public boolean archived;
	public int status;
}