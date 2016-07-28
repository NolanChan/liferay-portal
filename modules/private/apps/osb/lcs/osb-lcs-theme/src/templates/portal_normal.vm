<!DOCTYPE html>

#parse ($init)

<html class="$root_css_class" dir="#language ("lang.dir")" lang="$w3c_language_id">

<head>
	<title>$the_title - $company_name</title>

	<meta content="initial-scale=1.0, width=device-width" name="viewport" />

	$theme.include($top_head_include)
</head>

<body class="$css_class">

#if ($google_tag_manager_id != "")
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
		})(window, document, 'script', 'dataLayer', '$google_tag_manager_id');
	</script>
#end

$theme.include($body_top_include)

#if ($permissionChecker.isGroupAdmin($group_id))
	#dockbar()
#end

#if ($lcs_exclusive_page)
	<header class="lcs-header">
		#if ($is_signed_in)
			<div class="profile">
				<span class="user-avatar-image" style="background-image: url($my_account_picture);"></span>

				<span class="user-full-name">
					$htmlUtil.escape($theme_display.user.getFullName())
				</span>

				<a class="sign-out-link" href="$sign_out_url">
					#language ("sign-out")
				</a>
			</div>
		#else
			#if ($info_layout)
				<div class="profile">
					<a href="$sign_in_url">
						#language ("sign-in")
					</a>
				</div>
			#end
		#end
	</header>

	<section class="lcs-exclusive-content">
		$portletDisplay.recycle()

		$portletDisplay.setTitle($the_title)

		$theme.wrapPortlet("portlet.vm", $content_include)
	</section>
#else
	<header class="lcs-header">
		$theme.runtime('7_WAR_osblcsportlet')
	</header>

	<section class="lcs-navigation">
		$theme.runtime('4_WAR_osblcsportlet')
	</section>

	<section class="lcs-content">
		<div class="lcs-breadcrumb">
			$theme.runtime("2_WAR_osblcsportlet")
		</div>

		$theme.include($content_include)
	</section>

	<section class="lcs-feedback-bar">
		<a class="feedback-link" href="$theme.getSetting("feedback-page")">
			<i class="icon-bullhorn"></i>

			#language ("feedback")
		</a>
	</section>
#end

<footer class="lcs-footer">
	<a class="copyright" href="$theme.getSetting("liferay-home-url")" target="_blank">
		&copy; $the_year Liferay, Inc. All rights reserved.
	</a>

	<a href="$theme.getSetting("privacy-policy-url")" target="_blank">
		Privacy Policy
	</a>
</footer>

$theme.include($body_bottom_include)

$theme.include($bottom_include)

</body>

</html>