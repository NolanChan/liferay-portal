alter table KaleoProcess add uuid_ VARCHAR(75) null;
alter table KaleoProcess add workflowDefinitionName VARCHAR(75) null;
alter table KaleoProcess add workflowDefinitionVersion INTEGER;

COMMIT_TRANSACTION;