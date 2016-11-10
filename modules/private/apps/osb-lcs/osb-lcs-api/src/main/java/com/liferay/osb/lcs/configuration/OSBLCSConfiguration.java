/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.lcs.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.osb.lcs.util.ApplicationProfile;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Igor Beslic
 */
@ExtendedObjectClassDefinition(category = "osb-lcs")
@Meta.OCD(id = "com.liferay.osb.lcs.configuration.OSBLCSConfiguration")
public interface OSBLCSConfiguration {

	@Meta.AD(deflt = "LOCAL_DEVELOPMENT", required = true)
	public ApplicationProfile applicationProfile();

	@Meta.AD(deflt = "none", required = true)
	public String cassandraSessionFactoryCompression();

	@Meta.AD(deflt = "localhost", required = true)
	public String cassandraSessionFactoryHostNames();

	@Meta.AD(deflt = "9042", required = true)
	public String cassandraSessionFactoryHostPort();

	@Meta.AD(deflt = "osb", required = true)
	public String cassandraSessionFactoryKeyspace();

	@Meta.AD(deflt = "false", required = true)
	public String cassandraSessionFactoryLoggingRetryPolicyEnabled();

	@Meta.AD(
		deflt = "com.liferay.osb.lcs.cache.LocalCacheManagerImpl",
		required = true
	)
	public String cacheFactoryClassName();

	@Meta.AD(deflt = "localhost", required = true)
	public String cacheFactoryMasterHostName();

	@Meta.AD(deflt = "6379", required = true)
	public String cacheFactoryMasterHostPort();
		@Meta.AD(deflt = "$NULL$", required = true)
		public String cacheFactorySlaveHostName();

	@Meta.AD(deflt = "6379", required = true)
	public String cacheFactorySlaveHostPort();

	@Meta.AD(deflt = "SunRsaSign", required = true)
	public String digitalSignatureAlgorithmProvider();

	@Meta.AD(deflt = "localhost", required = true)
	public String digitalSignatureKeyName();

	@Meta.AD(deflt = "classpath:/keystore.jks", required = true)
	public String digitalSignatureKeyStorePath();

	@Meta.AD(deflt = "JKS", required = true)
	public String digitalSignatureKeyStoreType();

	@Meta.AD(deflt = "MD5withRSA", required = true)
	public String digitalSignatureSigningAlgorithm();

	@Meta.AD(
		deflt = "com.liferay.osb.lcs.report.DefaultInvoiceNumberGenerator",
		required = true
	)
	public String invoiceNumberGenerator();

	@Meta.AD(deflt = "aws-key", required = true)
	public String managementAccessKey();

	@Meta.AD(deflt = "https://ec2.us-east-1.amazonaws.com", required = true)
	public String managementEc2Endpoint();

	@Meta.AD(deflt = "8199", required = true)
	public String managementJmxRemotePort();

	@Meta.AD(deflt = "aws-secret-key", required = true)
	public String managementSecretKey();

	@Meta.AD(
		deflt = "/documents/10180/762582/lcs-portlet/489af29d-cf23-47cd-91da-bd834b20fb1a",
		required = true
	)
	public String lcsPortlet6210DownloadUrl();

	@Meta.AD(
		deflt = "https://web.liferay.com/marketplace/-/mp/application/71774947",
		required = true
	)
	public String lcsPortlet7010DownloadUrl();

	@Meta.AD(deflt = "2.0.0", required = true)
	public String lcsPortletTokenCompatibleVersion();

	@Meta.AD(deflt = "s3_bucket_name", required = true)
	public String logStorageManagerBucketName();

	@Meta.AD(
		deflt = "com.liferay.osb.lcsStorage.LogFileSystemStorageManagerImpl",
		required = true
	)
	public String logStorageManagerClassName();

	@Meta.AD(deflt = "/root/logs", required = true)
	public String logStorageManagerPath();

	@Meta.AD(deflt = "s3_prefix", required = true)
	public String logStorageManagerPrefix();

	@Meta.AD(
		deflt = "https://www.liferay.com/products/liferay-portal/ee/overview",
		required = true
	)
	public String lrdcomEePortalOverviewUrl();

	@Meta.AD(
		deflt = "https://dev.liferay.com/discover/deployment/-/knowledge_base/6-2/using-lcs#using-environment-tokens",
		required = true
	)
	public String lrdcomEnvironmentTokensUrl();

