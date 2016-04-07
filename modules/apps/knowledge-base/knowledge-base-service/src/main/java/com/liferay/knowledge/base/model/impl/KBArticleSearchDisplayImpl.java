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

package com.liferay.knowledge.base.model.impl;

import com.liferay.knowledge.base.model.KBArticle;
import com.liferay.knowledge.base.model.KBArticleSearchDisplay;

import java.util.List;

/**
 * @author Peter Shin
 */
public class KBArticleSearchDisplayImpl implements KBArticleSearchDisplay {

	public KBArticleSearchDisplayImpl(
		List<KBArticle> results, int total, int[] curStartValues) {

		_results = results;
		_total = total;
		_curStartValues = curStartValues;
	}

	@Override
	public int[] getCurStartValues() {
		return _curStartValues;
	}

	@Override
	public List<KBArticle> getResults() {
		return _results;
	}

	@Override
	public int getTotal() {
		return _total;
	}

	@Override
	public void setCurStartValues(int[] curStartValues) {
		_curStartValues = curStartValues;
	}

	@Override
	public void setResults(List<KBArticle> results) {
		_results = results;
	}

	@Override
	public void setTotal(int total) {
		_total = total;
	}

	private int[] _curStartValues;
	private List<KBArticle> _results;
	private int _total;

}