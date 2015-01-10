package com.bmc.traffic;

public class NoCarYetPassedState implements MonitorState
{
	public Monitor monitor;

	
	NoCarYetPassedState(Monitor monitor)
	{
		this.monitor = monitor;
	}

	public void processSenorAEntry()
	{
		monitor.setState(monitor.getaPassedState());

	}

	public void processSenorBEntry()
	{
		throw new RuntimeException("Cannot Support first Entry of B");
		//monitor.setState(monitor.getAbPassedState());

	}

	
}
