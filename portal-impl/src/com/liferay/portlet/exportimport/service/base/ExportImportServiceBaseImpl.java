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

package com.liferay.portlet.exportimport.service.base;

import com.liferay.exportimport.kernel.service.ExportImportService;
import com.liferay.exportimport.kernel.service.persistence.ExportImportConfigurationFinder;
import com.liferay.exportimport.kernel.service.persistence.ExportImportConfigurationPersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.util.InfrastructureUtil;
import com.liferay.portal.service.BaseServiceImpl;
import com.liferay.portal.service.persistence.ClassNamePersistence;
import com.liferay.portal.service.persistence.LayoutFinder;
import com.liferay.portal.service.persistence.LayoutPersistence;
import com.liferay.portal.service.persistence.UserFinder;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.util.PortalUtil;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the export import remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portlet.exportimport.service.impl.ExportImportServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portlet.exportimport.service.impl.ExportImportServiceImpl
 * @see com.liferay.exportimport.kernel.service.ExportImportServiceUtil
 * @generated
 */
public abstract class ExportImportServiceBaseImpl extends BaseServiceImpl
	implements ExportImportService, IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.exportimport.kernel.service.ExportImportServiceUtil} to access the export import remote service.
	 */

	/**
	 * Returns the export import local service.
	 *
	 * @return the export import local service
	 */
	public com.liferay.exportimport.kernel.service.ExportImportLocalService getExportImportLocalService() {
		return exportImportLocalService;
	}

	/**
	 * Sets the export import local service.
	 *
	 * @param exportImportLocalService the export import local service
	 */
	public void setExportImportLocalService(
		com.liferay.exportimport.kernel.service.ExportImportLocalService exportImportLocalService) {
		this.exportImportLocalService = exportImportLocalService;
	}

	/**
	 * Returns the export import remote service.
	 *
	 * @return the export import remote service
	 */
	public ExportImportService getExportImportService() {
		return exportImportService;
	}

	/**
	 * Sets the export import remote service.
	 *
	 * @param exportImportService the export import remote service
	 */
	public void setExportImportService(ExportImportService exportImportService) {
		this.exportImportService = exportImportService;
	}

	/**
	 * Returns the export import configuration local service.
	 *
	 * @return the export import configuration local service
	 */
	public com.liferay.exportimport.kernel.service.ExportImportConfigurationLocalService getExportImportConfigurationLocalService() {
		return exportImportConfigurationLocalService;
	}

	/**
	 * Sets the export import configuration local service.
	 *
	 * @param exportImportConfigurationLocalService the export import configuration local service
	 */
	public void setExportImportConfigurationLocalService(
		com.liferay.exportimport.kernel.service.ExportImportConfigurationLocalService exportImportConfigurationLocalService) {
		this.exportImportConfigurationLocalService = exportImportConfigurationLocalService;
	}

	/**
	 * Returns the export import configuration remote service.
	 *
	 * @return the export import configuration remote service
	 */
	public com.liferay.exportimport.kernel.service.ExportImportConfigurationService getExportImportConfigurationService() {
		return exportImportConfigurationService;
	}

	/**
	 * Sets the export import configuration remote service.
	 *
	 * @param exportImportConfigurationService the export import configuration remote service
	 */
	public void setExportImportConfigurationService(
		com.liferay.exportimport.kernel.service.ExportImportConfigurationService exportImportConfigurationService) {
		this.exportImportConfigurationService = exportImportConfigurationService;
	}

	/**
	 * Returns the export import configuration persistence.
	 *
	 * @return the export import configuration persistence
	 */
	public ExportImportConfigurationPersistence getExportImportConfigurationPersistence() {
		return exportImportConfigurationPersistence;
	}

	/**
	 * Sets the export import configuration persistence.
	 *
	 * @param exportImportConfigurationPersistence the export import configuration persistence
	 */
	public void setExportImportConfigurationPersistence(
		ExportImportConfigurationPersistence exportImportConfigurationPersistence) {
		this.exportImportConfigurationPersistence = exportImportConfigurationPersistence;
	}

	/**
	 * Returns the export import configuration finder.
	 *
	 * @return the export import configuration finder
	 */
	public ExportImportConfigurationFinder getExportImportConfigurationFinder() {
		return exportImportConfigurationFinder;
	}

	/**
	 * Sets the export import configuration finder.
	 *
	 * @param exportImportConfigurationFinder the export import configuration finder
	 */
	public void setExportImportConfigurationFinder(
		ExportImportConfigurationFinder exportImportConfigurationFinder) {
		this.exportImportConfigurationFinder = exportImportConfigurationFinder;
	}

	/**
	 * Returns the staging local service.
	 *
	 * @return the staging local service
	 */
	public com.liferay.exportimport.kernel.service.StagingLocalService getStagingLocalService() {
		return stagingLocalService;
	}

	/**
	 * Sets the staging local service.
	 *
	 * @param stagingLocalService the staging local service
	 */
	public void setStagingLocalService(
		com.liferay.exportimport.kernel.service.StagingLocalService stagingLocalService) {
		this.stagingLocalService = stagingLocalService;
	}

	/**
	 * Returns the staging remote service.
	 *
	 * @return the staging remote service
	 */
	public com.liferay.exportimport.kernel.service.StagingService getStagingService() {
		return stagingService;
	}

	/**
	 * Sets the staging remote service.
	 *
	 * @param stagingService the staging remote service
	 */
	public void setStagingService(
		com.liferay.exportimport.kernel.service.StagingService stagingService) {
		this.stagingService = stagingService;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the class name local service.
	 *
	 * @return the class name local service
	 */
	public com.liferay.portal.service.ClassNameLocalService getClassNameLocalService() {
		return classNameLocalService;
	}

	/**
	 * Sets the class name local service.
	 *
	 * @param classNameLocalService the class name local service
	 */
	public void setClassNameLocalService(
		com.liferay.portal.service.ClassNameLocalService classNameLocalService) {
		this.classNameLocalService = classNameLocalService;
	}

	/**
	 * Returns the class name remote service.
	 *
	 * @return the class name remote service
	 */
	public com.liferay.portal.service.ClassNameService getClassNameService() {
		return classNameService;
	}

	/**
	 * Sets the class name remote service.
	 *
	 * @param classNameService the class name remote service
	 */
	public void setClassNameService(
		com.liferay.portal.service.ClassNameService classNameService) {
		this.classNameService = classNameService;
	}

	/**
	 * Returns the class name persistence.
	 *
	 * @return the class name persistence
	 */
	public ClassNamePersistence getClassNamePersistence() {
		return classNamePersistence;
	}

	/**
	 * Sets the class name persistence.
	 *
	 * @param classNamePersistence the class name persistence
	 */
	public void setClassNamePersistence(
		ClassNamePersistence classNamePersistence) {
		this.classNamePersistence = classNamePersistence;
	}

	/**
	 * Returns the layout local service.
	 *
	 * @return the layout local service
	 */
	public com.liferay.portal.service.LayoutLocalService getLayoutLocalService() {
		return layoutLocalService;
	}

	/**
	 * Sets the layout local service.
	 *
	 * @param layoutLocalService the layout local service
	 */
	public void setLayoutLocalService(
		com.liferay.portal.service.LayoutLocalService layoutLocalService) {
		this.layoutLocalService = layoutLocalService;
	}

	/**
	 * Returns the layout remote service.
	 *
	 * @return the layout remote service
	 */
	public com.liferay.portal.service.LayoutService getLayoutService() {
		return layoutService;
	}

	/**
	 * Sets the layout remote service.
	 *
	 * @param layoutService the layout remote service
	 */
	public void setLayoutService(
		com.liferay.portal.service.LayoutService layoutService) {
		this.layoutService = layoutService;
	}

	/**
	 * Returns the layout persistence.
	 *
	 * @return the layout persistence
	 */
	public LayoutPersistence getLayoutPersistence() {
		return layoutPersistence;
	}

	/**
	 * Sets the layout persistence.
	 *
	 * @param layoutPersistence the layout persistence
	 */
	public void setLayoutPersistence(LayoutPersistence layoutPersistence) {
		this.layoutPersistence = layoutPersistence;
	}

	/**
	 * Returns the layout finder.
	 *
	 * @return the layout finder
	 */
	public LayoutFinder getLayoutFinder() {
		return layoutFinder;
	}

	/**
	 * Sets the layout finder.
	 *
	 * @param layoutFinder the layout finder
	 */
	public void setLayoutFinder(LayoutFinder layoutFinder) {
		this.layoutFinder = layoutFinder;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public com.liferay.portal.service.UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(
		com.liferay.portal.service.UserService userService) {
		this.userService = userService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	/**
	 * Returns the user finder.
	 *
	 * @return the user finder
	 */
	public UserFinder getUserFinder() {
		return userFinder;
	}

	/**
	 * Sets the user finder.
	 *
	 * @param userFinder the user finder
	 */
	public void setUserFinder(UserFinder userFinder) {
		this.userFinder = userFinder;
	}

	public void afterPropertiesSet() {
	}

	public void destroy() {
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return ExportImportService.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = InfrastructureUtil.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = com.liferay.exportimport.kernel.service.ExportImportLocalService.class)
	protected com.liferay.exportimport.kernel.service.ExportImportLocalService exportImportLocalService;
	@BeanReference(type = com.liferay.exportimport.kernel.service.ExportImportService.class)
	protected ExportImportService exportImportService;
	@BeanReference(type = com.liferay.exportimport.kernel.service.ExportImportConfigurationLocalService.class)
	protected com.liferay.exportimport.kernel.service.ExportImportConfigurationLocalService exportImportConfigurationLocalService;
	@BeanReference(type = com.liferay.exportimport.kernel.service.ExportImportConfigurationService.class)
	protected com.liferay.exportimport.kernel.service.ExportImportConfigurationService exportImportConfigurationService;
	@BeanReference(type = ExportImportConfigurationPersistence.class)
	protected ExportImportConfigurationPersistence exportImportConfigurationPersistence;
	@BeanReference(type = ExportImportConfigurationFinder.class)
	protected ExportImportConfigurationFinder exportImportConfigurationFinder;
	@BeanReference(type = com.liferay.exportimport.kernel.service.StagingLocalService.class)
	protected com.liferay.exportimport.kernel.service.StagingLocalService stagingLocalService;
	@BeanReference(type = com.liferay.exportimport.kernel.service.StagingService.class)
	protected com.liferay.exportimport.kernel.service.StagingService stagingService;
	@BeanReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@BeanReference(type = com.liferay.portal.service.ClassNameLocalService.class)
	protected com.liferay.portal.service.ClassNameLocalService classNameLocalService;
	@BeanReference(type = com.liferay.portal.service.ClassNameService.class)
	protected com.liferay.portal.service.ClassNameService classNameService;
	@BeanReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@BeanReference(type = com.liferay.portal.service.LayoutLocalService.class)
	protected com.liferay.portal.service.LayoutLocalService layoutLocalService;
	@BeanReference(type = com.liferay.portal.service.LayoutService.class)
	protected com.liferay.portal.service.LayoutService layoutService;
	@BeanReference(type = LayoutPersistence.class)
	protected LayoutPersistence layoutPersistence;
	@BeanReference(type = LayoutFinder.class)
	protected LayoutFinder layoutFinder;
	@BeanReference(type = com.liferay.portal.service.ResourceLocalService.class)
	protected com.liferay.portal.service.ResourceLocalService resourceLocalService;
	@BeanReference(type = com.liferay.portal.service.UserLocalService.class)
	protected com.liferay.portal.service.UserLocalService userLocalService;
	@BeanReference(type = com.liferay.portal.service.UserService.class)
	protected com.liferay.portal.service.UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@BeanReference(type = UserFinder.class)
	protected UserFinder userFinder;
}