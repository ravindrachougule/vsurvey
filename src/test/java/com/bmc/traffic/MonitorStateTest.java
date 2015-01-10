package com.bmc.traffic;

import static org.junit.Assert.*;

import org.junit.Test;

public class MonitorStateTest
{

	@Test
	public void testNorthBoundCar()
	{
		//Given
		Monitor monitor = new Monitor();

		//when
		monitor.getState().processSenorAEntry();

		//then
		assertEquals(monitor.getaPassedState(), monitor.getState());

		//when
		monitor.getState().processSenorAEntry();

		//then
		assertEquals(monitor.getNoCarYetPassedState(), monitor.getState());

	}
	
	@Test
	public void testSouthBoundCarWithTwoSenors()
	{
		//Given
		Monitor monitor = new Monitor();

		//when
		monitor.getState().processSenorAEntry();

		//then
		assertEquals(monitor.getaPassedState(), monitor.getState());

		//when
		monitor.getState().processSenorBEntry();

		//then
		assertEquals(monitor.getAbPassedState(), monitor.getState());
		
		
		//AB-AB
		//when
		monitor.getState().processSenorAEntry();

		//then
		assertEquals(monitor.getAbaPassedState(), monitor.getState());

		//when
		monitor.getState().processSenorBEntry();

		//then
		assertEquals(monitor.getNoCarYetPassedState(), monitor.getState());

	}

	@Test
	public void testFirstEntryB_INVALID()
	{
		//Given
		Monitor monitor = new Monitor();

		//when
		try{
		monitor.getState().processSenorBEntry();
		fail("Should throw error");
		}
		catch(RuntimeException r){
			
		}
		//then
	}
	
	
	@Test
	public void testSecondEntryB_afterABINVALID()
	{
		//Given
		Monitor monitor = new Monitor();
		monitor.getState().processSenorAEntry();
		monitor.getState().processSenorBEntry();
		
		//when
		try{
		monitor.getState().processSenorBEntry();
		fail("Should throw error");
		}
		catch(RuntimeException r){
			
		}
		//then
	}
	
	@Test
	public void testThirdEntryA_afterABAINVALID()
	{
		//Given
		Monitor monitor = new Monitor();
		monitor.getState().processSenorAEntry();
		monitor.getState().processSenorBEntry();
		monitor.getState().processSenorAEntry();
		
		//when
		try{
		monitor.getState().processSenorAEntry();
		fail("Should throw error");
		}
		catch(RuntimeException r){
			
		}
		//then
	}
}
