package com.bmc.traffic.interval;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Interval {

	private int startTime;
	private int endTime;

	public Interval(int startTime, int endTime) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public boolean contains(int date) {
		if (startTime <= date && endTime >= date)
			return true;
		else
			return false;
	}

	public int getEndTime() {
		return endTime;
	}

	public int getStartTime() {
		return startTime;
	}

	public Interval advanceFor(int day) {
		int aStartTime = startTime;
		int aEndTime = endTime;
		for (int i= 1; i<day;i++)
		{
			aStartTime += IntervalFactory.midNight;
			aEndTime += IntervalFactory.midNight;
		}
		return new Interval(aStartTime, aEndTime);
	}

	public List<Interval> breakDown(int interval)
	{
		int aStartTime = startTime;
		List<Interval> result = new ArrayList<Interval>();
		
		while (aStartTime + interval <= endTime)
		{
			result.add(new Interval(aStartTime, aStartTime + interval));
			aStartTime += interval  ;
		}
		return result;
	}

	@Override
	public String toString()
	{
		return "Interval [startTime (ms)=" + startTime + ", endTime (ms)=" + endTime + "]";
	}
	
	

}





