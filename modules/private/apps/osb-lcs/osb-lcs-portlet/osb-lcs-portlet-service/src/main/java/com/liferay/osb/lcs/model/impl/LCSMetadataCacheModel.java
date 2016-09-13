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

import com.liferay.osb.lcs.model.LCSMetadata;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing LCSMetadata in entity cache.
 *
 * @author Igor Beslic
 * @see LCSMetadata
 * @generated
 */
@ProviderType
public class LCSMetadataCacheModel implements CacheModel<LCSMetadata>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LCSMetadataCacheModel)) {
			return false;
		}

		LCSMetadataCacheModel lcsMetadataCacheModel = (LCSMetadataCacheModel)obj;

		if (lcsMetadataId == lcsMetadataCacheModel.lcsMetadataId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, lcsMetadataId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{lcsMetadataId=");
		sb.append(lcsMetadataId);
		sb.append(", availabilityIndex=");
		sb.append(availabilityIndex);
		sb.append(", buildNumber=");
		sb.append(buildNumber);
		sb.append(", gitTag=");
		sb.append(gitTag);
		sb.append(", portalEdition=");
		sb.append(portalEdition);
		sb.append(", supportedLCSPortlet=");
		sb.append(supportedLCSPortlet);
		sb.append(", supportedPatchingTool=");
		sb.append(supportedPatchingTool);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LCSMetadata toEntityModel() {
		LCSMetadataImpl lcsMetadataImpl = new LCSMetadataImpl();

		lcsMetadataImpl.setLcsMetadataId(lcsMetadataId);
		lcsMetadataImpl.setAvailabilityIndex(availabilityIndex);
		lcsMetadataImpl.setBuildNumber(buildNumber);

		if (gitTag == null) {
			lcsMetadataImpl.setGitTag(StringPool.BLANK);
		}
		else {
			lcsMetadataImpl.setGitTag(gitTag);
		}

		if (portalEdition == null) {
			lcsMetadataImpl.setPortalEdition(StringPool.BLANK);
		}
		else {
			lcsMetadataImpl.setPortalEdition(portalEdition);
		}

		lcsMetadataImpl.setSupportedLCSPortlet(supportedLCSPortlet);
		lcsMetadataImpl.setSupportedPatchingTool(supportedPatchingTool);

		lcsMetadataImpl.resetOriginalValues();

		return lcsMetadataImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		lcsMetadataId = objectInput.readLong();

		availabilityIndex = objectInput.readLong();

		buildNumber = objectInput.readInt();
		gitTag = objectInput.readUTF();
		portalEdition = objectInput.readUTF();

		supportedLCSPortlet = objectInput.readInt();

		supportedPatchingTool = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(lcsMetadataId);

		objectOutput.writeLong(availabilityIndex);

		objectOutput.writeInt(buildNumber);

		if (gitTag == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(gitTag);
		}

		if (portalEdition == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(portalEdition);
		}

		objectOutput.writeInt(supportedLCSPortlet);

		objectOutput.writeInt(supportedPatchingTool);
	}

	public long lcsMetadataId;
	public long availabilityIndex;
	public int buildNumber;
	public String gitTag;
	public String portalEdition;
	public int supportedLCSPortlet;
	public int supportedPatchingTool;
}