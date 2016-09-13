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

import com.liferay.osb.lcs.advisor.StringAdvisor;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Igor Beslic
 */
public class StringAdvisorImpl implements StringAdvisor {

	public String concat(Object... args) {
		if (args.length == 1) {
			return String.valueOf(args[0]);
		}
		else if (args.length == 2) {
			return String.valueOf(args[0]) + StringPool.SPACE +
				String.valueOf(args[1]);
		}

		StringBundler sb = new StringBundler((args.length * 2) - 1);

		for (int i = 0; i < args.length; i++) {
			sb.append(args[i]);

			if ((i + 1) < args.length) {
				sb.append(StringPool.SPACE);
			}
		}

		return sb.toString();
	}

}