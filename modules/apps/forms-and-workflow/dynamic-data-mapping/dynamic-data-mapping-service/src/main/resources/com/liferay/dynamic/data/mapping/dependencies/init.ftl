<#-- Tag libraries -->

<#assign fmt = PortalJspTagLibs["/WEB-INF/tld/fmt.tld"] />

<#-- CSS class -->

<#assign cssClass = "" />

<#if fieldStructure.width??>
	<#if fieldStructure.width == "large">
		<#assign cssClass = "input-large" />
	<#elseif fieldStructure.width == "medium">
		<#assign cssClass = "input-medium" />
	<#elseif fieldStructure.width == "small">
		<#assign cssClass = "input-small" />
	</#if>
</#if>

<#-- Repeatable -->

<#assign repeatable = false />

<#if fieldStructure.repeatable?? && (fieldStructure.repeatable == "true") && (!ignoreRepeatable?? || !ignoreRepeatable)>
	<#assign repeatable = true />
</#if>

<#-- Field name -->

<#assign
	fieldNamespace = "_INSTANCE_" + fieldStructure.fieldNamespace

	fieldName = fieldStructure.name

	parentName = parentFieldStructure.name!""
	parentType = parentFieldStructure.type!""

	isChildField = parentName?? && (parentName != "") && ((parentType == "radio") || (parentType == "select"))
/>

<#if isChildField>
	<#assign fieldName = parentName />
</#if>

<#assign
	namespace = namespace!""

	namespacedFieldName = "${namespace}${fieldName}${fieldNamespace}"

	namespacedParentName = "${namespace}${parentName}"
/>

<#-- Data -->

<#assign data = {
	"fieldName": fieldStructure.name,
	"fieldNamespace": fieldNamespace,
	"repeatable": repeatable?string
}>

<#-- Predefined value -->

<#assign predefinedValue = fieldStructure.predefinedValue!"" />

<#if isChildField>
	<#assign predefinedValue = parentFieldStructure.predefinedValue!"" />
</#if>

<#-- Field value -->

<#assign
	fieldValue = predefinedValue
	fieldRawValue = ""
	hasFieldValue = false
/>

<#if fields?? && fields.get(fieldName)??>
	<#assign
		field = fields.get(fieldName)

		valueIndex = getterUtil.getInteger(fieldStructure.valueIndex)

		fieldValue = field.getRenderedValue(requestedLocale, valueIndex)
		fieldRawValue = field.getValue(requestedLocale, valueIndex)!
	/>

	<#if fieldValue != "">
		<#assign hasFieldValue = true />
	</#if>
</#if>

<#-- Disabled -->

<#assign disabled = false />

<#if fieldStructure.disabled?? && (fieldStructure.disabled == "true")>
	<#assign disabled = true />
</#if>

<#-- Label -->

<#assign label = fieldStructure.label!"" />

<#if fieldStructure.showLabel?? && (fieldStructure.showLabel == "false")>
	<#assign label = "" />
</#if>

<#-- Required -->

<#assign required = false />

<#if fieldStructure.required?? && (fieldStructure.required == "true")>
	<#assign required = true />
</#if>

<#-- Util -->

<#assign authTokenUtil = serviceLocator.findService("com.liferay.portal.kernel.security.auth.AuthTokenUtil") />

<#function escape value="">
	<#if value?is_string>
		<#return htmlUtil.escape(value)>
	<#else>
		<#return value>
	</#if>
</#function>

<#function escapeAttribute value="">
	<#if value?is_string>
		<#return htmlUtil.escapeAttribute(value)>
	<#else>
		<#return value>
	</#if>
</#function>

<#function escapeCSS value="">
	<#if value?is_string>
		<#return htmlUtil.escapeCSS(value)>
	<#else>
		<#return value>
	</#if>
</#function>

<#function escapeJS value="">
	<#if value?is_string>
		<#return htmlUtil.escapeJS(value)>
	<#else>
		<#return value>
	</#if>
</#function>

<#assign dlAppServiceUtil = serviceLocator.findService("com.liferay.document.library.kernel.service.DLAppService") />

<#function getFileEntry fileJSONObject>
	<#assign fileEntryUUID = fileJSONObject.getString("uuid") />

	<#if fileJSONObject.getLong("groupId") gt 0>
		<#assign fileEntryGroupId = fileJSONObject.getLong("groupId") />
	<#else>
		<#assign fileEntryGroupId = scopeGroupId />
	</#if>

	<#return dlAppServiceUtil.getFileEntryByUuidAndGroupId(fileEntryUUID, fileEntryGroupId)!"">
</#function>

<#function getFileEntryURL fileEntry>
	<#return themeDisplay.getPathContext() + "/documents/" + fileEntry.getRepositoryId()?c + "/" + fileEntry.getFolderId()?c + "/" +  httpUtil.encodeURL(htmlUtil.unescape(fileEntry.getTitle()), true) + "/" + fileEntry.getUuid()>
</#function>

<#function getFileJSONObject fieldValue>
	<#return jsonFactoryUtil.createJSONObject(fieldValue)>
</#function>

<#assign journalArticleLocalService = serviceLocator.findService("com.liferay.journal.service.JournalArticleLocalService") />

<#function fetchLatestArticle journalArticleJSONObject>
	<#assign resourcePrimKey = journalArticleJSONObject.getLong("classPK") />

	<#return journalArticleLocalService.fetchLatestArticle(resourcePrimKey)!"">
</#function>

<#-- Token -->

<#assign ddmAuthToken = authTokenUtil.getToken(request, themeDisplay.getPlid(), "com_liferay_dynamic_data_mapping_web_portlet_DDMPortlet") />

<#assign data = data + {
	"ddmAuthToken": ddmAuthToken
}>