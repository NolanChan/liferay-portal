create table LCSClusterEntryPropertyDifferences (
	lcsClusterEntryId bigint,
	propertyName varchar,
	propertyValues map<varchar, varchar>,
	primary key(lcsClusterEntryId, propertyName)
)
WITH compaction = {'class': 'LeveledCompactionStrategy'};

create table LCSClusterNodeCurrentThreadsMetrics (
	currentThreadCount int,
	currentThreadsBusy int,
	key_ varchar,
	modifiedDate timestamp,
	name varchar,
	uuid timeuuid,
	primary key(key_, name)
)
WITH compaction = {'class': 'LeveledCompactionStrategy'};

create table LCSClusterNodeDetails (
	buildNumber int,
	companyIdsWebIds map<int, varchar>,
	configurationModifiedDate timestamp,
	heartbeatInterval bigint,
	key_ varchar,
	lastHeartbeat bigint,
	modifiedDate timestamp,
	patchingToolVersion int,
	portalEdition varchar,
	protocolVersion varchar,
	status int,
	primary key(key_)
)
WITH compaction = {'class': 'LeveledCompactionStrategy'};

create table LCSClusterNodeHibernateMetrics (
	key_ varchar,
	modifiedDate timestamp,
	queryCacheHitCount int,
	queryCacheMissCount int,
	queryCacheExecutionCount int,
	queryCacheExecutionMaxTime int,
	uuid timeuuid,
	primary key(key_)
)
WITH compaction = {'class': 'LeveledCompactionStrategy'};

create table LCSClusterNodeInstallationEnvironment (
	hardwareMetadata map<varchar, varchar>,
	key_ varchar,
	modifiedDate timestamp,
	softwareMetadata map<varchar, varchar>,
	primary key(key_)
)
WITH compaction = {'class': 'LeveledCompactionStrategy'};

create table LCSClusterNodeJDBCConnectionPoolMetrics (
	key_ varchar,
	modifiedDate timestamp,
	name varchar,
	numActive int,
	numIdle int,
	uuid timeuuid,
	primary key(key_, name)
)
WITH compaction = {'class': 'LeveledCompactionStrategy'};

create table LCSClusterNodeJVMMetrics (
	bufferPoolMetrics map<varchar, varchar>,
	daemonThreadCount int,
	deadlockedThreads set<varchar>,
	fileDescriptorUsage double,
	garbageCollectorMetrics map<varchar, varchar>,
	heapCommitted double,
	heapInit double,
	heapMax double,
	heapUsage double,
	heapUsed double,
	key_ varchar,
	memoryPoolUsage map<varchar, double>,
	modifiedDate timestamp,
	name varchar,
	nonHeapUsage double,
	threadCount int,
	threadStatePercentages map<varchar, double>,
	totalCommitted double,
	totalInit double,
	totalMax double,
	totalUsed double,
	uptime int,
	uuid timeuuid,
	version varchar,
	primary key(key_)
)
WITH compaction = {'class': 'LeveledCompactionStrategy'};

create table LCSClusterNodeJVMMetricsEvents (
	bufferPoolMetrics map<varchar, varchar>,
	daemonThreadCount int,
	deadlockedThreads set<varchar>,
	fileDescriptorUsage double,
	garbageCollectorMetrics map<varchar, varchar>,
	heapCommitted double,
	heapInit double,
	heapMax double,
	heapUsage double,
	heapUsed double,
	key_ varchar,
	memoryPoolUsage map<varchar, double>,
	modifiedDate timestamp,
	name varchar,
	nonHeapUsage double,
	partitionKey varchar,
	threadCount int,
	threadStatePercentages map<varchar, double>,
	totalCommitted double,
	totalInit double,
	totalMax double,
	totalUsed double,
	uptime int,
	uuid timeuuid,
	version varchar,
	primary key((partitionKey), modifiedDate, key_)
)
WITH compaction = {'class': 'DateTieredCompactionStrategy'}
AND default_time_to_live = 7776000
AND CLUSTERING ORDER BY (modifiedDate DESC, key_ ASC);

create table LCSClusterNodeLayoutMetrics (
	averageLoadTime int,
	companyId bigint,
	groupId bigint,
	count bigint,
	frequency int,
	key_ varchar,
	maxLoadTime int,
	minLoadTime int,
	modifiedDate timestamp,
	name varchar,
	uuid timeuuid,
	primary key(key_, companyId, groupId, name)
)
WITH compaction = {'class': 'LeveledCompactionStrategy'};

create table LCSClusterNodeLayoutMetricsCounter (
	companyId bigint,
	groupId bigint,
	count counter,
	key_ varchar,
	primary key(key_, companyId, groupId)
);

