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

package com.liferay.osgi.servicetrackermap.test;

import com.liferay.arquillian.deploymentscenario.annotations.BndFile;
import com.liferay.osgi.servicetrackermap.ServiceReferenceMapper;
import com.liferay.osgi.servicetrackermap.ServiceTrackerMap;
import com.liferay.osgi.servicetrackermap.ServiceTrackerMapFactory;
import com.liferay.osgi.servicetrackermap.internal.BundleContextWrapper;
import com.liferay.osgi.servicetrackermap.internal.TrackedOne;
import com.liferay.osgi.servicetrackermap.internal.TrackedTwo;

import java.util.Collection;
import java.util.Comparator;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

/**
 * @author Carlos Sierra Andrés
 */
@BndFile("test/integration/bnd.bnd")
@RunWith(Arquillian.class)
public class ObjectServiceTrackerMapTest {

	@Before
	public void setUp() throws BundleException {
		_bundle.start();

		_bundleContext = _bundle.getBundleContext();
	}

	@After
	public void tearDown() throws BundleException {
		_bundle.stop();

		if (_serviceTrackerMap != null) {
			_serviceTrackerMap.close();

			_serviceTrackerMap = null;
		}
	}

	@Test
	public void testGetServiceAfterRemoval() {
		ServiceTrackerMap<String, TrackedOne> serviceTrackerMap =
			createServiceTrackerMap(_bundleContext);

		ServiceRegistration<TrackedOne> serviceRegistration = registerService(
			new TrackedOne());

		Assert.assertNotNull(serviceTrackerMap.getService("aTarget"));

		serviceRegistration.unregister();

		Assert.assertNull(serviceTrackerMap.getService("aTarget"));
	}

	@Test
	public void testGetServiceGetsReplacedAfterRemoval() {
		ServiceTrackerMap<String, TrackedOne> serviceTrackerMap =
			createServiceTrackerMap(_bundleContext);

		TrackedOne trackedOne1 = new TrackedOne();

		ServiceRegistration<TrackedOne> serviceRegistration1 = registerService(
			trackedOne1, 2);

		TrackedOne trackedOne2 = new TrackedOne();

		ServiceRegistration<TrackedOne> serviceRegistration2 = registerService(
			trackedOne2, 1);

		Assert.assertEquals(
			trackedOne1, serviceTrackerMap.getService("aTarget"));

		serviceRegistration1.unregister();

		Assert.assertEquals(
			trackedOne2, serviceTrackerMap.getService("aTarget"));

		serviceRegistration2.unregister();
	}

	@Test
	public void testGetServiceGetsReplacedAfterRemovalInverseOrder() {
		ServiceTrackerMap<String, TrackedOne> serviceTrackerMap =
			createServiceTrackerMap(_bundleContext);

		TrackedOne trackedOne2 = new TrackedOne();

		registerService(trackedOne2, 1);

		TrackedOne trackedOne1 = new TrackedOne();

		ServiceRegistration<TrackedOne> serviceRegistration1 = registerService(
			trackedOne1, 2);

		Assert.assertEquals(
			trackedOne1, serviceTrackerMap.getService("aTarget"));

		serviceRegistration1.unregister();

		Assert.assertEquals(
			trackedOne2, serviceTrackerMap.getService("aTarget"));
	}

	@Test
	public void testGetServiceIsNullAfterDeregistration() {
		ServiceTrackerMap<String, TrackedOne> serviceTrackerMap =
			createServiceTrackerMap(_bundleContext);

		ServiceRegistration<TrackedOne> serviceRegistration1 = registerService(
			new TrackedOne());
		ServiceRegistration<TrackedOne> serviceRegistration2 = registerService(
			new TrackedOne());
		ServiceRegistration<TrackedOne> serviceRegistration3 = registerService(
			new TrackedOne());

		Assert.assertNotNull(serviceTrackerMap.getService("aTarget"));

		serviceRegistration1.unregister();
		serviceRegistration2.unregister();
		serviceRegistration3.unregister();

		Assert.assertNull(serviceTrackerMap.getService("aTarget"));
	}

	@Test
	public void testGetServiceWithCustomComparator()
		throws InvalidSyntaxException {

		ServiceTrackerMapFactory.
			PropertyServiceReferenceMapper<String, TrackedOne> target =
				new ServiceTrackerMapFactory.PropertyServiceReferenceMapper<>(
					"target");

		ServiceTrackerMap<String, TrackedOne> serviceTrackerMap =
			ServiceTrackerMapFactory.singleValueMap(
				_bundleContext, TrackedOne.class, "(target=*)", target,
				new Comparator<ServiceReference<TrackedOne>>() {

					@Override
					public int compare(
						ServiceReference<TrackedOne> serviceReference1,
						ServiceReference<TrackedOne> serviceReference2) {

						return -1;
					}

				}
			);

		serviceTrackerMap.open();

		TrackedOne trackedOne1 = new TrackedOne();

		ServiceRegistration<TrackedOne> serviceRegistration1 = registerService(
			trackedOne1);

		TrackedOne trackedOne2 = new TrackedOne();

		ServiceRegistration<TrackedOne> serviceRegistration2 = registerService(
			trackedOne2);

		Assert.assertEquals(
			trackedOne2, serviceTrackerMap.getService("aTarget"));

		serviceRegistration1.unregister();
		serviceRegistration2.unregister();

		registerService(trackedOne2);
		registerService(trackedOne1);

		Assert.assertEquals(
			trackedOne1, serviceTrackerMap.getService("aTarget"));

		serviceTrackerMap.close();
	}

