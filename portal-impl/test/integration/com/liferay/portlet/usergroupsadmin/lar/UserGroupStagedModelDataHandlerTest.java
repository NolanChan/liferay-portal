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

package com.liferay.portlet.usergroupsadmin.lar;

import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.lar.BaseStagedModelDataHandlerTestCase;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.StagedModel;
import com.liferay.portal.model.UserGroup;
import com.liferay.portal.service.UserGroupLocalServiceUtil;
import com.liferay.portal.test.DeleteAfterTestRun;
import com.liferay.portal.test.TransactionalMethodRule;
import com.liferay.portal.test.listeners.MainServletExecutionTestListener;
import com.liferay.portal.test.runners.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.util.test.UserGroupTestUtil;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Rule;
import org.junit.runner.RunWith;

/**
 * @author David Mendez Gonzalez
 */
@ExecutionTestListeners(listeners = {MainServletExecutionTestListener.class})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class UserGroupStagedModelDataHandlerTest
	extends BaseStagedModelDataHandlerTestCase {

	@After
	@Override
	public void tearDown() throws Exception {
		super.tearDown();

		_userGroup = UserGroupLocalServiceUtil.fetchUserGroupByUuidAndCompanyId(
			_userGroup.getUuid(), _userGroup.getCompanyId());
	}

	@Rule
	public TransactionalMethodRule transactionalMethodRule =
		new TransactionalMethodRule();

	@Override
	protected StagedModel addStagedModel(
			Group group,
			Map<String, List<StagedModel>> dependentStagedModelsMap)
		throws Exception {

		_userGroup = UserGroupTestUtil.addUserGroup();

		return _userGroup;
	}

	@Override
	protected void deleteStagedModel(
			StagedModel stagedModel,
			Map<String, List<StagedModel>> dependentStagedModelsMap,
			Group group)
		throws Exception {

		UserGroupLocalServiceUtil.deleteUserGroup((UserGroup)stagedModel);
	}

	@Override
	protected StagedModel getStagedModel(String uuid, Group group) {
		try {
			return UserGroupLocalServiceUtil.fetchUserGroupByUuidAndCompanyId(
				uuid, group.getCompanyId());
		}
		catch (Exception e) {
			return null;
		}
	}

	@Override
	protected Class<? extends StagedModel> getStagedModelClass() {
		return UserGroup.class;
	}

	@DeleteAfterTestRun
	private UserGroup _userGroup;

}