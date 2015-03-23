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

package com.liferay.portal.ac.profile.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.ac.profile.model.ServiceACProfile;
import com.liferay.portal.ac.profile.service.ServiceACProfileLocalServiceUtil;

/**
 * The extended model base implementation for the ServiceACProfile service. Represents a row in the &quot;ServiceACProfile&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ServiceACProfileImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ServiceACProfileImpl
 * @see com.liferay.portal.ac.profile.model.ServiceACProfile
 * @generated
 */
@ProviderType
public abstract class ServiceACProfileBaseImpl extends ServiceACProfileModelImpl
	implements ServiceACProfile {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a service a c profile model instance should use the {@link ServiceACProfile} interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			ServiceACProfileLocalServiceUtil.addServiceACProfile(this);
		}
		else {
			ServiceACProfileLocalServiceUtil.updateServiceACProfile(this);
		}
	}
}