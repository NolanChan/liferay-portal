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

package com.liferay.portal.cluster;

import com.liferay.portal.kernel.cluster.Address;
import com.liferay.portal.kernel.cluster.AddressSerializerUtil;
import com.liferay.portal.kernel.cluster.ClusterEventListener;
import com.liferay.portal.kernel.cluster.ClusterExecutor;
import com.liferay.portal.kernel.cluster.ClusterMasterTokenTransitionListener;
import com.liferay.portal.kernel.cluster.ClusterMessageType;
import com.liferay.portal.kernel.cluster.ClusterNode;
import com.liferay.portal.kernel.cluster.ClusterNodeResponse;
import com.liferay.portal.kernel.cluster.ClusterRequest;
import com.liferay.portal.kernel.cluster.ClusterResponseCallback;
import com.liferay.portal.kernel.cluster.FutureClusterResponses;
import com.liferay.portal.kernel.concurrent.NoticeableFuture;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.test.CaptureHandler;
import com.liferay.portal.kernel.test.CodeCoverageAssertor;
import com.liferay.portal.kernel.test.JDKLoggerTestUtil;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.Lock;
import com.liferay.portal.model.impl.LockImpl;
import com.liferay.portal.service.LockLocalServiceUtil;
import com.liferay.portal.service.impl.LockLocalServiceImpl;
import com.liferay.portal.uuid.PortalUUIDImpl;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.net.InetAddress;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

/**
 * @author Matthew Tambara
 */

public class ClusterMasterExecutorImplTest {

	@ClassRule
	public static CodeCoverageAssertor codeCoverageAssertor =
		new CodeCoverageAssertor();

	@Before
	public void setUp() {
		_mockLockLocalService = new MockLockLocalService();

		ReflectionTestUtil.setFieldValue(
			LockLocalServiceUtil.class, "_service", _mockLockLocalService);
	}

	@Test
	public void testClusterMasterTokenClusterEventListenerSuccess() {
		CaptureHandler captureHandler = JDKLoggerTestUtil.configureJDKLogger(
			ClusterMasterExecutorImpl.class.getName(), Level.SEVERE);

		try {
			List<LogRecord> logRecords = captureHandler.getLogRecords();

			ClusterMasterExecutorImpl clusterMasterExecutorImpl =
				new ClusterMasterExecutorImpl();

			ClusterExecutor mockClusterExecutor = new MockClusterExecutor(true);

			clusterMasterExecutorImpl.setClusterExecutor(mockClusterExecutor);
			clusterMasterExecutorImpl.initialize();

			List<ClusterEventListener> clusterEventListeners =
				mockClusterExecutor.getClusterEventListeners();

			ClusterEventListener clusterEventListener =
				clusterEventListeners.get(0);

			clusterEventListener.processClusterEvent(null);

			Assert.assertTrue(logRecords.isEmpty());
		}
		finally {
			captureHandler.close();
		}
	}

	@Test
	public void testClusterMasterTokenTransitionListeners() {
		ClusterMasterExecutorImpl clusterMasterExecutorImpl =
			new ClusterMasterExecutorImpl();

		ClusterMasterTokenTransitionListener
			clusterMasterTokenTransitionListener1 =
				new MockClusterMasterTokenTransitionListener();

		clusterMasterExecutorImpl.registerClusterMasterTokenTransitionListener(
			clusterMasterTokenTransitionListener1);

		Set<ClusterMasterTokenTransitionListener>
			clusterMasterTokenTransitionListeners =
				ReflectionTestUtil.getFieldValue(
					clusterMasterExecutorImpl,
					"_clusterMasterTokenTransitionListeners");

		Assert.assertEquals(1, clusterMasterTokenTransitionListeners.size());

		Set<ClusterMasterTokenTransitionListener>
			clusterMasterTokenTransitionListenerSet =
				new HashSet<ClusterMasterTokenTransitionListener>();

		ClusterMasterTokenTransitionListener
			clusterMasterTokenTransitionListener2 =
				new MockClusterMasterTokenTransitionListener();

		clusterMasterTokenTransitionListenerSet.add(
			clusterMasterTokenTransitionListener1);
		clusterMasterTokenTransitionListenerSet.add(
			clusterMasterTokenTransitionListener2);

		clusterMasterExecutorImpl.setClusterMasterTokenTransitionListeners(
			clusterMasterTokenTransitionListenerSet);

		Assert.assertEquals(2, clusterMasterTokenTransitionListeners.size());

		clusterMasterExecutorImpl.
			unregisterClusterMasterTokenTransitionListener(
				clusterMasterTokenTransitionListener2);

		Assert.assertEquals(1, clusterMasterTokenTransitionListeners.size());
	}

