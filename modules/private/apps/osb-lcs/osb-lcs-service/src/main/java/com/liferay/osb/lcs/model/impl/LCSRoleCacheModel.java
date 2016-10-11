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

import com.liferay.osb.lcs.model.LCSRole;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing LCSRole in entity cache.
 *
 * @author Igor Beslic
 * @see LCSRole
 * @generated
 */
@ProviderType
public class LCSRoleCacheModel implements CacheModel<LCSRole>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LCSRoleCacheModel)) {
			return false;
		}

		LCSRoleCacheModel lcsRoleCacheModel = (LCSRoleCacheModel)obj;

		if (lcsRoleId == lcsRoleCacheModel.lcsRoleId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, lcsRoleId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{lcsRoleId=");
		sb.append(lcsRoleId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", lcsProjectId=");
		sb.append(lcsProjectId);
		sb.append(", lcsClusterEntryId=");
		sb.append(lcsClusterEntryId);
		sb.append(", role=");
		sb.append(role);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LCSRole toEntityModel() {
		LCSRoleImpl lcsRoleImpl = new LCSRoleImpl();

		lcsRoleImpl.setLcsRoleId(lcsRoleId);
		lcsRoleImpl.setUserId(userId);
		lcsRoleImpl.setLcsProjectId(lcsProjectId);
		lcsRoleImpl.setLcsClusterEntryId(lcsClusterEntryId);
		lcsRoleImpl.setRole(role);

		lcsRoleImpl.resetOriginalValues();

		return lcsRoleImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		lcsRoleId = objectInput.readLong();

		userId = objectInput.readLong();

		lcsProjectId = objectInput.readLong();

		lcsClusterEntryId = objectInput.readLong();

		role = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(lcsRoleId);

		objectOutput.writeLong(userId);

		objectOutput.writeLong(lcsProjectId);

		objectOutput.writeLong(lcsClusterEntryId);

		objectOutput.writeInt(role);
	}

	public long lcsRoleId;
	public long userId;
	public long lcsProjectId;
	public long lcsClusterEntryId;
	public int role;
}