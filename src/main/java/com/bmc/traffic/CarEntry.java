package com.bmc.traffic;

import java.util.ArrayList;
import java.util.List;

public class CarEntry
{
	Direction direction;
	List <Record> recordEntries = new ArrayList<Record>();

	
	public CarEntry(Direction direction, List<Record> recordEntries)
	{
		super();
		this.direction = direction;
		this.recordEntries.addAll(recordEntries);
	}

	public List<Record> getRecordEntries()
	{
		return recordEntries;
	}

	public void setRecordEntries(List<Record> recordEntries)
	{
		this.recordEntries = recordEntries;
	}

	public Direction getDirection()
	{
		return direction;
	}

	public void setDirection(Direction direction)
	{
		this.direction = direction;
	}

	@Override
	public String toString()
	{
		return "CarEntry [direction=" + direction + ", recordEntries=" + recordEntries + "]";
	}
	
	
	
}
