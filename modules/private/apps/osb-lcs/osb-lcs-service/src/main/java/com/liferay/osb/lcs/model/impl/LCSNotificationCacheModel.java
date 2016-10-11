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

import com.liferay.osb.lcs.model.LCSNotification;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing LCSNotification in entity cache.
 *
 * @author Igor Beslic
 * @see LCSNotification
 * @generated
 */
@ProviderType
public class LCSNotificationCacheModel implements CacheModel<LCSNotification>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LCSNotificationCacheModel)) {
			return false;
		}

		LCSNotificationCacheModel lcsNotificationCacheModel = (LCSNotificationCacheModel)obj;

		if (lcsNotificationId == lcsNotificationCacheModel.lcsNotificationId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, lcsNotificationId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{lcsNotificationId=");
		sb.append(lcsNotificationId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", enabled=");
		sb.append(enabled);
		sb.append(", type=");
		sb.append(type);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LCSNotification toEntityModel() {
		LCSNotificationImpl lcsNotificationImpl = new LCSNotificationImpl();

		lcsNotificationImpl.setLcsNotificationId(lcsNotificationId);
		lcsNotificationImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			lcsNotificationImpl.setCreateDate(null);
		}
		else {
			lcsNotificationImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			lcsNotificationImpl.setModifiedDate(null);
		}
		else {
			lcsNotificationImpl.setModifiedDate(new Date(modifiedDate));
		}

		lcsNotificationImpl.setClassNameId(classNameId);
		lcsNotificationImpl.setClassPK(classPK);
		lcsNotificationImpl.setEnabled(enabled);
		lcsNotificationImpl.setType(type);

		lcsNotificationImpl.resetOriginalValues();

		return lcsNotificationImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		lcsNotificationId = objectInput.readLong();

		userId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		classNameId = objectInput.readLong();

		classPK = objectInput.readLong();

		enabled = objectInput.readBoolean();

		type = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(lcsNotificationId);

		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(classNameId);

		objectOutput.writeLong(classPK);

		objectOutput.writeBoolean(enabled);

		objectOutput.writeInt(type);
	}

	public long lcsNotificationId;
	public long userId;
	public long createDate;
	public long modifiedDate;
	public long classNameId;
	public long classPK;
	public boolean enabled;
	public int type;
}