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

import com.liferay.osb.lcs.model.LCSClusterEntryToken;

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
 * The cache model class for representing LCSClusterEntryToken in entity cache.
 *
 * @author Igor Beslic
 * @see LCSClusterEntryToken
 * @generated
 */
@ProviderType
public class LCSClusterEntryTokenCacheModel implements CacheModel<LCSClusterEntryToken>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LCSClusterEntryTokenCacheModel)) {
			return false;
		}

		LCSClusterEntryTokenCacheModel lcsClusterEntryTokenCacheModel = (LCSClusterEntryTokenCacheModel)obj;

		if (lcsClusterEntryTokenId == lcsClusterEntryTokenCacheModel.lcsClusterEntryTokenId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, lcsClusterEntryTokenId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{lcsClusterEntryTokenId=");
		sb.append(lcsClusterEntryTokenId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", lcsClusterEntryId=");
		sb.append(lcsClusterEntryId);
		sb.append(", content=");
		sb.append(content);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LCSClusterEntryToken toEntityModel() {
		LCSClusterEntryTokenImpl lcsClusterEntryTokenImpl = new LCSClusterEntryTokenImpl();

		lcsClusterEntryTokenImpl.setLcsClusterEntryTokenId(lcsClusterEntryTokenId);
		lcsClusterEntryTokenImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			lcsClusterEntryTokenImpl.setCreateDate(null);
		}
		else {
			lcsClusterEntryTokenImpl.setCreateDate(new Date(createDate));
		}

		lcsClusterEntryTokenImpl.setLcsClusterEntryId(lcsClusterEntryId);

		if (content == null) {
			lcsClusterEntryTokenImpl.setContent(StringPool.BLANK);
		}
		else {
			lcsClusterEntryTokenImpl.setContent(content);
		}

		lcsClusterEntryTokenImpl.resetOriginalValues();

		return lcsClusterEntryTokenImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		lcsClusterEntryTokenId = objectInput.readLong();

		userId = objectInput.readLong();
		createDate = objectInput.readLong();

		lcsClusterEntryId = objectInput.readLong();
		content = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(lcsClusterEntryTokenId);

		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);

		objectOutput.writeLong(lcsClusterEntryId);

		if (content == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(content);
		}
	}

	public long lcsClusterEntryTokenId;
	public long userId;
	public long createDate;
	public long lcsClusterEntryId;
	public String content;
}