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

package com.liferay.osb.ldn.generator.guest.internal.layout;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFileVersion;
import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.document.library.kernel.service.DLFileEntryMetadataLocalService;
import com.liferay.document.library.kernel.util.RawMetadataProcessor;
import com.liferay.dynamic.data.mapping.kernel.DDMFormValues;
import com.liferay.dynamic.data.mapping.kernel.DDMStructure;
import com.liferay.dynamic.data.mapping.kernel.DDMStructureManagerUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalArticleConstants;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.journal.service.JournalContentSearchLocalService;
import com.liferay.osb.ldn.generator.guest.internal.layout.constants.HomeLayoutGeneratorConstants;
import com.liferay.osb.ldn.generator.guest.site.constants.GuestSiteConstants;
import com.liferay.osb.ldn.generator.layout.BaseLayoutGenerator;
import com.liferay.osb.ldn.generator.layout.LayoutGenerator;
import com.liferay.osb.ldn.generator.layout.LayoutVersion;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.metadata.RawMetadataProcessorUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.model.LayoutTypePortlet;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.ResourceLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.File;

import java.net.URL;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.portlet.PortletPreferences;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Yury Butrymovich
 * @author Ryan Park
 * @author Howie Chou
 */
@Component(
	immediate = true,
	property = {
		"osb.ldn.layout.description=", "osb.ldn.layout.friendly.url=/home",
		"osb.ldn.layout.hidden:Boolean=true", "osb.ldn.layout.name=Home",
		"osb.ldn.layout.order:Integer=1", "osb.ldn.layout.title=Home",
		"osb.ldn.layout.type=" + LayoutConstants.TYPE_PORTLET,
		"osb.ldn.site.generator.key=" + GuestSiteConstants.GUEST_SITE_KEY
	},
	service = LayoutGenerator.class
)
public class HomeLayoutGenerator extends BaseLayoutGenerator {

	public static final int LAYOUT_VERSION = 1;

	@Override
	public int getLayoutVersion() {
		return LAYOUT_VERSION;
	}

	protected JournalArticle createJournalArticle(
			long userId, long companyId, long groupId, long layoutId,
			boolean isPrivateLayout)
		throws Exception {

		Bundle bundle = FrameworkUtil.getBundle(HomeLayoutGenerator.class);

		String content = getWebContent(userId, bundle);

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setScopeGroupId(groupId);
		serviceContext.setUserId(userId);

		uploadIconFiles(userId, bundle, companyId, groupId, serviceContext);

		content = replaceIconURL(content, groupId);

		Locale locale = LocaleUtil.fromLanguageId(
			LocalizationUtil.getDefaultLanguageId(content));

		Map<Locale, String> titleMap = new HashMap<>();

		titleMap.put(locale, HomeLayoutGeneratorConstants.WEB_CONTENT_TITLE);

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(new Date());

		JournalArticle journalArticle = _journalArticleLocalService.addArticle(
			userId, groupId, 0, JournalArticleConstants.CLASSNAME_ID_DEFAULT, 0,
			StringPool.BLANK, true, JournalArticleConstants.VERSION_DEFAULT,
			titleMap, null, content,
			HomeLayoutGeneratorConstants.DDM_STRUCTURE_KEY,
			HomeLayoutGeneratorConstants.DDM_TEMPLATE_KEY, null,
			calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE),
			calendar.get(Calendar.YEAR), calendar.get(Calendar.HOUR_OF_DAY),
			calendar.get(Calendar.MINUTE), 0, 0, 0, 0, 0, true, 0, 0, 0, 0, 0,
			true, true, false, StringPool.BLANK, new File(StringPool.BLANK),
			new HashMap<String, byte[]>(), StringPool.BLANK, serviceContext);

		_journalContentSearchLocalService.updateContentSearch(
			groupId, isPrivateLayout, layoutId,
			HomeLayoutGeneratorConstants.OPEN_SOURCE_FOR_LIFE_PORTLET_ID,
			journalArticle.getArticleId(), true);

