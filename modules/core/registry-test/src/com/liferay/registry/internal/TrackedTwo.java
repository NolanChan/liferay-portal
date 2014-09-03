/**
 * Copyright (c) 2000-2014 Liferay, Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.liferay.registry.internal;

/**
 * @author Carlos Sierra Andrés
 */
public class TrackedTwo {

	public TrackedTwo(TrackedOne trackedOne) {
		_trackedOne = trackedOne;
	}

	public TrackedOne getTrackedOne() {
		return _trackedOne;
	}

	private TrackedOne _trackedOne;
}
