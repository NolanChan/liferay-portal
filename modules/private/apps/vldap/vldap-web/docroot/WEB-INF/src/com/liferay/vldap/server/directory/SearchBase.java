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

package com.liferay.vldap.server.directory;

import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroup;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.vldap.server.directory.builder.DirectoryBuilder;
import com.liferay.vldap.server.directory.ldap.Directory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jonathan Potter
 */
public class SearchBase {

	public SearchBase(Directory directory, DirectoryBuilder directoryBuilder) {
		_directory = directory;
		_directoryBuilder = directoryBuilder;
	}

	public SearchBase(
		Directory directory, DirectoryBuilder directoryBuilder, String top) {

		this(directory, directoryBuilder);

		_top = top;
	}

	public SearchBase(
		Directory directory, DirectoryBuilder directoryBuilder, String top,
		Company company) {

		this(directory, directoryBuilder, top);

		_company = company;
	}

	public SearchBase(
		Directory directory, DirectoryBuilder directoryBuilder, String top,
		Company company, Group community) {

		this(directory, directoryBuilder, top, company);

		_community = community;
	}

	public SearchBase(
		Directory directory, DirectoryBuilder directoryBuilder, String top,
		Company company, Organization organization) {

		this(directory, directoryBuilder, top, company);

		_organization = organization;
	}

	public SearchBase(
		Directory directory, DirectoryBuilder directoryBuilder, String top,
		Company company, Role role) {

		this(directory, directoryBuilder, top, company);

		_role = role;
	}

	public SearchBase(
		Directory directory, DirectoryBuilder directoryBuilder, String top,
		Company company, User user) {

		this(directory, directoryBuilder, top, company);

		_user = user;
	}

	public SearchBase(
		Directory directory, DirectoryBuilder directoryBuilder, String top,
		Company company, UserGroup userGroup) {

		this(directory, directoryBuilder, top, company);

		_userGroup = userGroup;
	}

	public Group getCommunity() {
		return _community;
	}

	public List<Company> getCompanies() {
		if (_company == null) {
			return CompanyLocalServiceUtil.getCompanies();
		}
		else {
			List<Company> companies = new ArrayList<>(1);

			companies.add(_company);

			return companies;
		}
	}

	public Company getCompany() {
		return _company;
	}

	public long getCompanyId() {
		return _company.getCompanyId();
	}

	public Directory getDirectory() {
		return _directory;
	}

	public DirectoryBuilder getDirectoryBuilder() {
		return _directoryBuilder;
	}

	public Organization getOrganization() {
		return _organization;
	}

	public Role getRole() {
		return _role;
	}

	public long getSizeLimit() {
		return _sizeLimit;
	}

	public String getTop() {
		return _top;
	}

	public User getUser() {
		return _user;
	}

	public UserGroup getUserGroup() {
		return _userGroup;
	}

	public void setSizeLimit(long sizeLimit) {
		_sizeLimit = sizeLimit;
	}

	private final Group _community;
	private final Company _company;
	private final Directory _directory;
	private final DirectoryBuilder _directoryBuilder;
	private final Organization _organization;
	private final Role _role;
	private long _sizeLimit;
	private final String _top;
	private final User _user;
	private final UserGroup _userGroup;

}