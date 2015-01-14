package com.bmc.traffic.file;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.bmc.traffic.Record;
import com.bmc.traffic.reference.Sensor;

public class TrafficDataReaderTest
{

	@Test
	public void testErrorPath() throws IOException
	{
		//given
		String path ="a.txt";
		
		//when
		try{
		List<Record> records = TrafficDataReader.read(path);
		fail("Must throw exception");
		}catch(Exception e)
		{
			
		}
		//then
	}
	

	@Test
	public void testCorrectPath() throws IOException
	{
		//given path
		
		
		//when
		List<Record> records = TrafficDataReader.read(TrafficDataReader.path);
		
		//then
		assertEquals(67296, records.size());
		assertEquals(new Record(Sensor.A,85430817), records.get(67261));
		assertEquals(new Record(Sensor.A,85488419), records.get(67262));
	}

	@Test
	public void testParseSingleLineInput()
	{
		//given
		String input = "B604960";
		
		//when
		Record record =  TrafficDataReader.parse(input);
		
		//then
		assertEquals(new Record(Sensor.B,604960), record);
	}
}
