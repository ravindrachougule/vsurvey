package com.bmc.traffic;

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

	public List<Interval> breakDownHourly()
	{
		int divideInto = 12;
		List<Interval> result = new ArrayList<Interval>();
		
		int totalMilliSec = endTime - startTime;
		
		int interval = 3600000;
		//divideInto = totalMilliSec/interval;
		
		int aStartTime = startTime;
		for (int i = 1; i<=12;i++)
		{
			result.add(new Interval(aStartTime+1, aStartTime+interval));
			aStartTime = aStartTime+interval ;
		}
		return result;
	}

	public List<Interval> breakDownHalf()
	{
		int divideInto = 2;
		List<Interval> result = new ArrayList<Interval>();
		
		int totalMilliSec = endTime - startTime;
		
		int interval = totalMilliSec/divideInto;
		
		int aStartTime = startTime;
		for (int i = 1; i<=2;i++)
		{
			result.add(new Interval(aStartTime+1, aStartTime+interval));
			aStartTime = aStartTime+interval ;
		}
		return result;
	}
}





