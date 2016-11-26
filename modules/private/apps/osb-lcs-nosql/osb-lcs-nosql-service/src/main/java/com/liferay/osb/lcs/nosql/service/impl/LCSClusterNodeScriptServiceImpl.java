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

package com.liferay.osb.lcs.nosql.service.impl;

import com.liferay.osb.lcs.nosql.model.LCSClusterNodeScript;
import com.liferay.osb.lcs.nosql.service.LCSClusterNodeScriptService;
import com.liferay.osb.lcs.nosql.service.persistence.LCSClusterNodeScriptPersistence;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Ivica Cardic
 */
@Component(immediate = true, service = LCSClusterNodeScriptService.class)
public class LCSClusterNodeScriptServiceImpl
	implements LCSClusterNodeScriptService {

	@Override
	public LCSClusterNodeScript addLCSClusterNodeScript(
		String command, String correlationKey, String key) {

		LCSClusterNodeScript lcsClusterNodeScript =
			_lcsClusterNodeScriptPersistence.create();

		lcsClusterNodeScript.setCommand(command);
		lcsClusterNodeScript.setCorrelationKey(correlationKey);
		lcsClusterNodeScript.setKey(key);
		lcsClusterNodeScript.setModifiedDate(new Date());

		_lcsClusterNodeScriptPersistence.update(lcsClusterNodeScript);

		return lcsClusterNodeScript;
	}

	@Override
	public List<LCSClusterNodeScript> getLCSClusterNodeScripts(String key) {
		return _lcsClusterNodeScriptPersistence.findByKey(key);
	}

	public void setLCSClusterNodeScriptPersistence(
		LCSClusterNodeScriptPersistence lcsClusterNodeScriptPersistence) {

		_lcsClusterNodeScriptPersistence = lcsClusterNodeScriptPersistence;
	}

	@Override
	public LCSClusterNodeScript updateLCSClusterNodeScript(
		String correlationKey, String error, String result) {

		LCSClusterNodeScript lcsClusterNodeScript =
			_lcsClusterNodeScriptPersistence.findByCorrelationKey(
				correlationKey);

		lcsClusterNodeScript.setError(error);
		lcsClusterNodeScript.setModifiedDate(new Date());
		lcsClusterNodeScript.setResult(result);

		_lcsClusterNodeScriptPersistence.update(lcsClusterNodeScript);

		return lcsClusterNodeScript;
	}

	private LCSClusterNodeScriptPersistence _lcsClusterNodeScriptPersistence;

}