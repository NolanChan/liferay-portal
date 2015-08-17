<#include "${templatesPath}/NAVIGATION-MACRO-FTL" />

<#if entries?has_content>
	<#assign rootNavigationItem = entries?first />

	<div class="nav-menu nav-menu-style-${bulletStyle}">
		<h2>
			<a

			<#if rootNavigationItem.isBrowsable()>
		   		href="${rootNavigationItem.getRegularURL()!""} " ${rootNavigationItem.getTarget()}
			</#if>

			>${rootNavigationItem.getName()}</a>
		</h2>

		<@displayChildNavigation childLayoutLevel=1 childNavigationItems=rootNavigationItem.getChildren() includeAllChildEntries=false />
	</div>
</#if>