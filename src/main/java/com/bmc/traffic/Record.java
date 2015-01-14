package com.bmc.traffic;

import com.bmc.traffic.interval.IntervalFactory;
import com.bmc.traffic.reference.Sensor;

public class Record
{
	private Sensor sensor;
	private int timestamp;
	
	public Record(Sensor sensor, int timestamp)
	{
		super();
		this.sensor = sensor;
		this.timestamp = timestamp;
	}
	
	
	public Sensor getSensor()
	{
		return sensor;
	}
	public void setSensor(Sensor sensor)
	{
		this.sensor = sensor;
	}
	public int getTimestamp()
	{
		return timestamp;
	}
	public void setTimestamp(int timestamp)
	{
		this.timestamp = timestamp;
	}
	@Override
	public String toString()
	{
		return "Record [sensor=" + sensor + ", timestamp=" + timestamp + "]";
	}

	
	public Record advanceFor(int day) {
		int aTimestamp = timestamp;
		for (int i= 1; i<day;i++)
		{
			aTimestamp += IntervalFactory.midNight;
		}
		return new Record(sensor, aTimestamp);
	}


	public boolean crossedIntoNextDay(Record previousRecord) {
		return getTimestamp() < previousRecord.getTimestamp();
	}


	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sensor == null) ? 0 : sensor.hashCode());
		result = prime * result + timestamp;
		return result;
	}


	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Record other = (Record) obj;
		if (sensor != other.sensor)
			return false;
		if (timestamp != other.timestamp)
			return false;
		return true;
	}
	
	
}