	@Meta.AD(
		deflt = "https://www.liferay.com/group/customer/knowledge/release-notes/6.2-ee/-/release_notes/fix_pack/liferay-fixpack-{0}",
		required = true
	)
	public String lrdcomFixPackReleaseNotesUrl();

	@Meta.AD(
		deflt = "https://dev.liferay.com/discover/deployment/-/knowledge_base/6-2/configuring-the-patching-tool",
		required = true
	)
	public String lrdcomPatchingToolOverviewUrl();

	@Meta.AD(
		deflt = "https://dev.liferay.com/discover/deployment/-/knowledge_base/6-2/performance-tuning",
		required = true
	)
	public String lrdcomPerformanceTuningUrl();

	@Meta.AD(
		deflt = "https://www.liferay.com/supporting-products/liferay-connected-services",
		required = true
	)
	public String lrdcomProductPageUrl();

	@Meta.AD(
		deflt = "https://dev.liferay.com/discover/deployment/-/knowledge_base/6-2/managing-liferay-with-liferay-connected-services",
		required = true
	)
	public String lrdcomUserDocumentationUrl();

	@Meta.AD(
		deflt = "https://www.liferay.com/web/{0}/projects", required = true
	)
	public String lrdcomUserProjectsUrl();

	@Meta.AD(
		deflt = "13_WAR_osblcsportlet|8_WAR_osblcsportlet|10_WAR_osblcsportlet|1_WAR_webformportlet|6_WAR_osblcsportlet|11_WAR_osblcsportlet|9_WAR_osblcsportlet|3_WAR_osblcsportlet|12_WAR_osblcsportlet",
		required = true
	)
	public String[] osbLcsPortalPrivateLayoutPortlets();

	@Meta.AD(
		deflt = "Dashboard,Downloads,Environment,Feedback,Projects,Server,Subscriptions,Users,Account",
		required = true
	)
	public String[] osbLcsPortalPrivateLayoutNames();

	@Meta.AD(deflt = "1_column", required = true)
	public String osbLcsPortalPrivateLayoutTemplateId();

	@Meta.AD(deflt = "58|15_WAR_osblcsportlet", required = true)
	public String[] osbLcsPortalPublicLayoutPortlets();

	@Meta.AD(deflt = "Home,Info", required = true)
	public String[] osbLcsPortalPublicLayoutNames();

	@Meta.AD(deflt = "1_column", required = true)
	public String osbLcsPortalPublicLayoutTemplateId();

	@Meta.AD(deflt = "lcs.liferay.com", required = true)
	public String[] osbLcsPortalSecuredDomains();

	@Meta.AD(
		deflt = "/file-repository-web|/portal/lcs/check_lcs_health|/portal/lcs/patch/upload|/portal/lcs/upload_lcs_client_exception|/portal/oauth/access_token|/portal/oauth/authorize|/portal/oauth/request_token"
	)
	public String[] osbLcsPortalUnsecuredPaths();

	@Meta.AD(deflt = "localhost", required = true)
	public String osbLcsPortletHostName();

	@Meta.AD(deflt = "8080", required = true)
	public int osbLcsPortletHostPort();

	@Meta.AD(deflt = "Liferay Connected Services", required = true)
	public String osbLcsPortletMembersEmailFromName();

	@Meta.AD(deflt = "connected-services@liferay.com", required = true)
	public String osbLcsPortletMembersEmailFromAddress();

	@Meta.AD(deflt = "false", required = true)
	public boolean
		osbLcsPortletMembersEmailMembershipInvitationAcceptedEnabled();

	@Meta.AD(deflt = "false", required = true)
	public boolean osbLcsPortletMembersEmailMembershipInvitationEnabled();

	@Meta.AD(deflt = "false", required = true)
	public boolean osbLcsPortletMembersEmailMembershipRequestAcceptedEnabled();

	@Meta.AD(deflt = "false", required = true)
	public boolean osbLcsPortletMembersEmailMembershipRequestEnabled();

	@Meta.AD(deflt = "betaprogram@liferay.com", required = true)
	public String osbLcsPortletMembersFeedbackEmailAddress();

