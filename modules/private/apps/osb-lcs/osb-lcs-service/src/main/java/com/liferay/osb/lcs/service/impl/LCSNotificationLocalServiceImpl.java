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

package com.liferay.osb.lcs.service.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.lcs.model.LCSClusterEntry;
import com.liferay.osb.lcs.model.LCSClusterNode;
import com.liferay.osb.lcs.model.LCSNotification;
import com.liferay.osb.lcs.model.LCSProject;
import com.liferay.osb.lcs.model.impl.LCSNotificationImpl;
import com.liferay.osb.lcs.service.base.LCSNotificationLocalServiceBaseImpl;
import com.liferay.portal.kernel.model.ClassName;

import java.util.Date;
import java.util.List;

/**
 * Provides the local service for accessing, adding, deleting, and updating LCS
 * notifications.
 *
 * @author  Igor Beslic
 * @version LCS 1.7.1
 * @since   LCS 1.1
 */
@ProviderType
public class LCSNotificationLocalServiceImpl
	extends LCSNotificationLocalServiceBaseImpl {

	/**
	 * Adds an LCS notification for the LCS cluster entry.
	 *
	 * @param  userId the primary key of the user
	 * @param  lcsClusterEntry the notification's LCS cluster entry
	 * @param  enabled whether to enable the notification
	 * @param  type the notification type as defined in {@link
	 *         com.liferay.osb.lcs.notifications.util.LCSEventType}
	 * @return the LCS notification
	 */
	@Override
	public LCSNotification addLCSNotification(
		long userId, LCSClusterEntry lcsClusterEntry, boolean enabled,
		int type) {

		return addLCSNotification(
			userId, getClassNameId(LCSClusterEntry.class.getName()),
			lcsClusterEntry.getLcsClusterEntryId(), enabled, type);
	}

	/**
	 * Adds an LCS notification for the LCS cluster node.
	 *
	 * @param  userId the primary key of the user
	 * @param  lcsClusterNode the notification's LCS cluster node
	 * @param  enabled whether to enable the notification
	 * @param  type the notification type as defined in {@link
	 *         com.liferay.osb.lcs.notifications.util.LCSEventType}
	 * @return the LCS notification
	 */
	@Override
	public LCSNotification addLCSNotification(
		long userId, LCSClusterNode lcsClusterNode, boolean enabled, int type) {

		return addLCSNotification(
			userId, getClassNameId(LCSClusterNode.class.getName()),
			lcsClusterNode.getLcsClusterNodeId(), enabled, type);
	}

	/**
	 * Adds an LCS notification for the LCS project.
	 *
	 * @param  userId the primary key of the user
	 * @param  lcsProjectId the primary key of the notification's LCS project
	 * @param  enabled whether to enable the notification
	 * @param  type the notification type as defined in {@link
	 *         com.liferay.osb.lcs.notifications.util.LCSEventType}
	 * @return the LCS notification
	 */
	@Override
	public LCSNotification addLCSProjectLCSNotification(
		long userId, long lcsProjectId, boolean enabled, int type) {

		ClassName className = classNameLocalService.getClassName(
			LCSProject.class.getName());

		return addLCSNotification(
			userId, className.getClassNameId(), lcsProjectId, enabled, type);
	}

	/**
	 * Deletes the LCS notification for the LCS cluster entry.
	 *
	 * @param lcsClusterEntryId the primary key of the LCS cluster entry
	 */
	@Override
	public void deleteLCSClusterEntryLCSNotification(long lcsClusterEntryId) {
		deleteClassNameLCSNotification(
			getClassNameId(LCSClusterEntry.class.getName()), lcsClusterEntryId);
	}

	/**
	 * Deletes the LCS notification for the LCS cluster node.
	 *
	 * @param lcsClusterNodeId the primary key of the LCS cluster node
	 */
	@Override
	public void deleteLCSClusterNodeLCSNotification(long lcsClusterNodeId) {
		deleteClassNameLCSNotification(
			getClassNameId(LCSClusterNode.class.getName()), lcsClusterNodeId);
	}

	/**
	 * Deletes the LCS notification for the LCS project.
	 *
	 * @param lcsProjectId the primary key of the LCS project
	 */
	@Override
	public void deleteLCSProjectLCSNotification(long lcsProjectId) {
		deleteClassNameLCSNotification(
			getClassNameId(LCSProject.class.getName()), lcsProjectId);
	}

	/**
	 * Deletes the LCS notifications of the user.
	 *
	 * @param userId the primary key of the user
	 */
	@Override
	public void deleteUserLCSNotifications(long userId) {
		List<LCSNotification> lcsNotifications =
			lcsNotificationPersistence.findByUserId(userId);

		for (LCSNotification lcsNotification : lcsNotifications) {
			lcsNotificationPersistence.remove(lcsNotification);
		}
	}

	/**
	 * Deletes the LCS notifications of the user, for the name and primary key
	 * of the class.
	 *
	 * @param userId the primary key of the user
	 * @param classNameValue the class name
	 * @param classPK the primary key for the object of class
	 */
	@Override
	public void deleteUserLCSNotifications(
		long userId, String classNameValue, long classPK) {

		List<LCSNotification> lcsNotifications =
			lcsNotificationPersistence.findByU_C_C(
				userId, getClassNameId(classNameValue), classPK);

		for (LCSNotification lcsNotification : lcsNotifications) {
			lcsNotificationPersistence.remove(lcsNotification);
		}
	}

	/**
	 * Returns the LCS notification of the user, LCS cluster entry, and LCS
	 * notification type.
	 *
	 * @param  userId the primary key of the user
	 * @param  lcsClusterEntryId the primary key of the notification's LCS
	 *         cluster entry
	 * @param  type the notification type as defined in {@link
	 *         com.liferay.osb.lcs.notifications.util.LCSEventType}
	 * @return the matching LCS notification
	 */
	@Override
	public LCSNotification fetchLCSClusterEntryLCSNotification(
		long userId, long lcsClusterEntryId, int type) {

		return lcsNotificationPersistence.fetchByU_C_C_T(
			userId, getClassNameId(LCSClusterEntry.class.getName()),
			lcsClusterEntryId, type);
	}

	/**
	 * Returns the LCS notification of the user, LCS cluster node, and LCS
	 * notification type.
	 *
	 * @param  userId the primary key of the user
	 * @param  lcsClusterNodeId the primary key of the notification's LCS
	 *         cluster node
	 * @param  type the notification type as defined in {@link
	 *         com.liferay.osb.lcs.notifications.util.LCSEventType}
	 * @return the matching LCS notification
	 */
	@Override
	public LCSNotification fetchLCSClusterNodeLCSNotification(
		long userId, long lcsClusterNodeId, int type) {

		return lcsNotificationPersistence.fetchByU_C_C_T(
			userId, getClassNameId(LCSClusterNode.class.getName()),
			lcsClusterNodeId, type);
	}

	/**
	 * Returns the LCS notification for the user, LCS project, and LCS
	 * notification type.
	 *
	 * @param  userId the primary key of the user
	 * @param  lcsProjectId the primary key of the notification's LCS project
	 * @param  type the notification type as defined in {@link
	 *         com.liferay.osb.lcs.notifications.util.LCSEventType}
	 * @return the matching LCS notification
	 */
	@Override
	public LCSNotification fetchLCSProjectLCSNotification(
		long userId, long lcsProjectId, int type) {

		return lcsNotificationPersistence.fetchByU_C_C_T(
			userId, getClassNameId(LCSProject.class.getName()), lcsProjectId,
			type);
	}

	/**
	 * Adds an LCS notification for the user, class, object, and LCS
	 * notification type.
	 *
	 * @param  userId the primary key of the user
	 * @param  classNameId the primary key of the class
	 * @param  classPK the primary key for the object of the class
	 * @param  enabled whether to enable the notification
	 * @param  type the type of notification as defined in {@link
	 *         com.liferay.osb.lcs.notifications.util.LCSEventType}
	 * @return the LCS notification
	 */
	protected LCSNotification addLCSNotification(
		long userId, long classNameId, long classPK, boolean enabled,
		int type) {

		LCSNotification lcsNotification = new LCSNotificationImpl();

		lcsNotification.setLcsNotificationId(
			counterLocalService.increment(LCSNotification.class.getName()));

		lcsNotification.setUserId(userId);
		lcsNotification.setCreateDate(new Date());
		lcsNotification.setModifiedDate(new Date());
		lcsNotification.setClassNameId(classNameId);
		lcsNotification.setClassPK(classPK);
		lcsNotification.setEnabled(enabled);
		lcsNotification.setType(type);

		return lcsNotificationPersistence.update(lcsNotification);
	}

	/**
	 * Deletes the LCS notification of the class.
	 *
	 * @param classNameId the primary key of the class name
	 * @param classPK the primary key of the class object
	 */
	protected void deleteClassNameLCSNotification(
		long classNameId, long classPK) {

		List<LCSNotification> lcsNotifications =
			lcsNotificationPersistence.findByC_C(classNameId, classPK);

		for (LCSNotification lcsNotification : lcsNotifications) {
			lcsNotificationPersistence.remove(lcsNotification);
		}
	}

	/**
	 * Returns the primary key of the class.
	 *
	 * @param  classNameValue the name of the class
	 * @return the primary key
	 */
	protected long getClassNameId(String classNameValue) {
		ClassName className = classNameLocalService.fetchClassName(
			classNameValue);

		return className.getClassNameId();
	}

}