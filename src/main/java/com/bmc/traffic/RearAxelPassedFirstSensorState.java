package com.bmc.traffic;

public class RearAxelPassedFirstSensorState implements MonitorState
{
	public Monitor monitor;

	
	RearAxelPassedFirstSensorState(Monitor monitor)
	{
		this.monitor = monitor;
	}

	public void processSenorAEntry()
	{
		throw new RuntimeException("Cannot Support third Entry of A after ABA");

	}

	public void processSenorBEntry()
	{
		monitor.setState(monitor.getInitialState());
		monitor.recordCarEntry(Direction.SouthBound);

	}

	
}
