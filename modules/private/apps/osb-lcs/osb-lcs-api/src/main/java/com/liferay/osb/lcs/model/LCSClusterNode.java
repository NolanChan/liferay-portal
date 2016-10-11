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
 * The extended model interface for the LCSClusterNode service. Represents a row in the &quot;OSBLCS_LCSClusterNode&quot; database table, with each column mapped to a property of this class.
 *
 * @author Igor Beslic
 * @see LCSClusterNodeModel
 * @see com.liferay.osb.lcs.model.impl.LCSClusterNodeImpl
 * @see com.liferay.osb.lcs.model.impl.LCSClusterNodeModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.osb.lcs.model.impl.LCSClusterNodeImpl")
@ProviderType
public interface LCSClusterNode extends LCSClusterNodeModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.lcs.model.impl.LCSClusterNodeImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<LCSClusterNode, Long> LCS_CLUSTER_NODE_ID_ACCESSOR =
		new Accessor<LCSClusterNode, Long>() {
			@Override
			public Long get(LCSClusterNode lcsClusterNode) {
				return lcsClusterNode.getLcsClusterNodeId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<LCSClusterNode> getTypeClass() {
				return LCSClusterNode.class;
			}
		};

	public java.util.Date getConfigurationModifiedDate();

	public java.util.List<java.lang.String> getInstallablePatchNames();

	public int getLCSClusterNodeClusterLinkStatus();

	public int getMetricsLCSServiceStatus();

	public int getMonitoringStatus();

	public int getPatchesLCSServiceStatus();

	public java.util.Map<java.lang.String, java.lang.Integer> getPatchIdsStatuses();

	public int getPatchingToolStatus();

	public int getPatchingToolVersion();

	public java.lang.String getPortalEdition();

	public int getPortalPropertiesLCSServiceStatus();

	public boolean hasStatus(int status);

	public boolean isLCSCLusterNodeClusterLinkHealthy();

	public boolean isLCSNotificationEnabled(long userId, int type);

	public boolean isMetricsLCSServiceEnabled();

	public boolean isMonitoringUnavailable();

	public boolean isOffline();

	public boolean isPatchesLCSServiceEnabled();

	public boolean isPatchingToolUnavailable();

	public boolean isPortalPropertiesLCSServiceEnabled();

	public void setConfigurationModifiedDate(
		java.util.Date configurationModifiedDate);

	public void setInstallablePatchNames(
		java.util.List<java.lang.String> installablePatchNames);

	public void setPatchIdsStatuses(
		java.util.Map<java.lang.String, java.lang.Integer> patchIdsStatuses);

	public void setPatchingToolVersion(int patchingToolVersion);

	public void setPortalEdition(java.lang.String portalEdition);
}