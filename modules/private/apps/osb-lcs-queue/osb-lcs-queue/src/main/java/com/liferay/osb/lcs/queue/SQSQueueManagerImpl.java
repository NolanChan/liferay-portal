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

package com.liferay.osb.lcs.queue;

import com.amazon.sqs.javamessaging.AmazonSQSExtendedClient;
import com.amazon.sqs.javamessaging.ExtendedClientConfiguration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.BucketLifecycleConfiguration;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.amazonaws.services.sqs.model.DeleteMessageBatchRequest;
import com.amazonaws.services.sqs.model.DeleteMessageBatchRequestEntry;
import com.amazonaws.services.sqs.model.GetQueueAttributesRequest;
import com.amazonaws.services.sqs.model.GetQueueAttributesResult;
import com.amazonaws.services.sqs.model.QueueAttributeName;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SetQueueAttributesRequest;

import com.liferay.lcs.messaging.Message;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class SQSQueueManagerImpl extends AbstractQueueManagerImpl {

	@Override
	public void afterPropertiesSet() {
		AWSCredentials awsCredentials = new BasicAWSCredentials(
			_accessKey, _secretKey);

		AmazonS3 amazonS3 = new AmazonS3Client(awsCredentials);

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
			"yyyyMMddhhmmss");

		String s3BucketName = "lcs-sqs-" + simpleDateFormat.format(new Date());

		amazonS3.createBucket(s3BucketName);

		amazonS3.setBucketLifecycleConfiguration(
			s3BucketName, getBucketLifecycleConfiguration());

		Region region = Region.getRegion(Regions.fromName(_region));

		amazonS3.setRegion(region);

		ExtendedClientConfiguration extendedClientConfiguration =
			new ExtendedClientConfiguration();

		extendedClientConfiguration.withLargePayloadSupportEnabled(
			amazonS3, s3BucketName);

		_amazonSQS = new AmazonSQSExtendedClient(
			new AmazonSQSClient(awsCredentials), extendedClientConfiguration);

		_amazonSQS.setRegion(region);

		String globalOutQueueURL = getQueueURL(Queue.CLOUD_OUT.getName());

		configureDeadLetterQueue(globalOutQueueURL);
	}

	@Override
	public void deleteMessage(Message message) {
		List<Message> messages = new ArrayList<>();

		messages.add(message);

		deleteMessages(messages);
	}

	@Override
	public void deleteMessages(List<Message> messages) {
		if (messages.isEmpty()) {
			return;
		}

		Map<String, List<Message>> map = new HashMap<>();

		for (Message message : messages) {
			List<Message> queueMessages = null;
			SQSTransportMetadata sqsTransportMetadata =
				message.getTransportMetadata();

			if (map.containsKey(sqsTransportMetadata.getQueueURL())) {
				queueMessages = map.get(sqsTransportMetadata.getQueueURL());
			}
			else {
				queueMessages = new ArrayList<>();

				map.put(sqsTransportMetadata.getQueueURL(), queueMessages);
			}

			queueMessages.add(message);
		}

		for (Map.Entry<String, List<Message>> entry : map.entrySet()) {
			List<DeleteMessageBatchRequestEntry>
				deleteMessageBatchRequestEntries = new ArrayList<>();

			for (Message message : entry.getValue()) {
				SQSTransportMetadata sqsTrasnportMetadata =
					message.getTransportMetadata();

				DeleteMessageBatchRequestEntry deleteMessageBatchRequestEntry =
					new DeleteMessageBatchRequestEntry(
						sqsTrasnportMetadata.getMessageId(),
						sqsTrasnportMetadata.getReceiptHandle());

				deleteMessageBatchRequestEntries.add(
					deleteMessageBatchRequestEntry);
			}

			DeleteMessageBatchRequest deleteMessageBatchRequest =
				new DeleteMessageBatchRequest(
					entry.getKey(), deleteMessageBatchRequestEntries);

			_amazonSQS.deleteMessageBatch(deleteMessageBatchRequest);
		}
	}

	@Override
	public <T extends Message> List<T> getMessages(
		String queueName, Class clazz) {

		List<T> messages = new ArrayList<>();

		String queueURL = getQueueURL(queueName);

		ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(
			queueURL);

		receiveMessageRequest.setMaxNumberOfMessages(10);

		ReceiveMessageResult receiveMessageResult = _amazonSQS.receiveMessage(
			receiveMessageRequest);

		List<com.amazonaws.services.sqs.model.Message> sqsMessages =
			receiveMessageResult.getMessages();

		for (com.amazonaws.services.sqs.model.Message sqsMessage :
				sqsMessages) {

			String body = sqsMessage.getBody();

			T message = Message.fromJSON(body, clazz);

			SQSTransportMetadata sqsTransportMetadata =
				new SQSTransportMetadata();

			sqsTransportMetadata.setMessageId(sqsMessage.getMessageId());
			sqsTransportMetadata.setQueueURL(queueURL);
			sqsTransportMetadata.setReceiptHandle(
				sqsMessage.getReceiptHandle());

			message.setQueueName(queueName);
			message.setTransportMetadata(sqsTransportMetadata);

			messages.add(message);
		}

		return messages;
	}

	public <T extends Message> void moveMessageToDeadLetterQueue(T message) {
		String cloudOutDeadLetterQueueURL = getQueueURL(
			Queue.CLOUD_OUT_DEAD_LETTER.getName());

		_amazonSQS.sendMessage(
			new SendMessageRequest(
				cloudOutDeadLetterQueueURL, message.toJSON()));

		deleteMessage(message);
	}

	@Override
	public <T extends Message> void sendMessage(T message) {
		String queueName = getQueueName(message);

		String queueURL = getQueueURL(queueName);

		_amazonSQS.sendMessage(
			new SendMessageRequest(queueURL, message.toJSON()));
	}

	@Override
	public void setAccessKey(String accessKey) {
		_accessKey = accessKey;
	}

	@Override
	public void setRegion(String region) {
		_region = region;
	}

	@Override
	public void setSecretKey(String secretKey) {
		_secretKey = secretKey;
	}

	protected void configureDeadLetterQueue(String queueURL) {
		SetQueueAttributesRequest setQueueAttributesRequest =
			new SetQueueAttributesRequest();

		CreateQueueResult globalOutDeadLetterCreateQueueResult =
			_amazonSQS.createQueue(
				getPrefixedQueueName(Queue.CLOUD_OUT_DEAD_LETTER.getName()));

		GetQueueAttributesRequest deadLetterGetQueueAttributesRequest =
			new GetQueueAttributesRequest(
				globalOutDeadLetterCreateQueueResult.getQueueUrl());

		deadLetterGetQueueAttributesRequest.withAttributeNames(
			QueueAttributeName.QueueArn);

		GetQueueAttributesResult deadLetterGetQueueAttributesResult =
			_amazonSQS.getQueueAttributes(deadLetterGetQueueAttributesRequest);

		Map attributes = deadLetterGetQueueAttributesResult.getAttributes();

		setQueueAttributesRequest.addAttributesEntry(
			QueueAttributeName.RedrivePolicy.toString(),
			String.format(
				"{\"deadLetterTargetArn\":\"%s\", \"maxReceiveCount\":\"%d\"}",
				String.valueOf(
					attributes.get(QueueAttributeName.QueueArn.toString())),
				3));

		setQueueAttributesRequest.setQueueUrl(queueURL);

		_amazonSQS.setQueueAttributes(setQueueAttributesRequest);
	}

	protected BucketLifecycleConfiguration getBucketLifecycleConfiguration() {
		BucketLifecycleConfiguration bucketLifecycleConfiguration =
			new BucketLifecycleConfiguration();

		bucketLifecycleConfiguration.withRules(getExpirationRule());

		return bucketLifecycleConfiguration;
	}

	protected BucketLifecycleConfiguration.Rule getExpirationRule() {
		BucketLifecycleConfiguration.Rule expirationRule =
			new BucketLifecycleConfiguration.Rule();

		expirationRule.withExpirationInDays(14);
		expirationRule.withStatus("Enabled");

		return expirationRule;
	}

	protected String getQueueURL(String queueName) {
		CreateQueueRequest createQueueRequest = new CreateQueueRequest(
			getPrefixedQueueName(queueName));

		Queue queue = Queue.toQueue(queueName);

		Map<String, String> attributes = new HashMap<>();

		if (queue.getMessageRetentionPeriod() != -1) {
			attributes.put(
				QueueAttributeName.MessageRetentionPeriod.toString(),
				String.valueOf(queue.getMessageRetentionPeriod()));
		}

		if (queue.getReceiveMessageWaitTimeSeconds() != -1) {
			attributes.put(
				QueueAttributeName.ReceiveMessageWaitTimeSeconds.toString(),
				String.valueOf(queue.getReceiveMessageWaitTimeSeconds()));
		}

		if (queue.getVisibilityTimeout() != -1) {
			attributes.put(
				QueueAttributeName.VisibilityTimeout.toString(),
				String.valueOf(queue.getVisibilityTimeout()));
		}

		if (!attributes.isEmpty()) {
			createQueueRequest.setAttributes(attributes);
		}

		CreateQueueResult createQueueResult = _amazonSQS.createQueue(
			createQueueRequest);

		return createQueueResult.getQueueUrl();
	}

	private String _accessKey;
	private AmazonSQS _amazonSQS;
	private String _region;
	private String _secretKey;

}