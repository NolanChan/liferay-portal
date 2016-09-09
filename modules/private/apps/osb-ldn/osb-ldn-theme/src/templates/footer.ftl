<footer id="footer" role="contentinfo">
	<div class="container-fluid-1280">
		<div class="social-nav">
			<#assign footer_icons_path = "${images_folder}/custom/footer_icons.svg" />

			<ul class="list-inline">
				<li>
					<a class="facebook" href="//www.facebook.com/pages/Liferay/45119213107" target="_blank" title='<@liferay.language key="facebook" />'>
						<svg><use xlink:href="${footer_icons_path}#facebookSocialIcon"></use></svg>
					</a>
				</li>
				<li>
					<a class="github" href="//github.com/liferay" target="_blank" title='<@liferay.language key="github" />'>
						<svg><use xlink:href="${footer_icons_path}#githubSocialIcon"></use></svg>
					</a>
				</li>
				<li>
					<a class="instagram" href="//www.instagram.com/liferayinc/" target="_blank" title='<@liferay.language key="instagram" />'>
						<svg><use xlink:href="${footer_icons_path}#instagramSocialIcon"></use></svg>
					</a>
				</li>
				<li>
					<a class="linkedin" href="//www.linkedin.com/company/83609?trk=NUS_CMPY_TWIT" target="_blank" title='<@liferay.language key="linkedin" />'>
						<svg><use xlink:href="${footer_icons_path}#linkedinSocialIcon"></use></svg>
					</a>
				</li>
				<li>
					<a class="twitter" href="//www.twitter.com/liferay" target="_blank" title='<@liferay.language key="twitter" />'>
						<svg><use xlink:href="${footer_icons_path}#twitterSocialIcon"></use></svg>
					</a>
				</li>
				<li>
					<a class="youtube" href="//www.youtube.com/user/liferayinc" target="_blank" title='<@liferay.language key="youtube" />'>
						<svg><use xlink:href="${footer_icons_path}#youtubeSocialIcon"></use></svg>
					</a>
				</li>
			</ul>
		</div>

		<div class="fine-print">
			<p class="powered-by">
				<@liferay.language key="powered-by" /> <a href="http://www.liferay.com" rel="external">Liferay</a>
			</p>

			<p class="copyright">
				&copy; ${the_year} Liferay Inc.
			</p>
		</div>
	</div>
</footer>