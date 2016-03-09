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

package com.liferay.portal.security.audit.router.internal;

import com.liferay.portal.kernel.audit.AuditException;
import com.liferay.portal.kernel.audit.AuditMessage;
import com.liferay.portal.kernel.audit.AuditRouter;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.audit.AuditMessageProcessor;
import com.liferay.portal.security.audit.router.constants.AuditConstants;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Michael C. Han
 * @author Brian Wing Shun Chan
 * @author Brian Greenwald
 * @author Prathima Shreenath
 */
@Component(
	immediate = true, property = "audit.router.proxy=false",
	service = AuditRouter.class
)
public class DefaultAuditRouter implements AuditRouter {

	@Override
	public boolean isDeployed() {
		int auditMessageProcessorsCount = _auditMessageProcessors.size();

		if ((auditMessageProcessorsCount > 0) ||
			!_globalAuditMessageProcessors.isEmpty()) {

			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public void route(AuditMessage auditMessage) throws AuditException {
		for (AuditMessageProcessor globalAuditMessageProcessor :
				_globalAuditMessageProcessors) {

			globalAuditMessageProcessor.process(auditMessage);
		}

		String eventType = auditMessage.getEventType();

		Set<AuditMessageProcessor> auditMessageProcessors =
			_auditMessageProcessors.get(eventType);

		if (auditMessageProcessors != null) {
			for (AuditMessageProcessor auditMessageProcessor :
					auditMessageProcessors) {

				auditMessageProcessor.process(auditMessage);
			}
		}
	}

	protected String[] getEventTypes(
		AuditMessageProcessor auditMessageProcessor,
		Map<String, Object> properties) {

		String eventTypes = (String)properties.get(AuditConstants.EVENT_TYPES);

		if (Validator.isNull(eventTypes)) {
			throw new IllegalArgumentException(
				"No eventType specified for: " + auditMessageProcessor);
		}

		String[] eventTypesArray = StringUtil.split(eventTypes);

		return eventTypesArray;
	}

	@Reference (
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY,
		unbind = "unsetAuditMessageProcessor"
	)
	protected void setAuditMessageProcessor(
		AuditMessageProcessor auditMessageProcessor,
		Map<String, Object> properties) {

		String[] eventTypes = getEventTypes(auditMessageProcessor, properties);

		if ((eventTypes.length == 1) && eventTypes[0].equals(StringPool.STAR)) {
			_globalAuditMessageProcessors.add(auditMessageProcessor);

			return;
		}

		for (String eventType : eventTypes) {
			Set<AuditMessageProcessor> auditMessageProcessorsSet =
				_auditMessageProcessors.get(eventType);

			if (auditMessageProcessorsSet == null) {
				auditMessageProcessorsSet = new HashSet<>();

				_auditMessageProcessors.put(
					eventType, auditMessageProcessorsSet);
			}

			auditMessageProcessorsSet.add(auditMessageProcessor);
		}
	}

	protected void unsetAuditMessageProcessor(
		AuditMessageProcessor auditMessageProcessor,
		Map<String, Object> properties) {

		String[] eventTypes = getEventTypes(auditMessageProcessor, properties);

		if ((eventTypes.length == 1) && eventTypes[0].equals(StringPool.STAR)) {
			_globalAuditMessageProcessors.remove(auditMessageProcessor);

			return;
		}

		for (String eventType : eventTypes) {
			Set<AuditMessageProcessor> auditMessageProcessorsSet =
				_auditMessageProcessors.get(eventType);

			if (auditMessageProcessorsSet == null) {
				continue;
			}

			auditMessageProcessorsSet.remove(auditMessageProcessor);
		}
	}

	private final Map<String, Set<AuditMessageProcessor>>
		_auditMessageProcessors = new ConcurrentHashMap<>();
	private final List<AuditMessageProcessor> _globalAuditMessageProcessors =
		new CopyOnWriteArrayList<>();

}