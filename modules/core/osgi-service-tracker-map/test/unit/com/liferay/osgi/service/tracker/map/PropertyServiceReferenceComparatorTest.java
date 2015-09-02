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

package com.liferay.osgi.service.tracker.map;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import org.osgi.framework.Bundle;
import org.osgi.framework.ServiceReference;

/**
 * @author Carlos Sierra Andrés
 */
public class PropertyServiceReferenceComparatorTest {

	@Test
	public void testCompare() {
		PropertyServiceReferenceComparator<Object> propertyServiceReferenceComparator =
			new PropertyServiceReferenceComparator<>("ranking");

		ServiceReference<Object> serviceReference1 = new TestServiceReference<>(
			"ranking", 1);
		ServiceReference<Object> serviceReference2 = new TestServiceReference<>(
			"ranking", 2);

		Assert.assertTrue(propertyServiceReferenceComparator.compare(serviceReference2, serviceReference1) < 0);
		Assert.assertEquals(
			propertyServiceReferenceComparator.compare(serviceReference1, serviceReference2), -propertyServiceReferenceComparator.compare(serviceReference2, serviceReference1));

		ServiceReference<Object> serviceReference3 = new TestServiceReference<>(
			"ranking", 1);

		Assert.assertEquals(0, propertyServiceReferenceComparator.compare(serviceReference1, serviceReference3));
	}

	@Test
	public void testCompareIsTransitiveWithNulls() {
		PropertyServiceReferenceComparator<Object> propertyServiceReferenceComparator =
			new PropertyServiceReferenceComparator<>("ranking");

		ServiceReference<Object> lower = new TestServiceReference<>(
			"ranking", -1);
		ServiceReference<Object> zero = new TestServiceReference<>(
			"ranking", 0);
		ServiceReference<Object> higher = new TestServiceReference<>(
			"ranking", 1);

		Assert.assertTrue(propertyServiceReferenceComparator.compare(null, lower) > 0);
		Assert.assertTrue(propertyServiceReferenceComparator.compare(lower, zero) > 0);
		Assert.assertTrue(propertyServiceReferenceComparator.compare(null, zero) > 0);

		Assert.assertTrue(propertyServiceReferenceComparator.compare(null, zero) > 0);
		Assert.assertTrue(propertyServiceReferenceComparator.compare(zero, higher) > 0);
		Assert.assertTrue(propertyServiceReferenceComparator.compare(null, higher) > 0);
	}

	@Test
	public void testCompareIsTransitiveWithNullValues() {
		PropertyServiceReferenceComparator<Object> propertyServiceReferenceComparator =
			new PropertyServiceReferenceComparator<>("ranking");

		ServiceReference<Object> lower = new TestServiceReference<>(
			"ranking", -1);
		ServiceReference<Object> zero = new TestServiceReference<>(
			"ranking", 0);
		ServiceReference<Object> higher = new TestServiceReference<>(
			"ranking", 1);
		ServiceReference<Object> nullRef = new TestServiceReference<>();

		Assert.assertTrue(propertyServiceReferenceComparator.compare(nullRef, lower) > 0);
		Assert.assertTrue(propertyServiceReferenceComparator.compare(lower, zero) > 0);
		Assert.assertTrue(propertyServiceReferenceComparator.compare(nullRef, zero) > 0);

		Assert.assertTrue(propertyServiceReferenceComparator.compare(nullRef, zero) > 0);
		Assert.assertTrue(propertyServiceReferenceComparator.compare(zero, higher) > 0);
		Assert.assertTrue(propertyServiceReferenceComparator.compare(nullRef, higher) > 0);
	}

	@Test
	public void testCompareWithNulls() {
		PropertyServiceReferenceComparator<Object> propertyServiceReferenceComparator =
			new PropertyServiceReferenceComparator<>("ranking");

		ServiceReference<Object> serviceReference1 = new TestServiceReference<>(
			"ranking", 1);

		Assert.assertEquals(0, propertyServiceReferenceComparator.compare(null, null));
		Assert.assertTrue(propertyServiceReferenceComparator.compare(serviceReference1, null) < 0);
		Assert.assertTrue(
			propertyServiceReferenceComparator.compare(serviceReference1, null) == -propertyServiceReferenceComparator.compare(null, serviceReference1));
	}

	@Test
	public void testCompareWithNullValues() {
		PropertyServiceReferenceComparator<Object> propertyServiceReferenceComparator =
			new PropertyServiceReferenceComparator<>("ranking");

		ServiceReference<Object> serviceReference1 = new TestServiceReference<>(
			"ranking", 1);

		ServiceReference<Object> serviceReference2 = new TestServiceReference<>();

		Assert.assertTrue(propertyServiceReferenceComparator.compare(serviceReference1, serviceReference2) < 0);
		Assert.assertEquals(
			propertyServiceReferenceComparator.compare(serviceReference1, serviceReference2), -propertyServiceReferenceComparator.compare(serviceReference2, serviceReference1));

		ServiceReference<Object> serviceReference3 = new TestServiceReference<>();

		Assert.assertEquals(0, propertyServiceReferenceComparator.compare(serviceReference2, serviceReference3));
		Assert.assertEquals(
			propertyServiceReferenceComparator.compare(serviceReference2, serviceReference3), -propertyServiceReferenceComparator.compare(serviceReference3, serviceReference2));
	}

	private class TestServiceReference<S> implements ServiceReference<S> {

		public TestServiceReference(Object... arguments) {
			Map<String, Object> properties = new HashMap<>();
				
			for (int i = 0; i < arguments.length; i += 2) {
				String key = String.valueOf(arguments[i]);	
				Object value = arguments[i + 1];

				properties.put(key, value);
			}			
			
			_properties = properties;
		}

		@Override
		public int compareTo(Object object) {
			if (object == null) {
				return 1;
			}

			return toString().compareTo(object.toString());
		}

		@Override
		public Bundle getBundle() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Object getProperty(String key) {
			return _properties.get(key);
		}

		@Override
		public String[] getPropertyKeys() {
			return _properties.keySet().toArray(new String[0]);
		}

		@Override
		public Bundle[] getUsingBundles() {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean isAssignableTo(Bundle bundle, String className) {
			throw new UnsupportedOperationException();
		}

		private final Map<String, Object> _properties;

	}

}