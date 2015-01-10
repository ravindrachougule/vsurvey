package com.bmc.traffic;

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

	
}
