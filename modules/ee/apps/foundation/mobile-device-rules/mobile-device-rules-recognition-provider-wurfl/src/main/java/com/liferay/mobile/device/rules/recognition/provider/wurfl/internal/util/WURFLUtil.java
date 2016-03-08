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

package com.liferay.mobile.device.rules.recognition.provider.wurfl.internal.util;

import com.liferay.mobile.device.rules.recognition.provider.wurfl.configuration.WURFLEngineConfiguration;
import com.liferay.portal.kernel.util.Props;
import com.liferay.portal.kernel.util.PropsKeys;

/**
 * @author Michael C. Han
 */
public class WURFLUtil {

	public static String getWURFLDatabasePatchDirName(
		WURFLEngineConfiguration wurflEngineConfiguration, Props props) {

		return
			props.get(PropsKeys.LIFERAY_HOME) +
				wurflEngineConfiguration.wurflDatabasePatchDirName();
	}

}