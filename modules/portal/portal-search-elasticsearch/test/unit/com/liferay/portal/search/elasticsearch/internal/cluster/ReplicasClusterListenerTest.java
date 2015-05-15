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

package com.liferay.portal.search.elasticsearch.internal.cluster;

import com.liferay.portal.kernel.cluster.ClusterEvent;
import com.liferay.portal.kernel.test.util.RandomTestUtil;

import org.junit.Before;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * @author André de Oliveira
 */
public class ReplicasClusterListenerTest {

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		setEmbeddedCluster(true);
		setMasterExecutor(true);

		Mockito.when(
			_replicasClusterContext.getElasticsearchTargetIndexNames()
		).thenReturn(
			_INDICES
		);

		Mockito.when(
			_replicasClusterContext.getClusterSize()
		).thenReturn(
			_REPLICAS + 1
		);

		Mockito.when(
			_replicasClusterContext.getReplicasManager()
		).thenReturn(
			_replicasManager
		);

		_replicasClusterListener = new ReplicasClusterListener(
			_replicasClusterContext);
	}

	@Test
	public void testAHappyDay() {
		processClusterEvent();
		assertReplicasChanged();
	}

	@Test
	public void testLiferayClusterReportsEmpty() {
		Mockito.when(
			_replicasClusterContext.getClusterSize()
		).thenReturn(
			0
		);

		processClusterEvent();

		Mockito.verify(
			_replicasManager
		).updateNumberOfReplicas(
			0, _INDICES
		);
	}

	@Test
	public void testMasterTokenAcquired() {
		masterTokenAcquired();
		assertReplicasChanged();
	}

	@Test
	public void testMasterTokenReleased() {
		masterTokenReleased();
		assertReplicasUnchanged();
	}

	@Test
	public void testNonMasterLiferayNodeDoesNothing() {
		setMasterExecutor(false);
		processClusterEvent();
		assertReplicasUnchanged();
	}

	@Test
	public void testRemoteElasticsearchClusterIsLeftAlone() {
		setEmbeddedCluster(false);
		processClusterEvent();
		assertReplicasUnchanged();
	}

	@Test
	public void testResilientToUpdateFailures() {
		Mockito.doThrow(
			Exception.class
		).when(
			_replicasManager).updateNumberOfReplicas(
				Mockito.anyInt(), (String[])Mockito.anyVararg()
		);

		masterTokenAcquired();
	}

	protected void assertReplicasChanged() {
		Mockito.verify(
			_replicasManager
		).updateNumberOfReplicas(
			_REPLICAS, _INDICES
		);
	}

	protected void assertReplicasUnchanged() {
		Mockito.verify(
			_replicasManager, Mockito.never()
		).updateNumberOfReplicas(
			Mockito.anyInt(), (String[])Mockito.anyVararg()
		);
	}

	protected void masterTokenAcquired() {
		_replicasClusterListener.masterTokenAcquired();
	}

	protected void masterTokenReleased() {
		_replicasClusterListener.masterTokenReleased();
	}

	protected void processClusterEvent() {
		_replicasClusterListener.processClusterEvent(ClusterEvent.join());
	}

	protected void setEmbeddedCluster(boolean value) {
		Mockito.when(
			_replicasClusterContext.isElasticsearchEmbeddedCluster()
		).thenReturn(
			value
		);
	}

	protected void setMasterExecutor(boolean value) {
		Mockito.when(
			_replicasClusterContext.isOnMasterExecutor()
		).thenReturn(
			value
		);
	}

	private static final String[] _INDICES = new String[] {
		RandomTestUtil.randomString(), RandomTestUtil.randomString()
	};

	private static final int _REPLICAS = RandomTestUtil.randomInt() - 1;

	@Mock
	private ReplicasClusterContext _replicasClusterContext;

	private ReplicasClusterListener _replicasClusterListener;

	@Mock
	private ReplicasManager _replicasManager;

}