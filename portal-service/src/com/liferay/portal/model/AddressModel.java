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

package com.liferay.portal.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the Address service. Represents a row in the &quot;Address&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portal.model.impl.AddressModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portal.model.impl.AddressImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Address
 * @see com.liferay.portal.model.impl.AddressImpl
 * @see com.liferay.portal.model.impl.AddressModelImpl
 * @generated
 */
@ProviderType
public interface AddressModel extends AttachedModel, BaseModel<Address>,
	MVCCModel, PartitionedModel, StagedAuditedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a address model instance should use the {@link Address} interface instead.
	 */

	/**
	 * Returns the primary key of this address.
	 *
	 * @return the primary key of this address
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this address.
	 *
	 * @param primaryKey the primary key of this address
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this address.
	 *
	 * @return the mvcc version of this address
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this address.
	 *
	 * @param mvccVersion the mvcc version of this address
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the uuid of this address.
	 *
	 * @return the uuid of this address
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this address.
	 *
	 * @param uuid the uuid of this address
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the address ID of this address.
	 *
	 * @return the address ID of this address
	 */
	public long getAddressId();

	/**
	 * Sets the address ID of this address.
	 *
	 * @param addressId the address ID of this address
	 */
	public void setAddressId(long addressId);

	/**
	 * Returns the company ID of this address.
	 *
	 * @return the company ID of this address
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this address.
	 *
	 * @param companyId the company ID of this address
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this address.
	 *
	 * @return the user ID of this address
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this address.
	 *
	 * @param userId the user ID of this address
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this address.
	 *
	 * @return the user uuid of this address
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this address.
	 *
	 * @param userUuid the user uuid of this address
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this address.
	 *
	 * @return the user name of this address
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this address.
	 *
	 * @param userName the user name of this address
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this address.
	 *
	 * @return the create date of this address
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this address.
	 *
	 * @param createDate the create date of this address
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this address.
	 *
	 * @return the modified date of this address
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this address.
	 *
	 * @param modifiedDate the modified date of this address
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the fully qualified class name of this address.
	 *
	 * @return the fully qualified class name of this address
	 */
	@Override
	public String getClassName();

	public void setClassName(String className);

	/**
	 * Returns the class name ID of this address.
	 *
	 * @return the class name ID of this address
	 */
	@Override
	public long getClassNameId();

	/**
	 * Sets the class name ID of this address.
	 *
	 * @param classNameId the class name ID of this address
	 */
	@Override
	public void setClassNameId(long classNameId);

	/**
	 * Returns the class p k of this address.
	 *
	 * @return the class p k of this address
	 */
	@Override
	public long getClassPK();

	/**
	 * Sets the class p k of this address.
	 *
	 * @param classPK the class p k of this address
	 */
	@Override
	public void setClassPK(long classPK);

	/**
	 * Returns the street1 of this address.
	 *
	 * @return the street1 of this address
	 */
	@AutoEscape
	public String getStreet1();

	/**
	 * Sets the street1 of this address.
	 *
	 * @param street1 the street1 of this address
	 */
	public void setStreet1(String street1);

	/**
	 * Returns the street2 of this address.
	 *
	 * @return the street2 of this address
	 */
	@AutoEscape
	public String getStreet2();

	/**
	 * Sets the street2 of this address.
	 *
	 * @param street2 the street2 of this address
	 */
	public void setStreet2(String street2);

	/**
	 * Returns the street3 of this address.
	 *
	 * @return the street3 of this address
	 */
	@AutoEscape
	public String getStreet3();

	/**
	 * Sets the street3 of this address.
	 *
	 * @param street3 the street3 of this address
	 */
	public void setStreet3(String street3);

	/**
	 * Returns the city of this address.
	 *
	 * @return the city of this address
	 */
	@AutoEscape
	public String getCity();

	/**
	 * Sets the city of this address.
	 *
	 * @param city the city of this address
	 */
	public void setCity(String city);

	/**
	 * Returns the zip of this address.
	 *
	 * @return the zip of this address
	 */
	@AutoEscape
	public String getZip();

	/**
	 * Sets the zip of this address.
	 *
	 * @param zip the zip of this address
	 */
	public void setZip(String zip);

	/**
	 * Returns the region ID of this address.
	 *
	 * @return the region ID of this address
	 */
	public long getRegionId();

	/**
	 * Sets the region ID of this address.
	 *
	 * @param regionId the region ID of this address
	 */
	public void setRegionId(long regionId);

	/**
	 * Returns the country ID of this address.
	 *
	 * @return the country ID of this address
	 */
	public long getCountryId();

	/**
	 * Sets the country ID of this address.
	 *
	 * @param countryId the country ID of this address
	 */
	public void setCountryId(long countryId);

	/**
	 * Returns the type ID of this address.
	 *
	 * @return the type ID of this address
	 */
	public long getTypeId();

	/**
	 * Sets the type ID of this address.
	 *
	 * @param typeId the type ID of this address
	 */
	public void setTypeId(long typeId);

	/**
	 * Returns the mailing of this address.
	 *
	 * @return the mailing of this address
	 */
	public boolean getMailing();

	/**
	 * Returns <code>true</code> if this address is mailing.
	 *
	 * @return <code>true</code> if this address is mailing; <code>false</code> otherwise
	 */
	public boolean isMailing();

	/**
	 * Sets whether this address is mailing.
	 *
	 * @param mailing the mailing of this address
	 */
	public void setMailing(boolean mailing);

	/**
	 * Returns the primary of this address.
	 *
	 * @return the primary of this address
	 */
	public boolean getPrimary();

	/**
	 * Returns <code>true</code> if this address is primary.
	 *
	 * @return <code>true</code> if this address is primary; <code>false</code> otherwise
	 */
	public boolean isPrimary();

	/**
	 * Sets whether this address is primary.
	 *
	 * @param primary the primary of this address
	 */
	public void setPrimary(boolean primary);

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
	public int compareTo(com.liferay.portal.model.Address address);

	@Override
	public int hashCode();

	@Override
	public CacheModel<com.liferay.portal.model.Address> toCacheModel();

	@Override
	public com.liferay.portal.model.Address toEscapedModel();

	@Override
	public com.liferay.portal.model.Address toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}