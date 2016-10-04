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

package com.liferay.twitter.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.twitter.model.Feed;
import com.liferay.twitter.service.FeedLocalServiceUtil;

/**
 * The extended model base implementation for the Feed service. Represents a row in the &quot;Twitter_Feed&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link FeedImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FeedImpl
 * @see Feed
 * @generated
 */
@ProviderType
public abstract class FeedBaseImpl extends FeedModelImpl implements Feed {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a feed model instance should use the {@link Feed} interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			FeedLocalServiceUtil.addFeed(this);
		}
		else {
			FeedLocalServiceUtil.updateFeed(this);
		}
	}
}