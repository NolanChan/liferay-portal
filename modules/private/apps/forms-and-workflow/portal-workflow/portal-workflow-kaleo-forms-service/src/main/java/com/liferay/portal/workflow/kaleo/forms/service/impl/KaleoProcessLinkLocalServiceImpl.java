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

package com.liferay.portal.workflow.kaleo.forms.service.impl;

import com.liferay.dynamic.data.mapping.service.DDMTemplateLinkLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.spring.extender.service.ServiceReference;
import com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink;
import com.liferay.portal.workflow.kaleo.forms.service.base.KaleoProcessLinkLocalServiceBaseImpl;

import java.util.List;

/**
 * Provides the local service for accessing, adding, deleting, and updating
 * kaleo process links.
 *
 * @author Marcellus Tavares
 */
public class KaleoProcessLinkLocalServiceImpl
	extends KaleoProcessLinkLocalServiceBaseImpl {

	/**
	 * Adds the kaleo process link referencing the kaleo process.
	 *
	 * @param  kaleoProcessId the primary key of the kaleo process
	 * @param  workflowTaskName the name of the kaleo process link's workflow
	 *         task
	 * @param  ddmTemplateId the primary key of the kaleo process link's DDM
	 *         template
	 * @return the kaleo process link that was added
	 */
	public KaleoProcessLink addKaleoProcessLink(
		long kaleoProcessId, String workflowTaskName, long ddmTemplateId) {

		long kaleoProcessLinkId = counterLocalService.increment();

		KaleoProcessLink kaleoProcessLink = kaleoProcessLinkPersistence.create(
			kaleoProcessLinkId);

		kaleoProcessLink.setKaleoProcessId(kaleoProcessId);
		kaleoProcessLink.setWorkflowTaskName(workflowTaskName);
		kaleoProcessLink.setDDMTemplateId(ddmTemplateId);

		kaleoProcessLinkPersistence.update(kaleoProcessLink);

		_ddmTemplateLinkLocalService.addTemplateLink(
			classNameLocalService.getClassNameId(KaleoProcessLink.class),
			kaleoProcessLinkId, ddmTemplateId);

		return kaleoProcessLink;
	}

	/**
	 * Deletes the kaleo process links associated with the kaleo process, and their resources.
	 *
	 * @param kaleoProcessId the primary key of the kaleo process from which to
	 *        delete kaleo process links
	 */
	public void deleteKaleoProcessLinks(long kaleoProcessId) {
		List<KaleoProcessLink> kaleoProcessLinks =
			kaleoProcessLinkPersistence.findByKaleoProcessId(kaleoProcessId);

		for (KaleoProcessLink kaleoProcessLink : kaleoProcessLinks) {
			deleteKaleoProcessLink(kaleoProcessLink);

			_ddmTemplateLinkLocalService.deleteTemplateLink(
				classNameLocalService.getClassNameId(KaleoProcessLink.class),
				kaleoProcessLink.getKaleoProcessLinkId());
		}
	}

	/**
	 * Returns the kaleo process link matching the kaleo process and workflow
	 * task name.
	 *
	 * @param  kaleoProcessId the primary key of the kaleo process link's kaleo
	 *         process
	 * @param  workflowTaskName the name of the kaleo process link's workflow task
	 * @return the kaleo process link matching the kaleo process and workflow
	 *         task name, or <code>null</code> if a matching  kaleo process link
	 *         could not be found
	 */
	public KaleoProcessLink fetchKaleoProcessLink(
		long kaleoProcessId, String workflowTaskName) {

		return kaleoProcessLinkPersistence.fetchByKPI_WTN(
			kaleoProcessId, workflowTaskName);
	}

	/**
	 * Returns the kaleo process links matching the kaleo process.
	 *
	 * @param  kaleoProcessId the primary key of the kaleo process link's kaleo
	 *         process
	 * @return the kaleo process links matching the kaleo process, or
	 *         <code>null</code> if a matching kaleo process link could not be
	 *         found
	 */
	public List<KaleoProcessLink> getKaleoProcessLinks(long kaleoProcessId) {
		return kaleoProcessLinkPersistence.findByKaleoProcessId(kaleoProcessId);
	}

	/**
	 * Updates the kaleo process link, setting the primary key of the associated kaleo process.
	 *
	 * @param  kaleoProcessLinkId the primary key of the kaleo process link
	 * @param  kaleoProcessId the primary key of the kaleo process
	 * @return the kaleo process link
	 * @throws PortalException if a portal exception occurred
	 */
	public KaleoProcessLink updateKaleoProcessLink(
			long kaleoProcessLinkId, long kaleoProcessId)
		throws PortalException {

		KaleoProcessLink kaleoProcessLink =
			kaleoProcessLinkPersistence.findByPrimaryKey(kaleoProcessLinkId);

		kaleoProcessLink.setKaleoProcessId(kaleoProcessId);

		kaleoProcessLinkPersistence.update(kaleoProcessLink);

		return kaleoProcessLink;
	}

	/**
	 * Updates the kaleo process link, replacing its values with new ones. New values are set for the primary key of the associated kaleo process, the name of the associated workflow task, and the primary key of the associated DDM Template.
	 *
	 * @param  kaleoProcessLinkId the primary key of the kaleo process link
	 * @param  kaleoProcessId the primary key of the kaleo process
	 * @param  workflowTaskName the name of the kaleo process link's workflow
	 *         task
	 * @param  ddmTemplateId the primary key of the kaleo process link's DDM
	 *         template
	 * @return the kaleo process link
	 * @throws PortalException if a portal exception occurred
	 */
	public KaleoProcessLink updateKaleoProcessLink(
			long kaleoProcessLinkId, long kaleoProcessId,
			String workflowTaskName, long ddmTemplateId)
		throws PortalException {

		KaleoProcessLink kaleoProcessLink =
			kaleoProcessLinkPersistence.findByPrimaryKey(kaleoProcessLinkId);

		kaleoProcessLink.setKaleoProcessId(kaleoProcessId);
		kaleoProcessLink.setWorkflowTaskName(workflowTaskName);
		kaleoProcessLink.setDDMTemplateId(ddmTemplateId);

		kaleoProcessLinkPersistence.update(kaleoProcessLink);

		_ddmTemplateLinkLocalService.updateTemplateLink(
			classNameLocalService.getClassNameId(KaleoProcessLink.class),
			kaleoProcessLink.getKaleoProcessLinkId(), ddmTemplateId);

		return kaleoProcessLink;
	}

	/**
	 * Creates or updates the kaleo process link. If no kaleo process link is found matching the primary key of the kaleo process and the workflow task name, a new link is created.
	 *
	 * @param  kaleoProcessId the primary key of the kaleo process
	 * @param  workflowTaskName the name of the kaleo process link's workflow
	 *         task
	 * @param  ddmTemplateId the primary key of the kaleo process link's DDM
	 *         template
	 * @return the kaleo process link
	 */
	public KaleoProcessLink updateKaleoProcessLink(
		long kaleoProcessId, String workflowTaskName, long ddmTemplateId) {

		KaleoProcessLink kaleoProcessLink =
			kaleoProcessLinkPersistence.fetchByKPI_WTN(
				kaleoProcessId, workflowTaskName);

		if (kaleoProcessLink == null) {
			return addKaleoProcessLink(
				kaleoProcessId, workflowTaskName, ddmTemplateId);
		}

		kaleoProcessLink.setDDMTemplateId(ddmTemplateId);

		kaleoProcessLinkPersistence.update(kaleoProcessLink);

		_ddmTemplateLinkLocalService.updateTemplateLink(
			classNameLocalService.getClassNameId(KaleoProcessLink.class),
			kaleoProcessLink.getKaleoProcessLinkId(), ddmTemplateId);

		return kaleoProcessLink;
	}

	@ServiceReference(type = DDMTemplateLinkLocalService.class)
	private DDMTemplateLinkLocalService _ddmTemplateLinkLocalService;

}