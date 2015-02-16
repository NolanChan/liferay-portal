<%--
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
--%>

<%@ include file="/html/taglib/ui/language/init.jsp" %>

<%
for (int i = 0; i < locales.length; i++) {
	String currentLanguageId = LocaleUtil.toLanguageId(locales[i]);

	if (!displayCurrentLocale && languageId.equals(currentLanguageId)) {
		continue;
	}

	boolean currentLanguage = languageId.equals(currentLanguageId);
%>

	<liferay-ui:icon
		cssClass='<%= currentLanguage ? "current-language" : "" %>'
		image='<%= "../language/" + currentLanguageId %>'
		lang="<%= LocaleUtil.toW3cLanguageId(locales[i]) %>"
		message="<%= LocaleUtil.getLongDisplayName(locales[i], duplicateLanguages) %>"
		url="<%= currentLanguage ? null : HttpUtil.setParameter(formAction, name, currentLanguageId) %>"
	/>

<%
}
%>