package com.bmc.traffic;

public class APassedState implements MonitorState
{
	public Monitor monitor;

	
	APassedState(Monitor monitor)
	{
		this.monitor = monitor;
	}

	public void processSenorAEntry()
	{
		monitor.setState(monitor.getNoCarYetPassedState());
		monitor.recordCarEntry(Direction.NorthBound);
	}

	public void processSenorBEntry()
	{
		monitor.setState(monitor.getAbPassedState());

	}

	
}
