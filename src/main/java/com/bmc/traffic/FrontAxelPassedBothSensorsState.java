package com.bmc.traffic;

public class FrontAxelPassedBothSensorsState implements MonitorState
{
	public Monitor monitor;

	
	FrontAxelPassedBothSensorsState(Monitor monitor)
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
