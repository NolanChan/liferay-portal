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

import com.liferay.osb.lcs.model.LCSNotificationAuditEntry;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing LCSNotificationAuditEntry in entity cache.
 *
 * @author Igor Beslic
 * @see LCSNotificationAuditEntry
 * @generated
 */
@ProviderType
public class LCSNotificationAuditEntryCacheModel implements CacheModel<LCSNotificationAuditEntry>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LCSNotificationAuditEntryCacheModel)) {
			return false;
		}

		LCSNotificationAuditEntryCacheModel lcsNotificationAuditEntryCacheModel = (LCSNotificationAuditEntryCacheModel)obj;

		if (lcsNotificationAuditEntryId == lcsNotificationAuditEntryCacheModel.lcsNotificationAuditEntryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, lcsNotificationAuditEntryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{lcsNotificationAuditEntryId=");
		sb.append(lcsNotificationAuditEntryId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", lcsClusterNodeId=");
		sb.append(lcsClusterNodeId);
		sb.append(", type=");
		sb.append(type);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LCSNotificationAuditEntry toEntityModel() {
		LCSNotificationAuditEntryImpl lcsNotificationAuditEntryImpl = new LCSNotificationAuditEntryImpl();

		lcsNotificationAuditEntryImpl.setLcsNotificationAuditEntryId(lcsNotificationAuditEntryId);
		lcsNotificationAuditEntryImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			lcsNotificationAuditEntryImpl.setCreateDate(null);
		}
		else {
			lcsNotificationAuditEntryImpl.setCreateDate(new Date(createDate));
		}

		lcsNotificationAuditEntryImpl.setLcsClusterNodeId(lcsClusterNodeId);
		lcsNotificationAuditEntryImpl.setType(type);

		lcsNotificationAuditEntryImpl.resetOriginalValues();

		return lcsNotificationAuditEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		lcsNotificationAuditEntryId = objectInput.readLong();

		userId = objectInput.readLong();
		createDate = objectInput.readLong();

		lcsClusterNodeId = objectInput.readLong();

		type = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(lcsNotificationAuditEntryId);

		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);

		objectOutput.writeLong(lcsClusterNodeId);

		objectOutput.writeInt(type);
	}

	public long lcsNotificationAuditEntryId;
	public long userId;
	public long createDate;
	public long lcsClusterNodeId;
	public int type;
}