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

package com.liferay.portlet.journal.model.impl;

import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;

/**
 * The extended model base implementation for the JournalArticle service. Represents a row in the &quot;JournalArticle&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link JournalArticleImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JournalArticleImpl
 * @see com.liferay.portlet.journal.model.JournalArticle
 * @generated
 */
public abstract class JournalArticleBaseImpl extends JournalArticleModelImpl
	implements JournalArticle {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a journal article model instance should use the {@link JournalArticle} interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			JournalArticleLocalServiceUtil.addJournalArticle(this);
		}
		else {
			JournalArticleLocalServiceUtil.updateJournalArticle(this);
		}
	}

	@Override
	public void updateTreePath(String treePath) {
		JournalArticle journalArticle = this;

		journalArticle.setTreePath(treePath);

		JournalArticleLocalServiceUtil.updateJournalArticle(journalArticle);
	}
}