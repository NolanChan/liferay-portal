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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.lcs.service.http.LCSClusterEntryServiceSoap}.
 *
 * @author Igor Beslic
 * @see com.liferay.osb.lcs.service.http.LCSClusterEntryServiceSoap
 * @generated
 */
@ProviderType
public class LCSClusterEntrySoap implements Serializable {
	public static LCSClusterEntrySoap toSoapModel(LCSClusterEntry model) {
		LCSClusterEntrySoap soapModel = new LCSClusterEntrySoap();

		soapModel.setLcsClusterEntryId(model.getLcsClusterEntryId());
		soapModel.setLcsProjectId(model.getLcsProjectId());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());
		soapModel.setElastic(model.getElastic());
		soapModel.setHighPageLoadTime(model.getHighPageLoadTime());
		soapModel.setLocation(model.getLocation());
		soapModel.setMediumPageLoadTime(model.getMediumPageLoadTime());
		soapModel.setSubscriptionType(model.getSubscriptionType());
		soapModel.setType(model.getType());
		soapModel.setArchived(model.getArchived());

		return soapModel;
	}

	public static LCSClusterEntrySoap[] toSoapModels(LCSClusterEntry[] models) {
		LCSClusterEntrySoap[] soapModels = new LCSClusterEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LCSClusterEntrySoap[][] toSoapModels(
		LCSClusterEntry[][] models) {
		LCSClusterEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new LCSClusterEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new LCSClusterEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LCSClusterEntrySoap[] toSoapModels(
		List<LCSClusterEntry> models) {
		List<LCSClusterEntrySoap> soapModels = new ArrayList<LCSClusterEntrySoap>(models.size());

		for (LCSClusterEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new LCSClusterEntrySoap[soapModels.size()]);
	}

	public LCSClusterEntrySoap() {
	}

	public long getPrimaryKey() {
		return _lcsClusterEntryId;
	}

	public void setPrimaryKey(long pk) {
		setLcsClusterEntryId(pk);
	}

	public long getLcsClusterEntryId() {
		return _lcsClusterEntryId;
	}

	public void setLcsClusterEntryId(long lcsClusterEntryId) {
		_lcsClusterEntryId = lcsClusterEntryId;
	}

	public long getLcsProjectId() {
		return _lcsProjectId;
	}

	public void setLcsProjectId(long lcsProjectId) {
		_lcsProjectId = lcsProjectId;
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

	public boolean getElastic() {
		return _elastic;
	}

	public boolean isElastic() {
		return _elastic;
	}

	public void setElastic(boolean elastic) {
		_elastic = elastic;
	}

	public int getHighPageLoadTime() {
		return _highPageLoadTime;
	}

	public void setHighPageLoadTime(int highPageLoadTime) {
		_highPageLoadTime = highPageLoadTime;
	}

	public String getLocation() {
		return _location;
	}

	public void setLocation(String location) {
		_location = location;
	}

	public int getMediumPageLoadTime() {
		return _mediumPageLoadTime;
	}

	public void setMediumPageLoadTime(int mediumPageLoadTime) {
		_mediumPageLoadTime = mediumPageLoadTime;
	}

	public String getSubscriptionType() {
		return _subscriptionType;
	}

	public void setSubscriptionType(String subscriptionType) {
		_subscriptionType = subscriptionType;
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
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

	private long _lcsClusterEntryId;
	private long _lcsProjectId;
	private String _name;
	private String _description;
	private boolean _elastic;
	private int _highPageLoadTime;
	private String _location;
	private int _mediumPageLoadTime;
	private String _subscriptionType;
	private int _type;
	private boolean _archived;
}