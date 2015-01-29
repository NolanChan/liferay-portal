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

package com.liferay.portlet.asset.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AssetVocabularyLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AssetVocabularyLocalService
 * @generated
 */
@ProviderType
public class AssetVocabularyLocalServiceWrapper
	implements AssetVocabularyLocalService,
		ServiceWrapper<AssetVocabularyLocalService> {
	public AssetVocabularyLocalServiceWrapper(
		AssetVocabularyLocalService assetVocabularyLocalService) {
		_assetVocabularyLocalService = assetVocabularyLocalService;
	}

	/**
	* Adds the asset vocabulary to the database. Also notifies the appropriate model listeners.
	*
	* @param assetVocabulary the asset vocabulary
	* @return the asset vocabulary that was added
	*/
	@Override
	public com.liferay.portlet.asset.model.AssetVocabulary addAssetVocabulary(
		com.liferay.portlet.asset.model.AssetVocabulary assetVocabulary) {
		return _assetVocabularyLocalService.addAssetVocabulary(assetVocabulary);
	}

	@Override
	public com.liferay.portlet.asset.model.AssetVocabulary addDefaultVocabulary(
		long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetVocabularyLocalService.addDefaultVocabulary(groupId);
	}

	@Override
	public com.liferay.portlet.asset.model.AssetVocabulary addVocabulary(
		long userId, java.lang.String title,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetVocabularyLocalService.addVocabulary(userId, title,
			serviceContext);
	}

	@Override
	public com.liferay.portlet.asset.model.AssetVocabulary addVocabulary(
		long userId, java.lang.String title,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String settings,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetVocabularyLocalService.addVocabulary(userId, title,
			titleMap, descriptionMap, settings, serviceContext);
	}

	/**
	* @deprecated As of 6.1.0
	*/
	@Deprecated
	@Override
	public com.liferay.portlet.asset.model.AssetVocabulary addVocabulary(
		long userId,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String settings,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetVocabularyLocalService.addVocabulary(userId, titleMap,
			descriptionMap, settings, serviceContext);
	}

	@Override
	public void addVocabularyResources(
		com.liferay.portlet.asset.model.AssetVocabulary vocabulary,
		boolean addGroupPermissions, boolean addGuestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException {
		_assetVocabularyLocalService.addVocabularyResources(vocabulary,
			addGroupPermissions, addGuestPermissions);
	}

	@Override
	public void addVocabularyResources(
		com.liferay.portlet.asset.model.AssetVocabulary vocabulary,
		java.lang.String[] groupPermissions, java.lang.String[] guestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException {
		_assetVocabularyLocalService.addVocabularyResources(vocabulary,
			groupPermissions, guestPermissions);
	}

	/**
	* Creates a new asset vocabulary with the primary key. Does not add the asset vocabulary to the database.
	*
	* @param vocabularyId the primary key for the new asset vocabulary
	* @return the new asset vocabulary
	*/
	@Override
	public com.liferay.portlet.asset.model.AssetVocabulary createAssetVocabulary(
		long vocabularyId) {
		return _assetVocabularyLocalService.createAssetVocabulary(vocabularyId);
	}

	/**
	* Deletes the asset vocabulary from the database. Also notifies the appropriate model listeners.
	*
	* @param assetVocabulary the asset vocabulary
	* @return the asset vocabulary that was removed
	*/
	@Override
	public com.liferay.portlet.asset.model.AssetVocabulary deleteAssetVocabulary(
		com.liferay.portlet.asset.model.AssetVocabulary assetVocabulary) {
		return _assetVocabularyLocalService.deleteAssetVocabulary(assetVocabulary);
	}

	/**
	* Deletes the asset vocabulary with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param vocabularyId the primary key of the asset vocabulary
	* @return the asset vocabulary that was removed
	* @throws PortalException if a asset vocabulary with the primary key could not be found
	*/
	@Override
	public com.liferay.portlet.asset.model.AssetVocabulary deleteAssetVocabulary(
		long vocabularyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetVocabularyLocalService.deleteAssetVocabulary(vocabularyId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetVocabularyLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public void deleteVocabularies(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_assetVocabularyLocalService.deleteVocabularies(groupId);
	}

	@Override
	public void deleteVocabulary(
		com.liferay.portlet.asset.model.AssetVocabulary vocabulary)
		throws com.liferay.portal.kernel.exception.PortalException {
		_assetVocabularyLocalService.deleteVocabulary(vocabulary);
	}

	@Override
	public void deleteVocabulary(long vocabularyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_assetVocabularyLocalService.deleteVocabulary(vocabularyId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _assetVocabularyLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _assetVocabularyLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.asset.model.impl.AssetVocabularyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _assetVocabularyLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.asset.model.impl.AssetVocabularyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _assetVocabularyLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _assetVocabularyLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _assetVocabularyLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.portlet.asset.model.AssetVocabulary fetchAssetVocabulary(
		long vocabularyId) {
		return _assetVocabularyLocalService.fetchAssetVocabulary(vocabularyId);
	}

	/**
	* Returns the asset vocabulary matching the UUID and group.
	*
	* @param uuid the asset vocabulary's UUID
	* @param groupId the primary key of the group
	* @return the matching asset vocabulary, or <code>null</code> if a matching asset vocabulary could not be found
	*/
	@Override
	public com.liferay.portlet.asset.model.AssetVocabulary fetchAssetVocabularyByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _assetVocabularyLocalService.fetchAssetVocabularyByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _assetVocabularyLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns a range of all the asset vocabularies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.asset.model.impl.AssetVocabularyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of asset vocabularies
	* @param end the upper bound of the range of asset vocabularies (not inclusive)
	* @return the range of asset vocabularies
	*/
	@Override
	public java.util.List<com.liferay.portlet.asset.model.AssetVocabulary> getAssetVocabularies(
		int start, int end) {
		return _assetVocabularyLocalService.getAssetVocabularies(start, end);
	}

	/**
	* Returns all the asset vocabularies matching the UUID and company.
	*
	* @param uuid the UUID of the asset vocabularies
	* @param companyId the primary key of the company
	* @return the matching asset vocabularies, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.liferay.portlet.asset.model.AssetVocabulary> getAssetVocabulariesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _assetVocabularyLocalService.getAssetVocabulariesByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of asset vocabularies matching the UUID and company.
	*
	* @param uuid the UUID of the asset vocabularies
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of asset vocabularies
	* @param end the upper bound of the range of asset vocabularies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching asset vocabularies, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.liferay.portlet.asset.model.AssetVocabulary> getAssetVocabulariesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portlet.asset.model.AssetVocabulary> orderByComparator) {
		return _assetVocabularyLocalService.getAssetVocabulariesByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of asset vocabularies.
	*
	* @return the number of asset vocabularies
	*/
	@Override
	public int getAssetVocabulariesCount() {
		return _assetVocabularyLocalService.getAssetVocabulariesCount();
	}

	/**
	* Returns the asset vocabulary with the primary key.
	*
	* @param vocabularyId the primary key of the asset vocabulary
	* @return the asset vocabulary
	* @throws PortalException if a asset vocabulary with the primary key could not be found
	*/
	@Override
	public com.liferay.portlet.asset.model.AssetVocabulary getAssetVocabulary(
		long vocabularyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetVocabularyLocalService.getAssetVocabulary(vocabularyId);
	}

	/**
	* Returns the asset vocabulary matching the UUID and group.
	*
	* @param uuid the asset vocabulary's UUID
	* @param groupId the primary key of the group
	* @return the matching asset vocabulary
	* @throws PortalException if a matching asset vocabulary could not be found
	*/
	@Override
	public com.liferay.portlet.asset.model.AssetVocabulary getAssetVocabularyByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetVocabularyLocalService.getAssetVocabularyByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _assetVocabularyLocalService.getBeanIdentifier();
	}

	@Override
	public java.util.List<com.liferay.portlet.asset.model.AssetVocabulary> getCompanyVocabularies(
		long companyId) {
		return _assetVocabularyLocalService.getCompanyVocabularies(companyId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.portal.kernel.lar.PortletDataContext portletDataContext) {
		return _assetVocabularyLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public java.util.List<com.liferay.portlet.asset.model.AssetVocabulary> getGroupVocabularies(
		long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetVocabularyLocalService.getGroupVocabularies(groupId);
	}

	@Override
	public java.util.List<com.liferay.portlet.asset.model.AssetVocabulary> getGroupVocabularies(
		long groupId, boolean addDefaultVocabulary)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetVocabularyLocalService.getGroupVocabularies(groupId,
			addDefaultVocabulary);
	}

	@Override
	public java.util.List<com.liferay.portlet.asset.model.AssetVocabulary> getGroupVocabularies(
		long groupId, java.lang.String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portlet.asset.model.AssetVocabulary> obc) {
		return _assetVocabularyLocalService.getGroupVocabularies(groupId, name,
			start, end, obc);
	}

	@Override
	public java.util.List<com.liferay.portlet.asset.model.AssetVocabulary> getGroupVocabularies(
		long[] groupIds) {
		return _assetVocabularyLocalService.getGroupVocabularies(groupIds);
	}

	@Override
	public int getGroupVocabulariesCount(long[] groupIds) {
		return _assetVocabularyLocalService.getGroupVocabulariesCount(groupIds);
	}

	@Override
	public com.liferay.portlet.asset.model.AssetVocabulary getGroupVocabulary(
		long groupId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetVocabularyLocalService.getGroupVocabulary(groupId, name);
	}

	@Override
	public java.util.List<com.liferay.portlet.asset.model.AssetVocabulary> getGroupsVocabularies(
		long[] groupIds) {
		return _assetVocabularyLocalService.getGroupsVocabularies(groupIds);
	}

	@Override
	public java.util.List<com.liferay.portlet.asset.model.AssetVocabulary> getGroupsVocabularies(
		long[] groupIds, java.lang.String className) {
		return _assetVocabularyLocalService.getGroupsVocabularies(groupIds,
			className);
	}

	@Override
	public java.util.List<com.liferay.portlet.asset.model.AssetVocabulary> getGroupsVocabularies(
		long[] groupIds, java.lang.String className, long classTypePK) {
		return _assetVocabularyLocalService.getGroupsVocabularies(groupIds,
			className, classTypePK);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetVocabularyLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.util.List<com.liferay.portlet.asset.model.AssetVocabulary> getVocabularies(
		com.liferay.portal.kernel.search.Hits hits)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetVocabularyLocalService.getVocabularies(hits);
	}

	@Override
	public java.util.List<com.liferay.portlet.asset.model.AssetVocabulary> getVocabularies(
		long[] vocabularyIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetVocabularyLocalService.getVocabularies(vocabularyIds);
	}

	@Override
	public com.liferay.portlet.asset.model.AssetVocabulary getVocabulary(
		long vocabularyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetVocabularyLocalService.getVocabulary(vocabularyId);
	}

	@Override
	public com.liferay.portal.kernel.search.BaseModelSearchResult<com.liferay.portlet.asset.model.AssetVocabulary> searchVocabularies(
		long companyId, long groupId, java.lang.String title, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetVocabularyLocalService.searchVocabularies(companyId,
			groupId, title, start, end);
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_assetVocabularyLocalService.setBeanIdentifier(beanIdentifier);
	}

	/**
	* Updates the asset vocabulary in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetVocabulary the asset vocabulary
	* @return the asset vocabulary that was updated
	*/
	@Override
	public com.liferay.portlet.asset.model.AssetVocabulary updateAssetVocabulary(
		com.liferay.portlet.asset.model.AssetVocabulary assetVocabulary) {
		return _assetVocabularyLocalService.updateAssetVocabulary(assetVocabulary);
	}

	@Override
	public com.liferay.portlet.asset.model.AssetVocabulary updateVocabulary(
		long vocabularyId, java.lang.String title,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String settings,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetVocabularyLocalService.updateVocabulary(vocabularyId,
			title, titleMap, descriptionMap, settings, serviceContext);
	}

	/**
	* @deprecated As of 6.1.0
	*/
	@Deprecated
	@Override
	public com.liferay.portlet.asset.model.AssetVocabulary updateVocabulary(
		long vocabularyId,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String settings,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetVocabularyLocalService.updateVocabulary(vocabularyId,
			titleMap, descriptionMap, settings, serviceContext);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	@Deprecated
	public AssetVocabularyLocalService getWrappedAssetVocabularyLocalService() {
		return _assetVocabularyLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	@Deprecated
	public void setWrappedAssetVocabularyLocalService(
		AssetVocabularyLocalService assetVocabularyLocalService) {
		_assetVocabularyLocalService = assetVocabularyLocalService;
	}

	@Override
	public AssetVocabularyLocalService getWrappedService() {
		return _assetVocabularyLocalService;
	}

	@Override
	public void setWrappedService(
		AssetVocabularyLocalService assetVocabularyLocalService) {
		_assetVocabularyLocalService = assetVocabularyLocalService;
	}

	private AssetVocabularyLocalService _assetVocabularyLocalService;
}