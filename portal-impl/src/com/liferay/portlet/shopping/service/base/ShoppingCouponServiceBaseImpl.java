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

package com.liferay.portlet.shopping.service.base;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBFactoryUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.BaseServiceImpl;
import com.liferay.portal.service.persistence.UserFinder;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.util.PortalUtil;

import com.liferay.portlet.shopping.model.ShoppingCoupon;
import com.liferay.portlet.shopping.service.ShoppingCouponService;
import com.liferay.portlet.shopping.service.persistence.ShoppingCategoryPersistence;
import com.liferay.portlet.shopping.service.persistence.ShoppingCouponFinder;
import com.liferay.portlet.shopping.service.persistence.ShoppingCouponPersistence;
import com.liferay.portlet.shopping.service.persistence.ShoppingItemFinder;
import com.liferay.portlet.shopping.service.persistence.ShoppingItemPersistence;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the shopping coupon remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portlet.shopping.service.impl.ShoppingCouponServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portlet.shopping.service.impl.ShoppingCouponServiceImpl
 * @see com.liferay.portlet.shopping.service.ShoppingCouponServiceUtil
 * @generated
 */
public abstract class ShoppingCouponServiceBaseImpl extends BaseServiceImpl
	implements ShoppingCouponService, IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.portlet.shopping.service.ShoppingCouponServiceUtil} to access the shopping coupon remote service.
	 */

	/**
	 * Returns the shopping coupon local service.
	 *
	 * @return the shopping coupon local service
	 */
	public com.liferay.portlet.shopping.service.ShoppingCouponLocalService getShoppingCouponLocalService() {
		return shoppingCouponLocalService;
	}

	/**
	 * Sets the shopping coupon local service.
	 *
	 * @param shoppingCouponLocalService the shopping coupon local service
	 */
	public void setShoppingCouponLocalService(
		com.liferay.portlet.shopping.service.ShoppingCouponLocalService shoppingCouponLocalService) {
		this.shoppingCouponLocalService = shoppingCouponLocalService;
	}

	/**
	 * Returns the shopping coupon remote service.
	 *
	 * @return the shopping coupon remote service
	 */
	public ShoppingCouponService getShoppingCouponService() {
		return shoppingCouponService;
	}

	/**
	 * Sets the shopping coupon remote service.
	 *
	 * @param shoppingCouponService the shopping coupon remote service
	 */
	public void setShoppingCouponService(
		ShoppingCouponService shoppingCouponService) {
		this.shoppingCouponService = shoppingCouponService;
	}

	/**
	 * Returns the shopping coupon persistence.
	 *
	 * @return the shopping coupon persistence
	 */
	public ShoppingCouponPersistence getShoppingCouponPersistence() {
		return shoppingCouponPersistence;
	}

	/**
	 * Sets the shopping coupon persistence.
	 *
	 * @param shoppingCouponPersistence the shopping coupon persistence
	 */
	public void setShoppingCouponPersistence(
		ShoppingCouponPersistence shoppingCouponPersistence) {
		this.shoppingCouponPersistence = shoppingCouponPersistence;
	}

	/**
	 * Returns the shopping coupon finder.
	 *
	 * @return the shopping coupon finder
	 */
	public ShoppingCouponFinder getShoppingCouponFinder() {
		return shoppingCouponFinder;
	}

	/**
	 * Sets the shopping coupon finder.
	 *
	 * @param shoppingCouponFinder the shopping coupon finder
	 */
	public void setShoppingCouponFinder(
		ShoppingCouponFinder shoppingCouponFinder) {
		this.shoppingCouponFinder = shoppingCouponFinder;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
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

	/**
	 * Returns the shopping category local service.
	 *
	 * @return the shopping category local service
	 */
	public com.liferay.portlet.shopping.service.ShoppingCategoryLocalService getShoppingCategoryLocalService() {
		return shoppingCategoryLocalService;
	}

	/**
	 * Sets the shopping category local service.
	 *
	 * @param shoppingCategoryLocalService the shopping category local service
	 */
	public void setShoppingCategoryLocalService(
		com.liferay.portlet.shopping.service.ShoppingCategoryLocalService shoppingCategoryLocalService) {
		this.shoppingCategoryLocalService = shoppingCategoryLocalService;
	}

	/**
	 * Returns the shopping category remote service.
	 *
	 * @return the shopping category remote service
	 */
	public com.liferay.portlet.shopping.service.ShoppingCategoryService getShoppingCategoryService() {
		return shoppingCategoryService;
	}

	/**
	 * Sets the shopping category remote service.
	 *
	 * @param shoppingCategoryService the shopping category remote service
	 */
	public void setShoppingCategoryService(
		com.liferay.portlet.shopping.service.ShoppingCategoryService shoppingCategoryService) {
		this.shoppingCategoryService = shoppingCategoryService;
	}

	/**
	 * Returns the shopping category persistence.
	 *
	 * @return the shopping category persistence
	 */
	public ShoppingCategoryPersistence getShoppingCategoryPersistence() {
		return shoppingCategoryPersistence;
	}

	/**
	 * Sets the shopping category persistence.
	 *
	 * @param shoppingCategoryPersistence the shopping category persistence
	 */
	public void setShoppingCategoryPersistence(
		ShoppingCategoryPersistence shoppingCategoryPersistence) {
		this.shoppingCategoryPersistence = shoppingCategoryPersistence;
	}

	/**
	 * Returns the shopping item local service.
	 *
	 * @return the shopping item local service
	 */
	public com.liferay.portlet.shopping.service.ShoppingItemLocalService getShoppingItemLocalService() {
		return shoppingItemLocalService;
	}

	/**
	 * Sets the shopping item local service.
	 *
	 * @param shoppingItemLocalService the shopping item local service
	 */
	public void setShoppingItemLocalService(
		com.liferay.portlet.shopping.service.ShoppingItemLocalService shoppingItemLocalService) {
		this.shoppingItemLocalService = shoppingItemLocalService;
	}

	/**
	 * Returns the shopping item remote service.
	 *
	 * @return the shopping item remote service
	 */
	public com.liferay.portlet.shopping.service.ShoppingItemService getShoppingItemService() {
		return shoppingItemService;
	}

	/**
	 * Sets the shopping item remote service.
	 *
	 * @param shoppingItemService the shopping item remote service
	 */
	public void setShoppingItemService(
		com.liferay.portlet.shopping.service.ShoppingItemService shoppingItemService) {
		this.shoppingItemService = shoppingItemService;
	}

	/**
	 * Returns the shopping item persistence.
	 *
	 * @return the shopping item persistence
	 */
	public ShoppingItemPersistence getShoppingItemPersistence() {
		return shoppingItemPersistence;
	}

	/**
	 * Sets the shopping item persistence.
	 *
	 * @param shoppingItemPersistence the shopping item persistence
	 */
	public void setShoppingItemPersistence(
		ShoppingItemPersistence shoppingItemPersistence) {
		this.shoppingItemPersistence = shoppingItemPersistence;
	}

	/**
	 * Returns the shopping item finder.
	 *
	 * @return the shopping item finder
	 */
	public ShoppingItemFinder getShoppingItemFinder() {
		return shoppingItemFinder;
	}

	/**
	 * Sets the shopping item finder.
	 *
	 * @param shoppingItemFinder the shopping item finder
	 */
	public void setShoppingItemFinder(ShoppingItemFinder shoppingItemFinder) {
		this.shoppingItemFinder = shoppingItemFinder;
	}

	public void afterPropertiesSet() {
	}

	public void destroy() {
	}

	/**
	 * Returns the Spring bean ID for this bean.
	 *
	 * @return the Spring bean ID for this bean
	 */
	@Override
	public String getBeanIdentifier() {
		return _beanIdentifier;
	}

	/**
	 * Sets the Spring bean ID for this bean.
	 *
	 * @param beanIdentifier the Spring bean ID for this bean
	 */
	@Override
	public void setBeanIdentifier(String beanIdentifier) {
		_beanIdentifier = beanIdentifier;
	}

	protected Class<?> getModelClass() {
		return ShoppingCoupon.class;
	}

	protected String getModelClassName() {
		return ShoppingCoupon.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = shoppingCouponPersistence.getDataSource();

			DB db = DBFactoryUtil.getDB();

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

	@BeanReference(type = com.liferay.portlet.shopping.service.ShoppingCouponLocalService.class)
	protected com.liferay.portlet.shopping.service.ShoppingCouponLocalService shoppingCouponLocalService;
	@BeanReference(type = com.liferay.portlet.shopping.service.ShoppingCouponService.class)
	protected ShoppingCouponService shoppingCouponService;
	@BeanReference(type = ShoppingCouponPersistence.class)
	protected ShoppingCouponPersistence shoppingCouponPersistence;
	@BeanReference(type = ShoppingCouponFinder.class)
	protected ShoppingCouponFinder shoppingCouponFinder;
	@BeanReference(type = com.liferay.counter.service.CounterLocalService.class)
	protected com.liferay.counter.service.CounterLocalService counterLocalService;
	@BeanReference(type = com.liferay.portal.service.UserLocalService.class)
	protected com.liferay.portal.service.UserLocalService userLocalService;
	@BeanReference(type = com.liferay.portal.service.UserService.class)
	protected com.liferay.portal.service.UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@BeanReference(type = UserFinder.class)
	protected UserFinder userFinder;
	@BeanReference(type = com.liferay.portlet.shopping.service.ShoppingCategoryLocalService.class)
	protected com.liferay.portlet.shopping.service.ShoppingCategoryLocalService shoppingCategoryLocalService;
	@BeanReference(type = com.liferay.portlet.shopping.service.ShoppingCategoryService.class)
	protected com.liferay.portlet.shopping.service.ShoppingCategoryService shoppingCategoryService;
	@BeanReference(type = ShoppingCategoryPersistence.class)
	protected ShoppingCategoryPersistence shoppingCategoryPersistence;
	@BeanReference(type = com.liferay.portlet.shopping.service.ShoppingItemLocalService.class)
	protected com.liferay.portlet.shopping.service.ShoppingItemLocalService shoppingItemLocalService;
	@BeanReference(type = com.liferay.portlet.shopping.service.ShoppingItemService.class)
	protected com.liferay.portlet.shopping.service.ShoppingItemService shoppingItemService;
	@BeanReference(type = ShoppingItemPersistence.class)
	protected ShoppingItemPersistence shoppingItemPersistence;
	@BeanReference(type = ShoppingItemFinder.class)
	protected ShoppingItemFinder shoppingItemFinder;
	private String _beanIdentifier;
}