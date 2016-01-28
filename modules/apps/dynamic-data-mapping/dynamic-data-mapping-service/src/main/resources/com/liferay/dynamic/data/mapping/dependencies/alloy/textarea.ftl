<#include "../init.ftl">

<@liferay_aui["field-wrapper"] data=data>
	<div class="form-group">
		<@liferay_aui.input cssClass=cssClass dir=requestedLanguageDir helpMessage=escape(fieldStructure.tip) label=escape(label) name=namespacedFieldName placeholder=fieldName required=required type="textarea" value=fieldValue>
			<#if required>
				<@liferay_aui.validator name="required" />
			</#if>
		</@liferay_aui.input>
	</div>

	${fieldStructure.children}
</@>