package com.liferay.osb.lcs.advisor;

import com.liferay.osb.lcs.model.LCSSubscriptionEntry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by matijap on 14.11.16..
 */
public interface SubscriptionsAdvisor {
	String formatUptime(long startTime, long endTime, Locale locale);

	JSONObject getBillsJSONObject(
		long lcsProjectId, int month, int year, Locale locale)
		throws Exception;

	String getDuration(long start, long end, Locale locale);

	JSONArray getLCSClusterEntriesJSONArray(
		long lcsProjectId, Locale locale)
			throws PortalException;

	JSONArray getLCSClusterNodesJSONArray(
		long lcsProjectId, Locale locale)
				throws PortalException;

	JSONObject getLCSClusterNodeUptimesJSONObject(
		long lcsClusterEntryId, long lcsProjectId, int month, int year,
		Locale locale)
					throws Exception;

	JSONArray getLCSSubscriptionEntriesJSONArray(
		List<LCSSubscriptionEntry> lcsOrderEntries, Locale locale,
		TimeZone timeZone);

	JSONArray getPaymentsJSONArray(
		long lcsProjectId, int startMonth, int startYear, int endMonth,
		int endYear)
							throws Exception;

	JSONArray getSubscriptionsUsageJSONArray(
		long lcsProjectId, Locale locale)
								throws PortalException;

	boolean hasElasticSubscription(long lcsProjectId)
									throws PortalException;

	int[] parsePeriod(String period);
}
