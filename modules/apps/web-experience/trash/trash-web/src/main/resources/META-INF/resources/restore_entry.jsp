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

<%@ include file="/init.jsp" %>

<%
PortletURL backURL = renderResponse.createRenderURL();

String redirect = ParamUtil.getString(request, "redirect", backURL.toString());

RestoreEntryException ree = (RestoreEntryException)SessionErrors.get(renderRequest, RestoreEntryException.class.getName());

TrashEntry entry = TrashEntryLocalServiceUtil.getEntry(ree.getTrashEntryId());

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);

renderResponse.setTitle(ree.getOldName());
%>

<liferay-portlet:actionURL name="restoreEntry" varImpl="restoreURL" />

<div class="alert alert-warning" id="<portlet:namespace />messageContainer">
	<liferay-ui:message arguments="<%= new String[] {HtmlUtil.escape(ree.getOldName())} %>" key="an-entry-with-name-x-already-exists" translateArguments="<%= false %>" />
</div>

<aui:form action="<%= restoreURL %>" name="restoreTrashEntryFm">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="trashEntryId" type="hidden" value="<%= ree.getTrashEntryId() %>" />
	<aui:input name="duplicateEntryId" type="hidden" value="<%= ree.getDuplicateEntryId() %>" />
	<aui:input name="oldName" type="hidden" value="<%= ree.getOldName() %>" />

	<aui:fieldset>
		<c:choose>
			<c:when test="<%= ree.isOverridable() %>">
				<aui:input checked="<%= true %>" id="override" label="overwrite-the-existing-entry-with-the-one-from-the-recycle-bin" name="<%= Constants.CMD %>" type="radio" value="<%= Constants.OVERRIDE %>" />

				<aui:input id="rename" label="keep-both-entries-and-rename-the-entry-from-the-recycle-bin-as" name="<%= Constants.CMD %>" type="radio" value="<%= Constants.RENAME %>" />
			</c:when>
			<c:otherwise>
				<aui:input id="rename" name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.RENAME %>" />
			</c:otherwise>
		</c:choose>

		<aui:input cssClass="new-file-name" label='<%= ree.isOverridable() ? StringPool.BLANK : "keep-both-entries-and-rename-the-entry-from-the-recycle-bin-as" %>' name="newName" title="keep-both-entries-and-rename-the-entry-from-the-recycle-bin-as" value="<%= TrashUtil.getNewName(themeDisplay, entry.getClassName(), entry.getClassPK(), ree.getOldName()) %>" />
	</aui:fieldset>

	<aui:button-row>
		<aui:button cssClass="btn-cancel" type="cancel" />

		<aui:button type="submit" />
	</aui:button-row>
</aui:form>