package com.bmc.traffic;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class MonitorTest
{

	@Test
	public void testNorthBound()
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
		assertEquals("Should be empty",carEntries.size(),1);
		CarEntry carEntry = carEntries.get(0);
		assertEquals( Direction.NorthBound,carEntry.getDirection());
		assertEquals(2, carEntry.getRecordEntries().size());
		assertEquals("Should be empty",monitor.getRecordEntries().size(),0);
	}


	@Test
	public void testSouthBound()
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
