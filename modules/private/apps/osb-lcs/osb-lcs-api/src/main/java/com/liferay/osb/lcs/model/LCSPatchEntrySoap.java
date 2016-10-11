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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Igor Beslic
 * @generated
 */
@ProviderType
public class LCSPatchEntrySoap implements Serializable {
	public static LCSPatchEntrySoap toSoapModel(LCSPatchEntry model) {
		LCSPatchEntrySoap soapModel = new LCSPatchEntrySoap();

		soapModel.setLcsPatchEntryId(model.getLcsPatchEntryId());
		soapModel.setPatchId(model.getPatchId());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());
		soapModel.setPatchingToolVersion(model.getPatchingToolVersion());
		soapModel.setIncremental(model.getIncremental());
		soapModel.setSingular(model.getSingular());
		soapModel.setVersion(model.getVersion());
		soapModel.setSize(model.getSize());
		soapModel.setRank(model.getRank());
		soapModel.setRequirements(model.getRequirements());
		soapModel.setComponent(model.getComponent());
		soapModel.setCompatibleBuild(model.getCompatibleBuild());
		soapModel.setProduct(model.getProduct());
		soapModel.setFixedIssues(model.getFixedIssues());
		soapModel.setModuleName(model.getModuleName());
		soapModel.setModuleId(model.getModuleId());
		soapModel.setTunnelWeb(model.getTunnelWeb());
		soapModel.setBuildDate(model.getBuildDate());
		soapModel.setBuiltFor(model.getBuiltFor());

		return soapModel;
	}

	public static LCSPatchEntrySoap[] toSoapModels(LCSPatchEntry[] models) {
		LCSPatchEntrySoap[] soapModels = new LCSPatchEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LCSPatchEntrySoap[][] toSoapModels(LCSPatchEntry[][] models) {
		LCSPatchEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new LCSPatchEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new LCSPatchEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LCSPatchEntrySoap[] toSoapModels(List<LCSPatchEntry> models) {
		List<LCSPatchEntrySoap> soapModels = new ArrayList<LCSPatchEntrySoap>(models.size());

		for (LCSPatchEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new LCSPatchEntrySoap[soapModels.size()]);
	}

	public LCSPatchEntrySoap() {
	}

	public long getPrimaryKey() {
		return _lcsPatchEntryId;
	}

	public void setPrimaryKey(long pk) {
		setLcsPatchEntryId(pk);
	}

	public long getLcsPatchEntryId() {
		return _lcsPatchEntryId;
	}

	public void setLcsPatchEntryId(long lcsPatchEntryId) {
		_lcsPatchEntryId = lcsPatchEntryId;
	}

	public String getPatchId() {
		return _patchId;
	}

	public void setPatchId(String patchId) {
		_patchId = patchId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public int getPatchingToolVersion() {
		return _patchingToolVersion;
	}

	public void setPatchingToolVersion(int patchingToolVersion) {
		_patchingToolVersion = patchingToolVersion;
	}

	public boolean getIncremental() {
		return _incremental;
	}

	public boolean isIncremental() {
		return _incremental;
	}

	public void setIncremental(boolean incremental) {
		_incremental = incremental;
	}

	public boolean getSingular() {
		return _singular;
	}

	public boolean isSingular() {
		return _singular;
	}

	public void setSingular(boolean singular) {
		_singular = singular;
	}

	public int getVersion() {
		return _version;
	}

	public void setVersion(int version) {
		_version = version;
	}

	public long getSize() {
		return _size;
	}

	public void setSize(long size) {
		_size = size;
	}

	public long getRank() {
		return _rank;
	}

	public void setRank(long rank) {
		_rank = rank;
	}

	public String getRequirements() {
		return _requirements;
	}

	public void setRequirements(String requirements) {
		_requirements = requirements;
	}

	public String getComponent() {
		return _component;
	}

	public void setComponent(String component) {
		_component = component;
	}

	public String getCompatibleBuild() {
		return _compatibleBuild;
	}

	public void setCompatibleBuild(String compatibleBuild) {
		_compatibleBuild = compatibleBuild;
	}

	public String getProduct() {
		return _product;
	}

	public void setProduct(String product) {
		_product = product;
	}

	public String getFixedIssues() {
		return _fixedIssues;
	}

	public void setFixedIssues(String fixedIssues) {
		_fixedIssues = fixedIssues;
	}

	public String getModuleName() {
		return _moduleName;
	}

	public void setModuleName(String moduleName) {
		_moduleName = moduleName;
	}

	public String getModuleId() {
		return _moduleId;
	}

	public void setModuleId(String moduleId) {
		_moduleId = moduleId;
	}

	public boolean getTunnelWeb() {
		return _tunnelWeb;
	}

	public boolean isTunnelWeb() {
		return _tunnelWeb;
	}

	public void setTunnelWeb(boolean tunnelWeb) {
		_tunnelWeb = tunnelWeb;
	}

	public Date getBuildDate() {
		return _buildDate;
	}

	public void setBuildDate(Date buildDate) {
		_buildDate = buildDate;
	}

	public String getBuiltFor() {
		return _builtFor;
	}

	public void setBuiltFor(String builtFor) {
		_builtFor = builtFor;
	}

	private long _lcsPatchEntryId;
	private String _patchId;
	private String _name;
	private String _description;
	private int _patchingToolVersion;
	private boolean _incremental;
	private boolean _singular;
	private int _version;
	private long _size;
	private long _rank;
	private String _requirements;
	private String _component;
	private String _compatibleBuild;
	private String _product;
	private String _fixedIssues;
	private String _moduleName;
	private String _moduleId;
	private boolean _tunnelWeb;
	private Date _buildDate;
	private String _builtFor;
}