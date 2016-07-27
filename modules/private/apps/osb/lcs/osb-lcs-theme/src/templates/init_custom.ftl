#set ($google_tag_manager_id = $theme.getSetting("google-tag-manager-id"))
#set ($info_layout = $theme_display.getLayout().getFriendlyURL() == "/info")
#set ($my_account_picture = $theme_display.user.getPortraitURL($theme_display))
#set ($oauth_url = $theme_display.getURLCurrent().contains("oauth_token"))

#set ($root_css_class = $root_css_class + " lcs")

#if ($permissionChecker.isGroupAdmin($group_id))
	#set ($root_css_class = $root_css_class + " lcs-admin")
#end

#if (!$is_signed_in || !$selectable || $oauth_url || $info_layout)
	#set ($root_css_class = $root_css_class + " lcs-exclusive")
	#set ($lcs_exclusive_page = true)
#end

#if (!$is_signed_in)
	#set ($root_css_class = $root_css_class + " lcs-login")
#end

#if ($oauth_url)
	#set ($root_css_class = $root_css_class + " lcs-oauth")
#end