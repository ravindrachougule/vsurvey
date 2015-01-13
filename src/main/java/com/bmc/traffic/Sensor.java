package com.bmc.traffic;

public enum Sensor
{
	A("A"),B("B");
	
	private String name;
	private Sensor (final String name){
		this.name = name;
	}
	
	@Override
    public String toString() {
        return name;
    }
}
