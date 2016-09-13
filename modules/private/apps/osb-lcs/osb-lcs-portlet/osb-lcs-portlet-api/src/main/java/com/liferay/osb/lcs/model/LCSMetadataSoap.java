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
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.lcs.service.http.LCSMetadataServiceSoap}.
 *
 * @author Igor Beslic
 * @see com.liferay.osb.lcs.service.http.LCSMetadataServiceSoap
 * @generated
 */
@ProviderType
public class LCSMetadataSoap implements Serializable {
	public static LCSMetadataSoap toSoapModel(LCSMetadata model) {
		LCSMetadataSoap soapModel = new LCSMetadataSoap();

		soapModel.setLcsMetadataId(model.getLcsMetadataId());
		soapModel.setAvailabilityIndex(model.getAvailabilityIndex());
		soapModel.setBuildNumber(model.getBuildNumber());
		soapModel.setGitTag(model.getGitTag());
		soapModel.setPortalEdition(model.getPortalEdition());
		soapModel.setSupportedLCSPortlet(model.getSupportedLCSPortlet());
		soapModel.setSupportedPatchingTool(model.getSupportedPatchingTool());

		return soapModel;
	}

	public static LCSMetadataSoap[] toSoapModels(LCSMetadata[] models) {
		LCSMetadataSoap[] soapModels = new LCSMetadataSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LCSMetadataSoap[][] toSoapModels(LCSMetadata[][] models) {
		LCSMetadataSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new LCSMetadataSoap[models.length][models[0].length];
		}
		else {
			soapModels = new LCSMetadataSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LCSMetadataSoap[] toSoapModels(List<LCSMetadata> models) {
		List<LCSMetadataSoap> soapModels = new ArrayList<LCSMetadataSoap>(models.size());

		for (LCSMetadata model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new LCSMetadataSoap[soapModels.size()]);
	}

	public LCSMetadataSoap() {
	}

	public long getPrimaryKey() {
		return _lcsMetadataId;
	}

	public void setPrimaryKey(long pk) {
		setLcsMetadataId(pk);
	}

	public long getLcsMetadataId() {
		return _lcsMetadataId;
	}

	public void setLcsMetadataId(long lcsMetadataId) {
		_lcsMetadataId = lcsMetadataId;
	}

	public long getAvailabilityIndex() {
		return _availabilityIndex;
	}

	public void setAvailabilityIndex(long availabilityIndex) {
		_availabilityIndex = availabilityIndex;
	}

	public int getBuildNumber() {
		return _buildNumber;
	}

	public void setBuildNumber(int buildNumber) {
		_buildNumber = buildNumber;
	}

	public String getGitTag() {
		return _gitTag;
	}

	public void setGitTag(String gitTag) {
		_gitTag = gitTag;
	}

	public String getPortalEdition() {
		return _portalEdition;
	}

	public void setPortalEdition(String portalEdition) {
		_portalEdition = portalEdition;
	}

	public int getSupportedLCSPortlet() {
		return _supportedLCSPortlet;
	}

	public void setSupportedLCSPortlet(int supportedLCSPortlet) {
		_supportedLCSPortlet = supportedLCSPortlet;
	}

	public int getSupportedPatchingTool() {
		return _supportedPatchingTool;
	}

	public void setSupportedPatchingTool(int supportedPatchingTool) {
		_supportedPatchingTool = supportedPatchingTool;
	}

	private long _lcsMetadataId;
	private long _availabilityIndex;
	private int _buildNumber;
	private String _gitTag;
	private String _portalEdition;
	private int _supportedLCSPortlet;
	private int _supportedPatchingTool;
}