	@Test
	public void testDestroyClusterLinkDisabled() throws Exception {
		ClusterMasterExecutorImpl clusterMasterExecutorImpl =
			new ClusterMasterExecutorImpl();

		ClusterExecutor mockClusterExecutor = new MockClusterExecutor(false);

		clusterMasterExecutorImpl.setClusterExecutor(mockClusterExecutor);
		clusterMasterExecutorImpl.initialize();

		Assert.assertFalse(clusterMasterExecutorImpl.isEnabled());

		clusterMasterExecutorImpl.destroy();
	}

	@Test
	public void testDestroyClusterLinkEnabled() throws Exception {
		ClusterMasterExecutorImpl clusterMasterExecutorImpl =
			new ClusterMasterExecutorImpl();

		ClusterExecutor mockClusterExecutor = new MockClusterExecutor(true);

		clusterMasterExecutorImpl.setClusterExecutor(mockClusterExecutor);
		clusterMasterExecutorImpl.initialize();

		Assert.assertTrue(clusterMasterExecutorImpl.isEnabled());

		clusterMasterExecutorImpl.destroy();
	}

	@Test
	public void testDestroyLogWarning() throws Exception {
		CaptureHandler captureHandler = JDKLoggerTestUtil.configureJDKLogger(
			ClusterMasterExecutorImpl.class.getName(), Level.WARNING);

		try {
			List<LogRecord> logRecords = captureHandler.getLogRecords();

			ClusterMasterExecutorImpl clusterMasterExecutorImpl =
				new ClusterMasterExecutorImpl();

			ClusterExecutor mockClusterExecutor = new MockClusterExecutor(true);

			clusterMasterExecutorImpl.setClusterExecutor(mockClusterExecutor);
			clusterMasterExecutorImpl.initialize();

			Assert.assertTrue(clusterMasterExecutorImpl.isEnabled());

			_mockLockLocalService.setUnlockError(true);

			clusterMasterExecutorImpl.destroy();

			Assert.assertEquals(1, logRecords.size());

			LogRecord logRecord = logRecords.get(0);

			Assert.assertEquals(
				"Unable to destroy the cluster master executor",
				logRecord.getMessage());

			logRecords = captureHandler.resetLogLevel(Level.OFF);

			clusterMasterExecutorImpl.destroy();

			Assert.assertTrue(logRecords.isEmpty());
		}
		finally {
			captureHandler.close();
		}
	}

	@Test
	public void testExecuteOnMasterDisabled() throws Exception {
		CaptureHandler captureHandler = JDKLoggerTestUtil.configureJDKLogger(
			ClusterMasterExecutorImpl.class.getName(), Level.WARNING);

		try {
			List<LogRecord> logRecords = captureHandler.getLogRecords();

			ClusterMasterExecutorImpl clusterMasterExecutorImpl =
				new ClusterMasterExecutorImpl();

			ClusterExecutor mockClusterExecutor = new MockClusterExecutor(true);

			clusterMasterExecutorImpl.setClusterExecutor(mockClusterExecutor);

			Assert.assertFalse(clusterMasterExecutorImpl.isEnabled());

			String timeString = String.valueOf(System.currentTimeMillis());

			MethodHandler methodHandler = new MethodHandler(
				testMethodMethodKey, timeString);

			NoticeableFuture<String> noticeableFuture =
				clusterMasterExecutorImpl.executeOnMaster(methodHandler);

			Assert.assertSame(timeString, noticeableFuture.get());
			Assert.assertEquals(1, logRecords.size());

			LogRecord logRecord = logRecords.get(0);

			Assert.assertEquals(
				"Executing on the local node because the cluster master " +
					"executor is disabled",
				logRecord.getMessage());

			logRecords = captureHandler.resetLogLevel(Level.OFF);

			noticeableFuture = clusterMasterExecutorImpl.executeOnMaster(
				methodHandler);

			Assert.assertSame(timeString, noticeableFuture.get());
			Assert.assertTrue(logRecords.isEmpty());
		}
		finally {
			captureHandler.close();
		}
	}

