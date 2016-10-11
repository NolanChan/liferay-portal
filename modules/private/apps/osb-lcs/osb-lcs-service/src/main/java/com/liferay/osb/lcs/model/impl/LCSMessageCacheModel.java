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

import com.liferay.osb.lcs.model.LCSMessage;

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
 * The cache model class for representing LCSMessage in entity cache.
 *
 * @author Igor Beslic
 * @see LCSMessage
 * @generated
 */
@ProviderType
public class LCSMessageCacheModel implements CacheModel<LCSMessage>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LCSMessageCacheModel)) {
			return false;
		}

		LCSMessageCacheModel lcsMessageCacheModel = (LCSMessageCacheModel)obj;

		if (lcsMessageId == lcsMessageCacheModel.lcsMessageId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, lcsMessageId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{lcsMessageId=");
		sb.append(lcsMessageId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", sourceMessageId=");
		sb.append(sourceMessageId);
		sb.append(", sourceSystemName=");
		sb.append(sourceSystemName);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", content=");
		sb.append(content);
		sb.append(", endDate=");
		sb.append(endDate);
		sb.append(", global=");
		sb.append(global);
		sb.append(", severityLevel=");
		sb.append(severityLevel);
		sb.append(", type=");
		sb.append(type);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LCSMessage toEntityModel() {
		LCSMessageImpl lcsMessageImpl = new LCSMessageImpl();

		lcsMessageImpl.setLcsMessageId(lcsMessageId);

		if (createDate == Long.MIN_VALUE) {
			lcsMessageImpl.setCreateDate(null);
		}
		else {
			lcsMessageImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			lcsMessageImpl.setModifiedDate(null);
		}
		else {
			lcsMessageImpl.setModifiedDate(new Date(modifiedDate));
		}

		lcsMessageImpl.setSourceMessageId(sourceMessageId);

		if (sourceSystemName == null) {
			lcsMessageImpl.setSourceSystemName(StringPool.BLANK);
		}
		else {
			lcsMessageImpl.setSourceSystemName(sourceSystemName);
		}

		lcsMessageImpl.setClassNameId(classNameId);
		lcsMessageImpl.setClassPK(classPK);

		if (content == null) {
			lcsMessageImpl.setContent(StringPool.BLANK);
		}
		else {
			lcsMessageImpl.setContent(content);
		}

		if (endDate == Long.MIN_VALUE) {
			lcsMessageImpl.setEndDate(null);
		}
		else {
			lcsMessageImpl.setEndDate(new Date(endDate));
		}

		lcsMessageImpl.setGlobal(global);
		lcsMessageImpl.setSeverityLevel(severityLevel);
		lcsMessageImpl.setType(type);

		lcsMessageImpl.resetOriginalValues();

		return lcsMessageImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		lcsMessageId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		sourceMessageId = objectInput.readLong();
		sourceSystemName = objectInput.readUTF();

		classNameId = objectInput.readLong();

		classPK = objectInput.readLong();
		content = objectInput.readUTF();
		endDate = objectInput.readLong();

		global = objectInput.readBoolean();

		severityLevel = objectInput.readInt();

		type = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(lcsMessageId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(sourceMessageId);

		if (sourceSystemName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(sourceSystemName);
		}

		objectOutput.writeLong(classNameId);

		objectOutput.writeLong(classPK);

		if (content == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(content);
		}

		objectOutput.writeLong(endDate);

		objectOutput.writeBoolean(global);

		objectOutput.writeInt(severityLevel);

		objectOutput.writeInt(type);
	}

	public long lcsMessageId;
	public long createDate;
	public long modifiedDate;
	public long sourceMessageId;
	public String sourceSystemName;
	public long classNameId;
	public long classPK;
	public String content;
	public long endDate;
	public boolean global;
	public int severityLevel;
	public int type;
}