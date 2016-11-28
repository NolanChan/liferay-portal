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

import com.liferay.osb.lcs.advisor.CommandMessageAdvisor;
import com.liferay.osb.lcs.model.LCSClusterEntryToken;
import com.liferay.osb.lcs.model.LCSClusterNode;
import com.liferay.osb.lcs.model.impl.LCSClusterEntryTokenImpl;
import com.liferay.osb.lcs.service.base.LCSClusterEntryTokenLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.Date;
import java.util.List;

/**
 * @author Igor Beslic
 * @see    LCSClusterEntryTokenLocalServiceBaseImpl
 * @see    com.liferay.osb.lcs.service.LCSClusterEntryTokenLocalServiceUtil
 */
@ProviderType
public class LCSClusterEntryTokenLocalServiceImpl
	extends LCSClusterEntryTokenLocalServiceBaseImpl {

	@Override
	public LCSClusterEntryToken addLCSClusterEntryToken(
		long userId, long lcsClusterEntryId, String content) {

		LCSClusterEntryToken lcsClusterEntryToken =
			new LCSClusterEntryTokenImpl();

		lcsClusterEntryToken.setLcsClusterEntryTokenId(
			counterLocalService.increment(
				LCSClusterEntryToken.class.getName()));

		lcsClusterEntryToken.setUserId(userId);
		lcsClusterEntryToken.setCreateDate(new Date());
		lcsClusterEntryToken.setLcsClusterEntryId(lcsClusterEntryId);
		lcsClusterEntryToken.setContent(content);

		return lcsClusterEntryTokenPersistence.update(lcsClusterEntryToken);
	}

	@Override
	public LCSClusterEntryToken deleteLCSClusterEntryToken(
			long lcsClusterEntryTokenId)
		throws PortalException {

		LCSClusterEntryToken lcsClusterEntryToken =
			lcsClusterEntryTokenPersistence.fetchByPrimaryKey(
				lcsClusterEntryTokenId);

		List<LCSClusterNode> lcsClusterNodes =
			lcsClusterNodeLocalService.getLCSClusterEntryLCSClusterNodes(
				lcsClusterEntryToken.getLcsClusterEntryId(), true);

		for (LCSClusterNode lcsClusterNode : lcsClusterNodes) {
			if (lcsClusterNode.isOffline()) {
				continue;
			}

			_commandMessageAdvisor.invalidateLCSClusterEntryToken(
				lcsClusterNode.getKey());
		}

		return lcsClusterEntryTokenPersistence.remove(lcsClusterEntryTokenId);
	}

	@Override
	public LCSClusterEntryToken fetchLCSClusterEntryLCSClusterEntryToken(
		long lcsClusterEntryId) {

		return lcsClusterEntryTokenPersistence.fetchByLCSClusterEntryId(
			lcsClusterEntryId);
	}

	@Override
	public LCSClusterEntryToken fetchLCSClusterEntryToken(
		long lcsClusterEntryTokenId) {

		return lcsClusterEntryTokenPersistence.fetchByPrimaryKey(
			lcsClusterEntryTokenId);
	}

	@ServiceReference(type = CommandMessageAdvisor.class)
	private CommandMessageAdvisor _commandMessageAdvisor;

}