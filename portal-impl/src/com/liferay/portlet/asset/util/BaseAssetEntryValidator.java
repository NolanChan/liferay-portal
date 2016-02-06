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

package com.liferay.portlet.asset.util;

import com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil;
import com.liferay.asset.kernel.exception.AssetCategoryException;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;

import java.util.List;

/**
 * @author Juan Fernández
 */
public class BaseAssetEntryValidator implements AssetEntryValidator {

	@Override
	public void validate(
			long groupId, String className, long classTypePK,
			long[] categoryIds, String[] entryNames)
		throws PortalException {

		List<AssetVocabulary> vocabularies =
			AssetVocabularyLocalServiceUtil.getGroupVocabularies(
				groupId, false);

		Group group = GroupLocalServiceUtil.getGroup(groupId);

		if (!group.isCompany()) {
			Group companyGroup = GroupLocalServiceUtil.fetchCompanyGroup(
				group.getCompanyId());

			if (companyGroup != null) {
				vocabularies = ListUtil.copy(vocabularies);

				vocabularies.addAll(
					AssetVocabularyLocalServiceUtil.getGroupVocabularies(
						companyGroup.getGroupId()));
			}
		}

		long classNameId = ClassNameLocalServiceUtil.getClassNameId(className);

		for (AssetVocabulary vocabulary : vocabularies) {
			validate(classNameId, classTypePK, categoryIds, vocabulary);
		}
	}

	protected boolean isAssetCategorizable(long classNameId) {
		String className = PortalUtil.getClassName(classNameId);

		AssetRendererFactory<?> assetRendererFactory =
			AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(
				className);

		if ((assetRendererFactory == null) ||
			!assetRendererFactory.isCategorizable()) {

			return false;
		}

		return true;
	}

	protected void validate(
			long classNameId, long classTypePK, final long[] categoryIds,
			AssetVocabulary vocabulary)
		throws PortalException {

		if (!vocabulary.isAssociatedToClassNameIdAndClassTypePK(
				classNameId, classTypePK)) {

			return;
		}

		if (!isAssetCategorizable(classNameId)) {
			return;
		}

		if (vocabulary.isMissingRequiredCategory(
				classNameId, classTypePK, categoryIds)) {

			throw new AssetCategoryException(
				vocabulary, AssetCategoryException.AT_LEAST_ONE_CATEGORY);
		}

		if (!vocabulary.isMultiValued() &&
			vocabulary.hasMoreThanOneCategorySelected(categoryIds)) {

			throw new AssetCategoryException(
				vocabulary, AssetCategoryException.TOO_MANY_CATEGORIES);
		}
	}

}