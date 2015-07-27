<#assign rootNavigationItems = navItem.fromLayouts(request, themeDisplay.getLayouts(), null) />

<#if rootNavigationItems?has_content>
	<#assign layoutLevel = 0 />

	<div class="nav-menu nav-menu-style-${bulletStyle}">
		<ul class="layouts level-${layoutLevel}">
			<#list rootNavigationItems as rootNavigationItem>
				<li class="open">
					<a href="${rootNavigationItem.getRegularURL()!""} ">${htmlUtil.escape(rootNavigationItem.getName())}</a>

					<#if rootNavigationItem.isBelongsToNavigationEntries(entries) >
						<@displayChildNavigation childLayoutLevel=(layoutLevel + 1) childNavigationItems=rootNavigationItem.getChildren() includeAllChildEntries=false />
					</#if>
				</li>
			</#list>
		</ul>
	</div>
</#if>

<#macro displayChildNavigation
	childLayoutLevel
	childNavigationItems
	includeAllChildEntries
>
	<#if childNavigationItems?has_content>
		<ul class="layouts level-${childLayoutLevel}">
			<#list childNavigationItems as childNavigationItem>
				<li class="open">
					<a href="${childNavigationItem.getRegularURL()!""} ">${htmlUtil.escape(childNavigationItem.getName())}</a>

					<#if includeAllChildEntries || childNavigationItem.isBelongsToNavigationEntries(entries) >
						<@displayChildNavigation childLayoutLevel=(childLayoutLevel + 1) childNavigationItems=childNavigationItem.getChildren() includeAllChildEntries=includeAllChildEntries/>
					</#if>
				</li>
			</#list>
		</ul>
	</#if>
</#macro>