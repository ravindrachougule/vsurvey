package com.bmc.traffic;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class IntervalTest {

	private static final int OneHour = 3600000;

	public static final int HalfHour = OneHour/2;

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

	@Test
	public void testHourlyBreakDown()
	{
		//given
		Interval morning = IntervalFactory.getMorning();

		//when
		List<Interval>hourlyIntervals = morning.breakDown(IntervalFactory.hourInterval);

		assertEquals(12, hourlyIntervals.size());
		Interval firstHour = hourlyIntervals.get(0);
		assertTrue(hourlyIntervals.get(0).contains(IntervalTest.OneHour));
		assertFalse(hourlyIntervals.get(0).contains(IntervalTest.OneHour+1));

		assertFalse(hourlyIntervals.get(3).contains(IntervalTest.OneHour*3-1));
		assertTrue(hourlyIntervals.get(3).contains(IntervalTest.OneHour*3+1));
		assertTrue(hourlyIntervals.get(3).contains(IntervalTest.OneHour*4));
		assertFalse(hourlyIntervals.get(3).contains(IntervalTest.OneHour*4+1));

		List<Interval>halfHourly = firstHour.breakDown(IntervalFactory.halfHourInterval);
		assertEquals(2, halfHourly.size());
		assertTrue(halfHourly.get(0).contains(IntervalTest.HalfHour));
		assertFalse(halfHourly.get(0).contains(IntervalTest.HalfHour+1));
		assertTrue(halfHourly.get(1).contains(IntervalTest.OneHour-1));
		assertFalse(halfHourly.get(1).contains(IntervalTest.OneHour+1));
		
		List<Interval>twentyMinInterval = firstHour.breakDown(IntervalFactory.twentyMinInterval);
		assertEquals(3, twentyMinInterval.size());
		assertTrue(twentyMinInterval.get(0).contains(IntervalFactory.twentyMinInterval-1));
		assertFalse(twentyMinInterval.get(0).contains(IntervalFactory.twentyMinInterval+1));
		assertTrue(twentyMinInterval.get(1).contains(IntervalFactory.twentyMinInterval+1));
		
		
	}
}
