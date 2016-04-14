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

package com.liferay.portal.workflow.kaleo.forms.web.display.context;

import com.liferay.dynamic.data.mapping.exception.StorageException;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.dynamic.data.mapping.storage.StorageEngine;

/**
 * @author Leonardo Barros
 */
public class KaleoFormsDisplayContext {

	public KaleoFormsDisplayContext(StorageEngine storageEngine) {
		_storageEngine = storageEngine;
	}

	public DDMFormValues getDDMFormValues(long ddmStorageId)
		throws StorageException {

		return _storageEngine.getDDMFormValues(ddmStorageId);
	}

	private final StorageEngine _storageEngine;

}