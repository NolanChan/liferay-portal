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

package com.liferay.portal.upgrade.internal.release;

import com.liferay.osgi.service.tracker.map.PropertyServiceReferenceComparator;
import com.liferay.osgi.service.tracker.map.PropertyServiceReferenceMapper;
import com.liferay.osgi.service.tracker.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.map.ServiceTrackerMapFactory;
import com.liferay.portal.kernel.dao.db.DBContext;
import com.liferay.portal.kernel.dao.db.DBFactoryUtil;
import com.liferay.portal.kernel.dao.db.DBProcessContext;
import com.liferay.portal.kernel.upgrade.UpgradeStep;
import com.liferay.portal.kernel.util.RunnableUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Release;
import com.liferay.portal.output.stream.container.OutputStreamContainer;
import com.liferay.portal.output.stream.container.OutputStreamContainerFactory;
import com.liferay.portal.output.stream.container.OutputStreamContainerFactoryTracker;
import com.liferay.portal.service.ReleaseLocalService;
import com.liferay.portal.upgrade.constants.UpgradeStepConstants;
import com.liferay.portal.upgrade.internal.UpgradeInfo;
import com.liferay.portal.upgrade.internal.graph.ReleaseGraphManager;

import java.io.IOException;
import java.io.OutputStream;

import java.util.Collections;
import java.util.List;

import org.apache.felix.utils.log.Logger;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

/**
 * @author Miguel Pastor
 * @author Carlos Sierra Andrés
 */
@Component(
	immediate = true,
	property = {
		"osgi.command.function=execute", "osgi.command.function=list",
		"osgi.command.scope=upgrade"
	},
	service = Object.class
)
public class ReleaseManager {

	public void execute(String bundleSymbolicName) {
		ReleaseGraphManager releaseGraphManager = new ReleaseGraphManager(
			_serviceTrackerMap.getService(bundleSymbolicName));

		String version = getVersion(bundleSymbolicName);

		executeUpgradeInfos(
			bundleSymbolicName, releaseGraphManager.getUpgradeInfos(version));
	}

	public void execute(String bundleSymbolicName, String to) {
		String version = getVersion(bundleSymbolicName);

		ReleaseGraphManager releaseGraphManager = new ReleaseGraphManager(
			_serviceTrackerMap.getService(bundleSymbolicName));

		executeUpgradeInfos(
			bundleSymbolicName,
			releaseGraphManager.getUpgradeInfos(version, to));
	}

	public void list() {
		for (String bundleSymbolicName : _serviceTrackerMap.keySet()) {
			list(bundleSymbolicName);
		}
	}

	public void list(String bundleSymbolicName) {
		List<UpgradeInfo> upgradeProcesses = _serviceTrackerMap.getService(
			bundleSymbolicName);

		System.out.println(
			"Registered upgrade processes for " + bundleSymbolicName + " " +
				getVersion(bundleSymbolicName));

		for (UpgradeInfo upgradeProcess : upgradeProcesses) {
			System.out.println("\t" + upgradeProcess);
		}
	}

	@Reference
	public void setOutputStreamTracker(
		OutputStreamContainerFactoryTracker
			outputStreamContainerFactoryTracker) {

		_outputStreamContainerFactoryTracker =
			outputStreamContainerFactoryTracker;
	}

	@Activate
	protected void activate(final BundleContext bundleContext)
		throws InvalidSyntaxException {

		_logger = new Logger(bundleContext);

		_serviceTrackerMap = ServiceTrackerMapFactory.multiValueMap(
			bundleContext, UpgradeStep.class,
				"(&(" + UpgradeStepConstants.APPLICATION_NAME +
					"=*)(|(database=" +
						UpgradeStepConstants.ALL_DATABASES +
							")(database=" + DBFactoryUtil.getDB().getType() +
								")))",
			new PropertyServiceReferenceMapper<String, UpgradeStep>(
				UpgradeStepConstants.APPLICATION_NAME),
			new UpgradeServiceTrackerCustomizer(bundleContext),
			Collections.reverseOrder(
				new PropertyServiceReferenceComparator<UpgradeStep>(
					"upgrade.from")));

		_serviceTrackerMap.open();
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerMap.close();
	}