	@Test
	public void testExecuteOnMasterEnabled() throws Exception {
		PortalUUIDUtil portalUUIDUtil = new PortalUUIDUtil();
		portalUUIDUtil.setPortalUUID(new PortalUUIDImpl());

		ClusterMasterExecutorImpl clusterMasterExecutorImpl =
			new ClusterMasterExecutorImpl();

		ClusterExecutor mockClusterExecutor = new MockClusterExecutor(true);

		clusterMasterExecutorImpl.setClusterExecutor(mockClusterExecutor);
		clusterMasterExecutorImpl.initialize();

		Assert.assertTrue(clusterMasterExecutorImpl.isEnabled());

		String timeString = String.valueOf(System.currentTimeMillis());

		MethodHandler methodHandler = new MethodHandler(
			testMethodMethodKey, timeString);

		NoticeableFuture<String> noticeableFuture =
			clusterMasterExecutorImpl.executeOnMaster(methodHandler);

		Assert.assertSame(timeString, noticeableFuture.get());
	}

	@Test
	public void testExecuteOnMasterFailsOnLocal() throws Exception {
		ClusterMasterExecutorImpl clusterMasterExecutorImpl =
			new ClusterMasterExecutorImpl();

		ClusterExecutor mockClusterExecutor = new MockClusterExecutor(true);

		clusterMasterExecutorImpl.setClusterExecutor(mockClusterExecutor);

		Assert.assertFalse(clusterMasterExecutorImpl.isEnabled());

		MethodHandler methodHandler = new MethodHandler(
			testMethodMethodKey, null);

		CaptureHandler captureHandler = JDKLoggerTestUtil.configureJDKLogger(
			ClusterMasterExecutorImpl.class.getName(), Level.WARNING);

		try {
			clusterMasterExecutorImpl.executeOnMaster(methodHandler);

			Assert.fail();
		}

		catch (SystemException e) {
		}
		finally {
			captureHandler.close();
		}
	}

	@Test
	public void testExecuteOnMasterFailsOnMaster() throws Exception {
		PortalUUIDUtil portalUUIDUtil = new PortalUUIDUtil();
		portalUUIDUtil.setPortalUUID(new PortalUUIDImpl());

		ClusterMasterExecutorImpl clusterMasterExecutorImpl =
			new ClusterMasterExecutorImpl();

		ClusterExecutor mockClusterExecutor = new MockClusterExecutor(true);

		clusterMasterExecutorImpl.setClusterExecutor(mockClusterExecutor);
		clusterMasterExecutorImpl.initialize();

		MethodHandler methodHandler = new MethodHandler(
			testMethodMethodKey, null);

		try {
			MockClusterExecutor.setBreak(true);

			clusterMasterExecutorImpl.executeOnMaster(methodHandler);

			Assert.fail();
		}
		catch (SystemException se) {
			Assert.assertEquals(
				"Unable to execute on master " +
					_LOCAL_ADDRESS.getDescription(),
				se.getMessage());
		}
		finally {
			MockClusterExecutor.setBreak(false);
		}
	}

