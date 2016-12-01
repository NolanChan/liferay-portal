<div aria-expanded="false" class="collapse navbar-collapse" id="navigationCollapse">
	<#if has_navigation && is_setup_complete>
		<nav class="${nav_css_class} site-navigation" id="navigation" role="navigation">
			<div class="navbar-right">
				<#assign
					guestGroup = group_service.getGroup(portalUtil.getDefaultCompanyId(), "Guest")

					navigation_layouts = layout_service.getLayouts(guestGroup.getGroupId(), false, "portlet")
				/>

				<ul class="layouts">
					<#list navigation_layouts as navigation_layout>
						<#if navigation_layouts.getLayoutId() == 1>
							<li class="lfr-nav-item">
								<a href="${navigation_layout.getFriendlyURL(locale)}" class="lfr-nav-item">${navigation_layout.getHTMLTitle(locale)}</a>
							</li>
						</#if>
					</#list>
				</ul>
			</div>
		</nav>
	</#if>
</div>

<#assign VOID = freeMarkerPortletPreferences.reset() />