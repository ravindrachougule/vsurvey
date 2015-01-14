package com.bmc.traffic.monitorState;

import com.bmc.traffic.Monitor;

public class FrontAxelPassedBothSensorsState implements MonitorState
{
	public Monitor monitor;

	
	public FrontAxelPassedBothSensorsState(Monitor monitor)
	{
		this.monitor = monitor;
	}

	public void processSenorAEntry()
	{
		monitor.setState(monitor.getRearAxelPassedFirstSensorState());

	}

	public void processSenorBEntry()
	{
		throw new RuntimeException("Cannot Support second Entry of B after AB");

	}

	
}
