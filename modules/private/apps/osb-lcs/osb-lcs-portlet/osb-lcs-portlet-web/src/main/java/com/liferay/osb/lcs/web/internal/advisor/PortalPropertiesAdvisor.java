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

package com.liferay.osb.lcs.advisor;

import com.liferay.osb.lcs.NoSuchLCSMetadataException;
import com.liferay.osb.lcs.model.LCSClusterNode;
import com.liferay.osb.lcs.model.LCSMetadata;
import com.liferay.osb.lcs.nosql.model.LCSClusterNodeProperties;
import com.liferay.osb.lcs.nosql.model.LCSMetadataDetails;
import com.liferay.osb.lcs.nosql.service.LCSClusterEntryPropertyDifferencesService;
import com.liferay.osb.lcs.nosql.service.LCSClusterNodePropertiesService;
import com.liferay.osb.lcs.nosql.service.LCSMetadataDetailsService;
import com.liferay.osb.lcs.service.LCSClusterNodeService;
import com.liferay.osb.lcs.service.LCSMetadataLocalService;
import com.liferay.osb.lcs.util.ApplicationProfile;
import com.liferay.osb.lcs.util.LCSClusterNodeUtil;
import com.liferay.osb.lcs.util.PortletPropsValues;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Digester;
import com.liferay.portal.kernel.util.DigesterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Igor Beslic
 */
public class PortalPropertiesAdvisor {

	public String fetchLCSClusterNodePropertiesHashCode(String key) {
		LCSClusterNodeProperties lcsClusterNodeProperties =
			_lcsClusterNodePropertiesService.fetchLCSClusterNodeProperties(key);

		if (lcsClusterNodeProperties != null) {
			return lcsClusterNodeProperties.getHashCode();
		}

		return null;
	}

	public Map<String, Map<String, String>>
		getLCSClusterEntryPropertyDifferencesMap(long lcsClusterEntryId) {

		if (PortletPropsValues.APPLICATION_PROFILE ==
				ApplicationProfile.PRODUCTION) {

			throw new UnsupportedOperationException();
		}

		return
			_lcsClusterEntryPropertyDifferencesService.
				getLCSClusterEntryPropertyDifferencesMap(lcsClusterEntryId);
	}

	public JSONArray getPortalPropertiesDifference(String key)
		throws PortalException, SystemException {

		LCSClusterNode lcsClusterNode =
			_lcsClusterNodeService.fetchLCSClusterNode(key);

		lcsClusterNode = _lcsClusterNodeService.getLCSClusterNode(
			lcsClusterNode.getLcsClusterNodeId(), true);

		List<LCSMetadata> lcsMetadatas =
			_lcsMetadataLocalService.getLCSMetadatas(
				lcsClusterNode.getBuildNumber(),
				lcsClusterNode.getPortalEdition());

		if (lcsMetadatas.isEmpty()) {
			throw new NoSuchLCSMetadataException();
		}

		LCSMetadata lcsMetadata = lcsMetadatas.get(0);

		LCSMetadataDetails lcsMetadataDetails =
			_lcsMetadataDetailsService.fetchLCSMetadataDetails(
				lcsMetadata.getBuildNumber(), lcsMetadata.getGitTag(),
				lcsMetadata.getPortalEdition());

		Map<String, String> defaultPortalProperties =
			lcsMetadataDetails.getPortalProperties();

		LCSClusterNodeProperties lcsClusterNodeProperties =
			_lcsClusterNodePropertiesService.fetchLCSClusterNodeProperties(key);

		Map<String, String> lcsClusterNodePortalProperties =
			lcsClusterNodeProperties.getProperties();

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (Map.Entry<String, String> entry :
				lcsClusterNodePortalProperties.entrySet()) {

			String value = defaultPortalProperties.get(entry.getKey());

			if (!defaultPortalProperties.containsKey(entry.getKey())) {
				JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

				jsonObject.put("key", entry.getKey());
				jsonObject.put("lcsClusterNodeValue", entry.getValue());
				jsonObject.put("originalValue", StringPool.BLANK);

				jsonArray.put(jsonObject);
			}
			else if (!StringUtil.equalsIgnoreCase(value, entry.getValue())) {
				JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

				jsonObject.put("key", entry.getKey());
				jsonObject.put("lcsClusterNodeValue", entry.getValue());
				jsonObject.put("originalValue", value);

				jsonArray.put(jsonObject);
			}
		}

		return jsonArray;
	}

	public void processLCSClusterEntryPropertyDifferences(
		String key, long lcsClusterEntryId) {

		Map<String, Map<String, String>> lcsClusterEntryPropertyDifferencesMap =
			_lcsClusterEntryPropertyDifferencesService.
				getLCSClusterEntryPropertyDifferencesMap(lcsClusterEntryId);

		removeKey(key, lcsClusterEntryPropertyDifferencesMap);

		_lcsClusterEntryPropertyDifferencesService.
			addLCSClusterEntryPropertyDifferencesMap(
				lcsClusterEntryId, lcsClusterEntryPropertyDifferencesMap);
	}

