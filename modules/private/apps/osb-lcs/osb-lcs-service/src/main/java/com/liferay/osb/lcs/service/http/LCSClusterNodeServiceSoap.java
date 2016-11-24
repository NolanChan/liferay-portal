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

package com.liferay.osb.lcs.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.lcs.service.LCSClusterNodeServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link LCSClusterNodeServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.osb.lcs.model.LCSClusterNodeSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.osb.lcs.model.LCSClusterNode}, that is translated to a
 * {@link com.liferay.osb.lcs.model.LCSClusterNodeSoap}. Methods that SOAP cannot
 * safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Igor Beslic
 * @see LCSClusterNodeServiceHttp
 * @see com.liferay.osb.lcs.model.LCSClusterNodeSoap
 * @see LCSClusterNodeServiceUtil
 * @generated
 */
@ProviderType
public class LCSClusterNodeServiceSoap {
	public static com.liferay.osb.lcs.model.LCSClusterNodeSoap addLCSClusterNode(
		long lcsClusterEntryId, java.lang.String name,
		java.lang.String description, int buildNumber, java.lang.String key,
		java.lang.String location, int processorCoresTotal)
		throws RemoteException {
		try {
			com.liferay.osb.lcs.model.LCSClusterNode returnValue = LCSClusterNodeServiceUtil.addLCSClusterNode(lcsClusterEntryId,
					name, description, buildNumber, key, location,
					processorCoresTotal);

			return com.liferay.osb.lcs.model.LCSClusterNodeSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.lcs.model.LCSClusterNodeSoap addLCSClusterNode(
		java.lang.String siblingKey, java.lang.String name,
		java.lang.String description, java.lang.String key,
		java.lang.String location, int processorCoresTotal)
		throws RemoteException {
		try {
			com.liferay.osb.lcs.model.LCSClusterNode returnValue = LCSClusterNodeServiceUtil.addLCSClusterNode(siblingKey,
					name, description, key, location, processorCoresTotal);

			return com.liferay.osb.lcs.model.LCSClusterNodeSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* Deletes the LCS cluster node.
	* <p>
	* <p>
	* This method assures that the LCS cluster node connected to LCS is
	* properly disconnected. All NoSQL data is deleted.
	* </p>
	*
	* @param lcsClusterNodeId the primary key of the LCS cluster node
	* @throws PortalException if an LCS cluster node associated with the LCS
	        cluster entry could not be found or an operation was not allowed
	        by the LCS cluster entry membership policy
	* @since LCS 0.1
	*/
	public static void deleteLCSClusterNode(long lcsClusterNodeId)
		throws RemoteException {
		try {
			LCSClusterNodeServiceUtil.deleteLCSClusterNode(lcsClusterNodeId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* Returns an LCS cluster node matching the key.
	*
	* @param key the portal instance key provided by the licence tool
	* @return an LCS cluster node matching the key or <code>null</code> if no
	matching LCS cluster node was found
	* @throws PortalException if operation was not allowed by the LCS cluster
	        entry membership policy
	* @since LCS 1.0
	*/
	public static com.liferay.osb.lcs.model.LCSClusterNodeSoap fetchLCSClusterNode(
		java.lang.String key) throws RemoteException {
		try {
			com.liferay.osb.lcs.model.LCSClusterNode returnValue = LCSClusterNodeServiceUtil.fetchLCSClusterNode(key);

			return com.liferay.osb.lcs.model.LCSClusterNodeSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.lcs.model.LCSClusterNodeSoap fetchRandomSiblingLCSClusterNode(
		java.lang.String key) throws RemoteException {
		try {
			com.liferay.osb.lcs.model.LCSClusterNode returnValue = LCSClusterNodeServiceUtil.fetchRandomSiblingLCSClusterNode(key);

			return com.liferay.osb.lcs.model.LCSClusterNodeSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* Returns all LCS cluster nodes (excluding their transient details)
	* belonging to the LCS cluster entry.
	*
	* @param lcsClusterEntryId the primary key of the LCS cluster entry
	* @return the LCS cluster nodes belonging to the LCS cluster entry
	* @throws PortalException if an operation was not allowed by the LCS
	        cluster entry membership policy
	* @since LCS 0.1
	*/
	public static com.liferay.osb.lcs.model.LCSClusterNodeSoap[] getLCSClusterEntryLCSClusterNodes(
		long lcsClusterEntryId) throws RemoteException {
		try {
			java.util.List<com.liferay.osb.lcs.model.LCSClusterNode> returnValue =
				LCSClusterNodeServiceUtil.getLCSClusterEntryLCSClusterNodes(lcsClusterEntryId);

			return com.liferay.osb.lcs.model.LCSClusterNodeSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* Returns all LCS cluster nodes belonging to the LCS cluster entry.
	*
	* @param lcsClusterEntryId the primary key of the LCS cluster entry
	* @param details           whether to include transient details in the nodes
	* @return the LCS cluster nodes belonging to the LCS cluster entry
	* @throws PortalException if an operation was not allowed by the LCS
	        cluster entry membership policy
	* @since LCS 0.1
	*/
	public static com.liferay.osb.lcs.model.LCSClusterNodeSoap[] getLCSClusterEntryLCSClusterNodes(
		long lcsClusterEntryId, boolean details) throws RemoteException {
		try {
			java.util.List<com.liferay.osb.lcs.model.LCSClusterNode> returnValue =
				LCSClusterNodeServiceUtil.getLCSClusterEntryLCSClusterNodes(lcsClusterEntryId,
					details);

			return com.liferay.osb.lcs.model.LCSClusterNodeSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* Returns the LCS cluster node (excluding its transient details) with
	* primary key.
	*
	* @param lcsClusterNodeId the primary key of the LCS cluster node
	* @return the LCS cluster node with primary key
	* @throws PortalException if an LCS cluster node associated with the LCS
	        cluster entry could not be found or an operation was not allowed
	        by the LCS cluster entry membership policy
	* @since LCS 0.1
	*/
	public static com.liferay.osb.lcs.model.LCSClusterNodeSoap getLCSClusterNode(
		long lcsClusterNodeId) throws RemoteException {
		try {
			com.liferay.osb.lcs.model.LCSClusterNode returnValue = LCSClusterNodeServiceUtil.getLCSClusterNode(lcsClusterNodeId);

			return com.liferay.osb.lcs.model.LCSClusterNodeSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* Returns the LCS cluster node with primary key.
	*
	* @param lcsClusterNodeId the primary key of the LCS cluster node
	* @param details          whether to include transient details in the nodes
	* @return the LCS cluster node with primary key
	* @throws PortalException if an LCS cluster node associated with the LCS
	        cluster entry could not be found or an operation was not allowed
	        by the LCS cluster entry membership policy
	* @since LCS 0.1
	*/
	public static com.liferay.osb.lcs.model.LCSClusterNodeSoap getLCSClusterNode(
		long lcsClusterNodeId, boolean details) throws RemoteException {
		try {
			com.liferay.osb.lcs.model.LCSClusterNode returnValue = LCSClusterNodeServiceUtil.getLCSClusterNode(lcsClusterNodeId,
					details);

			return com.liferay.osb.lcs.model.LCSClusterNodeSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* Returns the LCS cluster node matching the key.
	*
	* @param key the portal instance key provided by the licence tool
	* @return the LCS cluster node matching the key
	* @throws PortalException if an LCS cluster node associated with the LCS
	        cluster entry could not be found or an operation was not allowed
	        by the LCS cluster entry membership policy
	* @since LCS 0.1
	*/
	public static com.liferay.osb.lcs.model.LCSClusterNodeSoap getLCSClusterNode(
		java.lang.String key) throws RemoteException {
		try {
			com.liferay.osb.lcs.model.LCSClusterNode returnValue = LCSClusterNodeServiceUtil.getLCSClusterNode(key);

			return com.liferay.osb.lcs.model.LCSClusterNodeSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.lcs.model.LCSClusterNodeSoap getLCSClusterNode(
		java.lang.String key, boolean details) throws RemoteException {
		try {
			com.liferay.osb.lcs.model.LCSClusterNode returnValue = LCSClusterNodeServiceUtil.getLCSClusterNode(key,
					details);

			return com.liferay.osb.lcs.model.LCSClusterNodeSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.lcs.model.LCSClusterNodeSoap[] getLCSClusterNodeSiblingNodes(
		java.lang.String key) throws RemoteException {
		try {
			java.util.List<com.liferay.osb.lcs.model.LCSClusterNode> returnValue =
				LCSClusterNodeServiceUtil.getLCSClusterNodeSiblingNodes(key);

			return com.liferay.osb.lcs.model.LCSClusterNodeSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* Returns all LCS cluster nodes (excluding their transient details)
	* accessible to the user and belonging to the LCS project.
	*
	* @param lcsProjectId the primary key of LCS project
	* @return the LCS cluster nodes belonging to the LCS project and accessible
	to the current user
	* @throws PortalException if an operation was not allowed by the LCS
	        cluster entry membership policy
	* @since LCS 0.1
	*/
	public static com.liferay.osb.lcs.model.LCSClusterNodeSoap[] getLCSProjectLCSClusterNodes(
		long lcsProjectId) throws RemoteException {
		try {
			java.util.List<com.liferay.osb.lcs.model.LCSClusterNode> returnValue =
				LCSClusterNodeServiceUtil.getLCSProjectLCSClusterNodes(lcsProjectId);

			return com.liferay.osb.lcs.model.LCSClusterNodeSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* Returns all LCS cluster nodes accessible to the current user and
	* belonging to the corp project.
	*
	* @param lcsProjectId the primary key of corp project
	* @param details      whether to include transient details in the nodes
	* @return the LCS cluster nodes belonging to the corp project and
	accessible to the current user
	* @throws PortalException if an operation was not allowed by the LCS
	        cluster entry membership policy
	* @since LCS 0.1
	*/
	public static com.liferay.osb.lcs.model.LCSClusterNodeSoap[] getLCSProjectLCSClusterNodes(
		long lcsProjectId, boolean details) throws RemoteException {
		try {
			java.util.List<com.liferay.osb.lcs.model.LCSClusterNode> returnValue =
				LCSClusterNodeServiceUtil.getLCSProjectLCSClusterNodes(lcsProjectId,
					details);

			return com.liferay.osb.lcs.model.LCSClusterNodeSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.lcs.model.LCSClusterNodeSoap[] getUserLCSClusterNodes(
		boolean details) throws RemoteException {
		try {
			java.util.List<com.liferay.osb.lcs.model.LCSClusterNode> returnValue =
				LCSClusterNodeServiceUtil.getUserLCSClusterNodes(details);

			return com.liferay.osb.lcs.model.LCSClusterNodeSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void mergeStatus(java.lang.String key, int status)
		throws RemoteException {
		try {
			LCSClusterNodeServiceUtil.mergeStatus(key, status);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void updateBuildNumber(long lcsClusterNodeId, int buildNumber)
		throws RemoteException {
		try {
			LCSClusterNodeServiceUtil.updateBuildNumber(lcsClusterNodeId,
				buildNumber);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void updateBuildNumber(java.lang.String key, int buildNumber)
		throws RemoteException {
		try {
			LCSClusterNodeServiceUtil.updateBuildNumber(key, buildNumber);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* Updates the LCS cluster node.
	*
	* @param lcsClusterNodeId the primary key of LCS cluster node
	* @param name             the portal instance friendly name
	* @param description      the portal instance description
	* @param location         the portal instance location
	* @return the LCS cluster node
	* @throws PortalException if a matching LCS cluster node could not be
	        found, or new attributes were invalid, or an operation was not
	        allowed by the LCS cluster entry membership policy
	* @since LCS 0.1
	*/
	public static com.liferay.osb.lcs.model.LCSClusterNodeSoap updateLCSClusterNode(
		long lcsClusterNodeId, java.lang.String name,
		java.lang.String description, java.lang.String location)
		throws RemoteException {
		try {
			com.liferay.osb.lcs.model.LCSClusterNode returnValue = LCSClusterNodeServiceUtil.updateLCSClusterNode(lcsClusterNodeId,
					name, description, location);

			return com.liferay.osb.lcs.model.LCSClusterNodeSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void verifyLCSClusterEntryLCSClusterNodesPropertiesDifferences(
		java.lang.String key) throws RemoteException {
		try {
			LCSClusterNodeServiceUtil.verifyLCSClusterEntryLCSClusterNodesPropertiesDifferences(key);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void verifyLCSClusterNodeClusterLink(java.lang.String key,
		java.lang.String siblingKeys) throws RemoteException {
		try {
			LCSClusterNodeServiceUtil.verifyLCSClusterNodeClusterLink(key,
				siblingKeys);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(LCSClusterNodeServiceSoap.class);
}