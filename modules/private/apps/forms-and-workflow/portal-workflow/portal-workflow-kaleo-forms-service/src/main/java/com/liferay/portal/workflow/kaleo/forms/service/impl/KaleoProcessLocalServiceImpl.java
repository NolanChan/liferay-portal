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

import com.liferay.dynamic.data.lists.model.DDLRecord;
import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.dynamic.data.lists.model.DDLRecordSetConstants;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLinkLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.spring.extender.service.ServiceReference;
import com.liferay.portal.workflow.kaleo.forms.exception.KaleoProcessDDMTemplateIdException;
import com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess;
import com.liferay.portal.workflow.kaleo.forms.model.KaleoTaskFormPair;
import com.liferay.portal.workflow.kaleo.forms.model.KaleoTaskFormPairs;
import com.liferay.portal.workflow.kaleo.forms.service.base.KaleoProcessLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Provides the local service for accessing, adding, deleting, and updating
 * kaleo processes.
 *
 * @author Marcellus Tavares
 */
public class KaleoProcessLocalServiceImpl
	extends KaleoProcessLocalServiceBaseImpl {

	/**
	 * Adds a kaleo process.
	 *
	 * @param  userId the primary key of the kaleo process's creator/owner
	 * @param  groupId the primary key of the kaleo process's group
	 * @param  ddmStructureId the primary key of the kaleo process's DDM
	 *         structure
	 * @param  nameMap the kaleo process's locales and localized names
	 * @param  descriptionMap the kaleo process's locales and localized
	 *         descriptions
	 * @param  ddmTemplateId the primary key of the kaleo process's DDM template
	 * @param  workflowDefinitionName the name of kaleo process's workflow
	 *         definition
	 * @param  workflowDefinitionVersion the version of kaleo process's workflow
	 *         definition
	 * @param  kaleoTaskFormPairs the kaleo task form pairs used to create a kaleo process link. See {@link
	 *         KaleoTaskFormPairs}
	 * @param  serviceContext the service context to be applied. This can set
	 *         guest permissions and group permissions for the kaleo process.
	 * @return the kaleo process
	 * @throws PortalException if a portal exception occurred
	 */
	public KaleoProcess addKaleoProcess(
			long userId, long groupId, long ddmStructureId,
			Map<Locale, String> nameMap, Map<Locale, String> descriptionMap,
			long ddmTemplateId, String workflowDefinitionName,
			int workflowDefinitionVersion,
			KaleoTaskFormPairs kaleoTaskFormPairs,
			ServiceContext serviceContext)
		throws PortalException {

		// Kaleo process

		User user = userLocalService.getUser(userId);
		Date now = new Date();

		validate(ddmTemplateId);

		long kaleoProcessId = counterLocalService.increment();

		KaleoProcess kaleoProcess = kaleoProcessPersistence.create(
			kaleoProcessId);

		kaleoProcess.setGroupId(groupId);
		kaleoProcess.setCompanyId(user.getCompanyId());
		kaleoProcess.setUserId(user.getUserId());
		kaleoProcess.setUserName(user.getFullName());
		kaleoProcess.setCreateDate(serviceContext.getCreateDate(now));
		kaleoProcess.setModifiedDate(serviceContext.getModifiedDate(now));

		DDLRecordSet ddlRecordSet = addDDLRecordSet(
			userId, groupId, ddmStructureId, nameMap, descriptionMap,
			serviceContext);

		kaleoProcess.setDDLRecordSetId(ddlRecordSet.getRecordSetId());

		kaleoProcess.setDDMTemplateId(ddmTemplateId);
		kaleoProcess.setWorkflowDefinitionName(workflowDefinitionName);
		kaleoProcess.setWorkflowDefinitionVersion(workflowDefinitionVersion);

		kaleoProcessPersistence.update(kaleoProcess);

		// Resources

		resourceLocalService.addModelResources(kaleoProcess, serviceContext);

		// Kaleo process links

		updateKaleoProcessLinks(kaleoProcessId, kaleoTaskFormPairs);

		// Dynamic data mapping template link

		_ddmTemplateLinkLocalService.addTemplateLink(
			classNameLocalService.getClassNameId(KaleoProcess.class),
			kaleoProcessId, ddmTemplateId);

		return kaleoProcess;
	}

	/**
	 * Deletes the kaleo process and its resources.
	 *
	 * @param  kaleoProcess the kaleo process to be deleted
	 * @return the deleted kaleo process
	 * @throws PortalException if a portal exception occurred
	 */
	@Override
	public KaleoProcess deleteKaleoProcess(KaleoProcess kaleoProcess)
		throws PortalException {

		// Kaleo process

		kaleoProcessPersistence.remove(kaleoProcess);

		// Kaleo process links

		kaleoProcessLinkLocalService.deleteKaleoProcessLinks(
			kaleoProcess.getPrimaryKey());

		// Kaleo process data

		deleteKaleoProcessData(kaleoProcess);

		// Dynamic data mapping template link

		_ddmTemplateLinkLocalService.deleteTemplateLink(
			classNameLocalService.getClassNameId(KaleoProcess.class),
			kaleoProcess.getKaleoProcessId());

		// Dynamic data lists record set

		ddlRecordSetLocalService.deleteRecordSet(
			kaleoProcess.getDDLRecordSetId());

		return kaleoProcess;
	}

	/**
	 * Deletes the kaleo process and its resources.
	 *
	 * @param  kaleoProcessId the primary key of the kaleo process to be deleted
	 * @return the deleted kaleo process
	 * @throws PortalException if a portal exception occurred
	 */
	@Override
	public KaleoProcess deleteKaleoProcess(long kaleoProcessId)
		throws PortalException {

		KaleoProcess kaleoProcess = kaleoProcessPersistence.findByPrimaryKey(
			kaleoProcessId);

		return deleteKaleoProcess(kaleoProcess);
	}

	/**
	 * Returns the kaleo process matching the record set ID.
	 *
	 * @param  ddlRecordSetId the primary key of the record set
	 * @return the matching kaleo process
	 * @throws PortalException if a matching kaleo process could not be found
	 */
	public KaleoProcess getDDLRecordSetKaleoProcess(long ddlRecordSetId)
		throws PortalException {

		return kaleoProcessPersistence.findByDDLRecordSetId(ddlRecordSetId);
	}

	/**
	 * Returns the kaleo process with the primary key.
	 *
	 * @param  kaleoProcessId the primary key of the kaleo process
	 * @return the kaleo process
	 * @throws PortalException if a kaleo process with the primary key could not
	 *         be found
	 */
	@Override
	public KaleoProcess getKaleoProcess(long kaleoProcessId)
		throws PortalException {

		return kaleoProcessPersistence.findByPrimaryKey(kaleoProcessId);
	}

	/**
	 * Returns all the kaleo processes belonging to the group.
	 *
	 * @param  groupId the primary key of the kaleo processes's group
	 * @return the kaleo processes
	 */
	public List<KaleoProcess> getKaleoProcesses(long groupId) {
		return kaleoProcessPersistence.findByGroupId(groupId);
	}

	/**
	 * Returns an ordered range of all kaleo processes belonging to the group.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end -
	 * start</code> instances. <code>start</code> and <code>end</code> are not
	 * primary keys, they are indexes in the result set. Thus, <code>0</code>
	 * refers to the first result in the set. Setting both <code>start</code>
	 * and <code>end</code> to {@link QueryUtil#ALL_POS} will return the
	 * full result set.
	 * </p>
	 *
	 * @param  groupId the primary key of the kaleo processes' group
	 * @param  start the lower bound of the range of kaleo processes to return
	 * @param  end the upper bound of the range of kaleo processes to return
	 *         (not inclusive)
	 * @param  orderByComparator the comparator to order the kaleo processes
	 * @return the range of matching kaleo processes ordered by the comparator
	 */
	public List<KaleoProcess> getKaleoProcesses(
		long groupId, int start, int end, OrderByComparator orderByComparator) {

		return kaleoProcessPersistence.findByGroupId(
			groupId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of kaleo processes matching the parameters.
	 *
	 * @param  groupId the primary key of the kaleo processes' group.
	 * @return the number of matching kaleo processes
	 */
	public int getKaleoProcessesCount(long groupId) {
		return kaleoProcessPersistence.countByGroupId(groupId);
	}

	/**
	 * Updates the kaleo process.
	 *
	 * @param  kaleoProcessId the primary key of the kaleo process
	 * @param  ddmStructureId the primary key of the kaleo process's DDM
	 *         structure
	 * @param  nameMap the kaleo process's locales and localized names
	 * @param  descriptionMap the kaleo process's locales and localized
	 *         descriptions
	 * @param  ddmTemplateId the primary key of the kaleo process's DDM template
	 * @param  workflowDefinitionName the name of kaleo process's workflow
	 *         definition
	 * @param  workflowDefinitionVersion the version of kaleo process's workflow
	 *         definition
	 * @param  kaleoTaskFormPairs the kaleo task form pairs. For more information see the <code>com.liferay.portal.workflow.kaleo.forms.api</code> module's <code>KaleoTaskFormPairs</code> class.
	 * @param  serviceContext the service context to be applied. This can set
	 *         guest permissions and group permissions for the kaleo process.
	 * @return the kaleo process
	 * @throws PortalException if a portal exception occurred
	 */
	public KaleoProcess updateKaleoProcess(
			long kaleoProcessId, long ddmStructureId,
			Map<Locale, String> nameMap, Map<Locale, String> descriptionMap,
			long ddmTemplateId, String workflowDefinitionName,
			int workflowDefinitionVersion,
			KaleoTaskFormPairs kaleoTaskFormPairs,
			ServiceContext serviceContext)
		throws PortalException {

		// Kaleo process

		KaleoProcess kaleoProcess = kaleoProcessPersistence.findByPrimaryKey(
			kaleoProcessId);

		boolean kaleoProcessDataStale = isKaleoProcessDataStale(
			kaleoProcess, ddmStructureId,
			workflowDefinitionName + "@" + workflowDefinitionVersion);

		validate(ddmTemplateId);

		kaleoProcess.setModifiedDate(serviceContext.getModifiedDate(null));
		kaleoProcess.setDDMTemplateId(ddmTemplateId);
		kaleoProcess.setWorkflowDefinitionName(workflowDefinitionName);
		kaleoProcess.setWorkflowDefinitionVersion(workflowDefinitionVersion);

		kaleoProcessPersistence.update(kaleoProcess);

		// Kaleo process links

		kaleoProcessLinkLocalService.deleteKaleoProcessLinks(kaleoProcessId);

		updateKaleoProcessLinks(kaleoProcessId, kaleoTaskFormPairs);

		// Kaleo process data

		if (kaleoProcessDataStale) {
			deleteKaleoProcessData(kaleoProcess);
		}

		// Dynamic data mapping template link

		_ddmTemplateLinkLocalService.updateTemplateLink(
			classNameLocalService.getClassNameId(KaleoProcess.class),
			kaleoProcessId, ddmTemplateId);

		// Dynamic data lists record set

		updateDDLRecordSet(
			kaleoProcess.getDDLRecordSetId(), ddmStructureId, nameMap,
			descriptionMap, serviceContext);

		return kaleoProcess;
	}

	/**
	 * Adds a DDL record set referencing the DDM structure.
	 *
	 * @param  userId the primary key of the record set's creator/owner
	 * @param  groupId the primary key of the record set's group
	 * @param  ddmStructureId the primary key of the record set's DDM structure
	 * @param  nameMap the record set's locales and localized names
	 * @param  descriptionMap the record set's locales and localized
	 *         descriptions
	 * @param  serviceContext the service context to be applied
	 * @return the record set
	 * @throws PortalException if a portal exception occurred
	 */
	protected DDLRecordSet addDDLRecordSet(
			long userId, long groupId, long ddmStructureId,
			Map<Locale, String> nameMap, Map<Locale, String> descriptionMap,
			ServiceContext serviceContext)
		throws PortalException {

		int scope = GetterUtil.getInteger(serviceContext.getAttribute("scope"));

		return ddlRecordSetLocalService.addRecordSet(
			userId, groupId, ddmStructureId, null, nameMap, descriptionMap,
			DDLRecordSetConstants.MIN_DISPLAY_ROWS_DEFAULT, scope,
			serviceContext);
	}

	/**
	 * Deletes the kaleo process's resources.
	 *
	 * @param  kaleoProcess the kaleo process
	 * @throws PortalException if a portal exception occurred
	 */
	protected void deleteKaleoProcessData(KaleoProcess kaleoProcess)
		throws PortalException {

		workflowDefinitionLinkLocalService.deleteWorkflowDefinitionLink(
			kaleoProcess.getCompanyId(), kaleoProcess.getGroupId(),
			KaleoProcess.class.getName(), kaleoProcess.getKaleoProcessId(), 0);

		List<DDLRecord> ddlRecords = ddlRecordLocalService.getRecords(
			kaleoProcess.getDDLRecordSetId());

		for (DDLRecord ddlRecord : ddlRecords) {
			workflowInstanceLinkLocalService.deleteWorkflowInstanceLinks(
				kaleoProcess.getCompanyId(), kaleoProcess.getGroupId(),
				KaleoProcess.class.getName(), ddlRecord.getRecordId());

			ddlRecordLocalService.deleteRecord(ddlRecord.getRecordId());
		}
	}

	/**
	 * Determines if kaleo process data is stale.
	 *
	 * @param  kaleoProcess the kaleo process
	 * @param  newDDMStructureId the new primary key of the kaleo process's DDM
	 *         structure
	 * @param  newWorkflowDefinition the new workflow definition of the kaleo
	 *         process
	 * @return <code>true</code> if the kaleo process data is stale;
	 *         <code>false</code> otherwise.
	 * @throws PortalException if a portal exception occurred
	 */
	protected boolean isKaleoProcessDataStale(
			KaleoProcess kaleoProcess, long newDDMStructureId,
			String newWorkflowDefinition)
		throws PortalException {

		DDLRecordSet ddlRecordSet = kaleoProcess.getDDLRecordSet();

		if ((newDDMStructureId != ddlRecordSet.getDDMStructureId()) ||
			!newWorkflowDefinition.equals(
				kaleoProcess.getWorkflowDefinition())) {

			return true;
		}

		return false;
	}

	/**
	 * Updates the matching DDL record set's name and description using its primary key.
	 *
	 * @param  ddlRecordSetId the primary key of the record set
	 * @param  ddmStructureId the primary key of the record set's DDM structure
	 * @param  nameMap the record set's locales and localized names
	 * @param  descriptionMap the record set's locales and localized
	 *         descriptions
	 * @param  serviceContext the service context to be applied. This can set
	 *         the record set modified date.
	 * @throws PortalException if a portal exception occurred
	 */
	protected void updateDDLRecordSet(
			long ddlRecordSetId, long ddmStructureId,
			Map<Locale, String> nameMap, Map<Locale, String> descriptionMap,
			ServiceContext serviceContext)
		throws PortalException {

		ddlRecordSetLocalService.updateRecordSet(
			ddlRecordSetId, ddmStructureId, nameMap, descriptionMap,
			DDLRecordSetConstants.MIN_DISPLAY_ROWS_DEFAULT, serviceContext);
	}

	/**
	 * Adds a kaleo process link referencing the kaleo process ID.
	 *
	 * @param kaleoProcessId the primary key of the kaleo process
	 * @param kaleoTaskFormPairs the kaleo task form pairs. For more information see the <code>com.liferay.portal.workflow.kaleo.forms.api</code> module's <code>KaleoTaskFormPairs</code> class.
	 */
	protected void updateKaleoProcessLinks(
		long kaleoProcessId, KaleoTaskFormPairs kaleoTaskFormPairs) {

		for (KaleoTaskFormPair kaleoTaskFormPair : kaleoTaskFormPairs) {
			if (Validator.isNotNull(kaleoTaskFormPair.getDDMTemplateId())) {
				kaleoProcessLinkLocalService.addKaleoProcessLink(
					kaleoProcessId, kaleoTaskFormPair.getWorkflowTaskName(),
					kaleoTaskFormPair.getDDMTemplateId());
			}
		}
	}

	/**
	 * Validates the the primary key of the DDM template
	 *
	 * @param  ddmTemplateId the primary key of the DDM template to check
	 * @throws PortalException if the primary key of the DDM template is
	 *         <code>null</code>
	 */
	protected void validate(long ddmTemplateId) throws PortalException {
		if (Validator.isNull(ddmTemplateId)) {
			throw new KaleoProcessDDMTemplateIdException();
		}
	}

	@ServiceReference(type = DDMTemplateLinkLocalService.class)
	private DDMTemplateLinkLocalService _ddmTemplateLinkLocalService;

}