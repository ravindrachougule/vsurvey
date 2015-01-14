package com.bmc.traffic.monitorState;

import com.bmc.traffic.Monitor;
import com.bmc.traffic.reference.Direction;

public class RearAxelPassedFirstSensorState implements MonitorState
{
	public Monitor monitor;

	
	public RearAxelPassedFirstSensorState(Monitor monitor)
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
