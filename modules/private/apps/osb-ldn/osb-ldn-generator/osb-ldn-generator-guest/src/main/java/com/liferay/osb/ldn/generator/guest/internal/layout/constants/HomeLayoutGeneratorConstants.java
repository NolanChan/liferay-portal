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

package com.liferay.osb.ldn.generator.guest.internal.layout.constants;

/**
 * @author Howie Chou
 */
public class HomeLayoutGeneratorConstants {

	public static final String CONVERSE_WITH_THE_COMMUNITY_ICON_FILE_NAME =
		"converse-with-the-community.png";

	public static final String DDM_STRUCTURE_KEY = "BASIC-WEB-CONTENT";

	public static final String DDM_TEMPLATE_KEY = "BASIC-WEB-CONTENT";

	public static final String DISCOVER_HOW_IT_WORKS_ICON_FILE_NAME =
		"discover-how-it-works.png";

	public static final String[] ICON_FILE_NAMES = {
		HomeLayoutGeneratorConstants.CONVERSE_WITH_THE_COMMUNITY_ICON_FILE_NAME,
		HomeLayoutGeneratorConstants.DISCOVER_HOW_IT_WORKS_ICON_FILE_NAME,
		HomeLayoutGeneratorConstants.SHARE_PERSPECTIVE_ICON_FILE_NAME
	};

	public static final String IMAGE_RESOURCE_FOLDER_URL =
		"open-source-for-life/images/";

	public static final String OPEN_SOURCE_FOR_LIFE_PORTLET_ID =
		"com_liferay_journal_content_web_portlet_JournalContentPortlet_" +
			"INSTANCE_pq90jAdypFZ3";

	public static final String[] PORTLET_IDS = {
		HomeLayoutGeneratorConstants.RANDOM_NINE_PORTLET_ID,
		HomeLayoutGeneratorConstants.OPEN_SOURCE_FOR_LIFE_PORTLET_ID
	};

	public static final String RANDOM_NINE_PORTLET_ID =
		"com_liferay_osb_ldn_documentation_project_random_nine_web_" +
			"DocumentationProjectRandomNinePortlet";

	public static final String SHARE_PERSPECTIVE_ICON_FILE_NAME =
		"share-perspective.png";

	public static final String WEB_CONTENT_TITLE = "Open Source For Life";

	public static final String XML_RESOURCE_URL =
		"open-source-for-life/open-source-for-life-web-content.xml";

	public static String[] getIconFileNames() {
		return ICON_FILE_NAMES;
	}

	public static String[] getPortletIds() {
		return PORTLET_IDS;
	}

}