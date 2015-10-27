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

import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

/**
 * The base model interface for the OrgLabor service. Represents a row in the &quot;OrgLabor&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portal.model.impl.OrgLaborModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portal.model.impl.OrgLaborImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OrgLabor
 * @see com.liferay.portal.model.impl.OrgLaborImpl
 * @see com.liferay.portal.model.impl.OrgLaborModelImpl
 * @generated
 */
@ProviderType
public interface OrgLaborModel extends BaseModel<OrgLabor>, MVCCModel,
	ShardedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a org labor model instance should use the {@link OrgLabor} interface instead.
	 */

	/**
	 * Returns the primary key of this org labor.
	 *
	 * @return the primary key of this org labor
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this org labor.
	 *
	 * @param primaryKey the primary key of this org labor
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this org labor.
	 *
	 * @return the mvcc version of this org labor
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this org labor.
	 *
	 * @param mvccVersion the mvcc version of this org labor
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the org labor ID of this org labor.
	 *
	 * @return the org labor ID of this org labor
	 */
	public long getOrgLaborId();

	/**
	 * Sets the org labor ID of this org labor.
	 *
	 * @param orgLaborId the org labor ID of this org labor
	 */
	public void setOrgLaborId(long orgLaborId);

	/**
	 * Returns the company ID of this org labor.
	 *
	 * @return the company ID of this org labor
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this org labor.
	 *
	 * @param companyId the company ID of this org labor
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the organization ID of this org labor.
	 *
	 * @return the organization ID of this org labor
	 */
	public long getOrganizationId();

	/**
	 * Sets the organization ID of this org labor.
	 *
	 * @param organizationId the organization ID of this org labor
	 */
	public void setOrganizationId(long organizationId);

	/**
	 * Returns the type ID of this org labor.
	 *
	 * @return the type ID of this org labor
	 */
	public long getTypeId();

	/**
	 * Sets the type ID of this org labor.
	 *
	 * @param typeId the type ID of this org labor
	 */
	public void setTypeId(long typeId);

	/**
	 * Returns the sun open of this org labor.
	 *
	 * @return the sun open of this org labor
	 */
	public int getSunOpen();

	/**
	 * Sets the sun open of this org labor.
	 *
	 * @param sunOpen the sun open of this org labor
	 */
	public void setSunOpen(int sunOpen);

	/**
	 * Returns the sun close of this org labor.
	 *
	 * @return the sun close of this org labor
	 */
	public int getSunClose();

	/**
	 * Sets the sun close of this org labor.
	 *
	 * @param sunClose the sun close of this org labor
	 */
	public void setSunClose(int sunClose);

	/**
	 * Returns the mon open of this org labor.
	 *
	 * @return the mon open of this org labor
	 */
	public int getMonOpen();

	/**
	 * Sets the mon open of this org labor.
	 *
	 * @param monOpen the mon open of this org labor
	 */
	public void setMonOpen(int monOpen);

	/**
	 * Returns the mon close of this org labor.
	 *
	 * @return the mon close of this org labor
	 */
	public int getMonClose();

	/**
	 * Sets the mon close of this org labor.
	 *
	 * @param monClose the mon close of this org labor
	 */
	public void setMonClose(int monClose);

	/**
	 * Returns the tue open of this org labor.
	 *
	 * @return the tue open of this org labor
	 */
	public int getTueOpen();

	/**
	 * Sets the tue open of this org labor.
	 *
	 * @param tueOpen the tue open of this org labor
	 */
	public void setTueOpen(int tueOpen);

	/**
	 * Returns the tue close of this org labor.
	 *
	 * @return the tue close of this org labor
	 */
	public int getTueClose();

	/**
	 * Sets the tue close of this org labor.
	 *
	 * @param tueClose the tue close of this org labor
	 */
	public void setTueClose(int tueClose);

	/**
	 * Returns the wed open of this org labor.
	 *
	 * @return the wed open of this org labor
	 */
	public int getWedOpen();

	/**
	 * Sets the wed open of this org labor.
	 *
	 * @param wedOpen the wed open of this org labor
	 */
	public void setWedOpen(int wedOpen);

	/**
	 * Returns the wed close of this org labor.
	 *
	 * @return the wed close of this org labor
	 */
	public int getWedClose();

	/**
	 * Sets the wed close of this org labor.
	 *
	 * @param wedClose the wed close of this org labor
	 */
	public void setWedClose(int wedClose);

	/**
	 * Returns the thu open of this org labor.
	 *
	 * @return the thu open of this org labor
	 */
	public int getThuOpen();

	/**
	 * Sets the thu open of this org labor.
	 *
	 * @param thuOpen the thu open of this org labor
	 */
	public void setThuOpen(int thuOpen);

	/**
	 * Returns the thu close of this org labor.
	 *
	 * @return the thu close of this org labor
	 */
	public int getThuClose();

	/**
	 * Sets the thu close of this org labor.
	 *
	 * @param thuClose the thu close of this org labor
	 */
	public void setThuClose(int thuClose);

	/**
	 * Returns the fri open of this org labor.
	 *
	 * @return the fri open of this org labor
	 */
	public int getFriOpen();

	/**
	 * Sets the fri open of this org labor.
	 *
	 * @param friOpen the fri open of this org labor
	 */
	public void setFriOpen(int friOpen);

	/**
	 * Returns the fri close of this org labor.
	 *
	 * @return the fri close of this org labor
	 */
	public int getFriClose();

	/**
	 * Sets the fri close of this org labor.
	 *
	 * @param friClose the fri close of this org labor
	 */
	public void setFriClose(int friClose);

	/**
	 * Returns the sat open of this org labor.
	 *
	 * @return the sat open of this org labor
	 */
	public int getSatOpen();

	/**
	 * Sets the sat open of this org labor.
	 *
	 * @param satOpen the sat open of this org labor
	 */
	public void setSatOpen(int satOpen);

	/**
	 * Returns the sat close of this org labor.
	 *
	 * @return the sat close of this org labor
	 */
	public int getSatClose();

	/**
	 * Sets the sat close of this org labor.
	 *
	 * @param satClose the sat close of this org labor
	 */
	public void setSatClose(int satClose);

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
	public int compareTo(com.liferay.portal.model.OrgLabor orgLabor);

	@Override
	public int hashCode();

	@Override
	public CacheModel<com.liferay.portal.model.OrgLabor> toCacheModel();

	@Override
	public com.liferay.portal.model.OrgLabor toEscapedModel();

	@Override
	public com.liferay.portal.model.OrgLabor toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}