package com.bmc.traffic;

public class ABPassedState implements MonitorState
{
	public Monitor monitor;

	
	ABPassedState(Monitor monitor)
	{
		this.monitor = monitor;
	}

	public void processSenorAEntry()
	{
		monitor.setState(monitor.getAbaPassedState());

	}

	public void processSenorBEntry()
	{
		throw new RuntimeException("Cannot Support second Entry of B after AB");

	}

	
}
