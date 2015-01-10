package com.bmc.traffic;

import java.util.ArrayList;
import java.util.List;

public class Monitor
{
	List <Record> recordEntries = new ArrayList<Record>();
	List <CarEntry> carEntries = new ArrayList<CarEntry>();

	MonitorState noCarYetPassedState = new NoCarYetPassedState(this);
	MonitorState aPassedState = new APassedState(this);
	MonitorState abPassedState = new ABPassedState(this);
	MonitorState abaPassedState = new ABAPassedState(this);


	MonitorState state = new NoCarYetPassedState(this);


	public List<Record> getRecordEntries()
	{
		return recordEntries;
	}


	public MonitorState getState()
	{
		return state;
	}


	public void setState(MonitorState state)
	{
		this.state = state;
	}

	public MonitorState getNoCarYetPassedState()
	{
		return noCarYetPassedState;
	}


	public MonitorState getaPassedState()
	{
		return aPassedState;
	}


	public MonitorState getAbPassedState()
	{
		return abPassedState;
	}


	public MonitorState getAbaPassedState()
	{
		return abaPassedState;
	}


	public void setaPassedState(MonitorState aPassedState)
	{
		this.aPassedState = aPassedState;
	}


	public void process(Record record)
	{
		recordEntries.add(record);

		switch (record.getSensor())
		{
		case A:
			state.processSenorAEntry();
			break;

		case B:
			state.processSenorBEntry();
			break;
		}
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
