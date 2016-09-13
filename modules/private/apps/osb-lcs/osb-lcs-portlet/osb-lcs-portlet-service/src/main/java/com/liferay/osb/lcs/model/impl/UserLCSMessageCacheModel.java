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

import com.liferay.osb.lcs.model.UserLCSMessage;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing UserLCSMessage in entity cache.
 *
 * @author Igor Beslic
 * @see UserLCSMessage
 * @generated
 */
@ProviderType
public class UserLCSMessageCacheModel implements CacheModel<UserLCSMessage>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof UserLCSMessageCacheModel)) {
			return false;
		}

		UserLCSMessageCacheModel userLCSMessageCacheModel = (UserLCSMessageCacheModel)obj;

		if (userLcsMessageId == userLCSMessageCacheModel.userLcsMessageId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, userLcsMessageId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{userLcsMessageId=");
		sb.append(userLcsMessageId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", lcsMessageId=");
		sb.append(lcsMessageId);
		sb.append(", hidden=");
		sb.append(hidden);
		sb.append(", read=");
		sb.append(read);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public UserLCSMessage toEntityModel() {
		UserLCSMessageImpl userLCSMessageImpl = new UserLCSMessageImpl();

		userLCSMessageImpl.setUserLcsMessageId(userLcsMessageId);
		userLCSMessageImpl.setUserId(userId);
		userLCSMessageImpl.setLcsMessageId(lcsMessageId);
		userLCSMessageImpl.setHidden(hidden);
		userLCSMessageImpl.setRead(read);

		userLCSMessageImpl.resetOriginalValues();

		return userLCSMessageImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		userLcsMessageId = objectInput.readLong();

		userId = objectInput.readLong();

		lcsMessageId = objectInput.readLong();

		hidden = objectInput.readBoolean();

		read = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(userLcsMessageId);

		objectOutput.writeLong(userId);

		objectOutput.writeLong(lcsMessageId);

		objectOutput.writeBoolean(hidden);

		objectOutput.writeBoolean(read);
	}

	public long userLcsMessageId;
	public long userId;
	public long lcsMessageId;
	public boolean hidden;
	public boolean read;
}