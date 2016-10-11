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

import com.liferay.osb.lcs.model.LCSSubscriptionEntry;

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
 * The cache model class for representing LCSSubscriptionEntry in entity cache.
 *
 * @author Igor Beslic
 * @see LCSSubscriptionEntry
 * @generated
 */
@ProviderType
public class LCSSubscriptionEntryCacheModel implements CacheModel<LCSSubscriptionEntry>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LCSSubscriptionEntryCacheModel)) {
			return false;
		}

		LCSSubscriptionEntryCacheModel lcsSubscriptionEntryCacheModel = (LCSSubscriptionEntryCacheModel)obj;

		if (lcsSubscriptionEntryId == lcsSubscriptionEntryCacheModel.lcsSubscriptionEntryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, lcsSubscriptionEntryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(37);

		sb.append("{lcsSubscriptionEntryId=");
		sb.append(lcsSubscriptionEntryId);
		sb.append(", lcsProjectId=");
		sb.append(lcsProjectId);
		sb.append(", actualPrice=");
		sb.append(actualPrice);
		sb.append(", currencyCode=");
		sb.append(currencyCode);
		sb.append(", instanceSize=");
		sb.append(instanceSize);
		sb.append(", type=");
		sb.append(type);
		sb.append(", platform=");
		sb.append(platform);
		sb.append(", platformVersion=");
		sb.append(platformVersion);
		sb.append(", processorCoresAllowed=");
		sb.append(processorCoresAllowed);
		sb.append(", product=");
		sb.append(product);
		sb.append(", productVersion=");
		sb.append(productVersion);
		sb.append(", serversAllowed=");
		sb.append(serversAllowed);
		sb.append(", serversUsed=");
		sb.append(serversUsed);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", endDate=");
		sb.append(endDate);
		sb.append(", supportStartDate=");
		sb.append(supportStartDate);
		sb.append(", supportEndDate=");
		sb.append(supportEndDate);
		sb.append(", active=");
		sb.append(active);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LCSSubscriptionEntry toEntityModel() {
		LCSSubscriptionEntryImpl lcsSubscriptionEntryImpl = new LCSSubscriptionEntryImpl();

		lcsSubscriptionEntryImpl.setLcsSubscriptionEntryId(lcsSubscriptionEntryId);
		lcsSubscriptionEntryImpl.setLcsProjectId(lcsProjectId);
		lcsSubscriptionEntryImpl.setActualPrice(actualPrice);

		if (currencyCode == null) {
			lcsSubscriptionEntryImpl.setCurrencyCode(StringPool.BLANK);
		}
		else {
			lcsSubscriptionEntryImpl.setCurrencyCode(currencyCode);
		}

		lcsSubscriptionEntryImpl.setInstanceSize(instanceSize);

		if (type == null) {
			lcsSubscriptionEntryImpl.setType(StringPool.BLANK);
		}
		else {
			lcsSubscriptionEntryImpl.setType(type);
		}

		if (platform == null) {
			lcsSubscriptionEntryImpl.setPlatform(StringPool.BLANK);
		}
		else {
			lcsSubscriptionEntryImpl.setPlatform(platform);
		}

		lcsSubscriptionEntryImpl.setPlatformVersion(platformVersion);
		lcsSubscriptionEntryImpl.setProcessorCoresAllowed(processorCoresAllowed);

		if (product == null) {
			lcsSubscriptionEntryImpl.setProduct(StringPool.BLANK);
		}
		else {
			lcsSubscriptionEntryImpl.setProduct(product);
		}

		lcsSubscriptionEntryImpl.setProductVersion(productVersion);
		lcsSubscriptionEntryImpl.setServersAllowed(serversAllowed);
		lcsSubscriptionEntryImpl.setServersUsed(serversUsed);

		if (startDate == Long.MIN_VALUE) {
			lcsSubscriptionEntryImpl.setStartDate(null);
		}
		else {
			lcsSubscriptionEntryImpl.setStartDate(new Date(startDate));
		}

		if (endDate == Long.MIN_VALUE) {
			lcsSubscriptionEntryImpl.setEndDate(null);
		}
		else {
			lcsSubscriptionEntryImpl.setEndDate(new Date(endDate));
		}

		if (supportStartDate == Long.MIN_VALUE) {
			lcsSubscriptionEntryImpl.setSupportStartDate(null);
		}
		else {
			lcsSubscriptionEntryImpl.setSupportStartDate(new Date(
					supportStartDate));
		}

		if (supportEndDate == Long.MIN_VALUE) {
			lcsSubscriptionEntryImpl.setSupportEndDate(null);
		}
		else {
			lcsSubscriptionEntryImpl.setSupportEndDate(new Date(supportEndDate));
		}

		lcsSubscriptionEntryImpl.setActive(active);

		lcsSubscriptionEntryImpl.resetOriginalValues();

		return lcsSubscriptionEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		lcsSubscriptionEntryId = objectInput.readLong();

		lcsProjectId = objectInput.readLong();

		actualPrice = objectInput.readDouble();
		currencyCode = objectInput.readUTF();

		instanceSize = objectInput.readInt();
		type = objectInput.readUTF();
		platform = objectInput.readUTF();

		platformVersion = objectInput.readInt();

		processorCoresAllowed = objectInput.readInt();
		product = objectInput.readUTF();

		productVersion = objectInput.readInt();

		serversAllowed = objectInput.readInt();

		serversUsed = objectInput.readInt();
		startDate = objectInput.readLong();
		endDate = objectInput.readLong();
		supportStartDate = objectInput.readLong();
		supportEndDate = objectInput.readLong();

		active = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(lcsSubscriptionEntryId);

		objectOutput.writeLong(lcsProjectId);

		objectOutput.writeDouble(actualPrice);

		if (currencyCode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(currencyCode);
		}

		objectOutput.writeInt(instanceSize);

		if (type == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(type);
		}

		if (platform == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(platform);
		}

		objectOutput.writeInt(platformVersion);

		objectOutput.writeInt(processorCoresAllowed);

		if (product == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(product);
		}

		objectOutput.writeInt(productVersion);

		objectOutput.writeInt(serversAllowed);

		objectOutput.writeInt(serversUsed);
		objectOutput.writeLong(startDate);
		objectOutput.writeLong(endDate);
		objectOutput.writeLong(supportStartDate);
		objectOutput.writeLong(supportEndDate);

		objectOutput.writeBoolean(active);
	}

	public long lcsSubscriptionEntryId;
	public long lcsProjectId;
	public double actualPrice;
	public String currencyCode;
	public int instanceSize;
	public String type;
	public String platform;
	public int platformVersion;
	public int processorCoresAllowed;
	public String product;
	public int productVersion;
	public int serversAllowed;
	public int serversUsed;
	public long startDate;
	public long endDate;
	public long supportStartDate;
	public long supportEndDate;
	public boolean active;
}