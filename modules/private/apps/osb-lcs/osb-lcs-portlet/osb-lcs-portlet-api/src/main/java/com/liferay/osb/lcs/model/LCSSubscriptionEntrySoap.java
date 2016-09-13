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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.lcs.service.http.LCSSubscriptionEntryServiceSoap}.
 *
 * @author Igor Beslic
 * @see com.liferay.osb.lcs.service.http.LCSSubscriptionEntryServiceSoap
 * @generated
 */
@ProviderType
public class LCSSubscriptionEntrySoap implements Serializable {
	public static LCSSubscriptionEntrySoap toSoapModel(
		LCSSubscriptionEntry model) {
		LCSSubscriptionEntrySoap soapModel = new LCSSubscriptionEntrySoap();

		soapModel.setLcsSubscriptionEntryId(model.getLcsSubscriptionEntryId());
		soapModel.setLcsProjectId(model.getLcsProjectId());
		soapModel.setActualPrice(model.getActualPrice());
		soapModel.setCurrencyCode(model.getCurrencyCode());
		soapModel.setInstanceSize(model.getInstanceSize());
		soapModel.setType(model.getType());
		soapModel.setPlatform(model.getPlatform());
		soapModel.setPlatformVersion(model.getPlatformVersion());
		soapModel.setProcessorCoresAllowed(model.getProcessorCoresAllowed());
		soapModel.setProduct(model.getProduct());
		soapModel.setProductVersion(model.getProductVersion());
		soapModel.setServersAllowed(model.getServersAllowed());
		soapModel.setServersUsed(model.getServersUsed());
		soapModel.setStartDate(model.getStartDate());
		soapModel.setEndDate(model.getEndDate());
		soapModel.setSupportStartDate(model.getSupportStartDate());
		soapModel.setSupportEndDate(model.getSupportEndDate());
		soapModel.setActive(model.getActive());

		return soapModel;
	}

	public static LCSSubscriptionEntrySoap[] toSoapModels(
		LCSSubscriptionEntry[] models) {
		LCSSubscriptionEntrySoap[] soapModels = new LCSSubscriptionEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LCSSubscriptionEntrySoap[][] toSoapModels(
		LCSSubscriptionEntry[][] models) {
		LCSSubscriptionEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new LCSSubscriptionEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new LCSSubscriptionEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LCSSubscriptionEntrySoap[] toSoapModels(
		List<LCSSubscriptionEntry> models) {
		List<LCSSubscriptionEntrySoap> soapModels = new ArrayList<LCSSubscriptionEntrySoap>(models.size());

		for (LCSSubscriptionEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new LCSSubscriptionEntrySoap[soapModels.size()]);
	}

	public LCSSubscriptionEntrySoap() {
	}

	public long getPrimaryKey() {
		return _lcsSubscriptionEntryId;
	}

	public void setPrimaryKey(long pk) {
		setLcsSubscriptionEntryId(pk);
	}

	public long getLcsSubscriptionEntryId() {
		return _lcsSubscriptionEntryId;
	}

	public void setLcsSubscriptionEntryId(long lcsSubscriptionEntryId) {
		_lcsSubscriptionEntryId = lcsSubscriptionEntryId;
	}

	public long getLcsProjectId() {
		return _lcsProjectId;
	}

	public void setLcsProjectId(long lcsProjectId) {
		_lcsProjectId = lcsProjectId;
	}

	public double getActualPrice() {
		return _actualPrice;
	}

	public void setActualPrice(double actualPrice) {
		_actualPrice = actualPrice;
	}

	public String getCurrencyCode() {
		return _currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		_currencyCode = currencyCode;
	}

	public int getInstanceSize() {
		return _instanceSize;
	}

	public void setInstanceSize(int instanceSize) {
		_instanceSize = instanceSize;
	}

	public String getType() {
		return _type;
	}

	public void setType(String type) {
		_type = type;
	}

	public String getPlatform() {
		return _platform;
	}

	public void setPlatform(String platform) {
		_platform = platform;
	}

	public int getPlatformVersion() {
		return _platformVersion;
	}

	public void setPlatformVersion(int platformVersion) {
		_platformVersion = platformVersion;
	}

	public int getProcessorCoresAllowed() {
		return _processorCoresAllowed;
	}

	public void setProcessorCoresAllowed(int processorCoresAllowed) {
		_processorCoresAllowed = processorCoresAllowed;
	}

	public String getProduct() {
		return _product;
	}

	public void setProduct(String product) {
		_product = product;
	}

	public int getProductVersion() {
		return _productVersion;
	}

	public void setProductVersion(int productVersion) {
		_productVersion = productVersion;
	}

	public int getServersAllowed() {
		return _serversAllowed;
	}

	public void setServersAllowed(int serversAllowed) {
		_serversAllowed = serversAllowed;
	}

	public int getServersUsed() {
		return _serversUsed;
	}

	public void setServersUsed(int serversUsed) {
		_serversUsed = serversUsed;
	}

	public Date getStartDate() {
		return _startDate;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	public Date getEndDate() {
		return _endDate;
	}

	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	public Date getSupportStartDate() {
		return _supportStartDate;
	}

	public void setSupportStartDate(Date supportStartDate) {
		_supportStartDate = supportStartDate;
	}

	public Date getSupportEndDate() {
		return _supportEndDate;
	}

	public void setSupportEndDate(Date supportEndDate) {
		_supportEndDate = supportEndDate;
	}

	public boolean getActive() {
		return _active;
	}

	public boolean isActive() {
		return _active;
	}

	public void setActive(boolean active) {
		_active = active;
	}

	private long _lcsSubscriptionEntryId;
	private long _lcsProjectId;
	private double _actualPrice;
	private String _currencyCode;
	private int _instanceSize;
	private String _type;
	private String _platform;
	private int _platformVersion;
	private int _processorCoresAllowed;
	private String _product;
	private int _productVersion;
	private int _serversAllowed;
	private int _serversUsed;
	private Date _startDate;
	private Date _endDate;
	private Date _supportStartDate;
	private Date _supportEndDate;
	private boolean _active;
}