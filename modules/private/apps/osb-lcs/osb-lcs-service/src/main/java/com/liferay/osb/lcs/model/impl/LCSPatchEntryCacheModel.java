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

import com.liferay.osb.lcs.model.LCSPatchEntry;

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
 * The cache model class for representing LCSPatchEntry in entity cache.
 *
 * @author Igor Beslic
 * @see LCSPatchEntry
 * @generated
 */
@ProviderType
public class LCSPatchEntryCacheModel implements CacheModel<LCSPatchEntry>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LCSPatchEntryCacheModel)) {
			return false;
		}

		LCSPatchEntryCacheModel lcsPatchEntryCacheModel = (LCSPatchEntryCacheModel)obj;

		if (lcsPatchEntryId == lcsPatchEntryCacheModel.lcsPatchEntryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, lcsPatchEntryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(41);

		sb.append("{lcsPatchEntryId=");
		sb.append(lcsPatchEntryId);
		sb.append(", patchId=");
		sb.append(patchId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", patchingToolVersion=");
		sb.append(patchingToolVersion);
		sb.append(", incremental=");
		sb.append(incremental);
		sb.append(", singular=");
		sb.append(singular);
		sb.append(", version=");
		sb.append(version);
		sb.append(", size=");
		sb.append(size);
		sb.append(", rank=");
		sb.append(rank);
		sb.append(", requirements=");
		sb.append(requirements);
		sb.append(", component=");
		sb.append(component);
		sb.append(", compatibleBuild=");
		sb.append(compatibleBuild);
		sb.append(", product=");
		sb.append(product);
		sb.append(", fixedIssues=");
		sb.append(fixedIssues);
		sb.append(", moduleName=");
		sb.append(moduleName);
		sb.append(", moduleId=");
		sb.append(moduleId);
		sb.append(", tunnelWeb=");
		sb.append(tunnelWeb);
		sb.append(", buildDate=");
		sb.append(buildDate);
		sb.append(", builtFor=");
		sb.append(builtFor);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LCSPatchEntry toEntityModel() {
		LCSPatchEntryImpl lcsPatchEntryImpl = new LCSPatchEntryImpl();

		lcsPatchEntryImpl.setLcsPatchEntryId(lcsPatchEntryId);

		if (patchId == null) {
			lcsPatchEntryImpl.setPatchId(StringPool.BLANK);
		}
		else {
			lcsPatchEntryImpl.setPatchId(patchId);
		}

		if (name == null) {
			lcsPatchEntryImpl.setName(StringPool.BLANK);
		}
		else {
			lcsPatchEntryImpl.setName(name);
		}

		if (description == null) {
			lcsPatchEntryImpl.setDescription(StringPool.BLANK);
		}
		else {
			lcsPatchEntryImpl.setDescription(description);
		}

		lcsPatchEntryImpl.setPatchingToolVersion(patchingToolVersion);
		lcsPatchEntryImpl.setIncremental(incremental);
		lcsPatchEntryImpl.setSingular(singular);
		lcsPatchEntryImpl.setVersion(version);
		lcsPatchEntryImpl.setSize(size);
		lcsPatchEntryImpl.setRank(rank);

		if (requirements == null) {
			lcsPatchEntryImpl.setRequirements(StringPool.BLANK);
		}
		else {
			lcsPatchEntryImpl.setRequirements(requirements);
		}

		if (component == null) {
			lcsPatchEntryImpl.setComponent(StringPool.BLANK);
		}
		else {
			lcsPatchEntryImpl.setComponent(component);
		}

		if (compatibleBuild == null) {
			lcsPatchEntryImpl.setCompatibleBuild(StringPool.BLANK);
		}
		else {
			lcsPatchEntryImpl.setCompatibleBuild(compatibleBuild);
		}

		if (product == null) {
			lcsPatchEntryImpl.setProduct(StringPool.BLANK);
		}
		else {
			lcsPatchEntryImpl.setProduct(product);
		}

		if (fixedIssues == null) {
			lcsPatchEntryImpl.setFixedIssues(StringPool.BLANK);
		}
		else {
			lcsPatchEntryImpl.setFixedIssues(fixedIssues);
		}

		if (moduleName == null) {
			lcsPatchEntryImpl.setModuleName(StringPool.BLANK);
		}
		else {
			lcsPatchEntryImpl.setModuleName(moduleName);
		}

		if (moduleId == null) {
			lcsPatchEntryImpl.setModuleId(StringPool.BLANK);
		}
		else {
			lcsPatchEntryImpl.setModuleId(moduleId);
		}

		lcsPatchEntryImpl.setTunnelWeb(tunnelWeb);

		if (buildDate == Long.MIN_VALUE) {
			lcsPatchEntryImpl.setBuildDate(null);
		}
		else {
			lcsPatchEntryImpl.setBuildDate(new Date(buildDate));
		}

		if (builtFor == null) {
			lcsPatchEntryImpl.setBuiltFor(StringPool.BLANK);
		}
		else {
			lcsPatchEntryImpl.setBuiltFor(builtFor);
		}

		lcsPatchEntryImpl.resetOriginalValues();

		return lcsPatchEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		lcsPatchEntryId = objectInput.readLong();
		patchId = objectInput.readUTF();
		name = objectInput.readUTF();
		description = objectInput.readUTF();

		patchingToolVersion = objectInput.readInt();

		incremental = objectInput.readBoolean();

		singular = objectInput.readBoolean();

		version = objectInput.readInt();

		size = objectInput.readLong();

		rank = objectInput.readLong();
		requirements = objectInput.readUTF();
		component = objectInput.readUTF();
		compatibleBuild = objectInput.readUTF();
		product = objectInput.readUTF();
		fixedIssues = objectInput.readUTF();
		moduleName = objectInput.readUTF();
		moduleId = objectInput.readUTF();

		tunnelWeb = objectInput.readBoolean();
		buildDate = objectInput.readLong();
		builtFor = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(lcsPatchEntryId);

		if (patchId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(patchId);
		}

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}

		objectOutput.writeInt(patchingToolVersion);

		objectOutput.writeBoolean(incremental);

		objectOutput.writeBoolean(singular);

		objectOutput.writeInt(version);

		objectOutput.writeLong(size);

		objectOutput.writeLong(rank);

		if (requirements == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(requirements);
		}

		if (component == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(component);
		}

		if (compatibleBuild == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(compatibleBuild);
		}

		if (product == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(product);
		}

		if (fixedIssues == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(fixedIssues);
		}

		if (moduleName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(moduleName);
		}

		if (moduleId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(moduleId);
		}

		objectOutput.writeBoolean(tunnelWeb);
		objectOutput.writeLong(buildDate);

		if (builtFor == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(builtFor);
		}
	}

	public long lcsPatchEntryId;
	public String patchId;
	public String name;
	public String description;
	public int patchingToolVersion;
	public boolean incremental;
	public boolean singular;
	public int version;
	public long size;
	public long rank;
	public String requirements;
	public String component;
	public String compatibleBuild;
	public String product;
	public String fixedIssues;
	public String moduleName;
	public String moduleId;
	public boolean tunnelWeb;
	public long buildDate;
	public String builtFor;
}