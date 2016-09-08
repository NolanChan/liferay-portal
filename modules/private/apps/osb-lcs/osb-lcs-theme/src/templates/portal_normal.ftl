<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
	<title>${the_title} - ${company_name}</title>

	<meta content="initial-scale=1.0, width=device-width" name="viewport" />

	<@liferay_util["include"] page=top_head_include />
</head>

<body class="${css_class}">

<#if google_tag_manager_id != "">
	<script>
		(function(w, d, s, l, i) {
			w[l] = w[l] || [];

			w[l].push(
				{
					event: 'gtm.js',
					'gtm.start': new Date().getTime()
				}
			);

			var dl = l != 'dataLayer' ? '&l=' + l : '';
			var f = d.getElementsByTagName(s)[0];
			var j = d.createElement(s);

			j.async = true;
			j.src = '//www.googletagmanager.com/gtm.js?id=' + i + dl;

			f.parentNode.insertBefore(j, f);
		})(window, document, 'script', 'dataLayer', '${google_tag_manager_id}');
	</script>
</#if>

<@liferay_util["include"] page=body_top_include />

<#if permission_checker.isGroupAdmin(group_id)>
	<@liferay.control_menu />
</#if>

<#if lcs_exclusive_page>
	<header class="lcs-header">
		<#if is_signed_in>
			<div class="profile">
				<span class="user-avatar-image" style="background-image: url(${my_account_picture});"></span>

				<span class="user-full-name">
					${htmlUtil.escape(user_name)}
				</span>

				<a class="sign-out-link" href="${sign_out_url}">
					${sign_out_text}
				</a>
			</div>
		<#else>
			<#if info_layout>
				<div class="profile">
					<a href="${sign_in_url!""}">
						${sign_in_text!""}
					</a>
				</div>
			</#if>
		</#if>
	</header>

	<section class="lcs-exclusive-content">
		${portletDisplay.recycle()}

		${portletDisplay.setTitle(the_title)}

		<@liferay_theme["wrap-portlet"] page="portlet.ftl">
			<@liferay_util["include"] page=content_include />
		</@>
	</section>
<#else>
	<header class="lcs-header">
		<@liferay_portlet["runtime"] portletName="7_WAR_osblcsportlet" />
	</header>

	<section class="lcs-navigation">
		<@liferay_portlet["runtime"] portletName="4_WAR_osblcsportlet" />
	</section>

	<section class="lcs-content">
		<div class="lcs-breadcrumb">
			<@liferay_portlet["runtime"] portletName="2_WAR_osblcsportlet" />
		</div>

		<@liferay_util["include"] page=content_include />
	</section>

	<section class="lcs-feedback-bar">
		<a class="feedback-link" href="${feedback_page}">
			<i class="icon-bullhorn"></i>

			<@liferay.language key="feedback" />
		</a>
	</section>
</#if>

<footer class="lcs-footer">
	<a class="copyright" href="${liferay_home_url}" target="_blank">
		&copy; ${the_year} Liferay, Inc. All rights reserved.
	</a>

	<a href="${privacy_policy_url}" target="_blank">
		Privacy Policy
	</a>
</footer>

<@liferay_util["include"] page=body_bottom_include />

<@liferay_util["include"] page=bottom_include />

</body>

</html>