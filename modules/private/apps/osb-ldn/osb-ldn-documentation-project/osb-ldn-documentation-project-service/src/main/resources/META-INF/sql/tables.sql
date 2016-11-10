create table OSB_LDN_DocumentationProject (
	uuid_ VARCHAR(75) null,
	documentationProjectId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	description STRING null,
	iconFileName VARCHAR(75) null,
	type_ VARCHAR(75) null,
	typeSettings TEXT null,
	status INTEGER
);