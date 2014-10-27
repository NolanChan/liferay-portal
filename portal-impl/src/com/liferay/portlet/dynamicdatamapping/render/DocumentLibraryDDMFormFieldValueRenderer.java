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

package com.liferay.portlet.dynamicdatamapping.render;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;
import com.liferay.portlet.dynamicdatamapping.model.DDMFormFieldType;
import com.liferay.portlet.dynamicdatamapping.model.Value;
import com.liferay.portlet.dynamicdatamapping.storage.DDMFormFieldValue;

import java.util.Locale;

/**
 * @author Bruno Basto
 * @author Marcellus Tavares
 */
public class DocumentLibraryDDMFormFieldValueRenderer
	extends BaseDDMFormFieldValueRenderer {

	@Override
	public String getSupportedDDMFormFieldType() {
		return DDMFormFieldType.DOCUMENT_LIBRARY;
	}

	@Override
	protected ValueAccessor getValueAcessor(Locale locale) {
		return new ValueAccessor(locale) {

			@Override
			public String get(DDMFormFieldValue ddmFormFieldValue) {
				Value value = ddmFormFieldValue.getValue();

				JSONObject jsonObject = createJSONObject(
					value.getString(locale));

				long fileEntryGroupId = jsonObject.getLong("groupId");
				String fileEntryUUID = jsonObject.getString("uuid");

				try {
					FileEntry fileEntry =
						DLAppLocalServiceUtil.getFileEntryByUuidAndGroupId(
							fileEntryUUID, fileEntryGroupId);

					return fileEntry.getTitle();
				}
				catch (Exception e) {
					return LanguageUtil.format(
						locale, "is-temporarily-unavailable", "content");
				}
			}

			protected JSONObject createJSONObject(String json) {
				try {
					return JSONFactoryUtil.createJSONObject(json);
				}
				catch (JSONException jsone) {
					throw new ValueAccessorException(jsone);
				}
			}

		};
	}

}