	@Test
	public void testGetServiceWithCustomResolver()
		throws InvalidSyntaxException {

		ServiceTrackerMap<String, TrackedOne> serviceTrackerMap =

			ServiceTrackerMapFactory.singleValueMap(
				_bundleContext, TrackedOne.class, "(&(other=*)(target=*))",
				new ServiceReferenceMapper<String, TrackedOne>() {

					@Override
					public void map(
						ServiceReference<TrackedOne> serviceReference,
						Emitter<String> keys) {

						keys.emit(
							serviceReference.getProperty("other") + " - " +
								serviceReference.getProperty("target"));
					}

				});

		serviceTrackerMap.open();

		Dictionary<String, String> properties = new Hashtable<>();

		properties.put("other", "aProperty");
		properties.put("target", "aTarget");

		_bundleContext.registerService(
			TrackedOne.class, new TrackedOne(), properties);

		Assert.assertNotNull(
			serviceTrackerMap.getService("aProperty - aTarget"));
	}

	@Test
	public void testGetServiceWithIncorrectKey() {
		ServiceTrackerMap<String, TrackedOne> serviceTrackerMap =
			createServiceTrackerMap(_bundleContext);

		registerService(new TrackedOne(), "anotherTarget");

		Assert.assertNull(serviceTrackerMap.getService("aTarget"));
	}

	@Test
	public void testGetServiceWithServiceCustomizer()
		throws InvalidSyntaxException {

		ServiceTrackerMap<String, TrackedTwo> serviceTrackerMap =
			ServiceTrackerMapFactory.singleValueMap(
				_bundleContext, TrackedOne.class, "target",
				new ServiceTrackerCustomizer<TrackedOne, TrackedTwo>() {

					@Override
					public TrackedTwo addingService(
						ServiceReference<TrackedOne> serviceReference) {

						return new TrackedTwo(
							_bundleContext.getService(serviceReference));
					}

					@Override
					public void modifiedService(
						ServiceReference<TrackedOne> serviceReference,
						TrackedTwo service) {

						removedService(serviceReference, service);
					}

					@Override
					public void removedService(
						ServiceReference<TrackedOne> serviceReference,
						TrackedTwo service) {

						_bundleContext.ungetService(serviceReference);
					}
				});

		serviceTrackerMap.open();

		TrackedOne one = new TrackedOne();

		registerService(one, "one");

		TrackedOne two = new TrackedOne();
		registerService(two, "two");

		TrackedTwo twoOne = serviceTrackerMap.getService("one");

		Assert.assertEquals(one, twoOne.getTrackedOne());

		TrackedTwo twoTwo = serviceTrackerMap.getService("two");

		Assert.assertEquals(two, twoTwo.getTrackedOne());

		serviceTrackerMap.close();
	}

	@Test
	public void testGetServiceWithServiceCustomizerAndServiceReferenceMapper()
		throws InvalidSyntaxException {

		ServiceTrackerMap<String, TrackedTwo> serviceTrackerMap =
			ServiceTrackerMapFactory.singleValueMap(
				_bundleContext, TrackedOne.class, ("(target=*)"),
				new ServiceReferenceMapper<String, TrackedOne>() {

					@Override
					public void map(
						ServiceReference<TrackedOne> serviceReference,
						Emitter<String> emitter) {

						TrackedOne service = _bundleContext.getService(
							serviceReference);

						String targetProperty =
							(String) serviceReference.getProperty("target");

						emitter.emit(targetProperty + "-" + service.getKey());

						_bundleContext.ungetService(serviceReference);
					}
				},
				new ServiceTrackerCustomizer<TrackedOne, TrackedTwo>() {

					@Override
					public TrackedTwo addingService(
						ServiceReference<TrackedOne> serviceReference) {

						return new TrackedTwo(
							_bundleContext.getService(serviceReference));
					}

					@Override
					public void modifiedService(
						ServiceReference<TrackedOne> serviceReference,
						TrackedTwo service) {

						removedService(serviceReference, service);
					}

					@Override
					public void removedService(
						ServiceReference<TrackedOne> serviceReference,
						TrackedTwo service) {

						_bundleContext.ungetService(serviceReference);
					}
				});

		serviceTrackerMap.open();

		TrackedOne one = new TrackedOne("1");

		registerService(one, "one");

		TrackedOne two = new TrackedOne("2");
		registerService(two, "two");

		TrackedTwo twoOne = serviceTrackerMap.getService("one-1");

		Assert.assertEquals(one, twoOne.getTrackedOne());

		TrackedTwo twoTwo = serviceTrackerMap.getService("two-2");

		Assert.assertEquals(two, twoTwo.getTrackedOne());

		serviceTrackerMap.close();
	}

