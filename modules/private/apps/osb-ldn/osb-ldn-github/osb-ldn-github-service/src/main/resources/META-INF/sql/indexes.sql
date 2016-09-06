create index IX_31094650 on OSB_LDN_GitHubContributor (gitHubRepositoryId);

create index IX_2AAC55A5 on OSB_LDN_GitHubRepository (owner[$COLUMN_LENGTH:75$], name[$COLUMN_LENGTH:75$]);