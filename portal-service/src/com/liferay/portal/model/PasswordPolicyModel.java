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

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the PasswordPolicy service. Represents a row in the &quot;PasswordPolicy&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portal.model.impl.PasswordPolicyModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portal.model.impl.PasswordPolicyImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PasswordPolicy
 * @see com.liferay.portal.model.impl.PasswordPolicyImpl
 * @see com.liferay.portal.model.impl.PasswordPolicyModelImpl
 * @generated
 */
@ProviderType
public interface PasswordPolicyModel extends BaseModel<PasswordPolicy>, MVCCModel,
	ShardedModel, StagedAuditedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a password policy model instance should use the {@link PasswordPolicy} interface instead.
	 */

	/**
	 * Returns the primary key of this password policy.
	 *
	 * @return the primary key of this password policy
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this password policy.
	 *
	 * @param primaryKey the primary key of this password policy
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this password policy.
	 *
	 * @return the mvcc version of this password policy
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this password policy.
	 *
	 * @param mvccVersion the mvcc version of this password policy
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the uuid of this password policy.
	 *
	 * @return the uuid of this password policy
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this password policy.
	 *
	 * @param uuid the uuid of this password policy
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the password policy ID of this password policy.
	 *
	 * @return the password policy ID of this password policy
	 */
	public long getPasswordPolicyId();

	/**
	 * Sets the password policy ID of this password policy.
	 *
	 * @param passwordPolicyId the password policy ID of this password policy
	 */
	public void setPasswordPolicyId(long passwordPolicyId);

	/**
	 * Returns the company ID of this password policy.
	 *
	 * @return the company ID of this password policy
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this password policy.
	 *
	 * @param companyId the company ID of this password policy
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this password policy.
	 *
	 * @return the user ID of this password policy
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this password policy.
	 *
	 * @param userId the user ID of this password policy
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this password policy.
	 *
	 * @return the user uuid of this password policy
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this password policy.
	 *
	 * @param userUuid the user uuid of this password policy
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this password policy.
	 *
	 * @return the user name of this password policy
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this password policy.
	 *
	 * @param userName the user name of this password policy
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this password policy.
	 *
	 * @return the create date of this password policy
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this password policy.
	 *
	 * @param createDate the create date of this password policy
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this password policy.
	 *
	 * @return the modified date of this password policy
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this password policy.
	 *
	 * @param modifiedDate the modified date of this password policy
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the default policy of this password policy.
	 *
	 * @return the default policy of this password policy
	 */
	public boolean getDefaultPolicy();

	/**
	 * Returns <code>true</code> if this password policy is default policy.
	 *
	 * @return <code>true</code> if this password policy is default policy; <code>false</code> otherwise
	 */
	public boolean isDefaultPolicy();

	/**
	 * Sets whether this password policy is default policy.
	 *
	 * @param defaultPolicy the default policy of this password policy
	 */
	public void setDefaultPolicy(boolean defaultPolicy);

	/**
	 * Returns the name of this password policy.
	 *
	 * @return the name of this password policy
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this password policy.
	 *
	 * @param name the name of this password policy
	 */
	public void setName(String name);

	/**
	 * Returns the description of this password policy.
	 *
	 * @return the description of this password policy
	 */
	@AutoEscape
	public String getDescription();

	/**
	 * Sets the description of this password policy.
	 *
	 * @param description the description of this password policy
	 */
	public void setDescription(String description);

	/**
	 * Returns the changeable of this password policy.
	 *
	 * @return the changeable of this password policy
	 */
	public boolean getChangeable();

	/**
	 * Returns <code>true</code> if this password policy is changeable.
	 *
	 * @return <code>true</code> if this password policy is changeable; <code>false</code> otherwise
	 */
	public boolean isChangeable();

	/**
	 * Sets whether this password policy is changeable.
	 *
	 * @param changeable the changeable of this password policy
	 */
	public void setChangeable(boolean changeable);

	/**
	 * Returns the change required of this password policy.
	 *
	 * @return the change required of this password policy
	 */
	public boolean getChangeRequired();

	/**
	 * Returns <code>true</code> if this password policy is change required.
	 *
	 * @return <code>true</code> if this password policy is change required; <code>false</code> otherwise
	 */
	public boolean isChangeRequired();

	/**
	 * Sets whether this password policy is change required.
	 *
	 * @param changeRequired the change required of this password policy
	 */
	public void setChangeRequired(boolean changeRequired);

	/**
	 * Returns the min age of this password policy.
	 *
	 * @return the min age of this password policy
	 */
	public long getMinAge();

	/**
	 * Sets the min age of this password policy.
	 *
	 * @param minAge the min age of this password policy
	 */
	public void setMinAge(long minAge);

	/**
	 * Returns the check syntax of this password policy.
	 *
	 * @return the check syntax of this password policy
	 */
	public boolean getCheckSyntax();

	/**
	 * Returns <code>true</code> if this password policy is check syntax.
	 *
	 * @return <code>true</code> if this password policy is check syntax; <code>false</code> otherwise
	 */
	public boolean isCheckSyntax();

	/**
	 * Sets whether this password policy is check syntax.
	 *
	 * @param checkSyntax the check syntax of this password policy
	 */
	public void setCheckSyntax(boolean checkSyntax);

	/**
	 * Returns the allow dictionary words of this password policy.
	 *
	 * @return the allow dictionary words of this password policy
	 */
	public boolean getAllowDictionaryWords();

	/**
	 * Returns <code>true</code> if this password policy is allow dictionary words.
	 *
	 * @return <code>true</code> if this password policy is allow dictionary words; <code>false</code> otherwise
	 */
	public boolean isAllowDictionaryWords();

	/**
	 * Sets whether this password policy is allow dictionary words.
	 *
	 * @param allowDictionaryWords the allow dictionary words of this password policy
	 */
	public void setAllowDictionaryWords(boolean allowDictionaryWords);

	/**
	 * Returns the min alphanumeric of this password policy.
	 *
	 * @return the min alphanumeric of this password policy
	 */
	public int getMinAlphanumeric();

	/**
	 * Sets the min alphanumeric of this password policy.
	 *
	 * @param minAlphanumeric the min alphanumeric of this password policy
	 */
	public void setMinAlphanumeric(int minAlphanumeric);

	/**
	 * Returns the min length of this password policy.
	 *
	 * @return the min length of this password policy
	 */
	public int getMinLength();

	/**
	 * Sets the min length of this password policy.
	 *
	 * @param minLength the min length of this password policy
	 */
	public void setMinLength(int minLength);

	/**
	 * Returns the min lower case of this password policy.
	 *
	 * @return the min lower case of this password policy
	 */
	public int getMinLowerCase();

	/**
	 * Sets the min lower case of this password policy.
	 *
	 * @param minLowerCase the min lower case of this password policy
	 */
	public void setMinLowerCase(int minLowerCase);

	/**
	 * Returns the min numbers of this password policy.
	 *
	 * @return the min numbers of this password policy
	 */
	public int getMinNumbers();

	/**
	 * Sets the min numbers of this password policy.
	 *
	 * @param minNumbers the min numbers of this password policy
	 */
	public void setMinNumbers(int minNumbers);

	/**
	 * Returns the min symbols of this password policy.
	 *
	 * @return the min symbols of this password policy
	 */
	public int getMinSymbols();

	/**
	 * Sets the min symbols of this password policy.
	 *
	 * @param minSymbols the min symbols of this password policy
	 */
	public void setMinSymbols(int minSymbols);

	/**
	 * Returns the min upper case of this password policy.
	 *
	 * @return the min upper case of this password policy
	 */
	public int getMinUpperCase();

	/**
	 * Sets the min upper case of this password policy.
	 *
	 * @param minUpperCase the min upper case of this password policy
	 */
	public void setMinUpperCase(int minUpperCase);

	/**
	 * Returns the regex of this password policy.
	 *
	 * @return the regex of this password policy
	 */
	@AutoEscape
	public String getRegex();

	/**
	 * Sets the regex of this password policy.
	 *
	 * @param regex the regex of this password policy
	 */
	public void setRegex(String regex);

	/**
	 * Returns the history of this password policy.
	 *
	 * @return the history of this password policy
	 */
	public boolean getHistory();

	/**
	 * Returns <code>true</code> if this password policy is history.
	 *
	 * @return <code>true</code> if this password policy is history; <code>false</code> otherwise
	 */
	public boolean isHistory();

	/**
	 * Sets whether this password policy is history.
	 *
	 * @param history the history of this password policy
	 */
	public void setHistory(boolean history);

	/**
	 * Returns the history count of this password policy.
	 *
	 * @return the history count of this password policy
	 */
	public int getHistoryCount();

	/**
	 * Sets the history count of this password policy.
	 *
	 * @param historyCount the history count of this password policy
	 */
	public void setHistoryCount(int historyCount);

	/**
	 * Returns the expireable of this password policy.
	 *
	 * @return the expireable of this password policy
	 */
	public boolean getExpireable();

	/**
	 * Returns <code>true</code> if this password policy is expireable.
	 *
	 * @return <code>true</code> if this password policy is expireable; <code>false</code> otherwise
	 */
	public boolean isExpireable();

	/**
	 * Sets whether this password policy is expireable.
	 *
	 * @param expireable the expireable of this password policy
	 */
	public void setExpireable(boolean expireable);

	/**
	 * Returns the max age of this password policy.
	 *
	 * @return the max age of this password policy
	 */
	public long getMaxAge();

	/**
	 * Sets the max age of this password policy.
	 *
	 * @param maxAge the max age of this password policy
	 */
	public void setMaxAge(long maxAge);

	/**
	 * Returns the warning time of this password policy.
	 *
	 * @return the warning time of this password policy
	 */
	public long getWarningTime();

	/**
	 * Sets the warning time of this password policy.
	 *
	 * @param warningTime the warning time of this password policy
	 */
	public void setWarningTime(long warningTime);

	/**
	 * Returns the grace limit of this password policy.
	 *
	 * @return the grace limit of this password policy
	 */
	public int getGraceLimit();

	/**
	 * Sets the grace limit of this password policy.
	 *
	 * @param graceLimit the grace limit of this password policy
	 */
	public void setGraceLimit(int graceLimit);

	/**
	 * Returns the lockout of this password policy.
	 *
	 * @return the lockout of this password policy
	 */
	public boolean getLockout();

	/**
	 * Returns <code>true</code> if this password policy is lockout.
	 *
	 * @return <code>true</code> if this password policy is lockout; <code>false</code> otherwise
	 */
	public boolean isLockout();

	/**
	 * Sets whether this password policy is lockout.
	 *
	 * @param lockout the lockout of this password policy
	 */
	public void setLockout(boolean lockout);

	/**
	 * Returns the max failure of this password policy.
	 *
	 * @return the max failure of this password policy
	 */
	public int getMaxFailure();

	/**
	 * Sets the max failure of this password policy.
	 *
	 * @param maxFailure the max failure of this password policy
	 */
	public void setMaxFailure(int maxFailure);

	/**
	 * Returns the lockout duration of this password policy.
	 *
	 * @return the lockout duration of this password policy
	 */
	public long getLockoutDuration();

	/**
	 * Sets the lockout duration of this password policy.
	 *
	 * @param lockoutDuration the lockout duration of this password policy
	 */
	public void setLockoutDuration(long lockoutDuration);

	/**
	 * Returns the require unlock of this password policy.
	 *
	 * @return the require unlock of this password policy
	 */
	public boolean getRequireUnlock();

	/**
	 * Returns <code>true</code> if this password policy is require unlock.
	 *
	 * @return <code>true</code> if this password policy is require unlock; <code>false</code> otherwise
	 */
	public boolean isRequireUnlock();

	/**
	 * Sets whether this password policy is require unlock.
	 *
	 * @param requireUnlock the require unlock of this password policy
	 */
	public void setRequireUnlock(boolean requireUnlock);

	/**
	 * Returns the reset failure count of this password policy.
	 *
	 * @return the reset failure count of this password policy
	 */
	public long getResetFailureCount();

	/**
	 * Sets the reset failure count of this password policy.
	 *
	 * @param resetFailureCount the reset failure count of this password policy
	 */
	public void setResetFailureCount(long resetFailureCount);

	/**
	 * Returns the reset ticket max age of this password policy.
	 *
	 * @return the reset ticket max age of this password policy
	 */
	public long getResetTicketMaxAge();

	/**
	 * Sets the reset ticket max age of this password policy.
	 *
	 * @param resetTicketMaxAge the reset ticket max age of this password policy
	 */
	public void setResetTicketMaxAge(long resetTicketMaxAge);

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
	public int compareTo(com.liferay.portal.model.PasswordPolicy passwordPolicy);

	@Override
	public int hashCode();

	@Override
	public CacheModel<com.liferay.portal.model.PasswordPolicy> toCacheModel();

	@Override
	public com.liferay.portal.model.PasswordPolicy toEscapedModel();

	@Override
	public com.liferay.portal.model.PasswordPolicy toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}