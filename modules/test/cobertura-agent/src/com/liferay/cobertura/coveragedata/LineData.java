package com.liferay.cobertura.coveragedata;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

public class LineData
		implements Comparable, CoverageData, Serializable
{
	private static final long serialVersionUID = 4;

	private final AtomicLong _hitCounter = new AtomicLong();
	private final ConcurrentMap<Integer, JumpData> _jumpDatas =
		new ConcurrentHashMap<>();
	private final ConcurrentMap<Integer, SwitchData> _switchDatas =
		new ConcurrentHashMap<>();
	private final int lineNumber;

	public LineData(int lineNumber)
	{
		this.lineNumber = lineNumber;
	}

	public int compareTo(Object o)
	{
		if (!o.getClass().equals(LineData.class))
			return Integer.MAX_VALUE;
		return this.lineNumber - ((LineData)o).lineNumber;
	}

	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if ((obj == null) || !(obj.getClass().equals(this.getClass())))
			return false;

		LineData lineData = (LineData)obj;

			return (_hitCounter.get() == lineData._hitCounter.get())
					&& ((this._jumpDatas == lineData._jumpDatas) || (this._jumpDatas.equals(lineData._jumpDatas)))
					&& ((this._switchDatas == lineData._switchDatas) || ((this._switchDatas != null) && (this._switchDatas.equals(lineData._switchDatas))))
					&& (this.lineNumber == lineData.lineNumber);
	}

	public double getBranchCoverageRate()
	{
		if (getNumberOfValidBranches() == 0)
			return 1d;

			return ((double) getNumberOfCoveredBranches()) / getNumberOfValidBranches();
	}

	public boolean isCovered()
	{
		return (_hitCounter.get() > 0) && ((getNumberOfValidBranches() == 0) || ((1.0 - getBranchCoverageRate()) < 0.0001));
	}

	public double getLineCoverageRate()
	{
		return (_hitCounter.get() > 0) ? 1 : 0;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public int getNumberOfCoveredLines()
	{
		return (_hitCounter.get() > 0) ? 1 : 0;
	}

	public int getNumberOfValidBranches()
	{
		int ret = 0;
			for (JumpData jumpData : _jumpDatas.values()) {
				ret += jumpData.getNumberOfValidBranches();
			}

			for (SwitchData switchData : _switchDatas.values()) {
				ret += switchData.getNumberOfValidBranches();
			}

			return ret;
	}

	public int getNumberOfCoveredBranches()
	{
		int ret = 0;
			for (JumpData jumpData : _jumpDatas.values()) {
				ret += jumpData.getNumberOfCoveredBranches();
			}

			for (SwitchData switchData : _switchDatas.values()) {
				ret += switchData.getNumberOfCoveredBranches();
			}

			return ret;
	}

	public int getNumberOfValidLines()
	{
		return 1;
	}

	public int hashCode()
	{
		return this.lineNumber;
	}

	public void merge(CoverageData coverageData)
	{
		LineData lineData = (LineData)coverageData;
			_hitCounter.addAndGet(lineData._hitCounter.get());

			ConcurrentMap<Integer, JumpData> otherJumpDatas =
				lineData._jumpDatas;

			for (JumpData jumpData : otherJumpDatas.values()) {
				JumpData previousJumpData = _jumpDatas.putIfAbsent(
					jumpData.getConditionNumber(), jumpData);

				if (previousJumpData != null) {
					previousJumpData.merge(jumpData);
				}
			}

			ConcurrentMap<Integer, SwitchData> otherSwitchDatas =
				lineData._switchDatas;

			for (SwitchData switchData : otherSwitchDatas.values()) {
				SwitchData previousSwitchData = _switchDatas.putIfAbsent(
					switchData.getSwitchNumber(), switchData);

				if (previousSwitchData != null) {
					previousSwitchData.merge(switchData);
				}
			}
	}

	public JumpData addJump(JumpData jumpData) {
		JumpData previousJumpData = _jumpDatas.putIfAbsent(
			jumpData.getConditionNumber(), jumpData);

		if (previousJumpData != null) {
			return previousJumpData;
		}

		return jumpData;
	}

	public SwitchData addSwitch(SwitchData switchData) {
		SwitchData previousSwitchData = _switchDatas.putIfAbsent(
			switchData.getSwitchNumber(), switchData);

		if (previousSwitchData != null) {
			return previousSwitchData;
		}

		return switchData;
	}

	public void touch(int new_hits) {
		_hitCounter.addAndGet(new_hits);
	}

	public void touchJump(
		String className, int jumpNumber, boolean branch,int hits) {

		JumpData jumpData = _jumpDatas.get(jumpNumber);

		if (jumpData == null) {
			throw new IllegalStateException(
				"No instrument data for class " + className + " line " +
					lineNumber + " jump " + jumpNumber);
		}

		jumpData.touchBranch(branch, hits);
	}

	public void touchSwitch(
		String className, int switchNumber, int branch,int hits) {

		SwitchData switchData = _switchDatas.get(switchNumber);

		if (switchData == null) {
			throw new IllegalStateException(
				"No instrument data for class " + className + " line " +
					lineNumber + " switch " + switchNumber);
		}

		switchData.touchBranch(className, lineNumber, branch, hits);
	}

}
