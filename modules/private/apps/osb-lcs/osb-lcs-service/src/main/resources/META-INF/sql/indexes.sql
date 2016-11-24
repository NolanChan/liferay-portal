create index IX_4CDD667A on OSBLCS_LCSClusterEntry (lcsProjectId, name[$COLUMN_LENGTH:75$], archived);
create index IX_790DCD66 on OSBLCS_LCSClusterEntry (lcsProjectId, subscriptionType[$COLUMN_LENGTH:75$], archived);

create unique index IX_4E6E8F93 on OSBLCS_LCSClusterEntryToken (lcsClusterEntryId);

create index IX_288303F6 on OSBLCS_LCSClusterNode (buildNumber, archived);
create unique index IX_1EA2141B on OSBLCS_LCSClusterNode (key_[$COLUMN_LENGTH:75$]);
create index IX_ED4BD22E on OSBLCS_LCSClusterNode (lcsClusterEntryId, archived);
create index IX_6AC37D2D on OSBLCS_LCSClusterNode (lcsClusterEntryId, name[$COLUMN_LENGTH:75$], archived);

create index IX_B2C8BF1A on OSBLCS_LCSClusterNodeUptime (lcsClusterNodeId, endTime);
create index IX_8CF91161 on OSBLCS_LCSClusterNodeUptime (lcsClusterNodeId, startTime);

create index IX_592E2806 on OSBLCS_LCSInvitation (emailAddress[$COLUMN_LENGTH:75$]);
create unique index IX_225BC912 on OSBLCS_LCSInvitation (lcsProjectId, emailAddress[$COLUMN_LENGTH:75$]);

create index IX_BB352AEE on OSBLCS_LCSMessage (classNameId, classPK, type_);
create index IX_D10A637E on OSBLCS_LCSMessage (endDate, global);
create index IX_37895343 on OSBLCS_LCSMessage (sourceMessageId, sourceSystemName[$COLUMN_LENGTH:75$], classNameId, classPK);
create index IX_2BC5FB50 on OSBLCS_LCSMessage (sourceSystemName[$COLUMN_LENGTH:75$], classNameId, classPK);

create unique index IX_5C74048F on OSBLCS_LCSMetadata (buildNumber, gitTag[$COLUMN_LENGTH:75$], portalEdition[$COLUMN_LENGTH:75$]);
create index IX_83AAC8EB on OSBLCS_LCSMetadata (buildNumber, portalEdition[$COLUMN_LENGTH:75$]);

create index IX_D03FEC41 on OSBLCS_LCSNotification (classNameId, classPK);
create unique index IX_2CAC187A on OSBLCS_LCSNotification (userId, classNameId, classPK, type_);

create index IX_92A15B72 on OSBLCS_LCSNotificationAuditEntry (lcsClusterNodeId);
create index IX_2CD39A29 on OSBLCS_LCSNotificationAuditEntry (userId, lcsClusterNodeId, type_);

create unique index IX_4378A848 on OSBLCS_LCSPatchEntry (patchId[$COLUMN_LENGTH:75$]);
create index IX_BD9BDC14 on OSBLCS_LCSPatchEntry (patchingToolVersion, product[$COLUMN_LENGTH:75$]);

create unique index IX_5CC7B876 on OSBLCS_LCSProject (corpProjectId);
create index IX_8C64F383 on OSBLCS_LCSProject (name[$COLUMN_LENGTH:75$]);

create index IX_FF8124A8 on OSBLCS_LCSRole (lcsClusterEntryId);
create index IX_3B06F6AD on OSBLCS_LCSRole (lcsProjectId, role);
create unique index IX_7DFD27E2 on OSBLCS_LCSRole (userId, lcsProjectId, lcsClusterEntryId);
create index IX_C784DE1B on OSBLCS_LCSRole (userId, role);

create index IX_34348B49 on OSBLCS_LCSSubscriptionEntry (active_);
create index IX_70EDAD3D on OSBLCS_LCSSubscriptionEntry (lcsProjectId, active_);
create index IX_A1F96420 on OSBLCS_LCSSubscriptionEntry (lcsProjectId, type_[$COLUMN_LENGTH:75$], active_);

create index IX_C6CFA9D7 on OSBLCS_UserLCSMessage (lcsMessageId);
create index IX_79D13D6E on OSBLCS_UserLCSMessage (userId, hidden_);
create unique index IX_BD756791 on OSBLCS_UserLCSMessage (userId, lcsMessageId);
create index IX_99FA9FC2 on OSBLCS_UserLCSMessage (userId, read_);