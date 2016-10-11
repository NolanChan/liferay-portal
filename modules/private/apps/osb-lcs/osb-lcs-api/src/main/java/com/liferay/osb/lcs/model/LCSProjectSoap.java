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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.lcs.service.http.LCSProjectServiceSoap}.
 *
 * @author Igor Beslic
 * @see com.liferay.osb.lcs.service.http.LCSProjectServiceSoap
 * @generated
 */
@ProviderType
public class LCSProjectSoap implements Serializable {
	public static LCSProjectSoap toSoapModel(LCSProject model) {
		LCSProjectSoap soapModel = new LCSProjectSoap();

		soapModel.setLcsProjectId(model.getLcsProjectId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setSourceSystemName(model.getSourceSystemName());
		soapModel.setName(model.getName());
		soapModel.setOrganizationId(model.getOrganizationId());
		soapModel.setAddressId(model.getAddressId());
		soapModel.setAccountEntryId(model.getAccountEntryId());
		soapModel.setCorpProjectId(model.getCorpProjectId());
		soapModel.setContactEmailAddress(model.getContactEmailAddress());
		soapModel.setPhoneNumber(model.getPhoneNumber());
		soapModel.setSubscriptionActive(model.getSubscriptionActive());
		soapModel.setArchived(model.getArchived());

		return soapModel;
	}

	public static LCSProjectSoap[] toSoapModels(LCSProject[] models) {
		LCSProjectSoap[] soapModels = new LCSProjectSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LCSProjectSoap[][] toSoapModels(LCSProject[][] models) {
		LCSProjectSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new LCSProjectSoap[models.length][models[0].length];
		}
		else {
			soapModels = new LCSProjectSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LCSProjectSoap[] toSoapModels(List<LCSProject> models) {
		List<LCSProjectSoap> soapModels = new ArrayList<LCSProjectSoap>(models.size());

		for (LCSProject model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new LCSProjectSoap[soapModels.size()]);
	}

	public LCSProjectSoap() {
	}

	public long getPrimaryKey() {
		return _lcsProjectId;
	}

	public void setPrimaryKey(long pk) {
		setLcsProjectId(pk);
	}

	public long getLcsProjectId() {
		return _lcsProjectId;
	}

	public void setLcsProjectId(long lcsProjectId) {
		_lcsProjectId = lcsProjectId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getSourceSystemName() {
		return _sourceSystemName;
	}

	public void setSourceSystemName(String sourceSystemName) {
		_sourceSystemName = sourceSystemName;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public long getOrganizationId() {
		return _organizationId;
	}

	public void setOrganizationId(long organizationId) {
		_organizationId = organizationId;
	}

	public long getAddressId() {
		return _addressId;
	}

	public void setAddressId(long addressId) {
		_addressId = addressId;
	}

	public long getAccountEntryId() {
		return _accountEntryId;
	}

	public void setAccountEntryId(long accountEntryId) {
		_accountEntryId = accountEntryId;
	}

	public long getCorpProjectId() {
		return _corpProjectId;
	}

	public void setCorpProjectId(long corpProjectId) {
		_corpProjectId = corpProjectId;
	}

	public String getContactEmailAddress() {
		return _contactEmailAddress;
	}

	public void setContactEmailAddress(String contactEmailAddress) {
		_contactEmailAddress = contactEmailAddress;
	}

	public String getPhoneNumber() {
		return _phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		_phoneNumber = phoneNumber;
	}

	public boolean getSubscriptionActive() {
		return _subscriptionActive;
	}

	public boolean isSubscriptionActive() {
		return _subscriptionActive;
	}

	public void setSubscriptionActive(boolean subscriptionActive) {
		_subscriptionActive = subscriptionActive;
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

	private long _lcsProjectId;
	private Date _createDate;
	private Date _modifiedDate;
	private String _sourceSystemName;
	private String _name;
	private long _organizationId;
	private long _addressId;
	private long _accountEntryId;
	private long _corpProjectId;
	private String _contactEmailAddress;
	private String _phoneNumber;
	private boolean _subscriptionActive;
	private boolean _archived;
}