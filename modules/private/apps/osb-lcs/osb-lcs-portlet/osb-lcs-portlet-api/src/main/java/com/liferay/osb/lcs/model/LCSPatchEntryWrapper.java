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

package com.liferay.osb.lcs.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link LCSPatchEntry}.
 * </p>
 *
 * @author Igor Beslic
 * @see LCSPatchEntry
 * @generated
 */
@ProviderType
public class LCSPatchEntryWrapper implements LCSPatchEntry,
	ModelWrapper<LCSPatchEntry> {
	public LCSPatchEntryWrapper(LCSPatchEntry lcsPatchEntry) {
		_lcsPatchEntry = lcsPatchEntry;
	}

	@Override
	public Class<?> getModelClass() {
		return LCSPatchEntry.class;
	}

	@Override
	public String getModelClassName() {
		return LCSPatchEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("lcsPatchEntryId", getLcsPatchEntryId());
		attributes.put("patchId", getPatchId());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("patchingToolVersion", getPatchingToolVersion());
		attributes.put("incremental", getIncremental());
		attributes.put("singular", getSingular());
		attributes.put("version", getVersion());
		attributes.put("size", getSize());
		attributes.put("rank", getRank());
		attributes.put("requirements", getRequirements());
		attributes.put("component", getComponent());
		attributes.put("compatibleBuild", getCompatibleBuild());
		attributes.put("product", getProduct());
		attributes.put("fixedIssues", getFixedIssues());
		attributes.put("moduleName", getModuleName());
		attributes.put("moduleId", getModuleId());
		attributes.put("tunnelWeb", getTunnelWeb());
		attributes.put("buildDate", getBuildDate());
		attributes.put("builtFor", getBuiltFor());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long lcsPatchEntryId = (Long)attributes.get("lcsPatchEntryId");

		if (lcsPatchEntryId != null) {
			setLcsPatchEntryId(lcsPatchEntryId);
		}

		String patchId = (String)attributes.get("patchId");

		if (patchId != null) {
			setPatchId(patchId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Integer patchingToolVersion = (Integer)attributes.get(
				"patchingToolVersion");

		if (patchingToolVersion != null) {
			setPatchingToolVersion(patchingToolVersion);
		}

		Boolean incremental = (Boolean)attributes.get("incremental");

		if (incremental != null) {
			setIncremental(incremental);
		}

		Boolean singular = (Boolean)attributes.get("singular");

		if (singular != null) {
			setSingular(singular);
		}

		Integer version = (Integer)attributes.get("version");

		if (version != null) {
			setVersion(version);
		}

		Long size = (Long)attributes.get("size");

		if (size != null) {
			setSize(size);
		}

		Long rank = (Long)attributes.get("rank");

		if (rank != null) {
			setRank(rank);
		}

		String requirements = (String)attributes.get("requirements");

		if (requirements != null) {
			setRequirements(requirements);
		}

		String component = (String)attributes.get("component");

		if (component != null) {
			setComponent(component);
		}

		String compatibleBuild = (String)attributes.get("compatibleBuild");

		if (compatibleBuild != null) {
			setCompatibleBuild(compatibleBuild);
		}

		String product = (String)attributes.get("product");

		if (product != null) {
			setProduct(product);
		}

		String fixedIssues = (String)attributes.get("fixedIssues");

		if (fixedIssues != null) {
			setFixedIssues(fixedIssues);
		}

		String moduleName = (String)attributes.get("moduleName");

		if (moduleName != null) {
			setModuleName(moduleName);
		}

		String moduleId = (String)attributes.get("moduleId");

		if (moduleId != null) {
			setModuleId(moduleId);
		}

		Boolean tunnelWeb = (Boolean)attributes.get("tunnelWeb");

		if (tunnelWeb != null) {
			setTunnelWeb(tunnelWeb);
		}

		Date buildDate = (Date)attributes.get("buildDate");

		if (buildDate != null) {
			setBuildDate(buildDate);
		}

		String builtFor = (String)attributes.get("builtFor");

		if (builtFor != null) {
			setBuiltFor(builtFor);
		}
	}

	@Override
	public LCSPatchEntry toEscapedModel() {
		return new LCSPatchEntryWrapper(_lcsPatchEntry.toEscapedModel());
	}

	@Override
	public LCSPatchEntry toUnescapedModel() {
		return new LCSPatchEntryWrapper(_lcsPatchEntry.toUnescapedModel());
	}

	/**
	* Returns the incremental of this l c s patch entry.
	*
	* @return the incremental of this l c s patch entry
	*/
	@Override
	public boolean getIncremental() {
		return _lcsPatchEntry.getIncremental();
	}

	/**
	* Returns the singular of this l c s patch entry.
	*
	* @return the singular of this l c s patch entry
	*/
	@Override
	public boolean getSingular() {
		return _lcsPatchEntry.getSingular();
	}

	/**
	* Returns the tunnel web of this l c s patch entry.
	*
	* @return the tunnel web of this l c s patch entry
	*/
	@Override
	public boolean getTunnelWeb() {
		return _lcsPatchEntry.getTunnelWeb();
	}

	@Override
	public boolean isCachedModel() {
		return _lcsPatchEntry.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _lcsPatchEntry.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this l c s patch entry is incremental.
	*
	* @return <code>true</code> if this l c s patch entry is incremental; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncremental() {
		return _lcsPatchEntry.isIncremental();
	}

	@Override
	public boolean isNew() {
		return _lcsPatchEntry.isNew();
	}

	/**
	* Returns <code>true</code> if this l c s patch entry is singular.
	*
	* @return <code>true</code> if this l c s patch entry is singular; <code>false</code> otherwise
	*/
	@Override
	public boolean isSingular() {
		return _lcsPatchEntry.isSingular();
	}

	/**
	* Returns <code>true</code> if this l c s patch entry is tunnel web.
	*
	* @return <code>true</code> if this l c s patch entry is tunnel web; <code>false</code> otherwise
	*/
	@Override
	public boolean isTunnelWeb() {
		return _lcsPatchEntry.isTunnelWeb();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _lcsPatchEntry.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<LCSPatchEntry> toCacheModel() {
		return _lcsPatchEntry.toCacheModel();
	}

	@Override
	public int compareTo(LCSPatchEntry lcsPatchEntry) {
		return _lcsPatchEntry.compareTo(lcsPatchEntry);
	}

	/**
	* Returns the patching tool version of this l c s patch entry.
	*
	* @return the patching tool version of this l c s patch entry
	*/
	@Override
	public int getPatchingToolVersion() {
		return _lcsPatchEntry.getPatchingToolVersion();
	}

	/**
	* Returns the version of this l c s patch entry.
	*
	* @return the version of this l c s patch entry
	*/
	@Override
	public int getVersion() {
		return _lcsPatchEntry.getVersion();
	}

	@Override
	public int hashCode() {
		return _lcsPatchEntry.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _lcsPatchEntry.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new LCSPatchEntryWrapper((LCSPatchEntry)_lcsPatchEntry.clone());
	}

	/**
	* Returns the built for of this l c s patch entry.
	*
	* @return the built for of this l c s patch entry
	*/
	@Override
	public java.lang.String getBuiltFor() {
		return _lcsPatchEntry.getBuiltFor();
	}

	/**
	* Returns the compatible build of this l c s patch entry.
	*
	* @return the compatible build of this l c s patch entry
	*/
	@Override
	public java.lang.String getCompatibleBuild() {
		return _lcsPatchEntry.getCompatibleBuild();
	}

	/**
	* Returns the component of this l c s patch entry.
	*
	* @return the component of this l c s patch entry
	*/
	@Override
	public java.lang.String getComponent() {
		return _lcsPatchEntry.getComponent();
	}

	/**
	* Returns the description of this l c s patch entry.
	*
	* @return the description of this l c s patch entry
	*/
	@Override
	public java.lang.String getDescription() {
		return _lcsPatchEntry.getDescription();
	}

	/**
	* Returns the fixed issues of this l c s patch entry.
	*
	* @return the fixed issues of this l c s patch entry
	*/
	@Override
	public java.lang.String getFixedIssues() {
		return _lcsPatchEntry.getFixedIssues();
	}

	/**
	* Returns the module ID of this l c s patch entry.
	*
	* @return the module ID of this l c s patch entry
	*/
	@Override
	public java.lang.String getModuleId() {
		return _lcsPatchEntry.getModuleId();
	}

	/**
	* Returns the module name of this l c s patch entry.
	*
	* @return the module name of this l c s patch entry
	*/
	@Override
	public java.lang.String getModuleName() {
		return _lcsPatchEntry.getModuleName();
	}

	/**
	* Returns the name of this l c s patch entry.
	*
	* @return the name of this l c s patch entry
	*/
	@Override
	public java.lang.String getName() {
		return _lcsPatchEntry.getName();
	}

	/**
	* Returns the patch ID of this l c s patch entry.
	*
	* @return the patch ID of this l c s patch entry
	*/
	@Override
	public java.lang.String getPatchId() {
		return _lcsPatchEntry.getPatchId();
	}

	/**
	* Returns the product of this l c s patch entry.
	*
	* @return the product of this l c s patch entry
	*/
	@Override
	public java.lang.String getProduct() {
		return _lcsPatchEntry.getProduct();
	}

	/**
	* Returns the requirements of this l c s patch entry.
	*
	* @return the requirements of this l c s patch entry
	*/
	@Override
	public java.lang.String getRequirements() {
		return _lcsPatchEntry.getRequirements();
	}

	@Override
	public java.lang.String toString() {
		return _lcsPatchEntry.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _lcsPatchEntry.toXmlString();
	}

	/**
	* Returns the build date of this l c s patch entry.
	*
	* @return the build date of this l c s patch entry
	*/
	@Override
	public Date getBuildDate() {
		return _lcsPatchEntry.getBuildDate();
	}

	/**
	* Returns the lcs patch entry ID of this l c s patch entry.
	*
	* @return the lcs patch entry ID of this l c s patch entry
	*/
	@Override
	public long getLcsPatchEntryId() {
		return _lcsPatchEntry.getLcsPatchEntryId();
	}

	/**
	* Returns the primary key of this l c s patch entry.
	*
	* @return the primary key of this l c s patch entry
	*/
	@Override
	public long getPrimaryKey() {
		return _lcsPatchEntry.getPrimaryKey();
	}

	/**
	* Returns the rank of this l c s patch entry.
	*
	* @return the rank of this l c s patch entry
	*/
	@Override
	public long getRank() {
		return _lcsPatchEntry.getRank();
	}

	/**
	* Returns the size of this l c s patch entry.
	*
	* @return the size of this l c s patch entry
	*/
	@Override
	public long getSize() {
		return _lcsPatchEntry.getSize();
	}

	@Override
	public void persist() {
		_lcsPatchEntry.persist();
	}

	/**
	* Sets the build date of this l c s patch entry.
	*
	* @param buildDate the build date of this l c s patch entry
	*/
	@Override
	public void setBuildDate(Date buildDate) {
		_lcsPatchEntry.setBuildDate(buildDate);
	}

	/**
	* Sets the built for of this l c s patch entry.
	*
	* @param builtFor the built for of this l c s patch entry
	*/
	@Override
	public void setBuiltFor(java.lang.String builtFor) {
		_lcsPatchEntry.setBuiltFor(builtFor);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_lcsPatchEntry.setCachedModel(cachedModel);
	}

	/**
	* Sets the compatible build of this l c s patch entry.
	*
	* @param compatibleBuild the compatible build of this l c s patch entry
	*/
	@Override
	public void setCompatibleBuild(java.lang.String compatibleBuild) {
		_lcsPatchEntry.setCompatibleBuild(compatibleBuild);
	}

	/**
	* Sets the component of this l c s patch entry.
	*
	* @param component the component of this l c s patch entry
	*/
	@Override
	public void setComponent(java.lang.String component) {
		_lcsPatchEntry.setComponent(component);
	}

	/**
	* Sets the description of this l c s patch entry.
	*
	* @param description the description of this l c s patch entry
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_lcsPatchEntry.setDescription(description);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_lcsPatchEntry.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_lcsPatchEntry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_lcsPatchEntry.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the fixed issues of this l c s patch entry.
	*
	* @param fixedIssues the fixed issues of this l c s patch entry
	*/
	@Override
	public void setFixedIssues(java.lang.String fixedIssues) {
		_lcsPatchEntry.setFixedIssues(fixedIssues);
	}

	/**
	* Sets whether this l c s patch entry is incremental.
	*
	* @param incremental the incremental of this l c s patch entry
	*/
	@Override
	public void setIncremental(boolean incremental) {
		_lcsPatchEntry.setIncremental(incremental);
	}

	/**
	* Sets the lcs patch entry ID of this l c s patch entry.
	*
	* @param lcsPatchEntryId the lcs patch entry ID of this l c s patch entry
	*/
	@Override
	public void setLcsPatchEntryId(long lcsPatchEntryId) {
		_lcsPatchEntry.setLcsPatchEntryId(lcsPatchEntryId);
	}

	/**
	* Sets the module ID of this l c s patch entry.
	*
	* @param moduleId the module ID of this l c s patch entry
	*/
	@Override
	public void setModuleId(java.lang.String moduleId) {
		_lcsPatchEntry.setModuleId(moduleId);
	}

	/**
	* Sets the module name of this l c s patch entry.
	*
	* @param moduleName the module name of this l c s patch entry
	*/
	@Override
	public void setModuleName(java.lang.String moduleName) {
		_lcsPatchEntry.setModuleName(moduleName);
	}

	/**
	* Sets the name of this l c s patch entry.
	*
	* @param name the name of this l c s patch entry
	*/
	@Override
	public void setName(java.lang.String name) {
		_lcsPatchEntry.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_lcsPatchEntry.setNew(n);
	}

	/**
	* Sets the patch ID of this l c s patch entry.
	*
	* @param patchId the patch ID of this l c s patch entry
	*/
	@Override
	public void setPatchId(java.lang.String patchId) {
		_lcsPatchEntry.setPatchId(patchId);
	}

	/**
	* Sets the patching tool version of this l c s patch entry.
	*
	* @param patchingToolVersion the patching tool version of this l c s patch entry
	*/
	@Override
	public void setPatchingToolVersion(int patchingToolVersion) {
		_lcsPatchEntry.setPatchingToolVersion(patchingToolVersion);
	}

	/**
	* Sets the primary key of this l c s patch entry.
	*
	* @param primaryKey the primary key of this l c s patch entry
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_lcsPatchEntry.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_lcsPatchEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the product of this l c s patch entry.
	*
	* @param product the product of this l c s patch entry
	*/
	@Override
	public void setProduct(java.lang.String product) {
		_lcsPatchEntry.setProduct(product);
	}

	/**
	* Sets the rank of this l c s patch entry.
	*
	* @param rank the rank of this l c s patch entry
	*/
	@Override
	public void setRank(long rank) {
		_lcsPatchEntry.setRank(rank);
	}

	/**
	* Sets the requirements of this l c s patch entry.
	*
	* @param requirements the requirements of this l c s patch entry
	*/
	@Override
	public void setRequirements(java.lang.String requirements) {
		_lcsPatchEntry.setRequirements(requirements);
	}

	/**
	* Sets whether this l c s patch entry is singular.
	*
	* @param singular the singular of this l c s patch entry
	*/
	@Override
	public void setSingular(boolean singular) {
		_lcsPatchEntry.setSingular(singular);
	}

	/**
	* Sets the size of this l c s patch entry.
	*
	* @param size the size of this l c s patch entry
	*/
	@Override
	public void setSize(long size) {
		_lcsPatchEntry.setSize(size);
	}

	/**
	* Sets whether this l c s patch entry is tunnel web.
	*
	* @param tunnelWeb the tunnel web of this l c s patch entry
	*/
	@Override
	public void setTunnelWeb(boolean tunnelWeb) {
		_lcsPatchEntry.setTunnelWeb(tunnelWeb);
	}

	/**
	* Sets the version of this l c s patch entry.
	*
	* @param version the version of this l c s patch entry
	*/
	@Override
	public void setVersion(int version) {
		_lcsPatchEntry.setVersion(version);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LCSPatchEntryWrapper)) {
			return false;
		}

		LCSPatchEntryWrapper lcsPatchEntryWrapper = (LCSPatchEntryWrapper)obj;

		if (Objects.equals(_lcsPatchEntry, lcsPatchEntryWrapper._lcsPatchEntry)) {
			return true;
		}

		return false;
	}

	@Override
	public LCSPatchEntry getWrappedModel() {
		return _lcsPatchEntry;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _lcsPatchEntry.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _lcsPatchEntry.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_lcsPatchEntry.resetOriginalValues();
	}

	private final LCSPatchEntry _lcsPatchEntry;
}