	public void updateLCSClusterNodeProperties(
		String key, Map<String, String> portalProperties) {

		if (PortletPropsValues.APPLICATION_PROFILE ==
				ApplicationProfile.PRODUCTION) {

			throw new UnsupportedOperationException();
		}

		_lcsClusterNodePropertiesService.updateLCSClusterNodeProperties(
			getHashCode(portalProperties), key, new Date(), portalProperties);
	}

	public void verifyLCSClusterEntryLCSClusterNodesPropertiesDifferences(
			String key)
		throws PortalException, SystemException {

		LCSClusterNode lcsClusterNode =
			_lcsClusterNodeService.getLCSClusterNode(key);

		LCSClusterNode randomSiblingLCSClusterNode =
			LCSClusterNodeUtil.fetchRandomSiblingLCSClusterNode(lcsClusterNode);

		if (randomSiblingLCSClusterNode == null) {
			return;
		}

		Map<String, Map<String, String>>
			lcsClusterEntryPropertyDifferencesMap =
				_lcsClusterEntryPropertyDifferencesService.
					getLCSClusterEntryPropertyDifferencesMap(
						lcsClusterNode.getLcsClusterEntryId());

		LCSClusterNodeProperties lcsClusterNodeProperties =
			_lcsClusterNodePropertiesService.fetchLCSClusterNodeProperties(key);

		LCSClusterNodeProperties randomSiblingLCSClusterNodeProperties =
			_lcsClusterNodePropertiesService.fetchLCSClusterNodeProperties(
				randomSiblingLCSClusterNode.getKey());

		if (lcsClusterEntryPropertyDifferencesMap.isEmpty()) {
			if (StringUtil.equalsIgnoreCase(
					lcsClusterNodeProperties.getHashCode(),
					randomSiblingLCSClusterNodeProperties.getHashCode())) {

				return;
			}
		}

		Map<String, String> newPortalProperties =
			lcsClusterNodeProperties.getProperties();

		Map<String, String> portalProperties =
			randomSiblingLCSClusterNodeProperties.getProperties();

		processLCSClusterEntryPropertyDifferences(
			key, lcsClusterNode.getLcsClusterEntryId(),
			lcsClusterEntryPropertyDifferencesMap, newPortalProperties,
			portalProperties);
	}

	protected String getHashCode(Map<String, String> portalProperties) {
		StringBundler sb = new StringBundler(portalProperties.size());

		for (Object portalPropertiesKey : portalProperties.keySet()) {
			sb.append(
				DigesterUtil.digestHex(
					Digester.MD5,
					(String)portalProperties.get(portalPropertiesKey)));
		}

		return DigesterUtil.digestHex(Digester.MD5, sb.toString());
	}

	protected void processAdditionalPortalProperties(
		Map<String, Map<String, String>> lcsClusterEntryPropertyDifferencesMap,
		String key, String siblingKeys, Map<String, String> newPortalProperties,
		Map<String, String> portalProperties) {

		Set<String> additionalKeys = newPortalProperties.keySet();

		additionalKeys.removeAll(portalProperties.keySet());

		for (String additionalKey : additionalKeys) {
			Map<String, String> propertyValuesMap =
				new HashMap<String, String>();

			propertyValuesMap.put(key, newPortalProperties.get(additionalKey));

			propertyValuesMap.put(siblingKeys, StringPool.BLANK);

			lcsClusterEntryPropertyDifferencesMap.put(
				additionalKey, propertyValuesMap);
		}
	}

	protected void processChangedPortalProperties(
		Map<String, Map<String, String>> lcsClusterEntryPropertyDifferencesMap,
		String key, String siblingKeys, Map<String, String> newPortalProperties,
		Map<String, String> portalProperties) {

		Set<Map.Entry<String, String>> changedPortalProperties =
			newPortalProperties.entrySet();

		changedPortalProperties.removeAll(portalProperties.entrySet());

		for (Map.Entry<String, String> entry : changedPortalProperties) {
			Map<String, String> propertyValuesMap =
				new HashMap<String, String>();

			String value = entry.getValue();

			propertyValuesMap.put(
				key, (value == null) ? StringPool.BLANK : value);

			value = portalProperties.get(entry.getKey());

			propertyValuesMap.put(
				siblingKeys, (value == null) ? StringPool.BLANK : value);

			lcsClusterEntryPropertyDifferencesMap.put(
				entry.getKey(), propertyValuesMap);
		}
	}

