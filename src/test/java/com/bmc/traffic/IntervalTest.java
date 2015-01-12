package com.bmc.traffic;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class IntervalTest {

	@Test
	public void testMorning() {
		//given
		int earlyMorning = 268981;
		int evening4PM = 64800000;
		
		Interval morning = IntervalFactory.getMorning();
		Interval evening = IntervalFactory.getEvening();
		
		//then
		assertTrue(morning.contains(earlyMorning));
		assertFalse(morning.contains(evening4PM));
		assertTrue(evening.contains(evening4PM));
		
	}
	
	@Test
	public void testDistribution()
	{
		//given
		 List<CarEntry> carEntries = new ArrayList<CarEntry>();
		 
		 List<Record> records = new ArrayList<Record>();
		 records.add(new Record(Sensor.A,268981));
		 carEntries.add(new CarEntry(Direction.NorthBound,records));
	}
	
	@Test
	public void testWeekDayInterval()
	{
		Interval dayOneMorning = IntervalFactory.getMorning();
		Interval dayTwoMorning = IntervalFactory.getMorning().advanceFor(2);
		assertEquals(dayOneMorning.getEndTime()+IntervalFactory.getOneDayInterval(),dayTwoMorning.getEndTime());
		
		Interval day4Morning = IntervalFactory.getMorning().advanceFor(4);
		assertEquals(IntervalFactory.getOneDayInterval()*3, day4Morning.getStartTime()- dayOneMorning.getStartTime());
	}

}
