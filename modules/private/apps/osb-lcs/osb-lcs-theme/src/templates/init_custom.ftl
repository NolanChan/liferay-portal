<#assign
	feedback_page = getterUtil.getString(theme_settings["feedback_page"], "")
	google_tag_manager_id = getterUtil.getString(theme_settings["google_tag_manager_id"], "")
/>

<#assign info_layout = theme_display.getLayout().getFriendlyURL() == "/info" />
<#assign
	liferay_home_url = getterUtil.getString(theme_settings["liferay_home_url"], "")
	my_account_picture = user.getPortraitURL(theme_display)
	oauth_url = theme_display.getURLCurrent()?contains("oauth_token")
	privacy_policy_url = getterUtil.getString(theme_settings["privacy-policy-url"], "")

	root_css_class = root_css_class + " lcs"

	permission_checker = themeDisplay.getPermissionChecker()
/>

<#if permission_checker.isGroupAdmin(group_id)>
	<#assign root_css_class = root_css_class + " lcs-admin" />
</#if>

<#assign lcs_exclusive_page = false />

<#if info_layout || !is_signed_in || oauth_url || !selectable>
	<#assign
		root_css_class = root_css_class + " lcs-exclusive"
		lcs_exclusive_page = true
	/>
</#if>

<#if !is_signed_in??>
	<#assign root_css_class = root_css_class + " lcs-login" />
</#if>

<#if oauth_url>
	<#assign root_css_class = root_css_class + " lcs-oauth" />
</#if>