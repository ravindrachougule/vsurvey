package com.bmc.traffic;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class MonitorTest
{

	@Test
	public void testNorthBoundMovement()
	{
		//Given
		Monitor monitor = new Monitor();
		Record recordAOne = new Record(Sensor.A,268981);
		
		//when
		monitor.process(recordAOne);
		
		//then
		List<CarEntry> carEntries = monitor.getCarEntries();
		assertEquals("Should be empty",carEntries.size(),0);
		
		//when
		Record recordAtwo = new Record(Sensor.A,269123);
		monitor.process(recordAtwo);
		
		//then
		assertEquals(carEntries.size(),1);
		CarEntry carEntry = carEntries.get(0);
		assertEquals( Direction.NorthBound,carEntry.getDirection());
		assertEquals(2, carEntry.getRecordEntries().size());
		assertEquals("Should be empty",monitor.getRecordEntries().size(),0);
		
		//given another car
		recordAOne = new Record(Sensor.A,499718);
		recordAtwo = new Record(Sensor.A,499886);
		
		//when
		monitor.process(recordAOne);
		monitor.process(recordAtwo);
		Interval morning = IntervalFactory.getMorning();
		Interval evening = IntervalFactory.getEvening();
		
		//then
		Analyser analyser = new Analyser(monitor.getCarEntries());
		assertEquals(2,analyser.getCarEntries(morning,Direction.NorthBound).size());
		assertEquals(0,analyser.getCarEntries(morning,Direction.SouthBound).size());
		assertEquals(0,analyser.getCarEntries(evening,Direction.SouthBound).size());
		
		
		//given we go into next day(Day 2).
		recordAOne = new Record(Sensor.A,268981);
		recordAtwo = new Record(Sensor.A,269123);
		monitor.process(recordAOne);
		monitor.process(recordAtwo);
		
		//then pass next day interval
		assertEquals(1,analyser.getCarEntries(morning.advanceFor(2),Direction.NorthBound).size());
		assertEquals(0,analyser.getCarEntries(morning.advanceFor(2),Direction.SouthBound).size());
		assertEquals(0,analyser.getCarEntries(morning.advanceFor(2),Direction.SouthBound).size());
		
		
		//given we got to third day
		recordAOne = new Record(Sensor.A,2);
		recordAtwo = new Record(Sensor.A,124);
		monitor.process(recordAOne);
		monitor.process(recordAtwo);
		//then pass next day interval
		assertEquals(1,analyser.getCarEntries(morning.advanceFor(3),Direction.NorthBound).size());
		assertEquals(0,analyser.getCarEntries(morning.advanceFor(3),Direction.SouthBound).size());
		assertEquals(0,analyser.getCarEntries(morning.advanceFor(3),Direction.SouthBound).size());
		
		//test Analyser out put
		List<Interval>twentyMinInterval = morning.breakDown(IntervalFactory.twentyMinInterval);
		
		for (Interval interval : twentyMinInterval){
			carEntries = analyser.getCarEntries(interval,Direction.NorthBound);
			if (!carEntries.isEmpty())
			System.out.println("interval: " + interval + " : [" + carEntries.size()  + "]"+carEntries  + " : " + Direction.NorthBound);
			
			carEntries = analyser.getCarEntries(interval,Direction.SouthBound);
			if (!carEntries.isEmpty())
			System.out.println("interval: " + interval + " : [" + carEntries.size()  + "]"+carEntries  + " : " + Direction.SouthBound);
			
		}
	}


	@Test
	public void testSouthBoundMovement()
	{
		//Given
		Monitor monitor = new Monitor();
		Record recordAOne = new Record(Sensor.A,604957);
		
		//when
		monitor.process(recordAOne);
		
		//then
		List<CarEntry> carEntries = monitor.getCarEntries();
		assertEquals("Should be empty",carEntries.size(),0);
		
		//when
		Record recordBOne = new Record(Sensor.B,604960);
		monitor.process(recordBOne);
		
		Record recordATwo = new Record(Sensor.A,605128);
		monitor.process(recordATwo);
		
		Record recordBTwo = new Record(Sensor.B,605132);
		monitor.process(recordBTwo);
		
		//then
		assertEquals("Should be empty",carEntries.size(),1);
		CarEntry carEntry = carEntries.get(0);
		assertEquals( Direction.SouthBound,carEntry.getDirection());
		assertEquals(4, carEntry.getRecordEntries().size());
		assertEquals("Should be empty",monitor.getRecordEntries().size(),0);
	}
}
