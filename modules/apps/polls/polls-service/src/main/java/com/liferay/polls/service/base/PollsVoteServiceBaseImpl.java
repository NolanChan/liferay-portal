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

package com.liferay.polls.service.base;

import com.liferay.polls.model.PollsVote;
import com.liferay.polls.service.PollsVoteService;
import com.liferay.polls.service.persistence.PollsChoicePersistence;
import com.liferay.polls.service.persistence.PollsQuestionPersistence;
import com.liferay.polls.service.persistence.PollsVotePersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.service.BaseServiceImpl;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.spring.extender.service.ServiceReference;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the polls vote remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.polls.service.impl.PollsVoteServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.polls.service.impl.PollsVoteServiceImpl
 * @see com.liferay.polls.service.PollsVoteServiceUtil
 * @generated
 */
public abstract class PollsVoteServiceBaseImpl extends BaseServiceImpl
	implements PollsVoteService, IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.polls.service.PollsVoteServiceUtil} to access the polls vote remote service.
	 */

	/**
	 * Returns the polls vote local service.
	 *
	 * @return the polls vote local service
	 */
	public com.liferay.polls.service.PollsVoteLocalService getPollsVoteLocalService() {
		return pollsVoteLocalService;
	}

	/**
	 * Sets the polls vote local service.
	 *
	 * @param pollsVoteLocalService the polls vote local service
	 */
	public void setPollsVoteLocalService(
		com.liferay.polls.service.PollsVoteLocalService pollsVoteLocalService) {
		this.pollsVoteLocalService = pollsVoteLocalService;
	}

	/**
	 * Returns the polls vote remote service.
	 *
	 * @return the polls vote remote service
	 */
	public PollsVoteService getPollsVoteService() {
		return pollsVoteService;
	}

	/**
	 * Sets the polls vote remote service.
	 *
	 * @param pollsVoteService the polls vote remote service
	 */
	public void setPollsVoteService(PollsVoteService pollsVoteService) {
		this.pollsVoteService = pollsVoteService;
	}

	/**
	 * Returns the polls vote persistence.
	 *
	 * @return the polls vote persistence
	 */
	public PollsVotePersistence getPollsVotePersistence() {
		return pollsVotePersistence;
	}

	/**
	 * Sets the polls vote persistence.
	 *
	 * @param pollsVotePersistence the polls vote persistence
	 */
	public void setPollsVotePersistence(
		PollsVotePersistence pollsVotePersistence) {
		this.pollsVotePersistence = pollsVotePersistence;
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
	 * Returns the polls choice local service.
	 *
	 * @return the polls choice local service
	 */
	public com.liferay.polls.service.PollsChoiceLocalService getPollsChoiceLocalService() {
		return pollsChoiceLocalService;
	}

	/**
	 * Sets the polls choice local service.
	 *
	 * @param pollsChoiceLocalService the polls choice local service
	 */
	public void setPollsChoiceLocalService(
		com.liferay.polls.service.PollsChoiceLocalService pollsChoiceLocalService) {
		this.pollsChoiceLocalService = pollsChoiceLocalService;
	}

	/**
	 * Returns the polls choice remote service.
	 *
	 * @return the polls choice remote service
	 */
	public com.liferay.polls.service.PollsChoiceService getPollsChoiceService() {
		return pollsChoiceService;
	}

	/**
	 * Sets the polls choice remote service.
	 *
	 * @param pollsChoiceService the polls choice remote service
	 */
	public void setPollsChoiceService(
		com.liferay.polls.service.PollsChoiceService pollsChoiceService) {
		this.pollsChoiceService = pollsChoiceService;
	}

	/**
	 * Returns the polls choice persistence.
	 *
	 * @return the polls choice persistence
	 */
	public PollsChoicePersistence getPollsChoicePersistence() {
		return pollsChoicePersistence;
	}

	/**
	 * Sets the polls choice persistence.
	 *
	 * @param pollsChoicePersistence the polls choice persistence
	 */
	public void setPollsChoicePersistence(
		PollsChoicePersistence pollsChoicePersistence) {
		this.pollsChoicePersistence = pollsChoicePersistence;
	}

	/**
	 * Returns the polls question local service.
	 *
	 * @return the polls question local service
	 */
	public com.liferay.polls.service.PollsQuestionLocalService getPollsQuestionLocalService() {
		return pollsQuestionLocalService;
	}

	/**
	 * Sets the polls question local service.
	 *
	 * @param pollsQuestionLocalService the polls question local service
	 */
	public void setPollsQuestionLocalService(
		com.liferay.polls.service.PollsQuestionLocalService pollsQuestionLocalService) {
		this.pollsQuestionLocalService = pollsQuestionLocalService;
	}

	/**
	 * Returns the polls question remote service.
	 *
	 * @return the polls question remote service
	 */
	public com.liferay.polls.service.PollsQuestionService getPollsQuestionService() {
		return pollsQuestionService;
	}

	/**
	 * Sets the polls question remote service.
	 *
	 * @param pollsQuestionService the polls question remote service
	 */
	public void setPollsQuestionService(
		com.liferay.polls.service.PollsQuestionService pollsQuestionService) {
		this.pollsQuestionService = pollsQuestionService;
	}

	/**
	 * Returns the polls question persistence.
	 *
	 * @return the polls question persistence
	 */
	public PollsQuestionPersistence getPollsQuestionPersistence() {
		return pollsQuestionPersistence;
	}

	/**
	 * Sets the polls question persistence.
	 *
	 * @param pollsQuestionPersistence the polls question persistence
	 */
	public void setPollsQuestionPersistence(
		PollsQuestionPersistence pollsQuestionPersistence) {
		this.pollsQuestionPersistence = pollsQuestionPersistence;
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
		return PollsVoteService.class.getName();
	}

	protected Class<?> getModelClass() {
		return PollsVote.class;
	}

	protected String getModelClassName() {
		return PollsVote.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = pollsVotePersistence.getDataSource();

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

	@BeanReference(type = com.liferay.polls.service.PollsVoteLocalService.class)
	protected com.liferay.polls.service.PollsVoteLocalService pollsVoteLocalService;
	@BeanReference(type = com.liferay.polls.service.PollsVoteService.class)
	protected PollsVoteService pollsVoteService;
	@BeanReference(type = PollsVotePersistence.class)
	protected PollsVotePersistence pollsVotePersistence;
	@ServiceReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@BeanReference(type = com.liferay.polls.service.PollsChoiceLocalService.class)
	protected com.liferay.polls.service.PollsChoiceLocalService pollsChoiceLocalService;
	@BeanReference(type = com.liferay.polls.service.PollsChoiceService.class)
	protected com.liferay.polls.service.PollsChoiceService pollsChoiceService;
	@BeanReference(type = PollsChoicePersistence.class)
	protected PollsChoicePersistence pollsChoicePersistence;
	@BeanReference(type = com.liferay.polls.service.PollsQuestionLocalService.class)
	protected com.liferay.polls.service.PollsQuestionLocalService pollsQuestionLocalService;
	@BeanReference(type = com.liferay.polls.service.PollsQuestionService.class)
	protected com.liferay.polls.service.PollsQuestionService pollsQuestionService;
	@BeanReference(type = PollsQuestionPersistence.class)
	protected PollsQuestionPersistence pollsQuestionPersistence;
	@ServiceReference(type = com.liferay.portal.service.UserLocalService.class)
	protected com.liferay.portal.service.UserLocalService userLocalService;
	@ServiceReference(type = com.liferay.portal.service.UserService.class)
	protected com.liferay.portal.service.UserService userService;
	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
}