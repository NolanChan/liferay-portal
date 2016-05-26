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

package com.liferay.portal.cluster.multiple.internal.portal.profile;

import com.liferay.portal.cluster.multiple.internal.ClusterExecutorImpl;
import com.liferay.portal.cluster.multiple.internal.ClusterLinkImpl;
import com.liferay.portal.cluster.multiple.internal.ClusterMasterExecutorImpl;
import com.liferay.portal.cluster.multiple.internal.jgroups.JGroupsClusterChannelFactory;
import com.liferay.portal.profile.BaseDSModulePortalProfile;
import com.liferay.portal.profile.PortalProfile;

import java.util.Collections;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author Shuyang Zhou
 */
@Component(immediate = true, service = PortalProfile.class)
public class ModulePortalProfile extends BaseDSModulePortalProfile {

	@Activate
	public void activate(ComponentContext componentContext) {
		init(
			componentContext,
			Collections.singleton(PortalProfile.PORTAL_PROFILE_NAME_DXP),
			ClusterExecutorImpl.class.getName(),
			ClusterLinkImpl.class.getName(),
			ClusterMasterExecutorImpl.class.getName(),
			JGroupsClusterChannelFactory.class.getName());
	}

}