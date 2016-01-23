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

package com.liferay.portal.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the MembershipRequest service. Represents a row in the &quot;MembershipRequest&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see MembershipRequestModel
 * @see com.liferay.portal.model.impl.MembershipRequestImpl
 * @see com.liferay.portal.model.impl.MembershipRequestModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.portal.model.impl.MembershipRequestImpl")
@ProviderType
public interface MembershipRequest extends MembershipRequestModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.portal.model.impl.MembershipRequestImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<MembershipRequest, Long> MEMBERSHIP_REQUEST_ID_ACCESSOR =
		new Accessor<MembershipRequest, Long>() {
			@Override
			public Long get(MembershipRequest membershipRequest) {
				return membershipRequest.getMembershipRequestId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<MembershipRequest> getTypeClass() {
				return MembershipRequest.class;
			}
		};
}