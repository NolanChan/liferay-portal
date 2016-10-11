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

package com.liferay.osb.lcs.util.comparator;

import com.liferay.osb.lcs.model.LCSMessage;
import com.liferay.osb.lcs.model.UserLCSMessage;

import java.util.Comparator;
import java.util.Date;

/**
 * @author Matija Petanjek
 */
public class UserLCSMessageModifiedDateComparator
	implements Comparator<UserLCSMessage> {

	@Override
	public int compare(
		UserLCSMessage userLCSMessage1, UserLCSMessage userLCSMessage2) {

		try {
			LCSMessage lcsMessage1 = userLCSMessage1.getLcsMessage();
			LCSMessage lcsMessage2 = userLCSMessage2.getLcsMessage();

			Date modifiedDate1 = lcsMessage1.getModifiedDate();
			Date modifiedDate2 = lcsMessage2.getModifiedDate();

			return modifiedDate2.compareTo(modifiedDate1);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}