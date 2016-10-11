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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.lcs.service.http.LCSClusterNodeUptimeServiceSoap}.
 *
 * @author Igor Beslic
 * @see com.liferay.osb.lcs.service.http.LCSClusterNodeUptimeServiceSoap
 * @generated
 */
@ProviderType
public class LCSClusterNodeUptimeSoap implements Serializable {
	public static LCSClusterNodeUptimeSoap toSoapModel(
		LCSClusterNodeUptime model) {
		LCSClusterNodeUptimeSoap soapModel = new LCSClusterNodeUptimeSoap();

		soapModel.setLcsClusterNodeUptimeId(model.getLcsClusterNodeUptimeId());
		soapModel.setLcsClusterNodeId(model.getLcsClusterNodeId());
		soapModel.setStartTime(model.getStartTime());
		soapModel.setEndTime(model.getEndTime());

		return soapModel;
	}

	public static LCSClusterNodeUptimeSoap[] toSoapModels(
		LCSClusterNodeUptime[] models) {
		LCSClusterNodeUptimeSoap[] soapModels = new LCSClusterNodeUptimeSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LCSClusterNodeUptimeSoap[][] toSoapModels(
		LCSClusterNodeUptime[][] models) {
		LCSClusterNodeUptimeSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new LCSClusterNodeUptimeSoap[models.length][models[0].length];
		}
		else {
			soapModels = new LCSClusterNodeUptimeSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LCSClusterNodeUptimeSoap[] toSoapModels(
		List<LCSClusterNodeUptime> models) {
		List<LCSClusterNodeUptimeSoap> soapModels = new ArrayList<LCSClusterNodeUptimeSoap>(models.size());

		for (LCSClusterNodeUptime model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new LCSClusterNodeUptimeSoap[soapModels.size()]);
	}

	public LCSClusterNodeUptimeSoap() {
	}

	public long getPrimaryKey() {
		return _lcsClusterNodeUptimeId;
	}

	public void setPrimaryKey(long pk) {
		setLcsClusterNodeUptimeId(pk);
	}

	public long getLcsClusterNodeUptimeId() {
		return _lcsClusterNodeUptimeId;
	}

	public void setLcsClusterNodeUptimeId(long lcsClusterNodeUptimeId) {
		_lcsClusterNodeUptimeId = lcsClusterNodeUptimeId;
	}

	public long getLcsClusterNodeId() {
		return _lcsClusterNodeId;
	}

	public void setLcsClusterNodeId(long lcsClusterNodeId) {
		_lcsClusterNodeId = lcsClusterNodeId;
	}

	public long getStartTime() {
		return _startTime;
	}

	public void setStartTime(long startTime) {
		_startTime = startTime;
	}

	public long getEndTime() {
		return _endTime;
	}

	public void setEndTime(long endTime) {
		_endTime = endTime;
	}

	private long _lcsClusterNodeUptimeId;
	private long _lcsClusterNodeId;
	private long _startTime;
	private long _endTime;
}