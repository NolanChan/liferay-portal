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

package com.liferay.osb.lcs.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import com.liferay.lcs.subscription.SubscriptionInstanceSize;
import com.liferay.lcs.subscription.SubscriptionType;
import com.liferay.osb.lcs.model.LCSSubscriptionEntry;

import java.io.IOException;

import java.util.Date;

/**
 * @author Igor Beslic
 */
public class LCSSubscriptionEntryDeserializer extends JsonDeserializer {

	@Override
	public Object deserialize(
			JsonParser jsonParser,
			DeserializationContext deserializationContext)
		throws IOException {

		Class<LCSSubscriptionEntry> lcsSubscriptionEntryClass =
			getLCSSubscriptionEntryClass();

		LCSSubscriptionEntry lcsSubscriptionEntry = null;

		try {
			lcsSubscriptionEntry = lcsSubscriptionEntryClass.newInstance();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}

		ObjectCodec objectCodec = jsonParser.getCodec();

		JsonNode jsonTreeNode = objectCodec.readTree(jsonParser);

		JsonNode jsonNode = jsonTreeNode.get("actualPrice");

		lcsSubscriptionEntry.setActualPrice(jsonNode.asDouble());

		jsonNode = jsonTreeNode.get("currencyCode");

		lcsSubscriptionEntry.setCurrencyCode(jsonNode.asText());

		jsonNode = jsonTreeNode.get("instanceSize");

		lcsSubscriptionEntry.setInstanceSize(jsonNode.asInt());

		jsonNode = jsonTreeNode.get("platform");

		lcsSubscriptionEntry.setPlatform(jsonNode.asText());

		jsonNode = jsonTreeNode.get("platformVersion");

		lcsSubscriptionEntry.setPlatformVersion(jsonNode.asInt());

		SubscriptionInstanceSize subscriptionInstanceSize =
			SubscriptionInstanceSize.valueOf(
				lcsSubscriptionEntry.getInstanceSize());

		lcsSubscriptionEntry.setProcessorCoresAllowed(
			subscriptionInstanceSize.getProcessorCoresAllowed());

		jsonNode = jsonTreeNode.get("product");

		lcsSubscriptionEntry.setProduct(jsonNode.asText());

		jsonNode = jsonTreeNode.get("productVersion");

		lcsSubscriptionEntry.setProductVersion(jsonNode.asInt());

		jsonNode = jsonTreeNode.get("serversAllowed");

		lcsSubscriptionEntry.setServersAllowed(jsonNode.asInt());

		jsonNode = jsonTreeNode.get("serversUsed");

		lcsSubscriptionEntry.setServersUsed(jsonNode.asInt());

		jsonNode = jsonTreeNode.get("startDate");

		lcsSubscriptionEntry.setStartDate(new Date(jsonNode.asLong()));

		jsonNode = jsonTreeNode.get("endDate");

		lcsSubscriptionEntry.setEndDate(new Date(jsonNode.asLong()));

		jsonNode = jsonTreeNode.get("supportStartDate");

		if (jsonNode.isNull()) {
			lcsSubscriptionEntry.setSupportStartDate(
				lcsSubscriptionEntry.getStartDate());
		}
		else {
			lcsSubscriptionEntry.setSupportStartDate(
				new Date(jsonNode.asLong()));
		}

		jsonNode = jsonTreeNode.get("supportEndDate");

		lcsSubscriptionEntry.setSupportEndDate(new Date(jsonNode.asLong()));

		jsonNode = jsonTreeNode.get("type");

		SubscriptionType subscriptionType = SubscriptionType.toSubscriptionType(
			jsonNode.asText());

		lcsSubscriptionEntry.setType(subscriptionType.name());

		return lcsSubscriptionEntry;
	}

	protected Class<LCSSubscriptionEntry> getLCSSubscriptionEntryClass() {
		if (_lcsSubscriptionEntryClass == null) {
			try {
				Class<?> thisClass = getClass();

				_lcsSubscriptionEntryClass = Class.forName(
					"com.liferay.osb.lcs.model.impl.LCSSubscriptionEntryImpl",
					false, thisClass.getClassLoader());
			}
			catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		return _lcsSubscriptionEntryClass;
	}

	private Class _lcsSubscriptionEntryClass;

}