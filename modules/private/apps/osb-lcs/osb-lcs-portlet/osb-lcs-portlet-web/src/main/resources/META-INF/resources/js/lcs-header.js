AUI.add(
	'lcs-header',
	function(A) {
		var CSS_LCS_MESSAGE = 'lcs-message';

		var CSS_OPEN = 'open';

		var STR_CLICK = 'click';

		var STR_DOT = '.';

		var STR_HREF = 'href';

		var SELECTOR_LCS_MESSAGE = STR_DOT + CSS_LCS_MESSAGE;

		var TPL_UPDATING = '<li class="updating-container">' +
				'<div class="updating"></div>' +
			'</li>';

		var LCSHeader = A.Component.create(
			{
				AUGMENTS: [Liferay.LCSBase, Liferay.PortletBase],

				EXTENDS: A.Base,

				NAME: 'lcs-header',

				prototype: {
					initializer: function(config) {
						var instance = this;

						instance._urls = config.urls;
						instance._userLCSMessagesCount = config.userLCSMessagesCount;

						var lcsMessagesMenu = instance.byId('lcsMessagesMenu');
						var lcsMessagesToggler = instance.byId('lcsMessagesToggler');
						var profileContainer = instance.byId('profile');
						var profileMenu = instance.byId('profileMenu');

						instance._userLCSMessagesCountContainer = instance.byId('userLCSMessagesCount');

						if (lcsMessagesMenu && lcsMessagesToggler && profileContainer && profileMenu) {
							lcsMessagesToggler.on(
								STR_CLICK,
								function(event) {
									event.stopPropagation();

									lcsMessagesToggler.toggleClass(CSS_OPEN);
									lcsMessagesMenu.toggleClass(CSS_OPEN);

									profileContainer.removeClass(CSS_OPEN);
									profileMenu.removeClass(CSS_OPEN);

									if (!instance._lcsMessagesReady) {
										instance._getUserLCSMessages(
											{}
										);
									}
								}
							);

							profileContainer.on(
								STR_CLICK,
								function(event) {
									event.stopPropagation();

									lcsMessagesToggler.removeClass(CSS_OPEN);
									lcsMessagesMenu.removeClass(CSS_OPEN);

									profileContainer.toggleClass(CSS_OPEN);
									profileMenu.toggleClass(CSS_OPEN);
								}
							);

							A.on(
								STR_CLICK,
								function(event) {
									lcsMessagesToggler.removeClass(CSS_OPEN);
									lcsMessagesMenu.removeClass(CSS_OPEN);

									profileContainer.removeClass(CSS_OPEN);
									profileMenu.removeClass(CSS_OPEN);
								}
							);
						}

						if (lcsMessagesMenu) {
							lcsMessagesMenu.on(
								STR_CLICK,
								function(event) {
									event.stopPropagation();
								}
							);

							lcsMessagesMenu.delegate(
								STR_CLICK,
								function(event) {
									var currentTarget = event.currentTarget;

									var lcsMessageContainer = currentTarget.ancestor(SELECTOR_LCS_MESSAGE);

									if (lcsMessageContainer) {
										instance._getUserLCSMessages(
											{
												hideUserLCSMessageId: lcsMessageContainer.attr('data-userlcsmessageid')
											}
										);
									}
								},
								'.options .lcs-message-hide'
							);

							new A.TooltipDelegate(
								{
									container: lcsMessagesMenu,
									position: 'left',
									trigger: '.lcs-message-hide'
								}
							);
						}

						instance._lcsMessagesMenu = lcsMessagesMenu;

						var logoLink = instance.byId('logo');

						var navigationDashboardLink = A.one('.lcs-navigation-header .lcs-dashboard-link');

						if (logoLink && navigationDashboardLink) {
							var dashboardURL = navigationDashboardLink.attr(STR_HREF);

							logoLink.attr(STR_HREF, dashboardURL);
						}
					},

					_getUserLCSMessages: function(config) {
						var instance = this;

						var hideUserLCSMessageId = config.hideUserLCSMessageId;

						var data = {
							hideUserLCSMessageId: hideUserLCSMessageId || 0,
							hideUserLCSMessages: config.hideUserLCSMessages || false,
							showUserLCSMessages: config.showUserLCSMessages || false
						};

						var lcsMessagesMenu = instance._lcsMessagesMenu;

						lcsMessagesMenu.html(TPL_UPDATING);

						A.io.request(
							instance._urls.getUserLCSMessages,
							{
								data: instance.ns(data),
								on: {
									failure: function(event, id, obj) {
										instance._showMessage(
											{
												container: lcsMessagesMenu,
												message: instance._errorMessage,
												type: 'error'
											}
										);
									},
									success: function(event, id, obj) {
										var responseData = this.get('responseData');

										lcsMessagesMenu.html(responseData);

										if (hideUserLCSMessageId) {
											var userLCSMessagesCountContainer = instance._userLCSMessagesCountContainer;

											if (userLCSMessagesCountContainer) {
												var userLCSMessagesCount = instance._userLCSMessagesCount;

												userLCSMessagesCount--;

												if (userLCSMessagesCount > 0) {
													userLCSMessagesCountContainer.html(userLCSMessagesCount);
												}
												else {
													userLCSMessagesCountContainer.hide();
												}

												instance._userLCSMessagesCount = userLCSMessagesCount;
											}
										}

										var hideUserLCSMessagesLink = instance.byId('hideUserLCSMessages');

										if (hideUserLCSMessagesLink) {
											hideUserLCSMessagesLink.on(
												STR_CLICK,
												function(event) {
													instance._getUserLCSMessages(
														{
															hideUserLCSMessages: true
														}
													);

													var userLCSMessagesCountContainer = instance._userLCSMessagesCountContainer;

													if (userLCSMessagesCountContainer) {
														userLCSMessagesCountContainer.hide();
													}
												}
											);
										}

										var showUserLCSMessagesLink = instance.byId('showUserLCSMessages');

										if (showUserLCSMessagesLink) {
											showUserLCSMessagesLink.on(
												STR_CLICK,
												function(event) {
													instance._getUserLCSMessages(
														{
															showUserLCSMessages: true
														}
													);
												}
											);
										}

										instance._lcsMessagesReady = true;
									}
								}
							}
						);
					}
				}
			}
		);

		Liferay.Portlet.LCSHeader = LCSHeader;
	},
	'',
	{
		requires: ['aui-node', 'lcs-base', 'liferay-portlet-base']
	}
);