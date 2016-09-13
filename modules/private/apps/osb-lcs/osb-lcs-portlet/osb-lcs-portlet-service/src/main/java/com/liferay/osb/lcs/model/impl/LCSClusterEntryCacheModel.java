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

import com.liferay.osb.lcs.model.LCSClusterEntry;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing LCSClusterEntry in entity cache.
 *
 * @author Igor Beslic
 * @see LCSClusterEntry
 * @generated
 */
@ProviderType
public class LCSClusterEntryCacheModel implements CacheModel<LCSClusterEntry>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LCSClusterEntryCacheModel)) {
			return false;
		}

		LCSClusterEntryCacheModel lcsClusterEntryCacheModel = (LCSClusterEntryCacheModel)obj;

		if (lcsClusterEntryId == lcsClusterEntryCacheModel.lcsClusterEntryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, lcsClusterEntryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{lcsClusterEntryId=");
		sb.append(lcsClusterEntryId);
		sb.append(", lcsProjectId=");
		sb.append(lcsProjectId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", elastic=");
		sb.append(elastic);
		sb.append(", highPageLoadTime=");
		sb.append(highPageLoadTime);
		sb.append(", location=");
		sb.append(location);
		sb.append(", mediumPageLoadTime=");
		sb.append(mediumPageLoadTime);
		sb.append(", subscriptionType=");
		sb.append(subscriptionType);
		sb.append(", type=");
		sb.append(type);
		sb.append(", archived=");
		sb.append(archived);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LCSClusterEntry toEntityModel() {
		LCSClusterEntryImpl lcsClusterEntryImpl = new LCSClusterEntryImpl();

		lcsClusterEntryImpl.setLcsClusterEntryId(lcsClusterEntryId);
		lcsClusterEntryImpl.setLcsProjectId(lcsProjectId);

		if (name == null) {
			lcsClusterEntryImpl.setName(StringPool.BLANK);
		}
		else {
			lcsClusterEntryImpl.setName(name);
		}

		if (description == null) {
			lcsClusterEntryImpl.setDescription(StringPool.BLANK);
		}
		else {
			lcsClusterEntryImpl.setDescription(description);
		}

		lcsClusterEntryImpl.setElastic(elastic);
		lcsClusterEntryImpl.setHighPageLoadTime(highPageLoadTime);

		if (location == null) {
			lcsClusterEntryImpl.setLocation(StringPool.BLANK);
		}
		else {
			lcsClusterEntryImpl.setLocation(location);
		}

		lcsClusterEntryImpl.setMediumPageLoadTime(mediumPageLoadTime);

		if (subscriptionType == null) {
			lcsClusterEntryImpl.setSubscriptionType(StringPool.BLANK);
		}
		else {
			lcsClusterEntryImpl.setSubscriptionType(subscriptionType);
		}

		lcsClusterEntryImpl.setType(type);
		lcsClusterEntryImpl.setArchived(archived);

		lcsClusterEntryImpl.resetOriginalValues();

		return lcsClusterEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		lcsClusterEntryId = objectInput.readLong();

		lcsProjectId = objectInput.readLong();
		name = objectInput.readUTF();
		description = objectInput.readUTF();

		elastic = objectInput.readBoolean();

		highPageLoadTime = objectInput.readInt();
		location = objectInput.readUTF();

		mediumPageLoadTime = objectInput.readInt();
		subscriptionType = objectInput.readUTF();

		type = objectInput.readInt();

		archived = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(lcsClusterEntryId);

		objectOutput.writeLong(lcsProjectId);

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

		objectOutput.writeBoolean(elastic);

		objectOutput.writeInt(highPageLoadTime);

		if (location == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(location);
		}

		objectOutput.writeInt(mediumPageLoadTime);

		if (subscriptionType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(subscriptionType);
		}

		objectOutput.writeInt(type);

		objectOutput.writeBoolean(archived);
	}

	public long lcsClusterEntryId;
	public long lcsProjectId;
	public String name;
	public String description;
	public boolean elastic;
	public int highPageLoadTime;
	public String location;
	public int mediumPageLoadTime;
	public String subscriptionType;
	public int type;
	public boolean archived;
}