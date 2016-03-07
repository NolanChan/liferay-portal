create index IX_C85A2A0F on Definition (companyId);
create index IX_FF33E851 on Definition (groupId);
create index IX_61463A2D on Definition (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_F58167EF on Definition (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_FC40AC47 on Source (companyId);
create index IX_50C59889 on Source (groupId);
create index IX_30AB48F5 on Source (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_76EB88B7 on Source (uuid_[$COLUMN_LENGTH:75$], groupId);