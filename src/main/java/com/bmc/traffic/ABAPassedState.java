package com.bmc.traffic;

public class ABAPassedState implements MonitorState
{
	public Monitor monitor;

	
	ABAPassedState(Monitor monitor)
	{
		this.monitor = monitor;
	}

	public void processSenorAEntry()
	{
		throw new RuntimeException("Cannot Support third Entry of A after ABA");

	}

	public void processSenorBEntry()
	{
		monitor.setState(monitor.getNoCarYetPassedState());
		monitor.recordCarEntry(Direction.SouthBound);

	}

	
}
