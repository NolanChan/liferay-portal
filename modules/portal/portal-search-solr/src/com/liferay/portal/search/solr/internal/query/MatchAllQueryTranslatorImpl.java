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

package com.liferay.portal.search.solr.internal.query;

import com.liferay.portal.kernel.search.generic.MatchAllQuery;
import com.liferay.portal.search.solr.query.MatchAllQueryTranslator;

import org.apache.lucene.search.MatchAllDocsQuery;

import org.osgi.service.component.annotations.Component;

/**
 * @author Michael C. Han
 */
@Component(immediate = true, service = MatchAllQueryTranslator.class)
public class MatchAllQueryTranslatorImpl implements MatchAllQueryTranslator {

	@Override
	public org.apache.lucene.search.Query translate(
		MatchAllQuery matchAllQuery) {

		org.apache.lucene.search.MatchAllDocsQuery matchAllDocsQuery =
			new MatchAllDocsQuery();

		if (!matchAllQuery.isDefaultBoost()) {
			matchAllDocsQuery.setBoost(matchAllQuery.getBoost());
		}

		return matchAllDocsQuery;
	}

}