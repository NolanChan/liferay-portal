create table OSBLCS_LCSClusterEntry (
	lcsClusterEntryId LONG not null primary key,
	lcsProjectId LONG,
	name VARCHAR(75) null,
	description VARCHAR(75) null,
	elastic BOOLEAN,
	highPageLoadTime INTEGER,
	location VARCHAR(75) null,
	mediumPageLoadTime INTEGER,
	subscriptionType VARCHAR(75) null,
	type_ INTEGER,
	archived BOOLEAN
);

create table OSBLCS_LCSClusterEntryToken (
	lcsClusterEntryTokenId LONG not null primary key,
	userId LONG,
	createDate DATE null,
	lcsClusterEntryId LONG,
	content VARCHAR(75) null
);

create table OSBLCS_LCSClusterNode (
	lcsClusterNodeId LONG not null primary key,
	lcsClusterEntryId LONG,
	installationId LONG,
	name VARCHAR(75) null,
	description VARCHAR(75) null,
	buildNumber INTEGER,
	key_ VARCHAR(75) null,
	location VARCHAR(75) null,
	processorCoresTotal INTEGER,
	archived BOOLEAN,
	status INTEGER
);

create table OSBLCS_LCSClusterNodeUptime (
	lcsClusterNodeUptimeId LONG not null primary key,
	lcsClusterNodeId LONG,
	startTime LONG,
	endTime LONG
);

create table OSBLCS_LCSInvitation (
	lcsInvitationId LONG not null primary key,
	userId LONG,
	createDate DATE null,
	lcsProjectId LONG,
	emailAddress VARCHAR(75) null,
	lcsClusterEntryId LONG,
	role INTEGER
);

create table OSBLCS_LCSMessage (
	lcsMessageId LONG not null primary key,
	createDate DATE null,
	modifiedDate DATE null,
	sourceMessageId LONG,
	sourceSystemName VARCHAR(75) null,
	classNameId LONG,
	classPK LONG,
	content VARCHAR(75) null,
	endDate DATE null,
	global BOOLEAN,
	severityLevel INTEGER,
	type_ INTEGER
);

create table OSBLCS_LCSMetadata (
	lcsMetadataId LONG not null primary key,
	availabilityIndex LONG,
	buildNumber INTEGER,
	gitTag VARCHAR(75) null,
	portalEdition VARCHAR(75) null,
	supportedLCSPortlet INTEGER,
	supportedPatchingTool INTEGER
);

create table OSBLCS_LCSNotification (
	lcsNotificationId LONG not null primary key,
	userId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	classNameId LONG,
	classPK LONG,
	enabled BOOLEAN,
	type_ INTEGER
);

create table OSBLCS_LCSNotificationAuditEntry (
	lcsNotificationAuditEntryId LONG not null primary key,
	userId LONG,
	createDate DATE null,
	lcsClusterNodeId LONG,
	type_ INTEGER
);

create table OSBLCS_LCSPatchEntry (
	lcsPatchEntryId LONG not null primary key,
	patchId VARCHAR(75) null,
	name VARCHAR(75) null,
	description VARCHAR(75) null,
	patchingToolVersion INTEGER,
	incremental BOOLEAN,
	singular BOOLEAN,
	version INTEGER,
	size_ LONG,
	rank LONG,
	requirements VARCHAR(75) null,
	component VARCHAR(75) null,
	compatibleBuild VARCHAR(75) null,
	product VARCHAR(75) null,
	fixedIssues VARCHAR(75) null,
	moduleName VARCHAR(75) null,
	moduleId VARCHAR(75) null,
	tunnelWeb BOOLEAN,
	buildDate DATE null,
	builtFor VARCHAR(75) null
);

create table OSBLCS_LCSProject (
	lcsProjectId LONG not null primary key,
	createDate DATE null,
	modifiedDate DATE null,
	sourceSystemName VARCHAR(75) null,
	name VARCHAR(75) null,
	organizationId LONG,
	addressId LONG,
	accountEntryId LONG,
	corpProjectId LONG,
	contactEmailAddress VARCHAR(75) null,
	phoneNumber VARCHAR(75) null,
	subscriptionActive BOOLEAN,
	archived BOOLEAN
);

create table OSBLCS_LCSRole (
	lcsRoleId LONG not null primary key,
	userId LONG,
	lcsProjectId LONG,
	lcsClusterEntryId LONG,
	role INTEGER
);

create table OSBLCS_LCSSubscriptionEntry (
	lcsSubscriptionEntryId LONG not null primary key,
	lcsProjectId LONG,
	actualPrice DOUBLE,
	currencyCode VARCHAR(75) null,
	instanceSize INTEGER,
	type_ VARCHAR(75) null,
	platform VARCHAR(75) null,
	platformVersion INTEGER,
	processorCoresAllowed INTEGER,
	product VARCHAR(75) null,
	productVersion INTEGER,
	serversAllowed INTEGER,
	serversUsed INTEGER,
	startDate DATE null,
	endDate DATE null,
	supportStartDate DATE null,
	supportEndDate DATE null,
	active_ BOOLEAN
);

create table OSBLCS_UserLCSMessage (
	userLcsMessageId LONG not null primary key,
	userId LONG,
	lcsMessageId LONG,
	hidden_ BOOLEAN,
	read_ BOOLEAN
);