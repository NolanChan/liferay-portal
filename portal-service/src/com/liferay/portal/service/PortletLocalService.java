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

package com.liferay.portal.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.CustomAttributesDisplay;

import com.liferay.portal.kernel.cluster.Clusterable;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.plugin.PluginPackage;
import com.liferay.portal.kernel.portlet.FriendlyURLMapper;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.spring.aop.Skip;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.PersistedModel;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.PortletApp;
import com.liferay.portal.model.PortletCategory;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

/**
 * Provides the local service interface for Portlet. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see PortletLocalServiceUtil
 * @see com.liferay.portal.service.base.PortletLocalServiceBaseImpl
 * @see com.liferay.portal.service.impl.PortletLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface PortletLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PortletLocalServiceUtil} to access the portlet local service. Add custom service methods to {@link com.liferay.portal.service.impl.PortletLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the portlet to the database. Also notifies the appropriate model listeners.
	*
	* @param portlet the portlet
	* @return the portlet that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Portlet addPortlet(Portlet portlet);

	@Skip
	public void addPortletCategory(long companyId, java.lang.String categoryName);

	public void checkPortlet(Portlet portlet) throws PortalException;

	public void checkPortlets(long companyId) throws PortalException;

	@Skip
	public void clearCache();

	/**
	* @deprecated As of 7.0.0, replaced by {@link #clearPortletsMap)}
	*/
	@Clusterable
	@java.lang.Deprecated
	@Transactional(enabled = false)
	public void clearCompanyPortletsPool();

	@Clusterable
	@Transactional(enabled = false)
	public void clearPortletsMap();

	@Skip
	public Portlet clonePortlet(java.lang.String portletId);

	/**
	* Creates a new portlet with the primary key. Does not add the portlet to the database.
	*
	* @param id the primary key for the new portlet
	* @return the new portlet
	*/
	public Portlet createPortlet(long id);

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	public void deletePortlet(long companyId, java.lang.String portletId,
		long plid) throws PortalException;

	/**
	* Deletes the portlet with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the portlet
	* @return the portlet that was removed
	* @throws PortalException if a portlet with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public Portlet deletePortlet(long id) throws PortalException;

	/**
	* Deletes the portlet from the database. Also notifies the appropriate model listeners.
	*
	* @param portlet the portlet
	* @return the portlet that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public Portlet deletePortlet(Portlet portlet);

	public void deletePortlets(long companyId, java.lang.String[] portletIds,
		long plid) throws PortalException;

	public Portlet deployRemotePortlet(Portlet portlet,
		java.lang.String categoryName) throws PortalException;

	public Portlet deployRemotePortlet(Portlet portlet,
		java.lang.String[] categoryNames) throws PortalException;

	public Portlet deployRemotePortlet(Portlet portlet,
		java.lang.String[] categoryNames, boolean eagerDestroy)
		throws PortalException;

	@Skip
	public void destroyPortlet(Portlet portlet);

	@Skip
	public void destroyRemotePortlet(Portlet portlet);

	public DynamicQuery dynamicQuery();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.PortletModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.PortletModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Portlet fetchPortlet(long id);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Skip
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CustomAttributesDisplay> getCustomAttributesDisplays();

	@Skip
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PortletCategory getEARDisplay(java.lang.String xml);

	@Skip
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Portlet> getFriendlyURLMapperPortlets();

	@Skip
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<FriendlyURLMapper> getFriendlyURLMappers();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Returns the portlet with the primary key.
	*
	* @param id the primary key of the portlet
	* @return the portlet
	* @throws PortalException if a portlet with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Portlet getPortlet(long id) throws PortalException;

	@Skip
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PortletApp getPortletApp(java.lang.String servletContextName);

	@Skip
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Portlet getPortletById(long companyId, java.lang.String portletId);

	@Skip
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Portlet getPortletById(java.lang.String portletId);

	@Skip
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Portlet getPortletByStrutsPath(long companyId,
		java.lang.String strutsPath);

	@Skip
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Portlet> getPortlets();

	@Skip
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Portlet> getPortlets(long companyId);

	@Skip
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Portlet> getPortlets(long companyId, boolean showSystem,
		boolean showPortal);

	/**
	* Returns a range of all the portlets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.PortletModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of portlets
	* @param end the upper bound of the range of portlets (not inclusive)
	* @return the range of portlets
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Portlet> getPortlets(int start, int end);

	/**
	* Returns the number of portlets.
	*
	* @return the number of portlets
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getPortletsCount();

	@Skip
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Portlet> getScopablePortlets();

	@Skip
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PortletCategory getWARDisplay(java.lang.String servletContextName,
		java.lang.String xml);

	@Skip
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasPortlet(long companyId, java.lang.String portletId);

	@Skip
	public void initEAR(ServletContext servletContext, java.lang.String[] xmls,
		PluginPackage pluginPackage);

	@Skip
	public List<Portlet> initWAR(java.lang.String servletContextName,
		ServletContext servletContext, java.lang.String[] xmls,
		PluginPackage pluginPackage);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Map<java.lang.String, Portlet> loadGetPortletsMap(long companyId);

	/**
	* @deprecated As of 7.0.0, replaced by {@link #loadGetPortletsMap(long))}
	*/
	@java.lang.Deprecated
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Map<java.lang.String, Portlet> loadGetPortletsPool(long companyId);

	@Clusterable
	@Transactional(enabled = false)
	public void removeCompanyPortletsPool(long companyId);

	public Portlet updatePortlet(long companyId, java.lang.String portletId,
		java.lang.String roles, boolean active);

	/**
	* Updates the portlet in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param portlet the portlet
	* @return the portlet that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Portlet updatePortlet(Portlet portlet);
}