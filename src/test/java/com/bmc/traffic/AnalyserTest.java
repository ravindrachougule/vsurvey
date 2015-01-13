package com.bmc.traffic;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;
import java.util.TreeSet;

import org.junit.Test;

import com.bmc.traffic.file.TrafficDataReader;

public class AnalyserTest
{

	@Test
	public void test() throws IOException
	{
		Monitor monitor = new Monitor();
		
		//given
		List<Record> records = TrafficDataReader.read("sampleData.txt");
		for (Record record: records){
			monitor.process(record);
		}
		
		//when
		Analyser analyser = new Analyser(monitor.getCarEntries());
		
		//then
		//A) Total Vehicle count in morning
		System.out.println(" ------------------ ");
		System.out.println(" Total Vehicle count in morning ");
		
		Interval morning = IntervalFactory.getMorning();
		Interval interval = null;
		for (int day=1;day<=5;day++)
		{
			interval = morning.advanceFor(day);
			System.out.print(" NorthBound Day[" + day + "]= " + analyser.getCarEntries(interval,Direction.NorthBound).size());
		}
		
		System.out.println();
		for (int day=1;day<=5;day++)
		{
			interval = morning.advanceFor(day);
			System.out.print(" SouthBound Day[" + day + "]= " + analyser.getCarEntries(interval,Direction.SouthBound).size());
		
		
		}
		System.out.println("\n \n");
		
		//B) Peak Volume
		TreeSet<TrafficInterval> peakIntervals = analyser.getPeak(morning,IntervalFactory.hourInterval,Direction.NorthBound);
		System.out.println("Peak at =" + peakIntervals.iterator().next() );
		
		//C) Distance between cars
		
		interval = IntervalFactory.getMorning();
		float distanceInMeter = analyser.getAvgDistance(interval,Direction.NorthBound);
		System.out.println("getAvgDistance (meters)= " + distanceInMeter );
		assertTrue(distanceInMeter > 1);
		
		System.out.println(" ------------------ ");
		
	}

	@Test
	public void testTrafficInterval()
	{
		TrafficInterval intervalPeak = new TrafficInterval(new Interval(0, 1), 2);
		TrafficInterval intervalFree = new TrafficInterval(new Interval(0, 1), 1);
		assertTrue(intervalPeak.compareTo(intervalFree) < 0);
		assertTrue(intervalFree.compareTo(intervalPeak) > 0);
		assertTrue(intervalFree.compareTo(intervalFree) == 0);
	}
}
