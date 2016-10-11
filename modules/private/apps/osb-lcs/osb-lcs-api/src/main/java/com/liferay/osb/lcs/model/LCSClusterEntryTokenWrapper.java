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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link LCSClusterEntryToken}.
 * </p>
 *
 * @author Igor Beslic
 * @see LCSClusterEntryToken
 * @generated
 */
@ProviderType
public class LCSClusterEntryTokenWrapper implements LCSClusterEntryToken,
	ModelWrapper<LCSClusterEntryToken> {
	public LCSClusterEntryTokenWrapper(
		LCSClusterEntryToken lcsClusterEntryToken) {
		_lcsClusterEntryToken = lcsClusterEntryToken;
	}

	@Override
	public Class<?> getModelClass() {
		return LCSClusterEntryToken.class;
	}

	@Override
	public String getModelClassName() {
		return LCSClusterEntryToken.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("lcsClusterEntryTokenId", getLcsClusterEntryTokenId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("lcsClusterEntryId", getLcsClusterEntryId());
		attributes.put("content", getContent());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long lcsClusterEntryTokenId = (Long)attributes.get(
				"lcsClusterEntryTokenId");

		if (lcsClusterEntryTokenId != null) {
			setLcsClusterEntryTokenId(lcsClusterEntryTokenId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Long lcsClusterEntryId = (Long)attributes.get("lcsClusterEntryId");

		if (lcsClusterEntryId != null) {
			setLcsClusterEntryId(lcsClusterEntryId);
		}

		String content = (String)attributes.get("content");

		if (content != null) {
			setContent(content);
		}
	}

	@Override
	public LCSClusterEntryToken toEscapedModel() {
		return new LCSClusterEntryTokenWrapper(_lcsClusterEntryToken.toEscapedModel());
	}

	@Override
	public LCSClusterEntryToken toUnescapedModel() {
		return new LCSClusterEntryTokenWrapper(_lcsClusterEntryToken.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _lcsClusterEntryToken.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _lcsClusterEntryToken.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _lcsClusterEntryToken.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _lcsClusterEntryToken.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<LCSClusterEntryToken> toCacheModel() {
		return _lcsClusterEntryToken.toCacheModel();
	}

	@Override
	public int compareTo(LCSClusterEntryToken lcsClusterEntryToken) {
		return _lcsClusterEntryToken.compareTo(lcsClusterEntryToken);
	}

	@Override
	public int hashCode() {
		return _lcsClusterEntryToken.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _lcsClusterEntryToken.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new LCSClusterEntryTokenWrapper((LCSClusterEntryToken)_lcsClusterEntryToken.clone());
	}

	/**
	* Returns the content of this l c s cluster entry token.
	*
	* @return the content of this l c s cluster entry token
	*/
	@Override
	public java.lang.String getContent() {
		return _lcsClusterEntryToken.getContent();
	}

	/**
	* Returns the user uuid of this l c s cluster entry token.
	*
	* @return the user uuid of this l c s cluster entry token
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _lcsClusterEntryToken.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _lcsClusterEntryToken.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _lcsClusterEntryToken.toXmlString();
	}

	/**
	* Returns the create date of this l c s cluster entry token.
	*
	* @return the create date of this l c s cluster entry token
	*/
	@Override
	public Date getCreateDate() {
		return _lcsClusterEntryToken.getCreateDate();
	}

	/**
	* Returns the lcs cluster entry ID of this l c s cluster entry token.
	*
	* @return the lcs cluster entry ID of this l c s cluster entry token
	*/
	@Override
	public long getLcsClusterEntryId() {
		return _lcsClusterEntryToken.getLcsClusterEntryId();
	}

	/**
	* Returns the lcs cluster entry token ID of this l c s cluster entry token.
	*
	* @return the lcs cluster entry token ID of this l c s cluster entry token
	*/
	@Override
	public long getLcsClusterEntryTokenId() {
		return _lcsClusterEntryToken.getLcsClusterEntryTokenId();
	}

	/**
	* Returns the primary key of this l c s cluster entry token.
	*
	* @return the primary key of this l c s cluster entry token
	*/
	@Override
	public long getPrimaryKey() {
		return _lcsClusterEntryToken.getPrimaryKey();
	}

	/**
	* Returns the user ID of this l c s cluster entry token.
	*
	* @return the user ID of this l c s cluster entry token
	*/
	@Override
	public long getUserId() {
		return _lcsClusterEntryToken.getUserId();
	}

	@Override
	public void persist() {
		_lcsClusterEntryToken.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_lcsClusterEntryToken.setCachedModel(cachedModel);
	}

	/**
	* Sets the content of this l c s cluster entry token.
	*
	* @param content the content of this l c s cluster entry token
	*/
	@Override
	public void setContent(java.lang.String content) {
		_lcsClusterEntryToken.setContent(content);
	}

	/**
	* Sets the create date of this l c s cluster entry token.
	*
	* @param createDate the create date of this l c s cluster entry token
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_lcsClusterEntryToken.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_lcsClusterEntryToken.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_lcsClusterEntryToken.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_lcsClusterEntryToken.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the lcs cluster entry ID of this l c s cluster entry token.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID of this l c s cluster entry token
	*/
	@Override
	public void setLcsClusterEntryId(long lcsClusterEntryId) {
		_lcsClusterEntryToken.setLcsClusterEntryId(lcsClusterEntryId);
	}

	/**
	* Sets the lcs cluster entry token ID of this l c s cluster entry token.
	*
	* @param lcsClusterEntryTokenId the lcs cluster entry token ID of this l c s cluster entry token
	*/
	@Override
	public void setLcsClusterEntryTokenId(long lcsClusterEntryTokenId) {
		_lcsClusterEntryToken.setLcsClusterEntryTokenId(lcsClusterEntryTokenId);
	}

	@Override
	public void setNew(boolean n) {
		_lcsClusterEntryToken.setNew(n);
	}

	/**
	* Sets the primary key of this l c s cluster entry token.
	*
	* @param primaryKey the primary key of this l c s cluster entry token
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_lcsClusterEntryToken.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_lcsClusterEntryToken.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this l c s cluster entry token.
	*
	* @param userId the user ID of this l c s cluster entry token
	*/
	@Override
	public void setUserId(long userId) {
		_lcsClusterEntryToken.setUserId(userId);
	}

	/**
	* Sets the user uuid of this l c s cluster entry token.
	*
	* @param userUuid the user uuid of this l c s cluster entry token
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_lcsClusterEntryToken.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LCSClusterEntryTokenWrapper)) {
			return false;
		}

		LCSClusterEntryTokenWrapper lcsClusterEntryTokenWrapper = (LCSClusterEntryTokenWrapper)obj;

		if (Objects.equals(_lcsClusterEntryToken,
					lcsClusterEntryTokenWrapper._lcsClusterEntryToken)) {
			return true;
		}

		return false;
	}

	@Override
	public LCSClusterEntryToken getWrappedModel() {
		return _lcsClusterEntryToken;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _lcsClusterEntryToken.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _lcsClusterEntryToken.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_lcsClusterEntryToken.resetOriginalValues();
	}

	private final LCSClusterEntryToken _lcsClusterEntryToken;
}