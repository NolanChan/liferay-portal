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

import com.liferay.osb.lcs.nosql.model.LCSClusterNodeInstallationEnvironment;
import com.liferay.osb.lcs.nosql.service.LCSClusterNodeInstallationEnvironmentService;
import com.liferay.osb.lcs.nosql.service.persistence.LCSClusterNodeInstallationEnvironmentPersistence;

import java.util.Date;
import java.util.Map;

/**
 * @author Igor Beslic
 */
public class LCSClusterNodeInstallationEnvironmentServiceImpl
	implements LCSClusterNodeInstallationEnvironmentService {

	@Override
	public LCSClusterNodeInstallationEnvironment
		addLCSClusterNodeInstallationEnvironment(
			Map<String, String> hardwareMetadata, String key,
			Map<String, String> softwareMetadata) {

		LCSClusterNodeInstallationEnvironment
			lcsClusterNodeInstallationEnvironment =
				_lcsClusterNodeInstallationEnvironmentPersistance.create();

		lcsClusterNodeInstallationEnvironment.setHardwareMetadata(
			hardwareMetadata);
		lcsClusterNodeInstallationEnvironment.setModifiedDate(new Date());
		lcsClusterNodeInstallationEnvironment.setKey(key);
		lcsClusterNodeInstallationEnvironment.setSoftwareMetadata(
			softwareMetadata);

		return _lcsClusterNodeInstallationEnvironmentPersistance.update(
			lcsClusterNodeInstallationEnvironment);
	}

	@Override
	public LCSClusterNodeInstallationEnvironment
		fetchLCSClusterNodeInstallationEnvironment(String key) {

		return _lcsClusterNodeInstallationEnvironmentPersistance.fetchByKey(
			key);
	}

	public void setLCSClusterNodeInstallationEnvironmentPersistance(
		LCSClusterNodeInstallationEnvironmentPersistence
			lcsClusterNodeInstallationEnvironmentPersistance) {

		_lcsClusterNodeInstallationEnvironmentPersistance =
			lcsClusterNodeInstallationEnvironmentPersistance;
	}

	@Override
	public void update(
		LCSClusterNodeInstallationEnvironment
			lcsClusterNodeInstallationEnvironment) {

		lcsClusterNodeInstallationEnvironment.setModifiedDate(new Date());

		_lcsClusterNodeInstallationEnvironmentPersistance.update(
			lcsClusterNodeInstallationEnvironment);
	}

	private LCSClusterNodeInstallationEnvironmentPersistence
		_lcsClusterNodeInstallationEnvironmentPersistance;

}