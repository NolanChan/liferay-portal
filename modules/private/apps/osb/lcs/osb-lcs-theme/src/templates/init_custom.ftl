<#assign feedback_page = getterUtil.getString(theme_settings["feedback_page"], "") />
<#assign google_tag_manager_id = getterUtil.getString(theme_settings["google_tag_manager_id"], "") />
<#assign info_layout = theme_display.getLayout().getFriendlyURL() == "/info" />
<#assign liferay_home_url = getterUtil.getString(theme_settings["liferay_home_url"], "") />
<#assign my_account_picture = user.getPortraitURL(theme_display) />
<#assign oauth_url = theme_display.getURLCurrent()?contains("oauth_token") />
<#assign privacy_policy_url = getterUtil.getString(theme_settings["privacy-policy-url"], "") />

<#assign root_css_class = root_css_class + " lcs" />

<#assign permission_checker = themeDisplay.getPermissionChecker() />

<#if permission_checker.isGroupAdmin(group_id)>
	<#assign root_css_class = root_css_class + " lcs-admin" />
</#if>

<#assign lcs_exclusive_page = false />

<#if !is_signed_in || !selectable || oauth_url || info_layout>
	<#assign root_css_class = root_css_class + " lcs-exclusive" />
	<#assign lcs_exclusive_page = true />
</#if>

<#if !is_signed_in??>
	<#assign root_css_class = root_css_class + " lcs-login" />
</#if>

<#if oauth_url>
	<#assign root_css_class = root_css_class + " lcs-oauth" />
</#if>