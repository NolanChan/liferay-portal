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

package com.liferay.dynamic.data.mapping.type.buttonswitch;

import com.liferay.dynamic.data.mapping.registry.DDMFormFieldValueRenderer;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.portal.kernel.language.LanguageUtil;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Renato Rego
 */
@Component(immediate = true, property = {"ddm.form.field.type.name=button-switch"})
public class ButtonSwitchDDMFormFieldValueRenderer
	implements DDMFormFieldValueRenderer {

	@Override
	public String render(DDMFormFieldValue ddmFormFieldValue, Locale locale) {
		Boolean valueBoolean = _buttonSwitchDDMFormFieldValueAccessor.getValue(
			ddmFormFieldValue, locale);

		if (valueBoolean == Boolean.TRUE) {
			return LanguageUtil.get(locale, "yes");
		}
		else {
			return LanguageUtil.get(locale, "no");
		}
	}

	@Reference
	protected void setButtonSwitchDDMFormFieldValueAccessor(
		ButtonSwitchDDMFormFieldValueAccessor buttonSwitchDDMFormFieldValueAccessor) {

		_buttonSwitchDDMFormFieldValueAccessor = buttonSwitchDDMFormFieldValueAccessor;
	}

	private ButtonSwitchDDMFormFieldValueAccessor
		_buttonSwitchDDMFormFieldValueAccessor;

}