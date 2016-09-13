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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the LCSSubscriptionEntry service. Represents a row in the &quot;OSBLCS_LCSSubscriptionEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.osb.lcs.model.impl.LCSSubscriptionEntryModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.osb.lcs.model.impl.LCSSubscriptionEntryImpl}.
 * </p>
 *
 * @author Igor Beslic
 * @see LCSSubscriptionEntry
 * @see com.liferay.osb.lcs.model.impl.LCSSubscriptionEntryImpl
 * @see com.liferay.osb.lcs.model.impl.LCSSubscriptionEntryModelImpl
 * @generated
 */
@ProviderType
public interface LCSSubscriptionEntryModel extends BaseModel<LCSSubscriptionEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a l c s subscription entry model instance should use the {@link LCSSubscriptionEntry} interface instead.
	 */

	/**
	 * Returns the primary key of this l c s subscription entry.
	 *
	 * @return the primary key of this l c s subscription entry
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this l c s subscription entry.
	 *
	 * @param primaryKey the primary key of this l c s subscription entry
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the lcs subscription entry ID of this l c s subscription entry.
	 *
	 * @return the lcs subscription entry ID of this l c s subscription entry
	 */
	public long getLcsSubscriptionEntryId();

	/**
	 * Sets the lcs subscription entry ID of this l c s subscription entry.
	 *
	 * @param lcsSubscriptionEntryId the lcs subscription entry ID of this l c s subscription entry
	 */
	public void setLcsSubscriptionEntryId(long lcsSubscriptionEntryId);

	/**
	 * Returns the lcs project ID of this l c s subscription entry.
	 *
	 * @return the lcs project ID of this l c s subscription entry
	 */
	public long getLcsProjectId();

	/**
	 * Sets the lcs project ID of this l c s subscription entry.
	 *
	 * @param lcsProjectId the lcs project ID of this l c s subscription entry
	 */
	public void setLcsProjectId(long lcsProjectId);

	/**
	 * Returns the actual price of this l c s subscription entry.
	 *
	 * @return the actual price of this l c s subscription entry
	 */
	public double getActualPrice();

	/**
	 * Sets the actual price of this l c s subscription entry.
	 *
	 * @param actualPrice the actual price of this l c s subscription entry
	 */
	public void setActualPrice(double actualPrice);

	/**
	 * Returns the currency code of this l c s subscription entry.
	 *
	 * @return the currency code of this l c s subscription entry
	 */
	@AutoEscape
	public String getCurrencyCode();

	/**
	 * Sets the currency code of this l c s subscription entry.
	 *
	 * @param currencyCode the currency code of this l c s subscription entry
	 */
	public void setCurrencyCode(String currencyCode);

	/**
	 * Returns the instance size of this l c s subscription entry.
	 *
	 * @return the instance size of this l c s subscription entry
	 */
	public int getInstanceSize();

	/**
	 * Sets the instance size of this l c s subscription entry.
	 *
	 * @param instanceSize the instance size of this l c s subscription entry
	 */
	public void setInstanceSize(int instanceSize);

	/**
	 * Returns the type of this l c s subscription entry.
	 *
	 * @return the type of this l c s subscription entry
	 */
	@AutoEscape
	public String getType();

	/**
	 * Sets the type of this l c s subscription entry.
	 *
	 * @param type the type of this l c s subscription entry
	 */
	public void setType(String type);

	/**
	 * Returns the platform of this l c s subscription entry.
	 *
	 * @return the platform of this l c s subscription entry
	 */
	@AutoEscape
	public String getPlatform();

	/**
	 * Sets the platform of this l c s subscription entry.
	 *
	 * @param platform the platform of this l c s subscription entry
	 */
	public void setPlatform(String platform);

	/**
	 * Returns the platform version of this l c s subscription entry.
	 *
	 * @return the platform version of this l c s subscription entry
	 */
	public int getPlatformVersion();

	/**
	 * Sets the platform version of this l c s subscription entry.
	 *
	 * @param platformVersion the platform version of this l c s subscription entry
	 */
	public void setPlatformVersion(int platformVersion);

	/**
	 * Returns the processor cores allowed of this l c s subscription entry.
	 *
	 * @return the processor cores allowed of this l c s subscription entry
	 */
	public int getProcessorCoresAllowed();

	/**
	 * Sets the processor cores allowed of this l c s subscription entry.
	 *
	 * @param processorCoresAllowed the processor cores allowed of this l c s subscription entry
	 */
	public void setProcessorCoresAllowed(int processorCoresAllowed);

	/**
	 * Returns the product of this l c s subscription entry.
	 *
	 * @return the product of this l c s subscription entry
	 */
	@AutoEscape
	public String getProduct();

	/**
	 * Sets the product of this l c s subscription entry.
	 *
	 * @param product the product of this l c s subscription entry
	 */
	public void setProduct(String product);

	/**
	 * Returns the product version of this l c s subscription entry.
	 *
	 * @return the product version of this l c s subscription entry
	 */
	public int getProductVersion();

	/**
	 * Sets the product version of this l c s subscription entry.
	 *
	 * @param productVersion the product version of this l c s subscription entry
	 */
	public void setProductVersion(int productVersion);

	/**
	 * Returns the servers allowed of this l c s subscription entry.
	 *
	 * @return the servers allowed of this l c s subscription entry
	 */
	public int getServersAllowed();

	/**
	 * Sets the servers allowed of this l c s subscription entry.
	 *
	 * @param serversAllowed the servers allowed of this l c s subscription entry
	 */
	public void setServersAllowed(int serversAllowed);

	/**
	 * Returns the servers used of this l c s subscription entry.
	 *
	 * @return the servers used of this l c s subscription entry
	 */
	public int getServersUsed();

	/**
	 * Sets the servers used of this l c s subscription entry.
	 *
	 * @param serversUsed the servers used of this l c s subscription entry
	 */
	public void setServersUsed(int serversUsed);

	/**
	 * Returns the start date of this l c s subscription entry.
	 *
	 * @return the start date of this l c s subscription entry
	 */
	public Date getStartDate();

	/**
	 * Sets the start date of this l c s subscription entry.
	 *
	 * @param startDate the start date of this l c s subscription entry
	 */
	public void setStartDate(Date startDate);

	/**
	 * Returns the end date of this l c s subscription entry.
	 *
	 * @return the end date of this l c s subscription entry
	 */
	public Date getEndDate();

	/**
	 * Sets the end date of this l c s subscription entry.
	 *
	 * @param endDate the end date of this l c s subscription entry
	 */
	public void setEndDate(Date endDate);

	/**
	 * Returns the support start date of this l c s subscription entry.
	 *
	 * @return the support start date of this l c s subscription entry
	 */
	public Date getSupportStartDate();

	/**
	 * Sets the support start date of this l c s subscription entry.
	 *
	 * @param supportStartDate the support start date of this l c s subscription entry
	 */
	public void setSupportStartDate(Date supportStartDate);

	/**
	 * Returns the support end date of this l c s subscription entry.
	 *
	 * @return the support end date of this l c s subscription entry
	 */
	public Date getSupportEndDate();

	/**
	 * Sets the support end date of this l c s subscription entry.
	 *
	 * @param supportEndDate the support end date of this l c s subscription entry
	 */
	public void setSupportEndDate(Date supportEndDate);

	/**
	 * Returns the active of this l c s subscription entry.
	 *
	 * @return the active of this l c s subscription entry
	 */
	public boolean getActive();

	/**
	 * Returns <code>true</code> if this l c s subscription entry is active.
	 *
	 * @return <code>true</code> if this l c s subscription entry is active; <code>false</code> otherwise
	 */
	public boolean isActive();

	/**
	 * Sets whether this l c s subscription entry is active.
	 *
	 * @param active the active of this l c s subscription entry
	 */
	public void setActive(boolean active);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(LCSSubscriptionEntry lcsSubscriptionEntry);

	@Override
	public int hashCode();

	@Override
	public CacheModel<LCSSubscriptionEntry> toCacheModel();

	@Override
	public LCSSubscriptionEntry toEscapedModel();

	@Override
	public LCSSubscriptionEntry toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}