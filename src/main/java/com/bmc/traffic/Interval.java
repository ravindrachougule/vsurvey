package com.bmc.traffic;

import java.sql.Date;

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
}





