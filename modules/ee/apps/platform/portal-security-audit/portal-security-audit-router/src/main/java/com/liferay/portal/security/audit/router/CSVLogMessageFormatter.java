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

package com.liferay.portal.security.audit.router;

import aQute.bnd.annotation.metatype.Configurable;

import com.liferay.portal.kernel.audit.AuditMessage;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.security.audit.router.configuration.CSVLogMessageFormatterConfiguration;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

/**
 * @author Mika Koivisto
 * @author Brian Greenwald
 * @author Prathima Shreenath
 */
@Component(
	configurationPid = "com.liferay.portal.security.audit.router.configuration.CSVLogMessageFormatterConfiguration",
	immediate = true,
	service = {CSVLogMessageFormatter.class, LogMessageFormatter.class}
)
public class CSVLogMessageFormatter implements LogMessageFormatter {

	public String format(AuditMessage auditMessage) {
		StringBundler sb = new StringBundler(_columns.length * 4 - 1);

		JSONObject jsonObject = auditMessage.toJSONObject();

		for (int i = 0; i < _columns.length; i++) {
			sb.append(StringPool.QUOTE);
			sb.append(jsonObject.getString(_columns[i]));
			sb.append(StringPool.QUOTE);

			if ((i + 1) < _columns.length) {
				sb.append(StringPool.COMMA);
			}
		}

		return sb.toString();
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		CSVLogMessageFormatterConfiguration
			csvLogMessageFormatterConfiguration =
				Configurable.createConfigurable(
					CSVLogMessageFormatterConfiguration.class, properties);

		_columns = csvLogMessageFormatterConfiguration.columns();
	}

	private String[] _columns;

}