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

package com.liferay.portlet.exportimport.backgroundtask;

import com.liferay.portal.kernel.backgroundtask.BaseBackgroundTaskExecutor;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.model.BackgroundTask;
import com.liferay.portal.spring.transaction.TransactionAttributeBuilder;
import com.liferay.portlet.exportimport.model.ExportImportConfiguration;
import com.liferay.portlet.exportimport.service.ExportImportConfigurationLocalServiceUtil;
import com.liferay.portlet.exportimport.staging.StagingUtil;

import java.io.Serializable;

import java.util.Map;

import org.springframework.transaction.interceptor.TransactionAttribute;

/**
 * @author Akos Thurzo
 */
public abstract class BaseExportImportBackgroundTaskExecutor
	extends BaseBackgroundTaskExecutor {

	public BaseExportImportBackgroundTaskExecutor() {
		setBackgroundTaskStatusMessageTranslator(
			new DefaultExportImportBackgroundTaskStatusMessageTranslator());
	}

	@Override
	public String handleException(BackgroundTask backgroundTask, Exception e) {
		ExportImportConfiguration exportImportConfiguration =
			getExportImportConfiguration(backgroundTask);

		JSONObject jsonObject = StagingUtil.getExceptionMessagesJSONObject(
			getLocale(backgroundTask), e,
			exportImportConfiguration.getSettingsMap());

		return jsonObject.toString();
	}

	protected ExportImportConfiguration getExportImportConfiguration(
		BackgroundTask backgroundTask) {

		Map<String, Serializable> taskContextMap =
			backgroundTask.getTaskContextMap();

		long exportImportConfigurationId = MapUtil.getLong(
			taskContextMap, "exportImportConfigurationId");

		return ExportImportConfigurationLocalServiceUtil.
			fetchExportImportConfiguration(exportImportConfigurationId);
	}

	protected TransactionAttribute transactionAttribute =
		TransactionAttributeBuilder.build(
			Propagation.REQUIRED, new Class<?>[] {Exception.class});

}