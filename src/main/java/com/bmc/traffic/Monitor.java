package com.bmc.traffic;

import java.util.ArrayList;
import java.util.List;

import com.bmc.traffic.monitorState.FrontAxelPassedBothSensorsState;
import com.bmc.traffic.monitorState.FrontAxelPassedState;
import com.bmc.traffic.monitorState.InitialState;
import com.bmc.traffic.monitorState.MonitorState;
import com.bmc.traffic.monitorState.RearAxelPassedFirstSensorState;
import com.bmc.traffic.reference.Direction;

public class Monitor implements MonitorState
{
	private Record previousRecord;
	int day = 1;
	List <Record> recordEntries = new ArrayList<Record>();
	List <CarEntry> carEntries = new ArrayList<CarEntry>();

	MonitorState initialState = new InitialState(this);
	MonitorState frontAxelPassedState = new FrontAxelPassedState(this);
	MonitorState frontAxelPassedBothSensorsState = new FrontAxelPassedBothSensorsState(this);
	MonitorState rearAxelPassedFirstSensorState = new RearAxelPassedFirstSensorState(this);


	MonitorState state = new InitialState(this);


	public List<Record> getRecordEntries()
	{
		return recordEntries;
	}

	public void processSenorAEntry()
	{
		getState().processSenorAEntry();

	}

	public void processSenorBEntry()
	{
		getState().processSenorBEntry();
	}

	public MonitorState getState()
	{
		return state;
	}


	public void setState(MonitorState state)
	{
		this.state = state;
	}

	public MonitorState getInitialState()
	{
		return initialState;
	}


	public MonitorState getFrontAxelPassedState()
	{
		return frontAxelPassedState;
	}


	public MonitorState getFrontAxelPassedBothSensorsState()
	{
		return frontAxelPassedBothSensorsState;
	}


	public MonitorState getRearAxelPassedFirstSensorState()
	{
		return rearAxelPassedFirstSensorState;
	}


	public void setaPassedState(MonitorState aPassedState)
	{
		this.frontAxelPassedState = aPassedState;
	}


	public void process(Record record)
	{
		Record aRecord = record;
		if( notTheFirstRecord() && record.crossedIntoNextDay(previousRecord))
		{
			day++;
		}
		
		recordEntries.add(record.advanceFor(day));

		switch (record.getSensor())
		{
		case A:
			state.processSenorAEntry();
			break;

		case B:
			state.processSenorBEntry();
			break;
		}
		
		previousRecord = record;
	}




	private boolean notTheFirstRecord() {
		return previousRecord!= null;
	}


	protected Record getFirst()
	{
		return recordEntries.get(0);
	}


	public List<CarEntry> getCarEntries()
	{
		return carEntries;
	}


	public void recordCarEntry(Direction direction)
	{
		carEntries.add(new CarEntry(direction,recordEntries));
		recordEntries.clear();
	}


}
