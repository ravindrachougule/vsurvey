package com.bmc.traffic;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

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
	}

}
