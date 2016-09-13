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

import com.liferay.osb.lcs.model.LCSInvitation;

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
 * The cache model class for representing LCSInvitation in entity cache.
 *
 * @author Igor Beslic
 * @see LCSInvitation
 * @generated
 */
@ProviderType
public class LCSInvitationCacheModel implements CacheModel<LCSInvitation>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LCSInvitationCacheModel)) {
			return false;
		}

		LCSInvitationCacheModel lcsInvitationCacheModel = (LCSInvitationCacheModel)obj;

		if (lcsInvitationId == lcsInvitationCacheModel.lcsInvitationId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, lcsInvitationId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{lcsInvitationId=");
		sb.append(lcsInvitationId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", lcsProjectId=");
		sb.append(lcsProjectId);
		sb.append(", emailAddress=");
		sb.append(emailAddress);
		sb.append(", lcsClusterEntryId=");
		sb.append(lcsClusterEntryId);
		sb.append(", role=");
		sb.append(role);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LCSInvitation toEntityModel() {
		LCSInvitationImpl lcsInvitationImpl = new LCSInvitationImpl();

		lcsInvitationImpl.setLcsInvitationId(lcsInvitationId);
		lcsInvitationImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			lcsInvitationImpl.setCreateDate(null);
		}
		else {
			lcsInvitationImpl.setCreateDate(new Date(createDate));
		}

		lcsInvitationImpl.setLcsProjectId(lcsProjectId);

		if (emailAddress == null) {
			lcsInvitationImpl.setEmailAddress(StringPool.BLANK);
		}
		else {
			lcsInvitationImpl.setEmailAddress(emailAddress);
		}

		lcsInvitationImpl.setLcsClusterEntryId(lcsClusterEntryId);
		lcsInvitationImpl.setRole(role);

		lcsInvitationImpl.resetOriginalValues();

		return lcsInvitationImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		lcsInvitationId = objectInput.readLong();

		userId = objectInput.readLong();
		createDate = objectInput.readLong();

		lcsProjectId = objectInput.readLong();
		emailAddress = objectInput.readUTF();

		lcsClusterEntryId = objectInput.readLong();

		role = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(lcsInvitationId);

		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);

		objectOutput.writeLong(lcsProjectId);

		if (emailAddress == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(emailAddress);
		}

		objectOutput.writeLong(lcsClusterEntryId);

		objectOutput.writeInt(role);
	}

	public long lcsInvitationId;
	public long userId;
	public long createDate;
	public long lcsProjectId;
	public String emailAddress;
	public long lcsClusterEntryId;
	public int role;
}