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
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.MVCCModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the Release service. Represents a row in the &quot;Release_&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portal.model.impl.ReleaseModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portal.model.impl.ReleaseImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Release
 * @see com.liferay.portal.model.impl.ReleaseImpl
 * @see com.liferay.portal.model.impl.ReleaseModelImpl
 * @generated
 */
@ProviderType
public interface ReleaseModel extends BaseModel<Release>, MVCCModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a release model instance should use the {@link Release} interface instead.
	 */

	/**
	 * Returns the primary key of this release.
	 *
	 * @return the primary key of this release
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this release.
	 *
	 * @param primaryKey the primary key of this release
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this release.
	 *
	 * @return the mvcc version of this release
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this release.
	 *
	 * @param mvccVersion the mvcc version of this release
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the release ID of this release.
	 *
	 * @return the release ID of this release
	 */
	public long getReleaseId();

	/**
	 * Sets the release ID of this release.
	 *
	 * @param releaseId the release ID of this release
	 */
	public void setReleaseId(long releaseId);

	/**
	 * Returns the create date of this release.
	 *
	 * @return the create date of this release
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this release.
	 *
	 * @param createDate the create date of this release
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this release.
	 *
	 * @return the modified date of this release
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this release.
	 *
	 * @param modifiedDate the modified date of this release
	 */
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the servlet context name of this release.
	 *
	 * @return the servlet context name of this release
	 */
	@AutoEscape
	public String getServletContextName();

	/**
	 * Sets the servlet context name of this release.
	 *
	 * @param servletContextName the servlet context name of this release
	 */
	public void setServletContextName(String servletContextName);

	/**
	 * Returns the build number of this release.
	 *
	 * @return the build number of this release
	 */
	public int getBuildNumber();

	/**
	 * Sets the build number of this release.
	 *
	 * @param buildNumber the build number of this release
	 */
	public void setBuildNumber(int buildNumber);

	/**
	 * Returns the build date of this release.
	 *
	 * @return the build date of this release
	 */
	public Date getBuildDate();

	/**
	 * Sets the build date of this release.
	 *
	 * @param buildDate the build date of this release
	 */
	public void setBuildDate(Date buildDate);

	/**
	 * Returns the verified of this release.
	 *
	 * @return the verified of this release
	 */
	public boolean getVerified();

	/**
	 * Returns <code>true</code> if this release is verified.
	 *
	 * @return <code>true</code> if this release is verified; <code>false</code> otherwise
	 */
	public boolean isVerified();

	/**
	 * Sets whether this release is verified.
	 *
	 * @param verified the verified of this release
	 */
	public void setVerified(boolean verified);

	/**
	 * Returns the state of this release.
	 *
	 * @return the state of this release
	 */
	public int getState();

	/**
	 * Sets the state of this release.
	 *
	 * @param state the state of this release
	 */
	public void setState(int state);

	/**
	 * Returns the test string of this release.
	 *
	 * @return the test string of this release
	 */
	@AutoEscape
	public String getTestString();

	/**
	 * Sets the test string of this release.
	 *
	 * @param testString the test string of this release
	 */
	public void setTestString(String testString);

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
	public int compareTo(Release release);

	@Override
	public int hashCode();

	@Override
	public CacheModel<Release> toCacheModel();

	@Override
	public Release toEscapedModel();

	@Override
	public Release toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}