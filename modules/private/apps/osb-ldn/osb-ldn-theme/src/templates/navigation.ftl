<#assign
	VOID = freeMarkerPortletPreferences.setValue("displayDepth", "1")
	VOID = freeMarkerPortletPreferences.setValue("displayTemplate", "list-menu")
	VOID = freeMarkerPortletPreferences.setValue("portletSetupPortletDecoratorId", "barebone")
/>

<div aria-expanded="false" class="collapse navbar-collapse" id="navigationCollapse">
	<#if has_navigation && is_setup_complete>
		<nav class="${nav_css_class} site-navigation" id="navigation" role="navigation">
			<div class="navbar-right">
				<@liferay.navigation_menu default_preferences="${freeMarkerPortletPreferences}" />
			</div>
		</nav>
	</#if>
</div>

<#assign VOID = freeMarkerPortletPreferences.reset() />