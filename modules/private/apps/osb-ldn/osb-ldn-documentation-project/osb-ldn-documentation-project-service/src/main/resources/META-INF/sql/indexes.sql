create index IX_60A30A2E on OSB_LDN_DocumentationProject (name[$COLUMN_LENGTH:75$]);
create index IX_CE0C0DE3 on OSB_LDN_DocumentationProject (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_B1F4B125 on OSB_LDN_DocumentationProject (uuid_[$COLUMN_LENGTH:75$], groupId);