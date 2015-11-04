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

package com.liferay.portal.workflow.kaleo.action.executor.internal;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.workflow.kaleo.action.executor.ActionExecutor;
import com.liferay.portal.workflow.kaleo.model.KaleoAction;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.runtime.action.ActionExecutorManager;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Leonardo Barros
 */
@Component(immediate = true)
public class ActionExecutorManagerImpl implements ActionExecutorManager {

	@Override
	public void executeKaleoAction(
			KaleoAction kaleoAction, ExecutionContext executionContext)
		throws PortalException {

		ActionExecutor actionExecutor =
			_actionExecutorTracker.getActionExecutor(
				kaleoAction.getScriptLanguage(), kaleoAction.getScript());

		actionExecutor.execute(kaleoAction, executionContext);
	}

	@Reference(unbind = "-")
	protected void setActionExecutorTracker(
		ActionExecutorTracker actionExecutorTracker) {

		_actionExecutorTracker = actionExecutorTracker;
	}

	private ActionExecutorTracker _actionExecutorTracker;

}