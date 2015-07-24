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

package com.liferay.journal.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.journal.model.JournalFolder;
import com.liferay.journal.service.JournalFolderLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.util.ArrayList;
import java.util.List;

/**
 * The extended model base implementation for the JournalFolder service. Represents a row in the &quot;JournalFolder&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link JournalFolderImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JournalFolderImpl
 * @see JournalFolder
 * @generated
 */
@ProviderType
public abstract class JournalFolderBaseImpl extends JournalFolderModelImpl
	implements JournalFolder {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a journal folder model instance should use the {@link JournalFolder} interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			JournalFolderLocalServiceUtil.addJournalFolder(this);
		}
		else {
			JournalFolderLocalServiceUtil.updateJournalFolder(this);
		}
	}

	@Override
	@SuppressWarnings("unused")
	public String buildTreePath() throws PortalException {
		List<JournalFolder> journalFolders = new ArrayList<JournalFolder>();

		JournalFolder journalFolder = this;

		while (journalFolder != null) {
			journalFolders.add(journalFolder);

			journalFolder = JournalFolderLocalServiceUtil.fetchJournalFolder(journalFolder.getParentFolderId());
		}

		StringBundler sb = new StringBundler((journalFolders.size() * 2) + 1);

		sb.append(StringPool.SLASH);

		for (int i = journalFolders.size() - 1; i >= 0; i--) {
			journalFolder = journalFolders.get(i);

			sb.append(journalFolder.getFolderId());
			sb.append(StringPool.SLASH);
		}

		return sb.toString();
	}

	@Override
	public void updateTreePath(String treePath) {
		JournalFolder journalFolder = this;

		journalFolder.setTreePath(treePath);

		JournalFolderLocalServiceUtil.updateJournalFolder(journalFolder);
	}
}