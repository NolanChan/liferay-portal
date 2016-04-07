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

package com.liferay.portal.search.solr.internal.facet;

import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.facet.Facet;
import com.liferay.portal.kernel.search.facet.config.FacetConfiguration;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.solr.facet.FacetProcessor;

import java.io.Serializable;

import org.apache.solr.client.solrj.SolrQuery;

import org.osgi.service.component.annotations.Component;

/**
 * @author Michael C. Han
 * @authot Tibor Lipusz
 */
@Component(
	immediate = true,
	property = {
		"class.name=com.liferay.portal.kernel.search.facet.ModifiedFacet"
	},
	service = FacetProcessor.class
)
public class ModifiedFacetProcessor extends RangeFacetProcessor {

	@Override
	public void processFacet(SolrQuery solrQuery, Facet facet) {
		super.processFacet(solrQuery, facet);

		SearchContext searchContext = facet.getSearchContext();

		Serializable modified = searchContext.getAttribute("modified");

		if (Validator.isNotNull(modified)) {
			FacetConfiguration facetConfiguration =
				facet.getFacetConfiguration();

			String facetQuery =
				facetConfiguration.getFieldName() + StringPool.COLON +
					GetterUtil.getString(modified);

			solrQuery.addFacetQuery(facetQuery);
		}
	}

}