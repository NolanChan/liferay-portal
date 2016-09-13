AUI.add(
	'lcs-notifications',
	function(A) {
		var ATTR_DATA_PATCH_NAME = 'data-patchname';

		var SELECTOR_DOWNLOAD_FIX_PACK = '.download-fix-pack';

		var STR_CLICK = 'click';

		var STR_RESPONSE_DATA = 'responseData';

		var LCSNotifications = A.Component.create(
			{
				AUGMENTS: [Liferay.LCSBase, Liferay.PortletBase],

				EXTENDS: A.Base,

				NAME: 'lcs-notifications',

				prototype: {
					initializeViewNotificationsPage: function(config) {
						var instance = this;

						instance._downloadPatchStatusAttributes = config.downloadPatchStatusAttributes;
						instance._downloadPatchStatusCodes = config.downloadPatchStatusCodes;
						instance._downloadPatchStatusURL = config.downloadPatchStatusURL;
						instance._downloadPatchURL = config.downloadPatchURL;
						instance._msgCompletedDownload = config.msgCompletedDownload;
						instance._msgConfirmDownloadFixPack = config.msgConfirmDownloadFixPack;
						instance._msgConfirmDownloadSelectedFixPacks = config.msgConfirmDownloadSelectedFixPacks;
						instance._msgNoAvailableFixPacks = config.msgNoAvailableFixPacks;
						instance._showServersDialogTitle = config.showServersDialogTitle;
						instance._showServersDialogURL = config.showServersDialogURL;

						instance._downloadsCount = 0;

						instance._initializeTooltips();

						var availableFixPacksContainer = instance.one('.available-fix-packs-container');

						if (availableFixPacksContainer) {
							availableFixPacksContainer.delegate(
								STR_CLICK,
								A.bind('_createShowServersDialog', instance),
								'.lcs-show-servers'
							);

							availableFixPacksContainer.delegate(
								STR_CLICK,
								A.bind('_downloadSinglePatch', instance),
								SELECTOR_DOWNLOAD_FIX_PACK
							);

							var updatingNode = availableFixPacksContainer.one('.updating');

							instance._updatingNode = updatingNode;

							var ongoingDownloadRows = availableFixPacksContainer.all('.downloading,.started-download');

							updatingNode.toggle(ongoingDownloadRows.size() > 0);

							ongoingDownloadRows.each(
								function(item, index, collection) {
									instance._downloadPatch(
										{
											downloading: true,
											row: item
										}
									);
								}
							);

							instance._availableFixPacksContainer = availableFixPacksContainer;
						}

						var tableContainers = instance.all('.table-container');

						tableContainers.setStyle('opacity', 1);
					},

					_createShowServersDialog: function(event) {
						var instance = this;

						var showServersButton = event.currentTarget;

						var clusterEntryId = showServersButton.attr('data-clusterentryid');
						var clusterEntryName = showServersButton.attr('data-clusterentryname');
						var lcsProjectId = showServersButton.attr('data-lcsprojectid');

						var patchName = showServersButton.attr(ATTR_DATA_PATCH_NAME);

						var dialog = Liferay.Util.Window.getWindow(
							{
								dialog: {
									cssClass: 'lcs-dialog',
									destroyOnHide: true,
									resizable: false,
									visible: false
								},
								title: instance._showServersDialogTitle + clusterEntryName
							}
						);

						dialog.plug(
							A.Plugin.IO,
							{
								after: {
									success: function() {
										dialog.show();
									}
								},
								data: instance.ns(
									{
										lcsClusterEntryId: clusterEntryId,
										lcsClusterEntryName: clusterEntryName,
										lcsProjectId: lcsProjectId,
										patchName: patchName
									}
								),
								uri: instance._showServersDialogURL
							}
						);
					},

					_downloadPatch: function(config) {
						var instance = this;

						var downloading = config.downloading || false;
						var row = config.row;

						var actionCell = row.one('.table-cell.action');
						var statusCell = row.one('.table-cell.status');

						if (actionCell && statusCell) {
							var downloadPatchNode = actionCell.one(SELECTOR_DOWNLOAD_FIX_PACK);

							var lcsClusterNodeIds = downloadPatchNode.attr('data-lcsclusternodeids');
							var lcsClusterNodeKeys = downloadPatchNode.attr('data-lcsclusternodekeys');
							var patchId = downloadPatchNode.attr('data-patchid');

							var patchName = downloadPatchNode.attr(ATTR_DATA_PATCH_NAME);

							instance._updatingNode.show();

							instance._downloadsCount++;

							if (downloading) {
								instance._getDownloadPatchStatus(
									{
										lcsClusterNodeIds: lcsClusterNodeIds,
										lcsClusterNodeKeys: lcsClusterNodeKeys,
										patchId: patchId,
										row: row,
										statusCell: statusCell
									}
								);
							}
							else {
								A.io.request(
									instance._downloadPatchURL,
									{
										data: instance.ns(
											{
												lcsClusterNodeIds: lcsClusterNodeIds,
												patchName: patchName
											}
										),
										dataType: 'json',
										on: {
											failure: function(event, id, obj) {
												instance._handleDownloadPatchError(statusCell);
											},
											success: function(event, id, obj) {
												var responseData = this.get(STR_RESPONSE_DATA);

												var lcsConstants = instance._lcsConstants;

												if (responseData[lcsConstants.JSON_KEY_RESULT] === lcsConstants.JSON_VALUE_SUCCESS) {
													instance._updateDownloadPatchStatus(
														{
															downloadPatchStatus: instance._downloadPatchStatusCodes.DOWNLOAD_INITIATED,
															statusCell: statusCell
														}
													);

													instance._sendGAEvent(
														{
															action: 'Download',
															category: 'FixPacks'
														}
													);

													setTimeout(
														function() {
															instance._getDownloadPatchStatus(
																{
																	lcsClusterNodeIds: lcsClusterNodeIds,
																	lcsClusterNodeKeys: lcsClusterNodeKeys,
																	patchId: patchId,
																	row: row,
																	statusCell: statusCell
																}
															);
														},
														1000
													);
												}
												else {
													instance._handleDownloadPatchError(statusCell);
												}
											}
										}
									}
								);
							}
						}
					},

					_downloadSinglePatch: function(event) {
						var instance = this;

						if (confirm(instance._msgConfirmDownloadFixPack)) {
							var downloadPatchNode = event.currentTarget;

							var row = downloadPatchNode.ancestor('tr');

							instance._downloadPatch(
								{
									row: row
								}
							);
						}
					},

					_getDownloadPatchStatus: function(config) {
						var instance = this;

						var statusCell = config.statusCell;

						A.io.request(
							instance._downloadPatchStatusURL,
							{
								data: instance.ns(
									{
										lcsClusterNodeIds: config.lcsClusterNodeIds,
										lcsClusterNodeKeys: config.lcsClusterNodeKeys,
										patchId: config.patchId
									}
								),
								dataType: 'json',
								method: 'GET',
								on: {
									failure: function(event, id, obj) {
										instance._handleDownloadPatchError(statusCell);
									},
									success: function(event, id, obj) {
										var responseData = this.get(STR_RESPONSE_DATA);

										var downloadPatchStatusCodes = instance._downloadPatchStatusCodes;
										var lcsConstants = instance._lcsConstants;

										var downloadPatchStatus = responseData[lcsConstants.JSON_KEY_DATA];

										instance._updateDownloadPatchStatus(
											{
												downloadPatchStatus: downloadPatchStatus,
												statusCell: statusCell
											}
										);

										if ((downloadPatchStatus == downloadPatchStatusCodes.DOWNLOADED) || (downloadPatchStatus == downloadPatchStatusCodes.ERROR)) {
											var downloadsCount = instance._downloadsCount;

											downloadsCount--;

											if (!downloadsCount) {
												instance._updatingNode.hide();
											}

											instance._downloadsCount = downloadsCount;
										}
										else {
											setTimeout(
												function() {
													instance._getDownloadPatchStatus(config);
												},
												1000
											);
										}
									}
								}
							}
						);
					},

					_initializeTooltips: function() {
						var instance = this;

						new A.Tooltip(
							{
								bodyContent: instance._msgCompletedDownload,
								trigger: '.completed-download',
								visible: false
							}
						).render();

						new A.TooltipDelegate(
							{
								trigger: '.alert-message, .fix-packs-name'
							}
						);
					},

					_updateDownloadPatchStatus: function(config) {
						var instance = this;

						var downloadPatchStatus = config.downloadPatchStatus;
						var statusCell = config.statusCell;

						var downloadPatchStatusAttributes = instance._downloadPatchStatusAttributes[downloadPatchStatus];

						if (downloadPatchStatusAttributes && statusCell) {
							statusCell.attr('className', downloadPatchStatusAttributes.cssClass + ' status table-cell');

							statusCell.html(downloadPatchStatusAttributes.label);
						}
					}
				}
			}
		);

		Liferay.Portlet.LCSNotifications = LCSNotifications;
	},
	'',
	{
		requires: ['aui-io-deprecated', 'aui-loading-mask-deprecated', 'aui-tooltip', 'lcs-base', 'liferay-portlet-base', 'liferay-util-window']
	}
);