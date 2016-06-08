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

package com.liferay.dynamic.data.mapping.model;

import java.io.Serializable;

/**
 * @author Leonardo Barros
 */
public class DDMFormFieldRule implements Serializable {

	public DDMFormFieldRule(DDMFormFieldRule ddmFormFieldRule) {
		_expression = ddmFormFieldRule._expression;
		_type = ddmFormFieldRule._type;
	}

	public DDMFormFieldRule(String expression, DDMFormFieldRuleType type) {
		_expression = expression;
		_type = type;
	}

	public String getExpression() {
		return _expression;
	}

	public DDMFormFieldRuleType getType() {
		return _type;
	}

	public void setExpression(String expression) {
		_expression = expression;
	}

	public void setType(DDMFormFieldRuleType type) {
		_type = type;
	}

	private String _expression;
	private DDMFormFieldRuleType _type;

}