AUI.add(
	'lcs-enrollment',
	function(A) {
		var Lang = A.Lang;

		var CSS_BTN = 'btn';

		var CSS_SELECTED = 'selected';

		var DATA_KEY_LCSPROJECTID = 'lcsprojectid';

		var ROWS_PER_PAGE = 10;

		var STR_ACTION_LINK = 'action-link';

		var STR_ADMINISTRATOR_EMAIL_ADDRESSES = 'administratorEmailAddresses';

		var STR_BUTTON = 'button';

		var STR_CLICK = 'click';

		var STR_CONTENT = 'content';

		var STR_DATA = 'data';

		var STR_DATA_LCSPROJECTID = 'data-' + DATA_KEY_LCSPROJECTID;

		var STR_DOT = '.';

		var STR_ERROR = 'error';

		var STR_INFO = 'info';

		var STR_JSON = 'json';

		var STR_LCSPROJECTID = 'lcsProjectId';

		var STR_NAME = 'name';

		var STR_OPACITY = 'opacity';

		var STR_RESPONSE_DATA = 'responseData';

		var STR_SPACE = ' ';

		var STR_SUCCESS = 'success';

		var STR_TITLE = 'title';

		var STR_YUI3_DATATABLE_PAGINATOR_WRAPPER = 'yui3-datatable-paginator-wrapper';

		var SELECTOR_ACTION_LINK = STR_DOT + STR_ACTION_LINK;

		var SELECTOR_CONTENT = STR_DOT + STR_CONTENT;

		var SELECTOR_DATATABLE_PAGINATOR_WRAPPER = STR_DOT + STR_YUI3_DATATABLE_PAGINATOR_WRAPPER;

		var SELECTOR_SELECTED = STR_DOT + CSS_SELECTED;

		var SELECTOR_TITLE = STR_DOT + STR_TITLE;

		var SELECTOR_UPDATING = '.updating';

		var TPL_ACTION_LINK = '<a class="' + STR_ACTION_LINK + '" data-{dataKey}="{dataValue}" href="javascript:;">' +
				'<i class="icon {iconCssClass}"></i>' +
				'<span class="taglib-text">{label}</span>' +
			'</a>';

		var LCSEnrollment = A.Component.create(
			{
				AUGMENTS: [Liferay.LCSBase, Liferay.PortletBase],

				EXTENDS: A.Base,

				NAME: 'lcs-enrollment',

				prototype: {
					initializer: function(config) {
						var instance = this;

						var labels = config.labels;

						instance._activeProjectsContainer = instance.byId('activeProjects');
						instance._administeredProjectsContainer = instance.byId('administeredProjects');
						instance._companyMessageContainer = instance.byId('companyMessageContainer');
						instance._companyProjectsContainer = instance.byId('companyProjects');
						instance._labels = labels;
						instance._pendingMessageContainer = instance.byId('pendingMessageContainer');
						instance._pendingProjectsContainer = instance.byId('pendingProjects');
						instance._projectTabs = instance.byId('projectTabs');
						instance._messageContainer = instance.byId('messageContainer');
						instance._unadministeredProjectsContainer = instance.byId('unadministeredProjects');
						instance._unlinkedMessageContainer = instance.byId('unlinkedMessageContainer');
						instance._updatingNode = instance.byId('updating');
						instance._urls = config.urls;

						instance._requestableProjectsTableColumns = [
							{
								key: STR_NAME,
								label: labels.name
							},
							{
								key: STR_ADMINISTRATOR_EMAIL_ADDRESSES,
								label: labels.administratorEmailAddresses
							},
							{
								allowHTML: true,
								formatter: function(node) {
									return Lang.sub(
										TPL_ACTION_LINK,
										{
											dataKey: DATA_KEY_LCSPROJECTID,
											dataValue: node.value,
											iconCssClass: 'icon-bell',
											label: labels.requestAccess
										}
									);
								},
								key: STR_LCSPROJECTID,
								label: STR_SPACE
							}
						];

						instance._getProjects();
					},

					_addProjectAdministrator: function(config) {
						var instance = this;

						var container = instance._unadministeredProjectsContainer;
						var messageContainer = instance._messageContainer;

						var updatingNode = container.one(SELECTOR_UPDATING);

						if (updatingNode) {
							updatingNode.show();

							var data = {
								lcsProjectId: config.lcsProjectId
							};

							A.io.request(
								instance._urls.addLCSAdministratorRole,
								{
									data: instance.ns(data),
									dataType: STR_JSON,
									on: {
										failure: function(event, id, obj) {
											instance._showMessage(
												{
													container: messageContainer,
													message: instance._errorMessage,
													type: STR_ERROR
												}
											);

											updatingNode.hide();
										},
										success: function(event, id, obj) {
											var responseData = this.get(STR_RESPONSE_DATA);

											var lcsConstants = instance._lcsConstants;

											if (responseData[lcsConstants.JSON_KEY_RESULT] == lcsConstants.JSON_VALUE_SUCCESS) {
												responseData = responseData[lcsConstants.JSON_KEY_DATA];

												var activeLCSProjectsData = responseData.active;
												var unadministeredLCSProjectsData = responseData.unadministered;

												instance._activeLCSProjectsData = activeLCSProjectsData;
												instance._unadministeredLCSProjectsData = unadministeredLCSProjectsData;

												instance._processTableData(
													{
														container: instance._activeProjectsContainer,
														data: activeLCSProjectsData,
														initTable: A.bind('_initializeActiveProjectsTable', instance),
														table: instance._activeProjectsTable
													}
												);

												instance._processTableData(
													{
														container: instance._unadministeredProjectsContainer,
														data: unadministeredLCSProjectsData,
														initTable: A.bind('_initializeUnadministeredProjectsTable', instance),
														table: instance._unadministeredProjectsTable
													}
												);

												if ((instance._administeredLCSProjectsData.length == 0) && (unadministeredLCSProjectsData.length == 0)) {
													instance._showMessage(
														{
															container: instance._unlinkedMessageContainer,
															message: instance._labels.msgNoUnlinkedProjects,
															type: STR_INFO
														}
													);
												}

												instance._showMessage(
													{
														container: messageContainer,
														message: instance._successMessage,
														type: STR_SUCCESS
													}
												);
											}
											else {
												instance._showMessage(
													{
														container: messageContainer,
														message: instance._errorMessage,
														type: STR_ERROR
													}
												);
											}

											updatingNode.hide();
										}
									}
								}
							);
						}
					},

					_getProjects: function() {
						var instance = this;

						var messageContainer = instance._messageContainer;

						var updatingNode = instance._updatingNode;

						A.io.request(
							instance._urls.getUserLCSProjects,
							{
								dataType: STR_JSON,
								on: {
									failure: function(event, id, obj) {
										instance._showMessage(
											{
												container: messageContainer,
												message: instance._errorMessage,
												type: STR_ERROR
											}
										);

										updatingNode.hide();
									},
									success: function(event, id, obj) {
										var responseData = this.get(STR_RESPONSE_DATA);

										var lcsConstants = instance._lcsConstants;

										if (responseData[lcsConstants.JSON_KEY_RESULT] == lcsConstants.JSON_VALUE_SUCCESS) {
											responseData = responseData[lcsConstants.JSON_KEY_DATA];

											var activeLCSProjectsData = responseData.active;
											var administeredLCSProjectsData = responseData.administered;
											var pendingLCSProjectsData = responseData.pending;
											var unadministeredLCSProjectsData = responseData.unadministered;

											instance._activeLCSProjectsData = activeLCSProjectsData;
											instance._administeredLCSProjectsData = administeredLCSProjectsData;
											instance._pendingLCSProjectsData = pendingLCSProjectsData;
											instance._unadministeredLCSProjectsData = unadministeredLCSProjectsData;

											instance._processTableData(
												{
													container: instance._activeProjectsContainer,
													data: activeLCSProjectsData,
													initTable: A.bind('_initializeActiveProjectsTable', instance),
													table: instance._activeProjectsTable
												}
											);

											instance._processTableData(
												{
													container: instance._administeredProjectsContainer,
													data: administeredLCSProjectsData,
													initTable: A.bind('_initializeAdministeredProjectsTable', instance),
													table: instance._administeredProjectsTable
												}
											);

											instance._processTableData(
												{
													container: instance._unadministeredProjectsContainer,
													data: unadministeredLCSProjectsData,
													initTable: A.bind('_initializeUnadministeredProjectsTable', instance),
													table: instance._unadministeredProjectsTable
												}
											);

											if ((administeredLCSProjectsData.length == 0) && (unadministeredLCSProjectsData.length == 0)) {
												instance._showMessage(
													{
														container: instance._unlinkedMessageContainer,
														message: instance._labels.msgNoUnlinkedProjects,
														type: STR_INFO
													}
												);
											}

											instance._processTableData(
												{
													container: instance._pendingProjectsContainer,
													data: pendingLCSProjectsData,
													initTable: A.bind('_initializePendingProjectsTable', instance),
													message: {
														container: instance._pendingMessageContainer,
														message: instance._labels.msgNoPendingProjects,
														type: STR_INFO
													},
													table: instance._pendingProjectsTable
												}
											);

											instance._processTableData(
												{
													container: instance._companyProjectsContainer,
													data: responseData.company,
													initTable: A.bind('_initializeCompanyProjectsTable', instance),
													message: {
														container: instance._companyMessageContainer,
														message: instance._labels.msgNoCompanyProjects,
														type: STR_INFO
													},
													table: instance._companyProjectsTable
												}
											);
										}
										else {
											instance._showMessage(
												{
													container: messageContainer,
													message: instance._errorMessage,
													type: STR_ERROR
												}
											);
										}

										updatingNode.hide();

										instance._projectTabs.show();

										var projectsFooter = instance.byId('projectsFooter');

										projectsFooter.setStyle(STR_OPACITY, 1);
									}
								}
							}
						);
					},

					_initializeActiveProjectsTable: function(data) {
						var instance = this;

						var container = instance._activeProjectsContainer;
						var labels = instance._labels;

						var projectNameEditor = new A.TextCellEditor(
							{
								hideOnSave: false,
								on: {
									save: function(event) {
										var selectedActionLink = container.one(SELECTOR_ACTION_LINK + SELECTOR_SELECTED);

										var textarea = this.bodyNode.one('.celleditor-element');

										if (selectedActionLink && textarea) {
											var lcsProjectName = textarea.val();

											var lcsProjectId = selectedActionLink.attr(STR_DATA_LCSPROJECTID);

											instance._updateProjectName(
												{
													lcsProjectId: lcsProjectId,
													lcsProjectName: lcsProjectName
												}
											);
										}
									}
								}
							}
						);

						instance._projectNameEditor = projectNameEditor;

						var columns = [
							{
								allowHTML: true,
								editor: projectNameEditor,
								key: STR_NAME,
								label: labels.name,
								nodeFormatter: function(obj) {
									var data = obj.data;
									var td = obj.td;

									var html = Liferay.Util.escapeHTML(obj.value);

									if (data.editable) {
										html = Lang.sub(
											TPL_ACTION_LINK,
											{
												dataKey: DATA_KEY_LCSPROJECTID,
												dataValue: data.lcsProjectId,
												iconCssClass: 'icon-pencil',
												label: html
											}
										);
									}
									else {
										td.removeClass('table-cell');
									}

									td.html(html);

									return false;
								}
							},
							{
								key: STR_ADMINISTRATOR_EMAIL_ADDRESSES,
								label: labels.administratorEmailAddresses
							}
						];

						var tableContent = container.one(SELECTOR_CONTENT);

						if (tableContent) {
							var table = new A.DataTable(
								{
									columnset: columns,
									pageSizes: [ROWS_PER_PAGE, labels.all],
									recordset: data,
									rowsPerPage: ROWS_PER_PAGE,
									sortable: true
								}
							).render(tableContent);

							instance._activeProjectsTable = table;

							var paginator = tableContent.one(SELECTOR_DATATABLE_PAGINATOR_WRAPPER);

							if (paginator) {
								paginator.toggle(data.length > ROWS_PER_PAGE);

								paginator.all(STR_BUTTON).addClass(CSS_BTN);
							}

							instance._initializeTableSearch(
								{
									container: container,
									data: data,
									paginator: paginator,
									rowsPerPage: ROWS_PER_PAGE,
									table: table
								}
							);

							tableContent.delegate(
								STR_CLICK,
								function(event) {
									var selectedCells = tableContent.all(SELECTOR_SELECTED);

									selectedCells.removeClass(CSS_SELECTED);

									var currentTarget = event.currentTarget;

									currentTarget.addClass(CSS_SELECTED);
								},
								SELECTOR_ACTION_LINK
							);
						}
					},

					_initializeAdministeredProjectsTable: function(data) {
						var instance = this;

						var container = instance._administeredProjectsContainer;

						var tableContent = container.one(SELECTOR_CONTENT);

						if (tableContent) {
							var table = new A.DataTable(
								{
									columnset: instance._requestableProjectsTableColumns,
									recordset: data,
									sortable: true
								}
							).render(tableContent);

							instance._administeredProjectsTable = table;

							tableContent.delegate(
								STR_CLICK,
								function(event) {
									if (confirm(instance._labels.msgConfirmProjectAccessRequest)) {
										var lcsProjectId = event.currentTarget.attr(STR_DATA_LCSPROJECTID);

										instance._requestProjectAccess(
											{
												container: container,
												lcsProjectId: lcsProjectId
											}
										);
									}
								},
								SELECTOR_ACTION_LINK
							);
						}
					},

					_initializeCompanyProjectsTable: function(data) {
						var instance = this;

						var container = instance._companyProjectsContainer;

						var tableContent = container.one(SELECTOR_CONTENT);

						if (tableContent) {
							var table = new A.DataTable(
								{
									columnset: instance._requestableProjectsTableColumns,
									pageSizes: [ROWS_PER_PAGE, instance._labels.all],
									recordset: data,
									rowsPerPage: ROWS_PER_PAGE,
									sortable: true
								}
							).render(tableContent);

							instance._companyProjectsTable = table;

							var paginator = tableContent.one(SELECTOR_DATATABLE_PAGINATOR_WRAPPER);

							if (paginator) {
								paginator.toggle(data.length > ROWS_PER_PAGE);

								paginator.all(STR_BUTTON).addClass(CSS_BTN);
							}

							instance._initializeTableSearch(
								{
									container: container,
									data: data,
									paginator: paginator,
									rowsPerPage: ROWS_PER_PAGE,
									table: table
								}
							);

							tableContent.delegate(
								STR_CLICK,
								function(event) {
									if (confirm(instance._labels.msgConfirmProjectAccessRequest)) {
										var lcsProjectId = event.currentTarget.attr(STR_DATA_LCSPROJECTID);

										instance._requestProjectAccess(
											{
												container: container,
												lcsProjectId: lcsProjectId
											}
										);
									}
								},
								SELECTOR_ACTION_LINK
							);
						}
					},

					_initializePendingProjectsTable: function(data) {
						var instance = this;

						var container = instance._pendingProjectsContainer;
						var labels = instance._labels;

						var columns = [
							{
								key: STR_NAME,
								label: labels.name
							},
							{
								key: STR_ADMINISTRATOR_EMAIL_ADDRESSES,
								label: labels.administratorEmailAddresses
							}
						];

						var tableContent = container.one(SELECTOR_CONTENT);

						if (tableContent) {
							var table = new A.DataTable(
								{
									columnset: columns,
									recordset: data,
									sortable: true
								}
							).render(tableContent);

							instance._pendingProjectsTable = table;
						}
					},

					_initializeUnadministeredProjectsTable: function(data) {
						var instance = this;

						var container = instance._unadministeredProjectsContainer;
						var labels = instance._labels;

						var columns = [
							{
								key: STR_NAME,
								label: labels.name
							},
							{
								allowHTML: true,
								formatter: function(node) {
									return Lang.sub(
										TPL_ACTION_LINK,
										{
											dataKey: DATA_KEY_LCSPROJECTID,
											dataValue: node.value,
											iconCssClass: 'icon-star',
											label: labels.becomeAnAdministrator
										}
									);
								},
								key: STR_LCSPROJECTID,
								label: STR_SPACE
							}
						];

						var tableContent = container.one(SELECTOR_CONTENT);

						if (tableContent) {
							var table = new A.DataTable(
								{
									columnset: columns,
									recordset: data,
									sortable: true
								}
							).render(tableContent);

							instance._unadministeredProjectsTable = table;

							tableContent.delegate(
								STR_CLICK,
								function(event) {
									if (confirm(labels.msgConfirmBecomingAdministrator)) {
										var lcsProjectId = event.currentTarget.attr(STR_DATA_LCSPROJECTID);

										instance._addProjectAdministrator(
											{
												lcsProjectId: lcsProjectId
											}
										);
									}
								},
								SELECTOR_ACTION_LINK
							);
						}
					},

					_processTableData: function(config) {
						var instance = this;

						var container = config.container;
						var data = config.data;

						var length = data.length;

						if (length) {
							var table = config.table;

							if (table) {
								table.set(STR_DATA, data);
							}
							else {
								config.initTable(data);
							}
						}
						else {
							var message = config.message;

							if (message) {
								instance._showMessage(message);
							}
						}

						container.toggle(length > 0);
						container.setStyle(STR_OPACITY, length);
					},

					_requestProjectAccess: function(config) {
						var instance = this;

						var messageContainer = instance._messageContainer;

						var updatingNode = config.container.one(SELECTOR_UPDATING);

						if (updatingNode) {
							updatingNode.show();

							var data = {
								lcsProjectId: config.lcsProjectId
							};

							A.io.request(
								instance._urls.addLCSEnvironmentMembershipPendingUserRole,
								{
									data: instance.ns(data),
									dataType: STR_JSON,
									on: {
										failure: function(event, id, obj) {
											instance._showMessage(
												{
													container: messageContainer,
													message: instance._errorMessage,
													type: STR_ERROR
												}
											);

											updatingNode.hide();
										},
										success: function(event, id, obj) {
											var responseData = this.get(STR_RESPONSE_DATA);

											var lcsConstants = instance._lcsConstants;

											if (responseData[lcsConstants.JSON_KEY_RESULT] == lcsConstants.JSON_VALUE_SUCCESS) {
												responseData = responseData[lcsConstants.JSON_KEY_DATA];

												var administeredLCSProjectsData = responseData.administered;
												var companyLCSProjectsData = responseData.company;
												var pendingLCSProjectsData = responseData.pending;

												instance._administeredLCSProjectsData = administeredLCSProjectsData;
												instance._companyLCSProjectsData = companyLCSProjectsData;
												instance._pendingLCSProjectsData = pendingLCSProjectsData;

												instance._processTableData(
													{
														container: instance._administeredProjectsContainer,
														data: administeredLCSProjectsData,
														initTable: A.bind('_initializeAdministeredProjectsTable', instance),
														table: instance._administeredProjectsTable
													}
												);

												instance._processTableData(
													{
														container: instance._companyProjectsContainer,
														data: companyLCSProjectsData,
														initTable: A.bind('_initializeCompanyProjectsTable', instance),
														message: {
															container: instance._companyMessageContainer,
															message: instance._labels.msgNoCompanyProjects,
															type: STR_INFO
														},
														table: instance._companyProjectsTable
													}
												);

												instance._processTableData(
													{
														container: instance._pendingProjectsContainer,
														data: pendingLCSProjectsData,
														initTable: A.bind('_initializePendingProjectsTable', instance),
														table: instance._pendingProjectsTable
													}
												);

												if ((administeredLCSProjectsData.length == 0) && (responseData.unadministeredCount == 0)) {
													instance._showMessage(
														{
															container: instance._unlinkedMessageContainer,
															message: instance._labels.msgNoUnlinkedProjects,
															type: STR_INFO
														}
													);
												}

												instance._pendingMessageContainer.toggle(!pendingLCSProjectsData.length);

												instance._showMessage(
													{
														container: messageContainer,
														message: instance._labels.msgProjectAccessRequestSuccess,
														type: STR_SUCCESS
													}
												);
											}
											else {
												instance._showMessage(
													{
														container: messageContainer,
														message: instance._errorMessage,
														type: STR_ERROR
													}
												);
											}

											updatingNode.hide();
										}
									}
								}
							);
						}
					},

					_updateProjectName: function(config) {
						var instance = this;

						var lcsProjectId = config.lcsProjectId;
						var lcsProjectName = config.lcsProjectName;

						var container = instance._activeProjectsContainer;
						var messageContainer = instance._messageContainer;
						var updatingNode = instance._updatingNode;

						var title = container.one(SELECTOR_TITLE);

						if (title) {
							updatingNode.show();

							var data = {
								lcsProjectId: lcsProjectId,
								lcsProjectName: lcsProjectName
							};

							A.io.request(
								instance._urls.updateLCSProjectName,
								{
									data: instance.ns(data),
									dataType: STR_JSON,
									on: {
										failure: function(event, id, obj) {
											instance._showMessage(
												{
													container: messageContainer,
													message: instance._errorMessage,
													type: STR_ERROR
												}
											);

											updatingNode.hide();
										},
										success: function(event, id, obj) {
											var responseData = this.get(STR_RESPONSE_DATA);

											var lcsConstants = instance._lcsConstants;

											if (responseData[lcsConstants.JSON_KEY_RESULT] == lcsConstants.JSON_VALUE_SUCCESS) {
												instance._showMessage(
													{
														container: messageContainer,
														message: instance._successMessage,
														type: STR_SUCCESS
													}
												);

												var data = instance._activeLCSProjectsData;

												for (var i = 0; i < data.length; i++) {
													var item = data[i];

													if (item.lcsProjectId == lcsProjectId) {
														item.name = lcsProjectName;
													}
												}

												instance._activeProjectsTable.set(STR_DATA, data);
											}
											else {
												instance._showMessage(
													{
														container: messageContainer,
														message: instance._errorMessage,
														type: STR_ERROR
													}
												);
											}

											updatingNode.hide();

											instance._projectNameEditor.hide();
										}
									}
								}
							);
						}
					}
				}
			}
		);

		Liferay.Portlet.LCSEnrollment = LCSEnrollment;
	},
	'',
	{
		requires: ['aui-datatable', 'datatable-paginator', 'lcs-base', 'liferay-portlet-base', 'liferay-util-list-fields']
	}
);