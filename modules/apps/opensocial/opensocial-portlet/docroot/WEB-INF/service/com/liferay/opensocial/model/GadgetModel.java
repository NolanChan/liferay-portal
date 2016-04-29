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

package com.liferay.opensocial.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the Gadget service. Represents a row in the &quot;OpenSocial_Gadget&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.opensocial.model.impl.GadgetModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.opensocial.model.impl.GadgetImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Gadget
 * @see com.liferay.opensocial.model.impl.GadgetImpl
 * @see com.liferay.opensocial.model.impl.GadgetModelImpl
 * @generated
 */
@ProviderType
public interface GadgetModel extends BaseModel<Gadget>, ShardedModel, StagedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a gadget model instance should use the {@link Gadget} interface instead.
	 */

	/**
	 * Returns the primary key of this gadget.
	 *
	 * @return the primary key of this gadget
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this gadget.
	 *
	 * @param primaryKey the primary key of this gadget
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this gadget.
	 *
	 * @return the uuid of this gadget
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this gadget.
	 *
	 * @param uuid the uuid of this gadget
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the gadget ID of this gadget.
	 *
	 * @return the gadget ID of this gadget
	 */
	public long getGadgetId();

	/**
	 * Sets the gadget ID of this gadget.
	 *
	 * @param gadgetId the gadget ID of this gadget
	 */
	public void setGadgetId(long gadgetId);

	/**
	 * Returns the company ID of this gadget.
	 *
	 * @return the company ID of this gadget
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this gadget.
	 *
	 * @param companyId the company ID of this gadget
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the create date of this gadget.
	 *
	 * @return the create date of this gadget
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this gadget.
	 *
	 * @param createDate the create date of this gadget
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this gadget.
	 *
	 * @return the modified date of this gadget
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this gadget.
	 *
	 * @param modifiedDate the modified date of this gadget
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the name of this gadget.
	 *
	 * @return the name of this gadget
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this gadget.
	 *
	 * @param name the name of this gadget
	 */
	public void setName(String name);

	/**
	 * Returns the url of this gadget.
	 *
	 * @return the url of this gadget
	 */
	@AutoEscape
	public String getUrl();

	/**
	 * Sets the url of this gadget.
	 *
	 * @param url the url of this gadget
	 */
	public void setUrl(String url);

	/**
	 * Returns the portlet category names of this gadget.
	 *
	 * @return the portlet category names of this gadget
	 */
	@AutoEscape
	public String getPortletCategoryNames();

	/**
	 * Sets the portlet category names of this gadget.
	 *
	 * @param portletCategoryNames the portlet category names of this gadget
	 */
	public void setPortletCategoryNames(String portletCategoryNames);

	/**
	 * Returns the last publish date of this gadget.
	 *
	 * @return the last publish date of this gadget
	 */
	public Date getLastPublishDate();

	/**
	 * Sets the last publish date of this gadget.
	 *
	 * @param lastPublishDate the last publish date of this gadget
	 */
	public void setLastPublishDate(Date lastPublishDate);

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
	public int compareTo(com.liferay.opensocial.model.Gadget gadget);

	@Override
	public int hashCode();

	@Override
	public CacheModel<com.liferay.opensocial.model.Gadget> toCacheModel();

	@Override
	public com.liferay.opensocial.model.Gadget toEscapedModel();

	@Override
	public com.liferay.opensocial.model.Gadget toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}