	@Test
	public void testGetServiceWithSimpleRegistration() {
		ServiceTrackerMap<String, TrackedOne> serviceTrackerMap =
			createServiceTrackerMap(_bundleContext);

		registerService(new TrackedOne());

		Assert.assertNotNull(serviceTrackerMap.getService("aTarget"));
	}

	@Test
	public void testOperationBalancesOutGetServiceAndUngetService() {
		BundleContextWrapper bundleContextWrapper = wrapContext();

		ServiceTrackerMap<String, TrackedOne> serviceTrackerMap =
			createServiceTrackerMap(bundleContextWrapper);

		ServiceRegistration<TrackedOne> serviceRegistration1 = registerService(
			new TrackedOne());
		ServiceRegistration<TrackedOne> serviceRegistration2 = registerService(
			new TrackedOne());

		serviceRegistration2.unregister();

		serviceRegistration2 = registerService(new TrackedOne());

		serviceRegistration2.unregister();

		serviceRegistration1.unregister();

		Map<ServiceReference<?>, AtomicInteger> serviceReferenceCountsMap =
			bundleContextWrapper.getServiceReferenceCountsMap();

		Collection<AtomicInteger> serviceReferenceCounts =
			serviceReferenceCountsMap.values();

		Assert.assertEquals(3, serviceReferenceCounts.size());

		for (AtomicInteger serviceReferenceCount : serviceReferenceCounts) {
			Assert.assertEquals(0, serviceReferenceCount.get());
		}

		serviceTrackerMap.close();
	}

	@Test
	public void testUnkeyedServiceReferencesBalanceRefCount()
		throws InvalidSyntaxException {

		BundleContextWrapper wrappedBundleContext = wrapContext();

		ServiceTrackerMap<TrackedOne, TrackedOne> serviceTrackerMap =
			ServiceTrackerMapFactory.singleValueMap(
				wrappedBundleContext, TrackedOne.class, null,
				new ServiceReferenceMapper<TrackedOne, TrackedOne>() {

					@Override
					public void map(
						ServiceReference<TrackedOne> serviceReference,
						Emitter<TrackedOne> emitter) {
					}

				});

		serviceTrackerMap.open();

		ServiceRegistration<TrackedOne> serviceRegistration1 = registerService(
			new TrackedOne());
		ServiceRegistration<TrackedOne> serviceRegistration2 = registerService(
			new TrackedOne());

		Map<ServiceReference<?>, AtomicInteger> serviceReferenceCountsMap =
			wrappedBundleContext.getServiceReferenceCountsMap();

		Collection<AtomicInteger> serviceReferenceCounts =
			serviceReferenceCountsMap.values();

		Assert.assertEquals(0, serviceReferenceCounts.size());

		serviceRegistration1.unregister();
		serviceRegistration2.unregister();

		Assert.assertEquals(0, serviceReferenceCounts.size());

		serviceTrackerMap.close();
	}

	@ArquillianResource
	public Bundle _bundle;

	protected ServiceTrackerMap<String, TrackedOne> createServiceTrackerMap(
		BundleContext bundleContext) {

		try {
			_serviceTrackerMap = ServiceTrackerMapFactory.singleValueMap(
				bundleContext, TrackedOne.class, "target");
		}
		catch (InvalidSyntaxException ise) {
			throw new RuntimeException(ise);
		}

		_serviceTrackerMap.open();

		return _serviceTrackerMap;
	}

	protected ServiceRegistration<TrackedOne> registerService(
		TrackedOne trackedOne) {

		return registerService(trackedOne, "aTarget");
	}

	protected ServiceRegistration<TrackedOne> registerService(
		TrackedOne trackedOne, int ranking) {

		return registerService(trackedOne, ranking, "aTarget");
	}

	protected ServiceRegistration<TrackedOne> registerService(
		TrackedOne trackedOne, int ranking, String target) {

		Dictionary<String, Object> properties = new Hashtable<>();

		properties.put("service.ranking", ranking);
		properties.put("target", target);

		return _bundleContext.registerService(
			TrackedOne.class, trackedOne, properties);
	}

	protected ServiceRegistration<TrackedOne> registerService(
		TrackedOne trackedOne, String target) {

		Dictionary<String, Object> properties = new Hashtable<>();

		properties.put("target", target);

		return _bundleContext.registerService(
			TrackedOne.class, trackedOne, properties);
	}

	protected BundleContextWrapper wrapContext() {
		return new BundleContextWrapper(_bundleContext);
	}

	private BundleContext _bundleContext;
	private ServiceTrackerMap<String, TrackedOne> _serviceTrackerMap;

}