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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.lcs.service.http.LCSClusterNodeServiceSoap}.
 *
 * @author Igor Beslic
 * @see com.liferay.osb.lcs.service.http.LCSClusterNodeServiceSoap
 * @generated
 */
@ProviderType
public class LCSClusterNodeSoap implements Serializable {
	public static LCSClusterNodeSoap toSoapModel(LCSClusterNode model) {
		LCSClusterNodeSoap soapModel = new LCSClusterNodeSoap();

		soapModel.setLcsClusterNodeId(model.getLcsClusterNodeId());
		soapModel.setLcsClusterEntryId(model.getLcsClusterEntryId());
		soapModel.setInstallationId(model.getInstallationId());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());
		soapModel.setBuildNumber(model.getBuildNumber());
		soapModel.setKey(model.getKey());
		soapModel.setLocation(model.getLocation());
		soapModel.setProcessorCoresTotal(model.getProcessorCoresTotal());
		soapModel.setArchived(model.getArchived());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static LCSClusterNodeSoap[] toSoapModels(LCSClusterNode[] models) {
		LCSClusterNodeSoap[] soapModels = new LCSClusterNodeSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LCSClusterNodeSoap[][] toSoapModels(LCSClusterNode[][] models) {
		LCSClusterNodeSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new LCSClusterNodeSoap[models.length][models[0].length];
		}
		else {
			soapModels = new LCSClusterNodeSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LCSClusterNodeSoap[] toSoapModels(List<LCSClusterNode> models) {
		List<LCSClusterNodeSoap> soapModels = new ArrayList<LCSClusterNodeSoap>(models.size());

		for (LCSClusterNode model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new LCSClusterNodeSoap[soapModels.size()]);
	}

	public LCSClusterNodeSoap() {
	}

	public long getPrimaryKey() {
		return _lcsClusterNodeId;
	}

	public void setPrimaryKey(long pk) {
		setLcsClusterNodeId(pk);
	}

	public long getLcsClusterNodeId() {
		return _lcsClusterNodeId;
	}

	public void setLcsClusterNodeId(long lcsClusterNodeId) {
		_lcsClusterNodeId = lcsClusterNodeId;
	}

	public long getLcsClusterEntryId() {
		return _lcsClusterEntryId;
	}

	public void setLcsClusterEntryId(long lcsClusterEntryId) {
		_lcsClusterEntryId = lcsClusterEntryId;
	}

	public long getInstallationId() {
		return _installationId;
	}

	public void setInstallationId(long installationId) {
		_installationId = installationId;
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

	public int getBuildNumber() {
		return _buildNumber;
	}

	public void setBuildNumber(int buildNumber) {
		_buildNumber = buildNumber;
	}

	public String getKey() {
		return _key;
	}

	public void setKey(String key) {
		_key = key;
	}

	public String getLocation() {
		return _location;
	}

	public void setLocation(String location) {
		_location = location;
	}

	public int getProcessorCoresTotal() {
		return _processorCoresTotal;
	}

	public void setProcessorCoresTotal(int processorCoresTotal) {
		_processorCoresTotal = processorCoresTotal;
	}

	public boolean getArchived() {
		return _archived;
	}

	public boolean isArchived() {
		return _archived;
	}

	public void setArchived(boolean archived) {
		_archived = archived;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private long _lcsClusterNodeId;
	private long _lcsClusterEntryId;
	private long _installationId;
	private String _name;
	private String _description;
	private int _buildNumber;
	private String _key;
	private String _location;
	private int _processorCoresTotal;
	private boolean _archived;
	private int _status;
}