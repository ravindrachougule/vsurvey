package com.bmc.traffic;

public class InitialState implements MonitorState
{
	public Monitor monitor;

	
	InitialState(Monitor monitor)
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
		//monitor.setState(monitor.getAbPassedState());

	}

	
}