	protected void processLCSClusterEntryPropertyDifferences(
			String key, long lcsClusterEntryId,
			Map<String, Map<String, String>>
				lcsClusterEntryPropertyDifferencesMap,
			Map<String, String> newPortalProperties,
			Map<String, String> portalProperties)
		throws PortalException, SystemException {

		if (!lcsClusterEntryPropertyDifferencesMap.isEmpty()) {
			updateLCSClusterEntryPropertyDifferencesMap(
				key, lcsClusterEntryPropertyDifferencesMap,
				newPortalProperties);

			removeProcessedProperties(
				portalProperties, newPortalProperties,
				lcsClusterEntryPropertyDifferencesMap.keySet());
		}

		String siblingKeys = LCSClusterNodeUtil.getLCSClusterNodeSiblingKeys(
			key);

		processAdditionalPortalProperties(
			lcsClusterEntryPropertyDifferencesMap, key, siblingKeys,
			new HashMap<String, String>(newPortalProperties), portalProperties);

		processMissingPortalProperties(
			lcsClusterEntryPropertyDifferencesMap, key, siblingKeys,
			newPortalProperties, new HashMap<String, String>(portalProperties));

		processChangedPortalProperties(
			lcsClusterEntryPropertyDifferencesMap, key, siblingKeys,
			newPortalProperties, portalProperties);

		_lcsClusterEntryPropertyDifferencesService.
			addLCSClusterEntryPropertyDifferencesMap(
				lcsClusterEntryId, lcsClusterEntryPropertyDifferencesMap);
	}

	protected void processMissingPortalProperties(
		Map<String, Map<String, String>> lcsClusterEntryPropertyDifferencesMap,
		String key, String siblingKeys, Map<String, String> newPortalProperties,
		Map<String, String> portalProperties) {

		processAdditionalPortalProperties(
			lcsClusterEntryPropertyDifferencesMap, siblingKeys, key,
			portalProperties, newPortalProperties);
	}

	protected void removeKey(
		String key,
		Map<String, Map<String, String>>
			lcsClusterEntryPropertyDifferencesMap) {

		for (Map.Entry<String, Map<String, String>> entry :
				lcsClusterEntryPropertyDifferencesMap.entrySet()) {

			Map<String, String> propertyValuesMap = entry.getValue();

			for (Map.Entry<String, String> propertyValuesEntry :
					propertyValuesMap.entrySet()) {

				String keys = propertyValuesEntry.getKey();

				if (keys.contains(key)) {
					if (StringUtil.equalsIgnoreCase(keys, key)) {
						propertyValuesMap.remove(key);

						break;
					}

					StringBundler sb = new StringBundler(5);

					sb.append("\\|");
					sb.append(key);
					sb.append(StringPool.PIPE);
					sb.append(key);
					sb.append("\\|");

					keys.replaceAll(sb.toString(), StringPool.BLANK);

					break;
				}
			}
		}
	}

	protected void removeProcessedProperties(
		Map<String, String> lcsClusterNodePortalProperties,
		Map<String, String> newPortalProperties, Set<String> keys) {

		for (String key : keys) {
			lcsClusterNodePortalProperties.remove(key);
			newPortalProperties.remove(key);
		}
	}

	protected void updateLCSClusterEntryPropertyDifferencesMap(
		String key,
		Map<String, Map<String, String>> lcsClusterEntryPropertyDifferencesMap,
		Map<String, String> newPortalProperties) {

		removeKey(key, lcsClusterEntryPropertyDifferencesMap);

		for (Map.Entry<String, Map<String, String>> entry :
				lcsClusterEntryPropertyDifferencesMap.entrySet()) {

			String value = newPortalProperties.get(entry.getKey());

			if (value == null) {
				value = StringPool.BLANK;
			}

			String oldKey = null;
			Map<String, String> propertyValuesMap = entry.getValue();

			for (Map.Entry<String, String> propertyValuesEntry :
					propertyValuesMap.entrySet()) {

				if (StringUtil.equalsIgnoreCase(
						propertyValuesEntry.getValue(), value)) {

					oldKey = propertyValuesEntry.getKey();

					propertyValuesMap.remove(oldKey);

					String newKey = StringPool.PIPE.concat(key);

					propertyValuesMap.put(oldKey.concat(newKey), value);

					break;
				}
			}

			if (oldKey == null) {
				propertyValuesMap.put(key, value);
			}
		}
	}

	@BeanReference(type = LCSClusterEntryPropertyDifferencesService.class)
	private LCSClusterEntryPropertyDifferencesService
		_lcsClusterEntryPropertyDifferencesService;

	@BeanReference(type = LCSClusterNodePropertiesService.class)
	private LCSClusterNodePropertiesService _lcsClusterNodePropertiesService;

	@BeanReference(type = LCSClusterNodeService.class)
	private LCSClusterNodeService _lcsClusterNodeService;

	@BeanReference(type = LCSMetadataDetailsService.class)
	private LCSMetadataDetailsService _lcsMetadataDetailsService;

	@BeanReference(type = LCSMetadataLocalService.class)
	private LCSMetadataLocalService _lcsMetadataLocalService;

}