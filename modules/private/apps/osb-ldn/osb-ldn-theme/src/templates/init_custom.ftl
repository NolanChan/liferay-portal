<#assign
	group_service = objectUtil("com.liferay.portal.kernel.service.GroupLocalServiceUtil")

	layout_service = objectUtil("com.liferay.portal.kernel.service.LayoutLocalServiceUtil")

	light_navigation = getterUtil.getBoolean(themeDisplay.getThemeSetting("light-navigation"))
/>

<#if light_navigation == true>
	<#assign css_class = css_class + " light-navigation" />
</#if>