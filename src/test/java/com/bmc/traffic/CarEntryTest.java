package com.bmc.traffic;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CarEntryTest {



	@Test
	public void testDistance()
	{
		//given

		List<Record> records = new ArrayList<Record>();
		records.add(new Record(Sensor.A,268981));
		
		CarEntry carEntryOne = new CarEntry(Direction.NorthBound,records);
		
		records = new ArrayList<Record>();
		records.add(new Record(Sensor.A,288981));
		CarEntry carEntryTwo = new CarEntry(Direction.NorthBound,records);
		
		//when
		float distanceInMeter = carEntryTwo.distance(carEntryOne);
		
		//then
		assertTrue(distanceInMeter == 333f);
	}
}
