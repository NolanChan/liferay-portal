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

package com.liferay.osb.lcs.json;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.BaseModel;

import java.util.Date;
import java.util.Map;

/**
 * @author Igor Beslic
 */
public class LCSJSONSerializer {

	public static <T> JSONObject toJSONObject(BaseModel<T> baseModel) {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		Map<String, Object> modelAttributes = baseModel.getModelAttributes();

		for (String key : modelAttributes.keySet()) {
			Object object = modelAttributes.get(key);

			if (object instanceof Boolean) {
				jsonObject.put(key, (Boolean)object);
			}
			else if (object instanceof Integer) {
				jsonObject.put(key, (Integer)object);
			}
			else if (object instanceof Long) {
				jsonObject.put(key, (Long)object);
			}
			else if (object instanceof String) {
				jsonObject.put(key, (String)object);
			}
			else if (object instanceof Date) {
				Date date = (Date)object;

				jsonObject.put(key, date.getTime());
			}
			else if (object instanceof Double) {
				jsonObject.put(key, (Double)object);
			}
			else if (object == null) {
				jsonObject.put(key, (String)null);
			}
		}

		return jsonObject;
	}

	public static <T extends BaseModel> String toJSONString(T baseModel) {
		return toJSONObject(baseModel).toString();
	}

}