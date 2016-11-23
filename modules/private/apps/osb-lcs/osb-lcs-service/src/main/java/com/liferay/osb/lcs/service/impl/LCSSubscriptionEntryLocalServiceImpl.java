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

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.TypeFactory;

import com.liferay.lcs.rest.JSONErrorCode;
import com.liferay.lcs.subscription.SubscriptionType;
import com.liferay.osb.lcs.exception.LCSSubscriptionEntryProductException;
import com.liferay.osb.lcs.exception.LCSSubscriptionEntryTypeException;
import com.liferay.osb.lcs.exception.NoSuchLCSSubscriptionEntryException;
import com.liferay.osb.lcs.json.LCSSubscriptionEntryDeserializer;
import com.liferay.osb.lcs.model.LCSClusterEntry;
import com.liferay.osb.lcs.model.LCSClusterNode;
import com.liferay.osb.lcs.model.LCSProject;
import com.liferay.osb.lcs.model.LCSSubscriptionEntry;
import com.liferay.osb.lcs.model.impl.LCSSubscriptionEntryImpl;
import com.liferay.osb.lcs.osbportlet.service.OSBPortletService;
import com.liferay.osb.lcs.service.base.LCSSubscriptionEntryLocalServiceBaseImpl;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Igor Beslic
 * @see LCSSubscriptionEntryLocalServiceBaseImpl
 * @see com.liferay.osb.lcs.service.LCSSubscriptionEntryLocalServiceUtil
 */
