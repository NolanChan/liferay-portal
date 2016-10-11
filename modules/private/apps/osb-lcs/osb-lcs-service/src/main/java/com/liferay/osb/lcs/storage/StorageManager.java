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

package com.liferay.osb.lcs.storage;

import java.io.File;

import java.util.Date;
import java.util.Map;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public interface StorageManager {

	public Map<String, Long> getFileNamesSizes();

	public Map<String, Long> getFileNamesSizes(Date sinceModifiedDate);

	public Map<String, Long> getFileNamesSizes(String nameFragment);

	public void setBucketName(String bucketName);

	public void setCredentials(String accessKey, String secretKey);

	public void setPath(String path);

	public void setPrefix(String prefix);

	public void writeFile(File file);

	public void writeFile(String key, String message, String throwable);

}