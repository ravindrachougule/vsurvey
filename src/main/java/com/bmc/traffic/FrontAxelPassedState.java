package com.bmc.traffic;

public class FrontAxelPassedState implements MonitorState
{
	public Monitor monitor;

	
	FrontAxelPassedState(Monitor monitor)
	{
		this.monitor = monitor;
	}

	public void processSenorAEntry()
	{
		monitor.setState(monitor.getInitialState());
		monitor.recordCarEntry(Direction.NorthBound);
	}

	public void processSenorBEntry()
	{
		monitor.setState(monitor.getFrontAxelPassedBothSensorsState());

	}

	
}
