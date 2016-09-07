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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link GitHubRepository}.
 * </p>
 *
 * @author Howie Chou
 * @see GitHubRepository
 * @generated
 */
@ProviderType
public class GitHubRepositoryWrapper implements GitHubRepository,
	ModelWrapper<GitHubRepository> {
	public GitHubRepositoryWrapper(GitHubRepository gitHubRepository) {
		_gitHubRepository = gitHubRepository;
	}

	@Override
	public Class<?> getModelClass() {
		return GitHubRepository.class;
	}

	@Override
	public String getModelClassName() {
		return GitHubRepository.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("gitHubRepositoryId", getGitHubRepositoryId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("owner", getOwner());
		attributes.put("name", getName());
		attributes.put("commits", getCommits());
		attributes.put("openIssues", getOpenIssues());
		attributes.put("stars", getStars());
		attributes.put("url", getUrl());
		attributes.put("repositoryCreateDate", getRepositoryCreateDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long gitHubRepositoryId = (Long)attributes.get("gitHubRepositoryId");

		if (gitHubRepositoryId != null) {
			setGitHubRepositoryId(gitHubRepositoryId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String owner = (String)attributes.get("owner");

		if (owner != null) {
			setOwner(owner);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		Integer commits = (Integer)attributes.get("commits");

		if (commits != null) {
			setCommits(commits);
		}

		Integer openIssues = (Integer)attributes.get("openIssues");

		if (openIssues != null) {
			setOpenIssues(openIssues);
		}

		Integer stars = (Integer)attributes.get("stars");

		if (stars != null) {
			setStars(stars);
		}

		String url = (String)attributes.get("url");

		if (url != null) {
			setUrl(url);
		}

		Date repositoryCreateDate = (Date)attributes.get("repositoryCreateDate");

		if (repositoryCreateDate != null) {
			setRepositoryCreateDate(repositoryCreateDate);
		}
	}

	@Override
	public GitHubRepository toEscapedModel() {
		return new GitHubRepositoryWrapper(_gitHubRepository.toEscapedModel());
	}

	@Override
	public GitHubRepository toUnescapedModel() {
		return new GitHubRepositoryWrapper(_gitHubRepository.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _gitHubRepository.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _gitHubRepository.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _gitHubRepository.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _gitHubRepository.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<GitHubRepository> toCacheModel() {
		return _gitHubRepository.toCacheModel();
	}

	@Override
	public int compareTo(GitHubRepository gitHubRepository) {
		return _gitHubRepository.compareTo(gitHubRepository);
	}

	/**
	* Returns the commits of this git hub repository.
	*
	* @return the commits of this git hub repository
	*/
	@Override
	public int getCommits() {
		return _gitHubRepository.getCommits();
	}

	/**
	* Returns the open issues of this git hub repository.
	*
	* @return the open issues of this git hub repository
	*/
	@Override
	public int getOpenIssues() {
		return _gitHubRepository.getOpenIssues();
	}

	/**
	* Returns the stars of this git hub repository.
	*
	* @return the stars of this git hub repository
	*/
	@Override
	public int getStars() {
		return _gitHubRepository.getStars();
	}

	@Override
	public int hashCode() {
		return _gitHubRepository.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _gitHubRepository.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new GitHubRepositoryWrapper((GitHubRepository)_gitHubRepository.clone());
	}

	/**
	* Returns the name of this git hub repository.
	*
	* @return the name of this git hub repository
	*/
	@Override
	public java.lang.String getName() {
		return _gitHubRepository.getName();
	}

	/**
	* Returns the owner of this git hub repository.
	*
	* @return the owner of this git hub repository
	*/
	@Override
	public java.lang.String getOwner() {
		return _gitHubRepository.getOwner();
	}

	/**
	* Returns the url of this git hub repository.
	*
	* @return the url of this git hub repository
	*/
	@Override
	public java.lang.String getUrl() {
		return _gitHubRepository.getUrl();
	}

	/**
	* Returns the user name of this git hub repository.
	*
	* @return the user name of this git hub repository
	*/
	@Override
	public java.lang.String getUserName() {
		return _gitHubRepository.getUserName();
	}

	/**
	* Returns the user uuid of this git hub repository.
	*
	* @return the user uuid of this git hub repository
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _gitHubRepository.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _gitHubRepository.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _gitHubRepository.toXmlString();
	}

	/**
	* Returns the create date of this git hub repository.
	*
	* @return the create date of this git hub repository
	*/
	@Override
	public Date getCreateDate() {
		return _gitHubRepository.getCreateDate();
	}

	/**
	* Returns the modified date of this git hub repository.
	*
	* @return the modified date of this git hub repository
	*/
	@Override
	public Date getModifiedDate() {
		return _gitHubRepository.getModifiedDate();
	}

	/**
	* Returns the repository create date of this git hub repository.
	*
	* @return the repository create date of this git hub repository
	*/
	@Override
	public Date getRepositoryCreateDate() {
		return _gitHubRepository.getRepositoryCreateDate();
	}

	/**
	* Returns the company ID of this git hub repository.
	*
	* @return the company ID of this git hub repository
	*/
	@Override
	public long getCompanyId() {
		return _gitHubRepository.getCompanyId();
	}

	/**
	* Returns the git hub repository ID of this git hub repository.
	*
	* @return the git hub repository ID of this git hub repository
	*/
	@Override
	public long getGitHubRepositoryId() {
		return _gitHubRepository.getGitHubRepositoryId();
	}

	/**
	* Returns the primary key of this git hub repository.
	*
	* @return the primary key of this git hub repository
	*/
	@Override
	public long getPrimaryKey() {
		return _gitHubRepository.getPrimaryKey();
	}

	/**
	* Returns the user ID of this git hub repository.
	*
	* @return the user ID of this git hub repository
	*/
	@Override
	public long getUserId() {
		return _gitHubRepository.getUserId();
	}

	@Override
	public void persist() {
		_gitHubRepository.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_gitHubRepository.setCachedModel(cachedModel);
	}

	/**
	* Sets the commits of this git hub repository.
	*
	* @param commits the commits of this git hub repository
	*/
	@Override
	public void setCommits(int commits) {
		_gitHubRepository.setCommits(commits);
	}

	/**
	* Sets the company ID of this git hub repository.
	*
	* @param companyId the company ID of this git hub repository
	*/
	@Override
	public void setCompanyId(long companyId) {
		_gitHubRepository.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this git hub repository.
	*
	* @param createDate the create date of this git hub repository
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_gitHubRepository.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_gitHubRepository.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_gitHubRepository.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_gitHubRepository.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the git hub repository ID of this git hub repository.
	*
	* @param gitHubRepositoryId the git hub repository ID of this git hub repository
	*/
	@Override
	public void setGitHubRepositoryId(long gitHubRepositoryId) {
		_gitHubRepository.setGitHubRepositoryId(gitHubRepositoryId);
	}

	/**
	* Sets the modified date of this git hub repository.
	*
	* @param modifiedDate the modified date of this git hub repository
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_gitHubRepository.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this git hub repository.
	*
	* @param name the name of this git hub repository
	*/
	@Override
	public void setName(java.lang.String name) {
		_gitHubRepository.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_gitHubRepository.setNew(n);
	}

	/**
	* Sets the open issues of this git hub repository.
	*
	* @param openIssues the open issues of this git hub repository
	*/
	@Override
	public void setOpenIssues(int openIssues) {
		_gitHubRepository.setOpenIssues(openIssues);
	}

	/**
	* Sets the owner of this git hub repository.
	*
	* @param owner the owner of this git hub repository
	*/
	@Override
	public void setOwner(java.lang.String owner) {
		_gitHubRepository.setOwner(owner);
	}

	/**
	* Sets the primary key of this git hub repository.
	*
	* @param primaryKey the primary key of this git hub repository
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_gitHubRepository.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_gitHubRepository.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the repository create date of this git hub repository.
	*
	* @param repositoryCreateDate the repository create date of this git hub repository
	*/
	@Override
	public void setRepositoryCreateDate(Date repositoryCreateDate) {
		_gitHubRepository.setRepositoryCreateDate(repositoryCreateDate);
	}

	/**
	* Sets the stars of this git hub repository.
	*
	* @param stars the stars of this git hub repository
	*/
	@Override
	public void setStars(int stars) {
		_gitHubRepository.setStars(stars);
	}

	/**
	* Sets the url of this git hub repository.
	*
	* @param url the url of this git hub repository
	*/
	@Override
	public void setUrl(java.lang.String url) {
		_gitHubRepository.setUrl(url);
	}

	/**
	* Sets the user ID of this git hub repository.
	*
	* @param userId the user ID of this git hub repository
	*/
	@Override
	public void setUserId(long userId) {
		_gitHubRepository.setUserId(userId);
	}

	/**
	* Sets the user name of this git hub repository.
	*
	* @param userName the user name of this git hub repository
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_gitHubRepository.setUserName(userName);
	}

	/**
	* Sets the user uuid of this git hub repository.
	*
	* @param userUuid the user uuid of this git hub repository
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_gitHubRepository.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof GitHubRepositoryWrapper)) {
			return false;
		}

		GitHubRepositoryWrapper gitHubRepositoryWrapper = (GitHubRepositoryWrapper)obj;

		if (Objects.equals(_gitHubRepository,
					gitHubRepositoryWrapper._gitHubRepository)) {
			return true;
		}

		return false;
	}

	@Override
	public GitHubRepository getWrappedModel() {
		return _gitHubRepository;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _gitHubRepository.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _gitHubRepository.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_gitHubRepository.resetOriginalValues();
	}

	private final GitHubRepository _gitHubRepository;
}