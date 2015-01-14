package com.bmc.traffic.monitorState;

import com.bmc.traffic.Monitor;

public class InitialState implements MonitorState
{
	public Monitor monitor;

	
	public InitialState(Monitor monitor)
	{
		this.monitor = monitor;
	}

	public void processSenorAEntry()
	{
		monitor.setState(monitor.getFrontAxelPassedState());

	}

	public void processSenorBEntry()
	{
		throw new RuntimeException("Cannot Support first Entry of B");

	}

	
}
