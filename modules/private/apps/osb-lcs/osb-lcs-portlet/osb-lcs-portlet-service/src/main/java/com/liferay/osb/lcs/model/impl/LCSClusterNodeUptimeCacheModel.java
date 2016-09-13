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

import com.liferay.osb.lcs.model.LCSClusterNodeUptime;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing LCSClusterNodeUptime in entity cache.
 *
 * @author Igor Beslic
 * @see LCSClusterNodeUptime
 * @generated
 */
@ProviderType
public class LCSClusterNodeUptimeCacheModel implements CacheModel<LCSClusterNodeUptime>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LCSClusterNodeUptimeCacheModel)) {
			return false;
		}

		LCSClusterNodeUptimeCacheModel lcsClusterNodeUptimeCacheModel = (LCSClusterNodeUptimeCacheModel)obj;

		if (lcsClusterNodeUptimeId == lcsClusterNodeUptimeCacheModel.lcsClusterNodeUptimeId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, lcsClusterNodeUptimeId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{lcsClusterNodeUptimeId=");
		sb.append(lcsClusterNodeUptimeId);
		sb.append(", lcsClusterNodeId=");
		sb.append(lcsClusterNodeId);
		sb.append(", startTime=");
		sb.append(startTime);
		sb.append(", endTime=");
		sb.append(endTime);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LCSClusterNodeUptime toEntityModel() {
		LCSClusterNodeUptimeImpl lcsClusterNodeUptimeImpl = new LCSClusterNodeUptimeImpl();

		lcsClusterNodeUptimeImpl.setLcsClusterNodeUptimeId(lcsClusterNodeUptimeId);
		lcsClusterNodeUptimeImpl.setLcsClusterNodeId(lcsClusterNodeId);
		lcsClusterNodeUptimeImpl.setStartTime(startTime);
		lcsClusterNodeUptimeImpl.setEndTime(endTime);

		lcsClusterNodeUptimeImpl.resetOriginalValues();

		return lcsClusterNodeUptimeImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		lcsClusterNodeUptimeId = objectInput.readLong();

		lcsClusterNodeId = objectInput.readLong();

		startTime = objectInput.readLong();

		endTime = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(lcsClusterNodeUptimeId);

		objectOutput.writeLong(lcsClusterNodeId);

		objectOutput.writeLong(startTime);

		objectOutput.writeLong(endTime);
	}

	public long lcsClusterNodeUptimeId;
	public long lcsClusterNodeId;
	public long startTime;
	public long endTime;
}