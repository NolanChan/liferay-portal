/*
 * Cobertura - http://cobertura.sourceforge.net/
 *
 * Copyright (C) 2006 Jiri Mares
 *
 * Cobertura is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published
 * by the Free Software Foundation; either version 2 of the License,
 * or (at your option) any later version.
 *
 * Cobertura is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Cobertura; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 * USA
 */

package com.liferay.cobertura.coveragedata;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 * This class implements HasBeenInstrumented so that when cobertura instruments
 * itself, it will omit this class. It does this to avoid an infinite recursion
 * problem because instrumented classes make use of this class.
 * </p>
 */
public class SwitchData implements BranchCoverageData, Serializable
{
	private static final long serialVersionUID = 9;

	private transient Lock lock;

	private int switchNumber;

	private long defaultHits;

	private long[] hits;

	public SwitchData(int switchNumber, int caseNumber)
	{
		this.switchNumber = switchNumber;
		hits = new long[caseNumber];
		initLock();
	}

	private void initLock()
	{
		 lock = new ReentrantLock();
	}

	public void touchBranch(
		String className, int lineNumber, int branch,int new_hits) {

		lock.lock();
		try
		{
			if (branch >= hits.length) {
				throw new IllegalStateException(
				"No instrument data for class " + className + " line " +
					lineNumber + " switch " + switchNumber + " branch " +
						branch);
			}

			if (branch == -1) {
				defaultHits++;
			} else {
				hits[branch] += new_hits;
			}
		}
		finally
		{
			lock.unlock();
		}
	}

	public double getBranchCoverageRate()
	{
		lock.lock();
		try
		{
			int branches = hits.length + 1;
			int hit = (defaultHits > 0) ? 1 : 0;
			for (int i = hits.length - 1; i >= 0; hit += ((hits[i--] > 0) ? 1 : 0));
			return ((double) hit) / branches;
		}
		finally
		{
			lock.unlock();
		}
	}

	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if ((obj == null) || !(obj.getClass().equals(this.getClass())))
			return false;

		SwitchData switchData = (SwitchData) obj;
		getBothLocks(switchData);
		try
		{
			return (this.defaultHits == switchData.defaultHits)
					&& (Arrays.equals(this.hits, switchData.hits))
					&& (this.switchNumber == switchData.switchNumber);
		}
		finally
		{
			lock.unlock();
			switchData.lock.unlock();
		}
	}

	public int hashCode()
	{
		return this.switchNumber;
	}

	public int getNumberOfCoveredBranches()
	{
		lock.lock();
		try
		{
			int ret = (defaultHits > 0) ? 1 : 0;
			for (int i = hits.length -1; i >= 0;i--)
			{
				if (hits[i] > 0) ret++;
			}
			return ret;
		}
		finally
		{
			lock.unlock();
		}
	}

	public int getNumberOfValidBranches()
	{
		lock.lock();
		try
		{
			return hits.length + 1;
		}
		finally
		{
			lock.unlock();
		}
	}

	public int getSwitchNumber() {
		return switchNumber;
	}

	public void merge(BranchCoverageData coverageData)
	{
		SwitchData switchData = (SwitchData) coverageData;
		getBothLocks(switchData);
		try
		{
			defaultHits += switchData.defaultHits;

			if (hits.length != switchData.hits.length) {
				throw new IllegalArgumentException(
					"Switch case number mismatch");
			}

			for (int i = 0; i < hits.length; i++) {
				hits[i] += switchData.hits[i];
			}
		}
		finally
		{
			lock.unlock();
			switchData.lock.unlock();
		}

	}

	private void getBothLocks(SwitchData other) {
		/*
		 * To prevent deadlock, we need to get both locks or none at all.
		 *
		 * When this method returns, the thread will have both locks.
		 * Make sure you unlock them!
		 */
		boolean myLock = false;
		boolean otherLock = false;
		while ((!myLock) || (!otherLock))
		{
			try
			{
				myLock = lock.tryLock();
				otherLock = other.lock.tryLock();
			}
			finally
			{
				if ((!myLock) || (!otherLock))
				{
					//could not obtain both locks - so unlock the one we got.
					if (myLock)
					{
						lock.unlock();
					}
					if (otherLock)
					{
						other.lock.unlock();
					}
					//do a yield so the other threads will get to work.
					Thread.yield();
				}
			}
		}
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
	{
		in.defaultReadObject();
		initLock();
	}
}