	@Meta.AD(
		deflt = "6102,6.1.2-ga3,CE,14,0|6130,ee-6.1.30-ga3,EE,14,17|6200,6.2.0-ga1,CE,14,0|6201,6.2.1-ga2,CE,14,0|6202,6.2.2-ga3,CE,14,0|6203,6.2.3-ga4,CE,14,0|6210,ee-6.2.10-ga1,EE,14,21|6210,ee-6.2.x,CE,14,0|6210,fix-pack-base-6210-sp10,EE,14,21",
		required = false
	)
	public String[] osbLcsPortletModelLcsmetadata();

	@Meta.AD(deflt = "consumerKey", required = true)
	public String osbLcsPortletOauthConsumerKey();

	@Meta.AD(deflt = "http", required = true)
	public String osbLcsPortletProtocol();

	@Meta.AD(
		deflt = "com.liferay.osb.lcs.osbportlet.service.impl.MockOSBPortletServiceImpl",
		required = true
	)
	public String osbPortletServiceClassName();

	@Meta.AD(deflt = "$NULL$", required = false)
	public String osbPortletContextPath();

	@Meta.AD(deflt = "localhost", required = true)
	public String osbPortletHostName();

	@Meta.AD(deflt = "8080", required = true)
	public String osbPortletHostPort();

	@Meta.AD(deflt = "https", required = true)
	public String osbPortletHostProtocol();

	@Meta.AD(deflt = "localhost", required = true)
	public String osbPortletKeyStoreDigitalSignatureKeyName();

	@Meta.AD(deflt = "123456", required = true)
	public String osbPortletKeyStorePassword();

	@Meta.AD(deflt = "classpath:/keystore.jks", required = true)
	public String osbPortletKeyStorePath();

	@Meta.AD(deflt = "JKS", required = true)
	public String osbPortletKeyStoreType();

	@Meta.AD(deflt = "$NULL$", required = true)
	public String osbPortletLogin();

	@Meta.AD(deflt = "ke8eJgsT2nla73NsRe", required = true)
	public String osbPortletSecureApiToken();

	@Meta.AD(deflt = "127.0.0.1,localhost,web-uat.liferay.com", required = true)
	public String[] osbPortletSynchronizationSafeHostNames();

	@Meta.AD(deflt = "s3_bucket_name", required = true)
	public String patchStorageManagerBucketName();

	@Meta.AD(
		deflt = "com.liferay.osb.lcsStorage.PatchFileSystemStorageManagerImpl",
		required = true
	)
	public String patchStorageManagerClassName();

	@Meta.AD(deflt = "/file-repository-web", required = true)
	public String patchStorageManagerContextPath();

	@Meta.AD(deflt = "localhost", required = true)
	public String patchStorageManagerDownloadHostName();

	@Meta.AD(deflt = "8080", required = true)
	public int patchStorageManagerDownloadHostPort();

	@Meta.AD(deflt = "localhost", required = true)
	public String patchStorageManagerHostName();

	@Meta.AD(deflt = "8080", required = true)
	public int patchStorageManagerHostPort();

	@Meta.AD(deflt = "/root/files", required = true)
	public String patchStorageManagerPath();

	@Meta.AD(deflt = "s3_prefix", required = true)
	public String patchStorageManagerPrefix();

	@Meta.AD(deflt = "false", required = true)
	public boolean sendingEmailsEnabled();

	@Meta.AD(deflt = "aws-key", required = true)
	public String queueFactoryAccessKey();

	@Meta.AD(
		deflt = "com.liferay.osb.lcs.queue.LocalQueueManagerImpl",
		required = true
	)
	public String queueFactoryClassName();

	@Meta.AD(deflt = "localhost", required = true)
	public String queueFactoryHostName();

	@Meta.AD(deflt = "5672", required = true)
	public String queueFactoryHostPort();

	@Meta.AD(deflt = "dev", required = true)
	public String queueFactoryQueuePrefix();

	@Meta.AD(deflt = "us-east-1", required = true)
	public String queueFactoryQueueRegion();

	@Meta.AD(deflt = "aws-secret-key", required = true)
	public String queueFactorySecretKey();

	@Meta.AD(deflt = "94p9Cud54p9neuoa4C", required = true)
	public String secureUploadApiToken();

	@Meta.AD(deflt = "aws-key", required = true)
	public String storageManagerAccessKey();

	@Meta.AD(deflt = "aws-secret-key", required = true)
	public String storageManagerSecretKey();

}