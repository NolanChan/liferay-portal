AUI().ready(
	'aui-base',
	function(A) {
		var BODY = A.getBody();

		var WIN = A.getWin();

		var banner = A.one('#banner');

		var lastScrollPos = 0;
		var savedScrollPos = 0;
		var triggerPos = 200;

		var displayBanner = function() {
			var scrollPos = WIN.get('docScrollY');

			if (scrollPos > 100) {
				banner.addClass('banner-fill');
			}
			else {
				banner.removeClass('banner-fill');
			}

			if (scrollPos > lastScrollPos) {
				savedScrollPos = scrollPos;
			}

			lastScrollPos = scrollPos;

			if (scrollPos < (savedScrollPos - 100)) {
				savedScrollPos = scrollPos + 100;

				BODY.removeClass('hide-banner-nav');
			}
			else if (scrollPos > triggerPos) {
				BODY.addClass('hide-banner-nav');
			}
			else {
				BODY.removeClass('hide-banner-nav');
			}
		};

		A.on(
			'scroll',
			function() {
				A.throttle(displayBanner(), 250);
			}
		);

		displayBanner();
	}
);