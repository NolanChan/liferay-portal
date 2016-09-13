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

import com.liferay.osb.lcs.model.LCSProject;

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
 * The cache model class for representing LCSProject in entity cache.
 *
 * @author Igor Beslic
 * @see LCSProject
 * @generated
 */
@ProviderType
public class LCSProjectCacheModel implements CacheModel<LCSProject>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LCSProjectCacheModel)) {
			return false;
		}

		LCSProjectCacheModel lcsProjectCacheModel = (LCSProjectCacheModel)obj;

		if (lcsProjectId == lcsProjectCacheModel.lcsProjectId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, lcsProjectId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{lcsProjectId=");
		sb.append(lcsProjectId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", sourceSystemName=");
		sb.append(sourceSystemName);
		sb.append(", name=");
		sb.append(name);
		sb.append(", organizationId=");
		sb.append(organizationId);
		sb.append(", addressId=");
		sb.append(addressId);
		sb.append(", accountEntryId=");
		sb.append(accountEntryId);
		sb.append(", corpProjectId=");
		sb.append(corpProjectId);
		sb.append(", contactEmailAddress=");
		sb.append(contactEmailAddress);
		sb.append(", phoneNumber=");
		sb.append(phoneNumber);
		sb.append(", subscriptionActive=");
		sb.append(subscriptionActive);
		sb.append(", archived=");
		sb.append(archived);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LCSProject toEntityModel() {
		LCSProjectImpl lcsProjectImpl = new LCSProjectImpl();

		lcsProjectImpl.setLcsProjectId(lcsProjectId);

		if (createDate == Long.MIN_VALUE) {
			lcsProjectImpl.setCreateDate(null);
		}
		else {
			lcsProjectImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			lcsProjectImpl.setModifiedDate(null);
		}
		else {
			lcsProjectImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (sourceSystemName == null) {
			lcsProjectImpl.setSourceSystemName(StringPool.BLANK);
		}
		else {
			lcsProjectImpl.setSourceSystemName(sourceSystemName);
		}

		if (name == null) {
			lcsProjectImpl.setName(StringPool.BLANK);
		}
		else {
			lcsProjectImpl.setName(name);
		}

		lcsProjectImpl.setOrganizationId(organizationId);
		lcsProjectImpl.setAddressId(addressId);
		lcsProjectImpl.setAccountEntryId(accountEntryId);
		lcsProjectImpl.setCorpProjectId(corpProjectId);

		if (contactEmailAddress == null) {
			lcsProjectImpl.setContactEmailAddress(StringPool.BLANK);
		}
		else {
			lcsProjectImpl.setContactEmailAddress(contactEmailAddress);
		}

		if (phoneNumber == null) {
			lcsProjectImpl.setPhoneNumber(StringPool.BLANK);
		}
		else {
			lcsProjectImpl.setPhoneNumber(phoneNumber);
		}

		lcsProjectImpl.setSubscriptionActive(subscriptionActive);
		lcsProjectImpl.setArchived(archived);

		lcsProjectImpl.resetOriginalValues();

		return lcsProjectImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		lcsProjectId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		sourceSystemName = objectInput.readUTF();
		name = objectInput.readUTF();

		organizationId = objectInput.readLong();

		addressId = objectInput.readLong();

		accountEntryId = objectInput.readLong();

		corpProjectId = objectInput.readLong();
		contactEmailAddress = objectInput.readUTF();
		phoneNumber = objectInput.readUTF();

		subscriptionActive = objectInput.readBoolean();

		archived = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(lcsProjectId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (sourceSystemName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(sourceSystemName);
		}

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		objectOutput.writeLong(organizationId);

		objectOutput.writeLong(addressId);

		objectOutput.writeLong(accountEntryId);

		objectOutput.writeLong(corpProjectId);

		if (contactEmailAddress == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(contactEmailAddress);
		}

		if (phoneNumber == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(phoneNumber);
		}

		objectOutput.writeBoolean(subscriptionActive);

		objectOutput.writeBoolean(archived);
	}

	public long lcsProjectId;
	public long createDate;
	public long modifiedDate;
	public String sourceSystemName;
	public String name;
	public long organizationId;
	public long addressId;
	public long accountEntryId;
	public long corpProjectId;
	public String contactEmailAddress;
	public String phoneNumber;
	public boolean subscriptionActive;
	public boolean archived;
}