create table LCSClusterNodeLayoutMetricsEvents (
	averageLoadTime int,
	companyId bigint,
	frequency int,
	groupId bigint,
	key_ varchar,
	modifiedDate timestamp,
	name varchar,
	partitionKey varchar,
	referer varchar,
	remoteAddr varchar,
	requestStatus varchar,
	requestURL varchar,
	statusCode int,
	userAgent varchar,
	userId bigint,
	uuid timeuuid,
	primary key((partitionKey), modifiedDate, key_, companyId, groupId, name)
)
WITH compaction = {'class': 'DateTieredCompactionStrategy'}
AND default_time_to_live = 7776000
AND CLUSTERING ORDER BY (modifiedDate DESC, key_ ASC, companyId ASC, groupId ASC, name ASC);

create table LCSClusterNodeLiferayVMMetrics (
	cacheHits int,
	cacheMisses int,
	key_ varchar,
	modifiedDate timestamp,
	name varchar,
	objectCount int,
	type varchar,
	uuid timeuuid,
	primary key(key_, type, name)
)
WITH compaction = {'class': 'LeveledCompactionStrategy'};

create table LCSClusterNodePatches (
	error varchar,
	fixedIssues list<varchar>,
	hashCode varchar,
	installablePatchIds list<varchar>,
	key_ varchar,
	modifiedDate timestamp,
	patchIdsStatuses map<varchar, int>,
	uuid timeuuid,
	primary key(key_)
)
WITH compaction = {'class': 'LeveledCompactionStrategy'};

create table LCSClusterNodePatchesEvents (
	error varchar,
	fixedIssues list<varchar>,
	hashCode varchar,
	key_ varchar,
	modifiedDate timestamp,
	patchIdsStatuses map<varchar, int>,
	uuid timeuuid,
	primary key(key_, modifiedDate)
);

create table LCSClusterNodePortletMetrics (
	averageLoadTime int,
	companyId bigint,
	groupId bigint,
	count bigint,
	displayName varchar,
	frequency int,
	key_ varchar,
	layoutName varchar,
	maxLoadTime int,
	minLoadTime int,
	modifiedDate timestamp,
	name varchar,
	portletId varchar,
	requestType varchar,
	uuid timeuuid,
	primary key(key_, companyId, groupId, layoutName, requestType, portletId)
)
WITH compaction = {'class': 'LeveledCompactionStrategy'};

create table LCSClusterNodePortletMetricsCounter (
	companyId bigint,
	groupId bigint,
	count counter,
	key_ varchar,
	layoutName varchar,
	requestType varchar,
	primary key(key_, companyId, groupId, layoutName, requestType)
);

create table LCSClusterNodePortletMetricsEvents (
	averageLoadTime int,
	companyId bigint,
	displayName varchar,
	frequency int,
	groupId bigint,
	key_ varchar,
	layoutName varchar,
	modifiedDate timestamp,
	name varchar,
	partitionKey varchar,
	portletId varchar,
	requestType varchar,
	uuid timeuuid,
	primary key((partitionKey), modifiedDate, key_, companyId, groupId, layoutName, requestType, portletId)
)
WITH compaction = {'class': 'DateTieredCompactionStrategy'}
AND default_time_to_live = 7776000
AND CLUSTERING ORDER BY (modifiedDate DESC, key_ ASC, companyId ASC, groupId ASC, layoutName ASC, requestType ASC, portletId ASC);

create table LCSClusterNodeProperties (
	hashCode varchar,
	key_ varchar,
	modifiedDate timestamp,
	properties map<varchar, varchar>,
	uuid timeuuid,
	primary key(key_)
)
WITH compaction = {'class': 'LeveledCompactionStrategy'};

create table LCSClusterNodeScript (
	command varchar,
	correlationKey varchar,
	error varchar,
	key_ varchar,
	modifiedDate timestamp,
	result varchar,
	uuid timeuuid,
	primary key(key_, uuid)
);

create table LCSClusterNodeSite (
	companyId bigint,
	friendlyURL varchar,
	groupId bigint,
	groupUuid varchar,
	installationId bigint,
	key_ varchar,
	modifiedDate timestamp,
	name varchar,
	type_ int,
	primary key((installationId), companyId, groupId)
)
WITH compaction = {'class': 'LeveledCompactionStrategy'};

create table LCSMetadataDetails (
	buildNumber int,
	gitTag varchar,
	portalEdition varchar,
	portalProperties map<varchar, varchar>,
	properties map<varchar, varchar>,
	primary key((buildNumber, gitTag, portalEdition))
)
WITH compaction = {'class': 'LeveledCompactionStrategy'};

create table LCSStatsLayoutMetricsEvents1440 (
	averageLoadTime int,
	companyId bigint,
	groupId bigint,
	jobDate timestamp,
	jobIdentifier varchar,
	key_ varchar,
	maxLoadTime int,
	minLoadTime int,
	modifiedDate timestamp,
	name varchar,
	partitionKey varchar,
	requestStatus varchar,
	requestURL varchar,
	sampleCount int,
	standardDeviation int,
	primary key((key_, partitionKey), companyId, groupId, name, modifiedDate)
)
WITH compaction = {'class': 'DateTieredCompactionStrategy'}
AND default_time_to_live = 7776000
AND CLUSTERING ORDER BY (companyId ASC, groupId ASC, name ASC, modifiedDate DESC);

