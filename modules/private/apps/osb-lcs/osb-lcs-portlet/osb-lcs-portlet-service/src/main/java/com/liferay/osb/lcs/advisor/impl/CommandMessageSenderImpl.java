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

package com.liferay.osb.lcs.messaging.impl;

import com.liferay.lcs.messaging.CommandMessage;
import com.liferay.lcs.messaging.HandshakeMessage;
import com.liferay.lcs.messaging.Message;
import com.liferay.lcs.security.DigitalSignature;
import com.liferay.lcs.util.PatchUtil;
import com.liferay.osb.lcs.messaging.CommandMessageSender;
import com.liferay.osb.lcs.model.LCSClusterNode;
import com.liferay.osb.lcs.nosql.model.LCSClusterNodeInstallationEnvironment;
import com.liferay.osb.lcs.nosql.service.LCSClusterNodeInstallationEnvironmentServiceUtil;
import com.liferay.osb.lcs.nosql.service.LCSClusterNodeScriptService;
import com.liferay.osb.lcs.queue.QueueManager;
import com.liferay.osb.lcs.service.permission.LCSClusterEntryPermission;
import com.liferay.osb.lcs.storage.PatchStorageManager;
import com.liferay.osb.lcs.util.ActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.security.permission.PermissionThreadLocal;

import java.net.URL;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class CommandMessageSenderImpl implements CommandMessageSender {

	@Override
	public void deregister(String key) {
		sendCommandMessage(CommandMessage.COMMAND_TYPE_DEREGISTER, key, null);
		sendHandshakeMessage(key);
	}

	@Override
	public void downloadPatches(
			LCSClusterNode lcsClusterNode, List<String> patchNames)
		throws PortalException, SystemException {

		LCSClusterEntryPermission.check(
			PermissionThreadLocal.getPermissionChecker(),
			lcsClusterNode.getLcsClusterEntryId(), ActionKeys.DOWNLOAD_PATCH);

		Map<String, String> payload = new HashMap<String, String>();

		for (String patchName : patchNames) {
			String patchFileName = PatchUtil.getPatchFileName(patchName);

			URL url = _patchStorageManager.getPatchAsURL(patchFileName);

			payload.put(patchFileName, url.toString());
		}

		sendCommandMessage(
			CommandMessage.COMMAND_TYPE_DOWNLOAD_PATCHES,
			lcsClusterNode.getKey(), payload);
	}

	@Override
	public void executeScript(LCSClusterNode lcsClusterNode, String script)
		throws PortalException, SystemException {

		LCSClusterEntryPermission.check(
			PermissionThreadLocal.getPermissionChecker(),
			lcsClusterNode.getLcsClusterEntryId(), ActionKeys.EXECUTE_COMMAND);

		sendCommandMessage(
			CommandMessage.COMMAND_TYPE_EXECUTE_SCRIPT, lcsClusterNode.getKey(),
			script);
	}

	@Override
	public void invalidateLCSClusterEntryToken(String key) {
		sendCommandMessage(CommandMessage.COMMAND_TYPE_DEREGISTER, key, null);

		sendHandshakeMessage(key, false);
	}

	public void setDigitalSignature(DigitalSignature digitalSignature) {
		_digitalSignature = digitalSignature;
	}

	public void setLCSClusterNodeScriptService(
		LCSClusterNodeScriptService lcsClusterNodeScriptService) {

		_lcsClusterNodeScriptService = lcsClusterNodeScriptService;
	}

	public void setPatchStorageManager(
		PatchStorageManager patchStorageManager) {

		_patchStorageManager = patchStorageManager;
	}

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
				LCSClusterNodeInstallationEnvironmentServiceUtil.
					fetchLCSClusterNodeInstallationEnvironment(key);

		Map<String, String> softwareMetadata = new TreeMap<String, String>(
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

	private static Log _log = LogFactoryUtil.getLog(
		CommandMessageSenderImpl.class);

	private DigitalSignature _digitalSignature;
	private LCSClusterNodeScriptService _lcsClusterNodeScriptService;
	private PatchStorageManager _patchStorageManager;
	private QueueManager _queueManager;

}