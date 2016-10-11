AUI.add(
	'lcs-members',
	function(A) {
		var Lang = A.Lang;
		var Util = Liferay.Util;

		var CSS_DIALOG = 'lcs-dialog';

		var CSS_DRAGGING_ORIGINAL = 'lcs-dragging-original';

		var CSS_REMOTE_USER_AVATAR_IMG = '.user-avatar-image';

		var CSS_USER_CONTAINER = '.lcs-user-wrapper';

		var EVENT_CHANGE = 'change';

		var EVENT_CLICK = 'click';

		var STR_CHECKED = 'checked';

		var STR_CONFIRM_INVITE_BUTTON = 'confirmInviteButton';

		var STR_DISABLED = 'disabled';

		var STR_ERROR = 'error';

		var STR_JSON = 'json';

		var STR_NODE = 'node';

		var STR_RESPONSE_DATA = 'responseData';

		var STR_SUCCESS = 'success';

		var STR_TR = 'tr';

		var TPL_USER = '<div class="field field-wrapper lcs-user-wrapper">' +
				'<div class="field-wrapper-content">' +
					'<input class="lcs-user-id" name="{portletNamespace}userId" type="hidden" value="{userId}">' +
					'{userPortraitHtml}' +
					'<span class="lcs-user-name">{userName}</span>' +
					'<span class="icon-remove lcs-cancel-user"></span>' +
				'</div>' +
			'</div>';

		var LCSMembers = A.Component.create(
			{
				AUGMENTS: [Liferay.LCSBase, Liferay.PortletBase],

				EXTENDS: A.Base,

				NAME: 'lcs-members',

				prototype: {
					initializer: function(config) {
						var instance = this;

						instance._lcsAdministratorRole = config.lcsAdministratorRole;
					},

					initializeManageRolesPage: function(config) {
						var instance = this;

						var form = instance.byId('fm');
						var roleSelect = instance.byId('role');

						if (form) {
							form.on(
								'submit',
								function(event) {
									if (roleSelect) {
										var role = roleSelect.val();

										var environmentRole = form.one('.lcs-environment-role');

										if (!environmentRole || (role != instance._lcsAdministratorRole) || confirm(config.msgConfirmAssignAdministratorRole)) {
											submitForm(form);
										}
									}
								}
							);
						}

						if (roleSelect) {
							roleSelect.on(EVENT_CHANGE, instance._onSelectRole, instance);
						}
					},

					initializeViewPage: function(config) {
						var instance = this;

						instance._assignRolesPanelTitle = config.assignRolesPanelTitle;
						instance._assignRolesURL = config.assignRolesURL;
						instance._cancelInvitationURL = config.cancelInvitationURL;
						instance._invitePanelTitle = config.invitePanelTitle;
						instance._inviteURL = config.inviteURL;
						instance._msgConfirmCancelInvitation = config.msgConfirmCancelInvitation;
						instance._msgConfirmRejectRequests = config.msgConfirmRejectRequests;
						instance._rejectRequestsURL = config.rejectRequestsURL;
						instance._renderAssignRolesPanelURL = config.renderAssignRolesPanelURL;
						instance._renderInvitePanelURL = config.renderInvitePanelURL;

						var assignRolesButton = instance.byId('assignRolesButton');
						var inviteButton = instance.byId('inviteButton');
						var membersContainer = instance.byId('members');

						if (assignRolesButton) {
							assignRolesButton.on(EVENT_CLICK, instance._createAssignRolesPanel, instance);
						}

						if (inviteButton) {
							inviteButton.on(EVENT_CLICK, instance._createInvitePanel, instance);
						}

						var portlet = A.one('.osb-lcs-portlet-members');

						portlet.delegate(
							EVENT_CLICK,
							function(event) {
								var link = event.currentTarget;

								var lcsInvitationId = link.attr('data-lcsinvitationid');
								var row = link.ancestor(STR_TR);

								if (confirm(instance._msgConfirmCancelInvitation)) {
									instance._cancelInvitation(
										{
											lcsInvitationId: lcsInvitationId,
											row: row
										}
									);
								}
							},
							'.cancel-invitation a'
						);

						if (membersContainer) {
							instance._initializeSelectableRows(
								{
									container: membersContainer
								}
							);
						}
					},

					_cancelInvitation: function(config) {
						var instance = this;

						var messageContainer = A.one('.invitations-message-container');

						var errorMessage = instance._errorMessage;

						A.io.request(
							instance._cancelInvitationURL,
							{
								data: instance.ns(
									{
										lcsInvitationId: config.lcsInvitationId
									}
								),
								dataType: STR_JSON,
								on: {
									failure: function(event, id, obj) {
										instance._showMessage(
											{
												container: messageContainer,
												message: errorMessage,
												type: STR_ERROR
											}
										);
									},
									success: function(event, id, obj) {
										var responseData = this.get(STR_RESPONSE_DATA);

										var lcsConstants = instance._lcsConstants;

										if (responseData[lcsConstants.JSON_KEY_RESULT] == lcsConstants.JSON_VALUE_SUCCESS) {
											var row = config.row;

											var table = row.ancestor();

											var rows = table.all(STR_TR);

											if (rows.size() > 2) {
												row.remove();
											}
											else {
												location.reload();
											}
										}
										else {
											instance._showMessage(
												{
													container: messageContainer,
													message: errorMessage,
													type: STR_ERROR
												}
											);
										}
									}
								}
							}
						);
					},

					_cancelUserSelection: function(event) {
						var instance = this;

						var selectedUserContainer = event.target.ancestor(CSS_USER_CONTAINER);

						var selectedUserIdNode = selectedUserContainer.one('.lcs-user-id');

						var selectedUserId = selectedUserIdNode.val();

						var remoteUsersPanel = instance._remoteUsersPanel;

						var remoteUsersCheckBoxes = remoteUsersPanel.all('input:checkbox');

						remoteUsersCheckBoxes.some(
							function(item, index, collection) {
								var userFound = (selectedUserId === item.val());

								if (userFound) {
									item.attr(STR_DISABLED, false);
									item.attr(STR_CHECKED, false);

									selectedUserContainer.remove(true);

									var remoteUserContainer = item.ancestor(CSS_USER_CONTAINER);

									remoteUserContainer.toggle();

									instance._selectAllRemoteUsersCheckBox.attr(STR_CHECKED, false);
								}

								return userFound;
							}
						);
					},

					_createAssignRolesPanel: function() {
						var instance = this;

						var assignRolesPanel = instance._assignRolesPanel;

						if (!assignRolesPanel) {
							assignRolesPanel = Liferay.Util.Window.getWindow(
								{
									dialog: {
										cssClass: CSS_DIALOG,
										resizable: false,
										visible: false,
										width: 630
									},
									title: instance._assignRolesPanelTitle
								}
							).render();

							assignRolesPanel.plug(
								A.Plugin.IO,
								{
									autoLoad: false,
									uri: instance._renderAssignRolesPanelURL
								}
							);

							instance._assignRolesPanel = assignRolesPanel;

							assignRolesPanel.io.after(STR_SUCCESS, instance._initializeAssignRolesPanel, instance);

							assignRolesPanel.io.start();
						}
						else {
							assignRolesPanel.show();
						}
					},

					_createInvitePanel: function() {
						var instance = this;

						var invitePanel = instance._invitePanel;

						if (!invitePanel) {
							invitePanel = Liferay.Util.Window.getWindow(
								{
									dialog: {
										cssClass: CSS_DIALOG,
										resizable: false,
										visible: false
									},
									title: instance._invitePanelTitle
								}
							).render();

							invitePanel.plug(
								A.Plugin.IO,
								{
									autoLoad: false,
									uri: instance._renderInvitePanelURL
								}
							);

							instance._invitePanel = invitePanel;

							invitePanel.io.after(STR_SUCCESS, instance._initializeInvitePanel, instance);

							invitePanel.io.start();
						}
						else {
							invitePanel.show();
						}
					},

					_createUserNode: function(config) {
						var instance = this;

						var userNodeHtml = Lang.sub(
							TPL_USER,
							{
								portletNamespace: instance.NS,
								userId: config.userId,
								userName: config.userName,
								userPortraitHtml: config.userPortraitHtml
							}
						);

						return A.Node.create(userNodeHtml);
					},

					_initializeAssignRolesPanel: function() {
						var instance = this;

						var assignRolesPanel = instance._assignRolesPanel;

						var addButton = instance.byId('addButton');
						var assignRolesButton = instance.byId('assignRolesButton');
						var assignRolesForm = instance.byId('assignRolesFm');
						var cancelButton = instance.byId('cancelButton');
						var rejectRequestsButton = instance.byId('rejectRequestsButton');
						var roleSelect = instance.byId('role');
						var searchUsersInput = instance.byId('searchUsers');
						var selectAllRemoteUsersCheckBox = instance.byId('selectAllUsersCheckbox');

						var remoteUsersPanel = assignRolesForm.one('.lcs-scroll-panel-remote');
						var selectedUsersColumn = assignRolesForm.one('.lcs-selected-users-column');
						var selectedUsersPanel = assignRolesForm.one('.lcs-scroll-panel-selected');

						var remoteUserContainers = remoteUsersPanel.all(CSS_USER_CONTAINER);

						addButton.on(EVENT_CLICK, instance._moveToSelectedUsersPanel, instance);

						assignRolesButton.on(
							EVENT_CLICK,
							function(event) {
								instance._submitForm(
									{
										form: assignRolesForm,
										url: instance._assignRolesURL
									}
								);
							}
						);

						cancelButton.on(
							EVENT_CLICK,
							function() {
								assignRolesPanel.hide();
							}
						);

						rejectRequestsButton.on(
							EVENT_CLICK,
							function(event) {
								if (confirm(instance._msgConfirmRejectRequests)) {
									instance._submitForm(
										{
											form: assignRolesForm,
											url: instance._rejectRequestsURL
										}
									);
								}
							}
						);

						remoteUsersPanel.delegate(
							EVENT_CLICK,
							function(event) {
								Util.checkAllBox(
									assignRolesForm,
									instance.NS + 'remoteUserIdCheckbox',
									selectAllRemoteUsersCheckBox
								);
							},
							'input:checkbox'
						);

						instance._remoteUsersPanel = remoteUsersPanel;

						roleSelect.on(EVENT_CHANGE, instance._onSelectRole, instance);

						selectAllRemoteUsersCheckBox.on(
							EVENT_CLICK,
							function(event) {
								var checkBoxes = remoteUsersPanel.all('input:checkbox');

								var checkBoxState = event.target.attr(STR_CHECKED);

								if (!checkBoxState) {
									checkBoxes = checkBoxes.filter(
										function(item, index, collection) {
											return !item.attr(STR_DISABLED);
										}
									);
								}

								checkBoxes.attr(STR_CHECKED, checkBoxState);
							}
						);

						instance._selectAllRemoteUsersCheckBox = selectAllRemoteUsersCheckBox;

						selectedUsersPanel.delegate(EVENT_CLICK, instance._cancelUserSelection, '.lcs-cancel-user', instance);

						instance._selectedUsersPanel = selectedUsersPanel;

						instance._selectedUsersColumn = selectedUsersColumn;

						instance._initializeDragDropMembers();

						new A.LiveSearch(
							{
								input: searchUsersInput,
								nodes: remoteUserContainers
							}
						);

						assignRolesPanel._syncUIPosAlign();

						assignRolesPanel.show();
					},

					_initializeDragDropMembers: function() {
						var instance = this;

						var dragDelegate = new A.DD.Delegate(
							{
								container: instance._remoteUsersPanel,
								nodes: '.lcs-user'
							}
						);

						dragDelegate.dd.plug(
							A.Plugin.DDProxy,
							{
								borderStyle: false,
								moveOnEnd: false
							}
						);

						dragDelegate.dd.addHandle(CSS_REMOTE_USER_AVATAR_IMG);

						new A.DD.Drop(
							{
								node: instance._selectedUsersColumn
							}
						);

						dragDelegate.on(
							'drag:start',
							function(event) {
								var originalNode = event.target.get(STR_NODE);
								var proxyNode = event.target.get('dragNode');

								proxyNode.html(originalNode.html());

								originalNode.addClass(CSS_DRAGGING_ORIGINAL);

								var userContainer = originalNode.ancestor(CSS_USER_CONTAINER);

								var userCheckBox = userContainer.one('input:checkbox');

								userCheckBox.attr(STR_CHECKED, true);
							}
						);

						dragDelegate.on(
							'drag:end',
							function(event) {
								var originalNode = event.target.get(STR_NODE);

								originalNode.removeClass(CSS_DRAGGING_ORIGINAL);
							}
						);

						dragDelegate.on(
							'drag:drophit',
							A.bind('_moveToSelectedUsersPanel', instance)
						);
					},

					_initializeInvitePanel: function() {
						var instance = this;

						var invitePanel = instance._invitePanel;

						var cancelButton = instance.byId('cancelButton');

						if (cancelButton) {
							cancelButton.on(
								EVENT_CLICK,
								function() {
									invitePanel.hide();
								}
							);
						}

						var emailAddressInput = instance.byId('emailAddress');

						if (emailAddressInput) {
							emailAddressInput.on(
								'valuechange',
								function(event) {
									var currentTarget = event.currentTarget;

									var validity = currentTarget.get('validity');
									var value = currentTarget.val();

									Liferay.Util.toggleDisabled(instance._inviteButton, !validity.valid || !value);
								}
							);
						}

						var inviteButton = instance.byId(STR_CONFIRM_INVITE_BUTTON);

						if (inviteButton) {
							inviteButton.on(
								EVENT_CLICK,
								function(event) {
									var form = instance.byId('inviteFm');

									if (form) {
										instance._submitForm(
											{
												form: form,
												url: instance._inviteURL
											}
										);
									}
								}
							);

							instance._inviteButton = inviteButton;
						}

						var roleSelect = instance.byId('role');

						if (roleSelect) {
							roleSelect.on(EVENT_CHANGE, instance._onSelectRole, instance);
						}

						invitePanel.show();
					},

					_moveToSelectedUsersPanel: function() {
						var instance = this;

						var remoteUsersPanel = instance._remoteUsersPanel;
						var selectedUsersPanel = instance._selectedUsersPanel;

						var checkedRemoteUsersCheckboxes = remoteUsersPanel.all('input:checkbox:checked');

						checkedRemoteUsersCheckboxes.each(
							function(item, index, collection) {
								if (!item.attr(STR_DISABLED)) {
									var remoteUserContainer = item.ancestor(CSS_USER_CONTAINER);

									var userId = item.val();

									var userName = remoteUserContainer.one('.lcs-user-name').text();

									var userPortraitHtml = remoteUserContainer.one(CSS_REMOTE_USER_AVATAR_IMG).outerHTML();

									var userNode = instance._createUserNode(
										{
											portletNamespace: instance.NS,
											userId: userId,
											userName: userName,
											userPortraitHtml: userPortraitHtml
										}
									);

									selectedUsersPanel.append(userNode);

									item.attr(STR_DISABLED, true);

									remoteUserContainer.toggle();
								}
							}
						);
					},

					_onSelectRole: function(event) {
						var instance = this;

						var environmentSelect = instance.byId('lcsClusterEntryId');

						if (environmentSelect) {
							var lcsAdministratorRole = (event.currentTarget.val() === instance._lcsAdministratorRole);

							environmentSelect.attr(STR_DISABLED, lcsAdministratorRole);

							var environmentSelectContainer = environmentSelect.ancestor('.control-group');

							environmentSelectContainer.toggle(!lcsAdministratorRole);
						}
					},

					_submitForm: function(config) {
						var instance = this;

						var form = config.form;

						var messageContainer = A.one('.lcs-message-container', form);

						A.io.request(
							config.url,
							{
								dataType: STR_JSON,
								form: {
									id: form.getDOM()
								},
								on: {
									failure: function(event, id, obj) {
										instance._showMessage(
											{
												container: messageContainer,
												message: instance._errorMessage,
												type: STR_ERROR
											}
										);
									},
									success: function(event, id, obj) {
										var responseData = this.get(STR_RESPONSE_DATA);

										var lcsConstants = instance._lcsConstants;

										if (responseData[lcsConstants.JSON_KEY_RESULT] == lcsConstants.JSON_VALUE_SUCCESS) {
											location.reload();
										}
										else {
											instance._showMessage(
												{
													container: messageContainer,
													message: responseData[lcsConstants.JSON_KEY_MESSAGE],
													type: STR_ERROR
												}
											);
										}
									}
								}
							}
						);
					}
				}
			}
		);

		Liferay.Portlet.LCSMembers = LCSMembers;
	},
	'',
	{
		requires: ['aui-io-deprecated', 'aui-live-search-deprecated', 'dd', 'lcs-base', 'liferay-form', 'liferay-portlet-base', 'liferay-util-window']
	}
);