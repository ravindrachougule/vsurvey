package com.bmc.traffic;

import java.util.ArrayList;
import java.util.List;

public class Analyser {

	private List<CarEntry>  carEntries;
	public Analyser(List<CarEntry> carEntries) {
		this.carEntries = carEntries;
	}

	
	public List<CarEntry> getCarEntries(Interval interval, Direction direction) {

		List<CarEntry> result = new ArrayList<CarEntry>(); 
		for (CarEntry entry : carEntries)
		{
			if (interval.contains(entry.recordEntries.get(0).getTimestamp()) && entry.direction.equals(direction))
				result.add(entry);
			}

		return result;
	}
}
