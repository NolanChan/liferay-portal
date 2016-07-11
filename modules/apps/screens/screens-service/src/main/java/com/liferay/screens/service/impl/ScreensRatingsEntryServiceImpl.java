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

package com.liferay.screens.service.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.ratings.kernel.model.RatingsEntry;
import com.liferay.screens.service.base.ScreensRatingsEntryServiceBaseImpl;

import java.util.List;

/**
 * @author Alejandro Hernández Malillos
 */
@ProviderType
public class ScreensRatingsEntryServiceImpl
	extends ScreensRatingsEntryServiceBaseImpl {

	public JSONObject deleteRatingEntry(
			long classPK, String className, int stepCount)
		throws PortalException {

		ratingsEntryLocalService.deleteEntry(getUserId(), className, classPK);

		return getRatingsEntries(classPK, className, stepCount);
	}

	public JSONObject getRatingsEntries(long entryId, int stepCount)
		throws PortalException {

		AssetEntry entry = assetEntryLocalService.fetchEntry(entryId);

		return getRatingsEntries(
			entry.getClassPK(), entry.getClassName(), stepCount);
	}

	public JSONObject getRatingsEntries(
			long classPK, String className, int stepCount)
		throws PortalException {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("className", className);
		jsonObject.put("classPK", classPK);

		List<RatingsEntry> entries = ratingsEntryLocalService.getEntries(
			className, classPK);

		int[] ratings = new int[stepCount];
		double totalScore = 0;

		long userId = getUserId();
		double userScore = -1;

		for (RatingsEntry entry : entries) {
			int position = (int) (entry.getScore() * stepCount);

			if (position == stepCount) {
				position--;
			}

			ratings[position]++;
			totalScore += entry.getScore();

			if (entry.getUserId() == userId) {
				userScore = entry.getScore();
			}
		}

		if (entries.size() > 0) {
			jsonObject.put("average", totalScore / entries.size());
		}
		else {
			jsonObject.put("average", 0);
		}

		jsonObject.put("ratings", ratings);
		jsonObject.put("totalCount", entries.size());
		jsonObject.put("totalScore", totalScore);
		jsonObject.put("userScore", userScore);

		return jsonObject;
	}

	public JSONObject updateRatingEntry(
			long classPK, String className, double score, int stepCount)
		throws PortalException {

		ratingsEntryLocalService.updateEntry(
			getUserId(), className, classPK, score, new ServiceContext());

		return getRatingsEntries(classPK, className, stepCount);
	}

}