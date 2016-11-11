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

import com.liferay.lcs.messaging.TransportMetadata;

/**
 * @author Riccardo Ferrari
 */
public class SQSTransportMetadata implements TransportMetadata {

	public String getMessageId() {
		return _messageId;
	}

	public String getQueueURL() {
		return _queueURL;
	}

	public String getReceiptHandle() {
		return _receiptHandle;
	}

	public void setMessageId(String messageId) {
		_messageId = messageId;
	}

	public void setQueueURL(String queueURL) {
		_queueURL = queueURL;
	}

	public void setReceiptHandle(String receiptHandle) {
		_receiptHandle = receiptHandle;
	}

	private String _messageId;
	private String _queueURL;
	private String _receiptHandle;

}