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

package com.liferay.osb.lcs.advisor.impl;

import com.liferay.lcs.messaging.CommandMessage;
import com.liferay.lcs.messaging.HandshakeMessage;
import com.liferay.lcs.messaging.Message;
import com.liferay.lcs.security.DigitalSignature;
import com.liferay.osb.lcs.advisor.CommandMessageAdvisor;
import com.liferay.osb.lcs.constants.OSBLCSActionKeys;
import com.liferay.osb.lcs.model.LCSClusterNode;
import com.liferay.osb.lcs.nosql.model.LCSClusterNodeInstallationEnvironment;
import com.liferay.osb.lcs.nosql.service.LCSClusterNodeInstallationEnvironmentService;
import com.liferay.osb.lcs.nosql.service.LCSClusterNodeScriptService;
import com.liferay.osb.lcs.queue.QueueManager;
import com.liferay.osb.lcs.service.permission.LCSClusterEntryPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
@Component(immediate = true, service = CommandMessageAdvisor.class)
public class CommandMessageAdvisorImpl implements CommandMessageAdvisor {

	@Override
	public void deregister(String key) {
		sendCommandMessage(CommandMessage.COMMAND_TYPE_DEREGISTER, key, null);
		sendHandshakeMessage(key);
	}

	@Override
	public void downloadPatches(
			LCSClusterNode lcsClusterNode, Map<String, String> patchNamesURLs)
		throws PortalException {

		LCSClusterEntryPermission.check(
			PermissionThreadLocal.getPermissionChecker(),
			lcsClusterNode.getLcsClusterEntryId(),
			OSBLCSActionKeys.DOWNLOAD_PATCH);

		sendCommandMessage(
			CommandMessage.COMMAND_TYPE_DOWNLOAD_PATCHES,
			lcsClusterNode.getKey(), patchNamesURLs);
	}

	@Override
	public void executeScript(LCSClusterNode lcsClusterNode, String script)
		throws PortalException {

		LCSClusterEntryPermission.check(
			PermissionThreadLocal.getPermissionChecker(),
			lcsClusterNode.getLcsClusterEntryId(),
			OSBLCSActionKeys.EXECUTE_COMMAND);

		sendCommandMessage(
			CommandMessage.COMMAND_TYPE_EXECUTE_SCRIPT, lcsClusterNode.getKey(),
			script);
	}

	@Override
	public void invalidateLCSClusterEntryToken(String key) {
		sendCommandMessage(CommandMessage.COMMAND_TYPE_DEREGISTER, key, null);

		sendHandshakeMessage(key, false);
	}

	@Reference(bind = "-", unbind = "-")
	public void setDigitalSignature(DigitalSignature digitalSignature) {
		_digitalSignature = digitalSignature;
	}

	@Reference(bind = "-", unbind = "-")
	public void setLCSClusterNodeInstallationEnvironmentService(
		LCSClusterNodeInstallationEnvironmentService
			lcsClusterNodeInstallationEnvironmentService) {

		_lcsClusterNodeInstallationEnvironmentService =
			lcsClusterNodeInstallationEnvironmentService;
	}

	@Reference(bind = "-", unbind = "-")
	public void setLCSClusterNodeScriptService(
		LCSClusterNodeScriptService lcsClusterNodeScriptService) {

		_lcsClusterNodeScriptService = lcsClusterNodeScriptService;
	}

	@Reference(bind = "-", unbind = "-")
	public void setQueueManager(QueueManager queueManager) {
		_queueManager = queueManager;
	}

	@Override
	public void updateSignaturePublicKey(String key, String certificate) {
		sendCommandMessage(
			CommandMessage.COMMAND_TYPE_UPDATE_SIGNATURE_PUBLIC_KEY, key,
			certificate);
	}

	protected int getLCSPortletBuildNumber(String key) {
		LCSClusterNodeInstallationEnvironment
			lcsClusterNodeInstallationEnvironment =
				_lcsClusterNodeInstallationEnvironmentService.
					fetchLCSClusterNodeInstallationEnvironment(key);

		Map<String, String> softwareMetadata = new TreeMap<>(
			lcsClusterNodeInstallationEnvironment.getSoftwareMetadata());

		return Integer.parseInt(
			softwareMetadata.get("lcs.portlet.build.number"));
	}

	protected void sendCommandMessage(String type, String key, Object payload) {
		CommandMessage commandMessage = new CommandMessage();

		commandMessage.setCommandType(type);
		commandMessage.setCorrelationId(new Random().nextLong() + "");
		commandMessage.setCreateTime(System.currentTimeMillis());
		commandMessage.setKey(key);
		commandMessage.setPayload(payload);

		if (_log.isDebugEnabled()) {
			_log.debug("Sending command message " + commandMessage);
		}

		if (type == CommandMessage.COMMAND_TYPE_EXECUTE_SCRIPT) {
			_lcsClusterNodeScriptService.addLCSClusterNodeScript(
				(String)payload, commandMessage.getCorrelationId(), key);
		}

		_digitalSignature.signMessage(
			getLCSPortletBuildNumber(key), commandMessage);

		_queueManager.sendMessage(commandMessage);
	}

	protected void sendHandshakeMessage(String key) {
		sendHandshakeMessage(key, true);
	}

	protected void sendHandshakeMessage(String key, boolean deregister) {
		HandshakeMessage handshakeMessage = new HandshakeMessage();

		if (deregister) {
			handshakeMessage.put(Message.KEY_DEREGISTER, true);
		}

		handshakeMessage.put(Message.KEY_SIGN_OFF, String.valueOf(0));
		handshakeMessage.setKey(key);

		if (_log.isDebugEnabled()) {
			_log.debug("Sending handshake message " + handshakeMessage);
		}

		_digitalSignature.signMessage(
			getLCSPortletBuildNumber(key), handshakeMessage);

		_queueManager.sendMessage(handshakeMessage);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommandMessageAdvisorImpl.class);

	private DigitalSignature _digitalSignature;
	private LCSClusterNodeInstallationEnvironmentService
		_lcsClusterNodeInstallationEnvironmentService;
	private LCSClusterNodeScriptService _lcsClusterNodeScriptService;
	private QueueManager _queueManager;

}