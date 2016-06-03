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

package com.liferay.saml.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the SamlSpAuthRequest service. Represents a row in the &quot;SamlSpAuthRequest&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.saml.model.impl.SamlSpAuthRequestModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.saml.model.impl.SamlSpAuthRequestImpl}.
 * </p>
 *
 * @author Mika Koivisto
 * @see SamlSpAuthRequest
 * @see com.liferay.saml.model.impl.SamlSpAuthRequestImpl
 * @see com.liferay.saml.model.impl.SamlSpAuthRequestModelImpl
 * @generated
 */
@ProviderType
public interface SamlSpAuthRequestModel extends BaseModel<SamlSpAuthRequest>,
	ShardedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a saml sp auth request model instance should use the {@link SamlSpAuthRequest} interface instead.
	 */

	/**
	 * Returns the primary key of this saml sp auth request.
	 *
	 * @return the primary key of this saml sp auth request
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this saml sp auth request.
	 *
	 * @param primaryKey the primary key of this saml sp auth request
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the saml sp authn request ID of this saml sp auth request.
	 *
	 * @return the saml sp authn request ID of this saml sp auth request
	 */
	public long getSamlSpAuthnRequestId();

	/**
	 * Sets the saml sp authn request ID of this saml sp auth request.
	 *
	 * @param samlSpAuthnRequestId the saml sp authn request ID of this saml sp auth request
	 */
	public void setSamlSpAuthnRequestId(long samlSpAuthnRequestId);

	/**
	 * Returns the company ID of this saml sp auth request.
	 *
	 * @return the company ID of this saml sp auth request
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this saml sp auth request.
	 *
	 * @param companyId the company ID of this saml sp auth request
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the create date of this saml sp auth request.
	 *
	 * @return the create date of this saml sp auth request
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this saml sp auth request.
	 *
	 * @param createDate the create date of this saml sp auth request
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Returns the saml idp entity ID of this saml sp auth request.
	 *
	 * @return the saml idp entity ID of this saml sp auth request
	 */
	@AutoEscape
	public String getSamlIdpEntityId();

	/**
	 * Sets the saml idp entity ID of this saml sp auth request.
	 *
	 * @param samlIdpEntityId the saml idp entity ID of this saml sp auth request
	 */
	public void setSamlIdpEntityId(String samlIdpEntityId);

	/**
	 * Returns the saml sp auth request key of this saml sp auth request.
	 *
	 * @return the saml sp auth request key of this saml sp auth request
	 */
	@AutoEscape
	public String getSamlSpAuthRequestKey();

	/**
	 * Sets the saml sp auth request key of this saml sp auth request.
	 *
	 * @param samlSpAuthRequestKey the saml sp auth request key of this saml sp auth request
	 */
	public void setSamlSpAuthRequestKey(String samlSpAuthRequestKey);

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
	public int compareTo(SamlSpAuthRequest samlSpAuthRequest);

	@Override
	public int hashCode();

	@Override
	public CacheModel<SamlSpAuthRequest> toCacheModel();

	@Override
	public SamlSpAuthRequest toEscapedModel();

	@Override
	public SamlSpAuthRequest toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}