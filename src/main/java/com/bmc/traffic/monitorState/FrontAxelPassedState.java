package com.bmc.traffic.monitorState;

import com.bmc.traffic.Monitor;
import com.bmc.traffic.reference.Direction;

public class FrontAxelPassedState implements MonitorState
{
	public Monitor monitor;

	
	public FrontAxelPassedState(Monitor monitor)
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
