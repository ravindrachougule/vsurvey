package com.bmc.traffic;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.bmc.traffic.interval.Interval;
import com.bmc.traffic.interval.IntervalFactory;
import com.bmc.traffic.reference.Direction;
import com.bmc.traffic.reference.Sensor;

public class MonitorTest
{

	@Test
	public void testNorthBoundMovement()
	{
		//Given
		Monitor monitor = new Monitor();
		Record recordFirstSensorFrontAxel = new Record(Sensor.A,268981);
		
		//when
		monitor.process(recordFirstSensorFrontAxel);
		
		//then
		List<CarEntry> carEntries = monitor.getCarEntries();
		assertEquals("Should be empty",carEntries.size(),0);
		
		//when
		Record recordFirstSenorRearAxel = new Record(Sensor.A,269123);
		monitor.process(recordFirstSenorRearAxel);
		
		//then
		assertEquals(carEntries.size(),1);
		CarEntry carEntry = carEntries.get(0);
		assertEquals( Direction.NorthBound,carEntry.getDirection());
		assertEquals(2, carEntry.getRecordEntries().size());
		assertEquals("Should be empty",monitor.getRecordEntries().size(),0);
		
		//given another car
		recordFirstSensorFrontAxel = new Record(Sensor.A,499718);
		recordFirstSenorRearAxel = new Record(Sensor.A,499886);
		
		//when
		monitor.process(recordFirstSensorFrontAxel);
		monitor.process(recordFirstSenorRearAxel);
		Interval morning = IntervalFactory.getMorning();
		Interval evening = IntervalFactory.getEvening();
		
		//then
		Analyser analyser = new Analyser(monitor.getCarEntries());
		assertEquals(2,analyser.getCarEntries(morning,Direction.NorthBound).size());
		assertEquals(0,analyser.getCarEntries(morning,Direction.SouthBound).size());
		assertEquals(0,analyser.getCarEntries(evening,Direction.SouthBound).size());
		
		
		//given we go into next day(Day 2).
		recordFirstSensorFrontAxel = new Record(Sensor.A,268981);
		recordFirstSenorRearAxel = new Record(Sensor.A,269123);
		monitor.process(recordFirstSensorFrontAxel);
		monitor.process(recordFirstSenorRearAxel);
		
		recordFirstSensorFrontAxel = new Record(Sensor.A,288981);
		recordFirstSenorRearAxel = new Record(Sensor.A,289123);
		monitor.process(recordFirstSensorFrontAxel);
		monitor.process(recordFirstSenorRearAxel);
		
		//then pass next day interval
		assertEquals(2,analyser.getCarEntries(morning.advanceFor(2),Direction.NorthBound).size());
		assertEquals(0,analyser.getCarEntries(morning.advanceFor(2),Direction.SouthBound).size());
		assertEquals(0,analyser.getCarEntries(morning.advanceFor(2),Direction.SouthBound).size());
		
		//check second cars timing
		assertEquals(recordFirstSensorFrontAxel.getTimestamp() + IntervalFactory.getOneDayInterval().getEndTime(),
				analyser.getCarEntries(morning.advanceFor(2),Direction.NorthBound).get(1).recordEntries.get(0).getTimestamp());
		
		
		//given we got to third day
		recordFirstSensorFrontAxel = new Record(Sensor.A,2);
		recordFirstSenorRearAxel = new Record(Sensor.A,124);
		monitor.process(recordFirstSensorFrontAxel);
		monitor.process(recordFirstSenorRearAxel);
		//then pass next day interval
		assertEquals(1,analyser.getCarEntries(morning.advanceFor(3),Direction.NorthBound).size());
		assertEquals(0,analyser.getCarEntries(morning.advanceFor(3),Direction.SouthBound).size());
		assertEquals(0,analyser.getCarEntries(morning.advanceFor(3),Direction.SouthBound).size());
		
		assertEquals(recordFirstSensorFrontAxel.getTimestamp() + IntervalFactory.getOneDayInterval().getEndTime() *2,
				analyser.getCarEntries(morning.advanceFor(3),Direction.NorthBound).get(0).recordEntries.get(0).getTimestamp());
		
		analyser.printDailyCount(analyser);
	}


	@Test
	public void testSouthBoundMovement()
	{
		//Given
		Monitor monitor = new Monitor();
		Record recordFirstSensorFrontAxel = new Record(Sensor.A,604957);
		
		//when
		monitor.process(recordFirstSensorFrontAxel);
		
		//then
		List<CarEntry> carEntries = monitor.getCarEntries();
		assertEquals("Should be empty",carEntries.size(),0);
		
		//when
		Record recordSecondSensorRearAxel = new Record(Sensor.B,604960);
		monitor.process(recordSecondSensorRearAxel);
		
		Record recordFirstSensorRearAxel = new Record(Sensor.A,605128);
		monitor.process(recordFirstSensorRearAxel);
		
		Record recordSecondSensorRealAxel = new Record(Sensor.B,605132);
		monitor.process(recordSecondSensorRealAxel);
		
		//then
		assertEquals("Should be empty",carEntries.size(),1);
		CarEntry carEntry = carEntries.get(0);
		assertEquals( Direction.SouthBound,carEntry.getDirection());
		assertEquals(4, carEntry.getRecordEntries().size());
		assertEquals("Should be empty",monitor.getRecordEntries().size(),0);
	}
}
