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

package com.liferay.osb.lcs.advisor;

import com.liferay.portal.kernel.exception.PortalException;

import java.io.File;

import java.net.URL;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Igor Beslic
 */
public interface PatchAdvisor {

	public void downloadPatch(long[] lcsClusterNodeIds, String patchName)
		throws PortalException;

	public String getDownloadPatchStatus(
		long[] lcsClusterNodeIds, String lcsClusterNodeKeys, String patchId);

	public URL getPatchAsURL(String patchFileName);

	public List<String> getPatchFileNames(Date sinceModifiedDate);

	public List<String> getPatchFileNames(int buildNumber);

	public Map<String, Long> getPatchFileNamesSizes();

	public void resetActivePatchFileNames();

	public void resetInstallablePatches() throws PortalException;

	public void writePatchFile(File file);

}