	protected void executeUpgradeInfos(
		final String bundleSymbolicName, final List<UpgradeInfo> upgradeInfos) {

		OutputStreamContainerFactory outputStreamContainerFactory =
			_outputStreamContainerFactoryTracker.
				getOutputStreamContainerFactory();

		OutputStreamContainer outputStreamContainer =
			outputStreamContainerFactory.create(
				"upgrade-" + bundleSymbolicName);

		final OutputStream outputStream =
			outputStreamContainer.getOutputStream();
			
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				for (UpgradeInfo upgradeInfo : upgradeInfos) {
					UpgradeStep upgradeStep = upgradeInfo.getUpgradeStep();

					try {
						upgradeStep.upgrade(
							new DBProcessContext() {

								@Override
								public DBContext getDBContext() {
									return new DBContext();
								}

								@Override
								public OutputStream getOutputStream() {
									return outputStream;
								}

							});

						_releaseLocalService.updateRelease(
							bundleSymbolicName, upgradeInfo.getToVersionString(),
							upgradeInfo.getFromVersionString());
					}
					catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
			}

		};

		RunnableUtil.runWithSwappedSystemOut(runnable, outputStream);

		try {
			outputStream.close();
		}
		catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}

		Release release = _releaseLocalService.fetchRelease(bundleSymbolicName);

		if (release != null) {
			_releasePublisher.publish(release);
		}
	}

	protected String getVersion(String bundleSymbolicName) {
		Release release = _releaseLocalService.fetchRelease(bundleSymbolicName);

		if ((release == null) || Validator.isNull(release.getVersion())) {
			return "0.0.0";
		}

		return release.getVersion();
	}

	@Reference
	protected void setReleaseLocalService(
		ReleaseLocalService releaseLocalService) {

		_releaseLocalService = releaseLocalService;
	}

	@Reference
	protected void setReleasePublisher(ReleasePublisher releasePublisher) {
		_releasePublisher = releasePublisher;
	}

	private static Logger _logger;

	private OutputStreamContainerFactoryTracker
		_outputStreamContainerFactoryTracker;
	private ReleaseLocalService _releaseLocalService;
	private ReleasePublisher _releasePublisher;
	private ServiceTrackerMap<String, List<UpgradeInfo>> _serviceTrackerMap;

	private class UpgradeServiceTrackerCustomizer
		implements ServiceTrackerCustomizer<UpgradeStep, UpgradeInfo> {

		public UpgradeServiceTrackerCustomizer(BundleContext bundleContext) {
			_bundleContext = bundleContext;
		}

		@Override
		public UpgradeInfo addingService(
			ServiceReference<UpgradeStep> serviceReference) {

			String fromVersionString = (String)serviceReference.getProperty(
				"upgrade.from.version");
			String toVersionString = (String)serviceReference.getProperty(
				"upgrade.to.version");

			UpgradeStep upgradeStep = _bundleContext.getService(
				serviceReference);

			if (upgradeStep == null) {
				_logger.log(
					Logger.LOG_WARNING,
					"Not tracking service " + serviceReference +
						" because it does not implement UpgradeStep");

				return null;
			}

			return new UpgradeInfo(
				fromVersionString, toVersionString, upgradeStep);
		}

		@Override
		public void modifiedService(
			ServiceReference<UpgradeStep> serviceReference,
			UpgradeInfo upgradeInfo) {
		}

		@Override
		public void removedService(
			ServiceReference<UpgradeStep> serviceReference,
			UpgradeInfo upgradeInfo) {

			_bundleContext.ungetService(serviceReference);
		}

		private final BundleContext _bundleContext;

	}

}