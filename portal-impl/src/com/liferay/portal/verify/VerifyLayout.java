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

package com.liferay.portal.verify;

import com.liferay.portal.LayoutFriendlyURLException;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutFriendlyURL;
import com.liferay.portal.service.LayoutFriendlyURLLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Gergely Mathe
 * @author Kenneth Chang
 */
public class VerifyLayout extends VerifyProcess {

	protected void deleteOrphanedLayouts() throws Exception {
		runSQL(
			"delete from Layout where layoutPrototypeUuid != '' and " +
				"layoutPrototypeUuid not in (select uuid_ from " +
					"LayoutPrototype)");
	}

	@Override
	protected void doVerify() throws Exception {
		deleteOrphanedLayouts();
		verifyFriendlyURL();
		verifyLayoutIdFriendlyURL();
		verifyLayoutPrototypeLinkEnabled();
		verifyUuid();
	}

	protected void verifyFriendlyURL() throws Exception {
		List<Layout> layouts =
			LayoutLocalServiceUtil.getNullFriendlyURLLayouts();

		for (Layout layout : layouts) {
			List<LayoutFriendlyURL> layoutFriendlyURLs =
				LayoutFriendlyURLLocalServiceUtil.getLayoutFriendlyURLs(
					layout.getPlid());

			for (LayoutFriendlyURL layoutFriendlyURL : layoutFriendlyURLs) {
				String friendlyURL = StringPool.SLASH + layout.getLayoutId();

				LayoutLocalServiceUtil.updateFriendlyURL(
					layout.getUserId(), layout.getPlid(), friendlyURL,
					layoutFriendlyURL.getLanguageId());
			}
		}
	}

	protected void verifyLayoutIdFriendlyURL() throws Exception {
		while (true) {
			List<Layout> affectedLayouts =
				getInvalidLayoutIdFriendlyURLLayouts();

			if (affectedLayouts.isEmpty()) {
				break;
			}

			for (Layout layout : affectedLayouts) {
				long layoutId = layout.getLayoutId();

				String friendlyURL = layout.getFriendlyURL();

				String newFriendlyURL = StringPool.SLASH + layoutId;

				if (_log.isDebugEnabled()) {
					_log.debug(
						"Updating layout with friendlyURL: " + friendlyURL +
						" to: " + newFriendlyURL);
				}

				List<LayoutFriendlyURL> layoutFriendlyURLs =
					LayoutFriendlyURLLocalServiceUtil.getLayoutFriendlyURLs(
						layout.getPlid());

				for (LayoutFriendlyURL layoutFriendlyURL : layoutFriendlyURLs) {
					if (!friendlyURL.equals(
							layoutFriendlyURL.getFriendlyURL())) {

						continue;
					}

					try {
						LayoutLocalServiceUtil.updateFriendlyURL(
							layout.getUserId(), layout.getPlid(),
							newFriendlyURL, layoutFriendlyURL.getLanguageId());
					}
					catch (LayoutFriendlyURLException lfurle) {
						int type = lfurle.getType();

						if (type == LayoutFriendlyURLException.DUPLICATE) {
							continue;
						}
						else {
							throw lfurle;
						}
					}
				}

				try {
					Layout duplicateLayout =
						LayoutLocalServiceUtil.fetchLayoutByFriendlyURL(
							layout.getGroupId(), layout.isPrivateLayout(),
							newFriendlyURL);

					if (duplicateLayout != null) {
						throw new LayoutFriendlyURLException(
							LayoutFriendlyURLException.DUPLICATE);
					}

					layout.setFriendlyURL(newFriendlyURL);

					LayoutLocalServiceUtil.updateLayout(layout);
				}
				catch (LayoutFriendlyURLException lfurle) {
					int type = lfurle.getType();

					if (type == LayoutFriendlyURLException.DUPLICATE) {
						continue;
					}
					else {
						throw lfurle;
					}
				}
			}
		}
	}

	protected void verifyLayoutPrototypeLinkEnabled() throws Exception {
		runSQL(
			"update Layout set layoutPrototypeLinkEnabled = [$FALSE$] where " +
				"type_ = 'link_to_layout' and layoutPrototypeLinkEnabled = " +
				"[$TRUE$]");
	}

	protected void verifyUuid() throws Exception {
		verifyUuid("AssetEntry");
		verifyUuid("JournalArticle");

		runSQL(
			"update Layout set uuid_ = sourcePrototypeLayoutUuid where " +
				"sourcePrototypeLayoutUuid != '' and uuid_ != " +
					"sourcePrototypeLayoutUuid");
	}

	protected void verifyUuid(String tableName) throws Exception {
		StringBundler sb = new StringBundler(12);

		sb.append("update ");
		sb.append(tableName);
		sb.append(" set layoutUuid = (select distinct ");
		sb.append("sourcePrototypeLayoutUuid from Layout where ");
		sb.append("Layout.uuid_ = ");
		sb.append(tableName);
		sb.append(".layoutUuid) where exists (select 1 from Layout where ");
		sb.append("Layout.uuid_ = ");
		sb.append(tableName);
		sb.append(".layoutUuid and Layout.uuid_ != ");
		sb.append("Layout.sourcePrototypeLayoutUuid and ");
		sb.append("Layout.sourcePrototypeLayoutUuid != '')");

		runSQL(sb.toString());
	}

	protected List<Layout> getInvalidLayoutIdFriendlyURLLayouts()
		throws Exception {

		final List<Layout> layouts = new ArrayList<>();

		ActionableDynamicQuery actionableDynamicQuery =
			LayoutLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod() {

				@Override
				public void performAction(Object object) {
					Layout layout = (Layout)object;

					String friendlyURL = layout.getFriendlyURL();
					
					friendlyURL = friendlyURL.substring(1);

					if (Validator.isNumber(friendlyURL) &&
						!friendlyURL.equals(
							String.valueOf(layout.getLayoutId()))) {

						layouts.add(layout);
					}
				}

			});

		actionableDynamicQuery.performActions();

		return layouts;
	}

	private static final Log _log = LogFactoryUtil.getLog(VerifyLayout.class);

}