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

package com.liferay.portal.service.base;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.PersistedModel;
import com.liferay.portal.service.BaseLocalServiceImpl;
import com.liferay.portal.service.CompanyLocalService;
import com.liferay.portal.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.service.persistence.AccountPersistence;
import com.liferay.portal.service.persistence.CompanyPersistence;
import com.liferay.portal.service.persistence.ContactPersistence;
import com.liferay.portal.service.persistence.GroupFinder;
import com.liferay.portal.service.persistence.GroupPersistence;
import com.liferay.portal.service.persistence.ImagePersistence;
import com.liferay.portal.service.persistence.LayoutPrototypePersistence;
import com.liferay.portal.service.persistence.LayoutSetPersistence;
import com.liferay.portal.service.persistence.LayoutSetPrototypePersistence;
import com.liferay.portal.service.persistence.OrganizationFinder;
import com.liferay.portal.service.persistence.OrganizationPersistence;
import com.liferay.portal.service.persistence.PasswordPolicyFinder;
import com.liferay.portal.service.persistence.PasswordPolicyPersistence;
import com.liferay.portal.service.persistence.PortalPreferencesPersistence;
import com.liferay.portal.service.persistence.PortletPersistence;
import com.liferay.portal.service.persistence.ResourcePermissionFinder;
import com.liferay.portal.service.persistence.ResourcePermissionPersistence;
import com.liferay.portal.service.persistence.RoleFinder;
import com.liferay.portal.service.persistence.RolePersistence;
import com.liferay.portal.service.persistence.UserFinder;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.VirtualHostPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the company local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portal.service.impl.CompanyLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.service.impl.CompanyLocalServiceImpl
 * @see com.liferay.portal.service.CompanyLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class CompanyLocalServiceBaseImpl extends BaseLocalServiceImpl
	implements CompanyLocalService, IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.portal.service.CompanyLocalServiceUtil} to access the company local service.
	 */

	/**
	 * Adds the company to the database. Also notifies the appropriate model listeners.
	 *
	 * @param company the company
	 * @return the company that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Company addCompany(Company company) {
		company.setNew(true);

		return companyPersistence.update(company);
	}

	/**
	 * Creates a new company with the primary key. Does not add the company to the database.
	 *
	 * @param companyId the primary key for the new company
	 * @return the new company
	 */
	@Override
	public Company createCompany(long companyId) {
		return companyPersistence.create(companyId);
	}

	/**
	 * Deletes the company with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param companyId the primary key of the company
	 * @return the company that was removed
	 * @throws PortalException if a company with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Company deleteCompany(long companyId) throws PortalException {
		return companyPersistence.remove(companyId);
	}

	/**
	 * Deletes the company from the database. Also notifies the appropriate model listeners.
	 *
	 * @param company the company
	 * @return the company that was removed
	 * @throws PortalException
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Company deleteCompany(Company company) throws PortalException {
		return companyPersistence.remove(company);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(Company.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return companyPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.CompanyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) {
		return companyPersistence.findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.CompanyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator) {
		return companyPersistence.findWithDynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return companyPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) {
		return companyPersistence.countWithDynamicQuery(dynamicQuery, projection);
	}

	@Override
	public Company fetchCompany(long companyId) {
		return companyPersistence.fetchByPrimaryKey(companyId);
	}

	/**
	 * Returns the company with the primary key.
	 *
	 * @param companyId the primary key of the company
	 * @return the company
	 * @throws PortalException if a company with the primary key could not be found
	 */
	@Override
	public Company getCompany(long companyId) throws PortalException {
		return companyPersistence.findByPrimaryKey(companyId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(com.liferay.portal.service.CompanyLocalServiceUtil.getService());
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(Company.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("companyId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(com.liferay.portal.service.CompanyLocalServiceUtil.getService());
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(Company.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("companyId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(com.liferay.portal.service.CompanyLocalServiceUtil.getService());
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(Company.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("companyId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return companyLocalService.deleteCompany((Company)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return companyPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the companies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.CompanyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of companies
	 * @param end the upper bound of the range of companies (not inclusive)
	 * @return the range of companies
	 */
	@Override
	public List<Company> getCompanies(int start, int end) {
		return companyPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of companies.
	 *
	 * @return the number of companies
	 */
	@Override
	public int getCompaniesCount() {
		return companyPersistence.countAll();
	}

	/**
	 * Updates the company in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param company the company
	 * @return the company that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Company updateCompany(Company company) {
		return companyPersistence.update(company);
	}

	/**
	 * Returns the company local service.
	 *
	 * @return the company local service
	 */
	public CompanyLocalService getCompanyLocalService() {
		return companyLocalService;
	}

	/**
	 * Sets the company local service.
	 *
	 * @param companyLocalService the company local service
	 */
	public void setCompanyLocalService(CompanyLocalService companyLocalService) {
		this.companyLocalService = companyLocalService;
	}

	/**
	 * Returns the company persistence.
	 *
	 * @return the company persistence
	 */
	public CompanyPersistence getCompanyPersistence() {
		return companyPersistence;
	}

	/**
	 * Sets the company persistence.
	 *
	 * @param companyPersistence the company persistence
	 */
	public void setCompanyPersistence(CompanyPersistence companyPersistence) {
		this.companyPersistence = companyPersistence;
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
	 * Returns the account local service.
	 *
	 * @return the account local service
	 */
	public com.liferay.portal.service.AccountLocalService getAccountLocalService() {
		return accountLocalService;
	}

	/**
	 * Sets the account local service.
	 *
	 * @param accountLocalService the account local service
	 */
	public void setAccountLocalService(
		com.liferay.portal.service.AccountLocalService accountLocalService) {
		this.accountLocalService = accountLocalService;
	}

	/**
	 * Returns the account persistence.
	 *
	 * @return the account persistence
	 */
	public AccountPersistence getAccountPersistence() {
		return accountPersistence;
	}

	/**
	 * Sets the account persistence.
	 *
	 * @param accountPersistence the account persistence
	 */
	public void setAccountPersistence(AccountPersistence accountPersistence) {
		this.accountPersistence = accountPersistence;
	}

	/**
	 * Returns the contact local service.
	 *
	 * @return the contact local service
	 */
	public com.liferay.portal.service.ContactLocalService getContactLocalService() {
		return contactLocalService;
	}

	/**
	 * Sets the contact local service.
	 *
	 * @param contactLocalService the contact local service
	 */
	public void setContactLocalService(
		com.liferay.portal.service.ContactLocalService contactLocalService) {
		this.contactLocalService = contactLocalService;
	}

	/**
	 * Returns the contact persistence.
	 *
	 * @return the contact persistence
	 */
	public ContactPersistence getContactPersistence() {
		return contactPersistence;
	}

	/**
	 * Sets the contact persistence.
	 *
	 * @param contactPersistence the contact persistence
	 */
	public void setContactPersistence(ContactPersistence contactPersistence) {
		this.contactPersistence = contactPersistence;
	}

	/**
	 * Returns the group local service.
	 *
	 * @return the group local service
	 */
	public com.liferay.portal.service.GroupLocalService getGroupLocalService() {
		return groupLocalService;
	}

	/**
	 * Sets the group local service.
	 *
	 * @param groupLocalService the group local service
	 */
	public void setGroupLocalService(
		com.liferay.portal.service.GroupLocalService groupLocalService) {
		this.groupLocalService = groupLocalService;
	}

	/**
	 * Returns the group persistence.
	 *
	 * @return the group persistence
	 */
	public GroupPersistence getGroupPersistence() {
		return groupPersistence;
	}

	/**
	 * Sets the group persistence.
	 *
	 * @param groupPersistence the group persistence
	 */
	public void setGroupPersistence(GroupPersistence groupPersistence) {
		this.groupPersistence = groupPersistence;
	}

	/**
	 * Returns the group finder.
	 *
	 * @return the group finder
	 */
	public GroupFinder getGroupFinder() {
		return groupFinder;
	}

	/**
	 * Sets the group finder.
	 *
	 * @param groupFinder the group finder
	 */
	public void setGroupFinder(GroupFinder groupFinder) {
		this.groupFinder = groupFinder;
	}

	/**
	 * Returns the image local service.
	 *
	 * @return the image local service
	 */
	public com.liferay.portal.service.ImageLocalService getImageLocalService() {
		return imageLocalService;
	}

	/**
	 * Sets the image local service.
	 *
	 * @param imageLocalService the image local service
	 */
	public void setImageLocalService(
		com.liferay.portal.service.ImageLocalService imageLocalService) {
		this.imageLocalService = imageLocalService;
	}

	/**
	 * Returns the image persistence.
	 *
	 * @return the image persistence
	 */
	public ImagePersistence getImagePersistence() {
		return imagePersistence;
	}

	/**
	 * Sets the image persistence.
	 *
	 * @param imagePersistence the image persistence
	 */
	public void setImagePersistence(ImagePersistence imagePersistence) {
		this.imagePersistence = imagePersistence;
	}

	/**
	 * Returns the layout prototype local service.
	 *
	 * @return the layout prototype local service
	 */
	public com.liferay.portal.service.LayoutPrototypeLocalService getLayoutPrototypeLocalService() {
		return layoutPrototypeLocalService;
	}

	/**
	 * Sets the layout prototype local service.
	 *
	 * @param layoutPrototypeLocalService the layout prototype local service
	 */
	public void setLayoutPrototypeLocalService(
		com.liferay.portal.service.LayoutPrototypeLocalService layoutPrototypeLocalService) {
		this.layoutPrototypeLocalService = layoutPrototypeLocalService;
	}

	/**
	 * Returns the layout prototype persistence.
	 *
	 * @return the layout prototype persistence
	 */
	public LayoutPrototypePersistence getLayoutPrototypePersistence() {
		return layoutPrototypePersistence;
	}

	/**
	 * Sets the layout prototype persistence.
	 *
	 * @param layoutPrototypePersistence the layout prototype persistence
	 */
	public void setLayoutPrototypePersistence(
		LayoutPrototypePersistence layoutPrototypePersistence) {
		this.layoutPrototypePersistence = layoutPrototypePersistence;
	}

	/**
	 * Returns the layout set local service.
	 *
	 * @return the layout set local service
	 */
	public com.liferay.portal.service.LayoutSetLocalService getLayoutSetLocalService() {
		return layoutSetLocalService;
	}

	/**
	 * Sets the layout set local service.
	 *
	 * @param layoutSetLocalService the layout set local service
	 */
	public void setLayoutSetLocalService(
		com.liferay.portal.service.LayoutSetLocalService layoutSetLocalService) {
		this.layoutSetLocalService = layoutSetLocalService;
	}

	/**
	 * Returns the layout set persistence.
	 *
	 * @return the layout set persistence
	 */
	public LayoutSetPersistence getLayoutSetPersistence() {
		return layoutSetPersistence;
	}

	/**
	 * Sets the layout set persistence.
	 *
	 * @param layoutSetPersistence the layout set persistence
	 */
	public void setLayoutSetPersistence(
		LayoutSetPersistence layoutSetPersistence) {
		this.layoutSetPersistence = layoutSetPersistence;
	}

	/**
	 * Returns the layout set prototype local service.
	 *
	 * @return the layout set prototype local service
	 */
	public com.liferay.portal.service.LayoutSetPrototypeLocalService getLayoutSetPrototypeLocalService() {
		return layoutSetPrototypeLocalService;
	}

	/**
	 * Sets the layout set prototype local service.
	 *
	 * @param layoutSetPrototypeLocalService the layout set prototype local service
	 */
	public void setLayoutSetPrototypeLocalService(
		com.liferay.portal.service.LayoutSetPrototypeLocalService layoutSetPrototypeLocalService) {
		this.layoutSetPrototypeLocalService = layoutSetPrototypeLocalService;
	}

	/**
	 * Returns the layout set prototype persistence.
	 *
	 * @return the layout set prototype persistence
	 */
	public LayoutSetPrototypePersistence getLayoutSetPrototypePersistence() {
		return layoutSetPrototypePersistence;
	}

	/**
	 * Sets the layout set prototype persistence.
	 *
	 * @param layoutSetPrototypePersistence the layout set prototype persistence
	 */
	public void setLayoutSetPrototypePersistence(
		LayoutSetPrototypePersistence layoutSetPrototypePersistence) {
		this.layoutSetPrototypePersistence = layoutSetPrototypePersistence;
	}

	/**
	 * Returns the organization local service.
	 *
	 * @return the organization local service
	 */
	public com.liferay.portal.service.OrganizationLocalService getOrganizationLocalService() {
		return organizationLocalService;
	}

	/**
	 * Sets the organization local service.
	 *
	 * @param organizationLocalService the organization local service
	 */
	public void setOrganizationLocalService(
		com.liferay.portal.service.OrganizationLocalService organizationLocalService) {
		this.organizationLocalService = organizationLocalService;
	}

	/**
	 * Returns the organization persistence.
	 *
	 * @return the organization persistence
	 */
	public OrganizationPersistence getOrganizationPersistence() {
		return organizationPersistence;
	}

	/**
	 * Sets the organization persistence.
	 *
	 * @param organizationPersistence the organization persistence
	 */
	public void setOrganizationPersistence(
		OrganizationPersistence organizationPersistence) {
		this.organizationPersistence = organizationPersistence;
	}

	/**
	 * Returns the organization finder.
	 *
	 * @return the organization finder
	 */
	public OrganizationFinder getOrganizationFinder() {
		return organizationFinder;
	}

	/**
	 * Sets the organization finder.
	 *
	 * @param organizationFinder the organization finder
	 */
	public void setOrganizationFinder(OrganizationFinder organizationFinder) {
		this.organizationFinder = organizationFinder;
	}

	/**
	 * Returns the password policy local service.
	 *
	 * @return the password policy local service
	 */
	public com.liferay.portal.service.PasswordPolicyLocalService getPasswordPolicyLocalService() {
		return passwordPolicyLocalService;
	}

	/**
	 * Sets the password policy local service.
	 *
	 * @param passwordPolicyLocalService the password policy local service
	 */
	public void setPasswordPolicyLocalService(
		com.liferay.portal.service.PasswordPolicyLocalService passwordPolicyLocalService) {
		this.passwordPolicyLocalService = passwordPolicyLocalService;
	}

	/**
	 * Returns the password policy persistence.
	 *
	 * @return the password policy persistence
	 */
	public PasswordPolicyPersistence getPasswordPolicyPersistence() {
		return passwordPolicyPersistence;
	}

	/**
	 * Sets the password policy persistence.
	 *
	 * @param passwordPolicyPersistence the password policy persistence
	 */
	public void setPasswordPolicyPersistence(
		PasswordPolicyPersistence passwordPolicyPersistence) {
		this.passwordPolicyPersistence = passwordPolicyPersistence;
	}

	/**
	 * Returns the password policy finder.
	 *
	 * @return the password policy finder
	 */
	public PasswordPolicyFinder getPasswordPolicyFinder() {
		return passwordPolicyFinder;
	}

	/**
	 * Sets the password policy finder.
	 *
	 * @param passwordPolicyFinder the password policy finder
	 */
	public void setPasswordPolicyFinder(
		PasswordPolicyFinder passwordPolicyFinder) {
		this.passwordPolicyFinder = passwordPolicyFinder;
	}

	/**
	 * Returns the portal preferences local service.
	 *
	 * @return the portal preferences local service
	 */
	public com.liferay.portal.service.PortalPreferencesLocalService getPortalPreferencesLocalService() {
		return portalPreferencesLocalService;
	}

	/**
	 * Sets the portal preferences local service.
	 *
	 * @param portalPreferencesLocalService the portal preferences local service
	 */
	public void setPortalPreferencesLocalService(
		com.liferay.portal.service.PortalPreferencesLocalService portalPreferencesLocalService) {
		this.portalPreferencesLocalService = portalPreferencesLocalService;
	}

	/**
	 * Returns the portal preferences persistence.
	 *
	 * @return the portal preferences persistence
	 */
	public PortalPreferencesPersistence getPortalPreferencesPersistence() {
		return portalPreferencesPersistence;
	}

	/**
	 * Sets the portal preferences persistence.
	 *
	 * @param portalPreferencesPersistence the portal preferences persistence
	 */
	public void setPortalPreferencesPersistence(
		PortalPreferencesPersistence portalPreferencesPersistence) {
		this.portalPreferencesPersistence = portalPreferencesPersistence;
	}

	/**
	 * Returns the portlet local service.
	 *
	 * @return the portlet local service
	 */
	public com.liferay.portal.service.PortletLocalService getPortletLocalService() {
		return portletLocalService;
	}

	/**
	 * Sets the portlet local service.
	 *
	 * @param portletLocalService the portlet local service
	 */
	public void setPortletLocalService(
		com.liferay.portal.service.PortletLocalService portletLocalService) {
		this.portletLocalService = portletLocalService;
	}

	/**
	 * Returns the portlet persistence.
	 *
	 * @return the portlet persistence
	 */
	public PortletPersistence getPortletPersistence() {
		return portletPersistence;
	}

	/**
	 * Sets the portlet persistence.
	 *
	 * @param portletPersistence the portlet persistence
	 */
	public void setPortletPersistence(PortletPersistence portletPersistence) {
		this.portletPersistence = portletPersistence;
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
	 * Returns the resource permission local service.
	 *
	 * @return the resource permission local service
	 */
	public com.liferay.portal.service.ResourcePermissionLocalService getResourcePermissionLocalService() {
		return resourcePermissionLocalService;
	}

	/**
	 * Sets the resource permission local service.
	 *
	 * @param resourcePermissionLocalService the resource permission local service
	 */
	public void setResourcePermissionLocalService(
		com.liferay.portal.service.ResourcePermissionLocalService resourcePermissionLocalService) {
		this.resourcePermissionLocalService = resourcePermissionLocalService;
	}

	/**
	 * Returns the resource permission persistence.
	 *
	 * @return the resource permission persistence
	 */
	public ResourcePermissionPersistence getResourcePermissionPersistence() {
		return resourcePermissionPersistence;
	}

	/**
	 * Sets the resource permission persistence.
	 *
	 * @param resourcePermissionPersistence the resource permission persistence
	 */
	public void setResourcePermissionPersistence(
		ResourcePermissionPersistence resourcePermissionPersistence) {
		this.resourcePermissionPersistence = resourcePermissionPersistence;
	}

	/**
	 * Returns the resource permission finder.
	 *
	 * @return the resource permission finder
	 */
	public ResourcePermissionFinder getResourcePermissionFinder() {
		return resourcePermissionFinder;
	}

	/**
	 * Sets the resource permission finder.
	 *
	 * @param resourcePermissionFinder the resource permission finder
	 */
	public void setResourcePermissionFinder(
		ResourcePermissionFinder resourcePermissionFinder) {
		this.resourcePermissionFinder = resourcePermissionFinder;
	}

	/**
	 * Returns the role local service.
	 *
	 * @return the role local service
	 */
	public com.liferay.portal.service.RoleLocalService getRoleLocalService() {
		return roleLocalService;
	}

	/**
	 * Sets the role local service.
	 *
	 * @param roleLocalService the role local service
	 */
	public void setRoleLocalService(
		com.liferay.portal.service.RoleLocalService roleLocalService) {
		this.roleLocalService = roleLocalService;
	}

	/**
	 * Returns the role persistence.
	 *
	 * @return the role persistence
	 */
	public RolePersistence getRolePersistence() {
		return rolePersistence;
	}

	/**
	 * Sets the role persistence.
	 *
	 * @param rolePersistence the role persistence
	 */
	public void setRolePersistence(RolePersistence rolePersistence) {
		this.rolePersistence = rolePersistence;
	}

	/**
	 * Returns the role finder.
	 *
	 * @return the role finder
	 */
	public RoleFinder getRoleFinder() {
		return roleFinder;
	}

	/**
	 * Sets the role finder.
	 *
	 * @param roleFinder the role finder
	 */
	public void setRoleFinder(RoleFinder roleFinder) {
		this.roleFinder = roleFinder;
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

	/**
	 * Returns the virtual host local service.
	 *
	 * @return the virtual host local service
	 */
	public com.liferay.portal.service.VirtualHostLocalService getVirtualHostLocalService() {
		return virtualHostLocalService;
	}

	/**
	 * Sets the virtual host local service.
	 *
	 * @param virtualHostLocalService the virtual host local service
	 */
	public void setVirtualHostLocalService(
		com.liferay.portal.service.VirtualHostLocalService virtualHostLocalService) {
		this.virtualHostLocalService = virtualHostLocalService;
	}

	/**
	 * Returns the virtual host persistence.
	 *
	 * @return the virtual host persistence
	 */
	public VirtualHostPersistence getVirtualHostPersistence() {
		return virtualHostPersistence;
	}

	/**
	 * Sets the virtual host persistence.
	 *
	 * @param virtualHostPersistence the virtual host persistence
	 */
	public void setVirtualHostPersistence(
		VirtualHostPersistence virtualHostPersistence) {
		this.virtualHostPersistence = virtualHostPersistence;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register("com.liferay.portal.model.Company",
			companyLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.portal.model.Company");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return CompanyLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return Company.class;
	}

	protected String getModelClassName() {
		return Company.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = companyPersistence.getDataSource();

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

	@BeanReference(type = com.liferay.portal.service.CompanyLocalService.class)
	protected CompanyLocalService companyLocalService;
	@BeanReference(type = CompanyPersistence.class)
	protected CompanyPersistence companyPersistence;
	@BeanReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@BeanReference(type = com.liferay.portal.service.AccountLocalService.class)
	protected com.liferay.portal.service.AccountLocalService accountLocalService;
	@BeanReference(type = AccountPersistence.class)
	protected AccountPersistence accountPersistence;
	@BeanReference(type = com.liferay.portal.service.ContactLocalService.class)
	protected com.liferay.portal.service.ContactLocalService contactLocalService;
	@BeanReference(type = ContactPersistence.class)
	protected ContactPersistence contactPersistence;
	@BeanReference(type = com.liferay.portal.service.GroupLocalService.class)
	protected com.liferay.portal.service.GroupLocalService groupLocalService;
	@BeanReference(type = GroupPersistence.class)
	protected GroupPersistence groupPersistence;
	@BeanReference(type = GroupFinder.class)
	protected GroupFinder groupFinder;
	@BeanReference(type = com.liferay.portal.service.ImageLocalService.class)
	protected com.liferay.portal.service.ImageLocalService imageLocalService;
	@BeanReference(type = ImagePersistence.class)
	protected ImagePersistence imagePersistence;
	@BeanReference(type = com.liferay.portal.service.LayoutPrototypeLocalService.class)
	protected com.liferay.portal.service.LayoutPrototypeLocalService layoutPrototypeLocalService;
	@BeanReference(type = LayoutPrototypePersistence.class)
	protected LayoutPrototypePersistence layoutPrototypePersistence;
	@BeanReference(type = com.liferay.portal.service.LayoutSetLocalService.class)
	protected com.liferay.portal.service.LayoutSetLocalService layoutSetLocalService;
	@BeanReference(type = LayoutSetPersistence.class)
	protected LayoutSetPersistence layoutSetPersistence;
	@BeanReference(type = com.liferay.portal.service.LayoutSetPrototypeLocalService.class)
	protected com.liferay.portal.service.LayoutSetPrototypeLocalService layoutSetPrototypeLocalService;
	@BeanReference(type = LayoutSetPrototypePersistence.class)
	protected LayoutSetPrototypePersistence layoutSetPrototypePersistence;
	@BeanReference(type = com.liferay.portal.service.OrganizationLocalService.class)
	protected com.liferay.portal.service.OrganizationLocalService organizationLocalService;
	@BeanReference(type = OrganizationPersistence.class)
	protected OrganizationPersistence organizationPersistence;
	@BeanReference(type = OrganizationFinder.class)
	protected OrganizationFinder organizationFinder;
	@BeanReference(type = com.liferay.portal.service.PasswordPolicyLocalService.class)
	protected com.liferay.portal.service.PasswordPolicyLocalService passwordPolicyLocalService;
	@BeanReference(type = PasswordPolicyPersistence.class)
	protected PasswordPolicyPersistence passwordPolicyPersistence;
	@BeanReference(type = PasswordPolicyFinder.class)
	protected PasswordPolicyFinder passwordPolicyFinder;
	@BeanReference(type = com.liferay.portal.service.PortalPreferencesLocalService.class)
	protected com.liferay.portal.service.PortalPreferencesLocalService portalPreferencesLocalService;
	@BeanReference(type = PortalPreferencesPersistence.class)
	protected PortalPreferencesPersistence portalPreferencesPersistence;
	@BeanReference(type = com.liferay.portal.service.PortletLocalService.class)
	protected com.liferay.portal.service.PortletLocalService portletLocalService;
	@BeanReference(type = PortletPersistence.class)
	protected PortletPersistence portletPersistence;
	@BeanReference(type = com.liferay.portal.service.ResourceLocalService.class)
	protected com.liferay.portal.service.ResourceLocalService resourceLocalService;
	@BeanReference(type = com.liferay.portal.service.ResourcePermissionLocalService.class)
	protected com.liferay.portal.service.ResourcePermissionLocalService resourcePermissionLocalService;
	@BeanReference(type = ResourcePermissionPersistence.class)
	protected ResourcePermissionPersistence resourcePermissionPersistence;
	@BeanReference(type = ResourcePermissionFinder.class)
	protected ResourcePermissionFinder resourcePermissionFinder;
	@BeanReference(type = com.liferay.portal.service.RoleLocalService.class)
	protected com.liferay.portal.service.RoleLocalService roleLocalService;
	@BeanReference(type = RolePersistence.class)
	protected RolePersistence rolePersistence;
	@BeanReference(type = RoleFinder.class)
	protected RoleFinder roleFinder;
	@BeanReference(type = com.liferay.portal.service.UserLocalService.class)
	protected com.liferay.portal.service.UserLocalService userLocalService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@BeanReference(type = UserFinder.class)
	protected UserFinder userFinder;
	@BeanReference(type = com.liferay.portal.service.VirtualHostLocalService.class)
	protected com.liferay.portal.service.VirtualHostLocalService virtualHostLocalService;
	@BeanReference(type = VirtualHostPersistence.class)
	protected VirtualHostPersistence virtualHostPersistence;
	@BeanReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry;
}