@ProviderType
public class LCSSubscriptionEntryLocalServiceImpl
	extends LCSSubscriptionEntryLocalServiceBaseImpl {

	@Override
	public void addLCSSubscriptionEntries(
			long lcsProjectId, String lcsSubscriptionEntriesJSON)
		throws PortalException {

		refreshLCSProjectLCSSubscriptionEntries(
			lcsProjectId, lcsSubscriptionEntriesJSON);
	}

	@Override
	public LCSSubscriptionEntry addLCSSubscriptionEntry(
		LCSSubscriptionEntry lcsSubscriptionEntry) {

		lcsSubscriptionEntry.setLcsSubscriptionEntryId(
			counterLocalService.increment(
				LCSSubscriptionEntry.class.getName()));

		if (isActive(lcsSubscriptionEntry)) {
			lcsSubscriptionEntry.setActive(true);
		}

		return super.addLCSSubscriptionEntry(lcsSubscriptionEntry);
	}

	@Override
	public void decrementServerUsed(
			long lcsProjectId, String licenseType, int processorCoresTotal)
		throws PortalException {

		updateServerUsed(lcsProjectId, licenseType, processorCoresTotal, false);
	}

	@Override
	public void deleteLCSProjectLCSSubscriptionEntries(long lcsProjectId)
		throws PortalException {

		lcsSubscriptionEntryPersistence.removeByLCSProjectId(lcsProjectId);
	}

	@Override
	public LCSSubscriptionEntry fetchLCSProjectLCSSubscriptionEntry(
		long lcsProjectId, int processorCoresTotal,
		SubscriptionType subscriptionType) {

		List<LCSSubscriptionEntry> lcsSubscriptionEntries =
			getLCSProjectLCSSubscriptionEntries(
				lcsProjectId, subscriptionType.name(), null);

		if (lcsSubscriptionEntries.isEmpty()) {
			return null;
		}

		for (LCSSubscriptionEntry lcsSubscriptionEntry :
				lcsSubscriptionEntries) {

			if (processorCoresTotal >
					lcsSubscriptionEntry.getProcessorCoresAllowed()) {

				continue;
			}

			return lcsSubscriptionEntry;
		}

		return null;
	}

	@Override
	public List<LCSSubscriptionEntry> getLCSProjectLCSSubscriptionEntries(
		long lcsProjectId) {

		return getLCSProjectLCSSubscriptionEntries(lcsProjectId, true);
	}

	@Override
	public List<LCSSubscriptionEntry> getLCSProjectLCSSubscriptionEntries(
		long lcsProjectId, boolean active) {

		List<SubscriptionType> notSubscriptionTypes = new ArrayList<>();

		notSubscriptionTypes.add(SubscriptionType.ELASTIC);

		return filterLCSSubscriptionEntries(
			lcsSubscriptionEntryPersistence.findByLPI_A(lcsProjectId, active),
			notSubscriptionTypes);
	}

	@Override
	public List<LCSSubscriptionEntry> getLCSProjectLCSSubscriptionEntries(
		long lcsProjectId, String type, OrderByComparator orderByComparator) {

		return lcsSubscriptionEntryPersistence.findByLPI_T_A(
			lcsProjectId, type, true, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			orderByComparator);
	}

	@Override
	public List<Long> getLCSSubscriptionEntryIds(long lcsProjectId) {
		Class<?> clazz = getClass();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			LCSSubscriptionEntry.class, clazz.getClassLoader());

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("lcsProjectId", lcsProjectId));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("active", true));

		Projection projection = ProjectionFactoryUtil.property(
			"lcsSubscriptionEntryId");

		dynamicQuery.setProjection(projection);

		return lcsSubscriptionEntryPersistence.findWithDynamicQuery(
			dynamicQuery);
	}

	@Override
	public boolean hasLCSProjectElasticLCSSubscriptionEntry(long lcsProjectId) {
		List<LCSSubscriptionEntry> lcsSubscriptionEntries =
			getLCSProjectLCSSubscriptionEntries(
				lcsProjectId, SubscriptionType.ELASTIC.name(), null);

		for (LCSSubscriptionEntry lcsSubscriptionEntry :
				lcsSubscriptionEntries) {

			if (lcsSubscriptionEntry.isActive()) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean hasLCSProjectLCSSubscriptionEntry(long lcsProjectId) {
		int count = lcsSubscriptionEntryPersistence.countByLPI_A(
			lcsProjectId, true);

		if (count > 0) {
			return true;
		}

		return false;
	}

	@Override
	public void incrementServerUsed(
			long lcsProjectId, String licenseType, int processorCoresTotal)
		throws PortalException {

		updateServerUsed(lcsProjectId, licenseType, processorCoresTotal, true);
	}

	@Override
	public void refreshLCSProjectLCSSubscriptionEntries()
		throws PortalException {

		List<LCSProject> lcsProjects = lcsProjectPersistence.findAll();

		for (LCSProject lcsProject : lcsProjects) {
			refreshLCSProjectLCSSubscriptionEntries(
				lcsProject.getLcsProjectId());
		}
	}

	@Override
	public void refreshLCSProjectLCSSubscriptionEntries(long lcsProjectId)
		throws PortalException {

		LCSProject lcsProject = lcsProjectPersistence.findByPrimaryKey(
			lcsProjectId);

		String lcsSubscriptionEntriesJSON =
			_osbPortletService.getCorpProjectLCSSubscriptionEntriesJSON(
				lcsProject.getCorpProjectId());

		refreshLCSProjectLCSSubscriptionEntries(
			lcsProjectId, lcsSubscriptionEntriesJSON);
	}

	@Override
	public void reorganizeLCSSubsriptionsServersUsed(long lcsProjectId) {
		List<LCSClusterEntry> lcsClusterEntries =
			lcsClusterEntryPersistence.findByLCSProjectId(lcsProjectId);

		Map<SubscriptionType, List<LCSClusterEntry>> lcsClusterEntriesMap =
			new HashMap<>();

		for (LCSClusterEntry lcsClusterEntry : lcsClusterEntries) {
			SubscriptionType subscriptionType = SubscriptionType.valueOf(
				lcsClusterEntry.getSubscriptionType());

			if (subscriptionType == SubscriptionType.UNDEFINED) {
				continue;
			}

			List<LCSClusterEntry> subscriptionTypeLCSClusterEntries =
				lcsClusterEntriesMap.get(subscriptionType);

			if (subscriptionTypeLCSClusterEntries == null) {
				subscriptionTypeLCSClusterEntries = new ArrayList<>();

				lcsClusterEntriesMap.put(
					subscriptionType, subscriptionTypeLCSClusterEntries);
			}

			subscriptionTypeLCSClusterEntries.add(lcsClusterEntry);
		}

		for (SubscriptionType subscriptionType :
				lcsClusterEntriesMap.keySet()) {

			List<LCSClusterEntry> subscriptionTypeLCSClusterEntries =
				lcsClusterEntriesMap.get(subscriptionType);

			long[] lcsClusterEntryIds =
				new long[subscriptionTypeLCSClusterEntries.size()];

			for (int i = 0; i < lcsClusterEntryIds.length; i++) {
				LCSClusterEntry lcsClusterEntry =
					subscriptionTypeLCSClusterEntries.get(i);

				lcsClusterEntryIds[i] = lcsClusterEntry.getLcsClusterEntryId();
			}

			List<LCSClusterNode> lcsClusterNodes =
				lcsClusterNodePersistence.findByLCEI_A(
					lcsClusterEntryIds, false);

			if (lcsClusterNodes.isEmpty()) {
				continue;
			}

			OrderByComparator orderByComparator =
				OrderByComparatorFactoryUtil.create(
					LCSSubscriptionEntryImpl.TABLE_NAME,
					"processorCoresAllowed", true);

			List<LCSSubscriptionEntry> lcsSubscriptionEntries =
				getLCSProjectLCSSubscriptionEntries(
					lcsProjectId, subscriptionType.name(), orderByComparator);

			if (lcsSubscriptionEntries.isEmpty()) {
				continue;
			}

			for (LCSSubscriptionEntry lcsSubscriptionEntry :
					lcsSubscriptionEntries) {

				lcsSubscriptionEntry.setServersUsed(0);
			}

			for (LCSClusterNode lcsClusterNode : lcsClusterNodes) {
				if (lcsClusterNode.isOffline()) {
					continue;
				}

				for (LCSSubscriptionEntry lcsSubscriptionEntry :
						lcsSubscriptionEntries) {

					if ((lcsClusterNode.getProcessorCoresTotal() <=
							lcsSubscriptionEntry.getProcessorCoresAllowed()) &&
						(lcsSubscriptionEntry.getServersUsed() <
							lcsSubscriptionEntry.getServersAllowed())) {

						lcsSubscriptionEntry.setServersUsed(
							lcsSubscriptionEntry.getServersUsed() + 1);

						break;
					}
				}
			}

			for (LCSSubscriptionEntry lcsSubscriptionEntry :
					lcsSubscriptionEntries) {

				lcsSubscriptionEntryPersistence.update(lcsSubscriptionEntry);
			}
		}
	}

	@Reference(bind = "-")
	public void setOSBPortletService(OSBPortletService osbPortletService) {
		_osbPortletService = osbPortletService;
	}

	protected List<LCSSubscriptionEntry> doGetToList(String json)
		throws PortalException {

		if ((json == null) || json.equals("") || json.equals("{}") ||
			json.equals("[]")) {

			return Collections.emptyList();
		}

		if (json.contains("exception\":\"")) {
			throw new PortalException("JSON data contains error " + json);
		}

		try {
			ObjectMapper objectMapper = getObjectMapper();

			TypeFactory typeFactory = objectMapper.getTypeFactory();

			JavaType javaType = typeFactory.constructCollectionType(
				List.class, LCSSubscriptionEntryImpl.class);

			return objectMapper.readValue(json, javaType);
		}
		catch (IOException ioe) {
			_log.error("Failed to deserialize", ioe);
		}

		return Collections.emptyList();
	}

	protected List<LCSSubscriptionEntry> filterLCSSubscriptionEntries(
		List<LCSSubscriptionEntry> lcsSubscriptionEntries,
		List<SubscriptionType> subscriptionTypes) {

		List<LCSSubscriptionEntry> filteredLCSSubscriptionEntries =
			new ArrayList<>();

		for (LCSSubscriptionEntry lcsSubscriptionEntry :
				lcsSubscriptionEntries) {

			if (subscriptionTypes.contains(
					SubscriptionType.valueOf(lcsSubscriptionEntry.getType()))) {

				continue;
			}

			filteredLCSSubscriptionEntries.add(lcsSubscriptionEntry);
		}

		return filteredLCSSubscriptionEntries;
	}

	protected LCSSubscriptionEntry getLCSProjectLCSSubscriptionEntry(
			long lcsProjectId, String type, int processorCoresTotal,
			boolean increment)
		throws PortalException {

		Class<?> clazz = getClass();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			LCSSubscriptionEntry.class, clazz.getClassLoader());

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("lcsProjectId", lcsProjectId));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("type", type));
		dynamicQuery.add(
			RestrictionsFactoryUtil.ge(
				"processorCoresAllowed", processorCoresTotal));

		if (increment) {
			dynamicQuery.add(
				RestrictionsFactoryUtil.ltProperty(
					"serversUsed", "serversAllowed"));
		}

		dynamicQuery.add(RestrictionsFactoryUtil.eq("active", Boolean.TRUE));

		if (increment) {
			dynamicQuery.addOrder(
				OrderFactoryUtil.asc("processorCoresAllowed"));
		}
		else {
			dynamicQuery.addOrder(
				OrderFactoryUtil.desc("processorCoresAllowed"));
		}

		List<LCSSubscriptionEntry> lcsSubscriptionEntries =
			lcsSubscriptionEntryPersistence.findWithDynamicQuery(dynamicQuery);

		if (lcsSubscriptionEntries.isEmpty()) {
			StringBundler sb = new StringBundler(11);

			sb.append("{errorCode: ");
			sb.append(
				JSONErrorCode.NO_SUCH_LCS_SUBSCRIPTION_ENTRY.getErrorCode());
			sb.append(", increment: ");
			sb.append(increment);
			sb.append(", lcsProjectId: ");
			sb.append(lcsProjectId);
			sb.append(", processorCoresTotal: ");
			sb.append(processorCoresTotal);
			sb.append(", type: '");
			sb.append(type);
			sb.append("'}");

			throw new NoSuchLCSSubscriptionEntryException(sb.toString());
		}

		return lcsSubscriptionEntries.get(0);
	}

	protected ObjectMapper getObjectMapper() {
		if (_objectMapper != null) {
			return _objectMapper;
		}

		_objectMapper = new ObjectMapper();

		SimpleModule simpleModule = new SimpleModule();

		simpleModule.addDeserializer(
			LCSSubscriptionEntryImpl.class,
			new LCSSubscriptionEntryDeserializer());

		_objectMapper.registerModule(simpleModule);

		return _objectMapper;
	}

	protected boolean isActive(LCSSubscriptionEntry lcsSubscriptionEntry) {
		if (DateUtil.compareTo(new Date(), lcsSubscriptionEntry.getEndDate()) >
				0) {

			return false;
		}

		return true;
	}

	protected void refreshLCSProjectLCSSubscriptionEntries(
			long lcsProjectId, String lcsSubscriptionEntriesJSON)
		throws PortalException {

		if (Validator.isNull(lcsSubscriptionEntriesJSON)) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"No subscription entries for LCS project " + lcsProjectId);
			}

			return;
		}

		List<LCSSubscriptionEntry> lcsSubscriptionEntries =
			getLCSProjectLCSSubscriptionEntries(lcsProjectId);

		for (LCSSubscriptionEntry subscriptionEntry : lcsSubscriptionEntries) {
			subscriptionEntry.setActive(false);

			updateLCSSubscriptionEntry(subscriptionEntry);
		}

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Process subscription entry JSON: " +
					lcsSubscriptionEntriesJSON);
		}

		List<LCSSubscriptionEntry> remoteLCSSubscriptionEntries =
			new ArrayList<>();

		remoteLCSSubscriptionEntries.addAll(
			doGetToList(lcsSubscriptionEntriesJSON));

		Iterator<LCSSubscriptionEntry> iterator =
			remoteLCSSubscriptionEntries.iterator();

		while (iterator.hasNext()) {
			LCSSubscriptionEntry remoteLCSSubscriptionEntry = iterator.next();

			if (!isActive(remoteLCSSubscriptionEntry)) {
				if (_log.isDebugEnabled()) {
					_log.debug(
						"Expired LCS subscription entry " +
							remoteLCSSubscriptionEntry);
				}

				iterator.remove();
			}

			try {
				validate(remoteLCSSubscriptionEntry);
			}
			catch (PortalException pe) {
				if ((pe instanceof LCSSubscriptionEntryProductException) ||
					(pe instanceof LCSSubscriptionEntryTypeException)) {

					_log.error(
						"Invalid LCS subscription entry with LCS project ID " +
							lcsProjectId,
						pe);

					continue;
				}

				throw pe;
			}
		}

		for (LCSSubscriptionEntry remoteLCSSubscriptionEntry :
				remoteLCSSubscriptionEntries) {

			remoteLCSSubscriptionEntry.setLcsProjectId(lcsProjectId);

			LCSSubscriptionEntry newLCSSubscriptionEntry =
				addLCSSubscriptionEntry(remoteLCSSubscriptionEntry);

			if (_log.isDebugEnabled()) {
				_log.debug(
					"Added LCS subscription entry " +
						newLCSSubscriptionEntry.getLcsSubscriptionEntryId());
			}
		}
	}

	protected void updateServerUsed(
			long lcsProjectId, String type, int processorCoresTotal,
			boolean increment)
		throws PortalException {

		String undefinedType = SubscriptionType.UNDEFINED.name();

		if (StringUtil.equalsIgnoreCase(type, undefinedType)) {
			return;
		}

		LCSSubscriptionEntry lcsSubscriptionEntry =
			getLCSProjectLCSSubscriptionEntry(
				lcsProjectId, type, processorCoresTotal, increment);

		int serversUsed = lcsSubscriptionEntry.getServersUsed();

		if (increment) {
			lcsSubscriptionEntry.setServersUsed(serversUsed + 1);
		}
		else {
			lcsSubscriptionEntry.setServersUsed(serversUsed - 1);
		}

		updateLCSSubscriptionEntry(lcsSubscriptionEntry);
	}

	protected void validate(LCSSubscriptionEntry lcsSubscriptionEntry)
		throws PortalException {

		SubscriptionType subscriptionType = SubscriptionType.valueOf(
			lcsSubscriptionEntry.getType());

		if (subscriptionType == SubscriptionType.UNDEFINED) {
			throw new LCSSubscriptionEntryTypeException(
				"Invalid subscription type \"" + subscriptionType.name() +
					"\"");
		}

		if (subscriptionType != SubscriptionType.PRODUCTION) {
			return;
		}

		String product = lcsSubscriptionEntry.getProduct();

		if (product.contains("Non-Production")) {
			StringBundler sb = new StringBundler(5);

			sb.append("Invalid product \"");
			sb.append(product);
			sb.append("\" for subscription type \"");
			sb.append(subscriptionType.name());
			sb.append("\"");

			throw new LCSSubscriptionEntryProductException(sb.toString());
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LCSSubscriptionEntryLocalServiceImpl.class);

	private ObjectMapper _objectMapper;
	private OSBPortletService _osbPortletService;

}