	@Test
	public void testGetMasterAddressStringOtherOwner() {
		CaptureHandler captureHandler = JDKLoggerTestUtil.configureJDKLogger(
			ClusterMasterExecutorImpl.class.getName(), Level.INFO);

		try {
			List<LogRecord> logRecords = captureHandler.getLogRecords();

			ClusterMasterExecutorImpl clusterMasterExecutorImpl =
				new ClusterMasterExecutorImpl();

			ClusterExecutor mockClusterExecutor = new MockClusterExecutor(true);

			clusterMasterExecutorImpl.setClusterExecutor(mockClusterExecutor);
			clusterMasterExecutorImpl.initialize();

			// Set a different lock owner

			String otherOwner = AddressSerializerUtil.serialize(_OTHER_ADDRESS);

			_mockLockLocalService.setLock(otherOwner);

			String owner = clusterMasterExecutorImpl.getMasterAddressString();

			Assert.assertEquals(
				_LOCAL_ADDRESS, AddressSerializerUtil.deserialize(owner));
			Assert.assertEquals(2, logRecords.size());

			LogRecord logRecord = logRecords.get(0);

			String message = logRecord.getMessage();

			Assert.assertEquals(
				"Lock currently held by " + otherOwner, message);

			logRecord = logRecords.get(1);

			message = logRecord.getMessage();

			Assert.assertEquals(
				"Reattempting to acquire the cluster master lock", message);

			logRecords = captureHandler.resetLogLevel(Level.INFO);

			// No lock owner

			_mockLockLocalService.setLock(null);

			owner = clusterMasterExecutorImpl.getMasterAddressString();

			Assert.assertEquals(
				_LOCAL_ADDRESS, AddressSerializerUtil.deserialize(owner));
			Assert.assertEquals(2, logRecords.size());

			logRecord = logRecords.get(0);

			Assert.assertEquals(
				"Unable to acquire the cluster master lock",
				logRecord.getMessage());

			logRecords = captureHandler.resetLogLevel(Level.OFF);

			_mockLockLocalService.setLock(null);

			owner = clusterMasterExecutorImpl.getMasterAddressString();

			Assert.assertEquals(
				_LOCAL_ADDRESS, AddressSerializerUtil.deserialize(owner));
			Assert.assertTrue(logRecords.isEmpty());
		}
		finally {
			captureHandler.close();
		}
	}

	@Test
	public void testGetMasterAddressStringReleaseMaster() {
		ClusterMasterExecutorImpl clusterMasterExecutorImpl =
			new ClusterMasterExecutorImpl();

		ClusterExecutor mockClusterExecutor = new MockClusterExecutor(true);

		clusterMasterExecutorImpl.setClusterExecutor(mockClusterExecutor);

		ClusterMasterTokenTransitionListener
			clusterMasterTokenTransitionListener =
				new MockClusterMasterTokenTransitionListener();

		clusterMasterExecutorImpl.registerClusterMasterTokenTransitionListener(
			clusterMasterTokenTransitionListener);

		clusterMasterExecutorImpl.initialize();

		Assert.assertTrue(clusterMasterExecutorImpl.isMaster());

		MockClusterExecutor.addClusterNodeAddress(_OTHER_ADDRESS);

		String otherOwner = AddressSerializerUtil.serialize(_OTHER_ADDRESS);

		_mockLockLocalService.setLock(otherOwner);

		clusterMasterExecutorImpl.getMasterAddressString();

		Assert.assertFalse(clusterMasterExecutorImpl.isMaster());
	}

	@Test
	public void testGetMasterAddressStringSuccess() {
		ClusterMasterExecutorImpl clusterMasterExecutorImpl =
			new ClusterMasterExecutorImpl();

		ClusterExecutor mockClusterExecutor = new MockClusterExecutor(true);

		clusterMasterExecutorImpl.setClusterExecutor(mockClusterExecutor);

		clusterMasterExecutorImpl.initialize();

		ClusterMasterTokenTransitionListener
			clusterMasterTokenTransitionListener =
				new MockClusterMasterTokenTransitionListener() {

					@Override
					public void masterTokenReleased() {
						Assert.fail();
					}

				};

		clusterMasterExecutorImpl.registerClusterMasterTokenTransitionListener(
			clusterMasterTokenTransitionListener);

		String owner = clusterMasterExecutorImpl.getMasterAddressString();

		Assert.assertEquals(
			_LOCAL_ADDRESS,  AddressSerializerUtil.deserialize(owner));
	}

	@Test
	public void testInitClusterLinkDisabled() {
		ClusterMasterExecutorImpl clusterMasterExecutorImpl =
			new ClusterMasterExecutorImpl();

		ClusterExecutor mockClusterExecutor = new MockClusterExecutor(false);

		clusterMasterExecutorImpl.setClusterExecutor(mockClusterExecutor);

		clusterMasterExecutorImpl.initialize();

		Assert.assertFalse(clusterMasterExecutorImpl.isEnabled());
		Assert.assertTrue(clusterMasterExecutorImpl.isMaster());
	}

