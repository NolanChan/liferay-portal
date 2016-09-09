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

package com.liferay.portal.service.impl;

import com.liferay.exportimport.kernel.staging.LayoutStagingUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.model.LayoutSetBranch;
import com.liferay.portal.kernel.model.LayoutSetStagingHandler;
import com.liferay.portal.kernel.service.LayoutSetLocalService;
import com.liferay.portal.kernel.util.ClassLoaderUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portlet.exportimport.staging.StagingAdvicesThreadLocal;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author Julio Camarero
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public class LayoutSetLocalServiceStagingAdvice
	extends LayoutSetLocalServiceImpl implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		if (!StagingAdvicesThreadLocal.isEnabled()) {
			return methodInvocation.proceed();
		}

		Method method = methodInvocation.getMethod();

		String methodName = method.getName();

		if (!_layoutSetLocalServiceStagingAdviceMethodNames.contains(
				methodName)) {

			return wrapReturnValue(methodInvocation.proceed());
		}

		Object returnValue = null;

		Object thisObject = methodInvocation.getThis();
		Object[] arguments = methodInvocation.getArguments();

		if (methodName.equals("updateSettings")) {
			returnValue = updateSettings(
				(LayoutSetLocalService)thisObject, (Long)arguments[0],
				(Boolean)arguments[1], (String)arguments[2]);
		}
		else {
			returnValue = methodInvocation.proceed();
		}

		return wrapReturnValue(returnValue);
	}

	public LayoutSet updateSettings(
			LayoutSetLocalService target, long groupId, boolean privateLayout,
			String settings)
		throws PortalException {

		LayoutSet layoutSet = layoutSetPersistence.findByG_P(
			groupId, privateLayout);

		LayoutSetBranch layoutSetBranch = _getLayoutSetBranch(layoutSet);

		if (layoutSetBranch == null) {
			return target.updateSettings(groupId, privateLayout, settings);
		}

		layoutSetBranch.setModifiedDate(new Date());
		layoutSetBranch.setSettings(settings);

		layoutSetBranchPersistence.update(layoutSetBranch);

		return layoutSet;
	}

	protected LayoutSet unwrapLayoutSet(LayoutSet layoutSet) {
		LayoutSetStagingHandler layoutSetStagingHandler =
			LayoutStagingUtil.getLayoutSetStagingHandler(layoutSet);

		if (layoutSetStagingHandler == null) {
			return layoutSet;
		}

		return layoutSetStagingHandler.getLayoutSet();
	}

	protected LayoutSet wrapLayoutSet(LayoutSet layoutSet) {
		LayoutSetStagingHandler layoutSetStagingHandler =
			LayoutStagingUtil.getLayoutSetStagingHandler(layoutSet);

		if (layoutSetStagingHandler != null) {
			return layoutSet;
		}

		Group group = null;

		try {
			group = layoutSet.getGroup();
		}
		catch (Exception e) {
			return layoutSet;
		}

		if (!LayoutStagingUtil.isBranchingLayoutSet(
				group, layoutSet.getPrivateLayout())) {

			return layoutSet;
		}

		return (LayoutSet)ProxyUtil.newProxyInstance(
			ClassLoaderUtil.getPortalClassLoader(),
			new Class[] {LayoutSet.class},
			new LayoutSetStagingHandler(layoutSet));
	}

	protected List<LayoutSet> wrapLayoutSets(List<LayoutSet> layoutSets) {
		if (layoutSets.isEmpty()) {
			return layoutSets;
		}

		List<LayoutSet> wrappedLayoutSets = new ArrayList<>(layoutSets.size());

		for (int i = 0; i < layoutSets.size(); i++) {
			LayoutSet wrappedLayoutSet = wrapLayoutSet(layoutSets.get(i));

			wrappedLayoutSets.add(wrappedLayoutSet);
		}

		return wrappedLayoutSets;
	}

	protected Object wrapReturnValue(Object returnValue) {
		if (returnValue instanceof LayoutSet) {
			returnValue = wrapLayoutSet((LayoutSet)returnValue);
		}
		else if (returnValue instanceof List<?>) {
			List<?> list = (List<?>)returnValue;

			if (!list.isEmpty() && (list.get(0) instanceof LayoutSet)) {
				returnValue = wrapLayoutSets((List<LayoutSet>)returnValue);
			}
		}

		return returnValue;
	}

	private LayoutSetBranch _getLayoutSetBranch(LayoutSet layoutSet)
		throws PortalException {

		LayoutSetStagingHandler layoutSetStagingHandler =
			LayoutStagingUtil.getLayoutSetStagingHandler(layoutSet);

		if (layoutSetStagingHandler != null) {
			return layoutSetStagingHandler.getLayoutSetBranch();
		}

		if (LayoutStagingUtil.isBranchingLayoutSet(
				layoutSet.getGroup(), layoutSet.getPrivateLayout())) {

			layoutSetStagingHandler = new LayoutSetStagingHandler(layoutSet);

			return layoutSetStagingHandler.getLayoutSetBranch();
		}

		return null;
	}

	private static final Set<String>
		_layoutSetLocalServiceStagingAdviceMethodNames = new HashSet<>();

	static {
		_layoutSetLocalServiceStagingAdviceMethodNames.add("updateSettings");
	}

}