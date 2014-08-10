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

package com.liferay.portal.verify.extender.internal;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ClassUtil;
import com.liferay.portal.verify.VerifyException;
import com.liferay.portal.verify.VerifyProcess;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Miguel Pastor
 */
@Component(
	immediate = true,
	property = {
		"osgi.command.function=execute",
		"osgi.command.function=list",
		"osgi.command.scope=verify-extender"
	},
	service = {Object.class}
)
public class VerifyProcessTracker {

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY,
		target = "(verify.process.name=*)",
		unbind = "removedService",
		updated = "modifiedService"
	)
	public void addingService(
		VerifyProcess verifyProcess, Map<String, Object> properties) {

		String verifyProcessName = (String)properties.get(
			"verify.process.name");

		_verifyProcesses.put(verifyProcessName, verifyProcess);

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Executing the verify process " +
					ClassUtil.getClassName(verifyProcess.getClass()));
		}

		try {
			verifyProcess.verify();
		}
		catch (VerifyException ve) {
			_log.error(
				"A verify exception was thrown while executing the verify " +
					"process " +
						ClassUtil.getClassName(verifyProcess.getClass()),
				ve);
		}
	}

	public void execute(String verifyProcessName) throws VerifyException {
		VerifyProcess verifyProcess = _verifyProcesses.get(verifyProcessName);

		if (verifyProcess == null) {
			System.out.println(
				"Unable to find a verify process with the name " +
					verifyProcessName);

			return;
		}

		verifyProcess.verify();
	}

	public void list() {
		for (Map.Entry<String, VerifyProcess> entry :
				_verifyProcesses.entrySet()) {

			System.out.println(
				"Verify process " + ClassUtil.getClassName(entry.getValue()) +
					" is registered with the name " + entry.getKey());
		}
	}

	public void modifiedService(
		VerifyProcess verifyProcess, Map<String, Object> properties) {

		removedService(verifyProcess, properties);
		addingService(verifyProcess, properties);
	}

	public void removedService(
		VerifyProcess verifyProcess, Map<String, Object> properties) {

		String verifyProcessName = (String)properties.get(
			"verify.process.name");

		_verifyProcesses.remove(verifyProcessName, verifyProcess);
	}

	private static Log _log = LogFactoryUtil.getLog(VerifyProcessTracker.class);

	private ConcurrentMap<String, VerifyProcess> _verifyProcesses =
		new ConcurrentHashMap<String, VerifyProcess>();

}