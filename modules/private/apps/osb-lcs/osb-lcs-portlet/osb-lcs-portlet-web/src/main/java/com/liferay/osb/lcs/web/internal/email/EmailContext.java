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

package com.liferay.osb.lcs.web.internal.email;

import com.liferay.lcs.notification.LCSEventType;
import com.liferay.osb.lcs.model.LCSClusterEntry;
import com.liferay.osb.lcs.model.LCSClusterNode;
import com.liferay.osb.lcs.model.LCSProject;
import com.liferay.osb.lcs.model.LCSRole;
import com.liferay.osb.lcs.service.LCSClusterEntryLocalServiceUtil;
import com.liferay.osb.lcs.service.LCSClusterNodeLocalServiceUtil;
import com.liferay.osb.lcs.service.LCSProjectLocalServiceUtil;
import com.liferay.osb.lcs.util.PortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.User;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.PortletConfigFactoryUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletConfig;

/**
 * @author Marko Cikos
 * @author Matija Petanjek
 */
public class EmailContext {

	public EmailContext(
			boolean cluster, String lcsClusterNodeKey,
			LCSEventType lcsEventType, long userId)
		throws PortalException {

		this(lcsEventType, userId);

		_cluster = cluster;

		_lcsClusterNode = LCSClusterNodeLocalServiceUtil.getLCSClusterNode(
			lcsClusterNodeKey);

		_lcsClusterEntry = LCSClusterEntryLocalServiceUtil.getLCSClusterEntry(
			_lcsClusterNode.getLcsClusterEntryId());

		_lcsProject = LCSProjectLocalServiceUtil.getLCSProject(
			_lcsClusterEntry.getLcsProjectId());
	}

	public EmailContext(LCSEventType lcsEventType, LCSRole lcsRole)
		throws PortalException {

		this(lcsEventType, lcsRole.getUserId());

		_lcsProject = LCSProjectLocalServiceUtil.getLCSProject(
			lcsRole.getLcsProjectId());
	}

	public EmailContext(LCSEventType lcsEventType, long userId)
		throws PortalException {

		_lcsEventType = lcsEventType;

		_user = UserLocalServiceUtil.getUserById(userId);

		Portlet portlet = PortletLocalServiceUtil.getPortletById(
			PortletKeys.NOTIFICATIONS);

		_portletConfig = PortletConfigFactoryUtil.update(portlet);

		_locale = _user.getLocale();
	}

	public EmailContext(
			String lcsClusterNodeKey, LCSEventType lcsEventType,
			List<String> siblingLCSClusterNodeKeys, long userId)
		throws PortalException {

		this(false, lcsClusterNodeKey, lcsEventType, userId);

		_siblingLCSClusterNodeKeys = siblingLCSClusterNodeKeys;
	}

	public EmailContext(
			String customMessage, String emailAddress,
			LCSEventType lcsEventType, long lcsProjectId, long userId)
		throws PortalException {

		this(lcsEventType, userId);

		_customMessage = customMessage;

		_emailAddress = emailAddress;

		_lcsProject = LCSProjectLocalServiceUtil.getLCSProject(lcsProjectId);
	}

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

	public PortletConfig getPortletConfig() {
		return _portletConfig;
	}

	public List<String> getSiblingLCSClusterNodeKeys() {
		return _siblingLCSClusterNodeKeys;
	}

	public User getUser() {
		return _user;
	}

	public boolean isCluster() {
		return _cluster;
	}

	protected String translate(String pattern, Object... arguments) {
		return LanguageUtil.format(_portletConfig, _locale, pattern, arguments);
	}

	private final boolean _cluster;
	private final String _customMessage;
	private final String _emailAddress;
	private final LCSClusterEntry _lcsClusterEntry;
	private final LCSClusterNode _lcsClusterNode;
	private final LCSEventType _lcsEventType;
	private final LCSProject _lcsProject;
	private final Locale _locale;
	private final PortletConfig _portletConfig;
	private final List<String> _siblingLCSClusterNodeKeys = new ArrayList<>();
	private final User _user;

}