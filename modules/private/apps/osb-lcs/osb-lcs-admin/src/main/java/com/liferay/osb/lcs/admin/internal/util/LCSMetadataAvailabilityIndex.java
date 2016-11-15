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

package com.liferay.osb.lcs.admin.internal.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Matija Petanjek
 */
public enum LCSMetadataAvailabilityIndex {

	PORTAL_PROPERTIES_AVAILABLE("portal-properties-available", 0x01);

	public static LCSMetadataAvailabilityIndex[]
		getLCSMetadataAvailabilityIndexes(long index) {

		List<LCSMetadataAvailabilityIndex> lcsMetadataAvailabilityIndexes =
			new ArrayList<LCSMetadataAvailabilityIndex>();

		for (LCSMetadataAvailabilityIndex lcsMetadataAvailabilityIndex :
				values()) {

			if ((index & lcsMetadataAvailabilityIndex._index) ==
					lcsMetadataAvailabilityIndex._index) {

				lcsMetadataAvailabilityIndexes.add(
					lcsMetadataAvailabilityIndex);
			}
		}

		return lcsMetadataAvailabilityIndexes.toArray(
			new LCSMetadataAvailabilityIndex[
				lcsMetadataAvailabilityIndexes.size()]);
	}

	public static String[] toLabels(long index) {
		LCSMetadataAvailabilityIndex[] lcsMetadataAvailabilityIndexes =
			getLCSMetadataAvailabilityIndexes(index);

		String[] labels = new String[lcsMetadataAvailabilityIndexes.length];

		for (int i = 0; i < labels.length; i++) {
			labels[i] = lcsMetadataAvailabilityIndexes[i]._label;
		}

		return labels;
	}

	public long getIndex() {
		return _index;
	}

	public String getLabel() {
		return _label;
	}

	public boolean isAvailable(long index) {
		if ((index & _index) == _index) {
			return true;
		}

		return false;
	}

	public long merge(long index) {
		return _index | index;
	}

	private LCSMetadataAvailabilityIndex(String label, long index) {
		_label = label;
		_index = index;
	}

	private long _index;
	private String _label;

}