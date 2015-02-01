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

package com.liferay.portal.search.lucene;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.MainServletTestRule;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Fieldable;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.Mockito;

import org.powermock.api.mockito.PowerMockito;

/**
 * @author Mate Thurzo
 */
public class PerFieldAnalyzerTest extends PowerMockito {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), MainServletTestRule.INSTANCE);

	@Before
	public void setUp() {
		_perFieldAnalyzer = (PerFieldAnalyzer)PortalBeanLocatorUtil.locate(
			PerFieldAnalyzer.class.getName());
	}

	@Test
	public void testAddAnalyzer() {
		try {
			Analyzer analyzer = mock(Analyzer.class);

			String fieldName = "testFieldName";

			when(
				analyzer.getPositionIncrementGap(fieldName)
			).thenReturn(
				1
			);

			when(
				analyzer.getOffsetGap(Mockito.any(Fieldable.class))
			).thenReturn(
				1
			);

			Fieldable fieldable = mock(Fieldable.class);

			when(
				fieldable.name()
			).thenReturn(
				fieldName
			);

			_perFieldAnalyzer.addAnalyzer(fieldName, analyzer);

			int positionIncrementGap =
				_perFieldAnalyzer.getPositionIncrementGap(fieldName);

			Assert.assertEquals(
				analyzer.getPositionIncrementGap(fieldName),
				positionIncrementGap);

			int offsetGap = _perFieldAnalyzer.getOffsetGap(fieldable);

			Assert.assertEquals(analyzer.getOffsetGap(fieldable), offsetGap);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	private PerFieldAnalyzer _perFieldAnalyzer;

}