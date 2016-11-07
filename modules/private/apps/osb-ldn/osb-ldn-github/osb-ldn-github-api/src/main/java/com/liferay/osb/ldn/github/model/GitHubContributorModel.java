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

package com.liferay.osb.ldn.github.model;

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
 * The base model interface for the GitHubContributor service. Represents a row in the &quot;OSB_LDN_GitHubContributor&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.osb.ldn.github.model.impl.GitHubContributorModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.osb.ldn.github.model.impl.GitHubContributorImpl}.
 * </p>
 *
 * @author Howie Chou
 * @see GitHubContributor
 * @see com.liferay.osb.ldn.github.model.impl.GitHubContributorImpl
 * @see com.liferay.osb.ldn.github.model.impl.GitHubContributorModelImpl
 * @generated
 */
@ProviderType
public interface GitHubContributorModel extends AuditedModel,
	BaseModel<GitHubContributor>, ShardedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a git hub contributor model instance should use the {@link GitHubContributor} interface instead.
	 */

	/**
	 * Returns the primary key of this git hub contributor.
	 *
	 * @return the primary key of this git hub contributor
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this git hub contributor.
	 *
	 * @param primaryKey the primary key of this git hub contributor
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the git hub contributor ID of this git hub contributor.
	 *
	 * @return the git hub contributor ID of this git hub contributor
	 */
	public long getGitHubContributorId();

	/**
	 * Sets the git hub contributor ID of this git hub contributor.
	 *
	 * @param gitHubContributorId the git hub contributor ID of this git hub contributor
	 */
	public void setGitHubContributorId(long gitHubContributorId);

	/**
	 * Returns the company ID of this git hub contributor.
	 *
	 * @return the company ID of this git hub contributor
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this git hub contributor.
	 *
	 * @param companyId the company ID of this git hub contributor
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this git hub contributor.
	 *
	 * @return the user ID of this git hub contributor
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this git hub contributor.
	 *
	 * @param userId the user ID of this git hub contributor
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this git hub contributor.
	 *
	 * @return the user uuid of this git hub contributor
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this git hub contributor.
	 *
	 * @param userUuid the user uuid of this git hub contributor
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this git hub contributor.
	 *
	 * @return the user name of this git hub contributor
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this git hub contributor.
	 *
	 * @param userName the user name of this git hub contributor
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this git hub contributor.
	 *
	 * @return the create date of this git hub contributor
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this git hub contributor.
	 *
	 * @param createDate the create date of this git hub contributor
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this git hub contributor.
	 *
	 * @return the modified date of this git hub contributor
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this git hub contributor.
	 *
	 * @param modifiedDate the modified date of this git hub contributor
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the git hub repository ID of this git hub contributor.
	 *
	 * @return the git hub repository ID of this git hub contributor
	 */
	public long getGitHubRepositoryId();

	/**
	 * Sets the git hub repository ID of this git hub contributor.
	 *
	 * @param gitHubRepositoryId the git hub repository ID of this git hub contributor
	 */
	public void setGitHubRepositoryId(long gitHubRepositoryId);

	/**
	 * Returns the name of this git hub contributor.
	 *
	 * @return the name of this git hub contributor
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this git hub contributor.
	 *
	 * @param name the name of this git hub contributor
	 */
	public void setName(String name);

	/**
	 * Returns the avatar u r l of this git hub contributor.
	 *
	 * @return the avatar u r l of this git hub contributor
	 */
	@AutoEscape
	public String getAvatarURL();

	/**
	 * Sets the avatar u r l of this git hub contributor.
	 *
	 * @param avatarURL the avatar u r l of this git hub contributor
	 */
	public void setAvatarURL(String avatarURL);

	/**
	 * Returns the contributions of this git hub contributor.
	 *
	 * @return the contributions of this git hub contributor
	 */
	public int getContributions();

	/**
	 * Sets the contributions of this git hub contributor.
	 *
	 * @param contributions the contributions of this git hub contributor
	 */
	public void setContributions(int contributions);

	/**
	 * Returns the profile u r l of this git hub contributor.
	 *
	 * @return the profile u r l of this git hub contributor
	 */
	@AutoEscape
	public String getProfileURL();

	/**
	 * Sets the profile u r l of this git hub contributor.
	 *
	 * @param profileURL the profile u r l of this git hub contributor
	 */
	public void setProfileURL(String profileURL);

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
	public int compareTo(GitHubContributor gitHubContributor);

	@Override
	public int hashCode();

	@Override
	public CacheModel<GitHubContributor> toCacheModel();

	@Override
	public GitHubContributor toEscapedModel();

	@Override
	public GitHubContributor toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}