		return journalArticle;
	}

	@Override
	protected void doGenerate(long plid) throws Exception {
		Layout layout = _layoutLocalService.getLayout(plid);

		if (isLayoutInstalled(layout)) {
			return;
		}

		layout.setTypeSettings(StringPool.BLANK);

		LayoutTypePortlet layoutTypePortlet =
			(LayoutTypePortlet)layout.getLayoutType();

		User user = _userLocalService.getDefaultUser(layout.getCompanyId());

		layoutTypePortlet.setLayoutTemplateId(
			user.getUserId(), "1_column", false);

		layoutTypePortlet.addPortletId(
			user.getUserId(),
			StringUtil.merge(
				HomeLayoutGeneratorConstants.getPortletIds(), StringPool.COMMA),
			"column-1", 1, false);

		_layoutLocalService.updateLayout(
			layout.getGroupId(), layout.getPrivateLayout(),
			layout.getLayoutId(), layout.getTypeSettings());

		JournalArticle journalArticle = createJournalArticle(
			user.getUserId(), layout.getCompanyId(), layout.getGroupId(),
			layout.getLayoutId(), layout.isPrivateLayout());

		setPortletPreferences(journalArticle, layout);
	}

	protected String getWebContent(long userId, Bundle bundle)
		throws Exception {

		String content = null;

		File resourceFile = null;

		try {
			URL resourceURL = bundle.getResource(
				HomeLayoutGeneratorConstants.XML_RESOURCE_URL);

			resourceFile = FileUtil.createTempFile(resourceURL.openStream());

			content = FileUtil.read(resourceFile);
		}
		finally {
			FileUtil.delete(resourceFile);
		}

		return content;
	}

	protected boolean isLayoutInstalled(Layout layout) {
		String[] portletIds = HomeLayoutGeneratorConstants.getPortletIds();

		for (String portletId : portletIds) {
			if (StringUtil.contains(
					layout.getTypeSettings(), portletId, StringPool.BLANK)) {

				continue;
			}

			return false;
		}

		return true;
	}

	protected String replaceIconURL(String content, long groupId) {
		Properties properties = PortalUtil.getPortalProperties();

		String browserLauncherURL = properties.getProperty(
			"browser.launcher.url");

		String[] iconFileNames =
			HomeLayoutGeneratorConstants.getIconFileNames();

		String iconUrlPrefix =
			browserLauncherURL + "/documents/" + groupId + "/0/";

		for (String iconFileName : iconFileNames) {
			content = StringUtil.replace(
				content, iconFileName, iconUrlPrefix + iconFileName);
		}

		return content;
	}

	protected void setPortletPreferences(
			JournalArticle journalArticle, Layout layout)
		throws Exception {

		PortletPreferences portletPreferences =
			PortletPreferencesFactoryUtil.getStrictPortletSetup(
				layout,
				HomeLayoutGeneratorConstants.OPEN_SOURCE_FOR_LIFE_PORTLET_ID);

		portletPreferences.setValue(
			"groupId", String.valueOf(journalArticle.getGroupId()));
		portletPreferences.setValue("articleId", journalArticle.getArticleId());

		portletPreferences.store();
	}

	protected void uploadIconFiles(
			long userId, Bundle bundle, long companyId, long groupId,
			ServiceContext serviceContext)
		throws Exception {

		File resourceFile = null;

		URL resourceURL = null;

		try {
			String[] iconFileNames =
				HomeLayoutGeneratorConstants.getIconFileNames();

			for (String iconFileName : iconFileNames) {
				resourceURL = bundle.getResource(
					HomeLayoutGeneratorConstants.IMAGE_RESOURCE_FOLDER_URL +
						iconFileName);

				resourceFile = FileUtil.createTempFile(
					resourceURL.openStream());

				byte[] fileByteArray = FileUtil.getBytes(resourceFile);

				DLFileEntry dlFileEntry = _dlFileEntryLocalService.addFileEntry(
					userId, groupId, groupId, 0, iconFileName,
					MimeTypesUtil.getContentType(iconFileName), iconFileName,
					StringPool.BLANK, StringPool.BLANK, 0,
					new HashMap<String, DDMFormValues>(), resourceFile, null,
					fileByteArray.length, new ServiceContext());

				_resourceLocalService.addResources(
					companyId, groupId, userId, DLFileEntry.class.getName(),
					dlFileEntry.getFileEntryId(), false, false, true);

				List<DDMStructure> ddmStructures =
					DDMStructureManagerUtil.getClassStructures(
						companyId,
						PortalUtil.getClassNameId(RawMetadataProcessor.class),
						QueryUtil.ALL_POS, QueryUtil.ALL_POS);

				DLFileVersion dlFileVersion = dlFileEntry.getFileVersion();

				Map<String, DDMFormValues> rawMetadataMap =
					RawMetadataProcessorUtil.getRawMetadataMap(
						MimeTypesUtil.getExtensionContentType(iconFileName),
						MimeTypesUtil.getContentType(iconFileName),
						resourceFile);

				_dlFileEntryMetadataLocalService.updateFileEntryMetadata(
					companyId, ddmStructures, dlFileEntry.getFileEntryId(),
					dlFileVersion.getFileVersionId(), rawMetadataMap,
					serviceContext);
			}
		}
		finally {
			FileUtil.delete(resourceFile);
		}
	}

	@Reference
	private void setLayoutVersion(LayoutVersion layoutVersion) {
		this.layoutVersion = layoutVersion;
	}

	@Reference
	private DLFileEntryLocalService _dlFileEntryLocalService;

	@Reference
	private DLFileEntryMetadataLocalService _dlFileEntryMetadataLocalService;

	@Reference
	private JournalArticleLocalService _journalArticleLocalService;

	@Reference
	private JournalContentSearchLocalService _journalContentSearchLocalService;

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private ResourceLocalService _resourceLocalService;

	@Reference
	private UserLocalService _userLocalService;

}