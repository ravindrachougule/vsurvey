package com.bmc.traffic.monitorState;

import static org.junit.Assert.*;

import org.junit.Test;

import com.bmc.traffic.Monitor;

public class MonitorStateTest
{

	@Test
	public void testNorthBoundCar()
	{
		//Given
		Monitor monitor = new Monitor();

		//when
		monitor.processSenorAEntry();

		//then
		assertEquals(monitor.getFrontAxelPassedState(), monitor.getState());

		//when
		monitor.processSenorAEntry();

		//then
		assertEquals(monitor.getInitialState(), monitor.getState());

	}
	
	@Test
	public void testSouthBoundCarWithTwoSenors()
	{
		//Given
		Monitor monitor = new Monitor();

		//when
		monitor.processSenorAEntry();

		//then
		assertEquals(monitor.getFrontAxelPassedState(), monitor.getState());

		//when
		monitor.processSenorBEntry();

		//then
		assertEquals(monitor.getFrontAxelPassedBothSensorsState(), monitor.getState());
		
		
		//AB-AB
		//when
		monitor.processSenorAEntry();

		//then
		assertEquals(monitor.getRearAxelPassedFirstSensorState(), monitor.getState());

		//when
		monitor.processSenorBEntry();

		//then
		assertEquals(monitor.getInitialState(), monitor.getState());

	}

	/**
	 * Does not support if two cars travel over the sensors and sequence get interlaced.
	 */
	@Test
	public void testFirstEntryB_INVALID()
	{
		//Given
		Monitor monitor = new Monitor();

		//when
		try{
		monitor.processSenorBEntry();
		fail("Should throw error");
		}
		catch(RuntimeException r){
			
		}
	}
	
	
	@Test
	public void testSecondEntryB_afterABINVALID()
	{
		//Given
		Monitor monitor = new Monitor();
		monitor.processSenorAEntry();
		monitor.processSenorBEntry();
		
		//when
		try{
		monitor.processSenorBEntry();
		fail("Should throw error");
		}
		catch(RuntimeException r){
			
		}
	}
	
	@Test
	public void testThirdEntryA_afterABAINVALID()
	{
		//Given
		Monitor monitor = new Monitor();
		monitor.processSenorAEntry();
		monitor.processSenorBEntry();
		monitor.processSenorAEntry();
		
		//when
		try{
		monitor.processSenorAEntry();
		fail("Should throw error");
		}
		catch(RuntimeException r){
			
		}
	}
}
