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

package com.liferay.osgi.util.test.instances;

import com.liferay.osgi.util.service.annotations.Reference;
import com.liferay.osgi.util.test.services.InterfaceOne;
import com.liferay.osgi.util.test.services.InterfaceTwo;

/**
 * @author Carlos Sierra Andrés
 */
public class TestInterface {

	public InterfaceOne getTrackedOne() {
		return _trackedOne;
	}

	public InterfaceTwo getTrackedTwo() {
		return _trackedTwo;
	}

	@Reference
	public void setTrackedOne(InterfaceOne trackedOne) {
		_trackedOne = trackedOne;
	}

	@Reference
	public void setTrackedTwo(InterfaceTwo trackedTwo) {
		_trackedTwo = trackedTwo;
	}

	private InterfaceOne _trackedOne;
	private InterfaceTwo _trackedTwo;

}