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

package com.liferay.message.boards.social.test;

import com.liferay.message.boards.web.constants.MessageBoardsPortletKeys;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portlet.social.model.SocialActivityInterpreter;
import com.liferay.portlet.social.test.BaseSocialActivityInterpreterTestCase;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;

import java.util.Collection;

/**
 * @author Adolfo Pérez
 */
public abstract class BaseMBSocialActivityInterpreterTestCase
	extends BaseSocialActivityInterpreterTestCase {

	@Override
	protected SocialActivityInterpreter getActivityInterpreter() {
		try {
			Registry registry = RegistryUtil.getRegistry();

			Collection<SocialActivityInterpreter> socialActivityInterpreters =
				registry.getServices(
					SocialActivityInterpreter.class,
					"(javax.portlet.name=" +
						MessageBoardsPortletKeys.MESSAGE_BOARDS + ")");

			for (SocialActivityInterpreter socialActivityInterpreter :
					socialActivityInterpreters) {

				if (ArrayUtil.contains(
						socialActivityInterpreter.getClassNames(),
						getClassName())) {

					return socialActivityInterpreter;
				}
			}

			throw new IllegalStateException(
				"No activity interpreter found for class " + getClassName());
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected abstract String getClassName();

}