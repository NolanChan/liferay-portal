/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Affero General Public License as published by the Free
 * Software Foundation; version 3.0 of the License.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 */

package com.liferay.mobile.device.rules.recognition.provider.wurfl.internal.jmx;

import com.liferay.mobile.device.rules.recognition.provider.wurfl.internal.WURFLDeviceRecognitionProvider;

import javax.management.DynamicMBean;
import javax.management.NotCompliantMBeanException;
import javax.management.StandardMBean;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(
	immediate = true,
	property = {
		"jmx.objectname=com.liferay.mobile.device.rules.recognition.provider:classification=wurfl,name=WURFLDeviceRecognitionProviderManager",
		"jmx.objectname.cache.key=WURFLDeviceRecognitionProviderManager"
	},
	service = DynamicMBean.class
)
public class WURFLDeviceRecognitionProviderManager
	extends StandardMBean implements WURFLDeviceRecognitionProviderMBean {

	public WURFLDeviceRecognitionProviderManager()
		throws NotCompliantMBeanException {

		super(WURFLDeviceRecognitionProviderMBean.class);
	}

	@Override
	public void reload() throws Exception {
		_wurflDeviceRecognitionProvider.reload();
	}

	@Reference(unbind = "-")
	protected void setWURFLDeviceRecognitionProvider(
		WURFLDeviceRecognitionProvider wurflDeviceRecognitionProvider) {

		_wurflDeviceRecognitionProvider = wurflDeviceRecognitionProvider;
	}

	private WURFLDeviceRecognitionProvider _wurflDeviceRecognitionProvider;

}