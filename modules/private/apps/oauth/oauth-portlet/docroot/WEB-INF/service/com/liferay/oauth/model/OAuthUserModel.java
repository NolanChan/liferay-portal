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

package com.liferay.oauth.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.AuditedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the OAuthUser service. Represents a row in the &quot;OAuth_OAuthUser&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.oauth.model.impl.OAuthUserModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.oauth.model.impl.OAuthUserImpl}.
 * </p>
 *
 * @author Ivica Cardic
 * @see OAuthUser
 * @see com.liferay.oauth.model.impl.OAuthUserImpl
 * @see com.liferay.oauth.model.impl.OAuthUserModelImpl
 * @generated
 */
@ProviderType
public interface OAuthUserModel extends AuditedModel, BaseModel<OAuthUser>,
	ShardedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a o auth user model instance should use the {@link OAuthUser} interface instead.
	 */

	/**
	 * Returns the primary key of this o auth user.
	 *
	 * @return the primary key of this o auth user
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this o auth user.
	 *
	 * @param primaryKey the primary key of this o auth user
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the o auth user ID of this o auth user.
	 *
	 * @return the o auth user ID of this o auth user
	 */
	public long getOAuthUserId();

	/**
	 * Sets the o auth user ID of this o auth user.
	 *
	 * @param oAuthUserId the o auth user ID of this o auth user
	 */
	public void setOAuthUserId(long oAuthUserId);

	/**
	 * Returns the o auth user uuid of this o auth user.
	 *
	 * @return the o auth user uuid of this o auth user
	 */
	public String getOAuthUserUuid();

	/**
	 * Sets the o auth user uuid of this o auth user.
	 *
	 * @param oAuthUserUuid the o auth user uuid of this o auth user
	 */
	public void setOAuthUserUuid(String oAuthUserUuid);

	/**
	 * Returns the company ID of this o auth user.
	 *
	 * @return the company ID of this o auth user
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this o auth user.
	 *
	 * @param companyId the company ID of this o auth user
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this o auth user.
	 *
	 * @return the user ID of this o auth user
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this o auth user.
	 *
	 * @param userId the user ID of this o auth user
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this o auth user.
	 *
	 * @return the user uuid of this o auth user
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this o auth user.
	 *
	 * @param userUuid the user uuid of this o auth user
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this o auth user.
	 *
	 * @return the user name of this o auth user
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this o auth user.
	 *
	 * @param userName the user name of this o auth user
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this o auth user.
	 *
	 * @return the create date of this o auth user
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this o auth user.
	 *
	 * @param createDate the create date of this o auth user
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this o auth user.
	 *
	 * @return the modified date of this o auth user
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this o auth user.
	 *
	 * @param modifiedDate the modified date of this o auth user
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the o auth application ID of this o auth user.
	 *
	 * @return the o auth application ID of this o auth user
	 */
	public long getOAuthApplicationId();

	/**
	 * Sets the o auth application ID of this o auth user.
	 *
	 * @param oAuthApplicationId the o auth application ID of this o auth user
	 */
	public void setOAuthApplicationId(long oAuthApplicationId);

	/**
	 * Returns the access token of this o auth user.
	 *
	 * @return the access token of this o auth user
	 */
	@AutoEscape
	public String getAccessToken();

	/**
	 * Sets the access token of this o auth user.
	 *
	 * @param accessToken the access token of this o auth user
	 */
	public void setAccessToken(String accessToken);

	/**
	 * Returns the access secret of this o auth user.
	 *
	 * @return the access secret of this o auth user
	 */
	@AutoEscape
	public String getAccessSecret();

	/**
	 * Sets the access secret of this o auth user.
	 *
	 * @param accessSecret the access secret of this o auth user
	 */
	public void setAccessSecret(String accessSecret);

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
	public int compareTo(com.liferay.oauth.model.OAuthUser oAuthUser);

	@Override
	public int hashCode();

	@Override
	public CacheModel<com.liferay.oauth.model.OAuthUser> toCacheModel();

	@Override
	public com.liferay.oauth.model.OAuthUser toEscapedModel();

	@Override
	public com.liferay.oauth.model.OAuthUser toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}