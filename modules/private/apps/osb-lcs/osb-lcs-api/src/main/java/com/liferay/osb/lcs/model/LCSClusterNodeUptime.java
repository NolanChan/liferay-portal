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

package com.liferay.osb.lcs.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the LCSClusterNodeUptime service. Represents a row in the &quot;OSBLCS_LCSClusterNodeUptime&quot; database table, with each column mapped to a property of this class.
 *
 * @author Igor Beslic
 * @see LCSClusterNodeUptimeModel
 * @see com.liferay.osb.lcs.model.impl.LCSClusterNodeUptimeImpl
 * @see com.liferay.osb.lcs.model.impl.LCSClusterNodeUptimeModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.osb.lcs.model.impl.LCSClusterNodeUptimeImpl")
@ProviderType
public interface LCSClusterNodeUptime extends LCSClusterNodeUptimeModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.lcs.model.impl.LCSClusterNodeUptimeImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<LCSClusterNodeUptime, Long> LCS_CLUSTER_NODE_UPTIME_ID_ACCESSOR =
		new Accessor<LCSClusterNodeUptime, Long>() {
			@Override
			public Long get(LCSClusterNodeUptime lcsClusterNodeUptime) {
				return lcsClusterNodeUptime.getLcsClusterNodeUptimeId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<LCSClusterNodeUptime> getTypeClass() {
				return LCSClusterNodeUptime.class;
			}
		};

	public double getAmount();

	public long getLcsClusterEntryId();

	public java.lang.String getLcsClusterEntryName();

	public java.lang.String getLcsClusterNodeKey();

	public java.lang.String getLcsClusterNodeName();

	public java.lang.String getLcsProjectName();

	public double getRate();

	public double getUptime();

	public void setAmount(double amount);

	public void setLcsClusterEntryId(long lcsClusterEntryId);

	public void setLcsClusterEntryName(java.lang.String lcsClusterEntryName);

	public void setLcsClusterNodeKey(java.lang.String lcsClusterNodeKey);

	public void setLcsClusterNodeName(java.lang.String lcsClusterNodeName);

	public void setLcsProjectName(java.lang.String lcsProjectName);

	public void setRate(double rate);

	public void setUptime(double uptime);
}