create table LCSStatsLayoutMetricsEvents60 (
	averageLoadTime int,
	companyId bigint,
	groupId bigint,
	jobDate timestamp,
	jobIdentifier varchar,
	key_ varchar,
	maxLoadTime int,
	minLoadTime int,
	modifiedDate timestamp,
	name varchar,
	partitionKey varchar,
	requestStatus varchar,
	requestURL varchar,
	sampleCount int,
	standardDeviation int,
	primary key((key_, partitionKey), companyId, groupId, name, modifiedDate)
)
WITH compaction = {'class': 'DateTieredCompactionStrategy'}
AND default_time_to_live = 7776000
AND CLUSTERING ORDER BY (companyId ASC, groupId ASC, name ASC, modifiedDate DESC);

create table LCSStatsLayoutMetricsSummaryEvents10080 (
	averageLoadTime int,
	companyId bigint,
	groupId bigint,
	jobDate timestamp,
	jobIdentifier varchar,
	key_ varchar,
	maxLoadTime int,
	minLoadTime int,
	modifiedDate timestamp,
	name varchar,
	partitionKey varchar,
	requestStatus varchar,
	requestURL varchar,
	sampleCount int,
	standardDeviation int,
	primary key((key_, partitionKey), companyId, groupId, modifiedDate, name)
)
WITH compaction = {'class': 'DateTieredCompactionStrategy'}
AND default_time_to_live = 7776000
AND CLUSTERING ORDER BY (companyId ASC, groupId ASC, modifiedDate DESC, name ASC);

create table LCSStatsLayoutMetricsSummaryEvents1440 (
	averageLoadTime int,
	companyId bigint,
	groupId bigint,
	jobDate timestamp,
	jobIdentifier varchar,
	key_ varchar,
	maxLoadTime int,
	minLoadTime int,
	modifiedDate timestamp,
	name varchar,
	partitionKey varchar,
	requestStatus varchar,
	requestURL varchar,
	sampleCount int,
	standardDeviation int,
	primary key((key_, partitionKey), companyId, groupId, modifiedDate, name)
)
WITH compaction = {'class': 'DateTieredCompactionStrategy'}
AND default_time_to_live = 7776000
AND CLUSTERING ORDER BY (companyId ASC, groupId ASC, modifiedDate DESC, name ASC);

create table LCSStatsLayoutMetricsSummaryEvents60 (
	averageLoadTime int,
	companyId bigint,
	groupId bigint,
	jobDate timestamp,
	jobIdentifier varchar,
	key_ varchar,
	maxLoadTime int,
	minLoadTime int,
	modifiedDate timestamp,
	name varchar,
	partitionKey varchar,
	requestStatus varchar,
	requestURL varchar,
	sampleCount int,
	standardDeviation int,
	primary key((key_, partitionKey), companyId, groupId, modifiedDate, name)
)
WITH compaction = {'class': 'DateTieredCompactionStrategy'}
AND default_time_to_live = 7776000
AND CLUSTERING ORDER BY (companyId ASC, groupId ASC, modifiedDate DESC, name ASC);

create table LCSStatsPortletMetricsEvents60 (
	averageLoadTime int,
	companyId bigint,
	displayName varchar,
	groupId bigint,
	jobDate timestamp,
	jobIdentifier varchar,
	key_ varchar,
	layoutName varchar,
	maxLoadTime int,
	minLoadTime int,
	modifiedDate timestamp,
	name varchar,
	partitionKey varchar,
	portletId varchar,
	requestType varchar,
	sampleCount int,
	standardDeviation int,
	primary key((key_, partitionKey), companyId, groupId, portletId, modifiedDate, layoutName, requestType)
)
WITH compaction = {'class': 'DateTieredCompactionStrategy'}
AND default_time_to_live = 7776000
AND CLUSTERING ORDER BY (companyId ASC, groupId ASC, portletId ASC, modifiedDate DESC, layoutName ASC, requestType ASC);

create table LCSStatsPortletMetricsSummaryEvents60 (
	averageLoadTime int,
	companyId bigint,
	displayName varchar,
	groupId bigint,
	jobDate timestamp,
	jobIdentifier varchar,
	key_ varchar,
	layoutName varchar,
	maxLoadTime int,
	minLoadTime int,
	modifiedDate timestamp,
	name varchar,
	partitionKey varchar,
	portletId varchar,
	requestType varchar,
	sampleCount int,
	standardDeviation int,
	primary key((key_, partitionKey), companyId, groupId, modifiedDate, portletId, layoutName, requestType)
)
WITH compaction = {'class': 'DateTieredCompactionStrategy'}
AND default_time_to_live = 7776000
AND CLUSTERING ORDER BY (companyId ASC, groupId ASC, modifiedDate DESC, portletId ASC, layoutName ASC, requestType ASC);