	@Test
	public void testInitClusterLinkEnabled() {
		ClusterMasterExecutorImpl clusterMasterExecutorImpl =
			new ClusterMasterExecutorImpl();

		ClusterExecutor mockClusterExecutor = new MockClusterExecutor(true);

		clusterMasterExecutorImpl.setClusterExecutor(mockClusterExecutor);

		Assert.assertTrue(clusterMasterExecutorImpl.isMaster());

		clusterMasterExecutorImpl.initialize();

		Assert.assertTrue(clusterMasterExecutorImpl.isEnabled());
		Assert.assertTrue(clusterMasterExecutorImpl.isMaster());
	}

	@Test
	public void testNotifyMasterTokenTransitionListenersTokenAcquired() {
		doNotifyMasterTokenTransitionListeners(true);
	}

	@Test
	public void testNotifyMasterTokenTransitionListenersTokenReleased() {
		doNotifyMasterTokenTransitionListeners(false);
	}

	protected void doNotifyMasterTokenTransitionListeners(boolean acquired) {
		ClusterMasterExecutorImpl clusterMasterExecutorImpl =
			new ClusterMasterExecutorImpl();

		ClusterMasterTokenTransitionListener
			clusterMasterTokenTransitionListener = null;

		if (acquired) {
			clusterMasterTokenTransitionListener =
				new MockClusterMasterTokenTransitionListener() {

				@Override
				public void masterTokenReleased() {
					Assert.fail();
				}

			};
		}
		else {
			clusterMasterTokenTransitionListener =
				new MockClusterMasterTokenTransitionListener() {

				@Override
				public void masterTokenAcquired() {
					Assert.fail();
				}

			};
		}

		clusterMasterExecutorImpl.registerClusterMasterTokenTransitionListener(
			clusterMasterTokenTransitionListener);
		clusterMasterExecutorImpl.notifyMasterTokenTransitionListeners(
			acquired);
	}

	protected static MethodKey testMethodMethodKey = new MethodKey(
		TestBean.class, "testMethod1", String.class);

	private static final Address _LOCAL_ADDRESS = new AddressImpl(
		new MockAddress("_LOCAL_ADDRESS"));

	private static final Address _OTHER_ADDRESS = new AddressImpl(
		new MockAddress("_OTHER_ADDRESS"));

	private MockLockLocalService _mockLockLocalService;

	private static class MockAddress implements org.jgroups.Address {

		public MockAddress() {
		}

		public MockAddress(String name) {
			_name = name;
		}

		@Override
		public int compareTo(org.jgroups.Address jGroupsAddress) {
			return 0;
		}

		@Override
		public boolean equals(Object object) {
			if (this == object) {
				return true;
			}

			if (!(object instanceof MockAddress)) {
				return false;
			}

			MockAddress mockAddress = (MockAddress)object;

			if (_name.equals(mockAddress._name)) {
				return true;
			}

			return false;
		}

		public String getName() {
			return _name;
		}

		@Override
		public int hashCode() {
			return _name.hashCode();
		}

		@Override
		public void readExternal(ObjectInput objectInput) throws IOException {
			_name = objectInput.readUTF();
		}

		@Override
		public void readFrom(DataInput dataInput) throws Exception {
			_name = dataInput.readUTF();
		}

		@Override
		public int size() {
			return 0;
		}

		@Override
		public void writeExternal(ObjectOutput objectOutput)
			throws IOException {

			objectOutput.writeUTF(_name);
		}

		@Override
		public void writeTo(DataOutput dataOutput) throws Exception {
			dataOutput.writeUTF(_name);
		}

		private String _name;

	}

	private static class MockClusterExecutor implements ClusterExecutor {

		public static void addClusterNodeAddress(Address address) {
			_addresses.add(address);
		}

		public static void setBreak(boolean brk) {
			_break = brk;
		}

		public MockClusterExecutor(boolean enabled) {
			_enabled = enabled;

			_localAddress = _LOCAL_ADDRESS;

			_addresses.add(_localAddress);
		}

		@Override
		public void addClusterEventListener(
			ClusterEventListener clusterEventListener) {

			_clusterEventListeners.add(clusterEventListener);
		}

		@Override
		public void destroy() {
			_addresses.clear();
		}

