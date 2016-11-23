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

import com.liferay.osb.lcs.advisor.LCSPortletLogAdvisor;
import com.liferay.osb.lcs.advisor.StringAdvisor;
import com.liferay.osb.lcs.configuration.OSBLCSConfiguration;
import com.liferay.osb.lcs.storage.StorageManager;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Igor Beslic
 */
@Component(
	configurationPid = "com.liferay.osb.lcs.configuration.OSBLCSConfiguration",
	configurationPolicy = ConfigurationPolicy.REQUIRE, immediate = true,
	service = LCSPortletLogAdvisor.class
)
public class LCSPortletLogAdvisorImpl implements LCSPortletLogAdvisor {

	@Reference(bind = "-", unbind = "-")
	public void setStringAdvisor(StringAdvisor stringAdvisor) {
		_stringAdvisor = stringAdvisor;
	}

	@Override
	public void writeToFile(String key, String message, String throwable) {
		_storageManager.writeFile(key, message, throwable);
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_osbLCSConfiguration = ConfigurableUtil.createConfigurable(
			OSBLCSConfiguration.class, properties);

		try {
			Class<?> storageManagerClass = Class.forName(
				_osbLCSConfiguration.logStorageManagerClassName());

			_storageManager = (StorageManager)storageManagerClass.newInstance();

			_storageManager.setCredentials(
				_osbLCSConfiguration.storageManagerAccessKey(),
				_osbLCSConfiguration.storageManagerSecretKey());
			_storageManager.setBucketName(
				_osbLCSConfiguration.logStorageManagerBucketName());
			_storageManager.setPath(
				_osbLCSConfiguration.logStorageManagerPath());
			_storageManager.setPrefix(
				_osbLCSConfiguration.logStorageManagerPrefix());
		}
		catch (Exception e) {
			throw new SystemException(
				_stringAdvisor.concat(
					"Unsupported storage manager class", e.getMessage()));
		}
	}

	@Deactivate
	protected void deactivate() {
		_osbLCSConfiguration = null;
	}

	private static volatile OSBLCSConfiguration _osbLCSConfiguration;

	private StorageManager _storageManager;
	private StringAdvisor _stringAdvisor;

}