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

package com.liferay.osb.lcs.email;

import com.liferay.lcs.notification.LCSEventType;
import com.liferay.osb.lcs.model.LCSClusterEntry;
import com.liferay.osb.lcs.model.LCSClusterNode;
import com.liferay.osb.lcs.model.LCSProject;
import com.liferay.portal.kernel.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author Marko Cikos
 * @author Matija Petanjek
 * @author Igor Beslic
 */
public class EmailContext {

	public String getCustomMessage() {
		return _customMessage;
	}

	public String getEmailAddress() {
		return _emailAddress;
	}

	public LCSClusterEntry getLCSClusterEntry() {
		return _lcsClusterEntry;
	}

	public long getLCSClusterEntryId() {
		return _lcsClusterEntry.getLcsClusterEntryId();
	}

	public String getLCSClusterEntryName() {
		return _lcsClusterEntry.getName();
	}

	public LCSClusterNode getLCSClusterNode() {
		return _lcsClusterNode;
	}

	public long getLCSClusterNodeId() {
		return _lcsClusterNode.getLcsClusterNodeId();
	}

	public String getLCSClusterNodeName() {
		return _lcsClusterNode.getName();
	}

	public LCSEventType getLCSEventType() {
		return _lcsEventType;
	}

	public LCSProject getLCSProject() {
		return _lcsProject;
	}

	public long getLCSProjectId() {
		return _lcsProject.getLcsProjectId();
	}

	public String getLCSProjectName() {
		return _lcsProject.getName();
	}

	public Locale getLocale() {
		return _locale;
	}

	public List<String> getSiblingLCSClusterNodeNames() {
		return _siblingLCSClusterNodeNames;
	}

	public User getUser() {
		return _user;
	}

	public boolean isCluster() {
		return _cluster;
	}

	public static class EmailContextBuilder {

		public EmailContextBuilder(LCSEventType lcsEventType) {
			_lcsEventType = lcsEventType;
		}

		public EmailContext build() {
			return new EmailContext(this);
		}

		public EmailContextBuilder cluster(boolean cluster) {
			_cluster = cluster;

			return this;
		}

		public EmailContextBuilder customMessage(String customMessage) {
			_customMessage = customMessage;

			return this;
		}

		public EmailContextBuilder emailAddress(String emailAddress) {
			_emailAddress = emailAddress;

			return this;
		}

		public EmailContextBuilder lcsClusterEntry(
			LCSClusterEntry lcsClusterEntry) {

			_lcsClusterEntry = lcsClusterEntry;

			return this;
		}

		public EmailContextBuilder lcsClusterNode(
			LCSClusterNode lcsClusterNode) {

			_lcsClusterNode = lcsClusterNode;

			return this;
		}

		public EmailContextBuilder lcsProject(LCSProject lcsProject) {
			_lcsProject = lcsProject;

			return this;
		}

		public EmailContextBuilder locale(Locale locale) {
			_locale = locale;

			return this;
		}

		public EmailContextBuilder siblingLCSClusterNodeNames(
			List<String> siblingLCSCLusterNodeNames) {

			_siblingLCSClusterNodeNames.addAll(siblingLCSCLusterNodeNames);

			return this;
		}

		public EmailContextBuilder user(User user) {
			_user = user;

			return this;
		}

		private boolean _cluster;
		private String _customMessage;
		private String _emailAddress;
		private LCSClusterEntry _lcsClusterEntry;
		private LCSClusterNode _lcsClusterNode;
		private final LCSEventType _lcsEventType;
		private LCSProject _lcsProject;
		private Locale _locale;
		private final List<String> _siblingLCSClusterNodeNames =
			new ArrayList<>();
		private User _user;

	}

	private EmailContext(EmailContextBuilder emailContextBuilder) {
		_cluster = emailContextBuilder._cluster;
		_customMessage = emailContextBuilder._customMessage;
		_emailAddress = emailContextBuilder._emailAddress;
		_lcsClusterEntry = emailContextBuilder._lcsClusterEntry;
		_lcsClusterNode = emailContextBuilder._lcsClusterNode;
		_lcsEventType = emailContextBuilder._lcsEventType;
		_lcsProject = emailContextBuilder._lcsProject;
		_locale = emailContextBuilder._locale;
		_siblingLCSClusterNodeNames.addAll(
			emailContextBuilder._siblingLCSClusterNodeNames);
		_user = emailContextBuilder._user;
	}

	private final boolean _cluster;
	private final String _customMessage;
	private final String _emailAddress;
	private final LCSClusterEntry _lcsClusterEntry;
	private final LCSClusterNode _lcsClusterNode;
	private final LCSEventType _lcsEventType;
	private final LCSProject _lcsProject;
	private final Locale _locale;
	private final List<String> _siblingLCSClusterNodeNames = new ArrayList<>();
	private final User _user;

}