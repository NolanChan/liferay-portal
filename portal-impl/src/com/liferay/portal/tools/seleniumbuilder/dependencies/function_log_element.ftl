<#if testCaseName??>
	selenium
<#else>
	liferaySelenium
</#if>

.sendActionLogger(

<#if functionElement.getName() == "execute">
	<#assign function = functionElement.attributeValue("function")>

	<#assign x = function?last_index_of("#")>

	<#assign functionCommand = function?substring(x + 1)>

	<#assign functionPathName = seleniumBuilderFileUtil.getObjectName(function?substring(0, x))>

	"${seleniumBuilderFileUtil.getVariableName(function?substring(0, x))}Function#${functionCommand}",

	new String[] {

	<#list 1..seleniumBuilderContext.getFunctionLocatorCount(functionPathName) as i>
		"",

		<#if functionElement.attributeValue("locator${i}")??>
			<#assign fullFunctionPath = functionElement.attributeValue("locator${i}")>

			<#if fullFunctionPath?contains("#")>
				<#assign y = fullFunctionPath?last_index_of("#")>

				<#assign functionPath = fullFunctionPath?substring(0, y)>
				<#assign functionPathLocator = fullFunctionPath?substring(y + 1)>

				RuntimeVariables.evaluateVariable(${functionPath}Path.getPathLocator("${seleniumBuilderFileUtil.escapeJava(functionPathLocator)}"), ${variableContext})
			<#else>
				RuntimeVariables.evaluateVariable("${seleniumBuilderFileUtil.escapeJava(fullFunctionPath)}", ${variableContext})
			</#if>
		<#else>
			""
		</#if>

		,

		<#if functionElement.attributeValue("value${i}")??>
			<#assign functionValue = functionElement.attributeValue("value${i}")>

			RuntimeVariables.evaluateVariable("${seleniumBuilderFileUtil.escapeJava(functionValue)}", ${variableContext})
		<#else>
			""
		</#if>

		<#if i_has_next>
			,
		</#if>
	</#list>

	})

	<#if functionElement.getName() == "execute">
		;
	</#if>
<#elseif functionElement.getName() == "echo" || functionElement.getName() == "fail">
	"${functionElement.getName()} message: \"" + RuntimeVariables.evaluateVariable("${message}", ${variableContext}) + "\"", new String[] {"", "", ""});
</#if>