		@Override
		public FutureClusterResponses execute(ClusterRequest clusterRequest) {
			if (_break) {
				return null;
			}

			List<Address> addresses = new ArrayList<Address>();

			Collection<Address> clusterNodeAddresses =
				clusterRequest.getTargetClusterNodeAddresses();

			if (clusterNodeAddresses != null) {
				addresses.addAll(clusterNodeAddresses);
			}

			FutureClusterResponses futureClusterResponses =
				new FutureClusterResponses(addresses);

			for (Address address : addresses) {
				ClusterNodeResponse clusterNodeResponse =
					new ClusterNodeResponse();

				clusterNodeResponse.setAddress(address);
				clusterNodeResponse.setClusterMessageType(
					ClusterMessageType.EXECUTE);
				clusterNodeResponse.setMulticast(clusterRequest.isMulticast());
				clusterNodeResponse.setUuid(clusterRequest.getUuid());

				MockAddress mockAddress = (MockAddress)address.getRealAddress();

				try {
					clusterNodeResponse.setClusterNode(
						new ClusterNode(
							String.valueOf(mockAddress.getName()),
							InetAddress.getLocalHost()));

					clusterNodeResponse.setResult(
						(clusterRequest.getMethodHandler().invoke()));
				}
				catch (Exception e) {
				}

				futureClusterResponses.addClusterNodeResponse(
					clusterNodeResponse);
			}

			return futureClusterResponses;
		}

		@Override
		public FutureClusterResponses execute(
			ClusterRequest clusterRequest,
			ClusterResponseCallback clusterResponseCallback) {

			return null;
		}

		@Override
		public List<ClusterEventListener> getClusterEventListeners() {
			return Collections.unmodifiableList(_clusterEventListeners);
		}

		@Override
		public List<Address> getClusterNodeAddresses() {
			return Collections.unmodifiableList(_addresses);
		}

		@Override
		public List<ClusterNode> getClusterNodes() {
			return Collections.emptyList();
		}

		@Override
		public ClusterNode getLocalClusterNode() {
			return null;
		}

		@Override
		public Address getLocalClusterNodeAddress() {
			return _localAddress;
		}

		@Override
		public void initialize() {
		}

		@Override
		public boolean isClusterNodeAlive(Address address) {
			return _addresses.contains(address);
		}

		@Override
		public boolean isClusterNodeAlive(String clusterNodeId) {
			return false;
		}

		@Override
		public boolean isEnabled() {
			return _enabled;
		}

		@Override
		public void removeClusterEventListener(
			ClusterEventListener clusterEventListener) {

			_clusterEventListeners.remove(clusterEventListener);
		}

		private static final List<Address> _addresses =
			new ArrayList<Address>();
		private static boolean _break;

		private final List<ClusterEventListener> _clusterEventListeners =
			new ArrayList<ClusterEventListener>();
		private final boolean _enabled;
		private final Address _localAddress;

	}

	private static class MockClusterMasterTokenTransitionListener
		implements ClusterMasterTokenTransitionListener {

		public MockClusterMasterTokenTransitionListener() {
		}

		@Override
		public void masterTokenAcquired() {
		}

		@Override
		public void masterTokenReleased() {
		}

	}

	private static class MockLockLocalService extends LockLocalServiceImpl {

		public Lock getLock() {
			return _lock;
		}

		@Override
		public Lock lock(String className, String key, String owner) {
			if (_lock == null) {
				Lock lock = new LockImpl();

				lock.setKey(key);
				lock.setOwner(owner);

				_lock = lock;
			}

			return _lock;
		}

		@Override
		public Lock lock(
			String className, String key, String expectedOwner,
			String updatedOwner) {

			Lock lock = new LockImpl();

			lock.setKey(key);
			lock.setOwner(updatedOwner);

			_lock = lock;

			return lock;
		}

		public void setLock(String owner) {
			Lock lock = new LockImpl();

			lock.setOwner(owner);

			_lock = lock;
		}

		public void setUnlockError(boolean error) {
			_errorOnUnlock = error;
		}

		@Override
		public void unlock(String className, String key, String owner) {
			if (_errorOnUnlock) {
				throw new SystemException();
			}

			_lock = null;
		}

		private boolean _errorOnUnlock;
		private Lock _lock;

	}

}