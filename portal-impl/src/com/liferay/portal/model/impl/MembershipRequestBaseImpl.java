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

package com.liferay.portal.model.impl;

import com.liferay.portal.model.MembershipRequest;
import com.liferay.portal.service.MembershipRequestLocalServiceUtil;

/**
 * The extended model base implementation for the MembershipRequest service. Represents a row in the &quot;MembershipRequest&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link MembershipRequestImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MembershipRequestImpl
 * @see com.liferay.portal.model.MembershipRequest
 * @generated
 */
public abstract class MembershipRequestBaseImpl
	extends MembershipRequestModelImpl implements MembershipRequest {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a membership request model instance should use the {@link MembershipRequest} interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			MembershipRequestLocalServiceUtil.addMembershipRequest(this);
		}
		else {
			MembershipRequestLocalServiceUtil.updateMembershipRequest(this);
		}
	}
}