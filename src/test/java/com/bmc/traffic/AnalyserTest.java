package com.bmc.traffic;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;
import java.util.TreeSet;

import org.junit.Test;

import com.bmc.traffic.file.TrafficDataReader;
import com.bmc.traffic.interval.Interval;
import com.bmc.traffic.interval.IntervalFactory;
import com.bmc.traffic.interval.TrafficInterval;
import com.bmc.traffic.reference.Direction;

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
		//A) a)Total Vehicle count in morning
		Interval interval;
		Interval getOneDayInterval = analyser.printDailyCount(analyser);

		//A) b) 20 min Interval
		List<Interval>twentyMinInterval = getOneDayInterval.breakDown(IntervalFactory.twentyMinInterval);
		assertFalse(twentyMinInterval.isEmpty());
		
		List<CarEntry> carEntriesInGivenTwentyMinutes = analyser.getCarEntries(twentyMinInterval.get(0),Direction.NorthBound);
		assertTrue(carEntriesInGivenTwentyMinutes.size()>0);
		System.out.println("carEntriesInGivenTwentyMinutes: ["+ twentyMinInterval.get(0) + "] =" +carEntriesInGivenTwentyMinutes.size() );

		//B) Peak Volume in which Interval
		TreeSet<TrafficInterval> peakIntervals = analyser.getPeak(getOneDayInterval,IntervalFactory.hourInterval,Direction.NorthBound);
		System.out.println("Peak at =" + peakIntervals.iterator().next() );

		//C) Distance between cars in given Morning
		interval = IntervalFactory.getMorning();
		float distanceInMeter = analyser.getAvgDistance(interval,Direction.NorthBound);
		System.out.println("getAvgDistance (meters)= " + distanceInMeter );
		assertTrue(distanceInMeter > 1);


	}

	


}
