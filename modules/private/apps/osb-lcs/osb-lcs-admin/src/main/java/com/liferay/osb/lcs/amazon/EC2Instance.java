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

package com.liferay.osb.lcs.amazon;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.Tag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Matija Petanjek
 */
public class EC2Instance {

	public Map<String, String> getPrivateIpAddressesNames() {
		Map<String, String> privateIpAddressesNames =
			new HashMap<String, String>();

		AWSCredentials awsCredentials = new BasicAWSCredentials(
			_accessKey, _secretKey);

		AmazonEC2Client amazonEC2Client = new AmazonEC2Client(awsCredentials);

		amazonEC2Client.setEndpoint(_endpoint);

		DescribeInstancesResult describeInstancesResult =
			amazonEC2Client.describeInstances();

		for (Reservation reservation :
				describeInstancesResult.getReservations()) {

			for (Instance instance : reservation.getInstances()) {
				List<Tag> tags = instance.getTags();

				if ((instance.getPublicIpAddress() == null) || tags.isEmpty()) {
					continue;
				}

				Tag tag = tags.get(0);

				privateIpAddressesNames.put(
					instance.getPrivateIpAddress(), tag.getValue());
			}
		}

		return privateIpAddressesNames;
	}

	public void setAccessKey(String accessKey) {
		_accessKey = accessKey;
	}

	public void setEndpoint(String endpoint) {
		_endpoint = endpoint;
	}

	public void setSecretKey(String secretKey) {
		_secretKey = secretKey;
	}

	private String _accessKey;
	private String _endpoint;
	private String _secretKey;

}