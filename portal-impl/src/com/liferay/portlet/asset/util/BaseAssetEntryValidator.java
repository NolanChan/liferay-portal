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

import com.liferay.portal.NoSuchGroupException;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.PredicateFilter;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.asset.AssetCategoryException;
import com.liferay.portlet.asset.AssetRendererFactoryRegistryUtil;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetCategoryConstants;
import com.liferay.portlet.asset.model.AssetRendererFactory;
import com.liferay.portlet.asset.model.AssetVocabulary;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetVocabularyLocalServiceUtil;

import java.util.List;

/**
 * @author Juan Fernández
 */
public class BaseAssetEntryValidator implements AssetEntryValidator {

	@Override
	public void validate(
			long groupId, String className, long[] categoryIds,
			String[] entryNames)
		throws PortalException, SystemException {

		List<AssetVocabulary> vocabularies =
			AssetVocabularyLocalServiceUtil.getGroupVocabularies(
				groupId, false);

		Group group = GroupLocalServiceUtil.getGroup(groupId);

		if (!group.isCompany()) {
			try {
				Group companyGroup = GroupLocalServiceUtil.getCompanyGroup(
					group.getCompanyId());

				vocabularies = ListUtil.copy(vocabularies);

				vocabularies.addAll(
					AssetVocabularyLocalServiceUtil.getGroupVocabularies(
						companyGroup.getGroupId()));
			}
			catch (NoSuchGroupException nsge) {
			}
		}

		long classNameId = ClassNameLocalServiceUtil.getClassNameId(className);

		for (AssetVocabulary vocabulary : vocabularies) {
			validate(classNameId, categoryIds, vocabulary);
		}
	}

	protected void validate(
			long classNameId, final long[] categoryIds,
			AssetVocabulary vocabulary)
		throws PortalException, SystemException {

		if (!vocabulary.isAssociatedToAssetRendererFactory(classNameId)) {
			return;
		}

		String className = PortalUtil.getClassName(classNameId);

		AssetRendererFactory assetRendererFactory =
			AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(
				className);

		if ((assetRendererFactory == null) ||
			!assetRendererFactory.isCategorizable()) {

			return;
		}

		long[] requiredClassNameIds = vocabulary.getRequiredClassNameIds();

		List<AssetCategory> categories =
			AssetCategoryLocalServiceUtil.getVocabularyCategories(
				vocabulary.getVocabularyId(), QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null);

		PredicateFilter<AssetCategory> existingCategoryFilter =
			new PredicateFilter<AssetCategory>() {

				@Override
				public boolean filter(AssetCategory assetCategory) {
					return ArrayUtil.contains(
						categoryIds, assetCategory.getCategoryId());
				}

			};

		if ((requiredClassNameIds.length > 0) &&
			((requiredClassNameIds[0] ==
				AssetCategoryConstants.ALL_CLASS_NAME_IDS) ||
			 ArrayUtil.contains(requiredClassNameIds, classNameId))) {

			if (!ListUtil.exists(categories, existingCategoryFilter)) {
				throw new AssetCategoryException(
					vocabulary, AssetCategoryException.AT_LEAST_ONE_CATEGORY);
			}
		}

		if (!vocabulary.isMultiValued()) {
			if (ListUtil.count(categories, existingCategoryFilter) > 1) {
				throw new AssetCategoryException(
					vocabulary, AssetCategoryException.TOO_MANY_CATEGORIES);
			}
		}
	}

}