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

import com.liferay.portal.model.AttachedModel;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.MVCCModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

/**
 * The base model interface for the PasswordPolicyRel service. Represents a row in the &quot;PasswordPolicyRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portal.model.impl.PasswordPolicyRelModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portal.model.impl.PasswordPolicyRelImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PasswordPolicyRel
 * @see com.liferay.portal.model.impl.PasswordPolicyRelImpl
 * @see com.liferay.portal.model.impl.PasswordPolicyRelModelImpl
 * @generated
 */
@ProviderType
public interface PasswordPolicyRelModel extends AttachedModel,
	BaseModel<PasswordPolicyRel>, MVCCModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a password policy rel model instance should use the {@link PasswordPolicyRel} interface instead.
	 */

	/**
	 * Returns the primary key of this password policy rel.
	 *
	 * @return the primary key of this password policy rel
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this password policy rel.
	 *
	 * @param primaryKey the primary key of this password policy rel
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this password policy rel.
	 *
	 * @return the mvcc version of this password policy rel
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this password policy rel.
	 *
	 * @param mvccVersion the mvcc version of this password policy rel
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the password policy rel ID of this password policy rel.
	 *
	 * @return the password policy rel ID of this password policy rel
	 */
	public long getPasswordPolicyRelId();

	/**
	 * Sets the password policy rel ID of this password policy rel.
	 *
	 * @param passwordPolicyRelId the password policy rel ID of this password policy rel
	 */
	public void setPasswordPolicyRelId(long passwordPolicyRelId);

	/**
	 * Returns the password policy ID of this password policy rel.
	 *
	 * @return the password policy ID of this password policy rel
	 */
	public long getPasswordPolicyId();

	/**
	 * Sets the password policy ID of this password policy rel.
	 *
	 * @param passwordPolicyId the password policy ID of this password policy rel
	 */
	public void setPasswordPolicyId(long passwordPolicyId);

	/**
	 * Returns the fully qualified class name of this password policy rel.
	 *
	 * @return the fully qualified class name of this password policy rel
	 */
	@Override
	public String getClassName();

	public void setClassName(String className);

	/**
	 * Returns the class name ID of this password policy rel.
	 *
	 * @return the class name ID of this password policy rel
	 */
	@Override
	public long getClassNameId();

	/**
	 * Sets the class name ID of this password policy rel.
	 *
	 * @param classNameId the class name ID of this password policy rel
	 */
	@Override
	public void setClassNameId(long classNameId);

	/**
	 * Returns the class p k of this password policy rel.
	 *
	 * @return the class p k of this password policy rel
	 */
	@Override
	public long getClassPK();

	/**
	 * Sets the class p k of this password policy rel.
	 *
	 * @param classPK the class p k of this password policy rel
	 */
	@Override
	public void setClassPK(long classPK);

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
	public int compareTo(
		com.liferay.portal.model.PasswordPolicyRel passwordPolicyRel);

	@Override
	public int hashCode();

	@Override
	public CacheModel<com.liferay.portal.model.PasswordPolicyRel> toCacheModel();

	@Override
	public com.liferay.portal.model.PasswordPolicyRel toEscapedModel();

	@Override
	public com.liferay.portal.model.PasswordPolicyRel toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}